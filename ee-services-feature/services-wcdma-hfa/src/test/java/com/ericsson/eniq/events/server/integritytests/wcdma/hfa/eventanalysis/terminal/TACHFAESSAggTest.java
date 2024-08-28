/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.eventanalysis.terminal;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis.TACHandoverFailureEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureTACEventsummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class TACHFAESSAggTest extends
        BaseDataIntegrityTest<HandoverFailureTACEventsummaryResult> {
    private TACHandoverFailureEventSummaryService tacHandoverFailureEventSummaryService;

    @Before
    public void onSetUp() throws Exception {
        tacHandoverFailureEventSummaryService = new TACHandoverFailureEventSummaryService();
        attachDependencies(tacHandoverFailureEventSummaryService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_TAC_EA_HandoverFailure_Aggregation_OneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(TAC_PARAM, "12345");
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        final String json = runQuery(tacHandoverFailureEventSummaryService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<HandoverFailureTACEventsummaryResult> result = getTranslator().translateResult(json,
                HandoverFailureTACEventsummaryResult.class);
        assertThat(result.size(), is(4));

        final HandoverFailureTACEventsummaryResult resultForHFATac = result.get(0);

        assertThat(resultForHFATac.getFailures(), is(4));
        assertThat(resultForHFATac.getImpactedSubscribers(), is(1));
    }

    private void insertData() throws Exception {
        final Map<String, Object> columnsFor_DIM_E_RAN_HFA_CATEGORY = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "0");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Soft Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "1");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "HSDSCH Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "2");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Interfrequency Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "3");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "IRAT Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Map<String, Object> columnsFor_DIM_E_SGEH_TAC = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_TAC.put(TAC, "12345");
        columnsFor_DIM_E_SGEH_TAC.put(MANUFACTURER, "ABC");
        columnsFor_DIM_E_SGEH_TAC.put(MARKETING_NAME, "ABC");
        insertRow(TEMP_DIM_E_SGEH_TAC, columnsFor_DIM_E_SGEH_TAC);

        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(TAC, "12345");
        dataForEventTable.put(NO_OF_ERRORS, "4");
        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        dataForEventTable.put(CATEGORY_ID, "0");
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "1");
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "2");
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "3");
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY, dataForEventTable);

        final Map<String, Object> dataForRawEventTable = new HashMap<String, Object>();
        dataForRawEventTable.put(IMSI, "123451");
        dataForRawEventTable.put(TAC, "12345");
        dataForRawEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        dataForRawEventTable.put(CATEGORY_ID, "0");
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(CATEGORY_ID, "1");
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(CATEGORY_ID, "2");
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(CATEGORY_ID, "3");
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForRawEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_DIM_E_RAN_HFA_CATEGORY = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Collection<String> columnsFor_DIM_E_SGEH_TAC = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_TAC.add(TAC);
        columnsFor_DIM_E_SGEH_TAC.add(MANUFACTURER);
        columnsFor_DIM_E_SGEH_TAC.add(MARKETING_NAME);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columnsFor_DIM_E_SGEH_TAC);

        final Collection<String> columnsForSOHOEventTable = new ArrayList<String>();
        columnsForSOHOEventTable.add(TAC);
        columnsForSOHOEventTable.add(NO_OF_ERRORS);
        columnsForSOHOEventTable.add(CATEGORY_ID);
        columnsForSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY, columnsForSOHOEventTable);

        final Collection<String> columnsForIFHOEventTable = new ArrayList<String>();
        columnsForIFHOEventTable.add(TAC);
        columnsForIFHOEventTable.add(NO_OF_ERRORS);
        columnsForIFHOEventTable.add(CATEGORY_ID);
        columnsForIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY, columnsForIFHOEventTable);

        final Collection<String> columnsForIRATEventTable = new ArrayList<String>();
        columnsForIRATEventTable.add(TAC);
        columnsForIRATEventTable.add(NO_OF_ERRORS);
        columnsForIRATEventTable.add(CATEGORY_ID);
        columnsForIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY, columnsForIRATEventTable);

        final Collection<String> columnsForHSDSCHEventTable = new ArrayList<String>();
        columnsForHSDSCHEventTable.add(TAC);
        columnsForHSDSCHEventTable.add(NO_OF_ERRORS);
        columnsForHSDSCHEventTable.add(CATEGORY_ID);
        columnsForHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_DAY, columnsForHSDSCHEventTable);

        final Collection<String> columnsForRawSOHOEventTable = new ArrayList<String>();
        columnsForRawSOHOEventTable.add(IMSI);
        columnsForRawSOHOEventTable.add(TAC);
        columnsForRawSOHOEventTable.add(CATEGORY_ID);
        columnsForRawSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, columnsForRawSOHOEventTable);

        final Collection<String> columnsForRawIFHOEventTable = new ArrayList<String>();
        columnsForRawIFHOEventTable.add(IMSI);
        columnsForRawIFHOEventTable.add(TAC);
        columnsForRawIFHOEventTable.add(CATEGORY_ID);
        columnsForRawIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, columnsForRawIFHOEventTable);

        final Collection<String> columnsForRawIRATEventTable = new ArrayList<String>();
        columnsForRawIRATEventTable.add(IMSI);
        columnsForRawIRATEventTable.add(TAC);
        columnsForRawIRATEventTable.add(CATEGORY_ID);
        columnsForRawIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columnsForRawIRATEventTable);

        final Collection<String> columnsForRawHSDSCHEventTable = new ArrayList<String>();
        columnsForRawHSDSCHEventTable.add(IMSI);
        columnsForRawHSDSCHEventTable.add(TAC);
        columnsForRawHSDSCHEventTable.add(CATEGORY_ID);
        columnsForRawHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, columnsForRawHSDSCHEventTable);
    }
}
