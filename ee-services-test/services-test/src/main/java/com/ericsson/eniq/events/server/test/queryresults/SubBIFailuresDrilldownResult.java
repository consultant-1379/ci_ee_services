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
 * @author EEMECOY
 *
 */
public class SubBIFailuresDrilldownResult implements QueryResult {

    private long imsi;

    private String ptmsi;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        imsi = object.getLong(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        ptmsi = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    public long getIMSI() {
        return imsi;
    }

    public String getPTMSI() {
        return ptmsi;
    }

}
