/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * dummy implementation of the HttpHeaders interface to enable tests
 * 
 * only method implemented is the getAcceptableMediaTypes(), which just returns a list with one element,
 * the application/json media type
 *  
 * @author eemecoy
 *
 */
public class DummyHttpHeaders implements HttpHeaders {

    private List<MediaType> acceptableMediaTypes;

    public DummyHttpHeaders() {
        final List<MediaType> defaultAcceptableMediaTypes = new ArrayList<MediaType>();
        defaultAcceptableMediaTypes.add(new MediaType("application", "json"));
        setAcceptableMediaTypes(defaultAcceptableMediaTypes);
    }

    /**
     * @param acceptableMediaTypes
     */
    public void setAcceptableMediaTypes(final List<MediaType> acceptableMediaTypes) {
        this.acceptableMediaTypes = acceptableMediaTypes;

    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getAcceptableLanguages()
     */
    @Override
    public List<Locale> getAcceptableLanguages() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getAcceptableMediaTypes()
     */
    @Override
    public List<MediaType> getAcceptableMediaTypes() {
        return acceptableMediaTypes;
    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getCookies()
     */
    @Override
    public Map<String, Cookie> getCookies() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getLanguage()
     */
    @Override
    public Locale getLanguage() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getMediaType()
     */
    @Override
    public MediaType getMediaType() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getRequestHeader(java.lang.String)
     */
    @Override
    public List<String> getRequestHeader(final String s) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.ws.rs.core.HttpHeaders#getRequestHeaders()
     */
    @Override
    public MultivaluedMap<String, String> getRequestHeaders() {
        final MultivaluedMap<String, String> mvMap = new MultivaluedMapImpl();
        mvMap.putSingle("requestId", "requestId");
        return mvMap;
    }

}
