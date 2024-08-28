/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * 
 * @author ezhelao
 *
 */
public class CallFailurOperatorDrillRoamingAnalysisQueryResult implements QueryResult {

    private String eventIDDesc;
    private int eventID;
    private int  numErrors;
    private int impactedSubscribers;
    private  String operatorName;
    private  String mcc;
    private String mnc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventIDDesc = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventID = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numErrors = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        operatorName = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        mcc= object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getEventIDDesc() {
        return eventIDDesc;
    }

    public int getEventID() {
        return eventID;
    }

    public int getNumErrors() {
        return numErrors;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }
}
