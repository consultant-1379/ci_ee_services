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

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.SBrowserCellKpiDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author eemecoy
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserCellUkpiServiceTest extends ServiceBaseTest {
    @Resource(name = "sBrowserCellUkpiService")
    private SBrowserCellUkpiService sBrowserCellUkpiService;

    @Test
    @Parameters(source = SBrowserCellKpiDataProvider.class)
    public void testCellKPI(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserCellUkpiService);
    }

}
