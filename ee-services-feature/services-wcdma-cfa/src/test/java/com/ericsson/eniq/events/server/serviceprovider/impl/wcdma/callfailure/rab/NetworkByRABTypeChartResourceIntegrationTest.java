/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2013 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rab;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CellByRabTypeDataProvider;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.RNCByRabTypeDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rabanalysis.CellByRABTypeChartResource;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rabanalysis.RNCByRABTypeChartResource;

/**
 * @author ehaoswa
 * @since 2013
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class NetworkByRABTypeChartResourceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "rncByRabTypeResource")
    private RNCByRABTypeChartResource rncByRABTypeChartResource;

    @Test
    @Parameters(source = RNCByRabTypeDataProvider.class)
    public void testRNCData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncByRABTypeChartResource);
    }

    @Resource(name = "cellByRabTypeResource")
    private CellByRABTypeChartResource cellByRABTypeChartResource;

    @Test
    @Parameters(source = CellByRabTypeDataProvider.class)
    public void testCellData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellByRABTypeChartResource);
    }

}
