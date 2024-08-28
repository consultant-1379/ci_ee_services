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

import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.HandoverFailureCauseCodeTableDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureCauseCodeTableService;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureSubCauseCodeTableService;

/**
 * @author eromsza
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-hfa-service-context.xml" })
public class WCDMAHandoverFailureCauseCodeTableServiceTest extends ServiceBaseTest {
    @Resource(name = "wcdmaHandoverFailureCauseCodeTableService")
    private WCDMAHandoverFailureCauseCodeTableService wcdmaHandoverFailureControllerCauseCodeTableService;

    @Resource(name = "wcdmaHandoverFailureSubCauseCodeTableService")
    private WCDMAHandoverFailureSubCauseCodeTableService wcdmaHandoverFailureControllerSubCauseCodeTableService;

    @Test
    @Parameters(source = HandoverFailureCauseCodeTableDataProvider.class)
    public void testWCDMAHandoverFailureControllerCauseCodeTableAnalysis(
            final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, wcdmaHandoverFailureControllerCauseCodeTableService);
    }

    @Test
    @Parameters(source = HandoverFailureCauseCodeTableDataProvider.class)
    public void testWCDMAHandoverFailureControllerSubCauseCodeTableAnalysis(
            final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, wcdmaHandoverFailureControllerSubCauseCodeTableService);
    }
}
