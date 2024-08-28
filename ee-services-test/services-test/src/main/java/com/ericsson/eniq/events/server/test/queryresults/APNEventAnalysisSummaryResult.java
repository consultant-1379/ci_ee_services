package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

public class APNEventAnalysisSummaryResult implements QueryResult {

    private String apn;

    private int eventId;

    private String eventType;

    private int noOfErrors;

    private int noOfSuccesses;

    private int occurrences;

    private double successRatio;

    private int impactedSubscribers;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        apn = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventId = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noOfErrors = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        noOfSuccesses = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        occurrences = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        successRatio = object.getDouble(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

    public String getApn() {
        return apn;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public int getNoOfErrors() {
        return noOfErrors;
    }

    public int getNoOfSuccesses() {
        return noOfSuccesses;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public double getSuccessRatio() {
        return successRatio;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

}
