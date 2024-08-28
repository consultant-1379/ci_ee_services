/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.userplane;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.sbr.kpi.userplane.SBrowserRncUkpiDrillTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRncUkpiDrillServiceTest extends ServiceBaseTest {
    @Resource(name = "sBrowserRncUkpiDrillService")
    private SBrowserRncUkpiDrillService sBrowserRncUkpiDrillService;

    @Test
    @Parameters(source = SBrowserRncUkpiDrillTestDataProvider.class)
    public void testRncUkpi(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, sBrowserRncUkpiDrillService);
    }

}
