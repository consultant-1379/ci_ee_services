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
public class MssKPIRatioDrillTypeBSCResult implements QueryResult {

    private String eventId;

    private String evntsrcId;

    private String hier3Id;

    private String hier321Id;

    private String vendor;

    private String accessArea;

    private String eventType;

    private int noOfErrors;

    private int noOfSuccesses;

    private int total;

    private String successRatio;

    private int impactedSubScribers;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        if (object.length() == 12) {
            parseJSONObjectForMSC(object);
        } else {
            parseJSONObjectForBSC(object);
        }
    }

    private void parseJSONObjectForMSC(final JSONObject object) throws JSONException {
        eventId = object.getString("1");
        evntsrcId = object.getString("2");
        hier3Id = object.getString("3");
        hier321Id = object.getString("4");
        vendor = object.getString("5");
        accessArea = object.getString("6");
        eventType = object.getString("7");
        noOfErrors = object.getInt("8");
        noOfSuccesses = object.getInt("9");
        total = object.getInt("10");
        successRatio = object.getString("11");
        impactedSubScribers = object.getInt("12");
    }

    private void parseJSONObjectForBSC(final JSONObject object) throws JSONException {
        eventId = object.getString("1");
        hier3Id = object.getString("2");
        hier321Id = object.getString("3");
        vendor = object.getString("4");
        accessArea = object.getString("5");
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

    public String getHier321Id() {
        return hier321Id;
    }

    public String getVendor() {
        return vendor;
    }

    public String getAccessArea() {
        return accessArea;
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
