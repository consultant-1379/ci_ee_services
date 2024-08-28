/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2012
 *
 */

public class LTECFATerminalGroupCategorySummaryResultRaw implements QueryResult {

    private String categoryId;

    private String categoryType;

    private String group;

    private int noOfErrors;

    private int impactedSubscribers;

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public String getGroup() {
        return group;
    }

    public int getNoOfErrors() {
        return noOfErrors;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public void setImpactedSubscribers(final int impactedSubscribers) {
        this.impactedSubscribers = impactedSubscribers;
    }

    @Override
    public void parseJSONObject(JSONObject jsonObject) throws JSONException {
        group = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryId = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryType = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }
}
