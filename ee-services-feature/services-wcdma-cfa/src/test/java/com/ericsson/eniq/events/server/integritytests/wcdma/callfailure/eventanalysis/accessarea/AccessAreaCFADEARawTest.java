/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.accessarea;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.AccessAreaCallFailureDetailedEAService;
import com.ericsson.eniq.events.server.test.queryresults.AccessAreaCallFailureDetailedEventAnalysisResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author etonayr
 * @since 2011
 *
 */
public class AccessAreaCFADEARawTest extends BaseDataIntegrityTest<AccessAreaCallFailureDetailedEventAnalysisResult> {

    private static final String _CELL_ID = "1";

    private AccessAreaCallFailureDetailedEAService service;

    private String expectedTime;

    private long HASH_ID;

    @Before
    public void setup() throws Exception {
        service = new AccessAreaCallFailureDetailedEAService();
        createHashId();
        attachDependencies(service);
        createEventTable();
        createAndPopulateLookupTables();
        insertEventData();
        insertDataIntoTacGroupTable();
    }

    private void createHashId() {
        HASH_ID = queryUtils.createHashIDFor3GCell(_3G, RNC01_ALTERNATIVE_FDN, _CELL_ID, ERICSSON);
    }

    @Test
    public void testFiveMinuteQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.add(CELL_ID_PARAM, _CELL_ID);
        requestParameters.add(EVENT_ID_PARAM, CALL_SETUP_FAILURE_EVENT_ID);
        requestParameters.add(RAN_VENDOR_PARAM, ERICSSON);
        requestParameters.add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN);
        final String result = runQuery(service, requestParameters);
        verifyResult(result);
    }

    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_CELL_RANKING_RAN_WCDMA_DETAILED_EVENT_ANALYSIS");
        final List<AccessAreaCallFailureDetailedEventAnalysisResult> results = getTranslator().translateResult(json,
                AccessAreaCallFailureDetailedEventAnalysisResult.class);

        assertThat(results.size(), is(1));
        final AccessAreaCallFailureDetailedEventAnalysisResult result = results.get(0);
        assertThat(result.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
        assertThat(result.getEvaluationCase(), is(NOT_APPLICABLE));
        assertThat(result.getExceptionClass(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
        assertThat(result.getImsi(), is(_CELL_ID));
        assertThat(result.getProcedureIndicator(), is(NOT_APPLICABLE));
        assertThat(result.getSeverityIndicator(), is(RECOVERABLE));
        assertThat(result.getTac(), is(SAMPLE_TAC));
        assertThat(result.getTerminalMake(), is(MANUFACTURER_FOR_SAMPLE_TAC));
        assertThat(result.getTerminalModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
    }

    private void insertEventData() throws Exception {
        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        dataForEventTable.put(C_ID_1, _CELL_ID);
        dataForEventTable.put(HIER3_CELL_ID, HASH_ID);
        dataForEventTable.put(IMSI, _CELL_ID);
        dataForEventTable.put(TAC, SAMPLE_TAC);
        dataForEventTable.put(EVENT_ID, CALL_SETUP_FAILURE_EVENT_ID);
        dataForEventTable.put(EVENT_TIME, DateTimeUtilities.getDateMinus5Minutes());
        dataForEventTable.put(SEVERITY_INDICATOR, "0");
        dataForEventTable.put(PROCEDURE_INDICATOR, "0");
        dataForEventTable.put(EVALUATION_CASE, "0");
        dataForEventTable.put(EXCEPTION_CLASS, "0");
        dataForEventTable.put(CAUSE_VALUE, "0");
        dataForEventTable.put(EXTENDED_CAUSE_VALUE, "0");
        expectedTime = DateTimeUtilities.getDateTimeMinus5Minutes();
        dataForEventTable.put(DATETIME_ID, expectedTime);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, dataForEventTable);
        insertDataIntoTacGroupTable();
    }

    private void createAndPopulateLookupTables() throws Exception {
        final List<String> lookupTables = new ArrayList<String>();
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EVALUATION_CASE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_CAUSE_VALUE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR);
        lookupTables.add(TEMP_DIM_E_SGEH_TAC);
        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }
    }

    private void insertDataIntoTacGroupTable() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        values.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP);
        insertRow(TEMP_GROUP_TYPE_E_TAC, values);
    }

    private void createEventTable() throws Exception {
        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(C_ID_1);
        columnsForEventTable.add(HIER3_CELL_ID);
        columnsForEventTable.add(IMSI);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(EVENT_ID);
        columnsForEventTable.add(EVENT_TIME);
        columnsForEventTable.add(DATETIME_ID);
        columnsForEventTable.add(SEVERITY_INDICATOR);
        columnsForEventTable.add(PROCEDURE_INDICATOR);
        columnsForEventTable.add(EVALUATION_CASE);
        columnsForEventTable.add(EXCEPTION_CLASS);
        columnsForEventTable.add(CAUSE_VALUE);
        columnsForEventTable.add(EXTENDED_CAUSE_VALUE);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEventTable);
    }

}
