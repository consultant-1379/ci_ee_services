/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventanalysis.trackingarea;

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis.LTECFATrackingAreaEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureTrackingAreaGroupEventSummaryResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
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
public class LTECFATrackingAreaGroupEventSummaryAggTest extends
        BaseDataIntegrityTest<LTECallFailureTrackingAreaGroupEventSummaryResult> {
    private LTECFATrackingAreaEventSummaryService lteCallFailureTrackingAreaEventSummaryService;

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

    private static final String TEST_VALUE_TRACING_AREA_GROUP = "trackingAreaGroup";

    private static final String TEST_VALUE_CATEGORY_ID_CALL_SETUP = "1";

    private static final String TEST_VALUE_CATEGORY_ID_CALL_DROP = "0";
    
    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTrackingAreaEventSummaryService = new LTECFATrackingAreaEventSummaryService();
        attachDependencies(lteCallFailureTrackingAreaEventSummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(TRAC);
        columnsForTable.add(TAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(TRAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(TRAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(GROUP_NAME);
        columnsForTable.add(TRAC);
        createTemporaryTable(TEMP_GROUP_TYPE_E_LTE_TRAC, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_RRC_CONN_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_RRC_CONN_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_S1_SIG_CONN_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_S1_SIG_CONN_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_INITIAL_CTXT_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_ERAB_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_ERAB_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_UE_CTXT_RELEASE");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TRACING_AREA_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TRACING_AREA_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);
    }

    private void insertData(final int trac, final String eventID,final String categoryID, final int tac, final String time,
            final int instances, final String imsi) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(CATEGORY_ID,categoryID);
            valuesForTable.put(TRAC, trac);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, imsi);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
        }
    }

    private void insertAggData(final String aggTable, final int trac, final String eventID,final String categoryID, final String time,
            final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(CATEGORY_ID,categoryID);
        valuesForTable.put(TRAC, trac);
        valuesForTable.put(NO_OF_ERRORS, instances);
        valuesForTable.put(DATETIME_ID, time);
        insertRow(aggTable, valuesForTable);
    }

    private void insertDataForAggTestWithEventID(final String eventID,final String categoryID, final int instances, final String dateTimeID,
            final String aggTable) throws SQLException {
        insertData(TEST_VALUE_TRAC_1, eventID,categoryID, SAMPLE_TAC, dateTimeID, instances, TEST_VALUE_IMSI_1);
        insertData(TEST_VALUE_TRAC_1, eventID,categoryID, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), dateTimeID, instances,
                TEST_VALUE_IMSI_1);
        insertData(TEST_VALUE_TRAC_1, eventID,categoryID, SAMPLE_TAC, dateTimeID, instances, TEST_VALUE_IMSI_2);
        insertData(TEST_VALUE_TRAC_1, eventID,categoryID,Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC), dateTimeID, instances,
                TEST_VALUE_IMSI_2);
        insertAggData(aggTable, TEST_VALUE_TRAC_1, eventID,categoryID, dateTimeID, instances);
    }

    private String getJsonResultCallSetUp(final String dateFrom, final String dateTo) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(CATEGORY_ID, TEST_VALUE_CATEGORY_ID_CALL_SETUP);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_TRACING_AREA_GROUP);
        return runQuery(lteCallFailureTrackingAreaEventSummaryService, requestParameters);
    }

    private String getJsonResultCallDrop(final String dateFrom, final String dateTo) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(CATEGORY_ID, TEST_VALUE_CATEGORY_ID_CALL_DROP);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_TRACING_AREA_GROUP);
        return runQuery(lteCallFailureTrackingAreaEventSummaryService, requestParameters);
    }
    
    private void insertAggData(final String aggTable, final String dateTimeID) throws Exception {
        insertDataForAggTestWithEventID(INTERNAL_PROC_RRC_CONN_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, 2, dateTimeID, aggTable);
        insertDataForAggTestWithEventID(INTERNAL_PROC_S1_SIG_CONN_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, 2, dateTimeID, aggTable);
        insertDataForAggTestWithEventID(INTERNAL_PROC_INITIAL_CTXT_SETUP, TEST_VALUE_CATEGORY_ID_CALL_SETUP,2, dateTimeID, aggTable);
        insertDataForAggTestWithEventID(INTERNAL_PROC_ERAB_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, 2, dateTimeID, aggTable);
        insertDataForAggTestWithEventID(INTERNAL_PROC_UE_CTXT_RELEASE,TEST_VALUE_CATEGORY_ID_CALL_DROP, 2, dateTimeID, aggTable);
    }

    @Test
    public void testTrackingAreaEventSummaryCallSetUp15MinAgg() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResultCallSetUp(DATE_FROM_15MIN, DATE_TO_15MIN);

        final ResultTranslator<LTECallFailureTrackingAreaGroupEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaGroupEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureTrackingAreaGroupEventSummaryResult.class);
        assertThat(eventResult.size(), is(4));
        assertResult(eventResult);
    }

    @Test
    public void testTrackingAreaEventSummaryCallDrop15MinAgg() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResultCallDrop(DATE_FROM_15MIN, DATE_TO_15MIN);

        final ResultTranslator<LTECallFailureTrackingAreaGroupEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaGroupEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureTrackingAreaGroupEventSummaryResult.class);
        assertThat(eventResult.size(), is(1));
        assertResult(eventResult);
    }
    
    @Test
    public void testTrackingAreaEventSummaryCallSetupDayAgg() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResultCallSetUp(DATE_FROM_DAY, DATE_TO_DAY);

        final ResultTranslator<LTECallFailureTrackingAreaGroupEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaGroupEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureTrackingAreaGroupEventSummaryResult.class);
        assertThat(eventResult.size(), is(4));
        assertResult(eventResult);
    }

    @Test
    public void testTrackingAreaEventSummaryCallDropDayAgg() throws Exception {
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_1);
        insertAggData(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResultCallDrop(DATE_FROM_DAY, DATE_TO_DAY);

        final ResultTranslator<LTECallFailureTrackingAreaGroupEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaGroupEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureTrackingAreaGroupEventSummaryResult.class);
        assertThat(eventResult.size(), is(1));
        assertResult(eventResult);
    }

    private void assertResult(final List<LTECallFailureTrackingAreaGroupEventSummaryResult> results) {
        for (final LTECallFailureTrackingAreaGroupEventSummaryResult rs : results) {
            //each eventID has two events happened at the same time. 
            //for 15MIN test: (DATETIME_ID_15MIN_1, DATETIME_ID_15MIN_2)
            //for DAY test: (DATETIME_ID_DAY_1, DATETIME_ID_DAY_2)
            //so each type of events, the failures is 4, and have 2 impacted subscriber (TEST_VALUE_IMSI_1, TEST_VALUE_IMSI_2)
            assertEquals(rs.getFailures(), 4);
            assertEquals(rs.getImpactedSubscribers(), 2);
        }
    }
}
