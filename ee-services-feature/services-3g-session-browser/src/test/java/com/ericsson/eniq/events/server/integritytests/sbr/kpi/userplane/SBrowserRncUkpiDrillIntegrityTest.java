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
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.junit.Assert.*;

import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.userplane.SBrowserRncUkpiDrillService;
import com.ericsson.eniq.events.server.test.populator.LookupTechPackPopulator;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@SuppressWarnings("rawtypes")
public class SBrowserRncUkpiDrillIntegrityTest extends BaseDataIntegrityTest {
    private static String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";

    private static final String TEMP_EVENT_E_RAN_SESSION_RAW = "#EVENT_E_RAN_SESSION_RAW";

    private SBrowserRncUkpiDrillService sBrowserRncUkpiDrillService;

    @Before
    public void setUp() throws Exception {
        sBrowserRncUkpiDrillService = new SBrowserRncUkpiDrillService();
        attachDependencies(sBrowserRncUkpiDrillService);
        createDimTables();
        createTableSchema();
        populateTestData();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSBrowserRncUkpiDrill_ECNO() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "17052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");
        requestParameters.putSingle(KPI_END_TIME, "1337230800000");
        requestParameters.putSingle(KPI_ID, "0");
        requestParameters.putSingle(DRILLTYPE_PARAM, "ECNO_AVG");
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(NODE_PARAM, "smartone_R:RNC09:RNC09,Ericsson,3G");
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);

        final String json = runQuery(sBrowserRncUkpiDrillService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("0.0", row0Data.getString("Value"));
        assertEquals("0", row0Data.getString("Samples"));
        assertEquals("-", row0Data.getString("DrillBy"));

        final JSONObject row1Data = data.getJSONObject(1);
        assertEquals("794.0", row1Data.getString("Value"));
        assertEquals("1", row1Data.getString("Samples"));
        assertEquals("-23.5", row1Data.getString("DrillBy"));

    }

    @SuppressWarnings("unchecked")
    private void populateTestData() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {

                put("PACKET_LOSS_TERM", "0.0");
                put("PACKET_LOSS_NET", "0.190476");
                put("THROUGHPUT", 794);
                put("HIER3_ID", 119570576707918668L);
                put("FIVE_MIN_AGG_TIME", "2012-05-17 05:00:00");
                put("SETUP_TIME_TERM", null);
                put("SETUP_TIME_NET", "104");
                put("DOWNLINK", "0");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_SESSION_RAW, new HashMap<String, Object>() {
            {

                put("ECNO_AVG", -23.5);
                put("RSCP_AVG", 1);
                put("START_HIER3_ID", 119570576707918668L);
                put("DATETIME_ID", "2012-05-17 05:00:00.000");
                put("UL_INTERFERENCE_AVG", 1.0);
                put("HSDSCH_AVG_USERS", 3);
            }
        });

    }

    private void createDimTables() throws Exception {
        new LookupTechPackPopulator().createAndPopulateLookupTable(connection, TEMP_DIM_E_SGEH_TAC);
        new LookupTechPackPopulator().createAndPopulateLookupTable(connection, TEMP_DIM_E_RAN_ECNO_MAPPING);

    }

    @SuppressWarnings("unchecked")
    private void createTableSchema() throws Exception {
        final List<ColumnDetails> ranTable = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("START_HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ECNO_AVG", "real", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_AVG", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UL_INTERFERENCE_AVG", "real", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HSDSCH_AVG_USERS", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_RAN_SESSION_RAW, ranTable);

        final List<ColumnDetails> tcpTable = new ArrayList<ColumnDetails>() {
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
        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_TCP_RAW, tcpTable);

    }
}
