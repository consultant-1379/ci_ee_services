/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.imsi;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFASubscriberCallDropRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberCallSetupFailureRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_CFA_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFASubScriberCallDropRankingServiceRawTest extends
        BaseDataIntegrityTest<SubscriberCallSetupFailureRankingResult> {

    private LTECFASubscriberCallDropRankingService lteCallFailureSubscriberCallDropRankingService;

    private static final int TEST_IMSI_0 = 0;

    private static final int TEST_IMSI_1 = 11111119;

    private static final int TEST_IMSI_2 = 22222229;

    private static final int TEST_IMSI_3 = 33333339;

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureSubscriberCallDropRankingService = new LTECFASubscriberCallDropRankingService();
        attachDependencies(lteCallFailureSubscriberCallDropRankingService);
        createTables();
        insertData();
    }

    /**
     * This function is used to assert if the actual test result is the same with the expect test result.
     * In the test case, excpet 3 ranking result.
     * 1. TEST_IMSI_1(11111111) 3 call setup failures events
     * 2. TEST_IMSI_2(22222222) 2 call setup failures events
     * 3. TEST_IMSI_3(33333333) 1 call setup failures events
     * @param rankingResult
     */
    private void assertResult(final List<SubscriberCallSetupFailureRankingResult> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final SubscriberCallSetupFailureRankingResult worstSubscriber = rankingResult.get(0);
        assertThat(worstSubscriber.getRank(), is(1));
        assertThat(worstSubscriber.getImsi(), is(TEST_IMSI_1));
        assertThat("worstSubscriber should have exactly 3 Drop Calls events!", worstSubscriber.getNumFailures(), is(3));

        final SubscriberCallSetupFailureRankingResult nextWorstSubscriber = rankingResult.get(1);
        assertThat(nextWorstSubscriber.getRank(), is(2));
        assertThat(nextWorstSubscriber.getImsi(), is(TEST_IMSI_2));
        assertThat("nextWorstSubscriber should have exactly 2 Drop Calls events!",
                nextWorstSubscriber.getNumFailures(), is(2));

        final SubscriberCallSetupFailureRankingResult lastSubscriber = rankingResult.get(2);
        assertThat(lastSubscriber.getRank(), is(3));
        assertThat(lastSubscriber.getImsi(), is(TEST_IMSI_3));
        assertThat("nextWorstSubscriber should have exactly 1 Drop Calls event!", lastSubscriber.getNumFailures(),
                is(1));
    }

    /**
     * Create the prepare test tables for testing.
     * 
     * raw table: EVENT_E_LTE_CFA_RAW
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(IMSI);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);

    }

    /**
     * This function is used to insert test data to the prepared tables.
     * For each IMSI, four type of events will be inserted:
     *  INTERNAL_PROC_UE_CTXT_RELEASE
     * 
     * The following data will be inserted to the raw tables.
     * 1. TEST_IMSI_1(11111111) 3 drop call failures events
     * 2. TEST_IMSI_2(22222222) 2 drop call failures events
     * 3. TEST_IMSI_3(33333333) 1 drop call failures events
     * @throws Exception
     */
    private void insertData() throws Exception {

        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertIMSI(TEST_IMSI_0, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 4);
        insertIMSI(TEST_IMSI_1, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 3);
        insertIMSI(TEST_IMSI_2, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 2);
        insertIMSI(TEST_IMSI_3, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 1);
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
    private void insertIMSI(final int imsi, final String dt, final String eventID, final String categoryID,
            final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(IMSI, imsi);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(CATEGORY_ID, Integer.valueOf(categoryID));
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, SAMPLE_TAC);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
        }
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
    public void testGetRankingDataSubscriberRankingRaw() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureSubscriberCallDropRankingService, requestParameters);

        final ResultTranslator<SubscriberCallSetupFailureRankingResult> rt = getTranslator();
        final List<SubscriberCallSetupFailureRankingResult> rankingResult = rt.translateResult(json,
                SubscriberCallSetupFailureRankingResult.class);
        assertResult(rankingResult);
    }
}
