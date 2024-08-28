/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.controller.rab;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.rabanalysis.RNCByRABTypeChartResource;
import com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure.RABTypeChartResourceResult;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eadrhyn
 *
 */
public class RNCByRABTypeChartResourceTest extends BaseDataIntegrityTest<RABTypeChartResourceResult> {

    private static final String TEMP_EVENT_E_RAN_CFA_ERR_RAW = "#EVENT_E_RAN_CFA_ERR_RAW";

    private static final String TEMP_DIM_E_RAN_RABTYPE = "#DIM_E_RAN_RABTYPE";

    private RNCByRABTypeChartResource controllerByRABType;

    @Before
    public void setUp() throws Exception {
        controllerByRABType = new RNCByRABTypeChartResource();
        attachDependencies(controllerByRABType);

        createCfaRawTableSchema();
        createDimTableSchema();

        populateData();
    }

    @Test
    public void testNetworkRncData_Raw() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2354");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "0002");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "17052012");

        requestParameters.putSingle(TZ_OFFSET, "+0100");
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "01423");
        requestParameters.putSingle(EVENT_ID_PARAM, "438");
        requestParameters.putSingle(HIER3_ID, "1234567890123");
        requestParameters.putSingle(TYPE_PARAM, "BSC");

        final String json = runQuery(controllerByRABType, requestParameters);
        System.out.println(json);

        final List<RABTypeChartResourceResult> results = getTranslator().translateResult(json,
                RABTypeChartResourceResult.class);
        assertThat(results.size(), is(3));

        final RABTypeChartResourceResult resultForPS = results.get(0);
        assertThat(resultForPS.getNoFailures(), is(1));
        assertEquals(resultForPS.getRabType(), "Packet Switch");

        final RABTypeChartResourceResult resultForCS = results.get(1);
        assertThat(resultForCS.getNoFailures(), is(1));
        assertEquals(resultForCS.getRabType(), "Circuit Switch");

        final RABTypeChartResourceResult resultForMulti = results.get(2);
        assertThat(resultForMulti.getNoFailures(), is(3));
        assertEquals(resultForMulti.getRabType(), "Multi RAB");

    }

    private void populateData() throws SQLException {
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "1234567890123");
                put("EVENT_ID", 438);
                put("FAILED_RAB_TYPE", 0);
                put("IMSI", "1234567894560");
                put("CS_RAB_FAIL_CNT", 1);
                put("CS_ERR_CNT", 1);
                put("DATETIME_ID", "2012-05-16 23:01:00");
                put("TAC", "132456");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "1234567890123");
                put("EVENT_ID", 438);
                put("FAILED_RAB_TYPE", 4);
                put("IMSI", "1234567894560");

                put("PS_RAB_FAIL_CNT", 1);
                put("PS_ERR_CNT", 1);

                put("DATETIME_ID", "2012-05-16 23:01:00");
                put("TAC", "132456");
            }
        });

        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, new HashMap<String, Object>() {
            {
                put("HIER3_ID", "1234567890123");
                put("EVENT_ID", 438);
                put("FAILED_RAB_TYPE", 19);
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

        // Dim tables data for joins
        insertRow(TEMP_DIM_E_RAN_RABTYPE, new HashMap<String, Object>() {
            {
                put("RABTYPE", 0);
                put("CATEGORY_ID_DESC", "Circuit Switch");
                put("CATEGORY_ID_VALUE", 0);
            }
        });

        insertRow(TEMP_DIM_E_RAN_RABTYPE, new HashMap<String, Object>() {
            {
                put("RABTYPE", 4);
                put("CATEGORY_ID_DESC", "Packet Switch");
                put("CATEGORY_ID_VALUE", 1);
            }
        });

        insertRow(TEMP_DIM_E_RAN_RABTYPE, new HashMap<String, Object>() {
            {
                put("RABTYPE", 19);
                put("CATEGORY_ID_DESC", "Multi RAB");
                put("CATEGORY_ID_VALUE", 2);
            }
        });

    }

    private void createDimTableSchema() throws SQLException {
        final List<ColumnDetails> rabTypeDimTable = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RABTYPE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RABTYPE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID_VALUE", "smallint", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_DIM_E_RAN_RABTYPE, rabTypeDimTable);
    }

    private void createCfaRawTableSchema() throws SQLException {
        final List<ColumnDetails> errTableSchema = new ArrayList<ColumnDetails>() {
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

        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, errTableSchema);
    }

}
