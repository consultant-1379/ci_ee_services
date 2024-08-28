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
import com.ericsson.eniq.events.server.serviceprovider.impl.dataproviders.KPINotificationCountDataProvider;

/**
 * @author eemecoy
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:kpi-notification-context.xml" })
public class KPINotificationCountIntegrationTest extends ServiceBaseTest {

    @Resource(name = "kpiNotificationCountService")
    private KPINotificationCountService kpiNotificationCountService;

    @Test
    @Parameters(source = KPINotificationCountDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, kpiNotificationCountService);
    }

}
