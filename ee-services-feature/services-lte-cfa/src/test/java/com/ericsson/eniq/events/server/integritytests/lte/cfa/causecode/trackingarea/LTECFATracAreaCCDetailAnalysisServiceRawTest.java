/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.causecode.trackingarea;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.causecodeanalysis.LTECFATrackingAreaCCDetailedEAService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureSubscriberDetailedAnalysisSetupResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echchik
 * @since 2011
 *
 */
public class LTECFATracAreaCCDetailAnalysisServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureSubscriberDetailedAnalysisSetupResult> {

    private LTECFATrackingAreaCCDetailedEAService lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String EVENT_TIME_RAW = "2011-09-20 08:12:00:1212";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String TEST_VALUE_TRAC_1 = "1";

    private static final String TEST_VALUE_TRAC_2 = "2";

    private static final String TEST_VALUE_HIER321_ID = "4809532081614999117";

    private static final String TEST_VALUE_HIER3_ID = "7210756712490856540";

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    private static final String TEST_VALUE_TRAC_GROUP = "tracGroup";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService = new LTECFATrackingAreaCCDetailedEAService();
        attachDependencies(lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(TRAC);
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(EVENT_TIME_COLUMN);
        columnsForTable.add(MSISDN_PARAM_UPPER_CASE);
        columnsForTable.add(IMSI);
        columnsForTable.add(TAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CAUSE_CODE_COLUMN);
        columnsForTable.add(MCC);
        columnsForTable.add(MNC);
        columnsForTable.add(DURATION);
        columnsForTable.add(RRC_ESTABL_CAUSE);
        columnsForTable.add(NO_OF_ERABS);
        columnsForTable.add(INTERNAL_RELEASE_CAUSE);
        columnsForTable.add(TRIGGERING_NODE);
        columnsForTable.add(GUMMEI_TYPE);
        columnsForTable.add(TTI_BUNDLING_MODE);
        columnsForTable.add(ACCUMULATED_UL_REQ_GBR);
        columnsForTable.add(ACCUMULATED_UL_ADM_GBR);
        columnsForTable.add(ACCUMULATED_DL_REQ_GBR);
        columnsForTable.add(ACCUMULATED_DL_ADM_GBR);
        columnsForTable.add(ERAB_DATA_LOST_BITMAP);
        columnsForTable.add(ERAB_DATA_LOST);
        columnsForTable.add(ERAB_RELEASE_SUCC);
        columnsForTable.add(HO_OUT_PREP_ERAB_FAILBITMAP);
        columnsForTable.add(HO_OUT_PREP_ERAB_FAIL);
        columnsForTable.add(ERAB_HO_PREP_DATA_LOST);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForTable);
        columnsForTable.add(TRAC);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(HIER321_ID);
        columnsForTable.add(VENDOR);
        columnsForTable.add(HIERARCHY_1);
        columnsForTable.add(HIERARCHY_3);
        columnsForTable.add(MCC);
        columnsForTable.add(MNC);
        columnsForTable.add(TRAC);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(CAUSE_CODE_COLUMN);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(CAUSE_CODE_DESC_COLUMN);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_CAUSE_CODE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(INTERNAL_RELEASE_CAUSE);
        columnsForTable.add(INTERNAL_RELEASE_CAUSE_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_INTERNAL_RELEASE_CAUSE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(RRC_ESTABL_CAUSE);
        columnsForTable.add(RRC_ESTABL_CAUSE_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_RRC_ESTABL_CAUSE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(TRIGGERING_NODE);
        columnsForTable.add(TRIGGERING_NODE_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_TRIGGERING_NODE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(VENDOR_NAME);
        columnsForTable.add(MARKETING_NAME);
        columnsForTable.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(TRAC);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_LTE_TRAC, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(GUMMEI_TYPE);
        columnsForTable.add(GUMMEI_TYPE_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_GUMMEI_TYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(BUNDLING_MODE);
        columnsForTable.add(BUNDLING_MODE_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_TTI_BUNDLING_MODE, columnsForTable);

    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID);
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID);
        valuesForTable.put(VENDOR, ERICSSON);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL);
        valuesForTable.put(HIERARCHY_3, TEST_VALUE_BSC);
        valuesForTable.put(MCC, TEST_VALUE_MCC);
        valuesForTable.put(MNC, TEST_VALUE_MNC);
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_1);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_RRC_CONN_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_RRC_CONN_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_S1_SIG_CONN_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_S1_SIG_CONN_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_INITIAL_CTXT_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_ERAB_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_ERAB_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_UE_CTXT_RELEASE");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TRAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TRAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(GUMMEI_TYPE, TEST_GUMMEI_TYPE);
        valuesForTable.put(GUMMEI_TYPE_DESC, TEST_VALUE_GUMMEI_TYPE);
        insertRow(TEMP_DIM_E_LTE_CFA_GUMMEI_TYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(BUNDLING_MODE, TEST_BUNDLING_MODE);
        valuesForTable.put(BUNDLING_MODE_DESC, TEST_VALUE_BUNDLING_MODE);
        insertRow(TEMP_DIM_E_LTE_CFA_TTI_BUNDLING_MODE, valuesForTable);


    }

    private void insertData(final String trac, final String eventID, final int tac, final String time,
            final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(TRAC, trac);
            valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID);
            valuesForTable.put(MSISDN_PARAM_UPPER_CASE, TEST_VALUE_MSISDN);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI_1);
            valuesForTable.put(EVENT_TIME_COLUMN, EVENT_TIME_RAW);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(CAUSE_CODE_COLUMN, TEST_VALUE_LTE_CFA_CAUSE_CODE);
            valuesForTable.put(MCC, TEST_VALUE_MCC);
            valuesForTable.put(MNC, TEST_VALUE_MNC);
            valuesForTable.put(DURATION, TEST_VALUE_DURATION);
            valuesForTable.put(GUMMEI_TYPE, TEST_GUMMEI_TYPE);
            valuesForTable.put(TTI_BUNDLING_MODE, TEST_TTI_BUNDLING_MODE);
            valuesForTable.put(RRC_ESTABL_CAUSE, TEST_VALUE_SETUP_REQ_QCI);
            valuesForTable.put(NO_OF_ERABS, TEST_VALUE_NO_OF_ERABS);
            valuesForTable.put(ACCUMULATED_UL_REQ_GBR, TEST_VALUE_ACCUMULATED_UL_REQ_GBR);
            valuesForTable.put(ACCUMULATED_UL_ADM_GBR, TEST_VALUE_ACCUMULATED_UL_ADM_GBR);
            valuesForTable.put(ACCUMULATED_DL_REQ_GBR, TEST_VALUE_ACCUMULATED_DL_REQ_GBR);
            valuesForTable.put(ACCUMULATED_DL_ADM_GBR, TEST_VALUE_ACCUMULATED_DL_ADM_GBR);
            valuesForTable.put(INTERNAL_RELEASE_CAUSE, TEST_VALUE_INTERNAL_RELEASE_CAUSE);
            valuesForTable.put(ERAB_DATA_LOST_BITMAP, TEST_VALUE_ERAB_DATA_LOST_BITMAP);
            valuesForTable.put(ERAB_DATA_LOST, TEST_VALUE_ERAB_DATA_LOST);
            valuesForTable.put(ERAB_RELEASE_SUCC, TEST_VALUE_ERAB_RELEASE_SUCC);
            valuesForTable.put(HO_OUT_PREP_ERAB_FAILBITMAP, TEST_VALUE_HO_OUT_PREP_ERAB_FAILBITMAP);
            valuesForTable.put(HO_OUT_PREP_ERAB_FAIL, TEST_VALUE_HO_OUT_PREP_ERAB_FAIL);
            valuesForTable.put(ERAB_HO_PREP_DATA_LOST, TEST_VALUE_ERAB_HO_PREP_DATA_LOST);
            valuesForTable.put(TRIGGERING_NODE, TEST_VALUE_TRIGGERING_NODE);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
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
        requestParameters.putSingle(CAUSE_CODE_PARAM, Integer.toString(TEST_VALUE_LTE_CFA_CAUSE_CODE));
        requestParameters.putSingle(TRAC, TEST_VALUE_TRAC_1);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService, requestParameters);
    }

    private String getJsonResultForGroup(final String eventId) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(EVENT_ID_PARAM, eventId);
        requestParameters.putSingle(CAUSE_CODE_PARAM, Integer.toString(TEST_VALUE_LTE_CFA_CAUSE_CODE));
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_TRAC_GROUP);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        return runQuery(lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService, requestParameters);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedRrcConnSetupEvent_Raw() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_RRC_CONN_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_RRC_CONN_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_RRC_CONN_SETUP);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_RRC_CONN_SETUP);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedS1SigSetupEvent_Raw() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_S1_SIG_CONN_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_S1_SIG_CONN_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_S1_SIG_CONN_SETUP);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_S1_SIG_CONN_SETUP);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedInitialCxtSetupEvent_Raw() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_INITIAL_CTXT_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_INITIAL_CTXT_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_INITIAL_CTXT_SETUP);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_INITIAL_CTXT_SETUP);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedUeCxtReleaseEvent_Raw() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_UE_CTXT_RELEASE, SAMPLE_TAC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_UE_CTXT_RELEASE, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
                DATETIME_ID_RAW, 2);

        final String json = getJsonResult(INTERNAL_PROC_UE_CTXT_RELEASE);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_UE_CTXT_RELEASE);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedRrcConnSetupEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_RRC_CONN_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_RRC_CONN_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_RRC_CONN_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_RRC_CONN_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_RRC_CONN_SETUP);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_RRC_CONN_SETUP);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedS1SigSetupEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_S1_SIG_CONN_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_S1_SIG_CONN_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_S1_SIG_CONN_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_S1_SIG_CONN_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_S1_SIG_CONN_SETUP);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_S1_SIG_CONN_SETUP);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedInitialCxtSetupEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_INITIAL_CTXT_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_INITIAL_CTXT_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_INITIAL_CTXT_SETUP, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_INITIAL_CTXT_SETUP, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_INITIAL_CTXT_SETUP);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_INITIAL_CTXT_SETUP);
    }

    @Test
    public void testTrackingAreaCauseCodeAnalysisDetailedUeCxtReleaseEvent_RawGrp() throws Exception {

        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_UE_CTXT_RELEASE, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_1, INTERNAL_PROC_UE_CTXT_RELEASE, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_UE_CTXT_RELEASE, SAMPLE_TAC, DATETIME_ID_RAW, 1);
        //        insertData(TEST_VALUE_TRAC_2, INTERNAL_PROC_UE_CTXT_RELEASE, Integer.parseInt(TEST_VALUE_EXCLUSIVE_TAC),
        //                DATETIME_ID_RAW, 1);

        final String json = getJsonResultForGroup(INTERNAL_PROC_UE_CTXT_RELEASE);

        final ResultTranslator<LTECallFailureSubscriberDetailedAnalysisSetupResult> rt = getTranslator();
        final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> eventResult = rt.translateResult(json,
                LTECallFailureSubscriberDetailedAnalysisSetupResult.class);
        assertResult(eventResult, INTERNAL_PROC_UE_CTXT_RELEASE);
    }

    private void assertResult(final List<LTECallFailureSubscriberDetailedAnalysisSetupResult> results,
            final String eventId) {
        assertThat(results.size(), is(2));
        for (final LTECallFailureSubscriberDetailedAnalysisSetupResult rs : results) {
            assertEquals(rs.getEventId(), eventId);
        }
    }
}