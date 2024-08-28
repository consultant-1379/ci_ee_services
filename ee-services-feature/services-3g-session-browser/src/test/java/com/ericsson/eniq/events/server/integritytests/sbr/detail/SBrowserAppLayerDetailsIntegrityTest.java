package com.ericsson.eniq.events.server.integritytests.sbr.detail;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static junit.framework.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.detail.SBrowserAppLayerDetailsService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SBrowserAppLayerDetailsIntegrityTest extends BaseDataIntegrityTest {

    private static String TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW = "#EVENT_E_USER_PLANE_CLASSIFICATION_RAW";

    private SBrowserAppLayerDetailsService sBrowserAppLayerDetailsService;

    @Before
    public void setUp() throws Exception {
        sBrowserAppLayerDetailsService = new SBrowserAppLayerDetailsService();
        attachDependencies(sBrowserAppLayerDetailsService);

        createTableSchema();
        populateTestData();
    }

    @Test
    public void testSessionRabImsiDetail() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2300");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2359");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "15052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");
        requestParameters.putSingle(INTERVAL_PARAM, "1");

        final String json = runQuery(sBrowserAppLayerDetailsService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Check row 0
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("12.0", row0Data.getString("web-browsing DL"));
        assertEquals("15.0", row0Data.getString("web-browsing UL"));

        // Check row 1
        final JSONObject row1Data = data.getJSONObject(1);
        assertEquals("22.0", row1Data.getString("web-browsing DL"));
        assertEquals("25.0", row1Data.getString("web-browsing UL"));
    }

    private void populateTestData() throws SQLException {
        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("BYTES_DOWNLINK", 22);
                put("BYTES_UPLINK", 25);
                put("FUNCTION", 2);

                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("BYTES_DOWNLINK", 12);
                put("BYTES_UPLINK", 15);
                put("FUNCTION", 2);

                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
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