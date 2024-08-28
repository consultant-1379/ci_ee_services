/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserImsiRrcPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRrcPopupDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRrcPopupDetailService")
    private SBrowserRrcPopupDetailService sBrowserRrcPopupDetailService;

    @Test
    @Parameters(source = SBrowserImsiRrcPopupTestDataProvider.class)
    public void testSessionRabImsiDetail(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRrcPopupDetailService);
    }

    //    @Test
    //    @Parameters(source = SBrowserMsisdnTestDataProvider.class)
    //    public void testSessionRabMsisdnDetail(final MultivaluedMap<String, String> requestParameters) {
    //        runQuery(requestParameters, sBrowserServerDistPopupService);
    //    }
}
