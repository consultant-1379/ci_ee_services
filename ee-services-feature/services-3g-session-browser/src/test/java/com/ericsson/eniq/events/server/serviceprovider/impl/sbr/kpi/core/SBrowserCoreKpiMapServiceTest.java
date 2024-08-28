/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.core;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.SBrowserCellKpiDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.SBrowserNetwkKpiTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.SBrowserRncKpiTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCoreKpiMapServiceTest extends ServiceBaseTest {
    @Resource(name = "sBrowserCoreKpiMapService")
    private SBrowserCoreKpiMapService sBrowserCoreKpiMapService;

    @Test
    @Parameters(source = SBrowserRncKpiTestDataProvider.class)
    public void testRncKpiMap(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreKpiMapService);
    }

    @Test
    @Parameters(source = SBrowserNetwkKpiTestDataProvider.class)
    public void testNetworkKpiMap(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreKpiMapService);
    }

    @Test
    @Parameters(source = SBrowserCellKpiDataProvider.class)
    public void testCellKpiMap(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreKpiMapService);
    }
}
