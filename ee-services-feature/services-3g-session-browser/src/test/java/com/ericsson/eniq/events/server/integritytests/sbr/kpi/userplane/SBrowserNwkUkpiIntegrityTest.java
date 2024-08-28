/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.integritytests.sbr.kpi.userplane;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static junit.framework.Assert.*;

import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.userplane.SBrowserNetworkUkpiService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@SuppressWarnings("rawtypes")
public class SBrowserNwkUkpiIntegrityTest extends BaseDataIntegrityTest {

    private static String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";

    private SBrowserNetworkUkpiService sBrowserNetworkUkpiService;

    @Before
    public void setUp() throws Exception {
        sBrowserNetworkUkpiService = new SBrowserNetworkUkpiService();
        attachDependencies(sBrowserNetworkUkpiService);

        createTableSchema();
        populateTestData();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSBrowserNwkUkpi() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "17052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");

        final String json = runQuery(sBrowserNetworkUkpiService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Check row 1
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("0", row0Data.getString("Downlink Throughput Samples"));
        assertEquals("1", row0Data.getString("Uplink Throughput Samples"));

        assertEquals("0", row0Data.getString("RTT Terminal Samples"));
        assertEquals("1", row0Data.getString("RTT Server Samples"));

        assertEquals("1", row0Data.getString("Packet Loss Terminal Samples"));
        assertEquals("1", row0Data.getString("Packet Loss Server Samples"));

        assertEquals("-", row0Data.getString("Downlink Throughput"));
        assertEquals("794.0", row0Data.getString("Uplink Throughput"));

        assertEquals("-", row0Data.getString("RTT Terminal"));
        assertEquals("104.0", row0Data.getString("RTT Server"));

        assertEquals("0.0", row0Data.getString("Packet Loss Terminal"));
        assertEquals("19.0476", row0Data.getString("Packet Loss Server"));
    }

    @SuppressWarnings("unchecked")
    private void populateTestData() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {

                put("PACKET_LOSS_TERM", "0.0");
                put("PACKET_LOSS_NET", "0.190476");
                put("THROUGHPUT", 794);
                put("HIER3_ID", 119570576707918668L);
                put("FIVE_MIN_AGG_TIME", "2012-05-17 05:00:00.000");
                put("EVENT_TIME", "2012-05-17 05:00:00.000");
                put("HIER321_ID", "5738");
                put("SETUP_TIME_TERM", null);
                put("SETUP_TIME_NET", "104");
                put("DOWNLINK", "0");
            }
        });

    }

    @SuppressWarnings("unchecked")
    private void createTableSchema() throws Exception {
        final List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {

                add(new ColumnDetails("PACKET_LOSS_TERM", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_NET", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SETUP_TIME_TERM", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SETUP_TIME_NET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATA_RECEIVED", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DOWNLINK", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_TCP_RAW, tableSchema);

    }
}