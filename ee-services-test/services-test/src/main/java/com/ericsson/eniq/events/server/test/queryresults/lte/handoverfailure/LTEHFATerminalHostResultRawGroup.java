/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import com.ericsson.eniq.events.server.json.JSONException;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 * @since 2011
 *
 */
public class LTEHFATerminalHostResultRawGroup implements QueryResult {

    private String categoryId;

    private String handoverStage;

    private String group;

    private int failures;
    
    private int impactedSubscribers;
    
    public String getCategoryId() {
        return categoryId;
    }

    public String getHandoverStage() {
        return handoverStage;
    }

    public String getGroup() {
        return group;
    }

    public int getFailures() {
        return failures;
    }
    
    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
    
    public void setImpactedSubscribers(final int impactedSubscribers) {
        this.impactedSubscribers = impactedSubscribers;
    }
    
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        group = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryId = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        handoverStage = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        
        
    }

}
