/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.eventanalysis.rnc;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GRID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAT_INTEGER_VALUE_FOR_3G;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CATEGORY_DESC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.HIERARCHY_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.VENDOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_HFA_CATEGORY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_HIER321_CELL;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_Z_SGEH_HIER321_CELL;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis.RNCHandoverFailureEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureRNCEventsummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class RNCHFAESSRawTest extends BaseDataIntegrityTest<HandoverFailureRNCEventsummaryResult> {
    private RNCHandoverFailureEventSummaryService rncHandoverFailureEventSummaryService;

    @Before
    public void onSetUp() throws Exception {
        rncHandoverFailureEventSummaryService = new RNCHandoverFailureEventSummaryService();
        attachDependencies(rncHandoverFailureEventSummaryService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_RNC_EA_HandoverFailure_Raw() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(NODE_PARAM, "ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G");
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        final String json = runQuery(rncHandoverFailureEventSummaryService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<HandoverFailureRNCEventsummaryResult> result = getTranslator().translateResult(json,
                HandoverFailureRNCEventsummaryResult.class);
        assertThat(result.size(), is(4));

        final HandoverFailureRNCEventsummaryResult resultForHFARnc = result.get(0);

        assertThat(resultForHFARnc.getRatId(), is(1));
        assertThat(resultForHFARnc.getFailures(), is(1));
        assertThat(resultForHFARnc.getController(), is("ONRM_ROOT_MO_R:RNC01:RNC01"));
        assertThat(resultForHFARnc.getVendor(), is("Ericsson"));
        assertThat(resultForHFARnc.getImpactedSubscribers(), is(1));
    }

    private void insertData() throws Exception {
        final Map<String, Object> columnsFor_DIM_E_RAN_HFA_CATEGORY = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "0");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Soft Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, RAT_INTEGER_VALUE_FOR_3G);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "HSDSCH Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "2");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Interfrequency Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "3");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "IRAT Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC01:RNC01");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "Cell");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIER3_ID, "7348569777370983771");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIER3_CELL_ID, "630291001961878596");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> columnsFor_DIM_Z_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC01:RNC01");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(CELL_ID, "Cell");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIER3_ID, "7348569777370983771");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIER3_CELL_ID, "630291001961878596");
        insertRow(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsFor_DIM_Z_SGEH_HIER321_CELL);

        final Map<String, Object> dataForRawEventTable = new HashMap<String, Object>();

        final String dateTimeNowMinus27Mins = DateTimeUtilities
                .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

        dataForRawEventTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        dataForRawEventTable.put(HIER3_ID, "7348569777370983771");
        dataForRawEventTable.put(IMSI, "123451");
        dataForRawEventTable.put(TAC, "2312");
        dataForRawEventTable.put(DATETIME_ID, dateTimeNowMinus27Mins);
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

        final Collection<String> columnsFor_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(RAT);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIER3_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIER3_CELL_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Collection<String> columnsFor_DIM_Z_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(RAT);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIER3_ID);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIER3_CELL_ID);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsFor_DIM_Z_SGEH_HIER321_CELL);

        final Collection<String> columnsForRawSOHOEventTable = new ArrayList<String>();
        columnsForRawSOHOEventTable.add(RAT);
        columnsForRawSOHOEventTable.add(HIER3_ID);
        columnsForRawSOHOEventTable.add(IMSI);
        columnsForRawSOHOEventTable.add(TAC);
        columnsForRawSOHOEventTable.add(CATEGORY_ID);
        columnsForRawSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, columnsForRawSOHOEventTable);

        final Collection<String> columnsForRawIFHOEventTable = new ArrayList<String>();
        columnsForRawIFHOEventTable.add(RAT);
        columnsForRawIFHOEventTable.add(HIER3_ID);
        columnsForRawIFHOEventTable.add(IMSI);
        columnsForRawIFHOEventTable.add(TAC);
        columnsForRawIFHOEventTable.add(CATEGORY_ID);
        columnsForRawIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, columnsForRawIFHOEventTable);

        final Collection<String> columnsForRawIRATEventTable = new ArrayList<String>();
        columnsForRawIRATEventTable.add(RAT);
        columnsForRawIRATEventTable.add(HIER3_ID);
        columnsForRawIRATEventTable.add(IMSI);
        columnsForRawIRATEventTable.add(TAC);
        columnsForRawIRATEventTable.add(CATEGORY_ID);
        columnsForRawIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columnsForRawIRATEventTable);

        final Collection<String> columnsForRawHSDSCHEventTable = new ArrayList<String>();
        columnsForRawHSDSCHEventTable.add(RAT);
        columnsForRawHSDSCHEventTable.add(HIER3_ID);
        columnsForRawHSDSCHEventTable.add(IMSI);
        columnsForRawHSDSCHEventTable.add(TAC);
        columnsForRawHSDSCHEventTable.add(CATEGORY_ID);
        columnsForRawHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, columnsForRawHSDSCHEventTable);
    }
}
