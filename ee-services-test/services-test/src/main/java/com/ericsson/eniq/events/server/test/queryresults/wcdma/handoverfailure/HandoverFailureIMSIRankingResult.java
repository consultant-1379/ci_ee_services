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
public class HandoverFailureIMSIRankingResult implements QueryResult {
    private int rank;

    private int imsi;

    private int failures;

    public int getRank() {
        return rank;
    }

    public int getImsi() {
        return imsi;
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
        imsi = object.getInt("2");
        failures = object.getInt("4");

    }

}
