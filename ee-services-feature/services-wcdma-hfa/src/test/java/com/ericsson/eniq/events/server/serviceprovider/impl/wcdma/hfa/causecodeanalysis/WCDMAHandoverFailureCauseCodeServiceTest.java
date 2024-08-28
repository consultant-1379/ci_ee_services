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

import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.HandoverFailureCauseCodeDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureCauseCodeService;

/**
 * @author eromsza
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-hfa-service-context.xml" })
public class WCDMAHandoverFailureCauseCodeServiceTest extends ServiceBaseTest {
    @Resource(name = "wcdmaHandoverFailureCauseCodeService")
    private WCDMAHandoverFailureCauseCodeService wcdmaHandoverFailureControllerCauseCodeService;

    @Test
    @Parameters(source = HandoverFailureCauseCodeDataProvider.class)
    public void testWCDMAHandoverFailureControllerCauseCodeAnalysis(
            final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, wcdmaHandoverFailureControllerCauseCodeService);
    }
}
