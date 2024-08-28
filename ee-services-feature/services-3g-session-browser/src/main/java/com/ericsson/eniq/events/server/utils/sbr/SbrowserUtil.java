/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.sbr;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;

import java.util.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public final class SbrowserUtil {

    //prevent initiate
    private SbrowserUtil() {
    }

    private static Map<String, String> mappedDrilltypeColumns = new HashMap<String, String>();

    private static Map<Integer, String> eventIDMappings = new HashMap<Integer, String>();

    private static final String DRILL_COLUMNS = "drillColumns";

    static {

        eventIDMappings.put(0, CORE);
        eventIDMappings.put(1, CORE);
        eventIDMappings.put(2, CORE);
        eventIDMappings.put(3, CORE);
        eventIDMappings.put(4, CORE);
        eventIDMappings.put(14, CORE);
        eventIDMappings.put(15, CORE);

        eventIDMappings.put(20000, RAB);

        eventIDMappings.put(438, RAN_CFA);
        eventIDMappings.put(456, RAN_CFA);

        //HFA_SOHO
        eventIDMappings.put(INTERNAL_SOFT_HANDOVER_EVALUATION, HFA_SOHO);
        eventIDMappings.put(INTERNAL_SOFT_HANDOVER_EXECUTION, HFA_SOHO);

        //HFA_IFHO
        eventIDMappings.put(INTERNAL_IFHO_EXECUTION_ACTIVE, HFA_IFHO);

        //HFA_IRAT_HARD_HO
        eventIDMappings.put(INTERNAL_OUT_HARD_HANDOVER_FAILURE, HFA_IRAT);

        //RAN_HSDSCH_SUC_CELL_CHANGE
        eventIDMappings.put(INTERNAL_SUCCESSFUL_HSDSCH_CELL_CHANGE, HFA_HSDSCH_SUC);

        //HFA_HSDSCH_FAIL_CELL_CHANGE/NO CHANGE
        eventIDMappings.put(INTERNAL_FAILED_HSDSCH_CELL_CHANGE, HFA_HSDSCH);
        eventIDMappings.put(INTERNAL_HSDSCH_CELL_SELECTION_NO_CELL_SELECTED, HFA_HSDSCH);

        mappedDrilltypeColumns.put(TYPE_TAC, TYPE_TAC);
        mappedDrilltypeColumns.put(TYPE_APN, TYPE_APN);
        mappedDrilltypeColumns.put(HS_CATEGORY, "HSDPA_UE_CATEGORY");
        mappedDrilltypeColumns.put(EUL_CATEGORY, "EUL_UE_CATEGORY");
        mappedDrilltypeColumns.put(START_CELL, "START_CELL_ID");
        mappedDrilltypeColumns.put(END_CELL, "END_CELL_ID");
        mappedDrilltypeColumns.put(HS_RATIO, HS_RATIO);
        mappedDrilltypeColumns.put(EUL_RATIO, EUL_RATIO);
        mappedDrilltypeColumns.put(DCH_ACT, "DCH_ACTIVITY");
        mappedDrilltypeColumns.put(ECNO, "ECNO_AVG");
        mappedDrilltypeColumns.put(RSCP, "RSCP_AVG");
        mappedDrilltypeColumns.put(UL_INER, "UL_INTERFERENCE_AVG");
        mappedDrilltypeColumns.put(HS_USER, "HSDSCH_AVG_USERS");
        mappedDrilltypeColumns.put(START_RAB, "START_RAB");
        mappedDrilltypeColumns.put(END_RAB, "END_RAB");
        mappedDrilltypeColumns.put(DL_PWR, "DL_NON_HS_TX_POWER_AVG");

    }

    public static String getSubscriberType(final MultivaluedMap<String, String> requestParameters) {
        if (requestParameters.containsKey(IMSI_PARAM)) {
            return TYPE_IMSI;
        }
        return TYPE_MSISDN;
    }

    public static String getPopupDetailOfEvent(final MultivaluedMap<String, String> requestParameters) {

        if (StringUtils.isNotBlank(requestParameters.getFirst(EVENT_ID_PARAM))) {
            return eventIDMappings.get(Integer.parseInt(requestParameters.getFirst(EVENT_ID_PARAM)));
        }
        throw new WebApplicationException(404);
    }

    public static List<String> getMappedColumnsForDrilltype(final String drilltype) {
        return Arrays.asList(mappedDrilltypeColumns.get(drilltype));
    }

    public static String addTenMilliSecondToTimeFrom(final MultivaluedMap<String, String> requestParameters) {
        final String time = requestParameters.getFirst(EVENT_TIME_FROM_PARAM);
        final Long timeLong = Long.parseLong(time) + 10;
        return DateTimeUtils.formattedEventTimeUTC(timeLong.toString());
    }

    public static String minusTenMillSecondToTimeFrom(final MultivaluedMap<String, String> requestParameters) {
        final String time = requestParameters.getFirst(EVENT_TIME_FROM_PARAM);
        final Long timeLong = Long.parseLong(time) - 10;
        return DateTimeUtils.formattedEventTimeUTC(timeLong.toString());
    }

    @SuppressWarnings("null")
    public static String calculateKpiStartTimeForDrill(final MultivaluedMap<String, String> requestParameters,
            final EventDataSourceType eventDataSourceType) {
        final String time = requestParameters.getFirst(KPI_END_TIME);
        Long timeLong = null;
        switch (eventDataSourceType) {
        case AGGREGATED_15MIN:
            timeLong = Long.parseLong(time) - 900000;
            break;
        case AGGREGATED_DAY:
            timeLong = Long.parseLong(time) - 86400000;
            break;
        case AGGREGATED_1MIN:
            timeLong = Long.parseLong(time) - 60000;
            break;
        case RAW:
            timeLong = Long.parseLong(time) - 60000;
            break;
        }

        return DateTimeUtils.formattedEventTimeUTC(timeLong.toString());
    }

    /**
     * Put user plane KPI drill information into velocity templates
     * 
     * @param requestParameters
     * @return Map<String,Object> K,V map for velocity tempaltes
     */
    public static Map<String, Object> getUserPlaneKPIDrillDownInformation(
            final MultivaluedMap<String, String> requestParameters) {

        if (userPlaneKPIDrillDownInformationShouldBeAdded(requestParameters)) {
            return addUserPlaneKPIDrillDownInformation(requestParameters);
        }
        return emptyHashMap();

    }

    private static boolean userPlaneKPIDrillDownInformationShouldBeAdded(
            final MultivaluedMap<String, String> requestParameters) {
        return requestParameters.containsKey(DRILLTYPE_PARAM) && requestParameters.containsKey(KPI_ID);
    }

    private static Map<String, Object> addUserPlaneKPIDrillDownInformation(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> serviceSpecificTemplateParameters = emptyHashMap();
        final String drilltype = requestParameters.getFirst(DRILLTYPE_PARAM);
        final String kpiId = requestParameters.getFirst(KPI_ID);
        serviceSpecificTemplateParameters.put(DRILLTYPE_PARAM, drilltype);
        serviceSpecificTemplateParameters.put(DRILL_COLUMNS, SbrowserUtil.getMappedColumnsForDrilltype(drilltype));
        serviceSpecificTemplateParameters.put(KPI, Integer.parseInt(kpiId));
        final String kpiEndTimeUTC = DateTimeUtils.formattedEventTimeUTC(requestParameters.getFirst(KPI_END_TIME));
        serviceSpecificTemplateParameters.put(KPI_END_TIME, kpiEndTimeUTC);
        return serviceSpecificTemplateParameters;
    }

    private static HashMap<String, Object> emptyHashMap() {
        return new HashMap<String, Object>();
    }

}
