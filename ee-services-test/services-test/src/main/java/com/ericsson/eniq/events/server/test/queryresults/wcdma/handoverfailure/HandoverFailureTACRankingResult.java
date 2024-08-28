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
public class HandoverFailureTACRankingResult implements QueryResult {

    private int rank;

    private String manufacturer;

    private String model;

    private int tac;

    private int failures;

    public int getRank() {
        return rank;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getTac() {
        return tac;
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
        manufacturer = object.getString("2");
        model = object.getString("3");
        tac = object.getInt("4");
        failures = object.getInt("5");

    }

}
