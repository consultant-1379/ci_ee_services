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
public class MssMultipleRankingDurationCallsResult implements QueryResult {

    private int rank;

    private String imsi;

    private String msisdn;

    private String callingPartyNum;

    private String callDuration;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt("1");
        imsi = object.getString("2");
        msisdn = object.getString("3");
        callingPartyNum = object.getString("4");
        callDuration = object.getString("5");
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
    public String getIMSI() {
        return imsi;
    }

    /**
     * @return
     */
    public String getMSISDN() {
        return msisdn;
    }

    /**
     * @return
     */
    public String getCallingPartyNum() {
        return callingPartyNum;
    }

    /**
     * @return
     */
    public String getCallDuration() {
        return callDuration;
    }
}