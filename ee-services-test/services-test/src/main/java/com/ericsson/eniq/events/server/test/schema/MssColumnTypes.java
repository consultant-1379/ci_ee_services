/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.schema;

import java.util.HashMap;
import java.util.Map;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * @author echchik
 *
 */
public class MssColumnTypes {

    private static final String EVENT_ID = "EVENT_ID";

    private static final String DATETIME_ID = "DATETIME_ID";

    private static final String NO_OF_UNANSWERED_CALLS = "NO_OF_UNANSWERED_CALLS";

    private static final String VENDOR = "VENDOR";

    private static final String IMSI = "IMSI";

    private static final String DATE_ID = "DATE_ID";

    private static String HIER3_ID = "HIER3_ID";

    private static String HIER321_ID = "HIER321_ID";

    private static final String EVENT_ID_DESC = "EVENT_ID_DESC";

    private static Map<String, String> columnTypes = new HashMap<String, String>();

    static {

        columnTypes.put(EVNTSRC_ID, UNSIGNED_BIGINT);
        columnTypes.put(HIER3_ID, UNSIGNED_BIGINT_NULLABLE);
        columnTypes.put(HIER321_ID, UNSIGNED_BIGINT_NULLABLE);
        columnTypes.put(_TAC, UNSIGNED_INT_NULLABLE);
        columnTypes.put(EVENT_TIME, TIMESTAMP);
        columnTypes.put(DATETIME_ID, TIMESTAMP);
        columnTypes.put(EVENT_ID, TINYINT);
        columnTypes.put(DATE_ID, DATE);
        columnTypes.put(HOUR_ID, TINYINT);
        columnTypes.put(IMSI, UNSIGNED_BIGINT);
        columnTypes.put(MSISDN, UNSIGNED_BIGINT);
        columnTypes.put(RAT, TINYINT_NULLABLE);

        columnTypes.put(RECORD_SEQ_NUM, UNSIGNED_INT);
        columnTypes.put(NETWORK_CALL_REFERENCE, UNSIGNED_BIGINT);
        columnTypes.put(MCC, VARCHAR_3);
        columnTypes.put(MNC, VARCHAR_3);
        columnTypes.put(RAC, TINYINT);
        columnTypes.put(LAC, UNSIGNED_INT);
        columnTypes.put(IMSI_MCC, VARCHAR_3_NULLABLE);
        columnTypes.put(IMSI_MNC, VARCHAR_3_NULLABLE);
        columnTypes.put(ROAMING, INT);

        columnTypes.put(VENDOR, VARCHAR_20);
        columnTypes.put(EVENT_SOURCE_NAME, VARCHAR_128);
        columnTypes.put(HIERARCHY_1, VARCHAR_128);
        columnTypes.put(HIERARCHY_3, VARCHAR_128);
        columnTypes.put(MANUFACTURER, VARCHAR_128);
        columnTypes.put(MARKETING_NAME, VARCHAR_255);
        columnTypes.put(EVENT_ID_DESC, VARCHAR_255);
        columnTypes.put(RAT_DESC, VARCHAR_128);
        columnTypes.put(GROUP_NAME, VARCHAR_128);

        ////voice related
        columnTypes.put(INTERNAL_CAUSE_CODE, TINYINT);
        columnTypes.put(EXTERNAL_CAUSE_CODE_COLUMN, SMALLINT);
        columnTypes.put(EXTERNAL_PROTOCOL_ID, SMALLINT);
        columnTypes.put(EXTERNAL_PROTOCOL_NAME, VARCHAR_128);
        columnTypes.put(INCOMING_ROUTE, VARCHAR_128);
        columnTypes.put(OUTGOING_ROUTE, VARCHAR_128);
        columnTypes.put(RANAP_CODE, TINYINT);
        columnTypes.put(RANAP_DESC, VARCHAR_128);
        columnTypes.put(BSSMAP_CODE, TINYINT);
        columnTypes.put(BSSMAP_DESC, VARCHAR_128);
        columnTypes.put(FAULT_CODE, UNSIGNED_INT);
        columnTypes.put(INTERNAL_LOCATION_CODE, TINYINT);
        columnTypes.put(BEARER_SERVICE_CODE, TINYINT);
        columnTypes.put(TELE_SERVICE_CODE, TINYINT);
        columnTypes.put(CALL_ID_NUM, UNSIGNED_INT);
        columnTypes.put(TYPE_OF_CALLING_SUB, TINYINT);
        columnTypes.put(CALLING_PARTY_NUM, UNSIGNED_BIGINT);
        columnTypes.put(CALLED_PARTY_NUM, UNSIGNED_BIGINT);
        columnTypes.put(CALLING_SUB_IMSI, UNSIGNED_BIGINT);
        columnTypes.put(CALLED_SUB_IMSI, UNSIGNED_BIGINT);
        columnTypes.put(CALLING_SUB_IMEI, UNSIGNED_BIGINT);
        columnTypes.put(CALLED_SUB_IMEI, UNSIGNED_BIGINT);
        columnTypes.put(MS_ROAMING_NUM, UNSIGNED_BIGINT);
        columnTypes.put(DISCONNECT_PARTY, TINYINT);
        columnTypes.put(CALL_DURATION, UNSIGNED_INT);
        columnTypes.put(SEIZURE_TIME, TIMESTAMP);
        columnTypes.put(ORIGINAL_CALLED_NUM, UNSIGNED_BIGINT);
        columnTypes.put(REDIRECT_NUM, UNSIGNED_BIGINT);
        columnTypes.put(REDIRECT_COUNTER, UNSIGNED_BIGINT);
        columnTypes.put(REDIRECT_IMSI, UNSIGNED_BIGINT);
        columnTypes.put(REDIRECT_SPN, UNSIGNED_BIGINT);
        columnTypes.put(CALL_POSITION, TINYINT);
        columnTypes.put(EOS_INFO, TINYINT);

        columnTypes.put(INTERNAL_CAUSE_CODE_DESC, VARCHAR_255);
        columnTypes.put(FAULT_CODE_DESC, VARCHAR_355);
        columnTypes.put(ADVICE, VARCHAR_128);
        ///location service related
        columnTypes.put(LCS_CLIENT_TYPE, TINYINT);
        columnTypes.put(UNSUC_POSITION_REASON, TINYINT);
        columnTypes.put(TYPE_LOCATION_REQ, TINYINT);
        columnTypes.put(TARGET_MSISDN, UNSIGNED_BIGINT);
        columnTypes.put(TARGET_IMSI, UNSIGNED_BIGINT);
        columnTypes.put(TARGET_IMEI, UNSIGNED_BIGINT);
        columnTypes.put(LCS_CLIENT_ID, UNSIGNED_BIGINT);
        columnTypes.put(POSITION_DELIVERY, TINYINT);

        columnTypes.put(LCS_CLIENT_TYPE_DESC, VARCHAR_255);
        columnTypes.put(UNSUC_POSITION_REASON_DESC, VARCHAR_255);
        columnTypes.put(TYPE_LOCATION_REQ_DESC, VARCHAR_255);

        columnTypes.put(FAULT_CODE_ADVICE_COLUMN, VARCHAR_255);

        ///SMS related
        columnTypes.put(SMS_RESULT, TINYINT);
        columnTypes.put(MSG_TYPE_INDICATOR, TINYINT);
        columnTypes.put(CALLING_SUB_IMEISV, UNSIGNED_BIGINT);
        columnTypes.put(CALLED_SUB_IMEISV, UNSIGNED_BIGINT);
        columnTypes.put(ORIGINATING_NUM, UNSIGNED_BIGINT);
        columnTypes.put(DEST_NUM, UNSIGNED_BIGINT);
        columnTypes.put(SERVICE_CENTRE, UNSIGNED_BIGINT);
        columnTypes.put(ORIGINATING_TIME, TIMESTAMP);
        columnTypes.put(DELIVERY_TIME, TIMESTAMP);

        columnTypes.put(SMS_RESULT_DESC, VARCHAR_255);
        columnTypes.put(MSG_TYPE_INDICATOR_DESC, VARCHAR_255);
        columnTypes.put(OSS_ID, VARCHAR_255);
        columnTypes.put(ACCESS_AREA_CODE, UNSIGNED_BIGINT);
        columnTypes.put(FAULT_CODE, UNSIGNED_BIGINT);

        //For aggregation tables

        columnTypes.put(NO_OF_ERRORS, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_SUCCESSES, UNSIGNED_BIGINT);
        columnTypes.put(NO_OF_UNANSWERED_CALLS, UNSIGNED_BIGINT);
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

}
