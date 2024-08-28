/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class PieChartCauseCodeAnalysisResult implements QueryResult {

    private String causeCodeID;

    private String causeCode;

    private int numberOfErrors;

    private int numberOfOccurences;

    public String getCauseCodeID() {
        return causeCodeID;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeCodeID = jsonObject.getString("1");
        causeCode = jsonObject.getString("2");
        numberOfErrors = Integer.parseInt(jsonObject.getString("3"));
        numberOfOccurences = Integer.parseInt(jsonObject.getString("4"));
    }
}
