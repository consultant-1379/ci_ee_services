/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echimma
 * @since 2011
 * 
 *
 */
public class LTEHFACauseCodeRankingResults implements QueryResult {

    private int rank;

    private String eventTypeDesc;

    private String causeCodeDesc;

    private String causeCodeID;

    private int failures;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventTypeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        causeCodeID = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    public int getRank() {
        return rank;
    }

    public String getEventTypeDesc() {
        return eventTypeDesc;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    public String getCauseCodeID() {
        return causeCodeID;
    }

    public int getFailures() {
        return failures;
    }
}
