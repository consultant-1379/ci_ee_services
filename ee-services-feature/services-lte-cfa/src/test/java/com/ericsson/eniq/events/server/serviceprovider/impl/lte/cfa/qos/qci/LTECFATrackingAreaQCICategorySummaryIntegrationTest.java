/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qci;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFATracAreaGrpQCICategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFATrackingAreaQCICategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFATrackingAreaQCICategorySummaryService;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFATrackingAreaQCICategorySummaryIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureTrackingAreaQCICategorySummaryService")
    private LTECFATrackingAreaQCICategorySummaryService lteCallFailureTrackingAreaQCICategorySummaryService;

    @Test
    @Parameters(source = LTECFATrackingAreaQCICategorySummaryTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaQCICategorySummaryService);
    }

    @Test
    @Parameters(source = LTECFATracAreaGrpQCICategorySummaryTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaQCICategorySummaryService);
    }
}
