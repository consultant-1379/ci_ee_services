package com.ericsson.eniq.events.server.integritytests.sbr.summary;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary.SBrowserRabSumService;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary.SBrowserTcpPerfSumService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
  * @author edarbla
  * @since 2012
  *
  */
public class SBrowserRabSumIntegrityTest extends BaseDataIntegrityTest {

    private static String TEMP_EVENT_E_RAN_SESSION = "#EVENT_E_RAN_SESSION_RAW";
    private static String TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW = "#EVENT_E_RAN_SESSION_CELL_VISITED_RAW";

    private SBrowserRabSumService sBrowserRabSumService;

    @Before
    public void setUp() throws Exception {
        sBrowserRabSumService = new SBrowserRabSumService();
        attachDependencies(sBrowserRabSumService);
        createTableSchema();
    }

    @Test
    public void testSBrowserRabSummaryNoEmptyTable() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1600");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "1700");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");

        populateTestDataBothTables();

        final String json = runQuery(sBrowserRabSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Row 0
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("20.0", row0Data.getString("goodCoverageBadSignal"));
        assertEquals("30.0", row0Data.getString("goodCoverageGoodSignal"));
        assertEquals("25.0", row0Data.getString("badCoverageBadSignal"));
        assertEquals("25.0", row0Data.getString("badCoverageGoodSignal"));

        assertEquals("20.0", row0Data.getString("goodCoverageBadSignalPercentage"));
        assertEquals("30.0", row0Data.getString("goodCoverageGoodSignalPercentage"));
        assertEquals("25.0", row0Data.getString("badCoverageBadSignalPercentage"));
        assertEquals("25.0", row0Data.getString("badCoverageGoodSignalPercentage"));

        assertEquals("-10.0", row0Data.getString("averageEcno"));
        assertEquals("-34.0", row0Data.getString("averageRscp"));
        assertEquals("100.0", row0Data.getString("noMeasurementReports"));
    }

    @Test
    public void testSBrowserRabSummaryEmptyRanTable() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1600");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "1700");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");

        populateTestDataCellVisOnly();

        final String json = runQuery(sBrowserRabSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Row 0
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("20.0", row0Data.getString("goodCoverageBadSignal"));
        assertEquals("30.0", row0Data.getString("goodCoverageGoodSignal"));
        assertEquals("25.0", row0Data.getString("badCoverageBadSignal"));
        assertEquals("25.0", row0Data.getString("badCoverageGoodSignal"));

        assertEquals("20.0", row0Data.getString("goodCoverageBadSignalPercentage"));
        assertEquals("30.0", row0Data.getString("goodCoverageGoodSignalPercentage"));
        assertEquals("25.0", row0Data.getString("badCoverageBadSignalPercentage"));
        assertEquals("25.0", row0Data.getString("badCoverageGoodSignalPercentage"));

        assertEquals("-", row0Data.getString("averageEcno"));
        assertEquals("-", row0Data.getString("averageRscp"));
        assertEquals("100.0", row0Data.getString("noMeasurementReports"));
    }

    @Test
    public void testSBrowserRabSummaryEmptyCellVisTable() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1600");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "1700");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");

        populateTestDataRanSessionOnly();

        final String json = runQuery(sBrowserRabSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");

        // Row 0
        final JSONObject row0Data = data.getJSONObject(0);
        assertEquals("-", row0Data.getString("goodCoverageBadSignal"));
        assertEquals("-", row0Data.getString("goodCoverageGoodSignal"));
        assertEquals("-", row0Data.getString("badCoverageBadSignal"));
        assertEquals("-", row0Data.getString("badCoverageGoodSignal"));

        assertEquals("-", row0Data.getString("goodCoverageBadSignalPercentage"));
        assertEquals("-", row0Data.getString("goodCoverageGoodSignalPercentage"));
        assertEquals("-", row0Data.getString("badCoverageBadSignalPercentage"));
        assertEquals("-", row0Data.getString("badCoverageGoodSignalPercentage"));

        assertEquals("-10.0", row0Data.getString("averageEcno"));
        assertEquals("-34.0", row0Data.getString("averageRscp"));
        assertEquals("-", row0Data.getString("noMeasurementReports"));
    }

    private void createTableSchema() throws Exception {
        List<ColumnDetails> tableSchema_raw = new ArrayList<ColumnDetails>(){
            {
                add(new ColumnDetails("ECNO_AVG","real",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_AVG","int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_RAN_SESSION, tableSchema_raw);

        List<ColumnDetails> tableSchema_cellVisited = new ArrayList<ColumnDetails>(){
            {
                add(new ColumnDetails("RRC_SAMPLES_GC_BS","unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_GC_GS","unsigned int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_BC_BS","unsigned int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_SAMPLES_BC_GS","unsigned int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, tableSchema_cellVisited);
    }

    private void populateTestDataBothTables() throws Exception{
        insertRow(TEMP_EVENT_E_RAN_SESSION, new HashMap<String, Object>() {
            {
                put("IMSI",123);
                put("ECNO_AVG", -10.0);
                put("RSCP_AVG", -34);
                put("EVENT_TIME", "2012-05-16 16:00:00.000");
                put("DATETIME_ID", "2012-05-16 16:00:00.000");
            }
        });


        insertRow(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, new HashMap<String, Object>() {
            {
                put("IMSI",123);
                put("RRC_SAMPLES_GC_BS", 20);
                put("RRC_SAMPLES_GC_GS", 30);
                put("RRC_SAMPLES_BC_BS", 25);
                put("RRC_SAMPLES_BC_GS", 25);
                put("EVENT_TIME", "2012-05-16 16:00:00.000");
                put("DATETIME_ID", "2012-05-16 16:00:00.000");
            }
        });
    }


    private void populateTestDataCellVisOnly() throws Exception{


        insertRow(TEMP_EVENT_E_RAN_SESSION_CELL_VISITED_RAW, new HashMap<String, Object>() {
            {
                put("IMSI",123);
                put("RRC_SAMPLES_GC_BS", 20);
                put("RRC_SAMPLES_GC_GS", 30);
                put("RRC_SAMPLES_BC_BS", 25);
                put("RRC_SAMPLES_BC_GS", 25);
                put("EVENT_TIME", "2012-05-16 16:00:00.000");
                put("DATETIME_ID", "2012-05-16 16:00:00.000");
            }
        });
    }


    private void populateTestDataRanSessionOnly() throws Exception{


        insertRow(TEMP_EVENT_E_RAN_SESSION, new HashMap<String, Object>() {
            {
                put("IMSI",123);
                put("ECNO_AVG", -10.0);
                put("RSCP_AVG", -34);
                put("EVENT_TIME", "2012-05-16 16:00:00.000");
                put("DATETIME_ID", "2012-05-16 16:00:00.000");
            }
        });
    }

}
