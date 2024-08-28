/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.trac;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFATrackingAreaCallSetupRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureTrackingAreaRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_CFA_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFATrackingAreaCallSetupRankingServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureTrackingAreaRankingResult> {
    private LTECFATrackingAreaCallSetupRankingService lteCallFailureTrackingAreaCallSetupRankingService;

    private static final int TEST_TRAC_1 = 11111119;

    private static final int TEST_TRAC_2 = 22222229;

    private static final int TEST_TRAC_3 = 33333339;

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTrackingAreaCallSetupRankingService = new LTECFATrackingAreaCallSetupRankingService();
        attachDependencies(lteCallFailureTrackingAreaCallSetupRankingService);
        createTables();
        insertData();
    }

    /**
     * This function is used to assert if the actual test result is the same with the expect test result.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_TRAC_1(11111111) 3 call drop failures events
     * 2. TEST_TRAC_2(22222222) 2 call drop failures events
     * 3. TEST_TRAC_3(33333333) 1 call drop failures events
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
     * raw table: EVENT_E_LTE_CFA_RAW
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TRAC);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);

    }

    /**
     * This function is used to insert test data to the prepared tables.
     * For each IMSI, four type of events will be inserted:
     *  INTERNAL_PROC_UE_CTXT_RELEASE
     * 
     * The following data will be inserted to the raw tables.
     * 1. TEST_TRAC_1(11111111) 3 drop call failures events
     * 2. TEST_TRAC_2(22222222) 2 drop call failures events
     * 3. TEST_TRAC_3(33333333) 1 drop call failures events
     * @throws Exception
     */
    private void insertData() throws SQLException {

        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertTRAC(TEST_TRAC_1, dateTime, LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 3);
        insertTRAC(TEST_TRAC_2, dateTime, LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 2);
        insertTRAC(TEST_TRAC_3, dateTime, LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 1);
    }

    /**
     * This function is used to insert no. of event with IMSI to the table.
     * @param imsi
     *  The Subscriber's IMSI.
     * @param dt
     *  The event time.
     * @param eventID
     *  The event ID.
     * @param instances
     *  The number of events.
     * @throws SQLException
     */
    private void insertTRAC(final int trac, final String dt, final String categoryId, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(CATEGORY_ID, Integer.valueOf(categoryId));
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TRAC, trac);
            valuesForTable.put(TAC, TEST_VALUE_TAC);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJson(final String timerange) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, timerange);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteCallFailureTrackingAreaCallSetupRankingService, requestParameters);
    }

    /**
     * This test case is used to test the LTE CFA IMSI Drop Call Failures Ranking.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_IMSI_1(11111111) 12 call setup failures events
     * 2. TEST_IMSI_2(22222222) 8 call setup failures events
     * 3. TEST_IMSI_3(33333333) 4 call setup failures events
     * @throws Exception
     */
    @Test
    public void testGetRankingDataTrackingAreaCallSetupRankingRaw() throws Exception {
        final String json = getJson(FIVE_MINUTES);

        final ResultTranslator<LTECallFailureTrackingAreaRankingResult> rt = getTranslator();
        final List<LTECallFailureTrackingAreaRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureTrackingAreaRankingResult.class);
        assertResult(rankingResult);
    }
}
