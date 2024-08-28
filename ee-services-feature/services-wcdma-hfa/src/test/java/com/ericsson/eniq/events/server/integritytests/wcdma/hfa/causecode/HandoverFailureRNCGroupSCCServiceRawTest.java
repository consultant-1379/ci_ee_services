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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode.WCDMAHandoverFailureSubCauseCodeService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureSCCGroupResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class HandoverFailureRNCGroupSCCServiceRawTest extends BaseDataIntegrityTest<HandoverFailureSCCGroupResult> {

    private WCDMAHandoverFailureSubCauseCodeService wcdmaHandoverFailureSubCauseCodeService;

    private static final String TEST_VALUE_HIER3_ID = "9876543210";

    private static final String TEST_EVENT_ID = "423";

    private static final String TEST_VALUE_CAUSE_VALUE = "1";

    private static final String TEST_VALUE_SUB_CAUSE_VALUE_1 = "1";

    private static final String TEST_VALUE_SUB_CAUSE_VALUE_2 = "2";

    private static final String TEST_VALUE_SUB_CAUSE_VALUE_3 = "3";

    private static final String TEST_VALUE_SUB_CAUSE_VALUE_4 = "4";

    private static final String TEST_VALUE_SUB_CAUSE_VALUE_DESC = "Test Sub Cause Code";

    private static final String TEST_VALUE_GROUP_NAME = "Access_WCDMA";

    private static final String TEST_VALUE_CATEGORY_TABLE_NAME = "TESTCAT";

    private static final String TEST_VALUE_CATEGORY_DESC = "TESTDESC";

    private static final String TEST_VALUE_EVENT_ID_LABEL = "_TEST_EVENT";

    private static final String TEST_VALUE_RNC01_NODE = "ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G";

    private static final String TEST_VALUE_BSC42_NODE = "BSC42,Ericsson,GSM";

    @Before
    public void onSetUp() throws Exception {
        wcdmaHandoverFailureSubCauseCodeService = new WCDMAHandoverFailureSubCauseCodeService();
        attachDependencies(wcdmaHandoverFailureSubCauseCodeService);
        createTables();
        insertData();
    }

    // RNC Group Test
    @Test
    public void testRNCGroupService() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(CAUSE_CODE_PARAM, TEST_VALUE_CAUSE_VALUE);
        requestParameters.putSingle(CATEGORY_ID_PARAM, String.valueOf(SOFT_HANDOVER_EVENT_ID_AS_INTEGER));
        requestParameters.putSingle(EVENT_ID_PARAM, TEST_EVENT_ID);
        requestParameters.putSingle(GROUP_NAME_PARAM, TEST_VALUE_GROUP_NAME);
        final String json = runQuery(wcdmaHandoverFailureSubCauseCodeService, requestParameters);

        final List<HandoverFailureSCCGroupResult> result = getTranslator().translateResult(json,
                HandoverFailureSCCGroupResult.class);
        assertThat("Insufficient number of results in JSON!", result.size(), is(1));

        final HandoverFailureSCCGroupResult resultForHFArncGroup = result.get(0);

        assertThat(resultForHFArncGroup.getLabel(), is("SCC" + TEST_VALUE_SUB_CAUSE_VALUE_1 + "-"
                + TEST_VALUE_CATEGORY_TABLE_NAME + TEST_VALUE_EVENT_ID_LABEL));
        assertThat(resultForHFArncGroup.getSubCauseCodeDesc(), is(TEST_VALUE_SUB_CAUSE_VALUE_DESC));
        assertThat(resultForHFArncGroup.getFailures(), is(1));
        assertThat(resultForHFArncGroup.getImpactedSubscribersSource(), is(1));
        assertThat(resultForHFArncGroup.getImpactedSubscribersTarget(), is(""));
        assertThat(resultForHFArncGroup.getCauseCodeId(), is(new Integer(TEST_VALUE_CAUSE_VALUE)));
        assertThat(resultForHFArncGroup.getSubCauseCodeId(), is(new Integer(TEST_VALUE_SUB_CAUSE_VALUE_1)));
        assertThat(resultForHFArncGroup.getEventId(), is(new Integer(TEST_EVENT_ID)));
        assertThat(resultForHFArncGroup.getCategoryId(), is(new Integer(SOFT_HANDOVER_EVENT_ID_AS_INTEGER)));
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
        columnsForTables.add(SUB_CAUSE_VALUE);
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
        columnsForTables.add(SUB_CAUSE_VALUE);
        columnsForTables.add(EVENT_ID);
        columnsForTables.add(SUB_CAUSE_VALUE_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, columnsForTables);

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
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_RNC01_NODE));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE);
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);

        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_2);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);

        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_3);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);

        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_4);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        // Non searched Controller. Should not be returned.
        dataForTables.clear();
        dataForTables.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_BSC42_NODE));
        dataForTables.put(CAUSE_VALUE, TEST_VALUE_CAUSE_VALUE);
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        dataForTables.put(TAC, TEST_VALUE_TAC);
        dataForTables.put(IMSI, TEST_VALUE_IMSI);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForTables);

        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_2);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForTables);

        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_3);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForTables);

        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_4);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        // Non group cell. Should not be returned.
        dataForTables.put(HIER3_ID, TEST_VALUE_HIER3_ID);
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_4);
        dataForTables.put(EVENT_ID, IRAT_HANDOVER_EVENT_ID_AS_INTEGER);
        dataForTables.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForTables);

        //// Dim cause codes
        dataForTables.clear();
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_1);
        dataForTables.put(EVENT_ID, TEST_EVENT_ID);
        dataForTables.put(SUB_CAUSE_VALUE_DESC, TEST_VALUE_SUB_CAUSE_VALUE_DESC);
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, dataForTables);
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_2);
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, dataForTables);
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_3);
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, dataForTables);
        dataForTables.put(SUB_CAUSE_VALUE, TEST_VALUE_SUB_CAUSE_VALUE_4);
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, dataForTables);

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

        // Dim rnc groups
        dataForTables.clear();
        dataForTables.put(HIER3_ID, createHashIDForController(TEST_VALUE_RNC01_NODE));
        dataForTables.put(RAT_COLUMN_NAME, RAT_INTEGER_VALUE_FOR_3G);
        dataForTables.put(GROUP_NAME, TEST_VALUE_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER3, dataForTables);
    }

    private long createHashIDForController(final String node) {
        final String[] allData1 = node.split(DELIMITER);
        return queryUtils.createHashIDForController(allData1[2], allData1[0], allData1[1]);
    }
}