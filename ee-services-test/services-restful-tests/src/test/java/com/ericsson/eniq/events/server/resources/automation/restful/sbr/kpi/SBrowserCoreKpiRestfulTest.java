/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.sbr.kpi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.resources.automation.restful.ResourceBaseRestfulTest;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.kpi.SBrowserCellKpiDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.kpi.SBrowserNetwkKpiTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.kpi.SBrowserRncKpiTestDataProvider;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Session Browser Restful test class.
 * Test HTTP response with remote Services.
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
public class SBrowserCoreKpiRestfulTest extends ResourceBaseRestfulTest {

    @Test
    @Parameters(source = SBrowserCellKpiDataProvider.class)
    public void testCoreCellKpiChartResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_KPI, CORE, KPI_CHART);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserRncKpiTestDataProvider.class)
    public void testCoreRncKpiChartResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_KPI, CORE, KPI_CHART);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserNetwkKpiTestDataProvider.class)
    public void testCoreNetworkKpiChartResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_KPI, CORE, KPI_CHART);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserRncKpiTestDataProvider.class)
    public void testCoreRncKpiMapResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_KPI, CORE, KPI_MAP);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    @Parameters(source = SBrowserNetwkKpiTestDataProvider.class)
    public void testCoreNetworkKpiMapResponseAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, SESSION_BROWSER,
                SESSION_KPI, CORE, KPI_MAP);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

}
