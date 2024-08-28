/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author EEMECOY
 *
 */
public class QOSStatisticsResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetQOSStatistics_JSON() {
        final String result = testGetData(MediaType.APPLICATION_JSON);
        assertJSONSucceeds(result);
    }

    @Test
    public void testGetQOSStatistics_CSV() {
        testGetData(MediaTypeConstants.APPLICATION_CSV);
    }

    private String testGetData(final String mediaType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_APN);
        queryParameters.putSingle(NODE_PARAM, SAMPLE_APN);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters)
                .path(QOS_STATISTICS).header(REQUEST_ID, SAMPLE_REQUEST_ID).accept(mediaType).get(ClientResponse.class);
        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, QOS_STATISTICS, mediaType, response);
    }

}
