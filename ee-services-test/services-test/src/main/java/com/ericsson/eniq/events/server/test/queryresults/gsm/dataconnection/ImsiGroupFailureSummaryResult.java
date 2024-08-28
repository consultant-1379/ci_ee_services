/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.*;
import com.ericsson.eniq.events.server.test.queryresults.*;

/**
 * @author eatiaro
 * @since 2011
 *
 */
public class ImsiGroupFailureSummaryResult implements QueryResult {

    private String eventType;

    private int failures;
    
    private int impactedSubscribers;
    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
    	eventType = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
    	failures = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
    
    public String getEventType() {
        return eventType;
    }
}
