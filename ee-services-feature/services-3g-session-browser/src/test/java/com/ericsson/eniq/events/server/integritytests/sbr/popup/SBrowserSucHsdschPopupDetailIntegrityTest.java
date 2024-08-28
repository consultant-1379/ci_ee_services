package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserSucHsdschDetailService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author edarbla
 * @since 2012
 *
 */
public class SBrowserSucHsdschPopupDetailIntegrityTest extends BaseDataIntegrityTest {

    private static final String TEMP_EVENT_E_RAN_SESSION_SUC_HSDSCH_CELL_CHANGE_RAW = "#EVENT_E_RAN_SESSION_SUC_HSDSCH_CELL_CHANGE_RAW";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_DIM_E_RAN_RNCFUNCTION = "#DIM_E_RAN_RNCFUNCTION";

    private static final String TEMP_DIM_E_RAN_RNC = "#DIM_E_RAN_RNC";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

    private static final String TEMP_DIM_E_RAN_HFA_EVENT_TRIGGER = "#DIM_E_RAN_HFA_EVENT_TRIGGER";

    private static final String TEMP_DIM_E_RAN_RABTYPE = "#DIM_E_RAN_RABTYPE";

    private static final String TEMP_DIM_E_RAN_SESSION_EVENTTYPE = "#DIM_E_RAN_SESSION_EVENTTYPE";

    private SBrowserSucHsdschDetailService sBrowserSucHsdschDetailService;

    @Before
    public void setUp() throws Exception {
        sBrowserSucHsdschDetailService = new SBrowserSucHsdschDetailService();
        attachDependencies(sBrowserSucHsdschDetailService);
        createTableSchema();
        populateTestData();
    }

    @Test
    public void testSucHsdschPopupDetailService() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(IMSI_PARAM, "123");

        requestParameters.putSingle(EVENT_ID_PARAM, "432");

        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337184000000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337356800000");

        final String json = runQuery(sBrowserSucHsdschDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");

        final JSONObject subIdDetails = details.getJSONObject("Subscriber Identity");
        assertThat(subIdDetails.getString("MSISDN"), is("-"));
        assertThat(subIdDetails.getString("IMSI"), is("123"));
        assertThat(subIdDetails.getString("Network"), is("-"));

        final JSONObject handoverDetails = details.getJSONObject("Handover Details");
        assertThat(handoverDetails.getString("Source Cell Id"), is("b"));
        assertThat(handoverDetails.getString("Source CID"), is("1"));
        assertThat(handoverDetails.getString("Source RNC"), is("1"));
        assertThat(handoverDetails.getString("Source Sec. Serving Hsdsch Cell"), is("11"));
        assertThat(handoverDetails.getString("Target Cell Id"), is("b"));
        assertThat(handoverDetails.getString("Target CID"), is("1"));
        assertThat(handoverDetails.getString("Target RNC"), is("1"));
        assertThat(handoverDetails.getString("Target Sec. Serving Hsdsch Cell"), is("12"));
        assertThat(handoverDetails.getString("Ec/No Source Cell (dB)"), is("1.5"));
        assertThat(handoverDetails.getString("Ec/No Target Cell (dB)"), is("2.5"));
        assertThat(handoverDetails.getString("RSCP Source Cell (dBm)"), is("15"));
        assertThat(handoverDetails.getString("RSCP Target Cell (dBm)"), is("16"));

        final JSONObject eventDetails = details.getJSONObject("Event Details");
        assertThat(eventDetails.getString("Event Time"), is("1337184000009"));
        assertThat(eventDetails.getString("Event Trigger"), is("RELEASE_OF_DL_CODES_IN_NORMAL_MODE"));
        assertThat(eventDetails.getString("RAB Type"), is("Test Rab"));

        assertThat(eventDetails.getString("GBR Uplink (bps)"), is("9"));
        assertThat(eventDetails.getString("GBR Downlink (bps)"), is("10"));
        assertThat(
                eventDetails.getString("Source Connection Prop"),
                is("Compressed Mode DL: not used\nCompressed Mode UL: not used\nTTL length: 2ms\nConnection using EL2: no\n64QAM: deactivated\nSpeech Category: Not applicable\nMIMO: deactivated\nSRB On Hs: no\nSRB On EUL: yes\nMulticarrier: deactivated\nHS-FACH: not used\nContinuous Packet Connectivity: not used\nDual-band HSDPA Multicarrier: not used\nEnhanced UE DRX: not used\n"));
        assertThat(
                eventDetails.getString("Target Connection Prop"),
                is("Compressed Mode DL: not used\nCompressed Mode UL: used\nTTL length: 10ms\nConnection using EL2: no\n64QAM: activated\nSpeech Category: Not applicable\nMIMO: deactivated\nSRB On Hs: no\nSRB On EUL: no\nMulticarrier: deactivated\nHS-FACH: not used\nContinuous Packet Connectivity: not used\nDual-band HSDPA Multicarrier: not used\nEnhanced UE DRX: not used\n"));

        assertThat(eventDetails.getString("Source Connection Prop Ext."), is("13"));
        assertThat(eventDetails.getString("Target Connection Prop Ext."), is("14"));

        final JSONObject activeSetDetails = details.getJSONObject("Active Set Details");
        assertThat(activeSetDetails.getString("Source Cell ID 1"), is("b"));
        assertThat(activeSetDetails.getString("Source Cell ID 2"), is("b"));
        assertThat(activeSetDetails.getString("Source Cell ID 3"), is("b"));
        assertThat(activeSetDetails.getString("Source Cell ID 4"), is("b"));
        assertThat(activeSetDetails.getString("Source CID 1"), is("1"));
        assertThat(activeSetDetails.getString("Source CID 2"), is("1"));
        assertThat(activeSetDetails.getString("Source CID 3"), is("1"));
        assertThat(activeSetDetails.getString("Source CID 4"), is("1"));
        assertThat(activeSetDetails.getString("Source RNC 1"), is("1"));
        assertThat(activeSetDetails.getString("Source RNC 2"), is("1"));
        assertThat(activeSetDetails.getString("Source RNC 3"), is("1"));
        assertThat(activeSetDetails.getString("Source RNC 4"), is("1"));
    }

    @SuppressWarnings("unchecked")
    private void populateTestData() throws Exception {

        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", "1");
                put("CID", 1);
                put("CELL_ID", "b");
                put("HIER3_CELL_ID", 3);
            }
        });

        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, new HashMap<String, Object>() {
            {
                put("SN", "a");
                put("rncId", 2);
            }
        });

        insertRow(TEMP_DIM_E_RAN_RNC, new HashMap<String, Object>() {
            {
                put("ALTERNATIVE_FDN", "1");
                put("RNC_FDN", "a");
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321, new HashMap<String, Object>() {
            {
                put("ACCESS_AREA_ID", 3);
                put("HIER321_ID", 4);
                put("HIERARCHY_1", "b");
                put("HIERARCHY_3", "1");
            }
        });

        insertRow(TEMP_DIM_E_SGEH_MCCMNC, new HashMap<String, Object>() {
            {
                put("MCC", "454");
                put("MNC", "46");
            }
        });

        insertRow(TEMP_DIM_E_RAN_HFA_EVENT_TRIGGER, new HashMap<String, Object>() {
            {
                put("EVENT_TRIGGER", 9);
                put("EVENT_TRIGGER_DESC", "Test Trigger");
            }
        });

        insertRow(TEMP_DIM_E_RAN_RABTYPE, new HashMap<String, Object>() {
            {
                put("RABTYPE", 2);
                put("RABTYPE_DESC", "Test Rab");
            }
        });

        insertRow(TEMP_DIM_E_RAN_SESSION_EVENTTYPE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 432);
                put("EVENT_ID_DESC", "Test Event");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_SESSION_SUC_HSDSCH_CELL_CHANGE_RAW, new HashMap<String, Object>() {
            {
                put("THIER321_ID", 4);
                put("THIER3_CELL_ID", 3);
                put("IMSI_MCC", "454");
                put("IMSI_MNC", "46");
                put("EVENT_ID", 432);
                put("SOURCE_CONF", 2);
                put("EVENT_TRIGGER", 9);
                put("C_ID_1", 1);
                put("RNC_ID_1", 2);
                put("C_ID_2", 1);
                put("RNC_ID_2", 2);
                put("C_ID_3", 1);
                put("RNC_ID_3", 2);
                put("C_ID_4", 1);
                put("RNC_ID_4", 2);
                put("C_ID_SOURCE", 1);
                put("RNC_ID_SOURCE", 2);
                put("GBR_UL", 9);
                put("GBR_DL", 10);
                put("SOURCE_CONNECTION_PROP", 516);
                put("SRC_C_ID_1_SEC_SERV_HSDSCH_CELL", 11);
                put("TARG_C_ID_1_SEC_SERV_HSDSCH_CELL", 12);
                put("EVENT_TIME", "2012-05-16 16:00:00.009");
                put("DATETIME_ID", "2012-05-16 16:00:00.000");
                put("SOURCE_CONN_PROPS_EXT", 13);
                put("TARGET_CONN_PROPS_EXT", 14);
                put("SOURCE_CELL_ECNO", 1.5);
                put("TARGET_CELL_ECNO", 2.5);
                put("SOURCE_CELL_RSCP", 15);
                put("TARGET_CELL_RSCP", 16);
                put("SRC_CONN_PROP", 516);
                put("TARG_CONN_PROP", 18);
                put("IMSI", "123");
                put("C_ID_TARGET", 19);
                put("RNC_ID_TARGET", 20);

            }
        });

    }

    @SuppressWarnings("unchecked")
    private void createTableSchema() throws Exception {
        createTemporaryTable(TEMP_EVENT_E_RAN_SESSION_SUC_HSDSCH_CELL_CHANGE_RAW, new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("THIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONF", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_1", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_2", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_3", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_4", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_SOURCE", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_SOURCE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GBR_UL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GBR_DL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONNECTION_PROP", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SRC_C_ID_1_SEC_SERV_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARG_C_ID_1_SEC_SERV_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONN_PROPS_EXT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARGET_CONN_PROPS_EXT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CELL_ECNO", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARGET_CELL_ECNO", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CELL_RSCP", "integer", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARGET_CELL_RSCP", "integer", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SRC_CONN_PROP", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARG_CONN_PROP", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_TARGET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_TARGET", "smallint", Nullable.CAN_BE_NULL));

            }
        });

        final List<ColumnDetails> tableSchema_HIER321Cell = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, tableSchema_HIER321Cell);

        final List<ColumnDetails> tableSchema_ranRncFunc = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("SN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("rncId", "smallint", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_RAN_RNCFUNCTION, tableSchema_ranRncFunc);

        final List<ColumnDetails> tableSchema_ranRnc = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("ALTERNATIVE_FDN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_FDN", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_RAN_RNC, tableSchema_ranRnc);

        final List<ColumnDetails> tableSchema_HIER321 = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("ACCESS_AREA_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, tableSchema_HIER321);

        final List<ColumnDetails> tableSchema_mccmnc = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_SGEH_MCCMNC, tableSchema_mccmnc);

        final List<ColumnDetails> tableSchema_eventTrig = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_TRIGGER", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_EVENT_TRIGGER, tableSchema_eventTrig);

        final List<ColumnDetails> tableSchema_rabType = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RABTYPE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RABTYPE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_RAN_RABTYPE, tableSchema_rabType);

        final List<ColumnDetails> tableSchema_eventType = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_RAN_SESSION_EVENTTYPE, tableSchema_eventType);

    }

}