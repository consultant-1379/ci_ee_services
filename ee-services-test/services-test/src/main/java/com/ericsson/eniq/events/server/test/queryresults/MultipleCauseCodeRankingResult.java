package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class MultipleCauseCodeRankingResult implements QueryResult {

    private String noOfErrors;

    private String noOfSuccesses;

    private String causeCodeDesc;

    private String causeProtTypeDesc;

    private int causeProtTypeID;

    private int causeCodeID;

    public String getCauseProtTypeDesc() {
        return causeProtTypeDesc;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    public String getNoSuccesses() {
        return noOfSuccesses;
    }

    public String getNoErrors() {
        return noOfErrors;
    }

    public int getCauseProtTypeID() {
        return causeProtTypeID;
    }

    public int getCauseCodeID() {
        return causeCodeID;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeProtTypeDesc = jsonObject.getString("2");
        causeCodeDesc = jsonObject.getString("3");
        causeProtTypeID = jsonObject.getInt("4");
        causeCodeID = jsonObject.getInt("5");
        noOfErrors = jsonObject.getString("6");
        noOfSuccesses = jsonObject.getString("7");
    }

}
