/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * Utility methods for request parameter handling
 *
 * @author eemecoy
 */
public class RequestParametersWrapper {

    private final MultivaluedMap<String, String> requestParameters;

    /**
     * @param requestParameters
     */
    public RequestParametersWrapper(final MultivaluedMap<String, String> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String getType() {
        return requestParameters.getFirst(TYPE_PARAM);
    }

    /**
     * This method gets the maxRows value from the parameter map and parses it to
     * an int, including error handling.
     *
     * @param maxAllowableSize The maximum allowable size for the result set returned
     * @return the value to be put into the count on the query ( <=0 for all
     *         results)
     */
    public int getCountValue(final int maxAllowableSize) {
        int count;
        try {
            count = Integer.parseInt(requestParameters.getFirst(MAX_ROWS));
        } catch (final NumberFormatException e) {
            ServicesLogger.warn(getClass().getName(), "getCountValue",
                    "URL parameter 'maxRows' is not a valid number, setting value to the maximum allowable value: "
                            + maxAllowableSize);
            count = maxAllowableSize;
        }

        if (count > maxAllowableSize) {
            ServicesLogger.warn(getClass().getName(), "getCountValue", "Requested result size of " + count
                    + " but this exceeds the maximum possible configurable limit of " + maxAllowableSize
                    + ", the maximum result set size will default to " + maxAllowableSize);
            count = maxAllowableSize;
        }

        return count;
    }

    /**
     * @return value of the display parameter in the requestParameters
     */
    public String getDisplay() {
        return requestParameters.getFirst(DISPLAY_PARAM);
    }

    public String getGroupName() {
        return requestParameters.getFirst(GROUP_NAME_PARAM);
    }

    public String getEventId() {
        return requestParameters.getFirst(EVENT_ID_PARAM);
    }

    public String getKey() {
        return requestParameters.getFirst(KEY_PARAM);
    }

    public String getSearchParam() {
        return requestParameters.getFirst(SEARCH_PARAM);
    }

    public String getFilterType() {
        return requestParameters.getFirst(FILTER_TYPE);
    }
    
    /**
     * Check to see if the requestParameters contains a tac parameter with a valid (non empty) value.
     * If it exists, we return true.
     *
     * @return true if there is a tac parameter with a valid value, false otherwise
     */
    public boolean requestContainsTACParam() {
        if (requestParameters.containsKey(TAC_PARAM)) {
            final String tacParam = requestParameters.getFirst(TAC_PARAM);
            if (StringUtils.isNotBlank(tacParam)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fetch the tac parameter from either:
     * - the NODE_PARAM if this is specified (in which case the format of the parameter value is marketing_name,tac
     *  eg MyPhone,1234556
     *  - the TAC_PARAM if this is specified (in thich case the format of the parameter value is just the tac value
     *  
     *  @return tac parameter if it exists, null otherwise
     */
    public String getTacParamFromNodeOrTacParam() {
        if (requestParameters.containsKey(TAC_PARAM)) {
            return requestParameters.getFirst(TAC_PARAM);
        }
        if (requestParameters.containsKey(TYPE_PARAM)) {
            final String nodeType = requestParameters.getFirst(TYPE_PARAM);
            //need to check for the NODE parameter here - otherwise it could be a terminal group
            if (nodeType.equals(TYPE_TAC) && requestParameters.containsKey(NODE_PARAM)) {
                return extractTacFromNodeParam(requestParameters.getFirst(NODE_PARAM));
            }
        }
        return null;

    }

    private String extractTacFromNodeParam(final String nodeParamContainingTac) {
        final int indexOfComma = nodeParamContainingTac.lastIndexOf(COMMA_CHAR);
        return nodeParamContainingTac.substring(indexOfComma + 1, nodeParamContainingTac.length());
    }

    private boolean isGroup() {
        return requestParameters.containsKey(GROUP_NAME_PARAM);
    }

    /**
     * Check if query is a controller query (and not a controller group query)
     * @return boolean see above
     */
    public boolean isController() {
        return requestParameters.containsKey(TYPE_PARAM) && BSC.equals(requestParameters.getFirst(TYPE_PARAM))
                && !isGroup();
    }

    public boolean isAccessArea() {
        return requestParameters.containsKey(TYPE_PARAM) && CELL.equals(requestParameters.getFirst(TYPE_PARAM))
                && !isGroup();
    }

}
