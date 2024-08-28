/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.interceptor.InvocationContext;

import com.ericsson.eniq.events.server.common.exception.CannotAccessLicensingServiceException;
import com.ericsson.eniq.events.server.common.exception.NoLicenseException;
import com.ericsson.eniq.events.server.common.exception.ServiceException;
import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.test.util.FileUtilities;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;
import com.ericsson.eniq.licensing.cache.LicenseInformation;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author eemecoy
 *
 */
public class LicensingInterceptorTest extends BaseJMockUnitTest {

    private static final String ONE_FREE_ELEMENT_PANE = "oneFreeElementPane";

    private static final String ONE_LICENSED_ELEMENT_PANE = "oneLicensedElementPane";

    private static final String ONE_FREE_AND_ONE_UNLICENSED_ELEMENT = "oneFreeAndOneUnlicensedElement";

    private static final String SUB_BI_TERMINALS = "SUB_BI_TERMINALS";

    private static final String SUB_BI_TERMINALS_GROUP = "SUB_BI_TERMINALS_GROUP";

    private static final String HEADER = "header";

    private static final String MANUFACTURER = "Manufacturer";

    private static final String MODEL = "Model";

    private static final String DEVICE_CAPABILITY = "Device Capability";

    private static final String EVENT_FAILURES = "Event Failures";

    private static final String TEST_LICENSES_CXC = "CXC4010923:CXC4010924:CXC4010933:CXC4011049:CXC4011159:CXC4011158";

    private static final String TEST_LICENSE_PRESENT = "CXC4010998";

    private static final String TEST_LICENSE_MISSING = "CXC4010999";

    private static final String SUBSCRIBER_SESSION_LICENSE_CXC = "CXC4010925";

    private static final String DEVICE_ANALYSIS_LICENSE_CXC = "CXC4010926";

    private static final String NETWORK_ANALYSIS_LICENSE_CXC = "CXC4010927";

    private static final String RANKING_ENGINE_LICENSE_CXC = "CXC4010929";

    private static final String KPI_PANEL_LICENSE = "CXC4011279";

    private static final String ONE_LICENSED_AND_ONE_UNLICENSED_ELEMENT = "oneLicensedAndOneUnlicensedElement";

    private LicensingInterceptor objToTest;

    InvocationContext mockedInvocationContext;

    LicensingService mockedLicensingService;

    LicenseInformation mockLicenseInformation;

    protected static String uiMetaData;

    @Before
    public void setUp() throws JSONException, IOException, CannotAccessLicensingServiceException {
        mockedInvocationContext = mockery.mock(InvocationContext.class);
        mockedLicensingService = mockery.mock(LicensingService.class);
        mockLicenseInformation = mockery.mock(LicenseInformation.class);
        objToTest = new LicensingInterceptor();
        objToTest.setLicensingService(mockedLicensingService);

        final JSONObject uiMetaDataObject = new JSONObject(
                FileUtilities.readFileFromClasspath("metadata/UIMetaData_test_only.json"));
        assertNotNull(uiMetaDataObject);
        uiMetaData = uiMetaDataObject.toString();

        allowLicensesInMockedLicenseService(true, "CXC_THAT_IS_LICENSED", TEST_LICENSE_PRESENT);
        allowLicensesInMockedLicenseService(false, "CXC_THAT_IS_NOT_LICENSED", TEST_LICENSE_MISSING);
    }

    @Test
    public void testTopLevelElementRemovedIfHasSingleUnlicensedElement() throws Exception {
        allowLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                DEVICE_ANALYSIS_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(false, KPI_PANEL_LICENSE);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String filteredJSON = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(new JSONObject(filteredJSON).has("kpiPanel"), is(false));
    }

    @Test
    public void testTopLevelElementRemovedIfHasTwoUnlicensedElements() throws Exception {
        allowLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                DEVICE_ANALYSIS_LICENSE_CXC);
        setUpLicensesInMockedLicenseService(1, false, KPI_PANEL_LICENSE);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String filteredJSON = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(new JSONObject(filteredJSON).has("dashboard"), is(false));
    }

    @Test
    public void testTopLevelRemainsWithIfHasOneLicensedElement() throws Exception {
        allowLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                DEVICE_ANALYSIS_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(false, KPI_PANEL_LICENSE);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String filteredJSON = (String) objToTest.checkLicenses(mockedInvocationContext);
        final JSONObject filteredJsonObject = new JSONObject(filteredJSON);
        assertThat(filteredJsonObject.has(ONE_LICENSED_ELEMENT_PANE), is(true));
        final JSONArray jsonArray = (JSONArray) filteredJsonObject.get(ONE_LICENSED_ELEMENT_PANE);
        assertThat(jsonArray.length(), is(1));
    }

    @Test
    public void testTopLevelRemainsWithIfHasOneFreeElement() throws Exception {
        allowLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                DEVICE_ANALYSIS_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(false, KPI_PANEL_LICENSE);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String filteredJSON = (String) objToTest.checkLicenses(mockedInvocationContext);
        final JSONObject jsonObject = new JSONObject(filteredJSON);
        assertThat(jsonObject.has(ONE_FREE_ELEMENT_PANE), is(true));
        final JSONArray jsonArray = (JSONArray) jsonObject.get(ONE_FREE_ELEMENT_PANE);
        assertThat(jsonArray.length(), is(1));
    }

    @Test
    public void testTopLevelRemainsWithIfHasOneFreeElementAndOneUnlicensedElement() throws Exception {
        allowLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                DEVICE_ANALYSIS_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(false, KPI_PANEL_LICENSE);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String filteredJSON = (String) objToTest.checkLicenses(mockedInvocationContext);
        final JSONObject filteredJsonObject = new JSONObject(filteredJSON);
        assertThat(filteredJsonObject.has(ONE_FREE_AND_ONE_UNLICENSED_ELEMENT), is(true));
        final JSONArray filteredArray = (JSONArray) filteredJsonObject.get(ONE_FREE_AND_ONE_UNLICENSED_ELEMENT);
        assertThat(filteredArray.length(), is(1));
        final JSONObject sampleDashboardItem = (JSONObject) filteredArray.get(0);
        assertThat((String) sampleDashboardItem.get("name"), is("sampleDashboardItem"));
    }

    @Test
    public void testTopLevelRemainsWithIfHasOneLicensedElementAndOneUnlicensedElement() throws Exception {
        allowLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                DEVICE_ANALYSIS_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(false, KPI_PANEL_LICENSE);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String filteredJSON = (String) objToTest.checkLicenses(mockedInvocationContext);
        final JSONObject filteredJsonObject = new JSONObject(filteredJSON);
        assertThat(filteredJsonObject.has(ONE_LICENSED_AND_ONE_UNLICENSED_ELEMENT), is(true));
        final JSONArray filteredArray = (JSONArray) filteredJsonObject.get(ONE_LICENSED_AND_ONE_UNLICENSED_ELEMENT);
        assertThat(filteredArray.length(), is(1));
    }

    @Test
    public void testLicensedColumnsAreUnchangedInMetaData() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                KPI_PANEL_LICENSE);
        allowLicensesInMockedLicenseService(false, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        final List<String> gridsToCheck = new ArrayList<String>();
        gridsToCheck.add(SUB_BI_TERMINALS);
        gridsToCheck.add(SUB_BI_TERMINALS_GROUP);
        final List<String> columnsThatShouldNotBeSystemColumns = new ArrayList<String>();
        columnsThatShouldNotBeSystemColumns.add(EVENT_FAILURES);
        checkThatColumnsDontHaveIsSystemColumnInGrids(result, gridsToCheck, columnsThatShouldNotBeSystemColumns);
    }

    private void checkThatColumnsDontHaveIsSystemColumnInGrids(final String parsedMetaData,
            final List<String> gridsToCheck, final List<String> columnsThatShouldNotBeSystemColumns)
            throws JSONException {
        for (final String gridToCheck : gridsToCheck) {
            final JSONObject gridObject = getGridFromMetaData(parsedMetaData, gridToCheck);
            final JSONArray gridColumns = (JSONArray) gridObject.get(COLUMNS);
            for (final String columnToCheck : columnsThatShouldNotBeSystemColumns) {
                final JSONObject columnFromGrid = getColumnFromGrid(gridColumns, columnToCheck);
                final boolean hasSystemValue = columnFromGrid.has(IS_SYSTEM);
                assertFalse("Column " + columnToCheck + " in grid " + gridToCheck
                        + " should not have the isSystem field", hasSystemValue);
            }
        }
    }

    @Test
    public void testUnlicensedColumnsAreMarkedAsSystemFromMetaData() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC,
                NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC,
                KPI_PANEL_LICENSE);
        allowLicensesInMockedLicenseService(false, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        final List<String> gridsToCheck = new ArrayList<String>();
        gridsToCheck.add(SUB_BI_TERMINALS);
        gridsToCheck.add(SUB_BI_TERMINALS_GROUP);
        final List<String> columnsThatShouldBeSystemColumns = new ArrayList<String>();
        columnsThatShouldBeSystemColumns.add(MANUFACTURER);
        columnsThatShouldBeSystemColumns.add(MODEL);
        columnsThatShouldBeSystemColumns.add(DEVICE_CAPABILITY);
        checkThatColumnsAreMarkedAsSystemColumnsInGrids(result, gridsToCheck, columnsThatShouldBeSystemColumns);
    }

    private void checkThatColumnsAreMarkedAsSystemColumnsInGrids(final String parsedMetaData,
            final List<String> gridsToCheck, final List<String> columnsThatShouldHaveBeenSystemColumns)
            throws JSONException {

        for (final String gridToCheck : gridsToCheck) {
            final JSONObject gridObject = getGridFromMetaData(parsedMetaData, gridToCheck);
            final JSONArray gridColumns = (JSONArray) gridObject.get(COLUMNS);
            for (final String columnToCheck : columnsThatShouldHaveBeenSystemColumns) {
                final JSONObject columnFromGrid = getColumnFromGrid(gridColumns, columnToCheck);
                final boolean hasSystemValue = columnFromGrid.has(IS_SYSTEM);
                assertTrue("Column " + columnToCheck + " in grid " + gridToCheck + " should have the isSystem field",
                        hasSystemValue);
                final String isSystemValue = (String) columnFromGrid.get(IS_SYSTEM);
                assertThat(isSystemValue, is("true"));
            }
        }
    }

    private JSONObject getColumnFromGrid(final JSONArray gridColumns, final String columnToSearchFor)
            throws JSONException {
        for (int j = 0; j < gridColumns.length(); j++) {
            final JSONObject column = (JSONObject) gridColumns.get(j);
            final String columnHeader = (String) column.get(HEADER);
            if (columnHeader.equals(columnToSearchFor)) {
                return column;
            }
        }
        return null;
    }

    private JSONObject getGridFromMetaData(final String parsedMetaData, final String gridId) throws JSONException {
        final JSONObject metaDataObject = new JSONObject(parsedMetaData);
        final JSONArray gridsArray = (JSONArray) metaDataObject.get(GRIDS);
        for (int i = 0; i < gridsArray.length(); i++) {
            final JSONObject gridObject = (JSONObject) gridsArray.get(i);
            final String thisGridId = (String) gridObject.get(ID);
            if (thisGridId.equals(gridId)) {
                return gridObject;
            }
        }
        return null;
    }

    @Test(expected = NoLicenseException.class)
    public void testExceptionThrownWhenBasicLicenseExistsButNoFeatureLicenses() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        allowLicensesInMockedLicenseService(false, DEVICE_ANALYSIS_LICENSE_CXC, NETWORK_ANALYSIS_LICENSE_CXC,
                RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC, KPI_PANEL_LICENSE);
        expectProceedOnMockedInvocationContext();
        objToTest.checkLicenses(mockedInvocationContext);
    }

    @Test
    public void testServiceExceptionThrownWhenExceptionThrownFromCallToMetaDataBean() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        final Throwable ioException = new IOException();
        expectProceedOnMockedInvocationContextWillThrowSomeException(ioException);
        try {
            objToTest.checkLicenses(mockedInvocationContext);
            fail("ServiceException should have been thrown");
        } catch (final ServiceException e) {
            assertThat(e.getCause(), is(ioException));
        }
    }

    private void expectProceedOnMockedInvocationContextWillThrowSomeException(final Throwable throwable)
            throws Exception {
        mockery.checking(new Expectations() {
            {
                one(mockedInvocationContext).proceed();
                will(throwException(throwable));
            }
        });
    }

    @Test
    public void testUnlicensedTabsAreFilteredOutFromMetadata() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(true, NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC,
                KPI_PANEL_LICENSE);
        setUpStrictLicensesInMockedLicenseService(false, SUBSCRIBER_SESSION_LICENSE_CXC);
        allowLicensesInMockedLicenseService(false, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(JSONTestUtils.isValidJson(result), is(true));
        checkExistenceOfTabIdInJSON(result, "TERMINAL_TAB", false);
        checkExistenceOfTabIdInJSON(result, "SUBSCRIBER_TAB", false);
        checkExistenceOfTabIdInJSON(result, "RANKINGS_TAB", true);
        checkExistenceOfTabIdInJSON(result, "NETWORK_TAB", true);
    }

    @Test
    public void testUnlicensedSearchFieldsAreFilteredOutFromMetadata() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(true, NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC,
                KPI_PANEL_LICENSE);
        setUpStrictLicensesInMockedLicenseService(false, SUBSCRIBER_SESSION_LICENSE_CXC);
        allowLicensesInMockedLicenseService(false, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(JSONTestUtils.isValidJson(result), is(true));
        checkExistenceOfTabIdInJSON(result, "TERMINAL_TAB", false);
        checkExistenceOfTabIdInJSON(result, "SUBSCRIBER_TAB", false);
        checkExistenceOfTabIdInJSON(result, "RANKINGS_TAB", true);
        checkExistenceOfTabIdInJSON(result, "NETWORK_TAB", true);
        checkExistenceOfTabIdInJSON(result, "SUMMARY_LTE", false);
        checkExistenceOfTabIdInJSON(result, "APN_NOT_LICENSED_IN_SEARCH_FIELDS", false);
        checkExistenceOfTabIdInJSON(result, "APN_LICENSED_IN_SEARCH_FIELDS", true);
        checkExistenceOfTabIdInJSON(result, "BSC_NOT_LICENSED_IN_SEARCH_FIELDS", false);
        checkExistenceOfTabIdInJSON(result, "SUMMARY_WCDMA", false);
    }

    @Test
    public void testUnlicensedMenuItemsAreFilteredOutFromMetadata() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(true, NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC,
                KPI_PANEL_LICENSE);
        setUpStrictLicensesInMockedLicenseService(false, SUBSCRIBER_SESSION_LICENSE_CXC);
        allowLicensesInMockedLicenseService(false, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(JSONTestUtils.isValidJson(result), is(true));
        checkExistenceOfNameForTaskBarInJSON(result, "Roaming by Operator (Event Volume)", false);//unlicensed
        checkExistenceOfNameForTaskBarInJSON(result, "Roaming by Country (Event Volume)", true);//licensed
        checkExistenceOfNameForTaskBarInJSON(result, "Roaming by Country (Data Volume)", true);//no licenseCXC entry - default = not removed
        checkExistenceOfNameForTaskBarInJSON(result, "RAN Ranking (For License Test Only)", false);//all children are unlicensed => remove
    }

    private void allowLicensesInMockedLicenseService(final boolean hasLicense, final String... licenses)
            throws CannotAccessLicensingServiceException {
        for (final String license : licenses) {
            mockery.checking(new Expectations() {
                {
                    allowing(mockedLicensingService).hasLicense(license);
                    will(returnValue(hasLicense));
                }
            });
        }

        mockery.checking(new Expectations() {

            {
                final Vector<LicenseInformation> info = new Vector<LicenseInformation>();
                info.add(mockLicenseInformation);
                allowing(mockedLicensingService).getLicenseInformation();
                will(returnValue(info));

                allowing(mockLicenseInformation).getFeatureName();
                will(returnValue("mockfeatureNameCXC1112344"));

                allowing(mockLicenseInformation).getDescription();
            }
        });
    }

    @Test
    public void testLicensesInformationAddedToMetadata() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(true, NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC,
                SUBSCRIBER_SESSION_LICENSE_CXC, KPI_PANEL_LICENSE);
        allowLicensesInMockedLicenseService(true, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(JSONTestUtils.isValidJson(result), is(true));
        assertThat(result.contains(LICENSES), is(true));
        assertThat(result.contains(FEATURE_NAME), is(true));
        assertThat(result.contains(DESCRIPTION), is(true));
        assertThat(result.contains("mockfeatureNameCXC1112344"), is(true));
    }

    @Test
    public void testAllTabsAreIncludedWhenAllLicensesPresent() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        setUpStrictLicensesInMockedLicenseService(true, NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC, SUBSCRIBER_SESSION_LICENSE_CXC, KPI_PANEL_LICENSE);
        allowLicensesInMockedLicenseService(true, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        expectProceedOnMockedInvocationContext();
        final String result = (String) objToTest.checkLicenses(mockedInvocationContext);
        assertThat(JSONTestUtils.isValidJson(result), is(true));
        checkExistenceOfTabIdInJSON(result, "TERMINAL_TAB", true);
        checkExistenceOfTabIdInJSON(result, "SUBSCRIBER_TAB", true);
        checkExistenceOfTabIdInJSON(result, "RANKINGS_TAB", true);
        checkExistenceOfTabIdInJSON(result, "NETWORK_TAB", true);
    }

    private void checkExistenceOfTabIdInJSON(final String json, final String tabId, final boolean shouldTabIdExist) {
        final String jsonEntryForTabId = "\"id\":\"" + tabId + "\"";
        assertThat("Result should/should not (" + shouldTabIdExist + ") have contained: " + jsonEntryForTabId + ", json was " + json, json.contains(jsonEntryForTabId), is(shouldTabIdExist));
    }

    private void checkExistenceOfNameForTaskBarInJSON(final String json, final String nameForTaskBar,
            final boolean shouldNameExist) {
        final String jsonEntryForNameForTaskBar = "\"nameForTaskBar\":\"" + nameForTaskBar + "\"";
        assertThat("Result should/should not (" + shouldNameExist + ") have contained: " + jsonEntryForNameForTaskBar
                + ", json was " + json, json.contains(jsonEntryForNameForTaskBar), is(shouldNameExist));
    }

    private void setUpStrictLicensesInMockedLicenseService(final boolean areLicensesPresent,
            final String... licenseNames) throws CannotAccessLicensingServiceException {
        setUpLicensesInMockedLicenseService(1, areLicensesPresent, licenseNames);
    }

    private void setUpLicensesInMockedLicenseService(final int numInvocationsExpected,
            final boolean areLicensesPresent, final String... licenseNames)
            throws CannotAccessLicensingServiceException {
        for (final String licenseName : licenseNames) {
            mockery.checking(new Expectations() {
                {
                    exactly(numInvocationsExpected).of(mockedLicensingService).hasLicense(licenseName);
                    will(returnValue(areLicensesPresent));
                }
            });
        }
    }

    @Test
    public void testCheckHasLicenseWhenItDoesProceedsWithCall() throws Exception {
        setUpStrictLicensesInMockedLicenseService(true, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        expectProceedOnMockedInvocationContext();
        setUpStrictLicensesInMockedLicenseService(true, NETWORK_ANALYSIS_LICENSE_CXC, RANKING_ENGINE_LICENSE_CXC,
                SUBSCRIBER_SESSION_LICENSE_CXC, KPI_PANEL_LICENSE);
        allowLicensesInMockedLicenseService(true, DEVICE_ANALYSIS_LICENSE_CXC);
        setUpAllLicenseTrue();
        objToTest.checkLicenses(mockedInvocationContext);
    }

    @Test(expected = NoLicenseException.class)
    public void testCheckHasLicenseWhenItDoesntThrowsException() throws Exception {
        setUpStrictLicensesInMockedLicenseService(false, LicensingInterceptor.ENIQ_EVENTS_STARTER_LICENSE_CXC);
        objToTest.checkLicenses(mockedInvocationContext);
    }

    private void expectProceedOnMockedInvocationContext() throws Exception {
        mockery.checking(new Expectations() {
            {
                one(mockedInvocationContext).proceed();
                will(returnValue(uiMetaData));
            }
        });
    }

    private void setUpAllLicenseTrue() throws CannotAccessLicensingServiceException {
        setUpLicense(TEST_LICENSES_CXC.split(":"));
    }

    private void setUpLicense(final String[] licensesCxcs) throws CannotAccessLicensingServiceException {
        for (final String licensesCxc : licensesCxcs) {
            allowLicensesInMockedLicenseService(true, licensesCxc);
        }
    }

}
