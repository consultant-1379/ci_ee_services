/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CallFailureAccessAreaEventSummaryDataProvider;

/**
 * @author etonayr
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class AccessAreaCallFailureEventSummaryIntegrationTest extends ServiceBaseTest {

    @Resource(name = "accessAreaCallFailureEventSummaryService")
    private AccessAreaCallFailureEventSummaryService service;

    @Test
    @Parameters(source = CallFailureAccessAreaEventSummaryDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, service);
        jsonAssertUtils.assertJSONSucceeds(result);
        System.out.println(result);
    }

    @Test
    @Parameters(source = CallFailureAccessAreaEventSummaryDataProvider.class)
    public void testGetDataAsCSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, service);
    }

}
