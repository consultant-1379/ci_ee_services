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
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFAEnodeBGroupQCICategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.qos.LTECFAEnodeBQCICategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFAEnodeBQCICategorySummaryService;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFAEnodeBQCICategorySummaryIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureEnodeBQCICategorySummaryService")
    private LTECFAEnodeBQCICategorySummaryService lteCallFailureEnodeBQCICategorySummaryService;

    @Test
    @Parameters(source = LTECFAEnodeBQCICategorySummaryTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBQCICategorySummaryService);
    }

    @Test
    @Parameters(source = LTECFAEnodeBGroupQCICategorySummaryTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureEnodeBQCICategorySummaryService);
    }
}
