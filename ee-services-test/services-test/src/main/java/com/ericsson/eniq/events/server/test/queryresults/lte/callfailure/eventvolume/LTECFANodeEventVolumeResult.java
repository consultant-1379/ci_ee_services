/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure.eventvolume;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echimma
 * @since 2012
 *
 */
public class LTECFANodeEventVolumeResult implements QueryResult {
    private int INTERNAL_PROC_RRC_CONN_SETUP_FailCount;

    private int INTERNAL_PROC_S1_SIG_CONN_SETUP_FailCount;

    private int INTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount;

    private int INTERNAL_PROC_ERAB_SETUP_FailCount;

    private int INTERNAL_PROC_UE_CTXT_RELEASE_FailCount;

    private int impactedSubscribers;

    private String time;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        INTERNAL_PROC_RRC_CONN_SETUP_FailCount = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        INTERNAL_PROC_S1_SIG_CONN_SETUP_FailCount = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        INTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        INTERNAL_PROC_ERAB_SETUP_FailCount = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        INTERNAL_PROC_UE_CTXT_RELEASE_FailCount = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        time = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
    }

    public int getINTERNAL_PROC_RRC_CONN_SETUP_FailCount() {
        return INTERNAL_PROC_RRC_CONN_SETUP_FailCount;
    }

    public int getINTERNAL_PROC_S1_SIG_CONN_SETUP_FailCount() {
        return INTERNAL_PROC_S1_SIG_CONN_SETUP_FailCount;
    }

    public int getINTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount() {
        return INTERNAL_PROC_INITIAL_CTXT_SETUP_FailCount;
    }

    public int getINTERNAL_PROC_ERAB_SETUP_FailCount() {
        return INTERNAL_PROC_ERAB_SETUP_FailCount;
    }

    public int getINTERNAL_PROC_UE_CTXT_RELEASE_FailCount() {
        return INTERNAL_PROC_UE_CTXT_RELEASE_FailCount;
    }

    public String getTime() {
        return time;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
}
