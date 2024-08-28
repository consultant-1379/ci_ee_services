/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejoegaf
 *
 */
public class GSMCallFailureRankingAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataAccessArea_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, ACCESS_AREA);
    }

    @Test
    public void testGetDataAccessArea_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, ACCESS_AREA);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataTerminal_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, TAC);
    }

    @Test
    public void testGetDataTerminal_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, TAC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataCauseCodeCallDrop_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI);
    }

    @Test
    public void testGetDataCauseCodeCallDrop_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataSubscriberCallDrop_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, SUBSCRIBER_FOR_CALL_DROP);
    }

    @Test
    public void testGetDataSubscriberCallDrop_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, SUBSCRIBER_FOR_CALL_DROP);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataController_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, BSC);
    }

    @Test
    public void testGetDataController_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, BSC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath 
     * @return The request response
     */
    private String runQuery(final String mimeType, final String subPath) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters).path(GSM)
                .path(CALL_FAILURE_RANKING_ANALYSIS).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, GSM + "/" + CALL_FAILURE_RANKING_ANALYSIS
                + "/" + subPath, mimeType, response);

    }

}
