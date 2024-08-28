/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseServiceIntegrationTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class SubscriberGroupHFAESTest extends BaseServiceIntegrationTest {
    private SubscriberHandoverFailureEventSummaryService service;

    @Before
    public void init() {
        service = new SubscriberHandoverFailureEventSummaryService();
        attachDependencies(service);
    }

    @Test
    public void testHandoverFailureEventSummary_FiveMin() {
        runQuery(FIVE_MINUTES);
    }

    @Test
    public void testHandoverFailureEventSummary_OneWeek() {
        runQuery(ONE_WEEK);
    }

    @Test
    public void testGetDataAsCSV() {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(FIVE_MINUTES);
        runQueryForCSV(requestParameters, service);
    }

    private String runQuery(final String time) {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters(time);
        final String result = runQueryAndAssertJsonSucceeds(requestParameters, service);
        return result;
    }

    private MultivaluedMap<String, String> getRequestParameters(final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, "IMSI-GROUP_1");
        return requestParameters;
    }
}
