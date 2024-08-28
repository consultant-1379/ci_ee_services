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

import com.ericsson.eniq.events.server.dataproviders.sbr.detail.SBrowserImsiRRCDetailTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRrcDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRrcSessionDetailService")
    private SBrowserRrcDetailService sBrowserRrcDetailService;

    @Test
    @Parameters(source = SBrowserImsiRRCDetailTestDataProvider.class)
    public void testSessionRrcImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRrcDetailService);
    }

    //    @Test
    //    @Parameters(source = SBrowserMsisdnTestDataProvider.class)
    //    public void testSessionRrcMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
    //        runQuery(requestParameters, sBrowserRrcDetailService);
    //    }

}
