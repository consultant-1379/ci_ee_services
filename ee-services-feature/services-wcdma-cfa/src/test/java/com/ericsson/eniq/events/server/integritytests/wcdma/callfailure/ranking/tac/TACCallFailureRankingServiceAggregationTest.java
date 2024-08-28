/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.ranking.tac;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.TACCallFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.CallFailureTACRankingResult;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eriwals
 * 
 */
public class TACCallFailureRankingServiceAggregationTest extends BaseDataIntegrityTest<CallFailureTACRankingResult> {

    private static final int TAC_1 = 100100;

    private static final int TAC_2 = 100200;

    private static final String SIEMENS = "Siemens";

    private static final String MITSUBISHI = "Mitsubishi";

    private TACCallFailureRankingService tacCallFailureRankingService;

    @Before
    public void onSetUp() throws Exception {
        tacCallFailureRankingService = new TACCallFailureRankingService();
        attachDependencies(tacCallFailureRankingService);
        createTables();
        insertData();
    }

    @Test
    public void testGetRankingData_TAC_CFA_15MIN() throws Exception {

        final String json = callServiceByURL();
        System.out.println(json);

        final ResultTranslator<CallFailureTACRankingResult> rt = getTranslator();
        final List<CallFailureTACRankingResult> rankingResult = rt.translateResult(json,
                CallFailureTACRankingResult.class);

        assertThat(rankingResult.size(), is(2));

        final CallFailureTACRankingResult worstTac = rankingResult.get(0);
        assertThat(worstTac.getManufacturer(), is(SIEMENS));
        assertThat(worstTac.getModel(), is("A53"));
        assertThat(worstTac.getTac(), is(100200));
        assertThat(worstTac.getFailures(), is(56));

        final CallFailureTACRankingResult nextWorstTac = rankingResult.get(1);
        assertThat(nextWorstTac.getManufacturer(), is(MITSUBISHI));
        assertThat(nextWorstTac.getModel(), is("G410"));
        assertThat(nextWorstTac.getTac(), is(100100));
        assertThat(nextWorstTac.getFailures(), is(23));
    }

    @Test
    public void testGetRankingData_TAC_CFA_DAY() throws Exception {

        final String json = callServiceByURL();
        System.out.println(json);

        final ResultTranslator<CallFailureTACRankingResult> rt = getTranslator();
        final List<CallFailureTACRankingResult> rankingResult = rt.translateResult(json,
                CallFailureTACRankingResult.class);

        assertThat(rankingResult.size(), is(2));

        final CallFailureTACRankingResult worstTac = rankingResult.get(0);
        assertThat(worstTac.getManufacturer(), is(SIEMENS));
        assertThat(worstTac.getModel(), is("A53"));
        assertThat(worstTac.getTac(), is(100200));
        assertThat(worstTac.getFailures(), is(56));

        final CallFailureTACRankingResult nextWorstTac = rankingResult.get(1);
        assertThat(nextWorstTac.getManufacturer(), is(MITSUBISHI));
        assertThat(nextWorstTac.getModel(), is("G410"));
        assertThat(nextWorstTac.getTac(), is(100100));
        assertThat(nextWorstTac.getFailures(), is(23));

    }

    private String callServiceByURL() {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, "grid");
        map.putSingle(TIME_QUERY_PARAM, "10085");
        map.putSingle(TZ_OFFSET, "+0100");
        map.putSingle(MAX_ROWS, "10");

        return runQuery(tacCallFailureRankingService, map);
    }

    private void insertData() throws Exception {
        final Map<String, Object> valuesForEventTable = new HashMap<String, Object>();
        valuesForEventTable.put(TAC, TAC_1);
        valuesForEventTable.put(NO_OF_ERRORS, 23);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        insertRow(EVENT_E_RAN_CFA_TAC_DAY, valuesForEventTable);

        valuesForEventTable.clear();
        valuesForEventTable.put(TAC, TAC_2);
        valuesForEventTable.put(NO_OF_ERRORS, 56);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        insertRow(EVENT_E_RAN_CFA_TAC_DAY, valuesForEventTable);

        valuesForEventTable.clear();
        valuesForEventTable.put(TAC, TAC_2);
        valuesForEventTable.put(NO_OF_ERRORS, 23);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        insertRow(EVENT_E_RAN_CFA_TAC_15MIN, valuesForEventTable);

        valuesForEventTable.clear();
        valuesForEventTable.put(TAC, TAC_2);
        valuesForEventTable.put(NO_OF_ERRORS, 56);
        valuesForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        insertRow(EVENT_E_RAN_CFA_TAC_15MIN, valuesForEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(TAC);
        columns.add(NO_OF_ERRORS);
        columns.add(DATETIME_ID);
        createTemporaryTable(EVENT_E_RAN_CFA_TAC_DAY, columns);
        createTemporaryTable(EVENT_E_RAN_CFA_TAC_15MIN, columns);

        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(HIER3_ID);
        columnsForEventTable.add(NO_OF_ERRORS);
        columnsForEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_15MIN, columnsForEventTable);
    }
}
