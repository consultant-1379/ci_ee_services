/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.ranking.tac;

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

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.ranking.LTEHFATerminalExecRankingService;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.ranking.LTEHFATerminalPrepRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHFATerminalRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejamves
 * @since 2012
 *
 */
public class LTEHFATerminalTacRankingServiceRawTest extends BaseDataIntegrityTest<LTEHFATerminalRankingResult> {

    private LTEHFATerminalExecRankingService lteHFATerminalExecRankingService;

    private LTEHFATerminalPrepRankingService lteHFATerminalPrepRankingService;

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
        lteHFATerminalExecRankingService = new LTEHFATerminalExecRankingService();
        attachDependencies(lteHFATerminalExecRankingService);
        lteHFATerminalPrepRankingService = new LTEHFATerminalPrepRankingService();
        attachDependencies(lteHFATerminalPrepRankingService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTables();
        insertData();
    }

    /**
     * This function is used to assert if the actual test result is the same with the expect test result.
     * @param rankingResult
     */
    private void assertResult(final List<LTEHFATerminalRankingResult> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final LTEHFATerminalRankingResult worstSubscriber = rankingResult.get(0);
        assertThat(worstSubscriber.getRank(), is(1));
        assertThat(worstSubscriber.getTac(), is(String.valueOf(TEST_TAC_1)));
        assertThat("worstSubscriber should have exactly 3 Drop Calls events!", worstSubscriber.getFailures(), is(3));

        final LTEHFATerminalRankingResult nextWorstSubscriber = rankingResult.get(1);
        assertThat(nextWorstSubscriber.getRank(), is(2));
        assertThat(nextWorstSubscriber.getTac(), is(String.valueOf(TEST_TAC_2)));
        assertThat("nextWorstSubscriber should have exactly 2 Drop Calls events!", nextWorstSubscriber.getFailures(),
                is(2));

        final LTEHFATerminalRankingResult lastSubscriber = rankingResult.get(2);
        assertThat(lastSubscriber.getRank(), is(3));
        assertThat(lastSubscriber.getTac(), is(String.valueOf(TEST_TAC_3)));
        assertThat("nextWorstSubscriber should have exactly 1 Drop Calls event!", lastSubscriber.getFailures(), is(1));
    }

    /**
     * Create the prepare test tables for testing.
     * 
     * raw table: EVENT_E_LTE_HFA_RAW
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(CATEGORY_ID_2);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(VENDOR_NAME);
        columnsForEETable.add(MARKETING_NAME);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForEETable);

    }

    /**
     * This function is used to insert test data to the prepared tables.
     * For each categoryID2, four type of events will be inserted:
     * @throws Exception
     */
    private void insertData() throws SQLException {

        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertTAC(TEST_TAC_1, dateTime, LTE_HFA_EXEC_CATEGORY_ID, 3);
        insertTAC(TEST_TAC_2, dateTime, LTE_HFA_EXEC_CATEGORY_ID, 2);
        insertTAC(TEST_TAC_3, dateTime, LTE_HFA_EXEC_CATEGORY_ID, 1);

        insertTAC(TEST_TAC_1, dateTime, LTE_HFA_PREP_CATEGORY_ID, 3);
        insertTAC(TEST_TAC_2, dateTime, LTE_HFA_PREP_CATEGORY_ID, 2);
        insertTAC(TEST_TAC_3, dateTime, LTE_HFA_PREP_CATEGORY_ID, 1);

        insertLookupData(TEST_TAC_1, MANUFACTURER_FOR_SAMPLE_TAC, MARKETING_NAME_FOR_SAMPLE_TAC);
        insertLookupData(TEST_TAC_2, MANUFACTURER_FOR_SAMPLE_TAC, MARKETING_NAME_FOR_SAMPLE_TAC);
        insertLookupData(TEST_TAC_3, MANUFACTURER_FOR_SAMPLE_TAC, MARKETING_NAME_FOR_SAMPLE_TAC);
    }

    /**
     * This function is used to insert no. of events with TAC to the table.
     * @param dt
     *  The event time.
     * @param categoryID2
     *  The categoryID2 ID.
     * @param instances
     *  The number of events.
     * @throws SQLException
     */
    private void insertTAC(final int tac, final String dt, final String categoryID2, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(CATEGORY_ID_2, categoryID2);
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, tac);
            insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
        }
    }

    private void insertLookupData(final int tac, final String manufacturer, final String marketingName)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(VENDOR_NAME, manufacturer);
        valuesForTable.put(MARKETING_NAME, marketingName);
        valuesForTable.put(TAC, tac);
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);
    }

    private String getJsonTACExec(final String timerange) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, timerange);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(CATEGORY_ID_2, LTE_HFA_EXEC_CATEGORY_ID);

        return runQuery(lteHFATerminalExecRankingService, requestParameters);
    }

    private String getJsonTACPrep(final String timerange) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, timerange);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(CATEGORY_ID_2, LTE_HFA_PREP_CATEGORY_ID);

        return runQuery(lteHFATerminalPrepRankingService, requestParameters);
    }

    /**
     * This test case is used to test the LTE HFA TAC EXEC Failures Ranking.
     * @throws Exception
     */
    @Test
    public void testGetRankingDataTerminalTACExecRankingRaw() throws Exception {
        final String json = getJsonTACExec(FIVE_MINUTES);

        final ResultTranslator<LTEHFATerminalRankingResult> rt = getTranslator();
        final List<LTEHFATerminalRankingResult> rankingResult = rt.translateResult(json,
                LTEHFATerminalRankingResult.class);
        assertResult(rankingResult);
    }

    /**
     * This test case is used to test the LTE HFA TAC PREP Failures Ranking.
     * @throws Exception
     */

    @Test
    public void testGetRankingDataTerminalTACPrepRankingRaw() throws Exception {
        final String json = getJsonTACPrep(FIVE_MINUTES);

        final ResultTranslator<LTEHFATerminalRankingResult> rt = getTranslator();
        final List<LTEHFATerminalRankingResult> rankingResult = rt.translateResult(json,
                LTEHFATerminalRankingResult.class);
        assertResult(rankingResult);
    }
}
