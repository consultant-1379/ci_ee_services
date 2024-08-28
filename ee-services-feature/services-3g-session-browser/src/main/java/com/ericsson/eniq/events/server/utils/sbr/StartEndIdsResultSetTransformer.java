package com.ericsson.eniq.events.server.utils.sbr;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;

/**
 * Selects proper <i>startCellId</i>,<i>startRncId</i>, <i>endCellId</i>,
 * <i>endRncId</i> when there are multiple entries with the same
 * <i>eventTime</i>. It selects a start id for the lowest <i>RRC_CONN_START</i>
 * time stamp and an end id for the highest <i>RRC_CONN_END</i> time stamp.
 * 
 * The assumption has been made that the iterated <i>resultSet<i/> is sorted by
 * <i>eventTime</i>.
 * 
 * @author ejedmar
 */
public class StartEndIdsResultSetTransformer extends JsonResultSetTransformer {

    private static final String RRC_CONN_END = "RRC_CONN_END";

    private static final String RRC_CONN_START = "RRC_CONN_START";

    private static final String[] TEMPORARY_ATTRIBUTES = new String[] { RRC_CONN_START, RRC_CONN_END };

    @SuppressWarnings("rawtypes")
    public StartEndIdsResultSetTransformer(final String timestampFrom, final String timestampTo, final String tzOffset,
            final Class classTemplate, final IObjectmapProcessor<List<Map<String, Object>>> mapProcessor,
            final Object... params) {
        super(timestampFrom, timestampTo, tzOffset, classTemplate, mapProcessor, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ericsson.eniq.events.server.utils.sbr.JsonResultSetTransformer#
     * transformToMapObjects(java.sql.ResultSet, java.util.List,
     * com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo[])
     */
    @Override
    protected List<Map<String, Object>> mapResultSetToObjects(final ResultSet resultSet,
            final FieldMappingInfo[] mappingInfos) throws SQLException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, ParseException {
        final List<Map<String, Object>> mappedResults = initMappedResults();
        Map<String, Object> enrichedPreviousRow = null;
        while (resultSet.next()) {
            final Map<String, Object> enrichedCurrentRow = super.transformRowToMapObjects(resultSet,
                    extendFieldsMapping(mappingInfos));
            if (isContinuation(enrichedPreviousRow, enrichedCurrentRow)) {
                recalculate(getLastRow(mappedResults), enrichedCurrentRow, enrichedPreviousRow);
            } else {
                enrichedPreviousRow = new HashMap<String, Object>(enrichedCurrentRow);
                final Map<String, Object> convertedObject = convertMapObject(purge(enrichedCurrentRow), mappedResults,
                        mappingInfos);
                addToMappedResults(mappedResults, convertedObject);
            }
        }
        return mappedResults;
    }

    private boolean isContinuation(final Map<String, Object> enrichedPreviousRow,
            final Map<String, Object> enrichedCurrentRow) {
        return enrichedPreviousRow != null
                && enrichedCurrentRow.get("eventTime").equals(enrichedPreviousRow.get("eventTime"));
    }

    private Map<String, Object> purge(final Map<String, Object> currentRow) {
        for (final String property : TEMPORARY_ATTRIBUTES) {
            currentRow.remove(property);
        }
        return currentRow;
    }

    private Map<String, Object> getLastRow(final List<Map<String, Object>> mappedResults) {
        return mappedResults.get(mappedResults.size() - 1);
    }

    private void recalculate(final Map<String, Object> toBeUpdatedRow, final Map<String, Object> enrichedCurrentRow,
            final Map<String, Object> enrichedPreviousRow) throws ParseException, SQLException {
        final Long previousStartTime = (Long) enrichedPreviousRow.get(RRC_CONN_START);
        final Long currentStartTime = (Long) enrichedCurrentRow.get(RRC_CONN_START);

        final Long previousEndTime = (Long) enrichedPreviousRow.get(RRC_CONN_END);
        final Long currentEndTime = (Long) enrichedCurrentRow.get(RRC_CONN_END);

        if (null != currentEndTime && null != previousEndTime) {
            if (currentStartTime.compareTo(previousStartTime) < 0) {
                toBeUpdatedRow.put("startCellId", enrichedCurrentRow.get("startCellId"));
                toBeUpdatedRow.put("startCId", enrichedCurrentRow.get("startCId"));
                toBeUpdatedRow.put("startRncId", enrichedCurrentRow.get("startRncId"));
                enrichedPreviousRow.put(RRC_CONN_START, enrichedCurrentRow.get(RRC_CONN_START));
            }

            if (currentEndTime.compareTo(previousEndTime) > 0) {
                toBeUpdatedRow.put("endCellId", enrichedCurrentRow.get("endCellId"));
                toBeUpdatedRow.put("endCId", enrichedCurrentRow.get("endCId"));
                toBeUpdatedRow.put("endRncId", enrichedCurrentRow.get("endRncId"));
                enrichedPreviousRow.put(RRC_CONN_END, enrichedCurrentRow.get(RRC_CONN_END));
            }
        }

    }

    private FieldMappingInfo[] extendFieldsMapping(final FieldMappingInfo[] mappingInfos) {
        final ArrayList<FieldMappingInfo> extendedFieldMapping = new ArrayList<FieldMappingInfo>(
                Arrays.asList(mappingInfos));
        extendedFieldMapping.add(new AbstractFieldMappingInfo() {
            @Override
            public String columnName() {
                return RRC_CONN_START;
            }

            @Override
            public String fieldName() {
                return RRC_CONN_START;
            }

            @Override
            public boolean isBoolean() {

                return false;
            }

            @Override
            public boolean isHidden() {

                return false;
            }
        });
        extendedFieldMapping.add(new AbstractFieldMappingInfo() {
            @Override
            public String columnName() {
                return RRC_CONN_END;
            }

            @Override
            public String fieldName() {
                return RRC_CONN_END;
            }

            @Override
            public boolean isBoolean() {

                return false;
            }

            @Override
            public boolean isHidden() {

                return false;
            }
        });

        return extendedFieldMapping.toArray(new FieldMappingInfo[] {});
    }

    @Override
    public void addToMappedResults(final List<Map<String, Object>> mappedResults, final Map<String, Object> mappedRow) {
        if (mappedRow != null) {
            mappedResults.add(mappedRow);
        }
    }
}
