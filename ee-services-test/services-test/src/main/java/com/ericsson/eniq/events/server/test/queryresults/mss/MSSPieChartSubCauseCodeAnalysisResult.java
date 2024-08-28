/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.mss;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

@Ignore
public class MSSPieChartSubCauseCodeAnalysisResult implements QueryResult {

    protected int causeCodeID;

    protected int subCauseCodeID;

    protected String whatnext;

    protected String subCauseCode;

    protected int numberOfErrors;

    protected int numberOfOccurences;

    /**
     * @return the causeCodeID
     */
    public int getCauseCodeID() {
        return causeCodeID;
    }

    /**
     * @return the subCauseCodeID
     */
    public int getSubCauseCodeID() {
        return subCauseCodeID;
    }

    /**
     * @return the whatnext
     */
    public String getWhatnext() {
        return whatnext;
    }

    /**
     * @return the subCauseCode
     */
    public String getSubCauseCode() {
        return subCauseCode;
    }

    /**
     * @return the numberOfErrors
     */
    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    /**
     * @return the numberOfOccurences
     */
    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }

    /*    {
            "1" : "Cause Code ID",
            "2" : "Sub Cause Code ID",
            "3" : "Sub Cause Code DESC (Cause Prototype DESC)",
            "4" : "What Next Text",
            "5" : "Number of failures",
            "6" : "Impacted Subscribers"
        }*/
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeCodeID = Integer.parseInt(jsonObject.getString("1"));
        subCauseCodeID = Integer.parseInt(jsonObject.getString("2"));
        subCauseCode = jsonObject.getString("3");
        whatnext = jsonObject.getString("4");
        numberOfErrors = Integer.parseInt(jsonObject.getString("5"));
        numberOfOccurences = Integer.parseInt(jsonObject.getString("6"));
    }
}
