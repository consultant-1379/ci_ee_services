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
public class HandoverFailureRNCRankingResult implements QueryResult {
    private int rank;

    private int ratId;

    private String rat;

    private String vendor;

    private String rnc;

    private int failures;

    public int getRank() {
        return rank;
    }

    public String getRat() {
        return rat;
    }

    public int getRatId() {
        return ratId;
    }

    public String getVendor() {
        return vendor;
    }

    public String getRnc() {
        return rnc;
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
        rat = object.getString("2");
        ratId = object.getInt("3");
        vendor = object.getString("4");
        rnc = object.getString("5");
        failures = object.getInt("6");

    }
}
