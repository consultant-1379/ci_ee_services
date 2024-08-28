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
public class MssMultipleRankingAccessAreaResult implements QueryResult {

    private String hier321Id;

    private int rank;

    private String ratDesc;

    private String rat;

    private String ranVendor;

    private String controller;

    private String accessArea;

    private String failures;

    private String successes;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        // TODO Auto-generated method stub
        hier321Id = object.getString("1");
        rank = object.getInt("2");
        ratDesc = object.getString("3");
        rat = object.getString("4");
        ranVendor = object.getString("5");
        controller = object.getString("6");
        accessArea = object.getString("7");
        failures = object.getString("8");
        successes = object.getString("9");
    }

    public String getHier321Id() {
        return hier321Id;
    }

    public int getRank() {
        return rank;
    }

    public String getRatDesc() {
        return ratDesc;
    }

    public String getRat() {
        return rat;
    }

    public String getRanVendor() {
        return ranVendor;
    }

    public String getController() {
        return controller;
    }

    public String getAccessArea() {
        return accessArea;
    }

    public String getFailures() {
        return failures;
    }

    public String getSuccesses() {
        return successes;
    }
}
