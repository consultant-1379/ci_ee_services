package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure.CallFailureMultipleRecurringFailuresDataProvider;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-cfa-service-context.xml" })
public class CallFailureMultipleRecurringFailuresIntegrationTest extends ServiceBaseTest {

    @Resource(name = "callFailureMultipleRecurringFailures")
    private CallFailureMultipleRecurringFailuresService callFailureMultipleRecurringFailures;

    @Test
    @Parameters(source = CallFailureMultipleRecurringFailuresDataProvider.class)
    public void test(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, callFailureMultipleRecurringFailures);
    }

}
