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
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CRNC_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DATA_IN_DL_RLC_BUFFERS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_DL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_UL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAN_DISCONNECTION_CODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAN_DISCONNECTION_SUBCODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SOURCE_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TARGET_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_DISCONNECTION;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ECNO_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RABTYPE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RSCP_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ULINT_MAPPING;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.SubscriberEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberEventAnalysisResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ebrowpa
 *
 */

public class SubscriberEventAnalysisTest extends BaseDataIntegrityTest<SubscriberEventAnalysisResult> {
    private SubscriberEventAnalysisService subscriberEventAnalysis;

    private long hashedIdForRNC01;

    @Before
    public void onSetUp() throws Exception {
        subscriberEventAnalysis = new SubscriberEventAnalysisService();
        attachDependencies(subscriberEventAnalysis);
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
        requestParameters.putSingle(DISPLAY_PARAM, "grid");
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, Long.toString(SAMPLE_IMSI));
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(TYPE_PARAM, "IMSI");
        final String result = runQuery(subscriberEventAnalysis, requestParameters);
        verifyResult(result);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "SUBSCRIBER_CALL_FAILURE_EVENT_ANALYSIS_IMSI");
        final List<SubscriberEventAnalysisResult> results = getTranslator().translateResult(json,
                SubscriberEventAnalysisResult.class);
        assertThat(results.size(), is(3)); //Unusually we are expecting the tac from the Exclusive Tac group to be included

        final SubscriberEventAnalysisResult rowWithSampleTac = findResult(results, SAMPLE_TAC);
        assertThat(rowWithSampleTac.getIMSI(), is(SAMPLE_IMSI));
        assertThat(rowWithSampleTac.getTAC(), is(SAMPLE_TAC));
        assertThat(rowWithSampleTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(rowWithSampleTac.getEventType(), is(CALL_DROPS));
        assertThat(rowWithSampleTac.getProcedureIndicatorDescription(), is(NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getExceptionClass(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(rowWithSampleTac.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getExtendedCauseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(rowWithSampleTac.getSeverityIndicator(), is(RECOVERABLE));

        assertThat(rowWithSampleTac.getSourceConf(), is("Idle"));
        assertThat(rowWithSampleTac.getCpich_ec_no_cell_1(), is(-24.0));
        assertThat(rowWithSampleTac.getUl_int_cell1(), is(-111.9));
        assertThat(rowWithSampleTac.getRscp_cell_1(), is(-115.0));
        assertThat(rowWithSampleTac.getTarget_conf(), is("SRB (13.6/13.6)"));
        assertThat(rowWithSampleTac.getC_id_serv_hsdsch_cell(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(rowWithSampleTac.getCrnc_id_serv_hsdsch_cell(), is(SAMPLE_CELL_ID));
        assertThat(rowWithSampleTac.getRscp_cell_2(), is(-115.0));
        assertThat(rowWithSampleTac.getRscp_cell_3(), is(-115.0));
        assertThat(rowWithSampleTac.getRscp_cell_4(), is(-115.0));
        assertThat(rowWithSampleTac.getCpich_ec_no_cell_2(), is(-24.0));
        assertThat(rowWithSampleTac.getCpich_ec_no_cell_3(), is(-24.0));
        assertThat(rowWithSampleTac.getCpich_ec_no_cell_4(), is(-24.0));
        assertThat(rowWithSampleTac.getUl_int_cell2(), is(-111.9));
        assertThat(rowWithSampleTac.getUl_int_cell3(), is(-111.9));
        assertThat(rowWithSampleTac.getUl_int_cell4(), is(-111.9));
        assertThat(rowWithSampleTac.getScrambling_code_cell_1(), is(1));
        assertThat(rowWithSampleTac.getScrambling_code_cell_2(), is(1));
        assertThat(rowWithSampleTac.getScrambling_code_cell_3(), is(1));
        assertThat(rowWithSampleTac.getScrambling_code_cell_4(), is(1));
        assertThat(rowWithSampleTac.getGbr_ul(), is(1));
        assertThat(rowWithSampleTac.getGbr_dl(), is(1));
        assertThat(rowWithSampleTac.getUtran_ranap_cause(), is("RAB pre-empted"));
        assertThat(rowWithSampleTac.getData_in_dl_rlc_buffers(), is(1));
    }

    private Map<String, Nullable> getRawTableColumns() {
        final Map<String, Nullable> columnsForEventTable = new HashMap<String, Nullable>();
        columnsForEventTable.put(PROCEDURE_INDICATOR, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(EVALUATION_CASE, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(EXCEPTION_CLASS, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(CAUSE_VALUE_COLUMN, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(EXTENDED_CAUSE_VALUE, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(SEVERITY_INDICATOR, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(TAC, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(EVENT_TIME, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(IMSI, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(HIER3_ID, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(HIER3_CELL_ID, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(C_ID_1, Nullable.CAN_BE_NULL);
        columnsForEventTable.put(RNC_ID_1_COLUMN, Nullable.CAN_BE_NULL);

        columnsForEventTable.put(SOURCE_CONF, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CPICH_EC_NO_CELL_1, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(UL_INT_CELL1, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(RSCP_CELL_1, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(TARGET_CONF, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(C_ID_SERV_HSDSCH_CELL, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CRNC_ID_SERV_HSDSCH_CELL, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(RSCP_CELL_2, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(RSCP_CELL_3, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(RSCP_CELL_4, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CPICH_EC_NO_CELL_2, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CPICH_EC_NO_CELL_3, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CPICH_EC_NO_CELL_4, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(UL_INT_CELL2, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(UL_INT_CELL3, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(UL_INT_CELL4, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(SCRAMBLING_CODE_CELL_1, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(SCRAMBLING_CODE_CELL_2, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(SCRAMBLING_CODE_CELL_3, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(SCRAMBLING_CODE_CELL_4, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(GBR_UL, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(GBR_DL, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CN_RANAP_CAUSE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(UTRAN_RANAP_CAUSE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(DATA_IN_DL_RLC_BUFFERS, Nullable.CANNOT_BE_NULL);

        columnsForEventTable.put(RAN_DISCONNECTION_CODE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(RAN_DISCONNECTION_SUBCODE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(TRIGGER_POINT, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(RRC_ESTABLISHMENT_CAUSE, Nullable.CANNOT_BE_NULL);

        return columnsForEventTable;
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
        lookupTables.add(TEMP_DIM_E_RAN_RABTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_ECNO_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_RSCP_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_ULINT_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_DISCONNECTION);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_TRIGGER_POINT);
        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForDIMRNCTable = new ArrayList<String>();
        columnsForDIMRNCTable.add(HIER3_ID);
        columnsForDIMRNCTable.add(HIER3_CELL_ID);
        columnsForDIMRNCTable.add(HIERARCHY_3);
        columnsForDIMRNCTable.add(VENDOR);
        columnsForDIMRNCTable.add(CELL_ID);
        columnsForDIMRNCTable.add(CID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForDIMRNCTable);
        columnsForDIMRNCTable.clear();
        columnsForDIMRNCTable.add(ALTERNATIVE_FDN);
        columnsForDIMRNCTable.add(RNC_ID);
        createTemporaryTable(TEMP_DIM_E_RAN_RNC, columnsForDIMRNCTable);
        columnsForDIMRNCTable.clear();
        columnsForDIMRNCTable.add(RNC_ID);
        columnsForDIMRNCTable.add(RNCID);
        createTemporaryTable(TEMP_DIM_E_RAN_RNCFUNCTION, columnsForDIMRNCTable);
    }

    private void createTemporaryEventTables(final Map<String, Nullable> rawTableColumns) throws Exception {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, rawTableColumns);
    }

    private void insertDataIntoTopologyTables() throws SQLException {
        final Map<String, Object> valuesForRncTable = new HashMap<String, Object>();
        valuesForRncTable.put(HIER3_ID, hashedIdForRNC01);
        valuesForRncTable.put(VENDOR, ERICSSON);
        valuesForRncTable.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForRncTable.put(CELL_ID, SAMPLE_CELL_ID);
        valuesForRncTable.put(CID, SAMPLE_C_ID_1);
        valuesForRncTable.put(HIER3_CELL_ID, 1);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForRncTable);
        valuesForRncTable.clear();
        valuesForRncTable.put(ALTERNATIVE_FDN, RNC01_ALTERNATIVE_FDN);
        valuesForRncTable.put(RNC_ID, SAMPLE_RNC_ID);
        insertRow(TEMP_DIM_E_RAN_RNC, valuesForRncTable);
        valuesForRncTable.clear();
        valuesForRncTable.put(RNC_ID, SAMPLE_RNC_ID);
        valuesForRncTable.put(RNCID, 1);
        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, valuesForRncTable);
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
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

    private Map<String, Object> getDefaultRawTableValues() {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(EVENT_ID, Integer.valueOf(CALL_DROP_EVENT_ID));
        valuesForEventTable.put(TAC, SAMPLE_TAC);
        valuesForEventTable.put(DATETIME_ID, null);
        valuesForEventTable.put(EVENT_TIME, null);
        valuesForEventTable.put(IMSI, SAMPLE_IMSI);
        valuesForEventTable.put(HIER3_ID, hashedIdForRNC01);
        valuesForEventTable.put(C_ID_1, SAMPLE_C_ID_1);
        valuesForEventTable.put(RNC_ID_1_COLUMN, 1);

        valuesForEventTable.put(SOURCE_CONF, 0);
        valuesForEventTable.put(CPICH_EC_NO_CELL_1, 1);
        valuesForEventTable.put(UL_INT_CELL1, 1);
        valuesForEventTable.put(RSCP_CELL_1, 1);
        valuesForEventTable.put(TARGET_CONF, 1);
        valuesForEventTable.put(C_ID_SERV_HSDSCH_CELL, SAMPLE_C_ID_1);
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
        valuesForEventTable.put(CN_RANAP_CAUSE, 1);
        valuesForEventTable.put(UTRAN_RANAP_CAUSE, 1);
        valuesForEventTable.put(DATA_IN_DL_RLC_BUFFERS, 1);

        valuesForEventTable.put(RAN_DISCONNECTION_CODE, 1);
        valuesForEventTable.put(RAN_DISCONNECTION_SUBCODE, 1);
        valuesForEventTable.put(TRIGGER_POINT, 1);
        valuesForEventTable.put(RRC_ESTABLISHMENT_CAUSE, 1);
        return valuesForEventTable;
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

    private SubscriberEventAnalysisResult findResult(final List<SubscriberEventAnalysisResult> results, final int tac) {
        for (final SubscriberEventAnalysisResult result : results) {
            if (result.getTAC() == tac) {
                return result;
            }
        }
        throw new RuntimeException("Could not find result for TAC " + tac);
    }
}
