/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author ejoegaf
 * @since 2011
 *
 */
public class KPIAnalysisResult implements QueryResult {

	private String dateTimeStr;
	
    private double attachSuccessRate;

    private double pdpContextActSR;

    private double raUpdateSuccessRate;

    private double interSGSN_MMERAUpdateSR;

    private double pdpContextCutoffRatio;

    private double detachSuccessRate;

    private double serviceRequestFailureRatio;

    private double pagingFailureRatio;

    private double pagingattemptsperSubs;

    private String impactedSubscribers;

    private double attachSuccessRateLTE;

    private double pdnConnectionSuccessRate;

    private String impactedSubscribersLTE;

    private double bearerActivationSuccessRate;

    private double ueInitiatedServiceRequestFailureRatioLTE;

    private double pagingFailureRatioLTE;

    private double trackingAreaUpdateSuccessRate;

    private double interMMETrackingAreaUpdateSuccessRate;

    private double x2basedhandover;

    private double x2basedHOwithoutSGWrelocation;

    private double x2basedHOwithSGWrelocation;

    private double s1basedhandover;

    private double s1basedHOwithoutSGWandwithMMErelocation;

    private double s1basedHOwithoutSGWandMMErelocation;

    private double s1basedHOwithSGWandwithoutMMErelocation;

    private double s1basedHOwithSGWandMMErelocation;

    /**
     * @return the attachSuccessRate
     */
    public String getDateTimeStr() {
        return dateTimeStr;
    }
    /**
     * @return the attachSuccessRate
     */
    public double getAttachSuccessRate() {
        return attachSuccessRate;
    }

    /**
     * @return the pdpContextActSR
     */
    public double getPdpContextActSR() {
        return pdpContextActSR;
    }

    /**
     * @return the raUpdateSuccessRate
     */
    public double getRaUpdateSuccessRate() {
        return raUpdateSuccessRate;
    }

    /**
     * @return the interSGSN_MMERAUpdateSR
     */
    public double getInterSGSN_MMERAUpdateSR() {
        return interSGSN_MMERAUpdateSR;
    }

    /**
     * @return the pdpContextCutoffRatio
     */
    public double getPdpContextCutoffRatio() {
        return pdpContextCutoffRatio;
    }

    /**
     * @return the detachSuccessRate
     */
    public double getDetachSuccessRate() {
        return detachSuccessRate;
    }

    /**
     * @return the serviceRequestFailureRatio
     */
    public double getServiceRequestFailureRatio() {
        return serviceRequestFailureRatio;
    }

    /**
     * @return the pagingFailureRatio
     */
    public double getPagingFailureRatio() {
        return pagingFailureRatio;
    }

    /**
     * @return the pagingattemptsperSubs
     */
    public double getPagingattemptsperSubs() {
        return pagingattemptsperSubs;
    }

    /**
     * @return the impactedSubscribers
     */
    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    /**
     * @return the attachSuccessRateLTE
     */
    public double getAttachSuccessRateLTE() {
        return attachSuccessRateLTE;
    }

    /**
     * @return the pdnConnectionSuccessRate
     */
    public double getPdnConnectionSuccessRate() {
        return pdnConnectionSuccessRate;
    }

    /**
     * @return the impactedSubscribersLTE
     */
    public String getImpactedSubscribersLTE() {
        return impactedSubscribersLTE;
    }

    /**
     * @return the bearerActivationSuccessRate
     */
    public double getBearerActivationSuccessRate() {
        return bearerActivationSuccessRate;
    }

    /**
     * @return the ueInitiatedServiceRequestFailureRatioLTE
     */
    public double getUeInitiatedServiceRequestFailureRatioLTE() {
        return ueInitiatedServiceRequestFailureRatioLTE;
    }

    /**
     * @return the pagingFailureRatioLTE
     */
    public double getPagingFailureRatioLTE() {
        return pagingFailureRatioLTE;
    }

    /**
     * @return the trackingAreaUpdateSuccessRate
     */
    public double getTrackingAreaUpdateSuccessRate() {
        return trackingAreaUpdateSuccessRate;
    }

    /**
     * @return the interMMETrackingAreaUpdateSuccessRate
     */
    public double getInterMMETrackingAreaUpdateSuccessRate() {
        return interMMETrackingAreaUpdateSuccessRate;
    }

    /**
     * @return the x2basedhandover
     */
    public double getX2basedhandover() {
        return x2basedhandover;
    }

    /**
     * @return the x2basedHOwithoutSGWrelocation
     */
    public double getX2basedHOwithoutSGWrelocation() {
        return x2basedHOwithoutSGWrelocation;
    }

    /**
     * @return the x2basedHOwithSGWrelocation
     */
    public double getX2basedHOwithSGWrelocation() {
        return x2basedHOwithSGWrelocation;
    }

    /**
     * @return the s1basedhandover
     */
    public double getS1basedhandover() {
        return s1basedhandover;
    }

    /**
     * @return the s1basedHOwithoutSGWandwithMMErelocation
     */
    public double getS1basedHOwithoutSGWandwithMMErelocation() {
        return s1basedHOwithoutSGWandwithMMErelocation;
    }

    /**
     * @return the s1basedHOwithoutSGWandMMErelocation
     */
    public double getS1basedHOwithoutSGWandMMErelocation() {
        return s1basedHOwithoutSGWandMMErelocation;
    }

    /**
     * @return the s1basedHOwithSGWandwithoutMMErelocation
     */
    public double getS1basedHOwithSGWandwithoutMMErelocation() {
        return s1basedHOwithSGWandwithoutMMErelocation;
    }

    /**
     * @return the s1basedHOwithSGWandMMErelocation
     */
    public double getS1basedHOwithSGWandMMErelocation() {
        return s1basedHOwithSGWandMMErelocation;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {

    	dateTimeStr = object.getString("1");
        attachSuccessRate = object.getDouble("2");
        pdpContextActSR = object.getDouble("3");
        raUpdateSuccessRate = object.getDouble("4");
        interSGSN_MMERAUpdateSR = object.getDouble("5");
        pdpContextCutoffRatio = object.getDouble("6");
        detachSuccessRate = object.getDouble("7");
        serviceRequestFailureRatio = object.getDouble("8");
        pagingFailureRatio = object.getDouble("9");
        pagingattemptsperSubs = object.getDouble("10");
        impactedSubscribers = object.getString("11");
        attachSuccessRateLTE = object.getDouble("12");
        pdnConnectionSuccessRate = object.getDouble("13");
        impactedSubscribersLTE = object.getString("14");
        bearerActivationSuccessRate = object.getDouble("15");
        ueInitiatedServiceRequestFailureRatioLTE = object.getDouble("16");
        pagingFailureRatioLTE = object.getDouble("17");
        trackingAreaUpdateSuccessRate = object.getDouble("18");
        interMMETrackingAreaUpdateSuccessRate = object.getDouble("19");
        x2basedhandover = object.getDouble("20");
        x2basedHOwithoutSGWrelocation = object.getDouble("21");
        x2basedHOwithSGWrelocation = object.getDouble("22");
        s1basedhandover = object.getDouble("23");
        s1basedHOwithoutSGWandwithMMErelocation = object.getDouble("24");
        s1basedHOwithoutSGWandMMErelocation = object.getDouble("25");
        s1basedHOwithSGWandwithoutMMErelocation = object.getDouble("26");
        s1basedHOwithSGWandMMErelocation = object.getDouble("27");

    }

}
