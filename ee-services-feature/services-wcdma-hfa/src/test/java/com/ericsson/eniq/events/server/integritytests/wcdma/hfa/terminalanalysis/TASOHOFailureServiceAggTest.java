/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.hfa.terminalanalysis;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.terminalanalysis.TerminalAnalysisSOHOFailureService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure.HandoverFailureTerminalAnalysisResult;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eseuhon
 * @since 2011
 *
 */
public class TASOHOFailureServiceAggTest extends
        BaseDataIntegrityTest<HandoverFailureTerminalAnalysisResult> {

    private TerminalAnalysisSOHOFailureService service;

    private final int numFailuresForSampleTac = 3;

    @Before
    public void setUp() throws Exception {
        service = new TerminalAnalysisSOHOFailureService();
        attachDependencies(service);
        createAggTable();
        insertData();
    }

    @Test
    public void getTerminalAnalysisSOHOFailureForOneWeek() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String result = runQuery(service, requestParameters);

        //validate result
        //validateAgainstGridDefinition(result, "TERMINAL_ANALYSIS_RAN_WCDMA_HFA_SOHO");
        final List<HandoverFailureTerminalAnalysisResult> results = getTranslator().translateResult(result,
                HandoverFailureTerminalAnalysisResult.class);
        assertThat(results.size(), is(1));

        final HandoverFailureTerminalAnalysisResult mostSOHOFailure = results.get(0);
        assertThat(mostSOHOFailure.getRank(), is(1));
        assertThat(mostSOHOFailure.getManufacturer(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(mostSOHOFailure.getModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
        assertThat(mostSOHOFailure.getTac(), is(SAMPLE_TAC));
        assertThat(mostSOHOFailure.getFailures(), is(numFailuresForSampleTac));
        assertThat(mostSOHOFailure.getCategory(), is(Integer.parseInt(WCDMA_HFA_SOHO_CATEGORY_ID)));
    }

    private void createAggTable() throws Exception {
        final Map<String, Nullable> columns = new HashMap<String, Nullable>();
        columns.put(TAC, Nullable.CAN_BE_NULL);
        columns.put(NO_OF_ERRORS, Nullable.CAN_BE_NULL);
        columns.put(CATEGORY_ID, Nullable.CAN_BE_NULL);
        columns.put(DATETIME_ID, Nullable.CANNOT_BE_NULL);
        createTemporaryTable(TEMP_EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY, columns);
    }

    private void insertData() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_TAC);
        values.put(NO_OF_ERRORS, numFailuresForSampleTac);
        values.put(CATEGORY_ID, Integer.parseInt(WCDMA_HFA_SOHO_CATEGORY_ID));
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus48Hours());
        insertRow(TEMP_EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY, values);
    }

}
