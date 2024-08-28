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
public class MostPopularEventSummaryTerminalResult implements QueryResult {

    private int tac;

    private String manufacturer;

    private String marketingName;

    private int noErrors;

    private int noSuccesses;

    private int occurrences;

    private double successRatio;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        tac = jsonObject.getInt("2");
        manufacturer = jsonObject.getString("3");
        marketingName = jsonObject.getString("4");
        noErrors = jsonObject.getInt("5");
        noSuccesses = jsonObject.getInt("6");
        occurrences = jsonObject.getInt("7");
        successRatio = jsonObject.getDouble("8");
    }

    public int getTac() {
        return tac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoSuccesses() {
        return noSuccesses;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public double getSuccessRatio() {
        return successRatio;
    }

    public double calculateExpectedSuccessRatio() {
        return SuccessRatioCalculator.calculateSuccessRatio(occurrences, noSuccesses);
    }

}
