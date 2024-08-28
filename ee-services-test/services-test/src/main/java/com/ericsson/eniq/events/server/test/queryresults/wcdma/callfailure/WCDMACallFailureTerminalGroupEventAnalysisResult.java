package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class WCDMACallFailureTerminalGroupEventAnalysisResult implements QueryResult {

    private int eventID;

    private String eventDesc;

    private int noOfErrors;

    private int impactedSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {

        eventID = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        noOfErrors = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public int getNoOfErrors() {
        return noOfErrors;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
}
