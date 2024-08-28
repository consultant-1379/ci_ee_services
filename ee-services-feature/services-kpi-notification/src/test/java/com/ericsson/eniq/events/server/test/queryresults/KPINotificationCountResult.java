/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
public class KPINotificationCountResult implements QueryResult {

    private String severityText;

    private int numAlarms;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        severityText = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        numAlarms = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    }

    public String getPerceivedSeverityText() {
        return severityText;
    }

    public int getNumberAlarms() {
        return numAlarms;
    }

}
