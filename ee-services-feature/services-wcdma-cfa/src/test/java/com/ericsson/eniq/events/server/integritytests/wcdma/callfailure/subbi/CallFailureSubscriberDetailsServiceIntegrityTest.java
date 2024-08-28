/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.subbi;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.subbi.CallFailureSubscriberDetailsService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailureSubscriberDetailsQueryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
public class CallFailureSubscriberDetailsServiceIntegrityTest extends
        BaseDataIntegrityTest<CallFailureSubscriberDetailsQueryResult> {

    private CallFailureSubscriberDetailsService subscriberDetailsService;

    private final String cellId = "RNC01-3-1";

    private final int lastHashedCellId = 1234;

    private String firstEventDate;

    private String lastEventDate;

    private String firstEventDateExpectedWithOffSet;

    private String lastEventDateExpectedWithOffSet;

    @Before
    public void setup() throws Exception {
        subscriberDetailsService = new CallFailureSubscriberDetailsService();
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_SGEH_MCCMNC);
        attachDependencies(subscriberDetailsService);
        createStaticTables();
        createTopologyTable();
        insertTopologyData();
        createRawTable();
        insertRawData();
    }

    private void insertRawData() throws SQLException {

        firstEventDate = DateTimeUtilities.getDateTimeMinus48Hours();
        firstEventDateExpectedWithOffSet = DateTimeUtilities.getDateTimeMinus48HoursWithOffSet(1);

        final String mediumEventDate = DateTimeUtilities.getDateTimeMinus36Hours(); //this shouldn't be included in the result

        lastEventDate = DateTimeUtilities.getDateTimeMinus5Minutes();
        lastEventDateExpectedWithOffSet = DateTimeUtilities.getDateTimeMinus5MinutesWithOffSet(1);

        final int anotherCell = 3456;
        insertRowIntoRawTableWithKnownValues(SAMPLE_IMSI, anotherCell, firstEventDate);
        insertRowIntoRawTableWithKnownValues(SAMPLE_IMSI, lastHashedCellId, mediumEventDate);
        insertRowIntoRawTableWithKnownValues(SAMPLE_IMSI, anotherCell, mediumEventDate);
        insertRowIntoRawTableWithKnownValues(SAMPLE_IMSI, lastHashedCellId, lastEventDate);

        insertRowIntoRawTableWithUnknownValues(SAMPLE_IMSI_2, firstEventDate);
        insertRowIntoRawTableWithUnknownValues(SAMPLE_IMSI_2, lastEventDate);

    }

    private void insertRowIntoRawTableWithUnknownValues(final long imsi, final String timestamp) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_TAC);
        values.put(IMSI, imsi);
        values.put(HIER3_CELL_ID, lastHashedCellId);
        values.put(IMSI_MCC, UNKNOWN_MCC);
        values.put(IMSI_MNC, UNKNOWN_MNC);
        values.put(ROAMING, 1);
        values.put(EVENT_TIME, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, values);

    }

    private void insertRowIntoRawTableWithKnownValues(final long imsi, final int hashedCellId, final String timestamp)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(TAC, SAMPLE_TAC);
        values.put(IMSI, imsi);
        values.put(HIER3_CELL_ID, hashedCellId);
        values.put(IMSI_MCC, SAMPLE_MCC);
        values.put(IMSI_MNC, SAMPLE_MNC);
        values.put(ROAMING, 0);
        values.put(EVENT_TIME, timestamp);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, values);
    }

    private void insertTopologyData() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        values.put(CELL_ID, cellId);
        values.put(HIERARCHY_3, SAMPLE_RNC);
        values.put(VENDOR, ERICSSON);
        values.put(HIER3_CELL_ID, lastHashedCellId);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, values);

    }

    private void createTopologyTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(HIER3_CELL_ID);
        columns.add(CELL_ID);
        columns.add(HIERARCHY_3);
        columns.add(RAT);
        columns.add(VENDOR);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columns);

    }

    private void createRawTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(TAC);
        columns.add(HIER3_CELL_ID);
        columns.add(IMSI);
        columns.add(IMSI_MCC);
        columns.add(IMSI_MNC);
        columns.add(ROAMING);
        columns.add(EVENT_TIME);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, columns);
    }

    private void createStaticTables() throws Exception {
        createAndPopulateLookupTable(TEMP_DIM_E_IMSI_MSISDN);
        createAndPopulateLookupTable(TEMP_DIM_E_SGEH_MCCMNC);
    }

    @Test
    public void testGetSubscriberDetails_WithUnknownMSISDNAndCountry() throws Exception {
        final String result = runSubscriberDetailsQuery(Long.toString(SAMPLE_IMSI_2));
        validateResultForSubscriberWithMissingValues(result);
    }

    private void validateResultForSubscriberWithMissingValues(final String json) throws Exception {
        final List<CallFailureSubscriberDetailsQueryResult> results = getTranslator().translateResult(json,
                CallFailureSubscriberDetailsQueryResult.class);
        assertThat(results.size(), is(1));
        final CallFailureSubscriberDetailsQueryResult subscriberDetails = results.get(0);
        assertThat(subscriberDetails.getMappedMSISDN(), is(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UNKNOWN));
        assertThat(subscriberDetails.getHomeCountry(), is(UNKNOWN_MCC));
        assertThat(subscriberDetails.getMobileNetworkOperator(), is(UNKNOWN_MNC));
        assertThat(subscriberDetails.getRoamingStatus(), is(ROAMING_FORMATTED_FOR_DISPLAY));
        assertThat(subscriberDetails.getLastCell(), is(cellId + COMMA + SAMPLE_RNC + COMMA + ERICSSON + COMMA + _3G));
        assertThat(subscriberDetails.getLastRNC(), is(SAMPLE_RNC + COMMA + ERICSSON + COMMA + _3G));
        assertThat(subscriberDetails.getFirstEventDate(), is(appendZeroMilliseconds(firstEventDateExpectedWithOffSet)));
        assertThat(subscriberDetails.getLastEventDate(), is(appendZeroMilliseconds(lastEventDateExpectedWithOffSet)));
    }

    @Test
    public void testGetSubscriberDetails_WithAllValuesKnown() throws Exception {
        final String result = runSubscriberDetailsQuery(SAMPLE_IMSI_AS_STRING);
        validateResultForSubscriberWithAllValuesKnown(result);
    }

    private String runSubscriberDetailsQuery(final String imsi) {
        final MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
        parameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        parameters.putSingle(IMSI_PARAM, imsi);
        parameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        parameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        return runQuery(subscriberDetailsService, parameters);
    }

    private void validateResultForSubscriberWithAllValuesKnown(final String json) throws Exception {
        final List<CallFailureSubscriberDetailsQueryResult> results = getTranslator().translateResult(json,
                CallFailureSubscriberDetailsQueryResult.class);
        assertThat(results.size(), is(1));
        final CallFailureSubscriberDetailsQueryResult subscriberDetails = results.get(0);
        assertThat(subscriberDetails.getMappedMSISDN(), is(Integer.toString(SAMPLE_MSISDN)));
        assertThat(subscriberDetails.getHomeCountry(), is(AFGHANISTAN));
        assertThat(subscriberDetails.getMobileNetworkOperator(), is(AFGHAN_TELECOM));
        assertThat(subscriberDetails.getRoamingStatus(), is(HOME));
        assertThat(subscriberDetails.getLastCell(), is(cellId + COMMA + SAMPLE_RNC + COMMA + ERICSSON + COMMA + _3G));
        assertThat(subscriberDetails.getLastRNC(), is(SAMPLE_RNC + COMMA + ERICSSON + COMMA + _3G));
        assertThat(subscriberDetails.getFirstEventDate(), is(appendZeroMilliseconds(firstEventDateExpectedWithOffSet)));
        assertThat(subscriberDetails.getLastEventDate(), is(appendZeroMilliseconds(lastEventDateExpectedWithOffSet)));
    }
}
