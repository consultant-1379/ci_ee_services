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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.roaminganalysis.CallFailureCountryDrillRoamingAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureCountryDrillRoamingAnalysisQueryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ezhelao
 *
 */
public class CallFailureCountryDrillRoamingServiceRawTest extends
        BaseDataIntegrityTest<CallFailureCountryDrillRoamingAnalysisQueryResult> {

    private CallFailureCountryDrillRoamingAnalysisService callFailureCountryDrillRoamingAnalysisService;

    @Before
    public void setup() throws Exception {
        callFailureCountryDrillRoamingAnalysisService = new CallFailureCountryDrillRoamingAnalysisService();
        attachDependencies(callFailureCountryDrillRoamingAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_SGEH_MCCMNC);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EVENTTYPE);
        createRawTable();
        insertRawData();
        createAndPopulateTempLookupTable(DIM_E_SGEH_MCCMNC);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EVENTTYPE);
    }

    private void insertRawData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertRowIntoRawTable(1, MCC_FOR_ARGENTINA, 1, timestamp, 438);
        insertRowIntoRawTable(2, MCC_FOR_ARGENTINA, 1, timestamp, 438);
        insertRowIntoRawTable(2, MCC_FOR_ARGENTINA, 1, timestamp, 438);
        insertRowIntoRawTable(3, MCC_FOR_ARGENTINA, 1, timestamp, 456);
        insertRowIntoRawTable(4, MCC_FOR_ARGENTINA, 1, timestamp, 456);
        insertRowIntoRawTable(4, MCC_FOR_ARGENTINA, 0, timestamp, 456);

        //this event shouldn't be included in the result - per MZ, if the IMSI_MCC cannot be determined,
        //the roaming column will never be set to 1
        insertRowIntoRawTable(SAMPLE_IMSI, null, 0, timestamp, 3);

    }

    private void insertRowIntoRawTable(final long imsi, final String mcc, final int roamingValue,
            final String timestamp, final int eventID) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, imsi);
        values.put(IMSI_MCC, mcc);
        values.put(ROAMING, roamingValue);
        values.put(TAC, 12312);
        values.put(DATETIME_ID, timestamp);
        values.put(EVENT_ID, eventID);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, values);
    }

    private void createRawTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(IMSI_MCC);
        columns.add(ROAMING);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, columns);
    }

    @Test
    public void testGetCountryRoamingData_5Minutes() throws Exception {
        final MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
        parameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        parameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        parameters.putSingle(MCC_PARAM, "722");
        final String result = runQuery(callFailureCountryDrillRoamingAnalysisService, parameters);
        validateResult(result);
    }

    private void validateResult(final String json) throws Exception {
        final List<CallFailureCountryDrillRoamingAnalysisQueryResult> results = getTranslator().translateResult(json,
                CallFailureCountryDrillRoamingAnalysisQueryResult.class);
        assertThat(results.size(), is(2));

        final CallFailureCountryDrillRoamingAnalysisQueryResult countryWithMostRoamingErrors = results.get(0);
        assertThat(countryWithMostRoamingErrors.getCountryName(), is(ARGENTINA));
        assertThat(countryWithMostRoamingErrors.getNumErrors(), is(3));
        assertThat(countryWithMostRoamingErrors.getImpactedSubscribers(), is(2));
        assertThat(countryWithMostRoamingErrors.getEventIDDesc(), is("Call Drops"));
        assertThat(countryWithMostRoamingErrors.getMcc(), is("722"));

        final CallFailureCountryDrillRoamingAnalysisQueryResult countryWithSecondMostRoamingErrors = results.get(1);
        assertThat(countryWithSecondMostRoamingErrors.getCountryName(), is(ARGENTINA));
        assertThat(countryWithSecondMostRoamingErrors.getNumErrors(), is(2));
        assertThat(countryWithSecondMostRoamingErrors.getImpactedSubscribers(), is(2));
        assertThat(countryWithSecondMostRoamingErrors.getEventIDDesc(), is("Call Setup Failures"));
        assertThat(countryWithSecondMostRoamingErrors.getMcc(), is("722"));

    }
}
