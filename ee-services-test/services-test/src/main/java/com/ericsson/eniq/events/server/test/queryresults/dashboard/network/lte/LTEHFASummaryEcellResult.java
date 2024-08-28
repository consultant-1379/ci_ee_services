/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.dashboard.network.lte;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejamves
 * @since 2012
 *
 */
public class LTEHFASummaryEcellResult implements QueryResult {

    //The rank (1|2|3|4|5) of the eCell's.
    private String eCellRank;

    //The enodeB name ('LTE001,LTE002' etc...) in network of  enodeB's.
    private String eCellname;

    //The number of failures for the enodeB.
    private String failures;

    private String hier321_id;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        this.eCellRank = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        this.hier321_id = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        this.eCellname = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        this.failures = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);

    }

    public String getRank() {
        return this.eCellRank;
    }

    public String getName() {
        return this.eCellname;
    }

    public String getFailures() {
        return this.failures;
    }

    public String getHashId() {
        return this.hier321_id;
    }
}
