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
public class WCDMACallFailureTerminalGroupEARawTest extends
        BaseDataIntegrityTest<WCDMACallFailureTerminalGroupEventAnalysisResult> {

    private static final String GRID_DEFINITION = "TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC_GROUP";

    private TerminalEventAnalysisService terminalEventAnalysisService;

    @Before
    public void onSetUp() throws Exception {
        terminalEventAnalysisService = new TerminalEventAnalysisService();
        attachDependencies(terminalEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        populateEventData();
    }

    /**
     * We should not get results from the Exclusive Tac Group
     * @throws Exception 
     */
    @Test
    public void testNonExclusiveTacGroup() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TYPE_PARAM, TAC);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(GROUP_NAME_PARAM, SAMPLE_TAC_GROUP);
        requestParameters.putSingle(MAX_ROWS, "10");
        final String json = runQuery(terminalEventAnalysisService, requestParameters);
        validateResult(json, 2, 2, 2);
    }

    /**
     * We are querying on the Exclusive Tac Group
     * @throws Exception 
     */
    @Test
    public void testExclusiveTacGroup() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TYPE_PARAM, TAC);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(GROUP_NAME_PARAM, EXCLUSIVE_TAC_GROUP_NAME);
        requestParameters.putSingle(MAX_ROWS, "10");
        final String json = runQuery(terminalEventAnalysisService, requestParameters);
        validateResult(json, 2, 1, 1);
    }

    private void validateResult(final String json, final int expectedResults, final int expectedFailures,
            final int expectedSubscribers) throws Exception {
        validateAgainstGridDefinition(json, GRID_DEFINITION);

        final List<WCDMACallFailureTerminalGroupEventAnalysisResult> results = getTranslator().translateResult(json,
                WCDMACallFailureTerminalGroupEventAnalysisResult.class);

        assertThat(results.size(), is(expectedResults));
        final WCDMACallFailureTerminalGroupEventAnalysisResult rowWithCSF = getResultFor(
                CALL_SETUP_FAILURES_EVENT_DESC, results);
        assertEquals("Wrong No of Errors in result!", expectedFailures, rowWithCSF.getNoOfErrors());
        assertEquals("Wrong No of Impacted Subscribers in result!", expectedSubscribers,
                rowWithCSF.getImpactedSubscribers());

        final WCDMACallFailureTerminalGroupEventAnalysisResult rowWithCD = getResultFor(CALL_DROPS, results);
        assertEquals("Wrong No of Errors in result!", expectedFailures, rowWithCD.getNoOfErrors());
        assertEquals("Wrong No of Impacted Subscribers in result!", expectedSubscribers,
                rowWithCD.getImpactedSubscribers());
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

    private void insertEventData(final String dateTime, final String tac, final String eventID, final String imsi)
            throws SQLException {

        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, tac);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(IMSI, imsi);
        valuesForTable.put(DATETIME_ID, dateTime);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
    }

    private void populateEventData() throws Exception {
        final String dateTime = DateTimeUtilities.getDateTimeMinus25Minutes();

        insertEventData(dateTime, String.valueOf(SAMPLE_TAC), CALL_SETUP_FAILURE_EVENT_ID, String.valueOf(SAMPLE_IMSI));
        insertEventData(dateTime, String.valueOf(SAMPLE_TAC), CALL_DROP_EVENT_ID, String.valueOf(SAMPLE_IMSI));
        insertEventData(dateTime, String.valueOf(SAMPLE_TAC_2), CALL_SETUP_FAILURE_EVENT_ID,
                String.valueOf(SAMPLE_IMSI_2));
        insertEventData(dateTime, String.valueOf(SAMPLE_TAC_2), CALL_DROP_EVENT_ID, String.valueOf(SAMPLE_IMSI_2));
        insertEventData(dateTime, String.valueOf(SAMPLE_TAC_3), CALL_SETUP_FAILURE_EVENT_ID,
                String.valueOf(SAMPLE_IMSI_3));
        insertEventData(dateTime, String.valueOf(SAMPLE_TAC_3), CALL_DROP_EVENT_ID, String.valueOf(SAMPLE_IMSI_3));

        populateGroupTables();
    }
}
