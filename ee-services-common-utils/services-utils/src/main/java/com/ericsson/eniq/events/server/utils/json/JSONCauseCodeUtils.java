/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.json;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;

/**
 * Utilities for transforming results for Cause Code queries to expected JSON
 * 
 * @author eriwals
 * @since 2011
 *
 */

public final class JSONCauseCodeUtils {

    private final static int MATCHER_POSITION = 4;

    private final static int MATCHER_OFFSET = 2;

    /** 
     * Generate the JSON output format for grid, which includes extraction of the Sub Cause
     * Code Help Text, based on Cause Code ID.
     * Added for TR HN63122.
     * 
     * @param rs - input result set
     * @param timestampFrom - date (and time) from in milliseconds
     * @param timestampTo - date(and time) to in milliseconds
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONGridDataObjectForCauseCode(final ResultSet rs, final String timestampFrom,
            final String timestampTo, final String timeColumn, final String tzOffset) throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(JSONUtils.getJSONSuccessHeader()).append(ApplicationConstants.COMMA)
                .append(JSONUtils.getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset))
                .append(ApplicationConstants.COMMA).append(toJSONGridDataArrayForCauseCode(rs, timeColumn, tzOffset))
                .append(CLOSE_BRACE).toString();
    }

    /**
     * Modified variant of toJSONGridDataArray, which includes extraction of the Sub Cause Code
     * Help Text, based on Cause Code ID.
     * Added for TR HN63122.
     * 
     * Convert ResultSet to JSON data grid array. The encoding for grid data is
     * as follows; "data":[ {"col1":"val11","col2":"val12",..,"colN":"val1N"},
     * {"col1":"val21","col2":"val22",..,"colN":"val2N"}, . .
     * {"col1":"valM1","col2":"valM2",..,"colN":"valMN"}]
     * The column identifier 'colN' is used as an index in the presentation
     * layer. The actual displayed column headers are encoded in UI meta data
     * which is loaded at application startup.
     * 
     * Note: method is responsible for closing the result set upon completion.
     *
     * @param rs input result set
     * @return JSON data array representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    private static String toJSONGridDataArrayForCauseCode(final ResultSet rs, final String timeColumn,
            final String tzOffset) throws SQLException {

        int subCauseCodeHelpColumnIndex;
        int causeCodeColumnIndex;

        try {
            subCauseCodeHelpColumnIndex = rs.findColumn(ApplicationConstants.SCC_HELP_SQL_NAME);
        } catch (final SQLException e) {
            subCauseCodeHelpColumnIndex = -1;
        }

        try {
            causeCodeColumnIndex = rs.findColumn(ApplicationConstants.CC_SQL_NAME);
        } catch (final SQLException e) {
            causeCodeColumnIndex = -1;
        }

        final StringBuilder result = new StringBuilder(DATA_ARRAY_BEGIN);
        if (rs.next()) {
            int i;
            final ResultSetMetaData resultSetMetaData = rs.getMetaData();
            final int numberColumns = resultSetMetaData.getColumnCount() + 1;

            result.append(OPEN_BRACE);
            do {
                for (i = 1; i < numberColumns; i++) {

                    // if current column is subcause code help column
                    if (i == subCauseCodeHelpColumnIndex) {
                        final String causeCodeID = rs.getString(causeCodeColumnIndex);
                        final String causeCodeHelpText = rs.getString(subCauseCodeHelpColumnIndex);

                        final Matcher matcher;
                        final Pattern pattern;

                        final String value;

                        pattern = Pattern.compile("((.*)(" + HASH + HASH + causeCodeID + HASH + HASH + ")[" + HASH
                                + HASH + "\\d*" + HASH + HASH + "]*(" + "\\" + ARRAY_START + "\\" + PIPE + ".*?" + "\\"
                                + PIPE + "\\" + ARRAY_END + ")(.*))");
                        matcher = pattern.matcher(causeCodeHelpText);
                        if (matcher.matches()) {
                            value = matcher.group(MATCHER_POSITION).substring(MATCHER_OFFSET,
                                    matcher.group(MATCHER_POSITION).length() - MATCHER_OFFSET);
                        } else {
                            value = "";
                        }

                        // append help text
                        result.append(JSONUtils.formatColumnIndex(i)).append(COLON)
                                .append(JSONUtils.formatValue(value));
                    } else {
                        if (timeColumn != null && i == Integer.parseInt(timeColumn) && tzOffset != null) {
                            result.append(JSONUtils.formatColumnIndex(i))
                                    .append(COLON)
                                    .append(JSONUtils.formatValue(DateTimeUtils.getLocalTime(rs.getString(i), tzOffset,
                                            ApplicationConstants.RECEIVED_DATE_FORMAT)));
                        } else {
                            result.append(JSONUtils.formatColumnIndex(i)).append(COLON)
                                    .append(JSONUtils.formatValue(rs.getString(i)));
                        }
                    }
                    if (i + 1 != numberColumns) {
                        result.append(COMMA);
                    }
                }
            } while (rs.next() && result.append(CLOSE_BRACE + COMMA + OPEN_BRACE) != null);
            result.append(CLOSE_BRACE);
        }

        result.append(ARRAY_END);

        return result.toString();

    }

    // this is a singleton.
    /**
     * The Constructor.
     */
    private JSONCauseCodeUtils() {
    }

}
