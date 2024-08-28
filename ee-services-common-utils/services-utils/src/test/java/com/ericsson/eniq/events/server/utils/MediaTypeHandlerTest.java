/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * @author eemecoy
 *
 */
public class MediaTypeHandlerTest extends BaseJMockUnitTest {

    private MediaTypeHandler mediaTypeHandler;

    HttpHeaders httpHeaders;

    @Before
    public void setup() {
        mediaTypeHandler = new MediaTypeHandler();
        httpHeaders = mockery.mock(HttpHeaders.class);
    }

    @Test
    public void testisMediaTypeApplicationCSVReturnsFalseForJSONType() {
        final List<MediaType> mediaTypesAccepted = new ArrayList<MediaType>();
        mediaTypesAccepted.add(MediaType.APPLICATION_JSON_TYPE);
        expectsOnHttpHeaders(mediaTypesAccepted);
        assertThat(mediaTypeHandler.isMediaTypeApplicationCSV(httpHeaders), is(false));
    }

    @Test
    public void testisMediaTypeApplicationCSVReturnsTrueForCSVType() {
        final List<MediaType> mediaTypesAccepted = new ArrayList<MediaType>();
        mediaTypesAccepted.add(new MediaType("application", "csv"));
        expectsOnHttpHeaders(mediaTypesAccepted);
        assertThat(mediaTypeHandler.isMediaTypeApplicationCSV(httpHeaders), is(true));
    }

    private void expectsOnHttpHeaders(final List<MediaType> mediaTypesAccepted) {
        mockery.checking(new Expectations() {
            {
                one(httpHeaders).getAcceptableMediaTypes();
                will(returnValue(mediaTypesAccepted));
            }
        });

    }
}
