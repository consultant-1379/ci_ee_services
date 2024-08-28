/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
@Ignore
public class MultipleSubscriberRankingResult implements QueryResult {

    private String imsi;

    private int noOfErrors;

    private String noOfSuccesses;

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        imsi = jsonObject.getString("2");
        noOfErrors = jsonObject.getInt("3");
        noOfSuccesses = jsonObject.getString("4");
    }

    public String getIMSI() {
        return imsi;
    }

    /**
     * @return
     */
    public int getNoErrors() {
        return noOfErrors;
    }

    /**
     * @return
     */
    public String getNoSuccesses() {
        return noOfSuccesses;
    }

}
