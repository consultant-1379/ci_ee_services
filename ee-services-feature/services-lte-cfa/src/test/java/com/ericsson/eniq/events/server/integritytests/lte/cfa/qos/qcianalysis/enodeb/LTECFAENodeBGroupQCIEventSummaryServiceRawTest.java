/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.qos.qcianalysis.enodeb;

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.qos.qcianalysis.LTECFAEnodeBQCIEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.qos.LTECallFailureQOSENodeBGroupQCIEventSummaryResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFAENodeBGroupQCIEventSummaryServiceRawTest extends
        BaseDataIntegrityTest<LTECallFailureQOSENodeBGroupQCIEventSummaryResult> {
    LTECFAEnodeBQCIEventSummaryService lteCallFailureEnodeBQCIEventSummaryService;

    private static final String DATETIME_ID_RAW = "2011-09-20 08:12:00";

    private static final String DATE_FROM_RAW = "20092011";

    private static final String DATE_TO_RAW = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "0930";

    private static final long TEST_VALUE_HIER3_ID_1 = 3135210477467174988L;

    private static final long TEST_VALUE_HIER3_ID_2 = 4809532081614999117L;

    private static final int TEST_VALUE_QCI_ID = 1;

    private static final int CATEGORY_ID_CALL_SETUP = 1;

    private static final int CATEGORY_ID_CALL_DROP = 0;

    private static final String TEST_VALUE_GROUP_NAME = "eNodeBGroup";

    private static final String TEST_VALUE_IMSI = "11111119";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteCallFailureEnodeBQCIEventSummaryService = new LTECFAEnodeBQCIEventSummaryService();
        attachDependencies(lteCallFailureEnodeBQCIEventSummaryService);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_LTE_QCI, TEMP_DIM_E_LTE_QCI);
        createTable();
        insertTopoData();
        insertRawData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(CATEGORY_ID);
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(QCI_ID_COLUMN);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(TAC);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_INITIAL_CTXT_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_ERAB_SETUP);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_ERAB_SETUP");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EVENT_ID, EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE);
        valuesForTable.put(EVENT_ID_DESC, "INTERNAL_PROC_UE_CTXT_RELEASE");
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, TEST_VALUE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID_1);
        valuesForTable.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, valuesForTable);
    }

    private void insertData(final String tac, final long hire3ID, final int categoryID, final String eventID,
            final String time, final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        for (int i = 0; i < instances; i++) {
            valuesForTable.put(TAC, tac);
            valuesForTable.put(IMSI_PARAM, TEST_VALUE_IMSI);
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(CATEGORY_ID, categoryID);
            valuesForTable.put(HIER3_ID, hire3ID);
            valuesForTable.put(QCI_ID_COLUMN, TEST_VALUE_QCI_ID);
            valuesForTable.put(EVENT_ID, eventID);
            insertRow(TEMP_EVENT_E_LTE_CFA_ARRAY_ERAB_ERR_RAW, valuesForTable);
            valuesForTable.clear();
        }
    }

    private String getJsonResult(final int categoryID) throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_RAW);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_RAW);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(QCI_ID_COLUMN, String.valueOf(TEST_VALUE_QCI_ID));
        requestParameters.putSingle(CATEGORY_ID, String.valueOf(categoryID));
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        return runQuery(lteCallFailureEnodeBQCIEventSummaryService, requestParameters);
    }

    private void insertRawData() throws Exception {
        insertData(TEST_VALUE_TAC, TEST_VALUE_HIER3_ID_1, CATEGORY_ID_CALL_DROP,
                EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_TAC, TEST_VALUE_HIER3_ID_2, CATEGORY_ID_CALL_DROP,
                EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_EXCLUSIVE_TAC, TEST_VALUE_HIER3_ID_1, CATEGORY_ID_CALL_DROP,
                EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE, DATETIME_ID_RAW, 1);
        insertData(TEST_VALUE_EXCLUSIVE_TAC, TEST_VALUE_HIER3_ID_2, CATEGORY_ID_CALL_DROP,
                EventIDConstants.INTERNAL_PROC_UE_CTXT_RELEASE, DATETIME_ID_RAW, 1);

        insertData(TEST_VALUE_TAC, TEST_VALUE_HIER3_ID_1, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_ERAB_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC, TEST_VALUE_HIER3_ID_2, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_ERAB_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_EXCLUSIVE_TAC, TEST_VALUE_HIER3_ID_1, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_ERAB_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_EXCLUSIVE_TAC, TEST_VALUE_HIER3_ID_2, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_ERAB_SETUP, DATETIME_ID_RAW, 2);

        insertData(TEST_VALUE_TAC, TEST_VALUE_HIER3_ID_1, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_TAC, TEST_VALUE_HIER3_ID_2, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP, DATETIME_ID_RAW, 2);
        insertData(TEST_VALUE_EXCLUSIVE_TAC, TEST_VALUE_HIER3_ID_1, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP, DATETIME_ID_RAW, 3);
        insertData(TEST_VALUE_EXCLUSIVE_TAC, TEST_VALUE_HIER3_ID_2, CATEGORY_ID_CALL_SETUP,
                EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP, DATETIME_ID_RAW, 3);
    }

    @Test
    public void testENodeBGroupQCIEventSummaryCallDrop() throws Exception {
        final String json = getJsonResult(CATEGORY_ID_CALL_DROP);
        final ResultTranslator<LTECallFailureQOSENodeBGroupQCIEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSENodeBGroupQCIEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSENodeBGroupQCIEventSummaryResult.class);
        assertThat(eventResult.size(), is(1));
    }

    @Test
    public void testENodeBGroupQCIEventSummaryCallSetup() throws Exception {
        final String json = getJsonResult(CATEGORY_ID_CALL_SETUP);
        final ResultTranslator<LTECallFailureQOSENodeBGroupQCIEventSummaryResult> rt = getTranslator();
        final List<LTECallFailureQOSENodeBGroupQCIEventSummaryResult> eventResult = rt.translateResult(json,
                LTECallFailureQOSENodeBGroupQCIEventSummaryResult.class);
        assertThat(eventResult.size(), is(2));
    }
}
