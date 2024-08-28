package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class MultipleAPNRankingResult implements QueryResult {

    private String manufacturer;

    private String noOfErrors;

    private String noOfSuccesses;

    public String getNoSuccesses() {
        return noOfSuccesses;
    }

    public String getNoErrors() {
        return noOfErrors;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        manufacturer = (String) jsonObject.get("2");
        noOfErrors = jsonObject.getString("3");
        noOfSuccesses = jsonObject.getString("4");
    }

}