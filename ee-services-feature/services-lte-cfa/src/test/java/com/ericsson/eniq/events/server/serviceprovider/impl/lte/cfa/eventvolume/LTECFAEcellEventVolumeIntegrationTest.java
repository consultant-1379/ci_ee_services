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

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.eventvolume.LTECFAEcellEventVolumeTestDataProvider;
import com.ericsson.eniq.events.server.dataproviders.lte.cfa.eventvolume.LTECFAEcellGroupEventVolumeTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:lte-cfa-service-context.xml" })
public class LTECFAEcellEventVolumeIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCFAEcellEventVolumeService")
    private LTECFAEcellEventVolumeService lteCFAEcellEventVolumeService;

    @Test
    @Parameters(source = LTECFAEcellEventVolumeTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCFAEcellEventVolumeService);
    }

    @Test
    @Parameters(source = LTECFAEcellGroupEventVolumeTestDataProvider.class)
    public void testGroupGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCFAEcellEventVolumeService);
    }
}
