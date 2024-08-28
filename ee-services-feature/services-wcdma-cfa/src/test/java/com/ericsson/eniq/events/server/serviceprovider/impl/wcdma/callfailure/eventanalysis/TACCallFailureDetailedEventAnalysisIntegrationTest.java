/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseServiceIntegrationTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.TACCallFailureDetailedEventAnalysisService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eriwals
 *
 */
public class TACCallFailureDetailedEventAnalysisIntegrationTest extends BaseServiceIntegrationTest {

    private TACCallFailureDetailedEventAnalysisService tacCallFailureDetailedEventAnalysisService;

    @Before
    public void setup() {
        tacCallFailureDetailedEventAnalysisService = new TACCallFailureDetailedEventAnalysisService();
        attachDependencies(tacCallFailureDetailedEventAnalysisService);
    }

    @Test
    public void testCSVQuery() {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(TWO_WEEKS);
        runQueryForCSV(requestParameters, tacCallFailureDetailedEventAnalysisService);
    }

    @Test
    public void testTACEventAnalysis_ThirtyMinutes_NoEventID() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        runThirtyMinuteQuery(requestParameters);
    }

    @Test
    public void testTACEventAnalysis_ThirtyMinutes_CallDropsEventID() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        runThirtyMinuteQuery(requestParameters);
    }

    private String runThirtyMinuteQuery(final MultivaluedMap<String, String> requestParameters) {
        requestParameters.putAll(getRequestParameters("1010101011"));
        return runQueryAndAssertJsonSucceeds(requestParameters, tacCallFailureDetailedEventAnalysisService);
    }

    private MultivaluedMap<String, String> getRequestParameters(final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(TACID_PARAM, "33000353");
        return requestParameters;
    }

}
