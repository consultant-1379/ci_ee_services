/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.sbr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.query.resultsettransformers.ResultSetTransformer;
import com.ericsson.eniq.events.server.utils.json.JSONUtils;
import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;
import com.ericsson.eniq.events.ui.shared.annotations.ResultSetMappingInfo;

/**
 * JSON transformer with Jackson
 * 
 * @author ehaoswa
 * @since 2012
 */
public abstract class AbstractJsonResultSetTransformer<T, V> implements ResultSetTransformer<String> {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String timestampFrom;

    private final String timestampTo;

    private final String tzOffset;

    private final IObjectmapProcessor<T> mapProcessor;

    private String drillType;

    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private final static String regex = "(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}).(\\d{3}|\\d{1}|\\d{2}|\\d{4})";

    final TimeZone utc = TimeZone.getTimeZone("UTC");

    private Date date;

    @SuppressWarnings("rawtypes")
    private final Class classTemplate;

    public AbstractJsonResultSetTransformer(final String timestampFrom, final String timestampTo,
            final String tzOffset, @SuppressWarnings("rawtypes")
            final Class classTemplate, final IObjectmapProcessor<T> mapProcessor, final Object... params) {
        this.timestampFrom = timestampFrom;
        this.timestampTo = timestampTo;
        this.tzOffset = tzOffset;
        this.classTemplate = classTemplate;
        this.mapProcessor = mapProcessor;
        if (params.length > 0) {
            this.drillType = (String) params[0];
        }
    }

    @Override
    public String transform(final List<ResultSet> results) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String transform(final ResultSet resultSet) throws SQLException {
        String serialisedResult = "";
        // map the result set to Hashmap representations for Json Processor
        final T mappedResults = mapResultSetToObjects(resultSet, classTemplate);
        // process the map if processor available
        if (mapProcessor != null) {
            serialisedResult = serialize(tzOffset, mapProcessor.processObjectMap(mappedResults));
        } else {
            serialisedResult = serialize(tzOffset, mappedResults);
        }
        return serialisedResult;
    }

    /**
     * Initialise resulting object;
     * @return
     */
    protected abstract T initMappedResults();

    /**
     * Add mapped row to the resulting object
     * @param mappedResults
     * @param mappedRow
     */
    protected abstract void addToMappedResults(final T mappedResults, final V mappedRow);

    /**
     * Convert mapped row to any other type
     * @param mappedObject
     * @param mappedResults
     * @param mappingInfos
     * @return converted row
     */
    protected abstract V convertMapObject(final Map<String, Object> mappedObject, final T mappedResults,
            final FieldMappingInfo[] mappingInfos);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T mapResultSetToObjects(final ResultSet resultSet, final Class classDefinition) throws SQLException {

        final ResultSetMappingInfo resultSetMappingInfo = (ResultSetMappingInfo) classDefinition
                .getAnnotation(ResultSetMappingInfo.class);
        if (resultSetMappingInfo != null) {
            final FieldMappingInfo[] mappingInfos = resultSetMappingInfo.fieldMappings();
            try {
                return mapResultSetToObjects(resultSet, mappingInfos);
            } catch (final Exception e) {
                ServicesLogger.error(getClass().getName(), "mapResultSetToObjects",
                        "Exception mapping resultSet to object", e);
                // wrap in SQL exception to avoid changing all implementations
                // of services derived from GenericService
                throw new SQLException("Exception mapping resultSet to object", e);
            }
        }
        return initMappedResults();
    }

    protected T mapResultSetToObjects(final ResultSet resultSet, final FieldMappingInfo[] mappingInfos)
            throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            ParseException {
        final T mappedResults = initMappedResults();
        while (resultSet.next()) {
            final Map<String, Object> mappedObject = transformRowToMapObjects(resultSet, mappingInfos);
            final V convertedObject = convertMapObject(mappedObject, mappedResults, mappingInfos);
            addToMappedResults(mappedResults, convertedObject);
        }
        return mappedResults;
    }

    protected Map<String, Object> transformRowToMapObjects(final ResultSet resultSet,
            final FieldMappingInfo[] mappingInfos) throws SQLException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, ParseException {
        final Map<String, Object> objectMap = new LinkedHashMap<String, Object>(mappingInfos.length);
        // iterate the mappingInfos and extract the values
        for (final FieldMappingInfo fieldMappingInfo : mappingInfos) {
            Object resultSetValue = null;
            if (fieldMappingInfo.columnIndex() > 0) {
                resultSetValue = resultSet.getObject(fieldMappingInfo.columnIndex());
            } else if (StringUtils.isNotBlank(fieldMappingInfo.columnName())) {
                resultSetValue = resultSet.getObject(fieldMappingInfo.columnName());
            }
            if (fieldMappingInfo.isLookupEnum() && resultSetValue != null) {
                final Class<?> enumType = fieldMappingInfo.enumType();
                final Method lookupMethod = enumType.getMethod(fieldMappingInfo.lookupMethod(),
                        new Class[] { Integer.TYPE });
                final int intValue = Integer.parseInt(resultSetValue.toString());
                final Enum<?> enumObj = (Enum<?>) lookupMethod.invoke(null, intValue);
                resultSetValue = enumObj;
            } else if (fieldMappingInfo.isLookupEnum() && StringUtils.isNotBlank(fieldMappingInfo.defaultEnumValue())) {
                final Class<?> enumType = fieldMappingInfo.enumType();
                final Method lookupMethod = enumType.getMethod(fieldMappingInfo.lookupMethod(),
                        new Class[] { String.class });
                final Enum<?> enumObj = (Enum<?>) lookupMethod.invoke(null, fieldMappingInfo.defaultEnumValue());
                resultSetValue = enumObj;

            }

            final Object mapValue = convertValue(resultSetValue, fieldMappingInfo);
            if (null == mapValue || mapValue.equals("") || mapValue.equals("null")) {
                objectMap.put(fieldMappingInfo.fieldName(), fieldMappingInfo.nullValueReplacement());
            } else {
                objectMap.put(fieldMappingInfo.fieldName(), mapValue);
            }

        }

        return objectMap;
    }

    private Object convertValue(Object resultSetValue, final FieldMappingInfo fieldMappingInfo) throws ParseException {
        if (resultSetValue != null) {
            if (StringUtils.isNotBlank(resultSetValue.toString()) && resultSetValue.toString().matches(regex)) {
                formatter.setTimeZone(utc);
                date = formatter.parse(resultSetValue.toString());
                resultSetValue = date.getTime();
            }
        }
        if (fieldMappingInfo.isTimeStamp()) {
            return String.valueOf(resultSetValue);
        } else if (fieldMappingInfo.outputAsString()) {
            if (null == resultSetValue) {
                return "";
            }
            return String.valueOf(resultSetValue);
        } else {
            return resultSetValue;
        }

    }

    @SuppressWarnings("hiding")
    private String serialize(final String tzOffset, final Object results) {
        try {
            final String payload = mapper.writeValueAsString(results);
            return JSONUtils.JSONSuccessResult(timestampFrom, timestampTo, tzOffset, payload);
        } catch (final Exception e) {
            ServicesLogger.error(getClass().getName(), "serialize", "Exception Serialising object", e);
            return JSONUtils.createJSONErrorResult("Failed to serialize json");
        }
    }
}