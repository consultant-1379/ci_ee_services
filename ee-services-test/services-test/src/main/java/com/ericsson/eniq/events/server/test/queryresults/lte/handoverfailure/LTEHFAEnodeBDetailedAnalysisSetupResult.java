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
public class LTEHFAEnodeBDetailedAnalysisSetupResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private String tac;

    private String terminalMake;

    private String terminalModel;

    private String eventType;

    private String failureReason;

    private String eventId;

    private String sourceController;

    private String sourceAccessArea;

    private String targetController;

    private String targetAccessArea;

    private String vendor;

    private String rat;

    private String mcc;

    private String mnc;

    private String duration;

    private String hoSourceTargetType;

    private String hoTargetSelectionType;

    private String hoPrepOutAttemptCause;

    private String hoOutPrepErabReqBitmap;

    private String hoOutPrepErabFailBitmap;

    private String hoOutExecErabReqBitmap;

    private String hoOutExecErabFailBitmap;

    private String hoType;

    private String randomAccessType;

    private String spidValue;

    private String noOfErabs;

    private String drxConfigIndex;

    private String hoPacketForward;

    private String prepInResultUeCtxt;

    private String causeGroup3Gpp;

    private String cause3Gpp;

    private String srvccType;

    private String hoExecOutAttemptCause;

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
            parseJSONObjectPrepX2In(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_X2_OUT) {
            parseJSONObjectPrepX2Out(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_X2_IN) {
            parseJSONObjectExecX2In(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_X2_OUT) {
            parseJSONObjectExecX2Out(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_S1_IN) {
            parseJSONObjectPrepS1In(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_PREP_S1_OUT) {
            parseJSONObjectPrepS1Out(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_S1_IN) {
            parseJSONObjectExecS1In(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_HO_EXEC_S1_OUT) {
            parseJSONObjectExecS1Out(jsonObject);
        }
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectExecS1Out(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        hoTargetSelectionType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        drxConfigIndex = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        hoExecOutAttemptCause = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectExecS1In(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        randomAccessType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectPrepS1Out(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        hoTargetSelectionType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        hoPrepOutAttemptCause = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        srvccType = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        cause3Gpp = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        causeGroup3Gpp = jsonObject.getString(INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_NINHTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectPrepS1In(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        randomAccessType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        spidValue = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        prepInResultUeCtxt = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        cause3Gpp = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        causeGroup3Gpp = jsonObject.getString(INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_NINHTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectExecX2Out(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        hoTargetSelectionType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        drxConfigIndex = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        hoPacketForward = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        hoType = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        hoExecOutAttemptCause = jsonObject.getString(INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_NINHTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectExecX2In(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        randomAccessType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        hoPacketForward = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        hoType = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectPrepX2Out(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        hoTargetSelectionType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        hoPrepOutAttemptCause = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        hoType = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        cause3Gpp = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        causeGroup3Gpp = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @param jsonObject
     */
    private void parseJSONObjectPrepX2In(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalMake = jsonObject.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        terminalModel = jsonObject.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        failureReason = jsonObject.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        sourceController = jsonObject.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        sourceAccessArea = jsonObject.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        targetController = jsonObject.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        targetAccessArea = jsonObject.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rat = jsonObject.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        mcc = jsonObject.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        mnc = jsonObject.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        duration = jsonObject.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        hoSourceTargetType = jsonObject.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        randomAccessType = jsonObject.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        spidValue = jsonObject.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        prepInResultUeCtxt = jsonObject.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        cause3Gpp = jsonObject.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        causeGroup3Gpp = jsonObject.getString(INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = jsonObject.getString(INDEX_OF_TWENTY_NINHTH_COLUMN_IN_JSON_RESULT);
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

    public String getFailureReason() {
        return failureReason;
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

    public String getRat() {
        return rat;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getDuration() {
        return duration;
    }

    public String getHoSourceTargetType() {
        return hoSourceTargetType;
    }

    public String getHoTargetSelectionType() {
        return hoTargetSelectionType;
    }

    public String getHoPrepOutAttemptCause() {
        return hoPrepOutAttemptCause;
    }

    public String getHoOutPrepErabReqBitmap() {
        return hoOutPrepErabReqBitmap;
    }

    public String getHoOutPrepErabFailBitmap() {
        return hoOutPrepErabFailBitmap;
    }

    public String getHoOutExecErabReqBitmap() {
        return hoOutExecErabReqBitmap;
    }

    public String getHoOutExecErabFailBitmap() {
        return hoOutExecErabFailBitmap;
    }

    public String getHoType() {
        return hoType;
    }

    public String getRandomAccessType() {
        return randomAccessType;
    }

    public String getSpidValue() {
        return spidValue;
    }

    public String getNoOfErabs() {
        return noOfErabs;
    }

    public String getDrxConfigIndex() {
        return drxConfigIndex;
    }

    public String getHoPacketForward() {
        return hoPacketForward;
    }

    public String getPrepInResultUeCtxt() {
        return prepInResultUeCtxt;
    }

    public String getCauseGroup3Gpp() {
        return causeGroup3Gpp;
    }

    public String getCause3Gpp() {
        return cause3Gpp;
    }

    public String getSrvccType() {
        return srvccType;
    }

    public String getHoExecOutAttemptCause() {
        return hoExecOutAttemptCause;
    }
}