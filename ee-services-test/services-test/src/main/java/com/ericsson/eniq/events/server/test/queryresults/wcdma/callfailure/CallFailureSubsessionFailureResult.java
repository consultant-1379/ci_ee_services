/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author EEMECOY
 *
 */
public class CallFailureSubsessionFailureResult implements QueryResult {

    private int noFailures;

    private String eventDesc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        noFailures = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    }

    public int getFailures() {
        return noFailures;
    }

    public String getEventDesc() {
        return eventDesc;
    }

}
