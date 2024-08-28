/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejedmar
 * @since 2011
 *
 */
public class CallFailureTerminalGroupRESTfulTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataMostCallDropsAsJson() {
        runGroupAnalysisQueryMostCallDrops(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON);
    }

    @Test
    public void testGetDataMostCallSetupFailuresAsJson() {
        runGroupAnalysisQueryMostCallSetupFailures(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON);
    }

    private String runGroupAnalysisQueryMostCallDrops(final MultivaluedMapImpl requestParameters, final String mediaType) {
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        return runQuery(requestParameters, mediaType, TERMINAL_SERVICES, CALL_FAILURE_TERMINAL_GROUP_ANALYSIS,
                MOST_CALL_DROPS);
    }

    private String runGroupAnalysisQueryMostCallSetupFailures(final MultivaluedMapImpl requestParameters,
            final String mediaType) {
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String response = runQuery(requestParameters, mediaType, TERMINAL_SERVICES,
                CALL_FAILURE_TERMINAL_GROUP_ANALYSIS, MOST_CALL_SETUP_FAILURES);
        return response;
    }

    @Test
    public void testGetDrilldownDataMostCallDropsAsJson() {
        final MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.putSingle(GROUP_NAME, SAMPLE_TAC_GROUP);
        final String json = runGroupAnalysisQueryMostCallDrops(map, MediaType.APPLICATION_JSON);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    public void testGetDrilldownDataMostCallDropsAsCSV() {
        final MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.putSingle(GROUP_NAME, SAMPLE_TAC_GROUP);
        runGroupAnalysisQueryMostCallDrops(map, MediaTypeConstants.APPLICATION_CSV);
    }

    @Test
    public void testGetDrilldownDataMostCallSetupFailuresAsJson() {
        final MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.putSingle(GROUP_NAME, SAMPLE_TAC_GROUP);
        final String json = runGroupAnalysisQueryMostCallSetupFailures(map, MediaType.APPLICATION_JSON);
        jsonAssertUtils.assertJSONSucceeds(json);
    }

    @Test
    public void testGetDrilldownDataMostCallSetupFailuresAsCSV() {
        final MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.putSingle(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        runGroupAnalysisQueryMostCallSetupFailures(map, MediaTypeConstants.APPLICATION_CSV);
    }

}
