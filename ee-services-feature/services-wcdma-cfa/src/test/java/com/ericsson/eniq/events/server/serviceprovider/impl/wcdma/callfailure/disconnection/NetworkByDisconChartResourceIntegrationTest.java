/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2013 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.disconnection;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CellDisconDataProvider;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.RNCDisconDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.disconnectionanalysis.CellByDisconChartResource;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.disconnectionanalysis.RNCByDisconChartResource;

/**
 * @author ehaoswa
 * @since 2013
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class NetworkByDisconChartResourceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "rncDisconResource")
    private RNCByDisconChartResource rncByDisconChartResource;

    @Test
    @Parameters(source = RNCDisconDataProvider.class)
    public void testRNCData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncByDisconChartResource);
    }

    @Resource(name = "cellDisconResource")
    private CellByDisconChartResource cellByDisconChartResource;

    @Test
    @Parameters(source = CellDisconDataProvider.class)
    public void testCellData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellByDisconChartResource);
    }

}
