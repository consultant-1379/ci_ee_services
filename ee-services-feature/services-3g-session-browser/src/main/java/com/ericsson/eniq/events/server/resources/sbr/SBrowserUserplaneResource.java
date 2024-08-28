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
public class SBrowserUserplaneResource extends AbstractResource {

    private static final String SESSION_BROWSER_TCP_PERFORMANCE_DETAIL_SERVICE = "SBrowserTCPPerfDetailsService";

    private static final String SESSION_BROWSER_APPLICATION_LAYER_DETAIL_SERVICE = "SBrowserAppLayerDetailsService";

    private static final String SESSION_BROWSER_TCP_PERFORMANCE_SUMMARY_SERVICE = "SBrowserTcpPerfSumService";

    private static final String SESSION_BROWSER_APPLICATION_LAYER_SUMMARY_SERVICE = "SBrowserAppLayerSumService";

    @EJB(beanName = SESSION_BROWSER_TCP_PERFORMANCE_DETAIL_SERVICE)
    private Service sBrowserTCPPerfDetailService;

    @EJB(beanName = SESSION_BROWSER_APPLICATION_LAYER_DETAIL_SERVICE)
    private Service sBrowserAppLayerDetailService;

    @EJB(beanName = SESSION_BROWSER_TCP_PERFORMANCE_SUMMARY_SERVICE)
    private Service sBrowserTcpPerfSumService;

    @EJB(beanName = SESSION_BROWSER_APPLICATION_LAYER_SUMMARY_SERVICE)
    private Service sBrowserAppLayerSumService;

    @Path(TCP_PERFORMANCE_DETAILS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserTCPPerfDetailService() {
        return sBrowserTCPPerfDetailService.getData(mapResourceLayerParameters());
    }

    @Path(TCP_PERFORMANCE_DETAILS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserTCPPerfDetailServiceAsCSV() {
        return sBrowserTCPPerfDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(APPLICATION_LAYER_DETAILS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserAppLayerDetailService() {
        return sBrowserAppLayerDetailService.getData(mapResourceLayerParameters());
    }

    @Path(APPLICATION_LAYER_DETAILS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserAppLayerDetailServiceAsCSV() {
        return sBrowserAppLayerDetailService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TCP_PERFORMANCE_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserTCPPerfSumService() {
        return sBrowserTcpPerfSumService.getData(mapResourceLayerParameters());
    }

    @Path(TCP_PERFORMANCE_SUMMARY)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserTCPPerfSumServiceAsCSV() {
        return sBrowserTcpPerfSumService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(APPLICATION_LAYER_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserAppLayerSumService() {
        return sBrowserAppLayerSumService.getData(mapResourceLayerParameters());
    }

    @Path(APPLICATION_LAYER_SUMMARY)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserAppLayerSumServiceAsCSV() {
        return sBrowserAppLayerSumService.getDataAsCSV(mapResourceLayerParameters(), response);
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
