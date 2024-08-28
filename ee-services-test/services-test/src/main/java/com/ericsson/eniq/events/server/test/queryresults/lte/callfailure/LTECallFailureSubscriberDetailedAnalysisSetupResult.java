/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2011
 *
 */
public class LTECallFailureSubscriberDetailedAnalysisSetupResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private String tac;

    private String terminalMake;

    private String terminalModel;

    private String eventType;

    private String eventId;

    private String causeCode;

    private String eNodeB;

    private String accessArea;

    private String vendor;
    
    private String radioAccessType ;

    private String mcc;

    private String mnc;

    private String duration;

    private String rrcEstablCause;
    
    private String gummeiTypeDesc;
    
    private String bundlingModeDesc;

    private String noOfErabs;

    private String accUlReqGbr;

    private String accUlAdmGbr;

    private String accDlReqGbr;

    private String accDlAdmGbr;

    private String erabDataLostBitMap;

    private String erabDataLost;

    private String erabReleaseSuc;

    private String hoOutPreperabFail;

    private String erabHoPrepDataLost;

    private String internalReleaseCause;

    private String triggeringNode;

    private enum EventType {
        INTERNAL_PROC_RRC_CONN_SETUP, INTERNAL_PROC_S1_SIG_CONN_SETUP, INTERNAL_PROC_INITIAL_CTXT_AND_ERAB_SETUP, INTERNAL_PROC_UE_CTXT_RELEASE
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        final EventType eventType1 = getEventType(jsonObject);
        if (eventType1 == EventType.INTERNAL_PROC_RRC_CONN_SETUP) {
            parseJSONObjectRrcConnSetUp(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_S1_SIG_CONN_SETUP) {
            parseJSONObjectS1SigConnSetUp(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_INITIAL_CTXT_AND_ERAB_SETUP) {
            parseJSONObjectInitialCxtAndErabConnSetUp(jsonObject);
        } else if (eventType1 == EventType.INTERNAL_PROC_UE_CTXT_RELEASE) {
            parseJSONObjectUeCxtRelease(jsonObject);
        }
    }

    private EventType getEventType(final JSONObject jsonObject) throws JSONException {
        final String eventIdDescription = jsonObject.getString("10");
        if ("INTERNAL_PROC_RRC_CONN_SETUP".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_RRC_CONN_SETUP;
        } else if ("INTERNAL_PROC_S1_SIG_CONN_SETUP".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_S1_SIG_CONN_SETUP;
        } else if ("INTERNAL_PROC_INITIAL_CTXT_SETUP".equals(eventIdDescription)
                || "INTERNAL_PROC_ERAB_SETUP".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_INITIAL_CTXT_AND_ERAB_SETUP;
        } else if ("INTERNAL_PROC_UE_CTXT_RELEASE".equals(eventIdDescription)) {
            return EventType.INTERNAL_PROC_UE_CTXT_RELEASE;
        }
        return EventType.INTERNAL_PROC_RRC_CONN_SETUP;
    }

    private void parseJSONObjectRrcConnSetUp(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        tac = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        causeCode = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eNodeB = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        radioAccessType = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        duration = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        rrcEstablCause = object.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        gummeiTypeDesc = object.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
    }

    private void parseJSONObjectS1SigConnSetUp(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        tac = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        causeCode = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eNodeB = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        radioAccessType = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        duration = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
    }

    private void parseJSONObjectInitialCxtAndErabConnSetUp(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        tac = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        causeCode = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eNodeB = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        radioAccessType = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        duration = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        noOfErabs = object.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        accUlReqGbr = object.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        accUlAdmGbr = object.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        accDlReqGbr = object.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        accDlAdmGbr = object.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
    }

    private void parseJSONObjectUeCxtRelease(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        tac = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        causeCode = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        eNodeB = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
        radioAccessType = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        
        mcc = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        duration = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        internalReleaseCause = object.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        triggeringNode = object.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        bundlingModeDesc = object.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        noOfErabs = object.getString(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        erabDataLostBitMap = object.getString(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        erabDataLost = object.getString(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        erabReleaseSuc = object.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        hoOutPreperabFail = object.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        erabHoPrepDataLost = object.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
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

    public String getCauseCode() {
        return causeCode;
    }

    public String getENodeB() {
        return eNodeB;
    }

    public String getAccessArea() {
        return accessArea;
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

    public String getDuration() {
        return duration;
    }

    public String getRrcEstablCause() {
        return rrcEstablCause;
    }

    public String getGummeiTypeDesc() {
        return gummeiTypeDesc;
    }

    public String getBundlingModeDesc() {
        return bundlingModeDesc;
    }

    public String getNoOfErabs() {
        return noOfErabs;
    }

    public String getAccUlReqGbr() {
        return accUlReqGbr;
    }

    public String getAccUlAdmGbr() {
        return accUlAdmGbr;
    }

    public String getAccDlReqGbr() {
        return accDlReqGbr;
    }

    public String getAccDlAdmGbr() {
        return accDlAdmGbr;
    }

    public String getErabDataLostBitMap() {
        return erabDataLostBitMap;
    }

    public String getErabDataLost() {
        return erabDataLost;
    }

    public String getErabReleaseSuc() {
        return erabReleaseSuc;
    }

    public String getHoOutPreperabFail() {
        return hoOutPreperabFail;
    }

    public String getErabHoPrepDataLost() {
        return erabHoPrepDataLost;
    }

    public String getInternalReleaseCause() {
        return internalReleaseCause;
    }

    public String getTriggeringNode() {
        return triggeringNode;
    }

    public String getEventId() {
        return eventId;
    }

    public String getRadioAccessType() {
        return radioAccessType;
    }
}