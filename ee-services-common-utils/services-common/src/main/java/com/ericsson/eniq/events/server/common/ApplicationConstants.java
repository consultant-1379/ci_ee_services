/**
 * -----------------------------------------------------------------------
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Constant data for service path parameters.
 *
 * @author eavidat
 * @author ehaoswa
 * @author estepdu
 * @since Mar 2010
 */
public final class ApplicationConstants {

    public static final String HSDSCH = "HSDSCH";

    public static final String SOHO = "SOHO";

    public static final String IFHO = "IFHO";

    public static final String HHO = "HHO";

    public static final String RAN_HFA_IRAT = "RAN/HFA/IRAT";

    public static final String IRAT = "IRAT";

    public static final String SUC_HSDSCH_CELL_CHANGE = "SUC_HSDSCH_CELL_CHANGE";

    public static final String THRESHOLD = "THRESHOLD";

    public static final String INTER_OUT_HHO = "INTER_OUT_HHO";

    public static final String RAN_SESSION = "RAN_SESSION";

    public static final String NETWORK = "NETWORK";

    public static final String DECLARED_ROLE = "secure";

    public static final String SUB_CAUSE_CODE_PATH = "SUB_CAUSE_CODE";

    public static final String CAUSE_CODE_PATH = "CAUSE_CODE";

    public static final String WCDMA_CFA_ACCESS_AREA_CAUSE_CODE = "WCDMA_CFA_ACCESS_AREA_CAUSE_CODE";

    public static final String NEW_LINE = "\n";

    public static final String MCC_MNC_WITH_NAMES = "MCC_MNC_WITH_NAMES";

    public static final String MCC_MNC = "MCC_MNC";

    public static final String NULL = "null";

    public static final char COLON_CHARACTER = ':';

    public static final String SINGLE_QUOTE = "'";

    public static final char WHITE_SPACE = ' ';

    public static final String WCDMA_CALL_FAILURE_COUNTRY_ROAMING_ANALYSIS = "WCDMA_CALL_FAILURE_COUNTRY_ROAMING_ANALYSIS";

    public static final String WCDMA_CALL_FAILURE_COUNTRY_DRILL_ROAMING_ANALYSIS = "WCDMA_CALL_FAILURE_COUNTRY_DRILL_ROAMING_ANALYSIS";

    public static final String WCDMA_CALL_FAILURE_COUNTRY_DRILL_DETAIL_ROAMING_ANALYSIS = "WCDMA_CALL_FAILURE_COUNTRY_DRILL_DETAIL_ROAMING_ANALYSIS";

    public static final String ACCESS_AREA_GROUP_BREAKDOWN = "ACCESS_AREA_GROUP/BREAKDOWN";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_GROUP_SUMMARY_BREAKDOWN = "GSM_CALL_FAILURE_ACCESS_AREA_GROUP_SUMMARY_BREAKDOWN";

    /**
     * the upper limit for the number of roaming analysis queries -  this is
     * approx the number of operators currently allocated MCC/MNC codes (including dormant or pre allocated codes)
     */
    public static final int MAXIMUM_NUMBER_OF_OPERATORS = 2500;

    public static final String OPERATOR = "OPERATOR";

    public static final String COUNTRY = "COUNTRY";

    public static final String COUNTRY_DRILL_DETAIL_FOR_CALL_SETUP = "COUNTRY/DRILL/DETAIL/CALL_SETUP";

    public static final String COUNTRY_DRILL_DETAIL_FOR_CALL_DROP = "COUNTRY/DRILL/DETAIL/CALL_DROP";

    public static final String OPERATOR_DRILL_DETAIL_FOR_CALL_SETUP = "OPERATOR/DRILL/DETAIL/CALL_SETUP";

    public static final String OPERATOR_DRILL_DETAIL_FOR_CALL_DROP = "OPERATOR/DRILL/DETAIL/CALL_DROP";

    public static final String OPERATOR_NAME_PARAM = "operatorName";

    public static final String COUNTRY_NAME_PARAM = "countryName";

    public static final String COUNTRY_OPERATOR_NAME_PARAM = "countryOperatorName";

    public static final String WCDMA_CALL_FAILURE_ROAMING_ANALYSIS = "WCDMA/CALL_FAILURE_ROAMING_ANALYSIS";

    public static final String ENIQ_EVENTS = "ENIQ_EVENTS";

    public static final String COMMENT_PREFIX = "#";

    /**
     * Path parameters
     */
    public static final String METADATA_SERVICE = "METADATA";

    public static final String UI_PARAM = "UI";

    public static final String UI_MSS_PARAM = "UI_MSS";

    public static final String METADATA_FILE = "UIMetaData.json";

    public static final String CAUSE_CODE_ANALYSIS = "CAUSE_CODE_ANALYSIS";

    public static final String WCDMA_HFA_CAUSE_CODE_ANALYSIS = "WCDMA_HFA_CAUSE_CODE_ANALYSIS";

    public static final String PIE_CHART_CAUSE_CODE_ANALYSIS = "PIE_CHART_CAUSE_CODE_ANALYSIS";

    public static final String PIE_CHART_CAUSE_CODE_ANALYSIS_DRILLDOWN = "PIE_CHART_CAUSE_CODE_ANALYSIS_DRILLDOWN";

    public static final String PIE_CHART_CC_LIST = "PIE_CHART_CC_LIST";

    public static final String CAUSE_CODE_TABLE_CC = "TABLE_CC";

    public static final String CAUSE_CODE_TABLE_CC_WCDMA = "CAUSE_CODE_TABLE_CC_WCDMA";

    public static final String CAUSE_CODE_TABLE_CC_GSM = "CAUSE_CODE_TABLE_CC_GSM";

    public static final String CAUSE_CODE_TABLE_SCC = "TABLE_SCC";

    public static final String LTE_CALL_FAILURE_CAUSE_CODE_TABLE_CC = "LTE_CALL_FAILURE_TABLE_CC";

    public static final String LTE_HANDOVER_FAILURE_CAUSE_CODE_TABLE_CC = "LTE_HANDOVER_FAILURE_TABLE_CC";

    public static final String INTERNAL_CAUSE_CODE_TABLE_ICC = "TABLE_ICC";

    public static final String INTERNAL_CAUSE_CODE_TABLE_FC = "TABLE_FC";

    public static final String DATAVOL_ROAMING_ANALYSIS = "DATAVOL_ROAMING_ANALYSIS";

    public static final String DATAVOLUME_ANALYSIS = "DATAVOLUME_ANALYSIS";

    public static final String EVENT_ANALYSIS = "EVENT_ANALYSIS";

    public static final String ROAMING_ANALYSIS = "ROAMING_ANALYSIS";

    public static final String ROAMING_ANALYSIS_DRILL_COUNTRY = "ROAMING_ANALYSIS_DRILL_COUNTRY";

    public static final String ROAMING_ANALYSIS_DRILL_OPERATOR = "ROAMING_ANALYSIS_DRILL_OPERATOR";

    public static final String ROAMING_ANALYSIS_DRILL_DETAIL_COUNTRY = "ROAMING_ANALYSIS_DRILL_DETAIL_COUNTRY";

    public static final String ROAMING_ANALYSIS_DRILL_DETAIL_OPERATOR = "ROAMING_ANALYSIS_DRILL_DETAIL_OPERATOR";

    public static final String MSS_ROAMING_ANALYSIS = "MSS_ROAMING_ANALYSIS";

    public static final String EVENT_VOLUME = "EVENT_VOLUME";

    public static final String NETWORK_EVENT_VOLUME = "NETWORK_EVENT_VOLUME";

    public static final String GROUP = "GROUP";

    public static final String URI_PATH_GROUP = "GROUP";

    public static final String SUBSCRIBER_SERVICES = "SUBSCRIBER";

    public static final String NETWORK_SERVICES = "NETWORK";    

    public static final String TERMINAL_SERVICES = "TERMINAL";

    public static final String HOMER_ROAMER_SERVICES = "HOMER_ROAMER";

    public static final String REPORTS_SERVICES = "REPORTS";

    public static final String NETWORK_KPI_SERVICES = "NETWORK_KPI";

    public static final String DRILL_DOWN_PATH = "/RAN/DRILL_DOWN";

    public static final String CORE_PATH = "CORE";

    public static final String RAN_CELL_PATH = "/RAN/CELL";

    public static final String RAN_CELL_GROUP_PATH = "/RAN/CELL_GROUP";

    public static final String RAN_BSC_GROUP_PATH = "/RAN/BSC_GROUP";

    public static final String RAN_BSC_PATH = "/RAN/BSC";

    public static final String RAN_SUMMARY_WCDMA = "/RAN/SUMMARY_WCDMA";

    public static final String RAN_SUMMARY_LTE = "/RAN/SUMMARY_LTE";

    public static final String CORE_SUMMARY_LTE = "/CORE/SUMMARY_LTE";

    public static final String SUMMARY_WCDMA = "SUMMARY_WCDMA";

    public static final String RANKING_SERVICES = "RANKING";

    public static final String RANKING_ANALYSIS = "RANKING_ANALYSIS";

    public static final String RANKING_ANALYSIS_IMSI="RANKING_ANALYSIS_IMSI";

    public static final String DATAVOL_RANKING_ANALYSIS = "DATAVOL_RANKING_ANALYSIS";

    public static final String DATAVOL_GROUP_RANKING_ANALYSIS = "DATAVOL_GROUP_RANKING_ANALYSIS";

    public static final String NETWORK_DATAVOL_ANALYSIS = "NETWORK_DATAVOL_ANALYSIS";

    public static final String DASHBOARD_SERVICES = "DASHBOARD";

    public static final String TOPOLOGY = "TOPOLOGY";

    public static final String THREE_G = "3G";

    public static final String CONNECTED_CELLS = "CONNECTED_CELLS";

    public static final String UNPARENTED_CELLS = "UNPARENTED_CELLS";

    public static final String LIVELOAD = "LIVELOAD";

    public static final String IMSI_MSISDN_MAPPING = "IMSI_MSISDN_MAPPING";

    public static final String CANCEL = "CANCEL";

    public static final String CONFIGURATION = "CONFIGURATION";

    public static final String LIVELOAD_APN = "APN";

    public static final String LIVELOAD_SGSN = "SGSN";

    public static final String LIVELOAD_MSC = "MSC";

    public static final String LIVELOAD_TRAC = "TRAC";

    public static final String LIVELOAD_COUNTRY = "COUNTRY";

    public static final String LIVELOAD_OPERATOR = "OPERATOR";

    public static final String LIVELOAD_HANDSET_MAKES = "HANDSET_MAKES";

    public static final String LIVELOAD_SBR_CELL = "SBR_CELL";

    public static final String LIVELOAD_KPI_RNC = "KPI_RNC";

    public static final String KPI = "KPI";

    public static final String LIST_GROUPS = "LIST_GROUPS";

    public static final String ROAMING_COUNTRY = "COUNTRY";

    public static final String ROAMING_COUNTRY_DRILL = "COUNTRY/DRILL";

    public static final String ROAMING_COUNTRY_DRILL_DETAIL = "COUNTRY/DRILL/DETAIL";

    public static final String ROAMING_OPERATOR_DRILL_DETAIL = "OPERATOR/DRILL/DETAIL";

    public static final String ROAMING_OPERATOR = "OPERATOR";

    public static final String ROAMING_OPERATOR_DRILL = "OPERATOR/DRILL";

    public static final String TERMINAL_ANALYSIS = "TERMINAL_ANALYSIS";

    public static final String TERMINAL_GROUP_ANALYSIS = "GROUP_ANALYSIS";

    public static final String MOST_SWAPPED_FROM = "MOST_SWAPPED_FROM";

    public static final String MOST_POPULAR = "MOST_POPULAR";

    public static final String MOST_POPULAR_EVENT_SUMMARY = "MOST_POPULAR_EVENT_SUMMARY";

    public static final String MOST_ATTACHED_FAILURES = "MOST_ATTACHED_FAILURES";

    public static final String MOST_PDP_SESSION_SETUP_FAILURES = "MOST_PDP_SESSION_SETUP_FAILURES";

    public static final String MOST_MOBILITY_ISSUES = "MOST_MOBILITY_ISSUES";

    public static final String MOST_SWAPPED_TO = "MOST_SWAPPED_TO";

    public static final String HIGHEST_DATAVOL = "HIGHEST_DATAVOL";

    public static final String TERMINAL_TYPE_DETAILS = "TERMINAL_TYPE_DETAILS";

    public static final String INTERNAL_CAUSE_CODE_PARAM = "internalCauseCode";

    public static final String FAULT_CODE_PARAM = "faultCode";

    public static final String MSS_IMSI_PARAM = "MSS_IMSI";

    public static final String ICC_SQL_NAME = "INTERNAL_CAUSE_CODE";

    public static final String FAULT_CODE_SQL_NAME = "FAULT_CODE";

    public static final String KPI_RATIO = "KPI_RATIO";

    public static final String SUBBI = "SUBBI";

    public static final String SUBBI_APN = "APN";

    public static final String SUBBI_CELL = "CELL";

    public static final String SUBBI_BUSY_DAY = "BUSY_DAY";

    public static final String SUBBI_BUSY_HOUR = "BUSY_HOUR";

    public static final String SUBBI_FAILURE = "FAILURE";

    public static final String SUBBI_FAILURE_DRILLDOWN = "SUBBI_FAILURE_DRILLDOWN";

    public static final String SUBBI_TERMINAL = "TERMINAL";

    public static final String SUBBI_TAU = "TAU";

    public static final String SUBBI_SUBSCRIBER_DETAILS = "SUBSCRIBER_DETAILS";

    public static final String SUBBI_HANDOVER = "HANDOVER";

    public static final String EVENT_RECURRING = "RECUR_ERR";

    public static final String QOS_STATISTICS = "QOS_STATISTICS";

    public static final String MSS = "MSS";

    public static final String VOICE = "VOICE";

    public static final String LOC_SERVICE = "LOC_SERVICE";

    public static final String SMS = "SMS";

    public static final String DROPPED_CALLS = "DROPPED_CALLS";

    public static final String BLOCKED_CALLS = "BLOCKED_CALLS";

    public static final String TOTAL_CALLS = "TOTAL_CALLS";

    public static final String LONG_DURATION_CALLS = "LONG_DURATION_CALLS";

    public static final String SHORT_DURATION_CALLS = "SHORT_DURATION_CALLS";

    public static final String MS_TERMINATING_UNANSWERED = "MS_TERMINATING_UNANSWERED";

    public static final String MS_ORIGINATING_UNANSWERED = "MS_ORIGINATING_UNANSWERED";

    public static final String EVNTSRC = "EVNTSRC";

    public static final String USER_SERVICES = "USER";

    public static final String USER_SETTINGS = "SETTINGS";

    public static final String WCDMA_HFA_RANKING = "WCDMA_HANDOVER_FAILURE_RANKING_ANALYSIS";
    
    public static final String WCDMA_UERTT = "WCDMA_UERTT";
    
    public static final String UERTT = "UERTT";

    public static final String WCDMA_HFA_EVENT_SUMMARY = "WCDMA_HANDOVER_FAILURE_EVENT_SUMMARY";

    public static final String WCDMA_HFA_NETWORK_EVENT_ANALYSIS = "WCDMA_HANDOVER_FAILURE_NETWORK_EVENT_ANALYSIS";

    public static final String WCDMA_HFA_DETAILED_EVENT_ANALYSIS = "WCDMA_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String WCDMA_HFA_TERMINAL_ANALYSIS = "WCDMA_HANDOVER_FAILURE_TERMINAL_ANALYSIS";

    public static final String WCDMA_HFA_TERMINAL_GROUP_ANALYSIS = "WCDMA_HANDOVER_FAILURE_TERMINAL_GROUP_ANALYSIS";

    public static final String SUBSCRIBER_FOR_SOHO = "SUBSCRIBER/SOHO";

    public static final String SUBSCRIBER_FOR_IFHO = "SUBSCRIBER/IFHO";

    public static final String SUBSCRIBER_FOR_IRAT = "SUBSCRIBER/IRAT";

    public static final String SUBSCRIBER_FOR_HSDSCH = "SUBSCRIBER/HSDSCH";

    public static final String MOST_SOHO_FAILURES = "MOST_SOHO_FAILURES";

    public static final String MOST_IRAT_FAILURES = "MOST_IRAT_FAILURES";

    public static final String MOST_HSDSCH_FAILURES = "MOST_HSDSCH_FAILURES";

    public static final String MOST_IFHO_FAILURES = "MOST_IFHO_FAILURES";

    public static final String WCDMA_HFA_SOHO_CATEGORY_ID = "0";

    public static final String WCDMA_HFA_HSDSCH_CATEGORY_ID = "1";

    public static final String WCDMA_HFA_IFHO_CATEGORY_ID = "2";

    public static final String WCDMA_HFA_IRAT_CATEGORY_ID = "3";

    public static final String GSM = "GSM";

    public static final String MSC_GROUP_PATH = "MSC_GROUP";

    public static final String SGSN_GROUP_PATH = "SGSN_GROUP";

    public static final String CELL_GROUP_PATH = "CELL_GROUP";

    public static final String BSC_GROUP_PATH = "BSC_GROUP";

    public static final String APN_GROUP_PATH = "APN_GROUP";

    public static final String KPI_NOTIFICATION = "KPI_NOTIFICATION";

    public static final String NOTIFICATION_DATA = "NOTIFICATION_DATA";

    public static final String NOTIFICATION_COUNT = "NOTIFICATION_COUNT";

    public static final String SONVIS_SERVICE = "SONVIS";

    /**
     * Query parameters
     */
    public static final String GROUP_PARAM = "group";

    public static final String TIME_QUERY_PARAM = "time";

    public static final String DATE_FROM_QUERY_PARAM = "dateFrom";

    public static final String DATE_TO_QUERY_PARAM = "dateTo";

    public static final String TIME_FROM_QUERY_PARAM = "timeFrom";

    public static final String TIME_TO_QUERY_PARAM = "timeTo";

    public static final String DATA_TIME_FROM_QUERY_PARAM = "dataTimeFrom";

    public static final String DATA_TIME_TO_QUERY_PARAM = "dataTimeTo";

    public static final String DISPLAY_PARAM = "display";

    public static final String HANDSET_PARAM = "handset";

    public static final String IMSI_PARAM = "imsi";

    public static final String MCC_PARAM = "MCC";

    public static final String MNC_PARAM = "MNC";

    public static final String IMSI_MCC_PARAM = "IMSI_MCC";

    public static final String IMSI_MNC_PARAM = "IMSI_MNC";

    public static final String MSISDN_PARAM = "msisdn";

    public static final String IMSI_PARAM_UPPER_CASE = IMSI_PARAM.toUpperCase();

    public static final String MSISDN_PARAM_UPPER_CASE = MSISDN_PARAM.toUpperCase();

    public static final String PTMSI_PARAM = "ptmsi";

    public static final String GGSN_NAME_PARAM = "GGSN_NAME";

    public static final String DTPUT_SGSN_NAME_PARAM = "SGSN_NAME";

    public static final String QOS_RELIABILITY_PARAM = "QOS_RELIABILITY";

    public static final String QOS_MEANTPUT_PARAM = "QOS_MEANTPUT";

    public static final String QOS_PEAKTPUT_PARAM = "QOS_PEAKTPUT";

    public static final String QOS_DELAY_PARAM = "QOS_DELAY";

    public static final String QOS_PRECEDENCE_PARAM = "QOS_PRECEDENCE";

    public static final String DATAVOL_UL_PARAM = "DATAVOL_UL";

    public static final String DATAVOL_DL_PARAM = "DATAVOL_DL";

    public static final String PTMSI_PARAM_UPPER_CASE = PTMSI_PARAM.toUpperCase();

    public static final String TYPE_PARAM = "type";

    public static final String DRILLTYPE_PARAM = "drilltype";

    public static final String EVENTS_DRILL_TYPE_PARAM = "EVENTS";

    public static final String NODE_PARAM = "node";

    public static final String SEARCH_PARAM = "searchParam";

    public static final String ALTERNATIVE_FDN_PARAM = "alternativeFdn";

    public static final String GRID_PARAM = "grid";

    public static final String CHART_PARAM = "chart";

    public static final String LINE_PARAM = "line";

    public static final String EVENT_ID_PARAM = "eventID";

    public static final String VENDOR_PARAM = "vendor";

    public static final String VENDOR_PARAM_UPPER_CASE = VENDOR_PARAM.toUpperCase();

    public static final String BSC_PARAM = "bsc";

    public static final String APN_PARAM = "apn";

    public static final String APN_PARAM_UPPER_CASE = APN_PARAM.toUpperCase();

    public static final String SGSN_PARAM = "sgsn";

    public static final String GGSN_PARAM = "ggsn";

    public static final String CELL_PARAM = "cell";

    public static final String MSC_PARAM = "msc";

    public static final String TAC_PARAM = "tac";

    public static final String EVENT_NAME_PARAM = "eventName";

    public static final String TAC_PARAM_UPPER_CASE = TAC_PARAM.toUpperCase();

    public static final String MAN_PARAM = "manufacturer";

    public static final String MARKETING_NAME_PARAM = "marketingname";

    public static final String MARKETING_NAME_PARAM_UPPERCASE = MARKETING_NAME_PARAM.toUpperCase();

    public static final String KEY_PARAM = "key";

    public static final String GROUP_NAME_PARAM = "groupname";

    public static final String GROUP_NAME_KEY = "GROUP_NAME";

    public static final String KEY_TYPE_ERR = "ERR";

    public static final String KEY_TYPE_SUC = "SUC";

    public static final String KEY_TYPE_SUM = "SUM";

    public static final String KEY_TYPE_TOTAL = "TOTAL";

    public static final String FORMATTED_DATE_TIME_RANGE = "formattedDateTimeRange";

    public static final String PREVIOUS_DAY = "previousDay";

    public static final String KEY_TYPE_COUNT = "COUNT";

    public static final String KEY_TYPE_DATA = "DATA";

    public static final String KEY_DATA_TIERED_DELAY = "dataTieredDelay";

    public static final String NOTIFICATION_SEVERITY_PARAM = "notificationSeverity";

    // FOR MSS FEATURE
    public static final String KEY_TYPE_DROP_CALL = "DROP_CALL";

    public static final String KEY_TYPE_LOC_SERVICE_ERR = "LOC_SERVICE_ERR";

    public static final String KEY_TYPE_LOC_SERVICE_SUC = "LOC_SERVICE_SUC";

    public static final String KEY_TYPE_SMS_ERR = "SMS_ERR";

    public static final String KEY_TYPE_SMS_SUC = "SMS_SUC";

    public static final String KEY_TYPE_DT = "KEY_TYPE_DT";

    public static final String KEY_TYPE_DTPDP = "KEY_TYPE_DTPDP";

    public static final String CALL_TYPE_PARAM = "callType";

    public static final String ERROR_TYPE_PARAM = "errorType";

    public static final String TYPE_IMSI = "IMSI";

    public static final String TYPE_IMSI_GROUP = "imsiGroup";

    public static final String TYPE_SGSN = "SGSN";

    public static final String TYPE_SGSN_GROUP = "SGSN_GROUP";

    public static final String TYPE_BSC = "BSC";

    public static final String TYPE_MSC = "MSC";

    public static final String TYPE_ENODEB = "ENODEB";

    public static final String TYPE_APN = "APN";

    public static final String TYPE_APN_GROUP = "APN_GROUP";

    public static final String TYPE_CELL = "CELL";

    public static final String TYPE_ECELL = "ECELL";

    public static final String TYPE_TRAC = "TRAC";

    public static final String TYPE_TAC = "TAC";

    public static final String TYPE_TERM = "TERM";

    public static final String TYPE_CAUSE_PROT_TYPE = "CAUSE_PROT_TYPE";

    public static final String TYPE_MAN = "MANUFACTURER";

    public static final String TYPE_RNC = "RNC";

    public static final String TYPE_PTMSI = "PTMSI";

    public static final String TYPE_GGSN = "GGSN";

    public static final String TYPE_QOS = "QOS";

    public static final String TYPE_MSISDN = "MSISDN";

    public static final String TYPE_NETWORK = "NETWORK";

    public static final String TYPE_SOURCE_CELL = "SOURCE_CELL";

    public static final String TYPE_TARGET_CELL = "TARGET_CELL";

    public static final String TYPE_MSS_IMSI_LONG_DURATION_CALLS = "MSS_IMSI_LONG_DURATION_CALLS";

    public static final String TYPE_MSS_IMSI_SHORT_DURATION_CALLS = "MSS_IMSI_SHORT_DURATION_CALLS";

    public static final String TYPE_MSS_MS_ORIGINATING_UNANSWERED = "MSS_MS_ORIGINATING_UNANSWERED";

    public static final String TYPE_MSS_MS_TERMINATING_UNANSWERED = "MSS_MS_TERMINATING_UNANSWERED";

    public static final String GROUP_TYPE_APN = "APN";

    public static final String GROUP_TYPE_IMSI = "IMSI";

    public static final String GROUP_TYPE_TAC = "TAC";

    public static final String GROUP_TYPE_SGSN = "EVNTSRC";

    public static final String GROUP_TYPE_EVENT_SRC_CS = "EVNTSRC_CS";

    public static final String GROUP_TYPE_HIER3 = "RAT_VEND_HIER3";

    public static final String GROUP_TYPE_HIER1 = "RAT_VEND_HIER321";

    public static final String GROUP_TYPE_LTE_TRAC = "LTE_TRAC";

    public static final String HIER2 = "HIERARCHY_2";

    public static final String SUBSCRIBER_TYPE = "subType";

    public static final String TIMERANGE_PARAM = "timerange";

    public static final String SUC_TIMERANGE = "sucTimerange";

    public static final String TIMEWINDOW_PARAM = "twind";

    public static final String DELIMITER = ",";

    public static final int CHART_SLOT_COUNT = 30;

    public static final String COUNT_PARAM = "count";

    public static final String INTERVAL_PARAM = "interval";

    public static final String START_TIME = "starttime";

    public static final String END_TIME = "endtime";

    public static final String KPI_X_AXIS_VALUE = "1";

    public static final String KPI_SECOND_Y_AXIS_VALUE = "11";

    public static final String MSS_KPI_SECOND_Y_AXIS_VALUE = "2";

    public static final String EVENT_VOLUME_X_AXIS_VALUE = "1";

    public static final String EVENT_VOLUME_SECOND_Y_AXIS_VALUE = "33";

    public static final String NETWORK_EVENT_VOLUME_X_AXIS_VALUE = "1";

    public static final String NETWORK_DATA_VOLUME_X_AXIS_VALUE = "1";

    public static final String NETWORK_DATA_VOLUME_SECOND_Y_AXIS_VALUE = "4";

    public static final String MSS_EVENT_VOLUME_X_AXIS_VALUE = "1";

    public static final String MSS_EVENT_VOLUME_SECOND_Y_AXIS_VALUE = "23";

    public static final String MSS_NETWORK_EVENT_VOLUME_X_AXIS_VALUE = "1";

    public static final String ROAMING_X_AXIS_VALUE = "1";

    public static final String ROAMING_SECOND_Y_AXIS_VALUE = "4";

    public static final String TERMINAL_GROUP_ANALYSIS_X_AXIS_VALUE = "2";

    public static final String EVENT_TIME_COLUMN_INDEX = "1";

    public static final int MSS_MS_ORIGINATING_TERMINATING_EVENT_TIME_COLUMN_INDEX = 4;

    public static final int MSS_CALL_FORWARDING_ROAMING_CALL_EVENT_TIME_COLUMN_INDEX = 2;

    public static final int MSS_MS_ORIGINATING_TERMINATING_SEIZURE_TIME_COLUMN_INDEX = 42;

    public static final int MSS_CALLFORWARDING_SEIZURE_TIME_COLUMN_INDEX = 34;

    public static final int MSS_ROAMING_CALL_SEIZURE_TIME_COLUMN_INDEX = 36;

    public static final int MSS_SMS_ORIGINATING_EVENT_TIME_COLUMN_INDEX = 36;

    public static final int MSS_SMS_DELIVERY_EVENT_TIME_COLUMN_INDEX = 37;

    public static final String MSS_LOCATION_SERVICE_EVENT_ID_SQL_PARAM = EventIDConstants.MSS_LOCATION_SERVICE_EVENT_ID;

    public static final String MSS_SMS_MS_ORIGINATING_EVENT_ID_SQL_PARAM = EventIDConstants.MSS_SMS_MS_ORIGINATING_EVENT_ID;

    public static final String MSS_SMS_MS_TERMINATING_EVENT_ID_SQL_PARAM = EventIDConstants.MSS_SMS_MS_TERMINATING_EVENT_ID;

    public static final String TZ_OFFSET = "tzOffset";

    public static final String CSV_PARAM = "csv";

    public static final String MAX_ROWS = "maxRows";

    public static final String TERMINAL_GROUP_ANALYSIS_MOST_EVENT_SUMMARY_SECOND_Y_AXIS_VALUE = "7";

    public static final String TERMINAL_GROUP_ANALYSIS_HIGHEST_DATAVOL_SECOND_Y_AXIS_VALUE = "6";

    public static final String Y_AXIS_MIN = "\"yaxis_min\"";

    public static final String Y_AXIS_MAX = "\"yaxis_max\"";

    public static final int MINUTES_INDEX = 14;

    public static final String ROAMING_OBJECT = "roamingobject";

    public static final String TYPE_ROAMING_COUNTRY = "MCC";

    public static final String TYPE_ROAMING_OPERATOR = "MCC_MNC";

    public static final String TYPE_ROAMING_MNC = "MNC";

    public static final String ALL_NODES_PARAM = "ALL";

    public static final String SINGLE_NODES_PARAM = "SINGLE";

    public static final String TYPE_CAUSE_CODE = "CC";

    public static final String TYPE_SUB_CAUSE_CODE = "SCC";

    public static final String TYPE_INTERNAL_CAUSE_CODE = "ICC";

    public static final String CAUSE_CODE_PARAM = "causeCode";

    public static final String CAUSE_CODE_GRID = "CAUSE_CODE_GRID";

    public static final String NETWORK_CAUSE_CODE_GRID = "NETWORK_CAUSE_CODE_GRID";

    public static final String SUB_CAUSE_CODE_GRID = "SUB_CAUSE_CODE_GRID";

    public static final String NETWORK_SUB_CAUSE_CODE_GRID = "NETWORK_SUB_CAUSE_CODE_GRID";

    public static final String SUB_CAUSE_CODE_PARAM = "subCauseCode";

    public static final String CAUSE_PROT_TYPE = "causeProtType";

    public static final String RAT_PARAM = "RAT";

    public static final String LIVELOAD_CALLBACK_PARAM = "callback";

    public static final String LIVELOAD_LIMIT_PARAM = "limit";

    public static final String LIVELOAD_QUERY_PARAM = "query";

    public static final String SUBBI_X_AXIS_VALUE = "3";

    public static final int INDEX_OF_CELL_COLUMN_IN_CELLS_CONNECTED_TO_SAC_QUERY = 3;

    public static final String PAGING_LIMIT_KEY = "limit";

    public static final String PAGING_START_KEY = "start";

    public static final String IMSI_SUBBI_DETAILS_FIRST_SEEN_TIME_COLUMN_INDEX = "9";

    public static final String IMSI_SUBBI_DETAILS_LAST_SEEN_TIME_COLUMN_INDEX = "10";

    public static final String PTMSI_SUBBI_DETAILS_FIRST_SEEN_TIME_COLUMN_INDEX = "7";

    public static final String PTMSI_SUBBI_DETAILS_LAST_SEEN_TIME_COLUMN_INDEX = "8";

    public static final String SUBBI_TERMINAL_FIRST_SEEN_TIME_COLUMN_INDEX = "10";

    public static final String SUBBI_TERMINAL_LAST_SEEN_TIME_COLUMN_INDEX = "11";

    public static final String RAW_TABLES = "rawtables";

    public static final String RAW_ALL_ERR_TABLES = "rawAllErrTables";

    public static final String RAW_ALL_SUC_TABLES = "rawAllSucTables";

    public static final String RAW_NON_LTE_TABLES = "rawNonLteTables";

    public static final String RAW_LTE_TABLES = "rawLteTables";

    public static final String RAW_MSS_TABLES = "rawMssTables";

    public static final String RAW_ERR_TABLES = "rawErrTables";

    public static final String RAW_SUC_TABLES = "rawSucTables";

    public static final String RAW_DTPUT_TABLES = "rawDtputTables";

    public static final String RAW_DT_TABLES = "rawDTTables";

    public static final String RAW_DTPDP_TABLES = "rawDTPDPTables";

    public static final String RAW_LTE_ERR_TABLES = "rawLteErrTables";

    public static final String RAW_LTE_SUC_TABLES = "rawLteSucTables";

    public static final String EVENT_RESULT_PARAM = "eventResult";

    public static final String EVENT_TYPE_PARAM = "eventType";

    public static final String CAUSE_PROT_TYPE_HEADER = "causeProtTypeHeader";

    public static final String CAUSE_CODE_HEADER = "causeCodeHeader";

    public static final String SUB_CAUSE_CODE_HEADER = "subCauseCodeHeader";

    public static final String PARAM_HEADERS = "paramHeaders";

    public static final String IMSI_HEADER = "imsiHeader";

    public static final String IP_ADDRESS_PARAM = "srcIpAddress";

    public static final String TO_SELECT_LATEST_TABLE = "toSelectLatestTable";

    /**
     * Terminal Query constants
     */
    public static final String PDP_KEY_VALUE = "ACTIVATE";

    public static final String ATTACHED_KEY_VALUE = "ATTACH";

    public static final String MOBILITY_KEY_VALUE = "MOBILITY";

    public static final String SEVERITY_INDICATOR_COLUMN = "SEVERITY_INDICATOR";

    public static final String EXCEPTION_CLASS_COLUMN = "EXCEPTION_CLASS";

    public static final String EVALUATION_CASE_COLUMN = "EVALUATION_CASE";

    public static final String PROCEDURE_INDICATOR_COLUMN = "PROCEDURE_INDICATOR";

    public static final String EXTENDED_CAUSE_VALUE_COLUMN = "EXTENDED_CAUSE_VALUE";

    public static final String C_ID_1_COLUMN = "C_ID_1";

    public static final String RNC_ID_1_COLUMN = "RNC_ID_1";

    public static final String EVENT_TIME_COLUMN = "EVENT_TIME";

    public static final String CELL_ID_COLUMN = "CELL_ID";

    public static final String CATEGORY_ID = "CATEGORY_ID";

    public static final String AF_CAUSE = "AF_CAUSE";

    public static final String AF_ID = "AF_ID";

    public static final String SHORT_DESC = "SHORT_DESC";

    public static final String ORIGIN = "ORIGIN";

    public static final String CATEGORY_ID_DESC = "CATEGORY_ID_DESC";

    public static final String CATEGORY_TABLE_NAME = "CATEGORY_TABLE_NAME";

    public static final String CATEGORY_ID_DESC_PARAM = "categoryIdDesc";

    public static final String QCI_NO = "QCI_NO";

    public static final String QCI_ID_DESCRIPTION = "QCI_ID_DESCRIPTION";

    /**
     * Events Tech Pack name.
     */
    public static final String EVENT_E_SGEH_TPNAME = "EVENT_E_SGEH";

    public static final String EVENT_E_GROUP_TPNAME = "GROUP_TYPE_E";

    public static final String EVENT_E_MSS_TPNAME = "EVENT_E_MSS";

    public static final String EVENT_E_GSN_DTPDP_TPNAME = "EVENT_E_GSN_DTPDP";

    public static final String EVENT_E_LTE_TPNAME = "EVENT_E_LTE";

    public static final String EVENT_E_GSN_DT_TPNAME = "EVENT_E_GSN_DT";

    public static final String EVENT_E_GSN_LICENCSED_TP_NAME = "EVENT_E_GSN";

    public static final String EVENT_E_LTE_CFA_LICENCSED_TP_NAME = "EVENT_E_LTE_CFA";

    public static final String EVENT_E_LTE_CFA_TP_NAME = "EVENT_E_LTE_CFA";

    public static final String EVENT_E_LTE_CFA_ARRAY_ERAB_TP_NAME = "EVENT_E_LTE_CFA_ARRAY_ERAB";

    public static final String EVENT_E_LTE_HFA_LICENCSED_TP_NAME = "EVENT_E_LTE_HFA";

    public static final String EVENT_E_LTE_HFA_TP_NAME = "EVENT_E_LTE_HFA";

    public static final String EVENT_E_LTE_HFA_ARRAY_ERAB_TP_NAME = "EVENT_E_LTE_HFA_ARRAY_ERAB";

    public static final String ARRAY_ERAB = "ARRAY_ERAB";

    public static final String DC_Z_ALARM = "DC_Z_ALARM";

    public static final String EVENT_E_RAN_CFA_TPNAME = "EVENT_E_RAN_CFA";

    public static final String EVENT_E_RAN_HFA_TPNAME = "EVENT_E_RAN_HFA";

    public static final String EVENT_E_RAN_SESSION_TPNAME = "EVENT_E_RAN_SESSION";

    public static final String EVENT_E_CORE_SESSION_TPNAME = "EVENT_E_CORE_SESSION";

    public static final String EVENT_E_USER_PLANE_TPNAME = "EVENT_E_USER_PLANE";

    /**
     * View postfixes
     */
    public static final String ONE_MINUTE_VIEW = "_1MIN";

    public static final String FIFTEEN_MINUTES_VIEW = "_15MIN";

    public static final String DAY_VIEW = "_DAY";

    public static final String RAW_VIEW = "_RAW";

    public static final String RAW_ERR_VIEW = "_ERR_RAW";

    public static final String SUMMARY_TYPE = "summarytype";

    public static final String ERROR_SUMMARY_TYPE = "ERROR_SUMMARY";

    public static final String TOTAL_SUMMARY_TYPE = "TOTAL_SUMMARY";

    /**
     * query template constants
     */
    // to be refactored - original constants used in vm templates so this is tem
    // measure.
    public static final String ONE_MINUTE = EventDataSourceType.AGGREGATED_1MIN.getValue();

    public static final String FIFTEEN_MINUTES = EventDataSourceType.AGGREGATED_15MIN.getValue();

    public static final String DAY = EventDataSourceType.AGGREGATED_DAY.getValue();

    public static final String RAW = EventDataSourceType.RAW.getValue();

    public static final String SGSN_SQL_NAME = "EVENT_SOURCE_NAME";

    public static final String BSC_SQL_NAME = "HIERARCHY_3";

    public static final String CELL_SQL_NAME = "HIERARCHY_1";

    public static final String QCI_ID_SQL_ID = "QCI_ID";

    public static final String CC_SQL_NAME = "CAUSE_CODE";

    public static final String SCC_SQL_NAME = "SUBCAUSE_CODE";

    public static final String INTERNAL_CC_SQL_NAME = "INTERNAL_CAUSE_CODE";

    public static final String SCC_HELP_SQL_NAME = "SUBCAUSE_CODE_HELP";

    public static final String EVENT_SOURCE_SQL_ID = "EVNTSRC_ID";

    public static final String CONTROLLER_SQL_ID = "HIER3_ID";

    public static final String CELL_SQL_ID = "HIER321_ID";

    public static final String CATEGORY_ID_2 = "CATEGORY_ID_2";

    public static final String SUBBI_CELL_SQL_ID = "SUBBI_HIER321_ID";

    public static final String IS_SUBBI_TERMINAL_ANALYSIS = "isSubbiterminalAnalysis";

    public static final String GROUP_COLUMN = "groupColumn";

    public static final String DATE_FROM = "dateFrom";

    public static final String DATE_TO = "dateTo";

    public static final String DATE_FROM_FOR_APN_RETENTION = "dateFromForApnRetention";

    public static final String GROUP_KEYS = "keys";

    public static final String GROUP_TABLE = "tableName";

    public static final String CAUSE_CODE = "causeCode";

    public static final String CAUSE_CODE_DESCRIPTION = "causeCodeDescription";

    public static final String SUB_CAUSE_CODE_DESCRIPTION = "subCauseCodeDescription";

    public static final String CAUSE_CODE_TABLE = "causeCode";

    public static final String SUB_CAUSE_CODE = "subCauseCode";

    public static final String GROUP_DEFINITIONS = "groupDefinitions";

    public static final String LIVELOAD_HIERARCHY_3 = "HIERARCHY_3";

    public static final String LIVELOAD_HIERARCHY_1 = "HIERARCHY_1";

    public static final String LIVELOAD_HIERARCHY_TYPE = "HIERARCHY_TYPE";

    public static final String LIVELOAD_4G_HIERARCHY_TYPE = "HIERARCHY_TYPE_4G";

    public static final String LIVE_LOAD_4G_CONTROLLER_COLUMN = "ERBS_ID";

    public static final String LIVE_LOAD_4G_CELL_COLUMN = "EUtranCellId";

    public static final String HOUR_FORMAT = "+,00;-,00";

    public static final String MINUTE_FORMAT = "#,00";

    public static final String SUBBI_BUSY_KEY = "busyKey";

    public static final String HOUR_PARAM = "HOUR";

    public static final String HOUR_SQL_PARAM = "HOUR_ID";

    public static final String EVENT_ID_SQL_PARAM = "EVENT_ID";

    public static final String DAY_PARAM = "DAY";

    public static final String RAW_TABLE_NAME_COLUMN = "TABLENAME";

    public static final String RAT_COLUMN_NAME = "RAT";

    /**
     * Event Types
     */
    public static final String L_ATTACH = "L_ATTACH";

    public static final String L_DETACH = "L_DETACH";

    public static final String L_HANDOVER = "L_HANDOVER";

    public static final String L_TAU = "L_TAU";

    public static final String L_DEDICATED_BEARER_ACTIVATE = "L_DEDICATED_BEARER_ACTIVATE";

    public static final String L_DEDICATED_BEARER_DEACTIVATE = "L_DEDICATED_BEARER_DEACTIVATE";

    public static final String L_PDN_CONNECT = "L_PDN_CONNECT";

    public static final String L_PDN_DISCONNECT = "L_PDN_DISCONNECT";

    public static final String L_SERVICE_REQUEST = "L_SERVICE_REQUEST";

    public static final String ATTACH = "ATTACH";

    public static final String ACTIVATE = "ACTIVATE";

    public static final String RAU = "RAU";

    public static final String ISRAU = "ISRAU";

    public static final String DEACTIVATE = "DEACTIVATE";

    public static final String DETACH = "DETACH";

    public static final String SERVICE_REQUEST = "SERVICE_REQUEST";

    public static final String EVENT_TYPE_TABLE = "DIM_E_SGEH_EVENTTYPE";

    public static final String EVENT_ID_DESC = "EVENT_ID_DESC";

    public static final String EVENT_ID_LABEL = "EVENT_ID_LABEL";

    public static final String EVENT_ID = "EVENT_ID";

    public static final String TARGET_EVENT_ID = "TARGET_EVENT_ID";

    public static final String EVENT_ID_WITH_CONDITIONS = "EVENT_ID_WITH_CONDITIONS";

    public static final String EVENT_IDS = "EVENT_IDS";

    // MSS related event types
    public static final String MS_ORIGINATING = "mSOriginating";

    public static final String MS_TERMINATING = "mSTerminating";

    public static final String CALL_FORWARDING = "callForwarding";

    public static final String ROAMING_CALL_FORWARDING = "roamingCallForwarding";

    public static final String MS_ORIGINATING_SMS_MSC = "mSOriginatingSMSinMSC";

    public static final String MS_TERMINATING_SMS_MSC = "mSTerminatingSMSinMSC";

    public static final String LOCATION_SERVICES = "locationServices";

    /**
     * used from template file
     */
    public static final String SGEH = "SGEH";

    public static final String LTE = "LTE";

    public static final String DT = "DT";

    public static final String IS_LTE_VIEW = "isLTEView";

    public static final String IS_COMBINED_VIEW = "isCombinedView";

    public static final String IS_MSS_VIEW = "isMSSView";

    public static final String IS_DTPDP_VIEW = "isDTPDPView";

    public static final String IS_DT_VIEW = "isDTView";

    public static final String RAT_DESC_COLUMN_NAME = "RAT_DESC";

    public static final String REQUEST_ID = "requestId";

    public static final String CANCEL_REQ_NOT_SUPPORTED = "cancelRequestNotSupported";

    public static final String RAT_INTEGER_VALUE_FOR_2G = "0";

    public static final String RAT_INTEGER_VALUE_FOR_3G = "1";

    public static final String RAT_INTEGER_VALUE_FOR_4G = "2";

    public static final String RAT_INTEGER_VALUE_FOR_LTE = RAT_INTEGER_VALUE_FOR_4G;

    public static final String EXCLUSIVE_TAC_GROUP_NAME = "EXCLUSIVE_TAC";

    public static final String USE_TAC_EXCLUSION_BOOLEAN = "useTACExclusion";

    public static final String IS_EXCULDED_TAC_OR_TACGROUP = "isExcludedTacOrGroup";

    // Fields in UI Meta Data
    public static final String TABS = "tabs";

    public static final String LICENSES = "licenses";

    public static final String FEATURE_NAME = "featureName";

    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String GRIDS = "grids";

    public static final String COLUMNS = "columns";

    public static final String LICENSE_CXC = "licenseCXC";

    public static final String DRILLDOWN_LICENSE_CXC = "drillDownLicenseCXC";

    public static final String DRILLDOWN_WINDOWTYPE_METADATA_ID = "drillDownWindowType";

    public static final String LAUNCH_WINDOWTYPE_METADATA_ID = "launchWindowType";

    public static final String MASTER_METADATA_CHANGER = "masterMetaDataChanger";

    public static final String ITEMS = "items";

    public static final String TYPE = "type";

    public static final String TAB_MENU_ITEMS = "tabMenuItems";

    public static final String SEARCH_FIELDS = "searchFields";

    public static final String TASKBAR_BUTTONS = "taskBarButtons";

    public static final String URL = "url";

    public static final String KEY = "key";

    public static final String ISLICENSED = "isLicenced";

    public static final String TRUE_UPPER_STR = "TRUE";

    public static final String FALSE_UPPER_STR = "FALSE";

    public static final String IS_SYSTEM = "isSystem";

    public static final String TRUE = "true";

    public static final String IS_ROLE_ENABLED = "isRoleEnabled";
	
	public static final String LICENCE_TYPES = "licenceTypes";

    // Time range constants
    public static final int MINUTES_IN_A_DAY = 1440;

    public static final int MINUTES_IN_12_HOURS = 720;

    public static final int MINUTES_IN_A_WEEK = 10080;

    public static final int MINUTES_IN_2_WEEKS = 20160;

    public static final int MILLISECOND_IN_1_SECOND = 1000;

    public static final int SECONDS_IN_1_MIN = 60;

    public static final int SECONDS_IN_1_HOUR = 3600;

    public static final int SECONDS_IN_15_MINS = 900;

    public static final int SECONDS_IN_1_DAY = 86400;

    public static final int MINUTES_IN_2_HOURS = 120;

    /**
     * Datetime format constants
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String UI_DATE_FORMAT = "ddMMyyyy";

    public static final String SINGLE_SPACE = " ";

    public static final String COMMA = ",";

    public static final char COMMA_CHAR = ',';

    public static final String HYPHEN = "-";

    public static final String QUOTE_SINGLE = "'";

    public static final String CLOSING_BRACKET = ")";

    public static final int ONE_MINUTE_VALUE = 1;

    public static final int FIVE_MINUTE_VALUE = 5;

    public static final int THIRTY_MINUTES_VALUE = 30;

    public static final String RECEIVED_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    // we expect the UI 24hr pattern to be of the form HHmm
    public static final Pattern UI_TIME_PATTERN_24HR = Pattern.compile("([01][0-9]|2[0-3])[0-5][0-9]");

    /**
     * Max possible value for the JSON Result size - to be used if the json result
     * should be unlimited
     */
    public static final int UNLIMITED_JSON_RESULT_SIZE = Integer.MAX_VALUE;

    public static final int GROUP_MOST_FREQ_SIGNAL_ERROR_COL_NO = 2;

    public static final int GROUP_MOST_FREQ_SIGNAL_SUCCESS_COL_NO = 3;

    public static final String ERR_AGGREGATION_TABLES = "errAggTables";

    public static final String SUC_AGGREGATION_TABLES = "sucAggTables";

    public static final String DT_AGGREGATION_TABLES = "dtAggTables";

    public static final String DTPDP_AGGREGATION_TABLES = "dtPdpAggTables";

    public static final String TABLES = "tables";

    public static final String FAILURE_TYPE_PARAM = "failure_type";

    public static final String CONTROLLER_PARAM = "controller";

    public static final String COLUMNS_FOR_QUERY = "columnsForQuery";

    public static final String COLUMNS_FOR_DTPUT_RANKING_QUERY = "columnsForDtQuery";

    public static final String UNDERSCORE = "_";

    public static final String USE_AGGREGATION_TABLES = "useAggregationTables";

    public static final String TECH_PACK_TABLES = "techPackTables";

    public static final String TECH_PACK_TABLES_SGEH = "techPackTablesSGEH";

    public static final String TECH_PACK_TABLES_LTE = "techPackTablesLTE";

    public static final String HIER3 = "HIER3";

    public static final String HIER3_EVENTID = "HIER3_EVENTID";

    public static final String HIER321_EVENTID = "HIER321_EVENTID";

    public static final String THIER321_EVENTID = "THIER321_EVENTID";

    public static final String HIER3_EVENTID_CV = "HIER3_EVENTID_CV_ECV";

    public static final String VEND_HIER321 = "VEND_HIER321";

    public static final String VEND_HIER3 = "VEND_HIER3";

    public static final String VEND_HIER3_EVENTID = "VEND_HIER3_EVENTID";

    public static final String EVNTSRC_CC = "EVNTSRC_CC";

    public static final String EVNTSRC_CC_SCC = "EVNTSRC_CC_SCC";

    public static final String EVNTSRC_EVENTID = "EVNTSRC_EVENTID";

    public static final String VEND_HIER3_CC_SCC = "VEND_HIER3_CC_SCC";

    public static final String VEND_HIER321_CC_SCC = "VEND_HIER321_CC_SCC";

    public static final String VEND_HIER321_EVENTID = "VEND_HIER321_EVENTID";

    public static final String EVENTID_EVNTSRC_HIER3 = "EVENTID_EVNTSRC_HIER3";

    public static final String IMSI_LONG_DUR_RANK = "IMSI_LONG_DUR_RANK";

    public static final String IMSI_SHORT_DUR_RANK = "IMSI_SHORT_DUR_RANK";

    public static final String IMSI_UNANSWERED_CALL_RANK = "IMSI_UNANSWERED_CALL_RANK";

    public static final String APN_CC_SCC = "APN_CC_SCC";

    public static final String EVNTSRC_CC_FC = "EVNTSRC_CC_FC";

    public static final String HIER3_CC_FC = "VEND_HIER3_CC_FC";

    public static final String APN_EVENTID_EVNTSRC_VEND_HIER3 = "APN_EVENTID_EVNTSRC_VEND_HIER3";

    public static final String APN_EVENTID = "APN_EVENTID";

    public static final String APN = "APN";

    public static final String QOS_NETWORK_SUMMARY = "QOS_NETWORK_SUMMARY";

    public static final String PATH_SEPARATOR = "/";

    public static final String COLUMNS_TO_SELECT_FROM_RAW_TABLES_FOR_QOS_ERR_SUMMARY = "COLUMNS_TO_SELECT_FROM_RAW_TABLES_FOR_QOS_ERR_SUMMARY";

    public static final String COLUMNS_TO_SELECT_FROM_RAW_TABLES_FOR_QOS_SUC_SUMMARY = "COLUMNS_TO_SELECT_FROM_RAW_TABLES_FOR_QOS_SUC_SUMMARY";

    public static final String DATETIME_ID = "DATETIME_ID";

    public static final String DATE_ID = "DATE_ID";

    public static final String COLUMNS_TO_FILTER_ON = "COLUMNS_TO_FILTER_ON";

    public static final String SQUARE_OPENING_BRACKET = "[";

    public static final String SQUARE_CLOSING_BRACKET = "]";

    public static final String AGGREGATION_TABLES = "AGGREGATION_TABLES";

    public static final String DTPUT_AGGREGATION_TABLES = "DTPUT_AGGREGATION_TABLES";

    public static final String OPENING_BRACKET = "(";

    public static final String DATABASE_IO_EXCEPTION_CODE = "JZ0C1";

    public static final String QCI_ID = "qci_id";

    public static final String QCI_ERR_FILTER = "QCI_ERR_FILTER";

    public static final String ERR = "ERR";

    public static final String SUC = "SUC";

    public static final String INFO = "INFO";

    public static final String QCI_ = "QCI_";

    public static final String IMSI = "IMSI";

    public static final String IMSI_GROUP = "IMSI_GROUP";

    public static final String IMSI_GROUP_BREAKDOWN = "IMSI_GROUP/BREAKDOWN";

    public static final String BSC_GROUP_BREAKDOWN = "BSC_GROUP/BREAKDOWN";

    public static final String INTERNAL_CAUSE_CODE_ANALYSIS = "INTERNAL_CAUSE_CODE_ANALYSIS";

    public static final String DIM_E_MSS_EVNTSRC = "DIM_E_MSS_EVNTSRC";

    public static final String DIM_E_MSS_EVNTTYPE = "DIM_E_MSS_EVENTTYPE";

    public static final String MSS_EVNTTYPE_DESCRIPTION_COLUMN = "EVENT_ID_DESC";

    public static final String DIM_E_SGEH_HIER321 = "DIM_E_SGEH_HIER321";

    public static final String DIM_Z_SGEH_HIER321 = "DIM_Z_SGEH_HIER321";

    public static final String DIM_Z_ALARM = "DIM_Z_ALARM";

    public static final String RAW_TYPE = "RAW_TYPE";

    public static final String DIM_E_SGEH_TAC = "DIM_E_SGEH_TAC";

    public static final String DIM_E_LTE_QCI = "DIM_E_LTE_QCI";

    public static final String NO_OF_SUC_ERR_RAW = "count(*)";

    public static final String NO_OF_SUCCESSES_AGG = "NO_OF_SUCCESSES";

    public static final String NO_OF_ERRORS_AGG = "NO_OF_ERRORS";

    public static final String SUM_OF_SUCCESSES_AGG = "sum(NO_OF_SUCCESSES)";

    public static final String SUM_OF_ERRORS_AGG = "sum(NO_OF_ERRORS)";

    public static final String NO_OF_SUCCESSES_CALLS = "NO_OF_SUCCESS_CALLS";

    public static final String NO_OF_DROPPED_CALLS = "NO_OF_DROPPED_CALLS";

    public static final String NO_OF_BLOCKED_CALLS = "NO_OF_BLOCKED_CALLS";

    public static final String NO_OF_UNANSWERED_CALLS = "NO_OF_UNANSWERED_CALLS";

    public static final String SUC_TABLE = "suc";

    public static final String SUC_TABLE_VAR = "sucTable";

    public static final String BLOCKED_TABLE_VAR = "blockedTable";

    public static final String DROPPED_TABLE_VAR = "droppedTable";

    public static final String ERR_AS_TABLE_BLOCKED = "err_blocked";

    public static final String ERR_AS_TABLE_DROPPED = "err_dropped";

    public static final String NO_OF_SUCCESS_VAR = "no_of_success";

    public static final String NO_OF_BLOCKED_CALLS_VAR = "no_of_blocked_calls";

    public static final String NO_OF_DROPPED_CALLS_VAR = "no_of_dropped_calls";

    public static final String IMSI_SUCCESSES_TABLE = "imsiSuccessTable";

    public static final String IMSI_BLOCKED_TABLE = "imsiBlockedTable";

    public static final String IMSI_DROPPED_TABLE = "imsiDroppedTable";

    public static final String IMSI_VIEW = "imsiView";

    public static final String IMSI_TOTAL_VAR = "imsi_table_alias";

    public static final String IMPACTED_SUBSCRIBERS = "impacted_subscriber";

    public static final String TO_CALC_TOTAL_SUBSCRIBERS = "toCalcTotalSubscribers";

    public static final String TABLES_VAR = "tables";

    public static final String TABLES_ALIAS_VAR = "tableAlias";

    public static final String FULL_OUTER_JOIN_STRING = "full outer join";

    public static final String LEFT_OUTER_JOIN_STRING = "left outer join";

    public static final String UNION_ALL = "union all";

    public static final String JOIN_COLUMNS_VAR = "joinColumns";

    public static final String COMMON_COLUMNS_VAR = "commonColumns";

    public static final String FILTER_COLUMNS_VAR = "filterColumns";

    public static final String IMSI_FILTER_COLUMNS_VAR = "imsiFilterColumns";

    public static final String SELECT_COLUMNS_VAR = "selectColumns";

    public static final String RAW_AGG_COLUMNS_VAR = "rawAggColumn";

    public static final String RAW_AGG_COLUMNS_ALIAS_VAR = "rawAggColumnAlias";

    public static final String EXTRA_TABLE_VAR = "extraTable";

    public static final String EXTRA_WHERE_CLAUSE = "extraWhereClause";

    public static final String EVENT_DESCRIPTION_TABLE_VAR = "evntDespTable";

    public static final String EVENT_DESCRIPTION_COLUMN_VAR = "evntDespColumn";

    public static final String TYPE_TO_NODE_TABLE_VIEW_VAR = "typeToNodeTableView";

    public static final String TOPOLOGY_TABLE_VIEW = "topologyView";

    public static final String EVENT_TYPE_TABLE_VIEW = "evntView";

    public static final String ALL_TABLES_QUERY_PART = "allTablesQueryPart";

    public static final String ALL_ALIAS_TABLE_LIST_VAR = "allTableAlias";

    public static final String SUC_COLUMNS_ALIAS_LIST_VAR = "listOfSuccessColumns";

    public static final String SUM_SUC_COLUMNS_ALIAS_LIST_VAR = "sumSucStatementAlias";

    public static final String ERR_COLUMNS_ALIAS_LIST_VAR = "listOfErrorColumns";

    public static final String SUM_ERR_COLUMNS_ALIAS_LIST_VAR = "sumErrStatementAlias";

    public static final String TOTAL_COLUMNS_ALIAS_LIST_VAR = "listOfAllColumns";

    public static final String SUM_TOTAL_COLUMNS_ALIAS_LIST_VAR = "sumTotalStatementAlias";

    public static final String TOTAL_EVENTS = "OCCURRENCES";

    public static final String EXTRA_TABLE_JOIN_COLUMNS_VAR = "extraJoin";

    public static final String TEMP_JOIN_TABLES = "jointables";

    public static final String TEMP_JOIN_VIEW_VAR = "tempView";

    public static final String ON_CLAUSE_JOIN_TABLE = "joinTable";

    public static final String ON_CLAUSE_LEFT_OUTER_JOIN_ALIAS_TABLE = "aliasTables";

    public static final String EVENT_RESULT_VALUE = "eventResult";

    public static final String EVENT_RESULT_DROPPED = "\'DROPPED\'";

    public static final String EVENT_RESULT_BLOCKED = "\'BLOCKED\'";

    public static final String EVENT_RESULT_SUCCESS = "\'SUCCESS\'";

    public static final String EVENT_RESULT_ERROR = "\'ERROR\'";

    public static final String RAW_TABLE_QUERY_VAR = "rawTableQueryPart";

    public static final String RAW_TABLE_COLUMN_ORDER_1_VAR = "columnOrder1";

    public static final String RAW_TABLE_COLUMN_ORDER_1_1_VAR = "columnOrder1_1";

    public static final String RAW_TABLE_COLUMN_ORDER_1_2_VAR = "columnOrder1_2";

    public static final String RAW_TABLE_COLUMN_ORDER_1_3_VAR = "columnOrder1_3";

    public static final String RAW_TABLE_COLUMN_ORDER_1_4_VAR = "columnOrder1_4";

    public static final String RAW_TABLE_COLUMN_ORDER_2_VAR = "columnOrder2";

    public static final String RAW_TABLE_COLUMN_ORDER_3_VAR = "columnOrder3";

    public static final String RAW_TABLE_COLUMN_ORDER_3_1_VAR = "columnOrder3_1";

    public static final String RAW_TABLE_COLUMN_ORDER_3_2_VAR = "columnOrder3_2";

    public static final String RAW_TABLE_COLUMN_ORDER_3_3_VAR = "columnOrder3_3";

    public static final String RAW_TABLE_COLUMN_ORDER_3_4_VAR = "columnOrder3_4";

    public static final String RAW_TABLE_COLUMN_ORDER_4_VAR = "columnOrder4";

    public static final String TOPOLOGY_FILTER_COLUMNS = "topologyFilterColumns";

    public static final String ERROR_VIEW = "errView";

    public static final String COMBINED_ERROR_TABLE = "combinedErrorTable";

    public static final String LIST_OF_ERROR_TABLE_ALIAS = "listOfErrTableAlias";

    public static final String JOIN_ON_CELL_CONTROLLER_VAR = "joinOnCellAndController";

    public static final String JOIN_ON_TAC_VAR = "joinOnTac";

    public static final String MS_ORIGINATING_TABLE_VAR = "allMsOriginatingEventsTable";

    public static final String MS_TERMINATING_TABLE_VAR = "allMsTerminatingEventsTable";

    public static final String CALL_FORWARDING_TABLE_VAR = "allCallForwardingEventsTable";

    public static final String ROAMING_CALL_TABLE_VAR = "allRoamingCallEventsTable";

    public static final String CAUSE_CODE_PIE_CHART = "CAUSE_CODE_PIE_CHART";

    public static final String CAUSE_CODE_TABLE_SCC_WCDMA = "CAUSE_CODE_TABLE_SCC_WCDMA";

    public static final String CAUSE_CODE_TABLE_SCC_GSM = "CAUSE_CODE_TABLE_SCC_GSM";

    public static final String CC_LIST = "CC_LIST";

    public static final String SUB_CAUSE_CODE_ANALYSIS = "SUB_CAUSE_CODE_ANALYSIS";

    public static final String IMPACTED_SUBSCRIBER_SQL_ENTRY = ",NO_OF_IMPACTED_SUBSCRIBER";

    public static final String IMPACTED_SUBSCRIBER_VAR = "no_of_impacted_subscriber";

    public static final String IS_MSS_VOICE_REPORT_VAR = "isMssVoiceReport";

    public static final String IS_MSS_CALLFORWARD_REPORT_VAR = "isMssCallForwardReport";

    public static final String IS_MSS_VOICE_EMERGENCY_CALL = "isMssVoiceEcall";

    public static final String IS_MSS_LOC_SERVICE_REPORT_VAR = "isMssLocServiceReport";

    public static final String TIME_COLUMN = "TIME_COLUMN";

    public static final String NO_TABLE = "NO_TABLE";

    public static final String TECH_PACK_LIST = "TECH_PACK_LIST";

    public static final String RNC_CALL_FAILURE_RANKING_ANALYSIS = "RNC_CALL_FAILURE_RANKING_ANALYSIS";

    public static final String ACCESS_AREA = "ACCESS_AREA";

    public static final String TARGET_ACCESS_AREA = "TARGET_ACCESS_AREA";

    public static final String CATEGORISED_ACCESS_AREA = "CATEGORISED_ACCESS_AREA";

    public static final String CONTROLLER = "CONTROLLER";

    public static final String ENODEB = "ENODEB";

    public static final String LTE_CFA_SUMMARY_ENODEB = "LTE_CFA_SUMMARY_ENODEB";

    public static final String LTE_CFA_SUMMARY_ECELL = "LTE_CFA_SUMMARY_ECELL";

    public static final String LTE_CFA_SUMMARY = "CFA/SUMMARY_LTE";

    public static final String LTE_HFA_SUMMARY = "HFA/SUMMARY_LTE";

    public static final String SUMMARY_LTE = "SUMMARY_LTE";

    public static final String LTE_HFA_SUMMARY_ECELL = "LTE_HFA_SUMMARY_ECELL";

    public static final String CATEGORISED_ENODEB = "CATEGORISED_ENODEB";

    public static final String CATEGORISED_IMSI = "CATEGORISED_IMSI";

    public static final String CATEGORISED_TERMINAL = "CATEGORISED_TERMINAL";

    public static final String TRACKING_AREA = "TRAC";

    public static final String CATEGORISED_TRACKING_AREA = "CATEGORISED_TRAC";

    public static final String ACCESS_AREA_CALL_FAILURE_ANALYSIS_RANKING = "ACCESS_AREA_CALL_FAILURE_ANALYSIS_RANKING";

    public static final String ACCESS_AREA_CALL_FAILURE_EVENT_SUMMARY_ANALYSIS = "ACCESS_AREA_CALL_FAILURE_EVENT_SUMMARY";

    public static final String ACCESS_AREA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "ACCESS_AREA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String TAC_CALL_FAILURE_RANKING_ANALYSIS = "TAC_CALL_FAILURE_RANKING_ANALYSIS";

    public static final String TAC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "TAC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String CAUSE_CODE_CALL_FAILURE_RANKING_ANALYSIS = "CAUSE_CODE_CALL_FAILURE_RANKING_ANALYSIS";

    public static final String SUBSCRIBER_CALL_FAILURE_RANKING_ANALYSIS = "SUBSCRIBER_CALL_FAILURE_RANKING_ANALYSIS";

    public static final String SUBSCRIBER_CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "SUBSCRIBER_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String SUBSCRIBER_CALL_FAILURE_EVENT_ANALYSIS = "SUBSCRIBER_CALL_FAILURE_EVENT_ANALYSIS";

    public static final String SUBSCRIBER_CALL_FAILURE_FAILURE_ANALYSIS_FOR_EVENT_ANALYSIS = "SUBSCRIBER_CALL_FAILURE_FAILURE_ANALYSIS_FOR_EVENT_ANALYSIS";

    public static final String SUBSCRIBER_FAILURE_ANALYSIS_BY_GROUP = "SUBSCRIBER_FAILURE_ANALYSIS_BY_GROUP";

    public static final String SUBSCRIBER_FAILURE_ANALYSIS = "SUBSCRIBER_FAILURE_ANALYSIS";

    public static final String LTE_SUBSCRIBER_CALL_FAILURE_CALL_DROP_RANKING_ANALYSIS = "LTE_SUBSCRIBER_CALL_FAILURE_CALL_DROP_RANKING_ANALYSIS";

    public static final String LTE_SUBSCRIBER_CALL_FAILURE_CALL_SETUP_FAILURE_RANKING_ANALYSIS = "LTE_SUBSCRIBER_CALL_FAILURE_CALL_SETUP_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_SUBSCRIBER_ERAB_DRILLDOWN = "LTE_CALL_FAILURE_SUBSCRIBER_ERAB_DRILLDOWN";

    public static final String LTE_HANDOVER_FAILURE_SUBSCRIBER_ERAB_DRILLDOWN = "LTE_HANDOVER_FAILURE_SUBSCRIBER_ERAB_DRILLDOWN";

    public static final String LTE_CALL_FAILURE_ACCESS_AREA_CALL_DROP_RANKING_ANALYSIS = "LTE_CALL_FAILURE_ACCESS_AREA_CALL_DROP_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ACCESS_AREA_CALL_SETUP_FAILURE_RANKING_ANALYSIS = "LTE_CALL_FAILURE_ACCESS_AREA_CALL_SETUP_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_CAUSE_CODE_CALL_DROP_RANKING_ANALYSIS = "LTE_CALL_FAILURE_CAUSE_CODE_CALL_DROP_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_CAUSE_CODE_CALL_SETUP_RANKING_ANALYSIS = "LTE_CALL_FAILURE_CAUSE_CODE_CALL_SETUP_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ENODEB_CALL_DROP_RANKING_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_CALL_DROP_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ENODEB_CALL_SETUP_FAILURE_RANKING_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_CALL_SETUP_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TERMINAL_CALL_DROP_RANKING_ANALYSIS = "LTE_CALL_FAILURE_TERMINAL_CALL_DROP_RANKING_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_EXEC_RANKING_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_EXEC_RANKING_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_PREP_RANKING_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_PREP_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TERMINAL_CALL_SETUP_FAILURE_RANKING_ANALYSIS = "LTE_CALL_FAILURE_TERMINAL_CALL_SETUP_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CALL_DROP_RANKING_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_CALL_DROP_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CALL_SETUP_FAILURE_RANKING_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_CALL_SETUP_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_CAUSE_CODE_FAILURE_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_CAUSE_CODE_FAILURE_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_SUBSCRIBER_REOCCURING_FAILURE_RANKING_ANALYSIS = "LTE_CALL_FAILURE_SUBSCRIBER_REOCCURING_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_ECELL_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_ECELL_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_ECELL_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_ECELL_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_ENODEB_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_ENODEB_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_ENODEB_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_ENODEB_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_TRACKING_AREA_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_TRACKING_AREA_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_TRACKING_AREA_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_TRACKING_AREA_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_ECELL_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_ECELL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_ECELL_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_ECELL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_ENODEB_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_ENODEB_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_ENODEB_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_ENODEB_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_TRACKING_AREA_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_TRACKING_AREA_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_TRACKING_AREA_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_TRACKING_AREA_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_ECELL_DETAILED_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_ECELL_DETAILED_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_ECELL_DETAILED_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_ECELL_DETAILED_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_ENODEB_DETAILED_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_ENODEB_DETAILED_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_ENODEB_DETAILED_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_ENODEB_DETAILED_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_QCI_TRACKING_AREA_DETAILED_ANALYSIS = "LTE_CALL_FAILURE_QOS_QCI_TRACKING_AREA_DETAILED_ANALYSIS";

    public static final String LTE_CALL_FAILURE_QOS_PCI_TRACKING_AREA_DETAILED_ANALYSIS = "LTE_CALL_FAILURE_QOS_PCI_TRACKING_AREA_DETAILED_ANALYSIS";

    public static final String LTE_CALL_FAILURE_NETWORK_EVENT_VOLUME = "LTE_CALL_FAILURE_NETWORK_EVENT_VOLUME";

    public static final String LTE_CALL_FAILURE_ECELL_EVENT_VOLUME = "LTE_CALL_FAILURE_ECELL_EVENT_VOLUME";

    public static final String LTE_CALL_FAILURE_ENODEB_EVENT_VOLUME = "LTE_CALL_FAILURE_ENODEB_EVENT_VOLUME";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_EVENT_VOLUME = "LTE_CALL_FAILURE_TRACKING_AREA_EVENT_VOLUME";

    public static final String LTE_HANDOVER_FAILURE_SUBSCRIBER_EVENT_SUMMARY_ANALYSIS = "LTE_HANDOVER_FAILURE_SUBSCRIBER_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_EVENT_HANDOVERSTAGE_ANALYSIS = "LTE_HANDOVER_FAILURE_EVENT_HANDOVERSTAGE_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_SUBSCRIBER_EVENT_HANDOVERSTAGE_ANALYSIS = "LTE_HANDOVER_FAILURE_SUBSCRIBER_EVENT_HANDOVERSTAGE_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS";

    public static final String RAW_TIMERANGE_VIEW = "RAW_TIMERANGE_VIEW";

    public static final String SELECT_BOTH_LATEST_ERR_AND_SUC_TABLES = "selectBothLatestERRAndSUCTables";

    public static final String EMPTY_STRING = "";

    public static final String RNC = "RNC";

    public static final String SOURCE_CELL = "SOURCE_CELL";

    public static final String TARGET_CELL = "TARGET_CELL";

    public static final String TAC = "TAC";

    public static final String SUBSCRIBER_FOR_CALL_SETUP_FAILURE = "SUBSCRIBER/CALL_SETUP";

    public static final String SUBSCRIBER_FOR_CALL_DROP = "SUBSCRIBER/CALL_DROP";

    public static final String SUBSCRIBER = "SUBSCRIBER";

    public static final String SUBSCRIBER_FOR_REOCCURING = "SUBSCRIBER/REOCCURING";

    public static final String SUBSCRIBER_CFA_EA_SUMMARY = "SUMMARY";

    public static final String SUBSCRIBER_CFA_EA_DRILL = "DRILL_DOWN";

    public static final String ACCESS_AREA_FOR_CALL_SETUP_FAILURE = "ACCESS_AREA/CALL_SETUP";

    public static final String ACCESS_AREA_FOR_CALL_DROP = "ACCESS_AREA/CALL_DROP";

    public static final String ENODEB_FOR_CALL_SETUP_FAILURE = "ENODEB/CALL_SETUP";

    public static final String ENODEB_FOR_EXEC_FAILURE = "ENODEB/EXEC";

    public static final String ENODEB_FOR_PREP_FAILURE = "ENODEB/PREP";

    public static final String ENODEB_FOR_CALL_DROP = "ENODEB/CALL_DROP";

    public static final String CAUSE_CODE_FOR_CALL_SETUP_FAILURE = "CAUSECODE/CALL_SETUP";

    public static final String CAUSE_CODE_FOR_CALL_DROP = "CAUSECODE/CALL_DROP";

    public static final String TERMINAL_FOR_CALL_SETUP_FAILURE = "TERMINAL/CALL_SETUP";

    public static final String TERMINAL_FOR_CALL_DROP = "TERMINAL/CALL_DROP";

    public static final String TERMINAL_CFA_EA_SUMMARY = "TERMINAL/SUMMARY";

    public static final String TRACKING_AREA_FOR_CALL_SETUP_FAILURE = "TRACKING_AREA/CALL_SETUP";

    public static final String TRACKING_AREA_FOR_CALL_DROP = "TRACKING_AREA/CALL_DROP";

    public static final String BY_IMSI = "BY_IMSI";

    public static final String LTE_CALL_FAILURE_RANKING_ANALYSIS = "LTE_CALL_FAILURE_RANKING_ANALYSIS";

    public static final String CALL_FAILURE_RANKING_ANALYSIS = "CALL_FAILURE_RANKING_ANALYSIS";

    public static final String CALL_FAILURE_ROAMING_ANALYSIS = "CALL_FAILURE_ROAMING_ANALYSIS";

    public static final String CALL_FAILURE_RANKING_BY_CALL_SETUP_URI = "CAUSECODE/CALL_SETUP";

    public static final String CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI = "CAUSECODE/CALL_DROPS";

    public static final String CAUSE_CODE_CALL_FAILURE_BY_CALL_SETUP = "CAUSE_CODE_CALL_FAILURE_BY_CALL_SETUP";

    public static final String DRILLDOWN = "DRILLDOWN";

    public static final String LTE_CFA_DRILLDOWN = "CFA/DRILLDOWN";

    public static final String LTE_HFA_DRILLDOWN = "HFA/DRILLDOWN";

    public static final String DASHBOARD_SUMMARY_CORE = "SUMMARY_CORE";

    public static final String DASHBOARD_SUMMARY_WCDMA = "SUMMARY_WCDMA";

    public static final String IS_MSS_SMS_REPORT_VAR = "isMssSmsReport";

    public static final String AGGREGATION_VIEW = "aggregationView";

    public static final String ERR_AGGREGATION_VIEW = "errAggregationView";

    public static final String SUC_AGGREGATION_VIEW = "sucAggregationView";

    public static final String AGGREGATION_VEIW_LIST = "aggregationViewList";

    public static final String AGGREGATION_VIEW_LIST_SPECIFIC = "aggregationViewListSpecific";

    public static final String AGGREGATION_VIEW_LIST_SPECIFIC_ALTERNATIVE = "aggregationViewListSpecificAlternative";

    public static final String FAILURE_TYPE = "failType";

    public static final String COLON = ":";

    public static final String EQUAL = "=";

    public static final String NOT_EQUAL = "<>";

    public static final String FEATURE_DESCRIPTION_FILE_LOCATION_PROPERTY_NAME = "FEATURE_DESCRIPTION_FILE_LOCATION";

    public static final String RNC_CALL_FAILURE_EVENT_SUMMARY = "RNC_CALL_FAILURE_EVENT_SUMMARY";

    public static final String RNC_ID = "RNC_ID";

    public static final String RNC_ID_PARAM = "RNC_ID";

    public static final String CALL_FAILURE_EVENT_SUMMARY = "CALL_FAILURE_EVENT_SUMMARY";

    public static final String WCDMA_CFA_TERMINAL_EVENT_ANALYSIS = "WCDMA_CFA_TERMINAL_EVENT_ANALYSIS";

    public static final String WCDMA_CFA_NETWORK_EVENT_ANALYSIS = "WCDMA_CFA_NETWORK_EVENT_ANALYSIS";

    public static final String WCDMA_CFA_NETWORK_DETAILED_EVENT_ANALYSIS = "WCDMA_CFA_NETWORK_DETAILED_EVENT_ANALYSIS";

    public static final String WCDMA_CFA_TERMINAL_FAILED_EVENT_ANALYSIS = "WCDMA_CFA_TERMINAL_FAILED_EVENT_ANALYSIS";

    public static final String WCDMA_CFA_CONTROLLER_CAUSE_CODE_LIST = "WCDMA_CFA_CONTROLLER_CAUSE_CODE_LIST";

    public static final String WCDMA_CFA_CONTROLLER_CAUSE_CODE = "WCDMA_CFA_CONTROLLER_CAUSE_CODE";

    public static final String WCDMA_CFA_SUB_CAUSE_CODE = "WCDMA_CFA_SUB_CAUSE_CODE";

    public static final String WCDMA_CFA_CAUSE_CODE_TABLE = "WCDMA_CFA_CAUSE_CODE_TABLE";

    public static final String WCDMA_CFA_SUB_CAUSE_CODE_TABLE = "WCDMA_CFA_SUB_CAUSE_CODE_TABLE";

    public static final String WCDMA_CFA_SUB_CAUSE_CODE_DEA = "WCDMA_CFA_SUB_CAUSE_CODE_DEA";

    public static final String WCDMA_CFA_ACCESS_AREA_CAUSE_CODE_LIST = "WCDMA_CFA_ACCESS_AREA_CAUSE_CODE_LIST";

    public static final String GSM_CFA_CAUSE_CODE_TABLE = "GSM_CFA_CAUSE_CODE_TABLE";

    public static final String GSM_CFA_SUB_CAUSE_CODE_TABLE = "GSM_CFA_SUB_CAUSE_CODE_TABLE";

    public static final String RNC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "RNC_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String ROAMING_STATUS_HOME = "Home";

    public static final String ROAMING_STATUS_AWAY = "Away";

    public static final String CONSTANT_COLUMN_VALUE = "constantColumnValue";

    public static final String CONSTANT_COLUMN_NAME = "constantColumnName";

    public static final String HASH_ID_DELIMITOR = "|";

    public static final String CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String CALL_FAILURE_CAUSE_CODE_ANALYSIS = "CALL_FAILURE_CAUSE_CODE_ANALYSIS";

    public static final String DATA_CONNECTION_DETAILED_EVENT_ANALYSIS = "DATA_CONNECTION_DETAILED_EVENT_ANALYSIS";

    public static final String DATA_CONNECTION_EVENT_SUMMARY = "DATA_CONNECTION_EVENT_SUMMARY";

    public static final String WCDMA_CALL_FAILURE_EVENT_ANALYSIS = "WCDMA/CALL_FAILURE_EVENT_ANALYSIS";

    public static final String CAUSE_VALUE = "CAUSE_VALUE";

    public static final String CAUSE_CODE_DESCRPTION = "CAUSE_CODE_DESCRPTION";

    public static final String CAUSE_CODE_CALL_FAILURE_EVENT_ANALYSIS = "CAUSE_CODE_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String RNCID = "rncId";

    public static final String CELL_ID_PARAM = "cellId";

    public static final String TACID_PARAM = "tacId";

    public static final String RNC_ALTERNATIVE_FDN_PARAM = "RNC_ALTERNATIVE_FDN";

    public static final String RAN_VENDOR_PARAM = "RAN_VENDOR";

    public static final String RNC_FDN_PARAM = "RNC_FDN";

    public static final String RAN_CELL_ID_PARAM = "RAN_CELL_ID";

    public static final String CALLFAILURE_SUBSESSION_BI = "CALLFAILURE_SUBSESSION_BI";

    public static final String CALLFAILURE_MULTIPLE_RECURRING_FAILURES = "CALLFAILURE_MULTIPLE_RECURRING";

    public static final String NO_AGGREGATION_AVAILABLE = "NO_AGGREGATION_AVAILABLE";

    public static final String TAC_EVENTID_AGG_TABLE = "TAC_EVENTID";

    public static final String CALL_FAILURE_SUBBI_FAILURE = "CALL_FAILURE_SUBBI_FAILURE";

    public static final String WCDMA_CALL_FAILURE_SUBSCRIBER_DETAILS = "WCDMA_CALL_FAILURE_SUBSCRIBER_DETAILS";

    public static final String WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String WCDMA_CAUSE_CODE_ANALYSIS = "WCDMA_CAUSE_CODE_ANALYSIS";

    public static final String HIER3_ID = "HIER3_ID";

    public static final String HIER32_ID = "HIER32_ID";

    public static final String HIER321_ID = "HIER321_ID";

    public static final String HIER3_CELL_HASHID = "HIER3_CELL_ID";

    public static final String HIER3_CELL_ID = "HIER3_CELL_ID";

    public static final String THIER3_CELL_ID = "THIER3_CELL_ID";

    public static final String THIER321_HASHID = "THIER321_ID";

    public static final String THIER3_HASHID = "THIER3_ID";

    public static final String THIER3_CELL_HASHID = "THIER3_CELL_ID";

    public static final String _3G = "3G";

    public static final String HFA_IFHO = "IFHO";

    public static final String HFA_SOHO = "SOHO";

    public static final String HFA_IRAT = "IRAT";

    public static final String HFA_HSDSCH = "HSDSCH";

    public static final String HFA_HSDSCH_SUC = "HSDSCH_SUC";

    public static final String CALL_FAILURE_TERMINAL_ANALYSIS = "CALL_FAILURE_TERMINAL_ANALYSIS";

    public static final String CALL_FAILURE_TERMINAL_GROUP_ANALYSIS = "CALL_FAILURE_TERMINAL_GROUP_ANALYSIS";

    public static final String CALLFAILURE_MULTIPLE_RECURRING_FAILURES_DRILL_ON_IMSI = "DRILLDOWN";

    public static final String WCDMA_CFA_TERMINAL_EVENT_ANALYSIS_URL = "WCDMA/CALL_FAILURE_EVENT_ANALYSIS";

    public static final String CALLFAILURE_MULTIPLE_RECURRING_FAILURES_SUMMARY = "SUMMARY";

    public static final String MOST_CALL_DROPS = "MOST_CALL_DROPS";

    public static final String MOST_CALL_SETUP_FAILURES = "MOST_CALL_SETUP_FAILURES";

    public static final String HIER3_CELL_ID_AGG = "HIER3_CELL_ID";

    public static final String HIER3_CELL_ID_AGG_EVENT_ID = "HIER3_CELL_ID_EVENTID";

    public static final String LTE_CALL_FAILURE_EVENT_SUMMARY = "LTE_CALL_FAILURE_EVENT_SUMMARY";

    public static final String LTE_CALL_FAILURE_ERAB_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_ERAB_DETAILED_EVENT_ANALYSIS";

    //added for HFA
    public static final String LTE_HANDOVER_FAILURE_EVENT_SUMMARY = "LTE_HANDOVER_FAILURE_EVENT_SUMMARY";

    public static final String LTE_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_HANDOVERSTAGE_SUMMARY = "LTE_HANDOVER_FAILURE_HANDOVERSTAGE_SUMMARY";

    public static final String LTE_HANDOVER_FAILURE_ERAB_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_ERAB_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_SUBSCRIBER_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_SUBSCRIBER_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_SUBSCRIBER_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_SUBSCRIBER_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_ACCESS_AREA_CALL_FAILURE_ANALYSIS_RANKING = "GSM_ACCESS_AREA_CALL_FAILURE_ANALYSIS_RANKING";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_RANKING = "GSM_CALL_FAILURE_ACCESS_AREA_RANKING";

    public static final String GSM_CALL_FAILURE_TERMINAL_RANKING = "GSM_CALL_FAILURE_TERMINAL_RANKING";

    public static final String GSM_CALL_FAILURE_CAUSE_CODE_CALL_DROP_RANKING = "GSM_CALL_FAILURE_CAUSE_CODE_CALL_DROP_RANKING";

    public static final String GSM_CALL_FAILURE_CONTROLLER_RANKING = "GSM_CALL_FAILURE_CONTROLLER_RANKING";

    public static final String GSM_DATA_CONNECTION_CONTROLLER_RANKING = "GSM_DATA_CONNECTION_CONTROLLER_RANKING";

    public static final String GSM_DETALIED_EVENT_CFA_BY_SUB_CAUSE_CODE = "GSM_DETALIED_EVENT_CFA_BY_SUB_CAUSE_CODE";

    public static final String GSM_DETALIED_EVENT_CFA_BY_SUB_CAUSE_CODE_ANALYSIS = "GSM_DETALIED_EVENT_CFA_BY_SUB_CAUSE_CODE_ANALYSIS";

    public static final String CATEGORY_ID_PARAM = "categoryId";

    public static final String GSM_CALL_DROP_CATEGORY_ID = "0";

    public static final String GSM_CALL_SETUP_FAILURE_CATEGORY_ID = "1";

    public static final String GSM_AF_CAUSE = "AF_CAUSE";

    public static final String GSM_AF_CAUSE_VALUE = "1";

    public static final String LTE_CALL_FAILURE_TERMINAL_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_TERMINAL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ENODEB_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ENODEB_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_EVENT_HANDOVERSTAGE_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_EVENT_HANDOVERSTAGE_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_EVENT_SUMMARY_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_EVENT_SUMMARY_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_TARGET_ECELL_EVENT_SUMMARY_ANALYSIS = "LTE_HANDOVER_FAILURE_TARGET_ECELL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_HANDOVER_STAGE_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_HANDOVER_STAGE_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_TARGET_ECELL_HANDOVER_STAGE_ANALYSIS = "LTE_HANDOVER_FAILURE_TARGET_ECELL_HANDOVER_STAGE_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ECELL_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_ECELL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ECELL_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_ECELL_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TERMINAL_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_TERMINAL_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CATEGORY_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_TERMINAL_HANDOVER_STAGE_ANALYSIS = "LTE_HFA_TERMINAL_HANDOVER_STAGE_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_TERMINAL_EVENT_SUMMARY_ANALYSIS = "LTE_HFA_TERMINAL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS = "LTE_HFA_TERMINAL_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_DROP_CATEGORY_ID = "0";

    public static final String LTE_CALL_SETUP_FAILURE_CATEGORY_ID = "1";

    public static final String EVENT_PARAM = "event";

    public static final String MEDIA_TYPE = "MEDIA_TYPE";

    public static final String REQUEST_URI = "REQUEST_URI";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_DETAILED_EVENT_ANALYSIS = "GSM_CALL_FAILURE_ACCESS_AREA_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_CALL_FAILURE_CONTROLLER_CAUSE_CODE_ANALYSIS = "GSM_CALL_FAILURE_CONTROLLER_CAUSE_CODE_ANALYSIS";

    public static final String GSM_CALL_FAILURE_CONTROLLER_SUB_CAUSE_CODE_ANALYSIS = "GSM_CALL_FAILURE_CONTROLLER_SUB_CAUSE_CODE_ANALYSIS";

    public static final String GSM_CALL_FAILURE_CAUSE_CODE_DETAILED_EVENT_ANALYSIS = "GSM_CALL_FAILURE_CAUSE_CODE_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_CALL_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS = "GSM_CALL_FAILURE_TERMINAL_DETAILED_EVENT_ANALYSIS";

    public static final String BSC = "BSC";

    public static final String CELL = "CELL";

    public static final String GSM_CALL_FAILURE_BSC_DETAILED_EVENT_ANALYSIS = "GSM_CALL_FAILURE_BSC_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_CALL_FAILURE_BSC_EVENT_ANALYSIS_SUMMARY = "GSM_CALL_FAILURE_BSC_EVENT_ANALYSIS_SUMMARY";

    public static final String GSM_SUBSCRIBER_CALL_FAILURE_CALL_DROP_RANKING_ANALYSIS = "GSM_SUBSCRIBER_CALL_FAILURE_CALL_DROP_RANKING_ANALYSIS";

    public static final String BSC_ID_PARAM = "bscId";

    public static final String LTE_CFA_EVENT_TIME_COLUMN_INDEX = "3";

    public static final String LTE_HFA_EVENT_TIME_COLUMN_INDEX = "3";

    public static final String LTE_CFA_ERAB_EVENT_TIME_COLUMN_INDEX = "1";

    public static final String LTE_HFA_ERAB_EVENT_TIME_COLUMN_INDEX = "1";

    public static final String LTE_CALL_FAILURE_ENODEB_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ECELL_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_ECELL_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_TARGET_ECELL_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_TARGET_ECELL_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_DETAILED_EVENT_ANALYSIS";

    public static final String DASHBOARD_HOMER_ROAMER_SUMMARY = "DASHBOARD_HOMER_ROAMER_SUMMARY";

    public static final String DASHBOARD_HOMER_ROAMER_SUMMARY_WCDMA = "DASHBOARD_HOMER_ROAMER_SUMMARY_WCDMA";

    public static final String DASHBOARD_TERMINAL_SUMMARY = "DASHBOARD_TERMINAL_SUMMARY";

    public static final String DASHBOARD_LTE_CFA_ENODEB_SUMMARY = "DASHBOARD_LTE_CFA_ENODEB_SUMMARY";

    public static final String DASHBOARD_LTE_CFA_ECELL_SUMMARY = "DASHBOARD_LTE_CFA_ECELL_SUMMARY";

    public static final String DASHBOARD_LTE_CFA_TERMINAL_SUMMARY = "DASHBOARD_LTE_CFA_TERMINAL_SUMMARY";

    public static final String DASHBOARD_LTE_CFA_TERMINAL_SUMMARY_DRILLDOWN = "DASHBOARD_LTE_CFA_TERMINAL_SUMMARY_DRILLDOWN";

    public static final String DASHBOARD_LTE_CFA_ACCESS_AREA_SUMMARY_DRILLDOWN = "DASHBOARD_LTE_CFA_ACCESS_AREA_SUMMARY_DRILLDOWN";

    public static final String DASHBOARD_LTE_HFA_ACCESS_AREA_SUMMARY_DRILLDOWN = "DASHBOARD_LTE_HFA_ACCESS_AREA_SUMMARY_DRILLDOWN";

    public static final String DASHBOARD_HFA_ECELL_SUMMARY = "DASHBOARD_HFA_ECELL_SUMMARY";

    public static final String DASHBOARD_TERMINAL_SUMMARY_DD = "DASHBOARD_TERMINAL_SUMMARY_DD";

    public static final String DASHBOARD_TERMINAL_SUMMARY_WCDMA = "DASHBOARD_TERMINAL_SUMMARY_WCDMA";

    public static final String DASHBOARD_TERMINAL_SUMMARY_WCDMA_DD_1 = "DASHBOARD_TERMINAL_SUMMARY_WCDMA_DD_1";

    public static final String GSM_ACCESS_AREA_CALL_FAILURE_EVENT_SUMMARY = "GSM_ACCESS_AREA_CALL_FAILURE_EVENT_SUMMARY";

    public static final String GSM_CALL_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS = "GSM_CALL_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_CALL_SETUP_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS = "GSM_CALL_SETUP_FAILURE_SUBSCRIBER_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_CALL_FAILURE_IMSI_GROUP_SUMMARY_BREAKDOWN = "GSM_CALL_FAILURE_IMSI_GROUP_SUMMARY_BREAKDOWN";

    public static final String GSM_CALL_FAILURE_CONTROLLER_GROUP_SUMMARY_BREAKDOWN = "GSM_CALL_FAILURE_CONTROLLER_GROUP_SUMMARY_BREAKDOWN";

    public static final String GSM_CALL_FAILURE_EVENT_SUMMARY_ANALYSIS = "GSM_CALL_FAILURE_EVENT_SUMMARY_ANALYSIS";

    public static final String GSM_CALL_FAILURE_SUBSCRIBER_SUMMARY_ANALYSIS = "GSM_CALL_FAILURE_SUBSCRIBER_SUMMARY_ANALYSIS";

    public static final String GSM_DATA_CONNECTION_SUBSCRIBER_DETAILED_EVENT_ANALYSIS = "GSM_DATA_CONNECTION_SUBSCRIBER_DETAILED_EVENT_ANALYSIS";

    public static final String GSM_DATA_CONNECTION_SUBSCRIBER_DETAILED_EVENT_ANALYSIS_GROUP = "GSM_DATA_CONNECTION_SUBSCRIBER_DETAILED_EVENT_ANALYSIS_GROUP";

    public static final String GSM_DATA_CONNECTION_SUBSCRIBER_GROUP_EVENT_SUMMARY = "GSM_DATA_CONNECTION_SUBSCRIBER_GROUP_EVENT_SUMMARY";

    public static final String GSM_DATA_CONNECTION_IMSI_RANKING = "GSM_DATA_CONNECTION_IMSI_RANKING";

    public static final int GSM_CALL_DROP_CATEGORY_ID_AS_INTEGER = Integer.parseInt(GSM_CALL_DROP_CATEGORY_ID);

    public static final int GSM_CALL_SETUP_FAILURE_CATEGORY_ID_AS_INTEGER = Integer
            .parseInt(GSM_CALL_SETUP_FAILURE_CATEGORY_ID);

    public static final String NODE = "NODE";

    public static final String CATEGORY_ID_ENODEB = "CATEGORY_ID_ENODEB";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_CAUSE_CODE_LIST_ANALYSIS = "GSM_CALL_FAILURE_ACCESS_AREA_CAUSE_CODE_LIST_ANALYSIS";

    public static final String GSM_CALL_FAILURE_CONTROLLER_CAUSE_CODE_LIST_ANALYSIS = "GSM_CALL_FAILURE_CONTROLLER_CAUSE_CODE_LIST_ANALYSIS";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_CAUSE_CODE_ANALYSIS = "GSM_CALL_FAILURE_ACCESS_AREA_CAUSE_CODE_ANALYSIS";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_SUB_CAUSE_CODE_ANALYSIS = "GSM_CALL_FAILURE_ACCESS_AREA_SUB_CAUSE_CODE_ANALYSIS";

    public static final String GSM_CALL_FAILURE_ACCESS_AREA_SUB_CAUSE_CODE_DETAIL_ANALYSIS = "GSM_CALL_FAILURE_ACCESS_AREA_SUB_CAUSE_CODE_DETAIL_ANALYSIS";

    public static final String GSM_CFA_COUNTRY_ROAMING_DETAIL_ANALYSIS = "GSM_CFA_COUNTRY_ROAMING_DETAIL_ANALYSIS";

    public static final String GSM_CFA_OPERATOR_ROAMING_DETAIL_ANALYSIS = "GSM_CFA_OPERATOR_ROAMING_DETAIL_ANALYSIS";

    // WCDMA HFA specific constants

    public static final String CATEGORY_TYPE_PARAM = "categoryType";

    public static final String CATEGORY_ID_SQL_PARAM = "CATEGORY_ID_2";

    public static final String CATEGORY_ID_2_DESC = "CATEGORY_ID_2_DESC";

    public static final String CELL_TYPE_FIXED_PARAM = "cellType";

    public static final String WCDMA_HFA_CONTROLLER_CAUSE_CODE_LIST = "WCDMA_HFA_CONTROLLER_CAUSE_CODE_LIST";

    public static final String WCDMA_HFA_CONTROLLER_CAUSE_CODE = "WCDMA_HFA_CONTROLLER_CAUSE_CODE";

    public static final String WCDMA_HFA_SUB_CAUSE_CODE = "WCDMA_HFA_SUB_CAUSE_CODE";

    public static final String WCDMA_HFA_CAUSE_CODE_TABLE = "WCDMA_HFA_CAUSE_CODE_TABLE";

    public static final String WCDMA_HFA_SUB_CAUSE_CODE_TABLE = "WCDMA_HFA_SUB_CAUSE_CODE_TABLE";

    public static final String SOURCE_GROUP_ID_PARAM = "sourceGroupId";

    public static final String TARGET_GROUP_ID_PARAM = "targetGroupId";

    public static final String WCDMA_HFA_STATE_PARAM = "state";

    public static final String WCDMA_HFA_SOURCE_GROUP_ID = "WCDMA_HFA_SOURCE";

    public static final String WCDMA_HFA_TARGET_GROUP_ID = "WCDMA_HFA_TARGET";

    public static final String WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID = "WCDMA_HFA_SOURCE_AND_TARGET";

    // LTE specific constants

    public static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_ECELL_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_CFA_HIER3_EVENTID_CC = "HIER3_CC";

    public static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_LIST = "LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_LIST";

    public static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_ANALYSIS_BY_PIE_CHART = "LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_ANALYSIS_BY_PIE_CHART";

    public static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_LIST = "LTE_CALL_FAILURE_ECELL_CAUSE_CODE_LIST";

    public static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_ANALYSIS_BY_PIE_CHART = "LTE_CALL_FAILURE_ECELL_CAUSE_CODE_ANALYSIS_BY_PIE_CHART";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_DETAILED_EVENT_ANALYSIS = "LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_LIST = "LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_LIST";

    public static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_ANALYSIS_BY_PIE_CHART = "LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_ANALYSIS_BY_PIE_CHART";

    public static final String LTE_CFA_EVENT_VOLUME = "LTE_CFA_EVENT_VOLUME";

    public static final String LTE_CALL_FAILURE_CAUSE_CODE_DATA = "causeCodeData";

    public static final String CAUSE_CODE_ID_LIST = "causeCodeIds";

    public static final String CAUSE_CODE_ID = "causeCode";

    public static final String CAUSE_CODE_LABEL_LIST_SOURCE = "causeCodeLabelsSource";

    public static final String CAUSE_CODE_LABEL_LIST_TARGET = "causeCodeLabelsTarget";

    public static final String LTE_CFA_CAUSE_CODE_ANALYSIS = "LTE_CFA_CAUSE_CODE_ANALYSIS";

    public static final String LTE_CFA_QOS_SUMMARY_ANALYSIS = "LTE_CFA_QOS_SUMMARY_ANALYSIS";

    public static final String LTE_CFA_QOS_DETAILED_EVENT_ANALYSIS = "LTE_CFA_QOS_DETAILED_EVENT_ANALYSIS";

    public static final String QCI_CATEGARY_SUMMARY = "QCI_CATEGARY_SUMMARY";

    public static final String QCI_EVENT_SUMMARY_TRAC = "QCI_EVENT_SUMMARY/TRAC";

    public static final String QCI_EVENT_SUMMARY_ECELL = "QCI_EVENT_SUMMARY/ECELL";

    public static final String QCI_EVENT_SUMMARY_ENODEB = "QCI_EVENT_SUMMARY/ENODEB";

    public static final String QCI_TRAC = "QCI/TRAC";

    public static final String QCI_ECELL = "QCI/ECELL";

    public static final String QCI_ENODEB = "QCI/ENODEB";

    public static final String CAUSE_CODE_ENODEB = "CAUSE_CODE_ENODEB";

    public static final String CAUSE_CODE_ACCESS_AREA = "CAUSE_CODE_ACCESS_AREA";

    public static final String CAUSE_CODE_TRACKING_AREA = "CAUSE_CODE_TRACKING_AREA";

    public static final String SUB_CAUSE_CODE_PIE_CHART = "SUB_CAUSE_CODE_PIE_CHART";

    public static final String DETAIL_SUB_CAUSE_CODE_GRID = "DETAIL_SUB_CAUSE_CODE_GRID";

    public static final String DETAIL_CC_SUB_CAUSE_CODE_GRID = "DETAIL_CC_SUB_CAUSE_CODE_GRID";

    public static final String DIM_E_GSM_PS_TBF_RELEASE_CAUSE = "DIM_E_GSM_PS_TBF_RELEASE_CAUSE";

    public static final String DIM_E_GSM_PS_TBF_MUX = "DIM_E_GSM_PS_TBF_MUX";

    public static final String DIM_E_GSM_PS_DATA_VALID_INDICATOR = "DIM_E_GSM_PS_DATA_VALID_INDICATOR";

    public static final String DIM_E_GSM_PS_MS_SAIC_CAP = "DIM_E_GSM_PS_MS_SAIC_CAP";

    public static final String DIM_E_GSM_PS_AQM_ACTIVE = "DIM_E_GSM_PS_AQM_ACTIVE";

    public static final String DIM_E_GSM_PS_DTM_FLAG = "DIM_E_GSM_PS_DTM_FLAG";

    public static final String DIM_E_GSM_PS_RLC_MODE = "DIM_E_GSM_PS_RLC_MODE";

    public static final String DIM_E_GSM_PS_DIR = "DIM_E_GSM_PS_DIR";

    public static final String DIM_E_GSM_PS_OFLW = "DIM_E_GSM_PS_OFLW";

    public static final String DIM_E_GSM_PS_MS_3GPP_CAP = "DIM_E_GSM_PS_MS_3GPP_CAP";

    public static final String DIM_E_GSM_PS_TTI_MODE = "DIM_E_GSM_PS_TTI_MODE";

    public static final String DIM_E_GSM_PS_REDUCED_LATENCY = "DIM_E_GSM_PS_REDUCED_LATENCY";

    public static final String DIM_E_GSM_PS_GPRS_MEAS_REPORT_RXQUAL_DL = "DIM_E_GSM_PS_GPRS_MEAS_REPORT_RXQUAL_DL";

    public static final String DIM_E_GSM_PS_GPRS_MEAS_REPORT_SIGN_VAR = "DIM_E_GSM_PS_GPRS_MEAS_REPORT_SIGN_VAR";

    public static final String DIM_E_GSM_PS_GPRS_MEAS_REPORT_MEAN_BEP = "DIM_E_GSM_PS_GPRS_MEAS_REPORT_MEAN_BEP";

    public static final String DIM_E_GSM_PS_GPRS_MEAS_REPORT_CV_BEP = "DIM_E_GSM_PS_GPRS_MEAS_REPORT_CV_BEP";

    public static final String DIM_E_GSM_PS_GPRS_MEAS_REPORT_CVALUE = "DIM_E_GSM_PS_GPRS_MEAS_REPORT_CVALUE";

    public static final String DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_850 = "DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_850";

    public static final String DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_900 = "DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_900";

    public static final String DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_1800 = "DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_1800";

    public static final String DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_1900 = "DIM_E_GSM_PS_MS_FREQ_BAND_CAP_GSM_1900";

    public static final String DIM_E_GSM_PS_PAN_INDICATOR = "DIM_E_GSM_PS_PAN_INDICATOR";

    public static final String DIM_E_GSM_PS_TBF_MODE = "DIM_E_GSM_PS_TBF_MODE";

    public static final String DIM_E_GSM_PS_CHANNEL_RELATED_RELEASE_CAUSE_GROUP = "DIM_E_GSM_PS_CHANNEL_RELATED_RELEASE_CAUSE_GROUP";

    public static final String DIM_E_GSM_PS_DCDL_CAPABILITY = "DIM_E_GSM_PS_DCDL_CAPABILITY";

    public static final String DIM_E_GSM_PS_DCDL_INDICATOR = "DIM_E_GSM_PS_DCDL_INDICATOR";

    public static final String DIM_E_GSM_PS_MS_MSLOT_CAP_REDUCTION = "DIM_E_GSM_PS_MS_MSLOT_CAP_REDUCTION";

    public static final String DIM_E_GSM_PS_EVENTTYPE = "DIM_E_GSM_PS_EVENTTYPE";

    public static final String GROUP_TYPE_E_IMSI = "GROUP_TYPE_E_IMSI";

    // Key value for DV drill down
    public static final String KEY_TYPE_DV_DRILL_DOWN = "DV_DRILL_DOWN";

    public static final String TO_SELECT_RANDOM_TABLE = "toSelectRandomTable";

    // HFA specific constants

    public static final String LTE_HANDOVER_FAILURE_SUBSCRIBER_EXEC_RANKING_ANALYSIS = "LTE_HANDOVER_FAILURE_SUBSCRIBER_EXEC_RANKING_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_SUBSCRIBER_PREP_RANKING_ANALYSIS = "LTE_HANDOVER_FAILURE_SUBSCRIBER_PREP_RANKING_ANALYSIS";

    public static final String LTE_HFA_EXEC_CATEGORY_ID = "1";

    public static final String LTE_HFA_PREP_CATEGORY_ID = "0";

    public static final String ACCESS_AREA_GROUP = "ACCESS_AREA_GROUP";

    public static final String LTE_HANDOVER_FAILURE_ECELL_GROUP_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_GROUP_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_SOURCECELL_EXEC_RANKING_ANALYSIS = "LTE_HANDOVER_FAILURE_SOURCECELL_EXEC_RANKING_ANALYSIS";

    public static final String LTE_HFA_CAUSE_CODE_ANALYSIS = "LTE_HFA_CAUSE_CODE_ANALYSIS";

    public static final String LTE_HFA_CAUSE_CODE_DATA = "causeCodeData";

    public static final String LTE_HFA_ECELL_CAUSE_CODE_ANALYSIS_BY_PIE_CHART = "LTE_HFA_ECELL_CAUSE_CODE_ANALYSIS_BY_PIE_CHART";

    public static final String LTE_HFA_ECELL_CAUSE_CODE_LIST = "LTE_HFA_ECELL_CAUSE_CODE_LIST";

    public static final String LTE_HFA_ECELL_CAUSE_CODE_SUMMARY_ANALYSIS = "LTE_HFA_ECELL_CAUSE_CODE_SUMMARY_ANALYSIS";

    public static final String LTE_HFA_ECELL_CAUSE_CODE_HANDOVER_STAGE_ANALYSIS = "LTE_HFA_ECELL_CAUSE_CODE_HANDOVER_STAGE_ANALYSIS";

    public static final String LTE_HFA_ENODEB_CAUSE_CODE_ANALYSIS_BY_PIE_CHART = "LTE_HFA_ENODEB_CAUSE_CODE_ANALYSIS_BY_PIE_CHART";

    public static final String LTE_HFA_ENODEB_CAUSE_CODE_HANDOVER_STAGE_ANALYSIS = "LTE_HFA_ENODEB_CAUSE_CODE_HANDOVER_STAGE_ANALYSIS";

    public static final String LTE_HFA_HIER3_EVENTID_CC = "HIER3_CC";

    public static final String LTE_HFA_ENODEB_CAUSE_CODE_LIST = "LTE_HFA_ENODEB_CAUSE_CODE_LIST";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_HFA_RANKING = "LTE_HANDOVER_FAILURE_RANKING_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_CAUSE_CODE_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTE_HANDOVER_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE";

    public static final String LTE_HFA_TARGET_CELL_PREP = "TARGETCELL_PREP";

    public static final String LTE_HFA_SOURCE_CELL_EXEC = "SOURCECELL_EXEC";

    public static final String LTE_HFA_SOURCE_CELL_PREP = "SOURCECELL_PREP";

    public static final String LTE_HFA_TARGET_CELL_EXEC = "TARGETCELL_EXEC";

    public static final String LTE_HFA_CAUSE_CODE_RANKING_EXEC_ANALYSIS = "LTE_HFA_CAUSE_CODE_RANKING_EXEC_ANALYSIS";

    public static final String LTE_HFA_CAUSE_CODE_RANKING_PREP_ANALYSIS = "LTE_HFA_CAUSE_CODE_RANKING_PREP_ANALYSIS";

    public static final String LTE_HFA_CAUSE_CODE_PREP = "CAUSE_CODE_PREP";

    public static final String LTE_HFA_CAUSE_CODE_EXEC = "CAUSE_CODE_EXEC";

    public static final String LTE_HANDOVER_FAILURE_ENODEB_EVENT_VOLUME_ANALYSIS = "LTE_HANDOVER_FAILURE_ENODEB_EVENT_VOLUME_ANALYSIS";

    public static final String LTE_HANDOVER_FAILURE_ECELL_EVENT_VOLUME_ANALYSIS = "LTE_HANDOVER_FAILURE_ECELL_EVENT_VOLUME_ANALYSIS";

    public static final String LTE_HFA_EVENT_VOLUME = "LTE_HFA_EVENT_VOLUME";

    public static final String DETAIL_CAUSE_CODE_GRID = "DETAIL_CAUSE_CODE_GRID";

    public static final String LTE_HANDOVER_FAILURE_NETWORK_EVENT_VOLUME_ANALYSIS = "LTE_HANDOVER_FAILURE_NETWORK_EVENT_VOLUME_ANALYSIS";

    public static final String LTE_HFA_QOS_QCI_ENODEB_EVENT_SUMMARY_ANALYSIS = "LTE_HFA_QOS_QCI_ENODEB_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HFA_QOS_QCI_ENODEB_CATEGORY_SUMMARY_ANALYSIS = "LTE_HFA_QOS_QCI_ENODEB_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_HFA_QOS_QCI_ENODEB_DETAILED_ANALYSIS = "LTE_HFA_QOS_QCI_ENODEB_DETAILED_ANALYSIS";

    public static final String LTE_HFA_QOS_SUMMARY_ANALYSIS = "LTE_HFA_QOS_SUMMARY_ANALYSIS";

    public static final String LTE_HFA_QOS_DETAILED_EVENT_ANALYSIS = "LTE_HFA_QOS_DETAILED_EVENT_ANALYSIS";

    public static final String HIERARCHY_3_SQL = "HIERARCHY_3";

    public static final String LTE_HFA_QOS_QCI_ECELL_CATEGORY_SUMMARY_ANALYSIS = "LTE_HFA_QOS_QCI_ECELL_CATEGORY_SUMMARY_ANALYSIS";

    public static final String LTE_HFA_QOS_QCI_ECELL_EVENT_SUMMARY_ANALYSIS = "LTE_HFA_QOS_QCI_ECELL_EVENT_SUMMARY_ANALYSIS";

    public static final String LTE_HFA_QOS_QCI_ECELL_DETAILED_ANALYSIS = "LTE_HFA_QOS_QCI_ECELL_DETAILED_ANALYSIS";

    public static final String ECELL = "ECELL";

    public static final String LTE_HFA_TAC_RANKING_ANALYSIS = "LTE_HFA_TAC_RANKING_ANALYSIS";

    public static final String TYPE_TAC_EXEC = "TAC_EXEC";

    public static final String TYPE_TAC_PREP = "TAC_PREP";

    public static final String LTE_HFA_EVENT_SUMMARY = "LTE_HFA_EVENT_SUMMARY";

    public static final String LTE_HFA_DETAILED_EVENT_ANALYSIS = "LTE_HFA_DETAILED_EVENT_ANALYSIS";

    // SESSION BROWSER ONLY
    public static final String SESSION_BROWSER = "SESSION_BROWSER";

    public static final String SESSION_SUMMARY = "SESSION_SUMMARY";

    public static final String SESSION_REPORTS = "SESSION_REPORTS";

    public static final String USER_PLANE = "USER_PLANE";

    public static final String SESSION_DETAIL = "SESSION_DETAIL";

    public static final String SESSION_KPI = "SESSION_KPI";

    public static final String SESSION_POPUP_DETAIL = "SESSION_POPUP_DETAIL";

    public static final String RAB = "RAB";

    public static final String VISITED_CELLS = "VISITED_CELLS";

    public static final String CELL_VISITED = "CELL_VISITED";

    public static final String TCP_PERFORMANCE = "TCP";

    public static final String APPLICATION_LAYER_PERF = "CLASSIFICATION";

    public static final String TCP_PERFORMANCE_DETAILS = "TCP_PERFORMANCE_DETAILS";

    public static final String APPLICATION_LAYER_DETAILS = "APPLICATION_LAYER_DETAILS";

    public static final String TCP_PERFORMANCE_SUMMARY = "TCP_PERFORMANCE_SUMMARY";

    public static final String APPLICATION_LAYER_SUMMARY = "APPLICATION_LAYER_SUMMARY";

    public static final String CORE = "CORE";

    public static final String REPORTS = "REPORTS";

    public static final String RAN_CFA = "RAN" + PATH_SEPARATOR + "CFA";

    public static final String RAN_HFA = "RAN" + PATH_SEPARATOR + "HFA";

    public static final String RAN_SESSION_HFA = "RAN" + PATH_SEPARATOR + "SESSION" + PATH_SEPARATOR + "HFA";

    public static final String POPUP_DETAIL = "POPUP_DETAIL";

    public static final String PROPERTIES = "PROPERTIES";

    public static final String WEB_SERVER_DIST = "WEB_SERVER_DISTRIBUTION";

    public static final String EVENT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String EVENT_TIME_FROM_PARAM = "eventTimeFrom";

    public static final String EVENT_TIME_TO_PARAM = "eventTimeTo";

    public static final String EVENT_TIME_FROM_LOCAL_PARAM = "eventTimeFromLocal";

    public static final String EVENT_TIME_TO_LOCAL_PARAM = "eventTimeToLocal";

    public static final String KPI_CHART = "KPI_CHART";

    public static final String KPI_MAP = "KPI_MAP";

    public static final String KPI_DRILL = "KPI_DRILL";

    public static final String MAKE_MODEL = "MAKE_MODEL";

    public static final String HS_CATEGORY = "HS_CATEGORY";

    public static final String EUL_CATEGORY = "EUL_CATEGORY";

    public static final String START_CELL = "START_CELL_ID";

    public static final String END_CELL = "END_CELL_ID";

    public static final String HS_RATIO = "HS_RATIO";

    public static final String EUL_RATIO = "EUL_RATIO";

    public static final String START_RAB = "START_RAB";

    public static final String END_RAB = "END_RAB";

    public static final String DCH_ACT = "DCH_ACTIVITY";

    public static final String ECNO = "ECNO_AVG";

    public static final String RSCP = "RSCP_AVG";

    public static final String UL_INER = "UL_INTERFERENCE_AVG";

    public static final String HS_USER = "HSDSCH_AVG_USERS";

    public static final String DL_PWR = "DL_NON_HS_TX_POWER_AVG";

    public final static String REPORT_DATEFORMAT = "REPORT_DATEFORMAT";

    public final static String KPI_ID = "kpiId";

    public final static String RRC_MEAS = "RRC_MEAS";

    public final static String RADIO_AND_CELL_CONDITION = "RCC";

    public final static String ADDITIONAL_EVENT_ID = "additionalEventId";

    public final static String KPI_START_TIME = "kpiStartTime";

    public final static String KPI_END_TIME = "kpiEndTime";

    //end of session browser

    public static final String FILTER_TYPE = "filterType";

    public static final String FILTER_VALUE = "filterValue";

    public static final String DISCONNECTION_CODE = "disConCode";

    public static final String DISCONNECTION_SUB_CODE = "disconnectionSubCode";

    public static final String RAB_TYPE = "rabType";

    public static final String UNKNOWN = "unknown";

    public static final String WCDMA_CFA_NETWORK_DETAILED_EVENT_ANALYSIS_BY_FILTER = "WCDMA_CFA_NETWORK_DETAILED_EVENT_ANALYSIS_BY_FILTER";

    public static final String WCDMA_CFA_TERMINAL_DETAILED_EVENT_ANALYSIS_BY_FILTER = "WCDMA_CFA_TERMINAL_DETAILED_EVENT_ANALYSIS_BY_FILTER";

    public static final String NODE_BY_FILTER = "NODE_BY_FILTER";

    public static final String TERMINAL_BY_FILTER = "TERMINAL_BY_FILTER";

    public static final String NODE_BY_DISCONNECTION = "NODE_BY_DISCONNECTION";

    public static final String WCDMA_CFA_CONTROLLER_DISCONNECTION = "WCDMA_CFA_CONTROLLER_DISCONNECTION";

    public static final String WCDMA_CFA_CELL_DISCONNECTION = "WCDMA_CFA_CELL_DISCONNECTION";

    public static final String WCDMA_CFA_TERMINAL_DISCONNECTION = "WCDMA_CFA_TERMINAL_DISCONNECTION";

    public static final String WCDMA_CFA_CONTROLLER_RAB_TYPE = "WCDMA_CFA_CONTROLLER_RAB_TYPE";

    public static final String WCDMA_CFA_CONTROLLER_RAB_DESC = "WCDMA_CFA_CONTROLLER_RAB_DESC";

    public static final String WCDMA_CFA_CELL_RAB_TYPE = "WCDMA_CFA_CELL_RAB_TYPE";

    public static final String WCDMA_CFA_CELL_RAB_DESC = "WCDMA_CFA_CELL_RAB_DESC";

    public static final String WCDMA_CFA_TERMINAL_RAB_TYPE = "WCDMA_CFA_TERMINAL_RAB_TYPE";

    public static final String WCDMA_CFA_TERMINAL_RAB_DESC = "WCDMA_CFA_TERMINAL_RAB_DESC";

    public static final String WCDMA_NETWORK_CALL_FAILURE_FILTER_ANALYSIS = "WCDMA_NETWORK_CALL_FAILURE_FILTER_ANALYSIS";

    public static final String WCDMA_TERMINAL_CALL_FAILURE_FILTER_ANALYSIS = "WCDMA_TERMINAL_CALL_FAILURE_FILTER_ANALYSIS";

    public static final String WCDMA_NETWORK_CALL_FAILURE_ANALYSIS = "WCDMA_NETWORK_CALL_FAILURE_ANALYSIS";

    public static final String WCDMA_TERMINAL_CALL_FAILURE_ANALYSIS = "WCDMA_TERMINAL_CALL_FAILURE_ANALYSIS";

    public static final String ALL_CALLS_AGGREGATION_VIEW = "allCallsAggregationView";

    public static final String WCDMA_CFA_DRILL_CATEGORY = "drillCat";

    public static final String HIER3_EVENTID_RABTYPE = "HIER3_EVENTID_RABTYPE";

    public static final String HIER3_CELL_ID_EVENTID_RABTYPE = "HIER3_CELL_ID_EVENTID_RABTYPE";

    public final static String NO_OF_CALLS = "NO_OF_CALLS";

    public final static String CALLS_CS_ALL = "CALLS_CS_ALL";

    public final static String CALLS_PS_ALL = "CALLS_PS_ALL";

    public final static String CALLS_MR_ALL = "CALLS_MR_ALL";

    public final static String ELEMENT_ID = "ELEMENT_ID";

    public static final String KEY_TYPE_ALLCALLS = "ALLCALLS";

    public final static int NO_OF_CS_CALLS = 400;

    public final static int NO_OF_PS_CALLS = 400;

    public final static int NO_OF_MR_CALLS = 200;

    private static final ApplicationConstants instance = new ApplicationConstants();

    public static ApplicationConstants getInstance() {
        return instance;
    }

    private ApplicationConstants() {
    }

    public static String returnDataTieringViewType(final String timeRange) {
        if (timeRange != null) {
            if (timeRange.equals(EventDataSourceType.AGGREGATED_1MIN.getValue())) {
                return "_TIERED";
            } else if (timeRange.equals(EventDataSourceType.AGGREGATED_15MIN.getValue())) {
                return FIFTEEN_MINUTES_VIEW;
            } else if (timeRange.equals(EventDataSourceType.AGGREGATED_DAY.getValue())) {
                return DAY_VIEW;
            } else {
                return "_TIERED";
            }
        }
        return "_TIERED";
    }

    public static String returnAggregateViewType(final String timeRange) {
        if (timeRange != null) {
            if (timeRange.equals(EventDataSourceType.AGGREGATED_1MIN.getValue())) {
                return ONE_MINUTE_VIEW;
            } else if (timeRange.equals(EventDataSourceType.AGGREGATED_15MIN.getValue())) {
                return FIFTEEN_MINUTES_VIEW;
            } else if (timeRange.equals(EventDataSourceType.AGGREGATED_DAY.getValue())) {
                return DAY_VIEW;
            } else {
                return RAW_VIEW;
            }
        }
        return RAW_VIEW;
    }

    public static String returnAggregateViewTypeWithOutOneMinute(final String timeRange) {
        if (timeRange != null) {
            if (timeRange.equals(EventDataSourceType.AGGREGATED_15MIN.getValue())) {
                return FIFTEEN_MINUTES_VIEW;
            } else if (timeRange.equals(EventDataSourceType.AGGREGATED_DAY.getValue())) {
                return DAY_VIEW;
            } else {
                return RAW_VIEW;
            }
        }
        return RAW_VIEW;
    }

    public static String getTypeOfColumn(final String columnName) {
        return TechPackData.getTypeOfColumn(columnName);
    }

    public static String getRATForTechPackTable(final String techPackTable) {
        return TechPackData.getRATForTechPackTable(techPackTable);
    }

    public static String getRATForTechPackTable(final List<String> techPackTables) {
        return TechPackData.getRATForTechPackTable(techPackTables);

    }

}
