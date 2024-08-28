/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.mss.MSSPieChartSubCauseCodeAnalysisResult;

@Ignore
public class PieChartSubCauseCodeAnalysisResult extends MSSPieChartSubCauseCodeAnalysisResult implements QueryResult {

    private int causeProtoType;

    /**
     * @return the causeProtoType
     */
    public int getCauseProtoType() {
        return causeProtoType;
    }

    /*    {
            "1" : "Cause Prototype",
            "2" : "Cause Code ID",
            "3" : "Sub Cause Code ID",
            "4" : "Sub Cause Code DESC (Cause Prototype DESC)",
            "5" : "What Next Text",
            "6" : "Number of failures",
            "7" : "Impacted Subscribers"
        }*/
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeProtoType = Integer.parseInt(jsonObject.getString("1"));
        causeCodeID = Integer.parseInt(jsonObject.getString("2"));
        subCauseCodeID = Integer.parseInt(jsonObject.getString("3"));
        subCauseCode = jsonObject.getString("4");
        whatnext = jsonObject.getString("5");
        numberOfErrors = Integer.parseInt(jsonObject.getString("6"));
        numberOfOccurences = Integer.parseInt(jsonObject.getString("7"));
    }
}
