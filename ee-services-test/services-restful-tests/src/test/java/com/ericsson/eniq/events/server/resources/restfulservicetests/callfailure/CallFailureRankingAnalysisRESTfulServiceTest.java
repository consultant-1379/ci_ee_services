/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

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
 * @author eemecoy
 *
 */
public class CallFailureRankingAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataRNC_CSV() {
        runQueryForAccessAreaAndRnc(MediaTypeConstants.APPLICATION_CSV, RNC);
    }

    @Test
    public void testGetDataRNC_JSON() {
        final String result = runQueryForAccessAreaAndRnc(MediaType.APPLICATION_JSON, RNC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataTAC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, TAC);
    }

    @Test
    public void testGetDataTAC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, TAC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataCauseCodeCallSetup_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, CALL_FAILURE_RANKING_BY_CALL_SETUP_URI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataCauseCodeCallSetup_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, CALL_FAILURE_RANKING_BY_CALL_SETUP_URI);
    }

    @Test
    public void testGetDataCauseCodeCallDrops_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI);
    }

    @Test
    public void testGetDataCauseCodeCallDrops_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAccessArea_CSV() {
        runQueryForAccessAreaAndRnc(MediaTypeConstants.APPLICATION_CSV, ACCESS_AREA);
    }

    @Test
    public void testGetDataAccessArea_JSON() {
        final String result = runQueryForAccessAreaAndRnc(MediaType.APPLICATION_JSON, ACCESS_AREA);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataSubscriberCallSetupFailure_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, SUBSCRIBER_FOR_CALL_SETUP_FAILURE);
    }

    @Test
    public void testGetDataSubscriberCallSetupFailure_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, SUBSCRIBER_FOR_CALL_SETUP_FAILURE);
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
        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters)
                .path(CALL_FAILURE_RANKING_ANALYSIS).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, CALL_FAILURE_RANKING_ANALYSIS, mimeType,
                response);

    }

    private String runQueryForAccessAreaAndRnc(final String mimeType, final String subPath) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        queryParameters.putSingle(CATEGORY_ID_PARAM, "3");
        queryParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "01423");
        queryParameters.putSingle(EVENT_ID_PARAM, "438");
        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters)
                .path(CALL_FAILURE_RANKING_ANALYSIS).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, CALL_FAILURE_RANKING_ANALYSIS, mimeType,
                response);

    }

}
