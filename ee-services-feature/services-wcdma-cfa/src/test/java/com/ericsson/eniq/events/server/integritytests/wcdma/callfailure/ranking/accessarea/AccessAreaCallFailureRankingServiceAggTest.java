/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.ranking.accessarea;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.AccessAreaCallFailureAnalysisRankingService;
import com.ericsson.eniq.events.server.test.queryresults.AccessAreaCallFailureRankingResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author etonayr
 * @since 2011
 *
 */
public class AccessAreaCallFailureRankingServiceAggTest extends
        BaseDataIntegrityTest<AccessAreaCallFailureRankingResult> {

    private static final String TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN = "#EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_ERR_15MIN";

    private static final int HASHED_HIER3_CELL_ID_1 = 1;

    private static final int HASHED_HIER3_CELL_ID_2 = 2;

    private static final int HASHED_HIER3_CELL_ID_3 = 3;

    private AccessAreaCallFailureAnalysisRankingService accessAreaCallFailureAnalysisRankingService;

    private static final String SAMPLE_RNC_FDN_1 = "sampleRNCFDNForRNC1";

    private static final String SAMPLE_RNC_FDN_2 = "sampleRNCFDNForRNC2";

    private static final String SAMPLE_RNC_FDN_3 = "sampleRNCFDNForRNC3";

    private static final String CELL_ID_1 = "8084A";

    private static final String CELL_ID_2 = "8052A";

    private static final String CELL_ID_3 = "8062A";

    private static final String SLOPE_UP = "UP";

    private static final String SLOPE_DOWN = "DOWN";

    private static final String SLOPE_LEVEL = "LEVEL";

    @Before
    public void onSetUp() throws Exception {
        accessAreaCallFailureAnalysisRankingService = new AccessAreaCallFailureAnalysisRankingService();
        attachDependencies(accessAreaCallFailureAnalysisRankingService);
        createDimTableSchema();
        createCfaDataTableSchema();
        populateData();

    }

    @Test
    public void testAccessAreaCallDropTotalRabRanking_AGG() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2254");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0402");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "3");
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "01423");
        requestParameters.putSingle(EVENT_ID_PARAM, "438");
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);

        final String json = runQuery(accessAreaCallFailureAnalysisRankingService, requestParameters);
        verifyResult(json);
    }

    @Test
    public void testAccessAreaCallSetupTotalRabRanking_AGG() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2254");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0402");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "3");
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "01423");
        requestParameters.putSingle(EVENT_ID_PARAM, "456");
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);

        final String json = runQuery(accessAreaCallFailureAnalysisRankingService, requestParameters);
        final List<AccessAreaCallFailureRankingResult> result = getTranslator().translateResult(json,
                AccessAreaCallFailureRankingResult.class);
        assertThat(result.size(), is(0));
    }

    private void verifyResult(final String json) throws Exception {
        final List<AccessAreaCallFailureRankingResult> result = getTranslator().translateResult(json,
                AccessAreaCallFailureRankingResult.class);
        assertThat(result.size(), is(3));

        final AccessAreaCallFailureRankingResult resultForCell1 = result.get(0);
        assertThat(resultForCell1.getVendor(), is(ERICSSON));
        assertThat(resultForCell1.getAccessArea(), is(CELL_ID_1));
        assertThat(resultForCell1.getRank(), is(1));
        assertThat(resultForCell1.getController(), is(SAMPLE_RNC_FDN_1));
        assertThat(resultForCell1.getFailures(), is(15));
        assertThat(resultForCell1.getSlope(), is(SLOPE_DOWN));

        final AccessAreaCallFailureRankingResult resultForCell3 = result.get(1);
        assertThat(resultForCell3.getVendor(), is(ERICSSON));
        assertThat(resultForCell3.getAccessArea(), is(CELL_ID_3));
        assertThat(resultForCell3.getRank(), is(1));
        assertThat(resultForCell3.getController(), is(SAMPLE_RNC_FDN_3));
        assertThat(resultForCell3.getFailures(), is(15));
        assertThat(resultForCell3.getSlope(), is(SLOPE_UP));

        final AccessAreaCallFailureRankingResult resultForCell2 = result.get(2);
        assertThat(resultForCell2.getVendor(), is(ERICSSON));
        assertThat(resultForCell2.getAccessArea(), is(CELL_ID_2));
        assertThat(resultForCell2.getRank(), is(3));
        assertThat(resultForCell2.getController(), is(SAMPLE_RNC_FDN_2));
        assertThat(resultForCell2.getFailures(), is(6));
        assertThat(resultForCell2.getSlope(), is(SLOPE_LEVEL));

    }

    private void populateData() throws SQLException {
        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_1);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:00:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_1);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 2);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 2);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 2);
                put("DATETIME_ID", "2012-05-16 22:00:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_1);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:15:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_1);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:30:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_2);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:00:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_2);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:15:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_3);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:00:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_3);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:00:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_3);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:15:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_3);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:15:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_3);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 22:15:00");
            }
        });

        // Dim tables data for joins

        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 438);
                put("EVENT_ID_ALTERNATE_DESC", "Call Drops");
            }
        });

        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 456);
                put("EVENT_ID_ALTERNATE_DESC", "Call Setup Failures");
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", SAMPLE_RNC_FDN_1);
                put("VENDOR", ERICSSON);
                put("CELL_ID", CELL_ID_1);
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_1);
                put("RAT", 1);
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", SAMPLE_RNC_FDN_2);
                put("VENDOR", ERICSSON);
                put("CELL_ID", CELL_ID_2);
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_2);
                put("RAT", 1);
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", SAMPLE_RNC_FDN_3);
                put("VENDOR", ERICSSON);
                put("CELL_ID", CELL_ID_3);
                put("HIER3_CELL_ID", HASHED_HIER3_CELL_ID_3);
                put("RAT", 1);
            }
        });

    }

    private void createDimTableSchema() throws SQLException {

        final List<ColumnDetails> eventTypeDimTable = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID_ALTERNATE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        final List<ColumnDetails> hier3TypeDimTable = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_NAME", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE, eventTypeDimTable);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, hier3TypeDimTable);
    }

    private void createCfaDataTableSchema() throws SQLException {

        final List<ColumnDetails> hier3CellErrTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_ERRORS", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_TOTAL_ERR_SUBSCRIBERS", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PS_RAB_FAIL_CNT", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CS_RAB_FAIL_CNT", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MULTI_RAB_FAIL_CNT", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PS_ERR_CNT", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CS_ERR_CNT", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MULTI_ERR_CNT", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("YEAR_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MONTH_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DAY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOUR_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MIN_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_EVENTID_15MIN, hier3CellErrTableSchema);
    }
}
