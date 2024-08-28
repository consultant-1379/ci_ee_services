/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventvolume.ecell;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventvolume.LTECFAEcellEventVolumeService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.eventvolume.LTECFANodeEventVolumeResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2012
 *
 */
public class LTECFAEcellEventVolumeAggTest extends BaseDataIntegrityTest<LTECFANodeEventVolumeResult> {
    private LTECFAEcellEventVolumeService lteCFAEcellEventVolumeService;

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
        lteCFAEcellEventVolumeService = new LTECFAEcellEventVolumeService();
        attachDependencies(lteCFAEcellEventVolumeService);
        createTables();
    }

    /**
     * Create the prepare test tables for testing.
     * 
     * raw table: EVENT_E_LTE_CFA_RAW
     * @throws Exception
     */
    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(NO_OF_ERRORS);
        columnsForEETable.add(HIER321_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_15MIN, columnsForEETable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_DAY, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        columnsForEETable.add(IMSI);
        columnsForEETable.add(HIER321_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);
    }

    private void insertData(final String tableName, final String eventTime) throws SQLException {

        insertEvent(tableName, eventTime, INTERNAL_PROC_INITIAL_CTXT_SETUP, 3);
        insertEvent(tableName, eventTime, INTERNAL_PROC_UE_CTXT_RELEASE, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_RRC_CONN_SETUP, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_ERAB_SETUP, 2);
        insertEvent(tableName, eventTime, INTERNAL_PROC_S1_SIG_CONN_SETUP, 2);

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
        insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
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

        return runQuery(lteCFAEcellEventVolumeService, requestParameters);
    }

    private void assertResult(final List<LTECFANodeEventVolumeResult> results) {
        assertThat("There should be exactly 3 results!", results.size(), is(2));
        for (final LTECFANodeEventVolumeResult result : results) {
            assertThat(result.getINTERNAL_PROC_ERAB_SETUP_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount(), is(3));
            assertThat(result.getINTERNAL_PROC_RRC_CONN_SETUP_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_UE_CTXT_RELEASE_FailCount(), is(2));
            assertThat(result.getImpactedSubscribers(), is(1));
        }

    }

    @Test
    public void testNetworkEventVolume15MIN() throws Exception {
        insertData(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertData(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResult(DATE_FROM_15MIN, DATE_TO_15MIN);

        final ResultTranslator<LTECFANodeEventVolumeResult> rt = getTranslator();
        final List<LTECFANodeEventVolumeResult> results = rt.translateResult(json, LTECFANodeEventVolumeResult.class);
        assertResult(results);
    }

    @Test
    public void testNetworkEventVolumeDay() throws Exception {
        insertData(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_DAY, DATETIME_ID_DAY_1);
        insertData(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResult(DATE_FROM_DAY, DATE_TO_DAY);

        final ResultTranslator<LTECFANodeEventVolumeResult> rt = getTranslator();
        final List<LTECFANodeEventVolumeResult> results = rt.translateResult(json, LTECFANodeEventVolumeResult.class);
        assertResult(results);
    }
}
