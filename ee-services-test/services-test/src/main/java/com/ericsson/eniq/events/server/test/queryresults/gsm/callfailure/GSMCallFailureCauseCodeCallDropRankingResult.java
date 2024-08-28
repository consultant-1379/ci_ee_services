/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejoegaf
 * @since 2011
 *
 */
public class GSMCallFailureCauseCodeCallDropRankingResult implements QueryResult {

    private int rank;

    private String causeCodeDescription;

    private String causeCodeId;

    private int numFailures;

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(final int rank) {
        this.rank = rank;
    }

    /**
     * @return the causeCodeDescription
     */
    public String getCauseCodeDescription() {
        return causeCodeDescription;
    }

    /**
     * @param causeCodeDescription the causeCodeDescription to set
     */
    public void setCauseCodeDescription(final String causeCodeDescription) {
        this.causeCodeDescription = causeCodeDescription;
    }

    /**
     * @return the causeCodeId
     */
    public String getCauseCodeId() {
        return causeCodeId;
    }

    /**
     * @param causeCodeId the causeCodeId to set
     */
    public void setCauseCodeId(final String causeCodeId) {
        this.causeCodeId = causeCodeId;
    }

    /**
     * @return the numFailures
     */
    public int getNumFailures() {
        return numFailures;
    }

    /**
     * @param numFailures the numFailures to set
     */
    public void setNumFailures(final int numFailures) {
        this.numFailures = numFailures;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDescription = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        causeCodeId = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
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
        result = prime * result + ((causeCodeDescription == null) ? 0 : causeCodeDescription.hashCode());
        result = prime * result + ((causeCodeId == null) ? 0 : causeCodeId.hashCode());
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
        if (!(obj instanceof GSMCallFailureCauseCodeCallDropRankingResult)) {
            return false;
        }
        final GSMCallFailureCauseCodeCallDropRankingResult other = (GSMCallFailureCauseCodeCallDropRankingResult) obj;
        if (rank != other.getRank()) {
            return false;
        }
        if (causeCodeDescription == null) {
            if (other.getCauseCodeDescription() != null) {
                return false;
            }
        } else if (!causeCodeDescription.equals(other.getCauseCodeDescription())) {
            return false;
        }
        if (causeCodeId == null) {
            if (other.getCauseCodeId() != null) {
                return false;
            }
        } else if (!causeCodeId.equals(other.getCauseCodeId())) {
            return false;
        }

        if (numFailures != other.getNumFailures()) {
            return false;
        }

        return true;
    }
}
