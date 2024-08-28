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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserRanCfaPopupDetailService;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserCoreDetailResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eeidpar, edarbla
 * @since 2012
 *
 */

public class SBrowserCFAPopupIntegrityTest extends BaseDataIntegrityTest<SBrowserCoreDetailResult> {

    //dim tables to use for the query
    private static final String TEMP_DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

    private static final String TEMP_DIM_E_RAN_CFA_EVENTTYPE = "#DIM_E_RAN_CFA_EVENTTYPE";

    private static final String TEMP_DIM_E_RAN_CFA_ORIGINATING_STATE = "#DIM_E_RAN_CFA_ORIGINATING_STATE";

    private static final String TEMP_DIM_E_RAN_CFA_AAL2NCI_REJECT = "#DIM_E_RAN_CFA_AAL2NCI_REJECT";

    private static final String TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE = "#DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE";

    private static final String TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE = "#DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE";

    private static final String TEMP_DIM_E_RAN_CFA_TRIGGER_POINT = "#DIM_E_RAN_CFA_TRIGGER_POINT";

    private static final String TEMP_DIM_E_RAN_RSCP_MAPPING = "#DIM_E_RAN_RSCP_MAPPING";

    private static final String TEMP_DIM_E_RAN_ECNO_MAPPING = "#DIM_E_RAN_ECNO_MAPPING";

    private static final String TEMP_DIM_E_RAN_ULINT_MAPPING = "#DIM_E_RAN_ULINT_MAPPING";

    private static final String TEMP_DIM_E_RAN_RABTYPE = "#DIM_E_RAN_RABTYPE";

    private static final String TEMP_DIM_E_RAN_CFA_DISCONNECTION = "#DIM_E_RAN_CFA_DISCONNECTION";

    private static final String TEMP_DIM_E_SGEH_TAC = "#DIM_E_SGEH_TAC";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_DIM_E_RAN_RNCFUNCTION = "#DIM_E_RAN_RNCFUNCTION";

    private static final String TEMP_DIM_E_RAN_RNC = "#DIM_E_RAN_RNC";

    //actual data table
    private static final String TEMP_EVENT_E_RAN_CFA_ERR_RAW = "#EVENT_E_RAN_CFA_ERR_RAW";

    //map of table columns to table names
    private final Map<String, String[]> tableColumnMap = new HashMap<String, String[]>();

    //the timestamps for the various events
    private long timeStampCfaISR1;

    private long timeStampCfaISR2;

    private long timeStampCfaCSF1;

    private long timeStampCfaCSF2;

    private SBrowserRanCfaPopupDetailService sBrowserRanCfaPopupDetailService;

    @Before
    public void onSetUp() throws Exception {
        sBrowserRanCfaPopupDetailService = new SBrowserRanCfaPopupDetailService();
        attachDependencies(sBrowserRanCfaPopupDetailService);
        //create all the table definitions
        createTables();
        //insert all the dim table data
        insertDimTableData();
        //setup the raw table data
        insertCfaInternalSystemReleaseData();
    }

    @Test
    public void testGetPopupDetails_CFA_InternalSystemRelease() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        //get the formatted string for the timestamp
        final String formattedDateTime = DateTimeUtilities.getDateTimeFromMillisecond(1337186797);
        final String formattedDateTimeTo = DateTimeUtilities.getDateTimeFromMillisecond(1337356800);
        System.err.println("Formatted timestamp : " + formattedDateTime);

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "18052012");
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1600");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "1900");
        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337186797000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337356800000");
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(EVENT_ID_PARAM, "438");
        requestParameters.putSingle(IMSI_PARAM, "12345");
        final String json = runQuery(sBrowserRanCfaPopupDetailService, requestParameters);
        System.out.println(json);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject eventDetails = details.getJSONObject("Event Details");
        final JSONObject activeSetMeasurements = details.getJSONObject("Active Set Measurements");

        assertThat(subscriberIdentity.getString(IMSI), is("12345"));
        assertThat(networkLocation.getString(PLMN), is("45406"));
        assertThat(eventDetails.getString("UTRAN RANAP Cause"), is("Test Cause Desc"));
    }

    private void createTables() throws Exception {

        //create the definitions for the dim tables
        tableColumnMap.put(TEMP_DIM_E_SGEH_TAC, new String[] { TAC, VENDOR_NAME, MARKETING_NAME });
        tableColumnMap.put(TEMP_DIM_E_SGEH_MCCMNC, new String[] { MCC, MNC, COUNTRY, OPERATOR });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new String[] { EVENT_ID, EVENT_ID_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_ORIGINATING_STATE, new String[] { ORIGINATING_STATE,
                ORIGINATING_STATE_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_AAL2NCI_REJECT, new String[] { CELLO_AAL2NCI_REJECT_REASON,
                CELLO_AAL2NCI_REJECT_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE, new String[] { RRC_ESTABLISHMENT_CAUSE,
                RRC_ESTABLISHMENT_CAUSE_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE, new String[] { UTRAN_RANAP_CAUSE,
                UTRAN_RANAP_CAUSE_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_TRIGGER_POINT, new String[] { TRIGGER_POINT, TRIGGER_POINT_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_RSCP_MAPPING, new String[] { RSCP_COLUMN, RSCP_DBM_COLUMN });
        tableColumnMap.put(TEMP_DIM_E_RAN_ECNO_MAPPING, new String[] { ECNO_COLUMN, ECNO_DBM_COLUMN });
        tableColumnMap.put(TEMP_DIM_E_RAN_ULINT_MAPPING, new String[] { ULINT_COLUMN, ULINT_DBM_COLUMN });
        tableColumnMap.put(TEMP_DIM_E_RAN_RNCFUNCTION, new String[] { RNCID, SN });
        tableColumnMap.put(TEMP_DIM_E_RAN_RNC, new String[] { RNC_FDN, ALTERNATIVE_FDN });
        tableColumnMap.put(TEMP_DIM_E_RAN_RABTYPE, new String[] { com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAB_TYPE, RAB_TYPE_DESC });
        tableColumnMap.put(TEMP_DIM_E_RAN_CFA_DISCONNECTION, new String[] { com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DISCONNECTION_CODE, DISCONNECTION_DESC });
        tableColumnMap.put(TEMP_DIM_E_SGEH_HIER321_CELL, new String[] { HIERARCHY_3, HIER3_CELL_ID, CID, CELL_ID });

        //create the definition for the raw table
        tableColumnMap.put(TEMP_EVENT_E_RAN_CFA_ERR_RAW, new String[] { EVENT_TIME, EVENT_ID, DATETIME_ID, MSISDN,
                IMSI, TAC, IMSI_MCC, IMSI_MNC, LAC, RAC, SOURCE_CONNECTION_PROPERTIES, TARGET_CONNECTION_PROPERTIES,
                WANTED_CONNECTION_PROPERTIES, GBR_UL, GBR_DL, DATA_IN_DL_RLC_BUFFERS, C_ID_SERV_HSDSCH_CELL,
                CRNC_ID_SERV_HSDSCH_CELL, SCRAMBLING_CODE_CELL_1, SCRAMBLING_CODE_CELL_2, SCRAMBLING_CODE_CELL_3,
                SCRAMBLING_CODE_CELL_4, SCRAMBLING_CODE_ADDED_CELL, RAN_DISCONNECTION_CODE, RAN_DISCONNECTION_SUBCODE,
                HIER3_CELL_ID, PROCEDURE_INDICATOR, EVALUATION_CASE, EXCEPTION_CLASS, EXTENDED_CAUSE_VALUE,
                CAUSE_VALUE, SEVERITY_INDICATOR, ORIGINATING_STATE, CELLO_AAL2NCI_REJECT_REASON,
                RRC_ESTABLISHMENT_CAUSE, UTRAN_RANAP_CAUSE, CN_RANAP_CAUSE, TRIGGER_POINT, RSCP_CELL_1, RSCP_CELL_2,
                RSCP_CELL_3, RSCP_CELL_4, RSCP_CELL_1_ADDED_CELL, CPICH_EC_NO_CELL_1, CPICH_EC_NO_CELL_2,
                CPICH_EC_NO_CELL_3, CPICH_EC_NO_CELL_4, CPICH_EC_NO_ADDED_CELL, UL_INT_CELL1, UL_INT_CELL2,
                UL_INT_CELL3, UL_INT_CELL4, SOURCE_CONF, TARGET_CONF, WANTED_CONF });

        for (final Map.Entry<String, String[]> entry : tableColumnMap.entrySet()) {
            //create a temporary table with given table name and columns
            createTemporaryTable(entry.getKey(), Arrays.asList(entry.getValue()));
        }
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

    private void insertDimTableData() throws Exception {
        //insert data for dim table TEMP_DIM_E_SGEH_TAC
        insertTableData(TEMP_DIM_E_SGEH_TAC, new Object[] { 100100, "Mitsubishi", "G410" });
        //to test when vendor and marketing name is null then should show tac
        insertTableData(TEMP_DIM_E_SGEH_TAC, new Object[] { 100200, null, null });

        //insert data for dim table TEMP_DIM_E_SGEH_MCCMNC
        insertTableData(TEMP_DIM_E_SGEH_MCCMNC, new Object[] { "454", "06", "China", "Smartone" });
        //to test when country and operator are null then should show 'Unknown operator from <Country>"
        insertTableData(TEMP_DIM_E_SGEH_MCCMNC, new Object[] { "200", "08", null, null });

        //insert data for dim table TEMP_DIM_E_RAN_CFA_EVENTTYPE
        insertTableData(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new Object[] { 438, "INTERNAL_SYSTEM_RELEASE" });
        insertTableData(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new Object[] { 456, "INTERNAL_CALL_SETUP_FAIL" });

        //insert data for dim table TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE
        insertTableData(TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE, new Object[] { 46, "Test Cause Desc" });

    }

    private void insertCfaInternalSystemReleaseData() throws Exception {
        timeStampCfaISR1 = System.currentTimeMillis();
        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(EVENT_ID, 438);
        dataForEventTable.put(EVENT_TIME, "2012-05-16 16:46:37.000");
        dataForEventTable.put(DATETIME_ID, "2012-05-16 16:46:37.000");
        dataForEventTable.put(MSISDN, "2345678");
        dataForEventTable.put(IMSI, "12345");
        dataForEventTable.put(TAC, 100100);
        dataForEventTable.put(IMSI_MCC, "454");
        dataForEventTable.put(IMSI_MNC, "06");
        dataForEventTable.put(LAC, 8900);
        dataForEventTable.put(RAC, 3);
        dataForEventTable.put(SOURCE_CONNECTION_PROPERTIES, 512);
        dataForEventTable.put(TARGET_CONNECTION_PROPERTIES, 512);
        dataForEventTable.put(WANTED_CONNECTION_PROPERTIES, 512);
        dataForEventTable.put(GBR_UL, 0);
        dataForEventTable.put(GBR_DL, 0);
        dataForEventTable.put(DATA_IN_DL_RLC_BUFFERS, 1);
        dataForEventTable.put(C_ID_SERV_HSDSCH_CELL, 21849);
        dataForEventTable.put(CRNC_ID_SERV_HSDSCH_CELL, 9);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_1, 138);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_2, 286);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_3, 167);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_4, 175);
        dataForEventTable.put(SCRAMBLING_CODE_ADDED_CELL, 122);
        dataForEventTable.put(RAN_DISCONNECTION_CODE, 1);
        dataForEventTable.put(RAN_DISCONNECTION_SUBCODE, 2);
        dataForEventTable.put(HIER3_CELL_ID, 20001);
        dataForEventTable.put(PROCEDURE_INDICATOR, 3);
        dataForEventTable.put(EVALUATION_CASE, 4);
        dataForEventTable.put(EXCEPTION_CLASS, 15);
        dataForEventTable.put(EXTENDED_CAUSE_VALUE, 43);
        dataForEventTable.put(CAUSE_VALUE, 69);
        dataForEventTable.put(SEVERITY_INDICATOR, 1);
        dataForEventTable.put(ORIGINATING_STATE, 4);
        dataForEventTable.put(CELLO_AAL2NCI_REJECT_REASON, 0);
        dataForEventTable.put(RRC_ESTABLISHMENT_CAUSE, 2);
        dataForEventTable.put(UTRAN_RANAP_CAUSE, 46);
        dataForEventTable.put(CN_RANAP_CAUSE, 11);
        dataForEventTable.put(TRIGGER_POINT, 2);
        dataForEventTable.put(RSCP_CELL_1, 2);
        dataForEventTable.put(RSCP_CELL_2, 10);
        dataForEventTable.put(RSCP_CELL_3, 14);
        dataForEventTable.put(RSCP_CELL_4, 22);
        dataForEventTable.put(RSCP_CELL_1_ADDED_CELL, 30);
        dataForEventTable.put(CPICH_EC_NO_CELL_1, 20);
        dataForEventTable.put(CPICH_EC_NO_CELL_2, 14);
        dataForEventTable.put(CPICH_EC_NO_CELL_3, 8);
        dataForEventTable.put(CPICH_EC_NO_CELL_4, 16);
        dataForEventTable.put(CPICH_EC_NO_ADDED_CELL, 34);
        dataForEventTable.put(UL_INT_CELL1, 13);
        dataForEventTable.put(UL_INT_CELL2, 20);
        dataForEventTable.put(UL_INT_CELL3, 26);
        dataForEventTable.put(UL_INT_CELL4, 38);
        dataForEventTable.put(SOURCE_CONF, 53);
        dataForEventTable.put(TARGET_CONF, 1);
        dataForEventTable.put(WANTED_CONF, 25);

        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, dataForEventTable);
    }
}
