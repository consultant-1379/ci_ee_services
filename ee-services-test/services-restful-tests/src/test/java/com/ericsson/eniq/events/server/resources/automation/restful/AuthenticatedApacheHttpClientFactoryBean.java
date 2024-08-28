package com.ericsson.eniq.events.server.resources.automation.restful;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.FactoryBean;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eromsza
 *
 * @since 2012
 */
public class AuthenticatedApacheHttpClientFactoryBean implements FactoryBean<ApacheHttpClient> {

    @Resource(name = "apacheHttpClient")
    private ApacheHttpClient client;

    private String uri;

    private String user;

    private String password;

    // Authenticate only once per client instance
    public void authenticate() throws UniformInterfaceException, URISyntaxException {
        final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.putSingle("j_username", user);
        formData.putSingle("j_password", password);
        assertResponseRedirect(client.resource(new URI(uri)).path("j_security_check")
                .type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData));
    }

    @Override
    public ApacheHttpClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<ApacheHttpClient> getObjectType() {
        return ApacheHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public void setClient(final ApacheHttpClient client) {
        this.client = client;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    ///////////////////////////////////////////////////////////////////

    private void assertResponseRedirect(final ClientResponse response) {
        assertTrue("Authentication failed, the request SHOULD be redirected.",
                response.getStatus() == ClientResponse.Status.FOUND.getStatusCode());
    }
}
