package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class MultipleAccessAreaRankingResult implements QueryResult {

    private int noOfErrors;

    private String noOfSuccesses;

    private String ratDesc;

    private String controller;

    private String cell;

    public String getCell() {
        return cell;
    }

    public String getController() {
        return controller;
    }

    public String getRATDesc() {
        return ratDesc;

    }

    public String getNoSuccesses() {
        return noOfSuccesses;
    }

    public int getNoErrors() {
        return noOfErrors;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        ratDesc = jsonObject.getString("2");
        controller = jsonObject.getString("5");
        cell = jsonObject.getString("6");
        noOfErrors = jsonObject.getInt("7");
        noOfSuccesses = jsonObject.getString("8");
    }

}