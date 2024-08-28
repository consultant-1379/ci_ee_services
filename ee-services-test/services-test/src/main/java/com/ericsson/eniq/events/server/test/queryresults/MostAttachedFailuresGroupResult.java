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
public class MostAttachedFailuresGroupResult implements QueryResult {

    private String groupName;

    private int noErrors;

    private int noTotalErrSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        groupName = jsonObject.getString("2");
        noErrors = jsonObject.getInt("3");
        noTotalErrSubscribers = jsonObject.getInt("4");

    }

    public String getGroupName() {
        return groupName;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoTotalErrSubscribers() {
        return noTotalErrSubscribers;
    }

}
