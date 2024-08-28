/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.imsigroup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GROUP_NAME_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_SETUP_FAILURE_EVENT_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CRNC_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DATA_IN_DL_RLC_BUFFERS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVALUATION_CASE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVENT_TIME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXCEPTION_CLASS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXTENDED_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIVE_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_DL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_UL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GROUP_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.HIERARCHY_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.PROCEDURE_INDICATOR;
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
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SEVERITY_INDICATOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SOURCE_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SOURCE_CONNECTION_PROPERTIES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TARGET_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TARGET_CONNECTION_PROPERTIES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.WANTED_CONNECTION_PROPERTIES;
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
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RSCP_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ULINT_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_HIER321_CELL;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_TAC;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_GROUP_TYPE_E_IMSI;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.SubscriberCallFailureFAforEAByImsiGroupService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberCFFailureAnalysisEAbyImsiGroupResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Subscriber CallFailure Failure Analysis for Event Analysis by IMSI Group Raw Data Test
 *
 * @author eflatib
 *
 */
public class SubscriberCallFailureFA_EAbyImsiGroupRawTest extends
        BaseDataIntegrityTest<SubscriberCFFailureAnalysisEAbyImsiGroupResult> {

    private SubscriberCallFailureFAforEAByImsiGroupService subscriberCallFailureFailureAnalysisForEventAnalysisByImsiGroupService;

    private static final int TEST_IMSI_1 = 11111119;

    private static final int TEST_IMSI_2 = 22222229;

    private static final int TEST_IMSI_3 = 33333339;

    private static final String TEST_GROUP = "TEST_GROUP";

    private static final String TEST_TAC = "1280600"; //belongs to: Apple,iPad 2 A1396

    private final String HASH_ID = "1234";

    private String timestamp;

    private String timestampWithOffset;

    @Before
    public void onSetUp() throws Exception {
        subscriberCallFailureFailureAnalysisForEventAnalysisByImsiGroupService = new SubscriberCallFailureFAforEAByImsiGroupService();
        attachDependencies(subscriberCallFailureFailureAnalysisForEventAnalysisByImsiGroupService);

        timestamp = DateTimeUtilities.getDateTimeMinus2Minutes().concat(".0");
        timestampWithOffset = appendZeroMilliseconds(DateTimeUtilities.getDateTimeMinus2MinutesWithOffSet(1));

        createAndPopulateLookupTables();
        createTables();
        insertCustomDimData();
        insertData();
    }

    @Test
    public void testGetData_SUBSCRIBER_EVENTANALYSIS_IMSIGROUP_CFA_CallSetupFailure() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(EVENT_ID_PARAM, CALL_SETUP_FAILURE_EVENT_ID);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_GROUP);
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(subscriberCallFailureFailureAnalysisForEventAnalysisByImsiGroupService,
                requestParameters);

        System.out.println(json);

        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "SUBSCRIBER_WCDMA_CFA_FAILURE_ANALYSIS_BY_GROUP");

        final ResultTranslator<SubscriberCFFailureAnalysisEAbyImsiGroupResult> rt = getTranslator();
        final List<SubscriberCFFailureAnalysisEAbyImsiGroupResult> rankingResult = rt.translateResult(json,
                SubscriberCFFailureAnalysisEAbyImsiGroupResult.class);

        assertThat("There should be exactly 10 results!", rankingResult.size(), is(10));

        final SubscriberCFFailureAnalysisEAbyImsiGroupResult firstData = rankingResult.get(0);
        assertThat("Unexpected TAC for first data in result!", firstData.getTac(), is(new Integer(TEST_TAC)));
        assertThat("Unexpected TAC for first data in result!", firstData.getTerminalMake(), is("Apple Inc"));
        assertThat("Unexpected Number of Failures for first data in result!", firstData.getTerminalModel(),
                is("iPad 2 A1396"));
        assertThat("Unexpected Impacted Subscriber for first data in result!", firstData.getEventType(),
                is("Call Setup Failures"));
        assertThat(firstData.getEventTime(), is(timestampWithOffset));

        final SubscriberCFFailureAnalysisEAbyImsiGroupResult secondData = rankingResult.get(0);
        assertThat("Unexpected TAC for first data in result!", secondData.getTac(), is(new Integer(TEST_TAC)));
        assertThat("Unexpected TAC for first data in result!", secondData.getTerminalMake(), is("Apple Inc"));
        assertThat("Unexpected Number of Failures for first data in result!", secondData.getTerminalModel(),
                is("iPad 2 A1396"));
        assertThat("Unexpected Impacted Subscriber for first data in result!", secondData.getEventType(),
                is("Call Setup Failures"));
        assertThat(secondData.getEventTime(), is(timestampWithOffset));
    }

    @Test
    public void testGetData_SUBSCRIBER_EVENTANALYSIS_IMSIGROUP_CFA_CallDrop() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_GROUP);
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(subscriberCallFailureFailureAnalysisForEventAnalysisByImsiGroupService,
                requestParameters);
        System.out.println(json);
        validateAgainstGridDefinition(json, "SUBSCRIBER_WCDMA_CFA_FAILURE_ANALYSIS_BY_GROUP");
        final ResultTranslator<SubscriberCFFailureAnalysisEAbyImsiGroupResult> rt = getTranslator();
        final List<SubscriberCFFailureAnalysisEAbyImsiGroupResult> rankingResult = rt.translateResult(json,
                SubscriberCFFailureAnalysisEAbyImsiGroupResult.class);

        assertThat("There should be exactly 6 results!", rankingResult.size(), is(6));

        final SubscriberCFFailureAnalysisEAbyImsiGroupResult firstData = rankingResult.get(0);
        assertThat("Unexpected TAC for first data in result!", firstData.getTac(), is(new Integer(TEST_TAC)));
        assertThat("Unexpected TAC for first data in result!", firstData.getTerminalMake(), is("Apple Inc"));
        assertThat("Unexpected Number of Failures for first data in result!", firstData.getTerminalModel(),
                is("iPad 2 A1396"));
        assertThat("Unexpected Impacted Subscriber for first data in result!", firstData.getEventType(),
                is("Call Drops"));

        final SubscriberCFFailureAnalysisEAbyImsiGroupResult secondData = rankingResult.get(0);
        assertThat("Unexpected TAC for first data in result!", secondData.getTac(), is(new Integer(TEST_TAC)));
        assertThat("Unexpected TAC for first data in result!", secondData.getTerminalMake(), is("Apple Inc"));
        assertThat("Unexpected Number of Failures for first data in result!", secondData.getTerminalModel(),
                is("iPad 2 A1396"));
        assertThat("Unexpected Impacted Subscriber for first data in result!", secondData.getEventType(),
                is("Call Drops"));
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(IMSI);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(EVENT_TIME);
        columnsForEETable.add(TAC);
        columnsForEETable.add(HIER3_CELL_ID);
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(CAUSE_VALUE);
        columnsForEETable.add(EXTENDED_CAUSE_VALUE);
        columnsForEETable.add(EXCEPTION_CLASS);
        columnsForEETable.add(EVALUATION_CASE);
        columnsForEETable.add(PROCEDURE_INDICATOR);
        columnsForEETable.add(SEVERITY_INDICATOR);

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
        columnsForEETable.add(SOURCE_CONNECTION_PROPERTIES);
        columnsForEETable.add(TARGET_CONNECTION_PROPERTIES);
        columnsForEETable.add(WANTED_CONNECTION_PROPERTIES);
        columnsForEETable.add(RAN_DISCONNECTION_CODE);
        columnsForEETable.add(RAN_DISCONNECTION_SUBCODE);
        columnsForEETable.add(TRIGGER_POINT);
        columnsForEETable.add(RRC_ESTABLISHMENT_CAUSE);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEETable);

        // Temporary DIM tables with custom values for this test
        final Collection<String> columnsForDESTable = new ArrayList<String>();
        columnsForDESTable.add(CELL_ID);
        columnsForDESTable.add(HIER3_CELL_ID);
        columnsForDESTable.add(HIER3_ID);
        columnsForDESTable.add(HIERARCHY_3);
        columnsForDESTable.add(CID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForDESTable);

        // Temporary GROUP tables with custom values for this test
        final Collection<String> columnsForGITable = new ArrayList<String>();
        columnsForGITable.add(GROUP_NAME);
        columnsForGITable.add(IMSI);
        createTemporaryTable(TEMP_GROUP_TYPE_E_IMSI, columnsForGITable);

        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI, TEST_IMSI_1);
        valuesForTable.put(GROUP_NAME, TEST_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForTable);

        final Map<String, Object> valuesForTable2 = new HashMap<String, Object>();
        valuesForTable2.put(IMSI, TEST_IMSI_2);
        valuesForTable2.put(GROUP_NAME, TEST_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForTable2);

        final Map<String, Object> valuesForTable3 = new HashMap<String, Object>();
        valuesForTable3.put(IMSI, TEST_IMSI_3);
        valuesForTable3.put(GROUP_NAME, TEST_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForTable3);

    }

    void insertCustomDimData() throws SQLException {
        populateDIM_E_SGEH_HIER321_CELL("RNC06-1-1", HASH_ID, "ONRM_ROOT_MO_R:RNC06:RNC06");
    }

    void populateDIM_E_SGEH_HIER321_CELL(final String cellId, final String hashId, final String hier3)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(CELL_ID, cellId);
        valuesForTable.put(HIER3_CELL_ID, hashId);
        valuesForTable.put(HIER3_ID, hashId);
        valuesForTable.put(HIERARCHY_3, hier3);
        valuesForTable.put(CID, 1501);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForTable);
    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();

        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVALUATION_CASE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);//
        lookupTables.add(TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR);//
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);//
        lookupTables.add(TEMP_DIM_E_RAN_RABTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_ULINT_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_RSCP_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_ECNO_MAPPING);
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

        DateTimeUtilities.getDateTimeMinus2Minutes();

        insertIMSI(TEST_IMSI_1, timestamp, CALL_DROP_EVENT_ID, TEST_TAC, HASH_ID, 3);
        insertIMSI(TEST_IMSI_1, timestamp, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, HASH_ID, 4);

        insertIMSI(TEST_IMSI_2, timestamp, CALL_DROP_EVENT_ID, TEST_TAC, HASH_ID, 2);
        insertIMSI(TEST_IMSI_2, timestamp, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, HASH_ID, 4);

        insertIMSI(TEST_IMSI_3, timestamp, CALL_DROP_EVENT_ID, TEST_TAC, HASH_ID, 1);
        insertIMSI(TEST_IMSI_3, timestamp, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, HASH_ID, 4);

    }

    private void insertIMSI(final int imsi, final String dt, final String eventID, final String tac,
            final String hashId, final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(IMSI, imsi);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(EVENT_TIME, dt);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(HIER3_CELL_ID, hashId);
            valuesForTable.put(HIER3_ID, hashId);
            valuesForTable.put(CAUSE_VALUE, 0);
            valuesForTable.put(EXTENDED_CAUSE_VALUE, 0);
            valuesForTable.put(EXCEPTION_CLASS, 0);
            valuesForTable.put(EVALUATION_CASE, 0);
            valuesForTable.put(PROCEDURE_INDICATOR, 0);
            valuesForTable.put(SEVERITY_INDICATOR, 0);
            valuesForTable.put(SOURCE_CONF, 1);
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
            valuesForTable.put(SOURCE_CONNECTION_PROPERTIES, 1);
            valuesForTable.put(TARGET_CONNECTION_PROPERTIES, 1);
            valuesForTable.put(WANTED_CONNECTION_PROPERTIES, 1);
            valuesForTable.put(RAN_DISCONNECTION_CODE, 1);
            valuesForTable.put(RAN_DISCONNECTION_SUBCODE, 1);
            valuesForTable.put(TRIGGER_POINT, 1);
            valuesForTable.put(RRC_ESTABLISHMENT_CAUSE, 1);
            insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
        }
    }
}
