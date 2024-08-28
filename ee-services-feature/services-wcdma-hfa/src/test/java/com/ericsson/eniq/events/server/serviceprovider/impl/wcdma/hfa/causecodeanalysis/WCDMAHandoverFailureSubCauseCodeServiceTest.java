/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecodeanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.HandoverFailureSubCauseCodeDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureSubCauseCodeService;

/**
 * @author eromsza
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-hfa-service-context.xml" })
public class WCDMAHandoverFailureSubCauseCodeServiceTest extends ServiceBaseTest {
    @Resource(name = "wcdmaHandoverFailureSubCauseCodeService")
    private WCDMAHandoverFailureSubCauseCodeService wcdmaHandoverFailureControllerSubCauseCodeService;

    @Test
    @Parameters(source = HandoverFailureSubCauseCodeDataProvider.class)
    public void testWCDMAHandoverFailureControllerSubCauseCodeAnalysis(
            final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, wcdmaHandoverFailureControllerSubCauseCodeService);
    }
}