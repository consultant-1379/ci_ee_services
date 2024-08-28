package com.ericsson.eniq.events.server.utils.sbr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author eeidpar
 * @author evyagrz
 */
public class ApplicationLayerDetailsProcessor implements IObjectmapProcessor<List<Map<String, Object>>> {

    @Override
    public Object processObjectMap(final List<Map<String, Object>> objectMap) {
        //set of functions found in results
        final Set<String> functionSet = new HashSet<String>();
        //map to keep objects with same timestamp together
        final Map<Object, List<Map<String, Object>>> timeMap = new HashMap<Object, List<Map<String, Object>>>();
        //temp variable
        List<Map<String, Object>> timeMappedList;
        //save multiple calls to get for eventTime
        String eventTime;

        //iterate over the list looking for all function values found in time period
        for (final Map<String, Object> mappedObject : objectMap) {
            //add the found function to the set for both UL and DL
            functionSet.add(mappedObject.get("function").toString() + " DL");
            functionSet.add(mappedObject.get("function").toString() + " UL");

            eventTime = mappedObject.get("eventTime").toString();
            //create the new list of event time not already found, skip nulls
            if ("null".equals(eventTime)) {
                continue;
            }

            if (!timeMap.containsKey(eventTime)) {
                timeMappedList = new ArrayList<Map<String, Object>>();
                timeMap.put(eventTime, timeMappedList);
            } else {
                timeMappedList = timeMap.get(eventTime);
            }
            //add the mapped object to the list
            timeMappedList.add(mappedObject);
        }
        //processed results that will be returned
        final List<Map<String, Object>> processedList = new ArrayList<Map<String, Object>>(timeMap.size());
        //stage two is to iterate over the timeMap and create one object with all found functions
        final String[] keys = timeMap.keySet().toArray(new String[timeMap.size()]);
        for (int i = 0; i < keys.length; i++) {
            timeMappedList = timeMap.get(keys[i]);
            final Map<String, Object> processedObjectMap = new TreeMap<String, Object>();
            processedObjectMap.put("eventTime", keys[i]);
            //fill defaults for all functions
            for (final String function : functionSet) {
                processedObjectMap.put(function, null);
                processedObjectMap.put(function, null);
            }
            for (final Map<String, Object> origObject : timeMappedList) {
                processedObjectMap.put(origObject.get("function").toString() + " DL", origObject.get("downlinkBytes"));
                processedObjectMap.put(origObject.get("function").toString() + " UL", origObject.get("uplinkBytes"));
            }
            processedList.add(processedObjectMap);
        }
        Collections.sort(processedList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
                final String eventTime1 = o1.get("eventTime").toString();
                final String eventTime2 = o2.get("eventTime").toString();

                return eventTime1.compareTo(eventTime2);
            }
        });
        //store the function list
        return processedList;
    }
}
