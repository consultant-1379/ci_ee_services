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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author echimma
 * @since 2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class LTECFARankingResource extends AbstractResource {

    private static final String LTE_CALLFAILURE_SUBSCRIBER_CALL_SETUP_FAILURE_RANKING_SERVICE = "LTECFASubscriberCallSetupFailureRankingService";

    private static final String LTE_CALLFAILURE_SUBSCRIBER_CALL_DROP_RANKING_SERVICE = "LTECFASubscriberCallDropRankingService";

    private static final String LTE_CALLFAILURE_ACCESS_AREA_CALL_SETUP_FAILURE_RANKING_SERVICE = "LTECFAAccessAreaCallSetupRankingService";

    private static final String LTE_CALLFAILURE_ACCESS_AREA_CALL_DROP_RANKING_SERVICE = "LTECFAAccessAreaCallDropRankingService";

    private static final String LTE_CALLFAILURE_ENODEB_CALL_SETUP_FAILURE_RANKING_SERVICE = "LTECFAEnodeBCallSetupRankingService";

    private static final String LTE_CALLFAILURE_ENODEB_CALL_DROP_RANKING_SERVICE = "LTECFAEnodeBCallDropRankingService";

    private static final String LTE_CALLFAILURE_SUBSCRIBER_REOCCURING_FAILURE_RANKING_SERVICE = "LTECFASubscriberReoccuringFailureRankingService";

    private static final String LTE_CALLFAILURE_CAUSE_CODE_CALL_DROP_RANKING_SERVICE = "LTECFACauseCodeCallDropRankingService";

    private static final String LTE_CALLFAILURE_CAUSE_CODE_CALL_SETUP_FAILURE_RANKING_SERVICE = "LTECFACauseCodeCallSetupRankingService";

    private static final String LTE_CALLFAILURE_TERMINAL_CALL_DROP_RANKING_SERVICE = "LTECFATerminalCallDropRankingService";

    private static final String LTE_CALLFAILURE_TERMINAL_CALL_SETUP_FAILURE_RANKING_SERVICE = "LTECFATerminalCallSetupRankingService";

    private static final String LTE_CALLFAILURE_TRACKING_AREA_CALL_DROP_RANKING_SERVICE = "LTECFATrackingAreaCallDropRankingService";

    private static final String LTE_CALLFAILURE_TRACKING_AREA_SETUP_FAILURE_RANKING_SERVICE = "LTECFATrackingAreaCallSetupRankingService";

    @EJB(beanName = LTE_CALLFAILURE_SUBSCRIBER_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service lteSubscriberCallSetupFailureRankingService;

    @EJB(beanName = LTE_CALLFAILURE_SUBSCRIBER_CALL_DROP_RANKING_SERVICE)
    private Service lteSubscriberCallDropRankingService;

    @EJB(beanName = LTE_CALLFAILURE_SUBSCRIBER_REOCCURING_FAILURE_RANKING_SERVICE)
    private Service lteCallFailureSubscriberReoccuringFailureRankingService;

    @EJB(beanName = LTE_CALLFAILURE_ACCESS_AREA_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service lteCallFailureAccessAreaCallSetupRankingService;

    @EJB(beanName = LTE_CALLFAILURE_ACCESS_AREA_CALL_DROP_RANKING_SERVICE)
    private Service lteCallFailureAccessAreaCallDropRankingService;

    @EJB(beanName = LTE_CALLFAILURE_ENODEB_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service lteCallFailureeNodeBCallSetupRankingService;

    @EJB(beanName = LTE_CALLFAILURE_ENODEB_CALL_DROP_RANKING_SERVICE)
    private Service lteCallFailureeNodeBCallDropRankingService;

    @EJB(beanName = LTE_CALLFAILURE_CAUSE_CODE_CALL_DROP_RANKING_SERVICE)
    private Service lteCallFailureCauseCodeCallDropRankingService;

    @EJB(beanName = LTE_CALLFAILURE_CAUSE_CODE_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service lteCallFailureCauseCodeCallSetupRankingService;

    @EJB(beanName = LTE_CALLFAILURE_TERMINAL_CALL_DROP_RANKING_SERVICE)
    private Service lteCallFailureTerminalCallDropRankingService;

    @EJB(beanName = LTE_CALLFAILURE_TERMINAL_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service lteCallFailureTerminalCallSetupRankingService;

    @EJB(beanName = LTE_CALLFAILURE_TRACKING_AREA_CALL_DROP_RANKING_SERVICE)
    private Service lteCallFailureTrackingAreaCallDropRankingService;

    @EJB(beanName = LTE_CALLFAILURE_TRACKING_AREA_SETUP_FAILURE_RANKING_SERVICE)
    private Service lteCallFailureTrackingAreaCallSetupRankingService;

    @Path(SUBSCRIBER_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingSub() {
        return lteSubscriberCallSetupFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingSubAsCSV() {
        return lteSubscriberCallSetupFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingSub() {
        return lteSubscriberCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingSubAsCSV() {
        return lteSubscriberCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_REOCCURING)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReoccuringFailureRankingSub() {
        return lteCallFailureSubscriberReoccuringFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_REOCCURING)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getReoccuringFailureRankingSubAsCSV() {
        return lteCallFailureSubscriberReoccuringFailureRankingService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }

    @Path(ACCESS_AREA_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingAccessArea() {
        return lteCallFailureAccessAreaCallSetupRankingService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingAccessAreaAsCSV() {
        return lteCallFailureAccessAreaCallSetupRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ACCESS_AREA_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingAccessArea() {
        return lteCallFailureAccessAreaCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingAccessAreaAsCSV() {
        return lteCallFailureAccessAreaCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ENODEB_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingeNodeB() {
        return lteCallFailureeNodeBCallSetupRankingService.getData(mapResourceLayerParameters());
    }

    @Path(ENODEB_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingeNodeBAsCSV() {
        return lteCallFailureeNodeBCallSetupRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ENODEB_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingeNodeB() {
        return lteCallFailureeNodeBCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(ENODEB_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingeNodeBAsCSV() {
        return lteCallFailureeNodeBCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CAUSE_CODE_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingCauseCode() {
        return lteCallFailureCauseCodeCallSetupRankingService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingCauseCodeAsCSV() {
        return lteCallFailureCauseCodeCallSetupRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CAUSE_CODE_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingCauseCode() {
        return lteCallFailureCauseCodeCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingCauseCodeAsCSV() {
        return lteCallFailureCauseCodeCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TERMINAL_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingTerminal() {
        return lteCallFailureTerminalCallSetupRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TERMINAL_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingTerminalAsCSV() {
        return lteCallFailureTerminalCallSetupRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TERMINAL_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingTerminal() {
        return lteCallFailureTerminalCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TERMINAL_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingTerminalAsCSV() {
        return lteCallFailureTerminalCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TRACKING_AREA_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingTrackingArea() {
        return lteCallFailureTrackingAreaCallSetupRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TRACKING_AREA_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingTrackingAreaAsCSV() {
        return lteCallFailureTrackingAreaCallSetupRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TRACKING_AREA_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingTrackingArea() {
        return lteCallFailureTrackingAreaCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TRACKING_AREA_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingTrackingAreaAsCSV() {
        return lteCallFailureTrackingAreaCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
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
