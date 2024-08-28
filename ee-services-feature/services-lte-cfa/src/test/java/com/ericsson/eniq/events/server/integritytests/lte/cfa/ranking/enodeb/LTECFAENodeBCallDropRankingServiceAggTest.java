/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.lte.cfa.ranking.enodeb;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.ranking.LTECFAEnodeBCallDropRankingService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureEnodeBRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECFAENodeBCallDropRankingServiceAggTest extends BaseDataIntegrityTest<LTECallFailureEnodeBRankingResult> {
    private LTECFAEnodeBCallDropRankingService lteCallFailureeNodeBCallDropRankingService;

    private static final String TEST_VALUE_HIER3_ID_1 = "3135210477467174988";

    private static final String TEST_VALUE_ENODEB_NAME_1 = "eNodeB_1";

    private static final String TEST_VALUE_HIER3_ID_2 = "3135210477467174989";

    private static final String TEST_VALUE_ENODEB_NAME_2 = "eNodeB_2";

    private static final String TEST_VALUE_HIER3_ID_3 = "3135210477467174990";

    private static final String TEST_VALUE_ENODEB_NAME_3 = "eNodeB_3";

    @Before
    public void onSetUp() throws Exception {
        lteCallFailureeNodeBCallDropRankingService = new LTECFAEnodeBCallDropRankingService();
        attachDependencies(lteCallFailureeNodeBCallDropRankingService);

        createTables();
        insertData();
    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertData() throws SQLException {
        //1.First insert topology data
        insertTopoData(TEST_VALUE_HIER3_ID_1, TEST_VALUE_ENODEB_NAME_1);
        insertTopoData(TEST_VALUE_HIER3_ID_2, TEST_VALUE_ENODEB_NAME_2);
        insertTopoData(TEST_VALUE_HIER3_ID_3, TEST_VALUE_ENODEB_NAME_3);

        //2.Insert test data
        insertEnodeBWithDateTime(DateTimeUtilities.getDateTimeMinus55Minutes());
        insertEnodeBWithDateTime(DateTimeUtilities.getDateTimeMinus48Hours());
    }

    private void insertEnodeBWithDateTime(final String dateTime) throws SQLException {
        inserteNodeB(TEST_VALUE_HIER3_ID_1, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 3);
        inserteNodeB(TEST_VALUE_HIER3_ID_2, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 2);
        inserteNodeB(TEST_VALUE_HIER3_ID_3, dateTime, INTERNAL_PROC_UE_CTXT_RELEASE, LTE_CALL_DROP_CATEGORY_ID, 1);
    }

    private void insertTopoData(final String hier3ID, final String eNodeBName) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(HIER3_ID, hier3ID);
        valuesForTable.put(HIERARCHY_3, eNodeBName);
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
    private void inserteNodeB(final String hier3ID, final String dateTime, final String eventID,
            final String categoryID, final int instances) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        for (int i = 0; i < instances; i++) {
            valuesForTable.clear();
            valuesForTable.put(HIER3_ID, hier3ID);
            valuesForTable.put(EVENT_ID, Integer.valueOf(eventID));
            valuesForTable.put(CATEGORY_ID, Integer.valueOf(categoryID));
            valuesForTable.put(DATETIME_ID, dateTime);
            valuesForTable.put(NO_OF_ERRORS, 1);
            insertRow(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, valuesForTable);
            insertRow(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, valuesForTable);
        }
    }

    /**
     * @throws Exception 
     * 
     */
    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(CATEGORY_ID);
        columnsForEETable.add(DATETIME_ID);
        columnsForEETable.add(NO_OF_ERRORS);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_DAY, columnsForEETable);
        createTemporaryTable(TEMP_EVENT_E_LTE_CFA_HIER3_EVENTID_ERR_15MIN, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(HIER3_ID);
        columnsForEETable.add(HIERARCHY_3);
        columnsForEETable.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columnsForEETable);
    }

    @Test
    public void testGetLTECFAeNodeBCallDropRankingDataAgg15MIN() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_DAY);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureeNodeBCallDropRankingService, requestParameters);

        final ResultTranslator<LTECallFailureEnodeBRankingResult> rt = getTranslator();
        final List<LTECallFailureEnodeBRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureEnodeBRankingResult.class);
        assertResult(rankingResult);
    }

    @Test
    public void testGetLTECFAeNodeBCallDropRankingDataAggDAY() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TIME_QUERY_PARAM, TWO_WEEKS);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCallFailureeNodeBCallDropRankingService, requestParameters);

        final ResultTranslator<LTECallFailureEnodeBRankingResult> rt = getTranslator();
        final List<LTECallFailureEnodeBRankingResult> rankingResult = rt.translateResult(json,
                LTECallFailureEnodeBRankingResult.class);
        assertResult(rankingResult);
    }

    /**
     * @param rankingResult
     */
    private void assertResult(final List<LTECallFailureEnodeBRankingResult> rankingResult) {
        assertThat("There should be exactly 3 results!", rankingResult.size(), is(3));

        final LTECallFailureEnodeBRankingResult result1 = rankingResult.get(0);
        assertThat(result1.getRank(), is(1));
        assertThat(result1.getHier3ID(), is(TEST_VALUE_HIER3_ID_1));
        assertThat(result1.getHierarchy3(), is(TEST_VALUE_ENODEB_NAME_1));
        assertThat(result1.getVendor(), is(ERICSSON));
        assertThat(result1.getFailures(), is(3));

        final LTECallFailureEnodeBRankingResult result2 = rankingResult.get(1);
        assertThat(result2.getRank(), is(2));
        assertThat(result2.getHier3ID(), is(TEST_VALUE_HIER3_ID_2));
        assertThat(result2.getHierarchy3(), is(TEST_VALUE_ENODEB_NAME_2));
        assertThat(result2.getVendor(), is(ERICSSON));
        assertThat(result2.getFailures(), is(2));

        final LTECallFailureEnodeBRankingResult result3 = rankingResult.get(2);
        assertThat(result3.getRank(), is(3));
        assertThat(result3.getHier3ID(), is(TEST_VALUE_HIER3_ID_3));
        assertThat(result3.getHierarchy3(), is(TEST_VALUE_ENODEB_NAME_3));
        assertThat(result3.getVendor(), is(ERICSSON));
        assertThat(result3.getFailures(), is(1));
    }
}
