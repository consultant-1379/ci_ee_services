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

import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserImsiRabDetailTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserMsisdnDetailTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRabDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRabDetailService")
    private SBrowserRabDetailService sBrowserRabDetailService;

    @Test
    @Parameters(source = SBrowserImsiRabDetailTestDataProvider.class)
    public void testSessionRabImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRabDetailService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnDetailTestDataProvider.class)
    public void testSessionRabMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRabDetailService);
    }

}
