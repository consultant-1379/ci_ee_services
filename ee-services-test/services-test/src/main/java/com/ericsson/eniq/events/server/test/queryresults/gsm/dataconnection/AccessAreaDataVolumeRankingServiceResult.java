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
 * @author eramiye
 * @since Dec 2011
 */
public class AccessAreaDataVolumeRankingServiceResult implements QueryResult {

  private int rank;

  private String vendor;

  private String controller;

  private String accessArea;

  private double uplinkDataVolume;

  private double downlinkDataVolume;

  private double totalDataVolume;

  private long hire321Id;

  public AccessAreaDataVolumeRankingServiceResult() {
  }

  public AccessAreaDataVolumeRankingServiceResult(final int rank, final String vendor,
      final String controller, final String accessArea, final double uplinkDataVolume, final double downlinkDataVolume,
      final double totalDataVolume, final long hier321Id) {
    this.rank = rank;
    this.vendor = vendor;
    this.controller = controller;
    this.accessArea = accessArea;
    this.uplinkDataVolume = uplinkDataVolume;
    this.downlinkDataVolume = downlinkDataVolume;
    this.totalDataVolume = totalDataVolume;
    this.hire321Id = hier321Id;
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

  public String getAccessArea() {
    return accessArea;
  }

  public double getUplinkDataVolume() {
    return uplinkDataVolume;
  }

  public double getDownlinkDataVolume() {
    return downlinkDataVolume;
  }

  public double getTotalDataVolume() {
    return totalDataVolume;
  }

  public long getHire321Id() {
    return hire321Id;
  }

  @Override
  public void parseJSONObject(final JSONObject object) throws JSONException {
    rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
    vendor = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    controller = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    accessArea = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    downlinkDataVolume = object.getDouble(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    uplinkDataVolume = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    totalDataVolume = object.getDouble(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    hire321Id = object.getLong(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
  }

  @Override
  public int hashCode() {
    final int prime = 37;
    int result = 17;
    result = prime * result + ((accessArea == null) ? 0 : accessArea.hashCode());
    result = prime * result + ((controller == null) ? 0 : controller.hashCode());
    long temp;
    temp = Double.doubleToLongBits(downlinkDataVolume);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + (int) (hire321Id ^ (hire321Id >>> 32));
    result = prime * result + rank;
    temp = Double.doubleToLongBits(totalDataVolume);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(uplinkDataVolume);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
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
    final AccessAreaDataVolumeRankingServiceResult other = (AccessAreaDataVolumeRankingServiceResult) obj;
    if (accessArea == null) {
      if (other.accessArea != null)
        return false;
    } else if (!accessArea.equals(other.accessArea))
      return false;
    if (controller == null) {
      if (other.controller != null)
        return false;
    } else if (!controller.equals(other.controller))
      return false;
    if (Double.doubleToLongBits(downlinkDataVolume) != Double.doubleToLongBits(other.downlinkDataVolume))
      return false;
    if (hire321Id != other.hire321Id)
      return false;
    if (rank != other.rank)
      return false;
    if (Double.doubleToLongBits(totalDataVolume) != Double.doubleToLongBits(other.totalDataVolume))
      return false;
    if (Double.doubleToLongBits(uplinkDataVolume) != Double.doubleToLongBits(other.uplinkDataVolume))
      return false;
    if (vendor == null) {
      if (other.vendor != null)
        return false;
    } else if (!vendor.equals(other.vendor))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return String.format("[%d %s %s %s %.2f %.2f %.2f %d]", rank, vendor, controller, accessArea, uplinkDataVolume,
        downlinkDataVolume, totalDataVolume, hire321Id);
  }

}
