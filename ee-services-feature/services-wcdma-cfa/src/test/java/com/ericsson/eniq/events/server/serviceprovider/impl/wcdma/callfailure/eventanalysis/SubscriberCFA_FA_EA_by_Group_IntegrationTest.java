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
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.SubscriberCFA_FA_EA_Group_CallDropDataProvider;

/**
 * @author eflatib
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class SubscriberCFA_FA_EA_by_Group_IntegrationTest extends ServiceBaseTest {
    @Resource(name = "subscriberCallFailureFAforEAByImsiGroupService")
    private SubscriberCallFailureFAforEAByImsiGroupService subscriberCallFailureFAforEAByImsiGroupService;

    @Test
    @Parameters(source = SubscriberCFA_FA_EA_Group_CallDropDataProvider.class)
    public void testCallDropGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, subscriberCallFailureFAforEAByImsiGroupService);
    }

}
