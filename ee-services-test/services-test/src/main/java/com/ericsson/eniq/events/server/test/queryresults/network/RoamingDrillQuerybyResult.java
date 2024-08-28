/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.network;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * Desrible RoamingDrillQuerybyResult
 *
 * @author ezhelao
 * @since 01/2012
 */
public class RoamingDrillQuerybyResult implements QueryResult {


    private   String eventIdDesc = null;
    private   String  eventId = null;
    private   String  numberOfFailures=null;
    private   String  impactedSubscriber=null;
    private   String countryOperatorName=null;


    public  String getEventId() {
        return eventId;
    }

    public  String getNumberOfFailures() {
        return numberOfFailures;
    }

    public  String getImpactedSubscriber() {
        return impactedSubscriber;
    }


    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public String getCountryOperatorName() {
        return countryOperatorName;
    }

    @Override
    public void parseJSONObject(JSONObject object) throws JSONException {
        eventIdDesc = object.getString("1");
        eventId =object.getString("2");
        numberOfFailures =object.getString("3");
        impactedSubscriber =object.getString("4");
        countryOperatorName = object.getString("5");
    }




}
