package com.ericsson.eniq.events.server.integritytests.sbr.summary;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.INTERVAL_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary.SBrowserAppLayerSumService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SBrowserAppLayerSumIntegrityTest extends BaseDataIntegrityTest {

    private static String TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW = "#EVENT_E_USER_PLANE_CLASSIFICATION_RAW";

    private SBrowserAppLayerSumService sBrowserAppLayerSumService;

    @Before
    public void setUp() throws Exception {
        sBrowserAppLayerSumService = new SBrowserAppLayerSumService();
        attachDependencies(sBrowserAppLayerSumService);

        createTableSchema();
        populateTestData();
    }

    @Test
    public void testApplicationLayerSummary_FindOne() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2300");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2359");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "15052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "15052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");
        requestParameters.putSingle(INTERVAL_PARAM, "1");

        final String json = runQuery(sBrowserAppLayerSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        assertEquals(1, data.length());

        // Row 0
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("3500", row0Data.getString("totalDownload"));
        assertEquals("1500", row0Data.getString("totalUpload"));
        assertEquals("web-browsing", row0Data.getString("function"));
    }

    @Test
    public void testApplicationLayerSummaryData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2300");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2359");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "14052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");
        requestParameters.putSingle(INTERVAL_PARAM, "1");

        final String json = runQuery(sBrowserAppLayerSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        assertEquals(3, data.length());

        // Row 0
        final JSONObject row0Data = data.getJSONObject(1);
        assertEquals("3500", row0Data.getString("totalDownload"));
        assertEquals("1200", row0Data.getString("totalUpload"));
        assertEquals("social-networking", row0Data.getString("function"));

        //Row 1, function => "Unclassified"
        final JSONObject row1Data = data.getJSONObject(2);
        assertEquals("4500", row1Data.getString("totalDownload"));
        assertEquals("1200", row1Data.getString("totalUpload"));
        assertEquals("Unclassified", row1Data.getString("function"));

        // Row 2
        final JSONObject row2Data = data.getJSONObject(0);
        assertEquals("3500", row2Data.getString("totalDownload"));
        assertEquals("1500", row2Data.getString("totalUpload"));
        assertEquals("web-browsing", row2Data.getString("function"));

    }

    private void populateTestData() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("BYTES_DOWNLINK", 3500);
                put("BYTES_UPLINK", 1500);
                put("FUNCTION", 2);
                put("EVENT_TIME", "2012-05-15 23:39:00.463");
                put("DATETIME_ID", "2012-05-15 23:39:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("BYTES_DOWNLINK", 3500);
                put("BYTES_UPLINK", 1200);
                put("FUNCTION", 7);
                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("BYTES_DOWNLINK", 4500);
                put("BYTES_UPLINK", 1200);
                put("FUNCTION", -1);
                put("EVENT_TIME", "2012-05-17 23:19:00.463");
                put("DATETIME_ID", "2012-05-17 23:19:00.463");
            }
        });
    }

    private void createTableSchema() throws Exception {
        final List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("IMSI_MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKETS_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKETS_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BYTES_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BYTES_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PROTOCOL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FUNCTION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ENCAPSULATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ENCRYPTION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SERVICE_PROVIDER", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CLIENT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_IP_ADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, tableSchema);
    }
}