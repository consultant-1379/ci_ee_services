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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureCauseCodeService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureCCResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class HandoverFailureRNCCCServiceRawTest extends BaseDataIntegrityTest<HandoverFailureCCResult> {

    private WCDMAHandoverFailureCauseCodeService wcdmaHandoverFailureCauseCodeService;

    private static final String TEST_VALUE_HIER3_ID = "9876543210";

    private static final String TEST_VALUE_CAUSE_VALUE_1 = "1";

    private static final String TEST_VALUE_CAUSE_VALUE_2 = "2";

    private static final String TEST_VALUE_CAUSE_VALUE_3 = "3";

    private static final String TEST_VALUE_CAUSE_VALUE_4 = "4";

    private static final String TEST_VALUE_CAUSE_VALUE_DESC = "Test Cause Code";

    private static final int TEST_EVENT_ID = 423;

    private static final String TEST_VALUE_GROUP_NAME = "Access_WCDMA";

    private static final String TEST_VALUE_CATEGORY_TABLE_NAME = "TESTCAT";

    private static final String TEST_VALUE_CATEGORY_DESC = "TESTDESC";

    private static final String TEST_VALUE_EVENT_ID_LABEL = "_TEST_EVENT";

    private static final String TEST_VALUE_CAUSE_VALUE_LABEL = "'" + "CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
            + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL + "'";

    private static final String TEST_VALUE_BSC42_NODE = "BSC42,Ericsson,GSM";

    @Before
    public void onSetUp() throws Exception {
        wcdmaHandoverFailureCauseCodeService = new WCDMAHandoverFailureCauseCodeService();
        attachDependencies(wcdmaHandoverFailureCauseCodeService);
        createTables();
        insertData();
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
        requestParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_SOURCE, TEST_VALUE_CAUSE_VALUE_LABEL);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureCCResult resultForHFArnc = result.get(0);

        assertThat(resultForHFArnc.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFArnc.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFArnc.getFailures(), is(1));
        assertThat(resultForHFArnc.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFArnc.getImpactedSubscribersTarget(), is(""));
        assertThat(resultForHFArnc.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFArnc.getEventID(), is(new Integer(TEST_EVENT_ID)));
        assertThat(resultForHFArnc.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertNull(resultForHFArnc.getGroupName(), null);
        assertThat(resultForHFArnc.getState(), is(WCDMA_HFA_SOURCE_GROUP_ID));
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
        requestParameters.putSingle(NODE_PARAM, TEST_BSC1_NODE);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(4));

        final HandoverFailureCCResult resultForHFArnc = result.get(0);

        assertThat(resultForHFArnc.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_3 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFArnc.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFArnc.getFailures(), is(1));
        assertThat(resultForHFArnc.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFArnc.getImpactedSubscribersTarget(), is(""));
        assertThat(resultForHFArnc.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_3)));
        assertThat(resultForHFArnc.getEventID(), is(new Integer(TEST_EVENT_ID)));
        assertThat(resultForHFArnc.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertNull(resultForHFArnc.getGroupName(), null);
        assertThat(resultForHFArnc.getState(), is(WCDMA_HFA_SOURCE_GROUP_ID));
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
        requestParameters.putSingle(CAUSE_CODE_LABEL_LIST_SOURCE, TEST_VALUE_CAUSE_VALUE_LABEL);
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureCCResult resultForHFArncGroup = result.get(0);

        assertThat(resultForHFArncGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFArncGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFArncGroup.getFailures(), is(1));
        assertThat(resultForHFArncGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFArncGroup.getImpactedSubscribersTarget(), is(""));
        assertThat(resultForHFArncGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFArncGroup.getEventID(), is(new Integer(TEST_EVENT_ID)));
        assertThat(resultForHFArncGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFArncGroup.getGroupName(), is(TEST_VALUE_GROUP_NAME));
        assertThat(resultForHFArncGroup.getState(), is(WCDMA_HFA_SOURCE_GROUP_ID));
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
        final String json = runQuery(wcdmaHandoverFailureCauseCodeService, requestParameters);

        final List<HandoverFailureCCResult> result = getTranslator().translateResult(json,
                HandoverFailureCCResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(4));

        final HandoverFailureCCResult resultForHFArncGroup = result.get(1);

        assertThat(resultForHFArncGroup.getLabel(), is("CC" + TEST_VALUE_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFArncGroup.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_VALUE_DESC));
        assertThat(resultForHFArncGroup.getFailures(), is(1));
        assertThat(resultForHFArncGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFArncGroup.getImpactedSubscribersTarget(), is(""));
        assertThat(resultForHFArncGroup.getCauseCode(), is(new Integer(TEST_VALUE_CAUSE_VALUE_1)));
        assertThat(resultForHFArncGroup.getEventID(), is(new Integer(TEST_EVENT_ID)));
        assertThat(resultForHFArncGroup.getHandoverType(), is(TEST_VALUE_CATEGORY_DESC));
        assertThat(resultForHFArncGroup.getGroupName(), is(TEST_VALUE_GROUP_NAME));
        assertThat(resultForHFArncGroup.getState(), is(WCDMA_HFA_SOURCE_GROUP_ID));
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForTables = new ArrayList<String>();

        // Raw event tables
        columnsForTables.add(DATETIME_ID);
        columnsForTables.add(HIER3_ID);
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

        // Dim E HIER321 CELL
        columnsForTables.clear();
        columnsForTables.add(HIER3_ID);
        columnsForTables.add(VENDOR);
        columnsForTables.add(HIERARCHY_3);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForTables);

        // Dim Z HIER321 CELL
        columnsForTables.clear();
        columnsForTables.add(HIER3_ID);
        columnsForTables.add(VENDOR);
        columnsForTables.add(HIERARCHY_3);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsForTables);

        // Dim controller groups
        columnsForTables.clear();
        columnsForTables.add(HIER3_ID);
        columnsForTables.add(RAT_COLUMN_NAME);
        columnsForTables.add(GROUP_NAME);
        createTemporaryTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, columnsForTables);
    }

    private void insertData() throws Exception {
        //// Event data
        final Map<String, Object> dataForTables = new HashMap<String, Object>();

        final String dateTimeNowMinus27Mins = DateTimeUtilities
                .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_BSC1_NODE));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
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

        // Non searched Controller. Should not be returned.
        dataForTables.clear();
        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_BSC42_NODE));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
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

        // Non group cell. Should not be returned.
        dataForTables.put(HIER3_ID, TEST_VALUE_HIER3_ID);
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_4);
        dataForTables.put(EVENT_ID, IRAT_HANDOVER_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        //// Dim cause codes
        dataForTables.clear();
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
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
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
        insertRow(TEMP_DIM_E_RAN_HFA_EVENTTYPE, dataForTables);

        // Dim E HIER321 CELL
        dataForTables.clear();
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_BSC1_NODE));
        dataForTables.put(VENDOR, TEST_VALUE_VENDOR);
        dataForTables.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC06:RNC06");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, dataForTables);

        // Dim Z HIER321 CELL
        dataForTables.clear();
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_BSC42_NODE));
        dataForTables.put(VENDOR, "AnotherVendor");
        dataForTables.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC09:RNC09");
        insertRow(TEMP_DIM_Z_SGEH_HIER321_CELL, dataForTables);

        // Dim rnc groups
        dataForTables.clear();
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_BSC1_NODE));
        dataForTables.put(RAT_COLUMN_NAME, RAT_INTEGER_VALUE_FOR_3G);
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, dataForTables);
    }

    private long createHashIDForController(final String node) {
        final String[] allData1 = node.split(DELIMITER);
        return queryUtils.createHashIDForController(allData1[2], allData1[0], allData1[1]);
    }
}
