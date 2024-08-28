/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author epesmit
 * @since 2011
 *
 */
public class CallFailureSubCauseCodeAnalysisResult implements QueryResult {

    private String subCauseCodeId;

    private String subCauseCodeDescp;

    private int numFailures;

    private int subscribers;

    private String causeCodeId;

    public String getSubCauseCodeId() {
        return subCauseCodeId;
    }

    public String getSubCauseCodeDescp() {
        return subCauseCodeDescp;
    }

    public int getNumFailures() {
        return numFailures;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public String getCauseCodeId() {
        return causeCodeId;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        subCauseCodeId = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        subCauseCodeDescp = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numFailures = jsonObject.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        subscribers = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        causeCodeId = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }
}
