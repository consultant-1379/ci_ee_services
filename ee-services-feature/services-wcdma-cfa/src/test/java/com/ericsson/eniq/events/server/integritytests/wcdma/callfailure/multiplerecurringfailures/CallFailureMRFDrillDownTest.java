/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.multiplerecurringfailures;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.CallFailureMultipleRecurringFailuresDrilldownService;
import com.ericsson.eniq.events.server.test.queryresults.wcdmacallfailure.multiplerecurringfailures.MultipleRecurringFailureDrillOnImsiResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eeikonl
 * @since 2011
 *
 */
public class CallFailureMRFDrillDownTest extends BaseDataIntegrityTest<MultipleRecurringFailureDrillOnImsiResult> {

    private final long hashID = 1475015981654136595l;

    private final long hashID2 = 3845899167620287061l;

    private final String vendor = "Ericsson";

    private final int CID = 39;

    private final int CID2 = 38;

    private final String rnc_fdn = "ONRM_ROOT_MO_R:RNC01:RNC01";

    private final int callDropEventID = 438;

    private CallFailureMultipleRecurringFailuresDrilldownService drilldownService;

    private final long imsi = 123456789012345L;

    private final long imsi2 = 223456789012345L;

    private String timestamp;

    private String timestampWithOffset;

    @Before
    public void onSetUp() throws Exception {
        drilldownService = new CallFailureMultipleRecurringFailuresDrilldownService();
        attachDependencies(drilldownService);

        timestamp = DateTimeUtilities.getDateTimeMinus30Minutes().concat(".0");
        timestampWithOffset = appendZeroMilliseconds(DateTimeUtilities.getDateTimeMinus30MinutesWithOffSet(1));

        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTemporyEventTable();
        createTemporaryDIMTables();
        //Populate event tables
        insertEventData();
    }

    @Test
    public void testDrillDownInvalidValues() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final int rncId1 = 1;
        final int cellId = 1;

        requestParameters.add(TZ_OFFSET, "+0100");
        requestParameters.add(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.add(RNCID, Integer.toString(rncId1));
        requestParameters.add(CELL_ID, Integer.toString(cellId));
        requestParameters.add(IMSI_PARAM, String.valueOf(imsi));
        requestParameters.add(CAUSE_VALUE, "0");
        requestParameters.add(EXTENDED_CAUSE_VALUE, "0");
        requestParameters.add(DISPLAY_PARAM, GRID);
        requestParameters.add(MAX_ROWS, Integer.toString(100));

        //We can't use the inherited runQuery method as that checks that the JSON result is a success string, and
        //we are looking for failures.

        final String result = runQueryWithoutAssertingJsonIsSuccessful(drilldownService, requestParameters);
        jsonAssertUtils.assertResultContains(result, "Please input a valid value");
    }

    @Test
    public void testDrillDown_1Result() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, "+0100");
        requestParameters.add(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.add(RNC_FDN_PARAM, rnc_fdn);
        requestParameters.add(CELL_ID, Integer.toString(CID));
        requestParameters.add(ACCESS_AREA, "RNC01-01-1");
        requestParameters.add(RAN_VENDOR_PARAM, vendor);
        requestParameters.add(IMSI_PARAM, String.valueOf(imsi));
        requestParameters.add(CAUSE_VALUE, "0");
        requestParameters.add(EXTENDED_CAUSE_VALUE, "0");
        requestParameters.add(DISPLAY_PARAM, GRID);
        requestParameters.add(MAX_ROWS, Integer.toString(100));

        final String result = runQuery(drilldownService, requestParameters);
        validateResult(result);
    }

    @Test
    public void testDrillDown_MultipleResult() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, "+0100");
        requestParameters.add(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.add(RNC_FDN_PARAM, rnc_fdn);
        requestParameters.add(ACCESS_AREA, "RNC06-01-1");
        requestParameters.add(CELL_ID, Integer.toString(CID2));
        requestParameters.add(RAN_VENDOR_PARAM, vendor);
        requestParameters.add(IMSI_PARAM, String.valueOf(imsi2));
        requestParameters.add(CAUSE_VALUE, "0");
        requestParameters.add(EXTENDED_CAUSE_VALUE, "0");
        requestParameters.add(DISPLAY_PARAM, GRID);
        requestParameters.add(MAX_ROWS, Integer.toString(100));

        final String result = runQuery(drilldownService, requestParameters);
        validateMultipleResults(result);
    }

    private void insertEventData() throws Exception {
        insertRowIntoEventTable(timestamp, callDropEventID, hashID, imsi, SAMPLE_TAC);
        insertRowIntoEventTable(timestamp, callDropEventID, hashID2, imsi2, SAMPLE_TAC_2);
        insertRowIntoEventTable(timestamp, callDropEventID, hashID2, imsi2, SAMPLE_TAC_2);
        insertRowIntoEventTable(timestamp, callDropEventID, hashID2, imsi2, SAMPLE_TAC_2);

        //These shouldn't appear in our results as they are in the exclusive tac group
        insertRowIntoEventTable(timestamp, callDropEventID, hashID2, imsi2, SAMPLE_EXCLUSIVE_TAC);
        insertRowIntoEventTable(timestamp, callDropEventID, hashID2, imsi, SAMPLE_EXCLUSIVE_TAC_2);
    }

    private void insertRowIntoEventTable(final String timeStamp, final int eventId, final long hashID1,
            final long imsi1, final int tac) throws SQLException {
        final int zero_value = 0;
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();

        valuesForEventTable.put(EVENT_TIME, timeStamp);
        valuesForEventTable.put(EVENT_ID, eventId);
        valuesForEventTable.put(IMSI, imsi1);
        valuesForEventTable.put(HIER3_CELL_ID, hashID1);
        valuesForEventTable.put(DATETIME_ID, timeStamp);
        valuesForEventTable.put(CAUSE_VALUE, zero_value);
        valuesForEventTable.put(EXTENDED_CAUSE_VALUE, zero_value);
        valuesForEventTable.put(TAC, tac);
        valuesForEventTable.put(PROCEDURE_INDICATOR, zero_value);
        valuesForEventTable.put(EVALUATION_CASE, zero_value);
        valuesForEventTable.put(EXCEPTION_CLASS, zero_value);
        valuesForEventTable.put(SEVERITY_INDICATOR, zero_value);

        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);
    }

    /**
     * @throws SQLException
     */
    private void createTemporyEventTable() throws SQLException {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, getRawTableColumns());
    }

    private void createTemporaryDIMTables() throws Exception {
        createAndPopulateLookupTables();
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
        insertDataIntoTacGroupTable();
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC_2);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    /**
     *
     * @return
     */
    private Map<String, Nullable> getRawTableColumns() {
        final Map<String, Nullable> valuesForEventTable = new HashMap<String, Nullable>();
        valuesForEventTable.put(EVENT_TIME, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER3_CELL_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(IMSI, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(CAUSE_VALUE_COLUMN, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(EXTENDED_CAUSE_VALUE, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(TAC, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(PROCEDURE_INDICATOR, Nullable.CAN_BE_NULL);
        valuesForEventTable.put(EVALUATION_CASE, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(EXCEPTION_CLASS, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(SEVERITY_INDICATOR, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        return valuesForEventTable;
    }

    private void validateResult(final String json) throws Exception {

        final List<MultipleRecurringFailureDrillOnImsiResult> results = getTranslator().translateResult(json,
                MultipleRecurringFailureDrillOnImsiResult.class);

        assertThat(results.size(), is(1));

        final MultipleRecurringFailureDrillOnImsiResult resultForDrilldown = results.get(0);
        checkCommonValues(resultForDrilldown);
        checkUncommonValues(resultForDrilldown, SAMPLE_TAC, MARKETING_NAME_FOR_SAMPLE_TAC, MANUFACTURER_FOR_SAMPLE_TAC);
    }

    private void validateMultipleResults(final String json) throws Exception {

        final List<MultipleRecurringFailureDrillOnImsiResult> results = getTranslator().translateResult(json,
                MultipleRecurringFailureDrillOnImsiResult.class);

        assertThat(results.size(), is(3));

        for (final MultipleRecurringFailureDrillOnImsiResult resultForDrilldown : results) {
            checkCommonValues(resultForDrilldown);
            checkUncommonValues(resultForDrilldown, SAMPLE_TAC_2, MARKETING_NAME_FOR_SAMPLE_TAC_2,
                    MANUFACTURER_FOR_SAMPLE_TAC_2);
        }
    }

    /**
     * Helper method that verifys the results that are differnet between the 2 success test cases
     * @param resultForDrilldown
     * @param tac
     * @param model
     */
    private void checkUncommonValues(final MultipleRecurringFailureDrillOnImsiResult resultForDrilldown, final int tac,
            final String model, final String manufacturer) {
        assertThat(resultForDrilldown.getTac(), is(String.valueOf(tac)));
        assertThat(resultForDrilldown.getTerminalModel(), is(model));
        assertThat(resultForDrilldown.getTerminalMake(), is(manufacturer));
    }

    /**
     * helper method to verify the values that are common across both success test cases
     * @param resultForDrilldown
     */
    private void checkCommonValues(final MultipleRecurringFailureDrillOnImsiResult resultForDrilldown) {
        assertThat(resultForDrilldown.getEventTime(), is(timestampWithOffset));
        assertThat(resultForDrilldown.getEventType(), is(CALL_DROPS));
        assertThat(resultForDrilldown.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(resultForDrilldown.getExceptionCase(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(resultForDrilldown.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(resultForDrilldown.getExtendedCauseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(resultForDrilldown.getProcedureIndicator(), is(NOT_APPLICABLE));
        assertThat(resultForDrilldown.getSeverityIndicator(), is(RECOVERABLE));

    }

}
