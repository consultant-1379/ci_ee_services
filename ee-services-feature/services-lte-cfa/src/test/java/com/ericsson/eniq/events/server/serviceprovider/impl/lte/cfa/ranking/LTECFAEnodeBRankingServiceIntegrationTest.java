/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFAEnodeBRankingDataProvider;
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
public class LTECFAEnodeBRankingServiceIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureEnodeBCallDropRankingService")
    private LTECFAEnodeBCallDropRankingService lteCallFailureEnodeBCallDropRankingService;

    @Resource(name = "lteCallFailureEnodeBCallSetupRankingService")
    private LTECFAEnodeBCallSetupRankingService lteCallFailureEnodeBCallSetupRankingService;

    @Test
    @Parameters(source = LTECFAEnodeBRankingDataProvider.class)
    public void testENodeBCallDropRankingGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBCallDropRankingService);
    }

    @Test
    @Parameters(source = LTECFAEnodeBRankingDataProvider.class)
    public void testENodeBCallSetupFailureRankingGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBCallSetupRankingService);
    }

}
