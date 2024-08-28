/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.qos.qcianalysis.trackingarea;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFATrackingAreaQCICategorySummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.qos.LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult;
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
 * @author echimma
 * @since 2011
 *
 */
public class LTECFATracAreaGrpQCICatSummaryAggTest extends
        BaseDataIntegrityTest<LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult> {
	LTECFATrackingAreaQCICategorySummaryService lteCallFailureTrackingAreaQCICategarySummaryService;

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

    private static final int TEST_VALUE_TRAC_1 = 1;

    private static final int TEST_VALUE_TRAC_2 = 2;

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    private static final String TEST_VALUE_IMSI_2 = "11111118";

    private static final int TEST_VALUE_QCI_ID_1 = 1;

    private static final int TEST_VALUE_QCI_ID_2 = 2;

    private static final int TEST_VALUE_QCI_ID_3 = 3;

    private static final int CATEGORY_ID_CALL_DROP = 0;

    private static final int CATEGORY_ID_CALL_SETUP = 1;

    private static final String TEST_VALUE_GROUP_NAME = "tracGroup";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTrackingAreaQCICategarySummaryService = new LTECFATrackingAreaQCICategorySummaryService();
        attachDependencies(lteCallFailureTrackingAreaQCICategarySummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_LTE_QCI, TEMP_DIM_E_LTE_QCI);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(TRAC);
        columnsForTable.add(QCI_ID_COLUMN);
        columnsForTable.add(TAC);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(QCI_ID_COLUMN);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(TRAC);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, columnsForTable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(CATEGORY_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(QCI_NUMBER_COLUMN);
        columnsForTable.add(QCI_ID_DESCRIPTION);
        createTemporaryTable(TEMP_DIM_E_LTE_QCI, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(TRAC);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_LTE_TRAC, columnsForTable);

    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(CATEGORY_ID, CATEGORY_ID_CALL_DROP);
        valuesForTable.put(CATEGORY_ID_DESC, "Call Drops");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CATEGORY_ID, CATEGORY_ID_CALL_SETUP);
        valuesForTable.put(CATEGORY_ID_DESC, "Call Setup Failures");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(QCI_NUMBER_COLUMN, TEST_VALUE_QCI_ID_1);
        valuesForTable.put(QCI_ID_DESCRIPTION, "Conversational Voice");
        insertRow(TEMP_DIM_E_LTE_QCI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(QCI_NUMBER_COLUMN, TEST_VALUE_QCI_ID_2);
        valuesForTable.put(QCI_ID_DESCRIPTION, "Conversational Video (Live Streaming)");
        insertRow(TEMP_DIM_E_LTE_QCI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(QCI_NUMBER_COLUMN, TEST_VALUE_QCI_ID_3);
        valuesForTable.put(QCI_ID_DESCRIPTION, "Real Time Gaming");
        insertRow(TEMP_DIM_E_LTE_QCI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);
    }

    private void insertData(final int trac, final int tac, final int categoryID, final int qciID, final String time,
            final int instances, final String imsi) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, imsi);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(TRAC, trac);
            valuesForTable.put(QCI_ID_COLUMN, qciID);
            insertRow(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_ERR_RAW, valuesForTable);
        }
    }

    private void insertAggData(final String aggTable, final int trac, final int categoryID, final int qciID,
            final String time, final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(QCI_ID_COLUMN, qciID);
        valuesForTable.put(CATEGORY_ID, categoryID);
        valuesForTable.put(TRAC, trac);
        valuesForTable.put(NO_OF_ERRORS, instances);
        valuesForTable.put(DATETIME_ID, time);
        insertRow(aggTable, valuesForTable);
    }

    private void insertDataForAggTest(final int trac, final int categoryID, final int qciID, final int instances,
            final String dateTimeID, final String aggTable) throws SQLException {
        insertData(trac, SAMPLE_TAC, categoryID, qciID, dateTimeID, instances, TEST_VALUE_IMSI_1);
        insertData(trac, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), categoryID, qciID, dateTimeID, instances,
                TEST_VALUE_IMSI_1);
        insertData(trac, SAMPLE_TAC, categoryID, qciID, dateTimeID, instances, TEST_VALUE_IMSI_2);
        insertData(trac, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), categoryID, qciID, dateTimeID, instances,
                TEST_VALUE_IMSI_2);
        insertAggData(aggTable, trac, categoryID, qciID, dateTimeID, instances);
    }

    private void insertAggData(final String aggTable, final String dateTimeID) throws Exception {
        insertDataForAggTest(TEST_VALUE_TRAC_1, CATEGORY_ID_CALL_DROP, TEST_VALUE_QCI_ID_1, 2, dateTimeID, aggTable);
        insertDataForAggTest(TEST_VALUE_TRAC_1, CATEGORY_ID_CALL_SETUP, TEST_VALUE_QCI_ID_1, 2, dateTimeID, aggTable);

        insertDataForAggTest(TEST_VALUE_TRAC_1, CATEGORY_ID_CALL_DROP, TEST_VALUE_QCI_ID_2, 2, dateTimeID, aggTable);
        insertDataForAggTest(TEST_VALUE_TRAC_1, CATEGORY_ID_CALL_SETUP, TEST_VALUE_QCI_ID_2, 2, dateTimeID, aggTable);

        insertDataForAggTest(TEST_VALUE_TRAC_1, CATEGORY_ID_CALL_DROP, TEST_VALUE_QCI_ID_3, 2, dateTimeID, aggTable);
        insertDataForAggTest(TEST_VALUE_TRAC_1, CATEGORY_ID_CALL_SETUP, TEST_VALUE_QCI_ID_3, 2, dateTimeID, aggTable);

        insertDataForAggTest(TEST_VALUE_TRAC_2, CATEGORY_ID_CALL_DROP, TEST_VALUE_QCI_ID_1, 2, dateTimeID, aggTable);
        insertDataForAggTest(TEST_VALUE_TRAC_2, CATEGORY_ID_CALL_SETUP, TEST_VALUE_QCI_ID_1, 2, dateTimeID, aggTable);

        insertDataForAggTest(TEST_VALUE_TRAC_2, CATEGORY_ID_CALL_DROP, TEST_VALUE_QCI_ID_2, 2, dateTimeID, aggTable);
        insertDataForAggTest(TEST_VALUE_TRAC_2, CATEGORY_ID_CALL_SETUP, TEST_VALUE_QCI_ID_2, 2, dateTimeID, aggTable);

        insertDataForAggTest(TEST_VALUE_TRAC_2, CATEGORY_ID_CALL_DROP, TEST_VALUE_QCI_ID_3, 2, dateTimeID, aggTable);
        insertDataForAggTest(TEST_VALUE_TRAC_2, CATEGORY_ID_CALL_SETUP, TEST_VALUE_QCI_ID_3, 2, dateTimeID, aggTable);
    }

    private String getJsonResultAgg(final String dateFrom, final String dateTo) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_TRAC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        return runQuery(lteCallFailureTrackingAreaQCICategarySummaryService, requestParameters);
    }

    private void assertResult(final List<LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult> results) {
        assertThat(results.size(), is(6));
        for (final LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult rs : results) {
            assertEquals(rs.getFailures(), 8);
            assertEquals(rs.getImpactedSubscribers(), 2);
        }
    }

    @Test
    public void testTrackingAreaGroupQCICategorySummary15MinAgg() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResultAgg(DATE_FROM_15MIN, DATE_TO_15MIN);

        final ResultTranslator<LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult.class);
        assertResult(eventResult);
    }

    @Test
    public void testTrackingAreaGroupQCICategorySummaryDayAgg() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, DATETIME_ID_DAY_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResultAgg(DATE_FROM_DAY, DATE_TO_DAY);

        final ResultTranslator<LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSTrackingAreaGroupQCICategorySummaryResult.class);
        assertResult(eventResult);
    }
}