/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.dataconnection;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eatiaro
 *
 */
public class EventSummaryAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    //Fail on Jenkins, pass on local host.
    //    @Test
    //    public void testGetDataSubscriber_CSV() {
    //        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberSpecificParameters(), IMSI);
    //
    //    }
    //
    //    @Test
    //    public void testGetDataSubscriber_JSON() {
    //        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberSpecificParameters(), IMSI);
    //        jsonAssertUtils.assertJSONSucceeds(result);
    //    }
    //
    //    @Test
    //    public void testEventSummaryRequestForImsiGroup_CSV() {
    //        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberGroupSpecificParameters(), IMSI);
    //    }
    //
    //    @Test
    //    public void testEventSummaryRequestForImsiGroup_JSON() {
    //        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberGroupSpecificParameters(), IMSI);
    //        jsonAssertUtils.assertJSONSucceeds(result);
    //    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath service
     * @param serviceSpecificQueryParameters parameters specific to the service given in the subPath
     * @return The request response
     */
    private String runQuery(final String mimeType, final MultivaluedMap<String, String> serviceSpecificQueryParameters,
            final String subPath) {
        final MultivaluedMap<String, String> standardQueryParameters = new MultivaluedMapImpl();
        standardQueryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        standardQueryParameters.putSingle(TZ_OFFSET, "+0100");
        standardQueryParameters.putSingle(MAX_ROWS, "500");
        standardQueryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        standardQueryParameters.putAll(serviceSpecificQueryParameters);

        final ClientResponse response = webResource.path(SUBSCRIBER_SERVICES).queryParams(standardQueryParameters)
                .path(GSM).path(DATA_CONNECTION_EVENT_SUMMARY).path(subPath)
                .header(REQUEST_ID, generateRandomRequestID()).accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, GSM + "/" + DATA_CONNECTION_EVENT_SUMMARY
                + "/" + subPath, mimeType, response);
    }

    private MultivaluedMap<String, String> getSubscriberSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_AS_STRING);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getSubscriberGroupSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        return queryParameters;
    }

}
