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
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFAEnodeBGroupQCIDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFAEnodeBQCIDetailedEventAnalysisTestDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFAEnodeBQCIDetailedEventAnalysisService;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFAEnodeBQCIDetailedEventAnalysisIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureEnodeBQCIDetailedEventAnalysisService")
    private LTECFAEnodeBQCIDetailedEventAnalysisService LTECFAEnodeBQCIDetailedEventAnalysisService;

    @Test
    @Parameters(source = LTECFAEnodeBQCIDetailedEventAnalysisTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, LTECFAEnodeBQCIDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = LTECFAEnodeBGroupQCIDetailedEventAnalysisTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, LTECFAEnodeBQCIDetailedEventAnalysisService);
    }
}
