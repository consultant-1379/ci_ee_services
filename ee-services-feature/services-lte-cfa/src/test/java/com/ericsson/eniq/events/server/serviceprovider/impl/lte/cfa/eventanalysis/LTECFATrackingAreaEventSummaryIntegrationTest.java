/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaEventSummaryTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaGroupEventSummaryTestDataProvider;
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
public class LTECFATrackingAreaEventSummaryIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCallFailureTrackingAreaEventSummaryService")
    private LTECFATrackingAreaEventSummaryService lteCallFailureTrackingAreaEventSummaryService;

    @Test
    @Parameters(source = LTECFATrackingAreaEventSummaryTestDataProvider.class)
    public void testTrackingAreaEventSummaryAnalysisGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaEventSummaryService);
    }

    @Test
    @Parameters(source = LTECFATrackingAreaGroupEventSummaryTestDataProvider.class)
    public void testTrackingAreaGroupEventSummaryAnalysisGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaEventSummaryService);
    }
}
