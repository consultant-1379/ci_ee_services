/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 * @author eromsza
 *
 */
public class MetaDataResourceRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetUIMetaData() {
        runQuery(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, METADATA_SERVICE, UI_PARAM);
    }
}
