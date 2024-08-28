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
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ehaoswa
 * @since  Apr 2010
 */
public class EniqEventsDataServiceTest {
    private Mockery mockery;

    private EniqEventsDataService eniqEventsDataService;

    @Before
    public void setUp() {
        mockery = new Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
        eniqEventsDataService = new EniqEventsDataService();
    }

    @Test
    public void testGetMetaDataResource() throws IOException {
        final MetaDataResource mockMetaDataResource = mockery.mock(MetaDataResource.class);

        eniqEventsDataService.metaDataResource = mockMetaDataResource;

        final String metaData = "some funky metadata";
        mockery.checking(new Expectations() {
            {
                one(mockMetaDataResource).getUIMetaData();
                will(returnValue(metaData));
            }
        });

        assertNotNull(metaData, eniqEventsDataService.getMetaDataResource());
    }

    @Test
    public void testGetSubscriberServiceResource() throws Exception {
        final SubscriberServiceResource mockSubscriberEventAnalysisResource = mockery
                .mock(SubscriberServiceResource.class);

        eniqEventsDataService.subscriberServiceResource = mockSubscriberEventAnalysisResource;

        final String subscriberEventAnalysisData = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockSubscriberEventAnalysisResource).getEventAnalysisResource();
                will(returnValue(subscriberEventAnalysisData));
            }
        });

        assertNotNull(subscriberEventAnalysisData, eniqEventsDataService.getSubscriberServiceResource());
    }

    @Test
    public void testGetNetworkServiceResource() throws Exception {
        final NetworkServiceResource mockNetworkServiceResource = mockery.mock(NetworkServiceResource.class);

        eniqEventsDataService.networkServiceResource = mockNetworkServiceResource;

        final String networkEventAnalysisData = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockNetworkServiceResource).getEventAnalysisResource();
                will(returnValue(networkEventAnalysisData));

            }
        });

        assertNotNull(networkEventAnalysisData, eniqEventsDataService.getNetworkServiceResource());
    }

    @Test
    public void testGetLiveLoadResource() throws Exception {
        final LiveLoadResource mockLiveLoadResource = mockery.mock(LiveLoadResource.class);

        eniqEventsDataService.liveLoadResource = mockLiveLoadResource;

        final String data = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockLiveLoadResource).getLiveLoadApns();
                will(returnValue(data));

            }
        });

        assertNotNull(data, eniqEventsDataService.getLiveLoadResource().getLiveLoadApns());
    }

    @Test
    public void testGetImsiMsisdnMappingResource() throws Exception {
        final ImsiMsisdnMappingResource mockImsiMsisdnMappingResource = mockery.mock(ImsiMsisdnMappingResource.class);

        eniqEventsDataService.imsiMsisdnMappingResource = mockImsiMsisdnMappingResource;

        final String data = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockImsiMsisdnMappingResource).getMappingResults("IMSI", "123456789012345");
                will(returnValue(data));

            }
        });

        assertNotNull(data,
                eniqEventsDataService.getImsiMsisdnMappingResource().getMappingResults("IMSI", "123456789012345"));
    }

}
