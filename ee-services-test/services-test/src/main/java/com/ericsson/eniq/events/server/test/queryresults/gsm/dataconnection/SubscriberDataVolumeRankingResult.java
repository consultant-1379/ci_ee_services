/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejoegaf
 * @since 2011
 *
 */
public class SubscriberDataVolumeRankingResult implements QueryResult {

    private int rank;

    private long imsi;

    private double downLinkDataVol;

    private double upLinkDataVol;

    private double totalDataVol;

    public SubscriberDataVolumeRankingResult() {
    }

    public SubscriberDataVolumeRankingResult(final int rank, final long imsi, final double downLinkDataVol,
            final double upLinkDataVol, final double totalDataVol) {
        this.rank = rank;
        this.imsi = imsi;
        this.downLinkDataVol = downLinkDataVol;
        this.upLinkDataVol = upLinkDataVol;
        this.totalDataVol = totalDataVol;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getLong(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        downLinkDataVol = object.getDouble(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        upLinkDataVol = object.getDouble(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        totalDataVol = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    public int getRank() {
        return rank;
    }

    public long getImsi() {
        return imsi;
    }

    /**
     * @return the downLinkDataVol
     */
    public double getDownLinkDataVol() {
        return downLinkDataVol;
    }

    /**
     * @return the upLinkDataVol
     */
    public double getUpLinkDataVol() {
        return upLinkDataVol;
    }

    /**
     * @return the totalDataVol
     */
    public double getTotalDataVol() {
        return totalDataVol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(downLinkDataVol);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (int) (imsi ^ (imsi >>> 32));
        result = prime * result + rank;
        temp = Double.doubleToLongBits(totalDataVol);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(upLinkDataVol);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SubscriberDataVolumeRankingResult other = (SubscriberDataVolumeRankingResult) obj;
        if (Double.doubleToLongBits(downLinkDataVol) != Double.doubleToLongBits(other.downLinkDataVol))
            return false;
        if (imsi != other.imsi)
            return false;
        if (rank != other.rank)
            return false;
        if (Double.doubleToLongBits(totalDataVol) != Double.doubleToLongBits(other.totalDataVol))
            return false;
        if (Double.doubleToLongBits(upLinkDataVol) != Double.doubleToLongBits(other.upLinkDataVol))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%d %d %.2f %.2f %.2f", rank, imsi, downLinkDataVol, upLinkDataVol, totalDataVol);
    }

}
