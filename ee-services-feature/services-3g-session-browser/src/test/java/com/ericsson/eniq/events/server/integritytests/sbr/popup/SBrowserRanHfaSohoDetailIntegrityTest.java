package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserRanHfaSohoDetailService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author edarbla
 * @since 2012
 *
 */
public class SBrowserRanHfaSohoDetailIntegrityTest extends BaseDataIntegrityTest {

    //dim tables to use for the query
    private static final String DIM_E_RAN_HFA_HANDOVER_TYPE = "#DIM_E_RAN_HFA_HANDOVER_TYPE";

    private static final String DIM_E_SGEH_TAC = "#DIM_E_SGEH_TAC";

    private static final String DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

    private static final String DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String DIM_E_RAN_HFA_EVENTTYPE = "#DIM_E_RAN_HFA_EVENTTYPE";

    private static final String DIM_E_RAN_HFA_EVENT_TRIGGER = "#DIM_E_RAN_HFA_EVENT_TRIGGER";

    private static final String DIM_E_RAN_HFA_CAUSE_CODE_VALUE = "#DIM_E_RAN_HFA_CAUSE_CODE_VALUE";

    private static final String DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE = "#DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE";

    private static final String DIM_E_RAN_RSCP_MAPPING = "#DIM_E_RAN_RSCP_MAPPING";

    private static final String DIM_E_RAN_ECNO_MAPPING = "#DIM_E_RAN_ECNO_MAPPING";

    //actual data tables
    private static final String EVENT_E_RAN_SOHO_HFA_ERR_RAW = "#EVENT_E_RAN_HFA_SOHO_ERR_RAW";

    private SBrowserRanHfaSohoDetailService sBrowserRanHfaSohoDetailService;

    @Before
    public void setUp() throws Exception {
        sBrowserRanHfaSohoDetailService = new SBrowserRanHfaSohoDetailService();
        attachDependencies(sBrowserRanHfaSohoDetailService);

        createDimTableSchema();
        createRawTableSchema();
        populateTestData();
    }

    @Test
    public void testRanHfaSohoDetail() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1000");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "2300");

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "16052012");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "16052012");
        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1337184001000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1337184002000");

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234");
        requestParameters.putSingle(EVENT_ID_PARAM, "40");

        final String json = runQuery(sBrowserRanHfaSohoDetailService, requestParameters);
        System.out.println(json);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");
        final JSONObject handoverDetails = details.getJSONObject("Handover Details");
        assertThat(handoverDetails.getString("Source Cell Id"), is("K"));
        assertThat(handoverDetails.getString("Source CID"), is("123"));
        assertThat(handoverDetails.getString("Target Cell Id"), is("K"));
        assertThat(handoverDetails.getString("Target CID"), is("123"));

        final JSONObject activeSetDetails = details.getJSONObject("Active Set Details");
        assertThat(activeSetDetails.getString("Source Cell ID 1"), is("K"));
        assertThat(activeSetDetails.getString("Source CID 1"), is("123"));
        assertThat(activeSetDetails.getString("Source RNC 1"), is("s"));

        assertThat(activeSetDetails.getString("Source Cell ID 2"), is("K"));
        assertThat(activeSetDetails.getString("Source CID 2"), is("123"));
        assertThat(activeSetDetails.getString("Source RNC 2"), is("s"));

        assertThat(activeSetDetails.getString("Source Cell ID 3"), is("K"));
        assertThat(activeSetDetails.getString("Source CID 3"), is("123"));
        assertThat(activeSetDetails.getString("Source RNC 3"), is("s"));

        assertThat(activeSetDetails.getString("Source Cell ID 4"), is("K"));
        assertThat(activeSetDetails.getString("Source CID 4"), is("123"));
        assertThat(activeSetDetails.getString("Source RNC 4"), is("s"));
    }

    private void createDimTableSchema() throws Exception {

        final List<ColumnDetails> tableSchema_HO = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HANDOVER_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HANDOVER_TYPE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_HFA_HANDOVER_TYPE, tableSchema_HO);

        final List<ColumnDetails> tableSchema_TAC = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_SGEH_TAC, tableSchema_TAC);

        final List<ColumnDetails> tableSchema_MCCMNC = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_SGEH_MCCMNC, tableSchema_MCCMNC);

        final List<ColumnDetails> tableSchema_HIER321 = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RAT", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIERARCHY_3", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CELL_ID", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("VENDOR", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_SGEH_HIER321_CELL, tableSchema_HIER321);

        final List<ColumnDetails> tableSchema_EVENTTYPE = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_HFA_EVENTTYPE, tableSchema_EVENTTYPE);

        final List<ColumnDetails> tableSchema_TRIGGER = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_HFA_EVENT_TRIGGER, tableSchema_TRIGGER);

        final List<ColumnDetails> tableSchema_CC = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_HFA_CAUSE_CODE_VALUE, tableSchema_CC);

        final List<ColumnDetails> tableSchema_SCC = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUB_CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUB_CAUSE_VALUE_DESC", "varchar (127)", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, tableSchema_SCC);

        final List<ColumnDetails> tableSchema_RSCP = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("RSCP", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_DBM", "float", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_RSCP_MAPPING, tableSchema_RSCP);

        final List<ColumnDetails> tableSchema_ECNO = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("ECNO", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ECNO_DBM", "float", Nullable.CAN_BE_NULL));
            }
        };
        createTemporaryTable(DIM_E_RAN_ECNO_MAPPING, tableSchema_ECNO);
    }

    private void createRawTableSchema() throws Exception {
        final List<ColumnDetails> tableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("HIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER3_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER32_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER321_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THIER3_CELL_ID", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MCC", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MCC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MNC", "varchar(3)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ROAMING", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RAC", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_CONTEXT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_MODULE_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONF", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TRIGGER", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SCANNER_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_1", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_1", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_2", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_2", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_3", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_3", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_4", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_4", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("C_ID_EVALUATED", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RNC_ID_EVALUATED", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CPICH_EC_NO_EVAL_CELL", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("RSCP_EVAL_CELL", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HANDOVER_TYPE", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SUB_CAUSE_VALUE", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SOURCE_CONNECTION_PROP", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SRC_C_ID_1_SS_HSDSCH_CELL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CATEGORY_ID", "tinyint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("OSS_ID", "varchar(20)", Nullable.CAN_BE_NULL));
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
                add(new ColumnDetails("NE_VERSION", "varchar(20)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SESSION_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BATCH_ID", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("LOCAL_DATE_ID", "date", Nullable.CAN_BE_NULL));

            }
        };
        createTemporaryTable(EVENT_E_RAN_SOHO_HFA_ERR_RAW, tableSchema);

        createTemporaryFromRealTableAndUseInTemplates("DIM_E_RAN_RNCFUNCTION", new String[] { "OSS_ID", "SUBNETWORK",
                "SN", "MOID", "RNCFUNCTION_FDN", "RNC_ID", "RncFunction", "rncId", "VENDOR", "STATUS", "CREATED",
                "MODIFIED", "MODIFIER" });

        createTemporaryFromRealTableAndUseInTemplates("DIM_E_RAN_RNC", new String[] { "OSS_ID", "SUBNETWORK",
                "RNC_FDN", "ALTERNATIVE_FDN", "RNC_NAME", "RNC_ID", "SITE_FDN", "SITE", "MECONTEXTID", "NEMIMVERSION",
                "RNC_VERSION", "MSC", "MCC", "MNC", "VENDOR", "STATUS", "CREATED", "MODIFIED", "MODIFIER" });
    }

    private void populateTestData() throws SQLException {

        insertRow(EVENT_E_RAN_SOHO_HFA_ERR_RAW, new HashMap<String, Object>() {
            {
                put("HANDOVER_TYPE", 0);
                put("TAC", 1);
                put("IMSI_MCC", "13");
                put("IMSI_MNC", "45");
                put("IMSI", 1234);
                put("HIER3_CELL_ID", 12);
                put("THIER3_CELL_ID", 12);
                put("EVENT_ID", 40);
                put("CAUSE_VALUE", 17);
                put("SUB_CAUSE_VALUE", 1);
                put("RSCP_EVAL_CELL", 1);
                put("CPICH_EC_NO_EVAL_CELL", 1);
                put("EVENT_TIME", "2012-05-16 16:00:00.990");
                put("DATETIME_ID", "2012-05-16 22:01:00.000");
                put("SOURCE_CONNECTION_PROP", 12);
                put("C_ID_1", 123);
                put("C_ID_2", 123);
                put("C_ID_3", 123);
                put("C_ID_4", 123);
                put("RNC_ID_1", 9);
                put("RNC_ID_2", 9);
                put("RNC_ID_3", 9);
                put("RNC_ID_4", 9);
            }
        });

        insertRow(DIM_E_RAN_HFA_HANDOVER_TYPE, new HashMap<String, Object>() {
            {
                put("HANDOVER_TYPE", 0);
                put("HANDOVER_TYPE_DESC", "A");

            }
        });

        insertRow(DIM_E_SGEH_TAC, new HashMap<String, Object>() {
            {
                put("TAC", 123);
            }
        });

        insertRow(DIM_E_SGEH_MCCMNC, new HashMap<String, Object>() {
            {
                put("MNC", "45");
                put("MCC", "13");

            }
        });

        insertRow(DIM_E_SGEH_HIER321_CELL, new HashMap<String, Object>() {
            {
                put("RAT", 1);
                put("HIERARCHY_3", "s");
                put("CID", 123);
                put("CELL_ID", "K");
                put("HIER3_CELL_ID", 12);
                put("VENDOR", "E");
            }
        });

        insertRow(DIM_E_RAN_HFA_EVENTTYPE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 40);
                put("EVENT_ID_DESC", "A");
            }
        });

        insertRow(DIM_E_RAN_HFA_EVENT_TRIGGER, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 40); // MAY NOT EXIST
                put("EVENT_TRIGGER", 15);
                put("EVENT_TRIGGER_DESC", "H");
            }
        });

        insertRow(DIM_E_RAN_HFA_CAUSE_CODE_VALUE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 40);
                put("CAUSE_VALUE", 1);
                put("CAUSE_VALUE_DESC", "B");
            }
        });

        insertRow(DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE, new HashMap<String, Object>() {
            {
                put("EVENT_ID", 40);
                put("SUB_CAUSE_VALUE", 2);
                put("SUB_CAUSE_VALUE_DESC", "C");
            }
        });

        insertRow(DIM_E_RAN_RSCP_MAPPING, new HashMap<String, Object>() {
            {
                put("RSCP", 1);
                put("RSCP_DBM", 2.4);
            }
        });

        insertRow(DIM_E_RAN_ECNO_MAPPING, new HashMap<String, Object>() {
            {
                put("ECNO", 1);
                put("ECNO_DBM", 2.0);
            }
        });

        insertRowIntoTemporaryTable("#DIM_E_RAN_RNCFUNCTION", new String[] { "events_oss_1", "smartone",
                "SubNetwork=smartone,SubNetwork=RNC09,MeContext=RNC09", "ManagedElement=1,RncFunction=1",
                "SubNetwork=smartone,SubNetwork=RNC09,MeContext=RNC09,ManagedElement=1,RncFunction=1", "RNC09", "",
                "9", "Ericsson", "DEACTIVE", "2012-08-29 11:37:39", "2012-08-29 11:37:39", "ENIQ EVENTS" });

        insertRowIntoTemporaryTable("#DIM_E_RAN_RNC", new String[] { "events_oss_1", "smartone",
                "SubNetwork=smartone,SubNetwork=RNC09,MeContext=RNC09", "s", "RNC09", "RNC09",
                "SubNetwork=smartone,Site=RNC09", "RNC09", "RNC09", "vM.1.150", "W11B", "", "454", "6", "Ericsson",
                "DEACTIVE", "2012-08-29 11:37:39", "2012-08-29 11:37:39", "ENIQ EVENTS" });
    }

}
