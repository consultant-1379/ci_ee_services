/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.callfailure;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author eadrhyn
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureTerminalChartAnalysisResource extends AbstractResource {

    private static final String WCDMA_CALL_FAILURE_TERMINAL_ANALYSIS_BY_DISCONNECTION_CHART_SERVICE = "TACByDisconChartResource";

    private static final String WCDMA_CALL_FAILURE_TERMINAL_ANALYSIS_BY_RAB_TYPE_CHART_SERVICE = "TACByRABTypeChartResource";

    private static final String WCDMA_CALL_FAILURE_TERMINAL_ANALYSIS_BY_RAB_DESC_CHART_SERVICE = "TACByRABDescChartResource";

    @EJB(beanName = WCDMA_CALL_FAILURE_TERMINAL_ANALYSIS_BY_DISCONNECTION_CHART_SERVICE)
    private Service terminalAnalysisByDisconnectionChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_TERMINAL_ANALYSIS_BY_RAB_TYPE_CHART_SERVICE)
    private Service terminalAnalysisByRABTypeChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_TERMINAL_ANALYSIS_BY_RAB_DESC_CHART_SERVICE)
    private Service terminalAnalysisByRABDescChartResource;

    @Path("PIE_CHART_TERMINAL_BY_DISCONNECTION")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDisconnectionChartByTerminal() {
        return terminalAnalysisByDisconnectionChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_TERMINAL_BY_DISCONNECTION")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDisconnectionChartByTerminalAsCSV() {
        return terminalAnalysisByDisconnectionChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path("PIE_CHART_TERMINAL_BY_RABTYPE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRABTypeChartByTerminal() {
        return terminalAnalysisByRABTypeChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_TERMINAL_BY_RABTYPE")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRABTypeChartByTerminalAsCSV() {
        return terminalAnalysisByRABTypeChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path("PIE_CHART_TERMINAL_BY_RABDESC")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRABDescChartByTerminal() {
        return terminalAnalysisByRABDescChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_TERMINAL_BY_RABDESC")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRABDescChartByTerminalAsCSV() {
        return terminalAnalysisByRABDescChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }
}