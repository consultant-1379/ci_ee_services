/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class GSMCallFailureControllerRankingResult implements QueryResult {

    private int rank;

    private String vendor;

    private String controller;

    private int numFailures;

    /**
     * @param rank the rank to set
     */
    public void setRank(final int rank) {
        this.rank = rank;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(final String vendor) {
        this.vendor = vendor;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(final String controller) {
        this.controller = controller;
    }

    /**
     * @param numFailures the numFailures to set
     */
    public void setNumFailures(final int numFailures) {
        this.numFailures = numFailures;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @return the numFailures
     */
    public int getNumFailures() {
        return numFailures;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + rank;
        result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
        result = prime * result + ((controller == null) ? 0 : controller.hashCode());
        result = prime * result + numFailures;

        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof GSMCallFailureControllerRankingResult)) {
            return false;
        }
        final GSMCallFailureControllerRankingResult other = (GSMCallFailureControllerRankingResult) obj;
        if (rank != other.getRank()) {
            return false;
        }
        if (vendor == null) {
            if (other.getVendor() != null) {
                return false;
            }
        } else if (!vendor.equals(other.getVendor())) {
            return false;
        }
        if (controller == null) {
            if (other.getController() != null) {
                return false;
            }
        } else if (!controller.equals(other.getController())) {
            return false;
        }

        if (numFailures != other.getNumFailures()) {
            return false;
        }

        return true;
    }
}
