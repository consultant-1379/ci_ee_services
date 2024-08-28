/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.subbi;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.subbi.CallFailureSubsessionBIService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureSubsessionDrilldownFailureResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 *
 *
 */
public class CallFailureSubGroupsessionBIServiceIntegrityTest extends
        BaseDataIntegrityTest<CallFailureSubsessionDrilldownFailureResult> {
    /**
     *
     */
    private static final String GRID_ID = "SUBSCRIBER_WCDMA_CFA_FAILURE_ANALYSIS_BY_GROUP";

    private static final String GRID_ID_CALL_DROPS = "SUBSCRIBER_WCDMA_CFA_FAILURE_ANALYSIS_BY_GROUP_CALL_DROPS";

    private static final String GRID_ID_CALL_SETUP = "SUBSCRIBER_WCDMA_CFA_FAILURE_ANALYSIS_BY_GROUP_CALL_SETUP_FAILURES";

    private final long hashID = 4114361191837027176l;

    private final long imsi = 123456789012345L;

    private final long imsi2 = 223456789012345L;

    private CallFailureSubsessionBIService callFailureSubsessionBIService;

    final String timestamp1 = DateTimeUtilities.getDateTimeMinus30Minutes();

    final String timestamp2 = DateTimeUtilities.getDateTimeMinus35Minutes();

    final String timestamp3 = DateTimeUtilities.getDateTimeMinus48Hours();

    @Before
    public void setup() throws Exception {
        callFailureSubsessionBIService = new CallFailureSubsessionBIService();
        attachDependencies(callFailureSubsessionBIService);
        createTables();
        insertData();
    }

    private void insertData() throws SQLException {

        insertRowIntoEventTable(timestamp1, CALL_DROP_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_TAC);
        insertRowIntoEventTable(timestamp1, CALL_DROP_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_TAC_2);
        insertRowIntoEventTable(timestamp1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_TAC);
        insertRowIntoEventTable(timestamp1, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_TAC);
        insertRowIntoEventTable(timestamp3, CALL_DROP_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_TAC);
        insertRowIntoEventTable(timestamp3, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_TAC);
        insertRowIntoEventTable(timestamp3, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, hashID, imsi, SAMPLE_EXCLUSIVE_TAC);

    }

    private void insertRowIntoEventTable(final String timeStamp, final int eventId, final long hashID1,
            final long imsi1, final int tac) throws SQLException {
        final int zero_value = 0;
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();

        valuesForEventTable.put(EVENT_TIME, timeStamp);
        valuesForEventTable.put(EVENT_ID, eventId);
        valuesForEventTable.put(IMSI, imsi1);
        valuesForEventTable.put(TAC, tac);
        valuesForEventTable.put(PROCEDURE_INDICATOR, zero_value);
        valuesForEventTable.put(EVALUATION_CASE, zero_value);
        valuesForEventTable.put(EXCEPTION_CLASS, zero_value);
        valuesForEventTable.put(CAUSE_VALUE, zero_value);
        valuesForEventTable.put(EXTENDED_CAUSE_VALUE, zero_value);
        valuesForEventTable.put(SEVERITY_INDICATOR, zero_value);
        valuesForEventTable.put(HIER321_ID, hashID1);
        valuesForEventTable.put(HIER3_ID, hashID1);
        valuesForEventTable.put(DATETIME_ID, timeStamp);

        valuesForEventTable.put(SOURCE_CONF, 0);
        valuesForEventTable.put(CPICH_EC_NO_CELL_1, 1);
        valuesForEventTable.put(UL_INT_CELL1, 1);
        valuesForEventTable.put(RSCP_CELL_1, 1);
        valuesForEventTable.put(TARGET_CONF, 1);
        valuesForEventTable.put(C_ID_SERV_HSDSCH_CELL, 1501);
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

        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);
    }

    private void createTemporyEventTable() throws SQLException {
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, getRawTableColumns());
    }

    private Map<String, Nullable> getRawTableColumns() {
        final Map<String, Nullable> columnsForEventTable = new HashMap<String, Nullable>();
        columnsForEventTable.put(EVENT_TIME, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(EVENT_ID, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(IMSI, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(TAC, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(PROCEDURE_INDICATOR, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(EVALUATION_CASE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(EXCEPTION_CLASS, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(CAUSE_VALUE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(EXTENDED_CAUSE_VALUE, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(SEVERITY_INDICATOR, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(HIER321_ID, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(HIER3_ID, Nullable.CANNOT_BE_NULL);
        columnsForEventTable.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);

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

    private void createTables() throws Exception {
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

        createTemporyEventTable();

        final Collection<String> columnsForGroupTable = new ArrayList<String>();
        columnsForGroupTable.add(GROUP_NAME);
        columnsForGroupTable.add(IMSI);
        createTemporaryTable(TEMP_GROUP_TYPE_E_IMSI, columnsForGroupTable);

        final Map<String, Object> valuesForGroupTable = new HashMap<String, Object>();
        valuesForGroupTable.put(GROUP_NAME, GROUP_NAME_KEY);
        valuesForGroupTable.put(IMSI, imsi);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForGroupTable);
        valuesForGroupTable.put(IMSI, imsi2);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForGroupTable);

        final Collection<String> columnsForHier321CellTable = new ArrayList<String>();
        columnsForHier321CellTable.add(HIER321_ID);
        columnsForHier321CellTable.add(HIER3_ID);
        columnsForHier321CellTable.add(CELL_ID);
        columnsForHier321CellTable.add(HIERARCHY_3);
        columnsForHier321CellTable.add(RAT);
        columnsForHier321CellTable.add(VENDOR);
        columnsForHier321CellTable.add(CID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForHier321CellTable);

        final Map<String, Object> valuesForHier3Table = new HashMap<String, Object>();
        valuesForHier3Table.put(CELL_ID, "RNCo1_1");
        valuesForHier3Table.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForHier3Table.put(HIER321_ID, hashID);
        valuesForHier3Table.put(HIER3_ID, hashID);
        valuesForHier3Table.put(RAT, RAT_FOR_3G);
        valuesForHier3Table.put(VENDOR, ERICSSON);
        valuesForHier3Table.put(CID, 1501);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForHier3Table);

    }

    @Test
    public void testGetFailuresForIMSIGroup_ThirtyMinutes_CallSetupFailure() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, GROUP_NAME_KEY);
        requestParameters.putSingle(EVENT_PARAM, CALL_SETUP_FAILURES_EVENT_DESC);

        final String json = runQuery(callFailureSubsessionBIService, requestParameters);
        System.out.println(json);
        validateAgainstGridDefinition(json, GRID_ID);
        final List<CallFailureSubsessionDrilldownFailureResult> resultList = getTranslator().translateResult(json,
                CallFailureSubsessionDrilldownFailureResult.class);
        assertEquals("Got a different number of results than expected.", 2, resultList.size());
        for (final CallFailureSubsessionDrilldownFailureResult result : resultList) {
            checkValues(result, CALL_SETUP_FAILURES_EVENT_DESC);
        }
    }

    @Test
    public void testGetFailuresForIMSIGroup_2Weeks_CallDrops() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, TWO_WEEKS);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, GROUP_NAME_KEY);
        requestParameters.putSingle(EVENT_PARAM, CALL_DROPS);

        final String json = runQuery(callFailureSubsessionBIService, requestParameters);

        final List<CallFailureSubsessionDrilldownFailureResult> resultList = getTranslator().translateResult(json,
                CallFailureSubsessionDrilldownFailureResult.class);
        validateAgainstGridDefinition(json, GRID_ID);
        assertEquals("Got a different number of results than expected.", 1, resultList.size());

        for (final CallFailureSubsessionDrilldownFailureResult result : resultList) {
            checkValues(result, CALL_DROPS);
        }
    }

    @Test
    public void testGetFailuresForIMSIGroup_2Weeks_CallSetupFailure() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, TWO_WEEKS);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, GROUP_NAME_KEY);
        requestParameters.putSingle(EVENT_PARAM, CALL_SETUP_FAILURES_EVENT_DESC);

        final String json = runQuery(callFailureSubsessionBIService, requestParameters);

        final List<CallFailureSubsessionDrilldownFailureResult> resultList = getTranslator().translateResult(json,
                CallFailureSubsessionDrilldownFailureResult.class);
        validateAgainstGridDefinition(json, GRID_ID_CALL_SETUP);
        assertEquals("Got a different number of results than expected.", 1, resultList.size()); // Should exclude SAMPLE_EXCLUSIVE_TAC

        for (final CallFailureSubsessionDrilldownFailureResult result : resultList) {
            checkValues(result, CALL_SETUP_FAILURES_EVENT_DESC);
        }
    }

    @Test
    public void testGetFailuresForIMSIGroup_ThirtyMinutes_CallDrops() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, GROUP_NAME_KEY);
        requestParameters.putSingle(EVENT_PARAM, CALL_DROPS);

        final String json = runQuery(callFailureSubsessionBIService, requestParameters);

        validateAgainstGridDefinition(json, GRID_ID_CALL_DROPS);
        final List<CallFailureSubsessionDrilldownFailureResult> resultList = getTranslator().translateResult(json,
                CallFailureSubsessionDrilldownFailureResult.class);
        assertEquals("Got a different number of results than expected.", 2, resultList.size());
        for (final CallFailureSubsessionDrilldownFailureResult result : resultList) {
            checkValues(result, CALL_DROPS);
        }

    }

    private void checkValues(final CallFailureSubsessionDrilldownFailureResult resultForDrilldown,
            final String eventDesc) {
        assertThat(resultForDrilldown.getEventType(), is(eventDesc));
        assertThat(resultForDrilldown.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(resultForDrilldown.getExceptionCase(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(resultForDrilldown.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(resultForDrilldown.getExtendedCauseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(resultForDrilldown.getProcedureIndicator(), is(NOT_APPLICABLE));
        assertThat(resultForDrilldown.getSeverityIndicator(), is(RECOVERABLE));

        assertThat(resultForDrilldown.getSourceConf(), is(RABTYPE_IDLE));
        assertThat(resultForDrilldown.getCpich_ec_no_cell_1(), is(-24.0));
        assertThat(resultForDrilldown.getRscp_cell_1(), is(-115.0));
        assertThat(resultForDrilldown.getUl_int_cell1(), is(-111.9));
        assertThat(resultForDrilldown.getTarget_conf(), is(RABTYPE_SRB));
        assertThat(resultForDrilldown.getC_id_serv_hsdsch_cell(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(resultForDrilldown.getCrnc_id_serv_hsdsch_cell(), is("RNCo1_1"));
        assertThat(resultForDrilldown.getRscp_cell_2(), is(-115.0));
        assertThat(resultForDrilldown.getRscp_cell_3(), is(-115.0));
        assertThat(resultForDrilldown.getRscp_cell_4(), is(-115.0));
        assertThat(resultForDrilldown.getCpich_ec_no_cell_2(), is(-24.0));
        assertThat(resultForDrilldown.getCpich_ec_no_cell_3(), is(-24.0));
        assertThat(resultForDrilldown.getCpich_ec_no_cell_4(), is(-24.0));
        assertThat(resultForDrilldown.getUl_int_cell2(), is(-111.9));
        assertThat(resultForDrilldown.getUl_int_cell3(), is(-111.9));
        assertThat(resultForDrilldown.getUl_int_cell4(), is(-111.9));
        assertThat(resultForDrilldown.getScrambling_code_cell_1(), is(1));
        assertThat(resultForDrilldown.getScrambling_code_cell_2(), is(1));
        assertThat(resultForDrilldown.getScrambling_code_cell_3(), is(1));
        assertThat(resultForDrilldown.getScrambling_code_cell_4(), is(1));
        assertThat(resultForDrilldown.getGbr_ul(), is(1));
        assertThat(resultForDrilldown.getGbr_dl(), is(1));
        assertThat(resultForDrilldown.getUtran_ranap_cause(), is("RAB pre-empted"));
        assertThat(resultForDrilldown.getData_in_dl_rlc_buffers(), is(1));
    }
}