package com.ericsson.eniq.events.server.test.queryresults.sbr;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author edarbla
 * @since 2012
 *
 */
public class SBrowserCorePopupDetailResult implements QueryResult {

    private int causeCode;
    
    private String causeCodeDesc;
    
    private String imsi;

    private int eventID;
    
    private String eventTime;
    
    private String dateTimeID;
    
    private int rat;
    
    private String ratDesc;
    
    private int tac;
    
    private String vendorName;
    
    private String mcc;
    
    private String mnc;
    
    private String imsi_mcc;
    
    private String imsi_mnc;
    
    private int roaming;
    
    private String vendor;
    
    private String eventSourceName;
    
    private int accessAreaID;
    
    private int lac;
    
    private String hier_3;
    
    private int rac;
    
    private String hier321ID;
    
    private String cellID;
    
    private int subCauseCode;

    private String subCauseCodeDesc;

    private String msisdn;
    
    private int duration;
    
    private String servingArea;
    
    private String controller;
    
    private int plmn;
    
    private String cell;
    
    private String terminal;
    
    private String network;
    
    private String eventType;
    
    private String sgsn;
    
    private int rountingArea;
    
    private int event_result;

    
    public int getCauseCode(){
        return causeCode;
    }
    
    public String getCauseCodeDesc(){
        return causeCodeDesc;
    }

    public String getImsi() {
        return imsi;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getDateTimeID() {
        return dateTimeID;
    }

    public int getRat() {
        return rat;
    }

    public String getRatDesc() {
        return ratDesc;
    }

    public int getTac() {
        return tac;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public String getImsi_mcc() {
        return imsi_mcc;
    }

    public String getImsi_mnc() {
        return imsi_mnc;
    }

    public int getRoaming() {
        return roaming;
    }

    public String getVendor() {
        return vendor;
    }

    public String getEventSourceName() {
        return eventSourceName;
    }

    public int getAccessAreaID() {
        return accessAreaID;
    }

    public int getLac() {
        return lac;
    }

    public String getHier_3() {
        return hier_3;
    }

    public int getRac() {
        return rac;
    }

    public String getHier321ID() {
        return hier321ID;
    }

    public String getCellID() {
        return cellID;
    }

    public int getSubCauseCode() {
        return subCauseCode;
    }

    public String getSubCauseCodeDesc() {
        return subCauseCodeDesc;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public int getDuration() {
        return duration;
    }

    public String getServingArea() {
        return servingArea;
    }

    public String getController() {
        return controller;
    }

    public int getPlmn() {
        return plmn;
    }

    public String getCell() {
        return cell;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getNetwork() {
        return network;
    }

    public String getEventType() {
        return eventType;
    }

    public String getSgsn() {
        return sgsn;
    }

    public int getRoutingArea() {
        return rountingArea;
    }

    public int getEvent_result() {
        return event_result;
    }

    /* (non-Javadoc)
    * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
    */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeCode = object.getInt("1");
        causeCodeDesc = object.getString("2");
    }
    
    
}
