package com.ericsson.eniq.events.server.utils.sbr;

import java.util.HashMap;
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
public class MapKpiProcessor implements IObjectmapProcessor<List<Map<String, Object>>> {

    @SuppressWarnings("rawtypes")
    private final Class classTemplate;

    private final boolean isNetwork;

    @SuppressWarnings({ "rawtypes" })
    public MapKpiProcessor(final Class classTemplate, final boolean isNetwork) {
        this.classTemplate = classTemplate;
        this.isNetwork = isNetwork;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object processObjectMap(final List<Map<String, Object>> objectMap) {
        //now build up final object
        final Map<String, Object> transformedMap = new LinkedHashMap<String, Object>();
        if (objectMap.size() > 0) {
            String mainId = "";
            for (final Map<String, Object> kpiMap : objectMap) {
                //get the result set mapping information
                final ResultSetMappingInfo resultSetMappingInfo = (ResultSetMappingInfo) classTemplate
                        .getAnnotation(ResultSetMappingInfo.class);
                if (resultSetMappingInfo != null) {
                    final FieldMappingInfo[] mappingInfos = resultSetMappingInfo.fieldMappings();
                    //temp map for the kpi values
                    final Map<String, Object> kpiGroupedMap = new HashMap<String, Object>();
                    //iterate over th mapping information
                    for (final FieldMappingInfo mappingInfo : mappingInfos) {
                        if (!mappingInfo.groupId().equals("")) {
                            //remove from original map as we go and put in kpiMap
                            kpiGroupedMap.put(mappingInfo.fieldName(), kpiMap.remove(mappingInfo.fieldName()));
                        }
                    }
                    if (isNetwork) {
                        //remove the cellId
                        mainId = kpiMap.remove("rncName").toString();
                    } else {
                        //TODO should be cellId
                        //remove the cellId
                        mainId = kpiMap.remove("cellName").toString();
                    }

                    //take existing map and add the kpi map 
                    kpiMap.put("kpis", kpiGroupedMap);
                    //add the cellid to final map along with the kpi map
                    transformedMap.put(mainId, kpiMap);
                }
            }
        }
        return transformedMap;
    }
}
