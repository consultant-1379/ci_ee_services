/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.TargetCellHFARSDataProvider;
import org.springframework.test.context.ContextConfiguration;
/**
 * @author eromsza
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:wcdma-hfa-service-context.xml"})
public class TargetCellHFARSTest extends ServiceBaseTest {

    @Resource(name = "targetCellHandoverFailureRankingService")
    private TargetCellHandoverFailureRankingService targetCellHandoverFailureRankingService;

    @Test
    @Parameters(source = TargetCellHFARSDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        final String result = runQuery(requestParameters, targetCellHandoverFailureRankingService);
        System.out.println(result);
    }

    @Test
    @Parameters(source = TargetCellHFARSDataProvider.class)
    public void testGetData_Drilldown_CSV(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, targetCellHandoverFailureRankingService);
    }
}