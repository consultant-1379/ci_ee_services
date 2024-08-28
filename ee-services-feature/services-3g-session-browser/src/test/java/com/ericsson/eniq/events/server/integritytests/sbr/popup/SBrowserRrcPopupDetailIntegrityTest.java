package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserRrcPopupDetailService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@SuppressWarnings("rawtypes")
public class SBrowserRrcPopupDetailIntegrityTest extends BaseDataIntegrityTest {

    //actual data tables
    private static final String EVENT_E_RAN_SESSION_RRC_MEAS_RAW = "#EVENT_E_RAN_SESSION_RRC_MEAS_RAW";

    private static final String DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private SBrowserRrcPopupDetailService sBrowserRrcPopupDetailService;

    @Before
    public void setUp() throws Exception {
        sBrowserRrcPopupDetailService = new SBrowserRrcPopupDetailService();
        attachDependencies(sBrowserRrcPopupDetailService);

        createRawTableSchema();
        populateTestData();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRrcPopupDetail() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0000");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "18052012");
        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337184001000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337184002000");

        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(IMSI_PARAM, "1234");
        requestParameters.putSingle(EVENT_ID_PARAM, "8");
        requestParameters.putSingle(ADDITIONAL_EVENT_ID, "45678");
        final String json = runQuery(sBrowserRrcPopupDetailService, requestParameters);
        System.out.println(json);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");
        final JSONObject subscriberDetails = details.getJSONObject("Subscriber Identity");
        assertThat(subscriberDetails.getString("IMSI"), is("1234"));
        assertThat(subscriberDetails.getString("Network"), is("Hong Kong-PRC,SmarTone"));

        final JSONObject eventDetails = details.getJSONObject("Event Details");
        assertThat(eventDetails.getString("Event Time"), is("1337184000999"));
        assertThat(eventDetails.getString("Measurement Type"), is("Intra-frequency Measurement"));

        final JSONObject measuredResults = details.getJSONObject("Measured Results");
        assertThat(measuredResults.getString("Scrambling Code 2"), is("33"));
        assertThat(measuredResults.getString("BSIC 1"), is("23"));
        assertThat(measuredResults.getString("Ec/No 2 (dB)"), is("25.2"));
        assertThat(measuredResults.getString("RSCP 2 (dBm)"), is("123"));

        final JSONObject eventResults = details.getJSONObject("Event Results");
        assertThat(eventResults.length(), is(4));
        assertThat(eventResults.getString("Event Results Scrambling Code 1"), is("50"));
        assertThat(eventResults.getString("Event Results Scrambling Code 2"), is("33"));

        final JSONObject activeSetDetails = details.getJSONObject("Active Set Details");
        assertThat(activeSetDetails.getString("Cell Id 1"), is("K"));
        assertThat(activeSetDetails.getString("CID 1"), is("123"));
        assertThat(activeSetDetails.getString("RNC Id 1"), is("s"));
    }

    @SuppressWarnings("unchecked")
    private void createRawTableSchema() throws Exception {
        final List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_CONTEXT", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_1", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_2", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_3", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_4", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TRIGGER_EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLINGCODE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP", "integer", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ADDITIONAL_EVENT_ID", "integer", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ECNO", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID_1", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID_2", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID_3", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID_4", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_MODULE_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MEASUREMENT_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MEAS_RSLTS_ORD_NO", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_RSLTS_ORD_NO", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BSIC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID_1", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID_1", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID_1", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID_2", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID_2", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID_2", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID_3", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID_3", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID_3", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID_4", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID_4", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID_4", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VALID_MEAS_RESULTS", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VALID_EVENT_RESULTS", "bit", Nullable.CAN_BE_NULL));

            }
        };
        createTemporaryTable(EVENT_E_RAN_SESSION_RRC_MEAS_RAW, tableSchema);

        final List<ColumnDetails> tableSchema_HIER321 = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_SGEH_HIER321_CELL, tableSchema_HIER321);
    }

    @SuppressWarnings("unchecked")
    private void populateTestData() throws SQLException {

        insertRow(EVENT_E_RAN_SESSION_RRC_MEAS_RAW, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 8);
                put("IMSI_MCC", "454");
                put("IMSI_MNC", "06");
                put("IMSI", 1234);
                put("HIER3_CELL_ID_1", 4951248562416252575L);
                put("ADDITIONAL_EVENT_ID", 45678);
                put("EVENT_TIME", "2012-05-16 16:00:00.999");
                put("DATETIME_ID", "2012-05-16 22:01:00");
                put("SCRAMBLINGCODE", 50);
                put("MEAS_RSLTS_ORD_NO", 0);
                put("EVENT_RSLTS_ORD_NO", 0);
                put("RSCP", 200);
                put("ECNO", null);
                put("BSIC", 23);
                put("TRIGGER_EVENT_ID", 0);
                put("MEASUREMENT_TYPE", 0);
                put("VALID_EVENT_RESULTS", 1);
                put("VALID_MEAS_RESULTS", 0);
            }
        });

        insertRow(EVENT_E_RAN_SESSION_RRC_MEAS_RAW, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 8);
                put("IMSI_MCC", "454");
                put("IMSI_MNC", "06");
                put("IMSI", 1234);
                put("HIER3_CELL_ID_1", 4951248562416252575L);
                put("ADDITIONAL_EVENT_ID", 45678);
                put("EVENT_TIME", "2012-05-16 16:00:00.999");
                put("DATETIME_ID", "2012-05-16 22:01:00");
                put("SCRAMBLINGCODE", 33);
                put("MEAS_RSLTS_ORD_NO", 1);
                put("EVENT_RSLTS_ORD_NO", 1);
                put("RSCP", 123);
                put("ECNO", 25.2);
                put("BSIC", null);
                put("TRIGGER_EVENT_ID", 0);
                put("MEASUREMENT_TYPE", 0);
                put("VALID_EVENT_RESULTS", 1);
                put("VALID_MEAS_RESULTS", 1);
            }
        });

        insertRow(DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("RAT", 1);
                put("HIERARCHY_3", "s");
                put("CID", 123);
                put("CELL_ID", "K");
                put("HIER3_CELL_ID", 4951248562416252575L);
                put("VENDOR", "E");
            }
        });

    }

}
