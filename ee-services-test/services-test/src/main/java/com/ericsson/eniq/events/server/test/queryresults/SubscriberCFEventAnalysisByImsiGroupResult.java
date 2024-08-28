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
 * @author eflatib
 * @since 2011
 *
 */
public class SubscriberCFEventAnalysisByImsiGroupResult implements QueryResult {

    private String eventType;

    private int numFailures;

    private int impactedSubscriber;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventType = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        impactedSubscriber = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the numFailures
     */
    public int getNumFailures() {
        return numFailures;
    }

    /**
     * @return the impactedSubscriber
     */
    public int getImpactedSubscriber() {
        return impactedSubscriber;
    }

}
