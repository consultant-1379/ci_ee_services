/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFACauseCodeRankingDataProvider;
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
public class LTECFACauseCodeRankingServiceIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureCauseCodeCallDropRankingService")
    private LTECFACauseCodeCallDropRankingService lteCallFailureCauseCodeCallDropRankingService;

    @Resource(name = "lteCallFailureCauseCodeCallSetupRankingService")
    private LTECFACauseCodeCallSetupRankingService lteCallFailureCauseCodeCallSetupRankingService;

    @Test
    @Parameters(source = LTECFACauseCodeRankingDataProvider.class)
    public void testCauseCodeCallDropRankingGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureCauseCodeCallDropRankingService);
    }

    @Test
    @Parameters(source = LTECFACauseCodeRankingDataProvider.class)
    public void testCauseCodeCallSetupRankingGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureCauseCodeCallSetupRankingService);
    }
}
