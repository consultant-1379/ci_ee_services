/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eriwals
 *
 */
@Ignore
public class CauseCodeAnalysisGroupSummaryResult implements QueryResult {

    private String group;

    private String causeProtocolType;

    private String causeProtocolTypeDescription;

    private String causeCodeID;

    private String causeCode;

    private String subCauseCodeID;

    private String subCauseCode;

    private String subCauseCodeHelp;

    private String occurrences;

    private String impactedSubscribers;

    public String getGroup() {
        return group;
    }

    public String getCauseProtocolType() {
        return causeProtocolType;
    }

    public String getCauseProtocolTypeDescription() {
        return causeProtocolTypeDescription;
    }

    public String getCauseCodeID() {
        return causeCodeID;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public String getSubCauseCodeID() {
        return subCauseCodeID;
    }

    public String getSubCauseCode() {
        return subCauseCode;
    }

    public String getSubCauseCodeHelp() {
        return subCauseCodeHelp;
    }

    public String getOccurrences() {
        return occurrences;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        group = jsonObject.getString("1");
        causeProtocolType = jsonObject.getString("2");
        causeProtocolTypeDescription = jsonObject.getString("3");
        causeCodeID = jsonObject.getString("4");
        causeCode = jsonObject.getString("5");
        subCauseCodeID = jsonObject.getString("6");
        subCauseCode = jsonObject.getString("7");
        subCauseCodeHelp = jsonObject.getString("8");
        occurrences = jsonObject.getString("9");
        impactedSubscribers = jsonObject.getString("10");
    }
}
