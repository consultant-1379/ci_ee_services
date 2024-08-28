/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMAHandoverFailureEventSummaryResource extends AbstractResource {

    private static final String RNC_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE = "RNCHandoverFailureEventSummaryService";

    private static final String TAC_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE = "TACHandoverFailureEventSummaryService";

    private static final String TAC_HANDOVER_FAILURE_EVENT_ANALYSIS_SERVICE = "TACHandoverFailureEventAnalysisService";

    private static final String SUBSCRIBER_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE = "SubscriberHandoverFailureEventSummaryService";

    private static final String SOURCE_CELL_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE = "SourceCellHandoverFailureEventSummaryService";

    private static final String TARGET_CELL_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE = "TargetCellHandoverFailureEventSummaryService";

    @EJB(beanName = RNC_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service rncHandoverFailureEventSummary;

    @EJB(beanName = TAC_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service tacHandoverFailureEventSummary;

    @EJB(beanName = TAC_HANDOVER_FAILURE_EVENT_ANALYSIS_SERVICE)
    private Service tacHandoverFailureEventAnalysis;

    @EJB(beanName = SUBSCRIBER_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service subscriberHandoverFailureEventSummary;

    @EJB(beanName = SOURCE_CELL_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service sourceCellHandoverFailureEventSummary;

    @EJB(beanName = TARGET_CELL_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service targetCellHandoverFailureEventSummary;

    @Path(NODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNetworkNodeHandoverFailureEventSummary() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return rncHandoverFailureEventSummary.getData(reqParams);
        }
        final String cellType = reqParams.getFirst(CELL_TYPE_FIXED_PARAM);
        if ("TARGET".equals(cellType)) {
            return targetCellHandoverFailureEventSummary.getData(reqParams);
        }
        return sourceCellHandoverFailureEventSummary.getData(reqParams);

    }

    @Path(NODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getNetworkNodeHandoverFailureEventSummaryAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return rncHandoverFailureEventSummary.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        final String cellType = reqParams.getFirst(CELL_TYPE_FIXED_PARAM);
        if ("TARGET".equals(cellType)) {
            return targetCellHandoverFailureEventSummary.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        return sourceCellHandoverFailureEventSummary.getDataAsCSV(mapResourceLayerParameters(), response);

    }

    @Path(TAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureEventSummaryTAC() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        if (reqParams.containsKey(NODE_PARAM) || reqParams.containsKey(GROUP_NAME_PARAM)) {
            return tacHandoverFailureEventAnalysis.getData(reqParams);
        }
        return tacHandoverFailureEventSummary.getData(reqParams);
    }

    @Path(TAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureEventSummaryTACAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        if (reqParams.containsKey(NODE_PARAM) || reqParams.containsKey(GROUP_NAME_PARAM)) {
            return tacHandoverFailureEventAnalysis.getDataAsCSV(reqParams, response);
        }
        return tacHandoverFailureEventSummary.getDataAsCSV(reqParams, response);
    }

    @Path(IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureEventSummaryIMSI() {
        return subscriberHandoverFailureEventSummary.getData(mapResourceLayerParameters());
    }

    @Path(IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureEventSummaryIMSIAsCSV() {
        return subscriberHandoverFailureEventSummary.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

}
