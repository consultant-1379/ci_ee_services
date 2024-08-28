package com.ericsson.eniq.events.server.integritytests.sbr.detail;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.detail.SBrowserRabDetailService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static junit.framework.Assert.assertEquals;

/**
 * @author edarbla
 * @since 2012
 */
public class SBrowserRabDetailIntegrityTest extends BaseDataIntegrityTest {

    private static final String TEMP_EVENT_E_RAN_SESSION_RAW = "#EVENT_E_RAN_SESSION_RAW";
    private static final String TEMP_EVENT_E_CORE_SESSION_RAW = "#EVENT_E_CORE_SESSION_RAW";
    private static final String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";


    private SBrowserRabDetailService sBrowserRabDetailService;

    @Before
    public void setUp() throws Exception {
        sBrowserRabDetailService = new SBrowserRabDetailService();
        attachDependencies(sBrowserRabDetailService);

        createRanTableSchema();
        createCoreTableSchema();
        createTcpTableSchema();
        populateTestData();
    }

    @Test
    public void testSessionRabImsiDetail() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2254");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2302");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");


        final String json = runQuery(sBrowserRabDetailService, requestParameters);
        System.out.println(json);

        final JSONObject jsonResult = new JSONObject(json);

        final JSONArray data = jsonResult.getJSONArray("data");

        // Check row 0
        JSONObject row0Data = data.getJSONObject(0);
        assertEquals("RAB", row0Data.getString("eventType"));
        assertEquals("A", row0Data.getString("startCellId"));
        assertEquals("B", row0Data.getString("endCellId"));
        assertEquals("21", row0Data.getString("startRncId"));
        assertEquals("22", row0Data.getString("endRncId"));

    }

    private void populateTestData() throws SQLException {
        insertRow(TEMP_EVENT_E_RAN_SESSION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", 20000);
                put("START_CELL_ID", "A");
                put("END_CELL_ID", "B");
                put("START_RNC_ID", 21);
                put("END_RNC_ID", 22);
                put("START_C_ID", 23);
                put("END_C_ID", 24);
                put("DISTINCT_CELL_CNT", 25);
                put("RRC_CONN_START", "133709760000");
                put("RRC_CONN_END", "133709760000");
                put("EVENT_TIME", "2012-05-16 23:01:00.000");

                put("DATETIME_ID", "2012-05-16 23:01:00.000");
            }
        });

        insertRow(TEMP_EVENT_E_CORE_SESSION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", 20000);
                put("TAC", 100200);
                put("EVENT_TIME", "2012-05-16 23:01:00.000");

                put("DATETIME_ID", "2012-05-16 23:01:00.000");

            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("FIVE_MIN_AGG_TIME", "2012-05-16 15:55:00.000");
                put("TAC", 100300);

                put("EVENT_TIME", "2012-05-16 23:01:00.000");

                put("DATETIME_ID", "2012-05-16 23:01:00.000");
            }
        });
    }

    private void createRanTableSchema() throws Exception {
        List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID","smallint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_RNC_ID","smallint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_RNC_ID","smallint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_C_ID","unsigned int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_C_ID","unsigned int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DISTINCT_CELL_CNT","unsigned int",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI","unsigned bigint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_CONN_START","timestamp",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_CONN_END","timestamp",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_CELL_ID","varchar",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_CELL_ID","varchar",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME","timestamp",Nullable.CAN_BE_NULL));

                add(new ColumnDetails("DATETIME_ID","timestamp",Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_RAN_SESSION_RAW, tableSchema);

    }

    private void createCoreTableSchema() throws Exception {
        List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID","smallint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI","unsigned bigint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME","timestamp",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC","unsigned int",Nullable.CAN_BE_NULL));

                add(new ColumnDetails("DATETIME_ID","timestamp",Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_CORE_SESSION_RAW, tableSchema);
    }

    private void createTcpTableSchema() throws Exception {
        List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("IMSI","unsigned bigint",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME","timestamp",Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC","unsigned int",Nullable.CAN_BE_NULL));

                add(new ColumnDetails("DATETIME_ID","timestamp",Nullable.CAN_BE_NULL));

                add(new ColumnDetails("EVENT_TIME","timestamp",Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_TCP_RAW, tableSchema);
    }
}
