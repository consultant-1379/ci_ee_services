/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.CallDropsDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.CallSetupDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.CauseCodeCallFailureEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author etonayr
 * @since 2011
 * 
 */
public class CauseCodeCallFailureDetailedEventAnalysisRawTest extends
        BaseDataIntegrityTest<CauseCodeCallFailureEventAnalysisResult> {

    private CallSetupDetailedEventAnalysisService callSetupEventAnalysisService;

    private CallDropsDetailedEventAnalysisService callDroppedEventAnalysisService;

    private final int HIER3_ID_VALUE = 1;

    // Test Cause Codes    
    private final static Map<String, Object> extendedCauseCodesTestMap = new HashMap<String, Object>();

    static {
        extendedCauseCodesTestMap.put("1", "ERROR_INDICATION");
        extendedCauseCodesTestMap.put("2", "RL_SETUP_PROCEDURE");
        extendedCauseCodesTestMap.put("3", "RL_RECONFIGURATION_PROCEDURE");
        extendedCauseCodesTestMap.put("4", "RADIO_LINK_RECONFIGURATION_PREPARE_FAILURE");
        extendedCauseCodesTestMap.put("5", "RADIO_LINK_RECONFIGURATION_PREPARE");
    }

    @Before
    public void onSetUp() throws Exception {
        callDroppedEventAnalysisService = new CallDropsDetailedEventAnalysisService();
        callSetupEventAnalysisService = new CallSetupDetailedEventAnalysisService();
        attachDependencies(callDroppedEventAnalysisService);
        attachDependencies(callSetupEventAnalysisService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_CauseCode_CallSetup_Raw_OneWeek() throws Exception {
        runTest(callSetupEventAnalysisService, ONE_WEEK);
    }

    @Test
    public void testGetData_CauseCode_CallsDropped_Raw_OneWeek() throws Exception {
        runTest(callDroppedEventAnalysisService, ONE_WEEK);
    }

    @Test
    public void testGetData_CauseCode_CallSetup_Raw() throws Exception {
        runTest(callSetupEventAnalysisService, FIVE_MINUTES);
    }

    @Test
    public void testGetData_CauseCode_CallsDropped_Raw() throws Exception {
        runTest(callDroppedEventAnalysisService, FIVE_MINUTES);
    }

    private void runTest(final GenericService service, final String time) throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, time);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(CAUSE_VALUE, "1");
        final String json = runQuery(service, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<CauseCodeCallFailureEventAnalysisResult> result = getTranslator().translateResult(json,
                CauseCodeCallFailureEventAnalysisResult.class);
        assertThat(result.size(), is(1));

        Collections.sort(result, new SortByCauseValueComparator());

        final CauseCodeCallFailureEventAnalysisResult resultForCauseCode1 = result.get(0);
        assertThat(resultForCauseCode1.getCauseValue(), is(1));
        assertThat(resultForCauseCode1.getDescription(), is("ERROR_INDICATION"));
        assertThat(resultForCauseCode1.getOccurences(), is(1));
        assertThat(resultForCauseCode1.getImpactedSubscribers(), is(1));

    }

    private void insertData() throws SQLException {
        insertIntoDimensionTable();
        insertDataIntoEventTable(DateTimeUtilities.getDateTimeMinus2Minutes());
        insertDataIntoEventTable(DateTimeUtilities.getDateTimeMinusDay(6));
        insertDataIntoTacGroupTable();
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void insertDataIntoEventTable(final String timestamp) throws SQLException {
        final Integer SETUP_EVT_ID = new Integer(CALL_SETUP_FAILURE_EVENT_ID);
        for (int i = 1; i < 6; i++) {
            final Map<String, Object> valuesForCauseCodeCFATable = new HashMap<String, Object>();
            valuesForCauseCodeCFATable.put(EVENT_ID, SETUP_EVT_ID);
            valuesForCauseCodeCFATable.put(EXTENDED_CAUSE_VALUE, i);
            valuesForCauseCodeCFATable.put(DATETIME_ID, timestamp);
            valuesForCauseCodeCFATable.put(IMSI, SAMPLE_IMSI);
            valuesForCauseCodeCFATable.put(CAUSE_VALUE, i);
            valuesForCauseCodeCFATable.put(TAC, SAMPLE_TAC);
            valuesForCauseCodeCFATable.put(HIER3_ID, HIER3_ID_VALUE);
            insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForCauseCodeCFATable);
        }

        final Integer DROP_EVT_ID = new Integer(CALL_DROP_EVENT_ID);

        for (int i = 1; i < 6; i++) {
            final Map<String, Object> valuesForCauseCodeCFATable = new HashMap<String, Object>();
            valuesForCauseCodeCFATable.put(EVENT_ID, DROP_EVT_ID);
            valuesForCauseCodeCFATable.put(EXTENDED_CAUSE_VALUE, i);
            valuesForCauseCodeCFATable.put(DATETIME_ID, timestamp);
            valuesForCauseCodeCFATable.put(IMSI, SAMPLE_IMSI);
            valuesForCauseCodeCFATable.put(CAUSE_VALUE, i);
            valuesForCauseCodeCFATable.put(TAC, SAMPLE_TAC);
            valuesForCauseCodeCFATable.put(HIER3_ID, HIER3_ID_VALUE);
            insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForCauseCodeCFATable);
        }

    }

    private void insertIntoDimensionTable() throws SQLException {
        for (int i = 1; i < 6; i++) {
            final Map<String, Object> values = new HashMap<String, Object>();
            values.put(EXTENDED_CAUSE_VALUE, i);
            values.put(EXTENDED_CAUSE_VALUE_DESCRIPTION, extendedCauseCodesTestMap.get("" + i));
            insertRow(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE, values);
        }
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForDIMECauseCodeTable = new ArrayList<String>();
        columnsForDIMECauseCodeTable.add(EXTENDED_CAUSE_VALUE);
        columnsForDIMECauseCodeTable.add(EXTENDED_CAUSE_VALUE_DESCRIPTION);
        createTemporaryTable(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE, columnsForDIMECauseCodeTable);

        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(CAUSE_VALUE);
        columnsForEventTable.add(EXTENDED_CAUSE_VALUE);
        columnsForEventTable.add(EVENT_ID);
        columnsForEventTable.add(DATETIME_ID);
        columnsForEventTable.add(IMSI);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(HIER3_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEventTable);
    }

    class SortByCauseValueComparator implements Comparator<CauseCodeCallFailureEventAnalysisResult> {

        @Override
        public int compare(final CauseCodeCallFailureEventAnalysisResult o1,
                final CauseCodeCallFailureEventAnalysisResult o2) {
            final int id1 = o1.getCauseValue();
            final int id2 = o2.getCauseValue();
            if (id1 < id2) {
                return -1;
            } else if (id1 > id2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
