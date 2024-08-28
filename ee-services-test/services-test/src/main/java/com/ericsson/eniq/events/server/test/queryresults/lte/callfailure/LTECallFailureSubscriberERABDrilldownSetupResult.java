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
 * @author echimma
 * @since 2011
 *
 */
public class LTECallFailureSubscriberERABDrilldownSetupResult implements QueryResult {
    private String hier3ID;

    private String hier321ID;

    private String eventTime;

    private String imsi;

    private String tac;

    private String terminalMake;

    private String terminalModel;

    private String eventType;

    private String eNodeB;

    private String accessArea;

    private String vendor;

    private String setupResult;

    private String setupReqQci;

    private String setupReqArp;

    private String setupReqPci;

    private String setupReqPvi;

    private String failure3gppCause;

    private String setupAttAccType;

    private String setupSuccAccType;

    private String setupFail3gppCauseGroup;

    private String releaseReqQci;

    private String erabDataLost;

    private String erabReleaseSucc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        if (object.length() == 21) {
            //INTERNAL_PROC_ERAB_SETUP( ERAB Drill down)
            //INTERNAL_PROC_INITIAL_CTXT_SETUP( ERAB Drill down)
            hier3ID = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
            hier321ID = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
            eventTime = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
            imsi = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
            tac = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
            terminalMake = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
            terminalModel = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
            eventType = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
            eNodeB = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
            accessArea = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
            vendor = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
            setupResult = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
            setupReqQci = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
            setupReqArp = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
            setupReqPci = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
            setupReqPvi = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
            failure3gppCause = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
            setupAttAccType = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
            setupSuccAccType = object.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
            setupFail3gppCauseGroup = object.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        } else {
            //INTERNAL_PROC_UE_CTXT_RELEASE( ERAB Drill down)
            hier3ID = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
            hier321ID = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
            eventTime = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
            imsi = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
            tac = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
            terminalMake = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
            terminalModel = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
            eventType = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
            eNodeB = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
            accessArea = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
            vendor = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
            releaseReqQci = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
            setupReqArp = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
            setupReqPci = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
            setupReqPvi = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
            erabDataLost = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
            erabReleaseSucc = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        }
    }

    public String gethier3ID() {
        return hier3ID;
    }

    public String getHier321ID() {
        return hier321ID;
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

    public String getENodeB() {
        return eNodeB;
    }

    public String getAccessArea() {
        return accessArea;
    }

    public String getVendor() {
        return vendor;
    }

    public String getSetupResult() {
        return setupResult;
    }

    public String getSetupReqQci() {
        return setupReqQci;
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

    public String getFailure3gppCause() {
        return failure3gppCause;
    }

    public String getSetupAttAccType() {
        return setupAttAccType;
    }

    public String getSetupSuccAccType() {
        return setupSuccAccType;
    }

    public String getSetupFail3gppCauseGroup() {
        return setupFail3gppCauseGroup;
    }

    public String getReleaseReqQci() {
        return releaseReqQci;
    }

    public String getErabDataLost() {
        return erabDataLost;
    }

    public String getErabReleaseSucc() {
        return erabReleaseSucc;
    }
}
