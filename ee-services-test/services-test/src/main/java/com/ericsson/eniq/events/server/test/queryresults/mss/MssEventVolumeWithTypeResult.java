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
public class MssEventVolumeWithTypeResult implements QueryResult {

    //"id" : "1"
    private String time;

    //"id": "2",
    private int mSOriginatingCallCompletionCount;

    //"id": "3",
    private int mSOriginatingCallBlockCount;

    //"id": "4",
    private int mSOriginatingCallDropCount;

    //"id": "5",
    private int mSOriginatingEmergencyCallCompletionCount;

    //"id": "6",
    private int mSOriginatingEmergencyCallBlockCount;

    //"id": "7",
    private int mSOriginatingEmergencyCallDropCount;

    //"id": "8",
    private int mSTerminatingCallCompletionCount;

    //"id": "9",
    private int mSTerminatingCallBlockCount;

    //"id": "10",
    private int mSTerminatingCallDropCount;

    //"id": "11",
    private int callForwardingCallCount;

    //"id": "12",
    private int callForwardingCallBlockCount;

    //"id": "13",
    private int callForwardingCallDropCount;

    //"id": "14",
    private int roamingCallForwardingCallCount;

    //"id": "15",
    private int roamingCallForwardingCallBlockCount;

    //"id": "16",
    private int roamingCallForwardingCallDropCount;

    //"id": "17",
    private int locationRequestsCount;

    //"id": "18",
    private int unsuccessfulLocationRequestCount;

    private int msOriginatingSMSCount; //19

    private int msOriginatingSMSFailCount; //20

    private int msTerminatingSMSCount; //21

    private int msTerminatingSMSFailCount; //22

    //"id": "23",
    private int totalNetworkEvents;

    //"id": "24",
    private int impactedSubscribers;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        time = object.getString("1");

        mSOriginatingCallCompletionCount = object.getInt("2");

        mSOriginatingCallBlockCount = object.getInt("3");

        mSOriginatingCallDropCount = object.getInt("4");

        mSOriginatingEmergencyCallCompletionCount = object.getInt("5");

        mSOriginatingEmergencyCallBlockCount = object.getInt("6");

        mSOriginatingEmergencyCallDropCount = object.getInt("7");

        mSTerminatingCallCompletionCount = object.getInt("8");

        mSTerminatingCallBlockCount = object.getInt("9");

        mSTerminatingCallDropCount = object.getInt("10");

        callForwardingCallCount = object.getInt("11");

        callForwardingCallBlockCount = object.getInt("12");

        callForwardingCallDropCount = object.getInt("13");

        roamingCallForwardingCallCount = object.getInt("14");

        roamingCallForwardingCallBlockCount = object.getInt("15");

        roamingCallForwardingCallDropCount = object.getInt("16");

        locationRequestsCount = object.getInt("17");

        unsuccessfulLocationRequestCount = object.getInt("18");

        msOriginatingSMSCount = object.getInt("19"); //19

        msOriginatingSMSFailCount = object.getInt("20"); //20

        msTerminatingSMSCount = object.getInt("21"); //21

        msTerminatingSMSFailCount = object.getInt("22"); //22

        impactedSubscribers = object.getInt("23");
        
        totalNetworkEvents = object.getInt("24");
    }

    public String getTime() {
        return time;
    }

    public int getMSOriginatingCallCompletionCount() {
        return mSOriginatingCallCompletionCount;
    }

    public int getMSOriginatingCallBlockCount() {
        return mSOriginatingCallBlockCount;
    }

    public int getMSOriginatingCallDropCount() {
        return mSOriginatingCallDropCount;
    }

    public int getMSOriginatingEmergencyCallCompletionCount() {
        return mSOriginatingEmergencyCallCompletionCount;
    }

    public int getMSOriginatingEmergencyCallBlockCount() {
        return mSOriginatingEmergencyCallBlockCount;
    }

    public int getMSOriginatingEmergencyCallDropCount() {
        return mSOriginatingEmergencyCallDropCount;
    }

    public int getMSTerminatingCallCompletionCount() {
        return mSTerminatingCallCompletionCount;
    }

    public int getMSTerminatingCallBlockCount() {
        return mSTerminatingCallBlockCount;
    }

    public int getMSTerminatingCallDropCount() {
        return mSTerminatingCallDropCount;
    }

    public int getCallForwardingCallCount() {
        return callForwardingCallCount;
    }

    public int getCallForwardingCallBlockCount() {
        return callForwardingCallBlockCount;
    }

    public int getCallForwardingCallDropCount() {
        return callForwardingCallDropCount;
    }

    public int getRoamingCallForwardingCallCount() {
        return roamingCallForwardingCallCount;
    }

    public int getRoamingCallForwardingCallBlockCount() {
        return roamingCallForwardingCallBlockCount;
    }

    public int getRoamingCallForwardingCallDropCount() {
        return roamingCallForwardingCallDropCount;
    }

    public int getLocationRequestsCount() {
        return locationRequestsCount;
    }

    public int getUnsuccessfulLocationRequestCount() {
        return unsuccessfulLocationRequestCount;
    }

    public int getMSOriginatingSMSCount() { //19
        return msOriginatingSMSCount;
    }

    public int getMSOriginatingSMSFailCount() { //20
        return msOriginatingSMSFailCount;
    }

    public int getMSTerminatingSMSCount() { //21
        return msTerminatingSMSCount;
    }

    public int getMSTerminatingSMSFailCount() { //22
        return msTerminatingSMSFailCount;
    }

    public int getTotalNetworkEvents() {
        return totalNetworkEvents;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

}
