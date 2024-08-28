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
public class MssMultipleRankingUnansweredCallsResult implements QueryResult {

    private int rank;

    private String imsi;

    private String callingPartyNum;

    private int noOfUnansweredCalls;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt("1");
        imsi = object.getString("2");
        callingPartyNum = object.getString("3");
        noOfUnansweredCalls = object.getInt("4");
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
    public String getCallingPartyNum() {
        return callingPartyNum;
    }

    /**
     * @return
     */
    public int getNoOfUnansweredCalls() {
        return noOfUnansweredCalls;
    }

}
