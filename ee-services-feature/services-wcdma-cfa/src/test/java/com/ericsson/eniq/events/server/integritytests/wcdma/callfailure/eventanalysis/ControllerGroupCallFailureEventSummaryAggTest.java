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

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.NetworkEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.ControllerGroupCallFailureEventSummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author epesmit
 * @since 2011
 */

public class ControllerGroupCallFailureEventSummaryAggTest extends
        BaseDataIntegrityTest<ControllerGroupCallFailureEventSummaryResult> {

    private NetworkEventAnalysisService networkEventAnalysisService;

    private final int numFailuresForCallDropsEvent = 3;

    private final int numFailuresForCallSetupFailureEvent = 5;

    @Before
    public void onSetUp() throws Exception {
        networkEventAnalysisService = new NetworkEventAnalysisService();
        attachDependencies(networkEventAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);

        createTables();
        insertDataIntoTacGroupTable();
        insertDataIntoGroupTables();
        insertEventData();
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();

        columnsForTable.add(IMSI);
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(TAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, columnsForTable);

        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);

        columnsForTable.clear();
        columnsForTable.add(MANUFACTURER);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_DAY, columnsForTable);
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void insertDataIntoGroupTables() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(HIER3_ID, TEST_VALUE_GSM_HIER3_ID_BSC1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_BSC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_GSM_HIER3_ID_BSC2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_BSC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);
    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus48Hours();

        // Use of GSM related constant to avoid adding new constants
        insertRowIntoRawEventTable(TEST_VALUE_GSM_HIER3_ID_BSC1, CALL_DROP_EVENT_ID, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(TEST_VALUE_GSM_HIER3_ID_BSC1, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(TEST_VALUE_GSM_HIER3_ID_BSC2, CALL_DROP_EVENT_ID, SAMPLE_TAC, timestamp);
        insertRowIntoRawEventTable(TEST_VALUE_GSM_HIER3_ID_BSC2, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC, timestamp);

        insertRowIntoAggTable(TEST_VALUE_GSM_HIER3_ID_BSC1, CALL_DROP_EVENT_ID, numFailuresForCallDropsEvent, timestamp);
        insertRowIntoAggTable(TEST_VALUE_GSM_HIER3_ID_BSC1, CALL_SETUP_FAILURE_EVENT_ID,
                numFailuresForCallSetupFailureEvent, timestamp);
        insertRowIntoAggTable(TEST_VALUE_GSM_HIER3_ID_BSC2, CALL_DROP_EVENT_ID, numFailuresForCallDropsEvent, timestamp);
        insertRowIntoAggTable(TEST_VALUE_GSM_HIER3_ID_BSC2, CALL_SETUP_FAILURE_EVENT_ID,
                numFailuresForCallSetupFailureEvent, timestamp);
    }

    private void insertRowIntoRawEventTable(final long hier3Id, final String eventId, final long tac,
            final String timestamp) throws SQLException {
        final Map<String, Object> valuesForRawEventTable = new HashMap<String, Object>();
        valuesForRawEventTable.put(HIER3_ID, hier3Id);
        valuesForRawEventTable.put(TAC, tac);
        valuesForRawEventTable.put(EVENT_ID, eventId);
        valuesForRawEventTable.put(DATETIME_ID, timestamp);
        valuesForRawEventTable.put(IMSI_PARAM, SAMPLE_IMSI);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForRawEventTable);
    }

    private void insertRowIntoAggTable(final long hier3Id, final String eventId, final int numFailures,
            final String timestamp) throws SQLException {
        final Map<String, Object> valuesForAggEventTable = new HashMap<String, Object>();
        valuesForAggEventTable.put(HIER3_ID, hier3Id);
        valuesForAggEventTable.put(EVENT_ID, eventId);
        valuesForAggEventTable.put(NO_OF_ERRORS, numFailures);
        valuesForAggEventTable.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_DAY, valuesForAggEventTable);

    }

    @Test
    public void testOneWeekQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_BSC_GROUP);
        final String result = runQuery(networkEventAnalysisService, requestParameters);
        verifyResult(result);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        final ResultTranslator<ControllerGroupCallFailureEventSummaryResult> rt = getTranslator();
        final List<ControllerGroupCallFailureEventSummaryResult> eventResult = rt.translateResult(json,
                ControllerGroupCallFailureEventSummaryResult.class);
        assertThat(eventResult.size(), is(2));

        final ControllerGroupCallFailureEventSummaryResult resultForCallDropsEvent = eventResult.get(0);
        assertThat(resultForCallDropsEvent.getEventType(), is(CALL_DROPS));
        assertThat(resultForCallDropsEvent.getNumOfFailures(), is("6"));
        assertThat(resultForCallDropsEvent.getImpactedSubscribers(), is("1"));

        final ControllerGroupCallFailureEventSummaryResult resultForCallSetupFailureEvent = eventResult.get(1);
        assertThat(resultForCallSetupFailureEvent.getEventType(), is(CALL_SETUP_FAILURES_EVENT_DESC));
        assertThat(resultForCallSetupFailureEvent.getNumOfFailures(), is("10"));
        assertThat(resultForCallSetupFailureEvent.getImpactedSubscribers(), is("1"));

    }
}
