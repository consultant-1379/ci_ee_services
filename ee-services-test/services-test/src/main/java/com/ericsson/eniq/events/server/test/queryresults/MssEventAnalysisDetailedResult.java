/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author echchik
 *
 */
public class MssEventAnalysisDetailedResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private String tac;

    private String manufacturer;

    private String marketingTime;

    private String eventIdDesc;

    private String eventResultDesc;

    private String internalCauseCodeDesc;

    private String externalCauseCodeDesc;

    private String externalCauseCode;

    private String externalProtocolID;

    private String externalProtocolName;

    private String faultCodeDesc;

    private String internalCauseCodeID;

    private String faultCodeID;

    private String advice;

    private String incomingRoute;

    private String outgoingRoute;

    private String eventSourceName;

    private String ratDesc;

    private String controller;

    private String cell;

    private String vendor;

    private String internalLocationCode;

    private String bearerServiceCde;

    private String teleServiceCode;

    private String callIdNum;

    private String typeOfCallingSubscriber;

    private String callingPartyNumber;

    private String calledPartyNumber;

    private String callingSubImsi;

    private String calledSubImsi;

    private String callingSubImei;

    private String calledSubImei;

    private String msRoamingNumber;

    private String disconnectParty;

    private String mcc;

    private String mnc;

    private String rac;

    private String lac;

    private String callDuration;

    private String seizureTime;

    private String originalCalledNumber;

    private String redirectNumber;

    private String redirectCounter;

    private String redirectImsi;

    private String redirectSpn;

    private String callPosition;

    private String eosInfo;

    private String recordSequenceNumber;

    private String networkCallReference;

    private String lcsClientTypeDesc;

    private String unSucPositionReasonDesc;

    private String typeLocationRequestDesc;

    private String lcsClientTypeID;

    private String unSucPositionReasonID;

    private String typeLocationRequestID;

    private String targetMsisdn;

    private String targetImsi;

    private String targetImei;

    private String lcsClientId;

    private String positionDelivery;

    private String msisdn;

    private String smsResultDesc;

    private String msgTypeIndicatorDesc;

    private String smsResultID;

    private String msgTypeIndicatorID;

    private String callingSubImeiSv;

    private String calledSubImeiSv;

    private String originatingNumber;

    private String destinationNumber;

    private String serviceCenter;

    private String originatingTime;

    private String deliveryTime;

    private enum EventType {
        msOriginatingOrTreminating, callForwarding, roamingCallForwarding, locationServices, mSOriginatingAndTerminatingSMSinMSC
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getImsi() {
        return imsi;
    }

    public String getTac() {
        return tac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingTime() {
        return marketingTime;
    }

    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public String getEventResultDesc() {
        return eventResultDesc;
    }

    public String getInternalCauseCodeDesc() {
        return internalCauseCodeDesc;
    }

    public String getExternalProtocolID() {
        return externalProtocolID;
    }

    public String getExternalProtocolName() {
        return externalProtocolName;
    }

    public String getExternalCauseCode() {
        return externalCauseCode;
    }

    public String getExternalCauseCodeDesc() {
        return externalCauseCodeDesc;
    }

    public String getFaultCodeDesc() {
        return faultCodeDesc;
    }

    public String getAdvice() {
        return advice;
    }

    public String getIncomingRoute() {
        return incomingRoute;
    }

    public String getOutgoingRoute() {
        return outgoingRoute;
    }

    public String getEventSourceName() {
        return eventSourceName;
    }

    public String getRatDesc() {
        return ratDesc;
    }

    public String getController() {
        return controller;
    }

    public String getCell() {
        return cell;
    }

    public String getVendor() {
        return vendor;
    }

    public String getInternalLocationCode() {
        return internalLocationCode;
    }

    public String getBearerServiceCde() {
        return bearerServiceCde;
    }

    public String getTeleServiceCode() {
        return teleServiceCode;
    }

    public String getCallIdNum() {
        return callIdNum;
    }

    public String getTypeOfCallingSubscriber() {
        return typeOfCallingSubscriber;
    }

    public String getCallingPartyNumber() {
        return callingPartyNumber;
    }

    public String getCalledPartyNumber() {
        return calledPartyNumber;
    }

    public String getCallingSubImsi() {
        return callingSubImsi;
    }

    public String getCalledSubImsi() {
        return calledSubImsi;
    }

    public String getCallingSubImei() {
        return callingSubImei;
    }

    public String getCalledSubImei() {
        return calledSubImei;
    }

    public String getMsRoamingNumber() {
        return msRoamingNumber;
    }

    public String getDisconnectParty() {
        return disconnectParty;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getRac() {
        return rac;
    }

    public String getLac() {
        return lac;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public String getSeizureTime() {
        return seizureTime;
    }

    public String getOriginalCalledNumber() {
        return originalCalledNumber;
    }

    public String getRedirectNumber() {
        return redirectNumber;
    }

    public String getRedirectCounter() {
        return redirectCounter;
    }

    public String getRedirectImsi() {
        return redirectImsi;
    }

    public String getRedirectSpn() {
        return redirectSpn;
    }

    public String getCallPosition() {
        return callPosition;
    }

    public String getEosInfo() {
        return eosInfo;
    }

    public String getRecordSequenceNumber() {
        return recordSequenceNumber;
    }

    public String getNetworkCallReference() {
        return networkCallReference;
    }

    public String getLcsClientTypeDesc() {
        return lcsClientTypeDesc;
    }

    public String getUnSucPositionReasonDesc() {
        return unSucPositionReasonDesc;
    }

    public String getTypeLocationRequestDesc() {
        return typeLocationRequestDesc;
    }

    public String getTargetMsisdn() {
        return targetMsisdn;
    }

    public String getTargetImsi() {
        return targetImsi;
    }

    public String getTargetImei() {
        return targetImei;
    }

    public String getLcsClientId() {
        return lcsClientId;
    }

    public String getPositionDelivery() {
        return positionDelivery;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(final String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSmsResultDesc() {
        return smsResultDesc;
    }

    public String getMsgTypeIndicatorDesc() {
        return msgTypeIndicatorDesc;
    }

    public String getCallingSubImeiSv() {
        return callingSubImeiSv;
    }

    public String getCalledSubImeiSv() {
        return calledSubImeiSv;
    }

    public String getOriginatingNumber() {
        return originatingNumber;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public String getOriginatingTime() {
        return originatingTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getInternalCauseCodeID() {
        return internalCauseCodeID;
    }

    public String getFaultCodeID() {
        return faultCodeID;
    }

    public String getLcsClientTypeID() {
        return lcsClientTypeID;
    }

    public String getUnSucPositionReasonID() {
        return unSucPositionReasonID;
    }

    public String getTypeLocationRequestID() {
        return typeLocationRequestID;
    }

    public String getSmsResultID() {
        return smsResultID;
    }

    public String getMsgTypeIndicatorID() {
        return msgTypeIndicatorID;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        final EventType eventType = getEventType(jsonObject);
        if (eventType == EventType.msOriginatingOrTreminating) {
            parseJSONObjectMsOriginatingNTerminating(jsonObject);
        } else if (eventType == EventType.callForwarding) {
            parseJSONObjectCallForwarding(jsonObject);
        } else if (eventType == EventType.roamingCallForwarding) {
            parseJSONObjectRoamingCall(jsonObject);
        } else if (eventType == EventType.locationServices) {
            parseJSONObjectLocationServices(jsonObject);
        } else if (eventType == EventType.mSOriginatingAndTerminatingSMSinMSC) {
            parseJSONObjectMsOriginatingNTerminatingSMS(jsonObject);
        }
    }

    private EventType getEventType(final JSONObject jsonObject) throws JSONException {
        try {
            jsonObject.getLong("2");
            jsonObject.getLong("3");
        } catch (final NumberFormatException e) {
            return isCallForwardingOrRomaingCall(jsonObject);
        }
        final String eventIdDescription = jsonObject.getString("10");
        if ("locationServices".equals(eventIdDescription)) {
            return EventType.locationServices;
        } else if ("mSOriginatingSMSinMSC".equals(eventIdDescription)
                || "mSTerminatingSMSinMSC".equals(eventIdDescription)) {
            return EventType.mSOriginatingAndTerminatingSMSinMSC;
        }
        return EventType.msOriginatingOrTreminating;
    }

    private EventType isCallForwardingOrRomaingCall(final JSONObject jsonObject) throws JSONException {
        final String tac1 = jsonObject.getString("8");
        if (tac1 == null || tac1.length() == 0) {
            return EventType.callForwarding;
        }
        return EventType.roamingCallForwarding;
    }

    private void parseJSONObjectMsOriginatingNTerminating(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString("4");
        msisdn = jsonObject.getString("13");
        imsi = jsonObject.getString("14");
        tac = jsonObject.getString("12");
        manufacturer = jsonObject.getString("15");
        marketingTime = jsonObject.getString("16");
        eventIdDesc = jsonObject.getString("17");
        eventResultDesc = jsonObject.getString("18");
        internalCauseCodeID = jsonObject.getString("21");
        internalCauseCodeDesc = jsonObject.getString("22");
        externalProtocolID = jsonObject.getString("7");
        externalProtocolName = jsonObject.getString("6");
        externalCauseCode = jsonObject.getString("19");
        externalCauseCodeDesc = jsonObject.getString("20");
        faultCodeID = jsonObject.getString("23");
        faultCodeDesc = jsonObject.getString("24");
        advice = jsonObject.getString("25");
        incomingRoute = jsonObject.getString("8");
        outgoingRoute = jsonObject.getString("9");
        internalLocationCode = jsonObject.getString("26");
        bearerServiceCde = jsonObject.getString("27");
        teleServiceCode = jsonObject.getString("28");
        ratDesc = jsonObject.getString("29");
        eventSourceName = jsonObject.getString("5");
        controller = jsonObject.getString("10");
        cell = jsonObject.getString("11");
        vendor = jsonObject.getString("30");
        callIdNum = jsonObject.getString("31");
        typeOfCallingSubscriber = jsonObject.getString("32");
        callingPartyNumber = jsonObject.getString("33");
        calledPartyNumber = jsonObject.getString("34");
        callingSubImsi = jsonObject.getString("35");
        calledSubImsi = jsonObject.getString("36");
        callingSubImei = jsonObject.getString("37");
        calledSubImei = jsonObject.getString("38");
        msRoamingNumber = jsonObject.getString("39");
        disconnectParty = jsonObject.getString("40");
        callDuration = jsonObject.getString("41");
        seizureTime = jsonObject.getString("42");
        originalCalledNumber = jsonObject.getString("43");
        redirectNumber = jsonObject.getString("44");
        redirectCounter = jsonObject.getString("45");
        redirectImsi = jsonObject.getString("46");
        redirectSpn = jsonObject.getString("47");
        callPosition = jsonObject.getString("48");
        eosInfo = jsonObject.getString("49");
        recordSequenceNumber = jsonObject.getString("50");
        networkCallReference = jsonObject.getString("51");
        mcc = jsonObject.getString("52");
        mnc = jsonObject.getString("53");
        rac = jsonObject.getString("54");
        lac = jsonObject.getString("55");
    }

    private void parseJSONObjectCallForwarding(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString("2");
        msisdn = jsonObject.getString("9");
        imsi = jsonObject.getString("10");
        tac = jsonObject.getString("8");
        eventIdDesc = jsonObject.getString("11");
        eventResultDesc = jsonObject.getString("12");
        internalCauseCodeID = jsonObject.getString("15");
        internalCauseCodeDesc = jsonObject.getString("16");
        externalProtocolID = jsonObject.getString("5");
        externalProtocolName = jsonObject.getString("4");
        externalCauseCode = jsonObject.getString("13");
        externalCauseCodeDesc = jsonObject.getString("14");
        faultCodeID = jsonObject.getString("17");
        faultCodeDesc = jsonObject.getString("18");
        advice = jsonObject.getString("19");
        incomingRoute = jsonObject.getString("6");
        outgoingRoute = jsonObject.getString("7");
        internalLocationCode = jsonObject.getString("20");
        bearerServiceCde = jsonObject.getString("21");
        teleServiceCode = jsonObject.getString("22");
        eventSourceName = jsonObject.getString("3");
        callIdNum = jsonObject.getString("23");
        typeOfCallingSubscriber = jsonObject.getString("24");
        callingPartyNumber = jsonObject.getString("25");
        calledPartyNumber = jsonObject.getString("26");
        callingSubImsi = jsonObject.getString("27");
        calledSubImsi = jsonObject.getString("28");
        callingSubImei = jsonObject.getString("29");
        calledSubImei = jsonObject.getString("30");
        msRoamingNumber = jsonObject.getString("31");
        disconnectParty = jsonObject.getString("32");
        callDuration = jsonObject.getString("33");
        seizureTime = jsonObject.getString("34");
        originalCalledNumber = jsonObject.getString("35");
        redirectNumber = jsonObject.getString("36");
        redirectCounter = jsonObject.getString("37");
        redirectImsi = jsonObject.getString("38");
        redirectSpn = jsonObject.getString("39");
        callPosition = jsonObject.getString("40");
        eosInfo = jsonObject.getString("41");
        recordSequenceNumber = jsonObject.getString("42");
        networkCallReference = jsonObject.getString("43");
        mcc = jsonObject.getString("44");
        mnc = jsonObject.getString("45");
        rac = jsonObject.getString("46");
        lac = jsonObject.getString("47");
    }

    private void parseJSONObjectRoamingCall(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString("2");
        msisdn = jsonObject.getString("9");
        imsi = jsonObject.getString("10");
        tac = jsonObject.getString("8");
        manufacturer = jsonObject.getString("11");
        marketingTime = jsonObject.getString("12");
        eventIdDesc = jsonObject.getString("13");
        eventResultDesc = jsonObject.getString("14");
        internalCauseCodeID = jsonObject.getString("17");
        internalCauseCodeDesc = jsonObject.getString("18");
        externalProtocolID = jsonObject.getString("5");
        externalProtocolName = jsonObject.getString("4");
        externalCauseCode = jsonObject.getString("15");
        externalCauseCodeDesc = jsonObject.getString("16");
        faultCodeID = jsonObject.getString("19");
        faultCodeDesc = jsonObject.getString("20");
        advice = jsonObject.getString("21");
        incomingRoute = jsonObject.getString("6");
        outgoingRoute = jsonObject.getString("7");
        internalLocationCode = jsonObject.getString("2");
        bearerServiceCde = jsonObject.getString("23");
        teleServiceCode = jsonObject.getString("24");
        eventSourceName = jsonObject.getString("3");
        callIdNum = jsonObject.getString("25");
        typeOfCallingSubscriber = jsonObject.getString("26");
        callingPartyNumber = jsonObject.getString("27");
        calledPartyNumber = jsonObject.getString("28");
        callingSubImsi = jsonObject.getString("29");
        calledSubImsi = jsonObject.getString("30");
        callingSubImei = jsonObject.getString("31");
        calledSubImei = jsonObject.getString("32");
        msRoamingNumber = jsonObject.getString("33");
        disconnectParty = jsonObject.getString("34");
        callDuration = jsonObject.getString("35");
        seizureTime = jsonObject.getString("36");
        originalCalledNumber = jsonObject.getString("37");
        redirectNumber = jsonObject.getString("38");
        redirectCounter = jsonObject.getString("39");
        redirectImsi = jsonObject.getString("40");
        redirectSpn = jsonObject.getString("41");
        callPosition = jsonObject.getString("42");
        eosInfo = jsonObject.getString("43");
        recordSequenceNumber = jsonObject.getString("44");
        networkCallReference = jsonObject.getString("45");
        mcc = jsonObject.getString("46");
        mnc = jsonObject.getString("47");
        rac = jsonObject.getString("48");
        lac = jsonObject.getString("49");
    }


    private void parseJSONObjectLocationServices(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString("4");
        msisdn = jsonObject.getString("5");
        imsi = jsonObject.getString("6");
        tac = jsonObject.getString("7");
        manufacturer = jsonObject.getString("8");
        marketingTime = jsonObject.getString("9");
        eventIdDesc = jsonObject.getString("10");
        eventResultDesc = jsonObject.getString("11");
        unSucPositionReasonDesc = jsonObject.getString("12");
        lcsClientTypeDesc = jsonObject.getString("13");
        typeLocationRequestDesc = jsonObject.getString("14");
        unSucPositionReasonID = jsonObject.getString("15");
        lcsClientTypeID = jsonObject.getString("16");
        typeLocationRequestID = jsonObject.getString("17");
        ratDesc = jsonObject.getString("18");
        eventSourceName = jsonObject.getString("19");
        controller = jsonObject.getString("20");
        cell = jsonObject.getString("21");
        vendor = jsonObject.getString("22");
        callIdNum = jsonObject.getString("23");
        targetMsisdn = jsonObject.getString("24");
        targetImsi = jsonObject.getString("25");
        targetImei = jsonObject.getString("26");
        lcsClientId = jsonObject.getString("27");
        positionDelivery = jsonObject.getString("28");
        recordSequenceNumber = jsonObject.getString("29");
        networkCallReference = jsonObject.getString("30");
        mcc = jsonObject.getString("31");
        mnc = jsonObject.getString("32");
        rac = jsonObject.getString("33");
        lac = jsonObject.getString("34");
    }

    private void parseJSONObjectMsOriginatingNTerminatingSMS(final JSONObject jsonObject) throws JSONException {
        eventTime = jsonObject.getString("4");
        msisdn = jsonObject.getString("5");
        imsi = jsonObject.getString("6");
        tac = jsonObject.getString("7");
        manufacturer = jsonObject.getString("8");
        marketingTime = jsonObject.getString("9");
        eventIdDesc = jsonObject.getString("10");
        eventResultDesc = jsonObject.getString("11");
        smsResultDesc = jsonObject.getString("12");
        msgTypeIndicatorDesc = jsonObject.getString("13");
        smsResultID = jsonObject.getString("14");
        msgTypeIndicatorID = jsonObject.getString("15");
        bearerServiceCde = jsonObject.getString("16");
        teleServiceCode = jsonObject.getString("17");
        ratDesc = jsonObject.getString("18");
        eventSourceName = jsonObject.getString("19");
        controller = jsonObject.getString("20");
        cell = jsonObject.getString("21");
        vendor = jsonObject.getString("22");
        callIdNum = jsonObject.getString("23");
        typeOfCallingSubscriber = jsonObject.getString("24");
        callingPartyNumber = jsonObject.getString("25");
        calledPartyNumber = jsonObject.getString("26");
        callingSubImsi = jsonObject.getString("27");
        calledSubImsi = jsonObject.getString("28");
        callingSubImei = jsonObject.getString("29");
        calledSubImei = jsonObject.getString("30");
        callingSubImeiSv = jsonObject.getString("31");
        calledSubImeiSv = jsonObject.getString("32");
        originatingNumber = jsonObject.getString("33");
        destinationNumber = jsonObject.getString("34");
        serviceCenter = jsonObject.getString("35");
        originatingTime = jsonObject.getString("36");
        deliveryTime = jsonObject.getString("37");
        recordSequenceNumber = jsonObject.getString("38");
        mcc = jsonObject.getString("39");
        mnc = jsonObject.getString("40");
        rac = jsonObject.getString("41");
        lac = jsonObject.getString("42");
    }
}
