/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.detail;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserImsiRanCfaDetailTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserMsisdnDetailTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRanCfaDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRanCfaDetailService")
    private SBrowserRanCfaDetailService sBrowserRanCfaDetailService;

    @Test
    @Parameters(source = SBrowserImsiRanCfaDetailTestDataProvider.class)
    public void testSessionCfaImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRanCfaDetailService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnDetailTestDataProvider.class)
    public void testSessionCfaMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRanCfaDetailService);
    }

}
