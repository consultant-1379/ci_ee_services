/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_CODE_DESCRIPTION;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_CODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DETAIL_SUB_CAUSE_CODE_GRID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NETWORK_SERVICES;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SUB_CAUSE_CODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SUB_CAUSE_CODE_PATH;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_CAUSE_CODE_ANALYSIS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_BSC1_NODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author epesmit
 * @since 2012
 *
 */
public class WCDMACallFailureSCCRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testFetchingSubCauseCode() {
        runSubCauseCodeQuery(SUB_CAUSE_CODE_PATH, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testFetchingSubCauseCodeAsCSV() {
        runSubCauseCodeQuery(SUB_CAUSE_CODE_PATH, MediaTypeConstants.APPLICATION_CSV);
    }

    @Test
    public void testFetchingSubCauseCodeDEA() {
        runSubCauseCodeDEAQuery(DETAIL_SUB_CAUSE_CODE_GRID, MediaType.APPLICATION_JSON);
    }

    @Test
    public void testFetchingSubCauseCodeDEAAsCSV() {
        runSubCauseCodeDEAQuery(DETAIL_SUB_CAUSE_CODE_GRID, MediaTypeConstants.APPLICATION_CSV);
    }

    private void runSubCauseCodeQuery(final String subPath, final String mediaType) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(CAUSE_CODE_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(CAUSE_CODE_DESCRIPTION, TEST_BSC1_NODE);
        final String result = runQuery(requestParameters, mediaType, NETWORK_SERVICES, WCDMA_CAUSE_CODE_ANALYSIS,
                subPath);
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            assertJSONSucceeds(result);
        }
    }

    private void runSubCauseCodeDEAQuery(final String subPath, final String mediaType) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(CAUSE_CODE_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(SUB_CAUSE_CODE_PARAM, THIRTY_MINUTES);
        final String result = runQuery(requestParameters, mediaType, NETWORK_SERVICES, WCDMA_CAUSE_CODE_ANALYSIS,
                subPath);
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            assertJSONSucceeds(result);
        }
    }
}
