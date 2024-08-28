/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.accessarea;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.SourceCellHandoverFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureAccessAreaRankingResult;
import com.ericsson.eniq.events.server.test.stubs.DummyHttpHeaders;
import com.ericsson.eniq.events.server.test.stubs.DummyUriInfoImpl;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class SourceCellHFARSAggTest extends
        BaseDataIntegrityTest<HandoverFailureAccessAreaRankingResult> {

    private SourceCellHandoverFailureRankingService accessAreaHandoverFailureRankingService;

    @Before
    public void onSetUp() throws Exception {
        accessAreaHandoverFailureRankingService = new SourceCellHandoverFailureRankingService();
        attachDependencies(accessAreaHandoverFailureRankingService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_AccessArea_HandoverFailure_Aggregation_OneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        new DummyUriInfoImpl(requestParameters, "WCDMA_HANDOVER_FAILURE_RANKING_ANALYSIS/" + TYPE_SOURCE_CELL, "");
        new DummyHttpHeaders();
        final String json = runQuery(accessAreaHandoverFailureRankingService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<HandoverFailureAccessAreaRankingResult> result = getTranslator().translateResult(json,
                HandoverFailureAccessAreaRankingResult.class);
        assertThat(result.size(), is(2));

        final HandoverFailureAccessAreaRankingResult resultForAccessArea = result.get(0);

        assertThat(resultForAccessArea.getRat(), is("3G"));
        assertThat(resultForAccessArea.getRatId(), is(1));
        assertThat(resultForAccessArea.getFailures(), is(16));
        assertThat(resultForAccessArea.getCell(), is("Cell"));
        assertThat(resultForAccessArea.getVendor(), is("Ericsson"));
        assertThat(resultForAccessArea.getRnc(), is("fdn"));
        assertThat(resultForAccessArea.getRank(), is(1));

        final HandoverFailureAccessAreaRankingResult resultForAccessAreaExternal = result.get(1);

        assertThat(resultForAccessAreaExternal.getRat(), is("3G"));
        assertThat(resultForAccessAreaExternal.getRatId(), is(1));
        assertThat(resultForAccessAreaExternal.getFailures(), is(16));
        assertThat(resultForAccessAreaExternal.getCell(), is(""));
        assertThat(resultForAccessAreaExternal.getVendor(), is("Non-Ericsson"));
        assertThat(resultForAccessAreaExternal.getRnc(), is(""));
        assertThat(resultForAccessAreaExternal.getRank(), is(1));

    }

    private void insertData() throws Exception {

        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "Cell");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_3, "fdn");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIER3_CELL_ID, "123456");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> columnsFor_DIM_Z_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(VENDOR, "Non-Ericsson");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(CELL_ID, "");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIERARCHY_3, "");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(HIER3_CELL_ID, "123456");
        columnsFor_DIM_Z_SGEH_HIER321_CELL.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsFor_DIM_Z_SGEH_HIER321_CELL);

        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        dataForEventTable.put(HIER3_CELL_ID, "123456");
        dataForEventTable.put(NO_OF_ERRORS, "4");
        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_HIER3_CELL_ERR_DAY, dataForEventTable);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_HIER3_CELL_ERR_DAY, dataForEventTable);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_HIER3_CELL_ERR_DAY, dataForEventTable);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_HIER3_CELL_ERR_DAY, dataForEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIER3_CELL_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Collection<String> columnsFor_DIM_Z_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIER3_CELL_ID);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_Z_SGEH_HIER321_CELL.add(RAT);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsFor_DIM_Z_SGEH_HIER321_CELL);

        final Collection<String> columnsForSOHOEventTable = new ArrayList<String>();
        columnsForSOHOEventTable.add(RAT);
        columnsForSOHOEventTable.add(HIER3_CELL_ID);
        columnsForSOHOEventTable.add(NO_OF_ERRORS);
        columnsForSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_HIER3_CELL_ERR_DAY, columnsForSOHOEventTable);

        final Collection<String> columnsForIFHOEventTable = new ArrayList<String>();
        columnsForIFHOEventTable.add(RAT);
        columnsForIFHOEventTable.add(HIER3_CELL_ID);
        columnsForIFHOEventTable.add(NO_OF_ERRORS);
        columnsForIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_HIER3_CELL_ERR_DAY, columnsForIFHOEventTable);

        final Collection<String> columnsForIRATEventTable = new ArrayList<String>();
        columnsForIRATEventTable.add(RAT);
        columnsForIRATEventTable.add(HIER3_CELL_ID);
        columnsForIRATEventTable.add(NO_OF_ERRORS);
        columnsForIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_HIER3_CELL_ERR_DAY, columnsForIRATEventTable);

        final Collection<String> columnsForHSDSCHEventTable = new ArrayList<String>();
        columnsForHSDSCHEventTable.add(RAT);
        columnsForHSDSCHEventTable.add(HIER3_CELL_ID);
        columnsForHSDSCHEventTable.add(NO_OF_ERRORS);
        columnsForHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_HIER3_CELL_ERR_DAY, columnsForHSDSCHEventTable);
    }
}
