/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.terminalgroupanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_HFA_HSDSCH_CATEGORY_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CATEGORY_DESC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_IMSI;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_IMSI_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_IMSI_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_GROUP;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_GROUP_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_HFA_CATEGORY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.terminalgroupanalysis.TerminalGAHSDSCHFailureService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureTerminalGroupAnalysisResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eseuhon
 * @since 2011
 *
 */
public class TGAHSDSCHFailureServiceRawTest extends
        BaseDataIntegrityTest<HandoverFailureTerminalGroupAnalysisResult> {

    private TerminalGAHSDSCHFailureService service;

    @Before
    public void setUp() throws Exception {
        service = new TerminalGAHSDSCHFailureService();
        attachDependencies(service);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC_2);
        populateTacGroupTable(SAMPLE_TAC_GROUP_2, SAMPLE_TAC_3);
        createRawTable();
        final String dateTimeNowMinus27Mins = DateTimeUtilities
                .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

        insertRowIntoEventTable(SAMPLE_IMSI, Integer.parseInt(WCDMA_HFA_HSDSCH_CATEGORY_ID), SAMPLE_TAC,
                dateTimeNowMinus27Mins);
        insertRowIntoEventTable(SAMPLE_IMSI_2, Integer.parseInt(WCDMA_HFA_HSDSCH_CATEGORY_ID), SAMPLE_TAC_2,
                dateTimeNowMinus27Mins);
        insertRowIntoEventTable(SAMPLE_IMSI_3, Integer.parseInt(WCDMA_HFA_HSDSCH_CATEGORY_ID), SAMPLE_TAC_3,
                dateTimeNowMinus27Mins);
    }

    @Test
    public void getTerminalGroupAnalysisHSDSCHFailure() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String result = runQuery(service, requestParameters);

        //validate result
        //validateAgainstGridDefinition(result, "TERMINAL_GROUP_ANALYSIS_RAN_WCDMA_HFA_HSDSCH");
        final List<HandoverFailureTerminalGroupAnalysisResult> results = getTranslator().translateResult(result,
                HandoverFailureTerminalGroupAnalysisResult.class);
        assertThat(results.size(), is(6));

        final HandoverFailureTerminalGroupAnalysisResult sampleTacGroupResult = results.get(0);
        assertThat(sampleTacGroupResult.getRank(), is(1));
        assertThat(sampleTacGroupResult.getGroupName(), is(SAMPLE_TAC_GROUP));
        assertThat(sampleTacGroupResult.getNumFailures(), is(2));
        assertThat(sampleTacGroupResult.getImpactedSubscribers(), is(2));
        assertThat(sampleTacGroupResult.getCategoryId(), is(Integer.parseInt(WCDMA_HFA_HSDSCH_CATEGORY_ID)));

        final HandoverFailureTerminalGroupAnalysisResult sampleTacGroup2Result = results.get(1);
        assertThat(sampleTacGroup2Result.getRank(), is(1));
        assertThat(sampleTacGroup2Result.getGroupName(), is(SAMPLE_TAC_GROUP));
        assertThat(sampleTacGroup2Result.getNumFailures(), is(2));
        assertThat(sampleTacGroup2Result.getImpactedSubscribers(), is(2));
        assertThat(sampleTacGroupResult.getCategoryId(), is(Integer.parseInt(WCDMA_HFA_HSDSCH_CATEGORY_ID)));
    }

    private void createRawTable() throws Exception {
        final Collection<String> columnsFor_DIM_E_RAN_HFA_CATEGORY = new ArrayList<String>();
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_ID);
        columnsFor_DIM_E_RAN_HFA_CATEGORY.add(CATEGORY_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_HFA_CATEGORY, columnsFor_DIM_E_RAN_HFA_CATEGORY);

        final Map<String, Nullable> columns = new HashMap<String, Nullable>();
        columns.put(IMSI, Nullable.CAN_BE_NULL);
        columns.put(CATEGORY_ID, Nullable.CAN_BE_NULL);
        columns.put(TAC, Nullable.CAN_BE_NULL);
        columns.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, columns);
    }

    private void insertRowIntoEventTable(final long imsi, final int categoryId, final int tac, final String timestamp)
            throws SQLException {
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

        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, imsi);
        values.put(CATEGORY_ID, categoryId);
        values.put(TAC, tac);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_RAN_HFA_HSDSCH_ERR_RAW, values);
    }
}
