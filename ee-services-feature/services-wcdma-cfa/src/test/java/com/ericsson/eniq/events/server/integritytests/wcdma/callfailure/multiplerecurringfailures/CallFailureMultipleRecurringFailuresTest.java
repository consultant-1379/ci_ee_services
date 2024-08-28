/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.multiplerecurringfailures;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.CallFailureMultipleRecurringFailuresService;
import com.ericsson.eniq.events.server.test.queryresults.validator.QueryResultValidator;
import com.ericsson.eniq.events.server.test.queryresults.wcdmacallfailure.multiplerecurringfailures.CallFailureMultipleRecurringFailuresResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_HIER321_CELL;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_TAC;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_GROUP_TYPE_E_TAC;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eeikonl
 * @since 2011
 *
 */
public class CallFailureMultipleRecurringFailuresTest extends
        BaseDataIntegrityTest<CallFailureMultipleRecurringFailuresResult> {

    private final int cellId = 1;

    private final String accessArea = "RNC01-50-3";

    private CallFailureMultipleRecurringFailuresService objUnderTest;

    final long hashedId = 12345677890l;

    @Before
    public void onSetUp() throws Exception {
        objUnderTest = new CallFailureMultipleRecurringFailuresService();
        attachDependencies(objUnderTest);

        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);

        //Create the temporary Tables
        createTemporyEventTable();
        createTemporaryDIMTables();

        //Populate the Temporary Tables
        insertDataIntoTopologyTables();
        insertDataIntoTacGroupTable();
        insertEventData();
    }

    @Test
    public void testMultipleRecurringFailures() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.add(TIME_QUERY_PARAM, "30");
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        final String json = runQuery(objUnderTest, requestParameters);

        //Make sure that the number of columns in result and ui meta data match
        new QueryResultValidator()
                .validateAgainstGridDefinition(json, "SUBSCRIBER_RANKING_MULTIPLE_RECURRING_FAILURES");
        final List<CallFailureMultipleRecurringFailuresResult> result = getTranslator().translateResult(json,
                CallFailureMultipleRecurringFailuresResult.class);

        //We are expecting 2 rows of data, and each row should have 3 failures
        assertThat(result.size(), is(2));
        for (final CallFailureMultipleRecurringFailuresResult callFailureMultipleRecurringFailuresResult : result) {
            assertThat(callFailureMultipleRecurringFailuresResult.getFailues(), is(3));
        }
    }

    /**
     * @throws SQLException
     */
    private void createTemporyEventTable() throws SQLException {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, getRawTableColumns());
    }

    /**
     * @return
     */
    private Map<String, Nullable> getRawTableColumns() {
        final Map<String, Nullable> valuesForEventTable = new HashMap<String, Nullable>();
        valuesForEventTable.put(IMSI, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(C_ID_1, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(HIER321_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(CAUSE_VALUE_COLUMN, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(EXTENDED_CAUSE_VALUE, Nullable.CANNOT_BE_NULL);
        valuesForEventTable.put(TAC, Nullable.CANNOT_BE_NULL);
        return valuesForEventTable;
    }

    private void insertDataIntoTopologyTables() throws SQLException {
        final Map<String, Object> valuesForHier3Table = new HashMap<String, Object>();
        valuesForHier3Table.put(CELL_ID, accessArea);
        valuesForHier3Table.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForHier3Table.put(HIER321_ID, hashedId);
        valuesForHier3Table.put(RAT, RAT_FOR_3G);
        valuesForHier3Table.put(VENDOR, ERICSSON);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForHier3Table);

    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void insertEventData() throws SQLException {
        int causeValue = 11;
        int extendedCauseValue = 15;

        //This shouldn't appear in the results as IMSI 0 should be excluded from all ranking.
        insertRowIntoEventTable(causeValue, extendedCauseValue, 0, getDateTimeMinusOffset(2), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 0, getDateTimeMinusOffset(5), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 0, getDateTimeMinusOffset(27), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 0, getDateTimeMinusOffset(30), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 0, getDateTimeMinusOffset(35), SAMPLE_TAC);

        insertRowIntoEventTable(causeValue, extendedCauseValue, 123456, getDateTimeMinusOffset(2), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123456, getDateTimeMinusOffset(5), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123456, getDateTimeMinusOffset(27), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123456, getDateTimeMinusOffset(30), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123456, getDateTimeMinusOffset(35), SAMPLE_TAC);

        //Should not appear in our results, as the TAC is in the Exclusive Tac Group
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123457, getDateTimeMinusOffset(5), SAMPLE_EXCLUSIVE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123457, getDateTimeMinusOffset(27),
                SAMPLE_EXCLUSIVE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123457, getDateTimeMinusOffset(30),
                SAMPLE_EXCLUSIVE_TAC);

        causeValue = 10;
        extendedCauseValue = 11;
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123556, getDateTimeMinusOffset(2), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123556, getDateTimeMinusOffset(5), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123556, getDateTimeMinusOffset(27), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123556, getDateTimeMinusOffset(30), SAMPLE_TAC);
        insertRowIntoEventTable(causeValue, extendedCauseValue, 123556, getDateTimeMinusOffset(35), SAMPLE_TAC);

    }

    private void insertRowIntoEventTable(final int causeValue, final int extendedCauseValue, final long imsi,
            final String dateTimeId, final int tac) throws SQLException {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(IMSI, imsi);
        valuesForEventTable.put(C_ID_1, cellId);
        valuesForEventTable.put(HIER321_ID, hashedId);
        valuesForEventTable.put(DATETIME_ID, dateTimeId);
        valuesForEventTable.put(CAUSE_VALUE, causeValue);
        valuesForEventTable.put(EXTENDED_CAUSE_VALUE, extendedCauseValue);
        valuesForEventTable.put(TAC, tac);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForHier321CellTable = new ArrayList<String>();
        columnsForHier321CellTable.add(HIER321_ID);
        columnsForHier321CellTable.add(CELL_ID);
        columnsForHier321CellTable.add(HIERARCHY_3);
        columnsForHier321CellTable.add(RAT);
        columnsForHier321CellTable.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForHier321CellTable);

    }

    public static String getDateTimeMinusOffset(final int offset) {
        final int off = -1 * offset;
        return DateTimeUtilities.getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, off);
    }
}
