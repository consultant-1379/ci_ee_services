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
public class MssKPIRatioDrillTypeCELLResult implements QueryResult {
    //1
    private String eventId;

    //2
    private String hier321Id;

    //3
    private String hier3Id;

    //4
    private String eventsrcId;

    //5
    private int internalCauseCodeId;

    //6
    private String internalCauseCode;

    //7
    private int faultCodeId;

    //8
    private String faultCode;

    //9
    private String eventType;

    //10
    private int occurrences;

    //11
    private int impactedSubscribers;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        if (object.length() == 11) {
            parseJSONObjectForMSC(object);
        } else if (object.length() == 10) {
            parseJSONObjectForBSC(object);
        } else if (object.length() == 9) {
            parseJSONObjectForCELL(object);
        }
    }

    /**
     * @param object
     */
    private void parseJSONObjectForCELL(final JSONObject object) throws JSONException {
        //1
        eventId = object.getString("1");

        //2
        hier321Id = object.getString("2");

        //3
        internalCauseCodeId = object.getInt("3");

        //4
        internalCauseCode = object.getString("4");

        //5
        faultCodeId = object.getInt("5");

        //6
        faultCode = object.getString("6");

        //7
        eventType = object.getString("7");

        //8
        occurrences = object.getInt("8");

        //9
        impactedSubscribers = object.getInt("9");
    }

    private void parseJSONObjectForMSC(final JSONObject object) throws JSONException {
        //1
        eventId = object.getString("1");

        //4
        hier321Id = object.getString("2");

        //3
        hier3Id = object.getString("3");

        //4
        eventsrcId = object.getString("4");

        //5
        internalCauseCodeId = object.getInt("5");

        //6
        internalCauseCode = object.getString("6");

        //7
        faultCodeId = object.getInt("7");

        //8
        faultCode = object.getString("8");

        //9
        eventType = object.getString("9");

        //10
        occurrences = object.getInt("10");

        //11
        impactedSubscribers = object.getInt("11");
    }

    public void parseJSONObjectForBSC(final JSONObject object) throws JSONException {
        //1
        eventId = object.getString("1");

        //2
        hier321Id = object.getString("2");

        //3
        hier3Id = object.getString("3");

        //4
        internalCauseCodeId = object.getInt("4");

        //5
        internalCauseCode = object.getString("5");

        //6
        faultCodeId = object.getInt("6");

        //7
        faultCode = object.getString("7");

        //8
        eventType = object.getString("8");

        //9
        occurrences = object.getInt("9");

        //10
        impactedSubscribers = object.getInt("10");
    }

    //1
    public String getEventId() {
        return eventId;
    }

    //2
    public String getHier321Id() {
        return hier321Id;
    }

    //3
    public String getHier3Id() {
        return hier3Id;
    }

    //4
    public String getEventsrcId() {
        return eventsrcId;
    }

    //5
    public int getInternalCauseCodeId() {
        return internalCauseCodeId;
    }

    //6
    public String getInternalCauseCode() {
        return internalCauseCode;
    }

    //7
    public int getFaultCodeId() {
        return faultCodeId;
    }

    //8
    public String getFaultCode() {
        return faultCode;
    }

    //9
    public String getEventType() {
        return eventType;
    }

    //10
    public int getOccurrences() {
        return occurrences;
    }

    //11
    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
}
