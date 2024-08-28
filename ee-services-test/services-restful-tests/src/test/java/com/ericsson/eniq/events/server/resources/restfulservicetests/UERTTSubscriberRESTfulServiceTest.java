/*------------------------------------------------------------------------------	
 *******************************************************************************	
 * COPYRIGHT Ericsson 2013	
 *	
 * The copyright to the computer program(s) herein is the property of	
 * Ericsson Inc. The programs may be used and/or copied only with written	
 * permission from Ericsson Inc. or in accordance with the terms and	
 * conditions stipulated in the agreement/contract under which the	
 * program(s) have been supplied.	
 *******************************************************************************	
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author xmonsou
 * @since 2013
 * 
 */
public class UERTTSubscriberRESTfulServiceTest extends BaseRESTfulServiceTest {
    @Test
    public void getDataUERTTSubscriber() {
        final String mediaType = MediaType.APPLICATION_JSON;
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(IMSI_PARAM, "46000000001234567");
        final ClientResponse response = webResource.path(SUBSCRIBER_SERVICES).queryParams(queryParameters)
                .path(WCDMA_UERTT).path(UERTT).accept(mediaType).get(ClientResponse.class);
        final String result = assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, WCDMA_UERTT, mediaType,
                response);
        assertJSONSucceeds(result);
    }
}
