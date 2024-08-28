/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.dataconnection;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejoegaf
 *
 */
public class CauseCodeAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    private static final String DATA_CONNECTION_CAUSE_CODE_ANALYSIS = "DATA_CONNECTION_CAUSE_CODE_ANALYSIS";

    private static final String CC_LIST_IMSI = "CC_LIST_IMSI";

    private static final String CAUSE_CODE_PIE_CHART_IMSI = "CAUSE_CODE_PIE_CHART_IMSI";

    private static final String SUB_CAUSE_CODE_PIE_CHART_IMSI = "SUB_CAUSE_CODE_PIE_CHART_IMSI";

    private static final String SUCCESS_CAUSE_CODE_PIE_CHART_IMSI = "SUCCESS_CAUSE_CODE_PIE_CHART_IMSI";

    @Test
    public void testGetImsiCauseCodeList_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiCCListSpecificParameters(),
                SUBSCRIBER_SERVICES, CC_LIST_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiGroupCauseCodeList_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiGroupCCListSpecificParameters(),
                SUBSCRIBER_SERVICES, CC_LIST_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiCauseCodePieChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiCCChartSpecificParameters(),
                SUBSCRIBER_SERVICES, CAUSE_CODE_PIE_CHART_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiGroupCauseCodePieChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiGroupCCChartSpecificParameters(),
                SUBSCRIBER_SERVICES, CAUSE_CODE_PIE_CHART_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiSubCauseCodePieChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiSubCCChartSpecificParameters(),
                SUBSCRIBER_SERVICES, SUB_CAUSE_CODE_PIE_CHART_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiSuccessCauseCodePieChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiSuccessCCChartSpecificParameters(),
                SUBSCRIBER_SERVICES, SUCCESS_CAUSE_CODE_PIE_CHART_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetImsiGroupSuccessCauseCodePieChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getImsiGroupSuccessCCChartSpecificParameters(),
                SUBSCRIBER_SERVICES, SUCCESS_CAUSE_CODE_PIE_CHART_IMSI);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath service
     * @param serviceSpecificQueryParameters parameters specific to the service given in the subPath
     * @return The request response
     */
    private String runQuery(final String mimeType, final MultivaluedMap<String, String> serviceSpecificQueryParameters,
            final String pathPart, final String subPath) {
        final MultivaluedMap<String, String> standardQueryParameters = new MultivaluedMapImpl();
        standardQueryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        standardQueryParameters.putSingle(TZ_OFFSET, "+0100");
        standardQueryParameters.putSingle(MAX_ROWS, "500");
        if (!serviceSpecificQueryParameters.containsKey(DISPLAY_PARAM)) {
            standardQueryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        }

        standardQueryParameters.putAll(serviceSpecificQueryParameters);

        final ClientResponse response = webResource.path(pathPart).queryParams(standardQueryParameters).path(GSM)
                .path(DATA_CONNECTION_CAUSE_CODE_ANALYSIS).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(pathPart, GSM + "/" + DATA_CONNECTION_CAUSE_CODE_ANALYSIS + "/"
                + subPath, mimeType, response);

    }

    private MultivaluedMap<String, String> getImsiCCListSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_2 + "");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiGroupCCListSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiCCChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_2 + "");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        queryParameters.putSingle(CAUSE_CODE_ID_LIST, "0,2");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiSubCCChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_2 + "");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        queryParameters.putSingle(CAUSE_CODE_ID_LIST, "0,2");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiGroupCCChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        queryParameters.putSingle(CAUSE_CODE_ID_LIST, "0,2");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiSuccessCCChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(IMSI_PARAM, SAMPLE_IMSI_2 + "");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiGroupSuccessCCChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup1");
        queryParameters.putSingle(TYPE_PARAM, IMSI_PARAM_UPPER_CASE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        return queryParameters;
    }

}
