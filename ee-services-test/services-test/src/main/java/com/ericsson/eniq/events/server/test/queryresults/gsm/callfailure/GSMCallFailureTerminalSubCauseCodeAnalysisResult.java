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
 * @author ekumjay
 * @since 2012
 *
 */
public class GSMCallFailureTerminalSubCauseCodeAnalysisResult implements QueryResult {

    private String subCauseCodeId;

    private String subCauseCodeDesc;

    private int numFailures;

    private int numImpactedSubs;
    
    private int categoryId;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        subCauseCodeId = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        subCauseCodeDesc = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        numImpactedSubs = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the subCauseCodeId
     */
    public String getSubCauseCodeId() {
        return subCauseCodeId;
    }

    /**
     * @param subCauseCodeId the subCauseCodeId to set
     */
    public void setSubCauseCodeId(final String subCauseCodeId) {
        this.subCauseCodeId = subCauseCodeId;
    }

    /**
     * @return the subCauseCodeDesc
     */
    public String getSubCauseCodeDesc() {
        return subCauseCodeDesc;
    }
    
    /**
     * 
     * @return category id
     */
    public int getcategoryId() {
    	return categoryId;
    }

    /**
     * @param subCauseCodeDesc the subCauseCodeDesc to set
     */
    public void setSubCauseCodeDesc(final String subCauseCodeDesc) {
        this.subCauseCodeDesc = subCauseCodeDesc;
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
     * 
     * @param categoryId categoryId
     */
    public void setCategoryId(final int categoryId) {
    	this.categoryId = categoryId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((subCauseCodeId == null) ? 0 : subCauseCodeId.hashCode());
        result = prime * result + ((subCauseCodeDesc == null) ? 0 : subCauseCodeDesc.hashCode());
        result = prime * result + numFailures;
        result = prime * result + numImpactedSubs;
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
        if (!(obj instanceof GSMCallFailureTerminalSubCauseCodeAnalysisResult)) {
            return false;
        }
        final GSMCallFailureTerminalSubCauseCodeAnalysisResult other = (GSMCallFailureTerminalSubCauseCodeAnalysisResult) obj;

        if (subCauseCodeId == null) {
            if (other.getSubCauseCodeId() != null) {
                printItemsNotEqual("subCauseCodeId", subCauseCodeId, other.getSubCauseCodeId());
                return false;
            }
        } else if (!subCauseCodeId.equals(other.getSubCauseCodeId())) {
            printItemsNotEqual("subCauseCodeId", subCauseCodeId, other.getSubCauseCodeId());
            return false;
        }
        if (subCauseCodeDesc == null) {
            if (other.getSubCauseCodeDesc() != null) {
                printItemsNotEqual("subCauseCodeDesc", subCauseCodeDesc, other.getSubCauseCodeDesc());
                return false;
            }
        } else if (!subCauseCodeDesc.equals(other.getSubCauseCodeDesc())) {
            printItemsNotEqual("subCauseCodeDesc", subCauseCodeDesc, other.getSubCauseCodeDesc());
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
        
        if(categoryId != other.categoryId) {
        	printItemsNotEqual("categoryId", categoryId, other.categoryId);
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
}
