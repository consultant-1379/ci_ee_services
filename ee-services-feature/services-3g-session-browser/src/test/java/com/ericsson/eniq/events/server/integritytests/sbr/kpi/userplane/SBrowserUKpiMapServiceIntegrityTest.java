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

import org.junit.*;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.userplane.SBrowserUKpiMapService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SBrowserUKpiMapServiceIntegrityTest extends BaseDataIntegrityTest {

    private static final String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_DIM_E_RAN_RNC = "#DIM_E_RAN_RNC";

    private static final String TEMP_DIM_E_RAN_RNCFUNCTION = "#DIM_E_RAN_RNCFUNCTION";

    private SBrowserUKpiMapService sBrowserUKpiMapService;

    @Before
    public void setUp() throws Exception {
        sBrowserUKpiMapService = new SBrowserUKpiMapService();
        attachDependencies(sBrowserUKpiMapService);

        createUserPlaneTableSchema();
        createCellTableSchema();
        createDimTableSchema();
    }

    //Pass on localhost, fail on CI.
    @Ignore
    @Test
    public void testRncCellsForTimePeriod() throws Exception {
        populateTestDataForRncCell();
        populateCellData();

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2254");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2302");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");
        requestParameters.putSingle(NODE_PARAM, "smartone_R:RNC09:RNC09,Ericsson,3G");
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);

        requestParameters.putSingle(TZ_OFFSET, "+0000");

        final String json = runQuery(sBrowserUKpiMapService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject cellData = data.getJSONObject("My Cell");
        final JSONObject kpis = cellData.getJSONObject("kpis");

        // Check Cell values
        assertEquals("9", cellData.getString("cellId"));
        assertEquals("1", cellData.getString("rat"));
        assertEquals("Ericsson", cellData.getString("vendor"));
        assertEquals("smartone:RNC09", cellData.getString("rncName"));

        // Check KPIs for Cell
        assertEquals("10.0", kpis.getString("RTT Terminal"));
        assertEquals("400.0", kpis.getString("Packet Loss Server"));
        assertEquals("400.0", kpis.getString("Packet Loss Terminal"));
        assertEquals("150.0", kpis.getString("Uplink Throughput"));
        assertEquals("20.0", kpis.getString("RTT Server"));
        assertEquals("250.0", kpis.getString("Downlink Throughput"));
    }

    @Test
    public void testAllRncForTimePeriod() throws Exception {
        populateTestDataForAllRnc();

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2254");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2302");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");

        final String json = runQuery(sBrowserUKpiMapService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject rncData = data.getJSONObject("smartone:RNC09");
        final JSONObject kpis = rncData.getJSONObject("kpis");

        // Check RNC values
        assertEquals("1", rncData.getString("rat"));
        assertEquals("Ericsson", rncData.getString("vendor"));

        // Check KPIs for RNC
        assertEquals("5.0", kpis.getString("RTT Terminal"));
        assertEquals("200.0", kpis.getString("Packet Loss Server"));
        assertEquals("200.0", kpis.getString("Packet Loss Terminal"));
        assertEquals("150.0", kpis.getString("Uplink Throughput"));
        assertEquals("10.0", kpis.getString("RTT Server"));
        assertEquals("250.0", kpis.getString("Downlink Throughput"));

    }

    private void populateTestDataForAllRnc() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "100");
                put("DOWNLINK", "1"); // DL Data
                put("THROUGHPUT", "250");
                put("SETUP_TIME_TERM", "5");
                put("SETUP_TIME_NET", "10");
                put("PACKET_LOSS_TERM", "3");
                put("PACKET_LOSS_NET", "2");
                put("FIVE_MIN_AGG_TIME", "2012-05-16 23:01:00.000");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "100");
                put("DOWNLINK", "0"); // UL Data
                put("THROUGHPUT", "150");
                put("SETUP_TIME_TERM", "5");
                put("SETUP_TIME_NET", "10");
                put("PACKET_LOSS_TERM", "1");
                put("PACKET_LOSS_NET", "2");
                put("FIVE_MIN_AGG_TIME", "2012-05-16 23:01:00.000");
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "100"); // join with User Plane data
                put("VENDOR", "Ericsson");
                put("RAT", "1");
                put("HIERARCHY_3", "smartone:RNC09");
            }
        });
    }

    private void populateTestDataForRncCell() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "119570576707918668");
                put("HIER321_ID", "100");
                put("DOWNLINK", "1"); // DL Data
                put("THROUGHPUT", "250");
                put("SETUP_TIME_TERM", "5");
                put("SETUP_TIME_NET", "10");
                put("PACKET_LOSS_TERM", "3");
                put("PACKET_LOSS_NET", "2");
                put("FIVE_MIN_AGG_TIME", "2012-05-16 23:01:00.000");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "119570576707918668");
                put("HIER321_ID", "100");
                put("DOWNLINK", "0"); // UL Data
                put("THROUGHPUT", "150");
                put("SETUP_TIME_TERM", "5");
                put("SETUP_TIME_NET", "10");
                put("PACKET_LOSS_TERM", "1");
                put("PACKET_LOSS_NET", "2");
                put("FIVE_MIN_AGG_TIME", "2012-05-16 23:01:00.000");
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "119570576707918668L"); // join with User Plane data
                put("VENDOR", "Ericsson");
                put("RAT", "1");
                put("HIERARCHY_3", "smartone:RNC09");
            }
        });

        insertRow(TEMP_DIM_E_RAN_RNC, new HashMap<String, Object>() {
            {
                put("ALTERNATIVE_FDN", "smartone:RNC09"); // join with User Plane data
                put("VENDOR", "Ericsson");
                put("RNC_ID", "RNC09");
                put("RNC_FDN", "smartone:RNC09");
            }
        });

        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, new HashMap<String, Object>() {
            {
                put("SN", "smartone:RNC09");
                put("VENDOR", "Ericsson");
                put("RNC_ID", "RNC09");
                put("rncId", "9");
            }
        });
    }

    private void populateCellData() throws Exception {
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("VENDOR", "Ericsson");
                put("RAT", "1");
                put("HIERARCHY_3", "smartone:RNC09");
                put("CID", "9");
                put("CELL_ID", "My Cell");
                put("HIER321_ID", 100);
            }
        });
    }

    private void createUserPlaneTableSchema() throws Exception {
        final List<ColumnDetails> userPlaneSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ARP", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SDU", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ALONE_RATIO", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_TERM", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_NET", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOST", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("URI", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TRAFFIC_CLASS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_IP_ADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ADDR_TERM", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ADDR_SERV", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GGSN_ADDR", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACTIVITY_START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DELAY_CLASS", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RELIABILITY_CLASS", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PRECEDENCE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GTPC_THROUGHPUT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DT_FLAG", "tinyint", Nullable.CAN_BE_NULL));
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
                add(new ColumnDetails("PORT_TERM", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PORT_SERV", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATA_RECEIVED", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GTPC_MAX_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GTPC_MAX_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GTPC_GBR_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GTPC_GBR_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IS_PARTIAL", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DOWNLINK", "bit", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_TCP_RAW, userPlaneSchema);
    }

    private void createCellTableSchema() throws Exception {
        final List<ColumnDetails> cellSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, cellSchema);
    }

    private void createDimTableSchema() throws Exception {
        final List<ColumnDetails> hier321Schema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
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
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> ranRncSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUBNETWORK", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_FDN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ALTERNATIVE_FDN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_NAME", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_FDN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MECONTEXTID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NEMIMVERSION", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_VERSION", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> rncFunctionSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUBNETWORK", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MOID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNCFUNCTION_FDN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RncFunction", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("rncId", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, hier321Schema);
        createTemporaryTable(TEMP_DIM_E_RAN_RNC, ranRncSchema);
        createTemporaryTable(TEMP_DIM_E_RAN_RNCFUNCTION, rncFunctionSchema);
    }
}