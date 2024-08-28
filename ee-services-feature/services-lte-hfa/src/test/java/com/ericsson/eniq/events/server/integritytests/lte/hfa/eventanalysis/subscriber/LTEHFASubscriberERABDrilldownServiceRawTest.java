/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.eventanalysis.subscriber;

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

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventanalysis.LTEHandoverFailureSubscriberERABDEAService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHandoverFailureSubscriberERABDrilldownSetupResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author evijred
 * @since 2011
 *
 */
public class LTEHFASubscriberERABDrilldownServiceRawTest extends
        BaseDataIntegrityTest<LTEHandoverFailureSubscriberERABDrilldownSetupResult> {
    private LTEHandoverFailureSubscriberERABDEAService lteHandoverFailureSubscriberERABDetailedEventAnalysisService;

    private static final String DATETIME_ID_RAW = "2011-10-26 08:12:00";

    private static final String EVENT_TIME_RAW = "2011-10-26 08:12:00.121";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "27102011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String TEST_VALUE_HIER3_ID_1 = "4809532081614999117";

    private static final String TEST_VALUE_HIER321_ID_1 = "7210756712490856540";

    private static final String TEST_VALUE_THIER3_ID_1 = "4809532081614999117";

    private static final String TEST_VALUE_THIER321_ID_1 = "7210756712490856540";

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteHandoverFailureSubscriberERABDetailedEventAnalysisService = new LTEHandoverFailureSubscriberERABDEAService();
        attachDependencies(lteHandoverFailureSubscriberERABDetailedEventAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(THIER3_HASHID);
        columnsForTable.add(THIER321_HASHID);
        columnsForTable.add(IMSI);
        columnsForTable.add(SETUP_REQ_PCI);
        columnsForTable.add(SETUP_REQ_PVI);
        columnsForTable.add(TAC);
        columnsForTable.add(SETUP_REQ_ARP);
        columnsForTable.add(HO_IN_PREP_ERAB_REQ);
        columnsForTable.add(HO_IN_PREP_ERAB_RESULT);
        columnsForTable.add(EVENT_TIME_COLUMN);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(QCI_ID_COLUMN);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ARRAY_ERAB_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(VENDOR);
        columnsForTable.add(HIERARCHY_1);
        columnsForTable.add(HIERARCHY_3);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(VENDOR_NAME);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(SETUP_REQ_PCI);
        columnsForTable.add(SETUP_REQ_PCI_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_REQ_PCI, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(SETUP_REQ_PVI);
        columnsForTable.add(SETUP_REQ_PVI_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_REQ_PVI, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(CAUSE_CODE_COLUMN);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CAUSE_CODE_DESC_COLUMN);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(QCI_ID_COLUMN);
        columnsForTable.add(QCI_ID_DESCRIPTION_COLUMN);
        createTemporaryTable(TEMP_DIM_E_LTE_QCI, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_BSC);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_X2_IN");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_X2_OUT");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_X2_IN");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_X2_OUT");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_S1_IN");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_S1_OUT");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_S1_IN");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_S1_OUT");
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(SETUP_REQ_PCI, 0);
        valuesForTable.put(SETUP_REQ_PCI_DESC, "EVENT_VALUE_SHALL_NOT_TRIGGER_PREEMPTION");
        insertRow(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_REQ_PCI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(SETUP_REQ_PCI, 1);
        valuesForTable.put(SETUP_REQ_PCI_DESC, "EVENT_VALUE_MAY_TRIGGER_PREEMPTION");
        insertRow(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_REQ_PCI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(SETUP_REQ_PVI, 0);
        valuesForTable.put(SETUP_REQ_PVI_DESC, "EVENT_VALUE_NOT_PREEMPTABLE");
        insertRow(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_REQ_PVI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(SETUP_REQ_PVI, 1);
        valuesForTable.put(SETUP_REQ_PVI_DESC, "EVENT_VALUE_PREEMPTABLE");
        insertRow(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_REQ_PVI, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(QCI_ID_COLUMN, TEST_VALUE_LTE_HFA_QCI);
        valuesForTable.put(QCI_ID_DESCRIPTION_COLUMN, "CONVERSATIONAL VOICE");
        insertRow(TEMP_DIM_E_LTE_QCI, valuesForTable);
    }

    private void insertData(final String imsi, final String eventID, final String time, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
            valuesForTable.put(THIER3_HASHID, TEST_VALUE_THIER3_ID_1);
            valuesForTable.put(THIER321_HASHID, TEST_VALUE_THIER321_ID_1);
            valuesForTable.put(IMSI_PARAM, imsi);
            valuesForTable.put(EVENT_TIME_COLUMN, EVENT_TIME_RAW);
            valuesForTable.put(TAC, SAMPLE_TAC);
            valuesForTable.put(SETUP_REQ_ARP, TEST_VALUE_SETUP_REQ_ARP);
            valuesForTable.put(SETUP_REQ_PCI, TEST_VALUE_SETUP_REQ_PCI);
            valuesForTable.put(SETUP_REQ_PVI, TEST_VALUE_SETUP_REQ_PVI);
            valuesForTable.put(HO_IN_PREP_ERAB_REQ, TEST_VALUE_HO_IN_PREP_ERAB_REQ);
            valuesForTable.put(HO_IN_PREP_ERAB_RESULT, TEST_VALUE_HO_IN_PREP_ERAB_RESULT);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(QCI_ID, TEST_VALUE_LTE_HFA_QCI);
            insertRow(TEMP_EVENT_E_LTE_HFA_ARRAY_ERAB_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResult(final String eventId) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(EVENT_ID_PARAM, eventId);
        requestParameters.putSingle(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        requestParameters.putSingle(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        requestParameters.putSingle(IMSI_PARAM, String.valueOf(TEST_VALUE_IMSI_1));
        requestParameters.putSingle(EVENT_TIME, EVENT_TIME_RAW);
        requestParameters.putSingle(TAC, String.valueOf(SAMPLE_TAC));
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteHandoverFailureSubscriberERABDetailedEventAnalysisService, requestParameters);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDPrepS1In_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_PREP_S1_IN_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_S1_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberAnalysisDrillDownEventIDPrepX2In_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_PREP_X2_IN_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_X2_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDPrepS1Out_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_S1_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDExecS1Out_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDExecX2Out_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDExecS1In_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_S1_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDPrepX2Out_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_X2_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testSubscriberERABDrilldownEventIDExecX2In_Raw() throws Exception {

        insertData(TEST_VALUE_IMSI_1, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_X2_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    private void assertResult(final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> results) {
        assertThat(results.size(), is(2));
    }
}