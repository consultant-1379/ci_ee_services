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

import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserImsiPopupHfaIfhoTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserMsisdnPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author eemecoy
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRanHfaIfhoDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRanHfaIfhoDetailService")
    private SBrowserRanHfaIfhoDetailService sessionBrowserIRATPopupService;

    @Test
    @Parameters(source = SBrowserImsiPopupHfaIfhoTestDataProvider.class)
    public void testQuery(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserIRATPopupService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnPopupTestDataProvider.class)
    public void testQueryForMSISDN(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserIRATPopupService);
    }

}
