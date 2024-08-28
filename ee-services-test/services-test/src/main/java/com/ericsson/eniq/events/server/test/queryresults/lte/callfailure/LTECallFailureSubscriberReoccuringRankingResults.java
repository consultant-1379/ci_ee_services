/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echimma
 * @since 2011
 *
 */
public class LTECallFailureSubscriberReoccuringRankingResults implements QueryResult {

    private int rank;

    private String imsi;

    private String eventID;

    private String hier321ID;

    private String hierarchy3;

    private String vendor;

    private String hierarchy1;

    private String eventIdDesc;

    private int failures;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventID = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        hier321ID = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        hierarchy3 = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        hierarchy1 = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        eventIdDesc = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    public int getRank() {
        return rank;
    }

    public String getImsi() {
        return imsi;
    }

    public String getHier321ID() {
        return hier321ID;
    }

    public String getHierarchy1() {
        return hierarchy1;
    }

    public String getHierarchy3() {
        return hierarchy3;
    }

    public String getVendor() {
        return vendor;
    }

    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public int getFailures() {
        return failures;
    }

    public String getEventID() {
        return eventID;
    }
}
