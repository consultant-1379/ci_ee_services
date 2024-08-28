/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
public class MostPopularGroupsResult implements QueryResult {

    private String groupName;

    private int noEvents;

    private int noTotalErrSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        groupName = jsonObject.getString("2");
        noEvents = jsonObject.getInt("3");
        noTotalErrSubscribers = jsonObject.getInt("4");
    }

    public String getTacGroupName() {
        return groupName;
    }

    public int getNoEvents() {
        return noEvents;
    }

    public int getNoTotalErrSubscribers() {
        return noTotalErrSubscribers;
    }

}
