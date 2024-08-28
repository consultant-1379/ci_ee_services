/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.causecodeanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAGroupCauseCodeDetailedAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATracCCPieDrilldownTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATracGroupCCPieDrilldownTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaCCDetailedTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author echchik
 * @since 2011
 */

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:lte-cfa-service-context.xml" })
public class LTECFATrackingAreaCCDetailedIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService")
    private LTECFATrackingAreaCCDetailedEAService lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService;

    @Test
    @Parameters(source = LTECFATrackingAreaCCDetailedTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = LTECFAGroupCauseCodeDetailedAnalysisTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = LTECFATracCCPieDrilldownTestDataProvider.class)
    public void testGetDataPieDrilldown(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = LTECFATracGroupCCPieDrilldownTestDataProvider.class)
    public void testGroupGetDataPieDrilldown(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService);
    }
}
