/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.subbi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.resources.BaseServiceIntegrationTest;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author EEMECOY
 *
 */
public class CallFailureSubsessionBIServiceIntegrationTest extends BaseServiceIntegrationTest {

    private static final String INVALID_IMSI = "abc";

    private CallFailureSubsessionBIService callFailureSubsessionBIService;

    @Before
    public void setup() {
        callFailureSubsessionBIService = new CallFailureSubsessionBIService();
        attachDependencies(callFailureSubsessionBIService);
    }

    @Test
    public void testWithInvalidIMSI() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(IMSI_PARAM, INVALID_IMSI);
        final String result = runQuery(requestParameters, callFailureSubsessionBIService);
        assertJSONErrorResult(result);
        jsonAssertUtils.assertInputValidValue(result);
    }

    @Test
    public void testGetFailuresForMSISDNReturnsCorrectErrorMessage() throws JSONException {
        testWithInvalidType(TYPE_MSISDN);
    }

    @Test
    public void testGetFailuresForPTMSIReturnsCorrectErrorMessage() throws JSONException {
        testWithInvalidType(TYPE_PTMSI);
    }

    private void testWithInvalidType(final String type) throws JSONException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, type);
        requestParameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        final String result = runQuery(requestParameters, callFailureSubsessionBIService);
        assertJSONErrorResult(result);
        final String expectedErrorMessage = " is not a valid search criterion for ";
        assertTrue("Error message should have contained " + expectedErrorMessage,
                JSONTestUtils.getErrorDescription(result).contains(expectedErrorMessage));
    }

    @Test
    public void testGetFailuresDrilldown_ThirtyMinutes() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(IMSI_PARAM, Long.toString(460040000699587L));
        requestParameters.putSingle(EVENT_PARAM, "Call Drops");
        runGetFailuresQuery(requestParameters);
    }

    @Test
    public void testGetFailures_ThirtyMinutes() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(IMSI_PARAM, Long.toString(SAMPLE_IMSI));
        runGetFailuresQuery(requestParameters);
    }

    @Test
    public void testGetFailuresDrilldownCSV_ThirtyMinutes() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(IMSI_PARAM, Long.toString(460040000699587L));
        requestParameters.putSingle(EVENT_PARAM, "Call Drops");
        runQueryForCSV(requestParameters, callFailureSubsessionBIService);
    }

    private void runGetFailuresQuery(final MultivaluedMap<String, String> requestParameters) {
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        runQueryAndAssertJsonSucceeds(requestParameters, callFailureSubsessionBIService);
    }

    @Test
    public void testGetFailures_ThirtyMinutesForIMSIGroup() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, "IMSIGroup");
        runGetFailuresForIMSIGroupQuery(requestParameters);
    }

    private void runGetFailuresForIMSIGroupQuery(final MultivaluedMap<String, String> requestParameters) {
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        runQueryAndAssertJsonSucceeds(requestParameters, callFailureSubsessionBIService);
    }

}
