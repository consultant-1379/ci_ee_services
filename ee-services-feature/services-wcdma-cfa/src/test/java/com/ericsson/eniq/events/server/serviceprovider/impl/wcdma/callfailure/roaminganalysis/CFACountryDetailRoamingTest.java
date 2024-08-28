/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.roaminganalysis;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.MCCEventIDDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author ezhelao
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CFACountryDetailRoamingTest extends ServiceBaseTest {

    @Resource(name = "callFailureCountryDrillDetailRoamingService")
    private Service callFailureCountryDrillDetailRoamingService;

    @Test
    @Parameters(source = MCCEventIDDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, callFailureCountryDrillDetailRoamingService);
    }

}
