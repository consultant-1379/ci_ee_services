/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.ranking.tac;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXCLUSIVE_TAC_GROUP;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIVE_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GROUP_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MANUFACTURER_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_EXCLUSIVE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_TAC;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_GROUP_TYPE_E_TAC;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.TACCallFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.CallFailureTACRankingResult;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eriwals
 *
 */
public class TACCallFailureRankingServiceRawTest extends BaseDataIntegrityTest<CallFailureTACRankingResult> {

    private static final int IMSI_1 = 12345678;

    private static final int IMSI_2 = 87654321;

    private TACCallFailureRankingService tacCallFailureRankingService;

    @Before
    public void onSetUp() throws Exception {
        tacCallFailureRankingService = new TACCallFailureRankingService();
        attachDependencies(tacCallFailureRankingService);
        createTables();
        insertData();
        createAndPopulateLookupTables();
        insertDataIntoTacGroupTable();
    }

    @Test
    public void testGetRankingData_TAC_CFA() throws Exception {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");

        final String json = runQuery(tacCallFailureRankingService, map);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);

        final ResultTranslator<CallFailureTACRankingResult> rt = getTranslator();
        final List<CallFailureTACRankingResult> rankingResult = rt.translateResult(json,
                CallFailureTACRankingResult.class);

        assertThat(rankingResult.size(), is(2));

        final CallFailureTACRankingResult worstTac = rankingResult.get(0);
        assertThat(worstTac.getManufacturer(), is("Apple Inc"));
        assertThat(worstTac.getModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC_2));
        assertThat(worstTac.getTac(), is(SAMPLE_TAC_2));
        assertThat(worstTac.getFailures(), is(1));

        final CallFailureTACRankingResult nextWorstTac = rankingResult.get(1);
        assertThat(nextWorstTac.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(nextWorstTac.getModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(nextWorstTac.getTac(), is(SAMPLE_TAC));
        assertThat(nextWorstTac.getFailures(), is(1));
    }

    private void insertData() throws Exception {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(IMSI, IMSI_1);
        valuesForEventTable.put(TAC, SAMPLE_TAC);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus5Minutes());
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);

        valuesForEventTable.clear();
        valuesForEventTable.put(IMSI, IMSI_2);
        valuesForEventTable.put(TAC, SAMPLE_TAC_2);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus5Minutes());
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);

        //Insert exclusive TAC for negative test case
        valuesForEventTable.clear();
        valuesForEventTable.put(IMSI, IMSI_2);
        valuesForEventTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus5Minutes());
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);

        //Insert null TAC for negative test case
        valuesForEventTable.clear();
        valuesForEventTable.put(IMSI, IMSI_2);
        valuesForEventTable.put(TAC, "1");
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus5Minutes());
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForEventTable);
    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);
        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void createTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
    }
}