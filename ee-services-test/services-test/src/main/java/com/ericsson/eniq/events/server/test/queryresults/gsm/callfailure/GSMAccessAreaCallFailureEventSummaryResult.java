/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ehorpte
 * @since 2011
 * 
 */
public class GSMAccessAreaCallFailureEventSummaryResult implements QueryResult {

    private String vendor;

    private String bscName;

    private int numFailures;

    private String eventDesc;

    private int numSubscribers;

    private long hier321Id;

    private int categoryId;

    private String accessArea;

    private double ratio;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
     * (com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        vendor = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        bscName = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventDesc = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        numSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        ratio = object.getDouble(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        hier321Id = object.getLong(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    public String getVendor() {
        return vendor;
    }

    public String getController() {
        return bscName;
    }

    public int getNumFailures() {
        return numFailures;
    }

    public String getEventDescription() {
        return eventDesc;
    }

    public int getNumImpactedSubscribers() {
        return numSubscribers;
    }

    public long getHier321Id() {
        return hier321Id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getAccessArea() {
        return accessArea;
    }

    public void setVendor(final String vendor) {
        this.vendor = vendor;
    }

    public void setBscName(final String bscName) {
        this.bscName = bscName;
    }

    public void setNumFailures(final int numErrors) {
        this.numFailures = numErrors;
    }

    public void setEventDesc(final String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public void setNumSubscribers(final int numSubscribers) {
        this.numSubscribers = numSubscribers;
    }

    public void setHier321Id(final long hier321Id) {
        this.hier321Id = hier321Id;
    }

    public void setCategoryId(final int categoryId) {
        this.categoryId = categoryId;
    }

    public void setAccessArea(final String accessArea) {
        this.accessArea = accessArea;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(final double ratio) {
        this.ratio = ratio;
    }
}
