/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.RNCCallFailureDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.RNCCallFailureDetailedEventResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class RNCCallFailureDetailedEventAnalysisTest extends BaseDataIntegrityTest<RNCCallFailureDetailedEventResult> {

    private RNCCallFailureDetailedEventAnalysisService detailedEventAnalysis;

    private long hashedIdForRNC01;

    @Before
    public void onSetUp() throws Exception {
        detailedEventAnalysis = new RNCCallFailureDetailedEventAnalysisService();
        attachDependencies(detailedEventAnalysis);
        hashedIdForRNC01 = calculateHashedId(_3G, RNC01_ALTERNATIVE_FDN, ERICSSON);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        final Map<String, Nullable> rawTableColumns = getRawTableColumns();
        createAndPopulateLookupTables();
        createTemporaryDIMTables();
        createTemporaryEventTables(rawTableColumns);
        insertDataIntoTopologyTables();
        insertDataIntoTacGroupTable();
        insertEventData();
    }

    @Test
    public void testFiveMinuteQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);
        requestParameters.add(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        final String result = runQuery(detailedEventAnalysis, requestParameters);
        verifyResult(result);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_DETAILED_EVENT_ANALYSIS_CALL_FALURE_RNC");
        final List<RNCCallFailureDetailedEventResult> results = getTranslator().translateResult(json,
                RNCCallFailureDetailedEventResult.class);
        assertThat(results.size(), is(2));

        final RNCCallFailureDetailedEventResult rowWithSampleTac = findResult(results, SAMPLE_TAC);
        assertThat(rowWithSampleTac.getIMSI(), is(SAMPLE_IMSI));
        assertThat(rowWithSampleTac.getTAC(), is(SAMPLE_TAC));
        assertThat(rowWithSampleTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(rowWithSampleTac.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(rowWithSampleTac.getEventType(), is(CALL_DROPS));
        assertThat(rowWithSampleTac.getProcedureIndicatorDescription(), is(NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getExceptionClass(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(rowWithSampleTac.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getExtendedCaluseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getSeverityIndicator(), is(RECOVERABLE));

        final RNCCallFailureDetailedEventResult resultForDummyTAC = findResult(results, DUMMY_TAC);
        assertThat(resultForDummyTAC.getIMSI(), is(SAMPLE_IMSI));
        assertThat(resultForDummyTAC.getTAC(), is(DUMMY_TAC));
        assertThat(resultForDummyTAC.getManufacturer(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getMarketingName(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getProcedureIndicatorDescription(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getEvaluationCase(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getExceptionClass(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getCauseValue(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getExtendedCaluseValue(), is(EMPTY_STRING));
        assertThat(resultForDummyTAC.getSeverityIndicator(), is(EMPTY_STRING));

    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVALUATION_CASE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR);
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);
        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void insertDataIntoTopologyTables() throws SQLException {
        final Map<String, Object> valuesForRncTable = new HashMap<String, Object>();
        valuesForRncTable.put(HIER3_ID, hashedIdForRNC01);
        valuesForRncTable.put(VENDOR, ERICSSON);
        valuesForRncTable.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        insertRow(TEMP_DIM_E_SGEH_HIER321, valuesForRncTable);
    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();
        final Map<String, Object> defaultValuesForEventTable = getDefaultRawTableValues();

        final Map<String, Object> rowWithSampleTac = new HashMap<String, Object>(defaultValuesForEventTable);
        rowWithSampleTac.put(TAC, SAMPLE_TAC);
        rowWithSampleTac.putAll(getDefaultValuesForOptionalColumns());
        insertRowIntoEventTable(rowWithSampleTac, timestamp);

        final Map<String, Object> rowWithDummyTacAndNullValues = new HashMap<String, Object>(defaultValuesForEventTable);
        rowWithDummyTacAndNullValues.put(TAC, DUMMY_TAC);
        insertRowIntoEventTable(rowWithDummyTacAndNullValues, timestamp);

        final Map<String, Object> rowWithExclusiveTac = new HashMap<String, Object>(defaultValuesForEventTable);
        rowWithExclusiveTac.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        insertRowIntoEventTable(rowWithExclusiveTac, timestamp); //shouldn't be included in result from query
    }

    private Map<String, Object> getDefaultValuesForOptionalColumns() {
        final Map<String, Object> defaultValuesForOptionalColumns = new HashMap<String, Object>();
        defaultValuesForOptionalColumns.put(PROCEDURE_INDICATOR, 0);
        defaultValuesForOptionalColumns.put(EVALUATION_CASE, 0);
        defaultValuesForOptionalColumns.put(EXCEPTION_CLASS, 0);
        defaultValuesForOptionalColumns.put(CAUSE_VALUE_COLUMN, 0);
        defaultValuesForOptionalColumns.put(EXTENDED_CAUSE_VALUE, 0);
        defaultValuesForOptionalColumns.put(SEVERITY_INDICATOR, 0);
        return defaultValuesForOptionalColumns;
    }

    private void insertRowIntoEventTable(final Map<String, Object> values, final String timestamp) throws SQLException {
        values.put(DATETIME_ID, timestamp);
        values.put(EVENT_TIME, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
    }

    private Map<String, Nullable> getRawTableColumns() {
        final Map<String, Nullable> valuesForEventTable = new HashMap<String, Nullable>();
        valuesForEventTable.put(PROCEDURE_INDICATOR, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(EVALUATION_CASE, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(EXCEPTION_CLASS, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(CAUSE_VALUE_COLUMN, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(EXTENDED_CAUSE_VALUE, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(SEVERITY_INDICATOR, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(TAC, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(EVENT_TIME, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(IMSI, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER3_ID, Nullable.CANNOT_BE_NULL);
        return valuesForEventTable;
    }

    private Map<String, Object> getDefaultRawTableValues() {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(EVENT_ID, Integer.valueOf(CALL_DROP_EVENT_ID));
        valuesForEventTable.put(TAC, 0);
        valuesForEventTable.put(DATETIME_ID, null);
        valuesForEventTable.put(EVENT_TIME, null);
        valuesForEventTable.put(IMSI, SAMPLE_IMSI);
        valuesForEventTable.put(HIER3_ID, hashedIdForRNC01);
        return valuesForEventTable;
    }

    private void createTemporaryEventTables(final Map<String, Nullable> rawTableColumns) throws Exception {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, rawTableColumns);
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForDIMRNCTable = new ArrayList<String>();
        columnsForDIMRNCTable.add(HIER3_ID);
        columnsForDIMRNCTable.add(HIERARCHY_3);
        columnsForDIMRNCTable.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, columnsForDIMRNCTable);
    }

    private RNCCallFailureDetailedEventResult findResult(final List<RNCCallFailureDetailedEventResult> results,
            final int tac) {
        for (final RNCCallFailureDetailedEventResult result : results) {
            if (result.getTAC() == tac) {
                return result;
            }
        }
        throw new RuntimeException("Could not find result for TAC " + tac);
    }
}
