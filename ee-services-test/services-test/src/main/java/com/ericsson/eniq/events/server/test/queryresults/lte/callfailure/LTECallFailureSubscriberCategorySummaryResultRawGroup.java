/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2011
 *
 */

public class LTECallFailureSubscriberCategorySummaryResultRawGroup implements QueryResult {

    private String categorytId;

    private String categoryType;

    private String group;

    private String noOfErrors;

    private String impactedSubscribers;

    public String getCategorytId() {
        return categorytId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public String getGroup() {
        return group;
    }

    public String getNoOfErrors() {
        return noOfErrors;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public void setImpactedSubscribers(String impactedSubscribers) {
        this.impactedSubscribers = impactedSubscribers;
    }

    @Override
    public void parseJSONObject(JSONObject jsonObject) throws JSONException {
        group = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categorytId = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryType = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }
}
