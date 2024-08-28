/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.dataconnection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GRID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GROUP_NAME_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GSM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.REQUEST_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SUBSCRIBER;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SUBSCRIBER_SERVICES;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.ONE_WEEK;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.SAMPLE_IMSI_AS_STRING;

/**
 * @author ETHOMIT
 * @since 2012
 *
 */
public class DataVolumeAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataSubscriber_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberSpecificParameters(), SUBSCRIBER);

    }

    @Test
    public void testGetDataSubscriber_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberSpecificParameters(), SUBSCRIBER);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataSubscriberGroup_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberGroupSpecificParameters(), SUBSCRIBER);

    }

    @Test
    public void testGetDataSubscriberGroup_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberGroupSpecificParameters(), SUBSCRIBER);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType
     *            The expected mime type of the returned data
     * @param subPath
     *            service
     * @param serviceSpecificQueryParameters
     *            parameters specific to the service given in the subPath
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
                .path(GSM).path("DATA_CONNECTION_DATAVOLUME_ANALYSIS").path(subPath)
                .header(REQUEST_ID, generateRandomRequestID()).accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, GSM + "/"
                + "DATA_CONNECTION_DATAVOLUME_ANALYSIS" + "/" + subPath, mimeType, response);
    }

    private MultivaluedMap<String, String> getSubscriberSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_AS_STRING + "");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getSubscriberGroupSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        return queryParameters;
    }

}
