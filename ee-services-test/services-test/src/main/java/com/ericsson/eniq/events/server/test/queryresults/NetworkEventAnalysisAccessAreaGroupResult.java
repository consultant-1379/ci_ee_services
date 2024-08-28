/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author ebrowpa
 * @since 2011
 *
 */
public class NetworkEventAnalysisAccessAreaGroupResult implements QueryResult {

    private String eventType;

    private int failures;

    private int impactedSubscribers;

    private int eventID;

    private String groupName;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        groupName = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        eventID = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the failures
     */
    public int getFailures() {
        return failures;
    }

    /**
     * @return the impactedSubscribers
     */
    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public int getEventID() {
        return eventID;
    }

    public String getGroupName() {
        return groupName;
    }
}
