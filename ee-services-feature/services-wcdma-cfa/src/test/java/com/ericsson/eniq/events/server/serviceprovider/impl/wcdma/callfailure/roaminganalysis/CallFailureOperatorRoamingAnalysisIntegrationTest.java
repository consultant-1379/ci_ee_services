/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.roaminganalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.BasicDataProvider;

/**
 * @author eemecoy
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CallFailureOperatorRoamingAnalysisIntegrationTest extends ServiceBaseTest {

    @Resource(name = "callFailureOperatorRoamingAnalysisService")
    private CallFailureOperatorRoamingAnalysisService operatorRoamingAnalysisService;

    @Test
    @Parameters(source = BasicDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, operatorRoamingAnalysisService);
    }

}
