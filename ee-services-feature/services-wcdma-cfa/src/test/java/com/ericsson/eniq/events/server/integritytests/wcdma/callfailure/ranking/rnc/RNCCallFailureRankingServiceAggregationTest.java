/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.ranking.rnc;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.RNCCallFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.validator.QueryResultValidator;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.RNCFailureRankingResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 * 
 */
public class RNCCallFailureRankingServiceAggregationTest extends BaseDataIntegrityTest<RNCFailureRankingResult> {

    private RNCCallFailureRankingService callFailureRankingService;

    public static final String SAMPLE_RNC_FDN_1 = "sampleRNCFDNForRNC1";

    public static final String SAMPLE_RNC_FDN_2 = "sampleRNCFDNForRNC2";

    private static final int HASHED_HIER3ID_FOR_RNC01 = 1;

    private static final int HASHED_HIER3ID_FOR_RNC02 = 2;

    @Before
    public void onSetUp() throws Exception {
        callFailureRankingService = new RNCCallFailureRankingService();
        attachDependencies(callFailureRankingService);
        createDimTableSchema();
        createCfaDataTableSchema();
        populateData();
    }

    @Test
    public void testRNCCallDropTotalRabRanking_AGG() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2354");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0502");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "3");
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "01423");
        requestParameters.putSingle(EVENT_ID_PARAM, "438");
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        final String json = runQuery(callFailureRankingService, requestParameters);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        new QueryResultValidator().validateAgainstGridDefinition(json, "RAN_CFA");
        final List<RNCFailureRankingResult> result = getTranslator().translateResult(json,
                RNCFailureRankingResult.class);
        assertThat(result.size(), is(2));

        final RNCFailureRankingResult resultForRNC1 = result.get(0);
        assertThat(resultForRNC1.getVendor(), is(ERICSSON));
        assertThat(resultForRNC1.getRNCFDN(), is(SAMPLE_RNC_FDN_2));
        assertThat(resultForRNC1.getNumErrors(), is(3));

        final RNCFailureRankingResult resultForRNC2 = result.get(1);
        assertThat(resultForRNC2.getVendor(), is(ERICSSON));
        assertThat(resultForRNC2.getRNCFDN(), is(SAMPLE_RNC_FDN_1));
        assertThat(resultForRNC2.getNumErrors(), is(3));

    }

    private void populateData() throws SQLException {

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_ID", HASHED_HIER3ID_FOR_RNC01);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 23:00:00");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_ID", HASHED_HIER3ID_FOR_RNC02);
                put("EVENT_ID", 438);
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 23:15:00");
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

        insertRow(TEMP_DIM_E_SGEH_HIER321, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", SAMPLE_RNC_FDN_1);
                put("VENDOR", ERICSSON);
                put("HIER3_ID", HASHED_HIER3ID_FOR_RNC01);
                put("RAT", 1);
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", SAMPLE_RNC_FDN_2);
                put("VENDOR", ERICSSON);
                put("HIER3_ID", HASHED_HIER3ID_FOR_RNC02);
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
                add(new ColumnDetails("OSS_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACCESS_AREA_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GLOBAL_CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FPDCH", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_NAME", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_TYPE", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_BAND", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_LAYER", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE, eventTypeDimTable);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, hier3TypeDimTable);
    }

    private void createCfaDataTableSchema() throws SQLException {

        final List<ColumnDetails> hier3ErrTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
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

        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_15MIN, hier3ErrTableSchema);

    }
}
