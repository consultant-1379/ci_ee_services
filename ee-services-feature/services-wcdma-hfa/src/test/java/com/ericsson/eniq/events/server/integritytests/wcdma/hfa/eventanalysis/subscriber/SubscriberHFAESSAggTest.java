/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.eventanalysis.subscriber;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis.SubscriberHandoverFailureEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureIMSIEventsummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class SubscriberHFAESSAggTest extends
        BaseDataIntegrityTest<HandoverFailureIMSIEventsummaryResult> {
    private SubscriberHandoverFailureEventSummaryService subscriberHandoverFailureEventSummaryService;

    @Before
    public void onSetUp() throws Exception {
        subscriberHandoverFailureEventSummaryService = new SubscriberHandoverFailureEventSummaryService();
        attachDependencies(subscriberHandoverFailureEventSummaryService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_IMSI_EA_HandoverFailure_Aggregation_OneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(IMSI_PARAM, "12345");
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        final String json = runQuery(subscriberHandoverFailureEventSummaryService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<HandoverFailureIMSIEventsummaryResult> result = getTranslator().translateResult(json,
                HandoverFailureIMSIEventsummaryResult.class);
        assertThat(result.size(), is(4));

        final HandoverFailureIMSIEventsummaryResult resultForHFAImsi = result.get(0);

        assertThat(resultForHFAImsi.getFailures(), is(4));
        assertThat(resultForHFAImsi.getImsi(), is(12345));
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

        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(IMSI, "12345");
        dataForEventTable.put(NO_OF_ERRORS, "4");
        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        dataForEventTable.put(CATEGORY_ID, "0");
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_IMSI_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "1");
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_IMSI_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "2");
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_IMSI_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "3");
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_IMSI_ERR_DAY, dataForEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_DIM_E_RAN_HFA_CATEGORY = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Collection<String> columnsForSOHOEventTable = new ArrayList<String>();
        columnsForSOHOEventTable.add(IMSI);
        columnsForSOHOEventTable.add(NO_OF_ERRORS);
        columnsForSOHOEventTable.add(CATEGORY_ID);
        columnsForSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_IMSI_ERR_DAY, columnsForSOHOEventTable);

        final Collection<String> columnsForIFHOEventTable = new ArrayList<String>();
        columnsForIFHOEventTable.add(IMSI);
        columnsForIFHOEventTable.add(NO_OF_ERRORS);
        columnsForIFHOEventTable.add(CATEGORY_ID);
        columnsForIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_IMSI_ERR_DAY, columnsForIFHOEventTable);

        final Collection<String> columnsForIRATEventTable = new ArrayList<String>();
        columnsForIRATEventTable.add(IMSI);
        columnsForIRATEventTable.add(NO_OF_ERRORS);
        columnsForIRATEventTable.add(CATEGORY_ID);
        columnsForIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_IMSI_ERR_DAY, columnsForIRATEventTable);

        final Collection<String> columnsForHSDSCHEventTable = new ArrayList<String>();
        columnsForHSDSCHEventTable.add(IMSI);
        columnsForHSDSCHEventTable.add(NO_OF_ERRORS);
        columnsForHSDSCHEventTable.add(CATEGORY_ID);
        columnsForHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_IMSI_ERR_DAY, columnsForHSDSCHEventTable);
    }
}
