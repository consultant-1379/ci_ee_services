/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.uertt;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.uertt.WcdmaUerttUrlTestDataProvider;

/**
 * @author xalomis
 * @since 2013
 * 
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:wcdma-uertt-service-context.xml" })
public class SubscriberUERTTServicesIntegrationTest  extends ServiceBaseTest{
    @Resource(name = "subscriberUERTTService")
	private SubscriberUERTTService subsUERTTService;
	@Test
	@Parameters(source = WcdmaUerttUrlTestDataProvider.class)
	public void testGetData(final MultivaluedMap<String, String> requestParameters) {
	    String result = runQuery(requestParameters, subsUERTTService);
	    jsonAssertUtils.assertJSONSucceeds(result);
	}
}
