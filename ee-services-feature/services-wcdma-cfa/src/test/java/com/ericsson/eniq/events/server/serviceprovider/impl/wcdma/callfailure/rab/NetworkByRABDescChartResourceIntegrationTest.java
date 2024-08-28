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
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CellByRabTypeDescDataProvider;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.RNCByRabTypeDescDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rabanalysis.CellByRABDescChartResource;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rabanalysis.RNCByRABDescChartResource;

/**
 * @author ehaoswa
 * @since 2013
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class NetworkByRABDescChartResourceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "rncByRabDescResource")
    private RNCByRABDescChartResource rabDescChartResource;

    @Test
    @Parameters(source = RNCByRabTypeDescDataProvider.class)
    public void testRNCData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rabDescChartResource);
    }

    @Resource(name = "cellByRabDescResource")
    private CellByRABDescChartResource cellDescChartResource;

    @Test
    @Parameters(source = CellByRabTypeDescDataProvider.class)
    public void testCellData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellDescChartResource);
    }

}
