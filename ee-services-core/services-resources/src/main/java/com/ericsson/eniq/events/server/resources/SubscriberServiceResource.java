/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.ericsson.eniq.events.server.resources.dtput.DataVolumeAnalysisResource;
import com.ericsson.eniq.events.server.resources.dtput.DatavolRankingResource;
import com.ericsson.eniq.events.server.resources.gsm.GSMResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFADetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAERABDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAEventSummaryResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFARankingResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureERABDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureSubscriberExecRankingResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureSubscriberPrepRankingResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureMultipleRecurringFailureResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureRankingResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureSubscriberEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureSubsessionBIResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.wcdma.uertt.WcdmaRealTimeTraceResource;

/**
 * The Class SubscriberServiceResource.
 * Sub-root resource of RESTful service.
 *
 * @author ehaoswa
 * 
 * @since Mar 2010
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class SubscriberServiceResource {
	
    @EJB
    protected DataVolumeAnalysisResource dataVolumeAnalysisResource;

    @EJB
    protected EventAnalysisResource eventAnalysisResource;

    @EJB
    protected GroupMgtResource groupMgtResource;

    @EJB
    protected MultipleRankingResource multipleRankingResource;

    @EJB
    protected KPIResource kpiResource;

    @EJB
    protected SubsessionBIResource subsessionBIResource;

    @EJB
    protected EventRecurringResource eventRecurringResource;

    @EJB
    protected DatavolRankingResource datavolRankingResource;

    @EJB
    protected CallFailureSubsessionBIResource callFailureSubsessionBIResource;

    @EJB
    protected CallFailureMultipleRecurringFailureResource callFailureMultipleRecurringFailureResource;

    @EJB
    protected CallFailureRankingResource callFailureRankingResource;

    @EJB
    protected LTECFAEventSummaryResource lteCallFailureEventSummaryResource;

    @EJB
    protected LTECFADetailedEventAnalysisResource lteCallFailureDetailedEventAnalysisResource;

    @EJB
    protected LTECFAERABDetailedEventAnalysisResource lteCallFailureERABDetailedEventAnalysisResource;

    @EJB
    protected LTEHandoverFailureERABDetailedEventAnalysisResource lteHandoverFailureERABDetailedEventAnalysisResource;

    @EJB
    protected LTECFARankingResource lteCallFailureRankingResource;

    @EJB
    protected LTEHandoverFailureEventSummaryResource lteHandoverFailureEventSummaryResource;

    @EJB
    protected LTEHandoverFailureDetailedEventAnalysisResource lteHandoverFailureDetailedEventAnalysisResource;

    @EJB
    protected WCDMAHandoverFailureEventSummaryResource wcdmaHandoverFailureEventSummaryResource;

    @EJB
    protected WCDMAHandoverFailureDetailedEventAnalysisResource wcdmaHandoverFailureDetailedEventAnalysisResource;

    @EJB
    protected CallFailureSubscriberEventAnalysisResource callFailureSubscriberEventAnalysisResource;

    @EJB
    protected GSMResource gsmResource;

    @EJB
    protected LTEHandoverFailureSubscriberExecRankingResource lteHandoverFailureSubscriberExecRankingResource;

    @EJB
    protected LTEHandoverFailureSubscriberPrepRankingResource lteHandoverFailureSubscriberPrepRankingResource;
    
    @EJB
    protected WcdmaRealTimeTraceResource wcdmaRealTimeTraceResource;

    /**
     * Gets the data volume analysis resource.
     *
     * @return the data volume analysis resource
     */
    @Path(DATAVOLUME_ANALYSIS)
    public DataVolumeAnalysisResource getDataVolumeAnalysisResource() {
        return this.dataVolumeAnalysisResource;
    }

    /**
     * Gets the event analysis resource.
     *
     * @return the event analysis resource
     */
    @Path(EVENT_ANALYSIS)
    public EventAnalysisResource getEventAnalysisResource() {
        return this.eventAnalysisResource;
    }

    /**
     * Gets the group management resource.
     *
     * @return the event analysis resource
     */
    @Path(GROUP)
    public GroupMgtResource getGroupMgtResource() {
        return groupMgtResource;
    }

    /**
     * Gets the multiple ranking resource.
     *
     * @return the multiple ranking resource
     */
    @Path(RANKING_ANALYSIS)
    public MultipleRankingResource getMultipleRankingResource() {
        return this.multipleRankingResource;
    }

    /**
     * Gets the kpi resource.
     *
     * @return the kpi resource
     */
    @Path(KPI)
    public KPIResource getKpiResource() {
        return this.kpiResource;
    }

    /**
     * Gets the SubBI resource.
     *
     * @return the SubBI resource
     */
    @Path(SUBBI)
    public SubsessionBIResource getSubsessionBIResource() {
        return this.subsessionBIResource;
    }

    /**
     * Gets the event recurring resource.
     *
     * @return the event recurring resource
     */
    @Path(EVENT_RECURRING)
    public EventRecurringResource getEventRecurringResource() {
        return this.eventRecurringResource;
    }

    /**
     * Gets the data volume ranking resource.
     *
     * @return the data volume ranking resource
     */
    @Path(DATAVOL_RANKING_ANALYSIS)
    public DatavolRankingResource getDatavolRankingResource() {
        return this.datavolRankingResource;
    }

    /**
     * Gets the data volume group ranking resource.
     *
     * @return the data volume group ranking resource
     */
    @Path(DATAVOL_GROUP_RANKING_ANALYSIS)
    public DatavolRankingResource getDatavolGroupRankingResource() {
        return this.datavolRankingResource;
    }

    @Path(CALLFAILURE_SUBSESSION_BI)
    public CallFailureSubsessionBIResource getCallFailureSubsessionBIResource() {
        return this.callFailureSubsessionBIResource;
    }

    @Path(CALLFAILURE_MULTIPLE_RECURRING_FAILURES)
    public CallFailureMultipleRecurringFailureResource getCallFailureMultipleRecurringFailuresResource() {
        return callFailureMultipleRecurringFailureResource;
    }

    @Path(CALL_FAILURE_RANKING_ANALYSIS)
    public CallFailureRankingResource getCallFailureRankingResource() {
        return callFailureRankingResource;
    }

    @Path(LTE_CALL_FAILURE_EVENT_SUMMARY)
    public LTECFAEventSummaryResource getLteCallFailureEventSummaryResource() {
        return lteCallFailureEventSummaryResource;
    }

    @Path(LTE_CALL_FAILURE_DETAILED_EVENT_ANALYSIS)
    public LTECFADetailedEventAnalysisResource getLteCallFailureDetailedEventAnalysisResource() {
        return lteCallFailureDetailedEventAnalysisResource;
    }

    @Path(LTE_CALL_FAILURE_ERAB_DETAILED_EVENT_ANALYSIS)
    public LTECFAERABDetailedEventAnalysisResource getLteCallFailureERABDetailedEventAnalysisResource() {
        return this.lteCallFailureERABDetailedEventAnalysisResource;
    }

    @Path(LTE_CALL_FAILURE_RANKING_ANALYSIS)
    public LTECFARankingResource getLteCallFailureRankingResource() {
        return this.lteCallFailureRankingResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_EVENT_SUMMARY_ANALYSIS)
    public LTEHandoverFailureEventSummaryResource getLteHandoverFailureEventSummaryResource() {
        return lteHandoverFailureEventSummaryResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS)
    public LTEHandoverFailureDetailedEventAnalysisResource getLteHandoverFailureDetailedEventAnalysisResource() {
        return lteHandoverFailureDetailedEventAnalysisResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_ERAB_DRILLDOWN)
    public LTEHandoverFailureERABDetailedEventAnalysisResource getLteHandoverFailureERABDetailedEventAnalysisResource() {
        return this.lteHandoverFailureERABDetailedEventAnalysisResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_EXEC_RANKING_ANALYSIS)
    public LTEHandoverFailureSubscriberExecRankingResource getLteHandoverFailureSubscriberExecRankingResource() {
        return lteHandoverFailureSubscriberExecRankingResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_PREP_RANKING_ANALYSIS)
    public LTEHandoverFailureSubscriberPrepRankingResource getLteHandoverFailureSubscriberPrepRankingResource() {
        return lteHandoverFailureSubscriberPrepRankingResource;
    }

    @Path(WCDMA_HFA_EVENT_SUMMARY)
    public WCDMAHandoverFailureEventSummaryResource getWcdmaHandoverFailureEventSummaryResource() {
        return this.wcdmaHandoverFailureEventSummaryResource;
    }

    @Path(WCDMA_HFA_DETAILED_EVENT_ANALYSIS)
    public WCDMAHandoverFailureDetailedEventAnalysisResource getWcdmaHandoverFailureDetailedEventAnalysisResource() {
        return this.wcdmaHandoverFailureDetailedEventAnalysisResource;
    }

    @Path(WCDMA_CALL_FAILURE_EVENT_ANALYSIS)
    public CallFailureSubscriberEventAnalysisResource getCallFailureSubscriberEventAnalysisResource() {
        return this.callFailureSubscriberEventAnalysisResource;
    }
    
    @Path(WCDMA_UERTT)
    public WcdmaRealTimeTraceResource getWcdmaRealTimeTraceResource() {
    	return this.wcdmaRealTimeTraceResource;
    }

    @Path(GSM)
    public GSMResource getGSMResource() {
        return this.gsmResource;
    }

}
