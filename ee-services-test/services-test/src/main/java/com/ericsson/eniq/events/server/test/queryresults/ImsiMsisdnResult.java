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
 * @author ehaoswa
 * @since 2011
 *
 */
@Ignore
public class ImsiMsisdnResult implements QueryResult {
    public static final String MSISDN_JSON_ID = "1";

    private String msisdn;

    public String getMsisdn() {
        return msisdn;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        msisdn = jsonObject.getString(MSISDN_JSON_ID);
    }

}
