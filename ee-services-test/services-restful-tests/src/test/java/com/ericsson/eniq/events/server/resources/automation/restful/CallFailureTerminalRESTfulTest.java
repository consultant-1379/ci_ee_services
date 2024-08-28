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
import com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.CallFailureTerminalGroupAndTerminalAnalysisTestDataProvider;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author ejedmar
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
public class CallFailureTerminalRESTfulTest extends ResourceBaseRestfulTest {

    @Test
    @Parameters(source = CallFailureTerminalGroupAndTerminalAnalysisTestDataProvider.class)
    public void testGetDataAsCSV(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaTypeConstants.APPLICATION_CSV,
                TERMINAL_SERVICES, CALL_FAILURE_TERMINAL_ANALYSIS, MOST_CALL_DROPS);
        assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
    }

    @Test
    @Parameters(source = CallFailureTerminalGroupAndTerminalAnalysisTestDataProvider.class)
    public void testGetDataAsJson(final MultivaluedMap<String, String> requestParameters) {
        final ClientResponse response = runQuery(requestParameters, MediaType.APPLICATION_JSON, TERMINAL_SERVICES,
                CALL_FAILURE_TERMINAL_ANALYSIS, MOST_CALL_DROPS);
        final String json = assertResponseIsOKAndReturnResponseText(MediaType.APPLICATION_JSON, response);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

}
