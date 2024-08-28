package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class CountryCallFailureSummaryQueryResult implements QueryResult {

    private String country;

    private String categoryDescription;

    private int numberOfFailures;

    private int numberOfImpactedSubscribers;

    private double failureRatio;

    private int categoryId;

    private String mcc;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        country = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryDescription = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numberOfImpactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failureRatio = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);

    }

    public String getCountry() {
        return country;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public int getNumberOfImpactedSubscribers() {
        return numberOfImpactedSubscribers;
    }

    public double getFailureRatio() {
        return failureRatio;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getMCC() {
        return mcc;
    }

}
