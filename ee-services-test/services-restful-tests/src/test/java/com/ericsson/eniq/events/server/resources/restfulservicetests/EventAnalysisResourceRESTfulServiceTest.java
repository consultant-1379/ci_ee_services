/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Test EventAnalysisResource on remote host
 * @author EEMECOY
 *
 */

public class EventAnalysisResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataCSVFormatWithMultipleEventIds() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        queryParameters.putSingle(TAC_PARAM, "35707103");
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(KEY_PARAM, KEY_TYPE_ERR);
        queryParameters.putSingle(TIME_QUERY_PARAM, "10080");
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(EVENT_ID_PARAM, "2,3");
        queryParameters.putSingle(MAX_ROWS, "500");
        runQuery(MediaTypeConstants.APPLICATION_CSV, queryParameters);
        final String result = runQuery(MediaTypeConstants.APPLICATION_CSV, queryParameters);
        System.out.println(result);
    }

    @Test
    public void testGetDataAsCSVWithDataTimeFromAndDataTimeTo() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        queryParameters.putSingle(NODE_PARAM, "BSC193,ERICSSON,1");
        queryParameters.putSingle(KEY_PARAM, KEY_TYPE_SUM);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(DATA_TIME_FROM_QUERY_PARAM, "303030");
        queryParameters.putSingle(DATA_TIME_TO_QUERY_PARAM, "303030");
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        runQuery(MediaTypeConstants.APPLICATION_CSV, queryParameters);
        final String result = runQuery(MediaTypeConstants.APPLICATION_CSV, queryParameters);
        System.out.println(result);
    }

    @Test
    public void testGetDataAsJsonBSC() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        queryParameters.putSingle(NODE_PARAM, "BSC193,ERICSSON,1");
        queryParameters.putSingle(KEY_PARAM, KEY_TYPE_SUM);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TIME_QUERY_PARAM, "10080");
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        final String result = runQuery(MediaType.APPLICATION_JSON, queryParameters);
        assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForIMSIAsJson() {
        final String result = testGetDataForImsi(MediaType.APPLICATION_JSON);
        assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForIMSIAsCSV() {
        testGetDataForImsi(MediaTypeConstants.APPLICATION_CSV);
    }

    private String testGetDataForImsi(final String mimeType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(KEY_PARAM, KEY_TYPE_TOTAL);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(IMSI_PARAM, "460000006051137");
        queryParameters.putSingle(TIME_QUERY_PARAM, "10080");
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        return runQuery(mimeType, queryParameters);
    }

    private String runQuery(final String mimeType, final MultivaluedMap<String, String> queryParameters) {
        return runQuery(mimeType, queryParameters, ClientResponse.Status.OK);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param queryParameters The service request parameters
     * @param expectedStatus The expected html status of the services call
     * @return The request response
     */
    private String runQuery(final String mimeType, final MultivaluedMap<String, String> queryParameters,
            final ClientResponse.Status expectedStatus) {
        final ClientResponse response = webResource.path(SUBSCRIBER_SERVICES).queryParams(queryParameters)
                .path(EVENT_ANALYSIS).header(REQUEST_ID, generateRandomRequestID()).accept(mimeType)
                .get(ClientResponse.class);
        if (expectedStatus.getStatusCode() == expectedStatus.getStatusCode()) {
            if (expectedStatus.getStatusCode() == ClientResponse.Status.OK.getStatusCode()) {
                return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, EVENT_ANALYSIS, mimeType, response);
            }
            //its an error status code thats expected and has been received
            return response.getEntity(String.class);
        }
        return assertResponseNotOk(response);

    }

}
