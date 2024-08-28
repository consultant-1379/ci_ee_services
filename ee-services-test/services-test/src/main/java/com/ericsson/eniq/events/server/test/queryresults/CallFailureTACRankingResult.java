/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eriwals
 *
 */
public class CallFailureTACRankingResult implements QueryResult {

    private int rank;

    private String manufacturer;

    private String model;

    private int tac;

    private int failures;

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getTac() {
        return tac;
    }

    public int getFailures() {
        return failures;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {

        manufacturer = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        model = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        tac = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failures = jsonObject.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);

    }
}