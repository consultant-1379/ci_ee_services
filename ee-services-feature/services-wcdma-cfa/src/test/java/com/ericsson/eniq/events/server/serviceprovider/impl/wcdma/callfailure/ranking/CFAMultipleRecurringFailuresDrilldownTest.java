package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CallFailureMRFDrilldownDataProvider;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CallFailureMRFErrorDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.CallFailureMultipleRecurringFailuresDrilldownService;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CFAMultipleRecurringFailuresDrilldownTest extends ServiceBaseTest {

    @Resource(name = "callFailureMultipleRecurringFailuresDrilldown")
    private CallFailureMultipleRecurringFailuresDrilldownService callFailureMultipleRecurringFailuresDrilldown;

    @Test
    @Parameters(source = CallFailureMRFDrilldownDataProvider.class)
    public void testValidData(final MultivaluedMap<String, String> requestParameters) {
        jsonAssertUtils.assertJSONSucceeds(runQuery(requestParameters, callFailureMultipleRecurringFailuresDrilldown));
    }

    @Test
    @Parameters(source = CallFailureMRFErrorDataProvider.class)
    public void testErrorData(final MultivaluedMap<String, String> requestParameters) {
        jsonAssertUtils.assertJSONErrorResult(runQueryWithoutAssesrtingJsonSucceeds(requestParameters,
                callFailureMultipleRecurringFailuresDrilldown));
    }

}
