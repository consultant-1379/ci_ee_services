/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.ranking;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.ranking.LTEHandoverFailureSubscriberExecRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHandoverFailureSubscriberExecRankingSetupResult;
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
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ejamves
 * @since 2011
 *
 */
public class LTEHFASubscriberExecRankingServiceRawTest extends
        BaseDataIntegrityTest<LTEHandoverFailureSubscriberExecRankingSetupResult> {
    private LTEHandoverFailureSubscriberExecRankingService lteHandoverFailureSubscriberPrepRankingAnalysisService;

    private static final String DATETIME_ID_RAW = "2011-10-26 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "27102011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String TEST_VALUE_IMSI_0 = "0";

    private static final String TEST_VALUE_IMSI_1 = "123456789012345";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteHandoverFailureSubscriberPrepRankingAnalysisService = new LTEHandoverFailureSubscriberExecRankingService();
        attachDependencies(lteHandoverFailureSubscriberPrepRankingAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();

        columnsForTable.add(EVENT_ID);
        columnsForTable.add(IMSI);
        columnsForTable.add(TAC);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(CATEGORY_ID_SQL_PARAM);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForTable);

    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

    }

    private void insertData(final String imsi, final String eventID, final String time, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(IMSI_PARAM, imsi);
            valuesForTable.put(TAC, SAMPLE_TAC);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(CATEGORY_ID_SQL_PARAM, LTE_HFA_EXEC_CATEGORY_ID);
            insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResult(final String categoryId) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(CATEGORY_ID_SQL_PARAM, categoryId);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteHandoverFailureSubscriberPrepRankingAnalysisService, requestParameters);
    }

    @Test
    public void testSubscriberExecRanking_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_0, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 1);

        final String json = getJsonResult(LTE_HFA_EXEC_CATEGORY_ID);

        final ResultTranslator<LTEHandoverFailureSubscriberExecRankingSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberExecRankingSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberExecRankingSetupResult.class);
        assertResult(rankingResult);
    }

    private void assertResult(final List<LTEHandoverFailureSubscriberExecRankingSetupResult> results) {
        assertThat(results.size(), is(1));
    }
}