/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.imsi;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFASubscriberReoccuringFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureSubscriberReoccuringRankingResults;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFASubscriberReoccurringRankingServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureSubscriberReoccuringRankingResults> {
    private LTECFASubscriberReoccuringFailureRankingService lteCallFailureIMSIReoccuringFailureRankingService;

    private static final String INTERNAL_PROC_UE_CTXT_RELEASE_DESC = "INTERNAL_PROC_UE_CTXT_RELEASE";

    private static final String INTERNAL_PROC_S1_SIG_CONN_SETUP_DESC = "INTERNAL_PROC_S1_SIG_CONN_SETUP";

    private static final String INTERNAL_PROC_RRC_CONN_SETUP_DESC = "INTERNAL_PROC_RRC_CONN_SETUP";

    private static final String INTERNAL_PROC_INITIAL_CTXT_SETUP_DESC = "INTERNAL_PROC_INITIAL_CTXT_SETUP";

    private static final String TEST_VALUE_HIER321_ID_1 = "7210756712490856541";

    private static final String TEST_VALUE_ECELL_NAME_1 = "eCell_1";

    private static final String TEST_VALUE_ENODEB_NAME_1 = "eNodeB_1";

    private static final String TEST_VALUE_HIER321_ID_2 = "7210756712490856542";

    private static final String TEST_VALUE_ECELL_NAME_2 = "eCell_2";

    private static final String TEST_VALUE_ENODEB_NAME_2 = "eNodeB_2";

    private static final String TEST_VALUE_HIER321_ID_3 = "7210756712490856543";

    private static final String TEST_VALUE_ECELL_NAME_3 = "eCell_3";

    private static final String TEST_VALUE_ENODEB_NAME_3 = "eNodeB_3";

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
        lteCallFailureIMSIReoccuringFailureRankingService = new LTECFASubscriberReoccuringFailureRankingService();
        attachDependencies(lteCallFailureIMSIReoccuringFailureRankingService);
        createTables();
        insertData();
    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertData() throws SQLException {
        insertTopoData();
        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();
        //No. 1
        insertEvent(TEST_IMSI_1, TEST_VALUE_HIER321_ID_1, INTERNAL_PROC_UE_CTXT_RELEASE, 4, dateTime);
        //No. 2
        insertEvent(TEST_IMSI_1, TEST_VALUE_HIER321_ID_1, INTERNAL_PROC_S1_SIG_CONN_SETUP, 3, dateTime);
        //No. 3
        insertEvent(TEST_IMSI_2, TEST_VALUE_HIER321_ID_2, INTERNAL_PROC_RRC_CONN_SETUP, 2, dateTime);
        //No. 4
        insertEvent(TEST_IMSI_3, TEST_VALUE_HIER321_ID_3, INTERNAL_PROC_INITIAL_CTXT_SETUP, 1, dateTime);
    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertEvent(final int imsi, final String hier321ID, final String eventID, final int instances,
            final String dt) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        for (int i = 0; i < instances; i++) {
            valuesForTable.put(IMSI, imsi);
            valuesForTable.put(HIER321_ID, hier321ID);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, SAMPLE_TAC);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
            valuesForTable.clear();
        }
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(EVENT_ID, INTERNAL_PROC_UE_CTXT_RELEASE);
        valuesForTable.put(EVENT_ID_DESC, INTERNAL_PROC_UE_CTXT_RELEASE_DESC);
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, INTERNAL_PROC_RRC_CONN_SETUP);
        valuesForTable.put(EVENT_ID_DESC, INTERNAL_PROC_RRC_CONN_SETUP_DESC);
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, INTERNAL_PROC_S1_SIG_CONN_SETUP);
        valuesForTable.put(EVENT_ID_DESC, INTERNAL_PROC_S1_SIG_CONN_SETUP_DESC);
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, INTERNAL_PROC_INITIAL_CTXT_SETUP);
        valuesForTable.put(EVENT_ID_DESC, INTERNAL_PROC_INITIAL_CTXT_SETUP_DESC);
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_ENODEB_NAME_1);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_ECELL_NAME_1);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_2);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_ENODEB_NAME_2);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_ECELL_NAME_2);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_3);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_ENODEB_NAME_3);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_ECELL_NAME_3);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
    }

    /**
     * @throws Exception 
     * 
     */
    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(IMSI);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(HIER321_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(HIER321_ID);
        columnsForEETable.add(HIERARCHY_3);
        columnsForEETable.add(HIERARCHY_1);
        columnsForEETable.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForEETable);
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
    public void testIMSIReoccuringFailureGetRankingData() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureIMSIReoccuringFailureRankingService, requestParameters);
        final ResultTranslator<LTECallFailureSubscriberReoccuringRankingResults> rt = getTranslator();
        final List<LTECallFailureSubscriberReoccuringRankingResults> rankingResult = rt.translateResult(json,
                LTECallFailureSubscriberReoccuringRankingResults.class);
        assertResult(rankingResult);
    }

    /**
     * @param rankingResult
     */
    private void assertResult(final List<LTECallFailureSubscriberReoccuringRankingResults> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(4));

        final LTECallFailureSubscriberReoccuringRankingResults top1 = rankingResult.get(0);
        assertThat(top1.getImsi(), is(String.valueOf(TEST_IMSI_1)));
        assertThat(top1.getRank(), is(1));
        assertThat(top1.getEventIdDesc(), is(INTERNAL_PROC_UE_CTXT_RELEASE_DESC));
        assertThat(top1.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_1));
        assertThat(top1.getFailures(), is(4));

        final LTECallFailureSubscriberReoccuringRankingResults top2 = rankingResult.get(1);
        assertThat(top2.getImsi(), is(String.valueOf(TEST_IMSI_1)));
        assertThat(top2.getRank(), is(2));
        assertThat(top2.getEventIdDesc(), is(INTERNAL_PROC_S1_SIG_CONN_SETUP_DESC));
        assertThat(top2.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_1));
        assertThat(top2.getFailures(), is(3));

        final LTECallFailureSubscriberReoccuringRankingResults top3 = rankingResult.get(2);
        assertThat(top3.getImsi(), is(String.valueOf(TEST_IMSI_2)));
        assertThat(top3.getRank(), is(3));
        assertThat(top3.getEventIdDesc(), is(INTERNAL_PROC_RRC_CONN_SETUP_DESC));
        assertThat(top3.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_2));
        assertThat(top3.getFailures(), is(2));

        final LTECallFailureSubscriberReoccuringRankingResults top4 = rankingResult.get(3);
        assertThat(top4.getImsi(), is(String.valueOf(TEST_IMSI_3)));
        assertThat(top4.getRank(), is(4));
        assertThat(top4.getEventIdDesc(), is(INTERNAL_PROC_INITIAL_CTXT_SETUP_DESC));
        assertThat(top4.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_3));
        assertThat(top4.getFailures(), is(1));

    }
}
