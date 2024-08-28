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
public class MostPopularEventSummaryGroupResult implements QueryResult {

    private String groupName;

    private int noErrors;

    private int noSuccesses;

    private int occurrences;

    private double successRatio;

    private int noTotalErrSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        groupName = jsonObject.getString("2");
        noErrors = jsonObject.getInt("3");
        noSuccesses = jsonObject.getInt("4");
        occurrences = jsonObject.getInt("5");
        successRatio = jsonObject.getDouble("6");
        noTotalErrSubscribers = jsonObject.getInt("7");
    }

    public String getTacGroupName() {
        return groupName;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoSuccess() {
        return noSuccesses;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public double getSuccessRatio() {
        return successRatio;
    }

    public int getNoTotalErrSubscribers() {
        return noTotalErrSubscribers;
    }

    public double calculateExpectedSuccessRatio() {
        return SuccessRatioCalculator.calculateSuccessRatio(occurrences, noSuccesses);
    }

}
