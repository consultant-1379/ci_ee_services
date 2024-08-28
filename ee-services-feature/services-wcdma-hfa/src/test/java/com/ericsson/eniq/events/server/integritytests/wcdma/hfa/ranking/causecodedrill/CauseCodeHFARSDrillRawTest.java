/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.causecodedrill;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_CODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SUB_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SUB_CAUSE_VALUE_DESC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.CauseCodeHandoverFailureRankingDrilldownService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureCauseCodeRankingDrillResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class CauseCodeHFARSDrillRawTest extends
        BaseDataIntegrityTest<HandoverFailureCauseCodeRankingDrillResult> {
    private CauseCodeHandoverFailureRankingDrilldownService causeCodeHandoverFailureRankingDrilldownService;

    @Before
    public void onSetUp() throws Exception {
        causeCodeHandoverFailureRankingDrilldownService = new CauseCodeHandoverFailureRankingDrilldownService();
        attachDependencies(causeCodeHandoverFailureRankingDrilldownService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_CauseCode_SOHO_Drilldown_HandoverFailure_Raw_ThirtyMinutes() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(CAUSE_CODE_PARAM, "0");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "0");
        final String json = runQuery(causeCodeHandoverFailureRankingDrilldownService, requestParameters);
        System.out.println(json);
        verifyResultSOHO(json);
    }

    @Test
    public void testGetData_CauseCode_HSDSCH_Drilldown_HandoverFailure_Raw_ThirtyMinutes() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(CAUSE_CODE_PARAM, "0");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "1");
        final String json = runQuery(causeCodeHandoverFailureRankingDrilldownService, requestParameters);
        System.out.println(json);
        verifyResultHSDSCH(json);
    }

    @Test
    public void testGetData_CauseCode_IFHO_Drilldown_HandoverFailure_Raw_ThirtyMinutes() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(CAUSE_CODE_PARAM, "0");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "2");
        final String json = runQuery(causeCodeHandoverFailureRankingDrilldownService, requestParameters);
        System.out.println(json);
        verifyResultIFHO(json);
    }

    @Test
    public void testGetData_CauseCode_IRAT_Drilldown_HandoverFailure_Raw_ThirtyMinutes() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(CAUSE_CODE_PARAM, "0");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "3");
        final String json = runQuery(causeCodeHandoverFailureRankingDrilldownService, requestParameters);
        System.out.println(json);
        verifyResultIRAT(json);
    }

    private void verifyResultSOHO(final String json) throws Exception {
        final List<HandoverFailureCauseCodeRankingDrillResult> result = getTranslator().translateResult(json,
                HandoverFailureCauseCodeRankingDrillResult.class);
        assertThat(result.size(), is(1));

        final HandoverFailureCauseCodeRankingDrillResult resultForCauseCodeDrill = result.get(0);

        assertThat(resultForCauseCodeDrill.getFailures(), is(1));
        assertThat(resultForCauseCodeDrill.getSubCauseCode(), is("SOHO_SUB_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCodeDrill.getSubCauseCodeId(), is("1"));
        assertThat(resultForCauseCodeDrill.getImpactedSubscribers(), is(1));
    }

    private void verifyResultHSDSCH(final String json) throws Exception {
        final List<HandoverFailureCauseCodeRankingDrillResult> result = getTranslator().translateResult(json,
                HandoverFailureCauseCodeRankingDrillResult.class);

        // No sub cause codes for HSDSCH
        assertThat(result.size(), is(0));
    }

    private void verifyResultIFHO(final String json) throws Exception {
        final List<HandoverFailureCauseCodeRankingDrillResult> result = getTranslator().translateResult(json,
                HandoverFailureCauseCodeRankingDrillResult.class);
        assertThat(result.size(), is(1));

        final HandoverFailureCauseCodeRankingDrillResult resultForCauseCodeDrill = result.get(0);

        assertThat(resultForCauseCodeDrill.getFailures(), is(1));
        assertThat(resultForCauseCodeDrill.getSubCauseCode(), is("IFHO_SUB_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCodeDrill.getSubCauseCodeId(), is("1"));
        assertThat(resultForCauseCodeDrill.getImpactedSubscribers(), is(1));
    }

    private void verifyResultIRAT(final String json) throws Exception {
        final List<HandoverFailureCauseCodeRankingDrillResult> result = getTranslator().translateResult(json,
                HandoverFailureCauseCodeRankingDrillResult.class);
        assertThat(result.size(), is(1));

        final HandoverFailureCauseCodeRankingDrillResult resultForCauseCodeDrill = result.get(0);

        assertThat(resultForCauseCodeDrill.getFailures(), is(1));
        assertThat(resultForCauseCodeDrill.getSubCauseCode(), is("IRAT_SUB_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCodeDrill.getSubCauseCodeId(), is("1"));
        assertThat(resultForCauseCodeDrill.getImpactedSubscribers(), is(1));
    }

    private void insertData() throws Exception {
        final Map<String, Object> columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(SUB_CAUSE_VALUE, "1");
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(EVENT_ID, "408");
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(SUB_CAUSE_VALUE_DESC, "SOHO_SUB_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE);

        // HSDSCH doesn't have sub-cause codes

        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(SUB_CAUSE_VALUE, "1");
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(EVENT_ID, "423");
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(SUB_CAUSE_VALUE_DESC, "IFHO_SUB_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE);

        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(SUB_CAUSE_VALUE, "1");
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(EVENT_ID, "458");
        columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE.put(SUB_CAUSE_VALUE_DESC, "IRAT_SUB_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE);

        final Map<String, Object> dataForRawEventTable = new HashMap<String, Object>();

        final String dateTimeNowMinus27Mins = DateTimeUtilities
                .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

        dataForRawEventTable.put(CAUSE_VALUE, "0");
        dataForRawEventTable.put(SUB_CAUSE_VALUE, "1");
        dataForRawEventTable.put(EVENT_ID, "408");
        dataForRawEventTable.put(IMSI, "123451");
        dataForRawEventTable.put(TAC, "2312");
        dataForRawEventTable.put(DATETIME_ID, dateTimeNowMinus27Mins);
        dataForRawEventTable.put(CATEGORY_ID, "0");
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, dataForRawEventTable);

        // HSDSCH doesn't have sub-cause codes

        dataForRawEventTable.put(SUB_CAUSE_VALUE, "1");
        dataForRawEventTable.put(EVENT_ID, "423");
        dataForRawEventTable.put(CATEGORY_ID, "2");
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, dataForRawEventTable);
        dataForRawEventTable.put(EVENT_ID, "458");
        dataForRawEventTable.put(CATEGORY_ID, "3");
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, dataForRawEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.add(SUB_CAUSE_VALUE);
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.add(EVENT_ID);
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.add(SUB_CAUSE_VALUE_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE);

        final Collection<String> columnsForRawSOHOEventTable = new ArrayList<String>();
        columnsForRawSOHOEventTable.add(CAUSE_VALUE);
        columnsForRawSOHOEventTable.add(SUB_CAUSE_VALUE);
        columnsForRawSOHOEventTable.add(EVENT_ID);
        columnsForRawSOHOEventTable.add(IMSI);
        columnsForRawSOHOEventTable.add(TAC);
        columnsForRawSOHOEventTable.add(CATEGORY_ID);
        columnsForRawSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_ERR_RAW, columnsForRawSOHOEventTable);

        final Collection<String> columnsForRawIFHOEventTable = new ArrayList<String>();
        columnsForRawIFHOEventTable.add(CAUSE_VALUE);
        columnsForRawIFHOEventTable.add(SUB_CAUSE_VALUE);
        columnsForRawIFHOEventTable.add(EVENT_ID);
        columnsForRawIFHOEventTable.add(IMSI);
        columnsForRawIFHOEventTable.add(TAC);
        columnsForRawIFHOEventTable.add(CATEGORY_ID);
        columnsForRawIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_ERR_RAW, columnsForRawIFHOEventTable);

        final Collection<String> columnsForRawIRATEventTable = new ArrayList<String>();
        columnsForRawIRATEventTable.add(CAUSE_VALUE);
        columnsForRawIRATEventTable.add(SUB_CAUSE_VALUE);
        columnsForRawIRATEventTable.add(EVENT_ID);
        columnsForRawIRATEventTable.add(IMSI);
        columnsForRawIRATEventTable.add(TAC);
        columnsForRawIRATEventTable.add(CATEGORY_ID);
        columnsForRawIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columnsForRawIRATEventTable);

        // HSDSCH doesn't have sub-cause codes
    }
}
