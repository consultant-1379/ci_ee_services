/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.utils.wcdma.cfa;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

/**
 * WCDMA CFA Utils class
 * Placeholder for common method across the WCDMA CFA feature
 * 
 * @author ehaoswa
 * @since 2013
 *
 */
public final class WcdmaCfaUtils {

    /**
     * Put WCDMA CFA drill category id and category id into velocity templates
     * 
     * @param requestParameters
     * @return Map<String,Object> K,V map for velocity tempaltes
     */
    public static Map<String, Object> getCategoryInformation(final MultivaluedMap<String, String> requestParameters) {
        if (cataegoryInformationShouldBeAdded(requestParameters)) {
            return addCategoryInformation(requestParameters);
        }
        return emptyHashMap();
    }

    private static boolean cataegoryInformationShouldBeAdded(final MultivaluedMap<String, String> requestParameters) {
        return requestParameters.containsKey(WCDMA_CFA_DRILL_CATEGORY)
                && requestParameters.containsKey(CATEGORY_ID_PARAM);
    }

    private static Map<String, Object> addCategoryInformation(final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> serviceSpecificTemplateParameters = emptyHashMap();
        serviceSpecificTemplateParameters.put(WCDMA_CFA_DRILL_CATEGORY,
                requestParameters.getFirst(WCDMA_CFA_DRILL_CATEGORY));
        serviceSpecificTemplateParameters.put(CATEGORY_ID_PARAM, requestParameters.getFirst(CATEGORY_ID_PARAM));
        return serviceSpecificTemplateParameters;
    }

    private static HashMap<String, Object> emptyHashMap() {
        return new HashMap<String, Object>();
    }

}
