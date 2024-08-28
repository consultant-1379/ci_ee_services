/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserRanHfaHsdschDetailService;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.utils.sbr.ConnectionPropertiesDecoder;
import com.ericsson.eniq.events.ui.shared.model.ConnectionPropertiesKeys;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author epstvxj
 * @since 2012
 *
 */
public class SBrowserRanHfaHsdschPopupDetailIntegrityTest extends BaseDataIntegrityTest<QueryResult> {

    private static final String TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW = "#EVENT_E_RAN_HFA_HSDSCH_ERR_RAW";

    private static final String TEMP_DIM_E_SGEH_TAC = "#DIM_E_SGEH_TAC";

    private static final String TEMP_DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE = "#DIM_E_RAN_HFA_CAUSE_CODE_VALUE";

    private static final String TEMP_DIM_E_RAN_ECNO_MAPPING = "#DIM_E_RAN_ECNO_MAPPING";

    private static final String TEMP_DIM_E_RAN_RSCP_MAPPING = "#DIM_E_RAN_RSCP_MAPPING";

    private static final String TEMP_DIM_E_RAN_RABTYPE = "#DIM_E_RAN_RABTYPE";

    private static final String TEMP_DIM_E_RAN_HFA_EVENT_TRIGGER = "#DIM_E_RAN_HFA_EVENT_TRIGGER";

    private SBrowserRanHfaHsdschDetailService sessionBrowserRanHfaHsdschDetailService;

    @Before
    public void setUp() throws Exception {
        sessionBrowserRanHfaHsdschDetailService = new SBrowserRanHfaHsdschDetailService();
        attachDependencies(sessionBrowserRanHfaHsdschDetailService);

        createTableSchema();
        populateTestData();
    }

    @Test
    public void testRanHfaHsdschPopupDetailService() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "17052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "454063306049984");

        requestParameters.putSingle(EVENT_ID_PARAM, "433");

        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337244895252");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337244896252");

        final String json = runQuery(sessionBrowserRanHfaHsdschDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");

        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        assertThat(subscriberIdentity.getString("MSISDN"), is("-"));
        assertThat(subscriberIdentity.getString("IMSI"), is("454063306049984"));
        assertThat(subscriberIdentity.getString("Terminal"), is("-"));
        assertThat(subscriberIdentity.getString("Network"), is("Hong Kong-PRC,SmarTone"));

        final JSONObject handoverDetails = details.getJSONObject("Handover Details");
        assertThat(handoverDetails.getString("Source Cell Id"), is("82095E"));
        assertThat(handoverDetails.getString("Source CID"), is("20950"));
        assertThat(handoverDetails.getString("Source RNC"), is("smartone_R:RNC09:RNC09"));
        assertThat(handoverDetails.getString("Source RAC"), is("2"));
        assertThat(handoverDetails.getString("Source LAC"), is("2"));
        assertThat(handoverDetails.getString("Source Sec. Serving HSDSCH Cell CID"), is("1"));
        assertThat(handoverDetails.getString("Target Cell Id"), is("82095E"));
        assertThat(handoverDetails.getString("Target CID"), is("20950"));
        assertThat(handoverDetails.getString("Target RNC"), is("smartone_R:RNC09:RNC09"));
        assertThat(handoverDetails.getString("Target Sec. Serving HSDSCH Cell CID"), is("1"));

        assertThat(handoverDetails.getString("Ec/No Source Cell (dB)"), is("-14.0"));
        assertThat(handoverDetails.getString("Ec/No Target Cell (dB)"), is("-8.0"));
        assertThat(handoverDetails.getString("RSCP Source Cell (dBm)"), is("-77.0"));
        assertThat(handoverDetails.getString("RSCP Target Cell (dBm)"), is("-71.0"));

        final JSONObject eventDetails = details.getJSONObject("Event Details");
        assertThat(eventDetails.getString("Event Time"), is("1337244895252"));
        assertThat(eventDetails.getString("Event Trigger"), is("EVENT_1D"));
        assertThat(eventDetails.getString("RAB Type"), is("INTERACTIVE_PS_EUL_HS"));
        assertThat(eventDetails.getString("Cause Value"), is("TOO_MANY_SERVING_CELL_USERS_EUL"));
        assertThat(eventDetails.getString("GBR Uplink (bps)"), is("0"));
        assertThat(eventDetails.getString("GBR Downlink (bps)"), is("0"));
        assertThat(eventDetails.getString("Source Connection Prop"),
                is(ConnectionPropertiesDecoder.decode(516, ConnectionPropertiesKeys.HFA_SOURCE_CONNECTION_PROPERTIES)));

        final JSONObject activeSets = details.getJSONObject("Active Set Details");
        assertThat(activeSets.getString("Source Cell ID 1"), is("82095E"));
        assertThat(activeSets.getString("Source CID 1"), is("20950"));
        assertThat(activeSets.getString("Source RNC 1"), is("smartone_R:RNC09:RNC09"));

        assertThat(activeSets.getString("Source Cell ID 2"), is("82095E"));
        assertThat(activeSets.getString("Source CID 2"), is("20950"));
        assertThat(activeSets.getString("Source RNC 2"), is("smartone_R:RNC09:RNC09"));

        assertThat(activeSets.getString("Source Cell ID 3"), is("82095E"));
        assertThat(activeSets.getString("Source CID 3"), is("20950"));
        assertThat(activeSets.getString("Source RNC 3"), is("smartone_R:RNC09:RNC09"));

        assertThat(activeSets.getString("Source Cell ID 4"), is("82095E"));
        assertThat(activeSets.getString("Source CID 4"), is("20950"));
        assertThat(activeSets.getString("Source RNC 4"), is("smartone_R:RNC09:RNC09"));
    }

    @Test
    public void testRanHfaHsdschPopupDetailServiceWithNoData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "19052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "19052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");

        requestParameters.putSingle(EVENT_ID_PARAM, "433");

        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1330000005252");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1330000006252");

        final String json = runQuery(sessionBrowserRanHfaHsdschDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");

        assertThat(details.length(), is(0));

    }

    @SuppressWarnings("serial")
    private void populateTestData() throws Exception {
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "454063306049984");
                put("EVENT_ID", "433");
                put("IMSI_MCC", "454");
                put("IMSI_MNC", "06");
                put("THIER3_CELL_ID", "1593936201967327430");
                put("CPICH_EC_NO_TARGET_CELL", 33);
                put("CPICH_EC_NO_SOURCE_CELL", 21);
                put("CPICH_RSCP_TARGET_CELL", 45);
                put("CPICH_RSCP_SOURCE_CELL", 39);
                put("SRC_C_ID_1_SS_HSDSCH_CELL", 1);
                put("TRGT_C_ID_1_SS_HSDSCH_CELL", 1);
                put("CAUSE_VALUE", 71);
                put("SOURCE_CONNECTION_PROP", 516);
                put("HIER3_CELL_ID", "1593936201967327430");
                put("EVENT_TIME", "2012-05-17 08:54:55.252");
                put("DATETIME_ID", "2012-05-17 08:54:00.000");
                put("EVENT_TRIGGER", 18);
                put("RAC", 2);
                put("LAC", 2);
                put("SOURCE_CONF", 25);
                put("GBR_UL", 0);
                put("GBR_DL", 0);
                put("SOURCE_CONNECTION_PROP", 516);
                put("C_ID_1", 20950);
                put("C_ID_2", 20950);
                put("C_ID_3", 20950);
                put("C_ID_4", 20950);
                put("RNC_ID_1", 9);
                put("RNC_ID_2", 9);
                put("RNC_ID_3", 9);
                put("RNC_ID_4", 9);
            }
        });

        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, new String[] { "433", "71",
                "TOO_MANY_SERVING_CELL_USERS_EUL" });

        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new Hashtable<String, Object>() {
            {
                put("RAT", "1");
                put("HIERARCHY_3", "smartone_R:RNC09:RNC09");
                put("HIERARCHY_2", "");
                put("HIERARCHY_1", "20950");
                put("CELL_ID", "82095E");
                put("CID", "20950");
                put("CELL_NAME", "");
                put("SITE_NAME", "");
                put("START_TIME", "2012-08-29 10:37:58");
                put("END_TIME", "2012-09-02 19:18:58");
                put("HIER3_ID", "119570576707918668");
                put("HIER32_ID", "534656958128093110");
                put("HIER321_ID", "2358802103608823452");
                put("HIER3_CELL_ID", "1593936201967327430");
                put("SCRAMBLINGCODE", "202");
                put("VENDOR", "Ericsson");
                put("STATUS", "DEACTIVE");
                put("CREATED", "2012-08-29 11:37:58");
                put("MODIFIED", "2012-08-29 11:37:58");
                put("MODIFIER", "ENIQ_EVENTS");

            }
        });

        insertRowIntoTemporaryTable("#DIM_E_RAN_RNCFUNCTION", new String[] { "events_oss_1", "smartone",
                "SubNetwork=smartone,SubNetwork=RNC09,MeContext=RNC09", "ManagedElement=1,RncFunction=1",
                "SubNetwork=smartone,SubNetwork=RNC09,MeContext=RNC09,ManagedElement=1,RncFunction=1", "RNC09", "",
                "9", "Ericsson", "DEACTIVE", "2012-08-29 11:37:39", "2012-08-29 11:37:39", "ENIQ EVENTS" });

        insertRowIntoTemporaryTable("#DIM_E_RAN_RNC", new String[] { "events_oss_1", "smartone",
                "SubNetwork=smartone,SubNetwork=RNC09,MeContext=RNC09", "smartone_R:RNC09:RNC09", "RNC09", "RNC09",
                "SubNetwork=smartone,Site=RNC09", "RNC09", "RNC09", "vM.1.150", "W11B", "", "454", "6", "Ericsson",
                "DEACTIVE", "2012-08-29 11:37:39", "2012-08-29 11:37:39", "ENIQ EVENTS" });

        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_RSCP_MAPPING, new String[] { "39", "-77.0" });
        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_RSCP_MAPPING, new String[] { "45", "-71.0" });
        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_ECNO_MAPPING, new String[] { "21", "-14.0" });
        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_ECNO_MAPPING, new String[] { "33", "-8.0" });
        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_RABTYPE, new String[] { "25", "INTERACTIVE_PS_EUL_HS", "true",
                "Packet Switch" });
        insertRowIntoTemporaryTable(TEMP_DIM_E_RAN_HFA_EVENT_TRIGGER, new String[] { "18", "EVENT_1D" });
    }

    @SuppressWarnings("serial")
    private void createTableSchema() throws Exception {
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONF", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_1", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_2", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_3", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_4", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_SOURCE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_RSCP_SOURCE_CELL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_TARGET", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_RSCP_TARGET_CELL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GBR_UL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GBR_DL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UARFCN_SOURCE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UARFCN_TARGET", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PATHLOSS", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("YEAR_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TIMEZONE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LOCAL_DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NE_VERSION", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_MODULE_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_SOURCE_CELL", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_TARGET_CELL", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UL_SYNC_STATUS_RLS1", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MONTH_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DAY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOUR_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MIN_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_CONTEXT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCANNER_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_SOURCE", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_TARGET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONNECTION_PROP", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SRC_C_ID_1_SS_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TRGT_C_ID_1_SS_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SESSION_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BATCH_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ROAMING", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUSPECTFLAG", "bit", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BAND", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MARKETING_NAME", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MANUFACTURER", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACCESS_CAPABILITY", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODEL", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR_NAME", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_TYPE", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OS", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("INPUT_MODE", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATE", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER_NAME", "varchar(20)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_SGEH_MCCMNC, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("MCC", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("COUNTRY", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OPERATOR", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar(20)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar(50)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar(50)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar(50)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_NAME", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_NAME", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLINGCODE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar(50)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE_DESC", "varchar(50)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_RAN_ECNO_MAPPING, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("ECNO", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ECNO_DBM", "float", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_RAN_RSCP_MAPPING, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RSCP", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_DBM", "float", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_RAN_RABTYPE, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RABTYPE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RABTYPE_DESC", "varchar(50)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID_DESC", "varchar(50)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_RAN_HFA_EVENT_TRIGGER, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_TRIGGER", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER_DESC", "varchar(50)", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACCESS_AREA_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GLOBAL_CELL_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FPDCH", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_NAME", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_TYPE", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_BAND", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_LAYER", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar", Nullable.CAN_BE_NULL));
            }
        });

        createTemporaryFromRealTableAndUseInTemplates("DIM_E_RAN_RNCFUNCTION", new String[] { "OSS_ID", "SUBNETWORK",
                "SN", "MOID", "RNCFUNCTION_FDN", "RNC_ID", "RncFunction", "rncId", "VENDOR", "STATUS", "CREATED",
                "MODIFIED", "MODIFIER" });

        createTemporaryFromRealTableAndUseInTemplates("DIM_E_RAN_RNC", new String[] { "OSS_ID", "SUBNETWORK",
                "RNC_FDN", "ALTERNATIVE_FDN", "RNC_NAME", "RNC_ID", "SITE_FDN", "SITE", "MECONTEXTID", "NEMIMVERSION",
                "RNC_VERSION", "MSC", "MCC", "MNC", "VENDOR", "STATUS", "CREATED", "MODIFIED", "MODIFIER" });

    }
}
