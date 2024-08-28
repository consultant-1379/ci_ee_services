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
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureTrackingAreaEventSummaryResult;
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
public class LTECFATrackingAreaEventSummaryServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureTrackingAreaEventSummaryResult> {
    private LTECFATrackingAreaEventSummaryService lteCallFailureTrackingAreaEventSummaryService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final int TEST_VALUE_TRAC = 1;

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
        lteCallFailureTrackingAreaEventSummaryService = new LTECFATrackingAreaEventSummaryService();
        attachDependencies(lteCallFailureTrackingAreaEventSummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
        insertRawData();
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
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

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
    }

    private void insertData(final int trac, final String eventID, final String categoryID,final int tac, final String time, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(TRAC, trac);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResultCallSetUp() throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(NODE_PARAM, String.valueOf(TEST_VALUE_TRAC));
        requestParameters.putSingle(CATEGORY_ID, TEST_VALUE_CATEGORY_ID_CALL_SETUP);
        return runQuery(lteCallFailureTrackingAreaEventSummaryService, requestParameters);
    }

    private String getJsonResultCallDrop() throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(NODE_PARAM, String.valueOf(TEST_VALUE_TRAC));
        requestParameters.putSingle(CATEGORY_ID, TEST_VALUE_CATEGORY_ID_CALL_DROP);
        return runQuery(lteCallFailureTrackingAreaEventSummaryService, requestParameters);
    }
    
    private void insertRawData() throws Exception {
        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_RRC_CONN_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_RRC_CONN_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_S1_SIG_CONN_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_S1_SIG_CONN_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_INITIAL_CTXT_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_INITIAL_CTXT_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_ERAB_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_ERAB_SETUP,TEST_VALUE_CATEGORY_ID_CALL_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_UE_CTXT_RELEASE,TEST_VALUE_CATEGORY_ID_CALL_DROP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC, INTERNAL_PROC_UE_CTXT_RELEASE,TEST_VALUE_CATEGORY_ID_CALL_DROP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

    }

    @Test
    public void testTrackingAreaEventSummaryCallSetUp() throws Exception {
        final String json = getJsonResultCallSetUp();
        final ResultTranslator<LTECallFailureTrackingAreaEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureTrackingAreaEventSummaryResult.class);
        assertThat(eventResult.size(), is(4));
        assertResult(eventResult);
    }

    @Test
    public void testTrackingAreaEventSummaryCallDrop() throws Exception {
        final String json = getJsonResultCallDrop();
        final ResultTranslator<LTECallFailureTrackingAreaEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureTrackingAreaEventSummaryResult.class);
        assertThat(eventResult.size(), is(1));
        assertResult(eventResult);
    }
    
    private void assertResult(final List<LTECallFailureTrackingAreaEventSummaryResult> results) {
        for (final LTECallFailureTrackingAreaEventSummaryResult rs : results) {
            assertEquals(rs.getFailures(), 2);
            assertEquals(rs.getImpactedSubscribers(), 1);
        }
    }
}
