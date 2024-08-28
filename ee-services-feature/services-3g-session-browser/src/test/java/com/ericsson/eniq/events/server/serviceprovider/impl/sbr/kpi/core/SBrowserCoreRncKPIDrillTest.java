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

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.core.SBrowserRncCoreKpiDrillTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ejoegaf
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCoreRncKPIDrillTest extends ServiceBaseTest {
    @Resource(name = "sBrowserCoreRncKpiDrillService")
    private SBrowserCoreRncKpiDrillService sBrowserCoreRncKpiDrillService;

    @Test
    @Parameters(source = SBrowserRncCoreKpiDrillTestDataProvider.class)
    public void testRncKpiDrill(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreRncKpiDrillService);
    }

}
