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

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.SBrowserRncKpiTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ejoegaf
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCoreRncKPIServiceTest extends ServiceBaseTest {
    @Resource(name = "sBrowserRncCoreKpiService")
    private SBrowserCoreRncKpiService sBrowserCoreRncKpiService;

    @Test
    @Parameters(source = SBrowserRncKpiTestDataProvider.class)
    public void testCoreKPIRNC(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCoreRncKpiService);
    }

}
