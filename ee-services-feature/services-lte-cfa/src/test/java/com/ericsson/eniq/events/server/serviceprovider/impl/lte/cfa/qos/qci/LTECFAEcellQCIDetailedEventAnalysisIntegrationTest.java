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
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFAEcellGroupQCIDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFAEcellQCIDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFAECellQCIDetailedEventAnalysisService;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFAEcellQCIDetailedEventAnalysisIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureECellQCIDetailedEventAnalysisService")
    private LTECFAECellQCIDetailedEventAnalysisService lteCallFailureECellQCIDetailedEventAnalysisService;

    @Test
    @Parameters(source = LTECFAEcellQCIDetailedEventAnalysisTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureECellQCIDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = LTECFAEcellGroupQCIDetailedEventAnalysisTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureECellQCIDetailedEventAnalysisService);
    }
}
