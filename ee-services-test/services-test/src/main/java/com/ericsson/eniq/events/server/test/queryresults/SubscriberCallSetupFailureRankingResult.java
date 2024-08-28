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
public class SubscriberCallSetupFailureRankingResult implements QueryResult {

    private int rank;

    private int imsi;

    private int numFailures;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    public int getRank() {
        return rank;
    }

    public int getImsi() {
        return imsi;
    }

    public int getNumFailures() {
        return numFailures;
    }

}
