/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 * @since 2011
 *
 */
public class LTEHandoverFailureEventHandoverStageResultRaw implements QueryResult {

    private String categoryId;

    private String handoverStage;

    private String imsi;

    private String noOfErrors;
    
    public String getCategoryId() {
        return categoryId;
    }

    public String getHandoverStage() {
        return handoverStage;
    }

    public String getImsi() {
        return imsi;
    }

    public String getNoOfErrors() {
        return noOfErrors;
    }


    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        imsi = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        handoverStage = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryId = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        
    }

}
