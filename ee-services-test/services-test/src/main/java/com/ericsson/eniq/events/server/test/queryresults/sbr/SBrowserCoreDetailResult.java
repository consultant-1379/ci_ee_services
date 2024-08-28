/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.sbr;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserCoreDetailResult implements QueryResult {

    private String eventTime;

    private int eventId;

    private String eventDesc;

    public String getEventTime() {
        return eventTime;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public int getEventId() {
        return eventId;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString("1");
        eventId = object.getInt("2");
        eventDesc = object.getString("3");
    }

}
