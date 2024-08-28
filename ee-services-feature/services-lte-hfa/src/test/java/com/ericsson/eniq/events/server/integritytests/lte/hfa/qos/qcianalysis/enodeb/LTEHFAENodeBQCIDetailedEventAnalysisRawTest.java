/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.qos.qcianalysis.enodeb;

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

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.qos.qcianalysis.LTEHFAEnodeBQCIDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHandoverFailureSubscriberERABDrilldownSetupResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTEHFAENodeBQCIDetailedEventAnalysisRawTest extends
        BaseDataIntegrityTest<LTEHandoverFailureSubscriberERABDrilldownSetupResult> {
    LTEHFAEnodeBQCIDetailedEventAnalysisService LTEHFAEnodeBQCIDetailedEventAnalysisService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String TEST_VALUE_HIER3_ID_1 = "4809532081614999117";

    private static final String TEST_VALUE_HIER3_ID_2 = "4809532081614999118";

    private static final String TEST_VALUE_HIER321_ID_1 = "7210756712490856540";

    private static final String TEST_VALUE_HIER321_ID_2 = "7210756712490856541";

    private static final String TEST_VALUE_QCI_ID_1 = "1";

    private static final String TEST_VALUE_QCI_ID_2 = "2";

    private static final String EVENT_TIME_RAW = "2011-09-20 08:12:00.121";

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    private static final String TEST_VALUE_GROUP_NAME = "enodeBGroup";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        LTEHFAEnodeBQCIDetailedEventAnalysisService = new LTEHFAEnodeBQCIDetailedEventAnalysisService();
        attachDependencies(LTEHFAEnodeBQCIDetailedEventAnalysisService);
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
        columnsForTable.add(MSISDN_PARAM_UPPER_CASE);
        columnsForTable.add(IMSI);
        columnsForTable.add(EVENT_TIME_COLUMN);
        columnsForTable.add(TAC);
        columnsForTable.add(SETUP_RESULT);
        columnsForTable.add(HO_IN_PREP_ERAB_RESULT);
        columnsForTable.add(HO_IN_PREP_ERAB_REQ);
        columnsForTable.add(SETUP_REQ_QCI);
        columnsForTable.add(SETUP_REQ_ARP);
        columnsForTable.add(SETUP_REQ_PCI);
        columnsForTable.add(SETUP_REQ_PVI);
        columnsForTable.add(FAILURE_3GPP_CAUSE);
        columnsForTable.add(SETUP_ATT_ACC_TYPE);
        columnsForTable.add(SETUP_SUCC_ACC_TYPE);
        columnsForTable.add(SETUP_FAIL_3GPP_CAUSE_GROUP);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(RELEASE_REQ_QCI);
        columnsForTable.add(QCI_ID_COLUMN);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ARRAY_ERAB_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(VENDOR);
        columnsForTable.add(HIERARCHY_1);
        columnsForTable.add(HIERARCHY_2);
        columnsForTable.add(HIERARCHY_3);
        columnsForTable.add(MCC);
        columnsForTable.add(MNC);
        columnsForTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_EVENTTYPE, columnsForTable);

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
        columnsForTable.add(CAUSE_CODE_DESC_COLUMN);
        columnsForTable.add(EVENT_ID);

        createTemporaryTable(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, columnsForTable);

        //        columnsForTable.clear();
        //        columnsForTable.add(SETUP_ATT_ACC_TYPE);
        //        columnsForTable.add(SETUP_ATT_ACC_TYPE_DESC);
        //        createTemporaryTable(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_ATT_ACC_TYPE, columnsForTable);
        //
        //        columnsForTable.clear();
        //        columnsForTable.add(SETUP_FAIL_3GPP_CAUSE_GROUP);
        //        columnsForTable.add(SETUP_FAIL_3GPP_CAUSE_GROUP_DESC);
        //        createTemporaryTable(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_FAIL_3GPP_CAUSE_GROUP, columnsForTable);
        //
        //        columnsForTable.clear();
        //        columnsForTable.add(SETUP_SUCC_ACC_TYPE);
        //        columnsForTable.add(SETUP_SUCC_ACC_TYPE_DESC);
        //        createTemporaryTable(TEMP_DIM_E_LTE_HFA_ERAB_SETUP_SUCC_ACC_TYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(VENDOR_NAME);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL);
        valuesForTable.put(HIERARCHY_2, "");
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_BSC);
        valuesForTable.put(MCC, TEST_VALUE_MCC);
        valuesForTable.put(MNC, TEST_VALUE_MNC);
        valuesForTable.put(RAT, TEST_VALUE_RAT);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_2);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_2);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, "cell,,bsc,nokia,gsm");
        valuesForTable.put(HIERARCHY_2, "");
        valuesForTable.put(HIERARCHY_3, "bsc,nokia,gsm");
        valuesForTable.put(MCC, "075");
        valuesForTable.put(MNC, "03");
        valuesForTable.put(RAT, TEST_VALUE_RAT);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);
    }

    private void insertData(final String eventID, final String time, final int instances, final String qciID,
            final String hire3ID, final String hire321ID) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(HIER3_ID, hire3ID);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(HIER321_ID, hire321ID);
            valuesForTable.put(THIER3_HASHID, TEST_VALUE_HIER3_ID_1);
            valuesForTable.put(THIER321_HASHID, TEST_VALUE_HIER321_ID_1);
            valuesForTable.put(MSISDN_PARAM_UPPER_CASE, TEST_VALUE_MSISDN);
            valuesForTable.put(IMSI, TEST_VALUE_IMSI_1);
            valuesForTable.put(EVENT_TIME_COLUMN, EVENT_TIME_RAW);
            valuesForTable.put(TAC, SAMPLE_TAC);
            valuesForTable.put(HO_IN_PREP_ERAB_REQ, TEST_VALUE_HO_IN_PREP_ERAB_REQ);
            valuesForTable.put(HO_IN_PREP_ERAB_RESULT, TEST_VALUE_HO_IN_PREP_ERAB_RESULT);
            valuesForTable.put(SETUP_RESULT, TEST_VALUE_SETUP_RESULT);
            valuesForTable.put(SETUP_RESULT, TEST_VALUE_SETUP_RESULT);
            valuesForTable.put(SETUP_REQ_QCI, TEST_VALUE_SETUP_REQ_QCI);
            valuesForTable.put(SETUP_REQ_ARP, TEST_VALUE_SETUP_REQ_ARP);
            valuesForTable.put(SETUP_REQ_PCI, TEST_VALUE_SETUP_REQ_PCI);
            valuesForTable.put(SETUP_REQ_PVI, TEST_VALUE_SETUP_REQ_PVI);
            valuesForTable.put(FAILURE_3GPP_CAUSE, TEST_VALUE_FAILURE_3GPP_CAUSE);
            valuesForTable.put(SETUP_ATT_ACC_TYPE, TEST_VALUE_SETUP_ATT_ACC_TYPE);
            valuesForTable.put(SETUP_SUCC_ACC_TYPE, TEST_VALUE_SETUP_SUCC_ACC_TYPE);
            valuesForTable.put(SETUP_FAIL_3GPP_CAUSE_GROUP, TEST_VALUE_SETUP_FAIL_3GPP_CAUSE_GROUP);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(RELEASE_REQ_QCI, TEST_VALUE_RELEASE_REQ_QCI);
            valuesForTable.put(QCI_ID_COLUMN, qciID);
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
        requestParameters.putSingle(EVENT_ID_SQL_PARAM, eventId);
        requestParameters.putSingle(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        requestParameters.putSingle(QCI_ID_COLUMN, TEST_VALUE_QCI_ID_1);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_ENODEB_NODE);

        return runQuery(LTEHFAEnodeBQCIDetailedEventAnalysisService, requestParameters);
    }

    private String getJsonResultGroup(final String eventId) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(EVENT_ID_PARAM, eventId);
        requestParameters.putSingle(EVENT_ID_SQL_PARAM, eventId);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        requestParameters.putSingle(QCI_ID_COLUMN, TEST_VALUE_QCI_ID_1);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(LTEHFAEnodeBQCIDetailedEventAnalysisService, requestParameters);
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDPrepX2Out_Raw() throws Exception {

        insertData(INTERNAL_PROC_HO_PREP_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_PREP_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_X2_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDExecX2In_Raw() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_X2_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_X2_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_X2_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDExecX2Out_Raw() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDPrepS1Out_Raw() throws Exception {

        insertData(INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_S1_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDExecS1Out_Raw() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDPrepS1In_Raw() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_2,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_S1_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDPrepX2Out_RawGroup() throws Exception {

        insertData(INTERNAL_PROC_HO_PREP_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_PREP_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResultGroup(INTERNAL_PROC_HO_PREP_X2_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDExecX2In_RawGroup() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_X2_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_X2_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResultGroup(INTERNAL_PROC_HO_EXEC_X2_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertResult(rankingResult);
    }

    private void assertResult(final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> results) {
        assertThat(results.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDExecX2Out_RawGroup() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResultGroup(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDPrepS1Out_RawGroup() throws Exception {

        insertData(INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_PREP_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResultGroup(INTERNAL_PROC_HO_PREP_S1_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDExecS1Out_RawGroup() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResultGroup(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }

    @Test
    public void testQCIeNodeBDetailedAnalysisDrillDownEventIDPrepS1In_RawGroup() throws Exception {

        insertData(INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_1, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);
        insertData(INTERNAL_PROC_HO_EXEC_S1_IN_HFA, DATETIME_ID_RAW, 2, TEST_VALUE_QCI_ID_2, TEST_VALUE_HIER3_ID_1,
                TEST_VALUE_HIER321_ID_1);

        final String json = getJsonResultGroup(INTERNAL_PROC_HO_EXEC_S1_IN_HFA);

        final ResultTranslator<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rt = getTranslator();
        final List<LTEHandoverFailureSubscriberERABDrilldownSetupResult> rankingResult = rt.translateResult(json,
                LTEHandoverFailureSubscriberERABDrilldownSetupResult.class);
        assertThat(rankingResult.size(), is(2));
    }
}
