/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;

/**
 * Utility methods for media type logic
 *
 * @author eemecoy
 */
@Stateless
@LocalBean
public class MediaTypeHandler {

    /**
     * The Constant APPLICATION_CSV_MEDIA_TYPE.
     */

    /**
     * Returns true if the first media type defined in the http headers is of type
     * If the acceptableMediaTypes in httpHeaders is null, then false is returned
     * text/csv.
     *
     * @param httpHeaders
     * @return true, if checks if is media type application csv
     */
    public boolean isMediaTypeApplicationCSV(final HttpHeaders httpHeaders) {

        try {
            final MediaType APPLICATION_CSV_MEDIA_TYPE = new MediaType("application", "csv");

            final List<MediaType> types = httpHeaders.getAcceptableMediaTypes();
            return types != null && types.contains(APPLICATION_CSV_MEDIA_TYPE);
        } catch (final RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isMediaTypeApplicationCSV(final List<String> acceptableMediaTypes) {
        try {
            return acceptableMediaTypes != null && acceptableMediaTypes.contains(MediaTypeConstants.APPLICATION_CSV);
        } catch (final RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
