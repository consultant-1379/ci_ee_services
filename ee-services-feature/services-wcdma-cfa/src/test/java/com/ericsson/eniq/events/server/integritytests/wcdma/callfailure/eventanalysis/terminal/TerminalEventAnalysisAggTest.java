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
import com.ericsson.eniq.events.server.test.queryresults.TerminalEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 *
 */
public class TerminalEventAnalysisAggTest extends BaseDataIntegrityTest<TerminalEventAnalysisResult> {

    private static final String TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC = "TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC";

    private TerminalEventAnalysisService terminalEventAnalysisService;

    private static final int TEST_IMSI_1 = 11111101;

    private static final int TEST_IMSI_2 = 11111102;

    private static final String TEST_MARKETING_NAME_4 = "9109PA";

    private static final int TEST_TAC = 1280600; //belongs to: Apple, iPad 2 A1396

    private static final int TEST_TAC_2 = 1066000; //belongs to: Danger Inc, PV-100C

    private static final int TEST_TAC_3 = 102500; //belongs to: Option International, Fizgig 

    private static final int TEST_TAC_4 = 33000353; //belongs to: Alcatel Radiotelephone, 9109PA

    @Before
    public void onSetUp() throws Exception {
        terminalEventAnalysisService = new TerminalEventAnalysisService();
        attachDependencies(terminalEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        insertData();
    }

    /*
     * CallSetupFailures have 8 FailNum and 3 impacted subscribers
     * CallDrops have 3 FailNum and 8 impacted subscribers
     */
    @Test
    public void testTerminalEventAnalysisCFA() throws Exception {
        final String json = runTACEventAnalysisQuery(TEST_MARKETING_NAME_4 + COMMA + TEST_TAC_4);
        validateResultsForRegularTAC(json);
    }

    private String runTACEventAnalysisQuery(final String nodeParameter) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TYPE_PARAM, TAC);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(NODE_PARAM, nodeParameter);
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(terminalEventAnalysisService, requestParameters);
        System.out.println(json);
        return json;
    }

    private void validateResultsForRegularTAC(final String json) throws Exception {
        validateAgainstGridDefinition(json, TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC);
        final List<TerminalEventAnalysisResult> results = getTranslator().translateResult(json,
                TerminalEventAnalysisResult.class);

        assertThat(results.size(), is(2));
        final TerminalEventAnalysisResult rowWithCSF = getResultFor(CALL_SETUP_FAILURES_EVENT_DESC, results);
        assertTrue("Unexpected FailNum in result!", rowWithCSF.getFailures() == 8);
        assertEquals("Unexpected ImpactedSubscribers in result!", 1, rowWithCSF.getImpactedSubscribers());

        final TerminalEventAnalysisResult rowWithCD = getResultFor(CALL_DROPS, results);
        assertTrue("Unexpected FailNum in result!", rowWithCD.getFailures() == 3);
        assertTrue("Unexpected ImpactedSubscribers in result!", rowWithCD.getImpactedSubscribers() == 1);
    }

    private TerminalEventAnalysisResult getResultFor(final String sampleEventType,
            final List<TerminalEventAnalysisResult> results) {
        for (final TerminalEventAnalysisResult result : results) {
            final String eventType = result.getEventType();
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

        final Collection<String> columnsForERTable = new ArrayList<String>();
        columnsForERTable.add(TAC);
        columnsForERTable.add(EVENT_ID);
        columnsForERTable.add(IMSI);
        columnsForERTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForERTable);
    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();

        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);

        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    private void insertData() throws Exception {
        final String dateTime = DateTimeUtilities.getDateTimeMinus36Hours();

        insertDataIntoAggTable(dateTime, TEST_TAC, CALL_SETUP_FAILURE_EVENT_ID, 6);
        insertDataIntoAggTable(dateTime, TEST_TAC, CALL_DROP_EVENT_ID, 3);
        insertDataIntoAggTable(dateTime, TEST_TAC_2, CALL_SETUP_FAILURE_EVENT_ID, 5);
        insertDataIntoAggTable(dateTime, TEST_TAC_2, CALL_DROP_EVENT_ID, 2);
        insertDataIntoAggTable(dateTime, TEST_TAC_3, CALL_SETUP_FAILURE_EVENT_ID, 4);
        insertDataIntoAggTable(dateTime, TEST_TAC_3, CALL_DROP_EVENT_ID, 1);
        insertDataIntoAggTable(dateTime, TEST_TAC_4, CALL_SETUP_FAILURE_EVENT_ID, 8);
        insertDataIntoAggTable(dateTime, TEST_TAC_4, CALL_DROP_EVENT_ID, 3);
        insertDataIntoAggTable(dateTime, SAMPLE_EXCLUSIVE_TAC, CALL_SETUP_FAILURE_EVENT_ID, 2);
        insertDataIntoAggTable(dateTime, SAMPLE_EXCLUSIVE_TAC, CALL_SETUP_FAILURE_EVENT_ID, 3);

        insertRawData(dateTime, TEST_TAC, CALL_DROP_EVENT_ID, TEST_IMSI_1);
        insertRawData(dateTime, TEST_TAC, CALL_SETUP_FAILURE_EVENT_ID, TEST_IMSI_1);
        insertRawData(dateTime, TEST_TAC_2, CALL_DROP_EVENT_ID, TEST_IMSI_1);
        insertRawData(dateTime, TEST_TAC_2, CALL_SETUP_FAILURE_EVENT_ID, TEST_IMSI_1);
        insertRawData(dateTime, TEST_TAC_3, CALL_DROP_EVENT_ID, TEST_IMSI_2);
        insertRawData(dateTime, TEST_TAC_3, CALL_SETUP_FAILURE_EVENT_ID, TEST_IMSI_2);
        insertRawData(dateTime, TEST_TAC_4, CALL_DROP_EVENT_ID, TEST_IMSI_2);
        insertRawData(dateTime, TEST_TAC_4, CALL_SETUP_FAILURE_EVENT_ID, TEST_IMSI_2);
        insertRawData(dateTime, SAMPLE_EXCLUSIVE_TAC, CALL_SETUP_FAILURE_EVENT_ID, TEST_IMSI_1);
        insertRawData(dateTime, SAMPLE_EXCLUSIVE_TAC, CALL_SETUP_FAILURE_EVENT_ID, TEST_IMSI_2);

    }

    private void insertDataIntoAggTable(final String dateTime, final int tac, final String eventID,
            final int numberOfErrors) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, tac);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(NO_OF_ERRORS, numberOfErrors);
        valuesForTable.put(DATETIME_ID, dateTime);
        insertRow(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_ERR_DAY, valuesForTable);
    }

    private void insertRawData(final String dateTime, final int tac, final String eventID, final int imsi)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, tac);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(IMSI, imsi);
        valuesForTable.put(DATETIME_ID, dateTime);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
    }
}
