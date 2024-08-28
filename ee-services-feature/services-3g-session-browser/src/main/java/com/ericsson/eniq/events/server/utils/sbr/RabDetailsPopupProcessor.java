package com.ericsson.eniq.events.server.utils.sbr;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;
import com.ericsson.eniq.events.ui.shared.annotations.ResultSetMappingInfo;

/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 22/02/12
 * Time: 08:31
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("PMD.UnusedFormalParameter")
public class RabDetailsPopupProcessor implements IObjectmapProcessor<List<Map<String, Object>>> {

    @SuppressWarnings("rawtypes")
    private final Class classTemplate;

    @SuppressWarnings({ "rawtypes" })
    public RabDetailsPopupProcessor(final Class classTemplate, @SuppressWarnings("unused")
    final String tzOffset) {
        this.classTemplate = classTemplate;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public Object processObjectMap(final List<Map<String, Object>> objectMap) {
        //resulting grouped property map
        final Map<String, Map<String, Object>> groupedPropertiesMap = new LinkedHashMap<String, Map<String, Object>>();
        if (objectMap.size() > 0) {
            //map to contain the traffic Mix
            final Map<String, Object> trafficMixMap = new LinkedHashMap<String, Object>();
            //iterate over the records and extract the traffic mix
            for (final Map<String, Object> recordMap : objectMap) {
                final String trafficType = (String) recordMap.remove("Traffic Type");
                if (null != trafficType && !("-").equals(trafficType)) {
                    //remove the Traffic Type and traffic volume properties
                    trafficMixMap.put(trafficType + " (Bytes)", recordMap.remove("Traffic Volume"));
                }
            }
            //get the main map of properties
            //Remove invalid IMSI from MAP
            if (objectMap.size() > 1) {
                for (int i = 0; i < objectMap.size(); i++) {
                    final Object imsiValue = objectMap.get(i).get("IMSI");
                    if (!(imsiValue instanceof BigDecimal)) {
                        objectMap.remove(i);
                    }
                }
            }

            final Map<String, Object> mainMap = objectMap.get(0);

            final ResultSetMappingInfo resultSetMappingInfo = (ResultSetMappingInfo) classTemplate
                    .getAnnotation(ResultSetMappingInfo.class);
            if (resultSetMappingInfo != null) {
                final FieldMappingInfo[] mappingInfos = resultSetMappingInfo.fieldMappings();
                //temp map
                Map<String, Object> groupedMap = new LinkedHashMap<String, Object>();
                for (final FieldMappingInfo mappingInfo : mappingInfos) {
                    //skip the traffic mix and traffic volume FieldMappingInfo
                    if (mappingInfo.fieldName().equals("Traffic Type")
                            || mappingInfo.fieldName().equals("Traffic Volume")) {
                        continue;
                    }
                    if (groupedPropertiesMap.containsKey(mappingInfo.groupId())) {
                        groupedMap = groupedPropertiesMap.get(mappingInfo.groupId());
                    } else {
                        groupedMap = new LinkedHashMap<String, Object>();
                        groupedPropertiesMap.put(mappingInfo.groupId(), groupedMap);
                    }

                    //get the value from the map
                    final Object objectValue = mainMap.get(mappingInfo.fieldName());

                    if (objectValue == null || objectValue.equals("") || objectValue.equals("null")) {
                        groupedMap.put(mappingInfo.fieldName(), mappingInfo.nullValueReplacement());
                    } else {
                        groupedMap.put(mappingInfo.fieldName(), objectValue);
                    }

                }
                //finally add the traffic mix map
                groupedPropertiesMap.put("Application Traffic Mix", trafficMixMap);
                //now check for Application Performance
                final Map<String, Object> appPerformanceMap = groupedPropertiesMap.get("Application Performance");
                final String[] keys = appPerformanceMap.keySet().toArray(new String[appPerformanceMap.size()]);
                for (final String key : keys) {
                    final Object value = appPerformanceMap.get(key);
                    if (value == null) {
                        appPerformanceMap.remove(key);
                    }
                }

            }
        }

        //store the function list
        return new DetailsObjectWrapper(groupedPropertiesMap);
    }

    class DetailsObjectWrapper {
        Map<String, Map<String, Object>> details;

        DetailsObjectWrapper(final Map<String, Map<String, Object>> details) {
            this.details = details;
        }

        public Map<String, Map<String, Object>> getDetails() {
            return details;
        }

        public void setDetails(final Map<String, Map<String, Object>> details) {
            this.details = details;
        }
    }
}
