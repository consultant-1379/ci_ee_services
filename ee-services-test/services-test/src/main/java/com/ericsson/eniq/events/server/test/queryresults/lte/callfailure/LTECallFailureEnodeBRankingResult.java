/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECallFailureEnodeBRankingResult implements QueryResult {

    private String hier3ID;

    private int rank;

    private String vendor;

    private String hierarchy3;

    private int failures;
    
    private String radioAccessType ;


    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        hier3ID = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        rank = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        hierarchy3 = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        radioAccessType = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public String getHier3ID() {
        return hier3ID;
    }

    public int getRank() {
        return rank;
    }

    public String getVendor() {
        return vendor;
    }

    public String getHierarchy3() {
        return hierarchy3;
    }

    public int getFailures() {
        return failures;
    }
    
    public String getRadioAccessType() {
        return radioAccessType;
    }
}
