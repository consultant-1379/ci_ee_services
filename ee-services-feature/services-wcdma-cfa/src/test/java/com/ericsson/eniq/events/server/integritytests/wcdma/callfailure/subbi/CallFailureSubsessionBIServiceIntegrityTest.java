/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.subbi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.subbi.CallFailureSubsessionBIService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureSubsessionFailureResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author EEMECOY
 *
 */
public class CallFailureSubsessionBIServiceIntegrityTest extends
        BaseDataIntegrityTest<CallFailureSubsessionFailureResult> {

    private CallFailureSubsessionBIService callFailureSubsessionBIService;

    @Before
    public void setup() throws Exception {
        callFailureSubsessionBIService = new CallFailureSubsessionBIService();
        attachDependencies(callFailureSubsessionBIService);
        createTables();
        insertData();
    }

    private void insertData() throws SQLException {
        final String timestamp1 = DateTimeUtilities.getDateTimeMinus30Minutes();
        final String timestamp2 = DateTimeUtilities.getDateTimeMinus35Minutes();

        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp2);

        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp2);

    }

    private void insertRowIntoEventTable(final int eventId, final int tac, final String timestamp) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, SAMPLE_IMSI_AS_STRING);
        values.put(EVENT_ID, eventId);
        values.put(TAC, tac);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
        final Map<String, Object> valuesForGroupTable = new HashMap<String, Object>();
        valuesForGroupTable.put(GROUP_NAME_KEY, TEST_VALUE_IMSI_GROUP);
        valuesForGroupTable.put(IMSI, SAMPLE_IMSI_AS_STRING);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForGroupTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        final Collection<String> columnsForGroupTable = new ArrayList<String>();
        columnsForGroupTable.add(GROUP_NAME_KEY);
        columnsForGroupTable.add(IMSI);
        createTemporaryTable(TEMP_GROUP_TYPE_E_IMSI, columnsForGroupTable);
    }

    @Test
    public void testGetFailures_ThirtyMinutes() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(IMSI_PARAM, Long.toString(SAMPLE_IMSI));
        final String result = runQuery(callFailureSubsessionBIService, requestParameters);
        validateResult(result);
    }

    private void validateResult(final String json) throws Exception {
        final List<CallFailureSubsessionFailureResult> results = getTranslator().translateResult(json,
                CallFailureSubsessionFailureResult.class);
        validateAgainstGridDefinition(json, "SUB_BI_FAILED_EVENTS_CFA");
        assertThat(results.size(), is(2));

        final CallFailureSubsessionFailureResult resultForCallDropsEvent = results.get(0);
        assertThat(resultForCallDropsEvent.getFailures(), is(4));
        assertThat(resultForCallDropsEvent.getEventDesc(), is(CALL_DROPS));

        final CallFailureSubsessionFailureResult resultForCallSetupFailuresEvent = results.get(1);
        assertThat(resultForCallSetupFailuresEvent.getFailures(), is(3));
        assertThat(resultForCallSetupFailuresEvent.getEventDesc(), is(CALL_SETUP_FAILURES_EVENT_DESC));

    }

    @Test
    public void testGetFailuresForIMSIGroup_ThirtyMinutes() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, TYPE_IMSI_GROUP);
        final String result = runQuery(callFailureSubsessionBIService, requestParameters);
        validateResultForIMSIGroup(result);
    }

    private void validateResultForIMSIGroup(final String json) throws Exception {
        final List<CallFailureSubsessionFailureResult> result = getTranslator().translateResult(json,
                CallFailureSubsessionFailureResult.class);
        validateAgainstGridDefinition(json, "SUB_BI_FAILED_EVENTS_CFA");
        assertThat(result.size(), is(2));

        final CallFailureSubsessionFailureResult resultForCallDropsEvent = result.get(0);
        assertThat(resultForCallDropsEvent.getFailures(), is(3));
        assertThat(resultForCallDropsEvent.getEventDesc(), is(CALL_DROPS));

        final CallFailureSubsessionFailureResult resultForCallSetupFailuresEvent = result.get(1);
        assertThat(resultForCallSetupFailuresEvent.getFailures(), is(2));
        assertThat(resultForCallSetupFailuresEvent.getEventDesc(), is(CALL_SETUP_FAILURES_EVENT_DESC));

    }
}
