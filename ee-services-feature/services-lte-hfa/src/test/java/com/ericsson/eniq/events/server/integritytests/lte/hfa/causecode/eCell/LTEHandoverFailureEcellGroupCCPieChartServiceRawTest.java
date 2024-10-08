/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.causecode.eCell;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.causecodeanalysis.LTEHandoverFailureEcellCauseCodePieChartService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHandoverFailureGroupCCPieChartResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejamves
 * @since 2011
 *
 */
public class LTEHandoverFailureEcellGroupCCPieChartServiceRawTest extends
        BaseDataIntegrityTest<LTEHandoverFailureGroupCCPieChartResult> {

    private LTEHandoverFailureEcellCauseCodePieChartService lteHandoverFailureEcellCauseCodePieChartService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final long TEST_VALUE_HIER321_ID = 5345046159527374400L;

    private static final String TEST_VALUE_GROUP_NAME = "eCellGroup";

    private static final String TEST_VALUE_IMSI = "11111119";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteHandoverFailureEcellCauseCodePieChartService = new LTEHandoverFailureEcellCauseCodePieChartService();
        attachDependencies(lteHandoverFailureEcellCauseCodePieChartService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
        insertRawData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(TAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(CAUSE_CODE_COLUMN);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        columnsForTable.add(CATEGORY_ID_SQL_PARAM);
        columnsForTable.add(CATEGORY_ID_2_DESC);
        columnsForTable.add(CAUSE_CODE_COLUMN);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(VENDOR_NAME);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(CAUSE_CODE_COLUMN);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CAUSE_CODE_DESC_COLUMN);
        columnsForTable.add(CAUSE_CODE_DESC_PIE);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_S1_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_S1_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_X2_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_X2_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_S1_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_S1_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_X2_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_X2_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        valuesForTable.put(CAUSE_CODE_DESC_PIE, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA
                + TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);

    }

    private void insertData(final long hier321Id, final String eventID, final String causeCD, final int tac,
            final String time, final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(HIER321_ID, hier321Id);
            valuesForTable.put(CAUSE_CODE_COLUMN, causeCD);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResult() throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        requestParameters.putSingle(CAUSE_CODE_ID_LIST, "4102-7,4103-7,4104-7,4105-7,4110-7,4111-7,4112-7,4113-7");

        return runQuery(lteHandoverFailureEcellCauseCodePieChartService, requestParameters);
    }

    private void insertRawData() throws Exception {

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE, SAMPLE_TAC,
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, TEST_VALUE_CAUSE_CODE,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

    }

    @Test
    public void testEcellCauseCodePieChartSummary() throws Exception {
        final String json = getJsonResult();
        final ResultTranslator<LTEHandoverFailureGroupCCPieChartResult> rt = getTranslator();
        final List<LTEHandoverFailureGroupCCPieChartResult> eventResult = rt.translateResult(json,
                LTEHandoverFailureGroupCCPieChartResult.class);
        assertResult(eventResult);
    }

    private void assertResult(final List<LTEHandoverFailureGroupCCPieChartResult> results) {
        assertThat(results.size(), is(8));
        for (final LTEHandoverFailureGroupCCPieChartResult rs : results) {
            assertEquals(rs.getNoOfErrors(), "4");
            assertEquals(rs.getCauseCodeId(), TEST_VALUE_CAUSE_CODE);
        }
    }
}