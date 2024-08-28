/**
* -----------------------------------------------------------------------
* COPYRIGHT Ericsson 2013 
* The copyright to the computer program(s) herein is the property of 
* Ericsson Inc. The programs may be used and/or copied only with written 
* permission from Ericsson Inc. or in accordance with the terms and 
* conditions stipulated in the agreement/contract under which the 
* program(s) have been supplied.
* -----------------------------------------------------------------------
*/
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.terminalanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*; 
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*; 
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.net.URISyntaxException; 
import java.sql.SQLException; 
import java.util.*;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Before; 
import org.junit.Test;
import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils; 
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest; 
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.terminalanalysis.CallFailureTerminalAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureTerminalAnalysisResult;
import com.ericsson.eniq.events.server.test.schema.Nullable; 
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities; 
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CallFailureTerminalAnalysisServiceAggTest extends BaseDataIntegrityTest<CallFailureTerminalAnalysisResult> {

	private CallFailureTerminalAnalysisService service;

	private final int numErrorsForSampleTac = 1;

	private final int numErrorsForSampleTac2 = 1;

	private final int numErrorsForDummyTac = 1;

	private final int numFailuresForSampleTac = 3;

	private final int numFailuresForSampleTac2 = 2;

	private final int numFailuresForDummyTac = 1;

	private final String numReestablishmentFailures = "1";

	private final String numReestablishmentFailures2 = "1";

	private final String numReestablishmentFailures3 = "1";
	
	private final String numReestablishmentFailuresForCallSetup = "";

	private final String numReestablishmentFailuresForCallSetup2 = "";

	private final String numReestablishmentFailuresForCallSetup3 = "";
	
	private final String SAMPLE_PROCEDURE_INDICATOR2 = "6";
	
	private final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);

	@Before
	public void setup() throws Exception {
		initialiseService();
		setupCreateAndPopulateDimTable();
		createEventTables();
		populateEventTables();
	}

	private void initialiseService() {
		service = new CallFailureTerminalAnalysisService();
		attachDependencies(service);
	}

	private void setupCreateAndPopulateDimTable() throws Exception {
		ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);		
		createAndPopulateLookupTable(TEMP_DIM_E_SGEH_TAC);
	}

	private void createEventTables() throws Exception {
		createAggTable();
		createRawTable();
	}

	private void createAggTable() throws Exception {
		final Collection<String> columns = new ArrayList<String>();
		columns.add(TAC);
		columns.add(NO_OF_ERRORS);
		columns.add(EVENT_ID);
		columns.add(DATETIME_ID);
		createTemporaryTable(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_DAY, columns);
		createTemporaryTable(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_15MIN, columns);
	}

	private void createRawTable() throws Exception {
		final Map<String, Nullable> columns = new HashMap<String, Nullable>();
		columns.put(TAC, Nullable.CAN_BE_NULL);
		columns.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
		columns.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
		columns.put(PROCEDURE_INDICATOR_COLUMN, Nullable.CANNOT_BE_NULL);
		createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
	}

	private void populateEventTables() throws SQLException {
		insertAggregationData();
		insertRawData(CALL_DROP_EVENT_ID_AS_INTEGER);
		insertRawData(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);
	}

	private void insertAggregationData() throws SQLException {
		insertRowsIntoAggregationTable(CALL_DROP_EVENT_ID_AS_INTEGER);
		insertRowsIntoAggregationTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);
	}

	private void insertRowsIntoAggregationTable(final int eventId) throws SQLException {
		insertRowIntoAggTable(SAMPLE_TAC, numFailuresForSampleTac, timestamp, eventId);
		insertRowIntoAggTable(SAMPLE_TAC_2, numFailuresForSampleTac2, timestamp, eventId);
		insertRowIntoAggTable(DUMMY_TAC, numFailuresForDummyTac, timestamp, eventId);
	}

	private void insertRowIntoAggTable(final int tac, final int numFailures, final String timestamp, final int eventId) throws SQLException {
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put(TAC, tac);
		values.put(NO_OF_ERRORS, numFailures);
		values.put(EVENT_ID, eventId);
		values.put(DATETIME_ID, timestamp);
		insertRow(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_DAY, values);
		insertRow(TEMP_EVENT_E_RAN_CFA_TAC_EVENTID_15MIN, values);
	}

	private void insertRawData(final int eventId) throws SQLException {
		insertDataToAppearInResultSet(eventId);
		insertDataThatWillNotAppearInResultSet(eventId);
	}

	private void insertDataToAppearInResultSet(final int eventId) throws SQLException {
		if(eventId==438)
			insertDataForCallDrop(eventId);
		else
			insertDataForCallSetup(eventId);
	}
	
	private void insertDataForCallDrop(final int eventId) throws SQLException {
		insertNumberOfErrorsIntoRawTableForTac(eventId, SAMPLE_TAC, timestamp, numErrorsForSampleTac, SAMPLE_PROCEDURE_INDICATOR);
		insertNumberOfErrorsIntoRawTableForTac(eventId, SAMPLE_TAC_2, timestamp, numErrorsForSampleTac2, SAMPLE_PROCEDURE_INDICATOR);
		insertNumberOfErrorsIntoRawTableForTac(eventId, DUMMY_TAC, timestamp, numErrorsForDummyTac, SAMPLE_PROCEDURE_INDICATOR);
	}
	
	private void insertDataForCallSetup(final int eventId) throws SQLException {
		insertNumberOfErrorsIntoRawTableForTac(eventId, SAMPLE_TAC, timestamp, numErrorsForSampleTac, SAMPLE_PROCEDURE_INDICATOR2);
		insertNumberOfErrorsIntoRawTableForTac(eventId, SAMPLE_TAC_2, timestamp, numErrorsForSampleTac2, SAMPLE_PROCEDURE_INDICATOR2);
		insertNumberOfErrorsIntoRawTableForTac(eventId, DUMMY_TAC, timestamp, numErrorsForDummyTac, SAMPLE_PROCEDURE_INDICATOR2);
	}

	private void insertNumberOfErrorsIntoRawTableForTac(final int eventId, final int tac, final String timestamp, final int numberOfErrors, final String procedure_indicator) throws SQLException {
		for (int i = 0; i < numberOfErrors; i++) {
			insertRowIntoRawTable(tac, eventId, timestamp, procedure_indicator);
		}
	}

	private void insertRowIntoRawTable(final Integer tac, final int eventId, final String timestamp, final String procedure_indicator) throws SQLException {
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put(TAC, tac);
		values.put(EVENT_ID, eventId);
		values.put(DATETIME_ID, timestamp);
		values.put(PROCEDURE_INDICATOR_COLUMN, procedure_indicator);
		insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
	}
	
	private String checkEventId(final int eventId)
	{
		if(eventId == 438)
			return SAMPLE_PROCEDURE_INDICATOR;
		else
			return SAMPLE_PROCEDURE_INDICATOR2;
	}

	private void insertDataThatWillNotAppearInResultSet(final int eventId) throws SQLException {
		final String procedure_indicator = checkEventId(eventId);
		insertRowIntoRawTable(SAMPLE_EXCLUSIVE_TAC, eventId, timestamp, procedure_indicator);
		insertRowWithNullTacIntoRawTable(eventId);
	}

	private void insertRowWithNullTacIntoRawTable(final int eventId) throws SQLException {
		final String procedure_indicator = checkEventId(eventId);
		final String timestamp = DateTimeUtilities.getDateTimeMinus48Hours();
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put(EVENT_ID, eventId);
		values.put(DATETIME_ID, timestamp);
		values.put(PROCEDURE_INDICATOR_COLUMN, procedure_indicator);
		insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
	}
	
	@Test
	public void testGetTerminalAnalysisOneDay() throws URISyntaxException, Exception {
		final String result = runQuery(service, populateRequestParameters(CALL_DROP_EVENT_ID, ONE_DAY));
		validateResult(result, CALL_DROP_EVENT_ID_AS_INTEGER, "TERMINAL_ANALYSIS_RAN_WCDMA_CALLFAILURE_MOST_DROPS");
	}

	@Test
	public void testGetTerminalAnalysisOneDayForSetupFailure() throws URISyntaxException, Exception {
		final String result = runQuery(service, populateRequestParameters(CALL_SETUP_FAILURE_EVENT_ID, ONE_DAY));
		validateResultForSetupFailure(result, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, "TERMINAL_ANALYSIS_RAN_WCDMA_CALLFAILURE_MOST_SETUP_FAILURES");
	}

	@Test
	public void testGetTerminalAnalysisOneWeek() throws URISyntaxException, Exception {
		final String result = runQuery(service, populateRequestParameters(CALL_DROP_EVENT_ID, ONE_WEEK));
		validateResult(result, CALL_DROP_EVENT_ID_AS_INTEGER, "TERMINAL_ANALYSIS_RAN_WCDMA_CALLFAILURE_MOST_DROPS");
	}

	@Test
	public void testGetTerminalAnalysisOneWeekForSetupFailure() throws URISyntaxException, Exception {
		final String result = runQuery(service, populateRequestParameters(CALL_SETUP_FAILURE_EVENT_ID, ONE_WEEK));
		validateResultForSetupFailure(result, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, "TERMINAL_ANALYSIS_RAN_WCDMA_CALLFAILURE_MOST_SETUP_FAILURES");
	}

	private MultivaluedMap<String, String> populateRequestParameters(final String eventId, final String timeRange) {
		final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
		requestParameters.putSingle(EVENT_ID, eventId);
		requestParameters.putSingle(TIME_QUERY_PARAM, timeRange);
		requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
		return requestParameters;
	}

	private void validateResult(final String json, final int eventId, final String gridDefinition) throws Exception {
		validateAgainstGridDefinition(json, gridDefinition);
		final List<CallFailureTerminalAnalysisResult> results = getTranslator().translateResult(json,
		CallFailureTerminalAnalysisResult.class);
		assertThat(results.size(), is(3));

		final CallFailureTerminalAnalysisResult resultForSampleTac = results.get(0);
		validateRowOfData(resultForSampleTac, 1, MANUFACTURER_FOR_SAMPLE_TAC, MARKETING_NAME_FOR_SAMPLE_TAC, SAMPLE_TAC, numFailuresForSampleTac,
		eventId, numReestablishmentFailures);

		final CallFailureTerminalAnalysisResult resultForSampleTac2 = results.get(1);
		validateRowOfData(resultForSampleTac2, 2, MANUFACTURER_FOR_SAMPLE_TAC_2, MARKETING_NAME_FOR_SAMPLE_TAC_2, SAMPLE_TAC_2,
		numFailuresForSampleTac2, eventId, numReestablishmentFailures2);

		final CallFailureTerminalAnalysisResult resultForDummyTac = results.get(2);
		validateRowOfData(resultForDummyTac, 3, EMPTY_STRING, EMPTY_STRING, DUMMY_TAC,
		numFailuresForDummyTac, eventId, numReestablishmentFailures3);
	}
	
	private void validateResultForSetupFailure(final String json, final int eventId, final String gridDefinition) throws Exception {
		validateAgainstGridDefinition(json, gridDefinition);
		final List<CallFailureTerminalAnalysisResult> results = getTranslator().translateResult(json,
		CallFailureTerminalAnalysisResult.class);
		assertThat(results.size(), is(3));

		final CallFailureTerminalAnalysisResult resultForSampleTac = results.get(0);
		validateRowOfData(resultForSampleTac, 1, MANUFACTURER_FOR_SAMPLE_TAC, MARKETING_NAME_FOR_SAMPLE_TAC, SAMPLE_TAC, numFailuresForSampleTac,
		eventId, numReestablishmentFailuresForCallSetup);

		final CallFailureTerminalAnalysisResult resultForSampleTac2 = results.get(1);
		validateRowOfData(resultForSampleTac2, 2, MANUFACTURER_FOR_SAMPLE_TAC_2, MARKETING_NAME_FOR_SAMPLE_TAC_2, SAMPLE_TAC_2,
		numFailuresForSampleTac2, eventId, numReestablishmentFailuresForCallSetup2);

		final CallFailureTerminalAnalysisResult resultForDummyTac = results.get(2);
		validateRowOfData(resultForDummyTac, 3, EMPTY_STRING, EMPTY_STRING, DUMMY_TAC,
		numFailuresForDummyTac, eventId, numReestablishmentFailuresForCallSetup3);
	}

	private void validateRowOfData(final CallFailureTerminalAnalysisResult resultForSampleTac, final int rank, final String manufacturer,
		final String maerketingName, final int tac, final int failures, final int eventId,
		final String reestablishmentFailures) {
		assertThat(resultForSampleTac.getRank(), is(rank));
		assertThat(resultForSampleTac.getManufacturer(), is(manufacturer));
		assertThat(resultForSampleTac.getMarketingName(), is(maerketingName));
		assertThat(resultForSampleTac.getTac(), is(tac));
		assertThat(resultForSampleTac.getNumFailures(), is(failures));
		assertThat(resultForSampleTac.getEventID(), is(eventId));
		assertThat(resultForSampleTac.getReestablishmentFailures(), is(reestablishmentFailures));
	}
}