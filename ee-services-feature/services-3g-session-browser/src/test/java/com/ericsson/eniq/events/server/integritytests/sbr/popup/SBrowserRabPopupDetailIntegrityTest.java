/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserRabPopupDetailService;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserCoreDetailResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SBrowserRabPopupDetailIntegrityTest extends BaseDataIntegrityTest<SBrowserCoreDetailResult> {

    //dim tables to use for the query
    private static final String TEMP_DIM_E_RAN_RABTYPE = "#DIM_E_RAN_RABTYPE";

    private static final String TEMP_DIM_E_RAN_SESSION_EUL_UE_CATEGORY = "#DIM_E_RAN_SESSION_EUL_UE_CATEGORY";

    private static final String TEMP_DIM_E_RAN_SESSION_HSDPA_UE_CATEGORY = "#DIM_E_RAN_SESSION_HSDPA_UE_CATEGORY";

    private static final String TEMP_DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

    private static final String TEMP_DIM_E_SGEH_TAC = "#DIM_E_SGEH_TAC";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_USER_PLANE_FUNCTION = "#DIM_E_USER_PLANE_FUNCTION";

    //actual data tables
    private static final String TEMP_EVENT_E_RAN_SESSION_RAW = "#EVENT_E_RAN_SESSION_RAW";

    private static final String TEMP_EVENT_E_CORE_SESSION_RAW = "#EVENT_E_CORE_SESSION_RAW";

    private static final String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";

    private static final String TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW = "#EVENT_E_USER_PLANE_CLASSIFICATION_RAW";

    //map of table columns to table names
    private final Map<String, String[]> tableColumnMap = new HashMap<String, String[]>();

    private SBrowserRabPopupDetailService sBrowserRabPopupDetailService;

    @Before
    public void onSetUp() throws Exception {
        sBrowserRabPopupDetailService = new SBrowserRabPopupDetailService();
        attachDependencies(sBrowserRabPopupDetailService);
        //create all the table definitions
        createTables();
        //insert all the dim table data
        insertDimTableData();
        insertUpFunction();
        //setup the raw table data
        insertRanSessionRawData();
        insertCoreSessionRawData();
        insertUserPlaneTcpRawData();
        insertAppRawData();
    }

    @Test
    public void testSBrowserRabPopupDetailServiceWithData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        //get the formatted string for the timestamp
        final String formattedDateTime = DateTimeUtilities.getDateTimeFromMillisecond(1337173200);
        System.err.println("Formatted timestamp : " + formattedDateTime);

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");
        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337208900000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337208900000");
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0000");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(EVENT_ID_PARAM, "20000");
        requestParameters.putSingle(IMSI_PARAM, "12356745");

        final String json = runQuery(sBrowserRabPopupDetailService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");

        final JSONObject applicationPerformance = details.getJSONObject("Application Performance");
        assertThat(applicationPerformance.getString("Downlink Volume (Bytes)"), is("400"));
        assertThat(applicationPerformance.getString("Uplink Volume (Bytes)"), is("400"));
        final JSONObject applicationTrafficMix = details.getJSONObject("Application Traffic Mix");

        assertThat(applicationTrafficMix.getString("web-browsing (Bytes)"), is("400"));
        assertThat(applicationTrafficMix.getString("Unclassified (Bytes)"), is("400"));
    }

    @Test
    public void testSBrowserRabPopupDetailServiceWithNoData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");
        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337208900000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337208900000");
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0000");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(EVENT_ID_PARAM, "20000");
        requestParameters.putSingle(IMSI_PARAM, "12356711");

        final String json = runQuery(sBrowserRabPopupDetailService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");

        assertThat(details.length(), is(8));
    }

    @SuppressWarnings("serial")
    private void createTables() throws Exception {
        //create the definitions for the dim tables
        tableColumnMap.put(TEMP_DIM_E_RAN_RABTYPE, new String[] { "RABTYPE", RAB_TYPE_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_SESSION_EUL_UE_CATEGORY,
                new String[] { EUL_UE_CATEGORY, EUL_UE_CATEGORY_DESC });
        tableColumnMap.put(TEMP_DIM_E_SGEH_MCCMNC, new String[] { MCC, MNC, COUNTRY, OPERATOR });
        tableColumnMap.put(TEMP_DIM_E_SGEH_TAC, new String[] { TAC, VENDOR_NAME, MARKETING_NAME });
        tableColumnMap.put(TEMP_DIM_E_SGEH_HIER321, new String[] { HIER3_ID, HIERARCHY_3 });

        //create the definition for the ran session raw table

        tableColumnMap.put(TEMP_EVENT_E_RAN_SESSION_RAW, new String[] { IMSI, EVENT_ID, DATETIME_ID, EVENT_TIME, _TAC,
                CM_CNT, CM_DURATION, CM_UL_CNT, CM_DL_CNT, CM_ULDL_CNT, CM_USER_CNT, EUL_UE_CATEGORY,
                HSDPA_UE_CATEGORY, START_RAB_COLUMN, END_RAB_COLUMN, RRC_CONN_START, PS_RAB_ACTIVITY_START_TIME,
                RRC_CONN_END, PS_RAB_ACTIVITY_END_TIME, RRC_CONNECTION_CNT, PS_RAB_ESTABLISH_CNT, IMSI_MCC, IMSI_MNC,
                START_HIER3_ID, END_HIER3_ID, START_CELL_ID, END_CELL_ID, START_C_ID, END_C_ID, RRC_MEAS_REP_SAMPLES,
                RRC_SAMPLES_GC_BS, RRC_SAMPLES_GC_GS, RRC_SAMPLES_BC_BS, RRC_SAMPLES_BC_GS, ECNO_AVG, RSCP_AVG,
                UL_INTERFERENCE_AVG, DL_NON_HS_TX_POWER_AVG, HSDSCH_AVG_USERS, DISTINCT_CELL_CNT, IFHO_EXEC_SUC_CNT,
                IFHO_EXEC_ERR_CNT, SOHO_EXEC_SUC_CNT, SOHO_EXEC_ERR_CNT, IRAT_EXEC_ERR_CNT, HS_CELL_CHANGE_SUC_CNT,
                HS_CELL_CHANGE_ERR_CNT, ACTIVITY, HS_RATIO, EUL_RATIO, CUS_SUCC_COUNT, CDS_SUCC_COUNT, CUS_ATT_CNT,
                CDS_ATT_CNT, CS_REASON_UE_ACTIVITY, CS_REASON_CAPACITY, CS_REASON_MOBILITY_COVERAGE, CS_REASON_QOS_DCH,
                CS_REASON_QUEUE, CS_REASON_OTHER });

        //create the definition for the core session raw table
        tableColumnMap.put(TEMP_EVENT_E_CORE_SESSION_RAW, new String[] { EVENT_TIME, EVENT_ID,

                // core
                IMSI, DATETIME_ID, APN_ACTIVITY_LATEST_TIME, APN_ACTIVITY_START_TIME, START_APN, END_APN, RAU_ATT_CNT,
                RAU_SUC_CNT,

                // start sgsn
                EVENT_SOURCE_NAME, SGSN_ACTIVITY_START_TIME, MSISDN, TAC,

                // end sgsn
                SGSN_ACTIVITY_LATEST_TIME });

        //create the definition for the user plane tcp raw table
        tableColumnMap.put(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new String[] { EVENT_TIME, EVENT_ID, DATETIME_ID, IMSI_MCC,
                IMSI_MNC, FIVE_MIN_AGG_TIME, MSISDN, START_APN, END_APN, TAC, ACTIVITY_START_TIME, IMSI, UE_IP_ADDRESS,
                DOWNLINK, DATA_RECEIVED, SETUP_TIME_TERM, SETUP_TIME_NET, PACKET_LOSS_TERM, PACKET_LOSS_NET });

        for (final Map.Entry<String, String[]> entry : tableColumnMap.entrySet()) {
            //create a temporary table with given table name and columns
            createTemporaryTable(entry.getKey(), Arrays.asList(entry.getValue()));
        }

        createTemporaryTable(TEMP_DIM_E_RAN_SESSION_HSDPA_UE_CATEGORY, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HSDPA_UE_CATEGORY", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HSDPA_UE_CATEGORY_DESC", "varchar(20)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BYTES_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BYTES_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FUNCTION", "unsigned int", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_USER_PLANE_FUNCTION, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("FUNCTION", "unsigned int", Nullable.CAN_BE_NULL));
            }
        });

    }

    private void insertTableData(final String tableName, final Object[] columnValues) throws Exception {
        final Map<String, Object> dataForTable = new HashMap<String, Object>();
        final String[] columnNames = tableColumnMap.get(tableName);
        //add data to map
        for (int i = 0; i < columnNames.length; i++)
            dataForTable.put(columnNames[i], columnValues[i]);

        //insert row into table
        insertRow(tableName, dataForTable);
    }

    @SuppressWarnings("serial")
    private void insertDimTableData() throws Exception {
        //insert data for dim table TEMP_DIM_E_SGEH_TAC
        insertTableData(TEMP_DIM_E_SGEH_TAC, new Object[] { 100100, "Mitsubishi", "G410" });
        //to test when vendor and marketing name is null then should show tac

        //insert data for dim table TEMP_DIM_E_SGEH_MCCMNC
        insertTableData(TEMP_DIM_E_SGEH_MCCMNC, new Object[] { "454", "036", "China", "Smartone" });
        //to test when country and operator are null then should show 'Unknown operator from <Country>"

        insertTableData(TEMP_DIM_E_RAN_SESSION_EUL_UE_CATEGORY, new Object[] { 24, "2" });
        insertTableData(TEMP_DIM_E_RAN_RABTYPE, new Object[] { 1, "Test Rab Desc" });
        insertTableData(TEMP_DIM_E_SGEH_HIER321, new Object[] { 12345678, "smartone_R:RNC09:RNC09" });

        insertRow(TEMP_DIM_E_RAN_SESSION_HSDPA_UE_CATEGORY, new HashMap<String, Object>() {
            {
                put("HSDPA_UE_CATEGORY", 83);
                put("HSDPA_UE_CATEGORY_DESC", "83_DESC");
            }
        });
    }

    private void insertRanSessionRawData() throws Exception {
        final Map<String, Object> dataForRanSessionRaw = new HashMap<String, Object>();
        dataForRanSessionRaw.put(IMSI, 12356745);
        dataForRanSessionRaw.put(EVENT_ID, 20000);
        dataForRanSessionRaw.put(DATETIME_ID, "2012-05-16 22:55:00.000");
        dataForRanSessionRaw.put(EVENT_TIME, "2012-05-16 16:00:00.000");
        dataForRanSessionRaw.put(_TAC, 100100);
        dataForRanSessionRaw.put(CM_CNT, 1222);
        dataForRanSessionRaw.put(CM_DURATION, 5546);
        dataForRanSessionRaw.put(CM_UL_CNT, 7551);
        dataForRanSessionRaw.put(CM_DL_CNT, 2341);
        dataForRanSessionRaw.put(CM_ULDL_CNT, 6712);
        dataForRanSessionRaw.put(CM_USER_CNT, 1342);
        dataForRanSessionRaw.put(EUL_UE_CATEGORY, 24);
        dataForRanSessionRaw.put(HSDPA_UE_CATEGORY, 83);
        dataForRanSessionRaw.put(START_RAB_COLUMN, 1);
        dataForRanSessionRaw.put(END_RAB_COLUMN, 1);
        dataForRanSessionRaw.put(RRC_CONN_START, "2012-05-16 15:00:00.000");
        dataForRanSessionRaw.put(PS_RAB_ACTIVITY_START_TIME, "2012-05-16 15:00:00.000");
        dataForRanSessionRaw.put(RRC_CONN_END, "2012-05-18 15:00:00.000");
        dataForRanSessionRaw.put(PS_RAB_ACTIVITY_END_TIME, "2012-05-18 15:00:00.000");
        dataForRanSessionRaw.put(RRC_CONNECTION_CNT, 1);
        dataForRanSessionRaw.put(PS_RAB_ESTABLISH_CNT, 41);
        dataForRanSessionRaw.put(IMSI_MCC, "454");
        dataForRanSessionRaw.put(IMSI_MNC, "036");
        dataForRanSessionRaw.put(START_HIER3_ID, 12345678);
        dataForRanSessionRaw.put(END_HIER3_ID, 12345678);
        dataForRanSessionRaw.put(START_CELL_ID, "4");
        dataForRanSessionRaw.put(END_CELL_ID, "4");
        dataForRanSessionRaw.put(START_C_ID, 1665);
        dataForRanSessionRaw.put(END_C_ID, 1434);
        dataForRanSessionRaw.put(RRC_MEAS_REP_SAMPLES, 1453);
        dataForRanSessionRaw.put(RRC_SAMPLES_GC_BS, 1476);
        dataForRanSessionRaw.put(RRC_SAMPLES_GC_GS, 1445);
        dataForRanSessionRaw.put(RRC_SAMPLES_BC_BS, 1686);
        dataForRanSessionRaw.put(RRC_SAMPLES_BC_GS, 1427);
        dataForRanSessionRaw.put(ECNO_AVG, 1.5);
        dataForRanSessionRaw.put(RSCP_AVG, 5);
        dataForRanSessionRaw.put(UL_INTERFERENCE_AVG, 1.9);
        dataForRanSessionRaw.put(DL_NON_HS_TX_POWER_AVG, 1678);
        dataForRanSessionRaw.put(HSDSCH_AVG_USERS, 17);
        dataForRanSessionRaw.put(DISTINCT_CELL_CNT, 1438);
        dataForRanSessionRaw.put(IFHO_EXEC_SUC_CNT, 1239);
        dataForRanSessionRaw.put(IFHO_EXEC_ERR_CNT, 1236);
        dataForRanSessionRaw.put(SOHO_EXEC_SUC_CNT, 1323);
        dataForRanSessionRaw.put(SOHO_EXEC_ERR_CNT, 1435);
        dataForRanSessionRaw.put(IRAT_EXEC_ERR_CNT, 1340);
        dataForRanSessionRaw.put(HS_CELL_CHANGE_SUC_CNT, 1762);
        dataForRanSessionRaw.put(HS_CELL_CHANGE_ERR_CNT, 3412);
        dataForRanSessionRaw.put(ACTIVITY, 0.8);
        dataForRanSessionRaw.put(HS_RATIO_COLUMN, 1.0);
        dataForRanSessionRaw.put(EUL_RATIO_COLUMN, 1.4);
        dataForRanSessionRaw.put(CUS_SUCC_COUNT, 12);
        dataForRanSessionRaw.put(CDS_SUCC_COUNT, 12);
        dataForRanSessionRaw.put(CUS_ATT_CNT, 12);
        dataForRanSessionRaw.put(CDS_ATT_CNT, 12);
        dataForRanSessionRaw.put(CS_REASON_UE_ACTIVITY, 31);
        dataForRanSessionRaw.put(CS_REASON_CAPACITY, 12);
        dataForRanSessionRaw.put(CS_REASON_MOBILITY_COVERAGE, 41);
        dataForRanSessionRaw.put(CS_REASON_QOS_DCH, 12);
        dataForRanSessionRaw.put(CS_REASON_QUEUE, 21);
        dataForRanSessionRaw.put(CS_REASON_OTHER, 13);

        insertRow(TEMP_EVENT_E_RAN_SESSION_RAW, dataForRanSessionRaw);
    }

    private void insertCoreSessionRawData() throws Exception {
        final Map<String, Object> dataForCoreSessionRaw = new HashMap<String, Object>();
        dataForCoreSessionRaw.put(IMSI, 12356745);
        dataForCoreSessionRaw.put(EVENT_TIME, "2012-05-16 16:55:00.000");
        dataForCoreSessionRaw.put(EVENT_ID, 20000);
        dataForCoreSessionRaw.put(DATETIME_ID, "2012-05-15 22:55:00.000");
        dataForCoreSessionRaw.put(APN_ACTIVITY_LATEST_TIME, "2012-05-18 16:00:00.000");
        dataForCoreSessionRaw.put(APN_ACTIVITY_START_TIME, "2012-05-16 16:00:00.000");
        dataForCoreSessionRaw.put(START_APN, "s");
        dataForCoreSessionRaw.put(END_APN, "R");
        dataForCoreSessionRaw.put(RAU_ATT_CNT, 5);
        dataForCoreSessionRaw.put(RAU_SUC_CNT, 4);
        dataForCoreSessionRaw.put(EVENT_SOURCE_NAME, "9");
        dataForCoreSessionRaw.put(SGSN_ACTIVITY_START_TIME, "2012-05-16 16:00:00.000");
        dataForCoreSessionRaw.put(MSISDN, "987654321");
        dataForCoreSessionRaw.put(_TAC, 100100);
        dataForCoreSessionRaw.put(SGSN_ACTIVITY_LATEST_TIME, "2012-05-18 16:00:00.000");

        insertRow(TEMP_EVENT_E_CORE_SESSION_RAW, dataForCoreSessionRaw);
    }

    private void insertUserPlaneTcpRawData() throws Exception {
        final Map<String, Object> dataForTcpRaw = new HashMap<String, Object>();
        dataForTcpRaw.put(IMSI, 12356745);
        dataForTcpRaw.put(EVENT_TIME, "2012-05-16 22:55:00.000");
        dataForTcpRaw.put(EVENT_ID, 20000);
        dataForTcpRaw.put(DATETIME_ID, "2012-05-16 22:55:00.000");
        dataForTcpRaw.put(IMSI_MCC, "454");
        dataForTcpRaw.put(IMSI_MNC, "036");
        dataForTcpRaw.put(FIVE_MIN_AGG_TIME, "2012-05-16 22:55:00.000");
        dataForTcpRaw.put(MSISDN, 987654321);
        dataForTcpRaw.put(START_APN, "s");
        dataForTcpRaw.put(END_APN, "R");
        dataForTcpRaw.put(_TAC, 100100);
        dataForTcpRaw.put(ACTIVITY_START_TIME, "2012-05-16 23:56:00.000");
        dataForTcpRaw.put(UE_IP_ADDRESS, "1");
        dataForTcpRaw.put(DOWNLINK, 0);
        dataForTcpRaw.put(DATA_RECEIVED, "5");
        dataForTcpRaw.put(SETUP_TIME_TERM, "1");
        dataForTcpRaw.put(SETUP_TIME_NET, "1");
        dataForTcpRaw.put(PACKET_LOSS_TERM, "3");
        dataForTcpRaw.put(PACKET_LOSS_NET, "4");

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, dataForTcpRaw);
    }

    @SuppressWarnings("serial")
    private void insertAppRawData() throws Exception {
        Map<String, Object> dataForAppRaw = new HashMap<String, Object>() {
            {
                put(IMSI, 12356745);
                put(MSISDN, 987654321);
                put(_TAC, 100100);
                put(EVENT_TIME, "2012-05-16 22:56:00.001");
                put(EVENT_ID, 20000);
                put(DATETIME_ID, "2012-05-16 22:55:00.000");
                put("BYTES_DOWNLINK", 100);
                put("BYTES_UPLINK", 300);
                put("FIVE_MIN_AGG_TIME", "2012-05-16 22:55:00.000");
                put("FUNCTION", 2);
            }
        };
        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, dataForAppRaw);
        dataForAppRaw = new HashMap<String, Object>() {
            {
                put(IMSI, 12356745);
                put(MSISDN, 987654321);
                put(_TAC, 100100);
                put(EVENT_TIME, "2012-05-16 22:57:00.001");
                put(EVENT_ID, 20000);
                put(DATETIME_ID, "2012-05-16 22:55:00.000");
                put("BYTES_DOWNLINK", 300);
                put("BYTES_UPLINK", 100);
                put("FIVE_MIN_AGG_TIME", "2012-05-16 22:55:00.000");
                put("FUNCTION", -1);
            }
        };
        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, dataForAppRaw);
    }

    private void insertUpFunction() throws Exception {
        final Map<String, Object> dataForAppRaw = new HashMap<String, Object>();
        dataForAppRaw.put("FUNCTION", 2);

        insertRow(TEMP_DIM_E_USER_PLANE_FUNCTION, dataForAppRaw);
    }
}
