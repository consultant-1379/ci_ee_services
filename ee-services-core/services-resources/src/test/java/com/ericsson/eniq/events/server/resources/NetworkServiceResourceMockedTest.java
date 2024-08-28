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
import com.ericsson.eniq.events.server.resources.piechart.CauseCodeAnalysisPieChartAPI;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * 
 * @author ehaoswa
 * 
 */
public class NetworkServiceResourceMockedTest extends BaseJMockUnitTest {
    private NetworkServiceResource networkServiceResource;

    @Before
    public void setUp() {
        networkServiceResource = new NetworkServiceResource();
    }

    @Test
    public void testGetEventAnalysisServiceResource() {
        final EventAnalysisResource mockEventAnalysisResource = mockery.mock(EventAnalysisResource.class);

        networkServiceResource.eventAnalysisResource = mockEventAnalysisResource;

        assertNotNull(networkServiceResource.getEventAnalysisResource());
    }

    @Test
    public void testGetCauseCodeAnalysisServiceResource() {
        final CauseCodeAnalysisResource mockCauseCodeAnalysisResource = mockery.mock(CauseCodeAnalysisResource.class);

        networkServiceResource.causeCodeAnalysisResource = mockCauseCodeAnalysisResource;

        assertNotNull(networkServiceResource.getCauseCodeAnalysisResource());
    }

    @Test
    public void testGetEventVolumeServiceResource() {
        final EventVolumeResource mockEventVolumeResource = mockery.mock(EventVolumeResource.class);

        networkServiceResource.eventVolumeResource = mockEventVolumeResource;

        assertNotNull(networkServiceResource.getEventVolumeResource());
    }

    @Test
    public void testGetKPIResource() {
        final KPIResource mockKpiResource = mockery.mock(KPIResource.class);

        networkServiceResource.kpiResource = mockKpiResource;

        assertNotNull(networkServiceResource.getKpiResource());
    }

    @Test
    public void testGetRoamingAnalysisResource() {
        final RoamingAnalysisResource mockRoamingAnalysisResource = mockery.mock(RoamingAnalysisResource.class);

        networkServiceResource.roamingAnalysisResource = mockRoamingAnalysisResource;

        assertNotNull(networkServiceResource.getroRoamingAnalysisResource());
    }

    @Test
    public void testGetGroupManagementResource() {
        final GroupMgtResource mockGroupMgtResource = mockery.mock(GroupMgtResource.class);

        networkServiceResource.groupMgtResource = mockGroupMgtResource;

        final String groupName = "blaa";
        final String groupData = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockGroupMgtResource).getGroupDetails(groupName);
                will(returnValue(groupData));
            }
        });
        assertNotNull(networkServiceResource.getGroupMgtResource());
        assertEquals(groupData, networkServiceResource.getGroupMgtResource().getGroupDetails(groupName));
    }

    @Test
    public void testGetRankingResource() {
        final MultipleRankingResource mockMultipleRankingResource = mockery.mock(MultipleRankingResource.class);

        networkServiceResource.multipleRankingResource = mockMultipleRankingResource;

        assertNotNull(networkServiceResource.getMultipleRankingResource());
    }

    @Test
    public void testGetKPIRatioResource() {
        final KPIRatioResource mockKpiRatioResource = mockery.mock(KPIRatioResource.class);

        networkServiceResource.kpiRatioResource = mockKpiRatioResource;

        assertNotNull(networkServiceResource.getKpiRatioResource());
    }

    @Test
    public void testGetDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        networkServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(networkServiceResource.getDatavolRankingResource());
    }

    @Test
    public void testGetGroupDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        networkServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(networkServiceResource.getDatavolGroupRankingResource());
    }

    @Test
    public void testGetDataVolumeResource() {
        final DataVolumeAnalysisResource mockDataVolumeAnalysisResource = mockery
                .mock(DataVolumeAnalysisResource.class);

        networkServiceResource.dataVolumeAnalysisResource = mockDataVolumeAnalysisResource;

        assertNotNull(networkServiceResource.getDataVolumeAnalysisResource());
    }

    @Test
    public void testGetCauseCodeAnalysisPieChartAPI() {
        final CauseCodeAnalysisPieChartAPI mockCauseCodeAnalysisPieChartAPI = mockery
                .mock(CauseCodeAnalysisPieChartAPI.class);

        networkServiceResource.causeCodeAnalysisPieChartAPI = mockCauseCodeAnalysisPieChartAPI;

        assertNotNull(networkServiceResource.getCauseCodeAnalysisPieChartAPI());
    }
}
