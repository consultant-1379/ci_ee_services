package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.kpi.SummaryWCDMADrillDownServiceTestDataProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Desrible SummaryWCDMADrillDownServiceTest
 *
 * @author ezhelao
 * @since 11/2011
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:dashboard-service-context.xml"})
public class SummaryWCDMADrillDownServiceTest extends ServiceBaseTest {
    @Resource(name = "summaryWCDMADrillDownService")
    SummaryWCDMADrillDownService service;

    @Test
    @Parameters(source = SummaryWCDMADrillDownServiceTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, service);
    }


}
