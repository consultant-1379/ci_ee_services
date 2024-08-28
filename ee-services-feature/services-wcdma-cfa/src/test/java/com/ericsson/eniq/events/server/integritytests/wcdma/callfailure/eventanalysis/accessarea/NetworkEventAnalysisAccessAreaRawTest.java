/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.eventanalysis.accessarea;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis.NetworkEventAnalysisService;
import com.ericsson.eniq.events.server.test.queryresults.NetworkEventAnalysisAccessAreaResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author EBROWPA
 * @since 2011
 *
 */

public class NetworkEventAnalysisAccessAreaRawTest extends BaseDataIntegrityTest<NetworkEventAnalysisAccessAreaResult> {
    private NetworkEventAnalysisService networkEventAnalysisService;

    private static final String TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_ALLCALLS_15MIN = "#EVENT_E_RAN_CFA_HIER3_CELL_ID_ALLCALLS_15MIN";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_DIM_E_RAN_CFA_EVENTTYPE = "#DIM_E_RAN_CFA_EVENTTYPE";

    private static final String TEMP_EVENT_E_RAN_CFA_ERR_RAW = "#EVENT_E_RAN_CFA_ERR_RAW";

    private long hashedIdForCell;

    @Before
    public void setUp() throws Exception {
        networkEventAnalysisService = new NetworkEventAnalysisService();
        attachDependencies(networkEventAnalysisService);
        hashedIdForCell = calculateHashedIdForCell(_3G, RNC01_ALTERNATIVE_FDN, SAMPLE_CELL_ID, ERICSSON);

        createCfaDataTableSchema();
        createDimTableSchema();

        populateData();
    }

    @Test
    public void testNetworkCellData_TotalRab_RAW() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2354");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0002");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");

        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "3");
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "01423");
        requestParameters.putSingle(EVENT_ID_PARAM, "438");
        requestParameters.putSingle(NODE_PARAM, SAMPLE_CELL_ID + COMMA + COMMA + RNC01_ALTERNATIVE_FDN + COMMA
                + ERICSSON + COMMA + _3G);
        requestParameters.putSingle(TYPE_PARAM, "CELL");

        final String json = runQuery(networkEventAnalysisService, requestParameters);
        System.out.println(json);

        final List<NetworkEventAnalysisAccessAreaResult> results = getTranslator().translateResult(json,
                NetworkEventAnalysisAccessAreaResult.class);
        assertThat(results.size(), is(1));

        final NetworkEventAnalysisAccessAreaResult resultForCallDropsEvent = results.get(0);
        assertThat(resultForCallDropsEvent.getFailures(), is(1));
        assertThat(resultForCallDropsEvent.getImpactedSubscribers(), is(1));
        assertEquals(resultForCallDropsEvent.getFailureType(), "Call Drops");

    }

    private void populateData() throws SQLException {
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", hashedIdForCell);
                put("EVENT_ID", 438);
                put("IMSI", "1234567894560");
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);
                put("MULTI_RAB_FAIL_CNT", 1);
                put("MULTI_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 23:01:00");
                put("TAC", "132456");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_ALLCALLS_15MIN, new HashMap<String, Object>() {
            {
                put("HIER3_CELL_ID", hashedIdForCell);
                put("CALLS_PS_ALL", 1);
                put("CALLS_CS_ALL", 1);
                put("CALLS_MR_ALL", 1);
                put("PS_SUC", 1);
                put("CS_SUC", 1);
                put("MR_SUC", 1);
                put("DATETIME_ID", "2012-05-16 23:01:00");
            }
        });

        // Dim tables data for joins
        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 438);
                put("EVENT_ID_ALTERNATE_DESC", "Call Drops");
            }
        });

        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 456);
                put("EVENT_ID_ALTERNATE_DESC", "Call Setup Failures");
            }
        });

        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("VENDOR", ERICSSON);
                put("HIERARCHY_3", RNC01_ALTERNATIVE_FDN);
                put("HIERARCHY_1", "110");
                put("HIER3_CELL_ID", hashedIdForCell);
                put("CELL_ID", SAMPLE_CELL_ID);
                put("RAT", 1);
            }
        });

    }

    private void createDimTableSchema() throws SQLException {
        final List<ColumnDetails> eventTypeDimTable = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID_ALTERNATE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> hier321TypeDimTable = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_NAME", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_NAME", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLINGCODE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE, eventTypeDimTable);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, hier321TypeDimTable);
    }

    private void createCfaDataTableSchema() throws SQLException {
        final List<ColumnDetails> errRawTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ROAMING", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_CONTEXT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_MODULE_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONF", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_SERV_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CRNC_ID_SERV_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARGET_CONF", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("WANTED_CONF", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PROCEDURE_INDICATOR", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVALUATION_CASE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EXCEPTION_CLASS", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EXTENDED_CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SEVERITY_INDICATOR", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TRIGGER_POINT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UTRAN_RANAP_CAUSE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CN_ID", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ORIGINATING_STATE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RRC_ESTABLISHMENT_CAUSE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELLO_AAL2NCI_REJECT_REASON", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAN_DISCONNECTION_CODE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAN_DISCONNECTION_SUBCODE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCANNER_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONNECTION_PROPERTIES", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TARGET_CONNECTION_PROPERTIES", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("WANTED_CONNECTION_PROPERTIES", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_C_ID_1_SECONDARY_SERV_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAN_ID_PARAMETER", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLING_CODE_CELL_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLING_CODE_CELL_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLING_CODE_CELL_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLING_CODE_CELL_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_CELL_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_CELL_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_CELL_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_CELL_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_CELL_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_CELL_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_CELL_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_CELL_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UL_INT_CELL1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UL_INT_CELL2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UL_INT_CELL3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UL_INT_CELL4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCRAMBLING_CODE_ADDED_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_CELL_1_ADDED_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_ADDED_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GBR_UL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GBR_DL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CN_RANAP_CAUSE", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATA_IN_DL_RLC_BUFFERS", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PS_RAB_FAIL_CNT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CS_RAB_FAIL_CNT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MULTI_RAB_FAIL_CNT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FAILED_RAB_TYPE", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PS_ERR_CNT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CS_ERR_CNT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MULTI_ERR_CNT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OSS_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("YEAR_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MONTH_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DAY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOUR_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MIN_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TIMEZONE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUSPECTFLAG", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NE_VERSION", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SESSION_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BATCH_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LOCAL_DATE_ID", "date", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> hier321AllCallFifteenTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CALLS_CS_ALL", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CALLS_PS_ALL", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CALLS_MR_ALL", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CS_SUC", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PS_SUC", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MR_SUC", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("YEAR_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MONTH_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DAY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOUR_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MIN_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_HIER3_CELL_ID_ALLCALLS_15MIN, hier321AllCallFifteenTableSchema);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, errRawTableSchema);

    }
}
