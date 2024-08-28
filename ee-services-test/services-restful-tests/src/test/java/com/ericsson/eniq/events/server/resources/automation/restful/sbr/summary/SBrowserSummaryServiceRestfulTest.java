/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.sbr.summary;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.resources.automation.restful.ResourceBaseRestfulTest;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.detail.SBrowserImsiRabDetailTestDataProvider;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Session Browser Restful test class.
 * Test HTTP response with remote Services.
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
public class SBrowserSummaryServiceRestfulTest extends ResourceBaseRestfulTest {

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testAppLayerSummaryResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                USER_PLANE, APPLICATION_LAYER_SUMMARY);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRabSummaryResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_SUMMARY, RAB);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testVisitCellSummaryResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_SUMMARY, VISITED_CELLS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testTcpSummaryResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                USER_PLANE, TCP_PERFORMANCE_SUMMARY);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }
}
