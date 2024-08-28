/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAEnodeBDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAGroupDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author echchik
 * @since 2011
 */

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFAEnodeBDetailedEventAnalysisIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCallFailureEnodeBDetailedEventAnalysisService")
    private LTECFAEnodeBDetailedEventAnalysisService lteCallFailureEnodeBDetailedEventAnalysisService;

    @Test
    @Parameters(source = LTECFAEnodeBDetailedEventAnalysisTestDataProvider.class)
    public void testCallSetupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = LTECFAGroupDetailedEventAnalysisTestDataProvider.class)
    public void testCallSetupGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBDetailedEventAnalysisService);
    }
}
