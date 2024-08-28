/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.accessarea;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CELL;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EXCLUSIVE_TAC_GROUP_NAME;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID_AGG;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAT_INTEGER_VALUE_FOR_3G;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID;
import static com.ericsson.eniq.events.server.common.EventIDConstants.CALL_DROP_EVENT_ID_AS_INTEGER;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CAUSE_VALUE_NOT_APPLICABLE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CELL_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CPICH_EC_NO_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CRNC_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_SERV_HSDSCH_CELL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DATA_IN_DL_RLC_BUFFERS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ERICSSON;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVALUATION_CASE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVENT_TIME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXCEPTION_CLASS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXTENDED_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EXTENDED_CAUSE_VALUE_NOT_APPLICABLE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_DL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GBR_UL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GRID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GROUP_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.HIERARCHY_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.HIERARCHY_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.LAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MANUFACTURER_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.MARKETING_NAME_FOR_SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.NOT_APPLICABLE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.PROCEDURE_EXECUTION_SUCCESSFUL;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.PROCEDURE_INDICATOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAN_DISCONNECTION_CODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAN_DISCONNECTION_SUBCODE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RAT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RECOVERABLE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RNC01_ALTERNATIVE_FDN;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RSCP_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_EXCLUSIVE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_IMSI;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_TAC_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SCRAMBLING_CODE_CELL_4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SEVERITY_INDICATOR;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SOURCE_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TARGET_CONF;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TZ_OFFSET_OF_ZERO;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL3;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UL_INT_CELL4;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.VENDOR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_DISCONNECTION;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EVALUATION_CASE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EVENTTYPE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EXCEPTION_CLASS;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_EXTENDED_CAUSE_VALUE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_PROCEDURE_INDICATOR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_SEVERITY_INDICATOR;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ECNO_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RABTYPE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RSCP_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ULINT_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_HIER321_CELL;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_SGEH_TAC;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_RAN_CFA_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_GROUP_TYPE_E_TAC;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.NetworkDetailedEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.WCDMACallFailureNetworkDEAResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ebrowpa
 * @since 2011
 *
 */
public class AccessAreaCallFailureDEARawTest extends BaseDataIntegrityTest<WCDMACallFailureNetworkDEAResult> {
    private NetworkDetailedEventAnalysisService service;

    private long cellHashID;

    @Before
    public void setup() throws Exception {
        createHashIds();
        service = new NetworkDetailedEventAnalysisService();
        attachDependencies(service);
        createEventTable();
        createAndPopulateLookupTables();
        insertDataIntoExclusiveTacGroupTables();
        insertEventData(SAMPLE_TAC);
        insertEventData(SAMPLE_TAC_2);
        //This result should not appear in the result set as it is an Exclusive Tac
        insertEventData(SAMPLE_EXCLUSIVE_TAC);
    }

    @Test
    public void testFiveMinuteQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.add(EVENT_ID_PARAM, CALL_DROP_EVENT_ID);
        requestParameters.add(DISPLAY_PARAM, GRID);

        requestParameters.add(HIER3_CELL_ID, Long.toString(cellHashID));
        requestParameters.add(MAX_ROWS, "30");
        requestParameters.add(TYPE_PARAM, CELL);
        final String result = runQuery(service, requestParameters);
        verifyResult(result);
    }

    /**
     * Verify that the result is correct.
     * @param json
     * @throws Exception
     */
    private void verifyResult(final String json) throws Exception {
        assertJSONSucceeds(json);
        validateAgainstGridDefinition(json, "NETWORK_DETAILED_EVENT_ANALYSIS_WCDMA_CFA_CELL_CALL_DROPS");
        validateAgainstGridDefinition(json, "NETWORK_DETAILED_EVENT_ANALYSIS_WCDMA_CFA_CELL_CALL_SETUP");
        final List<WCDMACallFailureNetworkDEAResult> results = getTranslator().translateResult(json,
                WCDMACallFailureNetworkDEAResult.class);

        assertThat(results.size(), is(4));
        for (final WCDMACallFailureNetworkDEAResult result : results) {

            assertThat(result.getCauseValue(), is(CAUSE_VALUE_NOT_APPLICABLE));
            assertThat(result.getEvaluationCase(), is(NOT_APPLICABLE));
            assertThat(result.getExceptionClass(), is(PROCEDURE_EXECUTION_SUCCESSFUL));
            assertThat(result.getImsi(), is(SAMPLE_IMSI));
            assertThat(result.getProcedureIndicator(), is(NOT_APPLICABLE));
            assertThat(result.getExtendedCauseValue(), is(EXTENDED_CAUSE_VALUE_NOT_APPLICABLE));
            assertThat(result.getSeverityIndicator(), is(RECOVERABLE));
            if (result.getTac() == SAMPLE_TAC) {
                assertThat(result.getTerminalMake(), is(MANUFACTURER_FOR_SAMPLE_TAC));
                assertThat(result.getTerminalModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC));
            } else if (result.getTac() == SAMPLE_TAC_2) {
                assertThat(result.getTerminalMake(), is("Apple Inc"));
                assertThat(result.getTerminalModel(), is(MARKETING_NAME_FOR_SAMPLE_TAC_2));
            } else {
                fail("Got an unexpected Tac value");
            }

            assertThat(result.getSourceConf(), is("SRB (13.6/13.6)"));
            assertThat(result.getCpich_ec_no_cell_1(), is(-24.0));
            assertThat(result.getUl_int_cell1(), is(-111.9));
            assertThat(result.getRscp_cell_1(), is(-115.0));
            assertThat(result.getTarget_conf(), is("SRB (13.6/13.6)"));
            assertThat(result.getC_id_serv_hsdsch_cell(), is("Cell_2"));
            assertThat(result.getCrnc_id_serv_hsdsch_cell(), is("ONRM_ROOT_MO_R:RNC02:RNC02"));
            assertThat(result.getRscp_cell_2(), is(-115.0));
            assertThat(result.getRscp_cell_3(), is(-115.0));
            assertThat(result.getRscp_cell_4(), is(-115.0));
            assertThat(result.getCpich_ec_no_cell_2(), is(-24.0));
            assertThat(result.getCpich_ec_no_cell_3(), is(-24.0));
            assertThat(result.getCpich_ec_no_cell_4(), is(-24.0));
            assertThat(result.getUl_int_cell2(), is(-111.9));
            assertThat(result.getUl_int_cell3(), is(-111.9));
            assertThat(result.getUl_int_cell4(), is(-111.9));
            assertThat(result.getScrambling_code_cell_1(), is(1));
            assertThat(result.getScrambling_code_cell_2(), is(1));
            assertThat(result.getScrambling_code_cell_3(), is(1));
            assertThat(result.getScrambling_code_cell_4(), is(1));
            assertThat(result.getGbr_ul(), is(1));
            assertThat(result.getGbr_dl(), is(1));
            assertThat(result.getUtran_ranap_cause(), is("RAB pre-empted"));
            assertThat(result.getData_in_dl_rlc_buffers(), is(1));
        }
    }

    /**
     * Insert data into the raw event table
     * @param tac
     * @throws Exception
     */
    private void insertEventData(final int tac) throws Exception {
        final Map<String, Object> dataForEventTable = new HashMap<String, Object>();
        final String time = DateTimeUtilities.getDateTimeMinus25Minutes();
        //Populate default lookup values
        dataForEventTable.putAll(getDefaultLookupValues());

        dataForEventTable.put(EVENT_TIME, time);
        dataForEventTable.put(IMSI, SAMPLE_IMSI);
        dataForEventTable.put(TAC, tac);
        dataForEventTable.put(EVENT_ID, CALL_DROP_EVENT_ID_AS_INTEGER);
        dataForEventTable.put(DATETIME_ID, time);

        dataForEventTable.put(SOURCE_CONF, 1);
        dataForEventTable.put(CPICH_EC_NO_CELL_1, 1);
        dataForEventTable.put(UL_INT_CELL1, 1);
        dataForEventTable.put(RSCP_CELL_1, 1);
        dataForEventTable.put(TARGET_CONF, 1);
        dataForEventTable.put(C_ID_SERV_HSDSCH_CELL, 2);
        dataForEventTable.put(CRNC_ID_SERV_HSDSCH_CELL, 1);
        dataForEventTable.put(RSCP_CELL_2, 1);
        dataForEventTable.put(RSCP_CELL_3, 1);
        dataForEventTable.put(RSCP_CELL_4, 1);
        dataForEventTable.put(CPICH_EC_NO_CELL_2, 1);
        dataForEventTable.put(CPICH_EC_NO_CELL_3, 1);
        dataForEventTable.put(CPICH_EC_NO_CELL_4, 1);
        dataForEventTable.put(UL_INT_CELL2, 1);
        dataForEventTable.put(UL_INT_CELL3, 1);
        dataForEventTable.put(UL_INT_CELL4, 1);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_1, 1);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_2, 1);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_3, 1);
        dataForEventTable.put(SCRAMBLING_CODE_CELL_4, 1);
        dataForEventTable.put(GBR_UL, 1);
        dataForEventTable.put(GBR_DL, 1);
        dataForEventTable.put(UTRAN_RANAP_CAUSE, 1);
        dataForEventTable.put(DATA_IN_DL_RLC_BUFFERS, 1);
        dataForEventTable.put(RAN_DISCONNECTION_CODE, 1);
        dataForEventTable.put(RAN_DISCONNECTION_SUBCODE, 1);
        dataForEventTable.put(TRIGGER_POINT, 1);
        dataForEventTable.put(RRC_ESTABLISHMENT_CAUSE, 1);
        insertRow(TEMP_EVENT_E_RAN_CFA_RAW, dataForEventTable);
    }

    /**
     * Get the deault lookup values for the description lookup tables
     * @return
     */
    private Map<String, Object> getDefaultLookupValues() {
        final Map<String, Object> lookupValues = new HashMap<String, Object>();
        final int default_value = 0;
        lookupValues.put(SEVERITY_INDICATOR, default_value);
        lookupValues.put(PROCEDURE_INDICATOR, 0);
        lookupValues.put(EVALUATION_CASE, 0);
        lookupValues.put(EXCEPTION_CLASS, 0);
        lookupValues.put(CAUSE_VALUE, 0);
        lookupValues.put(EXTENDED_CAUSE_VALUE, 0);
        lookupValues.put(HIER3_CELL_ID, cellHashID);
        lookupValues.put(LAC, 0);
        lookupValues.put(RAC, 0);
        lookupValues.put(HIER3_ID, 0);
        return lookupValues;
    }

    /**
     * Create and populate the description lookup tables
     * @throws Exception
     */
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
        lookupTables.add(TEMP_DIM_E_RAN_RABTYPE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_ECNO_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_RSCP_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_ULINT_MAPPING);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_DISCONNECTION);
        lookupTables.add(TEMP_DIM_E_RAN_CFA_TRIGGER_POINT);
        for (final String lookupTableRequired : lookupTables) {
            createAndPopulateLookupTable(lookupTableRequired);
        }

        populateTopologyTable();
    }

    /**
     * populate the topology table DIM_E_SGEH_HIER321_CELL
     * @throws Exception
     *
     */
    private void populateTopologyTable() throws Exception {
        final Collection<String> columnsFor_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_1);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(HIER3_CELL_ID_AGG);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(RAT);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        columnsFor_DIM_E_SGEH_HIER321_CELL.add(CID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> rowData = new HashMap<String, Object>();
        rowData.put(HIERARCHY_3, RNC01_ALTERNATIVE_FDN);
        rowData.put(HIERARCHY_1, "1");
        rowData.put(HIER3_CELL_ID_AGG, cellHashID);
        rowData.put(VENDOR, ERICSSON);
        rowData.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        rowData.put(CELL_ID, "Cell");
        rowData.put(CID, 1);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, rowData);

        rowData.put(HIERARCHY_3, "ONRM_ROOT_MO_R:RNC02:RNC02");
        rowData.put(HIERARCHY_1, "1");
        rowData.put(HIER3_CELL_ID_AGG, cellHashID);
        rowData.put(VENDOR, ERICSSON);
        rowData.put(RAT, RAT_INTEGER_VALUE_FOR_3G);
        rowData.put(CELL_ID, "Cell_2");
        rowData.put(CID, 2);
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, rowData);

    }

    /**
     * Declare the raw event table
     * @throws Exception
     */
    private void createEventTable() throws Exception {
        final Collection<String> columnsForEventTable = new ArrayList<String>();
        columnsForEventTable.add(EVENT_TIME);
        columnsForEventTable.add(IMSI);
        columnsForEventTable.add(TAC);
        columnsForEventTable.add(EVENT_ID);
        columnsForEventTable.add(SEVERITY_INDICATOR);
        columnsForEventTable.add(PROCEDURE_INDICATOR);
        columnsForEventTable.add(EVALUATION_CASE);
        columnsForEventTable.add(EXCEPTION_CLASS);
        columnsForEventTable.add(CAUSE_VALUE);
        columnsForEventTable.add(EXTENDED_CAUSE_VALUE);
        columnsForEventTable.add(HIER3_CELL_ID);
        columnsForEventTable.add(LAC);
        columnsForEventTable.add(RAC);
        columnsForEventTable.add(DATETIME_ID);
        columnsForEventTable.add(HIER3_ID);

        columnsForEventTable.add(SOURCE_CONF);
        columnsForEventTable.add(CPICH_EC_NO_CELL_1);
        columnsForEventTable.add(UL_INT_CELL1);
        columnsForEventTable.add(RSCP_CELL_1);
        columnsForEventTable.add(TARGET_CONF);
        columnsForEventTable.add(C_ID_SERV_HSDSCH_CELL);
        columnsForEventTable.add(CRNC_ID_SERV_HSDSCH_CELL);
        columnsForEventTable.add(RSCP_CELL_2);
        columnsForEventTable.add(RSCP_CELL_3);
        columnsForEventTable.add(RSCP_CELL_4);
        columnsForEventTable.add(CPICH_EC_NO_CELL_2);
        columnsForEventTable.add(CPICH_EC_NO_CELL_3);
        columnsForEventTable.add(CPICH_EC_NO_CELL_4);
        columnsForEventTable.add(UL_INT_CELL2);
        columnsForEventTable.add(UL_INT_CELL3);
        columnsForEventTable.add(UL_INT_CELL4);
        columnsForEventTable.add(SCRAMBLING_CODE_CELL_1);
        columnsForEventTable.add(SCRAMBLING_CODE_CELL_2);
        columnsForEventTable.add(SCRAMBLING_CODE_CELL_3);
        columnsForEventTable.add(SCRAMBLING_CODE_CELL_4);
        columnsForEventTable.add(GBR_UL);
        columnsForEventTable.add(GBR_DL);
        columnsForEventTable.add(UTRAN_RANAP_CAUSE);
        columnsForEventTable.add(DATA_IN_DL_RLC_BUFFERS);

        columnsForEventTable.add(RAN_DISCONNECTION_CODE);
        columnsForEventTable.add(RAN_DISCONNECTION_SUBCODE);
        columnsForEventTable.add(TRIGGER_POINT);
        columnsForEventTable.add(RRC_ESTABLISHMENT_CAUSE);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_RAW, columnsForEventTable);
    }

    /**
     * Populate the Group Tables, GROUP_TYPE_E_RAT_VEND_HIER321_CELL and GROUP_TYPE_E_TAC.
     * GROUP_TYPE_E_TAC is already created somewhere, so no need to create it again.
     * @throws Exception
     *
     */
    private void insertDataIntoExclusiveTacGroupTables() throws Exception {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(TAC, SAMPLE_EXCLUSIVE_TAC);
        valuesForTable.put(GROUP_NAME, EXCLUSIVE_TAC_GROUP_NAME);
        insertRow(TEMP_GROUP_TYPE_E_TAC, valuesForTable);
    }

    private void createHashIds() {
        cellHashID = queryUtils.createHashIDFor3GCell(RAT_INTEGER_VALUE_FOR_3G, RNC01_ALTERNATIVE_FDN, "RNC01-1-1",
                ERICSSON);
    }
}
