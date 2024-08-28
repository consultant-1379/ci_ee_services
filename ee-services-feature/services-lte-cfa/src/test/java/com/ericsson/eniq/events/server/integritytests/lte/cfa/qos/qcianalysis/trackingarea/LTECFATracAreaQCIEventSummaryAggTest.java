/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.qos.qcianalysis.trackingarea;

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFATrackingAreaQCIEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.qos.LTECallFailureQOSTrackingAreaQCIEventSummaryResult;
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
import static org.junit.Assert.assertThat;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFATracAreaQCIEventSummaryAggTest extends
        BaseDataIntegrityTest<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> {

    LTECFATrackingAreaQCIEventSummaryService lteCallFailureTrackingAreaQCIEventSummaryService;

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

    private static final int TEST_VALUE_TRAC_ID = 1;

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    private static final String TEST_VALUE_IMSI_2 = "11111118";

    private static final int TEST_VALUE_QCI = 1;

    private static final int CATEGORY_ID_CALL_DROP = 0;

    private static final int CATEGORY_ID_CALL_SETUP = 1;

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTrackingAreaQCIEventSummaryService = new LTECFATrackingAreaQCIEventSummaryService();
        attachDependencies(lteCallFailureTrackingAreaQCIEventSummaryService);
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
        columnsForTable.add(EVENT_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(QCI_ID_COLUMN);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(TRAC);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(EVENT_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, columnsForTable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_ERAB_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_ERAB_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_INITIAL_CTXT_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_UE_CTXT_RELEASE");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void insertData(final int tac, final int categoryID, final String eventID, final String time,
            final int instances, final String imsi) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI, imsi);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(TRAC, TEST_VALUE_TRAC_ID);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(QCI_ID_COLUMN, TEST_VALUE_QCI);
            insertRow(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_ERR_RAW, valuesForTable);
        }
    }

    private void insertAggData(final String aggTable, final int categoryID, final String eventID, final String time,
            final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(QCI_ID_COLUMN, TEST_VALUE_QCI);
        valuesForTable.put(CATEGORY_ID, categoryID);
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_ID);
        valuesForTable.put(NO_OF_ERRORS, instances);
        valuesForTable.put(DATETIME_ID, time);
        valuesForTable.put(EVENT_ID, eventID);
        insertRow(aggTable, valuesForTable);
    }

    private void insertDataForAggTestWithEventID(final int categoryID, final String eventID, final int instances,
            final String dateTimeID, final String aggTable) throws SQLException {
        insertData(SAMPLE_TAC, categoryID, eventID, dateTimeID, instances, TEST_VALUE_IMSI_1);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), categoryID, eventID, dateTimeID, instances,
                TEST_VALUE_IMSI_1);
        insertData(SAMPLE_TAC, categoryID, eventID, dateTimeID, instances, TEST_VALUE_IMSI_2);
        insertData(Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), categoryID, eventID, dateTimeID, instances,
                TEST_VALUE_IMSI_2);
        insertAggData(aggTable, categoryID, eventID, dateTimeID, instances);
    }

    private void insertAggData(final String aggTable, final String dateTimeID) throws Exception {
        insertDataForAggTestWithEventID(CATEGORY_ID_CALL_DROP, EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE, 2,
                dateTimeID, aggTable);
        insertDataForAggTestWithEventID(CATEGORY_ID_CALL_SETUP, EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP, 2,
                dateTimeID, aggTable);
        insertDataForAggTestWithEventID(CATEGORY_ID_CALL_SETUP, EventIDConstants.INTERNAL_PROC_ERAB_SETUP, 2,
                dateTimeID, aggTable);
    }

    private String getJsonResultAgg(final String dateFrom, final String dateTo, final int categoryID)
            throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(CATEGORY_ID, String.valueOf(categoryID));
        requestParameters.putSingle(QCI_ID_COLUMN, String.valueOf(TEST_VALUE_QCI));
        requestParameters.putSingle(TRAC, String.valueOf(TEST_VALUE_TRAC_ID));
        return runQuery(lteCallFailureTrackingAreaQCIEventSummaryService, requestParameters);
    }

    @Test
    public void testTrackingAreaQCIEventSummary15MinAggCallDrop() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResultAgg(DATE_FROM_15MIN, DATE_TO_15MIN, CATEGORY_ID_CALL_DROP);

        final ResultTranslator<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSTrackingAreaQCIEventSummaryResult.class);
        assertThat(eventResult.size(), is(1));
    }

    @Test
    public void testTrackingAreaQCIEventSummary15MinAggCallSetup() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResultAgg(DATE_FROM_15MIN, DATE_TO_15MIN, CATEGORY_ID_CALL_SETUP);

        final ResultTranslator<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSTrackingAreaQCIEventSummaryResult.class);
        assertThat(eventResult.size(), is(2));
    }

    @Test
    public void testTrackingAreaQCIEventSummaryDayAggCallDrop() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, DATETIME_ID_DAY_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResultAgg(DATE_FROM_DAY, DATE_TO_DAY, CATEGORY_ID_CALL_DROP);

        final ResultTranslator<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSTrackingAreaQCIEventSummaryResult.class);
        assertThat(eventResult.size(), is(1));
    }

    @Test
    public void testTrackingAreaQCIEventSummaryDayAggCallSetup() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, DATETIME_ID_DAY_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_TRAC_HIER3_EVENTID_QCI_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResultAgg(DATE_FROM_DAY, DATE_TO_DAY, CATEGORY_ID_CALL_SETUP);

        final ResultTranslator<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSTrackingAreaQCIEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSTrackingAreaQCIEventSummaryResult.class);
        assertThat(eventResult.size(), is(2));
    }
}
