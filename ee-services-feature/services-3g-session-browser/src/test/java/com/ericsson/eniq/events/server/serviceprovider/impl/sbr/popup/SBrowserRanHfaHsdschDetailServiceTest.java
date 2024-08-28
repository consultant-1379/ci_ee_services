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

import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserImsiPopupHsdschErrTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.sbr.popup.SBrowserMsisdnPopupTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author eemecoy
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRanHfaHsdschDetailServiceTest extends ServiceBaseTest {
    @Resource(name = "sessionBrowserRanHfaHsdschDetailService")
    private SBrowserRanHfaHsdschDetailService sessionBrowserRanHfaHsdschDetailService;

    @Test
    @Parameters(source = SBrowserImsiPopupHsdschErrTestDataProvider.class)
    public void testQueryIMSI(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserRanHfaHsdschDetailService);
    }

    @Test
    @Parameters(source = SBrowserMsisdnPopupTestDataProvider.class)
    public void testQueryMSISDN(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sessionBrowserRanHfaHsdschDetailService);
    }

}
