/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.subbi;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.SubscriberEventAnalysisDataProvider;

/**
 * @author eemecoy
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CallFailureSubscriberDetailsIntegrationTest extends ServiceBaseTest {

    @Resource(name = "callFailureSubscriberDetailsService")
    private CallFailureSubscriberDetailsService callFailureSubscriberDetailsService;

    @Test
    @Parameters(source = SubscriberEventAnalysisDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, callFailureSubscriberDetailsService);
    }

}
