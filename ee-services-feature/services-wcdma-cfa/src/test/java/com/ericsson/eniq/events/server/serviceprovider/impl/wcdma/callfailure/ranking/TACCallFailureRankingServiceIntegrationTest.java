/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseServiceIntegrationTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.TACCallFailureRankingService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eriwals
 *
 */
public class TACCallFailureRankingServiceIntegrationTest extends BaseServiceIntegrationTest {

    private TACCallFailureRankingService callFailureRankingService;

    @Before
    public void init() {
        callFailureRankingService = new TACCallFailureRankingService();
        attachDependencies(callFailureRankingService);
    }

    @Test
    public void testTACRanking_OneWeek() {
        runQuery(ONE_WEEK);
    }

    @Test
    public void testTACRanking_TwoWeeks() {
        runQuery("60");
    }

    @Test
    public void testTACRanking_30Minutes() {
        runQuery(THIRTY_MINUTES);
    }

    @Test
    public void testTACRanking_FiveMinutes() {
        runQuery(FIVE_MINUTES);
    }

    @Test
    public void testGetDataAsCSV() {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(TWO_WEEKS);
        runQueryForCSV(requestParameters, callFailureRankingService);
    }

    private String runQuery(final String time) {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(time);
        return runQueryAndAssertJsonSucceeds(requestParameters, callFailureRankingService);
    }

    private MultivaluedMap<String, String> getRequestParameters(final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(MAX_ROWS, "10");
        return requestParameters;
    }

}
