/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventvolume;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.eventvolume.LTECFANetworkEventVolumeTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:lte-cfa-service-context.xml" })
public class LTECFANetworkEventVolumeIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCFANetworkEventVolumeService")
    private LTECFANetworkEventVolumeService lteCFANetworkEventVolumeService;

    @Test
    @Parameters(source = LTECFANetworkEventVolumeTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCFANetworkEventVolumeService);
    }
}
