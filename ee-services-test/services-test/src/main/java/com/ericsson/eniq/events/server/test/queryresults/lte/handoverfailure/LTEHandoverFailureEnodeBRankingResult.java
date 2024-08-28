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
public class LTEHandoverFailureEnodeBRankingResult implements QueryResult {

    private String hier3ID;

    private String categoryID;

    private int rank;

    private String vendor;

    private String eNodeB;

    private int failures;
    
    private String ratDesc;

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        hier3ID = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryID = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        rank = jsonObject.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        eNodeB = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = jsonObject.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        ratDesc = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getHier3ID() {
        return hier3ID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public int getRank() {
        return rank;
    }

    public String getVendor() {
        return vendor;
    }

    public String getEnodeB() {
        return eNodeB;
    }

    public int getFailures() {
        return failures;
    }
    
    public String getRatDesc() {
        return ratDesc;
    }
}
