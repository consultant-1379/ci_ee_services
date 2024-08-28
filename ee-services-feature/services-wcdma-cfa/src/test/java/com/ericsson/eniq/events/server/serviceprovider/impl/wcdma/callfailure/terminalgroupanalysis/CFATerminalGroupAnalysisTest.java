/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.terminalgroupanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CallFailureTerminalGroupAndTATestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.TerminalGroupDrilldownTestDataProvider;

/**
 * @author EEMECOY
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CFATerminalGroupAnalysisTest extends ServiceBaseTest {

    @Resource(name = "callFailureTerminalGroupAnalysisService")
    private CallFailureTerminalGroupAnalysisService callFailureTerminalGroupAnalysisService;

    @Test
    @Parameters(source = CallFailureTerminalGroupAndTATestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, callFailureTerminalGroupAnalysisService);
        System.out.println(result);
    }

    @Test
    @Parameters(source = TerminalGroupDrilldownTestDataProvider.class)
    public void testGetData_Drilldown(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, callFailureTerminalGroupAnalysisService);
        System.out.println(result);
    }

    @Test
    @Parameters(source = TerminalGroupDrilldownTestDataProvider.class)
    public void testGetData_Drilldown_CSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, callFailureTerminalGroupAnalysisService);
    }

}
