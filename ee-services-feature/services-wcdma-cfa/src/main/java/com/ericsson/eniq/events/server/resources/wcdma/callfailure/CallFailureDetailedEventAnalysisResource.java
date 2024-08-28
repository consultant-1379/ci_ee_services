/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

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
 * Provide detailed event analysis data for WCDMA Call Failure data
 * @author eemecoy
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureDetailedEventAnalysisResource extends AbstractResource {

    private static final String RNC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "RNCCallFailureDetailedEventAnalysisService";

    private static final String CC_CALL_SETUP_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "CallSetupDetailedEventAnalysisService";

    private static final String CC_CALL_DROPS_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "CallDropsDetailedEventAnalysisService";

    private static final String TAC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TACCallFailureDetailedEventAnalysisService";

    private static final String SUBSCRIBER_CALL_DROP_DETAILED_EVENT_ANALYSIS_SERVICE = "SubscriberCallDropDetailedEventAnalysisService";

    private static final String SUBSCRIBER_CALL_SETUP_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "SubscriberCallSetupFailureDetailedEAService";

    private static final String ACCESS_AREA_DETAILED_EVENT_ANALYSIS_SERVICE = "AccessAreaCallFailureDetailedEAService";

    @EJB(beanName = RNC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service rncCallFailureDetailedEventAnalysisService;

    @EJB(beanName = CC_CALL_SETUP_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service ccCallSetupCallFailureDetailedEventAnalysisService;

    @EJB(beanName = CC_CALL_DROPS_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service ccCallDropsCallFailureDetailedEventAnalysisService;

    @EJB(beanName = TAC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service tacCallFailureDetailedEventAnalysisService;

    @EJB(beanName = SUBSCRIBER_CALL_DROP_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service subscriberCallDropDetailedEventAnalysisService;

    @EJB(beanName = SUBSCRIBER_CALL_SETUP_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service subscriberCallSetupFailureDetailedEventAnalysisService;

    @EJB(beanName = ACCESS_AREA_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service accessAreaDetailedEventAnalysisService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(RNC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDetailedEventAnalysisRNC() {
        return rncCallFailureDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(RNC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDetailedEventAnalysisRNCAsCSV() {
        return rncCallFailureDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropsDetailedEventAnalysisCC() {
        return ccCallDropsCallFailureDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropsDetailedEventAnalysisCCAsCSV() {
        return ccCallDropsCallFailureDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CALL_FAILURE_RANKING_BY_CALL_SETUP_URI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupDetailedEventAnalysisCC() {
        return ccCallSetupCallFailureDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(CALL_FAILURE_RANKING_BY_CALL_SETUP_URI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupDetailedEventAnalysisCCAsCSV() {
        return ccCallSetupCallFailureDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDetailedEventAnalysisTAC() {
        return tacCallFailureDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(TAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDetailedEventAnalysisTACAsCSV() {
        return tacCallFailureDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropDetailedEventAnalysisSubscriber() {
        return subscriberCallDropDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropDetailedEventAnalysisSubscriberAsCSV() {
        return subscriberCallDropDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureDetailedEventAnalysisSubscriber() {
        return subscriberCallSetupFailureDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureDetailedEventAnalysisSubscriberAsCSV() {
        return subscriberCallSetupFailureDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccessAreaCallSetupFailureDetailedEventAnalysis() {
        return accessAreaDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getAccessAreaCallSetupFailureDetailedEventAnalysisAsCSV() {
        return accessAreaDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

}
