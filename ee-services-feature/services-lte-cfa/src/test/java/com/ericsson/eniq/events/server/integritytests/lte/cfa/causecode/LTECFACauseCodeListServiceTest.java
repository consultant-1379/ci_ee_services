package com.ericsson.eniq.events.server.integritytests.lte.cfa.causecode;

/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

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

import com.ericsson.eniq.events.server.common.EventIDConstants;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.causecodeanalysis.LTECFACauseCodeListService;
import com.ericsson.eniq.events.server.test.queryresults.ResultTranslator;
import com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.LTECallFailureCauseCodeSummaryListResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author emohasu
 * @since 2012
 *
 */
public class LTECFACauseCodeListServiceTest extends BaseDataIntegrityTest<LTECallFailureCauseCodeSummaryListResult> {

    private LTECFACauseCodeListService lteCFACauseCodeListService;

    private static final String TEST_VALUE_CAUSE_CODE_ID_1 = "10";

    private static final String TEST_VALUE_CAUSE_CODE_ID_2 = "11";

    private static final String TEST_VALUE_CAUSE_CODE_ID_3 = "12";

    private static final String TEST_VALUE_CAUSE_CODE_ID_4 = "13";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_1 = "Cause Code 1";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_2 = "Cause Code 2";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_3 = "Cause Code 3";

    private static final String TEST_VALUE_CAUSE_CODE_DESC_4 = "Cause Code 4";

    @Before
    public void setup() throws Exception {
        lteCFACauseCodeListService = new LTECFACauseCodeListService();
        attachDependenciesForSimpleService(lteCFACauseCodeListService);

        createTables();
        insertData();
    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertData() throws SQLException {
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_1, TEST_VALUE_CAUSE_CODE_DESC_1,
                EventIDConstants.INTERNAL_PROC_RRC_CONN_SETUP);
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_2, TEST_VALUE_CAUSE_CODE_DESC_2,
                EventIDConstants.INTERNAL_PROC_S1_SIG_CONN_SETUP);
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_3, TEST_VALUE_CAUSE_CODE_DESC_3,
                EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP);
        insertTopoData(TEST_VALUE_CAUSE_CODE_ID_4, TEST_VALUE_CAUSE_CODE_DESC_4,
                EventIDConstants.INTERNAL_PROC_ERAB_SETUP);

        insertTopoData(EventIDConstants.INTERNAL_PROC_RRC_CONN_SETUP, "INTERNAL_PROC_RRC_CONN_SETUP");
        insertTopoData(EventIDConstants.INTERNAL_PROC_S1_SIG_CONN_SETUP, "INTERNAL_PROC_S1_SIG_CONN_SETUP");
        insertTopoData(EventIDConstants.INTERNAL_PROC_INITIAL_CTXT_SETUP, "INTERNAL_PROC_INITIAL_CTXT_SETUP");
        insertTopoData(EventIDConstants.INTERNAL_PROC_ERAB_SETUP, "INTERNAL_PROC_ERAB_SETUP");

    }

    /**
     * @throws SQLException 
     * 
     */
    private void insertTopoData(final String causeCodeID, final String causeCodeDesc, final String eventID)
            throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(CAUSE_CODE_COLUMN, causeCodeID);
        valuesForTable.put(CAUSE_CODE_DESC_COLUMN, causeCodeDesc);
        valuesForTable.put(EVENT_ID, eventID);
        insertRow(TEMP_DIM_E_LTE_CFA_CAUSE_CODE, valuesForTable);
    }

    private void insertTopoData(final String eventID, final String eventTypeDesc) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(EVENT_ID, eventID);
        valuesForTable.put(EVENT_ID_DESC, eventTypeDesc);
        insertRow(TEMP_DIM_E_LTE_CFA_EVENTTYPE, valuesForTable);
    }

    /**
     * @throws Exception 
     * 
     */
    private void createTables() throws Exception {
        final Collection<String> columnsForEETable = new ArrayList<String>();

        columnsForEETable.clear();
        columnsForEETable.add(CAUSE_CODE_COLUMN);
        columnsForEETable.add(CAUSE_CODE_DESC_COLUMN);
        columnsForEETable.add(EVENT_ID);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_CAUSE_CODE, columnsForEETable);

        columnsForEETable.clear();
        columnsForEETable.add(EVENT_ID);
        columnsForEETable.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_LTE_CFA_EVENTTYPE, columnsForEETable);

    }

    @Test
    public void testGetLTECFACauseCodeList() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(MAX_ROWS, DEFAULT_MAX_ROWS);

        final String json = runQuery(lteCFACauseCodeListService, requestParameters);

        final ResultTranslator<LTECallFailureCauseCodeSummaryListResult> rt = getTranslator();
        final List<LTECallFailureCauseCodeSummaryListResult> causeCodeListResult = rt.translateResult(json,
                LTECallFailureCauseCodeSummaryListResult.class);
        assertResult(causeCodeListResult);
    }

    private void assertResult(final List<LTECallFailureCauseCodeSummaryListResult> causeCodeListResult) {
        assertThat("There should be exactly 4 results!", causeCodeListResult.size(), is(4));

        final LTECallFailureCauseCodeSummaryListResult result1 = causeCodeListResult.get(0);

        assertThat(result1.getEventTypeDesc(), is("INTERNAL_PROC_RRC_CONN_SETUP"));
        assertThat(result1.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_1));
        assertThat(result1.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_1));

        final LTECallFailureCauseCodeSummaryListResult result2 = causeCodeListResult.get(1);
        assertThat(result2.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_2));
        assertThat(result2.getEventTypeDesc(), is("INTERNAL_PROC_S1_SIG_CONN_SETUP"));
        assertThat(result2.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_2));

        final LTECallFailureCauseCodeSummaryListResult result3 = causeCodeListResult.get(2);
        assertThat(result3.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_3));
        assertThat(result3.getEventTypeDesc(), is("INTERNAL_PROC_INITIAL_CTXT_SETUP"));
        assertThat(result3.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_3));

        final LTECallFailureCauseCodeSummaryListResult result4 = causeCodeListResult.get(3);
        assertThat(result4.getCauseCodeID(), is(TEST_VALUE_CAUSE_CODE_ID_4));
        assertThat(result4.getEventTypeDesc(), is("INTERNAL_PROC_ERAB_SETUP"));
        assertThat(result4.getCauseCodeDesc(), is(TEST_VALUE_CAUSE_CODE_DESC_4));

    }
}
