/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.causecodeanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaCategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATrackingAreaGroupCategorySummaryTestDataProvider;
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
public class LTECFATrackingAreaCCIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCallFailureTrackingAreaCauseCodeEventSummaryService")
    private LTECFATrackingAreaCCEventSummaryService lteCallFailureTrackingAreaCauseCodeEventSummaryService;

    @Test
    @Parameters(source = LTECFATrackingAreaCategorySummaryTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCauseCodeEventSummaryService);
    }

    @Test
    @Parameters(source = LTECFATrackingAreaGroupCategorySummaryTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCauseCodeEventSummaryService);
    }
}
