/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 * 
 */
public class CallFailureSubsessionBIRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetSubscriberDetails() {
        final String result = runQuery(SUBBI_SUBSCRIBER_DETAILS, MediaType.APPLICATION_JSON);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetFailureDataForIMSIDrilldown_JSON() {
        final String result = runQuery(SUBBI_FAILURE, MediaType.APPLICATION_JSON);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetFailureDataForIMSIDrilldown_CSV() {
        runQuery(SUBBI_FAILURE, MediaTypeConstants.APPLICATION_CSV);
    }

    private String runQuery(final String subPath, final String mediaType) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_AS_STRING);
        queryParameters.putSingle(EVENT_PARAM, CALL_DROPS);
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        final ClientResponse response = webResource.path(SUBSCRIBER_SERVICES).queryParams(queryParameters)
                .path(CALLFAILURE_SUBSESSION_BI).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mediaType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, CALLFAILURE_SUBSESSION_BI, mediaType,
                response);
    }

    @Test
    public void testGetFailureDataForImsiGroup() {
        final String resultforIMSIGroup = runQueryForIMSIGroup();
        jsonAssertUtils.assertJSONSucceeds(resultforIMSIGroup);
    }

    private String runQueryForIMSIGroup() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGROUP");
        final ClientResponse response = webResource.path(SUBSCRIBER_SERVICES).queryParams(queryParameters)
                .path(CALLFAILURE_SUBSESSION_BI).path(SUBBI_FAILURE).header(REQUEST_ID, generateRandomRequestID())
                .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, CALLFAILURE_SUBSESSION_BI,
                MediaType.APPLICATION_JSON, response);
    }
}
