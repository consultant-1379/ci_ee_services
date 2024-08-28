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

import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserImsiPopupHfaSohoTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserMsisdnPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author eemecoy
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRanHfaSohoDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRanHfaSohoDetailService")
    private SBrowserRanHfaSohoDetailService sessionBrowserRanHfaSohoDetailService;

    @Test
    @Parameters(source = SBrowserImsiPopupHfaSohoTestDataProvider.class)
    public void testQueryIMSI(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserRanHfaSohoDetailService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnPopupTestDataProvider.class)
    public void testQueryMSISDN(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserRanHfaSohoDetailService);
    }
}
