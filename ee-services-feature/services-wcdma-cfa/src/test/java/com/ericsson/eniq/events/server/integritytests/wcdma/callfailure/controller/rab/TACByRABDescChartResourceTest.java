/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.controller.rab;

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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rabanalysis.TACByRABDescChartResource;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.RABDescriptionChartResourceResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eadrhyn
 *
 */
public class TACByRABDescChartResourceTest extends
        BaseDataIntegrityTest<RABDescriptionChartResourceResult> {

    private TACByRABDescChartResource tacByRABType;
    private long hashedIdForRNC;
    private String TEST_TAC = "101800";

    @Before
    public void setup() throws Exception {
    	tacByRABType = new TACByRABDescChartResource();
        attachDependencies(tacByRABType);
        hashedIdForRNC = calculateHashedId(_3G, RNC01_ALTERNATIVE_FDN, ERICSSON);
        
        createTables();
        createTemporaryDIMTables();
        insertDataIntoTopologyTables();
        insertData();
    }

    private void createTemporaryDIMTables() throws Exception {
        final Collection<String> columnsForDIMRNCTable = new ArrayList<String>();
        columnsForDIMRNCTable.add(HIER3_ID);
        columnsForDIMRNCTable.add(HIER3_CELL_ID);
        columnsForDIMRNCTable.add(HIERARCHY_3);
        columnsForDIMRNCTable.add(VENDOR);
        columnsForDIMRNCTable.add(RAT);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, columnsForDIMRNCTable);
        
        final Collection<String> columnsForDIMRABTypeTable = new ArrayList<String>();
        columnsForDIMRABTypeTable.add(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAB_TYPE);
        columnsForDIMRABTypeTable.add(RAB_TYPE_DESC);
        columnsForDIMRABTypeTable.add(CATEGORY_ID_DESC); 
        createTemporaryTable(TEMP_DIM_E_RAN_RABTYPE, columnsForDIMRABTypeTable);
    }
    

    private void insertDataIntoTopologyTables() throws SQLException {
        final Map<String, Object> valuesForRncTable = new HashMap<String, Object>();
        valuesForRncTable.put(HIER3_ID, hashedIdForRNC);
        valuesForRncTable.put(HIER3_CELL_ID, hashedIdForRNC);
        valuesForRncTable.put(VENDOR, ERICSSON);
        valuesForRncTable.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        valuesForRncTable.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        insertRow(TEMP_DIM_E_SGEH_HIER321, valuesForRncTable);
        
        final Map<String, Object> valuesForRABTypeCodeRncTable = new HashMap<String, Object>();
        valuesForRABTypeCodeRncTable.put(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAB_TYPE, 1);        
        valuesForRABTypeCodeRncTable.put(RAB_TYPE_DESC, "CONVERSATIONAL_CS_12.2_12.2");
        valuesForRABTypeCodeRncTable.put(CATEGORY_ID_DESC, "Circuit Switch");
        insertRow(TEMP_DIM_E_RAN_RABTYPE, valuesForRABTypeCodeRncTable);
        valuesForRABTypeCodeRncTable.clear();
        valuesForRABTypeCodeRncTable.put(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAB_TYPE, 4);        
        valuesForRABTypeCodeRncTable.put(RAB_TYPE_DESC, "SRB_13.6_13.6");
        valuesForRABTypeCodeRncTable.put(CATEGORY_ID_DESC, "Circuit Switch");
        insertRow(TEMP_DIM_E_RAN_RABTYPE, valuesForRABTypeCodeRncTable);
        
        valuesForRABTypeCodeRncTable.clear();
        valuesForRABTypeCodeRncTable.put(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAB_TYPE, 2);        
        valuesForRABTypeCodeRncTable.put(RAB_TYPE_DESC, "INTERACTIVE_PS_64_384");
        valuesForRABTypeCodeRncTable.put(CATEGORY_ID_DESC, "Packet Switch");
        insertRow(TEMP_DIM_E_RAN_RABTYPE, valuesForRABTypeCodeRncTable);
        valuesForRABTypeCodeRncTable.clear();
        valuesForRABTypeCodeRncTable.put(com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAB_TYPE, 3);        
        valuesForRABTypeCodeRncTable.put(RAB_TYPE_DESC, "CONVERSATIONAL_CS_12.2_12.2-INTERACTIVE_PS_0_0");
        valuesForRABTypeCodeRncTable.put(CATEGORY_ID_DESC, "Multi RAB");
        insertRow(TEMP_DIM_E_RAN_RABTYPE, valuesForRABTypeCodeRncTable);

    }
    
    private void insertData() throws SQLException {
        final String timestamp1 = DateTimeUtilities.getDateTimeMinus30Minutes();
        final String timestamp2 = DateTimeUtilities.getDateTimeMinus35Minutes();
        
        final String timestamp3 = DateTimeUtilities.getDateTimeMinusDay(7);

        final int circuitSwitchType1 = 1;
        final int circuitSwitchType2 = 4;
        final int packetSwitchType = 2;
        final int multiRABType = 3;
        final int unknownType = 5;
        
        
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1, circuitSwitchType1);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1, packetSwitchType);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, multiRABType);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, circuitSwitchType2);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, unknownType);
        insertRowIntoEventTable(CALL_DROP_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp2, circuitSwitchType1);

        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp1, circuitSwitchType1);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp2, circuitSwitchType1);
        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_EXCLUSIVE_TAC, timestamp2, circuitSwitchType1);

        insertRowIntoEventTable(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER, SAMPLE_TAC, timestamp3, circuitSwitchType1);
    }

    private void insertRowIntoEventTable(final int eventId, final int tac, final String timestamp, final int rabType) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, SAMPLE_IMSI_AS_STRING);
        values.put(EVENT_ID, eventId);
        values.put(TAC, tac);
        values.put(DATETIME_ID, timestamp);
        values.put(HIER3_ID, hashedIdForRNC);
        values.put(HIER3_CELL_ID, hashedIdForRNC);
        values.put(SOURCE_CONF, rabType);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, values);
        final Map<String, Object> valuesForGroupTable = new HashMap<String, Object>();
        valuesForGroupTable.put(GROUP_NAME_KEY, TEST_VALUE_IMSI_GROUP);
        valuesForGroupTable.put(IMSI, SAMPLE_IMSI_AS_STRING);
        insertRow(TEMP_GROUP_TYPE_E_IMSI, valuesForGroupTable);
    }

    private void createTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI);
        columns.add(EVENT_ID);
        columns.add(TAC);
        columns.add(DATETIME_ID);
        columns.add(HIER3_ID);
        columns.add(HIER3_CELL_ID);
        columns.add(SOURCE_CONF);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columns);
        createAndPopulateLookupTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE);
        final Collection<String> columnsForGroupTable = new ArrayList<String>();
        columnsForGroupTable.add(GROUP_NAME_KEY);
        columnsForGroupTable.add(IMSI);
        createTemporaryTable(TEMP_GROUP_TYPE_E_IMSI, columnsForGroupTable);
    }

    @Test
    public void testGetFailures_ThirtyMinutes_CircuitRABType() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);  //THIRTY_MINUTES   TWO_WEEKS
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(com.ericsson.eniq.events.server.common.ApplicationConstants.RAB_TYPE, "Circuit Switch");
        requestParameters.putSingle(TAC, TEST_TAC);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(TYPE_PARAM, "TAC");
        final String result = runQuery(tacByRABType, requestParameters);
        validateResultCircuitRABType(result);
    }

    @Test
    public void testGetFailures_ThirtyMinutes_UnknownRABType() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES); 
        requestParameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        requestParameters.putSingle(com.ericsson.eniq.events.server.common.ApplicationConstants.RAB_TYPE, "Unknown");
        requestParameters.putSingle(TAC, TEST_TAC);
        requestParameters.putSingle(DISPLAY_PARAM, CHART_PARAM);
        requestParameters.putSingle(TYPE_PARAM, "TAC");
        
        final String result = runQuery(tacByRABType, requestParameters);
        validateResultUnknownRABType(result);
    }    
    
    private void validateResultCircuitRABType(final String json) throws Exception {
        final List<RABDescriptionChartResourceResult> results = getTranslator().translateResult(json,
        		RABDescriptionChartResourceResult.class);
        validateAgainstGridDefinition(json, "DRILL_CHART_RNC_DISCONNECTION_CODE_FROM_DRILL_BY");
        assertThat(results.size(), is(2));

        final RABDescriptionChartResourceResult resultForCallDropsEvent = results.get(0);
        assertThat(resultForCallDropsEvent.getNoFailures(), is(3));
        assertThat(resultForCallDropsEvent.getSubscribers(), is(1));
        assertEquals(resultForCallDropsEvent.getRabTypeDesc(), "CONVERSATIONAL_CS_12.2_12.2");
    }
    
    private void validateResultUnknownRABType(final String json) throws Exception {
        final List<RABDescriptionChartResourceResult> results = getTranslator().translateResult(json,
        		RABDescriptionChartResourceResult.class);
        validateAgainstGridDefinition(json, "DRILL_CHART_RNC_DISCONNECTION_CODE_FROM_DRILL_BY");
        assertThat(results.size(), is(1));

        final RABDescriptionChartResourceResult resultForCallDropsEvent = results.get(0);
        assertThat(resultForCallDropsEvent.getNoFailures(), is(1));
        assertThat(resultForCallDropsEvent.getSubscribers(), is(1));
        assertEquals(resultForCallDropsEvent.getRabTypeDesc(), "Unknown");
    }    
}
