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

public class LTEHFAEnodeBEventHandoverStageResultRaw implements QueryResult {

    private String categoryId;

    private String handoverStage;

    private String vendor;

    private String hier3Id;

    private String enodeB;

    private String noOfErrors;

    private String impactedSubscribers;

    public String getcategoryId() {
        return categoryId;
    }

    public String getHandoverType() {
        return handoverStage;
    }

    public String getVendor() {
        return vendor;
    }

    public String getHier3Id() {
        return hier3Id;
    }

    public String getEnodeB() {
        return enodeB;
    }

    public String getNoOfErrors() {
        return noOfErrors;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    @Override
    public void parseJSONObject(JSONObject jsonObject) throws JSONException {
        hier3Id = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        enodeB = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        handoverStage = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        categoryId = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }
}
