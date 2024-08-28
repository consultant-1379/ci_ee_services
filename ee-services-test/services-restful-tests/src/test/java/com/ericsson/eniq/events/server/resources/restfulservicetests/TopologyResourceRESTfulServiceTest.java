/**
 * -----------------------------------------------------------------------
 *     Copyright (C)/ 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 *
 * @author EEMECOY
 *
 */
public class TopologyResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void gestGetConnectedCells3G() {
        final String mediaType = MediaType.APPLICATION_JSON;
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        queryParameters.putSingle(NODE_PARAM, "769,RNC01,Ericsson,0");
        queryParameters.putSingle(CELL_PARAM, "CELL68513");//apparently all URIs should contain cell="cellid"

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters).path(TOPOLOGY)
                .path(THREE_G).path(CONNECTED_CELLS).accept(mediaType).get(ClientResponse.class);
        final String result = assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, TOPOLOGY, mediaType, response);
        assertJSONSucceeds(result);
    }

}
