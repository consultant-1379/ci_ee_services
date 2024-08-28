/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.TerminalEventAnalysisDataProvider;

/**
 * @author eflatib
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class TerminalEventAnalysisIntegrationTest extends ServiceBaseTest {
    @Resource(name = "terminalEventAnalysisService")
    private TerminalEventAnalysisService terminalEventAnalysisService;

    @Test
    @Parameters(source = TerminalEventAnalysisDataProvider.class)
    public void testCallDropGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalEventAnalysisService);
    }

}
