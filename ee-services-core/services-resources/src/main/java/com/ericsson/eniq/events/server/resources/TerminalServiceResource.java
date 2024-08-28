/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.ericsson.eniq.events.server.resources.dtput.DataVolumeAnalysisResource;
import com.ericsson.eniq.events.server.resources.dtput.DatavolRankingResource;
import com.ericsson.eniq.events.server.resources.gsm.GSMResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFADetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAEventSummaryResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureTerminalAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureTerminalChartAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureTerminalEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureTerminalFilterAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureTerminalGroupAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureTerminalAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureTerminalGroupAnalysisResource;

/**
 * The Class TerminalServiceResource.
 * Sub-root resource of RESTful service.
 * 
 * @author ehaoswa
 * 
 * @since  May 2010
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class TerminalServiceResource {

    @EJB
    protected DataVolumeAnalysisResource dataVolumeAnalysisResource;

    @EJB
    protected EventAnalysisResource eventAnalysisResource;

    @EJB
    protected MultipleRankingResource multipleRankingResource;

    @EJB
    protected KPIResource kpiResource;

    @EJB
    protected TerminalAndGroupAnalysisResource terminalAndGroupAnalysisResource;

    @EJB
    protected GroupMgtResource groupMgtResource;

    @EJB
    protected QOSStatisticsResource qosStatisticsResource;

    @EJB
    protected DatavolRankingResource datavolRankingResource;

    @EJB
    protected CallFailureTerminalAnalysisResource callFailureTerminalAnalysisResource;

    @EJB
    protected CallFailureTerminalGroupAnalysisResource callFailureTerminalGroupAnalysisResource;

    @EJB
    protected CallFailureTerminalEventAnalysisResource callFailureTerminalEventAnalysisResource;

    @EJB
    protected WCDMAHandoverFailureTerminalAnalysisResource wcdmaHandoverFailureTerminalAnalysisResource;

    @EJB
    protected WCDMAHandoverFailureTerminalGroupAnalysisResource wcdmaHandoverFailureTerminalGroupAnalysisResource;

    @EJB
    protected WCDMAHandoverFailureEventSummaryResource wcdmaHandoverFailureEventSummaryResource;

    @EJB
    protected LTECFAEventSummaryResource lteCallFailureEventSummaryResource;

    @EJB
    protected LTECFADetailedEventAnalysisResource lteCallFailureDetailedEventAnalysisResource;

    @EJB
    protected GSMResource gsmResource;

    @EJB
    protected LTEHandoverFailureEventSummaryResource lteHFAEventSummaryResource;

    @EJB
    protected LTEHandoverFailureDetailedEventAnalysisResource lteHandoverFailureDetailedEventAnalysisResource;

    @EJB
    protected CallFailureTerminalFilterAnalysisResource callFailureTerminalFilterAnalysisResource;

    @EJB
    protected CallFailureTerminalChartAnalysisResource callFailureTerminalChartAnalysisResource;

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
     * Gets the group analysis resource.
     *
     * @return the group analysis resource
     */
    @Path(TERMINAL_GROUP_ANALYSIS)
    public TerminalAndGroupAnalysisResource getGroupAnalysisResource() {
        return this.terminalAndGroupAnalysisResource;
    }

    /**
     * Gets the terminal analysis resource.
     *
     * @return the terminal analysis resource
     */
    @Path(TERMINAL_ANALYSIS)
    public TerminalAndGroupAnalysisResource getTerminalAnalysisResource() {
        return this.terminalAndGroupAnalysisResource;
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
     * Gets the QoS statistics resource.
     *
     * @return the QoS statistics resource
     */
    @Path(QOS_STATISTICS)
    public QOSStatisticsResource getQOSStatisticsResource() {
        return this.qosStatisticsResource;
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

    @Path(CALL_FAILURE_TERMINAL_ANALYSIS)
    public CallFailureTerminalAnalysisResource getCallFailureTerminalAnalysisResource() {
        return this.callFailureTerminalAnalysisResource;
    }

    @Path(CALL_FAILURE_TERMINAL_GROUP_ANALYSIS)
    public CallFailureTerminalGroupAnalysisResource getCallFailureGroupTerminalAnalysisResource() {
        return callFailureTerminalGroupAnalysisResource;

    }

    @Path(WCDMA_CFA_TERMINAL_EVENT_ANALYSIS_URL)
    public CallFailureTerminalEventAnalysisResource getCallFailureTerminalEventAnalysisResource() {
        return callFailureTerminalEventAnalysisResource;

    }

    @Path(WCDMA_HFA_TERMINAL_ANALYSIS)
    public WCDMAHandoverFailureTerminalAnalysisResource getWCDMAHandoverFailureTerminalAnalysisResource() {
        return wcdmaHandoverFailureTerminalAnalysisResource;
    }

    @Path(WCDMA_HFA_TERMINAL_GROUP_ANALYSIS)
    public WCDMAHandoverFailureTerminalGroupAnalysisResource getWCDMAHandoverFailureTerminalGroupAnalysisResource() {
        return wcdmaHandoverFailureTerminalGroupAnalysisResource;
    }

    @Path(WCDMA_HFA_EVENT_SUMMARY)
    public WCDMAHandoverFailureEventSummaryResource getWcdmaHandoverFailureEventSummaryResource() {
        return wcdmaHandoverFailureEventSummaryResource;
    }

    @Path(LTE_CALL_FAILURE_EVENT_SUMMARY)
    public LTECFAEventSummaryResource getLteCallFailureEventSummaryResource() {
        return lteCallFailureEventSummaryResource;
    }

    @Path(LTE_CALL_FAILURE_DETAILED_EVENT_ANALYSIS)
    public LTECFADetailedEventAnalysisResource getLteCallFailureDetailedEventAnalysisResource() {
        return lteCallFailureDetailedEventAnalysisResource;
    }

    @Path(LTE_HFA_EVENT_SUMMARY)
    public LTEHandoverFailureEventSummaryResource getLteHFAEventSummaryResource() {
        return lteHFAEventSummaryResource;
    }

    @Path(LTE_HFA_DETAILED_EVENT_ANALYSIS)
    public LTEHandoverFailureDetailedEventAnalysisResource getLteHandoverFailureDetailedEventAnalysisResource() {
        return lteHandoverFailureDetailedEventAnalysisResource;
    }

    @Path(GSM)
    public GSMResource getGSMResource() {
        return this.gsmResource;
    }

    @Path(WCDMA_TERMINAL_CALL_FAILURE_FILTER_ANALYSIS)
    public CallFailureTerminalFilterAnalysisResource getCallFailureTerminalFilterAnalysisResource() {
        return callFailureTerminalFilterAnalysisResource;
    }

    @Path(WCDMA_NETWORK_CALL_FAILURE_ANALYSIS)
    public CallFailureTerminalChartAnalysisResource getCallFailureTerminalChartAnalysisResource() {
        return callFailureTerminalChartAnalysisResource;
    }

}