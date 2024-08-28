/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.imsi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.SubscriberCallDropDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberCallFailureDetailedEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 *
 */
public class SubscriberCallDropDetailedEventAnalysisRawTest extends
        BaseDataIntegrityTest<SubscriberCallFailureDetailedEventAnalysisResult> {

    private SubscriberCallDropDetailedEventAnalysisService subscriberCallDropDetailedEventAnalysisService;

    private static final int TEST_IMSI_1 = 11111119;

    private String timestamp;

    private String timestampWithOffset;

    @Before
    public void onSetUp() throws Exception {
        subscriberCallDropDetailedEventAnalysisService = new SubscriberCallDropDetailedEventAnalysisService();
        attachDependencies(subscriberCallDropDetailedEventAnalysisService);
        timestamp = DateTimeUtilities.getDateTimeMinus2Minutes().concat(".0");
        timestampWithOffset = appendZeroMilliseconds(DateTimeUtilities.getDateTimeMinus2MinutesWithOffSet(1));
        createAndPopulateLookupTables();
        createTables();
        insertCustomDimData();
        insertData();
    }

    @Test
    public void testSubscriberDetailedEventAnalysisCFA() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, "grid");
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(IMSI, new Integer(TEST_IMSI_1).toString());
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(subscriberCallDropDetailedEventAnalysisService, requestParameters);

        System.out.println(json);

        validateResult(json);
    }

    private void validateResult(final String json) throws Exception {
        validateAgainstGridDefinition(json, "DRILL_GRID_CHART_SUB_BI_CFA");
        final List<SubscriberCallFailureDetailedEventAnalysisResult> results = getTranslator().translateResult(json,
                SubscriberCallFailureDetailedEventAnalysisResult.class);

        assertThat(results.size(), is(2));

        final SubscriberCallFailureDetailedEventAnalysisResult rowWithSampleTac = getResultFor(SAMPLE_TAC, results);
        assertTrue("Unexpected TAC in result!", rowWithSampleTac.getTac() == new Integer(SAMPLE_TAC));
        assertThat("Unexpected Terminal Make in result!", rowWithSampleTac.getTerminalMake(),
                is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat("Unexpected Event Type in result", rowWithSampleTac.getEventType(), is(CALL_DROPS));
        assertThat(rowWithSampleTac.getEventTime(), is(timestampWithOffset));
        assertEquals(rowWithSampleTac.getImsi(), TEST_IMSI_1);

        final SubscriberCallFailureDetailedEventAnalysisResult rowWithUnmatchedTac = getResultFor(DUMMY_TAC, results);
        assertTrue("Unexpected TAC in result!", rowWithUnmatchedTac.getTac() == new Integer(DUMMY_TAC));
        assertThat("Unexpected Terminal Make in result!", rowWithUnmatchedTac.getTerminalMake(), is(EMPTY_STRING));
        assertThat("Unexpected Event Type in result", rowWithUnmatchedTac.getEventType(), is(CALL_DROPS));
        assertThat(rowWithUnmatchedTac.getEventTime(), is(timestampWithOffset));
        assertEquals(rowWithUnmatchedTac.getImsi(), TEST_IMSI_1);
    }

    private SubscriberCallFailureDetailedEventAnalysisResult getResultFor(final int sampleTac,
            final List<SubscriberCallFailureDetailedEventAnalysisResult> results) {
        for (final SubscriberCallFailureDetailedEventAnalysisResult result : results) {
            final int tac = result.getTac();
            if (tac == sampleTac) {
                return result;
            }
        }
        fail("Could not find result for TAC " + sampleTac);
        return null;
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
        columnsForEETable.add(CAUSE_VALUE);
        columnsForEETable.add(EXTENDED_CAUSE_VALUE);
        columnsForEETable.add(EXCEPTION_CLASS);
        columnsForEETable.add(EVALUATION_CASE);
        columnsForEETable.add(PROCEDURE_INDICATOR);
        columnsForEETable.add(SEVERITY_INDICATOR);
        columnsForEETable.add(HIER321_ID);
        columnsForEETable.add(HIER3_ID);

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
        columnsForEETable.add(CN_RANAP_CAUSE);
        columnsForEETable.add(UTRAN_RANAP_CAUSE);
        columnsForEETable.add(DATA_IN_DL_RLC_BUFFERS);

        columnsForEETable.add(RAN_DISCONNECTION_CODE);
        columnsForEETable.add(RAN_DISCONNECTION_SUBCODE);
        columnsForEETable.add(TRIGGER_POINT);
        columnsForEETable.add(RRC_ESTABLISHMENT_CAUSE);

        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEETable);

        //Temporary DIM tables with custom values for this test
        final Collection<String> columnsForDESTable = new ArrayList<String>();
        columnsForDESTable.add(CELL_ID);
        columnsForDESTable.add(CID);
        columnsForDESTable.add(HIERARCHY_3);
        columnsForDESTable.add(HIER321_ID);
        columnsForDESTable.add(HIER3_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForDESTable);

        final Collection<String> columnsForDERTable = new ArrayList<String>();
        columnsForDERTable.add(ALTERNATIVE_FDN);
        columnsForDERTable.add(RNC_ID);
        createTemporaryTable(TEMP_DIM_E_RAN_RNC, columnsForDERTable);

        final Collection<String> columnsForDEFTable = new ArrayList<String>();
        columnsForDEFTable.add(RNC_ID);
        columnsForDEFTable.add(RNCID);
        createTemporaryTable(TEMP_DIM_E_RAN_RNCFUNCTION, columnsForDEFTable);

    }

    private void insertCustomDimData() throws SQLException {
        populateDIM_E_SGEH_HIER321_CELL("RNC06-1-1", 1501, "ONRM_ROOT_MO_R:RNC06:RNC06");
        populateDIM_E_RAN_RNC("ONRM_ROOT_MO_R:RNC06:RNC06", "RNC06");
        populateDIM_E_RAN_RNCFUNCTION("RNC06", 6);
    }

    private void populateDIM_E_RAN_RNCFUNCTION(final String rnc_id, final int rncId) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(RNC_ID, rnc_id);
        valuesForTable.put(RNCID, rncId);
        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, valuesForTable);
    }

    private void populateDIM_E_RAN_RNC(final String altFdn, final String rncId) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(ALTERNATIVE_FDN, altFdn);
        valuesForTable.put(RNC_ID, rncId);
        insertRow(TEMP_DIM_E_RAN_RNC, valuesForTable);
    }

    private void populateDIM_E_SGEH_HIER321_CELL(final String cellId, final int cid, final String hier3)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(CELL_ID, cellId);
        valuesForTable.put(CID, cid);
        valuesForTable.put(HIERARCHY_3, hier3);
        valuesForTable.put(HIER321_ID, 321);
        valuesForTable.put(HIER3_ID, 3);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForTable);

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

    private void insertData() throws Exception {
        insertIMSI(TEST_IMSI_1, timestamp, CALL_DROP_EVENT_ID, SAMPLE_TAC, 6);
        insertIMSI(TEST_IMSI_1, timestamp, CALL_DROP_EVENT_ID, DUMMY_TAC, 6);
        insertIMSI(TEST_IMSI_1, timestamp, CALL_SETUP_FAILURE_EVENT_ID, SAMPLE_TAC, 6); //shouldn't be included in result, different event id
        insertIMSI(TEST_IMSI_1, timestamp, CALL_SETUP_FAILURE_EVENT_ID, DUMMY_TAC, 6);//shouldn't be included in result, different event id
    }

    private void insertIMSI(final int imsi, final String dt, final String eventID, final int tac, final int rncId1)
            throws SQLException {

        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI, imsi);
        valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
        valuesForTable.put(DATETIME_ID, dt);
        valuesForTable.put(EVENT_TIME, dt);
        valuesForTable.put(TAC, tac);
        valuesForTable.put(RNC_ID_1, rncId1);
        valuesForTable.put(C_ID_1, 1501);
        valuesForTable.put(CAUSE_VALUE, 0);
        valuesForTable.put(EXTENDED_CAUSE_VALUE, 0);
        valuesForTable.put(EXCEPTION_CLASS, 0);
        valuesForTable.put(EVALUATION_CASE, 0);
        valuesForTable.put(PROCEDURE_INDICATOR, 0);
        valuesForTable.put(SEVERITY_INDICATOR, 0);
        valuesForTable.put(HIER321_ID, 321);
        valuesForTable.put(HIER3_ID, 3);

        valuesForTable.put(SOURCE_CONF, 0);
        valuesForTable.put(CPICH_EC_NO_CELL_1, 1);
        valuesForTable.put(UL_INT_CELL1, 1);
        valuesForTable.put(RSCP_CELL_1, 1);
        valuesForTable.put(TARGET_CONF, 1);
        valuesForTable.put(C_ID_SERV_HSDSCH_CELL, 1501);
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
        valuesForTable.put(CN_RANAP_CAUSE, 1);
        valuesForTable.put(UTRAN_RANAP_CAUSE, 1);
        valuesForTable.put(DATA_IN_DL_RLC_BUFFERS, 1);
        valuesForTable.put(RAN_DISCONNECTION_CODE, 1);
        valuesForTable.put(RAN_DISCONNECTION_SUBCODE, 1);
        valuesForTable.put(TRIGGER_POINT, 1);
        valuesForTable.put(RRC_ESTABLISHMENT_CAUSE, 1);

        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);

    }
}
