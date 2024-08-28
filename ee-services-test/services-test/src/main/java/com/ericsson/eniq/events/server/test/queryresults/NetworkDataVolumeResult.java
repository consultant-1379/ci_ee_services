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
 * @author evarzol
 * @since July, 2011
 *
 */
@Ignore
public class NetworkDataVolumeResult implements QueryResult {
    public static final String DL_JSON_ID = "2";
    public static final String UL_JSON_ID = "3";
    public static final String PDP_SESSIONS_JSON_ID = "4";

    private Long dl;
    private Long ul;
    private Long pdpSessions;
    
    public Long getDl() {
        return dl;
    }
    
    public Long getUl() {
        return ul;
    }
    
    public Long getPdpSessions() {
        return pdpSessions;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
    	dl = (new Double(jsonObject.getString(DL_JSON_ID))).longValue();
    	ul = (new Double(jsonObject.getString(UL_JSON_ID))).longValue();
    	pdpSessions = new Long(jsonObject.getString(PDP_SESSIONS_JSON_ID));
    }

}
