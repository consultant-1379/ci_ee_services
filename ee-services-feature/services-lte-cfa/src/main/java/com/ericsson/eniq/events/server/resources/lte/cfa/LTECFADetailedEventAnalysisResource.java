/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.lte.cfa;

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
 * Provide detailed event analysis data for LTE Call Failure data
 *
 * @author eemecoy
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class LTECFADetailedEventAnalysisResource extends AbstractResource {

    private static final String LTE_CALL_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFASubscriberDetailedEventAnalysisService";

    private static final String LTE_CALL_FAILURE_SUBSCRIBER_DETAILED_REOCCURING_FAILURE_ANALYSIS_SERVICE = "LTECFASubscriberDetailedReoccFailureService";

    private static final String LTE_CALL_FAILURE_ENODEB_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAEnodeBDetailedEventAnalysisService";

    private static final String LTE_CALL_FAILURE_ECELL_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAECellDetailedEventAnalysisService";

    private static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAEnodeBCauseCodeDetailedEAService";

    private static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAEcellCauseCodeDetailedEAService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFATrackingAreaDetailedEAService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFATrackingAreaCCDetailedEAService";

    private static final String LTE_CALL_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFATerminalDetailedEventAnalysisService";

    @EJB(beanName = LTE_CALL_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureSubscriberDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_SUBSCRIBER_DETAILED_REOCCURING_FAILURE_ANALYSIS_SERVICE)
    private Service lteCallFailureSubscriberReoccuringFailureAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureEnodeBDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureECellDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureEnodeBCauseCodeDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureEcellCauseCodeDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureTrackingAreaDetailedEAService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCfaTerminalDetailedEventAnalysisService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureSubscriberDetailedEventAnalysisService() {
        return lteCallFailureSubscriberDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureSubscriberDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureSubscriberDetailedEventAnalysisService
                .getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_REOCCURING)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureSubscriberReoccuringFailureAnalysisService() {
        return lteCallFailureSubscriberReoccuringFailureAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_REOCCURING)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureSubscriberReoccuringFailureAnalysisServiceAsCSV() {
        return lteCallFailureSubscriberReoccuringFailureAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }

    @Path(ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureEnodeBDetailedEventAnalysisService() {
        return lteCallFailureEnodeBDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureEnodeBDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureEnodeBDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureECellDetailedEventAnalysisService() {
        return lteCallFailureECellDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureECellDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureECellDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CAUSE_CODE_ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureEnodeBCauseCodeDetailedEventAnalysisService() {
        return lteCallFailureEnodeBCauseCodeDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE_ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureEnodeBCauseCodeDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureEnodeBCauseCodeDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }

    @Path(CAUSE_CODE_ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureEcellCauseCodeDetailedEventAnalysisService() {
        return lteCallFailureEcellCauseCodeDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE_ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureEcellCauseCodeDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureEcellCauseCodeDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }

    @Path(TRACKING_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureTrackingAreaDetailedEventAnalysisService() {
        return lteCallFailureTrackingAreaDetailedEAService.getData(mapResourceLayerParameters());
    }

    @Path(TRACKING_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureTrackingAreaDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureTrackingAreaDetailedEAService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CAUSE_CODE_TRACKING_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureTrackingAreaCauseCodeDetailedEventAnalysisService() {
        return lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE_TRACKING_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureTrackingAreaCauseCodeDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService.getDataAsCSV(
                mapResourceLayerParameters(), response);
    }

    @Path(TERMINAL_SERVICES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLTECallFailureTerminalDetailedEventAnalysisService() {
        return lteCfaTerminalDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(TERMINAL_SERVICES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLTECallFailureTerminalDetailedEventAnalysisServiceAsCSV() {
        return lteCfaTerminalDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
