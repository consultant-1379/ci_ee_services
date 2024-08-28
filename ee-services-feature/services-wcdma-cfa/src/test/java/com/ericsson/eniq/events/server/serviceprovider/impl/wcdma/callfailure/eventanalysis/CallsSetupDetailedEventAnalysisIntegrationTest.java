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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.CallDropsDetailedEventAnalysisService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CallsSetupDetailedEventAnalysisIntegrationTest extends BaseServiceIntegrationTest {

    private CallDropsDetailedEventAnalysisService service;

    @Before
    public void setup() {
        service = new CallDropsDetailedEventAnalysisService();
        attachDependencies(service);
    }

    @Test
    public void testCallsDroppedFailureEventAnalysis_FiveMin() {
        runQuery(FIVE_MINUTES);
    }

    @Test
    public void testGetDataAsCSV() {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(FIVE_MINUTES);
        runQueryForCSV(requestParameters, service);
    }

    private String runQuery(final String time) {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(time);
        return runQueryAndAssertJsonSucceeds(requestParameters, service);
    }

    private MultivaluedMap<String, String> getRequestParameters(final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(CAUSE_VALUE, "1");
        return requestParameters;
    }

}
