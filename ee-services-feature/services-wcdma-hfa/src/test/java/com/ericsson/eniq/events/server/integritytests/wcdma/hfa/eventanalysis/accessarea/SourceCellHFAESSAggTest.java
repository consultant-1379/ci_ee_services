/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.eventanalysis.accessarea;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis.SourceCellHandoverFailureEventSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureCellEventSummaryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class SourceCellHFAESSAggTest extends
        BaseDataIntegrityTest<HandoverFailureCellEventSummaryResult> {
    private SourceCellHandoverFailureEventSummaryService sourceCellHandoverFailureEventSummaryService;

    @Before
    public void onSetUp() throws Exception {
        sourceCellHandoverFailureEventSummaryService = new SourceCellHandoverFailureEventSummaryService();
        attachDependencies(sourceCellHandoverFailureEventSummaryService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_SourceCELL_EA__HandoverFailure_Aggregation_OneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        requestParameters.putSingle(MAX_ROWS, "10");
        requestParameters.putSingle(RAT_PARAM, "1");
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        requestParameters.putSingle(RAN_VENDOR_PARAM, "Ericsson");
        requestParameters.putSingle(CELL_ID_PARAM, "Cell");
        final String json = runQuery(sourceCellHandoverFailureEventSummaryService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<HandoverFailureCellEventSummaryResult> result = getTranslator().translateResult(json,
                HandoverFailureCellEventSummaryResult.class);
        assertThat(result.size(), is(4));

        final HandoverFailureCellEventSummaryResult resultForHFACell = result.get(0);

        assertThat(resultForHFACell.getRatId(), is(1));
        assertThat(resultForHFACell.getFailures(), is(4));
        assertThat(resultForHFACell.getCell(), is("Cell"));
        assertThat(resultForHFACell.getController(), is("ONRM_ROOT_MO_R:RNC01:RNC01"));
        assertThat(resultForHFACell.getVendor(), is("Ericsson"));
        assertThat(resultForHFACell.getImpactedSubscribers(), is(1));
    }

    private void insertData() throws Exception {
        final Map<String, Object> columnsFor_DIM_E_RAN_HFA_CATEGORY = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Soft Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "HSDSCH Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Interfrequency Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "IRAT Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321 = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321.put(RAT, "0");
        columnsFor_DIM_E_SGEH_HIER321.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC01:RNC01");
        columnsFor_DIM_E_SGEH_HIER321.put(VENDOR, "Ericsson");
        columnsFor_DIM_E_SGEH_HIER321.put(CELL_ID, "Cell");
        columnsFor_DIM_E_SGEH_HIER321.put(HIER321_ID, "630291001961878596");
        insertRow(TEMP_DIM_E_SGEH_HIER321, columnsFor_DIM_E_SGEH_HIER321);

        final Map<String, Object> columnsFor_DIM_Z_SGEH_HIER321 = new HashMap<String, Object>();
        columnsFor_DIM_Z_SGEH_HIER321.put(RAT, "0");
        columnsFor_DIM_Z_SGEH_HIER321.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC01:RNC01");
        columnsFor_DIM_Z_SGEH_HIER321.put(VENDOR, "Ericsson");
        columnsFor_DIM_Z_SGEH_HIER321.put(CELL_ID, "Cell");
        columnsFor_DIM_Z_SGEH_HIER321.put(HIER321_ID, "630291001961878596");
        insertRow(TEMP_DIM_Z_SGEH_HIER321, columnsFor_DIM_Z_SGEH_HIER321);

        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(RAT, "1");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "Cell");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIER3_CELL_ID, "630291001961878596");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> columnsFor_DIM_Z_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(RAT, "1");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC01:RNC01");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(CELL_ID, "Cell");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIER3_CELL_ID, "630291001961878596");
        insertRow(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsFor_DIM_Z_SGEH_HIER321_CELL);

        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(RAT, "1");
        dataForEventTable.put(HIER3_CELL_ID, "630291001961878596");
        dataForEventTable.put(NO_OF_ERRORS, "4");
        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        dataForEventTable.put(CATEGORY_ID, "0");
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_HIER3_CELL_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "1");
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_HIER3_CELL_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "2");
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_HIER3_CELL_ERR_DAY, dataForEventTable);
        dataForEventTable.put(CATEGORY_ID, "3");
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_HIER3_CELL_ERR_DAY, dataForEventTable);

        final Map<String, Object> dataForRawEventTable = new HashMap<String, Object>();
        dataForRawEventTable.put(RAT, "1");
        dataForRawEventTable.put(HIER3_CELL_ID, "630291001961878596");
        dataForRawEventTable.put(IMSI, "123451");
        dataForRawEventTable.put(TAC, "2312");
        dataForRawEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        dataForRawEventTable.put(CATEGORY_ID, WCDMA_HFA_SOHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(CATEGORY_ID, WCDMA_HFA_HSDSCH_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(CATEGORY_ID, WCDMA_HFA_IFHO_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(CATEGORY_ID, WCDMA_HFA_IRAT_CATEGORY_ID);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForRawEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_DIM_E_RAN_HFA_CATEGORY = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Collection<String> columnsFor_DIM_E_SGEH_HIER321 = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_HIER321.add(RAT);
        columnsFor_DIM_E_SGEH_HIER321.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_HIER321.add(VENDOR);
        columnsFor_DIM_E_SGEH_HIER321.add(CELL_ID);
        columnsFor_DIM_E_SGEH_HIER321.add(HIER321_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, columnsFor_DIM_E_SGEH_HIER321);

        final Collection<String> columnsFor_DIM_Z_SGEH_HIER321 = new ArrayList<String>();
        columnsFor_DIM_Z_SGEH_HIER321.add(RAT);
        columnsFor_DIM_Z_SGEH_HIER321.add(HIERARCHY_3);
        columnsFor_DIM_Z_SGEH_HIER321.add(VENDOR);
        columnsFor_DIM_Z_SGEH_HIER321.add(CELL_ID);
        columnsFor_DIM_Z_SGEH_HIER321.add(HIER321_ID);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321, columnsFor_DIM_Z_SGEH_HIER321);

        final Collection<String> columnsFor_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(RAT);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIER3_CELL_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Collection<String> columnsFor_DIM_Z_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(RAT);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIER3_CELL_ID);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsFor_DIM_Z_SGEH_HIER321_CELL);

        final Collection<String> columnsForSOHOEventTable = new ArrayList<String>();
        columnsForSOHOEventTable.add(RAT);
        columnsForSOHOEventTable.add(HIER3_CELL_ID);
        columnsForSOHOEventTable.add(NO_OF_ERRORS);
        columnsForSOHOEventTable.add(CATEGORY_ID);
        columnsForSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_HIER3_CELL_ERR_DAY, columnsForSOHOEventTable);

        final Collection<String> columnsForIFHOEventTable = new ArrayList<String>();
        columnsForIFHOEventTable.add(RAT);
        columnsForIFHOEventTable.add(HIER3_CELL_ID);
        columnsForIFHOEventTable.add(NO_OF_ERRORS);
        columnsForIFHOEventTable.add(CATEGORY_ID);
        columnsForIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_HIER3_CELL_ERR_DAY, columnsForIFHOEventTable);

        final Collection<String> columnsForIRATEventTable = new ArrayList<String>();
        columnsForIRATEventTable.add(RAT);
        columnsForIRATEventTable.add(HIER3_CELL_ID);
        columnsForIRATEventTable.add(NO_OF_ERRORS);
        columnsForIRATEventTable.add(CATEGORY_ID);
        columnsForIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_HIER3_CELL_ERR_DAY, columnsForIRATEventTable);

        final Collection<String> columnsForHSDSCHEventTable = new ArrayList<String>();
        columnsForHSDSCHEventTable.add(RAT);
        columnsForHSDSCHEventTable.add(HIER3_CELL_ID);
        columnsForHSDSCHEventTable.add(NO_OF_ERRORS);
        columnsForHSDSCHEventTable.add(CATEGORY_ID);
        columnsForHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_HIER3_CELL_ERR_DAY, columnsForHSDSCHEventTable);

        final Collection<String> columnsForRawSOHOEventTable = new ArrayList<String>();
        columnsForRawSOHOEventTable.add(RAT);
        columnsForRawSOHOEventTable.add(HIER3_CELL_ID);
        columnsForRawSOHOEventTable.add(IMSI);
        columnsForRawSOHOEventTable.add(TAC);
        columnsForRawSOHOEventTable.add(CATEGORY_ID);
        columnsForRawSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, columnsForRawSOHOEventTable);

        final Collection<String> columnsForRawIFHOEventTable = new ArrayList<String>();
        columnsForRawIFHOEventTable.add(RAT);
        columnsForRawIFHOEventTable.add(HIER3_CELL_ID);
        columnsForRawIFHOEventTable.add(IMSI);
        columnsForRawIFHOEventTable.add(TAC);
        columnsForRawIFHOEventTable.add(CATEGORY_ID);
        columnsForRawIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, columnsForRawIFHOEventTable);

        final Collection<String> columnsForRawIRATEventTable = new ArrayList<String>();
        columnsForRawIRATEventTable.add(RAT);
        columnsForRawIRATEventTable.add(HIER3_CELL_ID);
        columnsForRawIRATEventTable.add(IMSI);
        columnsForRawIRATEventTable.add(TAC);
        columnsForRawIRATEventTable.add(CATEGORY_ID);
        columnsForRawIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columnsForRawIRATEventTable);

        final Collection<String> columnsForRawHSDSCHEventTable = new ArrayList<String>();
        columnsForRawHSDSCHEventTable.add(RAT);
        columnsForRawHSDSCHEventTable.add(HIER3_CELL_ID);
        columnsForRawHSDSCHEventTable.add(IMSI);
        columnsForRawHSDSCHEventTable.add(TAC);
        columnsForRawHSDSCHEventTable.add(CATEGORY_ID);
        columnsForRawHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, columnsForRawHSDSCHEventTable);
    }
}
