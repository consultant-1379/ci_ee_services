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
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 * @since 2011
 *
 */
public class SubscriberCFA_EAbyImsiGroupServiceIntegrationTest extends BaseServiceIntegrationTest {
    private SubscriberEventAnalysisService service;

    @Before
    public void setup() {
        service = new SubscriberEventAnalysisService();
        attachDependencies(service);
    }

    @Test
    public void testFiveMinQuery() {
        runQuery(FIVE_MINUTES);
    }

    @Test
    public void testOneDayQuery() {
        runQuery(ONE_DAY);
    }

    @Test
    public void testOneWeekQuery() {
        runQuery(ONE_WEEK);
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
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(TYPE_PARAM, IMSI);
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(GROUP_NAME_PARAM, "TEST_GROUP");
        return requestParameters;
    }
}
