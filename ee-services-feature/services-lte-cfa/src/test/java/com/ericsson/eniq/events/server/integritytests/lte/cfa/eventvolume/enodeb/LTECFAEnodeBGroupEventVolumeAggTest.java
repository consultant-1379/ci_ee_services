/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventvolume.enodeb;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventvolume.LTECFAEnodeBEventVolumeService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.eventvolume.LTECFANodeEventVolumeResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2012
 *
 */
public class LTECFAEnodeBGroupEventVolumeAggTest extends BaseDataIntegrityTest<LTECFANodeEventVolumeResult> {
    private LTECFAEnodeBEventVolumeService lteCFAEnodeBEventVolumeService;

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

    private static final String TEST_VALUE_IMSI = "11111119";

    private static final long TEST_VALUE_HIER3_ID_1 = 3135210477467174988L;

    private static final long TEST_VALUE_HIER3_ID_2 = 4809532081614999117L;

    private static final String TEST_VALUE_GROUP_NAME = "eNodeBGroup";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCFAEnodeBEventVolumeService = new LTECFAEnodeBEventVolumeService();
        attachDependencies(lteCFAEnodeBEventVolumeService);
        createTables();
        insertTopoData();
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
        columnsForEETable.add(HIER3_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, columnsForEETable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        columnsForEETable.add(IMSI);
        columnsForEETable.add(HIER3_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForEETable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);
    }

    private void insertData(final long hier3ID, final String tableName, final String eventTime) throws SQLException {

        insertEvent(hier3ID, tableName, eventTime, INTERNAL_PROC_INITIAL_CTXT_SETUP, 3);
        insertEvent(hier3ID, tableName, eventTime, INTERNAL_PROC_UE_CTXT_RELEASE, 2);
        insertEvent(hier3ID, tableName, eventTime, INTERNAL_PROC_RRC_CONN_SETUP, 2);
        insertEvent(hier3ID, tableName, eventTime, INTERNAL_PROC_ERAB_SETUP, 2);
        insertEvent(hier3ID, tableName, eventTime, INTERNAL_PROC_S1_SIG_CONN_SETUP, 2);

    }

    private void insertEvent(final long hier3ID, final String tableName, final String dt, final String eventID,
            final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(DATETIME_ID, dt);
        valuesForTable.put(NO_OF_ERRORS, instances);
        valuesForTable.put(HIER3_ID, hier3ID);
        insertRow(tableName, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(DATETIME_ID, dt);
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(IMSI, TEST_VALUE_IMSI);
        valuesForTable.put(TAC, TEST_VALUE_TAC);
        valuesForTable.put(HIER3_ID, hier3ID);
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
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);

        return runQuery(lteCFAEnodeBEventVolumeService, requestParameters);
    }

    private void assertResult(final List<LTECFANodeEventVolumeResult> results) {
        assertThat("There should be exactly 3 results!", results.size(), is(2));
        for (final LTECFANodeEventVolumeResult result : results) {
            assertThat(result.getINTERNAL_PROC_ERAB_SETUP_FailCount(), is(4));
            assertThat(result.getINTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount(), is(6));
            assertThat(result.getINTERNAL_PROC_RRC_CONN_SETUP_FailCount(), is(4));
            assertThat(result.getINTERNAL_PROC_UE_CTXT_RELEASE_FailCount(), is(4));
            assertThat(result.getImpactedSubscribers(), is(1));
        }

    }

    @Test
    public void testNetworkEventVolume15MIN() throws Exception {
        insertData(TEST_VALUE_HIER3_ID_1, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertData(TEST_VALUE_HIER3_ID_1, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_2);

        insertData(TEST_VALUE_HIER3_ID_2, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_1);
        insertData(TEST_VALUE_HIER3_ID_2, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, DATETIME_ID_15MIN_2);

        final String json = getJsonResult(DATE_FROM_15MIN, DATE_TO_15MIN);

        final ResultTranslator<LTECFANodeEventVolumeResult> rt = getTranslator();
        final List<LTECFANodeEventVolumeResult> results = rt.translateResult(json, LTECFANodeEventVolumeResult.class);
        assertResult(results);
    }

    @Test
    public void testNetworkEventVolumeDay() throws Exception {
        insertData(TEST_VALUE_HIER3_ID_1, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_1);
        insertData(TEST_VALUE_HIER3_ID_1, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_2);

        insertData(TEST_VALUE_HIER3_ID_2, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_1);
        insertData(TEST_VALUE_HIER3_ID_2, TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, DATETIME_ID_DAY_2);

        final String json = getJsonResult(DATE_FROM_DAY, DATE_TO_DAY);

        final ResultTranslator<LTECFANodeEventVolumeResult> rt = getTranslator();
        final List<LTECFANodeEventVolumeResult> results = rt.translateResult(json, LTECFANodeEventVolumeResult.class);
        assertResult(results);
    }
}
