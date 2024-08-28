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
public class MssEventVolumeNoTypeResult implements QueryResult {

    private String eventTime; // 1

    private int msOriginatingCallCompletionCount; //2

    private int msOriginatingCallBlockCount; //3

    private int msOriginatingCallDropCount; //4

    private int msOriginatingEmergencyCallCompletionCount; //5

    private int msOriginatingEmergencyCallBlockCount; //6

    private int msOriginatingEmergencyCallDropCount; //7

    private int msTerminatingCallCompletionCount; //8

    private int msTerminatingCallBlockCount; //9

    private int msTerminatingCallDropCount; //10

    private int callForwardingCallCount; //11

    private int callForwardingCallBlockCount; //12

    private int callForwardingCallDropCount; //13

    private int roamingCallForwardingCallCount; //14

    private int roamingCallForwardingCallBlockCount; //15

    private int roamingCallForwardingCallDropCount; //16

    private int locationRequestsCount; //17

    private int unsuccessfulLocationRequests; //18

    private int msOriginatingSMSCount; //19

    private int msOriginatingSMSFailCount; //20

    private int msTerminatingSMSCount; //21

    private int msTerminatingSMSFailCount; //22

    private int totalNetworkEvents; //23

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString("1"); // 1

        msOriginatingCallCompletionCount = object.getInt("2"); //2

        msOriginatingCallBlockCount = object.getInt("3"); //3

        msOriginatingCallDropCount = object.getInt("4"); //4

        msOriginatingEmergencyCallCompletionCount = object.getInt("5"); //5

        msOriginatingEmergencyCallBlockCount = object.getInt("6"); //6

        msOriginatingEmergencyCallDropCount = object.getInt("7"); //7

        msTerminatingCallCompletionCount = object.getInt("8"); //8

        msTerminatingCallBlockCount = object.getInt("9"); //9

        msTerminatingCallDropCount = object.getInt("10"); //10

        callForwardingCallCount = object.getInt("11"); //11

        callForwardingCallBlockCount = object.getInt("12"); //12

        callForwardingCallDropCount = object.getInt("13"); //13

        roamingCallForwardingCallCount = object.getInt("14"); //14

        roamingCallForwardingCallBlockCount = object.getInt("15"); //15

        roamingCallForwardingCallDropCount = object.getInt("16"); //16

        locationRequestsCount = object.getInt("17"); //17

        unsuccessfulLocationRequests = object.getInt("18"); //18

        msOriginatingSMSCount = object.getInt("19"); //19

        msOriginatingSMSFailCount = object.getInt("20"); //20

        msTerminatingSMSCount = object.getInt("21"); //21

        msTerminatingSMSFailCount = object.getInt("22"); //22

        totalNetworkEvents = object.getInt("23"); //23
    }

    public String getEventTime() {
        return eventTime;
    }

    public int getMSOriginatingCallCompletionCount() {
        return msOriginatingCallCompletionCount;
    }

    public int getMSOriginatingCallBlockCount() {
        return msOriginatingCallBlockCount;
    }

    public int getMSOriginatingCallDropCount() {
        return msOriginatingCallDropCount;
    }

    public int getMSOriginatingEmergencyCallCompletionCount() {
        return msOriginatingEmergencyCallCompletionCount;
    }

    public int getMSOriginatingEmergencyCallBlockCount() {
        return msOriginatingEmergencyCallBlockCount;
    }

    public int getMSOriginatingEmergencyCallDropCount() {
        return msOriginatingEmergencyCallDropCount;
    }

    public int getMSTerminatingCallCompletionCount() {
        return msTerminatingCallCompletionCount;
    }

    public int getMSTerminatingCallBlockCount() {
        return msTerminatingCallBlockCount;
    }

    public int getMSTerminatingCallDropCount() {
        return msTerminatingCallDropCount;
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

    public int getUnsuccessfulLocationRequests() {
        return unsuccessfulLocationRequests;
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

}
