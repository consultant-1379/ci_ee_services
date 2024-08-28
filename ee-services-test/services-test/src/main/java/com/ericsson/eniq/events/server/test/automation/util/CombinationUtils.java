package com.ericsson.eniq.events.server.test.automation.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;


public class CombinationUtils {
    
    /**
     * Convert combinations to MultivaluedMap.
     * @return all combinations as a list of com.ericsson.eniq.events.server.test.common.DescriptiveMultivalueMapImpl
     */
    public static <T> Object[] convertToArrayOfMultivaluedMap(final List<Map<String, T>> combinationsList) {
        final List<MultivaluedMap<String, String>> result = new ArrayList<MultivaluedMap<String, String>>();
        final Iterator<Map<String, T>> iterator = combinationsList.iterator();
        while (iterator.hasNext()) {
            final Map<String, T> nextCombination = iterator.next();
            final MultivaluedMap<String, String> multivalueMap = new DescriptiveMultivaluedMapImpl();
            for (final Entry<String, T> entry : nextCombination.entrySet()) {
                multivalueMap.putSingle(entry.getKey(), (String) entry.getValue());
            }
            result.add(multivalueMap);
        }
        return result.toArray();
    }
}
