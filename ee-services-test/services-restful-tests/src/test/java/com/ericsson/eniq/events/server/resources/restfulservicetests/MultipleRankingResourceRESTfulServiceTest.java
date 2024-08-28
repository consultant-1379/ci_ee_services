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
 * test MultipleRankingResource on a remote host
 * @author EEMECOY
 *
 */

public class MultipleRankingResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    private static final String ONE_WEEK = "10080";

    @Test
    public void testGetDataAsJson() {
        final String mimeType = MediaType.APPLICATION_JSON;
        final String result = runQuery(mimeType);
        assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAsCSV() {
        final String result = runQuery(MediaTypeConstants.APPLICATION_CSV);
        assertDataIsNotInJSONFormat(result);
    }

    private String runQuery(final String mimeType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        queryParameters.putSingle(COUNT_PARAM, "30");
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");

        final ClientResponse response = webResource.path(SUBSCRIBER_SERVICES).queryParams(queryParameters)
                .path(RANKING_ANALYSIS).header("requestId", "requestId6654654").accept(mimeType)
                .get(ClientResponse.class);
        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, RANKING_ANALYSIS, mimeType, response);

    }

}
