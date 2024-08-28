/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaGroupDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author echimma
 * @since 2011
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFATracAreaDetailedEventAnalysisIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCallFailureTrackingAreaDetailedEventAnalysisService")
    private LTECFATrackingAreaDetailedEAService lteCallFailureTrackingAreaDetailedEAService;

    @Test
    @Parameters(source = LTECFATrackingAreaDetailedEventAnalysisTestDataProvider.class)
    public void testTrackingAreaDetailedEventAnalysisGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaDetailedEAService);
    }

    @Test
    @Parameters(source = LTECFATrackingAreaGroupDetailedEventAnalysisTestDataProvider.class)
    public void testTrackingAreaGroupDetailedEventAnalysisGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaDetailedEAService);
    }
}
