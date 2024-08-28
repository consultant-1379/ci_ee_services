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
 * test causeCodeAnalysis on remote host
 * @author EEMECOY
 *
 */
public class CauseCodeAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    protected static final String BSC_NAME = "BSC193,ERICSSON,1";

    @Test
    public void testGetDataAsJsonBSC() {
        final String result = testGetDataForBSC(MediaType.APPLICATION_JSON);
        assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAsCSVBSC() {
        testGetDataForBSC(MediaTypeConstants.APPLICATION_CSV);
    }

    private String testGetDataForBSC(final String mediaType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        queryParameters.putSingle(NODE_PARAM, BSC_NAME);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TIME_QUERY_PARAM, "10080");
        queryParameters.putSingle(TZ_OFFSET, "+0100");

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters)
                .path(CAUSE_CODE_ANALYSIS).header("requestId", generateRandomRequestID()).accept(mediaType)
                .get(ClientResponse.class);
        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, CAUSE_CODE_ANALYSIS, mediaType, response);
    }

}
