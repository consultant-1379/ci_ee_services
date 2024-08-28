/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.RNCAndAccessAreaRankingDataProvider;

/**
 * @author ehaoswa
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class RNCCallFailureRankingServiceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "rncCallFailureRankingService")
    private RNCCallFailureRankingService callFailureRankingService;

    @Test
    @Parameters(source = RNCAndAccessAreaRankingDataProvider.class)
    public void testData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, callFailureRankingService);
    }
}
