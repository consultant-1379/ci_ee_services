/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseServiceIntegrationTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.RNCCallFailureEventSummaryService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class RNCCallFailureEventSummaryServiceIntegrationTest extends BaseServiceIntegrationTest {

    private RNCCallFailureEventSummaryService rncCallFailureEventAnalysisService;

    @Before
    public void setup() {
        rncCallFailureEventAnalysisService = new RNCCallFailureEventSummaryService();
        attachDependencies(rncCallFailureEventAnalysisService);
    }

    @Test
    public void testCSVQuery() {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(TWO_WEEKS);
        runQueryForCSV(requestParameters, rncCallFailureEventAnalysisService);
    }

    @Test
    public void testRNCEventAnalysis_ThirtyMinutes() {
        runQuery(THIRTY_MINUTES);
    }

    @Test
    public void testRNCEventAnalysis_TwoWeeks() {
        runQuery(TWO_WEEKS);
    }

    @Test
    public void testRNCEventAnalysis_OneWeek() {
        runQuery(ONE_WEEK);
    }

    @Test
    public void testRNCEventAnalysis_FiveMinutes() {
        runQuery(FIVE_MINUTES);
    }

    private String runQuery(final String time) {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(time);
        return runQueryAndAssertJsonSucceeds(requestParameters, rncCallFailureEventAnalysisService);
    }

    private MultivaluedMap<String, String> getRequestParameters(final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        requestParameters.putSingle(RAN_VENDOR_PARAM, ERICSSON);
        return requestParameters;
    }
}
