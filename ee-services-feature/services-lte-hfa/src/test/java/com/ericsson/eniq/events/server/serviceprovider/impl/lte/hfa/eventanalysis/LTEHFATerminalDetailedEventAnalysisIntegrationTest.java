/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.LTEHFAGroupDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.handoverfailure.TerminalDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author evaraks
 * @since 2012
 */

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-hfa-service-context.xml"})
public class LTEHFATerminalDetailedEventAnalysisIntegrationTest extends ServiceBaseTest {

    @Resource(name = "terminalDetailedEAService")
    private TerminalDetailedEAService terminalDetailedEAService;

    @Test
    @Parameters(source = TerminalDetailedEventAnalysisTestDataProvider.class)
    public void testHandoverGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalDetailedEAService);
    }

   @Test
    @Parameters(source = LTEHFAGroupDetailedEventAnalysisTestDataProvider.class)
    public void testHandoverGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalDetailedEAService);
    }
}
