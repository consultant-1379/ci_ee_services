/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 * @since 2012
 *
 */

public class LTEHFATerminalGroupEventSummaryResultRaw implements QueryResult {

    private String eventId;

    private String eventType;

    private String group;

    private int failures;

    private int impactedSubscribers;

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getGroup() {
        return group;
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public void setImpactedSubscribers(final int impactedSubscribers) {
        this.impactedSubscribers = impactedSubscribers;
    }

    @Override
    public void parseJSONObject(JSONObject jsonObject) throws JSONException {
    	group = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
    	eventType = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    	eventId = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failures = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }
}
