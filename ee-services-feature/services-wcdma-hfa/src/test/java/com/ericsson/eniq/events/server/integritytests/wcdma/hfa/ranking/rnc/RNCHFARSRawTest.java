/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.rnc;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GRID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAT_INTEGER_VALUE_FOR_3G;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ERICSSON;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.HIERARCHY_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.VENDOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_HIER321_CELL;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_Z_SGEH_HIER321_CELL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
public class RNCHFARSRawTest extends BaseDataIntegrityTest<HandoverFailureRNCRankingResult> {
    private static final int HASHED_HIER3ID_FOR_RNC01 = 1;

    private static final int HASHED_HIER3ID_FOR_RNC02 = 2;

    private RNCHandoverFailureRankingService rncHandoverFailureRankingService;

    private final int numErrorsForRNC1 = 5;

    private final int numErrorsForRNC2 = 3;

    public static final String SAMPLE_RNC_FDN_1 = "sampleRNCFDNForRNC1";

    public static final String SAMPLE_RNC_FDN_2 = "sampleRNCFDNForRNC2";

    @Before
    public void onSetUp() throws Exception {
        rncHandoverFailureRankingService = new RNCHandoverFailureRankingService();
        attachDependencies(rncHandoverFailureRankingService);
        createTables();
        insertData();
    }

    private void insertData() throws SQLException {
        insertDataForRNC(SAMPLE_RNC_FDN_1, HASHED_HIER3ID_FOR_RNC01, numErrorsForRNC1);
        insertDataForRNC(SAMPLE_RNC_FDN_2, HASHED_HIER3ID_FOR_RNC02, numErrorsForRNC2);
    }

    private void insertDataForRNC(final String rncFDN, final int hashedHier3ID, final int numErrors)
            throws SQLException {

        final Map<String, Object> valuesForRncTable = new HashMap<String, Object>();
        valuesForRncTable.put(VENDOR, ERICSSON);
        valuesForRncTable.put(HIER3_ID, hashedHier3ID);
        valuesForRncTable.put(HIERARCHY_3, rncFDN);
        valuesForRncTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, valuesForRncTable);

        final Map<String, Object> valuesForZRncTable = new HashMap<String, Object>();
        valuesForZRncTable.put(VENDOR, "NON-Ericsson");
        valuesForZRncTable.put(HIER3_ID, hashedHier3ID);
        valuesForZRncTable.put(HIERARCHY_3, "");
        valuesForZRncTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_Z_SGEH_HIER321_CELL, valuesForZRncTable);

        for (int i = 0; i < numErrors; i++) {
            final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
            final String dateTimeNowMinus27Mins = DateTimeUtilities
                    .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

            valuesForEventTable.put(HIER3_ID, hashedHier3ID);
            valuesForEventTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
            valuesForEventTable.put(TAC, "123456");
            valuesForEventTable.put(DATETIME_ID, dateTimeNowMinus27Mins);
            insertRow("#EVENT_E_RAN_HFA_IFHO_ERR_RAW", valuesForEventTable);
            insertRow("#EVENT_E_RAN_HFA_SOHO_ERR_RAW", valuesForEventTable);
            insertRow("#EVENT_E_RAN_HFA_IRAT_ERR_RAW", valuesForEventTable);
            insertRow("#EVENT_E_RAN_HFA_HSDSCH_ERR_RAW", valuesForEventTable);
        }
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForDIMTable = new ArrayList<String>();
        columnsForDIMTable.add(RAT);
        columnsForDIMTable.add(VENDOR);
        columnsForDIMTable.add(HIERARCHY_3);
        columnsForDIMTable.add(HIER3_ID);
        columnsForDIMTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsForDIMTable);

        final Collection<String> columnsForZDIMTable = new ArrayList<String>();
        columnsForZDIMTable.add(RAT);
        columnsForZDIMTable.add(VENDOR);
        columnsForZDIMTable.add(HIERARCHY_3);
        columnsForZDIMTable.add(HIER3_ID);
        columnsForZDIMTable.add(RAT);
        createTemporaryTable(TEMP_DIM_Z_SGEH_HIER321_CELL, columnsForZDIMTable);

        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(RAT);
        columnsForEventTable.add(HIER3_ID);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(DATETIME_ID);
        createTemporaryTable("#EVENT_E_RAN_HFA_IFHO_ERR_RAW", columnsForEventTable);
        createTemporaryTable("#EVENT_E_RAN_HFA_SOHO_ERR_RAW", columnsForEventTable);
        createTemporaryTable("#EVENT_E_RAN_HFA_IRAT_ERR_RAW", columnsForEventTable);
        createTemporaryTable("#EVENT_E_RAN_HFA_HSDSCH_ERR_RAW", columnsForEventTable);
    }

    @Test
    public void testGetData_RNC_ThirtyMinutes() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
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
        assertThat(resultForRNC1.getRat(), is("3G"));
        assertThat(resultForRNC1.getRatId(), is(1));
        assertThat(resultForRNC1.getVendor(), is(ERICSSON));
        assertThat(resultForRNC1.getRnc(), is(SAMPLE_RNC_FDN_1));
        assertThat(resultForRNC1.getFailures(), is(20));

        final HandoverFailureRNCRankingResult resultForRNC2 = result.get(1);
        assertThat(resultForRNC2.getRat(), is("3G"));
        assertThat(resultForRNC2.getRatId(), is(1));
        assertThat(resultForRNC2.getVendor(), is("NON-Ericsson"));
        assertThat(resultForRNC2.getRnc(), is(""));
        assertThat(resultForRNC2.getFailures(), is(20));
    }
}
