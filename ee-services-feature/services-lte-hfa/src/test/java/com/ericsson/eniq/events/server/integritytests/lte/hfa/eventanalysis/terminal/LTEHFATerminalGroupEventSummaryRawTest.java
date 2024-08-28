/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.eventanalysis.terminal;

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventanalysis.TerminalEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHFATerminalGroupEventSummaryResultRaw;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CATEGORY_ID_2_EXEC;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CATEGORY_ID_2_PREP;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author evijred
 * @since 2012
 *
 */
public class LTEHFATerminalGroupEventSummaryRawTest extends
        BaseDataIntegrityTest<LTEHFATerminalGroupEventSummaryResultRaw> {

    private TerminalEventSummaryService terminalEventSummaryService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final int TEST_VALUE_TAC_1 = 35927204;
    
    private static final int TEST_VALUE_TAC_2 = 35927204;

    private static final String TEST_VALUE_IMSI = "11111119";
    
    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        terminalEventSummaryService = new TerminalEventSummaryService();
        attachDependencies(terminalEventSummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);
        createTable();
        insertTopoData();
        insertRawData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(TAC);
        columnsForTable.add(CATEGORY_ID_SQL_PARAM);
        columnsForTable.add(DATETIME_ID);
        columnsForTable.add(EVENT_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        columnsForTable.add(CATEGORY_ID_SQL_PARAM);
        columnsForTable.add(CATEGORY_ID_2_DESC);       
        createTemporaryTable(TEMP_DIM_E_LTE_HFA_EVENTTYPE, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_S1_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_S1_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_X2_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_EXEC_X2_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_EXEC);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.EXECUTION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_S1_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_S1_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_X2_IN");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_HO_PREP_X2_OUT");
        valuesForTable.put(CATEGORY_ID_SQL_PARAM, EventIDConstants.CATEGORY_ID_2_PREP);
        valuesForTable.put(CATEGORY_ID_2_DESC, EventIDConstants.PREPARATION_CATEGORY_ID_2_DESC);
        insertRow(TEMP_DIM_E_LTE_HFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_TAC_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable); 
        
        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_TAC_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void insertData(final int tac, final String eventID, final String categoryID, final String time, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(CATEGORY_ID_SQL_PARAM, categoryID);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResult(final String categoryId) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_TAC_GROUP);
        requestParameters.putSingle(CATEGORY_ID_SQL_PARAM, categoryId);
        return runQuery(terminalEventSummaryService, requestParameters);
    }

    private void insertRawData() throws Exception {
    	insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_PREP_X2_IN_HFA , CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_PREP_X2_IN_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);
        
        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_PREP_S1_IN_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2,  INTERNAL_PROC_HO_PREP_S1_IN_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);
        
        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, CATEGORY_ID_2_PREP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        
        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        
        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        
        insertData(TEST_VALUE_TAC_1, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC_2, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, CATEGORY_ID_2_EXEC, DATETIME_ID_RAW, 2);
    }

    @Test
    public void testTerminalGrpEventSummaryPrep() throws Exception {
         final String json = getJsonResult(EventIDConstants.CATEGORY_ID_2_PREP);
         final ResultTranslator<LTEHFATerminalGroupEventSummaryResultRaw> rt = getTranslator();
         final List<LTEHFATerminalGroupEventSummaryResultRaw> eventResult = rt.translateResult(json,
        		 LTEHFATerminalGroupEventSummaryResultRaw.class);
         assertResult(eventResult);
    }
          
    @Test
    public void testTerminalGrpEventSummaryExec() throws Exception {
         final String json = getJsonResult(EventIDConstants.CATEGORY_ID_2_EXEC);
         final ResultTranslator<LTEHFATerminalGroupEventSummaryResultRaw> rt = getTranslator();
         final List<LTEHFATerminalGroupEventSummaryResultRaw> eventResult = rt.translateResult(json,
        		 LTEHFATerminalGroupEventSummaryResultRaw.class);
         assertResult(eventResult);
    }

    private void assertResult(final List<LTEHFATerminalGroupEventSummaryResultRaw> results) {
        assertThat(results.size(), is(4));
        for (final LTEHFATerminalGroupEventSummaryResultRaw rs : results) {
        assertEquals(rs.getFailures(), 8);
        assertEquals(rs.getImpactedSubscribers(), 1);
        }
    }
}
