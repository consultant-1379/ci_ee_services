/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.sbr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;

/**
 * JSON transformer with Jackson
 * 
 * @author ehaoswa
 * @since 2012
 */
public class JsonResultSetTransformer extends
        AbstractJsonResultSetTransformer<List<Map<String, Object>>, Map<String, Object>> {

    public JsonResultSetTransformer(final String timestampFrom, final String timestampTo, final String tzOffset,
            @SuppressWarnings("rawtypes")
            final Class classTemplate, final IObjectmapProcessor<List<Map<String, Object>>> mapProcessor,
            final Object... params) {
        super(timestampFrom, timestampTo, tzOffset, classTemplate, mapProcessor, params);
    }

    @Override
    public List<Map<String, Object>> initMappedResults() {
        return new ArrayList<Map<String, Object>>();
    }

    @Override
    public void addToMappedResults(final List<Map<String, Object>> mappedResults, final Map<String, Object> mappedRow) {
        mappedResults.add(mappedRow);
    }

    @Override
    protected Map<String, Object> convertMapObject(final Map<String, Object> mappedObject,
            final List<Map<String, Object>> mappedResults, final FieldMappingInfo[] mappingInfos) {
        return mappedObject;
    }
}