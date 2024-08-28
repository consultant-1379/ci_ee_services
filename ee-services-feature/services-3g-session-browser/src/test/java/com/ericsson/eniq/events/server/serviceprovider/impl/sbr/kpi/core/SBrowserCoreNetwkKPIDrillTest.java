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

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.core.SBrowserNetwkKpiDrillTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCoreNetwkKPIDrillTest extends ServiceBaseTest {
    @Resource(name = "sBrowserCoreNetwkkpiDrillService")
    private SBrowserCoreNetwkKpiDrillService sBrowserCoreNetwkKpiDrillService;

    @Test
    @Parameters(source = SBrowserNetwkKpiDrillTestDataProvider.class)
    public void testNetwkKpiDrill(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreNetwkKpiDrillService);
    }

}
