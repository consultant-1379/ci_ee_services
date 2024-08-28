/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;


/**
 * @author etonayr
 * @since 2011
 *
 */
public class CauseCodeCallFailureRankingResult implements QueryResult {

  private String description;

  private int numFailures;

  private String causeCodeId;

  /* (non-Javadoc)
   * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
   */
  @Override
  public void parseJSONObject(final JSONObject object) throws JSONException {
    description = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    causeCodeId = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    numFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
  }

  public String getDescription() {
    return description;
  }

  public int getNumFailures() {
    return numFailures;
  }

  
  public String getCauseCodeId() {
    return causeCodeId;
  }

}
