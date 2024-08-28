package com.ericsson.eniq.events.server.test.queryresults.mss;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * 
 * @author echimma
 * @since 2011
 *
 */
public class MssKPIRatioTypeMSCDrillTypeMSCResult implements QueryResult {

    private String eventId;

    private String evntsrcId;

    private String hier3Id;

    private String vendor;

    private String controller;

    private String eventType;

    private int noOfErrors;

    private int noOfSuccesses;

    private int total;

    private String successRatio;

    private int impactedSubScribers;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventId = object.getString("1");
        evntsrcId = object.getString("2");
        hier3Id = object.getString("3");
        vendor = object.getString("4");
        controller = object.getString("5");
        eventType = object.getString("6");
        noOfErrors = object.getInt("7");
        noOfSuccesses = object.getInt("8");
        total = object.getInt("9");
        successRatio = object.getString("10");
        impactedSubScribers = object.getInt("11");
    }

    public String getEventId() {
        return eventId;
    }

    public String getEvntsrcId() {
        return evntsrcId;
    }

    public String getHier3Id() {
        return hier3Id;
    }

    public String getVendor() {
        return vendor;
    }

    public String getController() {
        return controller;
    }

    public String getEventType() {
        return eventType;
    }

    public int getNoOfErrors() {
        return noOfErrors;
    }

    public int getNoOfSuccesses() {
        return noOfSuccesses;
    }

    public int getTotal() {
        return total;
    }

    public String getSuccessRatio() {
        return successRatio;
    }

    public int getImpactedSubScribers() {
        return impactedSubScribers;
    }
}
