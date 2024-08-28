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
import com.ericsson.eniq.events.server.resources.dtput.DatavolRoamingAnalysisResource;
import com.ericsson.eniq.events.server.resources.dtput.NetworkDataVolumeResource;
import com.ericsson.eniq.events.server.resources.gsm.GSMResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFACauseCodeAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFADetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAEventSummaryResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAEventVolumeAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAQoSDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFAQosSummaryAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.cfa.LTECFARankingResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHFAEventVolumeResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHFAQoSDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHFAQosSummaryAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureCauseCodeAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureRankingResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureSubscriberExecRankingResource;
import com.ericsson.eniq.events.server.resources.piechart.CauseCodeAnalysisPieChartAPI;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureNetworkChartAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureNetworkEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureNetworkFilterAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.CallFailureRankingResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.WCDMACallFailureCauseCodeAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.WCDMACallFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.callfailure.WCDMACallFailureRoamingAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureCauseCodeAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureDetailedEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureEventSummaryResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureNetworkEventAnalysisResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureRankingResource;

/**
 * The Class NetworkServiceResource. Sub-root resource of RESTful service.
 *
 * @author ehaoswa
 *
 * @since Mar 2010
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class NetworkServiceResource {

    @EJB
    protected DataVolumeAnalysisResource dataVolumeAnalysisResource;

    @EJB
    protected EventAnalysisResource eventAnalysisResource;

    @EJB
    protected KPIResource kpiResource;

    @EJB
    protected CauseCodeAnalysisResource causeCodeAnalysisResource;

    @EJB
    protected EventVolumeResource eventVolumeResource;

    @EJB
    protected RoamingAnalysisResource roamingAnalysisResource;

    @EJB
    protected GroupMgtResource groupMgtResource;

    @EJB
    protected MultipleRankingResource multipleRankingResource;

    @EJB
    protected KPIRatioResource kpiRatioResource;

    @EJB
    protected TopologyResource topologyResource;

    @EJB
    protected EventRecurringResource eventRecurringResource;

    @EJB
    protected QOSStatisticsResource qosStatisticsResource;

    @EJB
    protected DatavolRankingResource datavolRankingResource;

    @EJB
    protected NetworkDataVolumeResource networkDataVolumeResource;

    @EJB
    protected CauseCodeAnalysisPieChartAPI causeCodeAnalysisPieChartAPI;

    @EJB
    protected DatavolRoamingAnalysisResource datavolRoamingAnalysisResource;

    @EJB
    protected CallFailureRankingResource callFailureRankingResource;

    @EJB
    protected LTECFARankingResource lteCallFailureRankingResource;

    @EJB
    protected LTEHandoverFailureSubscriberExecRankingResource lteHandoverFailureRankingResource;

    @EJB
    protected CallFailureEventSummaryResource callFailureEventSummaryResource;

    @EJB
    protected CallFailureDetailedEventAnalysisResource callFailureDetailedEventAnalysisResource;

    @EJB
    protected GSMResource gsmResource;

    @EJB
    protected WCDMAHandoverFailureRankingResource wcdmaHandoverFailureRankingResource;

    @EJB
    protected WCDMAHandoverFailureEventSummaryResource wcdmaHandoverFailureEventSummaryResource;

    @EJB
    protected WCDMAHandoverFailureNetworkEventAnalysisResource wcdmaHandoverFailureNetworkEventAnalysisResource;

    @EJB
    protected WCDMAHandoverFailureDetailedEventAnalysisResource wcdmaHandoverFailureDetailedEventAnalysisResource;

    @EJB
    protected LTECFAEventSummaryResource lteCallFailureEventSummaryResource;

    @EJB
    protected LTEHandoverFailureEventSummaryResource lteHandoverFailureEventSummaryResource;

    @EJB
    protected LTECFADetailedEventAnalysisResource lteCallFailureDetailedEventAnalysisResource;

    @EJB
    protected LTEHandoverFailureDetailedEventAnalysisResource lteHandoverFailureDetailedEventAnalysisResource;

    @EJB
    protected CallFailureNetworkEventAnalysisResource callFailureNetworkEventAnalysisResource;

    @EJB
    protected WCDMACallFailureRoamingAnalysisResource wcdmaCallFailureRoamingAnalysisResource;

    @EJB
    protected LTECFACauseCodeAnalysisResource lteCallFailureCauseCodeAnalysisResource;

    @EJB
    protected LTECFAQosSummaryAnalysisResource lteCallFailureQosSummaryAnalysisResource;

    @EJB
    protected LTECFAQoSDetailedEventAnalysisResource lteCallFailureQoSDetailedEventAnalysisResource;

    @EJB
    protected LTECFAEventVolumeAnalysisResource lteCFAEventVolumeAnalysisResource;

    @EJB
    protected WCDMACallFailureDetailedEventAnalysisResource wcdmaCallFailureDEAResource;

    @EJB
    protected LTEHandoverFailureRankingResource lteHandoverFailureCellRankingResource;

    @EJB
    protected LTEHandoverFailureCauseCodeAnalysisResource lteHandoverFailureCauseCodeAnalysisResource;

    @EJB
    protected KPINotificationResource kpiNotificationResource;

    @EJB
    protected LTEHFAEventVolumeResource lteHandoverFailureEventVolumeResource;

    @EJB
    protected WCDMACallFailureCauseCodeAnalysisResource wcdmaCauseCodeAnalysisResource;

    @EJB
    protected WCDMAHandoverFailureCauseCodeAnalysisResource wcdmaHfaCauseCodeAnalysisResource;

    @EJB
    protected CallFailureNetworkFilterAnalysisResource callFailureNetworkFilterAnalysisResource;

    @EJB
    protected CallFailureNetworkChartAnalysisResource callFailureNetworkChartAnalysisResource;


    @EJB
    protected IMSIRankingResource imsiRankingResource;

    /**
     * Gets the data volume roaming analysis resource.
     *
     * @return the data volume romaing analysis resource
     */
    @Path(DATAVOL_ROAMING_ANALYSIS)
    public DatavolRoamingAnalysisResource getDatavolRoamingAnalysisResource() {
        return this.datavolRoamingAnalysisResource;
    }

    @EJB
    protected LTEHFAQosSummaryAnalysisResource lteHFAQosSummaryAnalysisResource;

    @EJB
    protected LTEHFAQoSDetailedEventAnalysisResource lteHFAQoSDetailedEventAnalysisResource;

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
     * Gets the kpi resource.
     *
     * @return the kpi resource
     */
    @Path(KPI)
    public KPIResource getKpiResource() {
        return this.kpiResource;
    }

    /**
     * Gets the current value of the cause code resource.
     *
     * @return The cause code resource
     */
    @Path(CAUSE_CODE_ANALYSIS)
    public CauseCodeAnalysisResource getCauseCodeAnalysisResource() {
        return this.causeCodeAnalysisResource;
    }

    /**
     * Gets the event volume resource.
     *
     * @return the event volume resource
     */
    @Path(EVENT_VOLUME)
    public EventVolumeResource getEventVolumeResource() {
        return this.eventVolumeResource;
    }

    /**
     * Getro roaming analysis resource.
     *
     * @return the ro roaming analysis resource
     */
    @Path(ROAMING_ANALYSIS)
    public RoamingAnalysisResource getroRoamingAnalysisResource() {
        return this.roamingAnalysisResource;
    }

    /**
     * Gets the group management resource.
     *
     * @return the event volume resource
     */
    @Path(GROUP)
    public GroupMgtResource getGroupMgtResource() {
        return this.groupMgtResource;
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
     * a separated service that only do the IMSI ranking
     * @return
     */
    @Path(RANKING_ANALYSIS_IMSI)
    public IMSIRankingResource getIMSIRankingResource ()
    {
        return this.imsiRankingResource;
    }


    /**
     * Gets the kpi ratio resource.
     *
     * @return the kpi ratio resource
     */
    @Path(KPI_RATIO)
    public KPIRatioResource getKpiRatioResource() {
        return this.kpiRatioResource;
    }

    /**
     * Gets the topology resource
     *
     * @return the topologyResource
     */
    @Path(TOPOLOGY)
    public TopologyResource getTopologyResource() {
        return topologyResource;
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

    /**
     * @return the causeCodeAnalysisPieChartAPI
     */
    @Path(CAUSE_CODE_PIE_CHART)
    public CauseCodeAnalysisPieChartAPI getCauseCodeAnalysisPieChartAPI() {
        return this.causeCodeAnalysisPieChartAPI;
    }

    /**
     * Gets the data volume analysis resource.
     *
     * @return the data volume analysis resource
     */
    @Path(NETWORK_DATAVOL_ANALYSIS)
    public NetworkDataVolumeResource getNetworkDataVolumeResource() {
        return this.networkDataVolumeResource;
    }

    @Path(LTE_CALL_FAILURE_RANKING_ANALYSIS)
    public LTECFARankingResource getLteCallFailureRankingResource() {
        return this.lteCallFailureRankingResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_EXEC_RANKING_ANALYSIS)
    public LTEHandoverFailureSubscriberExecRankingResource getLteHandoverFailureRankingResource() {
        return this.lteHandoverFailureRankingResource;
    }

    @Path(CALL_FAILURE_RANKING_ANALYSIS)
    public CallFailureRankingResource getCallFailureRankingResource() {
        return this.callFailureRankingResource;
    }

    @Path(CALL_FAILURE_EVENT_SUMMARY)
    public CallFailureEventSummaryResource getCallFailureEventSummaryResource() {
        return callFailureEventSummaryResource;
    }

    @Path(CALL_FAILURE_DETAILED_EVENT_ANALYSIS)
    public CallFailureDetailedEventAnalysisResource getCallFailureDetailedEventAnalysisResource() {
        return callFailureDetailedEventAnalysisResource;
    }

    @Path(WCDMA_HFA_RANKING)
    public WCDMAHandoverFailureRankingResource getWcdmaHandoverFailureRankingResource() {
        return this.wcdmaHandoverFailureRankingResource;
    }

    @Path(WCDMA_HFA_EVENT_SUMMARY)
    public WCDMAHandoverFailureEventSummaryResource getWcdmaHandoverFailureEventSummaryResource() {
        return this.wcdmaHandoverFailureEventSummaryResource;
    }

    @Path(WCDMA_HFA_NETWORK_EVENT_ANALYSIS)
    public WCDMAHandoverFailureNetworkEventAnalysisResource getWcdmaHandoverFailureNetworkEventAnalysisResource() {
        return this.wcdmaHandoverFailureNetworkEventAnalysisResource;
    }

    @Path(WCDMA_HFA_DETAILED_EVENT_ANALYSIS)
    public WCDMAHandoverFailureDetailedEventAnalysisResource getWcdmaHandoverFailureDetailedEventAnalysisResource() {
        return this.wcdmaHandoverFailureDetailedEventAnalysisResource;
    }

    @Path(GSM)
    public GSMResource getGSMResource() {
        return this.gsmResource;
    }

    @Path(LTE_CALL_FAILURE_EVENT_SUMMARY)
    public LTECFAEventSummaryResource getLteCallFailureEventSummaryResource() {
        return lteCallFailureEventSummaryResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_EVENT_SUMMARY_ANALYSIS)
    public LTEHandoverFailureEventSummaryResource getLteHandoverFailureEventSummaryResource() {
        return lteHandoverFailureEventSummaryResource;
    }

    @Path(KPI_NOTIFICATION)
    public KPINotificationResource getKpiNotificationResource() {
        return kpiNotificationResource;
    }

    @Path(LTE_CALL_FAILURE_DETAILED_EVENT_ANALYSIS)
    public LTECFADetailedEventAnalysisResource getLteCallFailureDetailedEventAnalysisResource() {
        return lteCallFailureDetailedEventAnalysisResource;
    }

    @Path(LTE_HANDOVER_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS)
    public LTEHandoverFailureDetailedEventAnalysisResource getLteHandoverFailureDetailedEventAnalysisResource() {
        return lteHandoverFailureDetailedEventAnalysisResource;
    }

    @Path(WCDMA_CFA_NETWORK_EVENT_ANALYSIS)
    public CallFailureNetworkEventAnalysisResource getCallFailureNetworkEventAnalysisResource() {
        return callFailureNetworkEventAnalysisResource;
    }

    @Path(WCDMA_CALL_FAILURE_ROAMING_ANALYSIS)
    public WCDMACallFailureRoamingAnalysisResource getWcdmaCallFailureRoamingAnalysisResource() {
        return wcdmaCallFailureRoamingAnalysisResource;
    }

    @Path(LTE_CFA_CAUSE_CODE_ANALYSIS)
    public LTECFACauseCodeAnalysisResource getLTECallFailureCauseCodeAnalysisResource() {
        return lteCallFailureCauseCodeAnalysisResource;
    }

    @Path(LTE_CFA_QOS_SUMMARY_ANALYSIS)
    public LTECFAQosSummaryAnalysisResource getLTECallFailureQOSAnalysisResource() {
        return lteCallFailureQosSummaryAnalysisResource;
    }

    @Path(LTE_CFA_QOS_DETAILED_EVENT_ANALYSIS)
    public LTECFAQoSDetailedEventAnalysisResource getLTECallFailureQOSDetailedEventAnalysisResource() {
        return lteCallFailureQoSDetailedEventAnalysisResource;
    }

    @Path(LTE_CFA_EVENT_VOLUME)
    public LTECFAEventVolumeAnalysisResource getLTECFAEventVolumeResource() {
        return lteCFAEventVolumeAnalysisResource;
    }

    @Path(WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS)
    public WCDMACallFailureDetailedEventAnalysisResource getWcdmaCallFailureCauseCodeAnalysisResource() {
        return wcdmaCallFailureDEAResource;
    }

    @Path(LTE_HFA_RANKING)
    public LTEHandoverFailureRankingResource getLteHandoverFailureCellRankingResource() {
        return this.lteHandoverFailureCellRankingResource;
    }

    @Path(LTE_HFA_CAUSE_CODE_ANALYSIS)
    public LTEHandoverFailureCauseCodeAnalysisResource getLTEHandoverFailureCauseCodeAnalysisResource() {
        return lteHandoverFailureCauseCodeAnalysisResource;
    }

    @Path(LTE_HFA_EVENT_VOLUME)
    public LTEHFAEventVolumeResource getLTEHandoverFailureEventVolumeResource() {
        return lteHandoverFailureEventVolumeResource;
    }

    @Path(LTE_HFA_QOS_SUMMARY_ANALYSIS)
    public LTEHFAQosSummaryAnalysisResource getLTEHFAQOSAnalysisResource() {
        return lteHFAQosSummaryAnalysisResource;
    }

    @Path(LTE_HFA_QOS_DETAILED_EVENT_ANALYSIS)
    public LTEHFAQoSDetailedEventAnalysisResource getLTEHFAQOSDetailedEventAnalysisResource() {
        return lteHFAQoSDetailedEventAnalysisResource;
    }

    @Path(WCDMA_CAUSE_CODE_ANALYSIS)
    public WCDMACallFailureCauseCodeAnalysisResource getWcdmaCauseCodeAnalysisResource() {
        return wcdmaCauseCodeAnalysisResource;
    }

    @Path(WCDMA_HFA_CAUSE_CODE_ANALYSIS)
    public WCDMAHandoverFailureCauseCodeAnalysisResource getWcdmaHfaCauseCodeAnalysisResource() {
        return wcdmaHfaCauseCodeAnalysisResource;
    }

    @Path(WCDMA_NETWORK_CALL_FAILURE_FILTER_ANALYSIS)
    public CallFailureNetworkFilterAnalysisResource getCallFailureNetworkFilterAnalysisResource() {
        return callFailureNetworkFilterAnalysisResource;
    }

    @Path(WCDMA_NETWORK_CALL_FAILURE_ANALYSIS)
    public CallFailureNetworkChartAnalysisResource getCallFailureNetworkChartAnalysisResource() {
        return callFailureNetworkChartAnalysisResource;
    }

}
