/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.populator;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the list of columns required for detailed columns on the raw tables, and their default values used
 * when populating temporary tables for data integrity tests
 * @author EEMECOY
 *
 */
public class RawTableColumns {

    private static final String EVENT_ID = "EVENT_ID";

    private static final String DATETIME_ID = "DATETIME_ID";

    private static final String APN = "APN";

    private static final String VENDOR_PARAM_UPPER_CASE = "VENDOR";

    private static final String IMSI = "IMSI";

    private static final String TAC = "TAC";

    static Map<String, Object> getColumnsExclusiveToRawSgehTables() {
        final Map<String, Object> columnsInSgehRawTables = new HashMap<String, Object>();
        columnsInSgehRawTables.put(PTMSI, SAMPLE_PTMSI_VALUE);
        columnsInSgehRawTables.put(LINKED_NSAPI, 0);
        columnsInSgehRawTables.put(PDP_NSAPI_1, 0);
        columnsInSgehRawTables.put(PDP_NSAPI_2, 0);
        columnsInSgehRawTables.put(PDP_GGSN_IPADDRESS_1, 0);
        columnsInSgehRawTables.put(PDP_GGSN_IPADDRESS_2, 0);
        columnsInSgehRawTables.put(PDP_MS_IPADDRESS_1, 0);
        columnsInSgehRawTables.put(PDP_MS_IPADDRESS_2, 0);
        columnsInSgehRawTables.put(PDP_GGSN_NAME_1, "0");
        columnsInSgehRawTables.put(PDP_GGSN_NAME_2, "0");
        columnsInSgehRawTables.put(RAC, 0);
        columnsInSgehRawTables.put(LAC, 0);
        columnsInSgehRawTables.put(UPDATE_TYPE, 0);
        columnsInSgehRawTables.put(OLD_SGSN_IPADDRESS, 0);
        columnsInSgehRawTables.put(MSISDN, 0);
        return columnsInSgehRawTables;
    }

    static Map<String, Object> getColumnsExclusiveToRawLteTables() {

        final Map<String, Object> columnsInLteRawTables = new HashMap<String, Object>();
        columnsInLteRawTables.put(TRAC, 0);
        columnsInLteRawTables.put(OLD_TRAC, 0);
        columnsInLteRawTables.put(OLD_CELL_ID, 0);
        columnsInLteRawTables.put(OLD_L_MMEGI, 0);
        columnsInLteRawTables.put(OLD_L_MMEC, 0);
        columnsInLteRawTables.put(OLD_L_MTMSI, 0);
        columnsInLteRawTables.put(OLD_SGW_IPV4, 0);
        columnsInLteRawTables.put(OLD_SGW_IPV6, 0);
        columnsInLteRawTables.put(PDN_BEARER_ID_1, 0);
        columnsInLteRawTables.put(PDN_BEARER_ID_2, 0);
        columnsInLteRawTables.put(PDN_BEARER_ID_3, 0);
        columnsInLteRawTables.put(PDN_PAA_IPV4_1, 0);
        columnsInLteRawTables.put(PDN_PAA_IPV4_2, 0);
        columnsInLteRawTables.put(PDN_PAA_IPV4_3, 0);
        columnsInLteRawTables.put(PDN_PAA_IPV6_1, 0);
        columnsInLteRawTables.put(PDN_PAA_IPV6_2, 0);
        columnsInLteRawTables.put(PDN_PAA_IPV6_3, 0);
        columnsInLteRawTables.put(PDN_PGW_IPV4_1, 0);
        columnsInLteRawTables.put(PDN_PGW_IPV4_2, 0);
        columnsInLteRawTables.put(PDN_PGW_IPV4_3, 0);
        columnsInLteRawTables.put(PDN_PGW_IPV6_1, 0);
        columnsInLteRawTables.put(PDN_PGW_IPV6_2, 0);
        columnsInLteRawTables.put(PDN_PGW_IPV6_3, 0);
        columnsInLteRawTables.put(ARP_PL_1, 0);
        columnsInLteRawTables.put(ARP_PL_2, 0);
        columnsInLteRawTables.put(ARP_PL_3, 0);
        columnsInLteRawTables.put(GBR_UPLINK_1, 0);
        columnsInLteRawTables.put(GBR_UPLINK_2, 0);
        columnsInLteRawTables.put(GBR_UPLINK_3, 0);
        columnsInLteRawTables.put(GBR_DOWNLINK_1, 0);
        columnsInLteRawTables.put(GBR_DOWNLINK_2, 0);
        columnsInLteRawTables.put(GBR_DOWNLINK_3, 0);
        columnsInLteRawTables.put(L_DISCONNECT_PDN_TYPE, 0);
        columnsInLteRawTables.put(EVENT_SUBTYPE_ID, 0);
        columnsInLteRawTables.put(SMS_ONLY, 0);
        columnsInLteRawTables.put(COMBINED_TAU_TYPE, 0);
        columnsInLteRawTables.put(ARP_PCI_1, 0);
        columnsInLteRawTables.put(ARP_PCI_2, 0);
        columnsInLteRawTables.put(ARP_PCI_3, 0);
        columnsInLteRawTables.put(ARP_PVI_1, 0);
        columnsInLteRawTables.put(ARP_PVI_2, 0);
        columnsInLteRawTables.put(ARP_PVI_3, 0);
        columnsInLteRawTables.put(BEARER_CAUSE_1, 0);
        columnsInLteRawTables.put(BEARER_CAUSE_2, 0);
        columnsInLteRawTables.put(BEARER_CAUSE_3, 0);
        columnsInLteRawTables.put(EPS_BEARER_ID_1, 0);
        columnsInLteRawTables.put(EPS_BEARER_ID_2, 0);
        columnsInLteRawTables.put(EPS_BEARER_ID_3, 0);
        columnsInLteRawTables.put(QCI_ERR_1, 0);
        columnsInLteRawTables.put(QCI_ERR_2, 0);
        columnsInLteRawTables.put(QCI_ERR_3, 0);
        columnsInLteRawTables.put(QCI_ERR_4, 0);
        columnsInLteRawTables.put(QCI_ERR_5, 0);
        columnsInLteRawTables.put(QCI_ERR_6, 0);
        columnsInLteRawTables.put(QCI_ERR_7, 0);
        columnsInLteRawTables.put(QCI_ERR_8, 0);
        columnsInLteRawTables.put(QCI_ERR_9, 0);
        columnsInLteRawTables.put(QCI_ERR_10, 0);
        columnsInLteRawTables.put(QCI_SUC_1, 0);
        columnsInLteRawTables.put(QCI_SUC_2, 0);
        columnsInLteRawTables.put(QCI_SUC_3, 0);
        columnsInLteRawTables.put(QCI_SUC_4, 0);
        columnsInLteRawTables.put(QCI_SUC_5, 0);
        columnsInLteRawTables.put(QCI_SUC_6, 0);
        columnsInLteRawTables.put(QCI_SUC_7, 0);
        columnsInLteRawTables.put(QCI_SUC_8, 0);
        columnsInLteRawTables.put(QCI_SUC_9, 0);
        columnsInLteRawTables.put(QCI_SUC_10, 0);
        columnsInLteRawTables.put(MSISDN, 0);
        return columnsInLteRawTables;
    }

    static Map<String, Object> getColumnsForRawTables(final String timestamp) {
        final Map<String, Object> columnsInRawTables = new HashMap<String, Object>();
        columnsInRawTables.put(EVENT_ID, 0);
        columnsInRawTables.put(RAT, 0);
        columnsInRawTables.put(TAC, 0);
        columnsInRawTables.put(IMSI, SAMPLE_IMSI);
        columnsInRawTables.put(DATETIME_ID, timestamp);
        columnsInRawTables.put(APN, SAMPLE_APN);
        columnsInRawTables.put(EVENT_TIME, timestamp);
        columnsInRawTables.put(EVENT_SOURCE_NAME, "");
        columnsInRawTables.put(HIERARCHY_1, "");
        columnsInRawTables.put(HIERARCHY_2, "");
        columnsInRawTables.put(HIERARCHY_3, "");
        columnsInRawTables.put(VENDOR_PARAM_UPPER_CASE, ERICSSON);
        columnsInRawTables.put(DURATION, 0);
        columnsInRawTables.put(CAUSE_PROT_TYPE_COLUMN, 0);
        columnsInRawTables.put(CAUSE_CODE_COLUMN, 0);
        columnsInRawTables.put(SUBCAUSE_CODE_COLUMN, 0);
        columnsInRawTables.put(EVENT_RESULT, 0);
        columnsInRawTables.put(ATTACH_TYPE, 0);
        columnsInRawTables.put(DETACH_TYPE, 0);
        columnsInRawTables.put(DEACTIVATION_TRIGGER, 0);
        columnsInRawTables.put(DETACH_TRIGGER, 0);
        columnsInRawTables.put(SERVICE_REQ_TRIGGER, 0);
        columnsInRawTables.put(PAGING_ATTEMPTS, 0);
        columnsInRawTables.put(REQUEST_RETRIES, 0);
        columnsInRawTables.put(MCC, "0");
        columnsInRawTables.put(MNC, "0");
        columnsInRawTables.put(OLD_MCC, "0");
        columnsInRawTables.put(OLD_MNC, "0");
        columnsInRawTables.put(OLD_RAC, 0);
        columnsInRawTables.put(OLD_LAC, 0);
        columnsInRawTables.put(TRANSFERRED_PDP, 0);
        columnsInRawTables.put(HLR, 0);
        columnsInRawTables.put(DROPPED_PDP, 0);
        columnsInRawTables.put(MSISDN, 0);
        return columnsInRawTables;

    }

}
