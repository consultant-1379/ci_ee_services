/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author etonayr
 * @since 2012
 *
 */
public class AccessAreaGroupCauseCodeAnalysisResult implements QueryResult {

    private String causeCode;

    private String causeCodeDescription;

    private int numberOfFailures;

    private int impactedSubscribers;

    private String groupName;

    public String getCauseCode() {
        return causeCode;
    }

    public String getCauseCodeDescription() {
        return causeCodeDescription;
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String getGroupName() {
        return groupName;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeCode = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDescription = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        groupName = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

}
