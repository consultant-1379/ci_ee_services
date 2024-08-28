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
 * test on remote server
 * 
 * @author EEMECOY
 *
 */

public class TerminalAndGroupAnalysisResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testgetMostMobilityIssuesDataAsCSV() {
        testTerminalAnalysisForSubPath(MOST_MOBILITY_ISSUES, MediaTypeConstants.APPLICATION_CSV);
    }

    @Test
    public void testgetMostMobilityIssuesDataAsJson() {
        testTerminalAnalysisForSubPath(MOST_MOBILITY_ISSUES, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testgetMostPDPSessionSetupFailuresDataAsJson() {
        testTerminalAnalysisForSubPath(MOST_PDP_SESSION_SETUP_FAILURES, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testgetMostPDPSessionSetupFailuresDataAsCSV() {
        testTerminalAnalysisForSubPath(MOST_PDP_SESSION_SETUP_FAILURES, MediaTypeConstants.APPLICATION_CSV);
    }

    @Test
    public void testgetMostAttachedFailuresDataAsJson() {
        testTerminalAnalysisForSubPath(MOST_ATTACHED_FAILURES, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testgetMostAttachedFailuresDataAsCSV() {
        testTerminalAnalysisForSubPath(MOST_ATTACHED_FAILURES, MediaTypeConstants.APPLICATION_CSV);
    }

    @Test
    public void testGetMostPopularEventSummaryDataAsJson() {
        testTerminalAnalysisForSubPath(MOST_POPULAR_EVENT_SUMMARY, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testGetMostPopularEventSummaryDataAsCSV() {
        testTerminalAnalysisForSubPath(MOST_POPULAR_EVENT_SUMMARY, MediaTypeConstants.APPLICATION_CSV);
    }

    @Test
    public void testGetMostPopularDataAsJson() {
        testTerminalAnalysisForSubPath(MOST_POPULAR, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testGetMostPopularDataAsCSV() {
        testTerminalAnalysisForSubPath(MOST_POPULAR, MediaTypeConstants.APPLICATION_CSV);
    }

    private void testTerminalAnalysisForSubPath(final String subPath, final String mediaType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TIME_QUERY_PARAM, "120");
        queryParameters.putSingle(TZ_OFFSET, "+0100");

        final ClientResponse response = webResource.path(TERMINAL_SERVICES).path(TERMINAL_GROUP_ANALYSIS).path(subPath)
                .queryParams(queryParameters).header("requestId", "requestId").accept(mediaType)
                .get(ClientResponse.class);
        final String result = assertResponseIsOKAndReturnResponseText(TERMINAL_SERVICES, TERMINAL_GROUP_ANALYSIS,
                mediaType, response);
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            assertJSONSucceeds(result);
        }
    }

}
