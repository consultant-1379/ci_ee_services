/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.terminalanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CallFailureTerminalGroupAndTATestDataProvider;

/**
 * @author EEMECOY
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CallFailureTerminalAnalysisIntegrationTest extends ServiceBaseTest {

    @Resource(name = "callFailureTerminalAnalysisService")
    private CallFailureTerminalAnalysisService callFailureTerminalAnalysisService;

    @Test
    @Parameters(source = CallFailureTerminalGroupAndTATestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, callFailureTerminalAnalysisService);
        jsonAssertUtils.assertJSONSucceeds(result);
        System.out.println(result);
    }

    @Test
    @Parameters(source = CallFailureTerminalGroupAndTATestDataProvider.class)
    public void testGetDataAsCSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, callFailureTerminalAnalysisService);
    }

}
