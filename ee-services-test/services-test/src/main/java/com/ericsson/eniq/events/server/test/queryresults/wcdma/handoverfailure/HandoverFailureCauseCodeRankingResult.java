/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class HandoverFailureCauseCodeRankingResult implements QueryResult {
    private int rank;

    private String causeCode;

    private String causeCodeId;

    private String categoryId;

    private int failures;

    public int getRank() {
        return rank;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public String getCauseCodeId() {
        return causeCodeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getFailures() {
        return failures;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt("1");
        categoryId = object.getString("3");
        causeCode = object.getString("4");
        causeCodeId = object.getString("5");
        failures = object.getInt("6");
    }
}
