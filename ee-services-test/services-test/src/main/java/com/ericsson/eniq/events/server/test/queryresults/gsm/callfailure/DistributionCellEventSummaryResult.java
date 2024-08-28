/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author ewanggu
 * @since 2012
 * 
 */
public class DistributionCellEventSummaryResult extends GSMAccessAreaCallFailureEventSummaryResult {

    private double ratio;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
     * (com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        this.setVendor(object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT));
        this.setAccessArea(object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT));
        this.setEventDesc(object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT));
        this.setNumFailures(object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT));
        this.setNumSubscribers(object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT));
        ratio = object.getDouble(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    @Override
    public double getRatio() {
        return ratio;
    }

}
