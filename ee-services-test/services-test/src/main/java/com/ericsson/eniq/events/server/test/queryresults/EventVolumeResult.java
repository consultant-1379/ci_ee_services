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
public class EventVolumeResult implements QueryResult {

    public static final String ATTACH_EVENT_COUNT_JSON_ID = "2";

    public static final String ATTACH_EVENT_FAIL_COUNT_JSON_ID = "3";

    public static final String ACTIVATE_EVENT_COUNT_JSON_ID = "4";

    public static final String ACTIVATE_EVENT_FAIL_COUNT_JSON_ID = "5";

    public static final String RAU_EVENT_COUNT_JSON_ID = "6";

    public static final String RAU_EVENT_FAIL_COUNT_JSON_ID = "7";

    public static final String ISRAU_EVENT_COUNT_JSON_ID = "8";

    public static final String ISRAU_EVENT_FAIL_COUNT_JSON_ID = "9";

    public static final String DEACTIVATE_EVENT_COUNT_JSON_ID = "10";

    public static final String DEACTIVATE_EVENT_FAIL_COUNT_JSON_ID = "11";

    public static final String DETACH_EVENT_COUNT_JSON_ID = "12";

    public static final String DETACH_EVENT_FAIL_COUNT_JSON_ID = "13";

    public static final String SERVICE_REQUEST_EVENT_COUNT_JSON_ID = "14";

    public static final String SERVICE_REQUEST_EVENT_FAIL_COUNT_JSON_ID = "15";

    public static final String L_ATTACH_EVENT_COUNT_JSON_ID = "16";

    public static final String L_ATTACH_EVENT_FAIL_COUNT_JSON_ID = "17";

    public static final String L_DETACH_EVENT_COUNT_JSON_ID = "18";

    public static final String L_DETACH_EVENT_FAIL_COUNT_JSON_ID = "19";

    public static final String L_HANDOVER_EVENT_COUNT_JSON_ID = "20";

    public static final String L_HANDOVER_FAIL_EVENT_COUNT_JSON_ID = "21";

    public static final String L_TAU_EVENT_COUNT_JSON_ID = "22";

    public static final String L_TAU_FAIL_EVENT_COUNT_JSON_ID = "23";

    public static final String L_DEDICATED_BEARER_ACTIVATE_EVENT_COUNT_JSON_ID = "24";

    public static final String L_DEDICATED_BEARER_ACTIVATE_FAIL_EVENT_COUNT_JSON_ID = "25";

    public static final String L_DEDICATED_BEARER_DEACTIVATE_EVENT_COUNT_JSON_ID = "26";

    public static final String L_DEDICATED_BEARER_DEACTIVATE_FAIL_EVENT_COUNT_JSON_ID = "27";

    public static final String L_PDN_CONNECT_EVENT_COUNT_JSON_ID = "28";

    public static final String L_PDN_CONNECT_FAIL_EVENT_COUNT_JSON_ID = "29";

    public static final String L_PDN_DISCONNECT_EVENT_COUNT_JSON_ID = "30";

    public static final String L_PDN_DISCONNECT_FAIL_EVENT_COUNT_JSON_ID = "31";

    public static final String L_SERVICE_REQUEST_EVENT_COUNT_JSON_ID = "32";

    public static final String L_SERVICE_REQUEST_FAIL_EVENT_COUNT_JSON_ID = "33";

    public static final String IMPACTED_SUBSCRIBERS_JSON_ID = "34";

    private int attachEventCount;

    private int attachEventFailCount;

    private int activateEventCount;

    private int activateEventFailCount;

    private int rauEventCount;

    private int rauEventFailCount;

    private int israuEventCount;

    private int israuEventFailCount;

    private int deactivateEventCount;

    private int deactivateEventFailCount;

    private int detachEventCount;

    private int detachEventFailCount;

    private int serviceRequestEventCount;

    private int serviceRequestEventFailCount;

    private int lAttachEventCount;

    private int lAttachEventFailCount;

    private int lDetachEventCount;

    private int lDetachEventFailCount;

    private int lHandoverEventCount;

    private int lHandoverFailEventCount;

    private int lTauEventCount;

    private int lTauFailEventCount;

    private int lDedicatedBearerActivateEventCount;

    private int lDedicatedBearerActivateFailEventCount;

    private int lDedicatedBearerDeactivateEventCount;

    private int lDedicatedBearerDeactivateFailEventCount;

    private int lPdnConnectEventCount;

    private int lPdnConnectFailEventCount;

    private int lPdnDisconnectEventCount;

    private int lPdnDisconnectFailEventCount;

    private int lServiceRequestEventCount;

    private int lServiceRequestFailEventCount;

    private int impactedSubscribers;

    /**
     * @return the attachEventCount
     */
    public int getAttachEventCount() {
        return attachEventCount;
    }

    /**
     * @return the attachEventFailCount
     */
    public int getAttachEventFailCount() {
        return attachEventFailCount;
    }

    /**
     * @return the activateEventCount
     */
    public int getActivateEventCount() {
        return activateEventCount;
    }

    /**
     * @return the activateEventFailCount
     */
    public int getActivateEventFailCount() {
        return activateEventFailCount;
    }

    /**
     * @return the rauEventCount
     */
    public int getRauEventCount() {
        return rauEventCount;
    }

    /**
     * @return the rauEventFailCount
     */
    public int getRauEventFailCount() {
        return rauEventFailCount;
    }

    /**
     * @return the israuEventCount
     */
    public int getIsrauEventCount() {
        return israuEventCount;
    }

    /**
     * @return the israuEventFailCount
     */
    public int getIsrauEventFailCount() {
        return israuEventFailCount;
    }

    /**
     * @return the deactivateEventCount
     */
    public int getDeactivateEventCount() {
        return deactivateEventCount;
    }

    /**
     * @return the deactivateEventFailCount
     */
    public int getDeactivateEventFailCount() {
        return deactivateEventFailCount;
    }

    /**
     * @return the detachEventCount
     */
    public int getDetachEventCount() {
        return detachEventCount;
    }

    /**
     * @return the detachEventFailCount
     */
    public int getDetachEventFailCount() {
        return detachEventFailCount;
    }

    /**
     * @return the serviceRequestEventCount
     */
    public int getServiceRequestEventCount() {
        return serviceRequestEventCount;
    }

    /**
     * @return the serviceRequestEventFailCount
     */
    public int getServiceRequestEventFailCount() {
        return serviceRequestEventFailCount;
    }

    /**
     * @return the lAttachEventCount
     */
    public int getLAttachEventCount() {
        return lAttachEventCount;
    }

    /**
     * @return the lAttachEventFailCount
     */
    public int getLAttachEventFailCount() {
        return lAttachEventFailCount;
    }

    /**
     * @return the lDetachEventCount
     */
    public int getLDetachEventCount() {
        return lDetachEventCount;
    }

    /**
     * @return the lDetachEventFailCount
     */
    public int getLDetachEventFailCount() {
        return lDetachEventFailCount;
    }

    /**
     * @return the lHandoverEventCount
     */
    public int getLHandoverEventCount() {
        return lHandoverEventCount;
    }

    /**
     * @return the lHandoverFailEventCount
     */
    public int getLHandoverFailEventCount() {
        return lHandoverFailEventCount;
    }

    /**
     * @return the lTauEventCount
     */
    public int getLTauEventCount() {
        return lTauEventCount;
    }

    /**
     * @return the lTauFailEventCount
     */
    public int getLTauFailEventCount() {
        return lTauFailEventCount;
    }

    /**
     * @return the lDedicatedBearerActivateEventCount
     */
    public int getLDedicatedBearerActivateEventCount() {
        return lDedicatedBearerActivateEventCount;
    }

    /**
     * @return the lDedicatedBearerActivateFailEventCount
     */
    public int getLDedicatedBearerActivateFailEventCount() {
        return lDedicatedBearerActivateFailEventCount;
    }

    /**
     * @return the lDedicatedBearerDeactivateEventCount
     */
    public int getLDedicatedBearerDeactivateEventCount() {
        return lDedicatedBearerDeactivateEventCount;
    }

    /**
     * @return the lDedicatedBearerDeactivateFailEventCount
     */
    public int getLDedicatedBearerDeactivateFailEventCount() {
        return lDedicatedBearerDeactivateFailEventCount;
    }

    /**
     * @return the lPdnConnectEventCount
     */
    public int getLPdnConnectEventCount() {
        return lPdnConnectEventCount;
    }

    /**
     * @return the lPdnConnectFailEventCount
     */
    public int getLPdnConnectFailEventCount() {
        return lPdnConnectFailEventCount;
    }

    /**
     * @return the lPdnDisconnectEventCount
     */
    public int getLPdnDisconnectEventCount() {
        return lPdnDisconnectEventCount;
    }

    /**
     * @return the lPdnDisconnectFailEventCount
     */
    public int getLPdnDisconnectFailEventCount() {
        return lPdnDisconnectFailEventCount;
    }

    /**
     * @return the lServiceRequestEventCount
     */
    public int getLServiceRequestEventCount() {
        return lServiceRequestEventCount;
    }

    /**
     * @return the lServiceRequestFailEventCount
     */
    public int getLServiceRequestFailEventCount() {
        return lServiceRequestFailEventCount;
    }

    /**
     * @return the impactedSubscribers
     */
    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    /**
     * Parses the event volume json object and extracts the data to the relevant variables  
     * 
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        attachEventCount = jsonObject.getInt(ATTACH_EVENT_COUNT_JSON_ID);
        attachEventFailCount = jsonObject.getInt(ATTACH_EVENT_FAIL_COUNT_JSON_ID);
        activateEventCount = jsonObject.getInt(ACTIVATE_EVENT_COUNT_JSON_ID);
        activateEventFailCount = jsonObject.getInt(ACTIVATE_EVENT_FAIL_COUNT_JSON_ID);
        rauEventCount = jsonObject.getInt(RAU_EVENT_COUNT_JSON_ID);
        rauEventFailCount = jsonObject.getInt(RAU_EVENT_FAIL_COUNT_JSON_ID);
        israuEventCount = jsonObject.getInt(ISRAU_EVENT_COUNT_JSON_ID);
        israuEventFailCount = jsonObject.getInt(ISRAU_EVENT_FAIL_COUNT_JSON_ID);
        deactivateEventCount = jsonObject.getInt(DEACTIVATE_EVENT_COUNT_JSON_ID);
        deactivateEventFailCount = jsonObject.getInt(DEACTIVATE_EVENT_FAIL_COUNT_JSON_ID);
        detachEventCount = jsonObject.getInt(DETACH_EVENT_COUNT_JSON_ID);
        detachEventFailCount = jsonObject.getInt(DETACH_EVENT_FAIL_COUNT_JSON_ID);
        serviceRequestEventCount = jsonObject.getInt(SERVICE_REQUEST_EVENT_COUNT_JSON_ID);
        serviceRequestEventFailCount = jsonObject.getInt(SERVICE_REQUEST_EVENT_FAIL_COUNT_JSON_ID);
        lAttachEventCount = jsonObject.getInt(L_ATTACH_EVENT_COUNT_JSON_ID);
        lAttachEventFailCount = jsonObject.getInt(L_ATTACH_EVENT_FAIL_COUNT_JSON_ID);
        lDetachEventCount = jsonObject.getInt(L_DETACH_EVENT_COUNT_JSON_ID);
        lDetachEventFailCount = jsonObject.getInt(L_DETACH_EVENT_FAIL_COUNT_JSON_ID);
        lHandoverEventCount = jsonObject.getInt(L_HANDOVER_EVENT_COUNT_JSON_ID);
        lHandoverFailEventCount = jsonObject.getInt(L_HANDOVER_FAIL_EVENT_COUNT_JSON_ID);
        lTauEventCount = jsonObject.getInt(L_TAU_EVENT_COUNT_JSON_ID);
        lTauFailEventCount = jsonObject.getInt(L_TAU_FAIL_EVENT_COUNT_JSON_ID);
        lDedicatedBearerActivateEventCount = jsonObject.getInt(L_DEDICATED_BEARER_ACTIVATE_EVENT_COUNT_JSON_ID);
        lDedicatedBearerActivateFailEventCount = jsonObject
                .getInt(L_DEDICATED_BEARER_ACTIVATE_FAIL_EVENT_COUNT_JSON_ID);
        lDedicatedBearerDeactivateEventCount = jsonObject.getInt(L_DEDICATED_BEARER_DEACTIVATE_EVENT_COUNT_JSON_ID);
        lDedicatedBearerDeactivateFailEventCount = jsonObject
                .getInt(L_DEDICATED_BEARER_DEACTIVATE_FAIL_EVENT_COUNT_JSON_ID);
        lPdnConnectEventCount = jsonObject.getInt(L_PDN_CONNECT_EVENT_COUNT_JSON_ID);
        lPdnConnectFailEventCount = jsonObject.getInt(L_PDN_CONNECT_FAIL_EVENT_COUNT_JSON_ID);
        lPdnDisconnectEventCount = jsonObject.getInt(L_PDN_DISCONNECT_EVENT_COUNT_JSON_ID);
        lPdnDisconnectFailEventCount = jsonObject.getInt(L_PDN_DISCONNECT_FAIL_EVENT_COUNT_JSON_ID);
        lServiceRequestEventCount = jsonObject.getInt(L_SERVICE_REQUEST_EVENT_COUNT_JSON_ID);
        lServiceRequestFailEventCount = jsonObject.getInt(L_SERVICE_REQUEST_FAIL_EVENT_COUNT_JSON_ID);

    }

}
