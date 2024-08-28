/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.RNCCallFailureEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.RNCCallFailureEventSummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class RNCCallFailureEventSummaryAggTest extends BaseDataIntegrityTest<RNCCallFailureEventSummaryResult> {

    private RNCCallFailureEventSummaryService rncCallFailureEventAnalysisService;

    private final int numFailuresForCallDropsEvent = 3;

    private final int numFailuresForCallSetupFailureEvent = 5;

    private long hashedIdForRNC1;

    @Before
    public void onSetUp() throws Exception {
        rncCallFailureEventAnalysisService = new RNCCallFailureEventSummaryService();
        attachDependencies(rncCallFailureEventAnalysisService);
        hashedIdForRNC1 = calculateHashedId(_3G, RNC01_ALTERNATIVE_FDN, ERICSSON);
        createTemporaryDIMTables();
        createTemporaryEventTables();
        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        insertDataIntoTopologyTables();
        insertDataIntoTacGroupTable();
        insertEventData();
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void insertDataIntoTopologyTables() throws SQLException {
        final Map<String, Object> valuesForSgehHier3Table = new HashMap<String, Object>();
        valuesForSgehHier3Table.put(VENDOR, ERICSSON);
        valuesForSgehHier3Table.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForSgehHier3Table.put(HIER3_ID, hashedIdForRNC1);
        valuesForSgehHier3Table.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321, valuesForSgehHier3Table);
    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus48Hours();

        /* insert RAW data and 15Min Err Data data*/
        insertRowIntoRawEventTable(CALL_DROP_EVENT_ID, 123456, SAMPLE_TAC, timestamp); //jj pre-existing fill RAW Table and DAY Agg ERR Table jj//
        insertRowIntoRawEventTable(CALL_DROP_EVENT_ID, 99999, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(CALL_DROP_EVENT_ID, 99999, SAMPLE_TAC, timestamp);
        insertRowIntoAggTable(CALL_DROP_EVENT_ID, numFailuresForCallDropsEvent, timestamp);
        insertRowIntoRawEventTable(CALL_SETUP_FAILURE_EVENT_ID, 987987, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(CALL_SETUP_FAILURE_EVENT_ID, 99999, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(CALL_SETUP_FAILURE_EVENT_ID, 111, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(CALL_SETUP_FAILURE_EVENT_ID, 111, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(CALL_SETUP_FAILURE_EVENT_ID, 111, SAMPLE_EXCLUSIVE_TAC, timestamp);
        insertRowIntoAggTable(CALL_SETUP_FAILURE_EVENT_ID, numFailuresForCallSetupFailureEvent, timestamp);

        /* insert our DAY Err data*/
        insertRowInto15MinAggTable(CALL_DROP_EVENT_ID, numFailuresForCallDropsEvent, timestamp); //jj new fill  15 MIN Agg ERR Table jj//
        insertRowInto15MinAggTable(CALL_SETUP_FAILURE_EVENT_ID, numFailuresForCallSetupFailureEvent, timestamp); //we use raw in aggregation cfa to get the number of subscribers//\

    }

    private void insertRowIntoAggTable(final String eventId, final int numFailures, final String timestamp)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(EVENT_ID, Integer.valueOf(eventId));
        values.put(NO_OF_ERRORS, numFailures);
        values.put(HIER3_ID, hashedIdForRNC1);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_DAY, values);

    }

    private void insertRowInto15MinAggTable(final String eventId, final int numFailures, final String timestamp) //jj new fill RAW Table and 15 MIN Agg Table jj//
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(EVENT_ID, Integer.valueOf(eventId));
        values.put(NO_OF_ERRORS, numFailures);
        values.put(HIER3_ID, hashedIdForRNC1);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_15MIN, values);

    }

    private void insertRowIntoRawEventTable(final String eventId, final long imsi, final long tac,
            final String timestamp) throws SQLException {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(EVENT_ID, Integer.valueOf(eventId));
        valuesForEventTable.put(HIER3_ID, hashedIdForRNC1);
        valuesForEventTable.put(IMSI, imsi);
        valuesForEventTable.put(TAC, tac);
        valuesForEventTable.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);
    }

    private void createTemporaryEventTables() throws Exception {

        final Collection<String> columnsForRawTable = new ArrayList<String>(); //jj pre-existing create the RAW TABLE jj//
        columnsForRawTable.add(HIER3_ID);
        columnsForRawTable.add(IMSI);
        columnsForRawTable.add(EVENT_ID);
        columnsForRawTable.add(TAC);
        columnsForRawTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForRawTable);

        final Collection<String> columnsForAggTable = new ArrayList<String>(); //jj pre-existing create the day err aggregation table jj//			
        columnsForAggTable.add(NO_OF_ERRORS);
        columnsForAggTable.add(EVENT_ID);
        columnsForAggTable.add(HIER3_ID);
        columnsForAggTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_DAY, columnsForAggTable);

        final Collection<String> columnsFor15MinAggTable = new ArrayList<String>(); //jj added create the 15min err aggregation table and defined the 15min table in the TempTableNames Classjj//			
        columnsFor15MinAggTable.add(NO_OF_ERRORS);
        columnsFor15MinAggTable.add(EVENT_ID);
        columnsFor15MinAggTable.add(HIER3_ID);
        columnsFor15MinAggTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_15MIN, columnsForAggTable);
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForSgehHier3FunctionTable = new ArrayList<String>();
        columnsForSgehHier3FunctionTable.add(HIER3_ID);
        columnsForSgehHier3FunctionTable.add(VENDOR);
        columnsForSgehHier3FunctionTable.add(HIERARCHY_3);
        columnsForSgehHier3FunctionTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, columnsForSgehHier3FunctionTable);
    }

    @Test
    public void testOneWeekQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        final String result = runQuery(rncCallFailureEventAnalysisService, requestParameters);
        verifyResult(result);
    }

    @Test
    public void testThreeDayQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, THREE_DAY);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        final String result = runQuery(rncCallFailureEventAnalysisService, requestParameters);
        verifyResult(result);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_EVENT_ANALYSIS_CALL_FALURE_RNC");
        final List<RNCCallFailureEventSummaryResult> results = getTranslator().translateResult(json,
                RNCCallFailureEventSummaryResult.class);

        assertThat(results.size(), is(2));
        final RNCCallFailureEventSummaryResult resultForCallDropsEvent = results.get(0);
        assertThat(resultForCallDropsEvent.getVendor(), is(ERICSSON));
        assertThat(resultForCallDropsEvent.getRNCFDN(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(resultForCallDropsEvent.getEventDescription(), is(CALL_DROPS));
        assertThat(resultForCallDropsEvent.getNumErrors(), is(numFailuresForCallDropsEvent));
        assertThat(resultForCallDropsEvent.getNumImpactedSubscribers(), is(2));

        final RNCCallFailureEventSummaryResult resultForCallSetupFailureEvent = results.get(1);
        assertThat(resultForCallSetupFailureEvent.getVendor(), is(ERICSSON));
        assertThat(resultForCallSetupFailureEvent.getRNCFDN(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(resultForCallSetupFailureEvent.getEventDescription(), is(CALL_SETUP_FAILURES_EVENT_DESC));
        assertThat(resultForCallSetupFailureEvent.getNumErrors(), is(numFailuresForCallSetupFailureEvent));
        assertThat(resultForCallSetupFailureEvent.getNumImpactedSubscribers(), is(3));

    }
}
