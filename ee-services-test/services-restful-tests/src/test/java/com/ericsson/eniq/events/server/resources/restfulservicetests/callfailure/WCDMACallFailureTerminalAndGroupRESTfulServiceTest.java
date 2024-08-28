/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

/**
 * @author eeikonl
 * @since 2011
 *
 */

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class WCDMACallFailureTerminalAndGroupRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testgetMostMobilityIssuesDataAsCSV() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(GROUP_NAME_PARAM, "sampleTac");
        testTerminalAnalysisForSubPath("SUMMARY", MediaTypeConstants.APPLICATION_CSV, queryParameters);
    }

    @Test
    public void testgetMostMobilityIssuesDataAsJson() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(GROUP_NAME_PARAM, "sampleTac");
        testTerminalAnalysisForSubPath("SUMMARY", MediaType.APPLICATION_JSON, queryParameters);
    }

    private void testTerminalAnalysisForSubPath(final String subPath, final String mediaType,
            final MultivaluedMap<String, String> extraQueryParameters) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TIME_QUERY_PARAM, "120");
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        queryParameters.putAll(extraQueryParameters);

        //        http://localhost:18080/EniqEventsServices/TERMINAL/WCDMA/CALL_FAILURE_EVENT_ANALYSIS/TERMINAL/SUMMARY?time=30&type=TAC&groupname=TAC1&display=grid&tzOffset=+0100&maxRows=30
        final ClientResponse response = webResource.path(TERMINAL_SERVICES).path(WCDMA_CFA_TERMINAL_EVENT_ANALYSIS_URL)
                .path(TERMINAL_SERVICES).path(subPath).queryParams(queryParameters).header("requestId", "requestId")
                .accept(mediaType).get(ClientResponse.class);
        final String result = assertResponseIsOKAndReturnResponseText(TERMINAL_SERVICES, TERMINAL_GROUP_ANALYSIS,
                mediaType, response);
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            assertJSONSucceeds(result);
        }
    }
}
