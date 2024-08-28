/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.terminalgroupanalysis;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.terminalgroupanalysis.CallFailureTerminalGroupAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureTerminalGroupAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author EEMECOY
 *
 */
public class CallFailureTerminalGroupAnalysisServiceRawTest extends
        BaseDataIntegrityTest<CallFailureTerminalGroupAnalysisResult> {

    private CallFailureTerminalGroupAnalysisService terminalGroupAnalysisService;

    @Before
    public void setup() throws Exception {
        terminalGroupAnalysisService = new CallFailureTerminalGroupAnalysisService();
        attachDependencies(terminalGroupAnalysisService);
        createRawEventTable();
        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC);
        populateTacGroupTable(SAMPLE_TAC_GROUP_2, SAMPLE_TAC_2);
        populateTacGroupTable(SAMPLE_TAC_GROUP_3, SAMPLE_TAC_3);

        insertEventData();
    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();
        insertRowIntoEventTable(SAMPLE_IMSI, CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(SAMPLE_IMSI, CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp);
        insertRowIntoEventTable(SAMPLE_IMSI_2, CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp);

        insertRowIntoEventTable(SAMPLE_IMSI, CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp);
        insertRowIntoEventTable(SAMPLE_IMSI, CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp);

        insertRowIntoEventTable(SAMPLE_IMSI, CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC_2, timestamp);

    }

    private void insertRowIntoEventTable(final long imsi, final int eventId, final int tac, final String timestamp)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, imsi);
        values.put(EVENT_ID, eventId);
        values.put(TAC, tac);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
    }

    private void createRawEventTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
    }

    @Test
    public void testGetTerminalGroupAnalysis() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(EVENT_ID, CALL_DROP_EVENT_ID);
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String result = runQuery(terminalGroupAnalysisService, requestParameters);
        validateResult(result);
    }

    private void validateResult(final String json) throws Exception {
        final List<CallFailureTerminalGroupAnalysisResult> results = getTranslator().translateResult(json,
                CallFailureTerminalGroupAnalysisResult.class);
        validateAgainstGridDefinition(json, "TERMINAL_GROUP_ANALYSIS_RAN_WCDMA_CFA_CALL_DROPS");
        assertThat(results.size(), is(3));

        final CallFailureTerminalGroupAnalysisResult sampleTacGroupData = results.get(0);
        assertThat(sampleTacGroupData.getRankNumber(), is(1));
        assertThat(sampleTacGroupData.getNumFailures(), is(3));
        assertThat(sampleTacGroupData.getImpactedSubscribers(), is(2));
        assertThat(sampleTacGroupData.getGroupName(), is(SAMPLE_TAC_GROUP));

        final CallFailureTerminalGroupAnalysisResult exclusiveTacGroupData = results.get(1);
        assertThat(sampleTacGroupData.getRankNumber(), is(1));
        assertThat(exclusiveTacGroupData.getNumFailures(), is(2));
        assertThat(exclusiveTacGroupData.getImpactedSubscribers(), is(1));
        assertThat(exclusiveTacGroupData.getGroupName(), is(EXCLUSIVE_TAC_GROUP));

        final CallFailureTerminalGroupAnalysisResult sampleTacGroup2Data = results.get(2);
        assertThat(sampleTacGroupData.getRankNumber(), is(1));
        assertThat(sampleTacGroup2Data.getNumFailures(), is(1));
        assertThat(sampleTacGroup2Data.getImpactedSubscribers(), is(1));
        assertThat(sampleTacGroup2Data.getGroupName(), is(SAMPLE_TAC_GROUP_2));

    }

}
