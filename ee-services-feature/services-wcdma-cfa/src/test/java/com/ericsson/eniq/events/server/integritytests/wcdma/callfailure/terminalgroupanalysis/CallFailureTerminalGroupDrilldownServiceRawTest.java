/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.terminalgroupanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DIM_E_SGEH_TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EMPTY_STRING;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GROUP_NAME_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID_AS_INTEGER;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DUMMY_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXCLUSIVE_TAC_GROUP;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIVE_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MANUFACTURER_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MANUFACTURER_FOR_SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_EXCLUSIVE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_WEEK;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_EXCLUSIVE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_GROUP;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_TAC;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
public class CallFailureTerminalGroupDrilldownServiceRawTest extends
        BaseDataIntegrityTest<CallFailureTerminalGroupDrilldownResult> {
    private CallFailureTerminalGroupAnalysisService terminalGroupService;

    @Before
    public void setup() throws Exception {
        terminalGroupService = new CallFailureTerminalGroupAnalysisService();
        attachDependencies(terminalGroupService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createRawTable();
        createAndPopulateLookupTable(TEMP_DIM_E_SGEH_TAC);
        insertData();
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC_2);
        populateTacGroupTable(SAMPLE_TAC_GROUP, DUMMY_TAC);

    }

    private void insertData() throws SQLException {
        insertRow(SAMPLE_TAC);
        insertRow(SAMPLE_TAC);
        insertRow(SAMPLE_TAC);

        insertRow(SAMPLE_TAC_2);
        insertRow(SAMPLE_TAC_2);

        insertRow(DUMMY_TAC);

        insertRow(SAMPLE_EXCLUSIVE_TAC);
        insertRow(SAMPLE_EXCLUSIVE_TAC);
    }

    private void insertRow(final int tac) throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, tac);
        values.put(EVENT_ID, CALL_DROP_EVENT_ID_AS_INTEGER);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);

    }

    private void createRawTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(TAC);
        columns.add(EVENT_ID);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
    }

    @Test
    public void testGetTerminalGroupAnalysisForExclusiveTacGroup_OneWeek_UsesRawTables() throws Exception {
        final String json = runGroupQuery(EXCLUSIVE_TAC_GROUP, ONE_WEEK);
        final List<CallFailureTerminalGroupDrilldownResult> results = translateResult(json);
        assertThat(results.size(), is(0));
    }

    private List<CallFailureTerminalGroupDrilldownResult> translateResult(final String json) throws Exception {
        return getTranslator().translateResult(json, CallFailureTerminalGroupDrilldownResult.class);

    }

    @Test
    public void testGetTerminalGroupAnalysisForExclusiveTacGroup_FiveMinutes() throws Exception {
        final String json = runGroupQuery(EXCLUSIVE_TAC_GROUP, FIVE_MINUTES);
        validateResultForExclusiveTacGroup(json);
    }

    private void validateResultForExclusiveTacGroup(final String json) throws Exception {
        final List<CallFailureTerminalGroupDrilldownResult> results = translateResult(json);
        assertThat(results.size(), is(1));
        final CallFailureTerminalGroupDrilldownResult exclusiveTacDetails = results.get(0);
        assertThat(exclusiveTacDetails.getTac(), is(SAMPLE_EXCLUSIVE_TAC));
        assertThat(exclusiveTacDetails.getManufacturer(), is("Option International"));
        assertThat(exclusiveTacDetails.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_EXCLUSIVE_TAC));
        assertThat(exclusiveTacDetails.getNumFailures(), is(2));
    }

    @Test
    public void testGetTerminalGroupAnalysisForSampleTacGroup() throws Exception {
        final String json = runGroupQuery(SAMPLE_TAC_GROUP, FIVE_MINUTES);
        validateResultForSampleTacGroup(json);
    }

    private String runGroupQuery(final String tacGroup, final String time) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(GROUP_NAME_PARAM, tacGroup);
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(EVENT_ID, CALL_DROP_EVENT_ID);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        return runQuery(terminalGroupService, requestParameters);
    }

    private void validateResultForSampleTacGroup(final String json) throws Exception {
        validateAgainstGridDefinition(json, "DRILL_CHART_TERMINAL_GROUP_ANALYSIS_WCDMA_CALL_FAILURE_MOST_CALL_DROPS");
        final List<CallFailureTerminalGroupDrilldownResult> results = translateResult(json);
        assertThat(results.size(), is(3));

        final CallFailureTerminalGroupDrilldownResult detailsForFirstSampleTac = results.get(0);
        assertThat(detailsForFirstSampleTac.getTac(), is(SAMPLE_TAC));
        assertThat(detailsForFirstSampleTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(detailsForFirstSampleTac.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(detailsForFirstSampleTac.getNumFailures(), is(3));

        final CallFailureTerminalGroupDrilldownResult detailsForSecondSampleTac = results.get(1);
        assertThat(detailsForSecondSampleTac.getTac(), is(SAMPLE_TAC_2));
        assertThat(detailsForSecondSampleTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC_2));
        assertThat(detailsForSecondSampleTac.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC_2));
        assertThat(detailsForSecondSampleTac.getNumFailures(), is(2));

        final CallFailureTerminalGroupDrilldownResult detailsForDummyTac = results.get(2);
        assertThat(detailsForDummyTac.getTac(), is(DUMMY_TAC));
        assertThat(detailsForDummyTac.getManufacturer(), is(EMPTY_STRING));
        assertThat(detailsForDummyTac.getMarketingName(), is(EMPTY_STRING));
        assertThat(detailsForDummyTac.getNumFailures(), is(1));

    }
}
