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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author eemecoy
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureRankingResource extends AbstractResource {

    private static final String ACCESS_AREA_CALL_FAILURE_RANKING_SERVICE = "AccessAreaCallFailureAnalysisRankingService";

    private static final String CAUSE_CODE_CALLS_DROPPED_FAILURE_RANKING_SERVICE = "CallsDroppedCallFailureRankingService";

    private static final String CAUSE_CODE_CALL_SETUP_FAILURE_RANKING_SERVICE = "CallSetupCallFailureRankingService";

    private static final String RNC_CALL_FAILURE_RANKING_SERVICE = "RNCCallFailureRankingService";

    private static final String TAC_CALL_FAILURE_RANKING_SERVICE = "TACCallFailureRankingService";

    private static final String SUBSCRIBER_CALL_SETUP_FAILURE_RANKING_SERVICE = "SubscriberCallSetupFailureRankingService";

    private static final String SUBSCRIBER_CALL_DROP_RANKING_SERVICE = "SubscriberCallDropRankingService";

    @EJB(beanName = SUBSCRIBER_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service subscriberCallSetupFailureRankingService;

    @EJB(beanName = SUBSCRIBER_CALL_DROP_RANKING_SERVICE)
    private Service subscriberCallDropRankingService;

    @EJB(beanName = RNC_CALL_FAILURE_RANKING_SERVICE)
    private Service rncCallFailureRankingService;

    @EJB(beanName = ACCESS_AREA_CALL_FAILURE_RANKING_SERVICE)
    private Service accessAreaCallFailureRaningService;

    @EJB(beanName = TAC_CALL_FAILURE_RANKING_SERVICE)
    private Service tacCallFailureRankingService;

    @EJB(beanName = CAUSE_CODE_CALL_SETUP_FAILURE_RANKING_SERVICE)
    private Service callSetupCallFailureRankingService;

    @EJB(beanName = CAUSE_CODE_CALLS_DROPPED_FAILURE_RANKING_SERVICE)
    private Service callsDroppedCallFailureRankingService;

    @Path(RNC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRankingRNC() {
        return rncCallFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(RNC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRankingRNCAsCSV() {
        return rncCallFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccessAreaCallFailureRanking() {
        return accessAreaCallFailureRaningService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getAccessAreaCallFailureRankingAsCSV() {
        return accessAreaCallFailureRaningService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingSub() {
        return subscriberCallSetupFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_CALL_SETUP_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingSubAsCSV() {
        return subscriberCallSetupFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_CALL_DROP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallDropRankingSub() {
        return subscriberCallDropRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_CALL_DROP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallDropRankingSubAsCSV() {
        return subscriberCallDropRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureRankingTAC() {
        return tacCallFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureRankingTACAsCSV() {
        return tacCallFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CALL_FAILURE_RANKING_BY_CALL_SETUP_URI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallSetupFailureRankingByCauseCode() {
        return callSetupCallFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(CALL_FAILURE_RANKING_BY_CALL_SETUP_URI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallSetupFailureRankingByCauseCodeAsCSV() {
        return callSetupCallFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallsDroppedFailureRankingByCauseCode() {
        return callsDroppedCallFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallsDroppedFailureRankingByCauseCodeAsCSV() {
        return callsDroppedCallFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
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
