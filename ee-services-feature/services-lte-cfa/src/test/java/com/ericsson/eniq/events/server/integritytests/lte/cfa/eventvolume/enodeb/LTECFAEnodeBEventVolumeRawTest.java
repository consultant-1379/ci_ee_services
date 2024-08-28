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
public class LTECFAEnodeBEventVolumeRawTest extends BaseDataIntegrityTest<LTECFANodeEventVolumeResult> {
    private LTECFAEnodeBEventVolumeService lteCFAEnodeBEventVolumeService;

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final String EVENT_TIME_RAW_1 = "2011-09-20 08:12:00.121";

    private static final String EVENT_TIME_RAW_2 = "2011-09-20 08:13:00.121";

    private static final String EVENT_TIME_RAW_3 = "2011-09-20 08:14:00.121";

    private static final String TEST_NODE_PARAM_VALUE = "ONRM_RootMo_R:LTE01ERBS00001,Ericsson,LTE";

    private static final String TEST_VALUE_IMSI = "11111119";

    private static final long TEST_VALUE_HIER3_ID = 3135210477467174988L;

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
        columnsForEETable.add(TAC);
        columnsForEETable.add(IMSI);
        columnsForEETable.add(HIER3_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ERR_RAW, columnsForEETable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, TEST_VALUE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void insertData(final String eventTime) throws SQLException {

        insertEvent(TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_INITIAL_CTXT_SETUP, 3);
        insertEvent(TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_UE_CTXT_RELEASE, 2);
        insertEvent(TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_RRC_CONN_SETUP, 2);
        insertEvent(TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_ERAB_SETUP, 2);
        insertEvent(TEST_VALUE_EXCLUSIVE_TAC, eventTime, INTERNAL_PROC_S1_SIG_CONN_SETUP, 2);

        insertEvent(TEST_VALUE_TAC, eventTime, INTERNAL_PROC_INITIAL_CTXT_SETUP, 3);
        insertEvent(TEST_VALUE_TAC, eventTime, INTERNAL_PROC_UE_CTXT_RELEASE, 2);
        insertEvent(TEST_VALUE_TAC, eventTime, INTERNAL_PROC_RRC_CONN_SETUP, 2);
        insertEvent(TEST_VALUE_TAC, eventTime, INTERNAL_PROC_ERAB_SETUP, 2);
        insertEvent(TEST_VALUE_TAC, eventTime, INTERNAL_PROC_S1_SIG_CONN_SETUP, 2);
    }

    private void insertEvent(final String tac, final String dt, final String eventID, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI, TEST_VALUE_IMSI);
            valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID);
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
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_NODE_PARAM_VALUE);

        return runQuery(lteCFAEnodeBEventVolumeService, requestParameters);
    }

    private void assertResult(final List<LTECFANodeEventVolumeResult> results) {
        assertThat("There should be exactly 3 results!", results.size(), is(3));
        for (final LTECFANodeEventVolumeResult result : results) {
            assertThat(result.getINTERNAL_PROC_ERAB_SETUP_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount(), is(3));
            assertThat(result.getINTERNAL_PROC_RRC_CONN_SETUP_FailCount(), is(2));
            assertThat(result.getINTERNAL_PROC_UE_CTXT_RELEASE_FailCount(), is(2));
            assertThat(result.getImpactedSubscribers(), is(1));
        }

    }

    @Test
    public void testNetworkEventVolume() throws Exception {
        insertData(EVENT_TIME_RAW_1);
        insertData(EVENT_TIME_RAW_2);
        insertData(EVENT_TIME_RAW_3);

        final String json = getJsonResult();

        final ResultTranslator<LTECFANodeEventVolumeResult> rt = getTranslator();
        final List<LTECFANodeEventVolumeResult> results = rt.translateResult(json, LTECFANodeEventVolumeResult.class);
        assertResult(results);
    }
}
