/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.ericsson.eniq.events.server.resources.sbr.SBrowserCoreKpiResource;
import com.ericsson.eniq.events.server.resources.sbr.SBrowserDetailResource;
import com.ericsson.eniq.events.server.resources.sbr.SBrowserPopupDetailResource;
import com.ericsson.eniq.events.server.resources.sbr.SBrowserReportsResource;
import com.ericsson.eniq.events.server.resources.sbr.SBrowserSumResource;
import com.ericsson.eniq.events.server.resources.sbr.SBrowserUserPlaneKpiResource;
import com.ericsson.eniq.events.server.resources.sbr.SBrowserUserplaneResource;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class SessionBrowserResource {

    @EJB
    protected SBrowserDetailResource sBrowserDetailResource;

    @EJB
    protected SBrowserUserplaneResource sBrowserUserplaneResource;

    @EJB
    protected SBrowserPopupDetailResource sBrowserPopupDetailResource;

    @EJB
    protected SBrowserSumResource sBrowserSumResource;

    @EJB
    protected SBrowserReportsResource sBrowserReportsResource;

    @EJB
    protected SBrowserCoreKpiResource sBrowserCoreKpiResource;

    @EJB
    protected SBrowserUserPlaneKpiResource sBrowserUserPlaneKpiResource;

    @Path(SESSION_DETAIL)
    public SBrowserDetailResource getBrowserDetailResource() {
        return this.sBrowserDetailResource;
    }

    @Path(USER_PLANE)
    public SBrowserUserplaneResource getBrowserUserplaneResource() {
        return this.sBrowserUserplaneResource;
    }

    @Path(POPUP_DETAIL)
    public SBrowserPopupDetailResource getSBrowserPopupDetailResource() {
        return this.sBrowserPopupDetailResource;
    }

    @Path(SESSION_SUMMARY)
    public SBrowserSumResource getSBrowserSumResource() {
        return this.sBrowserSumResource;
    }

    @Path(SESSION_REPORTS)
    public SBrowserReportsResource getSBrowserReportsResource() {
        return this.sBrowserReportsResource;
    }

    @Path(SESSION_KPI + PATH_SEPARATOR + CORE)
    public SBrowserCoreKpiResource getSBrowserKpiResource() {
        return this.sBrowserCoreKpiResource;
    }

    @Path(SESSION_KPI + PATH_SEPARATOR + USER_PLANE)
    public SBrowserUserPlaneKpiResource getSBrowserUKpiResource() {
        return this.sBrowserUserPlaneKpiResource;
    }

}
