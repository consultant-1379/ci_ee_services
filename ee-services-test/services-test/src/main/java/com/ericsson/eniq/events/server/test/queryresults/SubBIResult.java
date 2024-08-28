/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author ericker
 * @since 2011
 *
 */
public class SubBIResult implements QueryResult {

    private String failureCount;

    private String successCount;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("SubBIResult [failureCount=").append(failureCount).append(", successCount=")
                .append(successCount).append(", label=").append(label).append("]");
        return builder.toString();
    }

    private String label;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        failureCount = (String) jsonObject.get("1");
        successCount = jsonObject.getString("2");
        label = jsonObject.getString("3");
    }

    /**
     * @param failureCount the failureCount to set
     */
    public final void setFailureCount(final String failureCount) {
        this.failureCount = failureCount;
    }

    /**
     * @param successCount the successCount to set
     */
    public final void setSuccessCount(final String successCount) {
        this.successCount = successCount;
    }

    /**
     * @param label the label to set
     */
    public final void setXAxisLabel(final String label) {
        this.label = label;
    }

    /**
     * @return the failureCount
     */
    public final String getFailureCount() {
        return failureCount;
    }

    /**
     * @return the successCount
     */
    public final String getSuccessCount() {
        return successCount;
    }

    /**
     * @return the label
     */
    public final String getXAxisLabel() {
        return label;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SubBIResult)) {
            return false;
        }
        final SubBIResult result = (SubBIResult) o;

        return failureCount.equals(result.failureCount) && successCount.equals(result.successCount)
                && label.equals(result.label);
    }

    @Override
    public int hashCode() {
        int result = 22;
        result = 31 * result + (label == null ? 0 : label.hashCode());
        result = 31 * result + (successCount == null ? 0 : successCount.hashCode());
        result = 31 * result + (failureCount == null ? 0 : failureCount.hashCode());
        return result;
    }

}
