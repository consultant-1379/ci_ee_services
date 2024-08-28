package com.ericsson.eniq.events.server.utils.sbr;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;

/**
 * @author ejedmar
 */
public class MapKpiResultSetTransformer extends
        AbstractJsonResultSetTransformer<Map<String, Object>, Map<String, Object>> {

    private final boolean isNetwork;

    @SuppressWarnings({ "rawtypes" })
    public MapKpiResultSetTransformer(final String timestampFrom, final String timestampTo, final String tzOffset,
            final Class classTemplate, final IObjectmapProcessor<Map<String, Object>> mapProcessor,
            final boolean isNetwork, final Object... params) {
        super(timestampFrom, timestampTo, tzOffset, classTemplate, mapProcessor, params);
        this.isNetwork = isNetwork;
    }

    @Override
    public Map<String, Object> initMappedResults() {
        return new LinkedHashMap<String, Object>();
    }

    @Override
    protected Map<String, Object> transformRowToMapObjects(final ResultSet resultSet,
            final FieldMappingInfo[] mappingInfos) throws SQLException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, ParseException {

        final Map<String, Object> mappedRow = super.transformRowToMapObjects(resultSet, mappingInfos);
        return organiseKpis(mappingInfos, mappedRow);
    }

    private Map<String, Object> organiseKpis(final FieldMappingInfo[] mappingInfos, final Map<String, Object> mappedRow) {
        final Map<String, Object> kpiGroupedMap = new HashMap<String, Object>();
        for (final FieldMappingInfo mappingInfo : mappingInfos) {
            if (!mappingInfo.groupId().equals("")) {
                // remove from original map as we go and put in kpiMap
                kpiGroupedMap.put(mappingInfo.fieldName(), mappedRow.remove(mappingInfo.fieldName()));
            }
        }
        mappedRow.put("kpis", kpiGroupedMap);
        return mappedRow;
    }

    @Override
    public void addToMappedResults(final Map<String, Object> mappedResults, final Map<String, Object> mappedRow) {
        String mainId = "";
        if (isNetwork) {
            // remove the cellId
            mainId = mappedRow.remove("rncName").toString();
        } else {
            mainId = mappedRow.remove("cellId").toString();
        }
        mappedResults.put(mainId, mappedRow);
    }

    @Override
    protected Map<String, Object> convertMapObject(final Map<String, Object> mappedObject,
            final Map<String, Object> mappedResults, final FieldMappingInfo[] mappingInfos) {
        return mappedObject;
    }
}
