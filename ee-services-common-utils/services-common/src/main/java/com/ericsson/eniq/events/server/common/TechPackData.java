package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data that is specific to tech packs
 *
 * @Todo - could/should this information be pulled from the database?
 * @author EEMECOY
 *
 */
public abstract class TechPackData {

    public static final String DIM_E_LTE = "DIM_E_LTE";

    private static final String VARCHAR_20 = "varchar(20)";

    private static final String SMALLINT = "smallint";

    private static final String TINYINT = "tinyint";

    private static final String VARCHAR_128 = "varchar(128)";

    private static final String UNSIGNED_INT = "unsigned int";

    public static final String EVENT_E_LTE = "EVENT_E_LTE";

    public static final String EVENT_E_GSM_CFA = "EVENT_E_GSM_CFA";

    public static final String EVENT_E_GSM_PS = "EVENT_E_GSM_PS";

    public static final List<String> completeSGEHTechPackList = new ArrayList<String>();

    public static final List<String> completeTechPackListForMss = new ArrayList<String>();

    public static final List<String> completeGSNTechPackList = new ArrayList<String>();

    public static final Map<String, List<String>> networkTypesPerTechPack = new HashMap<String, List<String>>();

    public static final List<String> radioNetworkTypes = new ArrayList<String>();

    public static final String EVENT_E_SGEH = "EVENT_E_SGEH";

    private static Map<String, String> columnTypes = new HashMap<String, String>();

    public static List<String> listOfTechPacksThatHaveRawErrOrSucTables = new ArrayList<String>();

    public static final String EVENT_E_LTE_ERR_RAW = "EVENT_E_LTE_ERR_RAW";

    public static final String EVENT_E_GSM_CFA_ERR_RAW = "EVENT_E_GSM_CFA_ERR_RAW";

    public static final String EVENT_E_LTE_SUC_RAW = "EVENT_E_LTE_SUC_RAW";

    public static final String EVENT_E_LTE_RAW = "EVENT_E_LTE_RAW";

    public static final String EVENT_E_SGEH_SUC_RAW = "EVENT_E_SGEH_SUC_RAW";

    public static final String EVENT_E_SGEH_ERR_RAW = "EVENT_E_SGEH_ERR_RAW";

    public static final String EVENT_E_SGEH_RAW = "EVENT_E_SGEH_RAW";

    public static final String EVENT_E_MSS_VOICE_CDR_SUC_RAW = "EVENT_E_MSS_VOICE_CDR_SUC_RAW";

    public static final String EVENT_E_MSS_VOICE_CDR_ERR_RAW = "EVENT_E_MSS_VOICE_CDR_ERR_RAW";

    public static final String EVENT_E_MSS_VOICE_CDR_DROP_CALL_RAW = "EVENT_E_MSS_VOICE_CDR_DROP_CALL_RAW";

    public static final String EVENT_E_MSS_LOC_SERVICE_CDR_ERR_RAW = "EVENT_E_MSS_LOC_SERVICE_CDR_ERR_RAW";

    public static final String EVENT_E_MSS_LOC_SERVICE_CDR_SUC_RAW = "EVENT_E_MSS_LOC_SERVICE_CDR_SUC_RAW";

    public static final String EVENT_E_MSS_SMS_CDR_ERR_RAW = "EVENT_E_MSS_SMS_CDR_ERR_RAW";

    public static final String EVENT_E_MSS_SMS_CDR_SUC_RAW = "EVENT_E_MSS_SMS_CDR_SUC_RAW";

    public static final String EVENT_E_MSS_RAW = "EVENT_E_MSS_RAW";

    public static final String EVENT_E_GSN_DT_RAW = "EVENT_E_GSN_DT_RAW";

    public static final String EVENT_E_GSN_DTPDP_RAW = "EVENT_E_GSN_DTPDP_RAW";

    public static final String DC_Z_ALARM_INFO_RAW = "DC_Z_ALARM_INFO_RAW";

    public static final String DIM_E_SGEH = "DIM_E_SGEH";

    public static final String EVENT_E_MSS = "EVENT_E_MSS";

    private static final String DIM_E_MSS = "DIM_E_MSS";

    private static final String DIM_E_GSN_DT = "DIM_E_GSN_DT";

    private static final String DIM_E_GSN_DTPDP = "DIM_E_GSN_DTPDP";

    public static final String EVENT_E_GSN_DTPDP = "EVENT_E_GSN_DTPDP";

    public static final String EVENT_E_GSN_DT = "EVENT_E_GSN_DT";

    public static final String EVENT_E_RAN_CFA = "EVENT_E_RAN_CFA";

    public static final String EVENT_E_RAN_HFA = "EVENT_E_RAN_HFA";

    public static final String EVENT_E_LTE_CFA = "EVENT_E_LTE_CFA";

    public static final String EVENT_E_LTE_HFA = "EVENT_E_LTE_HFA";

    public static final String EVENT_E_LTE_CFA_ARRAY_ERAB = "EVENT_E_LTE_CFA_ARRAY_ERAB";

    public static final String EVENT_E_LTE_HFA_ARRAY_ERAB = "EVENT_E_LTE_HFA_ARRAY_ERAB";

    public static final String EVENT_E_RAN_SESSION = "EVENT_E_RAN_SESSION";

    public static final String EVENT_E_CORE_SESSION = "EVENT_E_CORE_SESSION";

    public static final String EVENT_E_USER_PLANE = "EVENT_E_USER_PLANE";

    public static final Map<String, String> DIM_E_TECHPACKS = new HashMap<String, String>();

    public static final Map<String, List<String>> aggregationColumns = new HashMap<String, List<String>>();

    public static final Map<String, List<String>> dtAggregationColumns = new HashMap<String, List<String>>();

    static {
        completeSGEHTechPackList.add(EVENT_E_SGEH);
        completeSGEHTechPackList.add(EVENT_E_LTE);
        completeTechPackListForMss.add(EVENT_E_MSS);
        completeGSNTechPackList.add(EVENT_E_GSN_DT);
        completeGSNTechPackList.add(EVENT_E_GSN_DTPDP);

        final List<String> eventESgehNetworkTypes = new ArrayList<String>();
        eventESgehNetworkTypes.add(RAT_INTEGER_VALUE_FOR_2G);
        eventESgehNetworkTypes.add(RAT_INTEGER_VALUE_FOR_3G);
        networkTypesPerTechPack.put(EVENT_E_SGEH, eventESgehNetworkTypes);
        final List<String> eventELteNetworkTypes = new ArrayList<String>();
        eventELteNetworkTypes.add(RAT_INTEGER_VALUE_FOR_4G);
        networkTypesPerTechPack.put(EVENT_E_LTE, eventELteNetworkTypes);

        radioNetworkTypes.add(TYPE_BSC);
        radioNetworkTypes.add(TYPE_RNC);
        radioNetworkTypes.add(TYPE_CELL);

        columnTypes.put(TAC_PARAM_UPPER_CASE, UNSIGNED_INT);
        columnTypes.put(APN_PARAM, VARCHAR_128);
        columnTypes.put(TYPE_CAUSE_PROT_TYPE, TINYINT);
        columnTypes.put(CC_SQL_NAME, SMALLINT);
        columnTypes.put(VENDOR_PARAM, VARCHAR_20);
        columnTypes.put(VENDOR_PARAM_UPPER_CASE, VARCHAR_20);
        columnTypes.put(BSC_SQL_NAME, VARCHAR_128);
        columnTypes.put(CELL_SQL_NAME, VARCHAR_128);
        columnTypes.put(RAT_PARAM, TINYINT);

        DIM_E_TECHPACKS.put(EVENT_E_SGEH, DIM_E_SGEH);
        DIM_E_TECHPACKS.put(EVENT_E_LTE, DIM_E_LTE);
        DIM_E_TECHPACKS.put(EVENT_E_MSS, DIM_E_MSS);
        DIM_E_TECHPACKS.put(EVENT_E_GSN_DT, DIM_E_GSN_DT);
        DIM_E_TECHPACKS.put(EVENT_E_GSN_DTPDP, DIM_E_GSN_DTPDP);

        final List<String> dataVolColumns = new ArrayList<String>();
        dataVolColumns.add(DATAVOL_DL_PARAM);
        dataVolColumns.add(DATAVOL_UL_PARAM);

        final List<String> apnColumns = new ArrayList<String>();
        apnColumns.add(APN_PARAM_UPPER_CASE);
        aggregationColumns.put(TYPE_APN, apnColumns);

        final List<String> sgsnColumns = new ArrayList<String>();
        sgsnColumns.add(SGSN_SQL_NAME);
        aggregationColumns.put(TYPE_SGSN, sgsnColumns);

        final List<String> tacColumns = new ArrayList<String>();
        tacColumns.add(TAC_PARAM_UPPER_CASE);
        aggregationColumns.put(TYPE_TAC, tacColumns);

        final List<String> causeCodeColumns = new ArrayList<String>();
        //order is important here, determines order of columns returned from SQL queries
        causeCodeColumns.add(TYPE_CAUSE_PROT_TYPE);
        causeCodeColumns.add(CC_SQL_NAME);
        aggregationColumns.put(TYPE_CAUSE_CODE, causeCodeColumns);

        // For MSS feature
        final List<String> internalCauseCodeColumns = new ArrayList<String>();
        internalCauseCodeColumns.add(INTERNAL_CC_SQL_NAME);
        aggregationColumns.put(TYPE_INTERNAL_CAUSE_CODE, internalCauseCodeColumns);

        final List<String> controllerColumns = new ArrayList<String>();
        controllerColumns.add(RAT_PARAM);
        controllerColumns.add(VENDOR_PARAM_UPPER_CASE);
        controllerColumns.add(BSC_SQL_NAME);
        aggregationColumns.put(TYPE_BSC, controllerColumns);
        aggregationColumns.put(TYPE_RNC, controllerColumns);
        aggregationColumns.put(TYPE_ENODEB, controllerColumns);

        final List<String> cellColumns = new ArrayList<String>();
        cellColumns.add(RAT_PARAM);
        cellColumns.add(VENDOR_PARAM_UPPER_CASE);
        cellColumns.add(BSC_SQL_NAME);
        cellColumns.add(CELL_SQL_NAME);
        aggregationColumns.put(TYPE_CELL, cellColumns);
        aggregationColumns.put(TYPE_ECELL, cellColumns);

        //2G3G DTPUT ONLY
        final List<String> dtSgsnColumns = new ArrayList<String>();
        dtSgsnColumns.add(DTPUT_SGSN_NAME_PARAM);
        dtAggregationColumns.put(TYPE_SGSN, dtSgsnColumns);
        final List<String> dtTacColumns = new ArrayList<String>();
        dtTacColumns.add(TAC_PARAM_UPPER_CASE);
        dtAggregationColumns.put(TYPE_TAC, dtTacColumns);
        final List<String> dtGgsnColumns = new ArrayList<String>();
        dtGgsnColumns.add(GGSN_NAME_PARAM);
        dtAggregationColumns.put(TYPE_GGSN, dtGgsnColumns);
        final List<String> dtQosColumns = new ArrayList<String>();
        dtQosColumns.add(QOS_MEANTPUT_PARAM);
        dtQosColumns.add(QOS_PEAKTPUT_PARAM);
        dtQosColumns.add(QOS_RELIABILITY_PARAM);
        dtQosColumns.add(QOS_DELAY_PARAM);
        dtQosColumns.add(QOS_PRECEDENCE_PARAM);
        dtAggregationColumns.put(TYPE_QOS, dtQosColumns);
        final List<String> dtApnColumns = new ArrayList<String>();
        dtApnColumns.add(APN_PARAM_UPPER_CASE);
        dtAggregationColumns.put(TYPE_APN, dtApnColumns);
        final List<String> dtIMSIColumns = new ArrayList<String>();
        dtIMSIColumns.add(IMSI_PARAM_UPPER_CASE);
        dtAggregationColumns.put(TYPE_IMSI, dtIMSIColumns);

        listOfTechPacksThatHaveRawErrOrSucTables.add(EVENT_E_SGEH);
        listOfTechPacksThatHaveRawErrOrSucTables.add(EVENT_E_LTE);

    }

    /**
     * Get the type of a particular column (eg varchar, tinyint etc)
     *
     * @param columnName
     * @return
     */
    public static String getTypeOfColumn(final String columnName) {
        return columnTypes.get(columnName);
    }

    /**
    * Get the RAT that applies to a particular table If the table/view is from
    * the EVENT_E_LTE table, then 2 is returned (the RAT value for 4G/LTE)
    * Otherwise, 0 (RAT value for GSM) is returned (the EVENT_E_SGEH table is
    * applicable to both 2G and 3G, but in the main 2G and 3G events are treated
    * the same in eniq events)
    *
    * @param techPackTable
    *          a string eg EVENT_E_LTE_ERR_RAW
     * @return
     */
    public static String getRATForTechPackTable(final String techPackTable) {
        if (techPackTable.contains(EVENT_E_LTE)) {
            return RAT_INTEGER_VALUE_FOR_4G;
        }
        return RAT_INTEGER_VALUE_FOR_2G;
    }

    /**
    * Get the RAT that applies to a particular table, where the table is the
    * first element in the list (There's an assumption here that all tables in
    * the list belong to the same tech pack) If the table/view is from the
    * EVENT_E_LTE table, then 2 is returned (the RAT value for 4G/LTE) Otherwise,
    * 0 (RAT value for GSM) is returned (the EVENT_E_SGEH table is applicable to
    * both 2G and 3G, but in the main 2G and 3G events are treated the same in
    * eniq events)
    *
    * @param techPackTables
    *          list of strings eg EVENT_E_LTE_ERR_RAW_01, EVENT_E_LTE_ERR_RAW_02
     * @return
     */
    public static String getRATForTechPackTable(final List<String> techPackTables) {
        if (techPackTables.get(0).contains(EVENT_E_LTE)) {
            return RAT_INTEGER_VALUE_FOR_4G;
        }
        return RAT_INTEGER_VALUE_FOR_2G;
    }

}
