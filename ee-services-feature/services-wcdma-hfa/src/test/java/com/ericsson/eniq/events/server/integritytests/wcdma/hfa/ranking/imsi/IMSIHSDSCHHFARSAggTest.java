/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.imsi;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.SubscriberHSDSCHFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureIMSIRankingResult;
import com.ericsson.eniq.events.server.test.sql.SQLExecutor;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.List;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_HSDSCH_IMSI_ERR_15MIN;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_HSDSCH_IMSI_ERR_DAY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class IMSIHSDSCHHFARSAggTest extends
        BaseDataIntegrityTest<HandoverFailureIMSIRankingResult> {

    private static final int TEST_IMSI_0 = 0;

    private static final int TEST_IMSI_1 = 11111119;

    private static final int TEST_IMSI_2 = 22222229;

    private static final int TEST_IMSI_3 = 33333339;

    private SubscriberHSDSCHFailureRankingService subscriberHSDSCHFailureRankingService;

    @Before
    public void onSetUp() {
        subscriberHSDSCHFailureRankingService = new SubscriberHSDSCHFailureRankingService();
        attachDependencies(subscriberHSDSCHFailureRankingService);
    }

    @Test
    public void testGetRankingData_IMSI_HFA_15MINS() throws Exception {

        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_IMSI_ERR_15MIN,
                DateTimeUtilities.getDateTimeMinus48Hours());

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, "grid");
        map.putSingle(TIME_QUERY_PARAM, "10045");
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");
        map.putSingle(CATEGORY_ID_PARAM, WCDMA_HFA_HSDSCH_CATEGORY_ID);

        final String json = runQuery(subscriberHSDSCHFailureRankingService, map);

        System.out.println(json);

        final ResultTranslator<HandoverFailureIMSIRankingResult> rt = getTranslator();
        final List<HandoverFailureIMSIRankingResult> rankingResult = rt.translateResult(json,
                HandoverFailureIMSIRankingResult.class);

        assertThat(rankingResult.size(), is(3));

        final HandoverFailureIMSIRankingResult worstImsi = rankingResult.get(0);
        assertThat(worstImsi.getRank(), is(1));
        assertThat(worstImsi.getImsi(), is(TEST_IMSI_3));
        assertThat(worstImsi.getFailures(), is(60));

        final HandoverFailureIMSIRankingResult nextWorstImsi = rankingResult.get(1);
        assertThat(nextWorstImsi.getRank(), is(2));
        assertThat(nextWorstImsi.getImsi(), is(TEST_IMSI_2));
        assertThat(nextWorstImsi.getFailures(), is(56));
    }

    @Test
    public void testGetRankingData_IMSI_HFA_DAY() throws Exception {

        createAndPopulateAggTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_IMSI_ERR_DAY, DateTimeUtilities.getDateTimeMinus48Hours());

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, "grid");
        map.putSingle(TIME_QUERY_PARAM, "10085");
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");
        map.putSingle(CATEGORY_ID_PARAM, WCDMA_HFA_HSDSCH_CATEGORY_ID);

        final String json = runQuery(subscriberHSDSCHFailureRankingService, map);

        System.out.println(json);

        final ResultTranslator<HandoverFailureIMSIRankingResult> rt = getTranslator();
        final List<HandoverFailureIMSIRankingResult> rankingResult = rt.translateResult(json,
                HandoverFailureIMSIRankingResult.class);

        assertThat(rankingResult.size(), is(3));

        final HandoverFailureIMSIRankingResult worstImsi = rankingResult.get(0);
        assertThat(worstImsi.getRank(), is(1));
        assertThat(worstImsi.getImsi(), is(TEST_IMSI_3));
        assertThat(worstImsi.getFailures(), is(60));

        final HandoverFailureIMSIRankingResult nextWorstImsi = rankingResult.get(1);
        assertThat(nextWorstImsi.getRank(), is(2));
        assertThat(nextWorstImsi.getImsi(), is(TEST_IMSI_2));
        assertThat(nextWorstImsi.getFailures(), is(56));
    }

    private void createAndPopulateAggTable(final String tempTableName, final String dateTime) throws SQLException {
        SQLExecutor sqlExecutor = null;
        try {
            sqlExecutor = new SQLExecutor(connection);
            sqlExecutor
                    .executeUpdate("create local temporary table "
                            + tempTableName
                            + "(IMSI unsigned int, CATEGORY_ID unsigned int, NO_OF_ERRORS unsigned bigint, DATETIME_ID timestamp)");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_0 + "," + "1" + ","
                    + "230" + ",'" + dateTime + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_1 + "," + "1" + ","
                    + "23" + ",'" + dateTime + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_2 + "," + "1" + ","
                    + "56" + ",'" + dateTime + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_3 + "," + "1" + ","
                    + "60" + ",'" + dateTime + "')");
        } finally {
            if (sqlExecutor != null) {
                sqlExecutor.close();
            }
        }
    }
}
