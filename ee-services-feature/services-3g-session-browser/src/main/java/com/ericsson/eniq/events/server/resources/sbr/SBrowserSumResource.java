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
public class SBrowserSumResource extends AbstractResource {
    private static final String SESSION_BROWSER_RAB_SUMMARY_SERVICE = "SBrowserRabSumService";

    private static final String SESSION_BROWSER_VISITED_CELLS_SUMMARY_SERVICE = "SBrowserVisitedCellsSumService";

    private static final String SESSION_BROWSER_RAB_SUMMARY_THRESHOLD_SERVICE = "SBrowserRABThresholdService";

    @EJB(beanName = SESSION_BROWSER_RAB_SUMMARY_SERVICE)
    private Service sBrowserRabSumService;

    @EJB(beanName = SESSION_BROWSER_VISITED_CELLS_SUMMARY_SERVICE)
    private Service sBrowserVisitedCellsSumService;

    @EJB(beanName = SESSION_BROWSER_RAB_SUMMARY_THRESHOLD_SERVICE)
    private Service sBrowserRabThresholdService;

    @Path(RAB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRabSumService() {
        return sBrowserRabSumService.getData(mapResourceLayerParameters());
    }

    @Path(RAB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRabSumServiceAsCSV() {
        return sBrowserRabSumService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(VISITED_CELLS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserVisitedCellsSumService() {
        return sBrowserVisitedCellsSumService.getData(mapResourceLayerParameters());
    }

    @Path(VISITED_CELLS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserVisitedCellsSumServiceAsCSV() {
        return sBrowserVisitedCellsSumService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(THRESHOLD)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserRabThresholdService() {
        return sBrowserRabThresholdService.getData(mapResourceLayerParameters());
    }

    @Path(THRESHOLD)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserRabThresholdServiceAsCSV() {
        return sBrowserRabThresholdService.getDataAsCSV(mapResourceLayerParameters(), response);
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
