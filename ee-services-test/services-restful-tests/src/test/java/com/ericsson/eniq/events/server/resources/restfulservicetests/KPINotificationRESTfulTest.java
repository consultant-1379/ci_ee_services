/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class KPINotificationRESTfulTest extends BaseRESTfulServiceTest {

    @Test
    public void testCountService() {
        //   runKPINotificationQuery(NOTIFICATION_COUNT, MediaType.APPLICATION_JSON);
    }

    //@Test
    public void testDataServiceAsJSON() {
        runKPINotificationQuery(NOTIFICATION_DATA, MediaType.APPLICATION_JSON);
    }

    //@Test
    public void testDataServiceAsCSV() {
        runKPINotificationQuery(NOTIFICATION_DATA, MediaTypeConstants.APPLICATION_CSV);
    }

    private void runKPINotificationQuery(final String subPath, final String mediaType) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(NOTIFICATION_SEVERITY_PARAM, "major");
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String result = runQuery(requestParameters, mediaType, NETWORK_SERVICES, KPI_NOTIFICATION, subPath);
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            assertJSONSucceeds(result);
        }
    }

}
