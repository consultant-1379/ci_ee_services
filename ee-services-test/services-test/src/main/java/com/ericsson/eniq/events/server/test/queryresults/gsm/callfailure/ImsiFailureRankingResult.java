package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class ImsiFailureRankingResult implements QueryResult {

  private int rank;

  private String imsi;

  private int numFailures;

  /**
   * @param rank
   *          the rank to set
   */
  public void setRank(final int rank) {
    this.rank = rank;
  }

  /**
   * @param imsi
   *          the imsi to set
   */
  public void setImsi(final String imsi) {
    this.imsi = imsi;
  }

  /**
   * @param numFailures
   *          the numFailures to set
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
   * @return the controller
   */
  public String getImsi() {
    return imsi;
  }

  /**
   * @return the numFailures
   */
  public int getNumFailures() {
    return numFailures;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
   * (com.ericsson.eniq.events.server.json.JSONObject)
   */
  @Override
  public void parseJSONObject(final JSONObject object) throws JSONException {
    rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
    imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    numFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + rank;
    result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
    result = prime * result + numFailures;

    return result;
  }

  /*
   * (non-Javadoc)
   * 
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
    if (!(obj instanceof ImsiFailureRankingResult)) {
      return false;
    }
    final ImsiFailureRankingResult other = (ImsiFailureRankingResult) obj;
    if (rank != other.getRank()) {
      return false;
    }
    if (imsi == null) {
      if (other.getImsi() != null) {
        return false;
      }
    } else if (!imsi.equals(other.getImsi())) {
      return false;
    }

    if (numFailures != other.getNumFailures()) {
      return false;
    }

    return true;
  }

}
