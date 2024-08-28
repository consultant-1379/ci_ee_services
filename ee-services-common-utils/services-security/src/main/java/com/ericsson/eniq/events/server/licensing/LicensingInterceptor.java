/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.Iterator;
import java.util.Vector;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.common.exception.CannotAccessLicensingServiceException;
import com.ericsson.eniq.events.server.common.exception.NoLicenseException;
import com.ericsson.eniq.events.server.common.exception.ServiceException;
import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.licensing.cache.LicenseInformation;

/**
 * Interceptor to check that appropriate license(s) exists before returning meta data
 * <p/>
 * Various elements within the meta data can be licensed, see java doc on the checkLicenses() method
 *
 * @author EEMECOY
 */
public class LicensingInterceptor {

    @EJB
    private LicensingService licensingService;

    final static String ENIQ_EVENTS_STARTER_LICENSE_CXC = "CXC4011426";
    
    final static String DV_THROUGHPUT_LICENSE_CXC = "CXC4011159";
    
    final static String DV_THROUGHPUT_LICENCE_TYPE = "DV Throughput";

    private final static String LICENSE_LIST_SEPARATOR = ",";

    @AroundInvoke
    /**
     * Check licensing information
     *
     * This will run the following license checks:
     *
     * <li>check that the ENIQ Events Starter License exists - if not, a NoLicenseException will be thrown</li>
     *
     * <li>Check all json elements at the top level in the meta data file eg the tabs and kpiPanel elements
     * If any of the elements within these top level objects have the licenseCXC field, these licenses will be checked
     * and any unlicensed elements removed from the meta data.  If the parent top level JSON element is left with no 
     * licensed elements, this element is also removed from the overall meta data object.
     * This functionality spans both the kpiPanel element (shouldn't be present if there is no KPI license in place)
     * and the tab elements (each tab has a corresponding licenseCXC field, which will be checked) 
     * </li> 
     *
     * <li> Check that one or more tabs is licensed - if not, a NoLicenseException is thrown</li>
     *
     * <li>For each grid in the UI meta data, it will check that if any licenses are required for any of the grid's columns,
     * that these licenses exist (if the required license doesn't exist, that column is marked with isSystem=true so that
     * the UI will hide it)</li>
     *
     * <li>For each menu item, check if it has a license entry (e.g.  "licenseCXC" : "CXC4010911"), if it does then check if the mentioned license 
     * (or at least 1 of comma separated list of licenses) is installed. If a menu has a license entry and no corresponding license is installed,
     * then remove the menu item from the meta data that is returned to the UI. (This facilitates licensing individual features in the UI).  If this 
     * results in an empty menu, then the parent menu button is removed. <li>
     *
     *
     * @param invocationContext         provided by Java EE's Interceptors - this contains all information on the method call - parameters, method
     *                                  It also allows to call the method itself
     *
     * @return Object                   return's the meta data, with any unlicensed features/tabs/columns filtered out or marked as should be hidden in UI
     */
    public Object checkLicenses(final InvocationContext invocationContext) throws NoLicenseException, ServiceException,
            CannotAccessLicensingServiceException, JSONException {
        if (licensingService.hasLicense(ENIQ_EVENTS_STARTER_LICENSE_CXC)) {
            final String unfilteredMetaData = fetchUnfilteredMetaData(invocationContext);
            return filterUnlicensedFeatures(unfilteredMetaData);
        }
        throw new NoLicenseException("No license found");
    }

    private Object filterUnlicensedFeatures(final String unfilteredMetaData) throws JSONException,
            CannotAccessLicensingServiceException, NoLicenseException {
        final JSONObject filteredJsonObject = new JSONObject();
        filterTopLevelJSON(unfilteredMetaData, filteredJsonObject);
        if (noTabsLicensed(filteredJsonObject)) {
            throw new NoLicenseException("No valid feature licenses exist");
        }
        updateUnlicensedColumnsInGrids(filteredJsonObject);
        filterUnlicensedMenuItems(filteredJsonObject);
        filterUnlicensedSearchFields(filteredJsonObject);
        filterUnlicensedDashboardPortlets(filteredJsonObject);
        filterUnlicensedMenuFromViewButton(filteredJsonObject);

        addLicenseInformationToMetadata(filteredJsonObject);
        return filteredJsonObject.toString();
    }

    private void addLicenseInformationToMetadata(final JSONObject jsonObject) throws JSONException {
        final JSONArray licenseArray = new JSONArray();
        try {
            final Vector<LicenseInformation> licenses = licensingService.getLicenseInformation();
            for (final LicenseInformation licenseInformation : licenses) {
                final JSONObject licenseInformationObject = new JSONObject();
                licenseInformationObject.put(FEATURE_NAME, licenseInformation.getFeatureName());
                licenseInformationObject.put(DESCRIPTION, licenseInformation.getDescription());
                licenseArray.put(licenseInformationObject);
            }
            jsonObject.put(LICENSES, licenseArray);
            return;
        } catch (final CannotAccessLicensingServiceException e) {
            ServicesLogger.warn("LicensingInterceptor", "addLicenseInformationToMetadata", e.getMessage());
            jsonObject.put(LICENSES, licenseArray);
        }

    }

    private String fetchUnfilteredMetaData(final InvocationContext invocationContext) {
        try {
            return (String) invocationContext.proceed();
        } catch (final Exception e) {
            ServicesLogger.warn(getClass().getName(), "checkLicenses", "Problem running service", e);
            throw new ServiceException(e);
        }

    }

    private boolean noTabsLicensed(final JSONObject jsonObj) {
        return !jsonObj.has(TABS);
    }

    /**
     * Check for the "licenseCXC" field in the top level JSON elements
     * Each top level JSON element consists of a key and an array of JSON objects.
     * Each element in the array of JSON objects is checked - if the licenseCXC field is included in that element,
     * the specificed license is checked.  If the license doesn't exist, that element is stripped from the array of
     * JSON elements
     * If no licenseCXC field is specified, its assumed that the element is licensed
     * <p/>
     * Additionally, if after the licensing checks, a top level JSON element is left with an empty array, the top
     * level JSON element is stripped from the parent meta data JSON object
     *
     * @param unfilteredMetaData the unfiltered meta data as read from the UIMetaData.json files
     * @param filteredJsonObject holder for the filtered json
     */
    private void filterTopLevelJSON(final String unfilteredMetaData, final JSONObject filteredJsonObject)
            throws JSONException, CannotAccessLicensingServiceException {
        final JSONObject unfilteredMetaDataObject = new JSONObject(unfilteredMetaData);
        final Iterator<Object> iterator = unfilteredMetaDataObject.keys();
        while (iterator.hasNext()) {
            final String jsonKey = (String) iterator.next();
            final Object object = unfilteredMetaDataObject.get(jsonKey);
            if (object instanceof JSONArray) {
                final JSONArray objectArr = (JSONArray) object;
                final JSONArray filteredArray = filterUnLicensedElements(objectArr);
                if (filteredArray.length() > 0) {
                    filteredJsonObject.put(jsonKey, filteredArray);
                }
            } else {
                filteredJsonObject.put(jsonKey, object);
            }
        }
    }

    private JSONArray filterUnLicensedElements(final JSONArray object) throws JSONException,
            CannotAccessLicensingServiceException {
        final JSONArray filteredObject = new JSONArray();
        for (int i = 0; i < object.length(); i++) {
            final JSONObject element = (JSONObject) object.get(i);
            if (isElementLicensed(element)) {
                filteredObject.put(element);
            }
        }
        return filteredObject;
    }

    /**
     * Check if an individual JSON meta data element is licensed
     * eg
     * {
     * "id": "NETWORK_KPI_ALARM_PANEL",
     * "name": "Kpi Alarm Panel",
     * "licenseCXC": "CXC4011279",
     * "isRoleEnabled": "TRUE",
     * "url": "NETWORK/KPI_NOTIFICATION/NOTIFICATION_COUNT",
     * "style": "kpiAlarmPanel",
     * "isEnabled": "TRUE",
     * "notificationSeverities": [
     * {
     * "severity": "Critical",
     * "menuItem": "NETWORK_KPI_NOTIFICATION_CRITICAL_DATA",
     * "popUpMessage": "Critical KPI Warning"
     * }
     * ]
     * }
     * If the element has the licenseCXC field present, then the license specified in that field is checked
     * If that license exists, true is returned
     * If the license doesn't exist, false is returned
     * <p/>
     * If no licenseCXC field exists, its assumed the feature is licensed, and true is returned
     *
     * @param metaDataElement json meta data element
     *
     * @return boolean                  see above
     */
    private boolean isElementLicensed(final JSONObject metaDataElement) throws CannotAccessLicensingServiceException,
            JSONException {
        if (metaDataElement.has(LICENSE_CXC)) {
            final String licenseCXC = (String) metaDataElement.get(LICENSE_CXC);
            if (StringUtils.isNotEmpty(licenseCXC) && licensingService.hasLicense(licenseCXC)) {
                return true;
            }
            return false;
        }
        return true;

    }

    /**
     * Check if an individual column drilldown/launch window JSON
     * meta data element is licensed
     * eg
     * {
     * "header" : "Controller",
     * "width" : "50",
     * "datatype" : "string",
     * "drillDownLicenseCXC" : "CXC4010927"
     * "launchWindowType":"NETWORK_ENODEB_RANKING_DRILL_ON_RAN_LTE_CFA_CALL_SETUP_DROP"
     * }
     * <p/>
     * OR
     * <p/>
     * {
     * "header": "Failures",
     * "width": "70",
     * "datatype": "int",
     * "drillDownLicenseCXC" : "CXC4010927"
     * "drillDownWindowType": "NETWORK_EVENT_ANALYSIS_WCDMA_HFA_SUMMARY_CELL"
     * }
     * <p/>
     * If the element has the drillDownLicenseCXC field present, then the license specified in that field is checked
     * If that license exists, true is returned
     * If the license doesn't exist, false is returned
     * <p/>
     * If no drillDownLicenseCXC field exists, its assumed the feature is licensed, and true is returned
     *
     * @param metaDataElement json meta data element
     *
     * @return boolean                  see above
     */
    private boolean isDrillDownElementLicensed(final JSONObject metaDataElement)
            throws CannotAccessLicensingServiceException, JSONException {
        if (metaDataElement.has(DRILLDOWN_LICENSE_CXC)) {
            final String licenseCXC = (String) metaDataElement.get(DRILLDOWN_LICENSE_CXC);
            if (StringUtils.isNotEmpty(licenseCXC) && licensingService.hasLicense(licenseCXC)) {
                return true;
            }
            return false;
        }
        return true;

    }

    private void filterUnlicensedDashboardPortlets(final JSONObject metaDataObject) throws JSONException,
            NoLicenseException, CannotAccessLicensingServiceException {
        if (metaDataObject.has("dashboards")) {
            final JSONArray dashboardDataArray = metaDataObject.getJSONArray("dashboards");

            // Check all dashboards
            for (int i = 0; i < dashboardDataArray.length(); i++) {
                final JSONObject dashboardData = (JSONObject) dashboardDataArray.get(i);

                if (dashboardData != null && dashboardData.has("items")) {
                    final JSONArray licensedPortlets = new JSONArray();
                    final JSONArray dashboardItems = dashboardData.getJSONArray("items");

                    for (int j = 0; j < dashboardItems.length(); j++) {
                        final JSONObject portlet = (JSONObject) dashboardItems.get(j);
                        if (isElementLicensed(portlet)) {
                            licensedPortlets.put(portlet);
                        }
                    }

                    if (licensedPortlets.length() == 0) {
                        ServicesLogger.warn(getClass().getName(), "filterUnlicensedDashboardPortlets",
                                "No valid portlet licenses found");
                        throw new NoLicenseException("No valid portlet licenses exist");
                    }
                    dashboardData.put("items", licensedPortlets);
                }
            }
        }
    }

    /**
     * Skip(remove) any menu items that contain a license entry (e.g.  "licenseCXC" : "CXC4010911"), unless the
     * license is installed.
     * A menu item is an item contained in:  tabMenuItems .. taskBarButtons .. items (and sub lists of items).
     */
    private void filterUnlicensedMenuItems(final JSONObject metaDataJsonObject) throws JSONException,
            CannotAccessLicensingServiceException {
        final JSONArray tabMenuItems = (JSONArray) metaDataJsonObject.get(TAB_MENU_ITEMS);
        for (int tabMenuItemIndex = 0; tabMenuItemIndex < tabMenuItems.length(); tabMenuItemIndex++) {
            final JSONObject tabMenuItem = (JSONObject) tabMenuItems.get(tabMenuItemIndex);
            final JSONArray taskBarButtons = (JSONArray) tabMenuItem.get(TASKBAR_BUTTONS);
            for (int menuItemIndex = 0; menuItemIndex < taskBarButtons.length();) {
                final JSONObject menuItem = (JSONObject) taskBarButtons.get(menuItemIndex);
                if (!removeUnlicensedMenuItems(taskBarButtons, menuItemIndex, menuItem)) {
                    menuItemIndex++;//only increment the index if nothing is removed (removing an item reduces the array size by 1 
                }
            }
            removeEmptyMenus(taskBarButtons, tabMenuItem, tabMenuItemIndex);
        }
    }

    private void filterUnlicensedMenuFromViewButton(final JSONObject metaDataJsonObject) throws JSONException {
        final JSONArray toolBars = (JSONArray) metaDataJsonObject.get("toolBars");
        for (int i = 0; i < toolBars.length(); i++) {
            final JSONObject toolBar = (JSONObject) toolBars.get(i);
            final JSONArray panels = (JSONArray) toolBar.get("panels");
            for (int j = 0; j < panels.length(); j++) {
                final JSONObject panel = (JSONObject) panels.get(j);
                final JSONArray toolBarButtons = (JSONArray) panel.get("toolBarButtons");

                for (int k = 0; k < toolBarButtons.length(); k++) {
                    final JSONObject toolBarButton = (JSONObject) toolBarButtons.get(k);
                    if (toolBarButton.has("items")) {
                        final JSONArray items = (JSONArray) toolBarButton.get("items");
                        for (int l = 0; l < items.length();) {
                            final JSONObject item = (JSONObject) items.get(l);
                            if (!removeUnlicensedMenuItems(items, l, item)) {
                                l++;//only increment the index if nothing is removed (removing an item reduces the array size by 1 
                            }
                        }
                        removeEmptyMenus(items, toolBarButton, k);
                    }
                }
            }

        }
    }

    /**
     * Skip(remove) any search Fields that contain a license entry (e.g.  "licenseCXC" : "CXC4010911"), unless the
     * license is installed.
     * A searchFields type is an type contained in:  searchFields .. items (and sub lists of items).
     */
    private void filterUnlicensedSearchFields(final JSONObject metaDataJsonObject) throws JSONException,
            CannotAccessLicensingServiceException {
        final JSONArray searchFields = (JSONArray) metaDataJsonObject.get(SEARCH_FIELDS);
        for (int searchFieldsTypeIndex = 0; searchFieldsTypeIndex < searchFields.length(); searchFieldsTypeIndex++) {
            final JSONObject searchFieldsTypes = (JSONObject) searchFields.get(searchFieldsTypeIndex);
            if (searchFieldsTypes.has(TYPE)) {
                final JSONArray types = (JSONArray) searchFieldsTypes.get(TYPE);
                for (int typeIndex = 0; typeIndex < types.length();) {
                    final JSONObject typeItem = (JSONObject) types.get(typeIndex);
                    if (!removeUnlicensedSearchFields(types, typeIndex, typeItem)) {
                        typeIndex++;//only increment the index if nothing is removed (removing an item reduces the array size by 1 
                    }
                }
                removeEmptyMenus(types, searchFieldsTypes, searchFieldsTypeIndex);
            }
        }
    }

    private boolean removeUnlicensedMenuItems(final JSONArray containingJSONArray, final int menuItemIndex,
            final JSONObject menuItem) throws JSONException, CannotAccessLicensingServiceException {
        if (!isMenuItemLicensed(menuItem)) {
            containingJSONArray.remove(menuItemIndex);
            return true;//item removed
        }
        if (menuItem.has(ITEMS)) { //sub menu items
            final JSONArray subMenuItems = (JSONArray) menuItem.get(ITEMS);
            for (int subMenuIndex = 0; subMenuIndex < subMenuItems.length();) {
                //recursion to handle menu items containing sub menus which contain sub sub menus and so on.
                if (!removeUnlicensedMenuItems(subMenuItems, subMenuIndex, (JSONObject) subMenuItems.get(subMenuIndex))) {
                    subMenuIndex++;//only increment the index if nothing is removed (removing an item reduces the array size by 1)
                }
            }
            if (removeEmptyMenus(containingJSONArray, menuItem, menuItemIndex)) {
                return true;//item removed
            }
        }
        return false;//no item removed
    }

    /**
     * This function is used to remove the unlicensed Search Fields.
     * When the searchFields type is not licensed, this will be removed. (For dashboard searchFields licence UT)
     *
     * @param types
     * @param typeIndex
     * @param typeItem
     *
     * @return
     *
     * @throws JSONException
     * @throws CannotAccessLicensingServiceException
     *
     */
    private boolean removeUnlicensedSearchFields(final JSONArray types, final int typeIndex, final JSONObject typeItem)
            throws JSONException, CannotAccessLicensingServiceException {
        //can reuse the isMenuItemLicensed here.
        if (!isMenuItemLicensed(typeItem)) {
            types.remove(typeIndex);
            return true;//item removed
        }
        if (typeItem.has(TYPE)) { //sub menu items
            final JSONArray subTypes = (JSONArray) typeItem.get(TYPE);
            for (int subTypesIndex = 0; subTypesIndex < subTypes.length();) {
                //recursion to handle menu items containing sub menus which contain sub sub menus and so on.
                if (!removeUnlicensedSearchFields(subTypes, subTypesIndex, (JSONObject) subTypes.get(subTypesIndex))) {
                    subTypesIndex++;//only increment the index if nothing is removed (removing an item reduces the array size by 1)
                }
            }
            if (removeEmptyMenus(types, typeItem, typeIndex)) {
                return true;//item removed
            }
        }
        return false;//no item removed
    }

    private boolean isMenuItemLicensed(final JSONObject menuItem) throws JSONException,
            CannotAccessLicensingServiceException {
        if (menuItem.has(LICENSE_CXC)) {
            final String licenseCXCListStr = (String) menuItem.get(LICENSE_CXC);
            final String[] licenseCXCArray = licenseCXCListStr.split(LICENSE_LIST_SEPARATOR);
            for (final String licenseCXC : licenseCXCArray) {
                if (licensingService.hasLicense(licenseCXC)) {
                    return true; //if any license is valid, then display the menu
                }
            }
            return false;
        }
        return true;
    }

    private boolean isEmptyMenu(final JSONObject menuItem) throws JSONException {
        //in the case where all menu items are removed from this button, remove the button also
        if (menuHasNoSubMenus(menuItem) && menuItemIsNotButton(menuItem)) {
            return true;
        }
        return false;
    }

    private boolean removeEmptyMenus(final JSONArray parentMenuItems, final JSONObject menuItem, final int menuItemIndex)
            throws JSONException {
        //in the case where all menu items are removed from this button, remove the button also
        if (isEmptyMenu(menuItem)) {
            parentMenuItems.remove(menuItemIndex);
            return true;
        }
        return false;
    }

    /*
    * Check if the menuItem can be clicked - i.e. has a URL
    */
    private boolean menuItemIsNotButton(final JSONObject menuItem) throws JSONException {
        boolean isNotButton = false;
        String menuItemURL = null;
        if (menuItem.has(URL)) {
            menuItemURL = menuItem.getString(URL);
        }
        if (StringUtils.isBlank(menuItemURL)) {
            isNotButton = true;
        }
        return isNotButton;
    }

    private boolean menuHasNoSubMenus(final JSONObject menuItem) throws JSONException {
        if (menuItem.has(ITEMS) && (((JSONArray) menuItem.get(ITEMS)).length() == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Scan through grids in meta data, and check columns in grids If any
     * unlicensed columns are found in grids, the field isSystem is added to
     * that column and set to true A value of true for the isSystem columns
     * means that the UI won't display it or allow the user to access it
     * 
     * Also it checks for licenceTypes field of the grid, if this field has 
     * "DV Throughput" license type in the field value then this substring
     * is removed if DV Throughput license (i.e. CXC4011159) is not applied. The values in
     * licenceType filed separated by ',' decides the column sub-menu created
     * for a grid.
     *
     * @param metaDataJsonObject
     *
     * @throws JSONException
     * @throws CannotAccessLicensingServiceException
     *
     */
    private void updateUnlicensedColumnsInGrids(final JSONObject metaDataJsonObject) throws JSONException,
            CannotAccessLicensingServiceException {
        final JSONArray grids = (JSONArray) metaDataJsonObject.get(GRIDS);
        for (int i = 0; i < grids.length(); i++) {
            final JSONObject grid = (JSONObject) grids.get(i);
            
            
            if (grid.has(LICENCE_TYPES)){
            	String licenceType = (String)grid.get(LICENCE_TYPES);
            	 
                if (StringUtils.isNotBlank(licenceType)) {
                	if (!licensingService.hasLicense(DV_THROUGHPUT_LICENSE_CXC)){
                		
                		if (licenceType.contains(DV_THROUGHPUT_LICENCE_TYPE + LICENSE_LIST_SEPARATOR)){
                			licenceType = licenceType.replaceFirst(DV_THROUGHPUT_LICENCE_TYPE + LICENSE_LIST_SEPARATOR, "");
                    		grid.remove(LICENCE_TYPES);
                			grid.put(LICENCE_TYPES, licenceType);
                		}else if(licenceType.contains(LICENSE_LIST_SEPARATOR + DV_THROUGHPUT_LICENCE_TYPE)){
                			licenceType = licenceType.replaceFirst(LICENSE_LIST_SEPARATOR + DV_THROUGHPUT_LICENCE_TYPE, "");
                    		grid.remove(LICENCE_TYPES);
                			grid.put(LICENCE_TYPES, licenceType);
                		}else if (licenceType.contains(DV_THROUGHPUT_LICENCE_TYPE)){
                			licenceType = licenceType.replaceFirst(DV_THROUGHPUT_LICENCE_TYPE, "");
                    		grid.remove(LICENCE_TYPES);
                			grid.put(LICENCE_TYPES, licenceType);
                		}
                	}
                }
            }
            
            final JSONArray columns = (JSONArray) grid.get(COLUMNS);
            for (int j = 0; j < columns.length(); j++) {
                final JSONObject column = (JSONObject) columns.get(j);
                addIsSystemFieldForUnlicensedColumns(column);
                removeDrillDownSupportForUnlicensedColumns(column);
            }
        }
    }

    /**
     * If the column has the licenseCXC field, then this method will check that
     * the license is present in the licensing store If the license isn't
     * present, then the field isSystem is added with the value true for that
     * column entry
     * <p/>
     * Eg if the license CXC4010926 isn't present, then the column entry {
     * "header": "Manufacturer", "width": "200", "datatype": "string",
     * "licenseCXC": "CXC4010926" } becomes { "header": "Manufacturer", "width":
     * "200", "datatype": "string", "licenseCXC": "CXC4010926", "isSystem":
     * "true" }
     * <p/>
     * If the licenseCXC field isn't specified, or if that license does exist in
     * the licensing store, then the JSON is unchanged
     *
     * @param column
     *
     * @throws JSONException
     * @throws CannotAccessLicensingServiceException
     *
     */
    private void addIsSystemFieldForUnlicensedColumns(final JSONObject column) throws JSONException,
            CannotAccessLicensingServiceException {
        if (!isElementLicensed(column)) {
            column.put(IS_SYSTEM, TRUE);
        }
    }

    /**
     * If the column has the drillDownLicenseCXC field, then this method will check that
     * the license is present in the licensing store If the license isn't
     * present, then the field drillDownWindowType/launchWindowType is removed from
     * column entry
     * <p/>
     * Eg if the license CXC4010926 isn't present, then the column entry {
     * "header": "Manufacturer", "width": "200", "datatype": "string",
     * "licenseCXC": "CXC4010926" } becomes { "header": "Manufacturer", "width":
     * "200", "datatype": "string", "licenseCXC": "CXC4010926", "isSystem":
     * "true" }
     * <p/>
     * If the drillDownLicenseCXC field isn't specified, or if that license does exist in
     * the licensing store, then the JSON is unchanged
     *
     * @param column
     *
     * @throws JSONException
     * @throws CannotAccessLicensingServiceException
     *
     */
    private void removeDrillDownSupportForUnlicensedColumns(final JSONObject column) throws JSONException,
            CannotAccessLicensingServiceException {
        if (!isDrillDownElementLicensed(column)) {
            //Grid can have two columns with launchWindowType or drillDownLicenseCXC or both metadata ID,
            // hence don't used elseif
            //For example Subscriber Re-occuring failures has both launchWindowType and drillDownLicenseCXC metadata ID
            if (column.has(DRILLDOWN_WINDOWTYPE_METADATA_ID)) {
                column.remove(DRILLDOWN_WINDOWTYPE_METADATA_ID);
            }
            if (column.has(LAUNCH_WINDOWTYPE_METADATA_ID)) {
                column.remove(LAUNCH_WINDOWTYPE_METADATA_ID);
            }
        }
    }

    /**
     * exposed for unit test
     *
     * @param licensingService the licensingService to set
     */
    void setLicensingService(final LicensingService licensingService) {
        this.licensingService = licensingService;
    }
}
