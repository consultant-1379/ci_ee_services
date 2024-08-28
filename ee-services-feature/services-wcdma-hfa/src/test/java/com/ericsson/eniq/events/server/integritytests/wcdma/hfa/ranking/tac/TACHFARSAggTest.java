/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.tac;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.TerminalHandoverFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureTACRankingResult;
import com.ericsson.eniq.events.server.test.sql.SQLExecutor;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 * 
 */
public class TACHFARSAggTest extends
        BaseDataIntegrityTest<HandoverFailureTACRankingResult> {

    private static final int TAC_1 = 100100;

    private static final int TAC_2 = 100200;

    private static final String SIEMENS = "Siemens";

    private static final String MITSUBISHI = "Mitsubishi";

    private TerminalHandoverFailureRankingService terminalHandoverFailureRankingService;

    @Before
    public void onSetUp() {
        terminalHandoverFailureRankingService = new TerminalHandoverFailureRankingService();
        attachDependencies(terminalHandoverFailureRankingService);
    }

    @Test
    public void testGetRankingData_TAC_HFA_15MINS() throws Exception {

        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());
        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());
        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());
        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, "grid");
        map.putSingle(TIME_QUERY_PARAM, "10080");
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");

        final String json = runQuery(terminalHandoverFailureRankingService, map);

        System.out.println(json);

        final ResultTranslator<HandoverFailureTACRankingResult> rt = getTranslator();
        final List<HandoverFailureTACRankingResult> rankingResult = rt.translateResult(json,
                HandoverFailureTACRankingResult.class);

        assertThat(rankingResult.size(), is(2));

        final HandoverFailureTACRankingResult worstTac = rankingResult.get(0);
        assertThat(worstTac.getRank(), is(1));
        assertThat(worstTac.getManufacturer(), is(SIEMENS));
        assertThat(worstTac.getModel(), is("A53"));
        assertThat(worstTac.getTac(), is(100200));
        assertThat(worstTac.getFailures(), is(224));

        final HandoverFailureTACRankingResult nextWorstTac = rankingResult.get(1);
        assertThat(nextWorstTac.getRank(), is(2));
        assertThat(nextWorstTac.getManufacturer(), is(MITSUBISHI));
        assertThat(nextWorstTac.getModel(), is("G410"));
        assertThat(nextWorstTac.getTac(), is(100100));
        assertThat(nextWorstTac.getFailures(), is(92));
    }

    @Test
    public void testGetRankingData_TAC_HFA_DAY() throws Exception {

        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());
        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());
        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());
        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, "grid");
        map.putSingle(TIME_QUERY_PARAM, "10085");
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");

        final String json = runQuery(terminalHandoverFailureRankingService, map);

        System.out.println(json);

        final ResultTranslator<HandoverFailureTACRankingResult> rt = getTranslator();
        final List<HandoverFailureTACRankingResult> rankingResult = rt.translateResult(json,
                HandoverFailureTACRankingResult.class);

        assertThat(rankingResult.size(), is(2));

        final HandoverFailureTACRankingResult worstTac = rankingResult.get(0);
        assertThat(worstTac.getRank(), is(1));
        assertThat(worstTac.getManufacturer(), is(SIEMENS));
        assertThat(worstTac.getModel(), is("A53"));
        assertThat(worstTac.getTac(), is(100200));
        assertThat(worstTac.getFailures(), is(224));

        final HandoverFailureTACRankingResult nextWorstTac = rankingResult.get(1);
        assertThat(nextWorstTac.getRank(), is(2));
        assertThat(nextWorstTac.getManufacturer(), is(MITSUBISHI));
        assertThat(nextWorstTac.getModel(), is("G410"));
        assertThat(nextWorstTac.getTac(), is(100100));
        assertThat(nextWorstTac.getFailures(), is(92));
    }

    private void createAndPopulateAggTable(final String tempTableName, final String dateTime) throws SQLException {
        SQLExecutor sqlExecutor = null;
        try {
            sqlExecutor = new SQLExecutor(connection);
            sqlExecutor.executeUpdate("create local temporary table " + tempTableName
                    + "(TAC unsigned int, NO_OF_ERRORS unsigned bigint, DATETIME_ID timestamp)");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TAC_1 + "," + "23" + ",'"
                    + dateTime + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TAC_2 + "," + "56" + ",'"
                    + dateTime + "')");
        } finally {
            if (sqlExecutor != null) {
                sqlExecutor.close();
            }
        }
    }

}
