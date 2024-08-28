/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.accessarea;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.AccessAreaCallFailureEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.AccessAreaCallFailureEventSummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author etonayr
 * @since 2011
 *
 */
public class AccessAreaCallFailureEventSummaryAggTest extends
        BaseDataIntegrityTest<AccessAreaCallFailureEventSummaryResult> {

    private AccessAreaCallFailureEventSummaryService service;

    private long HASH_ID;

    @Before
    public void init() throws Exception {
        createHashId();
        service = new AccessAreaCallFailureEventSummaryService();
        attachDependencies(service);
        createTables();
        insertDataIntoTacGroupTable();
        insertData();
    }

    private void createHashId() {
        HASH_ID = queryUtils.createHashIDFor3GCell(_3G, RNC01_ALTERNATIVE_FDN, "1", ERICSSON);
    }

    @Test
    public void testOneWeekQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.add(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.add(CELL_ID_PARAM, "1");
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);

        final String result = runQuery(service, requestParameters);
        verifyResultOneWeek(result);
    }

    @Test
    public void testThreeDayQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.add(TIME_QUERY_PARAM, THREE_DAY);
        requestParameters.add(CELL_ID_PARAM, "1");
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);

        final String result = runQuery(service, requestParameters);
        verifyResult(result);
    }

    private void verifyResultOneWeek(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_CELL_RANKING_RAN_WCDMA_EVENT_ANALYSIS_SUMMARY");
        final List<AccessAreaCallFailureEventSummaryResult> results = getTranslator().translateResult(json,
                AccessAreaCallFailureEventSummaryResult.class);
        assertThat(results.size(), is(1));
        final AccessAreaCallFailureEventSummaryResult result = results.get(0);
        assertThat(result.getVendor(), is(ERICSSON));
        assertThat(result.getController(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(result.getEventType(), is("Call Setup Failure"));
        assertThat(result.getFailures(), is(2));
        assertThat(result.getImpactedSubscribers(), is(1));
        assertThat(result.getEventId(), is(456));
        assertThat(result.getCellId(), is("Cell"));

    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_CELL_RANKING_RAN_WCDMA_EVENT_ANALYSIS_SUMMARY");
        final List<AccessAreaCallFailureEventSummaryResult> results = getTranslator().translateResult(json,
                AccessAreaCallFailureEventSummaryResult.class);
        assertThat(results.size(), is(1));
        final AccessAreaCallFailureEventSummaryResult result = results.get(0);
        assertThat(result.getVendor(), is(ERICSSON));
        assertThat(result.getController(), is(RNC01_ALTERNATIVE_FDN));
        assertThat(result.getEventType(), is("Call Setup Failure"));
        assertThat(result.getFailures(), is(1));
        assertThat(result.getImpactedSubscribers(), is(1));
        assertThat(result.getEventId(), is(456));
        assertThat(result.getCellId(), is("Cell"));
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void insertData() throws Exception {

        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIER3_CELL_ID_AGG, HASH_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(VENDOR, ERICSSON);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "Cell");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE = new HashMap<String, Object>();
        columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE.put(EVENT_ID, "456");
        columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE.put(EVENT_ID_ALTERNATE_DESC, "Call Setup Failure");
        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE);

        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(HIER3_CELL_ID_AGG, HASH_ID);
        dataForEventTable.put(EVENT_ID, "456");
        dataForEventTable.put(IMSI, "1");
        dataForEventTable.put(NO_OF_ERRORS, "1");
        dataForEventTable.put(TAC, SAMPLE_TAC);

        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(2));
        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID, dataForEventTable); // populate DAY Agg
        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID_15MIN, dataForEventTable);

        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(5));
        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID, dataForEventTable); // populate DAY Agg
        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID_15MIN, dataForEventTable); // populate 15MIN Agg

        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(14));
        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID, dataForEventTable); // populate DAY Agg
        insertRow(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID_15MIN, dataForEventTable); // populate 15MIN Agg (Agg tables completed)						

        final Map<String, Object> dataForRawTable5DayAgo = new HashMap<String, Object>();
        dataForRawTable5DayAgo.put(HIER3_CELL_ID, HASH_ID);
        dataForRawTable5DayAgo.put(EVENT_ID, "456");
        dataForRawTable5DayAgo.put(IMSI, "1");
        dataForRawTable5DayAgo.put(TAC, SAMPLE_TAC);
        dataForRawTable5DayAgo.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(5));
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, dataForRawTable5DayAgo); // RAW Table (RAW Table completed)

        final Map<String, Object> dataForRawTable2DayAgo = new HashMap<String, Object>();
        dataForRawTable2DayAgo.put(HIER3_CELL_ID, HASH_ID);
        dataForRawTable2DayAgo.put(EVENT_ID, "456");
        dataForRawTable2DayAgo.put(IMSI, "1");
        dataForRawTable2DayAgo.put(TAC, SAMPLE_TAC);
        dataForRawTable2DayAgo.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(2));
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, dataForRawTable2DayAgo);

    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE = new ArrayList<String>();
        columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE.add(EVENT_ID_ALTERNATE_DESC);
        columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE.add(EVENT_ID);
        createTemporaryTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE, columnsFor_TEMP_DIM_E_RAN_CFA_EVENTTYPE);

        final Collection<String> columnsFor_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIER3_CELL_ID_AGG);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(RAT);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Collection<String> columnsFor_RawTable = new ArrayList<String>();
        columnsFor_RawTable.add(HIER3_CELL_ID);
        columnsFor_RawTable.add(EVENT_ID);
        columnsFor_RawTable.add(IMSI);
        columnsFor_RawTable.add(TAC);
        columnsFor_RawTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsFor_RawTable);

        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(HIER3_CELL_ID_AGG);
        columnsForEventTable.add(EVENT_ID);
        columnsForEventTable.add(IMSI);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(NO_OF_ERRORS);
        columnsForEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID, columnsForEventTable);

        final Collection<String> columnsFor15MinEventTable = new ArrayList<String>();
        columnsFor15MinEventTable.add(HIER3_CELL_ID_AGG);
        columnsFor15MinEventTable.add(EVENT_ID);
        columnsFor15MinEventTable.add(IMSI);
        columnsFor15MinEventTable.add(TAC);
        columnsFor15MinEventTable.add(NO_OF_ERRORS);
        columnsFor15MinEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_CELL_ID_EVENTID_15MIN, columnsFor15MinEventTable);

    }

}
