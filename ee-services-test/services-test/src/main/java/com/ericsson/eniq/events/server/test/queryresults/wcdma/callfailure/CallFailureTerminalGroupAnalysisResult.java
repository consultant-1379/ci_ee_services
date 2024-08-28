/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author EEMECOY
 *
 */
public class CallFailureTerminalGroupAnalysisResult implements QueryResult {

    private int numFailures;

    private int impactedSubscribers;

    private String groupName;

    private int rank;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
    	rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        groupName = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }

    public int getRankNumber(){
    	return rank;
    }

    public int getNumFailures() {
        return numFailures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String getGroupName() {
        return groupName;
    }

}
