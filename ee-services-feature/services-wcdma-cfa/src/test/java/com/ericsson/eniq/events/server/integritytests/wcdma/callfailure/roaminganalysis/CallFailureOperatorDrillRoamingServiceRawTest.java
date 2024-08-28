/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.roaminganalysis;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.roaminganalysis.CallFailureOperatorDrillRoamingAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.CallFailurOperatorDrillRoamingAnalysisQueryResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_ERR_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author ezhelao
 *
 */
public class CallFailureOperatorDrillRoamingServiceRawTest extends
        BaseDataIntegrityTest<CallFailurOperatorDrillRoamingAnalysisQueryResult> {

    private CallFailureOperatorDrillRoamingAnalysisService callFailureOperatorDrillRoamingAnalysisService;
    private static final String  SAMPLE_MNC="010";

    @Before
    public void setup() throws Exception {
        callFailureOperatorDrillRoamingAnalysisService = new CallFailureOperatorDrillRoamingAnalysisService();
        attachDependencies(callFailureOperatorDrillRoamingAnalysisService);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_SGEH_MCCMNC);
        ReplaceTablesWithTempTablesTemplateUtils.useTemporaryTableFor(DIM_E_RAN_CFA_EVENTTYPE);
        createRawTable();
        insertRawData();
        createAndPopulateTempLookupTable(DIM_E_SGEH_MCCMNC);
        createAndPopulateTempLookupTable(DIM_E_RAN_CFA_EVENTTYPE);
    }

    private void insertRawData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus2Minutes();

        insertRowIntoRawTable(1, MCC_FOR_ARGENTINA,SAMPLE_MNC, 1, timestamp,438);
        insertRowIntoRawTable(2, MCC_FOR_ARGENTINA,SAMPLE_MNC, 1, timestamp,438);
        insertRowIntoRawTable(2, MCC_FOR_ARGENTINA, SAMPLE_MNC,1, timestamp,438);
        insertRowIntoRawTable(3, MCC_FOR_ARGENTINA, SAMPLE_MNC,1, timestamp,456);
        insertRowIntoRawTable(4, MCC_FOR_ARGENTINA,SAMPLE_MNC, 1, timestamp,456);
        insertRowIntoRawTable(4, MCC_FOR_ARGENTINA, SAMPLE_MNC,0, timestamp,456);



        //this event shouldn't be included in the result - per MZ, if the IMSI_MCC cannot be determined,
        //the roaming column will never be set to 1
        insertRowIntoRawTable(SAMPLE_IMSI, null,SAMPLE_MNC, 0, timestamp,3);

    }

    private void insertRowIntoRawTable(final long imsi, final String imsi_mcc, String imsi_mnc,final int roamingValue, final String timestamp,int eventID)
            throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, imsi);
        values.put(IMSI_MCC, imsi_mcc);
        values.put(IMSI_MNC,imsi_mnc);
        values.put(ROAMING, roamingValue);
        values.put(TAC, 12312);
        values.put(DATETIME_ID, timestamp);
        values.put(EVENT_ID,eventID);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, values);
    }

    private void createRawTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(IMSI_MCC);
        columns.add(IMSI_MNC);
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
        parameters.putSingle(MCC,MCC_FOR_ARGENTINA);
        parameters.putSingle(MNC,SAMPLE_MNC);
        final String result = runQuery(callFailureOperatorDrillRoamingAnalysisService, parameters);
        validateResult(result);
    }

    private void validateResult(final String json) throws Exception {
        final List<CallFailurOperatorDrillRoamingAnalysisQueryResult> results = getTranslator().translateResult(json,
                CallFailurOperatorDrillRoamingAnalysisQueryResult.class);
        assertThat(results.size(), is(2));

        final CallFailurOperatorDrillRoamingAnalysisQueryResult operatorWithMostRoamingErrors = results.get(0);
        assertThat(operatorWithMostRoamingErrors.getOperatorName(), is(MOVISTAR));
        assertThat(operatorWithMostRoamingErrors.getNumErrors(), is(3));
        assertThat(operatorWithMostRoamingErrors.getImpactedSubscribers(), is(2));
        assertThat(operatorWithMostRoamingErrors.getEventIDDesc(), is("Call Drops"));
        assertThat(operatorWithMostRoamingErrors.getMcc(),is("722"));
        assertThat(operatorWithMostRoamingErrors.getMnc(),is("010"));

        final CallFailurOperatorDrillRoamingAnalysisQueryResult operatorWithSecondMostRoamingErrors = results.get(1);
        assertThat(operatorWithSecondMostRoamingErrors.getOperatorName(), is(MOVISTAR));
        assertThat(operatorWithSecondMostRoamingErrors.getNumErrors(), is(2));
        assertThat(operatorWithSecondMostRoamingErrors.getImpactedSubscribers(), is(2));
        assertThat(operatorWithSecondMostRoamingErrors.getEventIDDesc(), is("Call Setup Failures"));
        assertThat(operatorWithMostRoamingErrors.getMcc(),is("722"));
        assertThat(operatorWithMostRoamingErrors.getMnc(),is("010"));
    }
}
