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
 * @author ehaoswa
 *
 */
public class TerminalServiceResourceMockedTest extends BaseJMockUnitTest {
    private TerminalServiceResource terminalServiceResource;

    @Before
    public void setUp() {

        terminalServiceResource = new TerminalServiceResource();
    }

    @Test
    public void testGetGroupManagementResource() {
        final GroupMgtResource mockGroupMgtResource = mockery.mock(GroupMgtResource.class);
        terminalServiceResource.groupMgtResource = mockGroupMgtResource;
        final String groupName = "aGroup";
        final String someData = "some data";
        mockery.checking(new Expectations() {
            {
                one(mockGroupMgtResource).getGroupDetails(groupName);
                will(returnValue(someData));
            }
        });
        assertNotNull(terminalServiceResource.getGroupMgtResource());
        assertEquals(someData, terminalServiceResource.getGroupMgtResource().getGroupDetails(groupName));
    }

    @Test
    public void testGetEventAnalysisServiceResource() {
        final EventAnalysisResource mockEventAnalysisResource = mockery.mock(EventAnalysisResource.class);

        terminalServiceResource.eventAnalysisResource = mockEventAnalysisResource;

        assertNotNull(terminalServiceResource.getEventAnalysisResource());
    }

    @Test
    public void testGetKPIResource() {
        final KPIResource mockKpiResource = mockery.mock(KPIResource.class);

        terminalServiceResource.kpiResource = mockKpiResource;

        assertNotNull(terminalServiceResource.getKpiResource());
    }

    @Test
    public void testGetRankingResource() {
        final MultipleRankingResource mockMultipleRankingResource = mockery.mock(MultipleRankingResource.class);

        terminalServiceResource.multipleRankingResource = mockMultipleRankingResource;

        assertNotNull(terminalServiceResource.getMultipleRankingResource());
    }

    @Test
    public void testGetGroupAnalysisResource() {
        final TerminalAndGroupAnalysisResource mockTerminalAndGroupAnalysisResource = mockery
                .mock(TerminalAndGroupAnalysisResource.class);

        terminalServiceResource.terminalAndGroupAnalysisResource = mockTerminalAndGroupAnalysisResource;

        assertNotNull(terminalServiceResource.getGroupAnalysisResource());
    }

    @Test
    public void testGetTerminalAnalysisResource() {
        final TerminalAndGroupAnalysisResource mockTerminalAndGroupAnalysisResource = mockery
                .mock(TerminalAndGroupAnalysisResource.class);

        terminalServiceResource.terminalAndGroupAnalysisResource = mockTerminalAndGroupAnalysisResource;

        assertNotNull(terminalServiceResource.getTerminalAnalysisResource());
    }

    @Test
    public void testGetDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        terminalServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(terminalServiceResource.getDatavolRankingResource());
    }

    @Test
    public void testGetGroupDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        terminalServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(terminalServiceResource.getDatavolGroupRankingResource());
    }

    @Test
    public void testGetDataVolumeResource() {
        final DataVolumeAnalysisResource mockDataVolumeAnalysisResource = mockery
                .mock(DataVolumeAnalysisResource.class);

        terminalServiceResource.dataVolumeAnalysisResource = mockDataVolumeAnalysisResource;

        assertNotNull(terminalServiceResource.getDataVolumeAnalysisResource());
    }
}
