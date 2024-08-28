/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventanalysis;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFASubscriberDetailedReoccuringFailureDataProvider;
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
public class LTECFASubscriberReoccuringFailureIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCallFailureSubscriberDetailedReoccuringFailureService")
    private LTECFASubscriberDetailedReoccFailureService lteCallFailureSubscriberDetailedReoccuringFailureService;

    @Test
    @Parameters(source = LTECFASubscriberDetailedReoccuringFailureDataProvider.class)
    public void testIMSIReoccuringFailureDrilldownGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCallFailureSubscriberDetailedReoccuringFailureService);
    }
}
