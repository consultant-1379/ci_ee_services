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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
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
public class WCDMAHandoverFailureRankingResource extends AbstractResource {

    private static final String SOURCE_CELL_HANDOVER_FAILURE_RANKING_SERVICE = "SourceCellHandoverFailureRankingService";

    private static final String TARGET_CELL_HANDOVER_FAILURE_RANKING_SERVICE = "TargetCellHandoverFailureRankingService";

    private static final String RNC_HANDOVER_FAILURE_RANKING_SERVICE = "RNCHandoverFailureRankingService";

    private static final String TAC_HANDOVER_FAILURE_RANKING_SERVICE = "TerminalHandoverFailureRankingService";

    private static final String SUBSCRIBER_HANDOVER_FAILURE_SOHO_RANKING_SERVICE = "SubscriberSOHOFailureRankingService";

    private static final String SUBSCRIBER_HANDOVER_FAILURE_IFHO_RANKING_SERVICE = "SubscriberIFHOFailureRankingService";

    private static final String SUBSCRIBER_HANDOVER_FAILURE_IRAT_RANKING_SERVICE = "SubscriberIRATFailureRankingService";

    private static final String SUBSCRIBER_HANDOVER_FAILURE_HSDSCH_RANKING_SERVICE = "SubscriberHSDSCHFailureRankingService";

    private static final String CAUSE_CODE_HANDOVER_FAILURE_RANKIING_SERVICE = "CauseCodeHandoverFailureRankingService";

    private static final String CAUSE_CODE_HANDOVER_FAILURE_RANKING_DRILLDOWN_SERVICE = "CauseCodeHandoverFailureRankingDrilldownService";

    @EJB(beanName = SOURCE_CELL_HANDOVER_FAILURE_RANKING_SERVICE)
    private Service sourceCellHandoverFailureRankingService;

    @EJB(beanName = TARGET_CELL_HANDOVER_FAILURE_RANKING_SERVICE)
    private Service targetCellHandoverFailureRankingService;

    @EJB(beanName = RNC_HANDOVER_FAILURE_RANKING_SERVICE)
    private Service rncHandoverFailureRankingService;

    @EJB(beanName = TAC_HANDOVER_FAILURE_RANKING_SERVICE)
    private Service tacHandoverFailureRankingService;

    @EJB(beanName = SUBSCRIBER_HANDOVER_FAILURE_SOHO_RANKING_SERVICE)
    private Service subscriberSOHOFailureRankingService;

    @EJB(beanName = SUBSCRIBER_HANDOVER_FAILURE_IFHO_RANKING_SERVICE)
    private Service subscriberIFHOFailureRankingService;

    @EJB(beanName = SUBSCRIBER_HANDOVER_FAILURE_IRAT_RANKING_SERVICE)
    private Service subscriberIRATFailureRankingService;

    @EJB(beanName = SUBSCRIBER_HANDOVER_FAILURE_HSDSCH_RANKING_SERVICE)
    private Service subscriberHSDSCHFailureRankingService;

    @EJB(beanName = CAUSE_CODE_HANDOVER_FAILURE_RANKIING_SERVICE)
    private Service causeCodeHandoverFailureRankingService;

    @EJB(beanName = CAUSE_CODE_HANDOVER_FAILURE_RANKING_DRILLDOWN_SERVICE)
    private Service causeCodeHandoverFailureRankingDrilldownService;

    @Path(RNC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandOverFailureRankingRNC() {
        return rncHandoverFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(RNC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureRankingRNCAsCSV() {
        return rncHandoverFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TYPE_SOURCE_CELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSourceCellHandoverFailureRanking() {
        return sourceCellHandoverFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_SOURCE_CELL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSourceCellHandoverFailureRankingAsCSV() {
        return sourceCellHandoverFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TYPE_TARGET_CELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTargetCellHandoverFailureRanking() {
        return targetCellHandoverFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_TARGET_CELL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getTargetCellHandoverFailureRankingAsCSV() {
        return targetCellHandoverFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_SOHO)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSOHOFailureRankingSub() {
        return subscriberSOHOFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_SOHO)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSOHOFailureRankingSubAsCSV() {
        return subscriberSOHOFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_IFHO)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIFHOFailureRankingSub() {
        return subscriberIFHOFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_IFHO)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getIFHOFailureRankingSubAsCSV() {
        return subscriberIFHOFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_IRAT)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIRATFailureRankingSub() {
        return subscriberIRATFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_IRAT)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getIRATFailureRankingSubAsCSV() {
        return subscriberIRATFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(SUBSCRIBER_FOR_HSDSCH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHSDSCHFailureRankingSub() {
        return subscriberHSDSCHFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(SUBSCRIBER_FOR_HSDSCH)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHSDSCHFailureRankingSubAsCSV() {
        return subscriberHSDSCHFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TYPE_TAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverRankingTAC() {
        return tacHandoverFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_TAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverRankingTACAsCSV() {
        return tacHandoverFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CAUSE_CODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandOverFailureRankingCC() {
        return causeCodeHandoverFailureRankingService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandOverFailureRankingCCAsCSV() {
        return causeCodeHandoverFailureRankingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CAUSE_CODE + "/drilldown")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandOverFailureRankingCCDrilldown() {
        return causeCodeHandoverFailureRankingDrilldownService.getData(mapResourceLayerParameters());
    }

    @Path(CAUSE_CODE + "/drilldown")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandOverFailureRankingCCDrilldownAsCSV() {
        return causeCodeHandoverFailureRankingDrilldownService.getDataAsCSV(mapResourceLayerParameters(), response);
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
