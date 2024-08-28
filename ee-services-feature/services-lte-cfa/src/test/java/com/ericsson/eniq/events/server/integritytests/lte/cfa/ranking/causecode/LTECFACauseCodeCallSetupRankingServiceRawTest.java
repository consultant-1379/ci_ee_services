/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.causecode;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFACauseCodeCallSetupRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureCauseCodeRankingResults;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFACauseCodeCallSetupRankingServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureCauseCodeRankingResults> {
    private LTECFACauseCodeCallSetupRankingService lteCallFailureCauseCodeCallSetupRankingService;

    private static final String TEST_VALUE_CAUSE_CODE_ID_1 = "10";

    private static final String TEST_VALUE_CAUSE_CODE_ID_2 = "11";

    private static final String TEST_VALUE_CAUSE_CODE_ID_3 = "12";

    private static final String TEST_VALUE_CAUSE_CODE_ID_4 = "13";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_1 = "Cause Code 1";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_2 = "Cause Code 2";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_3 = "Cause Code 3";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_4 = "Cause Code 4";

    @Before
    public void onSetUp() throws Exception {
        lteCallFailureCauseCodeCallSetupRankingService = new LTECFACauseCodeCallSetupRankingService();
        attachDependencies(lteCallFailureCauseCodeCallSetupRankingService);

        createTables();
        insertData();
    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertData() throws SQLException {
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_1, TEST_VALUE_CAUSE_CODE_DESC_1, INTERNAL_PROC_RRC_CONN_SETUP);
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_2, TEST_VALUE_CAUSE_CODE_DESC_2, INTERNAL_PROC_S1_SIG_CONN_SETUP);
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_3, TEST_VALUE_CAUSE_CODE_DESC_3, INTERNAL_PROC_INITIAL_CTXT_SETUP);
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_4, TEST_VALUE_CAUSE_CODE_DESC_4, INTERNAL_PROC_ERAB_SETUP);

        insertTopoData(INTERNAL_PROC_RRC_CONN_SETUP, "INTERNAL_PROC_RRC_CONN_SETUP");
        insertTopoData(INTERNAL_PROC_S1_SIG_CONN_SETUP, "INTERNAL_PROC_S1_SIG_CONN_SETUP");
        insertTopoData(INTERNAL_PROC_INITIAL_CTXT_SETUP, "INTERNAL_PROC_INITIAL_CTXT_SETUP");
        insertTopoData(INTERNAL_PROC_ERAB_SETUP, "INTERNAL_PROC_ERAB_SETUP");
        insertTopoData(INTERNAL_PROC_UE_CTXT_RELEASE, "INTERNAL_PROC_UE_CTXT_RELEASE");

        //insert data to raw tables
        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();
        insertEventWithCauseCode(TEST_VALUE_CAUSE_CODE_ID_1, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID,
                dateTime, 4);
        insertEventWithCauseCode(TEST_VALUE_CAUSE_CODE_ID_1, INTERNAL_PROC_RRC_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, dateTime, 4);
        insertEventWithCauseCode(TEST_VALUE_CAUSE_CODE_ID_2, INTERNAL_PROC_S1_SIG_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, dateTime, 3);
        insertEventWithCauseCode(TEST_VALUE_CAUSE_CODE_ID_3, INTERNAL_PROC_INITIAL_CTXT_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, dateTime, 2);
        insertEventWithCauseCode(TEST_VALUE_CAUSE_CODE_ID_4, INTERNAL_PROC_ERAB_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, dateTime, 1);
        insertEventWithCauseCode(TEST_VALUE_CAUSE_CODE_ID_4, INTERNAL_PROC_RRC_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, dateTime, 1);

    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertTopoData(final String causeCodeID, final String causeCodeDesc, final String eventID)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(CAUSE_CODE_COLUMN, causeCodeID);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, causeCodeDesc);
        valuesForTable.put(EVENT_ID, eventID);
        insertRow(TEMP_DIM_E_LTE_CFA_CAUSE_CODE, valuesForTable);
    }

    private void insertTopoData(final String eventID, final String eventTypeDesc) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(EVENT_ID_DESC, eventTypeDesc);
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);
    }

    /**
     * 
     */
    private void insertEventWithCauseCode(final String causeCodeID, final String eventID, final String categoryID,
            final String dateTime, final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        for (int i = 0; i < instances; i++) {
            valuesForTable.clear();
            valuesForTable.put(CAUSE_CODE_COLUMN, causeCodeID);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(CATEGORY_ID, Integer.valueOf(categoryID));
            valuesForTable.put(DATETIME_ID, dateTime);
            valuesForTable.put(TAC, SAMPLE_TAC);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
        }
    }

    /**
     * @throws Exception 
     * 
     */
    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(CAUSE_CODE_COLUMN);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(CAUSE_CODE_COLUMN);
        columnsForEETable.add(CAUSE_CODE_DESC_COLUMN);
        columnsForEETable.add(EVENT_ID);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_CAUSE_CODE, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForEETable);

    }

    @Test
    public void testGetLTECFACauseCodeCallDropRankingData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureCauseCodeCallSetupRankingService, requestParameters);

        final ResultTranslator<LTECallFailureCauseCodeRankingResults> rt = getTranslator();
        final List<LTECallFailureCauseCodeRankingResults> rankingResult = rt.translateResult(json,
                LTECallFailureCauseCodeRankingResults.class);
        assertResult(rankingResult);
    }

    private void assertResult(final List<LTECallFailureCauseCodeRankingResults> rankingResult) {
        assertThat("There should be exactly 5 results!", rankingResult.size(), is(5));

        final LTECallFailureCauseCodeRankingResults result1 = rankingResult.get(0);
        assertThat(result1.getRank(), is(1));
        assertThat(result1.getEventTypeDesc(), is("INTERNAL_PROC_RRC_CONN_SETUP"));
        assertThat(result1.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_1));
        assertThat(result1.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_1));
        assertThat(result1.getFailures(), is(4));

        final LTECallFailureCauseCodeRankingResults result2 = rankingResult.get(1);
        assertThat(result2.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_2));
        assertThat(result2.getEventTypeDesc(), is("INTERNAL_PROC_S1_SIG_CONN_SETUP"));
        assertThat(result2.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_2));
        assertThat(result2.getRank(), is(2));
        assertThat(result2.getFailures(), is(3));

        final LTECallFailureCauseCodeRankingResults result3 = rankingResult.get(2);
        assertThat(result3.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_3));
        assertThat(result3.getEventTypeDesc(), is("INTERNAL_PROC_INITIAL_CTXT_SETUP"));
        assertThat(result3.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_3));
        assertThat(result3.getRank(), is(3));
        assertThat(result3.getFailures(), is(2));

        final LTECallFailureCauseCodeRankingResults result4 = rankingResult.get(3);
        assertThat(result4.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_4));
        assertThat(result4.getEventTypeDesc(), is("INTERNAL_PROC_RRC_CONN_SETUP"));
        assertThat(result4.getCauseCodeDesc(), is(""));
        assertThat(result4.getRank(), is(4));
        assertThat(result4.getFailures(), is(1));

        final LTECallFailureCauseCodeRankingResults result5 = rankingResult.get(4);
        assertThat(result5.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_4));
        assertThat(result5.getEventTypeDesc(), is("INTERNAL_PROC_ERAB_SETUP"));
        assertThat(result5.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_4));
        assertThat(result5.getRank(), is(4));
        assertThat(result5.getFailures(), is(1));
    }
}
