/**
 * -----------------------------------------------------------------------
h *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.schema;

import java.util.HashMap;
import java.util.Map;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * Holds the usual column types for various columns (the column types are gotten from viewing the database
 * schema on a blade)
 *
 * @author eemecoy
 *
 */
public class ColumnTypes {

    private ColumnTypes() {
    }

    private static final String EVENT_ID = "EVENT_ID";

    private static final String EVENT_ID_LABEL = "EVENT_ID_LABEL";

    private static final String DATETIME_ID = "DATETIME_ID";

    private static final String APN = "APN";

    private static final String VENDOR = "VENDOR";

    private static final String IMSI = "IMSI";

    private static final String TABLENAME = "TABLENAME";

    private static final String RNC_ID = "RNC_ID";

    private static final String EXTENDED_CAUSE_VALUE_DESC = "EXTENDED_CAUSE_VALUE_DESC";

    private static final String SEVERITY_INDICATOR_ID = "SEVERITY_INDICATOR_ID";

    private static final String SEVERITY_INDICATOR_DESC = "SEVERITY_INDICATOR_DESC";

    private static final String TAC = "TAC";

    private static final String RNCID = "rncId";

    private static final String EVENT_ID_DESC = "EVENT_ID_DESC";

    private static final String COUNTRY = "COUNTRY";

    private static final String OPERATOR = "OPERATOR";

    private static final String HIER3_CELL_ID = "HIER3_CELL_ID";

    private static final String THIER3_CELL_ID = "THIER3_CELL_ID";

    private static final String PERCEIVED_SEVERITY = "perceivedSeverity";

    private static final String HIER3_ID = "HIER3_ID";

    private static final String HIER321_ID = "HIER321_ID";

    private static final String THIER321_ID = "THIER321_ID";

    private static String ARP_PCI = "ARP_PCI";

    private static String BEARER_CAUSE = "BEARER_CAUSE";

    private static String BEARER_CAUSE_DESC = "BEARER_CAUSE_DESC";

    private static final String COMBINED_TAU_TYPE_DESC = "COMBINED_TAU_TYPE_DESC";

    private static final String EVENT_SUBTYPE_ID_DESC = "EVENT_SUBTYPE_ID_DESC";

    private static final String PDNDISCONNECTTYPE_DESCRIPTION = "PDNDISCONNECTTYPE_DESCRIPTION";

    private static final String SMS_ONLY_DESC = "SMS_ONLY_DESC";

    private static final String PDNDISCONNECTTYPE = "PDNDISCONNECTTYPE";

    private static final String ARP_PVI = "ARP_PVI";

    private static final String BAND = "BAND";

    private static final String ACCESS_AREA_ID = "ACCESS_AREA_ID";

    private static final String SAC = "SAC";

    private static String ARP_PCI_DESC = "ARP_PCI_DESC";

    private static String ARP_PVI_DESC = "ARP_PVI_DESC";

    private static final String THIER3_ID = "THIER3_ID";

    private static final String DATE_ID = "DATE_ID";

    private static final String CATEGORY_ID = "CATEGORY_ID";

    private static final String CATEGORY_ID_DESC = "CATEGORY_ID_DESC";

    private static final String CATEGORY_ID_2 = "CATEGORY_ID_2";

    private static final String CATEGORY_ID_2_DESC = "CATEGORY_ID_2_DESC";

    private static final String GROUP_NAME = "GROUP_NAME";

    public static final String RSCP_AVG = "RSCP_AVG";

    public static final String ECNO_AVG = "ECNO_AVG";

    public static final String UL_INTERFERENCE_AVG = "UL_INTERFERENCE_AVG";

    public static final String HSDSCH_AVG_USERS = "HSDSCH_AVG_USERS";

    private static String CATEGORY_TABLE_NAME = "CATEGORY_TABLE_NAME";

    private static String CELLO_AAL2NCI_REJECT_REASON = "CELLO_AAL2NCI_REJECT_REASON";

    private static String CELLO_AAL2NCI_REJECT_DESC = "CELLO_AAL2NCI_REJECT_DESC";

    private static String ORIGINATING_STATE = "ORIGINATING_STATE";

    private static String ORIGINATING_STATE_DESC = "ORIGINATING_STATE_DESC";

    private static String SCRAMBLING_CODE_ADDED_CELL = "SCRAMBLING_CODE_ADDED_CELL";

    private static String RSCP_CELL_1_ADDED_CELL = "RSCP_CELL_1_ADDED_CELL";

    private static String CPICH_EC_NO_ADDED_CELL = "CPICH_EC_NO_ADDED_CELL";

    private static String WANTED_CONF = "WANTED_CONF";

    private static String SERVING_AREA = "SERVING_AREA";

    private static String CONTROLLER = "CONTROLLER";

    private static String PLMN = "PLMN";

    private static String EVENT_TYPE = "EVENT_TYPE";

    private static String TERMINAL = "TERMINAL";

    private static String NETWORK = "NETWORK";

    private static String ROUTING_AREA = "ROUTING_AREA";

    private static String SGSN = "SGSN";

    private static String CELL = "CELL";

    private static String ELEMENT_ID = "ELEMENT_ID";

    private static String CALLS_CS_ALL = "CALLS_CS_ALL";

    private static String CALLS_PS_ALL = "CALLS_PS_ALL";

    private static String CALLS_MR_ALL = "CALLS_MR_ALL";

    private static String PS_SUC = "PS_SUC";

    private static String CS_SUC = "CS_SUC";

    private static String MR_SUC = "MR_SUC";

    private static String CATEGORY_ID_VALUE = "CATEGORY_ID_VALUE";

    private static Map<String, String> columnTypes = new HashMap<String, String>();

    private static Map<String, Object> defaultValuesForColumnTypes = new HashMap<String, Object>();

    static {

        defaultValuesForColumnTypes.put(DATE, "2012-01-22");
        defaultValuesForColumnTypes.put(INT, 0);
        defaultValuesForColumnTypes.put(VARCHAR_12, "");
        defaultValuesForColumnTypes.put(VARCHAR_255, "");

        columnTypes.put(BAND, VARCHAR_255);
        columnTypes.put(UTC_DATETIME_ID, TIMESTAMP);
        columnTypes.put(Event_Date, TIMESTAMP);
        columnTypes.put(CURRENTDATE, DATE);
        columnTypes.put(CURRENTMINUTE, INT);
        columnTypes.put(CURRENTHOUR, INT);
        columnTypes.put(ADDITIONALTEXT, VARCHAR_12);
        columnTypes.put(ADDITIONALINFORMATION, VARCHAR_12);
        columnTypes.put(MONITOREDATTRIBUTEVALUES, VARCHAR_12);
        columnTypes.put(MONITOREDATTRIBUTES, VARCHAR_255);
        columnTypes.put(THRESHOLDINFORMATION, VARCHAR_12);
        columnTypes.put(MANAGEDOBJECTCLASS, VARCHAR_12);
        columnTypes.put(MANAGEDOBJECTINSTANCE, VARCHAR_12);
        columnTypes.put(PERCEIVED_SEVERITY_TEXT, VARCHAR_12);
        columnTypes.put(PERCEIVED_SEVERITY, INT);

        columnTypes.put(NO_OF_ERRORS, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_SUCCESSES, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_PAGING_ATTEMPTS, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_TOTAL_ERR_SUBSCRIBERS, UNSIGNED_BIGINT);
        columnTypes.put(EVENT_ID, SMALLINT);
        columnTypes.put(EVENT_ID_LABEL, VARCHAR_255);
        columnTypes.put(EVENT_SOURCE_NAME, VARCHAR_128);
        columnTypes.put(TAC, UNSIGNED_INT);
        columnTypes.put(EVENT_TIME, TIMESTAMP);
        columnTypes.put(DATETIME_ID, TIMESTAMP);
        columnTypes.put(IMSI, UNSIGNED_BIGINT);
        columnTypes.put(PTMSI, UNSIGNED_INT);
        columnTypes.put(ACCESS_AREA_ID, UNSIGNED_INT);
        columnTypes.put(SAC, UNSIGNED_INT);
        columnTypes.put(RAT, TINYINT);
        columnTypes.put(APN, VARCHAR_127);
        columnTypes.put(IMSI_MCC, VARCHAR_127);
        columnTypes.put(IMSI_MNC, VARCHAR_127);
        columnTypes.put(ROAMING, INT);
        columnTypes.put(HIERARCHY_1, VARCHAR_128);
        columnTypes.put(HIERARCHY_2, VARCHAR_128);
        columnTypes.put(HIERARCHY_3, VARCHAR_128);
        columnTypes.put(VENDOR, VARCHAR_20);
        columnTypes.put(TRAC, UNSIGNED_INT);
        columnTypes.put(OLD_TRAC, UNSIGNED_INT);
        columnTypes.put(OLD_CELL_ID, UNSIGNED_INT);
        columnTypes.put(OLD_MCC, VARCHAR_3);
        columnTypes.put(OLD_MNC, VARCHAR_3);
        columnTypes.put(OLD_L_MMEGI, INT);
        columnTypes.put(OLD_L_MMEC, SMALLINT);
        columnTypes.put(OLD_L_MTMSI, UNSIGNED_BIGINT);
        columnTypes.put(OLD_SGW_IPV4, BINARY);
        columnTypes.put(OLD_SGW_IPV6, BINARY);
        columnTypes.put(PDN_BEARER_ID_1, SMALLINT);
        columnTypes.put(PDN_BEARER_ID_2, SMALLINT);
        columnTypes.put(PDN_BEARER_ID_3, SMALLINT);
        columnTypes.put(PDN_PAA_IPV4_1, BINARY);
        columnTypes.put(PDN_PAA_IPV4_2, BINARY);
        columnTypes.put(PDN_PAA_IPV4_3, BINARY);
        columnTypes.put(PDN_PAA_IPV6_1, BINARY);
        columnTypes.put(PDN_PAA_IPV6_2, BINARY);
        columnTypes.put(PDN_PAA_IPV6_3, BINARY);
        columnTypes.put(PDN_PGW_IPV4_1, BINARY);
        columnTypes.put(PDN_PGW_IPV4_2, BINARY);
        columnTypes.put(PDN_PGW_IPV4_3, BINARY);
        columnTypes.put(PDN_PGW_IPV6_1, BINARY);
        columnTypes.put(PDN_PGW_IPV6_2, BINARY);
        columnTypes.put(PDN_PGW_IPV6_3, BINARY);
        columnTypes.put(PAGING_ATTEMPTS, TINYINT);
        columnTypes.put(REQUEST_RETRIES, TINYINT);
        columnTypes.put(MCC, VARCHAR_127);
        columnTypes.put(MNC, VARCHAR_127);
        columnTypes.put(ARP_PL_1, TINYINT);
        columnTypes.put(ARP_PL_2, TINYINT);
        columnTypes.put(ARP_PL_3, TINYINT);
        columnTypes.put(_TAC, UNSIGNED_INT);

        columnTypes.put(FIVE_MIN_AGG_TIME, TIMESTAMP);
        columnTypes.put(START_APN, VARCHAR);
        columnTypes.put(END_APN, VARCHAR);
        columnTypes.put(ACTIVITY_START_TIME, TIMESTAMP);
        columnTypes.put(UE_IP_ADDRESS, BINARY);
        columnTypes.put(DOWNLINK, BIT);
        columnTypes.put(DATA_RECEIVED, UNSIGNED_BIGINT);
        columnTypes.put(SETUP_TIME_TERM, UNSIGNED_INT);
        columnTypes.put(SETUP_TIME_NET, UNSIGNED_INT);

        columnTypes.put(PACKET_LOSS_TERM, REAL_TYPE);
        columnTypes.put(PACKET_LOSS_NET, REAL_TYPE);
        columnTypes.put(APN_ACTIVITY_LATEST_TIME, TIMESTAMP);
        columnTypes.put(APN_ACTIVITY_START_TIME, TIMESTAMP);
        columnTypes.put(RAU_ATT_CNT, SMALLINT);
        columnTypes.put(RAU_SUC_CNT, SMALLINT);
        columnTypes.put(SGSN_ACTIVITY_START_TIME, TIMESTAMP);
        columnTypes.put(SGSN_ACTIVITY_LATEST_TIME, TIMESTAMP);
        columnTypes.put(EUL_UE_CATEGORY, SMALLINT);
        columnTypes.put(HSDPA_UE_CATEGORY, SMALLINT);
        columnTypes.put(START_RAB_COLUMN, SMALLINT);
        columnTypes.put(END_RAB_COLUMN, SMALLINT);
        columnTypes.put(CM_CNT, UNSIGNED_INT);
        columnTypes.put(CM_DURATION, UNSIGNED_INT);
        columnTypes.put(CM_UL_CNT, UNSIGNED_INT);
        columnTypes.put(CM_DL_CNT, UNSIGNED_INT);
        columnTypes.put(CM_ULDL_CNT, UNSIGNED_INT);
        columnTypes.put(CM_USER_CNT, UNSIGNED_INT);
        columnTypes.put(RRC_CONN_START, TIMESTAMP);
        columnTypes.put(PS_RAB_ACTIVITY_START_TIME, TIMESTAMP);
        columnTypes.put(RRC_CONN_END, TIMESTAMP);
        columnTypes.put(PS_RAB_ACTIVITY_END_TIME, TIMESTAMP);
        columnTypes.put(RRC_CONNECTION_CNT, TINYINT);
        columnTypes.put(PS_RAB_ESTABLISH_CNT, SMALLINT);
        columnTypes.put(START_HIER3_ID, UNSIGNED_BIGINT);
        columnTypes.put(END_HIER3_ID, UNSIGNED_BIGINT);
        columnTypes.put(START_CELL_ID, VARCHAR);
        columnTypes.put(END_CELL_ID, VARCHAR);
        columnTypes.put(START_C_ID, UNSIGNED_INT);
        columnTypes.put(END_C_ID, UNSIGNED_INT);
        columnTypes.put(RRC_MEAS_REP_SAMPLES, UNSIGNED_INT);
        columnTypes.put(RRC_SAMPLES_GC_BS, UNSIGNED_INT);
        columnTypes.put(RRC_SAMPLES_GC_GS, UNSIGNED_INT);
        columnTypes.put(RRC_SAMPLES_BC_BS, UNSIGNED_INT);
        columnTypes.put(RRC_SAMPLES_BC_GS, UNSIGNED_INT);
        columnTypes.put(RSCP_AVG, INT);
        columnTypes.put(UL_INTERFERENCE_AVG, FLOAT);
        columnTypes.put(DL_NON_HS_TX_POWER_AVG, UNSIGNED_INT);
        columnTypes.put(HSDSCH_AVG_USERS, SMALLINT);
        columnTypes.put(DISTINCT_CELL_CNT, UNSIGNED_INT);
        columnTypes.put(IFHO_EXEC_SUC_CNT, UNSIGNED_INT);
        columnTypes.put(IFHO_EXEC_ERR_CNT, UNSIGNED_INT);
        columnTypes.put(ECNO_AVG, FLOAT);
        columnTypes.put(SOHO_EXEC_SUC_CNT, UNSIGNED_INT);
        columnTypes.put(SOHO_EXEC_ERR_CNT, UNSIGNED_INT);
        columnTypes.put(IRAT_EXEC_ERR_CNT, UNSIGNED_INT);
        columnTypes.put(HS_CELL_CHANGE_SUC_CNT, UNSIGNED_INT);
        columnTypes.put(HS_CELL_CHANGE_ERR_CNT, UNSIGNED_INT);
        columnTypes.put(ACTIVITY, FLOAT);
        columnTypes.put(CUS_SUCC_COUNT, SMALLINT);
        columnTypes.put(CDS_SUCC_COUNT, SMALLINT);
        columnTypes.put(CUS_ATT_CNT, SMALLINT);
        columnTypes.put(CDS_ATT_CNT, SMALLINT);
        columnTypes.put(CS_REASON_UE_ACTIVITY, SMALLINT);
        columnTypes.put(CS_REASON_CAPACITY, SMALLINT);
        columnTypes.put(CS_REASON_MOBILITY_COVERAGE, SMALLINT);
        columnTypes.put(CS_REASON_QOS_DCH, SMALLINT);
        columnTypes.put(CS_REASON_QUEUE, SMALLINT);
        columnTypes.put(HS_RATIO_COLUMN, FLOAT);
        columnTypes.put(EUL_RATIO_COLUMN, FLOAT);
        columnTypes.put(CS_REASON_OTHER, SMALLINT);

        columnTypes.put(GBR_UPLINK_1, UNSIGNED_INT);
        columnTypes.put(GBR_UPLINK_2, UNSIGNED_INT);
        columnTypes.put(GBR_UPLINK_3, UNSIGNED_INT);

        columnTypes.put(GBR_DOWNLINK_1, UNSIGNED_INT);
        columnTypes.put(GBR_DOWNLINK_2, UNSIGNED_INT);
        columnTypes.put(GBR_DOWNLINK_3, UNSIGNED_INT);
        columnTypes.put(DURATION, UNSIGNED_INT);
        columnTypes.put(CAUSE_PROT_TYPE_COLUMN, SMALLINT);
        columnTypes.put(CAUSE_CODE_COLUMN, SMALLINT);
        columnTypes.put(SUBCAUSE_CODE_COLUMN, SMALLINT);
        columnTypes.put(ADVICE_COLUMN, VARCHAR_255);
        columnTypes.put(EVENT_RESULT, TINYINT);
        columnTypes.put(ATTACH_TYPE, TINYINT);
        columnTypes.put(DETACH_TRIGGER, TINYINT);
        columnTypes.put(DETACH_TYPE, TINYINT);
        columnTypes.put(DEACTIVATION_TRIGGER, TINYINT);
        columnTypes.put(SERVICE_REQ_TRIGGER, TINYINT);
        columnTypes.put(L_DISCONNECT_PDN_TYPE, TINYINT);
        columnTypes.put(EVENT_SUBTYPE_ID, TINYINT);
        columnTypes.put(SMS_ONLY, TINYINT);
        columnTypes.put(COMBINED_TAU_TYPE, TINYINT);
        columnTypes.put(ARP_PCI_1, TINYINT);
        columnTypes.put(ARP_PCI_2, TINYINT);
        columnTypes.put(ARP_PCI_3, TINYINT);
        columnTypes.put(ARP_PVI_1, TINYINT);
        columnTypes.put(ARP_PVI_2, TINYINT);
        columnTypes.put(ARP_PVI_3, TINYINT);
        columnTypes.put(BEARER_CAUSE_1, SMALLINT);
        columnTypes.put(BEARER_CAUSE_2, SMALLINT);
        columnTypes.put(BEARER_CAUSE_3, SMALLINT);
        columnTypes.put(LINKED_NSAPI, UNSIGNED_INT);
        columnTypes.put(PDP_NSAPI_1, UNSIGNED_INT);
        columnTypes.put(PDP_NSAPI_2, UNSIGNED_INT);
        columnTypes.put(PDP_GGSN_IPADDRESS_1, BINARY);
        columnTypes.put(PDP_GGSN_IPADDRESS_2, BINARY);
        columnTypes.put(PDP_MS_IPADDRESS_1, BINARY);
        columnTypes.put(PDP_MS_IPADDRESS_2, BINARY);
        columnTypes.put(PDP_GGSN_NAME_1, VARCHAR_128);
        columnTypes.put(PDP_GGSN_NAME_2, VARCHAR_128);
        columnTypes.put(RAC, TINYINT);
        columnTypes.put(LAC, TINYINT);
        columnTypes.put(UPDATE_TYPE, TINYINT);
        columnTypes.put(OLD_SGSN_IPADDRESS, BINARY);
        columnTypes.put(OLD_RAC, TINYINT);
        columnTypes.put(NO_OF_NET_INIT_DEACTIVATES, NUMERIC);
        columnTypes.put(OLD_LAC, UNSIGNED_INT);
        columnTypes.put(TRANSFERRED_PDP, TINYINT);
        columnTypes.put(HLR, UNSIGNED_BIGINT);
        columnTypes.put(DROPPED_PDP, TINYINT);
        columnTypes.put(EPS_BEARER_ID_1, TINYINT);
        columnTypes.put(EPS_BEARER_ID_2, TINYINT);
        columnTypes.put(EPS_BEARER_ID_3, TINYINT);
        columnTypes.put(QCI_ERR_1, SMALLINT);
        columnTypes.put(QCI_ERR_2, SMALLINT);
        columnTypes.put(QCI_ERR_3, SMALLINT);
        columnTypes.put(QCI_ERR_4, SMALLINT);
        columnTypes.put(QCI_ERR_5, SMALLINT);
        columnTypes.put(QCI_ERR_6, SMALLINT);
        columnTypes.put(QCI_ERR_7, SMALLINT);
        columnTypes.put(QCI_ERR_8, SMALLINT);
        columnTypes.put(QCI_ERR_9, SMALLINT);
        columnTypes.put(QCI_ERR_10, SMALLINT);
        columnTypes.put(QCI_SUC_1, SMALLINT);
        columnTypes.put(QCI_SUC_2, SMALLINT);
        columnTypes.put(QCI_SUC_3, SMALLINT);
        columnTypes.put(QCI_SUC_4, SMALLINT);
        columnTypes.put(QCI_SUC_5, SMALLINT);
        columnTypes.put(QCI_SUC_6, SMALLINT);
        columnTypes.put(QCI_SUC_7, SMALLINT);
        columnTypes.put(QCI_SUC_8, SMALLINT);
        columnTypes.put(QCI_SUC_9, SMALLINT);
        columnTypes.put(QCI_SUC_10, SMALLINT);
        columnTypes.put(MANUFACTURER, VARCHAR_128);
        columnTypes.put(VENDOR_NAME, VARCHAR_128);
        columnTypes.put(QOS_MEAN_DATAVOL, UNSIGNED_BIGINT);
        columnTypes.put(CHARGING_ID, UNSIGNED_BIGINT);
        columnTypes.put(DATAVOL_UL, UNSIGNED_BIGINT);
        columnTypes.put(DATAVOL_DL, UNSIGNED_BIGINT);
        columnTypes.put(SGSN_NAME, VARCHAR_128);
        columnTypes.put(NO_OF_TOTAL, UNSIGNED_INT);
        columnTypes.put(PEAK_USAGE_UL, UNSIGNED_INT);
        columnTypes.put(PEAK_USAGE_DL, UNSIGNED_BIGINT);
        columnTypes.put(QOS_HOP_UP, TINYINT);
        columnTypes.put(QOS_HOP_DOWN, TINYINT);
        columnTypes.put(SGSN_NAME_1, VARCHAR_128);
        columnTypes.put(SGSN_NAME_2, VARCHAR_128);
        columnTypes.put(SGSN_NAME_3, VARCHAR_128);
        columnTypes.put(SGSN_NAME_4, VARCHAR_128);
        columnTypes.put(SGSN_NAME_5, VARCHAR_128);
        columnTypes.put(GROUP_NAME, VARCHAR_64);
        columnTypes.put(LAST_SEEN, TIMESTAMP);
        columnTypes.put(MSISDN, UNSIGNED_BIGINT);
        columnTypes.put(CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(SUB_CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(CAUSE_PROT_TYPE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(SUB_CAUSE_CODE_HELP_COLUMN, VARCHAR_128);
        columnTypes.put(TABLENAME, VARCHAR_24);
        columnTypes.put(MAX_DATE, TIMESTAMP);
        columnTypes.put(MIN_DATE, TIMESTAMP);
        columnTypes.put(TECHPACK_NAME, VARCHAR_30);
        columnTypes.put(RNC_ID_1, UNSIGNED_INT);
        columnTypes.put(RNC_FDN, VARCHAR_255);
        columnTypes.put(RNCID, SMALLINT);
        columnTypes.put(RNC_ID, VARCHAR_35);
        columnTypes.put(CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(SUB_CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(CAUSE_PROT_TYPE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(SUB_CAUSE_CODE_HELP_COLUMN, VARCHAR_128);
        columnTypes.put(HIER3_ID, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_FAILURES, UNSIGNED_BIGINT);
        columnTypes.put(CAUSE_PROT_TYPE_COLUMN, TINYINT);
        columnTypes.put(CAUSE_PROT_TYPE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(CAUSE_PROT_TYPE_COLUMN, TINYINT);
        columnTypes.put(CAUSE_PROT_TYPE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(SUBCAUSE_CODE_COLUMN, SMALLINT);
        columnTypes.put(SUB_CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(SUB_CAUSE_CODE_HELP_COLUMN, VARCHAR_128);
        columnTypes.put(CAUSE_CODE_COLUMN, SMALLINT);
        columnTypes.put(CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(CAUSE_CODE_HELP_COLUMN, VARCHAR_128);
        columnTypes.put(CAUSE_PROT_TYPE_COLUMN, TINYINT);
        columnTypes.put(CAUSE_VALUE_COLUMN, TINYINT);
        columnTypes.put(CAUSE_VALUE_DESC_COLUMN, VARCHAR_255);
        columnTypes.put(EXTENDED_CAUSE_VALUE, TINYINT);
        columnTypes.put(SUB_CAUSE_VALUE, TINYINT);
        columnTypes.put(SUB_CAUSE_VALUE_DESC, VARCHAR_255);

        columnTypes.put(NO_OF_TOTAL_ERR_ROAMERS, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_TOTAL_ROAMERS, UNSIGNED_BIGINT);
        columnTypes.put(INTERNAL_CAUSE_CODE_COLUMN, SMALLINT);
        columnTypes.put(INTERNAL_CAUSE_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(FAULT_CODE_COLUMN, UNSIGNED_INT);
        columnTypes.put(FAULT_CODE_DESC_COLUMN, VARCHAR_128);
        columnTypes.put(FAULT_CODE_ADVICE_COLUMN, VARCHAR_128);
        columnTypes.put(EVNTSRC_ID, UNSIGNED_BIGINT);
        columnTypes.put(HIER3_ID, UNSIGNED_BIGINT);
        columnTypes.put(HIER321_ID, UNSIGNED_BIGINT);
        columnTypes.put(EVENT_ID_DESC, VARCHAR_255);
        columnTypes.put(EUL_UE_CATEGORY_DESC, VARCHAR);
        columnTypes.put(ALTERNATIVE_FDN, VARCHAR_255);
        columnTypes.put(EVENT_ID_ALTERNATE_DESC, VARCHAR_255);
        columnTypes.put(HSDPA_UE_CATEGORY_DESC, VARCHAR);
        columnTypes.put(EVALUATION_CASE, SMALLINT);
        columnTypes.put(PROCEDURE_INDICATOR, TINYINT);
        columnTypes.put(EXCEPTION_CASE, SMALLINT);
        columnTypes.put(EXTENDED_CAUSE_VALUE, SMALLINT);
        columnTypes.put(SEVERITY_INDICATOR, BIT);
        columnTypes.put(PROCEDURE_INDICATOR_DESC, VARCHAR_255);
        columnTypes.put(EVALUATION_CASE_DESC, VARCHAR_255);
        columnTypes.put(EXCEPTION_CLASS, TINYINT);
        columnTypes.put(EXCEPTION_CLASS_DESC, VARCHAR_255);
        columnTypes.put(EXTENDED_CAUSE_VALUE_DESC, VARCHAR_255);
        columnTypes.put(SEVERITY_INDICATOR_ID, INT);
        columnTypes.put(SEVERITY_INDICATOR_DESC, VARCHAR_255);
        columnTypes.put(MARKETING_NAME, VARCHAR_255);
        columnTypes.put(CELL_ID, VARCHAR_35);
        columnTypes.put(C_ID_1, UNSIGNED_INT);
        columnTypes.put(SN, VARCHAR_255);
        columnTypes.put(CID, UNSIGNED_INT);
        columnTypes.put(HIER3_CELL_ID, UNSIGNED_BIGINT);
        columnTypes.put(THIER3_CELL_ID, UNSIGNED_BIGINT);
        columnTypes.put(CATEGORY_DESC, VARCHAR_255);
        columnTypes.put(CATEGORY_TABLE_NAME, VARCHAR_255);
        columnTypes.put(COUNTRY, VARCHAR_255);
        columnTypes.put(OPERATOR, VARCHAR_255);
        //For LTE CFA
        columnTypes.put(SETUP_RESULT, TINYINT);
        columnTypes.put(SETUP_RESULT_DESC, VARCHAR_255);
        columnTypes.put(SETUP_REQ_PCI, TINYINT);
        columnTypes.put(SETUP_REQ_PCI_DESC, VARCHAR_255);
        columnTypes.put(SETUP_REQ_PVI, TINYINT);
        columnTypes.put(SETUP_REQ_PVI_DESC, VARCHAR_255);
        columnTypes.put(SETUP_ATT_ACC_TYPE, TINYINT);
        columnTypes.put(SETUP_ATT_ACC_TYPE_DESC, VARCHAR_255);
        columnTypes.put(SETUP_SUCC_ACC_TYPE, TINYINT);
        columnTypes.put(SETUP_SUCC_ACC_TYPE_DESC, VARCHAR_255);
        columnTypes.put(SETUP_REQ_QCI, SMALLINT);
        columnTypes.put(SETUP_REQ_ARP, SMALLINT);
        columnTypes.put(FAILURE_3GPP_CAUSE, TINYINT);
        columnTypes.put(SETUP_FAIL_3GPP_CAUSE_GROUP, TINYINT);
        columnTypes.put(SETUP_FAIL_3GPP_CAUSE_GROUP_DESC, VARCHAR_255);
        columnTypes.put(RELEASE_REQ_QCI, SMALLINT);
        columnTypes.put(RRC_ESTABL_CAUSE, UNSIGNED_INT);
        columnTypes.put(NO_OF_ERABS, UNSIGNED_INT);
        columnTypes.put(ACCUMULATED_UL_REQ_GBR, UNSIGNED_INT);
        columnTypes.put(ACCUMULATED_UL_ADM_GBR, UNSIGNED_INT);
        columnTypes.put(ACCUMULATED_DL_REQ_GBR, UNSIGNED_INT);
        columnTypes.put(ACCUMULATED_DL_ADM_GBR, UNSIGNED_INT);
        columnTypes.put(INTERNAL_RELEASE_CAUSE, UNSIGNED_INT);
        columnTypes.put(ERAB_DATA_LOST_BITMAP, UNSIGNED_INT);
        columnTypes.put(ERAB_DATA_LOST, UNSIGNED_INT);
        columnTypes.put(ERAB_RELEASE_SUCC, UNSIGNED_INT);
        columnTypes.put(HO_OUT_PREP_ERAB_FAILBITMAP, UNSIGNED_INT);
        columnTypes.put(HO_OUT_PREP_ERAB_FAIL, UNSIGNED_INT);
        columnTypes.put(ERAB_HO_PREP_DATA_LOST, UNSIGNED_INT);
        columnTypes.put(TRIGGERING_NODE, UNSIGNED_INT);
        columnTypes.put(CAUSE_CODE_COLUMN, UNSIGNED_INT);
        columnTypes.put(INTERNAL_RELEASE_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(RRC_ESTABL_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(TRIGGERING_NODE_DESC, VARCHAR_255);
        columnTypes.put(GSM_COLUMN_NAME_URGENCY_CONDITION, TINYINT);
        columnTypes.put(GSM_COLUMN_NAME_URGENCY_CONDITION_DESC, VARCHAR_255);
        columnTypes.put(QCI_ID_COLUMN, SMALLINT);
        columnTypes.put(QCI_NUMBER_COLUMN, SMALLINT);
        columnTypes.put(QCI_ID_DESCRIPTION_COLUMN, VARCHAR_255);
        columnTypes.put(DATA_LOST, SMALLINT);
        columnTypes.put(RELEASE_SUCC, SMALLINT);

        columnTypes.put(GUMMEI_TYPE, TINYINT);
        columnTypes.put(GUMMEI_TYPE_DESC, VARCHAR_255);
        columnTypes.put(BUNDLING_MODE, TINYINT);
        columnTypes.put(BUNDLING_MODE_DESC, VARCHAR_255);
        columnTypes.put(TTI_BUNDLING_MODE, TINYINT);

        //GSM CFA
        columnTypes.put(com.ericsson.eniq.events.server.common.ApplicationConstants.HIER32_ID, UNSIGNED_BIGINT);
        columnTypes.put(URGENCY_CONDITION, TINYINT);
        columnTypes.put(EXTENDED_CAUSE, TINYINT);
        columnTypes.put(MS_ID, UNSIGNED_INT);
        columnTypes.put(IMEISV, UNSIGNED_BIGINT);
        columnTypes.put(URGENCY_CONDITION_DESC, VARCHAR_255);
        columnTypes.put(RSAI, TINYINT);
        columnTypes.put(RELEASE_TYPE, TINYINT);
        columnTypes.put(CHANNEL_TYPE, TINYINT);
        columnTypes.put(CALL_ID, UNSIGNED_BIGINT);
        columnTypes.put(EXTENDED_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(DATE_ID, DATE);
        columnTypes.put(YEAR_ID, SMALLINT);
        columnTypes.put(MONTH_ID, TINYINT);
        columnTypes.put(DAY_ID, TINYINT);
        columnTypes.put(HOUR_ID, TINYINT);
        columnTypes.put(MIN_ID, TINYINT);
        columnTypes.put(TIMEZONE, SMALLINT);
        columnTypes.put(SUSPECTFLAG, BIT);
        columnTypes.put(NE_VERSION, VARCHAR_255);
        columnTypes.put(SESSION_ID, UNSIGNED_INT);
        columnTypes.put(BATCH_ID, UNSIGNED_INT);
        columnTypes.put(LOCAL_DATE_ID, DATE);
        columnTypes.put(OSS_ID, VARCHAR_255);
        columnTypes.put(RELEASE_TYPE_DESC, VARCHAR_255);
        columnTypes.put(MCC, VARCHAR_127);
        columnTypes.put(MNC, VARCHAR_127);
        columnTypes.put(LAC, UNSIGNED_INT);
        columnTypes.put(SERVING_AREA, UNSIGNED_INT);
        columnTypes.put(CONTROLLER, VARCHAR);
        columnTypes.put(PLMN, VARCHAR);
        columnTypes.put(CELL, TINYINT);
        columnTypes.put(TERMINAL, VARCHAR);
        columnTypes.put(NETWORK, VARCHAR);
        columnTypes.put(EVENT_TYPE, VARCHAR);
        columnTypes.put(SGSN, VARCHAR);
        columnTypes.put(ROUTING_AREA, TINYINT);
        columnTypes.put(RAC, TINYINT);
        columnTypes.put(RAT, TINYINT);
        columnTypes.put(CATEGORY_ID, TINYINT);
        columnTypes.put(CATEGORY_ID_DESC, VARCHAR_255);
        columnTypes.put(GROUP_NAME, VARCHAR_64);
        columnTypes.put(VAMOS_NEIGHBOR_INDICATOR, TINYINT);
        columnTypes.put(VAMOS_PAIR_ALLOCATION_BY_MS, VARCHAR_255);
        columnTypes.put(VAMOS_NEIGHBOR_INDICATOR_DESC, VARCHAR_255);
        columnTypes.put(CAUSE_GROUP, TINYINT);
        columnTypes.put(CAUSE_GROUP_DESC, VARCHAR_255);
        columnTypes.put(RSAI_DESC, VARCHAR_255);
        columnTypes.put(CHANNEL_TYPE_DESC, VARCHAR_255);

        //GSM Data Connection
        columnTypes.put(DOWNLOAD_DATA_VOLUME, UNSIGNED_BIGINT);
        columnTypes.put(UPLOAD_DATA_VOLUME, UNSIGNED_BIGINT);
        columnTypes.put(TOTAL_DATA_VOLUME, UNSIGNED_BIGINT);
        columnTypes.put(DOWNLOAD_DURATION, UNSIGNED_BIGINT);
        columnTypes.put(UPLOAD_DURATION, UNSIGNED_BIGINT);
        columnTypes.put(TOTAL_DURATION, UNSIGNED_BIGINT);
        columnTypes.put(TBF_RELEASE_CAUSE, UNSIGNED_INT);
        columnTypes.put(TBF_DATA_VOLUME, UNSIGNED_INT);
        columnTypes.put(TBF_DURATION, UNSIGNED_INT);
        columnTypes.put(RP_NUMBER, UNSIGNED_INT);
        columnTypes.put(CHANNEL_RELATED_RELEASE_CAUSE, UNSIGNED_INT);
        columnTypes.put(CHANNEL_RELATED_RELEASE_CAUSE_GROUP, UNSIGNED_INT);
        columnTypes.put(TBF_MUX, BIT);
        columnTypes.put(MS_3GPP_CAP, TINYINT);
        columnTypes.put(EFACTOR_SETTINGS, TINYINT);
        columnTypes.put(DATA_VALID_INDICATOR, BIT);
        columnTypes.put(MS_SAIC_CAP, TINYINT);
        columnTypes.put(AQM_ACTIVE, BIT);
        columnTypes.put(DTM_FLAG, BIT);
        columnTypes.put(RLC_MODE, BIT);
        columnTypes.put(DIR, BIT);
        columnTypes.put(OFLW, BIT);
        columnTypes.put(TTI_MODE, BIT);
        columnTypes.put(REDUCED_LATENCY, BIT);
        columnTypes.put(RADIO_LINK_BITRATE, SMALLINT);
        columnTypes.put(GPRS_MEAS_REPORT_RXQUAL_DL, TINYINT);
        columnTypes.put(GPRS_MEAS_REPORT_SIGN_VAR, TINYINT);
        columnTypes.put(GPRS_MEAS_REPORT_MEAN_BEP, TINYINT);
        columnTypes.put(GPRS_MEAS_REPORT_CV_BEP, TINYINT);
        columnTypes.put(GPRS_MEAS_REPORT_CVALUE, TINYINT);
        columnTypes.put(IP_LATENCY, UNSIGNED_INT);
        columnTypes.put(LOW_PRIORITY_MODE_TIME, UNSIGNED_INT);
        columnTypes.put(BLER, TINYINT);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_850, BIT);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_900, TINYINT);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_1800, BIT);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_1900, BIT);
        columnTypes.put(AQM_DATA_DELIVERED, UNSIGNED_INT);
        columnTypes.put(AQM_DATA_RECIEVED, UNSIGNED_INT);
        columnTypes.put(PAN_INDICATOR, BIT);
        columnTypes.put(TBF_MODE, TINYINT);
        columnTypes.put(DCDL_CAPABILITY, BIT);
        columnTypes.put(DCDL_INDICATOR, BIT);
        columnTypes.put(MS_MSLOT_CAP_REDUCTION, TINYINT);
        columnTypes.put(TBF_RELEASE_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(TBF_MUX_DESC, VARCHAR_128);
        columnTypes.put(DATA_VALID_INDICATOR_DESC, VARCHAR_20);
        columnTypes.put(MS_SAIC_CAP_DESC, VARCHAR_64);
        columnTypes.put(AQM_ACTIVE_DESC, VARCHAR_64);
        columnTypes.put(DTM_FLAG_DESC, VARCHAR_20);
        columnTypes.put(RLC_MODE_DESC, VARCHAR_20);
        columnTypes.put(DIR_DESC, VARCHAR_20);
        columnTypes.put(OFLW_DESC, VARCHAR_20);
        columnTypes.put(TTI_MODE_DESC, VARCHAR_20);
        columnTypes.put(REDUCED_LATENCY_DESC, VARCHAR_30);
        columnTypes.put(GPRS_MEAS_REPORT_RXQUAL_DL_DESC, VARCHAR_30);
        columnTypes.put(GPRS_MEAS_REPORT_SIGN_VAR_DESC, VARCHAR_30);
        columnTypes.put(GPRS_MEAS_REPORT_MEAN_BEP_DESC, VARCHAR_30);
        columnTypes.put(GPRS_MEAS_REPORT_CV_BEP_DESC, VARCHAR_30);
        columnTypes.put(GPRS_MEAS_REPORT_CVALUE_DESC, VARCHAR_30);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_850_DESC, VARCHAR_64);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_900_DESC, VARCHAR_128);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_1800_DESC, VARCHAR_64);
        columnTypes.put(MS_FREQ_BAND_CAP_GSM_1900_DESC, VARCHAR_64);
        columnTypes.put(PAN_INDICATOR_DESC, VARCHAR_30);
        columnTypes.put(TBF_MODE_DESC, VARCHAR_20);
        columnTypes.put(DCDL_CAPABILITY_DESC, VARCHAR_30);
        columnTypes.put(DCDL_INDICATOR_DESC, VARCHAR_64);
        columnTypes.put(MS_MSLOT_CAP_REDUCTION_DESC, VARCHAR_30);
        columnTypes.put(MS_3GPP_CAP_DESC, VARCHAR_64);
        columnTypes.put(CHANNEL_RELATED_RELEASE_CAUSE_GROUP_DESC, VARCHAR_20);
        columnTypes.put(OLD_SGSN_NAME, VARCHAR_20);
        columnTypes.put(ATTACH_TYPE_DESC, VARCHAR_64);
        columnTypes.put(DEACTIVATION_TRIGGER_DESC, VARCHAR_128);
        columnTypes.put(DETACH_TRIGGER_DESC, VARCHAR_128);
        columnTypes.put(DETACH_TYPE_DESC, VARCHAR_128);
        columnTypes.put(EVENT_RESULT_DESC, VARCHAR_128);
        columnTypes.put(GGSN_NAME, VARCHAR_128);
        columnTypes.put(GGSN_IPADDRESS, BINARY);
        columnTypes.put(STATUS, VARCHAR_20);
        columnTypes.put(MODIFIER, VARCHAR_20);
        columnTypes.put(CREATED, TIMESTAMP);
        columnTypes.put(MODIFIED, TIMESTAMP);
        columnTypes.put(RAT_DESC, VARCHAR_20);
        columnTypes.put(SERVICE_REQ_TRIGGER_DESC, VARCHAR_255);
        columnTypes.put(PASSWORD, VARCHAR_24);
        columnTypes.put(USERNAME, VARCHAR_24);
        columnTypes.put(EVENT_PATH, VARCHAR_255);
        columnTypes.put(POOLNAME, VARCHAR_255);
        columnTypes.put(IP_ADDRESS, BINARY);
        columnTypes.put(IP_ADDRESS, BINARY);
        columnTypes.put(CAUSE_CODE_DESC_PIE, VARCHAR_255);
        columnTypes.put(GSM_CSF_AF_ID, TINYINT);
        columnTypes.put(GSM_CSF_AF_CAUSE, TINYINT);
        columnTypes.put(GSM_CSF_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(GSM_CSF_SHORT_DESC, VARCHAR_255);
        columnTypes.put(GSM_CSF_ORIGIN, VARCHAR_255);

        //For LTE HFA

        columnTypes.put(PREP_IN_RESULT_UE_CTXT, UNSIGNED_INT);
        columnTypes.put(CAUSE_GROUP_3GPP, UNSIGNED_INT);
        columnTypes.put(CAUSE_3GPP, UNSIGNED_INT);
        columnTypes.put(SRVCC_TYPE, UNSIGNED_INT);
        columnTypes.put(HO_EXEC_OUT_ATTEMPT_CAUSE, UNSIGNED_INT);
        columnTypes.put(HO_SOURCE_TARGET_TYPE, TINYINT);
        columnTypes.put(HO_TARGET_SELECTION_TYPE, TINYINT);
        columnTypes.put(HO_PREP_OUT_ATTEMPT_CAUSE, TINYINT);
        columnTypes.put(HO_PACKET_FORWARD, TINYINT);
        columnTypes.put(RANDOM_ACCESS_TYPE, TINYINT);
        columnTypes.put(HO_TYPE, TINYINT);
        columnTypes.put(SPID_VALUE, UNSIGNED_INT);
        columnTypes.put(DRX_CONFIG_INDEX, TINYINT);
        columnTypes.put(HO_OUT_EXEC_ERAB_FAILBITMAP, UNSIGNED_INT);
        columnTypes.put(HO_OUT_PREP_ERAB_FAILBITMAP, UNSIGNED_INT);
        columnTypes.put(HO_OUT_PREP_ERAB_REQBITMAP, UNSIGNED_INT);
        columnTypes.put(HO_OUT_EXEC_ERAB_REQBITMAP, UNSIGNED_INT);
        columnTypes.put(HO_IN_PREP_ERAB_REQ, SMALLINT);
        columnTypes.put(HO_IN_PREP_ERAB_RESULT, TINYINT);
        columnTypes.put(CATEGORY_ID_2, TINYINT);
        columnTypes.put(CATEGORY_ID_2_DESC, VARCHAR_20);
        columnTypes.put(THIER3_ID, UNSIGNED_BIGINT);
        columnTypes.put(THIER321_ID, UNSIGNED_BIGINT);

        //RAN_SESSION
        //columnTypes.put(RSCP_AVG, UNSIGNED_INT);
        //columnTypes.put(ECNO_AVG, UNSIGNED_INT);
        //columnTypes.put(UL_INTERFERENCE_AVG, UNSIGNED_INT);
        //columnTypes.put(HSDSCH_AVG_USERS, UNSIGNED_INT);
        columnTypes.put(ARP_PCI, UNSIGNED_BIGINT);
        columnTypes.put(ARP_PVI, UNSIGNED_BIGINT);
        columnTypes.put(ARP_PCI_DESC, VARCHAR_255);
        columnTypes.put(ARP_PVI_DESC, VARCHAR_255);
        columnTypes.put(BEARER_CAUSE, UNSIGNED_BIGINT);
        columnTypes.put(BEARER_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(COMBINED_TAU_TYPE_DESC, VARCHAR_255);
        columnTypes.put(EVENT_SUBTYPE_ID_DESC, VARCHAR_255);
        columnTypes.put(PDNDISCONNECTTYPE, UNSIGNED_BIGINT);
        columnTypes.put(PDNDISCONNECTTYPE_DESCRIPTION, VARCHAR_255);
        columnTypes.put(SMS_ONLY_DESC, VARCHAR_255);
        columnTypes.put(EXCEPTION_CLASS_DESC, VARCHAR_255);
        columnTypes.put(EXCEPTION_CASE_DESC, VARCHAR_255);

        //CFA ADDITIONAL COLUMNS
        columnTypes.put(SOURCE_CONF, UNSIGNED_INT);
        columnTypes.put(RAB_TYPE, UNSIGNED_INT);
        columnTypes.put(RAB_TYPE_DESC, VARCHAR_255);
        columnTypes.put(CATEGORY_ID_VALUE, UNSIGNED_INT);
        columnTypes.put(CPICH_EC_NO_CELL_1, UNSIGNED_INT);
        columnTypes.put(UL_INT_CELL1, UNSIGNED_INT);
        columnTypes.put(RSCP_CELL_1, UNSIGNED_INT);
        columnTypes.put(TARGET_CONF, UNSIGNED_INT);
        columnTypes.put(C_ID_SERV_HSDSCH_CELL, UNSIGNED_INT);
        columnTypes.put(CRNC_ID_SERV_HSDSCH_CELL, UNSIGNED_INT);
        columnTypes.put(RSCP_CELL_2, UNSIGNED_INT);
        columnTypes.put(RSCP_CELL_3, UNSIGNED_INT);
        columnTypes.put(RSCP_CELL_4, UNSIGNED_INT);
        columnTypes.put(CPICH_EC_NO_CELL_2, UNSIGNED_INT);
        columnTypes.put(CPICH_EC_NO_CELL_3, UNSIGNED_INT);
        columnTypes.put(CPICH_EC_NO_CELL_4, UNSIGNED_INT);
        columnTypes.put(UL_INT_CELL2, UNSIGNED_INT);
        columnTypes.put(UL_INT_CELL3, UNSIGNED_INT);
        columnTypes.put(UL_INT_CELL4, UNSIGNED_INT);
        columnTypes.put(SCRAMBLING_CODE_CELL_1, UNSIGNED_INT);
        columnTypes.put(SCRAMBLING_CODE_CELL_2, UNSIGNED_INT);
        columnTypes.put(SCRAMBLING_CODE_CELL_3, UNSIGNED_INT);
        columnTypes.put(SCRAMBLING_CODE_CELL_4, UNSIGNED_INT);
        columnTypes.put(GBR_UL, UNSIGNED_INT);
        columnTypes.put(GBR_DL, UNSIGNED_INT);
        columnTypes.put(CN_RANAP_CAUSE, UNSIGNED_INT);
        columnTypes.put(UTRAN_RANAP_CAUSE, TINYINT);
        columnTypes.put(DATA_IN_DL_RLC_BUFFERS, UNSIGNED_INT);
        columnTypes.put(SOURCE_CONNECTION_PROPERTIES, UNSIGNED_INT);
        columnTypes.put(TARGET_CONNECTION_PROPERTIES, UNSIGNED_INT);
        columnTypes.put(WANTED_CONNECTION_PROPERTIES, UNSIGNED_INT);
        columnTypes.put(UTRAN_RANAP_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(CN_RANAP_CAUSE_DESC, VARCHAR_255);
        columnTypes.put(ECNO_COLUMN, TINYINT);
        columnTypes.put(ECNO_DBM_COLUMN, REAL_TYPE);
        columnTypes.put(ULINT_COLUMN, TINYINT);
        columnTypes.put(ULINT_DBM_COLUMN, REAL_TYPE);
        columnTypes.put(RSCP_COLUMN, TINYINT);
        columnTypes.put(RSCP_DBM_COLUMN, REAL_TYPE);

        columnTypes.put(CELLO_AAL2NCI_REJECT_REASON, TINYINT);
        columnTypes.put(CELLO_AAL2NCI_REJECT_DESC, VARCHAR_12);
        columnTypes.put(ORIGINATING_STATE, TINYINT);
        columnTypes.put(ORIGINATING_STATE_DESC, VARCHAR_12);
        columnTypes.put(SCRAMBLING_CODE_ADDED_CELL, UNSIGNED_INT);
        columnTypes.put(RSCP_CELL_1_ADDED_CELL, UNSIGNED_INT);
        columnTypes.put(CPICH_EC_NO_ADDED_CELL, UNSIGNED_INT);
        columnTypes.put(WANTED_CONF, UNSIGNED_INT);
        columnTypes.put(RRC_ESTABLISHMENT_CAUSE, TINYINT);
        columnTypes.put(RRC_ESTABLISHMENT_CAUSE_DESC, VARCHAR_255);

        columnTypes.put(RAN_DISCONNECTION_CODE, TINYINT);
        columnTypes.put(RAN_DISCONNECTION_SUBCODE, TINYINT);
        columnTypes.put(DISCONNECTION_CODE, TINYINT);
        columnTypes.put(DISCONNECTION_SUB_CODE, TINYINT);
        columnTypes.put(DISCONNECTION_DESC, VARCHAR_255);
        columnTypes.put(FAILED_RAB_TYPE, VARCHAR_255);

        columnTypes.put(PS_ERR_CNT, TINYINT);
        columnTypes.put(PS_RAB_FAIL_CNT, TINYINT);
        columnTypes.put(CS_ERR_CNT, TINYINT);
        columnTypes.put(CS_RAB_FAIL_CNT, TINYINT);
        columnTypes.put(MULTI_ERR_CNT, TINYINT);
        columnTypes.put(MULTI_RAB_FAIL_CNT, TINYINT);

        columnTypes.put(TRIGGER_POINT, TINYINT);
        columnTypes.put(TRIGGER_POINT_DESC, VARCHAR_255);

        // Column types required for PopupDetail events integrity test
        //columnTypes.put("CAUSE_CODE", TINYINT);
        columnTypes.put("CAUSE_CODE_DESC", VARCHAR_255);
        columnTypes.put("GGSN_NAME", VARCHAR_255);
        columnTypes.put("ACTIVATION_TYPE", TINYINT);
        columnTypes.put("DEACTIVATION_TRIGGER", TINYINT);
        columnTypes.put("INTRA_RAU_TYPE", TINYINT);
        columnTypes.put("UPDATE_TYPE", TINYINT);
        columnTypes.put("OLD_MNC", VARCHAR_12);
        columnTypes.put("OLD_MCC", VARCHAR_12);
        columnTypes.put("SERVICE_TYPE", TINYINT);

        //Columns for All Calls
        columnTypes.put(ELEMENT_ID, UNSIGNED_BIGINT);

        columnTypes.put(CALLS_CS_ALL, UNSIGNED_BIGINT);
        columnTypes.put(CALLS_PS_ALL, UNSIGNED_BIGINT);
        columnTypes.put(CALLS_MR_ALL, UNSIGNED_BIGINT);

        columnTypes.put(PS_SUC, UNSIGNED_BIGINT);
        columnTypes.put(CS_SUC, UNSIGNED_BIGINT);
        columnTypes.put(MR_SUC, UNSIGNED_BIGINT);
    }

    /**
     * Get the column type for a column
     * @throws Exception
     */
    public static String getColumnType(final String column) {
        if (columnTypes.containsKey(column)) {
            return columnTypes.get(column);
        }
        throw new RuntimeException("No column type specified for: " + column + ", see ColumnTypes");
    }

    public static Object getDefaultValueForColumnType(final String column) {
        final String columnType = getColumnType(column);
        if (defaultValuesForColumnTypes.containsKey(columnType)) {
            return defaultValuesForColumnTypes.get(columnType);
        }
        throw new RuntimeException("No default value provided for the column type " + columnType + ", see ColumnTypes");
    }

}
