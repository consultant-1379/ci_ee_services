package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.uertt;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Before;
import org.junit.Test;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SubscriberUERTTServicesTest extends BaseJMockUnitTest{
	private SubscriberUERTTService subscriberUERTTService;	
	final MultivaluedMap<String, String> map = new MultivaluedMapImpl();   
	final MultivaluedMap<String, String> mapFailure = new MultivaluedMapImpl();
	@Before
	public void setUp() throws Exception {		
		subscriberUERTTService = new SubscriberUERTTService();			    	
		map.add("time", "30");
		map.add("type", "imsi");
		map.add("imsi","46000000001234567");
		map.add("display", "grid");
		map.add("tzoffset", "+0530");
		map.add("maxRows", "500");
		
		mapFailure.add("time", "30");
		mapFailure.add("type", "imsi");
		mapFailure.add("imsi","123");
		mapFailure.add("display", "grid");
		mapFailure.add("tzoffset", "+0530");
		mapFailure.add("maxRows", "500");
	}
	@Test
	public void test() {		
		assertEquals("{\"success\":\"true\",\"errorDescription\":\"\",\"dataTimeFrom\":\"1371476160000\",\"dataTimeTo\":" +
				"\"1371477960000\",\"timeZone\":\"+0530\",\"data\":{\"eventId\": \"My_customized event\",\"protocol_type\": " +
				"\"RRC\",\"direction\": \"To\",\"timestamp\": \"2008-04-19 5:09.912\"}}", subscriberUERTTService.getData(map));		
	}
	@Test
	public void testFailure() {		
		assertEquals("{\"success\":\"true\",\"errorDescription\":\"\",\"dataTimeFrom\":\"1371476160000\",\"dataTimeTo\":" +
				"\"1371477960000\",\"timeZone\":\"+0530\",\"data\":No Data To Display}"
				,subscriberUERTTService.getData(mapFailure));		
	}
	
}
