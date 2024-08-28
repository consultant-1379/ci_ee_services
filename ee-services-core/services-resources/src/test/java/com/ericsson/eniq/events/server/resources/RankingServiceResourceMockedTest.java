/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.dtput.DatavolRankingResource;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 *
 */
public class RankingServiceResourceMockedTest extends BaseJMockUnitTest {
    private RankingServiceResource rankingServiceResource;

    @Before
    public void setUp() {

        rankingServiceResource = new RankingServiceResource();

        new MultivaluedMapImpl();
    }

    @Test
    public void testGetEventAnalysisServiceResource() {
        final EventAnalysisResource mockEventAnalysisResource = mockery.mock(EventAnalysisResource.class);

        rankingServiceResource.eventAnalysisResource = mockEventAnalysisResource;

        assertNotNull(rankingServiceResource.getEventAnalysisResource());
    }

    @Test
    public void testGetKPIResource() {
        final KPIResource mockKpiResource = mockery.mock(KPIResource.class);

        rankingServiceResource.kpiResource = mockKpiResource;

        assertNotNull(rankingServiceResource.getKpiResource());
    }

    @Test
    public void testGetRankingResource() {
        final MultipleRankingResource mockMultipleRankingResource = mockery.mock(MultipleRankingResource.class);

        rankingServiceResource.multipleRankingResource = mockMultipleRankingResource;

        assertNotNull(rankingServiceResource.getMultipleRankingResource());
    }

    @Test
    public void testGetDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        rankingServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(rankingServiceResource.getDatavolRankingResource());
    }

    @Test
    public void testGetGroupDataVolumeRankingResource() {
        final DatavolRankingResource mockDatavolRankingResource = mockery.mock(DatavolRankingResource.class);

        rankingServiceResource.datavolRankingResource = mockDatavolRankingResource;

        assertNotNull(rankingServiceResource.getDatavolGroupRankingResource());
    }
}
