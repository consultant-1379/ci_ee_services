/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.sbr.detail;

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
public class SBrowserDetailServiceRestfulTest extends ResourceBaseRestfulTest {

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testAppLayerDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                USER_PLANE, APPLICATION_LAYER_DETAILS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testCoreDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, CORE);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRabDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, RAB);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRanCfaDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, RAN_CFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRanHfaDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, RAN_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRanSessionDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, RAN_SESSION);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRccDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, RADIO_AND_CELL_CONDITION);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testRrcDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_DETAIL, RRC_MEAS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testTcpDetailResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                USER_PLANE, TCP_PERFORMANCE_DETAILS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }
}
