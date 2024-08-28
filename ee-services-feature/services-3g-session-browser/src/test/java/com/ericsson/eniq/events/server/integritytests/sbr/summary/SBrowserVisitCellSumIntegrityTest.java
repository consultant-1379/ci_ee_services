package com.ericsson.eniq.events.server.integritytests.sbr.summary;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary.SBrowserVisitedCellsSumService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author ehaoswa
 * @since 2012
 *
 */

@SuppressWarnings("rawtypes")
public class SBrowserVisitCellSumIntegrityTest extends BaseDataIntegrityTest {

    private static String TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW = "#EVENT_E_RAN_SESSION_CELL_VISITED_RAW";

    private static final String DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private SBrowserVisitedCellsSumService sBrowserVisitedCellsSumService;

    @Before
    public void setUp() throws Exception {
        sBrowserVisitedCellsSumService = new SBrowserVisitedCellsSumService();
        attachDependencies(sBrowserVisitedCellsSumService);

        createTableSchema();
        populateTestData();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSBrowserPerformanceSummary() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "0000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "17052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234");

        final String json = runQuery(sBrowserVisitedCellsSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Check row 2
        final JSONObject row0Data = data.getJSONObject(2);
        assertEquals("K", row0Data.getString("cellId"));
        assertEquals("s", row0Data.getString("controller"));

        assertEquals("1337230800000", row0Data.getString("startTime"));
        assertEquals("0.09", row0Data.getString("durationInCell"));

        assertEquals("20", row0Data.getString("samplesGoodCoverageBadSignal"));
        assertEquals("80", row0Data.getString("samplesBadCoverageBadSignal"));

        assertEquals("40", row0Data.getString("samplesBadCoverageGoodSignal"));
        assertEquals("50", row0Data.getString("samplesGoodCoverageGoodSignal"));
    }

    @SuppressWarnings("unchecked")
    private void populateTestData() throws Exception {
        insertRow(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, new HashMap<String, Object>() {
            {

                put("IMSI_MCC", "454");
                put("IMSI_MNC", "06");
                put("IMSI", 1234);
                put("HIER3_CELL_ID_1", 4951248562416252575L);
                put("DATETIME_ID", "2012-05-17 05:00:00.000");
                put("EVENT_TIME", "2012-05-17 05:00:00.000");
                put("DURATION", "5738");
                put("RRC_SAMPLES_GC_BS", "20");
                put("RRC_SAMPLES_GC_GS", "50");
                put("RRC_SAMPLES_BC_BS", "80");
                put("RRC_SAMPLES_BC_GS", "40");

            }
        });

        insertRow(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, new HashMap<String, Object>() {
            {
                put("IMSI_MCC", "454");
                put("IMSI_MNC", "06");
                put("IMSI", 1234);
                put("HIER3_CELL_ID_1", 4951248562416252576L);
                put("DATETIME_ID", "2012-05-17 05:05:00.000");
                put("EVENT_TIME", "2012-05-17 05:04:00.000");
                put("DURATION", "10501");
                put("RRC_SAMPLES_GC_BS", "10");
                put("RRC_SAMPLES_GC_GS", "20");
                put("RRC_SAMPLES_BC_BS", "30");
                put("RRC_SAMPLES_BC_GS", "40");

            }
        });

        insertRow(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, new HashMap<String, Object>() {
            {
                put("IMSI_MCC", "454");
                put("IMSI_MNC", "06");
                put("IMSI", 1234);
                put("HIER3_CELL_ID_1", 4951248562416252577L);
                put("DATETIME_ID", "2012-05-17 05:10:00.000");
                put("EVENT_TIME", "2012-05-17 05:08:00.000");
                put("DURATION", "55");
                put("RRC_SAMPLES_GC_BS", "10");
                put("RRC_SAMPLES_GC_GS", "20");
                put("RRC_SAMPLES_BC_BS", "30");
                put("RRC_SAMPLES_BC_GS", "40");

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

        insertRow(DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("RAT", 1);
                put("HIERARCHY_3", "sss");
                put("CID", 1233);
                put("CELL_ID", "Ksfd");
                put("HIER3_CELL_ID", 4951248562416252576L);
                put("VENDOR", "E");
            }
        });

        insertRow(DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("RAT", 1);
                put("HIERARCHY_3", "dcs");
                put("CID", 1223);
                put("CELL_ID", "Kfd");
                put("HIER3_CELL_ID", 4951248562416252577L);
                put("VENDOR", "E");
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void createTableSchema() throws Exception {
        final List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RNC_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_GC_BS", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_GC_GS", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_BC_BS", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_BC_GS", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID_1", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID_1", "unsigned bigint", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, tableSchema);

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
}