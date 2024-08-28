/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class WCDMACallFailureRoamingRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetRoamingDataByOperator() {
        runRoamingQuery(OPERATOR);
    }

    private void runRoamingQuery(final String roamingType) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        final String json = runQuery(requestParameters, MediaType.APPLICATION_JSON, NETWORK_SERVICES,
                WCDMA_CALL_FAILURE_ROAMING_ANALYSIS, roamingType);
        assertJSONSucceeds(json);
    }

    @Test
    public void testGetRoamingDataByCountry() {
        runRoamingQuery(COUNTRY);
    }
}
