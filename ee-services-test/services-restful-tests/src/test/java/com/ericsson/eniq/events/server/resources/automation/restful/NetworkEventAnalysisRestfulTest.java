/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.NetworkEventAnalysisTestDataProvider;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author ejedmar
 * @author eromsza
 *
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
public class NetworkEventAnalysisRestfulTest extends ResourceBaseRestfulTest {

    @Test
    @Parameters(source = NetworkEventAnalysisTestDataProvider.class)
    public void testGetDataAsCSV(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaTypeConstants.APPLICATION_CSV,
                NETWORK_SERVICES, EVENT_ANALYSIS);
        assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
    }

    @Test
    @Parameters(source = NetworkEventAnalysisTestDataProvider.class)
    public void testGetDataAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, NETWORK_SERVICES,
                EVENT_ANALYSIS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }
}
