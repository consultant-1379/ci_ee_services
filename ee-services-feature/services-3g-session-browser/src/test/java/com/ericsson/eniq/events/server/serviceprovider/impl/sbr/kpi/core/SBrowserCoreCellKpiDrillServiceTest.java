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

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.core.SBrowserCellCoreKpiDrillTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCoreCellKpiDrillServiceTest extends ServiceBaseTest {
    @Resource(name = "sBrowserCoreCellKpiDrillService")
    private SBrowserCoreCellKpiDrillService sBrowserCoreCellKpiDrillService;

    @Test
    @Parameters(source = SBrowserCellCoreKpiDrillTestDataProvider.class)
    public void testSessionCellKpiDrill(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreCellKpiDrillService);
    }
}
