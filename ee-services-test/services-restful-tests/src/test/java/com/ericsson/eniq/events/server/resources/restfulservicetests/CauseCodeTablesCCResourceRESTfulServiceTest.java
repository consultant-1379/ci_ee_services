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

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * test CauseCodeTablesCCResource on remote host
 * @author EEMECOY
 *
 */
public class CauseCodeTablesCCResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataAsJson() {
        final String mimeType = MediaType.APPLICATION_JSON;
        final String result = runQuery(mimeType);
        assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAsCSV() {
        final String mediaType = "application/csv";
        final String result = runQuery(mediaType);
        assertDataIsNotInJSONFormat(result);
    }

    private String runQuery(final String mediaType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();

        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);

        queryParameters.putSingle(TIME_QUERY_PARAM, "10080");

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters)
                .path(CAUSE_CODE_ANALYSIS).path(CAUSE_CODE_TABLE_CC).header("requestId", generateRandomRequestID())
                .accept(mediaType).get(ClientResponse.class);
        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, CAUSE_CODE_TABLE_CC, mediaType, response);
    }

}
