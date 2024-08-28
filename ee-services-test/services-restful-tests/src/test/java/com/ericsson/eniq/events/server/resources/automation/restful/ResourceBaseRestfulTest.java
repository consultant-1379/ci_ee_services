/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;
import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.ericsson.eniq.events.server.test.util.JSONAssertUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Note (eemecoy) - not sure whether these tests should be used when creating RESTful tests - these generate different parameter
 * combinations which results in more call on the services layers, but the RESTful tests should just test that the URLs exist
 * For that reason, the dataproviders have just been copied in here rather than reusing the data providers used in the unit tests
 * @author ejedmar
 * @since 2011
 *
 */
@ContextConfiguration(locations = { "classpath:com/ericsson/eniq/events/server/resources/automation/restful/restful-context.xml" })
public abstract class ResourceBaseRestfulTest {

    @Resource(name = "eniqWebResourceFactory")
    protected WebResource eniqResource;

    private TestContextManager testContextManager;

    @Autowired
    protected JSONAssertUtils jsonAssertUtils;

    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    protected String assertResponseIsOKAndReturnResponseText(final String mediaType, final ClientResponse response) {
        final String responseText = response.getEntity(String.class);
        assertEquals(
                "GET with mime type " + mediaType + " doesn't give expected response, response is " + responseText,
                ClientResponse.Status.OK, response.getClientResponseStatus());
        System.out.println(responseText);
        assertNotNull(responseText);
        return responseText;
    }

    protected ClientResponse runQuery(final MultivaluedMap<String, String> requestParameters, final String mimeType,
            final String... subPaths) {
        WebResource webResource = eniqResource;
        for (final String subPathElement : subPaths) {
            webResource = webResource.path(subPathElement);
        }
        final ClientResponse response = webResource.queryParams(requestParameters).header(REQUEST_ID, TEST_REQUEST_ID)
                .accept(mimeType).get(ClientResponse.class);
        return response;
    }
}