/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author EEMECOY
 *
 */
public class KPIByAPNGroupResult implements QueryResult {

    private int eventId;

    private String eventIdDesc;

    private int noErrors;

    private int noSuccesses;

    private int occurrences;

    private int noTotalErrSubscribers;

    private String apnGroup;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        apnGroup = object.getString("1");
        eventId = object.getInt("2");
        eventIdDesc = object.getString("3");
        noErrors = object.getInt("4");
        noSuccesses = object.getInt("5");
        occurrences = object.getInt("6");
        noTotalErrSubscribers = object.getInt("8");
    }

    public int getEvendID() {
        return eventId;
    }

    public String getEventDesc() {
        return eventIdDesc;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoSuccesses() {
        return noSuccesses;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public int getNoTotalErrSubscribers() {
        return noTotalErrSubscribers;
    }

    public String getAPNGroup() {
        return apnGroup;
    }

}
