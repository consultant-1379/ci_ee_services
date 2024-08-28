/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
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
public class CallFailureDetailedEventAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataRNC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getRNCSpecificParameters(), RNC);

    }

    @Test
    public void testGetDataRNC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getRNCSpecificParameters(), RNC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataTAC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getTACSpecificParameters(), TAC);
    }

    @Test
    public void testGetDataTAC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getTACSpecificParameters(), TAC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAccessArea_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getAccessAreaSpecificParameters(), ACCESS_AREA);

    }

    @Test
    public void testGetDataAccessArea_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getAccessAreaSpecificParameters(), ACCESS_AREA);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath service
     * @param serviceSpecificQueryParameters parameters specific to the service given in the subPath
     * @return The request response
     */
    private String runQuery(final String mimeType, final MultivaluedMap<String, String> serviceSpecificQueryParameters,
            final String subPath) {
        final MultivaluedMap<String, String> standardQueryParameters = new MultivaluedMapImpl();
        standardQueryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        standardQueryParameters.putSingle(TZ_OFFSET, "+0100");
        standardQueryParameters.putSingle(MAX_ROWS, "500");

        standardQueryParameters.putAll(serviceSpecificQueryParameters);

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(standardQueryParameters)
                .path(CALL_FAILURE_DETAILED_EVENT_ANALYSIS).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, CALL_FAILURE_DETAILED_EVENT_ANALYSIS,
                mimeType, response);

    }

    private MultivaluedMap<String, String> getRNCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        queryParameters.putSingle(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        queryParameters.putSingle(RAN_VENDOR_PARAM, ERICSSON);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getTACSpecificParameters() {

        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();

        queryParameters.putSingle(TACID_PARAM, "100100");

        return queryParameters;

    }

    private MultivaluedMap<String, String> getAccessAreaSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CELL_ID_PARAM, "1");
        queryParameters.putSingle(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        queryParameters.putSingle(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        queryParameters.putSingle(RAN_VENDOR_PARAM, ERICSSON);
        return queryParameters;
    }

}
