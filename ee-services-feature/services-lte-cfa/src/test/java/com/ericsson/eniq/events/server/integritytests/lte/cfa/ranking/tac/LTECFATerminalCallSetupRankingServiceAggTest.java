/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.tac;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFATerminalCallSetupRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureTerminalRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_15MIN;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_DAY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFATerminalCallSetupRankingServiceAggTest extends
        BaseDataIntegrityTest<LTECallFailureTerminalRankingResult> {
    private LTECFATerminalCallSetupRankingService lteCallFailureTerminalCallSetupRankingService;

    private static final int TEST_TAC_1 = 11111119;

    private static final int TEST_TAC_2 = 22222229;

    private static final int TEST_TAC_3 = 33333339;

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTerminalCallSetupRankingService = new LTECFATerminalCallSetupRankingService();
        attachDependencies(lteCallFailureTerminalCallSetupRankingService);
        createTables();
        insertData();
    }

    /**
     * This function is used to assert if the actual test result is the same with the expect test result.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TAC_1(11111111) 12 call drop failures events
     * 2. TEST_TAC_2(22222222) 8 call drop failures events
     * 3. TEST_TAC_3(33333333) 4 call drop failures events
     * @param rankingResult
     */
    private void assertResult(final List<LTECallFailureTerminalRankingResult> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final LTECallFailureTerminalRankingResult worstSubscriber = rankingResult.get(0);
        assertThat(worstSubscriber.getRank(), is(1));
        assertThat(worstSubscriber.getTac(), is(String.valueOf(TEST_TAC_1)));
        assertThat("worstSubscriber should have exactly 3 Drop Calls events!", worstSubscriber.getFailures(), is(12));

        final LTECallFailureTerminalRankingResult nextWorstSubscriber = rankingResult.get(1);
        assertThat(nextWorstSubscriber.getRank(), is(2));
        assertThat(nextWorstSubscriber.getTac(), is(String.valueOf(TEST_TAC_2)));
        assertThat("nextWorstSubscriber should have exactly 2 Drop Calls events!", nextWorstSubscriber.getFailures(),
                is(8));

        final LTECallFailureTerminalRankingResult lastSubscriber = rankingResult.get(2);
        assertThat(lastSubscriber.getRank(), is(3));
        assertThat(lastSubscriber.getTac(), is(String.valueOf(TEST_TAC_3)));
        assertThat("nextWorstSubscriber should have exactly 1 Drop Calls event!", lastSubscriber.getFailures(), is(4));
    }

    /**
     * Create the prepare test tables for testing.
     * 
     * Agg table: TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_15MIN, TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_DAY
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        columnsForEETable.add(NO_OF_ERRORS);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_15MIN, columnsForEETable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_DAY, columnsForEETable);

    }

    /**
     * This function is used to insert test data to the prepared tables.
     * For each IMSI, four type of events will be inserted:
     *  INTERNAL_PROC_UE_CTXT_RELEASE
     * 
     * The following data will be inserted to the raw tables.
     * 1. TEST_TAC_1(11111111) 3 drop call failures events
     * 2. TEST_TAC_2(22222222) 2 drop call failures events
     * 3. TEST_TAC_3(33333333) 1 drop call failures events
     * @throws Exception
     */
    private void insertData() throws SQLException {
        insertData(DateTimeUtilities.getDateTimeMinus55Minutes());
        insertData(DateTimeUtilities.getDateTimeMinus48Hours());
    }

    private void insertData(final String dateTime) throws SQLException {
        insertTAC(TEST_TAC_1, dateTime, 1, 12);
        insertTAC(TEST_TAC_2, dateTime, 1, 8);
        insertTAC(TEST_TAC_3, dateTime, 1, 4);
    }

    /**
     * This function is used to insert no. of event with TAC to the table.
     * @param tac
     *  The Terminal.
     * @param dt
     *  The event time.
     * @param eventID
     *  The event ID.
     * @param instances
     *  The number of events.
     * @throws SQLException
     */
    private void insertTAC(final int tac, final String dt, final int categoryID, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(NO_OF_ERRORS, 1);
            insertRow(TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_15MIN, valuesForTable);
            insertRow(TEMP_EVENT_E_LTE_CFA_TAC_EVENTID_ERR_DAY, valuesForTable);
        }
    }

    private String getJson(final String timerange) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, timerange);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteCallFailureTerminalCallSetupRankingService, requestParameters);
    }

    /**
     * This test case is used to test the LTE CFA Terminal Call Setup Failures Ranking.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TAC_1(11111111) 12 call setup failures events
     * 2. TEST_TAC_2(22222222) 8 call setup failures events
     * 3. TEST_TAC_3(33333333) 4 call setup failures events
     * @throws Exception
     */
    @Test
    public void testGetRankingDataTerminalCallSetupRankingAgg15MIN() throws Exception {
        final String json = getJson(ONE_DAY);

        final ResultTranslator<LTECallFailureTerminalRankingResult> rt = getTranslator();
        final List<LTECallFailureTerminalRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureTerminalRankingResult.class);
        assertResult(rankingResult);
    }

    /**
     * This test case is used to test the LTE CFA Terminal Call Setup Failures Ranking.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TAC_1(11111111) 12 call setup failures events
     * 2. TEST_TAC_2(22222222) 8 call setup failures events
     * 3. TEST_TAC_3(33333333) 4 call setup failures events
     * @throws Exception
     */
    @Test
    public void testGetRankingDataTerminalCallSetupRankingAggDAY() throws Exception {
        final String json = getJson(TWO_WEEKS);

        final ResultTranslator<LTECallFailureTerminalRankingResult> rt = getTranslator();
        final List<LTECallFailureTerminalRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureTerminalRankingResult.class);
        assertResult(rankingResult);
    }
}
