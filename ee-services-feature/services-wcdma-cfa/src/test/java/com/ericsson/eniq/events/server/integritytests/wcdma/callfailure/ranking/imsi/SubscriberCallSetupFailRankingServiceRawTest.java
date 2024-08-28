/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.ranking.imsi;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.SubscriberCallSetupFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.SubscriberCallSetupFailureRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_GROUP_TYPE_E_TAC;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eflatib
 *
 */
public class SubscriberCallSetupFailRankingServiceRawTest extends
        BaseDataIntegrityTest<SubscriberCallSetupFailureRankingResult> {

    private SubscriberCallSetupFailureRankingService subscriberCallSetupFailureRankingService;

    private static final int TEST_IMSI_0 = 0;

    private static final int TEST_IMSI_1 = 11111111;

    private static final int TEST_IMSI_2 = 22222222;

    private static final int TEST_IMSI_3 = 33333333;
    
    private static final String TEST_TAC = "1280600"; //belongs to: Apple,iPad 2 A1396

    @Before
    public void onSetUp() throws Exception {
        subscriberCallSetupFailureRankingService = new SubscriberCallSetupFailureRankingService();
        attachDependencies(subscriberCallSetupFailureRankingService);
        createTables();
        insertData();
    }

    /*
     * The expected outcome is for IMSI_1 to have 3 Failures while IMSI_2 will have only 2.
     * They should be ranked accordingly.
     */
    @Test
    public void testGetRankingData_SUBSCRIBER_CFA() throws Exception {

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, "grid");
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(MAX_ROWS, "10");

        final String json = runQuery(subscriberCallSetupFailureRankingService, requestParameters);

        System.out.println(json);

        final ResultTranslator<SubscriberCallSetupFailureRankingResult> rt = getTranslator();
        final List<SubscriberCallSetupFailureRankingResult> rankingResult = rt.translateResult(json,
                SubscriberCallSetupFailureRankingResult.class);

        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final SubscriberCallSetupFailureRankingResult worstSubscriber = rankingResult.get(0);
        assertThat(worstSubscriber.getRank(), is(1));
        assertThat(worstSubscriber.getImsi(), is(TEST_IMSI_1));
        assertThat("worstSubscriber should have exactly 3 CallSetupFail events!", worstSubscriber.getNumFailures(),
                is(3));

        final SubscriberCallSetupFailureRankingResult nextWorstSubscriber = rankingResult.get(1);
        assertThat(nextWorstSubscriber.getRank(), is(2));
        assertThat(nextWorstSubscriber.getImsi(), is(TEST_IMSI_2));
        assertThat("nextWorstSubscriber should have exactly 2 CallSetupFail events!",
                nextWorstSubscriber.getNumFailures(), is(2));

    }

    private void createTables() throws Exception {

        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(IMSI);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEETable);

    }

    /*
     * Control data:
     *      4 CallDrop events for each IMSI (to make sure we count the right type of event)
     *      1 CallSetupFailures for IMSI_3  (this is only to verify that the correct IMSIs are counted)
     *      
     * Relevant data:
     *      3 CallSetupFailures for IMSI_1
     *      2 CallSetupFailures for IMSI_2
     *      
     * +1 exclusive TAC as control data 
     */
    private void insertData() throws Exception {

        final String dateTime = DateTimeUtilities.getDateTimeMinus2Minutes();

        //This shouldn't appear in the results as IMSI 0 should be excluded.
        insertIMSI(TEST_IMSI_0, dateTime, CALL_SETUP_FAILURE_EVENT_ID, 3);
        insertIMSI(TEST_IMSI_0, dateTime, CALL_DROP_EVENT_ID, 4);

        insertIMSI(TEST_IMSI_1, dateTime, CALL_SETUP_FAILURE_EVENT_ID, 3);
        insertIMSI(TEST_IMSI_1, dateTime, CALL_DROP_EVENT_ID, 4);

        insertIMSI(TEST_IMSI_2, dateTime, CALL_SETUP_FAILURE_EVENT_ID, 2);
        insertIMSI(TEST_IMSI_2, dateTime, CALL_DROP_EVENT_ID, 4);

        insertIMSI(TEST_IMSI_3, dateTime, CALL_SETUP_FAILURE_EVENT_ID, 1);
        insertIMSI(TEST_IMSI_3, dateTime, CALL_DROP_EVENT_ID, 4);
        
        // Insert exclusive TAC to RAW table
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI, 22558855);
        valuesForTable.put(EVENT_ID, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);
        valuesForTable.put(DATETIME_ID, dateTime);
        valuesForTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);

        // Insert exclusive TAC to #GROUP_TYPE_E_TAC
        final Map<String, Object> valuesForGTable = new HashMap<String, Object>();
        valuesForGTable.put(GROUP_NAME, "EXCLUSIVE_TAC");
        valuesForGTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForGTable);
        
    }

    private void insertIMSI(final int imsi, final String dt, final String eventID, final int instances)
            throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(IMSI, imsi);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(DATETIME_ID, dt);
            valuesForTable.put(TAC, TEST_TAC);
            insertRow(TEMP_EVENT_E_RAN_CFA_RAW, valuesForTable);
        }
    }
}
