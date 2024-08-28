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

import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.HandoverFailureCauseCodeListDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureCauseCodeListService;

/**
 * @author eromsza
 * @since 2012
 *
 */

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-hfa-service-context.xml" })
public class WCDMAHandoverFailureCauseCodeListServiceTest extends ServiceBaseTest {

    @Resource(name = "wcdmaHandoverFailureCauseCodeListService")
    private WCDMAHandoverFailureCauseCodeListService wcdmaHandoverFailureControllerCauseCodeListService;

    @Test
    @Parameters(source = HandoverFailureCauseCodeListDataProvider.class)
    public void testWCDMAHandoverFailureControllerCauseCodeList(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, wcdmaHandoverFailureControllerCauseCodeListService);
    }
}
