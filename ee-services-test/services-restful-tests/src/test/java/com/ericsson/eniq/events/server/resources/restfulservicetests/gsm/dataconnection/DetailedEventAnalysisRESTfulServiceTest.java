/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.dataconnection;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class DetailedEventAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    private static final String IMSI_SCC = "IMSI_SCC";

    private static final String IMSI_CC = "IMSI_CC";

    //Fail on Jenkins, pass on local host.
    //    @Test
    //    public void testGetDataSubscriberGroup_CSV() {
    //        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberGroupSpecificParameters(), IMSI);
    //
    //    }
    //
    //    @Test
    //    public void testGetDataSubscriberGroup_JSON() {
    //        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberGroupSpecificParameters(), IMSI);
    //        jsonAssertUtils.assertJSONSucceeds(result);
    //    }

    @Test
    public void testGetImsiSubCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberSubCCSpecificParameters(), IMSI_SCC);
    }

    @Test
    public void testGetImsiSubCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberSubCCSpecificParameters(), IMSI_SCC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getImsiCCSpecificParameters(), IMSI_CC);
    }

    @Test
    public void testGetImsiCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiCCSpecificParameters(), IMSI_CC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiGroupCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getImsiGroupCCSpecificParameters(), IMSI_CC);
    }

    @Test
    public void testGetImsiGroupCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiGroupCCSpecificParameters(), IMSI_CC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

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
                .path(GSM).path(DATA_CONNECTION_DETAILED_EVENT_ANALYSIS).path(subPath)
                .header(REQUEST_ID, generateRandomRequestID()).accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(SUBSCRIBER_SERVICES, GSM + "/"
                + DATA_CONNECTION_DETAILED_EVENT_ANALYSIS + "/" + subPath, mimeType, response);

    }

    private MultivaluedMap<String, String> getSubscriberGroupSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getSubscriberSubCCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, TEST_VALUE_IMSI + "");
        queryParameters.putSingle(SUB_CAUSE_CODE_PARAM, "0");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiCCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, TEST_VALUE_IMSI + "");
        queryParameters.putSingle(CAUSE_CODE_PARAM, "0");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiGroupCCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        queryParameters.putSingle(CAUSE_CODE_PARAM, "0");
        return queryParameters;
    }
}