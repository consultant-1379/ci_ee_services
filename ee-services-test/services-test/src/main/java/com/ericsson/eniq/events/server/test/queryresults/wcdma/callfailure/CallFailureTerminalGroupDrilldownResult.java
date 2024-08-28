/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author EEMECOY
 *
 */
public class CallFailureTerminalGroupDrilldownResult implements QueryResult {

    private String manufacturer;

    private String marketingName;

    private int tac;

    private int numFailures;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        tac = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        manufacturer = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        marketingName = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);

    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public int getTac() {
        return tac;
    }

    public int getNumFailures() {
        return numFailures;
    }

}
