/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.eventanalysis.handoverstage.eCell;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventanalysis.LTEHandoverFailureECellDetailedEAService;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventanalysis.LTEHandoverFailureECellGroupDetailedEAService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHFAECELLDetailedAnalysisSetupResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author evijred
 * @since 2011
 *
 */
public class LTEHFAECellDetailedAnalysisServiceRawTest extends
        BaseDataIntegrityTest<LTEHFAECELLDetailedAnalysisSetupResult> {
    private LTEHandoverFailureECellDetailedEAService lteHandoverFailureECellDetailedEventAnalysisService;

    private LTEHandoverFailureECellGroupDetailedEAService lteHandoverFailureECellGroupDetailedEventAnalysisService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String EVENT_TIME_RAW = "2011-09-20 08:12:00:1212";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String TEST_VALUE_HIER3_ID_1 = "4809532081614999117";

    private static final String TEST_VALUE_HIER3_ID_2 = "3135210477467174911";

    private static final String TEST_VALUE_HIER321_ID_1 = "7210756712490856540";

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    private static final String TEST_VALUE_ENODEB_GROUP = "enodebGroup";

    private static final String TEST_VALUE_THIER3_ID_1 = "4809532081614999117";

    private static final String TEST_VALUE_THIER321_ID_1 = "7210756712490856540";

    private static final int TEST_VALUE_RAT = 2;

    private static final String TEST_VALUE_RAT_DESC = "LTE";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteHandoverFailureECellDetailedEventAnalysisService = new LTEHandoverFailureECellDetailedEAService();
        lteHandoverFailureECellGroupDetailedEventAnalysisService = new LTEHandoverFailureECellGroupDetailedEAService();
        attachDependencies(lteHandoverFailureECellDetailedEventAnalysisService);
        attachDependencies(lteHandoverFailureECellGroupDetailedEventAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(THIER3_HASHID);
        columnsForTable.add(THIER321_HASHID);
        columnsForTable.add(EVENT_TIME_COLUMN);
        columnsForTable.add(MSISDN_PARAM_UPPER_CASE);
        columnsForTable.add(IMSI);
        columnsForTable.add(TAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(MCC);
        columnsForTable.add(MNC);
        columnsForTable.add(CAUSE_CODE_COLUMN);
        columnsForTable.add(DURATION);
        columnsForTable.add(NO_OF_ERABS);
        columnsForTable.add(HO_SOURCE_TARGET_TYPE);
        columnsForTable.add(HO_TYPE);
        columnsForTable.add(RANDOM_ACCESS_TYPE);
        columnsForTable.add(HO_TARGET_SELECTION_TYPE);
        columnsForTable.add(HO_PREP_OUT_ATTEMPT_CAUSE);
        columnsForTable.add(HO_PACKET_FORWARD);
        columnsForTable.add(SPID_VALUE);
        columnsForTable.add(DRX_CONFIG_INDEX);
        columnsForTable.add(HO_OUT_EXEC_ERAB_FAILBITMAP);
        columnsForTable.add(HO_OUT_PREP_ERAB_FAILBITMAP);
        columnsForTable.add(HO_OUT_PREP_ERAB_REQBITMAP);
        columnsForTable.add(HO_OUT_EXEC_ERAB_REQBITMAP);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(RAT);
        columnsForTable.add(PREP_IN_RESULT_UE_CTXT);
        columnsForTable.add(CAUSE_GROUP_3GPP);
        columnsForTable.add(CAUSE_3GPP);
        columnsForTable.add(SRVCC_TYPE);
        columnsForTable.add(HO_EXEC_OUT_ATTEMPT_CAUSE);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(VENDOR);
        columnsForTable.add(HIERARCHY_1);
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
        columnsForTable.add(CAUSE_CODE_COLUMN);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CAUSE_CODE_DESC_COLUMN);
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(VENDOR_NAME);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForTable);
        columnsForTable.clear();
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(RAT);
        columnsForTable.add(RAT_DESC);
        createTemporaryTable(TEMP_DIM_E_SGEH_RAT, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(RAT, TEST_VALUE_RAT);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_BSC);
        valuesForTable.put(MCC, TEST_VALUE_MCC);
        valuesForTable.put(MNC, TEST_VALUE_MNC);
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
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_CAUSE_CODE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_ENODEB_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_ENODEB_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_ENODEB_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(RAT, TEST_VALUE_RAT);
        valuesForTable.put(RAT_DESC, TEST_VALUE_RAT_DESC);
        insertRow(TEMP_DIM_E_SGEH_RAT, valuesForTable);
    }

    private void insertData(final String hierId, final String eventID, final int tac, final String time,
            final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(HIER3_ID, hierId);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
            valuesForTable.put(THIER3_HASHID, TEST_VALUE_THIER3_ID_1);
            valuesForTable.put(THIER321_HASHID, TEST_VALUE_THIER321_ID_1);
            valuesForTable.put(MSISDN_PARAM_UPPER_CASE, TEST_VALUE_MSISDN);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI_1);
            valuesForTable.put(EVENT_TIME_COLUMN, EVENT_TIME_RAW);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(MCC, TEST_VALUE_MCC);
            valuesForTable.put(MNC, TEST_VALUE_MNC);
            valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_HFA_CAUSE_CODE);
            valuesForTable.put(DURATION, TEST_VALUE_DURATION);
            valuesForTable.put(NO_OF_ERABS, TEST_VALUE_NO_OF_ERABS);
            valuesForTable.put(HO_SOURCE_TARGET_TYPE, TEST_VALUE_HO_SOURCE_TARGET_TYPE);
            valuesForTable.put(HO_TYPE, TEST_VALUE_HO_TYPE);
            valuesForTable.put(RANDOM_ACCESS_TYPE, TEST_VALUE_RANDOM_ACCESS_TYPE);
            valuesForTable.put(HO_TARGET_SELECTION_TYPE, TEST_VALUE_HO_TARGET_SELECTION_TYPE);
            valuesForTable.put(HO_PREP_OUT_ATTEMPT_CAUSE, TEST_VALUE_HO_PREP_OUT_ATTEMPT_CAUSE);
            valuesForTable.put(HO_PACKET_FORWARD, TEST_VALUE_HO_PACKET_FORWARD);
            valuesForTable.put(SPID_VALUE, TEST_VALUE_SPID_VALUE);
            valuesForTable.put(DRX_CONFIG_INDEX, TEST_VALUE_DRX_CONFIG_INDEX);
            valuesForTable.put(HO_OUT_EXEC_ERAB_FAILBITMAP, TEST_VALUE_HO_OUT_EXEC_ERAB_FAILBITMAP);
            valuesForTable.put(HO_OUT_PREP_ERAB_FAILBITMAP, TEST_VALUE_HO_OUT_PREP_ERAB_FAILBITMAP);
            valuesForTable.put(HO_OUT_PREP_ERAB_REQBITMAP, TEST_VALUE_HO_OUT_PREP_ERAB_REQBITMAP);
            valuesForTable.put(HO_OUT_EXEC_ERAB_FAILBITMAP, TEST_VALUE_HO_OUT_EXEC_ERAB_FAILBITMAP);
            valuesForTable.put(HO_OUT_EXEC_ERAB_REQBITMAP, TEST_VALUE_HO_OUT_EXEC_ERAB_REQBITMAP);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(RAT, TEST_VALUE_RAT);
            valuesForTable.put(PREP_IN_RESULT_UE_CTXT, TEST_VALUE_PREP_IN_RESULT_UE_CTXT);
            valuesForTable.put(CAUSE_GROUP_3GPP, TEST_VALUE_CAUSE_GROUP_3GPP);
            valuesForTable.put(CAUSE_3GPP, TEST_VALUE_CAUSE_3GPP);
            valuesForTable.put(SRVCC_TYPE, TEST_VALUE_SRVCC_TYPE);
            valuesForTable.put(HO_EXEC_OUT_ATTEMPT_CAUSE, TEST_VALUE_HO_EXEC_OUT_ATTEMPT_CAUSE);
            insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
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
        requestParameters.putSingle(EVENT_ID_SQL_PARAM, eventId);
        requestParameters.putSingle(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteHandoverFailureECellDetailedEventAnalysisService, requestParameters);
    }

    private String getJsonResultForGroup(final String eventId) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(EVENT_ID_SQL_PARAM, eventId);
        requestParameters.putSingle(EVENT_ID_PARAM, eventId);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_ENODEB_GROUP);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteHandoverFailureECellGroupDetailedEventAnalysisService, requestParameters);
    }

    @Test
    public void testEcellAnalysisDetailedPrepX2InEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_X2_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_X2_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepX2OutEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_X2_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecX2InEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_X2_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecX2OutEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepS1InEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_S1_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_S1_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepS1OutEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_PREP_S1_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecS1InEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_S1_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecS1OutEvent_Raw() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepX2InEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_X2_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_X2_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_PREP_X2_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_X2_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepX2OutEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_PREP_X2_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecX2InEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_EXEC_X2_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecX2OutEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepS1InEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_S1_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_S1_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_PREP_S1_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_S1_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedPrepS1OutEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_PREP_S1_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecS1InEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_EXEC_S1_IN_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
    }

    @Test
    public void testEcellAnalysisDetailedExecS1OutEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_1, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_HIER3_ID_2, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);

        final ResultTranslator<LTEHFAECELLDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTEHFAECELLDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTEHFAECELLDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
    }

    private void assertResult(final List<LTEHFAECELLDetailedAnalysisSetupResult> results, final String eventId) {
        assertThat(results.size(), is(2));
        for (final LTEHFAECELLDetailedAnalysisSetupResult rs : results) {
            assertEquals(rs.getEventId(), eventId);
        }
    }
}