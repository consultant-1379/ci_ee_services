/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.json;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.*;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.*;
import static java.sql.Types.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.utils.Calculator;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;

/**
 * This is a JSON utility class used to construct valid JSON for the UI.<br>
 * The UI expects JSON in a certain format and the methods call will <br>
 * produce the desired result.
 *
 * @author edeccox
 * @author ehaoswa
 * @author estepdu
 * @author eavidat
 *
 * @since Mar 2010
 */

public final class JSONUtils {

    private static final int HOURS_IN_A_DAY = 24;

    private static final DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());

    private static final String[] weekdays = dfs.getWeekdays();

    private static final int Y_AXIS_NUM_INTERVALS = 20;

    private static boolean sample = false;

    private static final String RESULT_SET_MAY_NOT_BE_NULL = "ResultSet may not be null";

    private static final String JSONUTILS = "JSONUtils";

    /**
     * Generate the JSON output format for grid.
     *
     * @param rs            - input result set
     * @param timestampFrom - date (and time) from in milliseconds
     * @param timestampTo   - date(and time) to in milliseconds
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONGridDataObject(final ResultSet rs, final String timestampFrom, final String timestampTo,
            final String timeColumn, final String tzOffset) throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONGridDataObject(results, timestampFrom, timestampTo, timeColumn, tzOffset);
    }

    /**
     * Generate the JSON output format for grid, based on the list of ResultSets supplied
     *
     * @param results       - the list of ResultSets to convert to a json grid
     * @param timestampFrom - date (and time) from in milliseconds
     * @param timestampTo   - date(and time) to in milliseconds
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONGridDataObject(final List<ResultSet> results, final String timestampFrom,
            final String timestampTo, final String timeColumn, final String tzOffset) throws SQLException {
        List<Integer> timeColumnIndexes = null;
        if (timeColumn != null) {
            try {
                final int columnIndex = Integer.parseInt(timeColumn);
                timeColumnIndexes = new ArrayList<Integer>();
                timeColumnIndexes.add(columnIndex);
            } catch (final NumberFormatException numEx) {
                ServicesLogger.detailed(Level.WARNING, JSONUTILS, "toJSONGridDataObject", numEx);
            }
        }
        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArray(timeColumnIndexes, tzOffset, results)).append(CLOSE_BRACE).toString();
    }

    /**
     * Generate the JSON output format for grid.
     *
     * @param rs                - input result set
     * @param timestampFrom     - date (and time) from in milliseconds
     * @param timestampTo       - date(and time) to in milliseconds
     * @param timeColumnIndexes list of column index to be converted to local time
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONGridDataObject(final ResultSet rs, final String timestampFrom, final String timestampTo,
            final List<Integer> timeColumnIndexes, final String tzOffset) throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONGridDataObject(results, timestampFrom, timestampTo, timeColumnIndexes, tzOffset);
    }

    /**
     * Generate the JSON output format for grid, based on the list of ResultSets supplied
     *
     * @param results       - the list of ResultSets to convert to a json grid
     * @param timestampFrom - date (and time) from in milliseconds
     * @param timestampTo   - date(and time) to in milliseconds
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONGridDataObject(final List<ResultSet> results, final String timestampFrom,
            final String timestampTo, final List<Integer> timeColumnIndexes, final String tzOffset) throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArray(timeColumnIndexes, tzOffset, results)).append(CLOSE_BRACE).toString();
    }

    /**
     * Generate the JSON output format for grid, based on ResultSet supplied
     *
     * @param rs                resultset data to be converted to JSON
     * @param timestampFrom     date (and time) from in milliseconds
     * @param timestampTo       date(and time) to in milliseconds
     * @param timeColumnIndexes list of columns to be converted to local time
     * @param tzOffset          time zone in URL request
     * @param timeColumnIndexes list of columns to be converted to local time
     * @return JSON format representation
     * @throws SQLException
     */
    public static String toJSONGridDataObjectAppendRows(final ResultSet rs, final String timestampFrom,
            final String timestampTo, final List<Integer> timeColumnIndexes, final String tzOffset) throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONGridDataObjectAppendRows(results, timestampFrom, timestampTo, timeColumnIndexes, tzOffset);
    }

    /**
     * Generate the JSON output format for grid appending rows of data, based on ResultSet supplied
     *
     * @param timestampFrom     date (and time) from in milliseconds
     * @param timestampTo       date(and time) to in milliseconds
     * @param timeColumnIndexes list of columns to be converted to local time
     * @param tzOffset          time zone in URL request
     * @param timeColumnIndexes list of columns to be converted to local time
     * @return JSON format representation
     * @throws SQLException
     */
    public static String toJSONGridDataObjectAppendRows(final List<ResultSet> results, final String timestampFrom,
            final String timestampTo, final List<Integer> timeColumnIndexes, final String tzOffset) throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArrayAppendRows(timeColumnIndexes, tzOffset, results)).append(CLOSE_BRACE)
                .toString();
    }

    /**
     * Get the JSON Grid time range parameters formatted into JSON, this will look something like:
     * "dataTimeFrom":"1277216700000","dataTimeTo":"1277216760000","timeZone":"null"
     * The parameters timestampFrom and timestampTo must be in milliseconds (since 1/1/1970).
     *
     * @param timestampFrom the timestamp from
     * @param timestampTo   the timestamp to
     * @return the JSON grid time range parameters
     */
    public static String getJSONGridTimeRangeParameters(final String timestampFrom, final String timestampTo,
            final String tzOffset) {
        final String timestampFromInMilliseconds = DateTimeUtils.getTimeInMillisecondsUTC(timestampFrom);
        final String timestampToInMilliseconds = DateTimeUtils.getTimeInMillisecondsUTC(timestampTo);

        final StringBuilder result = new StringBuilder();
        final String headerTzOffset = ((tzOffset != null) && !tzOffset.startsWith("-")) ? "+" + tzOffset.substring(1)
                : tzOffset;
        return result.append(QUOTE).append(DATA_TIME_FROM).append(QUOTE).append(COLON).append(QUOTE)
                .append(timestampFromInMilliseconds).append(QUOTE).append(COMMA).append(QUOTE).append(DATA_TIME_TO)
                .append(QUOTE).append(COLON).append(QUOTE).append(timestampToInMilliseconds).append(QUOTE)
                .append(COMMA).append(QUOTE).append(TIMEZONE).append(QUOTE).append(COLON).append(QUOTE)
                .append(headerTzOffset).append(QUOTE).toString();
    }

    /**
     * Generate the JSON output format for  the Groups Most Frequent Signalling chart.
     * This chart needs special attention because it returns values that are not shown in the
     * chart (for toggle to grid only) - hence the max/min calculations are different.
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toGroupsMostFreqSignalJSONChartDataObject(final ResultSet rs, final String timestampFrom,
            final String dateTimeTo, final String timeColumn, final String tzOffset) throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toGroupsMostFreqSignalJSONChartDataObject(results, timestampFrom, dateTimeTo, timeColumn, tzOffset);
    }

    /**
     * Generate the JSON output format for  the Groups Most Frequent Signalling chart based on a list of ResultSets.
     * This chart needs special attention because it returns values that are not shown in the
     * chart (for toggle to grid only) - hence the max/min calculations are different.
     *
     * @param results - list of input result sets
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toGroupsMostFreqSignalJSONChartDataObject(final List<ResultSet> results,
            final String timestampFrom, final String timestampTo, final String timeColumn, final String tzOffset)
            throws SQLException {

        final List<Integer> timeColumnIndexes = getTimeColumnIndexes(timeColumn);
        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getGroupsMostFreqSignalJSONChartHeader(results)).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArray(timeColumnIndexes, tzOffset, results)).append(CLOSE_BRACE).toString();
    }

    /**
     * Generate the JSON output format for chart.
     *
     * @param rs          - input result set
     * @param xaxis       - the x axis position
     * @param secondYaxis - the second yaxis position
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONChartDataObject(final ResultSet rs, final String xaxis, final String secondYaxis,
            final String timestampFrom, final String timestampTo, final String timeColumn, final String tzOffset)
            throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONChartDataObject(results, xaxis, secondYaxis, timestampFrom, timestampTo, timeColumn, tzOffset);
    }

    /**
     * Generate the JSON output format for chart based on a list of ResultSets.
     *
     * @param results     - list of input result sets
     * @param xaxis       - the x axis position
     * @param secondYaxis - the second yaxis position
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONChartDataObject(final List<ResultSet> results, final String xaxis,
            final String secondYaxis, final String timestampFrom, final String timestampTo, final String timeColumn,
            final String tzOffset) throws SQLException {
        final List<Integer> timeColumnIndexes = getTimeColumnIndexes(timeColumn);

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONChartHeader(xaxis, secondYaxis, results)).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArray(timeColumnIndexes, tzOffset, results)).append(CLOSE_BRACE).toString();
    }

    /**
     * Generate the JSON output format for chart.
     *
     * @param rs          - input result set
     * @param xaxis       - the x axis position
     * @param secondYaxis - the second yaxis position
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONChartDataObjectWithAppendedRows(final ResultSet rs, final String xaxis,
            final String secondYaxis, final String timestampFrom, final String timestampTo, final String timeColumn,
            final String tzOffset) throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONChartDataObjectWithAppendedRows(results, xaxis, secondYaxis, timestampFrom, timestampTo,
                timeColumn, tzOffset);
    }

    /**
     * Generate the JSON output format for chart based on a list of ResultSets.
     *
     * @param results     - list of input result sets
     * @param xaxis       - the x axis position
     * @param secondYaxis - the second yaxis position
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONChartDataObjectWithAppendedRows(final List<ResultSet> results, final String xaxis,
            final String secondYaxis, final String timestampFrom, final String timestampTo, final String timeColumn,
            final String tzOffset) throws SQLException {
        final List<Integer> timeColumnIndexes = getTimeColumnIndexes(timeColumn);

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONChartHeaderWithAppendedRows(xaxis, secondYaxis, results)).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArrayAppendRows(timeColumnIndexes, tzOffset, results)).append(CLOSE_BRACE)
                .toString();
    }

    private static List<Integer> getTimeColumnIndexes(final String timeColumn) {
        List<Integer> timeColumnIndexes = null;
        if (timeColumn != null) {
            try {
                final int columnIndex = Integer.parseInt(timeColumn);
                timeColumnIndexes = new ArrayList<Integer>();
                timeColumnIndexes.add(columnIndex);
            } catch (final NumberFormatException numEx) {
                ServicesLogger.detailed(Level.WARNING, JSONUTILS, "toJSONChartDataObject", numEx);
            }
        }
        return timeColumnIndexes;
    }

    /**
     * Convert a successful ResultSet to the following JDON format:
     * {"success": "true",
     * "errorDescription": "",
     * "data": [{
     * "name" : "col-1", <-- rs.getString(1)
     * "values" : [
     * {
     * "id" : "col-2,col-3,....,col-n", <-- rs.getString(2) ... rs.getString(N)
     * },
     * {
     * "id" : "col-2,col-3,....,col-n", <-- rs.getString(2) ... rs.getString(N)
     * }]
     * }]
     * }
     * Each 'name' value is the first entry in the ResultSets row, 'id' is a comma separated string of the
     * rest of the rows values.
     * If there are multiple rows in the ResultSet with the same value for 'name', they are merge to existing
     * named entries.
     *
     * @param rs Input ResultSet
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONNameListDataObject(final ResultSet rs) throws SQLException {

        final StringBuilder json = new StringBuilder();
        json.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA).append(DATA_ARRAY_BEGIN);
        final Map<String, List<String>> results = toNameValueList(rs);
        final Iterator<String> nameIterator = results.keySet().iterator();
        while (nameIterator.hasNext()) {
            final String name = nameIterator.next();
            json.append(OPEN_BRACE).append(NVLIST_NAME).append(name).append("\", ").append(VALUES_BEGIN);
            final List<String> values = results.get(name);
            final Iterator<String> valuesIterator = values.iterator();
            while (valuesIterator.hasNext()) {
                final String aValue = valuesIterator.next();
                json.append(OPEN_BRACE).append(NVLIST_ID).append(aValue).append(INVERTED_COMMAS).append(CLOSE_BRACE);
                if (valuesIterator.hasNext()) {
                    json.append(COMMA);
                }
            }
            json.append(ARRAY_END).append(CLOSE_BRACE);
            if (nameIterator.hasNext()) {
                json.append(COMMA);
            }
        }
        json.append(ARRAY_END).append(CLOSE_BRACE);
        return json.toString();
    }

    /**
     * Convert a successful ResultSet to the following JDON format:
     * {"success": "true",
     * "errorDescription": "",
     * "data": [{
     * "name" : "col-1", <-- rs.getString(1)
     * "values" : [
     * {
     * "id" : "col-2:col-3,....,col-n", <-- rs.getString(2) ... rs.getString(N)
     * },
     * {
     * "id" : "col-2:col-3,....,col-n", <-- rs.getString(2) ... rs.getString(N)
     * }]
     * }]
     * }
     * Each 'name' value is the first entry in the ResultSets row, 'id' is a colon separated string of the
     * rest of the rows values.
     * If there are multiple rows in the ResultSet with the same value for 'name', they are merge to existing
     * named entries.
     *
     * @param rs Input ResultSet
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static String toJSONNameListMultipleValueDataObject(final ResultSet rs) throws SQLException {

        final StringBuilder json = new StringBuilder();
        json.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA).append(DATA_ARRAY_BEGIN);
        final Map<String, List<List<String>>> results = toNameMultipleValuesList(rs);
        final Iterator<String> nameIterator = results.keySet().iterator();
        while (nameIterator.hasNext()) {
            final String name = nameIterator.next();
            json.append(OPEN_BRACE).append(NVLIST_NAME).append(name).append("\", ").append(VALUES_BEGIN);
            final List<List<String>> values = results.get(name);
            final Iterator<List<String>> valuesIterator = values.iterator();
            while (valuesIterator.hasNext()) {
                final List<String> valueListOfOneRow = valuesIterator.next();

                int i = 0;
                String value = "";
                json.append(OPEN_BRACE).append(QUOTE + ID + QUOTE + COLON).append(OPEN_BRACE);
                final Iterator<String> valueListOfOneRowIterator = valueListOfOneRow.iterator();
                while (valueListOfOneRowIterator.hasNext()) {
                    value = valueListOfOneRowIterator.next();
                    i++;

                    json.append(QUOTE + i + QUOTE + COLON + QUOTE + value + QUOTE);
                    if (valueListOfOneRowIterator.hasNext()) {
                        json.append(COMMA);
                    }
                }
                json.append(CLOSE_BRACE).append(CLOSE_BRACE);
                if (valuesIterator.hasNext()) {
                    json.append(COMMA);
                }

            }
            json.append(ARRAY_END).append(CLOSE_BRACE);
            if (nameIterator.hasNext()) {
                json.append(COMMA);
            }
        }
        json.append(ARRAY_END).append(CLOSE_BRACE);
        return json.toString();
    }

    /**
     * Convert a successful ResultSet to the following JDON format without time information:
     * {"success": "true",
     * "errorDescription": "",
     * "data": [{
     * "1" : "col-1", <-- rs.getString(1)
     * }]
     * }]
     * }
     *
     * @param rs Input ResultSet
     * @return JSON format representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */

    public static String toJSONDataObjectWithoutTimeInfo(final ResultSet rs) throws SQLException {
        final List<ResultSet> resultSetList = new ArrayList<ResultSet>();
        resultSetList.add(rs);
        final StringBuilder json = new StringBuilder();
        json.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA).append(DATA_ARRAY_BEGIN);

        int rowCount = 0;
        for (final ResultSet resultSet : resultSetList) {
            while (resultSet.next()) {
                if (rowCount > 0) {
                    json.append(COMMA);
                }
                json.append(OPEN_BRACE);
                final int numberColumns = resultSet.getMetaData().getColumnCount() + 1;
                int columnIndex = 1;

                for (; columnIndex < numberColumns; columnIndex++) {

                    json.append(formatColumnIndex(columnIndex)).append(COLON)
                            .append(formatValue(resultSet.getString(columnIndex)));

                    if ((columnIndex + 1) != numberColumns) {
                        json.append(COMMA);
                    }
                }
                json.append(CLOSE_BRACE);
                rowCount++;
            }
        }
        json.append(ARRAY_END).append(CLOSE_BRACE);
        return json.toString();
    }

    /**
     * Convert a ResultSet in to a Map where the key of the Map is the first value of the ResultSet row
     * is the Maps KEY and subsequent values on the row are places in the Maps values.
     * If multiple rows exist that have the same first key, the values get merge into a single List.
     * Mainly used to get the UI's info data when displaying Groups.
     *
     * @param rs The ResultSet to convert.
     * @return Map, as explained above.
     * @throws SQLException If there are errors while iterating over the ResultSet.
     */
    public static Map<String, List<String>> toNameValueList(final ResultSet rs) throws SQLException {
        final Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        if (rs == null) {
            return resultMap;
        }
        final ResultSetMetaData rsMetaData = rs.getMetaData();
        final String rsKey = rsMetaData.getColumnName(1);
        while (rs.next()) {
            final StringBuilder sb = new StringBuilder(); //NOPMD eemecoy 3/6/10, a necessary evil
            for (int i = 2; i <= rsMetaData.getColumnCount(); i++) {
                final String vName = rsMetaData.getColumnName(i);
                final String value = rs.getString(vName);
                sb.append(value);
                if (i < rsMetaData.getColumnCount()) {
                    sb.append(COMMA);
                }
            }
            final String keyName = rs.getString(rsKey);
            final List<String> values;
            if (resultMap.containsKey(keyName)) {
                values = resultMap.get(keyName);
            } else {
                values = new ArrayList<String>(); //NOPMD edeccox 2/6/2010 transforming a result set - you dont know in advance how many values you are getting so this is necessary
                resultMap.put(keyName, values);
            }
            values.add(sb.toString());
        }
        return resultMap;
    }

    public static Map<String, List<List<String>>> toNameMultipleValuesList(final ResultSet rs) throws SQLException {
        final Map<String, List<List<String>>> resultMap = new LinkedHashMap<String, List<List<String>>>();
        if (rs == null) {
            return resultMap;
        }
        final ResultSetMetaData rsMetaData = rs.getMetaData();
        final String rsKey = rsMetaData.getColumnName(1);
        while (rs.next()) {
            final List<String> valueListOfOneRow = new ArrayList<String>(); //NOPMD eemecoy 3/6/10, a necessary evil
            for (int i = 2; i <= rsMetaData.getColumnCount(); i++) {
                final String vName = rsMetaData.getColumnName(i);
                final String value = rs.getString(vName);
                valueListOfOneRow.add(value);
            }
            final String keyName = rs.getString(rsKey);
            final List<List<String>> values;
            if (resultMap.containsKey(keyName)) {
                values = resultMap.get(keyName);
            } else {
                values = new ArrayList<List<String>>(); //NOPMD edeccox 2/6/2010 transforming a result set - you dont know in advance how many values you are getting so this is necessary
                resultMap.put(keyName, values);
            }
            values.add(valueListOfOneRow);
        }
        return resultMap;
    }

    /**
     * Generic JSON error result object. Add error text description.
     *
     * @param errorDescription the nature of the error
     * @return JSON error result
     */
    public static String createJSONErrorResult(final String errorDescription) {
        final StringBuilder result = new StringBuilder(OPEN_BRACE);
        result.append(getJSONHeader(false, errorDescription));
        result.append(CLOSE_BRACE);
        return result.toString();
    }

    public static String createBISSOAPClientJSONErrorResult(final String errorDescription) {
        final StringBuilder result = new StringBuilder(OPEN_BRACE);
        result.append(getJSONHeader(true, errorDescription));
        result.append(",\"BISServiceSuccess\" : \"false\"");
        result.append(CLOSE_BRACE);
        return result.toString();
    }

    /**
     * Generic JSON success result object.The data field is empty, success is true.
     *
     * @return JSON Empty JSON result with success set to TRUE
     */
    public static String JSONEmptySuccessResult() {
        final StringBuilder result = new StringBuilder(OPEN_BRACE);
        result.append(getJSONHeader(true, "")).append(COMMA);
        result.append(DATA_ARRAY_BEGIN).append(ARRAY_END);
        result.append(CLOSE_BRACE);
        return result.toString();
    }

    public static String JSONSuccessResult(final String timestampFrom, final String timestampTo, final String tzOffset,
            final String payload) {
        final StringBuilder result = new StringBuilder(OPEN_BRACE);
        result.append(getJSONHeader(true, "")).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(DATA_OBJECT).append(payload).append(CLOSE_BRACE);
        return result.toString();
    }

    /**
     * Header part of all JSON encoded result objects with success set to TRUE.
     *
     * @return Successful header string
     */
    public static String getJSONSuccessHeader() {
        return getJSONHeader(true, null);
    }

    /**
     * Header part of all JSON encoded result objects. If success is set to
     * false then the error description will optionally provide additional cause
     * information such exception messages.
     *
     * @param status           true if no errors occurred during data encoding
     * @param errorDescription present if errors occurred during encoding
     * @return header string
     */
    private static String getJSONHeader(final boolean status, final String errorDescription) {
        final StringBuilder result = new StringBuilder();
        return result.append(SUCCESS).append(COLON).append(INVERTED_COMMAS).append(status).append(INVERTED_COMMAS)
                .append(COMMA).append(ERROR_DESCRIPTION).append(COLON).append(INVERTED_COMMAS)
                .append(escapeErrorDescription(errorDescription)).append(INVERTED_COMMAS).toString();
    }

    /**
     * Escape error description.
     *
     * @param errorDescription the error description
     * @return the string
     */
    private static String escapeErrorDescription(final String errorDescription) {
        if (errorDescription == null) {
            return "";
        }
        String escaped = errorDescription.replace(INVERTED_COMMAS, "\\\"");
        escaped = escaped.replace("\\", "\\\\");
        return escaped;
    }

    /**
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
     * @return JSON data array representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */

    private static String toJsonGridDataArray(final List<Integer> timeColumns, final String tzOffset,
            final List<ResultSet> resultSetList) throws SQLException {
        if ((null == resultSetList) || resultSetList.isEmpty()) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final StringBuilder result = new StringBuilder(DATA_ARRAY_BEGIN);

        if (hasMoreRows(resultSetList)) {
            result.append(OPEN_BRACE);
            do {
                int rsIndex = 1;
                for (final ResultSet rs : resultSetList) {
                    final int numberColumns = rs.getMetaData().getColumnCount() + 1;

                    //Because the first column in each result set is the date_id, and we only want to include the date
                    //once, for the first resultSet we add columns starting at index 1, for the rest we start at
                    //column 2
                    int columnIndex = (rsIndex == 1) ? 1 : 2;

                    for (; columnIndex < numberColumns; columnIndex++) {
                        if ((timeColumns != null) && timeColumns.contains(columnIndex) && (tzOffset != null)) {
                            result.append(formatColumnIndex(columnIndex))
                                    .append(COLON)
                                    .append(formatValue(DateTimeUtils.getLocalTime(rs.getString(columnIndex), tzOffset,
                                            RECEIVED_DATE_FORMAT)));
                        } else {
                            //If the column contains floating point values, use getFloat instead of getString
                            if (doesColumnContainFloatingPointValues(rs.getMetaData().getColumnType(columnIndex))) {
                                result.append(formatColumnIndex(columnIndex)).append(COLON)
                                        .append(formatValue(String.valueOf(rs.getFloat(columnIndex))));
                            } else {
                                result.append(formatColumnIndex(columnIndex)).append(COLON)
                                        .append(formatValue(rs.getString(columnIndex)));
                            }

                        }
                        if ((columnIndex + 1) != numberColumns) {
                            result.append(COMMA);
                        }
                    }
                }
                rsIndex++;
            } while (hasMoreRows(resultSetList)
                    && (result.append(CLOSE_BRACE).append(COMMA).append(OPEN_BRACE) != null));
            result.append(CLOSE_BRACE);

        }

        result.append(ARRAY_END);

        return result.toString();
    }

    /**
     * Method to check if that specified columnType is one of FLOAT, REAL or DOUBLE
     *
     * @param columnType
     * @return
     */
    private static boolean doesColumnContainFloatingPointValues(final int columnType) {
        return columnType == FLOAT || columnType == REAL;
    }

    private static boolean hasMoreRows(final List<ResultSet> resultSetList) throws SQLException {
        boolean result = true;
        for (final ResultSet rs : resultSetList) {
            try {
                result = result && rs.next();
            } catch (final SQLException sqlException) {
                if (!(sqlException.getLocalizedMessage().contains("1006000"))) {
                    throw sqlException;
                }
                result = false;
                ServicesLogger.warn(JSONUTILS, "hasMoreRows", sqlException);
            }
        }
        return result;
    }

    /**
    *
    * convert the input string to a valid json string
    *
    * @param input a valid java string ,could be invalid json string because it has ASCII code like 10 ,13 .
    * @return a valid json string. ASCII code 10 is replaced by two char '\' and 'n'       .
    */
    public static String jsonEscapedString(final String input) {
        if (null == input) {
            return EMPTY_STRING;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            final char ch = input.charAt(i);
            switch (ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("");
                break;
            case '\r':
                sb.append("");
                break;
            case '\t':
                sb.append("");
                break;
            case '/':
                sb.append("\\/");
                break;
            default:
                if ((ch >= '\u0000' && ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F')
                        || (ch >= '\u2000' && ch <= '\u20FF')) {
                    final String hexString = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - hexString.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(hexString.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }

        }
        return sb.toString();
    }

    /**
     * Convert ResultSet to JSON data grid array. The encoding for grid data is
     * as follows; "data":[ {"col1":"val11","col2":"val12",..,"colN":"val1N"},
     * {"col1":"val21","col2":"val22",..,"colN":"val2N"}, . .
     * {"col1":"valM1","col2":"valM2",..,"colN":"valMN"}]
     * The column identifier 'colN' is used as an index in the presentation
     * layer. The actual displayed column headers are encoded in UI meta data
     * which is loaded at application startup.
     * <p/>


     /**
      * Convert ResultSet to JSON data grid array. The encoding for grid data is
      * as follows; "data":[ {"col1":"val11","col2":"val12",..,"colN":"val1N"},
      * {"col1":"val21","col2":"val22",..,"colN":"val2N"}, . .
      * {"col1":"valM1","col2":"valM2",..,"colN":"valMN"}]
      * The column identifier 'colN' is used as an index in the presentation
      * layer. The actual displayed column headers are encoded in UI meta data
      * which is loaded at application startup.
      *
      * The rows data of all the result set passed will be appended and converted to a
      * JSON object
      * Note: method is responsible for closing the result set upon completion.
      *
      * @param timeColumns of input result set, list of time column index needs to be changed to local time
      * @return JSON data array representation
      * @throws SQLException If there are errors while iterating over the ResultSet
      */
    private static String toJsonGridDataArrayAppendRows(final List<Integer> timeColumns, final String tzOffset,
            final List<ResultSet> resultSetList) throws SQLException {
        if ((null == resultSetList) || resultSetList.isEmpty()) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final StringBuilder result = new StringBuilder(DATA_ARRAY_BEGIN);
        int rowCount = 0;
        for (final ResultSet rs : resultSetList) {
            while (rs.next()) {
                if (rowCount > 0) {
                    result.append(COMMA);
                }
                result.append(OPEN_BRACE);
                final int numberColumns = rs.getMetaData().getColumnCount() + 1;
                int columnIndex = 1;

                for (; columnIndex < numberColumns; columnIndex++) {
                    if ((timeColumns != null) && timeColumns.contains(columnIndex) && (tzOffset != null)) {
                        result.append(formatColumnIndex(columnIndex))
                                .append(COLON)
                                .append(formatValue(DateTimeUtils.getLocalTime(rs.getString(columnIndex), tzOffset,
                                        RECEIVED_DATE_FORMAT)));
                    } else {
                        result.append(formatColumnIndex(columnIndex)).append(COLON)
                                .append(formatValue(rs.getString(columnIndex)));
                    }
                    if ((columnIndex + 1) != numberColumns) {
                        result.append(COMMA);
                    }
                }
                result.append(CLOSE_BRACE);
                rowCount++;
            }
        }

        result.append(ARRAY_END);

        return result.toString();
    }

    /**
     * Converts result set to a list of a list of strings representing row values.
     *
     * @param results input result set
     * @return a list of lists of string objects
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    public static List<List<String>> toList(final ResultSet... results) throws SQLException {
        final List<ResultSet> resultList = new ArrayList<ResultSet>();
        for (final ResultSet rs : results) {
            resultList.add(rs);
        }
        return toList(resultList);
    }

    /**
     * Converts result set to a list of a list of strings representing row values.
     *
     * @param results input result set
     * @return a list of lists of string objects
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    static List<List<String>> toListWithAppendedRows(final List<ResultSet> results) throws SQLException {
        if (results == null) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final List<List<String>> result = new ArrayList<List<String>>();
        for (final ResultSet rs : results) {
            while (rs.next()) {
                final List<String> rowValues = new ArrayList<String>();
                final int ncols = rs.getMetaData().getColumnCount() + 1;
                int i = 1;
                for (; i < ncols; i++) {
                    rowValues.add(rs.getString(i));
                }
                result.add(rowValues);
            }
        }
        return result;
    }

    /**
     * Converts result set to a list of a list of strings representing row values.
     *
     * @param results input result set
     * @return a list of lists of string objects
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    static List<List<String>> toList(final List<ResultSet> results) throws SQLException {
        final List<List<String>> result = new ArrayList<List<String>>();
        if (hasMoreRows(results)) {
            List<String> rowValues;
            do {
                rowValues = new ArrayList<String>();
                int rsIndex = 1;
                for (final ResultSet rs : results) {
                    final int ncols = rs.getMetaData().getColumnCount() + 1;
                    int i = rsIndex == 1 ? 1 : 2;
                    for (; i < ncols; i++) {
                        rowValues.add(rs.getString(i));
                    }
                    rsIndex++;
                }
                result.add(rowValues);
            } while (hasMoreRows(results));
        }
        return result;
    }

    /**
     * Maps a List of a List of String objects to a JSON data array.
     *
     * @param data list of lists of equal size representing row data
     * @return JSON data array object
     */
    private static String toJsonGridDataArray(final List<List<String>> data, final String timeColumn,
            final String tzOffset) {
        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        }
        final StringBuilder result = new StringBuilder(DATA_ARRAY_BEGIN);
        for (int i = 0; i < data.size(); i++) {
            result.append(OPEN_BRACE);
            final List<String> row = data.get(i);
            for (int j = 0; j < row.size(); j++) {
                if ((timeColumn != null) && (j == (Integer.parseInt(timeColumn) - 1))) {
                    result.append(formatColumnIndex(j + 1))
                            .append(COLON)
                            .append(formatValue(DateTimeUtils.getLocalTime(row.get(j), tzOffset, RECEIVED_DATE_FORMAT)));
                } else {
                    result.append(formatColumnIndex(j + 1)).append(COLON).append(formatValue(row.get(j)));
                }
                if (j != (row.size() - 1)) {
                    result.append(COMMA);
                }
            }
            result.append(CLOSE_BRACE);
            if (i != (data.size() - 1)) {
                result.append(COMMA);
            }
        }
        result.append(ARRAY_END);
        return result.toString();
    }

    /**
     * Gets the yaxis max and min value.
     *
     * @param data        - {@link List <tt>List</tt>} converted from {@link ResultSet <tt>ResultSet</tt>}
     * @param xaxis       - x axis position
     * @param secondYaxis - second yaxis position
     * @return {@link Map } contains y axis max and min value
     */
    public static Map<String, String> getYaxisMaxAndMinValue(final List<List<String>> data, final String xaxis,
            final String secondYaxis) {
        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        }
        final List<Long> longNumbers = new ArrayList<Long>();
        final List<Float> floatNumbers = new ArrayList<Float>();
        for (final List<String> row : data) {
            removeXaxis(row, xaxis);
            if (!StringUtils.isBlank(secondYaxis)) {
                removeSecondYaxis(row, secondYaxis);
            }
            createNewList(row, longNumbers, floatNumbers);
        }
        return calculateMaxAndMinValue(longNumbers, floatNumbers);
    }

    /**
     * Gets the yaxis max and min value for the Groups Most Frequent Signalling chart based on a list of ResultSets.
     * This chart needs special attention because it returns values that are not shown in the
     * chart (for toggle to grid only) - hence the max/min calculations are different..
     *
     * @param data        - {@link List <tt>List</tt>} converted from {@link ResultSet <tt>ResultSet</tt>}
     * @return {@link Map } contains y axis max and min value
     */
    public static Map<String, String> getGroupsMostFreqSignalYaxisMaxAndMinValue(final List<List<String>> data) {
        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        }
        final List<Long> longNumbers = new ArrayList<Long>();
        for (final List<String> row : data) {
            longNumbers.add(Long.parseLong(row.get(GROUP_MOST_FREQ_SIGNAL_ERROR_COL_NO)));
            longNumbers.add(Long.parseLong(row.get(GROUP_MOST_FREQ_SIGNAL_SUCCESS_COL_NO)));
        }
        final Map<String, String> maxAndMinMap = new HashMap<String, String>();
        if (longNumbers.isEmpty()) {
            maxAndMinMap.put(Y_AXIS_MIN, ZERO);
            maxAndMinMap.put(Y_AXIS_MAX, ZERO);
        } else {
            Long yAxisMin = Collections.min(longNumbers);
            final Long yAxisMax = Collections.max(longNumbers);

            //make the min, 1 interval below smallest value, so it will still show on the graph
            yAxisMin = yAxisMin - ((yAxisMax - yAxisMin) / Y_AXIS_NUM_INTERVALS);
            yAxisMin = (yAxisMin >= 0) ? yAxisMin : 0;

            maxAndMinMap.put(Y_AXIS_MAX, yAxisMax.toString());
            maxAndMinMap.put(Y_AXIS_MIN, (yAxisMin >= 0) ? yAxisMin.toString() : ZERO);
        }
        return maxAndMinMap;
    }

    /**
     * Removes the xaxis from the generated {@link List <tt>List</tt>}.
     *
     * @param rows  - the generated {@link List <tt>List</tt>}
     * @param xaxis - x axis position
     */
    private static void removeXaxis(final List<String> rows, final String xaxis) {

        for (int j = 0; j < rows.size(); j++) {
            if (j == (Integer.parseInt(xaxis) - 1)) {
                rows.remove(j);
            }
        }
    }

    /**
     * Removes the second yaxis from the generated {@link List <tt>List</tt>}.
     *
     * @param rows        - the generated {@link List <tt>List</tt>}
     * @param secondYaxis - second yaxis position
     */
    private static void removeSecondYaxis(final List<String> rows, final String secondYaxis) {

        for (int k = 0; k < rows.size(); k++) {
            if (Integer.parseInt(secondYaxis) <= rows.size()) {
                if (k == (Integer.parseInt(secondYaxis) - 1)) {
                    rows.remove(k);
                }
            } else {
                if (k == (Integer.parseInt(secondYaxis) - 2)) {
                    rows.remove(k);
                }
            }
        }
    }

    /**
     * Generate the new chart list for sampling chart only.
     *
     * @param data          original data list
     * @param xaxis         xaxis position
     * @param chartDateTime chart dateTime list
     * @return new data list
     */
    private static List<List<String>> getSamplingChartList(final List<List<String>> data, final String xaxis,
            final String[] chartDateTime) {
        if (!sample) {
            return data;
        }
        final List<List<String>> newDataList = new ArrayList<List<String>>();
        for (final List<String> row : data) {
            row.get(Integer.parseInt(xaxis) - 1);
            for (final String element : chartDateTime) {
                if (element.equals(row.get(Integer.parseInt(xaxis) - 1))) {
                    newDataList.add(row);
                }
            }
        }
        return newDataList;
    }

    /**
     * Creates the new {@link List <tt>List</tt>} after remove axis position's values.
     *
     * @param row         - the generated {@link List <tt>List</tt>}
     * @param longNumber  - {@link List <tt>List</tt>} contains {@link Long <tt>Long</tt>}
     * @param floatNumber - {@link List <tt>List</tt>} contains {@link Float <tt>Float</tt>}
     */
    private static void createNewList(final List<String> row, final List<Long> longNumber, final List<Float> floatNumber) {
        for (final String aRow : row) {
            if (aRow != null) {
                if (aRow.contains(DECIMAL_POINT)) {
                    floatNumber.add(Float.parseFloat(aRow));
                } else {
                    longNumber.add(Long.parseLong(aRow));
                }
            }
        }
    }

    /**
     * Calculate max and min value.
     *
     * @param longNumber     - {@link List <tt>List</tt>} contains {@link Long <tt>Long</tt>}
     * @param floatingNumber - {@link List <tt>List</tt>} contains {@link Float <tt>Float</tt>}
     * @return {@link Map } contains y axis max and min value
     */
    public static Map<String, String> calculateMaxAndMinValue(final List<Long> longNumber,
            final List<Float> floatingNumber) {
        final Map<String, String> maxAndMinMap = new HashMap<String, String>();
        if (!floatingNumber.isEmpty()) {
            maxAndMinMap.put(Y_AXIS_MIN, Collections.min(floatingNumber).toString());
            maxAndMinMap.put(Y_AXIS_MAX, Collections.max(floatingNumber).toString());
        } else if (!longNumber.isEmpty()) {
            maxAndMinMap.put(Y_AXIS_MIN, Collections.min(longNumber).toString());
            maxAndMinMap.put(Y_AXIS_MAX, Collections.max(longNumber).toString());
        } else {
            maxAndMinMap.put(Y_AXIS_MIN, ZERO);
            maxAndMinMap.put(Y_AXIS_MAX, ZERO);
        }
        return maxAndMinMap;
    }

    /**
     * Gets the JSON chart header.
     *
     * @param xaxis       - xaxis position
     * @param secondYaxis - second yaxis position
     * @param results     - {@link ResultSet <tt>ResultSet</tt>}
     * @return the JSON chart header in String
     * @throws NumberFormatException the number format exception
     * @throws SQLException          If there are errors while iterating over the ResultSet
     */
    private static String getJSONChartHeader(final String xaxis, final String secondYaxis, final List<ResultSet> results)
            throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();
        final Map<String, String> values = getYaxisMaxAndMinValue(toList(results), xaxis, secondYaxis);
        result.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(values.get(Y_AXIS_MIN))
                .append(INVERTED_COMMAS).append(COMMA).append(Y_AXIS_MAX).append(COLON).append(INVERTED_COMMAS)
                .append(values.get(Y_AXIS_MAX)).append(INVERTED_COMMAS);
        for (final ResultSet rs : results) {
            rs.beforeFirst();
        }
        return result.toString();
    }

    /**
     * Gets the JSON chart header.
     *
     * @param xaxis       - xaxis position
     * @param secondYaxis - second yaxis position
     * @param results     - {@link ResultSet <tt>ResultSet</tt>}
     * @return the JSON chart header in String
     * @throws NumberFormatException the number format exception
     * @throws SQLException          If there are errors while iterating over the ResultSet
     */
    private static String getJSONChartHeaderWithAppendedRows(final String xaxis, final String secondYaxis,
            final List<ResultSet> results) throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();
        final Map<String, String> values = getYaxisMaxAndMinValue(toListWithAppendedRows(results), xaxis, secondYaxis);
        result.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(values.get(Y_AXIS_MIN))
                .append(INVERTED_COMMAS).append(COMMA).append(Y_AXIS_MAX).append(COLON).append(INVERTED_COMMAS)
                .append(values.get(Y_AXIS_MAX)).append(INVERTED_COMMAS);
        for (final ResultSet rs : results) {
            rs.beforeFirst();
        }
        return result.toString();
    }

    /**
     * Gets the JSON chart header for  the Groups Most Frequent Signalling chart based on a list of ResultSets.
     * This chart needs special attention because it returns values that are not shown in the
     * chart (for toggle to grid only) - hence the max/min calculations are different.
     *
     * @param results     - {@link ResultSet <tt>ResultSet</tt>}
     * @return the JSON chart header in String
     * @throws NumberFormatException the number format exception
     * @throws SQLException          If there are errors while iterating over the ResultSet
     */
    private static String getGroupsMostFreqSignalJSONChartHeader(final List<ResultSet> results)
            throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();
        final Map<String, String> values = getGroupsMostFreqSignalYaxisMaxAndMinValue(toList(results));
        result.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(values.get(Y_AXIS_MIN))
                .append(INVERTED_COMMAS).append(COMMA).append(Y_AXIS_MAX).append(COLON).append(INVERTED_COMMAS)
                .append(values.get(Y_AXIS_MAX)).append(INVERTED_COMMAS);
        for (final ResultSet rs : results) {
            rs.beforeFirst();
        }
        return result.toString();
    }

    /**
     * Gets the json kpi chart header.
     *
     * @param xaxis         the xaxis
     * @param secondYaxis   the second yaxis
     * @param chartTimeList the cha
     * @param timeColumn    the chart time list
     * @param tzOffset      the time zone offset
     * @param resultSets    the result set
     * @return the JSONKPI chart header
     * @throws NumberFormatException the number format exception
     * @throws SQLException          the SQL exception
     */
    private static String getJSONSamplingChartHeader(final String xaxis, final String secondYaxis,
            final String[] chartTimeList, final String timeColumn, final String tzOffset,
            final List<ResultSet> resultSets) throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();

        List<List<String>> newSamplingData = getSamplingChartList(toList(resultSets), xaxis, chartTimeList);
        final Map<String, String> values = getYaxisMaxAndMinValue(newSamplingData, xaxis, secondYaxis);
        /** since its static, we need to get original list again after calculation */
        for (final ResultSet rs : resultSets) {
            rs.beforeFirst();
        }
        newSamplingData = getSamplingChartList(toList(resultSets), xaxis, chartTimeList);
        result.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(values.get(Y_AXIS_MIN))
                .append(INVERTED_COMMAS).append(COMMA).append(Y_AXIS_MAX).append(COLON).append(INVERTED_COMMAS)
                .append(values.get(Y_AXIS_MAX)).append(INVERTED_COMMAS).append(COMMA)
                .append(toJsonGridDataArray(newSamplingData, timeColumn, tzOffset));
        return result.toString();
    }

    /**
     *
     * @param xaxis
     * @param secondYaxis
     * @param chartTimeList
     * @param timeColumn
     * @param tzOffset
     * @param resultSets
     * @param calc
     * @return
     * @throws NumberFormatException
     * @throws SQLException
     */
    private static String getJSONSamplingChartHeaderWithCalculator(final String xaxis, final String secondYaxis,
            final String[] chartTimeList, final String timeColumn, final String tzOffset,
            final List<ResultSet> resultSets, final Calculator calc) throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();

        final List<List<String>> samplingData = getSamplingChartList(toList(resultSets), xaxis, chartTimeList);

        final List<List<String>> newSamplingData = new ArrayList<List<String>>();
        for (final List<String> row : samplingData) {
            newSamplingData.add(calc.calc(row, Arrays.asList(xaxis, secondYaxis)));
        }
        final Map<String, String> values = new HashMap<String, String>();
        values.put(Y_AXIS_MIN, calc.min());
        values.put(Y_AXIS_MAX, calc.max());
        result.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(values.get(Y_AXIS_MIN))
                .append(INVERTED_COMMAS).append(COMMA).append(Y_AXIS_MAX).append(COLON).append(INVERTED_COMMAS)
                .append(values.get(Y_AXIS_MAX)).append(INVERTED_COMMAS).append(COMMA)
                .append(toJsonGridDataArray(newSamplingData, timeColumn, tzOffset));
        return result.toString();
    }

    /**
     * Format column index.
     * @param i - the index
     * @return generated string
     */
    static String formatColumnIndex(final int i) {
        return INVERTED_COMMAS.concat(String.valueOf(i)).concat(INVERTED_COMMAS);
    }

    /**
     * Format value.
     *
     * @param value - the value from {@link ResultSet <tt>ResultSet</tt>}
     * @return generated string
     */
    static String formatValue(final String value) {
        if (value == null) {
            return INVERTED_COMMAS.concat("").concat(INVERTED_COMMAS);
        }
        return INVERTED_COMMAS.concat(jsonEscapedString(value)).concat(INVERTED_COMMAS);
    }

    // is not instantiated.

    /**
     * The Constructor.
     */
    private JSONUtils() {
    }

    /**
     * To json sampling chart data object.
     *
     * @param rs            the result set
     * @param xaxis         the xaxis
     * @param secondYaxis   the second yaxis
     * @param chartTimeList the chart time list
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn    the chart time list
     * @param tzOffset      the time zone offset
     * @return the string
     * @throws NumberFormatException the number format exception
     * @throws SQLException          the SQL exception
     */
    public static String toJSONSamplingChartDataObject(final ResultSet rs, final String xaxis,
            final String secondYaxis, final String[] chartTimeList, final String timestampFrom,
            final String timestampTo, final String timeColumn, final String tzOffset) throws NumberFormatException,
            SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONSamplingChartDataObject(xaxis, secondYaxis, chartTimeList, timestampFrom, timestampTo, timeColumn,
                tzOffset, results);
    }

    /**
     * To json sampling chart data object.
     *
     * @param xaxis         the xaxis
     * @param secondYaxis   the second yaxis
     * @param chartTimeList the chart time list
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param timeColumn
     * @param tzOffset
     * @param results
     * @return the string
     * @throws NumberFormatException the number format exception
     * @throws SQLException          the SQL exception
     */
    public static String toJSONSamplingChartDataObject(final String xaxis, final String secondYaxis,
            final String[] chartTimeList, final String timestampFrom, final String timestampTo,
            final String timeColumn, final String tzOffset, final List<ResultSet> results)
            throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(getJSONSamplingChartHeader(xaxis, secondYaxis, chartTimeList, timeColumn, tzOffset, results))
                .append(CLOSE_BRACE).toString();
    }

    /**
     * To json sampling chart data object.
     *
     * @param rs            the rs
     * @param xaxis         the xaxis
     * @param secondYaxis   the second yaxis
     * @param chartTimeList the chart time list
     * @param calc          the calculator
     * @return the string
     * @throws NumberFormatException the number format exception
     * @throws SQLException          the SQL exception
     */
    public static String toJSONSamplingChartDataObjectWithCalculator(final ResultSet rs, final String xaxis,
            final String secondYaxis, final String[] chartTimeList, final String timeColumn, final String tzOffset,
            final Calculator calc) throws NumberFormatException, SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONSamplingChartDataObjectWithCalculator(xaxis, secondYaxis, chartTimeList, timeColumn, tzOffset,
                results, calc);
    }

    /**
     * To json sampling chart data object.
     *
     * @param xaxis         the xaxis
     * @param secondYaxis   the second yaxis
     * @param chartTimeList the chart time list
     * @param timeColumn    the chart time list
     * @param tzOffset      the time zone offset
     * @param results       the result set
     * @param calc          the calculator
     * @return the string
     * @throws NumberFormatException the number format exception
     * @throws SQLException          the SQL exception
     */
    public static String toJSONSamplingChartDataObjectWithCalculator(final String xaxis, final String secondYaxis,
            final String[] chartTimeList, final String timeColumn, final String tzOffset,
            final List<ResultSet> results, final Calculator calc) throws NumberFormatException, SQLException {
        final StringBuilder result = new StringBuilder();
        return result
                .append(OPEN_BRACE)
                .append(getJSONSuccessHeader())
                .append(COMMA)
                .append(getJSONSamplingChartHeaderWithCalculator(xaxis, secondYaxis, chartTimeList, timeColumn,
                        tzOffset, results, calc)).append(CLOSE_BRACE).toString();
    }

    /**
     * JSON build failure error.
     *
     * @return the string
     */
    public static String JSONBuildFailureError() {
        return createJSONErrorResult("Failed to build query");
    }

    public static String jsonErrorInputMsg() {
        return createJSONErrorResult(E_INVALID_VALUES);
    }

    public static String jsonErrorTypeMsg() {
        return createJSONErrorResult(E_INVALID_TYPE);
    }

    /**
     * JSON error no raw tables.
     *
     * @return the string
     */
    public static String jsonErrorNoRawTables() {
        return createJSONErrorResult(E_MISSING_RAW_TABLES);
    }

    /**
     * Convert ResultSet from query to specific JSON for list of cells connected to a SAC
     * Result will have only 1 row of data, eg
     * {"success":"true","errorDescription":"","data":[{"1":"206","2":"ericsson","3":"celll,cell2"}]}
     * Its presumed that for a given SAC, all connected cells will be of the same vendor, so connected cells are just
     * returned in a comma separated list
     * @param rs
     * @return
     * @throws SQLException
     */
    public static String toJSONForListCellsConnectedToSACResults(final ResultSet rs) throws SQLException {
        final List<Integer> columnsToBeAppended = new ArrayList<Integer>();
        columnsToBeAppended.add(INDEX_OF_CELL_COLUMN_IN_CELLS_CONNECTED_TO_SAC_QUERY);
        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(toJsonGrid1RowDataArrayWithColumnAppending(rs, columnsToBeAppended)).append(CLOSE_BRACE)
                .toString();
    }

    /**
     * Convert ResultSet to specific JSON data grid array of length 1.
     * The encoding for grid data is as follows:
     * "data":[ {"1":"val11","2":"valA,valB,valC...",..,"N":"val1N"}
     * where column 2 is contained in the list of columnsToBeAppended
     * For all other columns, its presumed that the value is the same for each row, and the last value
     * is the one used in the final 1 row result
     *
     * The column identifier 'N' is used as an index in the presentation
     * layer. The actual displayed column headers are encoded in UI meta data
     * which is loaded at application startup.
     * <p/>
     * Note: method is responsible for closing the result set upon completion.
     *
     * @param rs                  input result set
     * @param columnsToBeAppended contains columns whose results over rows are to be
     *                            appended in a comma separated string
     * @return JSON data array representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    private static String toJsonGrid1RowDataArrayWithColumnAppending(final ResultSet rs,
            final List<Integer> columnsToBeAppended) throws SQLException {
        if (rs == null) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final ResultSetMetaData resultSetMetaData = rs.getMetaData();
        final int numberColumns = resultSetMetaData.getColumnCount() + 1;
        final Map<Integer, String> columnValues = new HashMap<Integer, String>();
        if (rs.next()) {
            int i;
            do {
                for (i = 1; i < numberColumns; i++) {
                    final String columnValue = rs.getString(i);
                    if (columnsToBeAppended.contains(i)) {
                        final String existingValuesForColumn = columnValues.get(i);
                        if (existingValuesForColumn == null) {
                            columnValues.put(i, columnValue);
                        } else {
                            columnValues.put(i, existingValuesForColumn + COMMA + columnValue);
                        }
                    } else {
                        columnValues.put(i, columnValue);
                    }
                }
            } while (rs.next());
        }
        final StringBuilder rightResult = new StringBuilder(DATA_ARRAY_BEGIN);
        rightResult.append(OPEN_BRACE);
        for (int i = 1; i < numberColumns; i++) {
            rightResult.append(formatColumnIndex(i)).append(COLON).append(formatValue(columnValues.get(i)));

            if ((i + 1) != numberColumns) {
                rightResult.append(COMMA);
            }
        }
        rightResult.append(CLOSE_BRACE);
        rightResult.append(ARRAY_END);
        return rightResult.toString();
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     *
     * @param rs       the result set
     * @param busyKey  the key to differentiate between busy hour and busy day
     * @param tzOffset the timezone offset
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJSONChartDataObjectForSubBIBusy(final ResultSet rs, final String busyKey,
            final String timestampFrom, final String timestampTo, final String tzOffset) throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(DELIMITER)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArrayForSubBIBusy(rs, busyKey, tzOffset)).append(CLOSE_BRACE).toString();
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     *
     * @param rs       the result set
     * @param busyKey  the key to differentiate between busy hour and busy day
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param tzOffset the timezone offset
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJSONChartDataObjectForSubBIBusyWithAppendedRows(final ResultSet rs, final String busyKey,
            final String timestampFrom, final String timestampTo, final String tzOffset) throws SQLException {
        final List<ResultSet> results = new ArrayList<ResultSet>();
        results.add(rs);
        return toJSONChartDataObjectForSubBIBusyWithAppendedRows(results, busyKey, timestampFrom, timestampTo, tzOffset);
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     *
     * @param results  the result set
     * @param busyKey  the key to differentiate between busy hour and busy day
     * @param timestampFrom  - date (and time) from in milliseconds
     * @param timestampTo    - date(and time) to in milliseconds
     * @param tzOffset the timezone offset
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJSONChartDataObjectForSubBIBusyWithAppendedRows(final List<ResultSet> results,
            final String busyKey, final String timestampFrom, final String timestampTo, final String tzOffset)
            throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(DELIMITER)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDataArrayForSubBIBusyWithAppendedRows(results, busyKey, tzOffset))
                .append(CLOSE_BRACE).toString();
    }

    /**
     * Get Json data Roaming
     *
     * @param results  the result set
     * @param busyKey  the key to differentiate between busy hour and busy day
     * @param tzOffset the timezone offset
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJSONChartDataObjectForRoamingAppendedRows(final List<ResultSet> results,
            final String busyKey, final String tzOffset) throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(DELIMITER)
                .append(toJsonGridDataArrayForRoamingAppendedRows(results, busyKey, tzOffset)).append(CLOSE_BRACE)
                .toString();
    }

    /**
     * Get Grid Json data for subBI - busy day data.
     *
     * @param rs            the result set
     * @param timestampFrom the timestampFrom
     * @param timestampTo   the timestampTo
     * @param timeColumn    the time column
     * @param tzOffset      the time zone offset
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJSONGridDataObjectForSubBIBusyDay(final ResultSet rs, final String timestampFrom,
            final String timestampTo, final String timeColumn, final String tzOffset) throws SQLException {

        final StringBuilder result = new StringBuilder();
        return result.append(OPEN_BRACE).append(getJSONSuccessHeader()).append(COMMA)
                .append(getJSONGridTimeRangeParameters(timestampFrom, timestampTo, tzOffset)).append(COMMA)
                .append(toJsonGridDrillDownDataArrayForSubBIBusyDay(rs, timeColumn, tzOffset)).append(CLOSE_BRACE)
                .toString();
    }

    /**
     * Convert ResultSet to JSON data grid array for DrillDownData on BusyDay.
     * The conversion of data is different for Busy Day
     * The encoding for grid data is as follows;
     * "data":[ {"col1":"val11","col2":"val12",..,"colN":"val1N"},
     * {"col1":"val21","col2":"val22",..,"colN":"val2N"}, . .
     * {"col1":"valM1","col2":"valM2",..,"colN":"valMN"}]
     * <p/>
     * The column identifier 'colN' is used as an index in the presentation
     * layer. The actual displayed column headers are encoded in UI meta data
     * which is loaded at application startup.
     * <p/>
     * It will add the TZ offset to the time(UTC) to check whether time + tzoffset
     * will cross the 24hrs day limit.
     * <p/>
     * Note: method is responsible for closing the result set upon completion.
     *
     * @param rs input result set
     * @return JSON data array representation
     * @throws SQLException If there are errors while iterating over the ResultSet
     */
    private static String toJsonGridDrillDownDataArrayForSubBIBusyDay(final ResultSet rs, final String timeColumn,
            final String tzOffset) throws SQLException {
        if (rs == null) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        formatter.setTimeZone(TimeZone.getTimeZone(UTC));
        final GregorianCalendar gregCal = new GregorianCalendar(TimeZone.getTimeZone(UTC));
        final int offsetHour = getTzOffsetInHours(tzOffset);

        final StringBuilder result = new StringBuilder(DATA_ARRAY_BEGIN);
        if (rs.next()) {
            int i;
            final ResultSetMetaData resultSetMetaData = rs.getMetaData();
            final int numberColumns = resultSetMetaData.getColumnCount() + 1;
            result.append(OPEN_BRACE);
            do {
                for (i = 1; i < numberColumns; i++) {
                    // The following condition will be true if
                    // TimeColumn and timezone offset parameters are not null and
                    // The timecolumn is used to get the time of event from the result set
                    if ((timeColumn != null) && (i == Integer.parseInt(timeColumn)) && (tzOffset != null)) {
                        final String timeFormatInStr = rs.getString(i);
                        try {
                            final Date date = formatter.parse(timeFormatInStr);
                            gregCal.setTime(date);
                            // No need to check the day of week, as query would have returned
                            // the result set for the day specified in request parameters
                            final int hourOfTheday = gregCal.get(Calendar.HOUR_OF_DAY);
                            if (isValidHourOfTheDay(hourOfTheday, offsetHour)) {
                                continue;
                            }
                        } catch (final Exception e) {
                            //This should not happen as, the database date format is same for all entries
                            continue;
                        }
                        result.append(formatColumnIndex(i))
                                .append(COLON)
                                .append(formatValue(DateTimeUtils.getLocalTime(timeFormatInStr, tzOffset,
                                        RECEIVED_DATE_FORMAT)));
                    } else {
                        result.append(formatColumnIndex(i)).append(COLON).append(formatValue(rs.getString(i)));
                    }
                    if ((i + 1) != numberColumns) {
                        result.append(COMMA);
                    }
                }
            } while (rs.next() && (result.append(CLOSE_BRACE).append(COMMA).append(OPEN_BRACE) != null));
            result.append(CLOSE_BRACE);
        }
        result.append(ARRAY_END);
        return result.toString();
    }

    /**
     * @param hourOfTheday specifies the hour in UTC
     * @param offsetHour   specifies the offset of TZ in hours
     * @return True if the hour belongs to the present day else false
     */
    private static boolean isValidHourOfTheDay(final int hourOfTheday, final int offsetHour) {
        final int totalHours = hourOfTheday + offsetHour;
        if ((totalHours >= 24) || (totalHours < 0)) {
            //if the total hours is greater or equal to 24, then the events belongs to the next day
            //if the total hours is lesser than 0, then the events belongs to the previous day
            return true;
        }
        return false;
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     * The idea is to manipulate result set when there is no event for particular day/hour in the database
     *
     * @param rs      the result set
     * @param busyKey the key to differentiate between busy hour and busy day
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJsonGridDataArrayForSubBIBusy(final ResultSet rs, final String busyKey, final String tzOffset)
            throws SQLException {
        if (rs == null) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final SubBIDataHolder subBIDataHolder = new SubBIDataHolder();
        toJsonGridDataArrayForSubBIBusy(rs, busyKey, tzOffset, subBIDataHolder);
        final StringBuilder chartHeader = new StringBuilder();
        chartHeader.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(0).append("\",").append(Y_AXIS_MAX)
                .append(COLON).append(INVERTED_COMMAS).append(subBIDataHolder.maxNoOfEvents).append(INVERTED_COMMAS)
                .append(COMMA);
        //create the JSON format
        return toChartJSONStringForSUBBIBusy(subBIDataHolder.subBIBusyMap, chartHeader);
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     * To manipulate list of result set and add the value from different result sets for
     * the corresponding busy hour OR busy day value
     *
     * @param results the result set
     * @param busyKey the key to differentiate between busy hour and busy day
     * @param tzOffset the time zone offset
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJsonGridDataArrayForSubBIBusyWithAppendedRows(final List<ResultSet> results,
            final String busyKey, final String tzOffset) throws SQLException {
        if (results.isEmpty()) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final SubBIDataHolder subBIDataHolder = new SubBIDataHolder();
        for (final ResultSet rs : results) {
            toJsonGridDataArrayForSubBIBusy(rs, busyKey, tzOffset, subBIDataHolder);
        }
        final StringBuilder chartHeader = new StringBuilder();
        chartHeader.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(0).append("\",").append(Y_AXIS_MAX)
                .append(COLON).append(INVERTED_COMMAS).append(subBIDataHolder.maxNoOfEvents).append(INVERTED_COMMAS)
                .append(COMMA);
        //create the JSON format
        return toChartJSONStringForSUBBIBusy(subBIDataHolder.subBIBusyMap, chartHeader);
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     * The idea is to manipulate result set when there is no event for particular day/hour in the database
     *
     * @param rs      the result set
     * @param busyKey the key to differentiate between busy hour and busy day
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static void toJsonGridDataArrayForSubBIBusy(final ResultSet rs, final String busyKey, final String tzOffset,
            final SubBIDataHolder subBIDataHolder) throws SQLException {
        if (rs == null) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        SimpleDateFormat formatter = null;
        //Initialise subBIBusyMap with zero values for all possible keys
        //for busyDay - key is day, for busyHour - key is hour
        if (busyKey == SUBBI_BUSY_DAY) {
            formatter = new SimpleDateFormat(DATE_PATTERN);
            formatter.setTimeZone(TimeZone.getTimeZone(UTC));

            for (int i = 1; i < weekdays.length; i++) {
                if (!subBIDataHolder.subBIBusyMap.containsKey(weekdays[i])) {
                    final SubBIStatistics subBIStat = getSubBIStat(ZERO, ZERO);
                    subBIDataHolder.subBIBusyMap.put(weekdays[i], subBIStat);
                }
            }
        } else if (busyKey == SUBBI_BUSY_HOUR) {
            for (int i = 0; i < 24; i++) {
                if (!subBIDataHolder.subBIBusyMap.containsKey(i + "")) {
                    final SubBIStatistics subBIStat = getSubBIStat(ZERO, ZERO);
                    subBIDataHolder.subBIBusyMap.put(i + "", subBIStat);
                }
            }
        }
        //take into account tzOffset
        final int offsetHour = getTzOffsetInHours(tzOffset);
        //update subBIBusyMap with values coming from result set
        if (rs.next()) {
            do {
                final String err, suc;
                String key = "";
                if (busyKey.equalsIgnoreCase(SUBBI_BUSY_HOUR)) {
                    //update time offset
                    /* If the hour + offset is greater than or equal to 24
                    then for example 23(hour)+ 2(offset) = 25, the modulus will return 1
                    that is next day busy hour, append the value to the same hour if time
                     range is more than a day
                    */
                    final int totalHours = (Integer.parseInt(rs.getString(3)) + offsetHour);
                    if (totalHours < 0) {
                        key = "" + ((HOURS_IN_A_DAY + totalHours) % HOURS_IN_A_DAY);
                    } else {
                        key = "" + (totalHours % HOURS_IN_A_DAY);
                    }
                } else {
                    key = rs.getString(3);
                }
                if (subBIDataHolder.subBIBusyMap.containsKey((key))) {
                    //gets the mapped values for this key
                    final SubBIStatistics subBIStat = (SubBIStatistics) (subBIDataHolder.subBIBusyMap.get(key));
                    err = subBIStat.noOfErr;
                    suc = subBIStat.noOfSuc;
                } else {
                    //if no map found then use default value
                    err = ZERO;
                    suc = ZERO;
                }
                if (err.equals(ZERO) && suc.equals(ZERO)) {
                    //first time updates for this key
                    final int noOfErr = Integer.parseInt(rs.getString(1));
                    final int noOfSuc = Integer.parseInt(rs.getString(2));
                    if ((subBIDataHolder.maxNoOfEvents < noOfErr) || (subBIDataHolder.maxNoOfEvents < noOfSuc)) {
                        subBIDataHolder.maxNoOfEvents = Math.max(noOfErr, noOfSuc);
                    }
                    subBIDataHolder.subBIBusyMap.put(key,
                            getSubBIStat(String.valueOf(noOfErr), String.valueOf(noOfSuc)));
                } else {
                    //later updates for this key
                    final int totErr = Integer.parseInt(rs.getString(1)) + Integer.parseInt(err);
                    final int totSuc = Integer.parseInt(rs.getString(2)) + Integer.parseInt(suc);
                    if ((subBIDataHolder.maxNoOfEvents < totErr) || (subBIDataHolder.maxNoOfEvents < totSuc)) {
                        subBIDataHolder.maxNoOfEvents = Math.max(totErr, totSuc);
                    }
                    subBIDataHolder.subBIBusyMap.put(key, getSubBIStat(totErr + "", totSuc + ""));
                }
            } while (rs.next());
        }
    }

    /*
     * Constructs the data part of the JSON object for SubBI Busy day or Hour only
     * @param subBIBusyMap containing data related to busy hour or day
     * @param chartHeader containing the data related to Y axis min and max values
     * @return the JSON object as string containing chart data
     */
    private static String toChartJSONStringForSUBBIBusy(final Map<String, Object> subBIBusyMap,
            final StringBuilder chartHeader) {
        if ((subBIBusyMap == null) || subBIBusyMap.isEmpty()) {
            final StringBuilder emptyData = chartHeader.append(DATA_ARRAY_BEGIN).append(ARRAY_END);
            return emptyData.toString();
        }
        final Iterator<Map.Entry<String, Object>> it = subBIBusyMap.entrySet().iterator();
        final StringBuilder result = chartHeader.append(DATA_ARRAY_BEGIN);
        int index = 1;
        while (it.hasNext()) {
            if (index > 1) {
                result.append(DELIMITER);
            }
            result.append(OPEN_BRACE);
            //get the mapped values
            final Map.Entry<String, Object> pairs = it.next();
            final SubBIStatistics subBIStat = (SubBIStatistics) pairs.getValue();
            //put the values in proper order
            result.append(formatColumnIndex(1)).append(COLON).append(formatValue(subBIStat.noOfErr)).append(DELIMITER);
            result.append(formatColumnIndex(2)).append(COLON).append(formatValue(subBIStat.noOfSuc)).append(DELIMITER);
            result.append(formatColumnIndex(3)).append(COLON).append(formatValue(pairs.getKey()));
            result.append(CLOSE_BRACE);
            index++;
        }
        result.append(ARRAY_END);
        return result.toString();
    }

    /*
     * This method converts timezone offset string to hours
     */
    private static int getTzOffsetInHours(final String tzOffset) {
        int offsetHour = 0;
        if (tzOffset != null) {
            String tzSign = "";
            if (tzOffset.substring(0, 1).equals("-")) {
                tzSign = "-";
            }
            offsetHour = Integer.parseInt(tzSign + tzOffset.substring(1, 3));
        }
        return offsetHour;
    }

    /**
     * @param noOfErr number of errors events
     * @param noOfSuc number of success events
     * @return a new instance of SubBIStat
     */
    private static SubBIStatistics getSubBIStat(final String noOfErr, final String noOfSuc) {
        return new SubBIStatistics(noOfErr, noOfSuc);
    }

    /**
     * inner class used for Sub BI Busy statistics
     */
    static class SubBIStatistics {
        final String noOfErr;

        final String noOfSuc;

        public SubBIStatistics(final String noOfErr, final String noOfSuc) {
            this.noOfErr = noOfErr;
            this.noOfSuc = noOfSuc;
        }
    }

    /**
     * Put together JSON for no licensed features error.
     * For an input list of one entry of NiceFeature, this method will return:
     * {"success":"false","errorDescription":"The feature NiceFeature is not licensed}
     * For an input list of two entries, NiceFeature1 and NiceFeature2, this method will return
     * {"success":"false","errorDescription":"The features Nicefeature2, NiceFeature2 are not licensed}
     *
     * @param features list of features that are unlicensed
     * @return json, see above
     */
    public static String JSONNoLicensedFeaturesError(final List<String> features) {
        final StringBuilder errorDescription = new StringBuilder("The feature");
        errorDescription.append(getNounEnding(features));
        errorDescription.append(SINGLE_SPACE);
        for (int i = 0; i < features.size(); i++) {
            errorDescription.append(features.get(i));
            if (i < (features.size() - 1)) {
                errorDescription.append(COMMA);
                errorDescription.append(SINGLE_SPACE);
            }
        }
        errorDescription.append(SINGLE_SPACE);
        errorDescription.append(getVerb(features));
        errorDescription.append(" not licensed");
        return createJSONErrorResult(errorDescription.toString());
    }

    private static String getVerb(final List<String> objectList) {
        if (objectList.size() > 1) {
            return "are";
        }
        return "is";
    }

    private static String getNounEnding(final List<String> objectList) {
        if (objectList.size() > 1) {
            return "s";
        }
        return "";
    }

    /**
     * Convert the results in a ResultSet to a map
     * This method will loop over a result set - each row in the result set will become an entry in the map,
     * where the key in the map is the first column, and the value is the second column (transformed from a string
     * seperated list to a list of strings)
     *
     * @param rs result set from query
     * @return see javadoc above
     */
    public static Map<String, List<String>> toMap(final ResultSet rs) throws SQLException {
        final Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        while (rs.next()) {
            final String key = rs.getString(1);
            final String valueAsStringifiedList = rs.getString(2);
            resultMap.put(key, convertToList(valueAsStringifiedList));
        }
        return resultMap;
    }

    private static List<String> convertToList(final String valueAsStringifiedList) {
        if (valueAsStringifiedList == null) {
            return new ArrayList<String>();
        }
        final StringTokenizer tokenizer = new StringTokenizer(valueAsStringifiedList, COMMA);
        final List<String> list = new ArrayList<String>();
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;
    }

    /**
     * inner class used for Sub BI Busy statistics Roaming
     */
    static class SubBIStatisticsRoaming {
        final String noOfErr;

        final String noOfSuc;

        final String noOfImsub;

        public SubBIStatisticsRoaming(final String noOfErr, final String noOfSuc, final String noOfImsub) {
            this.noOfErr = noOfErr;
            this.noOfSuc = noOfSuc;
            this.noOfImsub = noOfImsub;
        }
    }

    /**
     * inner class used for Sub BI Busy data
     */
    static class SubBIDataHolder {

        Map<String, Object> subBIBusyMap = new LinkedHashMap<String, Object>();

        int maxNoOfEvents;

    }

    Map<String, Object> subBIBusyMap = new LinkedHashMap<String, Object>();

    /*
     * Constructs the data part of the JSON object for SubBI Busy day or Hour only
     * @param subBIBusyMap containing data related to busy hour or day
     * @param chartHeader containing the data related to Y axis min and max values
     * @return the JSON object as string containing chart data
     */
    private static String toChartJSONStringForSUBBIBusyRoaming(final Map<String, Object> subBIBusyMap,
            final StringBuilder chartHeader) {
        if ((subBIBusyMap == null) || subBIBusyMap.isEmpty()) {
            final StringBuilder emptyData = chartHeader.append(DATA_ARRAY_BEGIN).append(ARRAY_END);
            return emptyData.toString();
        }
        final Iterator<Map.Entry<String, Object>> it = subBIBusyMap.entrySet().iterator();
        final StringBuilder result = chartHeader.append(DATA_ARRAY_BEGIN);
        int index = 1;
        while (it.hasNext()) {
            if (index > 1) {
                result.append(DELIMITER);
            }
            result.append(OPEN_BRACE);
            //get the mapped values
            final Map.Entry<String, Object> pairs = it.next();
            final SubBIStatisticsRoaming subBIStat = (SubBIStatisticsRoaming) pairs.getValue();
            //put the values in proper order
            result.append(formatColumnIndex(1)).append(COLON).append(formatValue(pairs.getKey())).append(DELIMITER);
            result.append(formatColumnIndex(2)).append(COLON).append(formatValue(subBIStat.noOfErr)).append(DELIMITER);
            result.append(formatColumnIndex(3)).append(COLON).append(formatValue(subBIStat.noOfSuc)).append(DELIMITER);
            result.append(formatColumnIndex(4)).append(COLON).append(formatValue(subBIStat.noOfImsub));
            result.append(CLOSE_BRACE);
            index++;
        }
        result.append(ARRAY_END);
        return result.toString();
    }

    /**
     * Get Json data for subBI - busy data (hour or day).
     * To manipulate list of result set and add the value from different result sets for
     * the corresponding busy hour OR busy day value
     *
     * @param results  the result set
     * @param busyKey  the key to differentiate between busy hour and busy day
     * @param tzOffset the time zone offset
     * @return the json string
     * @throws SQLException the SQL exception
     */
    public static String toJsonGridDataArrayForRoamingAppendedRows(final List<ResultSet> results, final String busyKey,
            final String tzOffset) throws SQLException {
        if (results.isEmpty()) {
            throw new IllegalArgumentException(RESULT_SET_MAY_NOT_BE_NULL);
        }
        final SubBIDataHolder subBIDataHolder = new SubBIDataHolder();

        final List<ResultSet> subResultsRsList = results.subList(0, results.size() - 1);

        for (final ResultSet rs : subResultsRsList) {
            toJsonGridDataArrayForSubBIBusy(rs, busyKey, tzOffset, subBIDataHolder);
        }

        toAddImpactedSubscribersRoamingAppendedRows(results.get(results.size() - 1), subBIDataHolder);

        final StringBuilder chartHeader = new StringBuilder();
        chartHeader.append(Y_AXIS_MIN).append(COLON).append(INVERTED_COMMAS).append(0).append("\",").append(Y_AXIS_MAX)
                .append(COLON).append(INVERTED_COMMAS).append(subBIDataHolder.maxNoOfEvents).append(INVERTED_COMMAS)
                .append(COMMA);
        //create the JSON format
        return toChartJSONStringForSUBBIBusyRoaming(subBIDataHolder.subBIBusyMap, chartHeader);
    }

    public static void toAddImpactedSubscribersRoamingAppendedRows(final ResultSet imSubRs,
            final SubBIDataHolder subBIDataHolder) {

        String key = "";
        SubBIStatistics value = null;
        SubBIStatisticsRoaming newValue = null;
        final Map<String, Object> roamingMap = new LinkedHashMap<String, Object>();

        try {
            while (imSubRs.next()) {
                key = imSubRs.getString(1);
                value = (SubBIStatistics) subBIDataHolder.subBIBusyMap.get(key);
                newValue = new SubBIStatisticsRoaming(value.noOfErr, value.noOfSuc, imSubRs.getString(2));
                roamingMap.put(key, newValue);
            }
            subBIDataHolder.subBIBusyMap = roamingMap;
        } catch (final SQLException e) {
            ServicesLogger.error(JSONUTILS, "toAddImpactedSubscribersRoamingAppendedRows", e);
        }
    }

    public static String listToJsonArray(final List<Object> values) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPEN_BRACE);
        for (int i = 0; i < values.size(); i++) {
            stringBuilder.append(INVERTED_COMMAS);
            stringBuilder.append(i + 1);
            stringBuilder.append(INVERTED_COMMAS);
            stringBuilder.append(COLON);
            stringBuilder.append(INVERTED_COMMAS);
            stringBuilder.append(values.get(i));
            stringBuilder.append(INVERTED_COMMAS);
            if (i != values.size() - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(CLOSE_BRACE);
        return stringBuilder.toString();
    }

    /**
     * Transform SQL result set to a key/value map
     * The first column in the result set is the key
     * The second columns is the value
     *
     * @param rs                result set from SQL
     * @return  map of values in result set
     */
    public static Map<String, String> toSingleValueMap(final ResultSet rs) throws SQLException {
        final Map<String, String> map = new HashMap<String, String>();
        while (rs.next()) {
            final String key = rs.getString(1);
            final String value = rs.getString(2);
            map.put(key, value);
        }
        return map;
    }

    /**
     * Generic JSON success result object.The data field is empty, success is true.
     *
     * @return JSON Empty JSON result with success set to TRUE
     * @param dataPayLoad The JSON data that needs to be wrapped with "success":"true",...
     */
    public static String JSONDataSuccessResult(final String dataPayLoad) {
        final StringBuilder result = new StringBuilder(OPEN_BRACE);
        result.append(getJSONHeader(true, "")).append(COMMA);
        result.append(DATA_ARRAY_BEGIN).append(dataPayLoad).append(ARRAY_END);
        result.append(CLOSE_BRACE);
        return result.toString();
    }
}
