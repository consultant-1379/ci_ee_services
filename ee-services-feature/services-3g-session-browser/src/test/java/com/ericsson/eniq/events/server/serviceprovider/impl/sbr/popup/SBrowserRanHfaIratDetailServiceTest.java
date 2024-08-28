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

import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserImsiPopupHfaIRatTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserMsisdnPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author eemecoy
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRanHfaIratDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRanHfaIratPopupService")
    private SBrowserRanHfaIratDetailService sessionBrowserRanHfaIratPopupService;

    @Test
    @Parameters(source = SBrowserImsiPopupHfaIRatTestDataProvider.class)
    public void testQueryIMSI(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserRanHfaIratPopupService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnPopupTestDataProvider.class)
    public void testQueryMSISDN(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserRanHfaIratPopupService);
    }

}
