/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.ranking.causecode;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.ranking.CauseCodeHandoverFailureRankingService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureCauseCodeRankingResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class CauseCodeHFARSAggTest extends BaseDataIntegrityTest<HandoverFailureCauseCodeRankingResult> {

    private CauseCodeHandoverFailureRankingService causeCodeHandoverFailureRankingService;

    @Before
    public void onSetUp() throws Exception {
        causeCodeHandoverFailureRankingService = new CauseCodeHandoverFailureRankingService();
        attachDependencies(causeCodeHandoverFailureRankingService);
        createTables();
        insertData();
    }

    @Test
    public void testGetData_CauseCdoe_HandoverFailure_Aggregation_OneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String json = runQuery(causeCodeHandoverFailureRankingService, requestParameters);
        System.out.println(json);
        verifyResult(json);
    }

    private void verifyResult(final String json) throws Exception {
        final List<HandoverFailureCauseCodeRankingResult> result = getTranslator().translateResult(json,
                HandoverFailureCauseCodeRankingResult.class);
        assertThat(result.size(), is(4));

        HandoverFailureCauseCodeRankingResult resultForCauseCode = result.get(0);

        assertThat(resultForCauseCode.getFailures(), is(4));
        assertThat(resultForCauseCode.getCauseCode(), is("HSDSCH_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCode.getCauseCodeId(), is("0"));
        assertThat(resultForCauseCode.getRank(), is(1));

        resultForCauseCode = result.get(1);

        assertThat(resultForCauseCode.getFailures(), is(3));
        assertThat(resultForCauseCode.getCauseCode(), is("IRAT_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCode.getCauseCodeId(), is("0"));
        assertThat(resultForCauseCode.getRank(), is(2));

        resultForCauseCode = result.get(2);

        assertThat(resultForCauseCode.getFailures(), is(2));
        assertThat(resultForCauseCode.getCauseCode(), is("SOHO_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCode.getCauseCodeId(), is("0"));
        assertThat(resultForCauseCode.getRank(), is(3));

        resultForCauseCode = result.get(3);

        assertThat(resultForCauseCode.getFailures(), is(1));
        assertThat(resultForCauseCode.getCauseCode(), is("IFHO_CAUSE_CODE_VALUE"));
        assertThat(resultForCauseCode.getCauseCodeId(), is("0"));
        assertThat(resultForCauseCode.getRank(), is(4));
    }

    private void insertData() throws Exception {

        final Map<String, Object> columnsFor_DIM_E_RAN_HFA_CATEGORY = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "0");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Soft Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "1");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "HSDSCH Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "2");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "Interfrequency Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_ID, "3");
        columnsFor_DIM_E_RAN_HFA_CATEGORY.put(CATEGORY_DESC, "IRAT Handover");
        insertRow(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Map<String, Object> columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(CAUSE_VALUE, "0");
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(EVENT_ID, "408");
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(CAUSE_VALUE_DESC, "SOHO_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE);

        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(EVENT_ID, "433");
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(CAUSE_VALUE_DESC, "HSDSCH_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE);

        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(EVENT_ID, "423");
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(CAUSE_VALUE_DESC, "IFHO_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE);

        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(EVENT_ID, "458");
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.put(CAUSE_VALUE_DESC, "IRAT_CAUSE_CODE_VALUE");
        insertRow(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE);

        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();

        dataForEventTable.put(CAUSE_VALUE, "0");
        dataForEventTable.put(EVENT_ID, "408");
        dataForEventTable.put(NO_OF_ERRORS, "2");
        dataForEventTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        dataForEventTable.put(CATEGORY_ID, "0");
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_CV_SCV_DAY, dataForEventTable);

        dataForEventTable.put(NO_OF_ERRORS, "4");
        dataForEventTable.put(CATEGORY_ID, "1");
        dataForEventTable.put(EVENT_ID, "433");
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_CV_SCV_DAY, dataForEventTable);

        dataForEventTable.put(NO_OF_ERRORS, "1");
        dataForEventTable.put(CATEGORY_ID, "2");
        dataForEventTable.put(EVENT_ID, "423");
        insertRow(TEMP_EVENT_E_RAN_HFA_IFHO_CV_SCV_DAY, dataForEventTable);

        dataForEventTable.put(NO_OF_ERRORS, "3");
        dataForEventTable.put(CATEGORY_ID, "3");
        dataForEventTable.put(EVENT_ID, "458");
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_CV_SCV_DAY, dataForEventTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columnsFor_DIM_E_RAN_HFA_CATEGORY = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Collection<String> columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.add(CAUSE_VALUE);
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.add(EVENT_ID);
        columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE.add(CAUSE_VALUE_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CAUSE_CODE_VALUE, columnsFor_DIM_E_RAN_HFA_CAUSE_CODE_VALUE);

        final Collection<String> columnsForSOHOEventTable = new ArrayList<String>();
        columnsForSOHOEventTable.add(CAUSE_VALUE);
        columnsForSOHOEventTable.add(EVENT_ID);
        columnsForSOHOEventTable.add(NO_OF_ERRORS);
        columnsForSOHOEventTable.add(CATEGORY_ID);
        columnsForSOHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_CV_SCV_DAY, columnsForSOHOEventTable);

        final Collection<String> columnsForIFHOEventTable = new ArrayList<String>();
        columnsForIFHOEventTable.add(CAUSE_VALUE);
        columnsForIFHOEventTable.add(EVENT_ID);
        columnsForIFHOEventTable.add(NO_OF_ERRORS);
        columnsForIFHOEventTable.add(CATEGORY_ID);
        columnsForIFHOEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IFHO_CV_SCV_DAY, columnsForIFHOEventTable);

        final Collection<String> columnsForIRATEventTable = new ArrayList<String>();
        columnsForIRATEventTable.add(CAUSE_VALUE);
        columnsForIRATEventTable.add(EVENT_ID);
        columnsForIRATEventTable.add(NO_OF_ERRORS);
        columnsForIRATEventTable.add(CATEGORY_ID);
        columnsForIRATEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_CV_SCV_DAY, columnsForIRATEventTable);

        final Collection<String> columnsForHSDSCHEventTable = new ArrayList<String>();
        columnsForHSDSCHEventTable.add(CAUSE_VALUE);
        columnsForHSDSCHEventTable.add(EVENT_ID);
        columnsForHSDSCHEventTable.add(NO_OF_ERRORS);
        columnsForHSDSCHEventTable.add(CATEGORY_ID);
        columnsForHSDSCHEventTable.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_CV_SCV_DAY, columnsForHSDSCHEventTable);
    }
}
