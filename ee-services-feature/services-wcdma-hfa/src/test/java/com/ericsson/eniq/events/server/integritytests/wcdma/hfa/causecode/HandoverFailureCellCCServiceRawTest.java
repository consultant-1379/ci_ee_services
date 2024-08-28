package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.causecode;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureCauseCodeService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureCCResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class HandoverFailureCellCCServiceRawTest extends BaseDataIntegrityTest<HandoverFailureCCResult> {

    private WCDMAHandoverFailureCauseCodeService wcdmaHandoverFailureCauseCodeService;

    private static final String TEST_VALUE_CAUSE_VALUE_1 = "1";

    private static final String TEST_VALUE_CAUSE_VALUE_2 = "2";

    private static final String TEST_VALUE_CAUSE_VALUE_3 = "3";

    private static final String TEST_VALUE_CAUSE_VALUE_4 = "4";

    private static final String TEST_VALUE_CAUSE_VALUE_DESC = "Test Cause Code";

    private static final int TEST_EVENT_ID_AS_INTEGER = 423;

    private static final String TEST_VALUE_GROUP_NAME_E = "Access_WCDMA_Managed";

    private static final String TEST_VALUE_GROUP_NAME_Z = "Access_WCDMA_External";

    private static final String TEST_VALUE_CATEGORY_TABLE_NAME = "TESTCAT";

    private static final String TEST_VALUE_CATEGORY_DESC = "TESTDESC";

    private static final String TEST_VALUE_EVENT_ID_LABEL = "_TEST_EVENT";

    private static final String TEST_VALUE_CAUSE_VALUE_LABEL = "'" + "CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
            + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL + "'";

    private static final String DUMMY_CELL_NODE_3G = "DummyRNC01-2-2,,DummyONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G";

    private static final String DUMMY_CELL_NODE_2G = "111,,DummyBSC,Ericsson,GSM";

    private static final String TEST_VALUE_E_CELL122_NODE_3G = "RNC01-2-2,,ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G";

    private static final String TEST_VALUE_Z_CELL122_NODE_3G = "RNC01-2-2,,ONRM_ROOT_MO_R:RNC01:RNC01,Non-Ericsson,3G";

    private static final String TEST_VALUE_E_CELL44_NODE_2G = "44,,BSC1,Ericsson,GSM";

    private static final String TEST_VALUE_Z_CELL44_NODE_2G = "44,,BSC1,Non-Ericsson,GSM";

    @Before
    public void onSetUp() throws Exception {
        wcdmaHandoverFailureCauseCodeService = new WCDMAHandoverFailureCauseCodeService();
        attachDependencies(wcdmaHandoverFailureCauseCodeService);
        createTables();
        insertData();
    }

    // Cell Test for Chart - Managed Cells
    @Test
    public void testCellServiceChartE() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_E_CELL122_NODE_3G);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_SOURCE, TEST_VALUE_CAUSE_VALUE_LABEL);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_TARGET, TEST_VALUE_CAUSE_VALUE_LABEL);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(""));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Test for Grid - Managed Cells
    @Test
    public void testCellServiceGridE() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_E_CELL122_NODE_3G);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(4));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_3 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_3)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(""));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Group Test for Chart - Managed Cells
    @Test
    public void testCellGroupServiceChartE() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME_E);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_SOURCE, TEST_VALUE_CAUSE_VALUE_LABEL);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_TARGET, TEST_VALUE_CAUSE_VALUE_LABEL);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(TEST_VALUE_GROUP_NAME_E));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Group Test for Grid - Managed Cells
    @Test
    public void testCellGroupServiceGridE() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME_E);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(4));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_3 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_3)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(TEST_VALUE_GROUP_NAME_E));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Test for Chart - External Cells
    @Test
    public void testCellServiceChartZ() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_Z_CELL122_NODE_3G);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_SOURCE, TEST_VALUE_CAUSE_VALUE_LABEL);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_TARGET, TEST_VALUE_CAUSE_VALUE_LABEL);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(""));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Test for Grid - External Cells
    @Test
    public void testCellServiceGridZ() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_Z_CELL122_NODE_3G);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(4));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_3 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_3)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(""));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Group Test for Chart - External Cells
    @Test
    public void testCellGroupServiceChartZ() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME_Z);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_SOURCE, TEST_VALUE_CAUSE_VALUE_LABEL);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_TARGET, TEST_VALUE_CAUSE_VALUE_LABEL);

        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(TEST_VALUE_GROUP_NAME_Z));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    // Cell Group Test for Grid - External Cells
    @Test
    public void testCellGroupServiceGridZ() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME_Z);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(4));

        final HandoverFailureCCResult resultForHFACellGroup = result.get(0);

        assertThat(resultForHFACellGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_3 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFACellGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFACellGroup.getFailures(), is(2));
        assertThat(resultForHFACellGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFACellGroup.getImpactedSubscribersTarget(), is("1"));
        assertThat(resultForHFACellGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_3)));
        assertThat(resultForHFACellGroup.getEventID(), is(new Integer(TEST_EVENT_ID_AS_INTEGER)));
        assertThat(resultForHFACellGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFACellGroup.getGroupName(), is(TEST_VALUE_GROUP_NAME_Z));
        assertThat(resultForHFACellGroup.getState(), is(WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID));
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForTables = new ArrayList<String>();

        // Raw event tables
        columnsForTables.add(DATETIME_ID);
        columnsForTables.add(HIER3_CELL_ID);
        columnsForTables.add(THIER321_HASHID);
        columnsForTables.add(THIER3_CELL_ID);
        columnsForTables.add(CAUSE_VALUE);
        columnsForTables.add(EVENT_ID);
        columnsForTables.add(CATEGORY_ID);
        columnsForTables.add(TAC);
        columnsForTables.add(IMSI);
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
        columnsForTables.add(CATEGORY_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsForTables);

        // Dim event types
        columnsForTables.clear();
        columnsForTables.add(EVENT_ID);
        columnsForTables.add(EVENT_ID_LABEL);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_EVENTTYPE, columnsForTables);

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
        //// Event data
        final Map<String, Object> dataForTables = new HashMap<String, Object>();

        final String dateTimeNowMinus27Mins = DateTimeUtilities
                .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_2);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL44_NODE_2G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_E_CELL44_NODE_2G));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_3);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);

        dataForTables.clear();
        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_4);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        //////////////////////////////////////////////
        dataForTables.clear();
        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_2);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_3);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);

        dataForTables.clear();
        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_E_CELL44_NODE_2G));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_4);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(TEST_VALUE_Z_CELL44_NODE_2G));
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        // Non searched Cells. Should not be returned.
        dataForTables.clear();
        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER3_CELL_ID, createHashIDForCell3G(DUMMY_CELL_NODE_3G));
        dataForTables.put(THIER321_HASHID, createHashIDForCell2G(DUMMY_CELL_NODE_2G));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_2);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_3);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);

        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_4);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        //// Dim cause codes
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
        dataForTables.put(CATEGORY_DESC, TEST_VALUE_CATEGORY_DESC);
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

        // Dim 3G cell groups
        dataForTables.clear();
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_E_CELL122_NODE_3G));
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME_E);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321_CELL, dataForTables);
        dataForTables.put(HIER3_CELL_ID, createHashIDForCell3G(TEST_VALUE_Z_CELL122_NODE_3G));
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME_Z);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321_CELL, dataForTables);

        // Dim 2G cell groups
        dataForTables.clear();
        dataForTables.put(CELL_SQL_ID, createHashIDForCell2G(TEST_VALUE_E_CELL44_NODE_2G));
        dataForTables.put(RAT_COLUMN_NAME, RAT_INTEGER_VALUE_FOR_2G);
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME_E);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, dataForTables);
        dataForTables.clear();
        dataForTables.put(CELL_SQL_ID, createHashIDForCell2G(TEST_VALUE_Z_CELL44_NODE_2G));
        dataForTables.put(RAT_COLUMN_NAME, RAT_INTEGER_VALUE_FOR_2G);
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME_Z);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, dataForTables);
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