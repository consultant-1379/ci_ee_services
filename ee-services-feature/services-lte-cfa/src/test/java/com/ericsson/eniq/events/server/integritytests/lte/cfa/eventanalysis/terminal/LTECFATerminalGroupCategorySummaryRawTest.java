/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventanalysis.terminal;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis.LTECFATerminalCategorySummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECFATerminalGroupCategorySummaryResultRaw;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author echchik
 * @since 2012
 *
 */
public class LTECFATerminalGroupCategorySummaryRawTest extends
        BaseDataIntegrityTest<LTECFATerminalGroupCategorySummaryResultRaw> {

    private LTECFATerminalCategorySummaryService lteCallFailureTerminalCategorySummaryService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final int TEST_VALUE_TAC_1 = 1090801;

    private static final int TEST_VALUE_TAC_2 = 1090802;

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
        lteCallFailureTerminalCategorySummaryService = new LTECFATerminalCategorySummaryService();
        attachDependencies(lteCallFailureTerminalCategorySummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
        insertRawData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(TAC);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(CATEGORY_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(CATEGORY_ID, "0");
        valuesForTable.put(CATEGORY_ID_DESC, "Call Drops");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CATEGORY_ID, "1");
        valuesForTable.put(CATEGORY_ID_DESC, "Call Setup Failures");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_TAC_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_TAC_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void insertData(final int tac, final String categoryID, final String time, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(CATEGORY_ID, categoryID);
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
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_TAC_GROUP);
        return runQuery(lteCallFailureTerminalCategorySummaryService, requestParameters);
    }

    private void insertRawData() throws Exception {
        insertData(TEST_VALUE_TAC_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC_1, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC_1, TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_RAW, 2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_RAW, 2);

    }

    @Test
    public void testTrackingAreaGroupEventSummary() throws Exception {
        final String json = getJsonResult();
        final ResultTranslator<LTECFATerminalGroupCategorySummaryResultRaw> rt = getTranslator();
        final List<LTECFATerminalGroupCategorySummaryResultRaw> eventResult = rt.translateResult(json,
                LTECFATerminalGroupCategorySummaryResultRaw.class);
        assertResult(eventResult);
    }

    private void assertResult(final List<LTECFATerminalGroupCategorySummaryResultRaw> results) {
        assertThat(results.size(), is(2));
        for (final LTECFATerminalGroupCategorySummaryResultRaw rs : results) {
            if (rs.getCategoryId().equals("0")) {
                assertEquals(rs.getNoOfErrors(), 4);
                assertEquals(rs.getImpactedSubscribers(), 1);
            } else {
                assertEquals(rs.getNoOfErrors(), 16);
                assertEquals(rs.getImpactedSubscribers(), 1);
            }
        }
    }
}
