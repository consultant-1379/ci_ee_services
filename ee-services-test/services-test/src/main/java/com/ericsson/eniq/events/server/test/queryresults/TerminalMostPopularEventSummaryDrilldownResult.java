/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import java.text.DecimalFormat;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
public class TerminalMostPopularEventSummaryDrilldownResult implements QueryResult {

    private int tac;

    private String manufacturer;

    private String marketingName;

    private int noOfErrors;

    private int noOfSuccesses;

    private int occurrences;

    private double successRatio;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        tac = jsonObject.getInt("1");
        manufacturer = jsonObject.getString("2");
        marketingName = jsonObject.getString("3");
        noOfErrors = jsonObject.getInt("4");
        noOfSuccesses = jsonObject.getInt("5");
        occurrences = jsonObject.getInt("6");
        successRatio = jsonObject.getDouble("7");

    }

    public int getTac() {
        return tac;
    }

    public double calculateExpectedSuccessRatio() {
        final double result = (double) noOfSuccesses / occurrences * 100;
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(result));
    }

    public int getOccurrences() {
        return occurrences;
    }

    public int getNoSuccess() {
        return noOfSuccesses;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public int getNoErrors() {
        return noOfErrors;
    }

    public double getSuccessRatio() {
        return successRatio;

    }

}
