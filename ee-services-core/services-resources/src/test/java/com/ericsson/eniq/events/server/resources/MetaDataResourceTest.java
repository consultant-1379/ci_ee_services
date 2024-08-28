/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.ericsson.eniq.events.server.services.MetaDataService;

/**
 * 
 * @author edeccox
 * @since  Apr 2010
 *
 */
public class MetaDataResourceTest {
    @Test
    public void testGetMetaData() throws IOException {
        final Mockery mockery = new Mockery();
        final MetaDataService mockMetaDataService = mockery.mock(MetaDataService.class);
        final MetaDataResource metaDataResource = new MetaDataResource();
        metaDataResource.metaDataService = mockMetaDataService;

        final String metaData = "some funky metadata";
        mockery.checking(new Expectations() {
            {
                one(mockMetaDataService).getUIMetaData();
                will(returnValue(metaData));
            }
        });

        ;
        assertNotNull(metaData, metaDataResource.getUIMetaData());
    }
}