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
 * @author eatiaro
 * 2012
 *
 */
public class GSMRoamingAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetOperatorRoamingData_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, OPERATOR);
    }

    @Test
    public void testGetOperatorRoamingData_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, OPERATOR);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetCountryRoamingData_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, COUNTRY);
    }

    @Test
    public void testGetCountryRoamingData_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, COUNTRY);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath service
     * @param serviceSpecificQueryParameters parameters specific to the service given in the subPath
     * @return The request response
     */
    private String runQuery(final String mimeType, final String subPath) {
        final MultivaluedMap<String, String> standardQueryParameters = new MultivaluedMapImpl();
        standardQueryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        standardQueryParameters.putSingle(TZ_OFFSET, "+0100");
        standardQueryParameters.putSingle(MAX_ROWS, "500");
        standardQueryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(standardQueryParameters)
                .path(GSM).path(CALL_FAILURE_ROAMING_ANALYSIS).path(subPath)
                .header(REQUEST_ID, generateRandomRequestID()).accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, GSM + "/" + CALL_FAILURE_ROAMING_ANALYSIS
                + "/" + subPath, mimeType, response);
    }
}
