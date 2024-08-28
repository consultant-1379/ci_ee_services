package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ekumjay
 * @since 2012
 *
 */
public class AccessAreaGroupBreakdownServiceRawResult implements QueryResult {

    private String bscName;

    private int numberOfFailures;

    private int numberOfImpactedSubscribers;

    //private int numberofImpectedCells;

    private double failureRatio;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
     * (com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        bscName = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        numberOfImpactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        //numberofImpectedCells = object.getInt(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        failureRatio = object.getDouble(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    public String getController() {
        return bscName;
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public int getNumberOfImpactedSubscribers() {
        return numberOfImpactedSubscribers;
    }

    /*public int getNumberofImpectedCells() {
        return numberofImpectedCells;
    }*/

    public double getFailureRatio() {
        return failureRatio;
    }

}
