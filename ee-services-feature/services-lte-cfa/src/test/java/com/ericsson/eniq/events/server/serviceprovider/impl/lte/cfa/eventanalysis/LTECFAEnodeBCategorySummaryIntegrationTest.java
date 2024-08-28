/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAEnodeBCategorySummaryRankingDrillTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAEnodeBCategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAEnodeBGroupCategorySummaryTestDataProvider;
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
public class LTECFAEnodeBCategorySummaryIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCallFailureEnodeBCategorySummaryService")
    private LTECFAEnodeBCategorySummaryService lteCallFailureEnodeBCategorySummaryService;

    @Test
    @Parameters(source = LTECFAEnodeBCategorySummaryRankingDrillTestDataProvider.class)
    public void testGetDataRankingDrill(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBCategorySummaryService);
    }

    @Test
    @Parameters(source = LTECFAEnodeBCategorySummaryTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBCategorySummaryService);
    }

    @Test
    @Parameters(source = LTECFAEnodeBGroupCategorySummaryTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBCategorySummaryService);
    }
}
