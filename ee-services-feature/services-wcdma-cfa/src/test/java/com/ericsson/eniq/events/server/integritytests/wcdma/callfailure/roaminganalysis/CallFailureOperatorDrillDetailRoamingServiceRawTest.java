/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.roaminganalysis;

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

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.roaminganalysis.CallFailureOperatorDrillDetailRoamingService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailurCountryDrilDetaillRoamingQueryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ezhelao
 *
 */
public class CallFailureOperatorDrillDetailRoamingServiceRawTest extends
        BaseDataIntegrityTest<CallFailurCountryDrilDetaillRoamingQueryResult> {

    private CallFailureOperatorDrillDetailRoamingService callFailureOperatorDrillDetailRoamingService;

    private static final String AWWC_MNC = "01";

    @Before
    public void setup() throws Exception {
        callFailureOperatorDrillDetailRoamingService = new CallFailureOperatorDrillDetailRoamingService();
        attachDependencies(callFailureOperatorDrillDetailRoamingService);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_SGEH_MCCMNC);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EVENTTYPE);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_CAUSE_VALUE);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EVALUATION_CASE);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EXCEPTION_CASE);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EXCEPTION_CLASS);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_PROCEDURE_INDICATOR);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_SEVERITY_INDICATOR);

        createRawTable();
        insertRawData();
        createAndPopulateTempLookupTable(DIM_E_SGEH_MCCMNC);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EVENTTYPE);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_CAUSE_VALUE);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EVALUATION_CASE);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EXCEPTION_CASE);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EXCEPTION_CLASS);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_PROCEDURE_INDICATOR);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_SEVERITY_INDICATOR);

    }

    private void insertRawData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();
        insertRowIntoRawTable(timestamp, "1", "438", 1, MCC_FOR_ARGENTINA, AWWC_MNC);
        insertRowIntoRawTable(timestamp, "2", "438", 1, MCC_FOR_ARGENTINA, AWWC_MNC);
        insertRowIntoRawTable(timestamp, "3", "438", 1, MCC_FOR_ARGENTINA, AWWC_MNC);

        insertRowIntoRawTable(timestamp, "2", "438", 0, MCC_FOR_ARGENTINA, AWWC_MNC);
        insertRowIntoRawTable(timestamp, "4", "438", 1, MCC_FOR_ARGENTINA, SAMPLE_MNC);
        insertRowIntoRawTable(timestamp, "3", "456", 1, MCC_FOR_ARGENTINA, AWWC_MNC);
        insertRowIntoRawTable(timestamp, "3", "456", 1, MCC_FOR_ARGENTINA, AWWC_MNC);
    }

    private void insertRowIntoRawTable(final String event_time, final String imsi, final String eventID,
            final int roaming, final String imsi_mcc, final String imsi_mnc) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(EVENT_TIME_COLUMN, event_time);
        values.put(IMSI, imsi);
        values.put(TAC, 0);
        values.put(EVENT_ID, eventID);
        values.put(SEVERITY_INDICATOR, 0);
        values.put(PROCEDURE_INDICATOR, 0);
        values.put(EVALUATION_CASE, 0);
        values.put(EXCEPTION_CASE, 0);
        values.put(HIER3_ID, 0);
        values.put(HIER3_CELL_ID, 0);
        values.put(LAC, 0);
        values.put(RAC, 0);
        values.put(CAUSE_VALUE, 0);
        values.put(EXTENDED_CAUSE_VALUE, 0);
        values.put(EXCEPTION_CLASS, 0);
        values.put(DATETIME_ID, event_time);
        values.put(ROAMING, roaming);
        values.put(IMSI_MCC, imsi_mcc);
        values.put(IMSI_MNC, imsi_mnc);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, values);
    }

    private void createRawTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(EVENT_TIME_COLUMN);
        columns.add(IMSI);
        columns.add(TAC);
        columns.add(EVENT_ID);
        columns.add(SEVERITY_INDICATOR);
        columns.add(PROCEDURE_INDICATOR);
        columns.add(EVALUATION_CASE);
        columns.add(EXCEPTION_CASE);
        columns.add(EXCEPTION_CLASS);
        columns.add(HIER3_ID);
        columns.add(CAUSE_VALUE);
        columns.add(EXTENDED_CAUSE_VALUE);
        columns.add(HIER3_CELL_ID);
        columns.add(LAC);
        columns.add(RAC);
        columns.add(DATETIME_ID);
        columns.add(ROAMING);
        columns.add(IMSI_MCC);
        columns.add(IMSI_MNC);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, columns);
    }

    @Test
    public void testGetCountryRoamingData_5Minutes() throws Exception {
        final MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
        parameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        parameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        parameters.putSingle(MCC, MCC_FOR_ARGENTINA);
        parameters.putSingle(MNC_PARAM, AWWC_MNC);
        parameters.putSingle(EVENT_ID_PARAM, "438");
        parameters.putSingle(MAX_ROWS, "200");
        final String result = runQuery(callFailureOperatorDrillDetailRoamingService, parameters);
        validateResult(result);
    }

    @SuppressWarnings("unchecked")
    private void validateResult(final String json) throws Exception {
        final List<CallFailurCountryDrilDetaillRoamingQueryResult> results = getTranslator().translateResult(json,
                CallFailurCountryDrilDetaillRoamingQueryResult.class);
        assertThat(results.size(), is(3));

        final CallFailurCountryDrilDetaillRoamingQueryResult result1 = results.get(0);
        assertThat(result1.getImsi(), anyOf(is("1"), is("2"), is("3")));
        assertThat(result1.getCauseValue(), is("CAUSE_VALUE_NOT_APPLICABLE"));
        assertThat(result1.getEvaludationCase(), is("NOT_APPLICABLE"));
        assertThat(result1.getProcedureIndicator(), is("NOT_APPLICABLE"));
        assertThat(result1.getEventTypeDesc(), is("Call Drops"));
        assertThat(result1.getExceptionClass(), is("PROCEDURE_EXECUTION_SUCCESSFUL"));

        final CallFailurCountryDrilDetaillRoamingQueryResult result2 = results.get(1);
        assertThat(result2.getImsi(), anyOf(is("1"), is("2"), is("3")));
        assertThat(result2.getCauseValue(), is("CAUSE_VALUE_NOT_APPLICABLE"));
        assertThat(result2.getEvaludationCase(), is("NOT_APPLICABLE"));
        assertThat(result2.getProcedureIndicator(), is("NOT_APPLICABLE"));
        assertThat(result2.getEventTypeDesc(), is("Call Drops"));
        assertThat(result2.getExceptionClass(), is("PROCEDURE_EXECUTION_SUCCESSFUL"));
    }
}
