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

import org.junit.Ignore;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ejoegaf
 *
 */
@Ignore
public class GSMCallFailureCauseCodeAnalysisRESTfulServiceTest extends BaseRESTfulServiceTest {

    private static final String EXCESSIVE_TA = "EXCESSIVE TA";

    private static final String CAUSE_CODE_1 = "1";
    
    private static final String CAUSE_CODE_DESC_1 = "HARDWARE";

    private static final String SUB_CAUSE_CODE_5 = "5";
    
    private static final String MANUFACTURER_1 = "Nokia";
    
    private static final String MODEL_1 = "1110b";
    
    private static final String FAILURE_TYPE_1 = "Call Drops";
    
    private static final String CATEGORY_ID_1 = "0";
    
    private static final String TAC_ID_1 = "1072700";
    
    

    @Test
    public void testGetControllerCauseCodeAnalysisGrid_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBSCSpecificParameters(), CAUSE_CODE_GRID);

    }

    @Test
    public void testGetControllerCauseCodeAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBSCSpecificParameters(), CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetControllerSubCauseCodeAnalysisGrid_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getBSCSubCauseCodeSpecificParameters(), SUB_CAUSE_CODE_GRID);

    }

    @Test
    public void testGetControllerSubCauseCodeAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBSCSubCauseCodeSpecificParameters(),
                SUB_CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetCellCauseCodeListAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getCellCCListSpecificParameters(), CC_LIST);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetBscCauseCodeListAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBscCCListSpecificParameters(), CC_LIST);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetCellCauseCodeAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getCellCauseCodeGridSpecificParameters(),
                CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetCellCauseCodeAnalysisGrid_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getCellCauseCodeGridSpecificParameters(), CAUSE_CODE_GRID);
    }

    @Test
    public void testGetCellCauseCodeAnalysisChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getCellCauseCodeChartSpecificParameters(),
                CAUSE_CODE_PIE_CHART);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetCellSubCauseCodeAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getCellSubCauseCodeGridSpecificParameters(),
                SUB_CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetCellSubCauseCodeAnalysisGrid_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getCellSubCauseCodeGridSpecificParameters(), SUB_CAUSE_CODE_GRID);
    }

    @Test
    public void testGetCellSubCauseCodeAnalysisChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getCellSubCauseCodeChartSpecificParameters(),
                SUB_CAUSE_CODE_PIE_CHART);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetBscCauseCodeAnalysisChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBscCauseCodeChartSpecificParameters(),
                CAUSE_CODE_PIE_CHART);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetBscSubCauseCodeAnalysisChart_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getBscSubCauseCodeChartSpecificParameters(),
                SUB_CAUSE_CODE_PIE_CHART);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testDetailedGridByBSC_CC_SCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getDetailedEventSpecificParametersBy_BSC_CC_SCC(),
                DETAIL_SUB_CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testDetailedGridByBSC_CC_SCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getDetailedEventSpecificParametersBy_BSC_CC_SCC(),
                DETAIL_SUB_CAUSE_CODE_GRID);
    }

    @Test
    public void testDetailedGridByCELL_CC_SCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getDetailedEventSpecificParametersBy_CELL_CC_SCC(),
                DETAIL_SUB_CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testDetailedGridByCELL_CC_SCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getDetailedEventSpecificParametersBy_CELL_CC_SCC(),
                DETAIL_SUB_CAUSE_CODE_GRID);
    }

    @Test
    public void testDetailedGridByTAC_CC_SCC_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getDetailedEventSpecificParametersBy_TAC_CC_SCC(),
                DETAIL_SUB_CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testDetailedGridByTAC_CC_SCC_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getDetailedEventSpecificParametersBy_TAC_CC_SCC(),
                DETAIL_SUB_CAUSE_CODE_GRID);
    }

    @Test
    public void testGetTerminalCauseCodeAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getTerminalCauseCodeGridSpecificParameters(),
                CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetTerminalCauseCodeAnalysisGrid_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getTerminalCauseCodeGridSpecificParameters(), CAUSE_CODE_GRID);
    }
    
    @Test
    public void testGetTerminalSubCauseCodeAnalysisGrid_JSON() {
        final String result = runQuery(MediaType.APPLICATION_JSON, getTerminalSubCauseCodeGridSpecificParameters(),
                SUB_CAUSE_CODE_GRID);
        jsonAssertUtils.assertJSONSucceeds(result);
    }

    @Test
    public void testGetTerminalSubCauseCodeAnalysisGrid_CSV() {
        runQuery(MediaTypeConstants.APPLICATION_CSV, getTerminalSubCauseCodeGridSpecificParameters(), SUB_CAUSE_CODE_GRID);
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
        if (!serviceSpecificQueryParameters.containsKey(DISPLAY_PARAM)) {
            standardQueryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        }

        standardQueryParameters.putAll(serviceSpecificQueryParameters);

        final ClientResponse response = webResource.path(NETWORK_SERVICES).queryParams(standardQueryParameters)
                .path(GSM).path(CALL_FAILURE_CAUSE_CODE_ANALYSIS).path(subPath)
                .header(REQUEST_ID, generateRandomRequestID()).accept(mimeType).get(ClientResponse.class);

        return assertResponseIsOKAndReturnResponseText(NETWORK_SERVICES, GSM + "/" + CALL_FAILURE_CAUSE_CODE_ANALYSIS
                + "/" + subPath, mimeType, response);

    }

    private MultivaluedMap<String, String> getBSCSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        queryParameters.putSingle(TYPE_PARAM, BSC);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBSCSubCauseCodeSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        queryParameters.putSingle(TYPE_PARAM, BSC);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        queryParameters.putSingle(CAUSE_CODE_DESCRIPTION, EXCESSIVE_TA);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBscCauseCodeChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        queryParameters.putSingle(TYPE_PARAM, BSC);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        queryParameters.putSingle(CAUSE_CODE_ID_LIST, "1,2");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBscSubCauseCodeChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        queryParameters.putSingle(TYPE_PARAM, BSC);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        queryParameters.putSingle(CAUSE_CODE_DESCRIPTION, EXCESSIVE_TA);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getCellCCListSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CELL_NODE);
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getBscCCListSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getCellCauseCodeGridSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CELL_NODE);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getCellCauseCodeChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CELL_NODE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        queryParameters.putSingle(CAUSE_CODE_ID_LIST, "1,2");
        return queryParameters;
    }

    private MultivaluedMap<String, String> getCellSubCauseCodeGridSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CELL_NODE);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getCellSubCauseCodeChartSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CELL_NODE);
        queryParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getTerminalCauseCodeGridSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_MAN, MANUFACTURER_1);
        queryParameters.putSingle("MODEL", MODEL_1);
        queryParameters.putSingle(FAILURE_TYPE_PARAM, FAILURE_TYPE_1);
        queryParameters.putSingle(CATEGORY_ID, CATEGORY_ID_1);
        queryParameters.putSingle(TAC_PARAM, TAC_ID_1); 
        queryParameters.putSingle(TYPE_PARAM, TAC);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getTerminalSubCauseCodeGridSpecificParameters() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TAC_PARAM, TAC_ID_1); 
        queryParameters.putSingle(TYPE_MAN, MANUFACTURER_1);
        queryParameters.putSingle("MODEL", MODEL_1);
        queryParameters.putSingle(FAILURE_TYPE_PARAM, FAILURE_TYPE_1);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        queryParameters.putSingle(CAUSE_CODE_DESCRIPTION, CAUSE_CODE_DESC_1);
        queryParameters.putSingle(FAILURE_TYPE_PARAM, FAILURE_TYPE_1);
        queryParameters.putSingle(TYPE_PARAM, TAC);
        queryParameters.putSingle(CATEGORY_ID, CATEGORY_ID_1);        
        return queryParameters;
    }
    
    private MultivaluedMap<String, String> getDetailedEventSpecificParametersBy_BSC_CC_SCC() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CONTROLLER_NODE);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        queryParameters.putSingle(SUB_CAUSE_CODE_PARAM, SUB_CAUSE_CODE_5);
        return queryParameters;
    }

    //Broken
    private MultivaluedMap<String, String> getDetailedEventSpecificParametersBy_CELL_CC_SCC() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(NODE_PARAM, TEST_VALUE_MSS_CELL_NODE);
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        queryParameters.putSingle(SUB_CAUSE_CODE_PARAM, SUB_CAUSE_CODE_5);
        return queryParameters;
    }

    private MultivaluedMap<String, String> getDetailedEventSpecificParametersBy_TAC_CC_SCC() {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TYPE_PARAM, TAC);
        queryParameters.putSingle(CAUSE_CODE_PARAM, CAUSE_CODE_1);
        return queryParameters;
    }
}
