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
public class CallFailureEventSummaryRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataRNC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, RNC, getRncParams());
    }

    @Test
    public void testGetDataRNC_JSON() {
        final String jsonResult = runQuery(MediaType.APPLICATION_JSON, RNC, getRncParams());
        jsonAssertUtils.assertJSONSucceeds(jsonResult);
    }

    @Test
    public void testGetDataAccessArea_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, ACCESS_AREA, getAccessAreaParams());
    }

    @Test
    public void testGetDataAccessArea_JSON() {
        final String jsonResult = runQuery(MediaType.APPLICATION_JSON, ACCESS_AREA, getAccessAreaParams());
        jsonAssertUtils.assertJSONSucceeds(jsonResult);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath 
     * @return The request response
     */
    private String runQuery(final String mimeType, final String subPath,
            final MultivaluedMap<String, String> serviceParams) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        queryParameters.putAll(serviceParams);

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters)
                .path(CALL_FAILURE_EVENT_SUMMARY).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, CALL_FAILURE_EVENT_SUMMARY, mimeType, response);
    }

    private MultivaluedMap<String, String> getRncParams() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        queryParameters.putSingle(RAN_VENDOR_PARAM, ERICSSON);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getAccessAreaParams() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(RNC_FDN_PARAM, "fdn");
        queryParameters.putSingle(CELL_ID_PARAM, "1");
        queryParameters.putSingle(RAN_VENDOR_PARAM, ERICSSON);
        return queryParameters;
    }

}
