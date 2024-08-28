/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.mss;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echimma
 * @since 2011
 *
 */
public class MssMultipleRankingMSCResult implements QueryResult {

    private String evntsrcId;

    private int rank;

    private String vendor;

    private String msc;

    private int failures;

    private int successes;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        // TODO Auto-generated method stub
        evntsrcId = object.getString("1");

        rank = object.getInt("2");

        vendor = object.getString("3");

        msc = object.getString("4");

        failures = object.getInt("5");

        successes = object.getInt("6");
    }

    public String getEvntsrcId() {
        return evntsrcId;
    }

    public int getRank() {
        return rank;
    }

    public String getVendor() {
        return vendor;
    }

    public String getMsc() {
        return msc;
    }

    public int getFailures() {
        return failures;
    }

    public int getSuccesses() {
        return successes;
    }
}
