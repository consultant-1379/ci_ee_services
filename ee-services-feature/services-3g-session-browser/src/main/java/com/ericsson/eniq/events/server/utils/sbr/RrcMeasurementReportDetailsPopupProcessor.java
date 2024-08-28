package com.ericsson.eniq.events.server.utils.sbr;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;
import com.ericsson.eniq.events.ui.shared.annotations.ResultSetMappingInfo;

/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 22/02/12
 * Time: 08:31
 * To change this template use File | Settings | File Templates.
 */
public class RrcMeasurementReportDetailsPopupProcessor implements IObjectmapProcessor<List<Map<String, Object>>> {

    @SuppressWarnings("rawtypes")
    private final Class classTemplate;

    @SuppressWarnings({ "unused" })
    public RrcMeasurementReportDetailsPopupProcessor(@SuppressWarnings("rawtypes")
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
                boolean measflag = false;
                boolean eventflag = false;
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
                    Object objectValue = mainMap.get(mappingInfo.fieldName());
                    if (objectValue == null) {
                        objectValue = mappingInfo.nullValueReplacement();
                    }

                    if (!mappingInfo.canHaveMultiples()) {
                        groupedMap.put(mappingInfo.fieldName(), objectValue);
                    }

                    //2 if statements below is ugly hack, but have to be here.
                    if (mappingInfo.canHaveMultiples() && !measflag) {
                        for (int i = 0; i < objectMap.size(); i++) {
                            final Map<String, Object> map = objectMap.get(i);

                            //create order for measure results.
                            if (!map.get("MEAS_RSLTS_ORD_NO").equals("-")) {
                                final int measOrderNum = (Integer) map.get("MEAS_RSLTS_ORD_NO");
                                if (!map.get("BSIC").equals("-")) {

                                    groupedMap.put("BSIC" + " " + (measOrderNum + 1), map.get("BSIC"));
                                } else {
                                    if (!map.get("Scrambling Code").equals("-")
                                            && (!map.get("Ec/No").equals("-") || !map.get("RSCP").equals("-"))) {
                                        groupedMap.put("Scrambling Code" + " " + (measOrderNum + 1),
                                                map.get("Scrambling Code"));
                                    }
                                    if (!map.get("Ec/No").equals("-")) {

                                        groupedMap.put("Ec/No" + " " + (measOrderNum + 1) + " (dB)", map.get("Ec/No"));
                                    }
                                    if (!map.get("RSCP").equals("-")) {
                                        groupedMap.put("RSCP" + " " + (measOrderNum + 1) + " (dBm)", map.get("RSCP"));
                                    }
                                }
                            }

                        }
                        measflag = true;
                    }

                    if (mappingInfo.canHaveMultiples() && !eventflag) {
                        final Map<String, Object> groupedMap2 = new LinkedHashMap<String, Object>();
                        final Map<String, Object> groupedMap3 = new TreeMap<String, Object>();
                        for (int i = 0; i < objectMap.size(); i++) {
                            final Map<String, Object> map = objectMap.get(i);
                            if (!map.get("Reporting Event").equals("-")) {
                                groupedMap2.put("Reporting Event", map.get("Reporting Event"));
                                groupedMap2.put("Reporting Event Description", map.get("Reporting Event Description"));
                                if (!map.get("EVENT_RSLTS_ORD_NO").equals("-")) {
                                    final int erOrdNum = (Integer) map.get("EVENT_RSLTS_ORD_NO");
                                    if (!map.get("Event Results Scrambling Code").equals("-")) {
                                        groupedMap3.put("Event Results Scrambling Code" + " " + (erOrdNum + 1),
                                                map.get("Event Results Scrambling Code"));
                                    }
                                }
                            }
                        }
                        groupedMap2.putAll(groupedMap3);
                        groupedPropertiesMap.put("Event Results", groupedMap2);
                        eventflag = true;
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
