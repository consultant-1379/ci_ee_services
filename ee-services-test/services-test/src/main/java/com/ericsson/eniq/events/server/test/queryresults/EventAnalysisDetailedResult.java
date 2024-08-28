/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

import java.security.PublicKey;

/**
 * @author EEMECOY
 *
 */
public class EventAnalysisDetailedResult implements QueryResult {

    private String eventTime;
    private String imsi;
    private String tac;
    private String manufacturer;
    private String marketingTime;
    private String eventIdDesc;
    private String eventResultDesc;
    private String causeProtTypeDesc;
    private String causeCodeDesc;
    private String subcauseCodeDesc;
    private String eventSourceName;
    private String ratDesc;
    private String hierarchy3;
    private String hierarchy2;
    private String hierarchy1;
    private String vendor;
    private String linkedNsapi;
    private String pdpNsapi1;
    private String pdpGgsnName1;
    private String pdpMSIpAddress1;
    private String pdpNsapi2;
    private String pdpGgsnName2;
    private String pdpMsIpaddress2;
    private String pagingAttempts;
    private String serviceReqTriggerDesc;
    private String requestRetries;
    private String apn;
    private String mcc;
    private String mnc;
    private String rac;
    private String lac;
    private String updateType;
    private String oldSgsnName;
    private String oldMcc;
    private String oldMnc;
    private String oldRac;
    private String oldLac;
    private String transferredPdp;
    private String droppedPdp;
    private String hlr;
    private String deactivationTrigger;
    private String causeCode;
    private String subCauseCode;
    private String eventResult;
    private String causeProtType;
    private String rat;

    public String getEventTime(){
        return eventTime;
    }

    public String getImsi(){
        return imsi;
    }

    public String getTac(){
        return tac;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public String getMarketingTime(){
        return marketingTime;
    }

    public String getEventIdDesc(){
        return eventIdDesc;
    }

    public String getEventResultDesc(){
        return eventResultDesc;
    }

    public String getCauseProtTypeDesc(){
        return causeProtTypeDesc;
    }

    public String getCauseCodeDesc(){
        return causeCodeDesc;
    }

    public String getSubcauseCodeDesc(){
        return subcauseCodeDesc;
    }

    public String getEventSourceName(){
        return eventSourceName;
    }

    public String getRatDesc(){
        return ratDesc;
    }

    public String getHierarchy3(){
        return hierarchy3;
    }

    public String getHierarchy2(){
        return hierarchy2;
    }

    public String getHierarchy1(){
        return hierarchy1;
    }

    public String getVendor(){
        return vendor;
    }

    public String getLinkedNsapi(){
        return linkedNsapi;
    }

    public String getPdpNsapi1(){
        return pdpNsapi1;
    }

    public String getPdpGgsnName1(){
        return pdpGgsnName1;
    }

    public String getPdpMSIpAddress1(){
        return pdpMSIpAddress1;
    }

    public String getPdpNsapi2(){
        return pdpNsapi2;
    }

    public String getPdpGgsnName2(){
        return pdpGgsnName2;
    }

    public String getPdpMsIpaddress2(){
        return pdpMsIpaddress2;
    }

    public String getPagingAttempts(){
        return pagingAttempts;
    }

    public String getServiceReqTriggerDesc(){
        return serviceReqTriggerDesc;
    }

    public String getRequestRetries(){
        return requestRetries;
    }

    public String getApn(){
        return apn;
    }

    public String getMcc(){
        return mcc;
    }

    public String getMnc(){
        return mnc;
    }

    public String getRac(){
        return rac;
    }

    public String getLac(){
        return lac;
    }

    public String getUpdateType(){
        return updateType;
    }

    public String getOldSgsnName(){
        return oldSgsnName;
    }

    public String getOldMcc(){
        return oldMcc;
    }

    public String getOldMnc(){
        return oldMnc;
    }

    public String getOldRac(){
        return oldRac;
    }

    public String getOldLac(){
        return oldLac;
    }

    public String getTransferredPdp(){
        return transferredPdp;
    }

    public String getDroppedPdp(){
        return droppedPdp;
    }

    public String getHlr(){
        return hlr;
    }

    public String getDeactivationTrigger(){
        return deactivationTrigger;
    }
    ;
    public String getCauseCode(){
        return causeCode;
    }

    public String getSubCauseCode(){
        return subCauseCode;
    }

    public String getEventResult(){
        return eventResult;
    }

    public String getCauseProtType(){
        return causeProtType;
    }

    public String getRat(){
        return rat;
    }


    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
            eventTime = jsonObject.getString("1");
            imsi = jsonObject.getString("2");
            tac = jsonObject.getString("3");
            manufacturer = jsonObject.getString("4");
            marketingTime = jsonObject.getString("5");
            eventIdDesc = jsonObject.getString("6");
            eventResultDesc = jsonObject.getString("7");
            causeProtTypeDesc = jsonObject.getString("8");
            causeCodeDesc = jsonObject.getString("9");
            subcauseCodeDesc = jsonObject.getString("10");
            eventSourceName = jsonObject.getString("11");
            ratDesc = jsonObject.getString("12");
            hierarchy3 = jsonObject.getString("13");
            hierarchy2 = jsonObject.getString("14");
            hierarchy1 = jsonObject.getString("15");
            vendor = jsonObject.getString("16");
            linkedNsapi = jsonObject.getString("17");
            pdpNsapi1 = jsonObject.getString("18");
            pdpGgsnName1 = jsonObject.getString("19");
            pdpMSIpAddress1 = jsonObject.getString("20");
            pdpNsapi2 = jsonObject.getString("21");
            pdpGgsnName2 = jsonObject.getString("22");
            pdpMsIpaddress2 = jsonObject.getString("23");
            pagingAttempts = jsonObject.getString("24");
            serviceReqTriggerDesc = jsonObject.getString("25");
            requestRetries = jsonObject.getString("26");
            apn = jsonObject.getString("27");
            mcc = jsonObject.getString("28");
            mnc = jsonObject.getString("29");
            rac = jsonObject.getString("30");
            lac = jsonObject.getString("31");
            updateType = jsonObject.getString("32");
            oldSgsnName = jsonObject.getString("33");
            oldMcc = jsonObject.getString("34");
            oldMnc = jsonObject.getString("35");
            oldRac = jsonObject.getString("36");
            oldLac = jsonObject.getString("37");
            transferredPdp = jsonObject.getString("38");
            droppedPdp = jsonObject.getString("39");
            hlr = jsonObject.getString("40");
            deactivationTrigger = jsonObject.getString("41");
            causeCode = jsonObject.getString("42");
            subCauseCode = jsonObject.getString("43");
            eventResult = jsonObject.getString("44");
            causeProtType = jsonObject.getString("45");
            rat = jsonObject.getString("46");

    }

}
