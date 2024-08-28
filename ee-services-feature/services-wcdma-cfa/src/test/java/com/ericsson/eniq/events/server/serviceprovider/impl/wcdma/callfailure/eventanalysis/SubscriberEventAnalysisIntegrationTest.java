/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import java.net.URISyntaxException;

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
 * @author EBROWPA
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class SubscriberEventAnalysisIntegrationTest extends ServiceBaseTest {

    @Resource(name = "subscriberEventAnalysisService")
    private SubscriberEventAnalysisService service;

    @Test
    @Parameters(source = SubscriberEventAnalysisDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) throws URISyntaxException {
        final String result = runQuery(requestParameters, service);
        jsonAssertUtils.assertJSONSucceeds(result);
        System.out.println(result);
    }

    @Test
    @Parameters(source = SubscriberEventAnalysisDataProvider.class)
    public void testGetDataAsCSV(final MultivaluedMap<String, String> requestParameters) throws URISyntaxException {
        runQueryForCSV(requestParameters, service);
    }
}
