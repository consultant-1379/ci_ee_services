package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.causecode;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureCauseCodeListService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureCellGroupCCListResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class HandoverFailureCCListServiceRawTest extends BaseDataIntegrityTest<HandoverFailureCellGroupCCListResult> {
    private WCDMAHandoverFailureCauseCodeListService wcdmaHandoverFailureCauseCodeListService;

    private static final String TEST_VALUE_HIER3_CELL_ID2 = "9876543210";

    private static final String TEST_VALUE_HIER3_ID = "9876543210";

    private static final String TEST_VALUE_CAUSE_VALUE_1 = "1";

    private static final String TEST_VALUE_CAUSE_VALUE_2 = "2";

    private static final String TEST_VALUE_CAUSE_VALUE_3 = "3";

    private static final String TEST_VALUE_CAUSE_VALUE_4 = "4";

    private static final String TEST_VALUE_CAUSE_VALUE_DESC = "Test Cause Code";

    private static final int TEST_EVENT_ID_AS_INTEGER = 423;

    private static final String TEST_VALUE_GROUP_NAME = "Access_WCDMA";

    private static final String TEST_VALUE_CATEGORY_TABLE_NAME = "TESTCAT";

    private static final String TEST_VALUE_EVENT_ID_LABEL = "_TEST_EVENT";

    private static final String TEST_VALUE_CAUSE_VALUE_LABEL = "CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
            + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL;

    public static final String TEST_VALUE_CELL122_NODE = "RNC01-2-2,,ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G";

    public static final String TEST_VALUE_BSC122_NODE = "BSC1-2-2,,BSC1,Ericsson,GSM";

    public static final String TEST_VALUE_RNC1_NODE = "ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G";

    @Before
    public void onSetUp() throws Exception {
        wcdmaHandoverFailureCauseCodeListService = new WCDMAHandoverFailureCauseCodeListService();
        attachDependencies(wcdmaHandoverFailureCauseCodeListService);
        createTables();
        insertData();
    }

    // RNC Test for Grid
    @Test
    public void testRNCServiceGrid() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_RNC1_NODE);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultRNC(json);
    }

    // RNC Test for Chart
    @Test
    public void testRNCServiceChart() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_RNC1_NODE);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultRNC(json);
    }

    // RNC Group Test for Grid
    @Test
    public void testRNCGroupServiceGrid() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultRNC(json);
    }

    // RNC Group Test for Chart
    @Test
    public void testRNCGroupServiceChart() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultRNC(json);
    }

    // Cell Test for Grid
    @Test
    public void testCellServiceGrid() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_ACCESS_AREA_NODE);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultCell(json);
    }

    // Cell Test for Chart
    @Test
    public void testCellServiceChart() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_ACCESS_AREA_NODE);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultCell(json);
    }

    // Cell Group Test for Grid
    @Test
    public void testCellGroupServiceGrid() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultCellGroup(json);
    }

    // Cell Group Test for Chart
    @Test
    public void testCellGroupServiceChart() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeListService, requestParameters);
        verifyResultCellGroup(json);
    }

    private void verifyResultRNC(final String json) throws Exception {
        final List<HandoverFailureCellGroupCCListResult> result = getTranslator().translateResult(json,
                HandoverFailureCellGroupCCListResult.class);
        assertThat(result.size(), is(4));

        final HandoverFailureCellGroupCCListResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getCauseValueLabel(), is(TEST_VALUE_CAUSE_VALUE_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getGroupId(), is(WCDMA_HFA_SOURCE_GROUP_ID));
    }

    private void verifyResultCell(final String json) throws Exception {
        final List<HandoverFailureCellGroupCCListResult> result = getTranslator().translateResult(json,
                HandoverFailureCellGroupCCListResult.class);
        assertThat(result.size(), is(6));

        HandoverFailureCellGroupCCListResult resultForHFACellGroup = result.get(1);

        assertThat(resultForHFACellGroup.getCauseValueLabel(), is(TEST_VALUE_CAUSE_VALUE_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getGroupId(), is(WCDMA_HFA_SOURCE_GROUP_ID));

        resultForHFACellGroup = result.get(0);
        assertThat(resultForHFACellGroup.getCauseValueLabel(), is(TEST_VALUE_CAUSE_VALUE_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getGroupId(), is(WCDMA_HFA_TARGET_GROUP_ID));
    }
    
    private void verifyResultCellGroup(final String json) throws Exception {
        final List<HandoverFailureCellGroupCCListResult> result = getTranslator().translateResult(json,
                HandoverFailureCellGroupCCListResult.class);
        assertThat(result.size(), is(6));

        HandoverFailureCellGroupCCListResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getCauseValueLabel(), is(TEST_VALUE_CAUSE_VALUE_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getGroupId(), is(WCDMA_HFA_SOURCE_GROUP_ID));

        resultForHFACellGroup = result.get(1);
        assertThat(resultForHFACellGroup.getCauseValueLabel(), is(TEST_VALUE_CAUSE_VALUE_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getGroupId(), is(WCDMA_HFA_TARGET_GROUP_ID));
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForTables = new ArrayList<String>();

        // Raw event tables
        columnsForTables.add(DATETIME_ID);
        columnsForTables.add(HIER3_CELL_ID);
        columnsForTables.add(THIER3_CELL_ID);
        columnsForTables.add(THIER321_HASHID);
        columnsForTables.add(HIER3_ID);
        columnsForTables.add(CAUSE_VALUE);
        columnsForTables.add(EVENT_ID);
        columnsForTables.add(CATEGORY_ID);
        columnsForTables.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, columnsForTables);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, columnsForTables);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columnsForTables);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, columnsForTables);

        // Dim cause codes
        columnsForTables.clear();
        columnsForTables.add(CAUSE_VALUE);
        columnsForTables.add(EVENT_ID);
        columnsForTables.add(CAUSE_VALUE_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, columnsForTables);

        // Dim categories
        columnsForTables.clear();
        columnsForTables.add(CATEGORY_ID);
        columnsForTables.add(CATEGORY_TABLE_NAME);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsForTables);

        // Dim event types
        columnsForTables.clear();
        columnsForTables.add(EVENT_ID);
        columnsForTables.add(EVENT_ID_LABEL);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_EVENTTYPE, columnsForTables);

        // Dim controller groups
        columnsForTables.clear();
        columnsForTables.add(HIER3_ID);
        columnsForTables.add(RAT_COLUMN_NAME);
        columnsForTables.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForTables);

        // Dim cell groups
        columnsForTables.clear();
        columnsForTables.add(HIER3_CELL_ID);
        columnsForTables.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321_CELL, columnsForTables);

        columnsForTables.clear();
        columnsForTables.add(CELL_SQL_ID);
        columnsForTables.add(RAT_COLUMN_NAME);
        columnsForTables.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, columnsForTables);
    }

    private void insertData() throws Exception {
        // Event data
        final Map<String, Object> dataForTables = new HashMap<String, Object>();

        final String dateTimeNowMinus27Mins = DateTimeUtilities
                .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_RNC1_NODE));
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_2);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_3);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_3);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_BSC122_NODE));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_BSC122_NODE));
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_CELL122_NODE));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_BSC122_NODE));
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        // Non group cell. Should not be returned.
        dataForTables.put(HIER3_CELL_ID, TEST_VALUE_HIER3_CELL_ID2);
        dataForTables.put(THIER321_HASHID, TEST_VALUE_HIER3_CELL_ID2);
        dataForTables.put(HIER3_ID, TEST_VALUE_HIER3_ID);
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_4);
        dataForTables.put(EVENT_ID, IRAT_HANDOVER_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        // Dim cause codes
        dataForTables.clear();
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CAUSE_VALUE_DESC, TEST_VALUE_CAUSE_VALUE_DESC);
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, dataForTables);
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_2);
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, dataForTables);
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_3);
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, dataForTables);
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_4);
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, dataForTables);

        // Dim categories
        dataForTables.clear();
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(CATEGORY_TABLE_NAME, TEST_VALUE_CATEGORY_TABLE_NAME);
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, dataForTables);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, dataForTables);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, dataForTables);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, dataForTables);

        // Dim event types
        dataForTables.clear();
        dataForTables.put(EVENT_ID_LABEL, TEST_VALUE_EVENT_ID_LABEL);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        insertRow(TEMP_DIM_E_RAN_HFA_EVENTTYPE, dataForTables);

        // Dim rnc groups
        dataForTables.clear();
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_RNC1_NODE));
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        dataForTables.put(RAT_COLUMN_NAME, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, dataForTables);

        // Dim cell groups
        dataForTables.clear();
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_ACCESS_AREA_NODE));
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321_CELL, dataForTables);

        // Dim cell groups
        dataForTables.clear();
        dataForTables.put(CELL_SQL_ID, createHashIDForCell2G(TEST_VALUE_BSC122_NODE));
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        dataForTables.put(RAT_COLUMN_NAME, RAT_INTEGER_VALUE_FOR_2G);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, dataForTables);
    }

    private long createHashIDForController(final String node) {
        final String[] allData1 = node.split(DELIMITER);
        return queryUtils.createHashIDForController(allData1[2], allData1[0], allData1[1]);
    }

    private long createHashIDForCell3G(final String node) {
        final String[] allData1 = node.split(DELIMITER);
        return queryUtils.createHashIDFor3GCell(allData1[4], allData1[2], allData1[0], allData1[3]);
    }

    private long createHashIDForCell2G(final String node) {
        final String[] allData1 = node.split(DELIMITER);
        return queryUtils.createHashIDForCell(allData1[4], allData1[2], "", allData1[0], allData1[3]);
    }
}
