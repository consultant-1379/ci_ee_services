/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.rnc;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.RNCHandoverFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureRNCRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class RNCHFARSAggTest extends
        BaseDataIntegrityTest<HandoverFailureRNCRankingResult> {
    private RNCHandoverFailureRankingService rncHandoverFailureRankingService;

    private final int numErrorsForRNC2 = 3;

    private final int numErrorsForRNC1FirstTimeStamp = 4;

    private final int numErrorsForRNC1SecondTimeStamp = 3;

    public static final String SAMPLE_RNC_FDN_1 = "sampleRNCFDNForRNC1";

    public static final String SAMPLE_RNC_FDN_2 = "sampleRNCFDNForRNC2";

    private static final int HASHED_HIER3ID_FOR_RNC01 = 1;

    private static final int HASHED_HIER3ID_FOR_RNC02 = 2;

    @Before
    public void onSetUp() throws Exception {
        rncHandoverFailureRankingService = new RNCHandoverFailureRankingService();
        attachDependencies(rncHandoverFailureRankingService);
        createTables();
        insertTopologyData();
        insertEventData();
    }

    private void insertTopologyData() throws SQLException {
        insertTopologyData(SAMPLE_RNC_FDN_1, HASHED_HIER3ID_FOR_RNC01);
        insertTopologyData(SAMPLE_RNC_FDN_2, HASHED_HIER3ID_FOR_RNC02);
    }

    private void insertTopologyData(final String rncFDN, final int hashedHier3ID) throws SQLException {

        final Map<String, Object> valuesForSgehTable = new HashMap<String, Object>();
        valuesForSgehTable.put(HIER3_ID, hashedHier3ID);
        valuesForSgehTable.put(VENDOR, ERICSSON);
        valuesForSgehTable.put(HIERARCHY_3, rncFDN);
        valuesForSgehTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForSgehTable);

        final Map<String, Object> valuesForZSgehTable = new HashMap<String, Object>();
        valuesForZSgehTable.put(HIER3_ID, hashedHier3ID);
        valuesForZSgehTable.put(VENDOR, "NON-ERICSSON");
        valuesForZSgehTable.put(HIERARCHY_3, "");
        valuesForZSgehTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_Z_SGEH_HIER321_CELL, valuesForZSgehTable);

    }

    private void insertEventData() throws SQLException {
        final String firstTimeStamp = DateTimeUtilities.getDateTimeMinus48Hours();
        insertDataForRNC(HASHED_HIER3ID_FOR_RNC01, numErrorsForRNC1FirstTimeStamp, firstTimeStamp);
        final String secondTimeStamp = DateTimeUtilities.getDateTimeMinus36Hours();
        insertDataForRNC(HASHED_HIER3ID_FOR_RNC01, numErrorsForRNC1SecondTimeStamp, secondTimeStamp);
        insertDataForRNC(HASHED_HIER3ID_FOR_RNC02, numErrorsForRNC2, firstTimeStamp);
    }

    private void insertDataForRNC(final int rncIdAsInteger, final int numErrors, final String timestamp)
            throws SQLException {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        valuesForEventTable.put(HIER3_ID, rncIdAsInteger);
        valuesForEventTable.put(NO_OF_ERRORS, numErrors);
        valuesForEventTable.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_HIER3_ERR_DAY, valuesForEventTable);
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_HIER3_ERR_DAY, valuesForEventTable);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_HIER3_ERR_DAY, valuesForEventTable);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_HIER3_ERR_DAY, valuesForEventTable);

    }

    private void createTables() throws Exception {
        final Collection<String> columnsForSGEHTopologyTable = new ArrayList<String>();
        columnsForSGEHTopologyTable.add(VENDOR);
        columnsForSGEHTopologyTable.add(HIERARCHY_3);
        columnsForSGEHTopologyTable.add(HIER3_ID);
        columnsForSGEHTopologyTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForSGEHTopologyTable);

        final Collection<String> columnsForZSGEHTopologyTable = new ArrayList<String>();
        columnsForZSGEHTopologyTable.add(VENDOR);
        columnsForZSGEHTopologyTable.add(HIERARCHY_3);
        columnsForZSGEHTopologyTable.add(HIER3_ID);
        columnsForZSGEHTopologyTable.add(RAT);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsForZSGEHTopologyTable);

        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(RAT);
        columnsForEventTable.add(HIER3_ID);
        columnsForEventTable.add(NO_OF_ERRORS);
        columnsForEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_HIER3_ERR_DAY, columnsForEventTable);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_HIER3_ERR_DAY, columnsForEventTable);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_HIER3_ERR_DAY, columnsForEventTable);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_HIER3_ERR_DAY, columnsForEventTable);
    }

    @Test
    public void testGetData_RNC_OneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        final String json = runQuery(rncHandoverFailureRankingService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {

        final List<HandoverFailureRNCRankingResult> result = getTranslator().translateResult(json,
                HandoverFailureRNCRankingResult.class);
        assertThat(result.size(), is(4));

        final HandoverFailureRNCRankingResult resultForRNC1 = result.get(0);
        assertThat(resultForRNC1.getVendor(), is(ERICSSON));
        assertThat(resultForRNC1.getRnc(), is(SAMPLE_RNC_FDN_1));
        assertThat(resultForRNC1.getFailures(), is(28));

        final HandoverFailureRNCRankingResult resultForRNC2 = result.get(1);
        assertThat(resultForRNC2.getVendor(), is("NON-ERICSSON"));
        assertThat(resultForRNC2.getRnc(), is(""));
        assertThat(resultForRNC2.getFailures(), is(28));
    }
}
