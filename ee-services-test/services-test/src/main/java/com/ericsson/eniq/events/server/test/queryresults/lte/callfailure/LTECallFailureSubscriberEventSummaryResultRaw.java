/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2011
 *
 */

public class LTECallFailureSubscriberEventSummaryResultRaw implements QueryResult {

    private String eventId;

    private String eventType;

    private String imsi;

    private String noOfErrors;

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getImsi() {
        return imsi;
    }

    public String getNoOfErrors() {
        return noOfErrors;
    }

    @Override
    public void parseJSONObject(JSONObject jsonObject) throws JSONException {
        imsi = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }
}
