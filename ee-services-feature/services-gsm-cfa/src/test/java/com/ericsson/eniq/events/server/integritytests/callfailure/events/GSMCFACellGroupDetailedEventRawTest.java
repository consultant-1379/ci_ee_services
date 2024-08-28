/**
 * -----------------------------------------------------------------------
t *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.callfailure.events;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.gsm.callfailure.events.AccessAreaDetailedService;
import com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure.GSMCallFailureAccessAreaGroupDetailedEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eatiaro
 * @since 2011
 *
 */
public class GSMCFACellGroupDetailedEventRawTest extends
        BaseDataIntegrityTest<GSMCallFailureAccessAreaGroupDetailedEventAnalysisResult> {

    private static final String TEST_VALUE_CATEGORY_ID = GSM_CALL_DROP_CATEGORY_ID;

    private static final String TEST_VALUE_HIER321_ID_1 = "4948639634796658772";

    private static final String TEST_VALUE_HIER321_ID_2 = "5287639634796658772";

    private static final String TEST_VALUE_HIER321_ID_3 = "6358639634796658772";

    private String dateTime;

    private String dateTimeLocal;

    private static final String TEST_VALUE_URGENCY_CONDITION = "4";

    private static final String TEST_VALUE_URGENCY_CONDITION_DESC = "LOW SS DOWNLINK";

    private static final String TEST_VALUE_EXTENDED_CAUSE = "61";

    private static final String TEST_VALUE_EXTENDED_DESC = "OTHER, PREEMPTION";

    private static final String TEST_VALUE_CATEGORY_ID_DESC = "Call Drops";

    private static final String TEST_VALUE_IMSI = "46000608201336";

    private static final int TEST_VALUE_TAC = 100100;

    private static final String TEST_VALUE_MANUFACTURER = "Mitsubishi";

    private static final String TEST_VALUE_MARKETING_NAME = "G410";

    private static final int TEST_VALUE_RELEASE_TYPE = 0;

    private static final String TEST_VALUE_RELEASE_TYPE_DESC = "(MSC) NORMAL RELEASE";

    private static final String TEST_VALUE_HIER2 = "";

    private static final String TEST_VALUE_VENDOR = "Ericsson";

    private static final String TEST_VALUE_CELL_NAME_1 = "Cell1";

    private static final String TEST_VALUE_CELL_CONTROL_GROUP = "Control_Group";

    private long hashId;

    private AccessAreaDetailedService service;

    @Before
    public void setup() throws Exception {
        service = new AccessAreaDetailedService();
        createHashId();
        attachDependencies(service);
        createEventTable();
        createLookupTables();
        insertLookupData();
        insertDataIntoTacGroupTable();
        insertEventData();
    }

    private void createHashId() {
        hashId = queryUtils.createHashIDForCell("0", TEST_VALUE_GSM_CONTROLLER1_NAME, TEST_VALUE_HIER2,
                TEST_VALUE_GSM_CELL1_NAME, TEST_VALUE_VENDOR);

        System.out.println(hashId);
    }

    @Test
    public void testFiveMinuteQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, "+0000");
        requestParameters.add(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.add(TYPE_PARAM, TYPE_CELL);
        requestParameters.add(GROUP_NAME_PARAM, TEST_VALUE_CELL_GROUP);
        requestParameters.add(NODE_PARAM, TEST_VALUE_GSM_CELL1_NAME + "," + TEST_VALUE_HIER2 + ","
                + TEST_VALUE_GSM_CONTROLLER1_NAME + "," + TEST_VALUE_VENDOR + "," + "0");
        requestParameters.add(CATEGORY_ID, TEST_VALUE_CATEGORY_ID);
        final String result = runQuery(service, requestParameters);
        verifyResult(result);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_RAN_GSM_DETAILED_EVENT_ANALYSIS_CELL_GROUP");
        final List<GSMCallFailureAccessAreaGroupDetailedEventAnalysisResult> results = getTranslator().translateResult(
                json, GSMCallFailureAccessAreaGroupDetailedEventAnalysisResult.class);

        assertThat(results.size(), is(2));
        final GSMCallFailureAccessAreaGroupDetailedEventAnalysisResult result = results.get(0);
        assertThat(result.getCauseValue(), is(TEST_VALUE_URGENCY_CONDITION_DESC));
        assertThat(result.getExtendedCauseValue(), is(TEST_VALUE_EXTENDED_DESC));
        assertThat(result.getCategoryDesc(), is(TEST_VALUE_CATEGORY_ID_DESC));
        assertThat(result.getImsi(), is(TEST_VALUE_IMSI));
        assertThat(result.getTac(), is(TEST_VALUE_TAC));
        assertThat(result.getTerminalMake(), is(TEST_VALUE_MANUFACTURER));
        assertThat(result.getTerminalModel(), is(TEST_VALUE_MARKETING_NAME));
        final String expectedEventTime = DateTimeUtilities.adjustForDST(appendZeroMilliseconds(dateTime));
        assertThat(result.getEventTime(), is(expectedEventTime));
    }

    private void insertEventData() throws Exception {
        dateTime = DateTimeUtilities.getDateTimeMinus5Minutes();
        //dateTimeLocal = DateTimeUtilities.applyOffsetToDateTime(dateTime, 1);
        insertData(TEST_VALUE_IMSI, TEST_VALUE_CATEGORY_ID, TEST_VALUE_HIER321_ID_1, TEST_VALUE_TAC, dateTime);
        insertData(TEST_VALUE_IMSI, TEST_VALUE_CATEGORY_ID, TEST_VALUE_HIER321_ID_2, TEST_VALUE_TAC, dateTime);
        insertData(TEST_VALUE_IMSI, TEST_VALUE_CATEGORY_ID, TEST_VALUE_HIER321_ID_3, TEST_VALUE_TAC, dateTime);
        insertData(TEST_VALUE_IMSI, TEST_VALUE_CATEGORY_ID, TEST_VALUE_HIER321_ID_1, SAMPLE_EXCLUSIVE_TAC, dateTime);
    }

    private void insertData(final String imsi, final String categoryId, final String hier321ID, final int tac,
            final String time) throws SQLException {
        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(HIER321_ID, hier321ID);
        dataForEventTable.put(TAC, tac);
        dataForEventTable.put(URGENCY_CONDITION, TEST_VALUE_URGENCY_CONDITION);
        dataForEventTable.put(EXTENDED_CAUSE, TEST_VALUE_EXTENDED_CAUSE);
        dataForEventTable.put(RELEASE_TYPE, TEST_VALUE_RELEASE_TYPE);
        dataForEventTable.put(DATETIME_ID, time);
        dataForEventTable.put(EVENT_TIME, time);
        dataForEventTable.put(TIMEZONE, "0");
        dataForEventTable.put(IMSI, imsi);
        dataForEventTable.put(CATEGORY_ID, categoryId);
        insertRow(TEMP_EVENT_E_GSM_CFA_ERR_RAW, dataForEventTable);

    }

    private void createLookupTables() throws Exception {
        createAndReplaceLookupTable(TEMP_DIM_E_SGEH_TAC, DIM_E_SGEH_TAC, MANUFACTURER, MARKETING_NAME, TAC);
        createAndReplaceLookupTable(TEMP_DIM_E_GSM_CFA_EVENTTYPE, "DIM_E_GSM_CFA_EVENTTYPE", CATEGORY_ID,
                CATEGORY_ID_DESC);
        createAndReplaceLookupTable(TEMP_DIM_E_GSM_CFA_URGENCY_CONDITION, "DIM_E_GSM_CFA_URGENCY_CONDITION",
                URGENCY_CONDITION, URGENCY_CONDITION_DESC);
        createAndReplaceLookupTable(TEMP_DIM_E_GSM_CFA_EXTENDED_CAUSE, "DIM_E_GSM_CFA_EXTENDED_CAUSE", EXTENDED_CAUSE,
                EXTENDED_CAUSE_DESC);
        createAndReplaceLookupTable(TEMP_DIM_E_GSM_CFA_RELEASE_TYPE, "DIM_E_GSM_CFA_RELEASE_TYPE", RELEASE_TYPE,
                RELEASE_TYPE_DESC);
        createAndReplaceLookupTable(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, "GROUP_TYPE_E_RAT_VEND_HIER321",
                GROUP_NAME_KEY, HIER321_ID, HIERARCHY_1);
    }

    private void createAndReplaceLookupTable(final String tempTableName, final String tableNameToReplace,
            final String... columns) throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        for (final String column : columns) {
            columnsForTable.add(column);
        }
        createTemporaryTable(tempTableName, columnsForTable);
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(tableNameToReplace, tempTableName);
    }

    private void insertLookupData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(MANUFACTURER, TEST_VALUE_MANUFACTURER);
        valuesForTable.put(MARKETING_NAME, TEST_VALUE_MARKETING_NAME);
        valuesForTable.put(TAC, TEST_VALUE_TAC);
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(CATEGORY_ID, TEST_VALUE_CATEGORY_ID);
        valuesForTable.put(CATEGORY_ID_DESC, TEST_VALUE_CATEGORY_ID_DESC);
        insertRow(TEMP_DIM_E_GSM_CFA_EVENTTYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(URGENCY_CONDITION, TEST_VALUE_URGENCY_CONDITION);
        valuesForTable.put(URGENCY_CONDITION_DESC, TEST_VALUE_URGENCY_CONDITION_DESC);
        insertRow(TEMP_DIM_E_GSM_CFA_URGENCY_CONDITION, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(EXTENDED_CAUSE, TEST_VALUE_EXTENDED_CAUSE);
        valuesForTable.put(EXTENDED_CAUSE_DESC, TEST_VALUE_EXTENDED_DESC);
        insertRow(TEMP_DIM_E_GSM_CFA_EXTENDED_CAUSE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(RELEASE_TYPE, TEST_VALUE_RELEASE_TYPE);
        valuesForTable.put(RELEASE_TYPE_DESC, TEST_VALUE_RELEASE_TYPE_DESC);
        insertRow(TEMP_DIM_E_GSM_CFA_RELEASE_TYPE, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(GROUP_NAME_KEY, TEST_VALUE_CELL_GROUP);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_1);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL_NAME_1);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(GROUP_NAME_KEY, TEST_VALUE_CELL_GROUP);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_2);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL_NAME_1);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(GROUP_NAME_KEY, TEST_VALUE_CELL_CONTROL_GROUP);
        valuesForTable.put(HIER321_ID, TEST_VALUE_HIER321_ID_3);
        valuesForTable.put(HIERARCHY_1, TEST_VALUE_CELL_NAME_1);
        insertRow(TEMP_GROUP_TYPE_E_RAT_VEND_HIER321, valuesForTable);
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void createEventTable() throws Exception {
        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(HIER321_ID);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(URGENCY_CONDITION);
        columnsForEventTable.add(EXTENDED_CAUSE);
        columnsForEventTable.add(RELEASE_TYPE);
        columnsForEventTable.add(DATETIME_ID);
        columnsForEventTable.add(EVENT_TIME);
        columnsForEventTable.add(TIMEZONE);
        columnsForEventTable.add(IMSI);
        columnsForEventTable.add(CATEGORY_ID);
        createTemporaryTable(TEMP_EVENT_E_GSM_CFA_ERR_RAW, columnsForEventTable);
    }

}
