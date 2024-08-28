/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CORE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.POPUP_DETAIL;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.PROPERTIES;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAB;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAN_CFA;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAN_HFA;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAN_SESSION_HFA;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RRC_MEAS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SESSION_BROWSER;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.resources.automation.restful.ResourceBaseRestfulTest;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiHfaHhoPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiPopupCfaTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiPopupHfaIRatTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiPopupHfaIfhoTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiPopupHfaSohoTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiPopupHsdschErrTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiPopupHsdschSucTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiRabPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiRrcPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup.SBrowserImsiSGEHPopupTestDataProvider;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Session Browser Restful test class.
 * Test HTTP response with remote Services.
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
public class SBrowserPopupServiceRestfulTest extends ResourceBaseRestfulTest {

    @Test
    @Parameters(source = SBrowserImsiRabPopupTestDataProvider.class)
    public void testRabPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAB);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiPopupCfaTestDataProvider.class)
    public void testRanCfaPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_CFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiHfaHhoPopupTestDataProvider.class)
    public void testRanHfaHhoPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_SESSION_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiPopupHsdschErrTestDataProvider.class)
    public void testRanHfaHHsdschPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiPopupHfaIfhoTestDataProvider.class)
    public void testRanHfaIfhoPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiPopupHfaIRatTestDataProvider.class)
    public void testRanHfaIratPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiPopupHfaSohoTestDataProvider.class)
    public void testRanHfaSohoPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiRrcPopupTestDataProvider.class)
    public void testRanRrcPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RRC_MEAS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiPopupHsdschSucTestDataProvider.class)
    public void testRanHfaSucHsdschPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, RAN_SESSION_HFA);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserImsiSGEHPopupTestDataProvider.class)
    public void testSgehPopupResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                POPUP_DETAIL, PROPERTIES, CORE);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

}
