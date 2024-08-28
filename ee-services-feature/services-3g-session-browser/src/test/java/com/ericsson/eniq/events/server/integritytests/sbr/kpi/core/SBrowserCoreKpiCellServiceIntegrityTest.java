package com.ericsson.eniq.events.server.integritytests.sbr.kpi.core;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static junit.framework.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.core.SBrowserCoreKpiMapService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SBrowserCoreKpiCellServiceIntegrityTest extends BaseDataIntegrityTest {

    private static final String TEMP_EVENT_E_SGEH_ERR_RAW = "#EVENT_E_SGEH_ERR_RAW";

    private static final String TEMP_EVENT_E_SGEH_VEND_HIER3_EVENTID_SUC_15MIN = "#EVENT_E_SGEH_VEND_HIER3_EVENTID_SUC_15MIN";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_RAN_RNC = "#DIM_E_RAN_RNC";

    private static final String TEMP_DIM_E_RAN_RNCFUNCTION = "#DIM_E_RAN_RNCFUNCTION";

    private SBrowserCoreKpiMapService sBrowserCoreKpiMapService;

    @Before
    public void setUp() throws Exception {
        sBrowserCoreKpiMapService = new SBrowserCoreKpiMapService();
        attachDependencies(sBrowserCoreKpiMapService);

        createSgehTableSchema();
        createDimTableSchema();

        populateSgehTestData();
    }

    @Test
    public void testNetworkCellData() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "2254");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2302");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");

        requestParameters.putSingle(TZ_OFFSET, "+0000");

        final String json = runQuery(sBrowserCoreKpiMapService, requestParameters);
        System.out.println(json);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject rncData = data.getJSONObject("RNC01");
        final JSONObject kpis = rncData.getJSONObject("kpis");

        // RNC
        assertEquals("1", rncData.getString("rncId"));
        assertEquals("1", rncData.getString("rat"));
        assertEquals("ERICSSON", rncData.getString("vendor"));
        // KPI's
        assertEquals("-", kpis.getString("Service Request Failure Ratio"));
        assertEquals("-", kpis.getString("Paging Failure Ratio"));
        assertEquals("-", kpis.getString("RAU Success Rate"));
        assertEquals("50.0", kpis.getString("PDP Context Activation Success Rate"));
        assertEquals("-", kpis.getString("ISRAU Success Rate"));
        assertEquals("-", kpis.getString("Detach Success Rate"));
        assertEquals("-", kpis.getString("PDP Context Cutoff Ratio"));
        assertEquals("-", kpis.getString("Attach Success Rate"));

    }

    @SuppressWarnings("unchecked")
    private void populateSgehTestData() throws SQLException {
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", 1);
                put("HIERARCHY_3", "RNC01");
                put("VENDOR", "ERICSSON");
                put("RAT", "1");
                put("EVENT_TIME", "2012-05-16 23:01:00.000");
                put("DATETIME_ID", "2012-05-16 23:01:00.000");
            }
        });

        insertRow(TEMP_EVENT_E_SGEH_VEND_HIER3_EVENTID_SUC_15MIN, new HashMap<String, Object>() {
            {
                put("NO_OF_SUCCESSES", "1");
                put("EVENT_ID", 1);
                put("HIERARCHY_3", "RNC01");
                put("VENDOR", "ERICSSON");
                put("RAT", "1");
                put("DATETIME_ID", "2012-05-16 23:01:00.000");
            }
        });

        // Dim tables data for joins
        insertRow(TEMP_DIM_E_SGEH_HIER321, new HashMap<String, Object>() {
            {
                put("HIERARCHY_3", "RNC01");
                put("VENDOR", "ERICSSON");
                put("RAT", "1");
            }
        });

        insertRow(TEMP_DIM_E_RAN_RNC, new HashMap<String, Object>() {
            {
                put("SUBNETWORK", "smartone");
                put("RNC_NAME", "RNC01");
                put("RNC_ID", "RNC01");
                put("VENDOR", "ERICSSON");
                put("ALTERNATIVE_FDN", "RNC01");
                put("RNC_FDN", "RNC01");
            }
        });

        insertRow(TEMP_DIM_E_RAN_RNCFUNCTION, new HashMap<String, Object>() {
            {
                put("SUBNETWORK", "smartone");
                put("RNC_ID", "RNC01");
                put("rncId", 1);
                put("VENDOR", "ERICSSON");
                put("SN", "RNC01");
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void createDimTableSchema() throws SQLException {
        final List<ColumnDetails> hier321TableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACCESS_AREA_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GLOBAL_CELL_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FPDCH", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_NAME", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_TYPE", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_BAND", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_LAYER", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> ranTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUBNETWORK", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_FDN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ALTERNATIVE_FDN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_NAME", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE_FDN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SITE", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MECONTEXTID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NEMIMVERSION", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_VERSION", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> rncTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUBNETWORK", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MOID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNCFUNCTION_FDN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RncFunction", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("rncId", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("STATUS", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CREATED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIED", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MODIFIER", "varchar", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321, hier321TableSchema);
        createTemporaryTable(TEMP_DIM_E_RAN_RNC, ranTableSchema);
        createTemporaryTable(TEMP_DIM_E_RAN_RNCFUNCTION, rncTableSchema);
    }

    @SuppressWarnings("unchecked")
    private void createSgehTableSchema() throws SQLException {
        final List<ColumnDetails> errTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_2", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_1", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OLD_MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OLD_MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OLD_LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OLD_RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_CODE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUBCAUSE_CODE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACTIVATION_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ATTACH_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DEACTIVATION_TRIGGER", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DROPPED_PDP", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_RESULT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("INTRA_RAU_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TRANSFERRED_PDP", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UPDATE_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HLR", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOMEZONE_IDENTITY", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PTMSI", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GGSN_IPADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("GGSN_NAME", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SGSN_IPADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OLD_SGSN_IPADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ROAMING", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_PROT_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LINKED_NSAPI", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_NSAPI_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_GGSN_IPADDRESS_1", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_GGSN_NAME_1", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_MS_IPADDRESS_1", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_NSAPI_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_GGSN_IPADDRESS_2", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_GGSN_NAME_2", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PDP_MS_IPADDRESS_2", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PAGING_ATTEMPTS", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SERVICE_REQ_TRIGGER", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("REQUEST_RETRIES", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SERVICE_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DETACH_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DETACH_TRIGGER", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_SOURCE_NAME", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OSS_ID", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("YEAR_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MONTH_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DAY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOUR_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MIN_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TIMEZONE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUSPECTFLAG", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NE_VERSION", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SESSION_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BATCH_ID", "unsigned int", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> succTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("YEAR_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATE_ID", "date", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar(127) ", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar(127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MONTH_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DAY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOUR_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MIN_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_SUCCESSES", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_ERRORS", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_TOTAL_ERR_SUBSCRIBERS", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_NET_INIT_DEACTIVATES", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_PAGING_ATTEMPTS", "unsigned bigint", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_EVENT_E_SGEH_ERR_RAW, errTableSchema);
        createTemporaryTable(TEMP_EVENT_E_SGEH_VEND_HIER3_EVENTID_SUC_15MIN, succTableSchema);
    }
}
