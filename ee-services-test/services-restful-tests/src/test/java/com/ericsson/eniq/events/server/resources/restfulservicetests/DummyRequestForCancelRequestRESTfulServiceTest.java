/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.junit.Assert.*;

import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.resources.CancelRequestResourceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejoegaf
 * @since 2011
 *
 */
public class DummyRequestForCancelRequestRESTfulServiceTest extends BaseRESTfulServiceTest {

    protected static final String BSC_NAME = "BSC193,ERICSSON,1";

    @Test
    public void testCallingGetData() throws Exception {
        final String requestId = generateRandomRequestID();
        // Create the object with the run() method
        final CancelRequestResourceTest runnableCancelReq = new CancelRequestResourceTest();
        runnableCancelReq.setRequestId(requestId);

        // Create the thread supplying it with the runnable object
        final Thread thread = new Thread(runnableCancelReq);

        System.out.println(new Date() + "In Main Request - Before Cancel Thread Start");
        // Start the thread - this will send a cancel request to cancel the request we are about to send below.
        thread.start();

        try {
            //give the cancel more time to reach the server
            Thread.sleep(1000);
        } catch (final InterruptedException intEx) {
            System.out.println("Failed to sleep");
        }

        System.out.println(new Date() + "After Cancel Thread Start - Before Main Request");

        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, "10080");
        queryParameters.putSingle(TZ_OFFSET, "+0100");

        final ClientResponse response = webResource.path(NETWORK_SERVICES).path(CALL_FAILURE_RANKING_ANALYSIS)
                .path(CELL).queryParams(queryParameters).header("requestId", requestId)
                .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        assertResponseNotReceived(response);
    }

    void assertResponseNotReceived(final ClientResponse response) {
        final int responseStatus = response.getStatus();
        assertTrue((responseStatus == 204) || (responseStatus == 500));
    }

}
