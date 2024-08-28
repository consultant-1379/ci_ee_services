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
public class MssMultipleRankingTerminalResult implements QueryResult {
    private int rank;

    private String manufacturer;

    private String marketingName;

    private String tac;

    private int failures;

    private int successes;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt("1");
        manufacturer = object.getString("2");
        marketingName = object.getString("3");
        tac = object.getString("4");
        failures = object.getInt("5");
        successes = object.getInt("6");
    }

    /**
     * @return
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @return
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * @return
     */
    public String getTac() {
        return tac;
    }

    /**
     * @return
     */
    public int getFailures() {
        return failures;
    }

    /**
     * @return
     */
    public int getSuccesses() {
        return successes;
    }

}
