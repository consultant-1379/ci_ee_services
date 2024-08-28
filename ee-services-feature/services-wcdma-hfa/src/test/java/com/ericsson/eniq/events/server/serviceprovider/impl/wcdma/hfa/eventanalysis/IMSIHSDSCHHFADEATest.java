/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.IMSIGroupHSDSCHHFADEADataProvider;
import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.IMSIHSDSCHHFADEADataProvider;
import org.springframework.test.context.ContextConfiguration;
/**
 * @author eromsza
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:wcdma-hfa-service-context.xml"})
public class IMSIHSDSCHHFADEATest extends ServiceBaseTest {

    @Resource(name = "imsiHSDSCHHandoverFailureDetailedEventAnalysisService")
    private IMSIHSDSCHHandoverFailureDetailedEAService imsiHSDSCHHandoverFailureDetailedEventAnalysisService;

    @Test
    @Parameters(source = IMSIHSDSCHHFADEADataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, imsiHSDSCHHandoverFailureDetailedEventAnalysisService);

        System.out.println(result);
    }

    @Test
    @Parameters(source = IMSIHSDSCHHFADEADataProvider.class)
    public void testGetData_Drilldown_CSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, imsiHSDSCHHandoverFailureDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = IMSIGroupHSDSCHHFADEADataProvider.class)
    public void testGetGroupData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, imsiHSDSCHHandoverFailureDetailedEventAnalysisService);

        System.out.println(result);
    }

    @Test
    @Parameters(source = IMSIGroupHSDSCHHFADEADataProvider.class)
    public void testGetGroupData_Drilldown_CSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, imsiHSDSCHHandoverFailureDetailedEventAnalysisService);
    }
}
