/**
 * 
 */
package com.ericsson.eniq.events.server.integritytests.lte.hfa.eventvolume.enodeb;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EXCLUSIVE_TAC_GROUP_NAME;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_S1_OUT_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_EXEC_X2_OUT_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_S1_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_S1_OUT_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_X2_IN_HFA;
import static com.ericsson.eniq.events.server.common.EventIDConstants.INTERNAL_PROC_HO_PREP_X2_OUT_HFA;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DEFAULT_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GRID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GROUP_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.NO_OF_ERRORS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_EXCLUSIVE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_HFA_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_GROUP_TYPE_E_TAC;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.eventvolume.LTEHFAEnodeBEventVolumeService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.LTEHFAEventVolumeResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author evijred
 *
 */
public class LTEHFAEnodeBEventVolServiceAggTest extends BaseDataIntegrityTest<LTEHFAEventVolumeResult> {

    private LTEHFAEnodeBEventVolumeService lteHFAEnodeBEventVolumeService;

    private static final String DATETIME_ID_DAY_1 = "2011-09-19 08:15:00";

    private static final String DATETIME_ID_DAY_2 = "2011-09-18 08:15:00";

    private static final String DATE_FROM_DAY = "13092011";

    private static final String DATE_TO_DAY = "20092011";

    private static final String DATETIME_ID_15MIN_1 = "2011-09-20 08:15:00";

    private static final String DATETIME_ID_15MIN_2 = "2011-09-20 12:15:00";

    private static final String DATE_FROM_15MIN = "20092011";

    private static final String DATE_TO_15MIN = "20092011";

    private static final String TIME_FROM = "0900";

    private static final String TIME_TO = "1530";

    private static final long TEST_VALUE_HIER3_ID = 3135210477467174988L;

    private static final String TEST_VALUE_NODE_PARAM = "ONRM_RootMo_R:LTE01ERBS00001,Ericsson,LTE";

    private static final String TEST_VALUE_IMSI_1 = "11111119";

    /**
     * 1. Create tables.
     * 2. Insert test datas to the tables.
     * @throws Exception
     */
    @Before
    public void onSetUp() throws Exception {
        lteHFAEnodeBEventVolumeService = new LTEHFAEnodeBEventVolumeService();
        attachDependencies(lteHFAEnodeBEventVolumeService);
        createTable();
        insertTopoData();
    }

    private void createTable() throws Exception {
        final Collection<String> columnsForTable = new ArrayList<String>();
        columnsForTable.add(IMSI);
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(TAC);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_ERR_RAW, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, columnsForTable);

        columnsForTable.clear();
        columnsForTable.add(HIER3_ID);
        columnsForTable.add(NO_OF_ERRORS);
        columnsForTable.add(EVENT_ID);
        columnsForTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, columnsForTable);
    }

    private void insertTopoData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.clear();
        valuesForTable.put(TAC, Integer.valueOf(TEST_VALUE_EXCLUSIVE_TAC));
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void insertEventVolData(final String aggTable, final String eventID, final String time,
            final int noOfErrors, final int instances) throws SQLException {
        for (int i = 0; i < instances; i++) {
            final Map<String, Object> valuesForTable = new HashMap<String, Object>();
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID);
            valuesForTable.put(NO_OF_ERRORS, noOfErrors);
            valuesForTable.put(DATETIME_ID, time);
            insertRow(aggTable, valuesForTable);

            valuesForTable.clear();
            valuesForTable.put(DATETIME_ID, time);
            valuesForTable.put(EVENT_ID, eventID);
            valuesForTable.put(IMSI, TEST_VALUE_IMSI_1);
            valuesForTable.put(TAC, TEST_VALUE_TAC);
            valuesForTable.put(HIER3_ID, TEST_VALUE_HIER3_ID);
            insertRow(TEMP_EVENT_E_LTE_HFA_ERR_RAW, valuesForTable);
        }
    }

    private String getJsonResultDay() throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_DAY);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_DAY);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_NODE_PARAM);
        return runQuery(lteHFAEnodeBEventVolumeService, requestParameters);
    }

    private String getJsonResult15Min() throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, DATE_FROM_15MIN);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, DATE_TO_15MIN);
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, TIME_FROM);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, TIME_TO);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(NODE_PARAM, TEST_VALUE_NODE_PARAM);
        return runQuery(lteHFAEnodeBEventVolumeService, requestParameters);
    }

    private void insertAggDataDay() throws Exception {

        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_X2_IN_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_X2_OUT_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_S1_IN_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_S1_OUT_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_X2_IN_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_S1_IN_HFA,
                DATETIME_ID_DAY_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA,
                DATETIME_ID_DAY_1, 1, 20);

        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_X2_IN_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_X2_OUT_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_S1_IN_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_PREP_S1_OUT_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_X2_IN_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_S1_IN_HFA,
                DATETIME_ID_DAY_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_DAY, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA,
                DATETIME_ID_DAY_2, 1, 20);
    }

    private void insertAggData15Min() throws Exception {

        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_X2_IN_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_X2_OUT_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_S1_IN_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_S1_OUT_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_X2_IN_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_S1_IN_HFA,
                DATETIME_ID_15MIN_1, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA,
                DATETIME_ID_15MIN_1, 1, 20);

        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_X2_IN_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_X2_OUT_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_S1_IN_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_PREP_S1_OUT_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_X2_IN_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_X2_OUT_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_S1_IN_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
        insertEventVolData(TEMP_EVENT_E_LTE_HFA_HIER3_EVENTID_ERR_15MIN, INTERNAL_PROC_HO_EXEC_S1_OUT_HFA,
                DATETIME_ID_15MIN_2, 1, 20);
    }

    @Test
    public void testEnodeBEventVolumeDay() throws Exception {
        insertAggDataDay();
        final String json = getJsonResultDay();
        final ResultTranslator<LTEHFAEventVolumeResult> rt = getTranslator();
        final List<LTEHFAEventVolumeResult> eventResult = rt.translateResult(json, LTEHFAEventVolumeResult.class);
        assertResult(eventResult);
    }

    @Test
    public void testEnodeBEventVolume15min() throws Exception {
        insertAggData15Min();
        final String json = getJsonResult15Min();
        final ResultTranslator<LTEHFAEventVolumeResult> rt = getTranslator();
        final List<LTEHFAEventVolumeResult> eventResult = rt.translateResult(json, LTEHFAEventVolumeResult.class);
        assertResult(eventResult);
    }

    private void assertResult(final List<LTEHFAEventVolumeResult> results) {
        assertThat(results.size(), is(2));
        for (final LTEHFAEventVolumeResult rs : results) {
            assertThat(rs.getINTERNAL_PROC_HO_PREP_X2_IN_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_PREP_X2_OUT_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_EXEC_X2_IN_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_EXEC_X2_OUT_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_PREP_S1_IN_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_PREP_S1_OUT_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_EXEC_S1_IN_FailCount(), is(20));
            assertThat(rs.getINTERNAL_PROC_HO_EXEC_S1_OUT_FailCount(), is(20));
            assertThat(rs.getImpactedSubscribers(), is(1));
        }
    }
}
