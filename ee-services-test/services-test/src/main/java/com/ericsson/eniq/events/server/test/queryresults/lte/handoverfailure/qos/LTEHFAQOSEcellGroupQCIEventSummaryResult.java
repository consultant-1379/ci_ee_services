/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.qos;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;


public class LTEHFAQOSEcellGroupQCIEventSummaryResult implements QueryResult {
    private String eCellGroupName;

    private String qciID;

    private String eventID;

    private String eventType;

    private int failures;

    private int impactedSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
    	eCellGroupName = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        qciID = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventID = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public String getENodeBGroup() {
        return eCellGroupName;
    }

    public String getQciID() {
        return qciID;
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
}
