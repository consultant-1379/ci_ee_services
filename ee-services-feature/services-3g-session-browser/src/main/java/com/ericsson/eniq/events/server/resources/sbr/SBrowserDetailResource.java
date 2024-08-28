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
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class SBrowserDetailResource extends AbstractResource {

    private static final String SESSION_BROWSER_RAB_DETAIL_SERVICE = "SBrowserRabDetailService";

    private static final String SESSION_BROWSER_CORE_DETAIL_SERVICE = "SBrowserCoreDetailService";

    private static final String SESSION_BROWSER_RAN_CFA_DETAIL_SERVICE = "SBrowserRanCfaDetailService";

    private static final String SESSION_BROWSER_RAN_HFA_DETAIL_SERVICE = "SBrowserRanHfaDetailService";

    private static final String SESSION_BROWSER_RAN_SESSION_DETAIL_SERVICE = "SBrowserRanSessionDetailService";

    private static final String SESSION_BROWSER_RRC_MEAS_DETAIL_SERVICE = "SBrowserRrcDetailService";

    private static final String SESSION_BROWSER_RCC_DETAIL_SERVICE = "SBrowserRccDetailService";

    @EJB(beanName = SESSION_BROWSER_RAB_DETAIL_SERVICE)
    private Service sBrowserRabDetailService;

    @EJB(beanName = SESSION_BROWSER_CORE_DETAIL_SERVICE)
    private Service sBrowserCoreDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_CFA_DETAIL_SERVICE)
    private Service sBrowserRanCfaDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_HFA_DETAIL_SERVICE)
    private Service sBrowserRanHfaDetailService;

    @EJB(beanName = SESSION_BROWSER_RAN_SESSION_DETAIL_SERVICE)
    private Service sBrowserRanSessionService;

    @EJB(beanName = SESSION_BROWSER_RRC_MEAS_DETAIL_SERVICE)
    private Service sBrowserRrcDetailService;

    @EJB(beanName = SESSION_BROWSER_RCC_DETAIL_SERVICE)
    private Service sBrowserRccDetailService;

    @Path(RAN_SESSION)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSessionBrowserRanSessionDetails() {
        return sBrowserRanSessionService.getData(mapResourceLayerParameters());
    }

    @Path(RAN_SESSION)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSessionBrowserRanSessionDetailsAsCSV() {
        return sBrowserRanSessionService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(RADIO_AND_CELL_CONDITION)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSessionBrowserRccDetails() {
        return sBrowserRccDetailService.getData(mapResourceLayerParameters());
    }

    @Path(RADIO_AND_CELL_CONDITION)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSessionBrowserRccDetailsAsCSV() {
        return sBrowserRccDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(RAB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRabDetailService() {
        return sBrowserRabDetailService.getData(mapResourceLayerParameters());
    }

    @Path(RAB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRabDetailServiceAsCSV() {
        return sBrowserRabDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CORE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserCoreDetailService() {
        return sBrowserCoreDetailService.getData(mapResourceLayerParameters());
    }

    @Path(CORE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserCoreDetailServiceAsCSV() {
        return sBrowserCoreDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(RAN_CFA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRanCfaDetailService() {
        return sBrowserRanCfaDetailService.getData(mapResourceLayerParameters());
    }

    @Path(RAN_CFA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRanCfaDetailServiceAsCSV() {
        return sBrowserRanCfaDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(RAN_HFA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRanHfaDetailService() {
        return sBrowserRanHfaDetailService.getData(mapResourceLayerParameters());
    }

    @Path(RAN_HFA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRanHfaDetailServiceAsCSV() {
        return sBrowserRanHfaDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(RRC_MEAS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRrcDetailService() {
        return sBrowserRrcDetailService.getData(mapResourceLayerParameters());
    }

    @Path(RRC_MEAS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRrcDetailServiceAsCSV() {
        return sBrowserRrcDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
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
