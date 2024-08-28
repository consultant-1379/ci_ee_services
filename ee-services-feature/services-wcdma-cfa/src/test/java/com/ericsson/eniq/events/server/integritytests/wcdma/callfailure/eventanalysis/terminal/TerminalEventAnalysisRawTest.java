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
public class TerminalEventAnalysisRawTest extends BaseDataIntegrityTest<TerminalEventAnalysisResult> {

    private static final String TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC = "TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC";

    private TerminalEventAnalysisService terminalEventAnalysisService;

    @Before
    public void onSetUp() throws Exception {
        terminalEventAnalysisService = new TerminalEventAnalysisService();
        attachDependencies(terminalEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        insertData();
    }

    @Test
    public void testExclusiveTacQueryForOneWeekUsesTheRawTables() throws Exception {
        final String json = runTerminalEventAnalysisQuery(MARKETING_NAME_FOR_SAMPLE_EXCLUSIVE_TAC + COMMA
                + SAMPLE_EXCLUSIVE_TAC, ONE_WEEK);
        validateResultForExclusiveTAC(json);
    }

    private void validateResultForExclusiveTAC(final String json) throws Exception {
        final List<TerminalEventAnalysisResult> results = getTranslator().translateResult(json,
                TerminalEventAnalysisResult.class);
        assertThat(results.size(), is(1));
        final TerminalEventAnalysisResult callDropsData = results.get(0);
        assertThat(callDropsData.getEventType(), is(CALL_DROPS));
        assertThat(callDropsData.getFailures(), is(2));
        assertThat(callDropsData.getImpactedSubscribers(), is(2));
        assertThat(callDropsData.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_EXCLUSIVE_TAC));
        assertThat(callDropsData.getModel(), is(MARKETING_NAME_FOR_SAMPLE_EXCLUSIVE_TAC));

    }

    /*
     * TEST_TAC has 3 CallDrops entries in the #RAW table and 2 CallSetupFailures 
     * and only 1 impacted subscriber for both
     */
    @Test
    public void testTerminalEventAnalysisCFA() throws Exception {
        final String json = runTerminalEventAnalysisQuery(MARKETING_NAME_FOR_SAMPLE_TAC + COMMA + SAMPLE_TAC,
                FIVE_MINUTES);
        validateResult(json);
    }

    private String runTerminalEventAnalysisQuery(final String nodeParameter, final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TYPE_PARAM, TAC);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(NODE_PARAM, nodeParameter);
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(terminalEventAnalysisService, requestParameters);
        System.out.println(json);
        return json;
    }

    private void validateResult(final String json) throws Exception {
        validateAgainstGridDefinition(json, TERMINAL_EVENT_ANALYSIS_WCDMA_CFA_SUMMARY_TAC);

        final List<TerminalEventAnalysisResult> results = getTranslator().translateResult(json,
                TerminalEventAnalysisResult.class);

        assertThat(results.size(), is(2));
        final TerminalEventAnalysisResult rowWithCSF = getResultFor(CALL_SETUP_FAILURES_EVENT_DESC, results);
        assertTrue("Unexpected FailNum in result!", rowWithCSF.getFailures() == 2);
        assertTrue("Unexpected FailNum in result!", rowWithCSF.getImpactedSubscribers() == 1);

        final TerminalEventAnalysisResult rowWithCD = getResultFor(CALL_DROPS, results);
        assertTrue("Unexpected FailNum in result!", rowWithCD.getFailures() == 3);
        assertTrue("Unexpected FailNum in result!", rowWithCD.getImpactedSubscribers() == 1);
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
        columnsForEETable.add(IMSI);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEETable);
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
        final String dateTimeForTwoMinutesAgo = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertData(dateTimeForTwoMinutesAgo, CALL_DROP_EVENT_ID, SAMPLE_TAC, SAMPLE_IMSI);
        insertData(dateTimeForTwoMinutesAgo, CALL_DROP_EVENT_ID, SAMPLE_TAC, SAMPLE_IMSI);
        insertData(dateTimeForTwoMinutesAgo, CALL_DROP_EVENT_ID, SAMPLE_TAC, SAMPLE_IMSI);
        insertData(dateTimeForTwoMinutesAgo, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC, SAMPLE_IMSI);
        insertData(dateTimeForTwoMinutesAgo, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC, SAMPLE_IMSI);

        insertData(dateTimeForTwoMinutesAgo, CALL_DROP_EVENT_ID, SAMPLE_TAC_2, SAMPLE_IMSI);
        insertData(dateTimeForTwoMinutesAgo, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC_2, SAMPLE_IMSI);

        final String dateTimeMinus48Hours = DateTimeUtilities.getDateTimeMinus48Hours();
        insertData(dateTimeMinus48Hours, CALL_DROP_EVENT_ID, SAMPLE_EXCLUSIVE_TAC, SAMPLE_IMSI);
        insertData(dateTimeMinus48Hours, CALL_DROP_EVENT_ID, SAMPLE_EXCLUSIVE_TAC, SAMPLE_IMSI_2);

    }

    private void insertData(final String dateTime, final String eventId, final int tac, final long imsi)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(DATETIME_ID, dateTime);
        values.put(EVENT_ID, eventId);
        values.put(TAC, tac);
        values.put(IMSI, imsi);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, values);

    }

}
