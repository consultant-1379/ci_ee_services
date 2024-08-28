package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ethomit
 * @since 2011
 * 
 */

public class ControllerDataVolumeRankingResult implements QueryResult {

  private int rank;

  private String vendor;

  private String controller;

  private double downLinkDataVol;

  private double upLinkDataVol;

  private double totalDataVol;

  private Long HIER3_ID;

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
    vendor = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    controller = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    downLinkDataVol = object.getDouble(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    upLinkDataVol = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    totalDataVol = object.getDouble(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    HIER3_ID = object.getLong(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
  }

  public int getRank() {
    return rank;
  }

  public String getVendor() {
    return vendor;
  }

  public String getController() {
    return controller;
  }

  public double getDownLinkDataVol() {
    return downLinkDataVol;
  }

  public double getUpLinkDataVol() {
    return upLinkDataVol;
  }

  public double getTotalDataVol() {
    return totalDataVol;
  }

  public Long getHIER3_ID() {
    return HIER3_ID;
  }

}
