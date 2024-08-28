/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.callfailure.roaming;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.gsm.callfailure.roaming.CountryRoamingSummaryService;
import com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure.CountryRoamingSummaryQueryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eatiaro
 * 2012
 *
 */
public class CountryRoamingSummaryRawTest extends BaseDataIntegrityTest<CountryRoamingSummaryQueryResult> {

    private CountryRoamingSummaryService roamingAnalysisService;

    @Before
    public void setup() throws Exception {
        roamingAnalysisService = new CountryRoamingSummaryService();
        attachDependencies(roamingAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_SGEH_MCCMNC);
        createRawTable();
        insertRawData();
        createAndPopulateLookupTable(TEMP_DIM_E_SGEH_MCCMNC);
    }

    private void insertRawData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertRowIntoRawTable(SAMPLE_IMSI, MCC_FOR_ARGENTINA, 1, timestamp, SAMPLE_TAC);
        insertRowIntoRawTable(SAMPLE_IMSI, MCC_FOR_ARGENTINA, 1, timestamp, SAMPLE_TAC);
        insertRowIntoRawTable(SAMPLE_IMSI_2, MCC_FOR_ARGENTINA, 1, timestamp, SAMPLE_TAC);

        insertRowIntoRawTable(SAMPLE_IMSI, MCC_FOR_AFGHANISTAN, 1, timestamp, SAMPLE_TAC);
        insertRowIntoRawTable(SAMPLE_IMSI, MCC_FOR_AFGHANISTAN, 1, timestamp, SAMPLE_TAC);

        //exclusive TAC event
        insertRowIntoRawTable(SAMPLE_IMSI, MCC_FOR_AFGHANISTAN, 1, timestamp, SAMPLE_EXCLUSIVE_TAC);

        //Out of range event
        insertRowIntoRawTable(SAMPLE_IMSI, MCC_FOR_ARGENTINA, 1, DateTimeUtilities.getDateTimeMinus30Minutes(),
                SAMPLE_TAC);

        //this event shouldn't be included in the result - per MZ, if the IMSI_MCC cannot be determined,
        //the roaming column will never be set to 1
        insertRowIntoRawTable(SAMPLE_IMSI, null, 0, timestamp, SAMPLE_TAC);

    }

    private void insertRowIntoRawTable(final long imsi, final String mcc, final int roamingValue,
            final String timestamp, final int tac) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, imsi);
        values.put(IMSI_MCC, mcc);
        values.put(ROAMING, roamingValue);
        values.put(TAC, tac);
        values.put(DATETIME_ID, timestamp);
        insertRow(TEMP_EVENT_E_GSM_CFA_ERR_RAW, values);
    }

    private void createRawTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(IMSI_MCC);
        columns.add(ROAMING);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        createTemporaryTable(TEMP_EVENT_E_GSM_CFA_ERR_RAW, columns);
    }

    @Test
    public void testGetCountryRoamingDataFiveMinutes() throws Exception {
        final MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
        parameters.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        parameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        final String result = runQuery(roamingAnalysisService, parameters);
        validateResult(result);
    }

    private void validateResult(final String json) throws Exception {
        validateAgainstGridDefinition(json, "ROAMING_ANALYSIS_RAN_GSM_COUNTRY");
        final List<CountryRoamingSummaryQueryResult> results = getTranslator().translateResult(json,
                CountryRoamingSummaryQueryResult.class);
        assertThat(results.size(), is(2));

        final CountryRoamingSummaryQueryResult countryWithMostRoamingErrors = results.get(0);
        assertThat(countryWithMostRoamingErrors.getCountry(), is(ARGENTINA));
        assertThat(countryWithMostRoamingErrors.getNumErrors(), is(3));
        assertThat(countryWithMostRoamingErrors.getNumRoamers(), is(2));
        assertThat(countryWithMostRoamingErrors.getMCC(), is(MCC_FOR_ARGENTINA));

        final CountryRoamingSummaryQueryResult countryWithSecondMostRoamingErrors = results.get(1);
        assertThat(countryWithSecondMostRoamingErrors.getCountry(), is(AFGHANISTAN));
        assertThat(countryWithSecondMostRoamingErrors.getNumErrors(), is(2));
        assertThat(countryWithSecondMostRoamingErrors.getNumRoamers(), is(1));
        assertThat(countryWithSecondMostRoamingErrors.getMCC(), is(MCC_FOR_AFGHANISTAN));

    }
}
