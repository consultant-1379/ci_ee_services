/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 * @since 2011
 *
 */

public class LTEHFAEnodeBGroupEventHandoverStageResultRaw implements QueryResult {

    private String categoryId;

    private String handoverStage;

    private String group;

    private String noOfErrors;

    private String impactedSubscribers;

    public String getCategoryId() {
        return categoryId;
    }

    public String getHandoverStage() {
        return handoverStage;
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

    public void setImpactedSubscribers(final String impactedSubscribers) {
        this.impactedSubscribers = impactedSubscribers;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        group = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        handoverStage = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryId = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }
}
