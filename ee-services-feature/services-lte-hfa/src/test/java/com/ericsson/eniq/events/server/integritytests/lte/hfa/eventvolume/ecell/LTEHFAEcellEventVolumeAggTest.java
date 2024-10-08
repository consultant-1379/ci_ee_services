/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.eventvolume.ecell;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventvolume.LTEHFAEcellEventVolumeService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHFAEventVolumeResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2012
 *
 */
public class LTEHFAEcellEventVolumeAggTest extends BaseDataIntegrityTest<LTEHFAEventVolumeResult> {
    private LTEHFAEcellEventVolumeService lteHFAEcellEventVolumeService;

    private static final String DATETIME_ID_DAY_1 = "2011-09-19 00:00:00";

    private static final String DATETIME_ID_DAY_2 = "2011-09-18 00:00:00";

    private static final String DATE_FROM_DAY = "13092011";

    private static final String DATE_TO_DAY = "20092011";

    private static final String DATETIME_ID_15MIN_1 = "2011-09-20 08:15:00";

    private static final String DATETIME_ID_15MIN_2 = "2011-09-20 12:15:00";

    private static final String DATE_FROM_15MIN = "20092011";

    private static final String DATE_TO_15MIN = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "1530";

    private static final String TEST_NODE_PARAM_VALUE = "LTE01ERBS00004-2,,ONRM_RootMo_R:LTE01ERBS00004,Ericsson,LTE";

    private static final String TEST_VALUE_IMSI = "11111119";

    private static final long TEST_VALUE_HIER321_ID = 7964427362722299527L;

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
    	lteHFAEcellEventVolumeService = new LTEHFAEcellEventVolumeService();
        attachDependencies(lteHFAEcellEventVolumeService);
        createTables();
    }

    /**
     * Create the prepare test tables for testing.
     * 
     * raw table: EVENT_E_LTE_HFA_RAW
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(NO_OF_ERRORS);
        columnsForEETable.add(HIER321_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_HIER321_EVENTID_ERR_15MIN, columnsForEETable);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_HIER321_EVENTID_ERR_DAY, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        columnsForEETable.add(IMSI);
        columnsForEETable.add(HIER321_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForEETable);
    }

    private void insertData(final String tableName, final String eventTime) throws SQLException {

        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_PREP_X2_IN_HFA, 3);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_PREP_X2_OUT_HFA, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_PREP_S1_IN_HFA, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_PREP_S1_OUT_HFA, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_EXEC_X2_IN_HFA, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_EXEC_S1_IN_HFA, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA, 2);

    }

    private void insertEvent(final String tableName, final String dt, final String eventID, final int instances)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(DATETIME_ID, dt);
        valuesForTable.put(NO_OF_ERRORS, instances);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID);
        insertRow(tableName, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(DATETIME_ID, dt);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(IMSI, TEST_VALUE_IMSI);
        valuesForTable.put(TAC, TEST_VALUE_TAC);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID);
        insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
    }

    private String getJsonResult(final String dateFrom, final String dateTo) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_NODE_PARAM_VALUE);

        return runQuery(lteHFAEcellEventVolumeService, requestParameters);
    }

    private void assertResult(final List<LTEHFAEventVolumeResult> results) {
        assertThat("There should be exactly 2 results!", results.size(), is(2));
        for (final LTEHFAEventVolumeResult result : results) {
        	assertThat(result.getINTERNAL_PROC_HO_PREP_X2_IN_FailCount(), is(3));
            assertThat(result.getINTERNAL_PROC_HO_PREP_X2_OUT_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_HO_EXEC_X2_IN_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_HO_EXEC_X2_OUT_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_HO_PREP_S1_IN_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_HO_PREP_S1_OUT_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_HO_EXEC_S1_IN_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_HO_EXEC_S1_OUT_FailCount(), is(2));
            assertThat(result.getImpactedSubscribers(), is(1));
        }

    }

    @Test
    public void testEcellEventVolume15MIN() throws Exception {
        insertData(TEMP_EVENT_E_LTE_HFA_HIER321_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertData(TEMP_EVENT_E_LTE_HFA_HIER321_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResult(DATE_FROM_15MIN, DATE_TO_15MIN);

        final ResultTranslator<LTEHFAEventVolumeResult> rt = getTranslator();
        final List<LTEHFAEventVolumeResult> results = rt.translateResult(json, LTEHFAEventVolumeResult.class);
        assertResult(results);
    }

    @Test
    public void testEcellEventVolumeDay() throws Exception {
        insertData(TEMP_EVENT_E_LTE_HFA_HIER321_EVENTID_ERR_DAY, DATETIME_ID_DAY_1);
        insertData(TEMP_EVENT_E_LTE_HFA_HIER321_EVENTID_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResult(DATE_FROM_DAY, DATE_TO_DAY);

        final ResultTranslator<LTEHFAEventVolumeResult> rt = getTranslator();
        final List<LTEHFAEventVolumeResult> results = rt.translateResult(json, LTEHFAEventVolumeResult.class);
        assertResult(results);
    }
}
