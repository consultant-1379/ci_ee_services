/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.disconnection;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.disconnectionanalysis.RNCByDisconChartResource;
import com.ericsson.eniq.events.server.test.common.ApplicationTestConstants;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.DisconnectionChartResourceResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eadrhyn
 *
 */
public class RNCByDisconChartResourceTest extends BaseDataIntegrityTest<DisconnectionChartResourceResult> {

    private RNCByDisconChartResource controllerByDisconnection;

    private long hashedIdForRNC;

    String node = RNC01_ALTERNATIVE_FDN + "," + ERICSSON + "," + _3G;

    @Before
    public void setup() throws Exception {
        controllerByDisconnection = new RNCByDisconChartResource();
        attachDependencies(controllerByDisconnection);
        hashedIdForRNC = calculateHashedId(_3G, RNC01_ALTERNATIVE_FDN, ERICSSON);

        createTables();
        createTemporaryDIMTables();
        insertDataIntoTopologyTables();
        insertData();
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForDIMRNCTable = new ArrayList<String>();
        columnsForDIMRNCTable.add(HIER3_ID);
        columnsForDIMRNCTable.add(HIERARCHY_3);
        columnsForDIMRNCTable.add(VENDOR);
        columnsForDIMRNCTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, columnsForDIMRNCTable);

        final Collection<String> columnsForDIMDisconnectionTable = new ArrayList<String>();
        columnsForDIMDisconnectionTable
                .add(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DISCONNECTION_CODE);
        columnsForDIMDisconnectionTable.add(ApplicationTestConstants.DISCONNECTION_SUB_CODE);
        columnsForDIMDisconnectionTable.add(DISCONNECTION_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_CFA_DISCONNECTION, columnsForDIMDisconnectionTable);

    }

    private void insertDataIntoTopologyTables() throws SQLException {
        final Map<String, Object> valuesForRncTable = new HashMap<String, Object>();
        valuesForRncTable.put(HIER3_ID, hashedIdForRNC);
        valuesForRncTable.put(VENDOR, ERICSSON);
        valuesForRncTable.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForRncTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321, valuesForRncTable);

        final Map<String, Object> valuesForDisconnectionCodeRncTableRow1 = new HashMap<String, Object>();
        valuesForDisconnectionCodeRncTableRow1.put(
                com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DISCONNECTION_CODE, 1);
        valuesForDisconnectionCodeRncTableRow1.put(ApplicationTestConstants.DISCONNECTION_SUB_CODE, 1);
        valuesForDisconnectionCodeRncTableRow1.put(DISCONNECTION_DESC, "Radio Connection Supervision (RCS)");
        insertRow(TEMP_DIM_E_RAN_CFA_DISCONNECTION, valuesForDisconnectionCodeRncTableRow1);

        final Map<String, Object> valuesForDisconnectionCodeRncTableRow2 = new HashMap<String, Object>();
        valuesForDisconnectionCodeRncTableRow2.put(
                com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DISCONNECTION_CODE, 2);
        valuesForDisconnectionCodeRncTableRow2.put(ApplicationTestConstants.DISCONNECTION_SUB_CODE, 1);
        valuesForDisconnectionCodeRncTableRow2.put(DISCONNECTION_DESC, "Congestion");
        insertRow(TEMP_DIM_E_RAN_CFA_DISCONNECTION, valuesForDisconnectionCodeRncTableRow2);

        final Map<String, Object> valuesForDisconnectionCodeRncTableRow3 = new HashMap<String, Object>();
        valuesForDisconnectionCodeRncTableRow3.put(
                com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DISCONNECTION_CODE, 3);
        valuesForDisconnectionCodeRncTableRow3.put(ApplicationTestConstants.DISCONNECTION_SUB_CODE, 1);
        valuesForDisconnectionCodeRncTableRow3.put(DISCONNECTION_DESC, "Measurement control failure");
        insertRow(TEMP_DIM_E_RAN_CFA_DISCONNECTION, valuesForDisconnectionCodeRncTableRow3);

    }

    private void insertData() throws SQLException {
        final String timestamp1 = DateTimeUtilities.getDateTimeMinus30Minutes();
        final String timestamp2 = DateTimeUtilities.getDateTimeMinus35Minutes();

        final String timestamp3 = DateTimeUtilities.getDateTimeMinusDay(7);

        final int RCSDisConCode = 1;
        final int CongestionDisConCode = 2;
        final int disConSubCode1 = 1;
        final int unknownDisConCode1 = 4;
        final int unknownDisConCode2 = 6;
        final int ps_rab_failed_cnt = 1;
        final int cs_rab_failed_cnt = 1;
        final int multi_rab_failed_cnt = 1;
        final int ps_err_cnt = 1;
        final int cs_err_cnt = 1;
        final int multi_err_cnt = 1;

        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1, RCSDisConCode, disConSubCode1,
                ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt, multi_err_cnt);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1, RCSDisConCode, disConSubCode1,
                ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt, multi_err_cnt);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, CongestionDisConCode,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);

        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, unknownDisConCode1,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, unknownDisConCode2,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);

        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp2, RCSDisConCode,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);

        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1, RCSDisConCode,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, CongestionDisConCode,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp2,
                RCSDisConCode, disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt,
                cs_err_cnt, multi_err_cnt);

        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp3, RCSDisConCode,
                disConSubCode1, ps_rab_failed_cnt, cs_rab_failed_cnt, multi_rab_failed_cnt, ps_err_cnt, cs_err_cnt,
                multi_err_cnt);

    }

    private void insertRowIntoEventTable(final int eventId, final int tac, final String timestamp,
            final int disConCode, final int disConSubCode, final int ps_rab_failed_cnt, final int cs_rab_failed_cnt,
            final int multi_rab_failed_cnt, final int ps_err_cnt, final int cs_err_cnt, final int multi_err_cnt)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, SAMPLE_IMSI_AS_STRING);
        values.put(EVENT_ID, eventId);
        values.put(TAC, tac);
        values.put(DATETIME_ID, timestamp);
        values.put(HIER3_ID, hashedIdForRNC);
        values.put(RAN_DISCONNECTION_CODE, disConCode);
        values.put(RAN_DISCONNECTION_SUBCODE, disConSubCode);
        values.put(PS_ERR_CNT, ps_err_cnt);
        values.put(CS_ERR_CNT, cs_err_cnt);
        values.put(MULTI_ERR_CNT, multi_err_cnt);
        values.put(PS_RAB_FAIL_CNT, ps_rab_failed_cnt);
        values.put(CS_RAB_FAIL_CNT, cs_rab_failed_cnt);
        values.put(MULTI_RAB_FAIL_CNT, multi_rab_failed_cnt);

        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
        final Map<String, Object> valuesForGroupTable = new HashMap<String, Object>();
        valuesForGroupTable.put(GROUP_NAME_KEY, TEST_VALUE_IMSI_GROUP);
        valuesForGroupTable.put(IMSI, SAMPLE_IMSI_AS_STRING);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForGroupTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        columns.add(HIER3_ID);
        columns.add(RAN_DISCONNECTION_CODE);
        columns.add(RAN_DISCONNECTION_SUBCODE);
        columns.add(PS_ERR_CNT);
        columns.add(CS_ERR_CNT);
        columns.add(MULTI_ERR_CNT);
        columns.add(PS_RAB_FAIL_CNT);
        columns.add(CS_RAB_FAIL_CNT);
        columns.add(MULTI_RAB_FAIL_CNT);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        final Collection<String> columnsForGroupTable = new ArrayList<String>();
        columnsForGroupTable.add(GROUP_NAME_KEY);
        columnsForGroupTable.add(IMSI);
        createTemporaryTable(TEMP_GROUP_TYPE_E_IMSI, columnsForGroupTable);
    }

    @Test
    public void testGetFailures_ThirtyMinutes() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(TYPE_PARAM, "BSC");
        requestParameters.putSingle(HIER3_ID, "4894708643454430832");
        requestParameters.putSingle(EVENT_ID_PARAM, "438");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "0");
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "123");

        final String result = runQuery(controllerByDisconnection, requestParameters);
        validateResult(result);
    }

    private void validateResult(final String json) throws Exception {
        final List<DisconnectionChartResourceResult> results = getTranslator().translateResult(json,
                DisconnectionChartResourceResult.class);
        validateAgainstGridDefinition(json, "DRILL_CHART_RNC_DISCONNECTION_CODE_FROM_DRILL_BY");
        assertThat(results.size(), is(4));

        final DisconnectionChartResourceResult resultForDisconnectionRCS = results.get(0);
        assertThat(resultForDisconnectionRCS.getNoFailures(), is(2));
        assertThat(resultForDisconnectionRCS.getSubscribers(), is(1));
        assertEquals(resultForDisconnectionRCS.getDisconnectionDesc(), "Radio Connection Supervision (RCS)");

        final DisconnectionChartResourceResult resultForDisconnectionCongestion = results.get(1);
        assertThat(resultForDisconnectionCongestion.getNoFailures(), is(1));
        assertThat(resultForDisconnectionCongestion.getSubscribers(), is(1));
        assertEquals(resultForDisconnectionCongestion.getDisconnectionDesc(), "Disconnection Code: 4");

        final DisconnectionChartResourceResult resultForDisconnectionUnknown = results.get(2);
        assertThat(resultForDisconnectionUnknown.getNoFailures(), is(1));
        assertThat(resultForDisconnectionUnknown.getSubscribers(), is(1));
        assertEquals(resultForDisconnectionUnknown.getDisconnectionDesc(), "Congestion");

    }
}
