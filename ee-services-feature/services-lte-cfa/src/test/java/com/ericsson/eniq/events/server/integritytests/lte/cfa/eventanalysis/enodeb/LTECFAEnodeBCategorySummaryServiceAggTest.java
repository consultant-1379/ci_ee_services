/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventanalysis.enodeb;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis.LTECFAEnodeBCategorySummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureEnodeBCategorySummaryResultRaw;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echchik
 * @since 2011
 *
 */
public class LTECFAEnodeBCategorySummaryServiceAggTest extends
        BaseDataIntegrityTest<LTECallFailureEnodeBCategorySummaryResultRaw> {

    private LTECFAEnodeBCategorySummaryService lteCallFailureEnodeBCategorySummaryService;

    private static final String DATETIME_ID_DAY_1 = "2011-09-19 08:15:00";

    private static final String DATETIME_ID_DAY_2 = "2011-09-18 12:15:00";

    private static final String DATE_FROM_DAY = "13092011";

    private static final String DATE_TO_DAY = "20092011";

    private static final String DATETIME_ID_15MIN_1 = "2011-09-20 08:15:00";

    private static final String DATETIME_ID_15MIN_2 = "2011-09-20 12:15:00";

    private static final String DATE_FROM_15MIN = "20092011";

    private static final String DATE_TO_15MIN = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "1530";

    private static final long TEST_VALUE_HIER3_ID = 3135210477467174988L;

    private static final String TEST_VALUE_HIERARCHY_3 = "ENODEB";

    private static final String TEST_VALUE_VENDOR = "ERICSSON";

    private static final String TEST_VALUE_CATEGORY_ID_CALL_SETUP = "1";

    private static final String TEST_VALUE_CATEGORY_ID_CALL_DROP = "0";

    private static final String TEST_VALUE_NODE_PARAM = "ONRM_RootMo_R:LTE01ERBS00001,Ericsson,LTE";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureEnodeBCategorySummaryService = new LTECFAEnodeBCategorySummaryService();
        attachDependencies(lteCallFailureEnodeBCategorySummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(TAC);
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
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIERARCHY_3);
        columnsForTable.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, columnsForTable);
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
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_HIERARCHY_3);
        valuesForTable.put(VENDOR, TEST_VALUE_VENDOR);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
    }

    private void insertDataAgg(final String aggTable, final long hier3Id, final String categoryID, final String time,
            final int instances, final int noOfErrors) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(HIER3_ID, hier3Id);
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(NO_OF_ERRORS, noOfErrors);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(aggTable, valuesForTable);
        }
    }

    private String getJsonResultDay(final boolean isRankingDrillDown) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_DAY);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_DAY);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        if (isRankingDrillDown) {
            requestParameters.putSingle(HIER3_ID, Long.toString(TEST_VALUE_HIER3_ID));
        } else {
            requestParameters.putSingle(NODE_PARAM, TEST_VALUE_NODE_PARAM);
        }
        return runQuery(lteCallFailureEnodeBCategorySummaryService, requestParameters);
    }

    private String getJsonResult15Min(final boolean isRankingDrillDown) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_15MIN);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_15MIN);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        if (isRankingDrillDown) {
            requestParameters.putSingle(HIER3_ID, Long.toString(TEST_VALUE_HIER3_ID));
        } else {
            requestParameters.putSingle(NODE_PARAM, TEST_VALUE_NODE_PARAM);
        }
        return runQuery(lteCallFailureEnodeBCategorySummaryService, requestParameters);
    }

    private void insertAggDataDay() throws Exception {
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_DAY_1, 1, 20);

        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_DAY_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_DAY_2, 1, 10);
    }

    private void insertAggData15Min() throws Exception {
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_1, 1, 20);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_15MIN_1, 1, 20);

        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_SETUP, DATETIME_ID_15MIN_2, 1, 10);
        insertDataAgg(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, TEST_VALUE_HIER3_ID,
                TEST_VALUE_CATEGORY_ID_CALL_DROP, DATETIME_ID_15MIN_2, 1, 10);
    }

    @Test
    public void testEnodeBCategorySummaryDay() throws Exception {
        insertAggDataDay();
        final String json = getJsonResultDay(false);
        final ResultTranslator<LTECallFailureEnodeBCategorySummaryResultRaw> rt = getTranslator();
        final List<LTECallFailureEnodeBCategorySummaryResultRaw> eventResult = rt.translateResult(json,
                LTECallFailureEnodeBCategorySummaryResultRaw.class);
        assertResultDay(eventResult);
    }

    @Test
    public void testEnodeBCategorySummary15min() throws Exception {
        insertAggData15Min();
        final String json = getJsonResult15Min(false);
        final ResultTranslator<LTECallFailureEnodeBCategorySummaryResultRaw> rt = getTranslator();
        final List<LTECallFailureEnodeBCategorySummaryResultRaw> eventResult = rt.translateResult(json,
                LTECallFailureEnodeBCategorySummaryResultRaw.class);
        assertResultDay(eventResult);
    }

    @Test
    public void testEnodeBCategorySummaryRankingDrillDownDay() throws Exception {
        insertAggDataDay();
        final String json = getJsonResultDay(true);
        final ResultTranslator<LTECallFailureEnodeBCategorySummaryResultRaw> rt = getTranslator();
        final List<LTECallFailureEnodeBCategorySummaryResultRaw> eventResult = rt.translateResult(json,
                LTECallFailureEnodeBCategorySummaryResultRaw.class);
        assertResultDay(eventResult);
    }

    @Test
    public void testEnodeBCategorySummaryRankingDrillDown15min() throws Exception {
        insertAggData15Min();
        final String json = getJsonResult15Min(true);
        final ResultTranslator<LTECallFailureEnodeBCategorySummaryResultRaw> rt = getTranslator();
        final List<LTECallFailureEnodeBCategorySummaryResultRaw> eventResult = rt.translateResult(json,
                LTECallFailureEnodeBCategorySummaryResultRaw.class);
        assertResultDay(eventResult);
    }

    private void assertResultDay(final List<LTECallFailureEnodeBCategorySummaryResultRaw> results) {
        assertThat(results.size(), is(2));
        for (final LTECallFailureEnodeBCategorySummaryResultRaw rs : results) {
            if (rs.getCategoryId().equals("0")) {
                assertEquals("30", rs.getNoOfErrors());
            } else {
                assertEquals("120", rs.getNoOfErrors());
            }
        }
    }
}