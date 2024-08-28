/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.imsigroup;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.SubscriberEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberCFEventAnalysisByImsiGroupResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eflatib
 *
 */
public class SubscriberCFAEventByImsiGroupRawTest extends
        BaseDataIntegrityTest<SubscriberCFEventAnalysisByImsiGroupResult> {

    private SubscriberEventAnalysisService subscriberEventAnalysisService;

    private static final int TEST_IMSI_1 = 11111119;

    private static final int TEST_IMSI_2 = 22222229;

    private static final int TEST_IMSI_3 = 33333339;

    private static final String TEST_GROUP = "TEST_GROUP";

    private static final String TEST_TAC = "1280600"; //belongs to: Apple,iPad 2 A1396

    @Before
    public void onSetUp() throws Exception {
        subscriberEventAnalysisService = new SubscriberEventAnalysisService();
        attachDependencies(subscriberEventAnalysisService);
        createAndPopulateLookupTables();
        createTables();
        insertData();
    }

    /*
     * The expected outcome is for CallDrops to have 6 Occurrences and CallSetupFailures will have 12.
     * They should be ranked accordingly.
     */
    @Test
    public void testGetData_SUBSCRIBER_EVENTANALYSIS_IMSIGROUP_CFA() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_GROUP);
        requestParameters.putSingle(TYPE_PARAM, "IMSI");
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(subscriberEventAnalysisService, requestParameters);

        System.out.println(json);

        final ResultTranslator<SubscriberCFEventAnalysisByImsiGroupResult> rt = getTranslator();
        final List<SubscriberCFEventAnalysisByImsiGroupResult> rankingResult = rt.translateResult(json,
                SubscriberCFEventAnalysisByImsiGroupResult.class);

        assertThat("There should be exactly 2 results!", rankingResult.size(), is(2));
        final SubscriberCFEventAnalysisByImsiGroupResult firstData = rankingResult.get(0);
        assertThat("Unexpected Event for first data Type in result", firstData.getEventType(), is(CALL_DROPS_TEST_JSON));
        assertTrue("Unexpected Number of Failures for first data in result!", firstData.getNumFailures() == 6);
        assertTrue("Unexpected Impacted Subscriber for first data in result!", firstData.getImpactedSubscriber() == 3);

        final SubscriberCFEventAnalysisByImsiGroupResult secondData = rankingResult.get(1);
        assertThat("Unexpected Event for second data Type in result", secondData.getEventType(),
                is("Call Setup Failures"));
        assertTrue("Unexpected Number for second data os Failures in result!", secondData.getNumFailures() == 12);
        assertTrue("Unexpected Impacted for second data Subscriber in result!", secondData.getImpactedSubscriber() == 3);

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
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEETable);

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

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();

        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);

        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    /*
     *      6  CallDrops
     *      12 CallSetupFailures
     *      +1 Exclusive TAC as control data
     */
    private void insertData() throws Exception {

        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertIMSI(TEST_IMSI_1, dateTime, CALL_DROP_EVENT_ID, TEST_TAC, 6, 3);
        insertIMSI(TEST_IMSI_1, dateTime, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, 6, 4);

        insertIMSI(TEST_IMSI_2, dateTime, CALL_DROP_EVENT_ID, TEST_TAC, 6, 2);
        insertIMSI(TEST_IMSI_2, dateTime, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, 6, 4);

        insertIMSI(TEST_IMSI_3, dateTime, CALL_DROP_EVENT_ID, TEST_TAC, 6, 1);
        insertIMSI(TEST_IMSI_3, dateTime, CALL_SETUP_FAILURE_EVENT_ID, TEST_TAC, 6, 4);
        
        // Insert exclusive TAC to RAW table
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI, 2255887);
        valuesForTable.put(EVENT_ID, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);
        valuesForTable.put(DATETIME_ID, dateTime);
        valuesForTable.put(EVENT_TIME, dateTime);
        valuesForTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        valuesForTable.put(RNC_ID_1, 6);
        valuesForTable.put(C_ID_1, 1501);
        valuesForTable.put(CAUSE_VALUE, 0);
        valuesForTable.put(EXTENDED_CAUSE_VALUE, 0);
        valuesForTable.put(EXCEPTION_CLASS, 0);
        valuesForTable.put(EVALUATION_CASE, 0);
        valuesForTable.put(PROCEDURE_INDICATOR, 0);
        valuesForTable.put(SEVERITY_INDICATOR, 0);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
        
        // Insert exclusive TAC to #GROUP_TYPE_E_TAC
        final Map<String, Object> valuesForGTable = new HashMap<String, Object>();
        valuesForGTable.put(GROUP_NAME, "EXCLUSIVE_TAC");
        valuesForGTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForGTable);

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
            insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
        }
    }
}
