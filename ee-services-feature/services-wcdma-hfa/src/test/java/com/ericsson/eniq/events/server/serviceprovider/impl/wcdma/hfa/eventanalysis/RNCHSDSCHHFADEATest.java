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
import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.RNCGroupHSDSCHHFADEADataProvider;
import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.RNCHSDSCHHFADEADataProvider;
import org.springframework.test.context.ContextConfiguration;
/**
 * @author eromsza
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:wcdma-hfa-service-context.xml"})
public class RNCHSDSCHHFADEATest extends ServiceBaseTest {

    @Resource(name = "rncHSDSCHHandoverFailureDetailedEventAnalysisService")
    private RNCHSDSCHHandoverFailureDetailedEAService rncHSDSCHHandoverFailureDetailedEventAnalysisService;

    @Test
    @Parameters(source = RNCHSDSCHHFADEADataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, rncHSDSCHHandoverFailureDetailedEventAnalysisService);

        System.out.println(result);
    }

    @Test
    @Parameters(source = RNCHSDSCHHFADEADataProvider.class)
    public void testGetData_Drilldown_CSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, rncHSDSCHHandoverFailureDetailedEventAnalysisService);
    }

    @Test
    @Parameters(source = RNCGroupHSDSCHHFADEADataProvider.class)
    public void testGetGroupData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, rncHSDSCHHandoverFailureDetailedEventAnalysisService);

        System.out.println(result);
    }

    @Test
    @Parameters(source = RNCGroupHSDSCHHFADEADataProvider.class)
    public void testGetGroupData_Drilldown_CSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, rncHSDSCHHandoverFailureDetailedEventAnalysisService);
    }
}
