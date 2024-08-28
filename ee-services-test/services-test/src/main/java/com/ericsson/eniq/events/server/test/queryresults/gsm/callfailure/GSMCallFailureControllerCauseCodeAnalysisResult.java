/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejoegaf
 * @since 2011
 *
 */
public class GSMCallFailureControllerCauseCodeAnalysisResult implements QueryResult,
        Comparable<GSMCallFailureControllerCauseCodeAnalysisResult> {

    private String causeCodeId;

    private String causeCodeDesc;

    private int numFailures;

    private int numImpactedSubs;

    private String node;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeCodeId = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numImpactedSubs = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        node = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the causeCodeId
     */
    public String getCauseCodeId() {
        return causeCodeId;
    }

    /**
     * @param causeCodeId the causeCodeId to set
     */
    public void setCauseCodeId(final String causeCodeId) {
        this.causeCodeId = causeCodeId;
    }

    /**
     * @return the causeCodeDesc
     */
    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    /**
     * @param causeCodeDesc the causeCodeDesc to set
     */
    public void setCauseCodeDesc(final String causeCodeDesc) {
        this.causeCodeDesc = causeCodeDesc;
    }

    /**
     * @return the numFailures
     */
    public int getNumFailures() {
        return numFailures;
    }

    /**
     * @param numFailures the numFailures to set
     */
    public void setNumFailures(final int numFailures) {
        this.numFailures = numFailures;
    }

    /**
     * @return the numImpactedSubs
     */
    public int getNumImpactedSubs() {
        return numImpactedSubs;
    }

    /**
     * @param numImpactedSubs the numImpactedSubs to set
     */
    public void setNumImpactedSubs(final int numImpactedSubs) {
        this.numImpactedSubs = numImpactedSubs;
    }

    /**
     * @return the node
     */
    public String getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(final String node) {
        this.node = node;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((causeCodeId == null) ? 0 : causeCodeId.hashCode());
        result = prime * result + ((causeCodeDesc == null) ? 0 : causeCodeDesc.hashCode());
        result = prime * result + numFailures;
        result = prime * result + numImpactedSubs;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
        return result;
    }

    /* (non-Javadoc)
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
        if (!(obj instanceof GSMCallFailureControllerCauseCodeAnalysisResult)) {
            return false;
        }
        final GSMCallFailureControllerCauseCodeAnalysisResult other = (GSMCallFailureControllerCauseCodeAnalysisResult) obj;

        if (causeCodeId == null) {
            if (other.getCauseCodeId() != null) {
                printItemsNotEqual("causeCodeId", causeCodeId, other.getCauseCodeId());
                return false;
            }
        } else if (!causeCodeId.equals(other.getCauseCodeId())) {
            printItemsNotEqual("causeCodeId", causeCodeId, other.getCauseCodeId());
            return false;
        }
        if (causeCodeDesc == null) {
            if (other.getCauseCodeDesc() != null) {
                printItemsNotEqual("causeCodeDesc", causeCodeDesc, other.getCauseCodeDesc());
                return false;
            }
        } else if (!causeCodeDesc.equals(other.getCauseCodeDesc())) {
            printItemsNotEqual("causeCodeDesc", causeCodeDesc, other.getCauseCodeDesc());
            return false;
        }
        if (numFailures != other.getNumFailures()) {
            printItemsNotEqual("numFailures", numFailures, other.getNumFailures());
            return false;
        }
        if (numImpactedSubs != other.getNumImpactedSubs()) {
            printItemsNotEqual("numImpactedSubs", numImpactedSubs, other.getNumImpactedSubs());
            return false;
        }
        if (node == null) {
            if (other.getNode() != null) {
                printItemsNotEqual("node", node, other.getNode());
                return false;
            }
        } else if (!node.equals(other.getNode())) {
            printItemsNotEqual("node", node, other.getNode());
            return false;
        }
        return true;
    }

    private void printItemsNotEqual(final String varName, final String thisObjValue, final String otherObjValue) {
        System.out.println("Results not equal: " + varName + " : " + thisObjValue + " != " + otherObjValue);
    }

    private void printItemsNotEqual(final String varName, final int thisObjValue, final int otherObjValue) {
        System.out.println("Results not equal: " + varName + " : " + thisObjValue + " != " + otherObjValue);
    }

    @Override
    public int compareTo(final GSMCallFailureControllerCauseCodeAnalysisResult otherResult) {
        final int thisNumFailures = this.numFailures;
        final int otherNumFailures = otherResult.getNumFailures();
        return otherNumFailures - thisNumFailures;
    }

}
