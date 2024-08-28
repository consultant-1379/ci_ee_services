/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful;

import javax.annotation.Resource;

import org.springframework.beans.factory.FactoryBean;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;

/**
 * @author ejedmar
 * @author eromsza
 *
 * @since 2011
 */
public class WebResourceFactoryBean implements FactoryBean<WebResource> {

    @Resource(name = "authenticatedApacheHttpClientFactoryBean")
    private ApacheHttpClient client;

    private String uri;

    @Override
    public WebResource getObject() throws Exception {
        return client.resource(uri);
    }

    @Override
    public Class<WebResource> getObjectType() {
        return WebResource.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public void setClient(final ApacheHttpClient client) {
        this.client = client;

    }
}
