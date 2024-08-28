/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.terminalanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_HFA_IRAT_CATEGORY_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MANUFACTURER_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.terminalanalysis.TerminalAnalysisIRATFailureService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureTerminalAnalysisResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eseuhon
 * @since 2011
 *
 */
public class TAIRATFailureServiceRawTest extends
        BaseDataIntegrityTest<HandoverFailureTerminalAnalysisResult> {

    private TerminalAnalysisIRATFailureService service;

    private final int numFailuresForSampleTac = 2;

    @Before
    public void setUp() throws Exception {
        service = new TerminalAnalysisIRATFailureService();
        attachDependencies(service);
        createRawTable();
        insertRowData();
    }

    @Test
    public void getTerminalAnalysisIRATFailure() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String result = runQuery(service, requestParameters);

        //validate result
        //validateAgainstGridDefinition(result, "TERMINAL_ANALYSIS_RAN_WCDMA_HFA_IRAT");
        final List<HandoverFailureTerminalAnalysisResult> results = getTranslator().translateResult(result,
                HandoverFailureTerminalAnalysisResult.class);
        assertThat(results.size(), is(1));

        final HandoverFailureTerminalAnalysisResult mostIRATFailure = results.get(0);
        assertThat(mostIRATFailure.getRank(), is(1));
        assertThat(mostIRATFailure.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(mostIRATFailure.getModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(mostIRATFailure.getTac(), is(SAMPLE_TAC));
        assertThat(mostIRATFailure.getFailures(), is(numFailuresForSampleTac));
        assertThat(mostIRATFailure.getCategory(), is(Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID)));
    }

    private void createRawTable() throws Exception {
        final Map<String, Nullable> columns = new HashMap<String, Nullable>();
        columns.put(TAC, Nullable.CAN_BE_NULL);
        columns.put(CATEGORY_ID, Nullable.CAN_BE_NULL);
        columns.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, columns);
    }

    private void insertRowData() throws SQLException {
        for (int i = 0; i < numFailuresForSampleTac; i++) {
            final Map<String, Object> values = new HashMap<String, Object>();
            final String dateTimeNowMinus27Mins = DateTimeUtilities
                    .getDateTimeMinusMinutes(20 + WCDMA_HFA_LATENCY_ON_THIRTY_MIN_QUERY);

            values.put(TAC, SAMPLE_TAC);
            values.put(CATEGORY_ID, Integer.parseInt(WCDMA_HFA_IRAT_CATEGORY_ID));
            values.put(DATETIME_ID, dateTimeNowMinus27Mins);
            insertRow(TEMP_EVENT_E_RAN_HFA_IRAT_ERR_RAW, values);
        }
    }
}