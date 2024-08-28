/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.REQUEST_ID;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.TEST_REQUEST_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Random;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import com.ericsson.eniq.events.server.test.util.JSONAssertUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.ApacheHttpClientConfig;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Base class for running tests against a WAR file deployed remotely
 * Remote host is configured in remote_tests.properties
 *
 * @author EEMECOY
 * @author eromsza
 */
@Ignore
public class BaseRESTfulServiceTest {

    protected static String host;

    protected static int timeout;

    protected static String user;

    protected static String password;

    protected static Client client;

    /** The webResource. */
    protected WebResource webResource;

    protected JSONAssertUtils jsonAssertUtils;

    @BeforeClass
    public static void readHostPropertyFromFile() throws IOException, URISyntaxException {
        final Properties properties = new Properties();
        final InputStream resourceAsStream = BaseRESTfulServiceTest.class.getClassLoader().getResourceAsStream(
                "com/ericsson/eniq/events/server/test/remote_tests.properties");
        properties.load(resourceAsStream);
        host = (String) properties.get("remote_host");
        timeout = Integer.parseInt((String) properties.get("timeout"));
        user = (String) properties.get("user");
        password = (String) properties.get("password");

        final DefaultApacheHttpClientConfig config = new DefaultApacheHttpClientConfig();
        config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, true);

        client = ApacheHttpClient.create(config);
        client.setFollowRedirects(false);

        // Authenticate only once per client instance
        final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.putSingle("j_username", user);
        formData.putSingle("j_password", password);
        assertResponseRedirect(client.resource(new URI(host)).path("j_security_check")
                .type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData));
    }

    @Before
    public void setup() throws URISyntaxException {
        final URI serverUri = new URI(host);
        webResource = client.resource(serverUri);
        jsonAssertUtils = new JSONAssertUtils();
    }

    protected String runQuery(final MultivaluedMap<String, String> requestParameters, final String mimeType,
            final String... subPaths) {
        for (final String subPathElement : subPaths) {
            webResource = webResource.path(subPathElement);
        }

        final ClientResponse response = webResource.queryParams(requestParameters).type(MediaType.APPLICATION_JSON)
                .header(REQUEST_ID, TEST_REQUEST_ID).accept(mimeType).get(ClientResponse.class);
        return assertResponseIsOKAndReturnResponseText(mimeType, response);
    }

    protected void assertJSONSucceeds(final String testString) {
        jsonAssertUtils.assertJSONSucceeds(testString);
    }

    void assertDataIsNotInJSONFormat(final String result) {
        jsonAssertUtils.assertDataIsNotInJSONFormat(result);

    }

    /**
     * Check that response code is 200 (OK)
     *
     * If not test fails
     * If response code is ok, then return text contained in response
     *
     * @param path              main path eg SUBSCRIBER_SERVICES
     * @param subPath           sub path eg EVENT_ANALYSIS
     * @param mediaType
     * @param response
     * @param responseText
     * @return
     */
    protected String assertResponseIsOKAndReturnResponseText(final String path, final String subPath,
            final String mediaType, final ClientResponse response) {
        final String infoMessageReal = "on path " + host + "/" + path + "/" + subPath;
        return assertResponseIsOKAndReturnResponseText(response, mediaType, infoMessageReal);
    }

    private String assertResponseIsOKAndReturnResponseText(final ClientResponse response, final String mediaType,
            final String infoMessageReal) {
        final String responseText = response.getEntity(String.class);
        assertEquals("GET " + infoMessageReal + "with mime type " + mediaType
                + " doesn't give expected response, response is " + responseText, ClientResponse.Status.OK,
                response.getClientResponseStatus());
        System.out.println(responseText);
        assertNotNull(responseText);
        return responseText;
    }

    protected String assertResponseIsOKAndReturnResponseText(final String mediaType, final ClientResponse response) {
        return assertResponseIsOKAndReturnResponseText(response, mediaType, "");
    }

    public String assertResponseNotOk(final ClientResponse response) {
        assertFalse(response.getStatus() == ClientResponse.Status.OK.getStatusCode());
        return response.getEntity(String.class);
    }

    public static String assertResponseRedirect(final ClientResponse response) {
        assertTrue("Authentication failed, the request SHOULD be redirected.",
                response.getStatus() == ClientResponse.Status.FOUND.getStatusCode());
        return response.getEntity(String.class);
    }

    protected String generateRandomRequestID() {
        final Random randomGenerator = new Random();
        return "requestId" + randomGenerator.nextInt();
    }
}
