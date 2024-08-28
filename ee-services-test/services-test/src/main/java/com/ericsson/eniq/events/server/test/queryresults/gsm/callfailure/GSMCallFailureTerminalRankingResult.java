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
public class GSMCallFailureTerminalRankingResult implements QueryResult {

    private int rank;

    private String manufacturer;

    private String model;

    private int tac;

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
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * @return the tac
     */
    public int getTac() {
        return tac;
    }

    /**
     * @param tac the tac to set
     */
    public void setTac(final int tac) {
        this.tac = tac;
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
        manufacturer = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        model = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + rank;
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + tac;
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
        if (!(obj instanceof GSMCallFailureTerminalRankingResult)) {
            return false;
        }
        final GSMCallFailureTerminalRankingResult other = (GSMCallFailureTerminalRankingResult) obj;
        if (rank != other.getRank()) {
            return false;
        }
        if (manufacturer == null) {
            if (other.getManufacturer() != null) {
                return false;
            }
        } else if (!manufacturer.equals(other.getManufacturer())) {
            return false;
        }
        if (model == null) {
            if (other.getModel() != null) {
                return false;
            }
        } else if (!model.equals(other.getModel())) {
            return false;
        }
        if (tac != other.getTac()) {
            return false;
        }
        if (numFailures != other.getNumFailures()) {
            return false;
        }

        return true;
    }
}
