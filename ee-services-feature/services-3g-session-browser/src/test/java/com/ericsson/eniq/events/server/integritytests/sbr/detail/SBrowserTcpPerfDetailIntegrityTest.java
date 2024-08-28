/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
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
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.detail.SBrowserTCPPerfDetailsService;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserCoreDetailResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author evyagrz
 * @since 2012
 */
public class SBrowserTcpPerfDetailIntegrityTest extends BaseDataIntegrityTest<SBrowserCoreDetailResult> {

    private static String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";

    private SBrowserTCPPerfDetailsService sBrowserTCPPerfDetailsService;

    @Before
    public void setUp() throws Exception {
        sBrowserTCPPerfDetailsService = new SBrowserTCPPerfDetailsService();
        attachDependencies(sBrowserTCPPerfDetailsService);

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

        final String json = runQuery(sBrowserTCPPerfDetailsService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Check row 0
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("0.0050", row0Data.getString("uplinkThroughput"));
        assertEquals("0.0", row0Data.getString("downlinkThroughput"));
        assertEquals("1.0", row0Data.getString("packetLossTerminal"));

        // Check row 1
        final JSONObject row1Data = data.getJSONObject(1);
        assertEquals("0.0", row1Data.getString("uplinkThroughput"));
        assertEquals("0.0040", row1Data.getString("downlinkThroughput"));
        assertEquals("2.0", row1Data.getString("packetLossTerminal"));

        // Check row 1
        final JSONObject row2Data = data.getJSONObject(2);
        assertEquals("0.0030", row2Data.getString("uplinkThroughput"));
        assertEquals("0.0040", row1Data.getString("downlinkThroughput"));
        assertEquals("3.0", row2Data.getString("packetLossTerminal"));
    }

    private void populateTestData() throws SQLException {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("PACKET_LOSS_TERM", "3");
                put("DOWNLINK", 0);
                put("THROUGHPUT", 3);

                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("PACKET_LOSS_TERM", "2");
                put("DOWNLINK", 1);
                put("THROUGHPUT", 4);

                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("PACKET_LOSS_TERM", "1");
                put("DOWNLINK", 0);
                put("THROUGHPUT", 5);

                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });
    }

    private void createTableSchema() throws Exception {
        final List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ALONE_RATIO", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_TERM", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_NET", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOST", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_IP_ADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACTIVITY_START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PIPE_THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NONSS_PIPE_THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CHANNEL_PIPE_THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MAX_RWIN", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SETUP_TIME_TERM", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SETUP_TIME_NET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MAX_DATA_PACKET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CONTENT_TYPE", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACTIVITY_DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_SAMPLES", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATA_RECEIVED", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IS_PARTIAL", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DOWNLINK", "bit", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_TCP_RAW, tableSchema);
    }
}