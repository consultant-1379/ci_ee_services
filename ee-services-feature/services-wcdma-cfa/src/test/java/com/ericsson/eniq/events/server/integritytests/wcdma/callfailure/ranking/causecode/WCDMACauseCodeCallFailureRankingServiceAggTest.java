/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.ranking.causecode;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.CallSetupCallFailureRankingService;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.ranking.CallsDroppedCallFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.CauseCodeCallFailureRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author etonayr
 * @since 2011
 *
 */
public class WCDMACauseCodeCallFailureRankingServiceAggTest extends
        BaseDataIntegrityTest<CauseCodeCallFailureRankingResult> {
    private CallSetupCallFailureRankingService causeCodeCallFailureService;

    private CallsDroppedCallFailureRankingService causeCodeCallDroppedFailureService;

    // Test Cause Codes
    private final static Map<String, Object> causeCodesTestMap = new HashMap<String, Object>();
    static {
        causeCodesTestMap.put("1", "REQUESTED_REQUEST_TYPE_NOT_SUPPORTED");
        causeCodesTestMap.put("2", "NODE_RESOURCE_NOT_AVAILABLE_1");
        causeCodesTestMap.put("3", "ASYNCHRONOUS_FORCED_CONNECTION_RELEASE");
        causeCodesTestMap.put("4", "NODE_INTERNAL_FAILURE_1");
        causeCodesTestMap.put("5", "CELL_UPDATE_FAILURE");
    }

    @Before
    public void onSetUp() throws Exception {
        causeCodeCallFailureService = new CallSetupCallFailureRankingService();
        causeCodeCallDroppedFailureService = new CallsDroppedCallFailureRankingService();
        attachDependencies(causeCodeCallFailureService);
        attachDependencies(causeCodeCallDroppedFailureService);
        createTables();
        insertDataIntoTacGroupTable();
        insertAggregationData();
    }

    @Test
    public void testGetData_CauseCode_CallDrop() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        final String json = runQuery(causeCodeCallDroppedFailureService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    @Test
    public void testGetData_CauseCode_CallFailure() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        final String json = runQuery(causeCodeCallDroppedFailureService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<CauseCodeCallFailureRankingResult> result = getTranslator().translateResult(json,
                CauseCodeCallFailureRankingResult.class);
        assertThat(result.size(), is(5));

        Collections.sort(result, new Comparator<CauseCodeCallFailureRankingResult>() {

            @Override
            public int compare(final CauseCodeCallFailureRankingResult o1, final CauseCodeCallFailureRankingResult o2) {
                final int id1 = Integer.parseInt(o1.getCauseCodeId());
                final int id2 = Integer.parseInt(o2.getCauseCodeId());
                if (id1 < id2) {
                    return -1;
                } else if (id1 > id2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        final CauseCodeCallFailureRankingResult resultForCauseCode1 = result.get(0);
        assertThat(resultForCauseCode1.getCauseCodeId(), is("1"));
        assertThat(resultForCauseCode1.getDescription(), is("REQUESTED_REQUEST_TYPE_NOT_SUPPORTED"));
        assertThat(resultForCauseCode1.getNumFailures(), is(1));

        final CauseCodeCallFailureRankingResult resultForCauseCode2 = result.get(1);
        assertThat(resultForCauseCode2.getCauseCodeId(), is("2"));
        assertThat(resultForCauseCode2.getDescription(), is("NODE_RESOURCE_NOT_AVAILABLE_1"));
        assertThat(resultForCauseCode2.getNumFailures(), is(1));

        final CauseCodeCallFailureRankingResult resultForCauseCode3 = result.get(2);
        assertThat(resultForCauseCode3.getCauseCodeId(), is("3"));
        assertThat(resultForCauseCode3.getDescription(), is("ASYNCHRONOUS_FORCED_CONNECTION_RELEASE"));
        assertThat(resultForCauseCode3.getNumFailures(), is(1));

        final CauseCodeCallFailureRankingResult resultForCauseCode4 = result.get(3);
        assertThat(resultForCauseCode4.getCauseCodeId(), is("4"));
        assertThat(resultForCauseCode4.getDescription(), is("NODE_INTERNAL_FAILURE_1"));
        assertThat(resultForCauseCode4.getNumFailures(), is(1));

        final CauseCodeCallFailureRankingResult resultForCauseCode5 = result.get(4);
        assertThat(resultForCauseCode5.getCauseCodeId(), is("5"));
        assertThat(resultForCauseCode5.getDescription(), is("CELL_UPDATE_FAILURE"));
        assertThat(resultForCauseCode5.getNumFailures(), is(1));
    }

    private void insertAggregationData() throws SQLException {
        for (int i = 1; i < 6; i++) {
            final Map<String, Object> valuesForCCTable = new HashMap<String, Object>();
            valuesForCCTable.put(CAUSE_VALUE_COLUMN, i);
            valuesForCCTable.put(CAUSE_VALUE_DESC_COLUMN, causeCodesTestMap.get("" + i));
            insertRow(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE, valuesForCCTable);
        }

        final Integer SETUP_EVT_ID = new Integer(CALL_SETUP_FAILURE_EVENT_ID);
        for (int i = 1; i < 6; i++) {
            final Map<String, Object> valuesForCauseCodeCFATable = new HashMap<String, Object>();
            valuesForCauseCodeCFATable.put(EVENT_ID, SETUP_EVT_ID);
            valuesForCauseCodeCFATable.put(CAUSE_VALUE_COLUMN, i);
            valuesForCauseCodeCFATable.put(TAC, SAMPLE_TAC);
            valuesForCauseCodeCFATable.put(NO_OF_ERRORS, 1);
            final String timestamp = DateTimeUtilities.getDateTimeMinusDay(5);
            valuesForCauseCodeCFATable.put(DATETIME_ID, timestamp);
            insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_CV_ECV_ERR_DAY, valuesForCauseCodeCFATable);
        }

        final Integer DROP_EVT_ID = new Integer(CALL_DROP_EVENT_ID);

        for (int i = 1; i < 6; i++) {
            final Map<String, Object> valuesForCauseCodeCFATable = new HashMap<String, Object>();
            valuesForCauseCodeCFATable.put(EVENT_ID, DROP_EVT_ID);
            valuesForCauseCodeCFATable.put(CAUSE_VALUE_COLUMN, i);
            valuesForCauseCodeCFATable.put(TAC, SAMPLE_TAC);
            valuesForCauseCodeCFATable.put(NO_OF_ERRORS, 1);
            final String timestamp = DateTimeUtilities.getDateTimeMinusDay(5);
            valuesForCauseCodeCFATable.put(DATETIME_ID, timestamp);
            insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_CV_ECV_ERR_DAY, valuesForCauseCodeCFATable);
        }
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsForDIMECauseCodeTable = new ArrayList<String>();
        columnsForDIMECauseCodeTable.add(CAUSE_VALUE_COLUMN);
        columnsForDIMECauseCodeTable.add(CAUSE_VALUE_DESC_COLUMN);
        createTemporaryTable(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE, columnsForDIMECauseCodeTable);

        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(CAUSE_VALUE_COLUMN);
        columnsForEventTable.add(NO_OF_ERRORS);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(EVENT_ID);
        columnsForEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_EVENTID_CV_ECV_ERR_DAY, columnsForEventTable);
    }
}
