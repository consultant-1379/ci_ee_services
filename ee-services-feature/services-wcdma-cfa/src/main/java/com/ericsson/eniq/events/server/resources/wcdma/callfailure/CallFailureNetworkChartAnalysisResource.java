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
public class CallFailureNetworkChartAnalysisResource extends AbstractResource {

    private static final String WCDMA_CALL_FAILURE_CONTROLLER_ANALYSIS_BY_DISCONNECTION_CHART_SERVICE = "RNCByDisconChartResource";

    private static final String WCDMA_CALL_FAILURE_CONTROLLER_ANALYSIS_BY_RAB_TYPE_CHART_SERVICE = "RNCByRABTypeChartResource";

    private static final String WCDMA_CALL_FAILURE_CONTROLLER_ANALYSIS_BY_RAB_DESC_CHART_SERVICE = "RNCByRABDescChartResource";

    private static final String WCDMA_CALL_FAILURE_CELL_ANALYSIS_BY_DISCONNECTION_CHART_SERVICE = "CellByDisconChartResource";

    private static final String WCDMA_CALL_FAILURE_CELL_ANALYSIS_BY_RAB_TYPE_CHART_SERVICE = "CellByRABTypeChartResource";

    private static final String WCDMA_CALL_FAILURE_CELL_ANALYSIS_BY_RAB_DESC_CHART_SERVICE = "CellByRABDescChartResource";

    @EJB(beanName = WCDMA_CALL_FAILURE_CONTROLLER_ANALYSIS_BY_DISCONNECTION_CHART_SERVICE)
    private Service controllerAnalysisByDisconnectionChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_CONTROLLER_ANALYSIS_BY_RAB_TYPE_CHART_SERVICE)
    private Service controllerAnalysisByRABTypeChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_CONTROLLER_ANALYSIS_BY_RAB_DESC_CHART_SERVICE)
    private Service controllerAnalysisByRABDescriptionChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_CELL_ANALYSIS_BY_DISCONNECTION_CHART_SERVICE)
    private Service cellAnalysisByDisconnectionChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_CELL_ANALYSIS_BY_RAB_TYPE_CHART_SERVICE)
    private Service cellAnalysisByRABTypeChartResource;

    @EJB(beanName = WCDMA_CALL_FAILURE_CELL_ANALYSIS_BY_RAB_DESC_CHART_SERVICE)
    private Service cellAnalysisByRABDescChartResource;

    @Path("PIE_CHART_NODE_BY_DISCONNECTION")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDisconnectionChartByNode() {
        return controllerAnalysisByDisconnectionChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_NODE_BY_DISCONNECTION")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDisconnectionChartByNodeAsCSV() {
        return controllerAnalysisByDisconnectionChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path("PIE_CHART_NODE_BY_RABTYPE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRABTypeChartByNode() {
        return controllerAnalysisByRABTypeChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_NODE_BY_RABTYPE")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRABTypeChartByNodeAsCSV() {
        return controllerAnalysisByRABTypeChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path("PIE_CHART_NODE_BY_RABDESC")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRABDescriptionChartByNode() {
        return controllerAnalysisByRABDescriptionChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_NODE_BY_RABDESC")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRABDescriptionChartByNodeAsCSV() {
        return controllerAnalysisByRABDescriptionChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    ///

    @Path("PIE_CHART_CELL_BY_DISCONNECTION")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDisconnectionChartByCell() {
        return cellAnalysisByDisconnectionChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_CELL_BY_DISCONNECTION")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDisconnectionChartByCellAsCSV() {
        return cellAnalysisByDisconnectionChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path("PIE_CHART_CELL_BY_RABTYPE")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRABTypeChartByCell() {
        return cellAnalysisByRABTypeChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_CELL_BY_RABTYPE")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRABTypeChartByCellAsCSV() {
        return cellAnalysisByRABTypeChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path("PIE_CHART_CELL_BY_RABDESC")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRABDescChartByCell() {
        return cellAnalysisByRABDescChartResource.getData(mapResourceLayerParameters());
    }

    @Path("PIE_CHART_CELL_BY_RABDESC")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRABDescChartByCellAsCSV() {
        return cellAnalysisByRABDescChartResource.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }
}