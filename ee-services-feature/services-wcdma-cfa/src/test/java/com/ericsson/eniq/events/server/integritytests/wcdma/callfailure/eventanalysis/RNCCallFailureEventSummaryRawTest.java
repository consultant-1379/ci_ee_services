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
public class RNCCallFailureEventSummaryRawTest extends BaseDataIntegrityTest<RNCCallFailureEventSummaryResult> {

    private RNCCallFailureEventSummaryService rncCallFailureEventAnalysisService;

    private long hashedIdForRNC;

    @Before
    public void onSetUp() throws Exception {
        rncCallFailureEventAnalysisService = new RNCCallFailureEventSummaryService();
        attachDependencies(rncCallFailureEventAnalysisService);
        hashedIdForRNC = calculateHashedId(_3G, RNC01_ALTERNATIVE_FDN, ERICSSON);
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
        final Map<String, Object> valuesForRncTable = new HashMap<String, Object>();
        valuesForRncTable.put(HIER3_ID, hashedIdForRNC);
        valuesForRncTable.put(VENDOR, ERICSSON);
        valuesForRncTable.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForRncTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321, valuesForRncTable);
    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();
        insertRowIntoEventTable(CALL_DROP_EVENT_ID, 123456, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID, 99999, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID, 99999, SAMPLE_TAC, timestamp);

        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID, 987987, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID, 99999, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID, 111, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID, 111, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID, 111, SAMPLE_EXCLUSIVE_TAC, timestamp);

    }

    private void insertRowIntoEventTable(final String eventId, final long imsi, final long tac, final String timestamp)
            throws SQLException {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(EVENT_ID, Integer.valueOf(eventId));
        valuesForEventTable.put(HIER3_ID, hashedIdForRNC);
        valuesForEventTable.put(IMSI, imsi);
        valuesForEventTable.put(TAC, tac);
        valuesForEventTable.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);
    }

    private void createTemporaryEventTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(HIER3_ID);
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForDIMRNCTable = new ArrayList<String>();
        columnsForDIMRNCTable.add(HIER3_ID);
        columnsForDIMRNCTable.add(HIERARCHY_3);
        columnsForDIMRNCTable.add(VENDOR);
        columnsForDIMRNCTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, columnsForDIMRNCTable);
    }

    @Test
    public void testFiveMinuteQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);
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
        assertThat(resultForCallDropsEvent.getNumErrors(), is(3));
        assertThat(resultForCallDropsEvent.getNumImpactedSubscribers(), is(2));

        final RNCCallFailureEventSummaryResult resultForCallSetupFailureEvent = results.get(1);
        assertThat(resultForCallSetupFailureEvent.getVendor(), is(ERICSSON));
        assertThat(resultForCallSetupFailureEvent.getRNCFDN(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(resultForCallSetupFailureEvent.getEventDescription(), is(CALL_SETUP_FAILURES_EVENT_DESC));
        assertThat(resultForCallSetupFailureEvent.getNumErrors(), is(4));
        assertThat(resultForCallSetupFailureEvent.getNumImpactedSubscribers(), is(3));
    }
}
