/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.auth.rbac;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import com.ericsson.eniq.events.server.common.exception.ServiceException;
import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.ldap.management.LDAPException;
import com.ericsson.eniq.ldap.util.ENIQEventsAccessControlUtil;
import com.ericsson.eniq.ldap.vo.LoginVO;
import com.sun.enterprise.security.auth.login.common.PasswordCredential;

//import com.ericsson.eniq.events.server.services.ServiceException;

/**
 * Interceptor to check that appropriate RoleBased Permissions exist before running query or service
 *
 * @author EFLATIB
 * @author eromsza
 */
public class AccessControlInterceptor {

    private final static String SUBJECT_CONTAINER = "javax.security.auth.Subject.container";

    /* Permission constants */
    private final static String EVENTSUI_NETWORK_VIEW = "eventsui.network.view";

    private final static String EVENTSUI_TERMINAL_VIEW = "eventsui.terminal.view";

    private final static String EVENTSUI_SUBSCRIBER_VIEW = "eventsui.subscriber.view";

    private final static String EVENTSUI_RANKING_VIEW = "eventsui.ranking.view";

    private final static String EVENTSUI_DASHBOARD_VIEW = "eventsui.dashboard.view";

    private final static String EVENTSUI_BUSINESSOBJECTS_VIEW = "ventsui.businessobjects.view";

    private final static String ERICSSONOSS_SONVIS_VIEW = "ericssonoss.sonvis.view";

    private final static String RESTFUL_NBI_VIEW = "restful.nbi.view";

    private final static String  EVENTSUI_KPI_VIEW = "eventsui.kpi.view";

    private final static String  EVENTSUI_SESSIONBROWSER_VIEW = "eventsui.sessionbrowser.view";

    /* Tab constants */
    private final static String NETWORK_TAB = "NETWORK_TAB";

    private final static String TERMINAL_TAB = "TERMINAL_TAB";

    private final static String SUBSCRIBER_TAB = "SUBSCRIBER_TAB";

    private final static String RANKINGS_TAB = "RANKINGS_TAB";

    private final static String DASHBOARD_TAB = "DASHBOARD_TAB";

    private final static String BUSINESS_OBJECTS_TAB = "BUSINESS_OBJECTS_TAB";

    private final static String SONVIS_TAB = "SONVIS_TAB";

    private final static String KPI_TAB = "KPI_ANALYSIS_TAB";

    private final static String SESSION_BROWSER_TAB = "SESSION_BROWSER_TAB";

    private final static Map<String, String> rolePermissionToTabMap = new HashMap<String, String>();

    static {
        rolePermissionToTabMap.put(EVENTSUI_NETWORK_VIEW, NETWORK_TAB);
        rolePermissionToTabMap.put(EVENTSUI_TERMINAL_VIEW, TERMINAL_TAB);
        rolePermissionToTabMap.put(EVENTSUI_SUBSCRIBER_VIEW, SUBSCRIBER_TAB);
        rolePermissionToTabMap.put(EVENTSUI_RANKING_VIEW, RANKINGS_TAB);
        rolePermissionToTabMap.put(EVENTSUI_DASHBOARD_VIEW, DASHBOARD_TAB);
        rolePermissionToTabMap.put(EVENTSUI_BUSINESSOBJECTS_VIEW, BUSINESS_OBJECTS_TAB);
        rolePermissionToTabMap.put(ERICSSONOSS_SONVIS_VIEW, SONVIS_TAB);
        rolePermissionToTabMap.put(EVENTSUI_KPI_VIEW, KPI_TAB);
        rolePermissionToTabMap.put(EVENTSUI_SESSIONBROWSER_VIEW, SESSION_BROWSER_TAB);
    }

    @AroundInvoke
    /**
     * Check RBAC permission information
     *
     * @param invocationContext         provided by Java EE's Interceptors - this contains all information on the method call - parameters, method
     *                                  It also allows to call the method itself
     *
     * @return Object                   return's the meta data, with any unlicensed features/tabs/columns filtered out or marked as should be hidden in UI
     */
    public Object checkPermissions(final InvocationContext invocationContext) throws Exception {
        String metaDataJson;
        try {
            metaDataJson = (String) invocationContext.proceed();
        } catch (final Exception e) {
            ServicesLogger.exception(getClass().getName(), "checkPermissions", "Problem running service", e);
            ServicesLogger.warn(getClass().getName(), "checkPermissions", "Problem running service", e);
            throw new ServiceException(e);
        }

        final Subject subject = getSubjectFromContainer();

        if (subject != null) {
            final Iterator<Object> iterator = subject.getPrivateCredentials().iterator();
            while (iterator.hasNext()) {
                final Object credential = iterator.next();
                if (credential instanceof PasswordCredential) {
                    final PasswordCredential passwordCredential = (PasswordCredential) credential;
                    final String userName = passwordCredential.getUser();
                    ServicesLogger.info(getClass().getName(), "checkPermissions", "username in passwordCredential = "
                            + userName);
                    final String password = new String(passwordCredential.getPassword());
                    metaDataJson = filterRbacFeatures(metaDataJson, userName, password);
                    return metaDataJson;
                }
            }
            ServicesLogger.error(getClass().getName(), "checkPermissions", "passwordCredential not found");
            throw new ServiceException("passwordCredential not found");
        }
        ServicesLogger.error(getClass().getName(), "checkPermissions", "subject is null");
        throw new ServiceException("subject is null");
    }

    /**
     * A hacky private credentials retrieval from Glassfish.
     *
     * @return Subject A subject instance with PrivateCredentials of a logged in principal
     */
    protected Subject getSubjectFromContainer() {
        try {
            return (Subject) PolicyContext.getContext(SUBJECT_CONTAINER);
        } catch (final PolicyContextException ex) {
            ServicesLogger.warn(getClass().getName(), "checkPermissions", "subject is null");
            throw new ServiceException("subject is null");
        }
    }

    /**
     * Filter out the tabs that the user has role permission to access.
     *
     *
     * @param metaDataJson
     * @return String                       the input metaDataJson filtered
     * @throws JSONException               if JSON syntax incorrect
     */
    private String filterRbacFeatures(final String metaDataJson, final String userName, final String password)
            throws Exception {
        final JSONObject metaDataJsonObject = new JSONObject(metaDataJson);
        String result = "";
        if (metaDataJsonObject.has(TABS)) {
            final JSONArray tabElements = (JSONArray) metaDataJsonObject.get(TABS);
            final Set<String> permissionSet = getUserPermissions(userName, password);
            ServicesLogger.info(getClass().getName(), "Interceptor", "filterRbacFeatures " + "Permitted tab elements: "
                    + permissionSet);
            // if user has no roles, we still need to show all tabs locked
            /*if (permissionSet.size() == 0) {
                ServicesLogger.warn(getClass().getName(), "filterRbacFeatures", "No valid permissions found");
                throw new NoRolePermissionException("No Role permission exist");
            }*/
            metaDataJsonObject.remove(TABS);
            updateNonPermittedColumnsInTabs(tabElements, permissionSet);
            metaDataJsonObject.put(TABS, tabElements);
            result = metaDataJsonObject.toString();
        }
        return result;
    }

    /**
     * Update the tabs with permissions with the field "isRoleEnabled" and set it to "TRUE"
     * This way when the UI MainPresenter builds the site it can decide wether a tab is to be shown as active or inactive
     *
     * @param tabElements
     * @param permissionSet
     * @throws JSONException
     */
    private void updateNonPermittedColumnsInTabs(final JSONArray tabElements, final Set<String> permissionSet)
            throws JSONException {
        for (int i = 0; i < tabElements.length(); i++) {
            final JSONObject tab = (JSONObject) tabElements.get(i);
            if (tab.has(ID)) {
                final String columnId = (String) tab.get(ID);
                if (permissionSet.contains(columnId)) {
                    tab.put(IS_ROLE_ENABLED, "TRUE");
                    ServicesLogger.info(getClass().getName(), "Interceptor",
                            "updateNonPermittedColumnsInTabs, Updated permitted tab: " + tab);
                }
            }
        }
    }

    /**
    * Retrieve the set of permissions for current user.
    *
    * @param userName
    * @param userPassword
    * @return Set<String>      for which the current user has Role based permission
    * @throws NamingException
    */
    protected Set<String> getUserPermissions(final String userName, final String userPassword) throws NamingException {
        Set<String> permissionSet;
        final LoginVO loginVO = new LoginVO();
        loginVO.setLoginId(userName);
        loginVO.setPassword(userPassword);
        try {
            permissionSet = ENIQEventsAccessControlUtil.findPermissionsByUserId(loginVO);
            //if user has no roles, we still need to show all tabs locked
            /* if (0 == permissionSet.size()) {
                throw new NamingException("This user has no permissions so can not be logged in : " + userName);
            }*/
        } catch (final LDAPException exp) {
            ServicesLogger.exception(getClass().getName(), "getUserPermissions", "Failed to get permissions of user: ",
                    exp);
            throw new NamingException("failed to find permissons of user: " + userName);
        }

        final Set<String> mappedPermissionSet = new HashSet<String>();

        if (!permissionSet.contains(RESTFUL_NBI_VIEW)) {
            for (final String item : permissionSet) {
                String tabName = "";
                if (rolePermissionToTabMap.containsKey(item)) {
                    tabName = rolePermissionToTabMap.get(item);
                    mappedPermissionSet.add(tabName);
                } else {
                    ServicesLogger.error(getClass().getName(), "getUserPermissions",
                            "Failed to map a user's permission:" + item + "to a tab: ");
                    throw new NamingException("Failed to map permission" + item + "to a tab name.");
                }
            }
        }

        ServicesLogger.info(getClass().getName(), "getUserPermissions", "UserRolePermissions: " + mappedPermissionSet);

        return mappedPermissionSet;
    }
}
