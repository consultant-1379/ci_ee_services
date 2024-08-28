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

import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserImsiRanHfaDetailTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserMsisdnDetailTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRanHfaDetailServiceTest extends ServiceBaseTest {

    @Resource(name = "sessionBrowserRanHfaDetailService")
    private SBrowserRanHfaDetailService sBrowserRanHfaDetailService;

    @Test
    @Parameters(source = SBrowserImsiRanHfaDetailTestDataProvider.class)
    public void testSessionHfaImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRanHfaDetailService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnDetailTestDataProvider.class)
    public void testSessionHfaMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRanHfaDetailService);
    }
}
