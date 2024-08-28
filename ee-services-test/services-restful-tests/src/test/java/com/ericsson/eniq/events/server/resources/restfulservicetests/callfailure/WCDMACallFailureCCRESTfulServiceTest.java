/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class WCDMACallFailureCCRESTfulServiceTest extends BaseRESTfulServiceTest {

    @Test
    public void testFetchingCauseCodeList_AccessArea() {
        runCauseCodeQuery(CC_LIST, MediaType.APPLICATION_JSON, false);
    }

    @Test
    public void testFetchingCauseCodeList_AccessAreaGroup() {
        runCauseCodeQuery(CC_LIST, MediaType.APPLICATION_JSON, false);
    }

    @Test
    public void testFetchingCauseCode_AccessArea() {
        runCauseCodeQuery(CAUSE_CODE_PATH, MediaType.APPLICATION_JSON, false);
    }

    @Test
    public void testFetchingCauseCodeAsCSV_AccessArea() {
        runCauseCodeQuery(CAUSE_CODE_PATH, MediaTypeConstants.APPLICATION_CSV, false);
    }

    @Test
    public void testFetchingCauseCode_AccessAreaGroup() {
        runCauseCodeQuery(CAUSE_CODE_PATH, MediaType.APPLICATION_JSON, true);
    }

    @Test
    public void testFetchingCauseCodeAsCSV_AccessAreaGroup() {
        runCauseCodeQuery(CAUSE_CODE_PATH, MediaTypeConstants.APPLICATION_CSV, true);
    }

    private void runCauseCodeQuery(final String subPath, final String mediaType, final boolean isGroup) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        if (isGroup) {
            requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_CELL_GROUP);
        } else {
            requestParameters.putSingle(NODE_PARAM, SAMPLE_3G_ACCESS_AREA_NODE_PARAM);
        }

        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);

        final String result = runQuery(requestParameters, mediaType, NETWORK_SERVICES, WCDMA_CAUSE_CODE_ANALYSIS,
                subPath);
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            assertJSONSucceeds(result);
        }

    }

}