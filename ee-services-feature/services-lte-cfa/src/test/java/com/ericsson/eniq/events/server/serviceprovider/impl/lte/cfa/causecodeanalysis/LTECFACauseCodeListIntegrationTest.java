package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.causecodeanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.dataproviders.lte.cfa.LTECFACauseCodeListTestDataProvider;
import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:lte-cfa-service-context.xml"})
public class LTECFACauseCodeListIntegrationTest extends ServiceBaseTest{
	
	@Resource(name = "lteCFACauseCodeListService")
    private LTECFACauseCodeListService lteCFACauseCodeListService;

    @Test
    @Parameters(source = LTECFACauseCodeListTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCFACauseCodeListService);
    }


}
