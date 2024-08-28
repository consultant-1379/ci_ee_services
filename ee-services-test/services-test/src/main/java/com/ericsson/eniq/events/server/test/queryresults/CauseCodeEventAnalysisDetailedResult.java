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
public class CauseCodeEventAnalysisDetailedResult implements QueryResult {

    private String eventtime;

    private String imsi;

    private String tac;

    private String manufacturer;

    private String marketingName;

    private String eventIdDesc;

    private String eventResultDesc;

    private String causeProtTypeDesc;

    private String causeCode;

    private String causeCodeDesc;

    private String subcauseCode;

    private String subcauseCodeDesc;

    private String eventSourceName;

    private String ratDesc;

    private String hierarchy_3;

    private String hierarchy_2;

    private String hierarchy_1;

    private String vendor;

    private String linkedNsapi;

    private String pdpNsapi_1;

    private String pdpGgsnName_1;

    private String pdpMsIpaddress_1;

    private String pdpNsapi_2;

    private String pdpGgsnName_2;

    private String pdpMsIpaddress_2;

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

    private String causeCode_2;

    private String subcauseCode_2;

    private String eventResult;

    private String causeProtType;

    private String rat;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        eventtime = jsonObject.getString("1");
        imsi = jsonObject.getString("2");
        tac = jsonObject.getString("3");
        manufacturer = jsonObject.getString("4");
        marketingName = jsonObject.getString("5");
        eventIdDesc = jsonObject.getString("6");
        eventResultDesc = jsonObject.getString("7");
        causeProtTypeDesc = jsonObject.getString("8");
        causeCode = jsonObject.getString("9");
        causeCodeDesc = jsonObject.getString("10");
        subcauseCode = jsonObject.getString("11");
        subcauseCodeDesc = jsonObject.getString("12");
        eventSourceName = jsonObject.getString("13");
        ratDesc = jsonObject.getString("14");
        hierarchy_3 = jsonObject.getString("15");
        hierarchy_2 = jsonObject.getString("16");
        hierarchy_1 = jsonObject.getString("17");
        vendor = jsonObject.getString("18");
        linkedNsapi = jsonObject.getString("19");
        pdpNsapi_1 = jsonObject.getString("20");
        pdpGgsnName_1 = jsonObject.getString("21");
        pdpMsIpaddress_1 = jsonObject.getString("22");
        pdpNsapi_2 = jsonObject.getString("23");
        pdpGgsnName_2 = jsonObject.getString("24");
        pdpMsIpaddress_2 = jsonObject.getString("25");
        pagingAttempts = jsonObject.getString("26");
        serviceReqTriggerDesc = jsonObject.getString("27");
        requestRetries = jsonObject.getString("28");
        apn = jsonObject.getString("29");
        mcc = jsonObject.getString("30");
        mnc = jsonObject.getString("31");
        rac = jsonObject.getString("32");
        lac = jsonObject.getString("33");
        updateType = jsonObject.getString("34");
        oldSgsnName = jsonObject.getString("35");
        oldMcc = jsonObject.getString("36");
        oldMnc = jsonObject.getString("37");
        oldRac = jsonObject.getString("38");
        oldLac = jsonObject.getString("39");
        transferredPdp = jsonObject.getString("40");
        droppedPdp = jsonObject.getString("41");
        hlr = jsonObject.getString("42");
        deactivationTrigger = jsonObject.getString("43");
        causeCode = jsonObject.getString("44");
        subcauseCode = jsonObject.getString("45");
        eventResult = jsonObject.getString("46");
        causeProtType = jsonObject.getString("47");
        rat = jsonObject.getString("48");
    }

    public String getEventtime() {
        return eventtime;
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

    public String getMarketingName() {
        return marketingName;
    }

    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public String getEventResultDesc() {
        return eventResultDesc;
    }

    public String getCauseProtTypeDesc() {
        return causeProtTypeDesc;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    public String getSubcauseCode() {
        return subcauseCode;
    }

    public String getSubcauseCodeDesc() {
        return subcauseCodeDesc;
    }

    public String getEventSourceName() {
        return eventSourceName;
    }

    public String getRatDesc() {
        return ratDesc;
    }

    public String getHierarchy_3() {
        return hierarchy_3;
    }

    public String getHierarchy_2() {
        return hierarchy_2;
    }

    public String getHierarchy_1() {
        return hierarchy_1;
    }

    public String getVendor() {
        return vendor;
    }

    public String getLinkedNsapi() {
        return linkedNsapi;
    }

    public String getPdpNsapi_1() {
        return pdpNsapi_1;
    }

    public String getPdpGgsnName_1() {
        return pdpGgsnName_1;
    }

    public String getPdpMsIpaddress_1() {
        return pdpMsIpaddress_1;
    }

    public String getPdpNsapi_2() {
        return pdpNsapi_2;
    }

    public String getPdpGgsnName_2() {
        return pdpGgsnName_2;
    }

    public String getPdpMsIpaddress_2() {
        return pdpMsIpaddress_2;
    }

    public String getPagingAttempts() {
        return pagingAttempts;
    }

    public String getServiceReqTriggerDesc() {
        return serviceReqTriggerDesc;
    }

    public String getRequestRetries() {
        return requestRetries;
    }

    public String getApn() {
        return apn;
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

    public String getUpdateType() {
        return updateType;
    }

    public String getOldSgsnName() {
        return oldSgsnName;
    }

    public String getOldMcc() {
        return oldMcc;
    }

    public String getOldMnc() {
        return oldMnc;
    }

    public String getOldRac() {
        return oldRac;
    }

    public String getOldLac() {
        return oldLac;
    }

    public String getTransferredPdp() {
        return transferredPdp;
    }

    public String getDroppedPdp() {
        return droppedPdp;
    }

    public String getHlr() {
        return hlr;
    }

    public String getDeactivationTrigger() {
        return deactivationTrigger;
    }

    public String getCauseCode_2() {
        return causeCode_2;
    }

    public String getSubcauseCode_2() {
        return subcauseCode_2;
    }

    public String getEventResult() {
        return eventResult;
    }

    public String getCauseProtType() {
        return causeProtType;
    }

    public String getRat() {
        return rat;
    }

}
