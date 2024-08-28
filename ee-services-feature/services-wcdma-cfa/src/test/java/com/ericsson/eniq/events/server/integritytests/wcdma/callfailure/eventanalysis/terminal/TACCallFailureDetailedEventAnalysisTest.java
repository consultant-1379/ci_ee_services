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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.TACCallFailureDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.TACCallFailureDetailedEventResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eriwals
 *
 */
public class TACCallFailureDetailedEventAnalysisTest extends BaseDataIntegrityTest<TACCallFailureDetailedEventResult> {

    private TACCallFailureDetailedEventAnalysisService detailedEventAnalysis;

    private final int rncId1 = 3;

    @Before
    public void onSetUp() throws Exception {
        detailedEventAnalysis = new TACCallFailureDetailedEventAnalysisService();
        attachDependencies(detailedEventAnalysis);
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
    public void testFiveMinuteQuery_SpecifyingEventID() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        requestParameters.add(TACID_PARAM, Integer.toString(SAMPLE_TAC));
        final String result = runTest(requestParameters);
        verifyResult(result);
    }

    @Test
    public void testFiveMinuteQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TACID_PARAM, Integer.toString(SAMPLE_TAC));
        final String result = runTest(requestParameters);
        verifyResult(result);
    }

    @Test
    public void testFiveMinuteQueryWithExcludedTAC() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TACID_PARAM, Integer.toString(SAMPLE_EXCLUSIVE_TAC));
        final String result = runTest(requestParameters);
        verifyResultForExcludedTAC(result);
    }

    private String runTest(final MultivaluedMap<String, String> requestParameters) throws URISyntaxException, Exception {
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, FIVE_MINUTES);
        final String result = runQuery(detailedEventAnalysis, requestParameters);
        return result;
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_DETAILED_EVENT_ANALYSIS_CALL_FAILURE_TAC");
        final List<TACCallFailureDetailedEventResult> results = getTranslator().translateResult(json,
                TACCallFailureDetailedEventResult.class);
        assertThat(results.size(), is(2));

        final TACCallFailureDetailedEventResult firstRow = findResult(results, SAMPLE_TAC);
        assertThat(firstRow.getIMSI(), is(SAMPLE_IMSI));
        assertThat(firstRow.getTAC(), is(SAMPLE_TAC));
        assertThat(firstRow.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(firstRow.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(firstRow.getEventType(), is(CALL_DROPS));
        assertThat(firstRow.getProcedureIndicatorDescription(), is(NOT_APPLICABLE));
        assertThat(firstRow.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(firstRow.getExceptionClass(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(firstRow.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(firstRow.getExtendedCaluseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(firstRow.getSeverityIndicator(), is(RECOVERABLE));
        assertThat(firstRow.getController(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(firstRow.getAccessArea(), is(SAMPLE_CELL_ID));

        final TACCallFailureDetailedEventResult secondRow = findResult(results, SAMPLE_TAC);
        assertThat(secondRow.getIMSI(), is(SAMPLE_IMSI));
        assertThat(secondRow.getTAC(), is(SAMPLE_TAC));
        assertThat(secondRow.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(secondRow.getMarketingName(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(secondRow.getEventType(), is(CALL_DROPS));
        assertThat(secondRow.getProcedureIndicatorDescription(), is(NOT_APPLICABLE));
        assertThat(secondRow.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(secondRow.getExceptionClass(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(secondRow.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(secondRow.getExtendedCaluseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(secondRow.getSeverityIndicator(), is(RECOVERABLE));
        assertThat(secondRow.getController(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(secondRow.getAccessArea(), is(SAMPLE_CELL_ID));

    }

    private void verifyResultForExcludedTAC(final String json) throws Exception {
        //note: cannot use validateAgainstGridDefinition as no data is returned - this is the purpose of the test
        final List<TACCallFailureDetailedEventResult> results = getTranslator().translateResult(json,
                TACCallFailureDetailedEventResult.class);
        assertThat(results.size(), is(0));
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
        lookupTables.add(TEMP_DIM_E_RAN_CFA_TRIGGER_POINT);
        lookupTables.add(TEMP_DIM_E_RAN_RABTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_ECNO_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_RSCP_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_ULINT_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_DISCONNECTION);
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

        final Map<String, Object> valuesForHier321Table = new HashMap<String, Object>();
        valuesForHier321Table.put(CELL_ID, SAMPLE_CELL_ID);
        valuesForHier321Table.put(HIERARCHY_3, SAMPLE_HIERARCHY_3);
        valuesForHier321Table.put(HIER321_ID, rncId1);
        valuesForHier321Table.put(HIER3_CELL_ID, rncId1);
        valuesForHier321Table.put(CID, rncId1);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForHier321Table);

    }

    private void insertEventData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();
        final Map<String, Object> defaultValuesForEventTable = getDefaultRawTableValues();

        final Map<String, Object> rowWithSampleTac = new HashMap<String, Object>(defaultValuesForEventTable);
        rowWithSampleTac.put(TAC, SAMPLE_TAC);
        rowWithSampleTac.putAll(getDefaultValuesForOptionalColumns());
        insertRowIntoEventTable(rowWithSampleTac, timestamp);

        final Map<String, Object> rowWithDummyTacAndNullValues = new HashMap<String, Object>(defaultValuesForEventTable);
        rowWithDummyTacAndNullValues.put(TAC, SAMPLE_TAC);
        rowWithDummyTacAndNullValues.putAll(getDefaultValuesForOptionalColumns());
        insertRowIntoEventTable(rowWithDummyTacAndNullValues, timestamp);

        final Map<String, Object> rowWithExclusiveTac = new HashMap<String, Object>(defaultValuesForEventTable);
        rowWithExclusiveTac.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        rowWithExclusiveTac.putAll(getDefaultValuesForOptionalColumns());
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
        valuesForEventTable.put(TRIGGER_POINT, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(RRC_ESTABLISHMENT_CAUSE, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(RAN_DISCONNECTION_CODE, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(RAN_DISCONNECTION_SUBCODE, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER321_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER3_CELL_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(LAC, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(RAC, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(HIER3_ID, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(SOURCE_CONF, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(CPICH_EC_NO_CELL_1, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(UL_INT_CELL1, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(RSCP_CELL_1, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(TARGET_CONF, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(C_ID_SERV_HSDSCH_CELL, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(CRNC_ID_SERV_HSDSCH_CELL, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(RSCP_CELL_2, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(RSCP_CELL_3, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(RSCP_CELL_4, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(CPICH_EC_NO_CELL_2, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(CPICH_EC_NO_CELL_3, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(CPICH_EC_NO_CELL_4, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(UL_INT_CELL2, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(UL_INT_CELL3, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(UL_INT_CELL4, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_1, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_2, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_3, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_4, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(GBR_UL, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(GBR_DL, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(UTRAN_RANAP_CAUSE, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(DATA_IN_DL_RLC_BUFFERS, Nullable.CAN_BE_NULL);
        return valuesForEventTable;
    }

    private Map<String, Object> getDefaultRawTableValues() {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(EVENT_ID, Integer.valueOf(CALL_DROP_EVENT_ID));
        valuesForEventTable.put(DATETIME_ID, null);
        valuesForEventTable.put(EVENT_TIME, null);
        valuesForEventTable.put(IMSI, SAMPLE_IMSI);
        valuesForEventTable.put(HIER321_ID, rncId1);
        valuesForEventTable.put(TRIGGER_POINT, 1);
        valuesForEventTable.put(RRC_ESTABLISHMENT_CAUSE, 1);
        valuesForEventTable.put(RAN_DISCONNECTION_CODE, 1);
        valuesForEventTable.put(RAN_DISCONNECTION_SUBCODE, 1);
        valuesForEventTable.put(HIER3_CELL_ID, rncId1);
        valuesForEventTable.put(LAC, 1);
        valuesForEventTable.put(RAC, 1);
        valuesForEventTable.put(HIER3_ID, 1);
        valuesForEventTable.put(SOURCE_CONF, 1);
        valuesForEventTable.put(CPICH_EC_NO_CELL_1, 1);
        valuesForEventTable.put(UL_INT_CELL1, 1);
        valuesForEventTable.put(RSCP_CELL_1, 1);
        valuesForEventTable.put(TARGET_CONF, 1);
        valuesForEventTable.put(C_ID_SERV_HSDSCH_CELL, 1);
        valuesForEventTable.put(CRNC_ID_SERV_HSDSCH_CELL, 1);
        valuesForEventTable.put(RSCP_CELL_2, 1);
        valuesForEventTable.put(RSCP_CELL_3, 1);
        valuesForEventTable.put(RSCP_CELL_4, 1);
        valuesForEventTable.put(CPICH_EC_NO_CELL_2, 1);
        valuesForEventTable.put(CPICH_EC_NO_CELL_3, 1);
        valuesForEventTable.put(CPICH_EC_NO_CELL_4, 1);
        valuesForEventTable.put(UL_INT_CELL2, 1);
        valuesForEventTable.put(UL_INT_CELL3, 1);
        valuesForEventTable.put(UL_INT_CELL4, 1);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_1, 1);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_2, 1);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_3, 1);
        valuesForEventTable.put(SCRAMBLING_CODE_CELL_4, 1);
        valuesForEventTable.put(GBR_UL, 1);
        valuesForEventTable.put(GBR_DL, 1);
        valuesForEventTable.put(UTRAN_RANAP_CAUSE, 1);
        valuesForEventTable.put(DATA_IN_DL_RLC_BUFFERS, 1);
        return valuesForEventTable;
    }

    private void createTemporaryEventTables(final Map<String, Nullable> rawTableColumns) throws Exception {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, rawTableColumns);
    }

    private void createTemporaryDIMTables() throws Exception {

        final Collection<String> columnsForHier321Table = new ArrayList<String>();
        columnsForHier321Table.add(CELL_ID);
        columnsForHier321Table.add(HIERARCHY_3);
        columnsForHier321Table.add(HIER321_ID);
        columnsForHier321Table.add(HIER3_CELL_ID);
        columnsForHier321Table.add(CID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForHier321Table);
    }

    private TACCallFailureDetailedEventResult findResult(final List<TACCallFailureDetailedEventResult> results,
            final int tac) {
        for (final TACCallFailureDetailedEventResult result : results) {
            if (result.getTAC() == tac) {
                return result;
            }
        }
        throw new RuntimeException("Could not find result for TAC " + tac);
    }
}
