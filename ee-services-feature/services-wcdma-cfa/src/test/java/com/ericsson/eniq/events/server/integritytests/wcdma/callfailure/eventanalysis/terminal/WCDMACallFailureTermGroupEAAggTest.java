/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.terminal;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.TerminalEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.WCDMACallFailureTerminalGroupEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 *
 */
public class WCDMACallFailureTermGroupEAAggTest extends
        BaseDataIntegrityTest<WCDMACallFailureTerminalGroupEventAnalysisResult> {

    private static final String GRID_DEFINITION = "TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC_GROUP";

    private TerminalEventAnalysisService terminalEventAnalysisService;

    @Before
    public void onSetUp() throws Exception {
        terminalEventAnalysisService = new TerminalEventAnalysisService();
        attachDependencies(terminalEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        insertAggregationData();
        populateSubscriberData();
    }

    /**
     * We should not get results from the Exclusive Tac Group
     * @throws Exception 
     */
    @Test
    public void testNonExclusiveTacGroup() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, "grid");
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TYPE_PARAM, TAC);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(GROUP_NAME_PARAM, SAMPLE_TAC_GROUP);
        requestParameters.putSingle(MAX_ROWS, "10");
        final String json = runQuery(terminalEventAnalysisService, requestParameters);
        validateAgainstGridDefinition(json, GRID_DEFINITION);

        getTranslator().translateResult(json, WCDMACallFailureTerminalGroupEventAnalysisResult.class);
        final int numberOfRows = 2;
        final int expectedNoOfErrors = 11;
        final int impactEdSubscribers = 2;
        validateResult(json, numberOfRows, expectedNoOfErrors, impactEdSubscribers);
    }

    /**
     * We are querying on the Exclusive Tac Group
     * @throws Exception 
     */
    @Test
    public void testExclusiveTacGroup() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, "grid");
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TYPE_PARAM, TAC);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(GROUP_NAME_PARAM, EXCLUSIVE_TAC_GROUP_NAME);
        requestParameters.putSingle(MAX_ROWS, "10");
        final String json = runQuery(terminalEventAnalysisService, requestParameters);
        final int numberOfRows = 2;
        final int expectedNoOfErrors = 1;
        final int impactEdSubscribers = 1;
        validateResult(json, numberOfRows, expectedNoOfErrors, impactEdSubscribers);
    }

    private void validateResult(final String json, final int expectedResults, final int expectedFailures,
            final int expectedSubscribers) throws Exception {
        validateAgainstGridDefinition(json, GRID_DEFINITION);

        final List<WCDMACallFailureTerminalGroupEventAnalysisResult> results = getTranslator().translateResult(json,
                WCDMACallFailureTerminalGroupEventAnalysisResult.class);

        assertThat(results.size(), is(expectedResults));
        final WCDMACallFailureTerminalGroupEventAnalysisResult rowWithCSF = getResultFor(
                CALL_SETUP_FAILURES_EVENT_DESC, results);
        assertEquals("Unexpected FailNum in result!", rowWithCSF.getNoOfErrors(), expectedFailures);
        assertEquals("Unexpected FailNum in result!", rowWithCSF.getImpactedSubscribers(), expectedSubscribers);

        final WCDMACallFailureTerminalGroupEventAnalysisResult rowWithCD = getResultFor(CALL_DROPS, results);
        assertEquals("Unexpected FailNum in result!", rowWithCD.getNoOfErrors(), expectedFailures);
        assertEquals("Unexpected FailNum in result!", rowWithCD.getImpactedSubscribers(), expectedSubscribers);
    }

    private WCDMACallFailureTerminalGroupEventAnalysisResult getResultFor(final String sampleEventType,
            final List<WCDMACallFailureTerminalGroupEventAnalysisResult> results) {
        for (final WCDMACallFailureTerminalGroupEventAnalysisResult result : results) {
            final String eventType = result.getEventDesc();
            if (eventType.equals(sampleEventType)) {
                return result;
            }
        }
        fail("Could not find result for TAC " + sampleEventType);
        return null;
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(TAC);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(NO_OF_ERRORS);
        columnsForEETable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_ERR_DAY, columnsForEETable);

        final Collection<String> columnsForRawTable = new ArrayList<String>();
        columnsForRawTable.add(TAC);
        columnsForRawTable.add(EVENT_ID);
        columnsForRawTable.add(IMSI);
        columnsForRawTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForRawTable);
    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();

        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);

        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    private void populateGroupTables() throws SQLException {
        // Insert exclusive TAC to #GROUP_TYPE_E_TAC
        final Map<String, Object> valuesForTacTable = new HashMap<String, Object>();
        valuesForTacTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        valuesForTacTable.put(TAC, SAMPLE_TAC_3);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTacTable);

        valuesForTacTable.clear();
        valuesForTacTable.put(GROUP_NAME, SAMPLE_TAC_GROUP);
        valuesForTacTable.put(TAC, SAMPLE_TAC_2);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTacTable);

        valuesForTacTable.clear();
        valuesForTacTable.put(GROUP_NAME, SAMPLE_TAC_GROUP);
        valuesForTacTable.put(TAC, SAMPLE_TAC);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTacTable);
    }

    private void insertAggregationData() throws Exception {
        final String dateTime = DateTimeUtilities.getDateTimeMinus36Hours();

        insertData(dateTime, String.valueOf(SAMPLE_TAC), CALL_SETUP_FAILURE_EVENT_ID, 6);
        insertData(dateTime, String.valueOf(SAMPLE_TAC), CALL_DROP_EVENT_ID, 6);
        insertData(dateTime, String.valueOf(SAMPLE_TAC_2), CALL_SETUP_FAILURE_EVENT_ID, 5);
        insertData(dateTime, String.valueOf(SAMPLE_TAC_2), CALL_DROP_EVENT_ID, 5);
        insertData(dateTime, String.valueOf(SAMPLE_TAC_3), CALL_SETUP_FAILURE_EVENT_ID, 4);
        insertData(dateTime, String.valueOf(SAMPLE_TAC_3), CALL_DROP_EVENT_ID, 1);

        populateGroupTables();
    }

    private void insertRawData(final String dateTime, final String tac, final String eventID, final String imsi)
            throws SQLException {

        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, tac);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(IMSI, imsi);
        valuesForTable.put(DATETIME_ID, dateTime);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
    }

    private void populateSubscriberData() throws Exception {
        final String dateTime = DateTimeUtilities.getDateTimeMinus36Hours();

        insertRawData(dateTime, String.valueOf(SAMPLE_TAC), CALL_SETUP_FAILURE_EVENT_ID, String.valueOf(SAMPLE_IMSI));
        insertRawData(dateTime, String.valueOf(SAMPLE_TAC), CALL_DROP_EVENT_ID, String.valueOf(SAMPLE_IMSI));
        insertRawData(dateTime, String.valueOf(SAMPLE_TAC_2), CALL_SETUP_FAILURE_EVENT_ID,
                String.valueOf(SAMPLE_IMSI_2));
        insertRawData(dateTime, String.valueOf(SAMPLE_TAC_2), CALL_DROP_EVENT_ID, String.valueOf(SAMPLE_IMSI_2));
        insertRawData(dateTime, String.valueOf(SAMPLE_TAC_3), CALL_SETUP_FAILURE_EVENT_ID,
                String.valueOf(SAMPLE_IMSI_3));
        insertRawData(dateTime, String.valueOf(SAMPLE_TAC_3), CALL_DROP_EVENT_ID, String.valueOf(SAMPLE_IMSI_3));

        populateGroupTables();
    }

    private void insertData(final String dt, final String tac, final String eventID, final int errNum)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, tac);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(NO_OF_ERRORS, errNum);
        valuesForTable.put(DATETIME_ID, dt);
        insertRow(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_ERR_DAY, valuesForTable);
    }
}
