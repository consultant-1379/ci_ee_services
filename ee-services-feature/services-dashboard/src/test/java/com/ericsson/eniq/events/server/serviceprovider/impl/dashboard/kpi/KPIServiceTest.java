package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.kpi.RNCKPIServiceTestDataProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Desrible RNCKPIServiceTest
 *
 * @author ezhelao
 * @since 11/2011
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:dashboard-service-context.xml"})
public class KPIServiceTest extends ServiceBaseTest {
    @Resource(name = "rncKPIService")
    RNCKPIService rncKPIService;

    @Resource(name = "cellKPIService")
    CellKPIService cellKPIService;

    @Resource(name = "cellGroupKPIService")
    CellGroupKPIService cellGroupKPIService;

    @Resource(name = "cellKPIDrillDownService")
    CellKPIDrillDownService cellKPIDrillDownService;

    @Resource(name = "cellGroupKPIDrillDownService")
    CellGroupKPIDrillDownService cellGroupKPIDrillDownService;

    @Resource(name = "rnckpiDrillDownService")
    RNCKPIDrillDownService rnckpiDrillDownService;

    @Resource(name = "rncGroupKPIDrillDownService")
    RNCGroupKPIDrillDownService rncGroupKPIDrillDownService;


    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_RNCKPIService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncKPIService);
    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_CellKPIService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_CellGroupKPIService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellGroupKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_CellKPIDrillDownService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_CellGroupKPIDrillDownService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellGroupKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_RNCKPIDrillDownService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rnckpiDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetData_RNCGroupKPIDrillDownService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncGroupKPIDrillDownService);

    }


}
