/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.imsi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.SubscriberIRATFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureIMSIRankingResult;
import com.ericsson.eniq.events.server.test.sql.SQLExecutor;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class IMSIIRATHFARSRawTest extends BaseDataIntegrityTest<HandoverFailureIMSIRankingResult> {
    private static final int TEST_IMSI_0 = 0;

    private static final int TEST_IMSI_1 = 11111119;

    private static final int TEST_IMSI_2 = 22222229;

    private SubscriberIRATFailureRankingService subscriberIRATFailureRankingService;

    @Before
    public void onSetUp() {
        subscriberIRATFailureRankingService = new SubscriberIRATFailureRankingService();
        attachDependencies(subscriberIRATFailureRankingService);
    }

    @Test
    public void testGetRankingData_IMSI_HFA_RAW() throws Exception {

        createAndPopulateRawTable("#EVENT_E_RAN_HFA_IRAT_ERR_RAW");

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, "grid");
        map.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");
        map.putSingle(CATEGORY_ID_PARAM, WCDMA_HFA_IRAT_CATEGORY_ID);

        final String json = runQuery(subscriberIRATFailureRankingService, map);

        System.out.println(json);

        final ResultTranslator<HandoverFailureIMSIRankingResult> rt = getTranslator();
        final List<HandoverFailureIMSIRankingResult> rankingResult = rt.translateResult(json,
                HandoverFailureIMSIRankingResult.class);

        assertThat(rankingResult.size(), is(2));

        final HandoverFailureIMSIRankingResult worstImsi = rankingResult.get(0);
        assertThat(worstImsi.getRank(), is(1));
        assertThat(worstImsi.getImsi(), is(TEST_IMSI_2));
        assertThat(worstImsi.getFailures(), is(2));

        final HandoverFailureIMSIRankingResult nextWorstImsi = rankingResult.get(1);
        assertThat(nextWorstImsi.getRank(), is(2));
        assertThat(nextWorstImsi.getImsi(), is(TEST_IMSI_1));
        assertThat(nextWorstImsi.getFailures(), is(1));
    }

    private void createAndPopulateRawTable(final String tempTableName) throws SQLException {
        SQLExecutor sqlExecutor = null;
        try {
            sqlExecutor = new SQLExecutor(connection);
            sqlExecutor.executeUpdate("create local temporary table " + tempTableName
                    + "(IMSI unsigned int, CATEGORY_ID unsigned int, TAC unsigned int,DATETIME_ID timestamp)");
            final String dateTimeNowMinus27Mins = DateTimeUtilities
                    .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_0 + "," + "3" + ","
                    + "123456" + ",'" + dateTimeNowMinus27Mins + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_1 + "," + "3" + ","
                    + "123456" + ",'" + dateTimeNowMinus27Mins + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_2 + "," + "3" + ","
                    + "123456" + ",'" + dateTimeNowMinus27Mins + "')");
            sqlExecutor.executeUpdate("insert into " + tempTableName + " values(" + TEST_IMSI_2 + "," + "3" + ","
                    + "123456" + ",'" + dateTimeNowMinus27Mins + "')");

        } finally {
            if (sqlExecutor != null) {
                sqlExecutor.close();
            }
        }
    }
}
