/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eemecoy
 *
 */
public class CallFailureSubscriberDetailsQueryResult implements QueryResult {

    private String msisdn;

    private String homeCountry;

    private String mobileNetworkOperator;

    private String roamingStatus;

    private String lastCell;

    private String lastRNC;

    private String firstEventDate;

    private String lastEventDate;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        msisdn = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        homeCountry = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        mobileNetworkOperator = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        roamingStatus = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        lastCell = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        lastRNC = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        firstEventDate = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        lastEventDate = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public String getMobileNetworkOperator() {
        return mobileNetworkOperator;
    }

    public String getRoamingStatus() {
        return roamingStatus;
    }

    public String getLastEventDate() {
        return lastEventDate;
    }

    public String getLastRNC() {
        return lastRNC;
    }

    public String getLastCell() {
        return lastCell;
    }

    public String getFirstEventDate() {
        return firstEventDate;
    }

    public String getMappedMSISDN() {
        return msisdn;
    }

}
