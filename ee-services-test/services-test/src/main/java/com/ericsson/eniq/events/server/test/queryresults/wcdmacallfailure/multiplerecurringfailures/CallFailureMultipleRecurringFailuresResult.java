/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdmacallfailure.multiplerecurringfailures;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eemecoy
 *
 */
public class CallFailureMultipleRecurringFailuresResult implements QueryResult {

    private String imsi;

    private int failues;

    private int rank;

    private int rncId;

    private int cellId;

    private String controller;

    private String accessArea;

    private String causeValue;

    private String extendedCauseValue;

    private String vendor;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        failues = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        cellId = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the imsi
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @return the failues
     */
    public int getFailues() {
        return failues;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the rncId
     */
    public int getRncId() {
        return rncId;
    }

    /**
     * @return the cellId
     */
    public int getCellId() {
        return cellId;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @return the accessArea
     */
    public String getAccessArea() {
        return accessArea;
    }

    /**
     * @return the extendedCauseValue
     */
    public String getExtendedCauseValue() {
        return extendedCauseValue;
    }

    /**
     * @return the causeValue
     */
    public String getCauseValue() {
        return causeValue;
    }
}
