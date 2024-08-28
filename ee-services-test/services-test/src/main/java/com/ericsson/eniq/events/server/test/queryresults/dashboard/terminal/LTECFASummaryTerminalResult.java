/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.dashboard.terminal;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2012
 *
 */
public class LTECFASummaryTerminalResult implements QueryResult {

    //The rank (1|2|3|4|5) of the terminal's.
    private String terminalRank;

    private String terminalManuf;

    private String terminalModel;

    //The number of failures for the enodeB.
    private String failures;

    //The terminal name ('12234,23456' etc...) in network of  terminal's.
    private String tac;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        this.terminalRank = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        this.terminalManuf = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        this.terminalModel = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        this.tac = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        this.failures = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);

    }
    
    public String getTerminalRank() {
        return terminalRank;
    }

    public String getTerminalManuf() {
        return terminalManuf;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public String getTac() {
        return tac;
    }
    public String getFailures() {
        return this.failures;
    }
}
