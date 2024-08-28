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
public class MssMultipleRankingICCResult implements QueryResult {

    private int rank;

    private String iccDesc;

    private String icc;

    private int failures;

    private int successes;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt("1");
        iccDesc = object.getString("2");
        icc = object.getString("3");
        failures = object.getInt("4");
        successes = object.getInt("5");
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
    public String getICCDesc() {
        return iccDesc;
    }

    /**
     * @return
     */
    public Integer getFailures() {
        return failures;
    }

    /**
     * @return
     */
    public String getICC() {
        return icc;
    }

    /**
     * @return
     */
    public Integer getSuccesses() {
        return successes;
    }

}
