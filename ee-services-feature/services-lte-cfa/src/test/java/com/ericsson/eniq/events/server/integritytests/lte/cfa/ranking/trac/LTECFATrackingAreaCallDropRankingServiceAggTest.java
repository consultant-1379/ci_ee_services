/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.trac;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFATrackingAreaCallDropRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureTrackingAreaRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFATrackingAreaCallDropRankingServiceAggTest extends
        BaseDataIntegrityTest<LTECallFailureTrackingAreaRankingResult> {
    private LTECFATrackingAreaCallDropRankingService lteCallFailureTrackingAreaCallDropRankingService;

    private static final int TEST_TRAC_1 = 1;

    private static final int TEST_TRAC_2 = 2;

    private static final int TEST_TRAC_3 = 3;

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTrackingAreaCallDropRankingService = new LTECFATrackingAreaCallDropRankingService();
        attachDependencies(lteCallFailureTrackingAreaCallDropRankingService);
        createTables();
        insertData();
    }

    /**
     * This function is used to assert if the actual test result is the same with the expect test result.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TAC_1(11111111) 3 call drop failures events
     * 2. TEST_TAC_2(22222222) 2 call drop failures events
     * 3. TEST_TAC_3(33333333) 1 call drop failures events
     * @param rankingResult
     */
    private void assertResult(final List<LTECallFailureTrackingAreaRankingResult> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final LTECallFailureTrackingAreaRankingResult worstSubscriber = rankingResult.get(0);
        assertThat(worstSubscriber.getRank(), is(1));
        assertThat(worstSubscriber.getTrac(), is(String.valueOf(TEST_TRAC_1)));
        assertThat("worstSubscriber should have exactly 3 Drop Calls events!", worstSubscriber.getFailures(), is(3));

        final LTECallFailureTrackingAreaRankingResult nextWorstSubscriber = rankingResult.get(1);
        assertThat(nextWorstSubscriber.getRank(), is(2));
        assertThat(nextWorstSubscriber.getTrac(), is(String.valueOf(TEST_TRAC_2)));
        assertThat("nextWorstSubscriber should have exactly 2 Drop Calls events!", nextWorstSubscriber.getFailures(),
                is(2));

        final LTECallFailureTrackingAreaRankingResult lastSubscriber = rankingResult.get(2);
        assertThat(lastSubscriber.getRank(), is(3));
        assertThat(lastSubscriber.getTrac(), is(String.valueOf(TEST_TRAC_3)));
        assertThat("nextWorstSubscriber should have exactly 1 Drop Calls event!", lastSubscriber.getFailures(), is(1));
    }

    /**
     * Create the prepare test tables for testing.
     * 
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TRAC);
        columnsForEETable.add(NO_OF_ERRORS);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, columnsForEETable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, columnsForEETable);

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
        insertTAC(TEST_TRAC_1, dateTime, 0, 3);
        insertTAC(TEST_TRAC_2, dateTime, 0, 2);
        insertTAC(TEST_TRAC_3, dateTime, 0, 1);
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
            valuesForTable.put(TRAC, tac);
            valuesForTable.put(NO_OF_ERRORS, 1);
            insertRow(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_15MIN, valuesForTable);
            insertRow(TEMP_EVENT_E_LTE_CFA_TRAC_HIER3_EVENTID_ERR_DAY, valuesForTable);
        }
    }

    private String getJson(final String timerange) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, timerange);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteCallFailureTrackingAreaCallDropRankingService, requestParameters);
    }

    /**
     * This test case is used to test the LTE CFA Terminal Drop Call Failures Ranking.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TAC_1(11111111) 3 call setup failures events
     * 2. TEST_TAC_2(22222222) 2 call setup failures events
     * 3. TEST_TAC_3(33333333) 1 call setup failures events
     * @throws Exception
     */
    @Test
    public void testGetRankingDataTerminalCallDropRankingAgg15MIN() throws Exception {
        final String json = getJson(ONE_DAY);

        final ResultTranslator<LTECallFailureTrackingAreaRankingResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureTrackingAreaRankingResult.class);
        assertResult(rankingResult);
    }

    /**
     * This test case is used to test the LTE CFA Terminal Drop Call Failures Ranking.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TAC_1(11111111) 3 call setup failures events
     * 2. TEST_TAC_2(22222222) 2 call setup failures events
     * 3. TEST_TAC_3(33333333) 1 call setup failures events
     * @throws Exception
     */
    @Test
    public void testGetRankingDataTerminalCallDropRankingAggDAY() throws Exception {
        final String json = getJson(TWO_WEEKS);

        final ResultTranslator<LTECallFailureTrackingAreaRankingResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureTrackingAreaRankingResult.class);
        assertResult(rankingResult);
    }
}
