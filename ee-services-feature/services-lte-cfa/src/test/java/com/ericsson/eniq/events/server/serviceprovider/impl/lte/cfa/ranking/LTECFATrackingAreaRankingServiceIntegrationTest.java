/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFATerminalRankingDataProvider;
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
public class LTECFATrackingAreaRankingServiceIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureTrackingAreaCallDropRankingService")
    private LTECFATrackingAreaCallDropRankingService lteCallFailureTrackingAreaCallDropRankingService;

    @Resource(name = "lteCallFailureTrackingAreaCallSetupRankingService")
    private LTECFATrackingAreaCallSetupRankingService lteCallFailureTrackingAreaCallSetupRankingService;

    @Test
    @Parameters(source = LTECFATerminalRankingDataProvider.class)
    public void testCallDropGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCallDropRankingService);
    }

    @Test
    @Parameters(source = LTECFATerminalRankingDataProvider.class)
    public void testCallSetupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureTrackingAreaCallSetupRankingService);
    }
}
