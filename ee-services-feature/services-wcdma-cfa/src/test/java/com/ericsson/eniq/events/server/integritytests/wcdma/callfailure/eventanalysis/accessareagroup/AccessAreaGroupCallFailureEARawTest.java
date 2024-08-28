/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.accessareagroup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.NetworkEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.NetworkEventAnalysisAccessAreaGroupResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eeikonl
 * @since 2011
 *
 */
public class AccessAreaGroupCallFailureEARawTest extends
        BaseDataIntegrityTest<NetworkEventAnalysisAccessAreaGroupResult> {

    private NetworkEventAnalysisService service;

    private final String hash_id1 = "1234";

    private final String hash_id2 = "1235";

    private final String hash_id3 = "1236";

    @Before
    public void init() throws Exception {
        service = new NetworkEventAnalysisService();
        attachDependencies(service);

        createAndPopulateLookupTables();
        createOtherTables();

        insertDataIntoAccessAreaGroupTable(hash_id1);
        insertDataIntoAccessAreaGroupTable(hash_id2);
        populateExclusiveTacTable();
        populatetablesForRawQueries();

        populatetablesForAggQueries();

    }

    private void populateExclusiveTacTable() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        valuesForTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void populatetablesForRawQueries() throws SQLException {
        final String timeStamp = DateTimeUtilities.getDateTimeMinus30Minutes().concat(".0");
        insertRawEventData(hash_id1, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);
        insertRawEventData(hash_id1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);
        insertRawEventData(hash_id2, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);
        insertRawEventData(hash_id2, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);

        //These shouldn't appear in the result list as they are not in the Access Area Group
        insertRawEventData(hash_id3, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);
        insertRawEventData(hash_id3, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);

        //These shouldn't appear as they are in the exclusive tac group
        insertRawEventData(hash_id1, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_3, SAMPLE_EXCLUSIVE_TAC);
        insertRawEventData(hash_id1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_3,
                SAMPLE_EXCLUSIVE_TAC);
    }

    private void populatetablesForAggQueries() throws SQLException {
        final String timeStamp = DateTimeUtilities.getDateTimeMinus48Hours().concat(".0");

        insertAggregateEventData(hash_id1, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp);
        insertAggregateEventData(hash_id1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp);
        insertAggregateEventData(hash_id2, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp);
        insertAggregateEventData(hash_id2, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp);

        //These shouldn't appear in the result list as they are not in the Access Area Group
        insertAggregateEventData(hash_id3, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp);
        insertAggregateEventData(hash_id3, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp);

        //Populate raw data for the agg query impacted subs
        insertRawEventData(hash_id1, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);
        insertRawEventData(hash_id1, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_2, SAMPLE_TAC);
        insertRawEventData(hash_id2, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_3, SAMPLE_TAC);

        insertRawEventData(hash_id1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI, SAMPLE_TAC);
        insertRawEventData(hash_id1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_2, SAMPLE_TAC);

        //These shouldn't appear as they are in the exclusive tac group
        insertRawEventData(hash_id1, CALL_DROP_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_3, SAMPLE_EXCLUSIVE_TAC);
        insertRawEventData(hash_id1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, timeStamp, SAMPLE_IMSI_3,
                SAMPLE_EXCLUSIVE_TAC);
    }

    @Test
    public void test2FailureEventsForBothEventTypes() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, "+0100");
        requestParameters.add(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.add(TYPE_PARAM, TYPE_CELL);
        requestParameters.add(GROUP_NAME_PARAM, "sampleGroup");
        requestParameters.add(DISPLAY_PARAM, GRID);
        requestParameters.add(MAX_ROWS, Integer.toString(100));

        final String result = runQuery(service, requestParameters);
        final int expectedCallDropFailures = 2;
        final int expectedCallSetupFailures = 2;
        final int expectedImpactedSubs = 1;
        validateResult(result, expectedCallDropFailures, expectedCallSetupFailures, expectedImpactedSubs,
                expectedImpactedSubs);
    }

    @Test
    public void test2FailureEventsForBothEventTypesAggregatedData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, "+0100");
        requestParameters.add(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.add(TYPE_PARAM, TYPE_CELL);
        requestParameters.add(GROUP_NAME_PARAM, "sampleGroup");
        requestParameters.add(DISPLAY_PARAM, GRID);
        requestParameters.add(MAX_ROWS, Integer.toString(100));

        final String result = runQuery(service, requestParameters);
        final int expectedCallDropFailures = 4;
        final int expectedCallSetupFailures = 4;
        final int expectedImpactedSubsCSF = 2;
        final int expectedImpactedSubsCD = 3;
        validateResult(result, expectedCallDropFailures, expectedCallSetupFailures, expectedImpactedSubsCSF,
                expectedImpactedSubsCD);
    }

    private void validateResult(final String json, final int expectedCallDropFailures,
            final int expectedCallSetupFailures, final int expectedImpactedSubsCSF, final int expectedImpactedSubsCD)
            throws Exception {

        final List<NetworkEventAnalysisAccessAreaGroupResult> results = getTranslator().translateResult(json,
                NetworkEventAnalysisAccessAreaGroupResult.class);

        validateCommonValues(results);

        for (final NetworkEventAnalysisAccessAreaGroupResult result : results) {
            if (result.getEventID() == 438) {
                assertEquals(expectedCallDropFailures, result.getFailures());
                assertEquals(expectedImpactedSubsCD, result.getImpactedSubscribers());
            } else if (result.getEventID() == 456) {
                assertEquals(expectedCallSetupFailures, result.getFailures());
                assertEquals(expectedImpactedSubsCSF, result.getImpactedSubscribers());
            }
        }
    }

    /**
     * @param results
     */
    private void validateCommonValues(final List<NetworkEventAnalysisAccessAreaGroupResult> results) {
        assertThat(results.size(), is(2));//Should always be 2 (call setup failures and call drops)

        for (final NetworkEventAnalysisAccessAreaGroupResult result : results) {
            assertTrue("EventId is incorrect " + result.getEventID(),
                    result.getEventID() == CALL_DROP_EVENT_ID_AS_INTEGER
                            || result.getEventID() == CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);
            assertTrue("GroupName is incorrect " + result.getGroupName(), result.getGroupName().equals("sampleGroup"));
            assertTrue("EventType is incorrect " + result.getEventType(),
                    result.getEventType().equals("Call Setup Failures") || result.getEventType().equals("Call Drops"));
        }
    }

    /**
     * @param hashId 
     * @throws SQLException 
     * 
     */
    private void insertAggregateEventData(final String hashId, final int eventId, final String datetime_id)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_CELL_ID, hashId);
        valuesForTable.put(EVENT_ID, eventId);
        valuesForTable.put(DATETIME_ID, datetime_id);
        valuesForTable.put(NO_OF_ERRORS, 2);

        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID, valuesForTable);

    }

    /**
     * @param hashId 
     * @throws SQLException 
     * 
     */
    private void insertRawEventData(final String hashId, final int eventId, final String datetime_id, final long imsi,
            final int tac) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_CELL_ID, hashId);
        valuesForTable.put(IMSI, imsi);
        valuesForTable.put(EVENT_ID, eventId);
        valuesForTable.put(DATETIME_ID, datetime_id);
        valuesForTable.put(TAC, tac);

        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);

    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertDataIntoAccessAreaGroupTable(final String hashId) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(GROUP_NAME, "sampleGroup");
        valuesForTable.put(HIER3_CELL_ID, hashId);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321_CELL, valuesForTable);
    }

    /**
     * Creates the tables required for this query
     * @throws SQLException 
     */
    private void createOtherTables() throws SQLException {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, getRawTableColumns());
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID, getAggTableColumns());
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321_CELL, getAccessAreaGroupColumns());
        createTemporaryTable(GROUP_TYPE_TAC, getGrouptypeTacColumns());

    }

    /**
     * @return
     */
    private Map<String, Nullable> getGrouptypeTacColumns() {
        final Map<String, Nullable> valuesForGroupTable = new HashMap<String, Nullable>();
        valuesForGroupTable.put(TAC, Nullable.CANNOT_BE_NULL);
        valuesForGroupTable.put(GROUP_NAME, Nullable.CANNOT_BE_NULL);
        return valuesForGroupTable;
    }

    /**
     * @return
     */
    private Map<String, Nullable> getAccessAreaGroupColumns() {
        final Map<String, Nullable> valuesForGroupTable = new HashMap<String, Nullable>();
        valuesForGroupTable.put(HIER3_CELL_ID, Nullable.CANNOT_BE_NULL);
        valuesForGroupTable.put(GROUP_NAME, Nullable.CANNOT_BE_NULL);
        return valuesForGroupTable;
    }

    /**
    *
    * @return
    */
    private Map<String, Nullable> getRawTableColumns() {
        final Map<String, Nullable> valuesForEventTable = new HashMap<String, Nullable>();
        valuesForEventTable.put(IMSI, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER3_CELL_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(TAC, Nullable.CANNOT_BE_NULL);
        return valuesForEventTable;
    }

    private Map<String, Nullable> getAggTableColumns() {
        final Map<String, Nullable> valuesForEventTable = new HashMap<String, Nullable>();
        valuesForEventTable.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER3_CELL_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(NO_OF_ERRORS, Nullable.CANNOT_BE_NULL);
        return valuesForEventTable;
    }

    private void createAndPopulateLookupTables() throws Exception {
        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
    }

}
