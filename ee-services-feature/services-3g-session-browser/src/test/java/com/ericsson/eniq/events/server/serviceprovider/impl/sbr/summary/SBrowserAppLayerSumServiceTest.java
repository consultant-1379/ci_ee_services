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
public class SBrowserAppLayerSumServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserAppLayerSumService")
    private SBrowserAppLayerSumService sBrowserAppLayerSumService;

    @Test
    @Parameters(source = SBrowserImsiRabSumTestDataProvider.class)
    public void testSessionApplayerSumImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserAppLayerSumService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnRabSumTestDataProvider.class)
    public void testSessionAppLayerSumMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserAppLayerSumService);
    }
}
