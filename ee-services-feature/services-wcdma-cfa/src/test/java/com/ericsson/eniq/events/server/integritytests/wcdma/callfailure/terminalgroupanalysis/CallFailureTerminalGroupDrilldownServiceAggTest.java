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

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.terminalgroupanalysis.CallFailureTerminalGroupAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureTerminalGroupDrilldownResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author EEMECOY
 *
 */
public class CallFailureTerminalGroupDrilldownServiceAggTest extends
        BaseDataIntegrityTest<CallFailureTerminalGroupDrilldownResult> {
    private CallFailureTerminalGroupAnalysisService terminalGroupService;

    private final int numFailuresForSampleTac = 3;

    private final int numFailuresForSampleTac2 = 2;

    private final int numFailuresForDummyTac = 1;

    @Before
    public void setup() throws Exception {
        terminalGroupService = new CallFailureTerminalGroupAnalysisService();
        attachDependencies(terminalGroupService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createAndPopulateLookupTable(TEMP_DIM_E_SGEH_TAC);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC_2);
        populateTacGroupTable(SAMPLE_TAC_GROUP, DUMMY_TAC);
        createAggTable();
        insertEventData();
    }

    @Test
    public void testGetTerminalGroupAnalysisForSampleTacGroup() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(GROUP_NAME_PARAM, SAMPLE_TAC_GROUP);
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(EVENT_ID, CALL_DROP_EVENT_ID);
        final String json = runQuery(terminalGroupService, requestParameters);
        validateResultForRegularTacGroup(json);
    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus48Hours();
        insertRowIntoAggTable(SAMPLE_TAC, numFailuresForSampleTac, timestamp);
        insertRowIntoAggTable(SAMPLE_TAC_2, numFailuresForSampleTac2, timestamp);
        insertRowIntoAggTable(DUMMY_TAC, numFailuresForDummyTac, timestamp);

    }

    private void insertRowIntoAggTable(final int tac, final int numFailures, final String timestamp)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, tac);
        values.put(EVENT_ID, CALL_DROP_EVENT_ID);
        values.put(NO_OF_ERRORS, numFailures);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_DAY, values);

    }

    private void createAggTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(TAC);
        columns.add(NO_OF_ERRORS);
        columns.add(EVENT_ID);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_DAY, columns);
    }

    private void validateResultForRegularTacGroup(final String json) throws Exception {

        validateAgainstGridDefinition(json, "DRILL_CHART_TERMINAL_GROUP_ANALYSIS_WCDMA_CALL_FAILURE_MOST_CALL_DROPS");
        final List<CallFailureTerminalGroupDrilldownResult> results = getTranslator().translateResult(json,
                CallFailureTerminalGroupDrilldownResult.class);
        assertThat(results.size(), is(3));
        final CallFailureTerminalGroupDrilldownResult resultForSampleTac = results.get(0);
        assertThat(resultForSampleTac.getTac(), is(SAMPLE_TAC));
        assertThat(resultForSampleTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(resultForSampleTac.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(resultForSampleTac.getNumFailures(), is(numFailuresForSampleTac));

        final CallFailureTerminalGroupDrilldownResult resultForSecondSampleTac = results.get(1);
        assertThat(resultForSecondSampleTac.getTac(), is(SAMPLE_TAC_2));
        assertThat(resultForSecondSampleTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC_2));
        assertThat(resultForSecondSampleTac.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC_2));
        assertThat(resultForSecondSampleTac.getNumFailures(), is(numFailuresForSampleTac2));

        final CallFailureTerminalGroupDrilldownResult resultForDummyTac = results.get(2);
        assertThat(resultForDummyTac.getTac(), is(DUMMY_TAC));
        assertThat(resultForDummyTac.getManufacturer(), is(EMPTY_STRING));
        assertThat(resultForDummyTac.getMarketingName(), is(EMPTY_STRING));
        assertThat(resultForDummyTac.getNumFailures(), is(numFailuresForDummyTac));
    }

}
