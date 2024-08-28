/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ewanggu
 * @since 2011
 *
 */
public class GSMCallFailureAccessAreaDetailedEventAnalysisResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private int tac;

    private String categoryDesc;

    private String causeValue;

    private String extendedCauseValue;

    private String terminalMake;

    private String terminalModel;

    private String releaseType;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        categoryDesc = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        releaseType = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getImsi() {
        return imsi;
    }

    public int getTac() {
        return tac;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public String getCauseValue() {
        return causeValue;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public String getExtendedCauseValue() {
        return extendedCauseValue;
    }

    public String getTerminalMake() {
        return terminalMake;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

}
