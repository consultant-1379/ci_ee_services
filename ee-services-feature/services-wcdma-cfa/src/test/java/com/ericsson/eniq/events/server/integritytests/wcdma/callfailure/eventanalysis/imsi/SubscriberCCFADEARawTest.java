/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.imsi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER321_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RNCID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RNC_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_SETUP_FAILURE_EVENT_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ALTERNATIVE_FDN;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CRNC_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DATA_IN_DL_RLC_BUFFERS;
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
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RNC_ID_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SEVERITY_INDICATOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SOURCE_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TARGET_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;
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
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.SubscriberCallSetupFailureDetailedEAService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberCallFailureDetailedEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 *
 */
public class SubscriberCCFADEARawTest extends BaseDataIntegrityTest<SubscriberCallFailureDetailedEventAnalysisResult> {

    private SubscriberCallSetupFailureDetailedEAService subscriberCallSetupFailureDetailedEventAnalysisService;

    private static final int TEST_IMSI_1 = 11111119;

    private static final int TEST_IMSI_2 = 22222229;

    private static final int TEST_IMSI_3 = 33333339;

    private static final String TEST_TAC = "1280600"; //belongs to: Apple,iPad 2 A1396

    @Before
    public void onSetUp() throws Exception {
        subscriberCallSetupFailureDetailedEventAnalysisService = new SubscriberCallSetupFailureDetailedEAService();
        attachDependencies(subscriberCallSetupFailureDetailedEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        insertCustomDimData();
        insertData();
    }

    /*
     * The expected outcome is for IMSI_1 to have 3 Failures while IMSI_2 will have only 2.
     * They should be ranked accordingly.
     */
    @Test
    public void testGetRankingData_SUBSCRIBER_CFA() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, "grid");
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(IMSI, new Integer(TEST_IMSI_1).toString());
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(subscriberCallSetupFailureDetailedEventAnalysisService, requestParameters);

        System.out.println(json);

        final ResultTranslator<SubscriberCallFailureDetailedEventAnalysisResult> rt = getTranslator();
        final List<SubscriberCallFailureDetailedEventAnalysisResult> rankingResult = rt.translateResult(json,
                SubscriberCallFailureDetailedEventAnalysisResult.class);

        assertThat("There should be exactly 3 results!", rankingResult.size(), is(1));

        final SubscriberCallFailureDetailedEventAnalysisResult worstSubscriber = rankingResult.get(0);
        assertTrue("Unexpected TAC in result!", worstSubscriber.getTac() == new Integer(TEST_TAC));
        assertThat("Unexpected Terminal Make in result!", worstSubscriber.getTerminalMake(), is("Apple Inc"));
        assertThat("Unexpected Event Type in result", worstSubscriber.getEventType(), is("Call Setup Failures"));

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
        columnsForEETable.add(ApplicationConstants.HIER321_ID);
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

        // Temporary DIM tables with custom values for this test
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

    void insertCustomDimData() throws SQLException {
        populateDIM_E_SGEH_HIER321_CELL("RNC06-1-1", 1501, "ONRM_ROOT_MO_R:RNC06:RNC06");
        populateDIM_E_RAN_RNC("ONRM_ROOT_MO_R:RNC06:RNC06", "RNC06");
        populateDIM_E_RAN_RNCFUNCTION("RNC06", 6);
    }

    void populateDIM_E_RAN_RNCFUNCTION(final String rnc_id, final int rncId) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(RNC_ID, rnc_id);
        valuesForTable.put(RNCID, rncId);
        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, valuesForTable);
    }

    void populateDIM_E_RAN_RNC(final String altFdn, final String rncId) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(ALTERNATIVE_FDN, altFdn);
        valuesForTable.put(RNC_ID, rncId);
        insertRow(TEMP_DIM_E_RAN_RNC, valuesForTable);
    }

    void populateDIM_E_SGEH_HIER321_CELL(final String cellId, final int cid, final String hier3) throws SQLException {
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

        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVALUATION_CASE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR);//
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);//

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

    /*
     * Control data:
     *      4 CallSetupFailures events for each IMSI (to make sure we count the right type of event)
     *      1 CallSetupFailures for IMSI_3  (this is only to verify that the correct IMSIs are counted)
     *
     * Relevant data:
     *      3 CallDrops for IMSI_1
     *      2 CallDrops for IMSI_2
     */
    private void insertData() throws Exception {

        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertIMSI(TEST_IMSI_1, dateTime, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, 6, 3);
        insertIMSI(TEST_IMSI_1, dateTime, CALL_DROP_EVENT_ID, TEST_TAC, 6, 4);

        insertIMSI(TEST_IMSI_2, dateTime, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, 6, 2);
        insertIMSI(TEST_IMSI_2, dateTime, CALL_DROP_EVENT_ID, TEST_TAC, 6, 4);

        insertIMSI(TEST_IMSI_3, dateTime, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, 6, 1);
        insertIMSI(TEST_IMSI_3, dateTime, CALL_DROP_EVENT_ID, TEST_TAC, 6, 4);

    }

    private void insertIMSI(final int imsi, final String dt, final String eventID, final String tac, final int rncId1,
            final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
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
            valuesForTable.put(C_ID_SERV_HSDSCH_CELL, 1);
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
}
