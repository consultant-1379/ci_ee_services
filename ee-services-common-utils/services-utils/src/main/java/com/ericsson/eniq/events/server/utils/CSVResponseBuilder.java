/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.ContentDisposition;

/**
 * Class responsible for building the Response object for CSV requests
 * @author eemecoy
 *
 */
@Stateless
@Local
public class CSVResponseBuilder {

    /** file name for the exported csv data. */
    private static final String CSV_EXPORT_FILE_NAME = "export.csv";

    /**      
     * Put together the response (including the http headers) for the csv data
     * This includes headers to ensure the data isn't cached.

     *
     * @return the response
     */
    public Response buildHttpResponseForCSVData() {
        final ContentDisposition contentDisposition = ContentDisposition.type("application")
                .fileName(CSV_EXPORT_FILE_NAME).build();
        return Response.ok(null).header("Content-Disposition", contentDisposition).header("Cache-control", "no-cache")
                .header("Content-Description", "File Transfer").header("Transfer-Encoding", "chunked").build();
    }

}
