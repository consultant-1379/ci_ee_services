/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.dataconnection;

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
public class RankingAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataSubscriberByDataVolume_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, "SUBSCRIBER_DATAVOL");
    }

    @Test
    public void testGetDataSubscriberByDataVolume_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, "SUBSCRIBER_DATAVOL");
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataControllerByDataVolume_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, "CONTROLLER_DATAVOL");
    }

    @Test
    public void testGetDataControllerByDataVolume_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, "CONTROLLER_DATAVOL");
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAccessAreaByDataVolume_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, "ACCESS_AREA_DATAVOL");

    }

    @Test
    public void testGetDataAccessAreaByDataVolume_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, "ACCESS_AREA_DATAVOL");
        jsonAssertUtils.assertJSONSucceeds(result);

    }

    @Test
    public void testGetFailureRankingByControllerData_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, "CONTROLLER_FAILURE");
    }

    @Test
    public void getFailureRankingByControllerData_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, "CONTROLLER_FAILURE");
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetFailureRankingByImsi_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, "IMSI_FAILURE");
    }

    @Test
    public void getFailureRankingByImsi_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, "IMSI_FAILURE");
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType
     *          The expected mime type of the returned data
     * @param subPath
     * @return The request response
     */
    private String runQuery(final String mimeType, final String subPath) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters).path(GSM)
                .path("DATA_CONNECTION_RANKING_ANALYSIS").path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, GSM + "/" + "DATA_CONNECTION_RANKING_ANALYSIS"
                + "/" + subPath, mimeType, response);

    }

}
