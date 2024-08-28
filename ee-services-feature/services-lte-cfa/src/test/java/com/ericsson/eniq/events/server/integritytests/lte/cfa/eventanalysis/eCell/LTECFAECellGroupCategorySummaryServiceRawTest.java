/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventanalysis.eCell;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
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

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis.LTECFAEcellCategorySummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureECellGroupCategorySummaryResultRaw;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author estepdu
 * @since 2011
 *
 */
public class LTECFAECellGroupCategorySummaryServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureECellGroupCategorySummaryResultRaw> {

    private LTECFAEcellCategorySummaryService lteCallFailureEcellCategorySummaryService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final long TEST_VALUE_HIER321_ID_1 = 3135210477467174988L;

    private static final long TEST_VALUE_HIER321_ID_2 = 4809532081614999117L;

    private static final String TEST_VALUE_GROUP_NAME = "eCellGroup";

    private static final String TEST_VALUE_IMSI = "11111119";

    private static final String TEST_VALUE_CATEGORY_ID_CALL_SETUP = "1";

    private static final String TEST_VALUE_CATEGORY_ID_CALL_DROP = "0";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureEcellCategorySummaryService = new LTECFAEcellCategorySummaryService();
        attachDependencies(lteCallFailureEcellCategorySummaryService);
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
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(CATEGORY_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(VENDOR_NAME);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(CATEGORY_ID, TEST_VALUE_CATEGORY_ID_CALL_SETUP);
        valuesForTable.put(CATEGORY_ID_DESC, "Call Setup Failures");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CATEGORY_ID, TEST_VALUE_CATEGORY_ID_CALL_DROP);
        valuesForTable.put(CATEGORY_ID_DESC, "Call Drops");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);
    }

    private void insertData(final long hier3Id, final String categoryID, final int tac, final String time,
            final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(HIER321_ID, hier3Id);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
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
        return runQuery(lteCallFailureEcellCategorySummaryService, requestParameters);
    }

    private void insertRawData() throws Exception {
        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_DROP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_1, TEST_VALUE_CATEGORY_ID_CALL_DROP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_DROP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER321_ID_2, TEST_VALUE_CATEGORY_ID_CALL_DROP,
                Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), DATETIME_ID_RAW, 1);

    }

    @Test
    public void testECellGroupEventSummary() throws Exception {
        final String json = getJsonResult();
        final ResultTranslator<LTECallFailureECellGroupCategorySummaryResultRaw> rt = getTranslator();
        final List<LTECallFailureECellGroupCategorySummaryResultRaw> eventResult = rt.translateResult(json,
                LTECallFailureECellGroupCategorySummaryResultRaw.class);

        assertResult(eventResult);
    }

    private void assertResult(final List<LTECallFailureECellGroupCategorySummaryResultRaw> results) {
        assertThat(results.size(), is(2));
        for (final LTECallFailureECellGroupCategorySummaryResultRaw rs : results) {
            if (rs.getCategoryId().equals("0")) {
                assertEquals("2", rs.getNoOfErrors());
            } else {
                assertEquals("8", rs.getNoOfErrors());
            }
        }
    }
}