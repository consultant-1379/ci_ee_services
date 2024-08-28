/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.gsm.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.resources.common.RestfulTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehorpte
 *
 */
public class GSMCallFailureEventAnalysisSummaryRESTfulServiceTest extends BaseRESTfulServiceTest {

    private static String EXTENDED_CAUSE = "EXTENDED_CAUSE";

    private static String DISTRIBUTION_CELL = "ACCESS_AREA_DISTRIBUTION";

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
    public void testGetDataBsc_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBscSpecificParameters(), BSC);
    }

    @Test
    public void testGetDataBsc_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBscSpecificParameters(), BSC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataSCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getSCCSpecificParameters(), EXTENDED_CAUSE);
    }

    @Test
    public void testGetDataSCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getSCCSpecificParameters(), EXTENDED_CAUSE);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForBscNetworkEventAnalysis_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBSCSpecificParametersForNetwork(), NODE);
    }

    //This test is needed since the query of summary event analysis goes to the same service,
    //but not on the same URL.
    //From the Ranking tab it is : .../BSC
    //From the Network tab it is : .../NODE
    // .../NODE is jointly used for Controller, Controller group, 
    //Access Area and Access Area group from the Network tab. 
    @Test
    public void testGetDataForBscNetworkEventAnalysis_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBSCSpecificParametersForNetwork(), NODE);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForBscGroupNetworkEventAnalysis_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBscGroupSpecificParametersForNetwork(), NODE);

    }

    @Test
    public void testGetDataForBscGroupNetworkEventAnalysis_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBscGroupSpecificParametersForNetwork(), NODE);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForBscGroupEventAnalysisSummary_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBscGroupSpecificEventAnalysisSummaryParameters(),
                BSC);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForBscGroupEventAnalysisSummary_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBscGroupSpecificEventAnalysisSummaryParameters(), BSC);
    }

    @Test
    public void testGetDataForAccessAreaNetworkEventAnalysis_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getAccessAreaSpecificParametersForNetwork(), NODE);
    }

    //Similar as for BSC, see comment above.
    @Test
    public void testGetDataForAccessAreaNetworkEventAnalysis_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getAccessAreaSpecificParametersForNetwork(), NODE);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataForAccessAreaGroupNetworkEventAnalysis_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getAccessAreaGroupSpecificParametersForNetwork(), NODE);

    }

    @Test
    public void testGetDataForAccessAreaGroupNetworkEventAnalysis_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getAccessAreaGroupSpecificParametersForNetwork(),
                NODE);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataImsi_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getImsiParams(), IMSI);
    }

    @Test
    public void testGetDataImsi_JSON() {
        final String jsonResult = runQuery(MediaType.APPLICATION_JSON, getImsiParams(), IMSI);
        jsonAssertUtils.assertJSONSucceeds(jsonResult);
    }

    @Test
    public void testGetDataImsiGroup_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getImsiGroupParams(), IMSI);
    }

    @Test
    public void testGetDataImsiGroup_JSON() {
        final String jsonResult = runQuery(MediaType.APPLICATION_JSON, getImsiGroupParams(), IMSI);
        jsonAssertUtils.assertJSONSucceeds(jsonResult);
    }

    @Test
    public void testGetDataImsiGroupBreakDown_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getImsiGroupBreakdownParams(), IMSI_GROUP_BREAKDOWN);
    }

    @Test
    public void testGetDataImsiGroupBreakDown_JSON() {
        final String jsonResult = runQuery(MediaType.APPLICATION_JSON, getImsiGroupBreakdownParams(),
                IMSI_GROUP_BREAKDOWN);
        jsonAssertUtils.assertJSONSucceeds(jsonResult);
    }

    @Test
    public void testGetDataAccessAreaGroup_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getAccessAreaGroupSpecificParameters(), ACCESS_AREA);
    }

    @Test
    public void testGetDataAccessAreaGroup_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getAccessAreaGroupSpecificParameters(), ACCESS_AREA);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataTerminalSummary_CSV() {
        runTerminalQuery(MediaTypeConstants.APPLICATION_CSV, getTerminalSummaryParameters(), TERMINAL_SERVICES);
    }

    @Test
    public void testGetDataTerminalSummary_JSON() {
        final String result = runTerminalQuery(MediaType.APPLICATION_JSON, getTerminalSummaryParameters(),
                TERMINAL_SERVICES);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetDataDistributionCellSummary_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getDistributionCellSummaryParameters(), DISTRIBUTION_CELL);
    }

    @Test
    public void testGetDataDistributionCellSummary_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getDistributionCellSummaryParameters(),
                DISTRIBUTION_CELL);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath 
     * @return The request response
     */
    private String runQuery(final String mimeType, final MultivaluedMap<String, String> serviceParameters,
            final String subPath) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        queryParameters.putAll(serviceParameters);
        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(queryParameters).path(GSM)
                .path(CALL_FAILURE_EVENT_SUMMARY).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, GSM + "/" + CALL_FAILURE_EVENT_SUMMARY + "/"
                + subPath, mimeType, response);

    }

    /**
     * @param mimeType The expected mime type of the returned data
     * @param subPath 
     * @return The request response
     */
    private String runTerminalQuery(final String mimeType, final MultivaluedMap<String, String> serviceParameters,
            final String subPath) {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        queryParameters.putSingle(TZ_OFFSET, "+0100");
        queryParameters.putSingle(MAX_ROWS, "500");
        queryParameters.putAll(serviceParameters);
        final ClientResponse response = webResource.path(TERMINAL_SERVICES).queryParams(queryParameters).path(GSM)
                .path(CALL_FAILURE_EVENT_SUMMARY).path(subPath).header(REQUEST_ID, generateRandomRequestID())
                .accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(TERMINAL_SERVICES, GSM + "/" + CALL_FAILURE_EVENT_SUMMARY + "/"
                + subPath, mimeType, response);

    }

    private MultivaluedMap<String, String> getBscSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CONTROLLER_SQL_ID, "1579249632524835481");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getAccessAreaSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CELL_SQL_ID, "1579249632524835481");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBSCSpecificParametersForNetwork() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_GSM_CONTROLLER1_NAME + "," + "Ericsson" + "," + "0");
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBscGroupSpecificParametersForNetwork() {
        final MultivaluedMap<String, String> queryParameters = getBSCSpecificParametersForNetwork();
        queryParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_BSC_GROUP);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBscGroupSpecificEventAnalysisSummaryParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        queryParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_BSC_GROUP);
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getAccessAreaGroupSpecificParametersForNetwork() {
        final MultivaluedMap<String, String> queryParameters = getAccessAreaSpecificParametersForNetwork();
        queryParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_CELL_GROUP);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getAccessAreaSpecificParametersForNetwork() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_GSM_CELL1_NAME + "," + "" + ","
                + TEST_VALUE_GSM_CONTROLLER1_NAME + "," + "Ericsson" + "," + "0");
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getSCCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CAUSE_CODE_PARAM, "1");
        queryParameters.putSingle(CAUSE_CODE_DESCRIPTION, "CauseCode1 Description");
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiParams() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(IMSI_PARAM, TEST_VALUE_IMSI + "");
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiGroupParams() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(GROUP_NAME_PARAM, "testGrp1");
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getImsiGroupBreakdownParams() {
        final MultivaluedMap<String, String> queryParameters = getImsiGroupParams();
        queryParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        queryParameters.putSingle(CATEGORY_ID_DESC, "Call%20Drops");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getAccessAreaGroupSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TYPE_PARAM, CELL);
        queryParameters.putSingle(GROUP_NAME_PARAM, "AccesAreaGroup1" + "");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getTerminalSummaryParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(TAC, TEST_VALUE_TAC);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getDistributionCellSummaryParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(CONTROLLER_SQL_ID, "8432899184272901578");
        queryParameters.putSingle(CATEGORY_ID, GSM_CALL_DROP_CATEGORY_ID);
        return queryParameters;
    }
}
