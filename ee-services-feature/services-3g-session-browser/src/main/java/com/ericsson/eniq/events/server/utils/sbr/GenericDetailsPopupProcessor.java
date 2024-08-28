package com.ericsson.eniq.events.server.utils.sbr;

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
public class GenericDetailsPopupProcessor implements IObjectmapProcessor<List<Map<String, Object>>> {

    @SuppressWarnings("rawtypes")
    private final Class classTemplate;

    @SuppressWarnings({ "unused" })
    public GenericDetailsPopupProcessor(@SuppressWarnings("rawtypes")
    final Class classTemplate, final String tzOffset) {
        this.classTemplate = classTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object processObjectMap(final List<Map<String, Object>> objectMap) {
        //resulting grouped property map
        final Map<String, Map<String, Object>> groupedPropertiesMap = new LinkedHashMap<String, Map<String, Object>>();
        if (objectMap.size() > 0) {
            //get the main map of properties
            final Map<String, Object> mainMap = objectMap.get(0);
            final ResultSetMappingInfo resultSetMappingInfo = (ResultSetMappingInfo) classTemplate
                    .getAnnotation(ResultSetMappingInfo.class);
            if (resultSetMappingInfo != null) {
                final FieldMappingInfo[] mappingInfos = resultSetMappingInfo.fieldMappings();
                //temp map
                Map<String, Object> groupedMap = new LinkedHashMap<String, Object>();
                for (final FieldMappingInfo mappingInfo : mappingInfos) {
                    if (groupedPropertiesMap.containsKey(mappingInfo.groupId())) {
                        groupedMap = groupedPropertiesMap.get(mappingInfo.groupId());
                    } else {
                        groupedMap = new LinkedHashMap<String, Object>();
                        groupedPropertiesMap.put(mappingInfo.groupId(), groupedMap);
                    }
                    //get the value from the map
                    final Object objectValue = mainMap.get(mappingInfo.fieldName());
                    if (objectValue == null) {
                        groupedMap.put(mappingInfo.fieldName(), mappingInfo.nullValueReplacement());
                    } else if (!mappingInfo.connectionPropertiesKey().isEmpty()) {
                        final String key = mappingInfo.connectionPropertiesKey();
                        final String codedValueStr = objectValue.toString();
                        final int codedValue = Integer.parseInt(codedValueStr);
                        final String decodedValue = ConnectionPropertiesDecoder.decode(codedValue, key);
                        groupedMap.put(mappingInfo.fieldName(), decodedValue);
                    } else {
                        groupedMap.put(mappingInfo.fieldName(), objectValue);
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
