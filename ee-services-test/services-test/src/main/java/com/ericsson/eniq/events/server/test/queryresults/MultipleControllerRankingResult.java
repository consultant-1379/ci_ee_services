package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

public class MultipleControllerRankingResult implements QueryResult {

    private int noOfErrors;

    private int noOfSuccesses;

    private String ratDesc;

    private String controller;

    private String vendor;

    public String getController() {
        return controller;
    }

    public String getRATDesc() {
        return ratDesc;

    }

    public int getNoSuccesses() {
        return noOfSuccesses;
    }

    public int getNoErrors() {
        return noOfErrors;
    }

    public String getVendor() {
        return vendor;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        ratDesc = jsonObject.getString("2");
        vendor = jsonObject.getString("4");
        controller = jsonObject.getString("5");
        noOfErrors = jsonObject.getInt("6");
        noOfSuccesses = jsonObject.getInt("7");
    }

}