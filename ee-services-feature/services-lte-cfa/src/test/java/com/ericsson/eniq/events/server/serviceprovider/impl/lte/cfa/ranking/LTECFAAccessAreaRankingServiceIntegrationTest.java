/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAAccessAreaRankingDataProvider;
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
public class LTECFAAccessAreaRankingServiceIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureAccessAreaCallDropRankingService")
    private LTECFAAccessAreaCallDropRankingService lteCallFailureAccessAreaCallDropRankingService;

    @Resource(name = "lteCallFailureAccessAreaCallSetupRankingService")
    private LTECFAAccessAreaCallSetupRankingService lteCallFailureAccessAreaCallSetupRankingService;

    @Test
    @Parameters(source = LTECFAAccessAreaRankingDataProvider.class)
    public void testAccessAreaCallDropRankingGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureAccessAreaCallDropRankingService);
    }

    @Test
    @Parameters(source = LTECFAAccessAreaRankingDataProvider.class)
    public void testAccessAreaCallSetupFailureRankingGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureAccessAreaCallSetupRankingService);
    }
}
