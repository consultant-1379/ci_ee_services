package com.ericsson.eniq.events.server.test.queryresults.network;/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited  All rights reserved
 * -----------------------------------------------------------------------
 */

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * Desrible RoamingDrillQuerybyResult
 *
 * @author ezhelao
 * @since 01/2012
 */
public class RoamingDrillDetailResult implements QueryResult {

    private String eventtime;
    private String imsi;
    private String ptmsi;
    private String tac;
    private String manufacturer;
    private String marketingname;
    private String eventiddesc;
    private String eventresultdesc;
    private String causeprottypedesc;
    private String causecodedesc;
    private String subcausecodedesc;
    private String eventsourcename;
    private String ratdesc;
    private String hierarchy3;
    private String hierarchy2;
    private String hierarchy1;
    private String vendor;
    private String attachtypedesc;
    private String detachtriggerdesc;
    private String detachtypedesc;

    //21
    private String linkednsapi;
    private String pdpnsapi1;
    private String pdpggsnname1;
    private String pdpmsipaddress1;
    private String pdpnsapi2;
    private String pdpggsnname2;
    private String pdpmsipaddress2;
    private String pagingattempts;
    private String servicereqtriggerdesc;
    private String requestretries;

    //31
    private String apn;
    private String imsimcc;
    private String imsimnc;
    private String rac;
    private String lac;
    private String updatetype;
    private String old_sgsn_name;
    private String oldmcc;
    private String oldmnc;
    private String oldrac;



    //41
    private String oldlac;
    private String transferredpdp;
    private String droppedpdp;
    private String hlr;
    private String  deactivationtrigger;
    private String causecode;
    private String subcausecode;
    private int  eventresult;
    private String causeprottype;
    private int rat;


    //51
    private String duration;


    public String getEventtime() {
        return eventtime;
    }

    public String getImsi() {
        return imsi;
    }

    public String getPtmsi() {
        return ptmsi;
    }

    public String getTac() {
        return tac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingname() {
        return marketingname;
    }

    public String getEventiddesc() {
        return eventiddesc;
    }

    public String getEventresultdesc() {
        return eventresultdesc;
    }

    public String getCauseprottypedesc() {
        return causeprottypedesc;
    }

    public String getCausecodedesc() {
        return causecodedesc;
    }

    public String getSubcausecodedesc() {
        return subcausecodedesc;
    }

    public String getEventsourcename() {
        return eventsourcename;
    }

    public String getRatdesc() {
        return ratdesc;
    }

    public String getHierarchy3() {
        return hierarchy3;
    }

    public String getHierarchy2() {
        return hierarchy2;
    }

    public String getHierarchy1() {
        return hierarchy1;
    }

    public String getVendor() {
        return vendor;
    }

    public String getAttachtypedesc() {
        return attachtypedesc;
    }

    public String getDetachtriggerdesc() {
        return detachtriggerdesc;
    }

    public String getDetachtypedesc() {
        return detachtypedesc;
    }

    public String getLinkednsapi() {
        return linkednsapi;
    }

    public String getPdpnsapi1() {
        return pdpnsapi1;
    }

    public String getPdpggsnname1() {
        return pdpggsnname1;
    }

    public String getPdpmsipaddress1() {
        return pdpmsipaddress1;
    }

    public String getPdpnsapi2() {
        return pdpnsapi2;
    }

    public String getPdpggsnname2() {
        return pdpggsnname2;
    }

    public String getPdpmsipaddress2() {
        return pdpmsipaddress2;
    }

    public String getPagingattempts() {
        return pagingattempts;
    }

    public String getServicereqtriggerdesc() {
        return servicereqtriggerdesc;
    }

    public String getRequestretries() {
        return requestretries;
    }

    public String getApn() {
        return apn;
    }

    public String getImsimcc() {
        return imsimcc;
    }

    public String getImsimnc() {
        return imsimnc;
    }

    public String getRac() {
        return rac;
    }

    public String getLac() {
        return lac;
    }

    public String getUpdatetype() {
        return updatetype;
    }

    public String getTransferredpdp() {
        return transferredpdp;
    }

    public String getDroppedpdp() {
        return droppedpdp;
    }

    public String getHlr() {
        return hlr;
    }

    public String getDeactivationtrigger() {
        return deactivationtrigger;
    }

    public String getCausecode() {
        return causecode;
    }

    public String getSubcausecode() {
        return subcausecode;
    }

    public int getEventresult() {
        return eventresult;
    }

    public String getCauseprottype() {
        return causeprottype;
    }

    public int getRat() {
        return rat;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public void parseJSONObject(JSONObject object) throws JSONException {
        eventtime = object.getString("1");
        imsi =object.getString("2");
        ptmsi = object.getString("3");
        tac = object.getString("4");
        manufacturer = object.getString("5");
        marketingname = object.getString("6");
        eventiddesc = object.getString("7");
        eventresultdesc=object.getString("8");
        causeprottypedesc= object.getString("9");
        causecodedesc = object.getString("10");
        subcausecodedesc = object.getString("11");
        eventsourcename= object.getString("12");
        ratdesc =object.getString("13");
        hierarchy3 = object.getString("14");
        hierarchy2 = object.getString("15");
        hierarchy1 = object.getString("16");
        vendor = object.getString("17");
        attachtypedesc = object.getString("18");
        detachtriggerdesc = object.getString("19");
        detachtypedesc = object.getString("20");
        imsimcc = object.getString("32");
        imsimnc = object.getString("33");


        
    }
}
