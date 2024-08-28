/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFASubscriberCategorySummaryTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFASubscriberGroupCategorySummaryTestDataProvider;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author echchik
 * @since 2011
 *
 */

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFASubscriberCatSummaryIntegrationTest extends ServiceBaseTest {


    @Resource(name = "lteCallFailureSubscriberCategorySummaryService")
    private LTECFASubscriberCategorySummaryService lteCallFailureSubscriberCategorySummaryService;

    @Test
    @Parameters(source = LTECFASubscriberCategorySummaryTestDataProvider.class)
    public void testCallSetupGetData(final MultivaluedMap<String, String> requestParameters){
        runQuery(requestParameters, lteCallFailureSubscriberCategorySummaryService);
    }
    
    @Test
    @Parameters(source = LTECFASubscriberGroupCategorySummaryTestDataProvider.class)
    public void testCallSetupGroupGetData(final MultivaluedMap<String, String> requestParameters){
        runQuery(requestParameters, lteCallFailureSubscriberCategorySummaryService);
    }
}
