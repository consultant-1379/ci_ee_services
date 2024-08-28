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
public class MssMultipleRankingBSCResult implements QueryResult {

    private String hier3Id;

    private int rank;

    private String ratDesc;

    private int rat;

    private String vendor;

    private String controller;

    private int failures;

    private int successes;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        // TODO Auto-generated method stub
        hier3Id = object.getString("1");

        rank = object.getInt("2");

        ratDesc = object.getString("3");

        rat = object.getInt("4");

        vendor = object.getString("5");

        controller = object.getString("6");

        failures = object.getInt("7");

        successes = object.getInt("8");
    }

    public String getHier3Id() {
        return hier3Id;
    }

    public int getRank() {
        return rank;
    }

    public String getRatDesc() {
        return ratDesc;
    }

    public int getRat() {
        return rat;
    }

    public String getVendor() {
        return vendor;
    }

    public String getController() {
        return controller;
    }

    public int getFailures() {
        return failures;
    }

    public int getSuccesses() {
        return successes;
    }
}
