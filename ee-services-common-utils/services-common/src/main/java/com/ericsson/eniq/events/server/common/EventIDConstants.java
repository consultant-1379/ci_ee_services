/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to hold event id values
 * @author eemecoy
 *
 */
public abstract class EventIDConstants {

    public static final int INTERNAL_SUCCESSFUL_HSDSCH_CELL_CHANGE = 432;

    public static final int INTERNAL_IRAT_HO_CC_EVALUATION = 398;

    public static final int INTERNAL_SOFT_HANDOVER_EVALUATION = 407;

    public static final int INTERNAL_SOFT_HANDOVER_EXECUTION = 408;

    public static final int INTERNAL_IFHO_EVALUATION = 421;

    public static final int INTERNAL_IFHO_EXECUTION_ACTIVE = 423;

    public static final int INTERNAL_FAILED_HSDSCH_CELL_CHANGE = 433;

    public static final int INTERNAL_HSDSCH_CELL_SELECTION_NO_CELL_SELECTED = 436;

    public static final int INTERNAL_OUT_HARD_HANDOVER_FAILURE = 458;

    public static final int ATTACH_IN_2G_AND_3G = 0;

    public static final int ACTIVATE_IN_2G_AND_3G = 1;

    public static final int RAU_IN_2G_AND_3G = 2;

    public static final int ISRAU_IN_2G_AND_3G = 3;

    public final static int DEACTIVATE_IN_2G_AND_3G = 4;

    public static final int ATTACH_IN_4G = 5;

    public final static int DETACH_IN_4G = 6;

    public static final int HANDOVER_IN_4G = 7;

    public static final int TAU_IN_4G = 8;

    public final static int DEDICATED_BEARER_ACTIVATE_IN_4G = 9;

    public final static int DEDICATED_BEARER_DEACTIVATE_IN_4G = 10;

    public static final int PDN_CONNECT_IN_4G = 11;

    public static final int PDN_DISCONNECT_IN_4G = 12;

    public final static int SERVICE_REQUEST_IN_4G = 13;

    public final static int DETACH_IN_2G_AND_3G = 14;

    public final static int SERVICE_REQUEST_IN_2G_AND_3G = 15;

    public static final String MSS_MS_ORIGINATING_EVENT_ID = "0";

    public static final String MSS_MS_TERMINATING_EVENT_ID = "1";

    public static final String MSS_CALL_FORWARDING_EVENT_ID = "2";

    public static final String MSS_ROAMING_CALL_EVENT_ID = "3";

    public static final String MSS_LOCATION_SERVICE_EVENT_ID = "6";

    public static final String MSS_SMS_MS_ORIGINATING_EVENT_ID = "4";

    public static final String MSS_SMS_MS_TERMINATING_EVENT_ID = "5";

    public static final String CALL_DROPS = "Call Drops";

    public static final String CALL_SETUP_FAILURES_EVENT_DESC = "Call Setup Failures";

    public static final int CALL_DROP_EVENT_ID_AS_INTEGER = 438;

    public static final int CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER = 456;

    public static final String CALL_SETUP_FAILURE_EVENT_ID = Integer.toString(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);

    public static final String CALL_DROP_EVENT_ID = Integer.toString(CALL_DROP_EVENT_ID_AS_INTEGER);

    public static final int SOFT_HANDOVER_EVENT_ID_AS_INTEGER = 0;

    public static final int HSDSCH_HANDOVER_EVENT_ID_AS_INTEGER = 1;

    public static final int IFHO_HANDOVER_EVENT_ID_AS_INTEGER = 2;

    public static final int IRAT_HANDOVER_EVENT_ID_AS_INTEGER = 3;

    public static final String SOFT_HANDOVER = "Soft Handover";

    public static final String HSDSCH_HANDOVER = "HSDSCH Handover";

    public static final String IFHO_HANDOVER = "Interfrequency Handover";

    public static final String IRAT_HANDOVER = "IRAT Handover";

    public static final String SOFT_HANDOVER_EVALUATION = "Soft Handover Evaluation";

    public static final String SOFT_HANDOVER_EXECUTION = "Soft Handover Execution Failure";

    public static final String IFHO_EVALUATION = "IFHO Handover Evaluation";

    public static final String IFHO_EXECUTION_ACTIVE = "IFHO Handover Failure";

    public static final String IRAT_HO_CC_EVALUATION = "Inter-RAT Handover Evaluation";

    public static final String OUT_HARD_HANDOVER_FAILURE = "IFHO Handover Failure";

    public static final String FAILED_HSDSCH_CELL_CHANGE = "HSDSCH Failed Cell Change";

    public static final String HSDSCH_CELL_SELECTION_NO_CELL_SELECTED = "HSDSCH No Cell Selected";

    public static final String INTERNAL_PROC_RRC_CONN_SETUP = "4097";

    public static final String INTERNAL_PROC_S1_SIG_CONN_SETUP = "4098";

    public static final String INTERNAL_PROC_INITIAL_CTXT_SETUP = "4106";

    public static final String INTERNAL_PROC_ERAB_SETUP = "4099";

    public static final String INTERNAL_PROC_UE_CTXT_RELEASE = "4125";

    public static final String INTERNAL_PROC_HO_PREP_X2_IN_HFA = "4111";

    public static final String INTERNAL_PROC_HO_PREP_X2_OUT_HFA = "4110";

    public static final String INTERNAL_PROC_HO_EXEC_X2_IN_HFA = "4113";

    public static final String INTERNAL_PROC_HO_EXEC_X2_OUT_HFA = "4112";

    public static final String INTERNAL_PROC_HO_PREP_S1_IN_HFA = "4103";

    public static final String INTERNAL_PROC_HO_PREP_S1_OUT_HFA = "4102";

    public static final String INTERNAL_PROC_HO_EXEC_S1_IN_HFA = "4105";

    public static final String INTERNAL_PROC_HO_EXEC_S1_OUT_HFA = "4104";

    public static final String CATEGORY_ID_2_PREP = "0";

    public static final String CATEGORY_ID_2_EXEC = "1";

    public static final String HIERARCHY_3_SQL = "HIERARCHY_3";

    public static final String EXECUTION_CATEGORY_ID_2_DESC = "Execution";

    public static final String PREPARATION_CATEGORY_ID_2_DESC = "Preparation";

    public static final int GSM_CALL_DROP_EVENT_ID_AS_INTEGER = 34;

    public static final String GSM_CALL_DROP_EVENT_ID = Integer.toString(GSM_CALL_DROP_EVENT_ID_AS_INTEGER);

    private static Map<String, Integer> eventDescMap = new HashMap<String, Integer>();

    static {
        eventDescMap.put(CALL_DROPS, CALL_DROP_EVENT_ID_AS_INTEGER);
        eventDescMap.put(CALL_SETUP_FAILURES_EVENT_DESC, CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER);
    }

    public static final boolean isMssVoiceEvent(final String eventId) {
        if (MSS_MS_ORIGINATING_EVENT_ID.equals(eventId) || MSS_MS_TERMINATING_EVENT_ID.equals(eventId)
                || MSS_CALL_FORWARDING_EVENT_ID.equals(eventId) || MSS_ROAMING_CALL_EVENT_ID.equals(eventId)) {
            return true;
        }
        return false;
    }

    public static final boolean isMssLocationServiceEvent(final String eventId) {
        if (MSS_LOCATION_SERVICE_EVENT_ID.equals(eventId)) {
            return true;
        }
        return false;
    }

    public static final boolean isMssSMSEvent(final String eventId) {
        if (MSS_SMS_MS_ORIGINATING_EVENT_ID.equals(eventId) || MSS_SMS_MS_TERMINATING_EVENT_ID.equals(eventId)) {
            return true;
        }
        return false;
    }

    /**
     * Fetch the event id for a given event description
     *
     * @TODO - this needs to be read from the DIM_E_ event type tables rather than hard coded here
     *
     * @param eventDesc  event description eg CALL_DROPS
     * @return the event id matching this description eg 438
     */
    public static int getEventId(final String eventDesc) {
        if (eventDescMap.containsKey(eventDesc)) {
            return eventDescMap.get(eventDesc);
        }
        throw new RuntimeException("No event id defined for event " + eventDesc);
    }
}
