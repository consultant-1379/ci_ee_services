/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.dtput.DataVolumeAnalysisResource;
import com.ericsson.eniq.events.server.resources.dtput.DatavolRankingResource;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * mockup testing
 * 
 * @author eavidat
 * @author EEMECOY
 * @author ehaoswa 
 */
public class SubscriberServiceResourceMockedTest extends BaseJMockUnitTest {

    private SubscriberServiceResource subscriberServiceResource;

    @Before
    public void setUp() {

        subscriberServiceResource = new SubscriberServiceResource();

    }

    @Test
    public void testGetGroupManagementResource() {
        final GroupMgtResource mockGroupMgtResource = mockery.mock(GroupMgtResource.class);
        subscriberServiceResource.groupMgtResource = mockGroupMgtResource;
        final String groupName = "aGroup";
        final String someData = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockGroupMgtResource).getGroupDetails(groupName);
                will(returnValue(someData));
            }
        });
        assertNotNull(subscriberServiceResource.getGroupMgtResource());
        assertEquals(someData, subscriberServiceResource.getGroupMgtResource().getGroupDetails(groupName));
    }

    @Test
    public void testGetEventAnalysisServiceResource() {
        final EventAnalysisResource mockEventAnalysisResource = mockery.mock(EventAnalysisResource.class);

        subscriberServiceResource.eventAnalysisResource = mockEventAnalysisResource;

        assertNotNull(subscriberServiceResource.getEventAnalysisResource());
    }

    @Test
    public void testGetKPIResource() {
        final KPIResource mockKpiResource = mockery.mock(KPIResource.class);

        subscriberServiceResource.kpiResource = mockKpiResource;

        assertNotNull(subscriberServiceResource.getKpiResource());
    }

    @Test
    public void testGetRankingResource() {
        final MultipleRankingResource mockMultipleRankingResource = mockery.mock(MultipleRankingResource.class);

        subscriberServiceResource.multipleRankingResource = mockMultipleRankingResource;

        assertNotNull(subscriberServiceResource.getMultipleRankingResource());
    }

    @Test
    public void testGetSubsessionBIResource() {
        final SubsessionBIResource mockSubsessionBIResource = mockery.mock(SubsessionBIResource.class);

        subscriberServiceResource.subsessionBIResource = mockSubsessionBIResource;

        assertNotNull(subscriberServiceResource.getSubsessionBIResource());
    }

    @Test
    public void testGetEventRecurringResource() {
        final EventRecurringResource mockEventRecurringResource = mockery.mock(EventRecurringResource.class);

        subscriberServiceResource.eventRecurringResource = mockEventRecurringResource;

        assertNotNull(subscriberServiceResource.getEventRecurringResource());
    }

    @Test
    public void testGetDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        subscriberServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(subscriberServiceResource.getDatavolRankingResource());
    }

    @Test
    public void testGetGroupDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        subscriberServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(subscriberServiceResource.getDatavolGroupRankingResource());
    }

    @Test
    public void testGetDataVolumeResource() {
        final DataVolumeAnalysisResource mockDataVolumeAnalysisResource = mockery
                .mock(DataVolumeAnalysisResource.class);

        subscriberServiceResource.dataVolumeAnalysisResource = mockDataVolumeAnalysisResource;

        assertNotNull(subscriberServiceResource.getDataVolumeAnalysisResource());
    }

}
