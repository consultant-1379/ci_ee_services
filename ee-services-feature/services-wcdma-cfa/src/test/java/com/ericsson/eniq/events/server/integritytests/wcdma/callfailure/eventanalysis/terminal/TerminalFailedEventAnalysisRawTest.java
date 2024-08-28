/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.terminal;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RNCID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RNC_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROPS;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_SETUP_FAILURE_EVENT_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ALTERNATIVE_FDN;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CRNC_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DATA_IN_DL_RLC_BUFFERS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DEFAULT_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVALUATION_CASE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVENT_TIME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXCEPTION_CLASS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXTENDED_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIVE_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_DL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_UL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.HIERARCHY_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.PROCEDURE_INDICATOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAN_DISCONNECTION_CODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAN_DISCONNECTION_SUBCODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RNC01_ALTERNATIVE_FDN;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RNC_ID_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_C_ID_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_EXCLUSIVE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_EXCLUSIVE_TAC_TO_STRING;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_IMSI;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_RNC_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_TO_STRING;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SEVERITY_INDICATOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SOURCE_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TARGET_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TIMEZONE_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_DISCONNECTION;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EVALUATION_CASE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EVENTTYPE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ECNO_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RABTYPE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RNC;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RNCFUNCTION;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RSCP_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ULINT_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_HIER321_CELL;
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

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.TerminalFailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.TerminalFailedEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 *
 */
public class TerminalFailedEventAnalysisRawTest extends BaseDataIntegrityTest<TerminalFailedEventAnalysisResult> {

    private TerminalFailedEventAnalysisService terminalFailedEventAnalysisService;

    @Before
    public void onSetUp() throws Exception {
        terminalFailedEventAnalysisService = new TerminalFailedEventAnalysisService();
        attachDependencies(terminalFailedEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        insertData();
    }

    /*
     * TEST_TAC has 3 CallDrops entries in the #RAW table
     */
    @Test
    public void testTerminalFailedEventAnalysisCFA_StandardTAC() throws Exception {
        final List<TerminalFailedEventAnalysisResult> results = runDetailedTerminalEventAnalysisQuery(
                SAMPLE_TAC_TO_STRING, CALL_DROP_EVENT_ID);
        assertThat(results.size(), is(3));
    }

    private List<TerminalFailedEventAnalysisResult> runDetailedTerminalEventAnalysisQuery(final String tac,
            final String eventId) throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TAC_PARAM, tac);
        requestParameters.putSingle(EVENT_ID_PARAM, eventId);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(terminalFailedEventAnalysisService, requestParameters);

        System.out.println(json);

        validateAgainstGridDefinition(json, "TERMINAL_WCDMA_CFA_EVENT_ANALYSIS_DRILL_ON_FAILURES_CALL_DROPS");
        validateAgainstGridDefinition(json, "TERMINAL_WCDMA_CFA_EVENT_ANALYSIS_DRILL_ON_FAILURES_CALL_SETUP");

        return getTranslator().translateResult(json, TerminalFailedEventAnalysisResult.class);
    }

    /*
     * TEST_TAC_3 is an exclusive tac so this query should return empty result.
     */
    @Test
    public void testTerminalEventAnalysisExclusiveTacCFA() throws Exception {
        final List<TerminalFailedEventAnalysisResult> results = runDetailedTerminalEventAnalysisQuery(
                SAMPLE_EXCLUSIVE_TAC_TO_STRING, CALL_DROP_EVENT_ID);
        assertThat(results.size(), is(1));
        final TerminalFailedEventAnalysisResult eventData = results.get(0);
        assertThat(eventData.getTac(), is(SAMPLE_EXCLUSIVE_TAC));
        assertThat(eventData.getEventType(), is(CALL_DROPS));

        assertThat(eventData.getSourceConf(), is("SRB (13.6/13.6)"));
        assertThat(eventData.getCpich_ec_no_cell_1(), is(-24.0));
        assertThat(eventData.getUl_int_cell1(), is(-111.9));
        assertThat(eventData.getRscp_cell_1(), is(-115.0));
        assertThat(eventData.getTarget_conf(), is("SRB (13.6/13.6)"));
        assertThat(eventData.getC_id_serv_hsdsch_cell(), is(SAMPLE_CELL_ID));
        assertThat(eventData.getCrnc_id_serv_hsdsch_cell(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(eventData.getRscp_cell_2(), is(-115.0));
        assertThat(eventData.getRscp_cell_3(), is(-115.0));
        assertThat(eventData.getRscp_cell_4(), is(-115.0));
        assertThat(eventData.getCpich_ec_no_cell_2(), is(-24.0));
        assertThat(eventData.getCpich_ec_no_cell_3(), is(-24.0));
        assertThat(eventData.getCpich_ec_no_cell_4(), is(-24.0));
        assertThat(eventData.getUl_int_cell2(), is(-111.9));
        assertThat(eventData.getUl_int_cell3(), is(-111.9));
        assertThat(eventData.getUl_int_cell4(), is(-111.9));
        assertThat(eventData.getScrambling_code_cell_1(), is(1));
        assertThat(eventData.getScrambling_code_cell_2(), is(1));
        assertThat(eventData.getScrambling_code_cell_3(), is(1));
        assertThat(eventData.getScrambling_code_cell_4(), is(1));
        assertThat(eventData.getGbr_ul(), is(1));
        assertThat(eventData.getGbr_dl(), is(1));
        assertThat(eventData.getUtran_ranap_cause(), is("RAB pre-empted"));
        assertThat(eventData.getData_in_dl_rlc_buffers(), is(1));
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(IMSI);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(EVENT_TIME);
        columnsForEETable.add(TAC);
        columnsForEETable.add(RNC_ID_1);
        columnsForEETable.add(C_ID_1);
        columnsForEETable.add(PROCEDURE_INDICATOR);
        columnsForEETable.add(EVALUATION_CASE);
        columnsForEETable.add(EXCEPTION_CLASS);
        columnsForEETable.add(CAUSE_VALUE);
        columnsForEETable.add(EXTENDED_CAUSE_VALUE);
        columnsForEETable.add(SEVERITY_INDICATOR);
        columnsForEETable.add(HIER3_CELL_ID);

        columnsForEETable.add(SOURCE_CONF);
        columnsForEETable.add(CPICH_EC_NO_CELL_1);
        columnsForEETable.add(UL_INT_CELL1);
        columnsForEETable.add(RSCP_CELL_1);
        columnsForEETable.add(TARGET_CONF);
        columnsForEETable.add(C_ID_SERV_HSDSCH_CELL);
        columnsForEETable.add(CRNC_ID_SERV_HSDSCH_CELL);
        columnsForEETable.add(RSCP_CELL_2);
        columnsForEETable.add(RSCP_CELL_3);
        columnsForEETable.add(RSCP_CELL_4);
        columnsForEETable.add(CPICH_EC_NO_CELL_2);
        columnsForEETable.add(CPICH_EC_NO_CELL_3);
        columnsForEETable.add(CPICH_EC_NO_CELL_4);
        columnsForEETable.add(UL_INT_CELL2);
        columnsForEETable.add(UL_INT_CELL3);
        columnsForEETable.add(UL_INT_CELL4);
        columnsForEETable.add(SCRAMBLING_CODE_CELL_1);
        columnsForEETable.add(SCRAMBLING_CODE_CELL_2);
        columnsForEETable.add(SCRAMBLING_CODE_CELL_3);
        columnsForEETable.add(SCRAMBLING_CODE_CELL_4);
        columnsForEETable.add(GBR_UL);
        columnsForEETable.add(GBR_DL);
        columnsForEETable.add(UTRAN_RANAP_CAUSE);
        columnsForEETable.add(DATA_IN_DL_RLC_BUFFERS);

        columnsForEETable.add(RAN_DISCONNECTION_CODE);
        columnsForEETable.add(RAN_DISCONNECTION_SUBCODE);
        columnsForEETable.add(TRIGGER_POINT);
        columnsForEETable.add(RRC_ESTABLISHMENT_CAUSE);

        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEETable);
    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();

        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVALUATION_CASE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR);
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
        // Create #DIM_E_SGEH_HIER321_CELL
        final Collection<String> columnsForDSTable = new ArrayList<String>();
        columnsForDSTable.add(HIERARCHY_3);
        columnsForDSTable.add(CELL_ID);
        columnsForDSTable.add(CID);
        columnsForDSTable.add(HIER3_CELL_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForDSTable);
        // Populate #DIM_E_SGEH_HIER321_CELL
        final Map<String, Object> valuesForDSTable = new HashMap<String, Object>();
        valuesForDSTable.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForDSTable.put(CELL_ID, SAMPLE_CELL_ID);
        valuesForDSTable.put(CID, SAMPLE_C_ID_1);
        valuesForDSTable.put(HIER3_CELL_ID, 6);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForDSTable);
        // Create #DIM_E_RAN_RNC
        final Collection<String> columnsForRRTable = new ArrayList<String>();
        columnsForRRTable.add(RNC_ID);
        columnsForRRTable.add(ALTERNATIVE_FDN);
        createTemporaryTable(TEMP_DIM_E_RAN_RNC, columnsForRRTable);
        // Populate #DIM_E_RAN_RNC
        final Map<String, Object> valuesForRRTable = new HashMap<String, Object>();
        valuesForRRTable.put(RNC_ID, SAMPLE_RNC_ID);
        valuesForRRTable.put(ALTERNATIVE_FDN, "TEST_HIER3");
        insertRow(TEMP_DIM_E_RAN_RNC, valuesForRRTable);
        // Create #DIM_E_RAN_RNCFUNCTION
        final Collection<String> columnsForRFTable = new ArrayList<String>();
        columnsForRFTable.add(RNC_ID);
        columnsForRFTable.add(RNCID);
        createTemporaryTable(TEMP_DIM_E_RAN_RNCFUNCTION, columnsForRFTable);
        // Populate #DIM_E_RAN_RNCFUNCTION
        final Map<String, Object> valuesForRFTable = new HashMap<String, Object>();
        valuesForRFTable.put(RNC_ID, SAMPLE_RNC_ID);
        valuesForRFTable.put(RNCID, 6);
        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, valuesForRFTable);

    }

    private void insertData() throws Exception {
        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertData(dateTime, CALL_DROP_EVENT_ID, SAMPLE_TAC);
        insertData(dateTime, CALL_DROP_EVENT_ID, SAMPLE_TAC);
        insertData(dateTime, CALL_DROP_EVENT_ID, SAMPLE_TAC);
        insertData(dateTime, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC);
        insertData(dateTime, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC);
        insertData(dateTime, CALL_DROP_EVENT_ID, SAMPLE_TAC_2);
        insertData(dateTime, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC_2);

        insertData(dateTime, CALL_DROP_EVENT_ID, SAMPLE_EXCLUSIVE_TAC);
        insertData(dateTime, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_EXCLUSIVE_TAC);
    }

    private void insertData(final String dt, final String eventID, final int tac) throws SQLException {

        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI, SAMPLE_IMSI);
        valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
        valuesForTable.put(EVENT_TIME, dt);
        valuesForTable.put(DATETIME_ID, dt);
        valuesForTable.put(TAC, tac);
        valuesForTable.put(RNC_ID_1, 6);
        valuesForTable.put(C_ID_1, SAMPLE_C_ID_1);
        valuesForTable.put(PROCEDURE_INDICATOR, 0);
        valuesForTable.put(EVALUATION_CASE, 0);
        valuesForTable.put(EXCEPTION_CLASS, 0);
        valuesForTable.put(CAUSE_VALUE, 0);
        valuesForTable.put(EXTENDED_CAUSE_VALUE, 0);
        valuesForTable.put(SEVERITY_INDICATOR, 0);
        valuesForTable.put(HIER3_CELL_ID, 6);

        valuesForTable.put(SOURCE_CONF, 1);
        valuesForTable.put(CPICH_EC_NO_CELL_1, 1);
        valuesForTable.put(UL_INT_CELL1, 1);
        valuesForTable.put(RSCP_CELL_1, 1);
        valuesForTable.put(TARGET_CONF, 1);
        valuesForTable.put(C_ID_SERV_HSDSCH_CELL, 3241);
        valuesForTable.put(CRNC_ID_SERV_HSDSCH_CELL, 1);
        valuesForTable.put(RSCP_CELL_2, 1);
        valuesForTable.put(RSCP_CELL_3, 1);
        valuesForTable.put(RSCP_CELL_4, 1);
        valuesForTable.put(CPICH_EC_NO_CELL_2, 1);
        valuesForTable.put(CPICH_EC_NO_CELL_3, 1);
        valuesForTable.put(CPICH_EC_NO_CELL_4, 1);
        valuesForTable.put(UL_INT_CELL2, 1);
        valuesForTable.put(UL_INT_CELL3, 1);
        valuesForTable.put(UL_INT_CELL4, 1);
        valuesForTable.put(SCRAMBLING_CODE_CELL_1, 1);
        valuesForTable.put(SCRAMBLING_CODE_CELL_2, 1);
        valuesForTable.put(SCRAMBLING_CODE_CELL_3, 1);
        valuesForTable.put(SCRAMBLING_CODE_CELL_4, 1);
        valuesForTable.put(GBR_UL, 1);
        valuesForTable.put(GBR_DL, 1);
        valuesForTable.put(UTRAN_RANAP_CAUSE, 1);
        valuesForTable.put(DATA_IN_DL_RLC_BUFFERS, 1);

        valuesForTable.put(RAN_DISCONNECTION_CODE, 1);
        valuesForTable.put(RAN_DISCONNECTION_SUBCODE, 1);
        valuesForTable.put(TRIGGER_POINT, 1);
        valuesForTable.put(RRC_ESTABLISHMENT_CAUSE, 1);

        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);

    }
}
