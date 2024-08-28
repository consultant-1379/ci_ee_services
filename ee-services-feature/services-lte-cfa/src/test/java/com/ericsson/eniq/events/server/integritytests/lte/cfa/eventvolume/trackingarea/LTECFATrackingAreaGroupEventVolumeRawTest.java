/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.eventvolume.trackingarea;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.eventvolume.LTECFATrackingAreaEventVolumeService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.eventvolume.LTECFANodeEventVolumeResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2012
 *
 */
public class LTECFATrackingAreaGroupEventVolumeRawTest extends BaseDataIntegrityTest<LTECFANodeEventVolumeResult> {
    private LTECFATrackingAreaEventVolumeService lteCFATrackingAreaEventVolumeService;

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String EVENT_TIME_RAW_1 = "2011-09-20 08:12:00.121";

    private static final String EVENT_TIME_RAW_2 = "2011-09-20 08:13:00.121";

    private static final String EVENT_TIME_RAW_3 = "2011-09-20 08:14:00.121";

    private static final String TEST_VALUE_IMSI = "11111119";

    private static final int TEST_VALUE_TRAC_ID_1 = 1;

    private static final int TEST_VALUE_TRAC_ID_2 = 2;

    private static final String TEST_VALUE_GROUP_NAME = "tracGroup";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCFATrackingAreaEventVolumeService = new LTECFATrackingAreaEventVolumeService();
        attachDependencies(lteCFATrackingAreaEventVolumeService);
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
        columnsForEETable.add(TAC);
        columnsForEETable.add(IMSI);
        columnsForEETable.add(TRAC);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(TRAC);
        columnsForEETable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_LTE_TRAC, columnsForEETable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, TEST_VALUE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TRAC, TEST_VALUE_TRAC_ID_2);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_LTE_TRAC, valuesForTable);
    }

    private void insertData(final int trac, final String eventTime) throws SQLException {

        insertEvent(trac, TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_INITIAL_CTXT_SETUP, 3);
        insertEvent(trac, TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_UE_CTXT_RELEASE, 2);
        insertEvent(trac, TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_RRC_CONN_SETUP, 2);
        insertEvent(trac, TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_ERAB_SETUP, 2);
        insertEvent(trac, TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_S1_SIG_CONN_SETUP, 2);

        insertEvent(trac, TEST_VALUE_TAC, eventTime, INTERNAL_PROC_INITIAL_CTXT_SETUP, 3);
        insertEvent(trac, TEST_VALUE_TAC, eventTime, INTERNAL_PROC_UE_CTXT_RELEASE, 2);
        insertEvent(trac, TEST_VALUE_TAC, eventTime, INTERNAL_PROC_RRC_CONN_SETUP, 2);
        insertEvent(trac, TEST_VALUE_TAC, eventTime, INTERNAL_PROC_ERAB_SETUP, 2);
        insertEvent(trac, TEST_VALUE_TAC, eventTime, INTERNAL_PROC_S1_SIG_CONN_SETUP, 2);
    }

    private void insertEvent(final int trac, final String tac, final String dt, final String eventID,
            final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI, TEST_VALUE_IMSI);
            valuesForTable.put(TRAC, trac);
            insertRow(TEMP_EVENT_E_LTE_CFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResult() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_TRAC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);

        return runQuery(lteCFATrackingAreaEventVolumeService, requestParameters);
    }

    private void assertResult(final List<LTECFANodeEventVolumeResult> results) {
        assertThat("There should be exactly 3 results!", results.size(), is(3));
        for (final LTECFANodeEventVolumeResult result : results) {
            assertThat(result.getINTERNAL_PROC_ERAB_SETUP_FailCount(), is(8));
            assertThat(result.getINTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount(), is(12));
            assertThat(result.getINTERNAL_PROC_RRC_CONN_SETUP_FailCount(), is(8));
            assertThat(result.getINTERNAL_PROC_UE_CTXT_RELEASE_FailCount(), is(8));
            assertThat(result.getImpactedSubscribers(), is(1));
        }

    }

    @Test
    public void testNetworkEventVolume() throws Exception {
        insertData(TEST_VALUE_TRAC_ID_1, EVENT_TIME_RAW_1);
        insertData(TEST_VALUE_TRAC_ID_1, EVENT_TIME_RAW_2);
        insertData(TEST_VALUE_TRAC_ID_1, EVENT_TIME_RAW_3);

        insertData(TEST_VALUE_TRAC_ID_2, EVENT_TIME_RAW_1);
        insertData(TEST_VALUE_TRAC_ID_2, EVENT_TIME_RAW_2);
        insertData(TEST_VALUE_TRAC_ID_2, EVENT_TIME_RAW_3);

        final String json = getJsonResult();

        final ResultTranslator<LTECFANodeEventVolumeResult> rt = getTranslator();
        final List<LTECFANodeEventVolumeResult> results = rt.translateResult(json, LTECFANodeEventVolumeResult.class);
        assertResult(results);
    }
}
