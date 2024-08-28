/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.userplane;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.userplane.SBrowserCellUKpiDrillTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCellUkpiDrillServiceTest extends ServiceBaseTest {
    @Resource(name = "sBrowserCellUkpiDrillService")
    private SBrowserCellUkpiDrillService sBrowserCellUkpiDrillService;

    @Test
    @Parameters(source = SBrowserCellUKpiDrillTestDataProvider.class)
    public void test(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCellUkpiDrillService);
    }

}
