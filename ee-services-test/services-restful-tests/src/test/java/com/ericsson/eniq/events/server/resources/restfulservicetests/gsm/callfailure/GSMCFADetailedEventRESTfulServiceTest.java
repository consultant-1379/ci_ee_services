/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.callfailure;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.ACCESS_AREA;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CALL_FAILURE_DETAILED_EVENT_ANALYSIS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_CODE_DESCRIPTION;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EXTENDED_CAUSE_VALUE_COLUMN;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GRID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GSM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GSM_CALL_DROP_CATEGORY_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NETWORK_SERVICES;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.REQUEST_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SUBSCRIBER_FOR_CALL_DROP;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SUB_CAUSE_CODE_DESCRIPTION;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_CELL;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_WEEK;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_IMSI_AS_STRING;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_GSM_CELL1_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_GSM_CONTROLLER1_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TAC;

/**
 * @author ewanggu
 *
 */
public class GSMCFADetailedEventRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testGetDataBSC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBSCSpecificParameters(), BSC);

    }

    @Test
    public void testGetDataBSC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBSCSpecificParameters(), BSC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataCauseCode_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getCCSpecificParameters(),
                CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI);
    }

    @Test
    public void testGetDataCauseCode_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getCCSpecificParameters(),
                CALL_FAILURE_RANKING_BY_CALLS_DROPPED_URI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataAccessArea_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getAccessAreaSpecificParameters(), ACCESS_AREA);

    }

    @Test
    public void testGetDataAccessArea_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getAccessAreaSpecificParameters(), ACCESS_AREA);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataSubscriberCallDrop_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getSubscriberCallDropSpecificParameters(),
                SUBSCRIBER_FOR_CALL_DROP);

    }

    @Test
    public void testGetDataSubscriberCallDrop_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getSubscriberCallDropSpecificParameters(),
                SUBSCRIBER_FOR_CALL_DROP);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataTerminal_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getTerminalSpecificParameters(), TAC);

    }

    @Test
    public void testGetDataTerminal_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getTerminalSpecificParameters(), TAC);
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

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(standardQueryParameters)
                .path(GSM).path(CALL_FAILURE_DETAILED_EVENT_ANALYSIS).path(subPath)
                .header(REQUEST_ID, generateRandomRequestID()).accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, GSM + "/"
                + CALL_FAILURE_DETAILED_EVENT_ANALYSIS + "/" + subPath, mimeType, response);

    }

    private MultivaluedMap<String, String> getBSCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_GSM_CONTROLLER1_NAME + "," + "Ericsson" + "," + "0");
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getCCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CAUSE_VALUE, "1");
        queryParameters.putSingle(EXTENDED_CAUSE_VALUE_COLUMN, "1");
        queryParameters.putSingle(CAUSE_CODE_DESCRIPTION, "CAUSE_CODE_DESCRIPTION");
        queryParameters.putSingle(SUB_CAUSE_CODE_DESCRIPTION, "SUB_CAUSE_CODE_DESCRIPTION");
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getAccessAreaSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_GSM_CELL1_NAME + "," + "" + ","
                + TEST_VALUE_GSM_CONTROLLER1_NAME + "," + "Ericsson" + "," + "0");
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getSubscriberCallDropSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_AS_STRING);
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getTerminalSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TAC, TEST_VALUE_TAC);
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }
}
