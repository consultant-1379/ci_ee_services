/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author edivkir
 * @since 2011
 *
 */
public class RoamingAnalysisResult implements QueryResult {

    private String operator;

    private String country;

    private int noErrors;

    private int noSuccesses;

    private int roamingSubscribers;

    public String getOperator() {
        return operator;
    }

    public String getCountry() {
        return country;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoSuccesses() {
        return noSuccesses;
    }

    public int getRoamingSubscribers() {
        return roamingSubscribers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        operator = jsonObject.getString("1");
        country = jsonObject.getString("1");
        noErrors = jsonObject.getInt("2");
        noSuccesses = jsonObject.getInt("3");
        roamingSubscribers = jsonObject.getInt("4");

    }
}
