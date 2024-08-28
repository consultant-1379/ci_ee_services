/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.mss;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author egraman
 * @since 2011
 *
 */
public class InternalCauseCodeAnalysisSummaryResult implements QueryResult {

    private String internalCauseCodeId;

    private String internalCauseCode;

    private String faultCodeId;

    private String faultCode;

    private String recommendedAction;

    private String ocurrences;

    private String impactedSubscribers;

    /**
     * @return the internalCauseCodeId
     */
    public String getInternalCauseCodeId() {
        return internalCauseCodeId;
    }

    /**
     * @return the internalCauseCode
     */
    public String getInternalCauseCode() {
        return internalCauseCode;
    }

    /**
     * @return the faultCodeId
     */
    public String getFaultCodeId() {
        return faultCodeId;
    }

    /**
     * @return the faultCode
     */
    public String getFaultCode() {
        return faultCode;
    }

    /**
     * @return the recommendedAction
     */
    public String getRecommendedAction() {
        return recommendedAction;
    }

    /**
     * @return the ocurrences
     */
    public String getOcurrences() {
        return ocurrences;
    }

    /**
     * @return the impactedSubscribers
     */
    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        internalCauseCodeId = jsonObject.getString("1");
        internalCauseCode = jsonObject.getString("2");
        faultCodeId = jsonObject.getString("3");
        faultCode = jsonObject.getString("4");
        recommendedAction = jsonObject.getString("5");
        ocurrences = jsonObject.getString("6");
        impactedSubscribers = jsonObject.getString("7");
    }

}
