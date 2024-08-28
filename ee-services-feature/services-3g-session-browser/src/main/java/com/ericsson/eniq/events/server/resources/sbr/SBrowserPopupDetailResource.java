/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.sbr;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.utils.sbr.SbrowserUtil;

/**
 * @author ehaoswa 
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class SBrowserPopupDetailResource extends AbstractResource {

    private static final String SESSION_BROWSER_RAB_POPUP_DETAIL_SERVICE = "SBrowserRabPopupDetailService";

    private static final String SESSION_BROWSER_RAN_CFA_POPUP_DETAIL_SERVICE = "SBrowserRanCfaPopupDetailService";

    private static final String SESSION_BROWSER_HHO_POPUP_DETAIL_SERVICE = "SBrowserRanHfaHhoDetailService";

    private static final String SESSION_BROWSER_SGEH_POPUP_DETAIL_SERVICE = "SBrowserSgehPopupDetailService";

    private static final String SESSION_BROWSER_WEB_SERVER_DISTRIBUTION_POPUP_SERVICE = "SBrowserServerDistPopupService";

    private static final String SESSION_BROWSER_RAN_HFA_IFHO_DETAIL_SERVICE = "SBrowserRanHfaIfhoDetailService";

    private static final String SESSION_BROWSER_RAN_HFA_SOHO_DETAIL_SERVICE = "SBrowserRanHfaSohoDetailService";

    private static final String SESSION_BROWSER_RAN_HFA_HSDSCH_DETAIL_SERVICE = "SBrowserRanHfaHsdschDetailService";

    private static final String SESSION_BROWSER_RAN_HFA_IRAT_DETAIL_SERVICE = "SBrowserRanHfaIratDetailService";

    private static final String SESSION_BROWSER_SUC_HSDSCH_DETAIL_SERVICE = "SBrowserSucHsdschDetailService";

    private static final String SESSION_BROWSER_RRC_MEAS_POPUP_DETAIL_SERVICE = "SBrowserRrcPopupDetailService";

    @EJB(beanName = SESSION_BROWSER_RAB_POPUP_DETAIL_SERVICE)
    private Service sBrowserRabPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_CFA_POPUP_DETAIL_SERVICE)
    private Service sBrowserRanCfaPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_HHO_POPUP_DETAIL_SERVICE)
    private Service sBrowserHHOPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_SGEH_POPUP_DETAIL_SERVICE)
    private Service sBrowserSgehPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_WEB_SERVER_DISTRIBUTION_POPUP_SERVICE)
    private Service sBrowserServerDistPopupService;

    @EJB(beanName = SESSION_BROWSER_RAN_HFA_IFHO_DETAIL_SERVICE)
    private Service sBrowserRanHfaIfhoPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_HFA_SOHO_DETAIL_SERVICE)
    private Service sBrowserRanHfaSohoPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_HFA_HSDSCH_DETAIL_SERVICE)
    private Service sBrowserRanHfaHsdschPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_HFA_IRAT_DETAIL_SERVICE)
    private Service sBrowserRanHfaIratPopupDetailService;

    @EJB(beanName = SESSION_BROWSER_SUC_HSDSCH_DETAIL_SERVICE)
    private Service sBrowserSucHsdschDetailService;

    @EJB(beanName = SESSION_BROWSER_RRC_MEAS_POPUP_DETAIL_SERVICE)
    private Service sBrowserRrcPopupDetailService;

    @Path(PROPERTIES + PATH_SEPARATOR + CORE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserCorePopupDetailService() {
        return sBrowserSgehPopupDetailService.getData(mapResourceLayerParameters());
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRabPopupDetailService() {
        return sBrowserRabPopupDetailService.getData(mapResourceLayerParameters());
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAN_CFA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRanCfaPopupDetailService() {
        return sBrowserRanCfaPopupDetailService.getData(mapResourceLayerParameters());
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAN_HFA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRanHfaDetailService() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = SbrowserUtil.getPopupDetailOfEvent(reqParams);
        if (HFA_IFHO.equals(type)) {
            return sBrowserRanHfaIfhoPopupDetailService.getData(reqParams);
        } else if (HFA_SOHO.equals(type)) {
            return sBrowserRanHfaSohoPopupDetailService.getData(reqParams);
        } else if (HFA_HSDSCH.equals(type)) {
            return sBrowserRanHfaHsdschPopupDetailService.getData(reqParams);
        } else if (HFA_IRAT.equals(type)) {
            return sBrowserRanHfaIratPopupDetailService.getData(reqParams);
        }

        throw new WebApplicationException(404);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAN_SESSION_HFA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRanSessionHfaDetailService() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = SbrowserUtil.getPopupDetailOfEvent(reqParams);

        if (HFA_IRAT.equals(type)) {
            return sBrowserHHOPopupDetailService.getData(reqParams);
        } else if (HFA_HSDSCH_SUC.equals(type)) {
            return sBrowserSucHsdschDetailService.getData(reqParams);
        }
        throw new WebApplicationException(404);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + CORE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserCorePopupDetailServiceAsCSV() {
        return sBrowserSgehPopupDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRabPopupDetailServiceAsCSV() {
        return sBrowserRabPopupDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAN_CFA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRanCfaPopupDetailServiceAsCSV() {
        return sBrowserRanCfaPopupDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAN_HFA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRanHfaPopupDetailServiceAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = SbrowserUtil.getPopupDetailOfEvent(reqParams);
        if (HFA_IFHO.equals(type)) {
            return sBrowserRanHfaIfhoPopupDetailService.getDataAsCSV(reqParams, response);
        } else if (HFA_SOHO.equals(type)) {
            return sBrowserRanHfaSohoPopupDetailService.getDataAsCSV(reqParams, response);
        } else if (HFA_HSDSCH.equals(type)) {
            return sBrowserRanHfaHsdschPopupDetailService.getDataAsCSV(reqParams, response);
        } else if (HFA_IRAT.equals(type)) {
            return sBrowserRanHfaIratPopupDetailService.getDataAsCSV(reqParams, response);
        }
        throw new WebApplicationException(404);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RAN_SESSION_HFA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRanSessionHfaPopupDetailServiceAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = SbrowserUtil.getPopupDetailOfEvent(reqParams);

        if (HFA_IRAT.equals(type)) {
            return sBrowserHHOPopupDetailService.getDataAsCSV(reqParams, response);
        } else if (HFA_HSDSCH_SUC.equals(type)) {
            return sBrowserSucHsdschDetailService.getDataAsCSV(reqParams, response);
        }
        throw new WebApplicationException(404);
    }

    @Path(WEB_SERVER_DIST)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserWebServerDistService() {
        return sBrowserServerDistPopupService.getData(mapResourceLayerParameters());
    }

    @Path(WEB_SERVER_DIST)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserWebServerDistServiceAsCSV() {
        return sBrowserServerDistPopupService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RRC_MEAS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRrcMeasPopupService() {
        return sBrowserRrcPopupDetailService.getData(mapResourceLayerParameters());
    }

    @Path(PROPERTIES + PATH_SEPARATOR + RRC_MEAS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRrcMeasPopupServiceAsCSV() {
        return sBrowserRrcPopupDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getData()
     */
    @Override
    public String getData() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Override
    public Response getDataAsCSV() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

}
