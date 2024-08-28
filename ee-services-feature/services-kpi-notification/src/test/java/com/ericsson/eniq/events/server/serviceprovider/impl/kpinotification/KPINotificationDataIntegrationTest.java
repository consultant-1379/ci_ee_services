/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dataproviders.KPINotificationDataProvider;

/**
 * @author eemecoy
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:kpi-notification-context.xml" })
public class KPINotificationDataIntegrationTest extends ServiceBaseTest {

    @Resource(name = "kpiNotificationDataService")
    private KPINotificationDataService kpiNotificationDataService;

    @Test
    @Parameters(source = KPINotificationDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, kpiNotificationDataService);
    }

}
