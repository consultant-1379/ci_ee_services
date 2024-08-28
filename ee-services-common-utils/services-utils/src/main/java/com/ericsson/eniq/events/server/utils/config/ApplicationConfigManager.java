package com.ericsson.eniq.events.server.utils.config;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ApplicationConfigManager {

    int NO_LIMIT = -1;

    /** JNDI name for application properties object */
    String ENIQ_EVENTS_JNDI_NAME = "Eniq_Event_Properties";

    String ENIQ_EVENTS_TIME_DELAY_TO_BE_USED = "ENIQ_EVENTS_TIME_DELAY_TO_BE_USED";

    /** 
     * Services JNDI property keys  
     */
    String ENIQ_EVENTS_MAX_JSON_RESULT_SIZE = "ENIQ_EVENTS_MAX_JSON_RESULT_SIZE";

    String ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_LTE_CFA_IMSI_CALL_SETUP_RANKING_COUNT = "ENIQ_EVENTS_LTE_CFA_IMSI_CALL_SETUP_RANKING_COUNT";

    String ENIQ_EVENTS_LTE_CFA_IMSI_CALL_DROP_RANKING_COUNT = "ENIQ_EVENTS_LTE_CFA_IMSI_CALL_DROP_RANKING_COUNT";

    String ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_UNANSWERED_CALLS_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_UNANSWERED_CALLS_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_DURATION_CALLS_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_DURATION_CALLS_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_MSC_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_MSC_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_ENODEB_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_ENODEB_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_TERMINAL_GROUP_ANALYSIS_COUNT = "ENIQ_EVENTS_TERMINAL_GROUP_ANALYSIS_COUNT";

    String ENIQ_EVENTS_TERMINAL_ANALYSIS_COUNT = "ENIQ_EVENTS_TERMINAL_ANALYSIS_COUNT";

    String ENIQ_EVENTS_RNC_EVENT_FAILURE_RANKING_COUNT = "ENIQ_EVENTS_RNC_EVENT_FAILURE_RANKING_COUNT";

    String ENIQ_EVENTS_SUBBI_COUNT = "ENIQ_EVENTS_SUBBI_COUNT";

    String ENIQ_EVENTS_PDP_STATISTICS_COUNT = "ENIQ_EVENTS_PDP_STATISTICS_COUNT";

    String ENIQ_EVENTS_LIVE_LOAD_COUNT = "ENIQ_EVENTS_LIVE_LOAD_COUNT";

    String ENIQ_EVENTS_ONE_MINUTE_AGGREGATION = "ENIQ_EVENTS_ONE_MINUTE_AGGREGATION";

    String ENIQ_EVENTS_KPI_ONE_MINUTE_AGGREGATION = "ENIQ_EVENTS_TIME_SERIES_CHART_ONE_MINUTE_AGGREGATION";

    // The following parameters offset the time range of user queries to take account
    // of the time it takes for data to be available in the data base once collected from 
    // the SGSN nodes. The parameter value is in seconds.
    String ENIQ_EVENTS_TIME_DELAY_1MIN_DATA = "ENIQ_EVENTS_TIME_DELAY_1MIN_DATA";

    String ENIQ_EVENTS_TIME_DELAY_15MIN_DATA = "ENIQ_EVENTS_TIME_DELAY_15MIN_DATA";

    String ENIQ_EVENTS_TIME_DELAY_DAY_DATA = "ENIQ_EVENTS_TIME_DELAY_DAY_DATA";

    //For MSS offsets time in the aggregation tables
    //*Start
    String ENIQ_EVENTS_MSS_TIME_DELAY_1MIN_DATA = "ENIQ_EVENTS_MSS_TIME_DELAY_1MIN_DATA";

    String ENIQ_EVENTS_MSS_TIME_DELAY_15MIN_DATA = "ENIQ_EVENTS_MSS_TIME_DELAY_15MIN_DATA";

    String ENIQ_EVENTS_MSS_TIME_DELAY_DAY_DATA = "ENIQ_EVENTS_MSS_TIME_DELAY_DAY_DATA";

    //*End

    String ENIQ_EVENTS_APN_RETENTION_IN_DAYS = "ENIQ_EVENTS_APN_RETENTION_IN_DAYS";

    String ENIQ_EVENTS_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN = "ENIQ_EVENTS_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN";

    // Latencies for 2/3G DataVolume Throughput
    String ENIQ_EVENTS_DT_TIME_DELAY_1MIN_DATA = "ENIQ_EVENTS_DT_TIME_DELAY_1MIN_DATA";

    String ENIQ_EVENTS_DT_TIME_DELAY_15MIN_DATA = "ENIQ_EVENTS_DT_TIME_DELAY_15MIN_DATA";

    String ENIQ_EVENTS_DT_TIME_DELAY_DAY_DATA = "ENIQ_EVENTS_DT_TIME_DELAY_DAY_DATA";

    String ENIQ_EVENTS_DT_USE_TIME_DELAY = "ENIQ_EVENTS_DT_USE_TIME_DELAY";

    // Business Intelligent Serivce Related Param name 

    String BIS_SOAP_SERVICE_URL = "BIS_SOAP_SERVICE_URL";

    String BIS_SOAP_SERVICE_USERNAME = "BIS_SOAP_SERVICE_USERNAME";

    String BIS_SOAP_SERVICE_PASSWORD = "BIS_SOAP_SERVICE_PASSWORD";

    String BIS_OPENDOC_URL_FORMATTER = "BIS_OPENDOC_URL_FORMATTER";

    String BIS_REPORTS_ROOT_OBJECT_NAME = "BIS_REPORTS_ROOT_OBJECT_NAME";

    String BIS_SB_REPORTS_ROOT_OBJECT_NAME = "BIS_SB_REPORTS_ROOT_OBJECT_NAME";

    //    String BIS_REPORTS_USERNAME = "BIS_REPORTS_USERNAME";
    //
    //    String BIS_REPORTS_PASSWORD = "BIS_REPORTS_PASSWORD";

    String ENIQ_EVENTS_DATA_TIERING = "ENIQ_EVENTS_DATA_TIERING";

    String ENIQ_EVENTS_DATA_TIERING_DELAY = "ENIQ_EVENTS_DATA_TIERING_DELAY";

    /**
     * Property value defaults
     */

    //default value of Business Intelligent Serivce 

    String BIS_SOAP_SERVICE_URL_DEFAULT_VALUE = "http://bisserver:8080/";

    String BIS_SOAP_SERVICE_USERNAME_DEFAULT_VALUE = "Administrator";

    String BIS_SOAP_SERVICE_PASSWORD_DEFAULT_VALUE = "";

    String BIS_OPENDOC_URL_FORMATTER_DEFAULT_VALUE = "OpenDocument/opendoc/openDocument.jsp?iDocID=%s&sIDType=InfoObjectID&token=%s&sRefresh=Y";

    String BIS_REPORTS_ROOT_OBJECT_NAME_DEFAULT_VALUE = "ENIQ Business Intelligence Reports";

    String BIS_SESSION_BROWSER_REPORTS_ROOT_OBJECT_NAME_DEFAULT_VALUE = "3G Session Activity";

    //    String BIS_REPORTS_USERNAME_DEFAULT_VALUE = "administrator";
    //
    //    String BIS_REPORTS_PASSWORD_DEFAULT_VALUE = "";

    //Session Browser KPI Drill max value
    String ENIQ_EVENTS_SESSION_BROWSER_KPI_DRILL_MAX_COUNT = "ENIQ_EVENTS_SESSION_BROWSER_KPI_DRILL_MAX_COUNT";

    int ENIQ_EVENTS_SESSION_BROWSER_KPI_DRILL_MAX_COUNT_DEFAULT = 20;

    //ENIQ_EVENTS_TIME_DELAY default value

    int ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES = 3;

    int ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES = 10;

    int ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES = 180;

    //MSS defaults for aggregations
    int ENIQ_EVENTS_MSS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES = 0;

    int ENIQ_EVENTS_MSS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES = 10;

    int ENIQ_EVENTS_MSS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES = 180;

    int DEFAULT_EVENT_FAILURE_RANKING_COUNT = 50;

    int MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE = 5000;

    int DEFAULT_MAXIMUM_JSON_RESULT_SIZE = 500;

    int DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_UNANSWERED_CALLS_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_DURATION_CALLS_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_MSC_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_ENODEB_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_TERMINAL_COUNT = 30;

    int DEFAULT_TERMINAL_GROUP_ANALYSIS_COUNT = 30;

    int DEFAULT_TERMINAL_ANALYSIS_COUNT = 30;

    int DEFAULT_ENIQ_EVENTS_LIVE_LOAD_COUNT = 100;

    int MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_LIVE_LOAD_COUNT = 1000;

    //3G support
    int DEFAULT_RNC_EVENT_FAILURE_RANKING_COUNT = 50;

    int DEFAULT_SUBBI_COUNT = 30;

    int DEFAULT_PDP_STATISTICS_COUNT = 50;

    //LTE CFA Support

    int DEFAULT_LTE_CFA_IMSI_CALL_SETUP_RANKING_COUNT = 50;

    int DEFAULT_LTE_CFA_IMSI_CALL_DROP_RANKING_COUNT = 50;

    //HM82411
    int MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS = 100;

    //one minute aggregation support
    boolean DEFAULT_ONE_MINUTE_AGGREGATION = false;

    // To remove APN greater than 30 days by default

    int MAXIMUM_POSSIBLE_APN_RETENTION_IN_DAYS = 100;

    int DEFAULT_APN_RETENTION_IN_DAYS = 30;

    // Default time out 2 hrs
    int DEFAULT_ENIQ_EVENTS_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN = 60;

    int MAXIMUM_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN = 100;

    // 2/3G DT latency defaults

    int ENIQ_EVENTS_DT_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES = 30;

    int ENIQ_EVENTS_DT_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES = 30;

    int ENIQ_EVENTS_DT_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES = 240;

    int ENIQ_EVENTS_DATA_TIERING_DELAY_DEFAULTS_MINUTES = 6;

    boolean DEFAULT_ENIQ_EVENTS_DT_USE_TIME_DELAY = true;

    boolean DEFAULT_DATA_TIERING_ENABLED = true;

    String getBISServiceURL();

    String getBISServiceOpenDocCompleteURLWithFormatter();

    String getBISReportsRootObjectName();

    int getMaxJsonResultSize();

    int getImsiEventFailureRankingCount();

    int getTerminalEventFailureRankingCount();

    int getCellEventFailureRankingCount();

    int getMscEventFailureRankingCount();

    int getBscEventFailureRankingCount();

    int getCauseCodeEventFailureRankingCount();

    int getApnEventFailureRankingCount();

    int getEnodeBEventFailureRankingCount();

    int getTerminalGroupAnalysisCount();

    int getUnansweredCallsEventFailureRankingCount();

    int getDurationCallsEventFailureRankingCount();

    int getTerminalAnalysisCount();

    int getLiveLoadCount();

    //LTE CFA Support

    int getLTECallFailureSubscriberCallSetupRankingCount();

    int getLTECallFailureSubscriberCallDropRankingCount();

    //3G support
    int getRncEventFailureRankingCount();

    int getSubBICount();

    int getPDPStatisticsCount();

    //one minute aggregation support
    boolean getOneMinuteAggregation();

    /**
     * Delay applied to all query time ranges when
     * the query range targets 1 Minute aggregated
     * data (also known as the least offset, see http://atrclin2.athtem.eei.ericsson.se/wiki/index.php/ENIQ_Events_Services_-_Latency_Settings#Proposal 
     * for more information)
     * Depending on the user's configuration, the delay returned may be dependent on the tech packs or technologies
     * used in the query
     * 
     * @param techPacks         list of tech packs that apply to this query         
     * @return delay in minutes
     */

    int getTimeDelayOneMinuteData(List<String> techPacks);

    /**
     * Delay applied to all query time ranges when
     * the query range targets 15 Minute aggregated
     * data (also known as the medium offset, see http://atrclin2.athtem.eei.ericsson.se/wiki/index.php/ENIQ_Events_Services_-_Latency_Settings#Proposal 
     * for more information)
     * Depending on the user's configuration, the delay returned may be dependent on the tech packs or technologies
     * used in the query
     * 
     * @param techPacks         list of tech packs that apply to this query
     * @return delay in minutes
     */
    int getTimeDelayFifteenMinuteData(List<String> techPacks);

    /**
     * Delay applied to all query time ranges when
     * the query range targets day aggregated
     * data (also known as the large offset, see http://atrclin2.athtem.eei.ericsson.se/wiki/index.php/ENIQ_Events_Services_-_Latency_Settings#Proposal 
     * for more information)
     * Depending on the user's configuration, the delay returned may be dependent on the tech packs or technologies
     * used in the query
     * 
     * @param techPacks         list of tech packs that apply to this query
     * @return delay in minutes
     */
    int getTimeDelayDayData(List<String> techPacks);

    /**
     * Default APN retention to be used while fetching APN's for Liveload
     * 
     * @ return default APN retention in minutes
     */

    int getAPNRetention();

    /**
     * Timeout used to clear the failed cancel request id
     * 
     * @ return timeout in millis
     */

    int getCancelRequestTimeOut();

    /**
     * Delay applied to all query time ranges when
     * the query range targets 1 Minute aggregated
     * data.
     * 
     * @return delay in minutes
     */
    int getMSSTimeDelayOneMinuteData();

    /**
     * Delay applied to all query time ranges when
     * the query range targets MSS 15 Minute aggregated
     * data.
     * 
     * @return delay in minutes
     */
    int getMSSTimeDelayFifteenMinuteData();

    /**
     * Delay applied to all query time ranges when
     * the query range targets MSS day aggregated
     * data.
     * 
     * @return delay in minutes
     */
    int getMSSTimeDelayDayData();

    /**
     * Returns the max count value for the session browser KPI drills (TAC/MAKE/MODEL)
     * @return
     */
    int getSessionBrowserKpiDrillMaxCount();

    /**
     * Returns the root folder for the session browser reports
     * @return
     */
    String getBISSessionBrowserReportsRootObjectName();

    /**
     * Returns the username to use to login to the BIS
     * @return
     */
    String getBISUsername();

    /**
     * Returns the password to use to login to the BIS
     * @return
     */
    String getBISPassword();

    /**
     * Is DataTiering enabled.
     * @return true/false indicating if DataTiering is enabled. If there is no DataTiering flag set, defaults to false
     */
    boolean isDataTieringEnabled();

    /**
     * Returns 
     * @return
     */
    int getDataTieringDelay();

}