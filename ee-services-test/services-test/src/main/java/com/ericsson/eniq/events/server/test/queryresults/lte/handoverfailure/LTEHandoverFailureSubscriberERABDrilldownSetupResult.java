/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;


/**
 * @author evijred
 * @since 2011
 *
 */
public class LTEHandoverFailureSubscriberERABDrilldownSetupResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private String tac;

    private String terminalMake;

    private String terminalModel;

    private String eventType;

    private String eventId;

    private String sourceController;

    private String sourceAccessArea;

    private String targetController;

    private String targetAccessArea;

    private String vendor;

    private String mcc;

    private String mnc;

    private String setupReqArp;

    private String setupReqPci;

    private String setupReqPvi;

    private String hoInPrepErabReq;

    private String hoInPrepErabResult;
    
    private String qciIdDesc;

    private enum EventType {
        INTERNAL_PROC_HO_PREP_X2_IN, INTERNAL_PROC_HO_PREP_X2_OUT, INTERNAL_PROC_HO_EXEC_X2_IN, INTERNAL_PROC_HO_EXEC_X2_OUT, INTERNAL_PROC_HO_PREP_S1_IN, INTERNAL_PROC_HO_PREP_S1_OUT, INTERNAL_PROC_HO_EXEC_S1_IN, INTERNAL_PROC_HO_EXEC_S1_OUT
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        final EventType eventType1 = getEventType(jsonObject);
        if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_X2_IN) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_X2_OUT) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_X2_IN) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_X2_OUT) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_S1_IN) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_S1_OUT) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_S1_IN) {
        	parseJSONObjectEventTypes(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_S1_OUT) {
        	parseJSONObjectEventTypes(jsonObject);
        }
    }

    private void parseJSONObjectEventTypes(final JSONObject jsonObject) throws JSONException {
    	eventTime = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        setupReqPci = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        setupReqPvi = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        hoInPrepErabResult = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        hoInPrepErabReq = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        setupReqArp = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        qciIdDesc = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
    }

    private EventType getEventType(final JSONObject jsonObject) throws JSONException {
        final String eventIdDescription = jsonObject.getString("12");
        if ("INTERNAL_PROC_HO_PREP_X2_IN".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_PREP_X2_IN;
        } else if ("INTERNAL_PROC_HO_PREP_X2_OUT".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_PREP_X2_OUT;
        } else if ("INTERNAL_PROC_HO_EXEC_X2_IN".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_EXEC_X2_IN;
        } else if ("INTERNAL_PROC_HO_EXEC_X2_OUT".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_EXEC_X2_OUT;
        } else if ("INTERNAL_PROC_HO_PREP_S1_IN".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_PREP_S1_IN;
        } else if ("INTERNAL_PROC_HO_PREP_S1_OUT".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_PREP_S1_OUT;
        } else if ("INTERNAL_PROC_HO_EXEC_S1_IN".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_EXEC_S1_IN;
        } else if ("INTERNAL_PROC_HO_EXEC_S1_OUT".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_HO_EXEC_S1_OUT;
        }
        return EventType.INTERNAL_PROC_HO_PREP_X2_IN;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getImsi() {
        return imsi;
    }

    public String getTac() {
        return tac;
    }

    public String getTerminalMake() {
        return terminalMake;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventId() {
        return eventId;
    }

    public String getSourceController() {
        return sourceController;
    }

    public String getSourceAccessArea() {
        return sourceAccessArea;
    }

    public String getTargetController() {
        return targetController;
    }

    public String getTargetAccessArea() {
        return targetAccessArea;
    }

    public String getVendor() {
        return vendor;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getSetupReqArp() {
        return setupReqArp;
    }

    public String getSetupReqPci() {
        return setupReqPci;
    }

    public String getSetupReqPvi() {
        return setupReqPvi;
    }

    public String getHoInPrepErabReq() {
        return hoInPrepErabReq;
    }

    public String getHoInPrepErabResult() {
        return hoInPrepErabResult;
    }
    
    public String getQciIdDesc() {
        return qciIdDesc;
    }
    
    
}
