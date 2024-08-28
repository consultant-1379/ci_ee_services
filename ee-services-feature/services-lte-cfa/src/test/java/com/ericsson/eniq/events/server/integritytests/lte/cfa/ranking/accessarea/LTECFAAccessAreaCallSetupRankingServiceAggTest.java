/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.accessarea;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFAAccessAreaCallSetupRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureAccessAreaRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFAAccessAreaCallSetupRankingServiceAggTest extends
        BaseDataIntegrityTest<LTECallFailureAccessAreaRankingResult> {
    private LTECFAAccessAreaCallSetupRankingService lteCallFailureAccessAreaCallSetupFailureRankingService;

    private static final String TEST_VALUE_HIER3_ID_1 = "3135210477467174988";

    private static final String TEST_VALUE_HIER321_ID_1 = "7210756712490856541";

    private static final String TEST_VALUE_ENODEB_NAME_1 = "eNodeB_1";

    private static final String TEST_VALUE_ECELL_NAME_1 = "eCell_1";

    private static final String TEST_VALUE_HIER3_ID_2 = "3135210477467174989";

    private static final String TEST_VALUE_HIER321_ID_2 = "7210756712490856542";

    private static final String TEST_VALUE_ENODEB_NAME_2 = "eNodeB_2";

    private static final String TEST_VALUE_ECELL_NAME_2 = "eCell_2";

    private static final String TEST_VALUE_HIER3_ID_3 = "3135210477467174990";

    private static final String TEST_VALUE_HIER321_ID_3 = "7210756712490856543";

    private static final String TEST_VALUE_ENODEB_NAME_3 = "eNodeB_3";

    private static final String TEST_VALUE_ECELL_NAME_3 = "eCell_3";

    @Before
    public void onSetUp() throws Exception {
        lteCallFailureAccessAreaCallSetupFailureRankingService = new LTECFAAccessAreaCallSetupRankingService();
        attachDependencies(lteCallFailureAccessAreaCallSetupFailureRankingService);

        createTables();
        insertData();
    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertData() throws SQLException {
        //1.First insert topology data
        insertTopoData(TEST_VALUE_HIER3_ID_1, TEST_VALUE_HIER321_ID_1, TEST_VALUE_ENODEB_NAME_1,
                TEST_VALUE_ECELL_NAME_1);
        insertTopoData(TEST_VALUE_HIER3_ID_2, TEST_VALUE_HIER321_ID_2, TEST_VALUE_ENODEB_NAME_2,
                TEST_VALUE_ECELL_NAME_2);
        insertTopoData(TEST_VALUE_HIER3_ID_3, TEST_VALUE_HIER321_ID_3, TEST_VALUE_ENODEB_NAME_3,
                TEST_VALUE_ECELL_NAME_3);

        //2.Insert test data
        insertCellDatasWithDateTime(DateTimeUtilities.getDateTimeMinus55Minutes());
        insertCellDatasWithDateTime(DateTimeUtilities.getDateTimeMinus48Hours());
    }

    private void insertCellDatasWithDateTime(final String dateTime) throws SQLException {
        inserteCell(TEST_VALUE_HIER3_ID_1, TEST_VALUE_HIER321_ID_1, dateTime, INTERNAL_PROC_RRC_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 3);
        inserteCell(TEST_VALUE_HIER3_ID_1, TEST_VALUE_HIER321_ID_1, dateTime, INTERNAL_PROC_S1_SIG_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 3);
        inserteCell(TEST_VALUE_HIER3_ID_1, TEST_VALUE_HIER321_ID_1, dateTime, INTERNAL_PROC_INITIAL_CTXT_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 3);
        inserteCell(TEST_VALUE_HIER3_ID_1, TEST_VALUE_HIER321_ID_1, dateTime, INTERNAL_PROC_ERAB_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 3);

        inserteCell(TEST_VALUE_HIER3_ID_2, TEST_VALUE_HIER321_ID_2, dateTime, INTERNAL_PROC_RRC_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 2);
        inserteCell(TEST_VALUE_HIER3_ID_2, TEST_VALUE_HIER321_ID_2, dateTime, INTERNAL_PROC_S1_SIG_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 2);
        inserteCell(TEST_VALUE_HIER3_ID_2, TEST_VALUE_HIER321_ID_2, dateTime, INTERNAL_PROC_INITIAL_CTXT_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 2);
        inserteCell(TEST_VALUE_HIER3_ID_2, TEST_VALUE_HIER321_ID_2, dateTime, INTERNAL_PROC_ERAB_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 2);

        inserteCell(TEST_VALUE_HIER3_ID_3, TEST_VALUE_HIER321_ID_3, dateTime, INTERNAL_PROC_RRC_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 1);
        inserteCell(TEST_VALUE_HIER3_ID_3, TEST_VALUE_HIER321_ID_3, dateTime, INTERNAL_PROC_S1_SIG_CONN_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 1);
        inserteCell(TEST_VALUE_HIER3_ID_3, TEST_VALUE_HIER321_ID_3, dateTime, INTERNAL_PROC_INITIAL_CTXT_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 1);
        inserteCell(TEST_VALUE_HIER3_ID_3, TEST_VALUE_HIER321_ID_3, dateTime, INTERNAL_PROC_ERAB_SETUP,
                LTE_CALL_SETUP_FAILURE_CATEGORY_ID, 1);

    }

    private void insertTopoData(final String hier3ID, final String hier321ID, final String eNodeBName,
            final String eCellName) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_ID, hier3ID);
        valuesForTable.put(HIER321_ID, hier321ID);
        valuesForTable.put(HIERARCHY_3, eNodeBName);
        valuesForTable.put(HIERARCHY_1, eCellName);
        valuesForTable.put(VENDOR, ERICSSON);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
    }

    /**
     * @param tEST_VALUE_HIER3_ID_12
     * @param dateTime
     * @param internalProcUeCtxtRelease
     * @param i
     * @throws SQLException 
     */
    private void inserteCell(final String hier3ID, final String hier321ID, final String dateTime, final String eventID,
            final String categoryID, final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        for (int i = 0; i < instances; i++) {
            valuesForTable.clear();
            valuesForTable.put(HIER3_ID, hier3ID);
            valuesForTable.put(HIER321_ID, hier321ID);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(CATEGORY_ID, Integer.valueOf(categoryID));
            valuesForTable.put(DATETIME_ID, dateTime);
            valuesForTable.put(NO_OF_ERRORS, 1);
            insertRow(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_15MIN, valuesForTable);
            insertRow(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_DAY, valuesForTable);
        }
    }

    /**
     * @throws Exception 
     * 
     */
    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(HIER321_ID);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(NO_OF_ERRORS);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_15MIN, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(HIER321_ID);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(NO_OF_ERRORS);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER321_EVENTID_ERR_DAY, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(HIER321_ID);
        columnsForEETable.add(HIERARCHY_3);
        columnsForEETable.add(HIERARCHY_1);
        columnsForEETable.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForEETable);
    }

    @Test
    public void testGetLTECFAeCellCallSetupFailureRankingDataAgg15MIN() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_DAY);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureAccessAreaCallSetupFailureRankingService, requestParameters);

        final ResultTranslator<LTECallFailureAccessAreaRankingResult> rt = getTranslator();
        final List<LTECallFailureAccessAreaRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureAccessAreaRankingResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testGetLTECFAeCellCallSetupFailureRankingDataAggDAY() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, TWO_WEEKS);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureAccessAreaCallSetupFailureRankingService, requestParameters);

        final ResultTranslator<LTECallFailureAccessAreaRankingResult> rt = getTranslator();
        final List<LTECallFailureAccessAreaRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureAccessAreaRankingResult.class);
        assertResult(rankingResult);
    }

    /**
     * @param rankingResult
     */
    private void assertResult(final List<LTECallFailureAccessAreaRankingResult> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final LTECallFailureAccessAreaRankingResult result1 = rankingResult.get(0);
        assertThat(result1.getRank(), is(1));
        assertThat(result1.getHier3ID(), is(TEST_VALUE_HIER3_ID_1));
        assertThat(result1.getHier321ID(), is(TEST_VALUE_HIER321_ID_1));
        assertThat(result1.getHierarchy3(), is(TEST_VALUE_ENODEB_NAME_1));
        assertThat(result1.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_1));
        assertThat(result1.getVendor(), is(ERICSSON));
        assertThat(result1.getFailures(), is(12));

        final LTECallFailureAccessAreaRankingResult result2 = rankingResult.get(1);
        assertThat(result2.getRank(), is(2));
        assertThat(result2.getHier3ID(), is(TEST_VALUE_HIER3_ID_2));
        assertThat(result2.getHier321ID(), is(TEST_VALUE_HIER321_ID_2));
        assertThat(result2.getHierarchy3(), is(TEST_VALUE_ENODEB_NAME_2));
        assertThat(result2.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_2));
        assertThat(result2.getVendor(), is(ERICSSON));
        assertThat(result2.getFailures(), is(8));

        final LTECallFailureAccessAreaRankingResult result3 = rankingResult.get(2);
        assertThat(result3.getRank(), is(3));
        assertThat(result3.getHier3ID(), is(TEST_VALUE_HIER3_ID_3));
        assertThat(result3.getHier321ID(), is(TEST_VALUE_HIER321_ID_3));
        assertThat(result3.getHierarchy3(), is(TEST_VALUE_ENODEB_NAME_3));
        assertThat(result3.getHierarchy1(), is(TEST_VALUE_ECELL_NAME_3));
        assertThat(result3.getVendor(), is(ERICSSON));
        assertThat(result3.getFailures(), is(4));
    }
}
