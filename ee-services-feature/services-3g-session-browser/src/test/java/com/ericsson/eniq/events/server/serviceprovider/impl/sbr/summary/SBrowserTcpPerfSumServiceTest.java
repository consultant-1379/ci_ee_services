/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.sbr.summary.SBrowserImsiRabSumTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.summary.SBrowserMsisdnRabSumTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserTcpPerfSumServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserTcpPerfSumService")
    private SBrowserTcpPerfSumService sBrowserTcpPerfSumService;

    @Test
    @Parameters(source = SBrowserImsiRabSumTestDataProvider.class)
    public void testSessionTcpPerfSumImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserTcpPerfSumService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnRabSumTestDataProvider.class)
    public void testSessionTcpPerfSumMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserTcpPerfSumService);
    }
}
