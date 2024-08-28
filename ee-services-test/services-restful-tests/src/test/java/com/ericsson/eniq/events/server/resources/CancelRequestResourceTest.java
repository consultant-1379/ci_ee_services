/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author ejoegaf
 * @since 2011
 *
 */
public class CancelRequestResourceTest extends BaseRESTfulServiceTest implements Runnable {

    private String requestId;

    /**
     * @param requestId
     */
    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    @Override
    @Before
    public void setup() throws URISyntaxException {
        super.setup();
    }

    @Test
    public void testDummy() {
        assertTrue(true);//always pass - this is a place holder to allow this class be initialised properly.
    }

    @Override
    public void run() {
        //This method will get called from the DummyRequestForCancelRequestResourceTest
        //It will send a cancel request.

        try {
            super.readHostPropertyFromFile();
            super.setup();
        } catch (final Exception e) {
            //ignore
        }

        System.out.println("In Cancel Thread:: about to send cancel request");
        webResource.path(CANCEL).header("requestId", requestId).accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("In Cancel Thread::  Cancel Sent");
    }

}
