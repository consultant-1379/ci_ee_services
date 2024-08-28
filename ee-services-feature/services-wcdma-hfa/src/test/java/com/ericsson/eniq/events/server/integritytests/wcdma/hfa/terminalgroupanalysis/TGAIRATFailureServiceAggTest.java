/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.terminalgroupanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.terminalgroupanalysis.TerminalGAIRATFailureService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureTerminalGroupAnalysisResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eseuhon
 * @since 2011
 *
 */
public class TGAIRATFailureServiceAggTest extends
        BaseDataIntegrityTest<HandoverFailureTerminalGroupAnalysisResult> {

    private TerminalGAIRATFailureService service;

    @Before
    public void setUp() throws Exception {
        service = new TerminalGAIRATFailureService();
        attachDependencies(service);

        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC);
        populateTacGroupTable(SAMPLE_TAC_GROUP, SAMPLE_TAC_2);
        populateTacGroupTable(SAMPLE_TAC_GROUP_2, SAMPLE_TAC_3);

        createAggTable();
        createRawTable();

        //insert data into RAW table
        insertDataIntoRawTable(SAMPLE_TAC, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID), SAMPLE_IMSI,
                DateTimeUtilities.getDateTimeMinus48Hours());
        insertDataIntoRawTable(SAMPLE_TAC, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID), SAMPLE_IMSI,
                DateTimeUtilities.getDateTimeMinus48Hours());
        insertDataIntoRawTable(SAMPLE_TAC_2, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID), SAMPLE_IMSI_2,
                DateTimeUtilities.getDateTimeMinus48Hours());
        insertDataIntoRawTable(SAMPLE_EXCLUSIVE_TAC, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID), SAMPLE_IMSI_3,
                DateTimeUtilities.getDateTimeMinus48Hours());
        //insert data into Aggregation table
        insertDataIntoAggTable(SAMPLE_TAC, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID), 2,
                DateTimeUtilities.getDateTimeMinus48Hours());
        insertDataIntoAggTable(SAMPLE_TAC_2, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID), 1,
                DateTimeUtilities.getDateTimeMinus48Hours());
    }

    @Test
    public void getTerminalGroupAnalysisIRATFailureForOneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String result = runQuery(service, requestParameters);

        //validate result
        //validateAgainstGridDefinition(result, "TERMINAL_GROUP_ANALYSIS_RAN_WCDMA_HFA_IRAT");
        final List<HandoverFailureTerminalGroupAnalysisResult> results = getTranslator().translateResult(result,
                HandoverFailureTerminalGroupAnalysisResult.class);
        assertThat(results.size(), is(2));

        final HandoverFailureTerminalGroupAnalysisResult sampleTacGroupResult = results.get(0);
        assertThat(sampleTacGroupResult.getRank(), is(1));
        assertThat(sampleTacGroupResult.getGroupName(), is(SAMPLE_TAC_GROUP));
        assertThat(sampleTacGroupResult.getNumFailures(), is(3));
        assertThat(sampleTacGroupResult.getImpactedSubscribers(), is(2));
        assertThat(sampleTacGroupResult.getCategoryId(), is(Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID)));

        final HandoverFailureTerminalGroupAnalysisResult sampleExclusiveTacGroupResult = results.get(1);
        assertThat(sampleExclusiveTacGroupResult.getRank(), is(2));
        assertThat(sampleExclusiveTacGroupResult.getGroupName(), is(EXCLUSIVE_TAC_GROUP));
        assertThat(sampleExclusiveTacGroupResult.getNumFailures(), is(1));
        assertThat(sampleExclusiveTacGroupResult.getImpactedSubscribers(), is(1));
        assertThat(sampleExclusiveTacGroupResult.getCategoryId(), is(Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID)));

    }

    private void createRawTable() throws SQLException {
        final Map<String, Nullable> columns = new HashMap<String, Nullable>();
        columns.put(TAC, Nullable.CAN_BE_NULL);
        columns.put(CATEGORY_ID, Nullable.CAN_BE_NULL);
        columns.put(IMSI, Nullable.CAN_BE_NULL);
        columns.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columns);
    }

    private void createAggTable() throws SQLException {
        final Map<String, Nullable> columns = new HashMap<String, Nullable>();
        columns.put(TAC, Nullable.CAN_BE_NULL);
        columns.put(CATEGORY_ID, Nullable.CAN_BE_NULL);
        columns.put(NO_OF_ERRORS, Nullable.CAN_BE_NULL);
        columns.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY, columns);
    }

    private void insertDataIntoRawTable(final int tac, final int categoryId, final long imsi, final String date)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, tac);
        values.put(CATEGORY_ID, categoryId);
        values.put(IMSI, imsi);
        values.put(DATETIME_ID, date);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, values);
    }

    private void insertDataIntoAggTable(final int tac, final int categoryId, final int numOfErrs, final String date)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, tac);
        values.put(CATEGORY_ID, categoryId);
        values.put(NO_OF_ERRORS, numOfErrs);
        values.put(DATETIME_ID, date);
        insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY, values);
    }
}
