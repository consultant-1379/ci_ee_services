/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.json;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.COLON;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.COMMA;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DELIMITER;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EMPTY_STRING;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.ID;
import static com.ericsson.eniq.events.server.common.LiveLoadConstants.LIVELOAD_TYPE_BSC;
import static com.ericsson.eniq.events.server.common.LiveLoadConstants.LIVELOAD_TYPE_CELL;
import static com.ericsson.eniq.events.server.common.LiveLoadConstants.LIVE_LOAD_TYPE_COUNTRY;
import static com.ericsson.eniq.events.server.common.LiveLoadConstants.LIVE_LOAD_TYPE_OPERATOR;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.ARRAY_END;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.ARRAY_START;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.CLOSE_BRACE;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.CLOSE_BRACKET;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.DATA;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.DATA_ARRAY_BEGIN;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.ID_EQUALS;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.LIVELOAD_TOTAL_HEADER;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.LIVELOAD_TOTAL_KWORD;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.LIVELOAD_URL;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.NVLIST_ID;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.ONE_SPACE;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.OPEN_BRACE;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.PATH_END;
import static com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants.QUOTE;

/**
 * Utilities for transforming results for Live Load queries to expected JSON
 *
 * @author eemecoy
 */
public abstract class JSONLiveLoadUtils {

    /**
     * Convert the results of a LiveLoad/Search query to the Ext GWT format expected.
     * The JSON returned from this method will not have the success or errorDescription fields.
     *
     * @param liveLoadType the Live Load Type e.g. APN, CELL, BSC etc.
     * @param callbackId   The Callback ID, passed from the client
     * @param pagingIndex  start index in result
     * @param pagingLimit  maximum number of rows requested for this result
     * @param rs           The ResultSet to convert.
     * @return JSON for the ExtGWT LiveSearch feature e.g. {transId2({"totalCount":"1","APN" : [{"id" : "blackberry.net"}]})}
     * @throws SQLException Errors iterating over ResultSet
     */
    public static String toJSONLiveLoad(final String liveLoadType, final String callbackId, final String pagingLimit,
                                        final String pagingIndex, final ResultSet rs)
            throws SQLException {
        final StringBuilder json = appendHeaderUpToDataStartForLiveLoad(liveLoadType, callbackId);
        final int totalNumberRows = appendLiveLoadData(liveLoadType, rs, json, pagingLimit, pagingIndex);
        final String resultAsString = appendLiveLoadEndTags(json, totalNumberRows);
        return resultAsString;
    }

    private static int appendLiveLoadData(final String liveLoadType, final ResultSet rs, final StringBuilder json,
                                          final String pagingLimit, final String pagingIndex)
            throws SQLException {
        int numberRowsInResultFromDB = 0;
        final ResultSetMetaData rsMetaData = rs.getMetaData();
        final int columnCount = rsMetaData.getColumnCount();
        final int pagingLimitIntValue = Integer.parseInt(pagingLimit);
        final int pagingIndexIntValue = Integer.parseInt(pagingIndex);
        int numberRowsInThisPage = 0;
        while (rs.next()) {
            if (numberRowsInThisPage < pagingLimitIntValue && numberRowsInResultFromDB >= pagingIndexIntValue) {
                final String rowData = JSONLiveLoadUtils.concatRowValues(columnCount, rs, liveLoadType);
                json.append(OPEN_BRACE).append(QUOTE + ID + QUOTE + COLON).append(rowData).append(CLOSE_BRACE)
                        .append(COMMA);
                numberRowsInThisPage++;
            }
            numberRowsInResultFromDB++;
        }
        if (numberRowsInResultFromDB > 0) {
            json.deleteCharAt(json.length() - 1);
        }
        return numberRowsInResultFromDB;
    }

    private static String appendLiveLoadEndTags(final StringBuilder json, final int total) {
        json.append(ARRAY_END).append(CLOSE_BRACE).append(CLOSE_BRACKET);
        final String result = json.toString().replace(LIVELOAD_TOTAL_KWORD, "" + total);
        return result;
    }

    private static StringBuilder appendHeaderUpToDataStartForLiveLoad(final String liveLoadType, final String callbackId) {
        final StringBuilder json = new StringBuilder();
        json.append(callbackId).append(LIVELOAD_TOTAL_HEADER);
        json.append(QUOTE);
        if (liveLoadType == null) {
            json.append(DATA);
        } else {
            json.append(JSONUtils.jsonEscapedString(liveLoadType));
        }
        json.append(QUOTE).append(ONE_SPACE).append(COLON).append(ONE_SPACE).append(ARRAY_START);
        return json;
    }

    /**
     * To json live load handsets metadata ui.
     *
     * @param rs          the rs
     * @param servicesUrl the services url
     * @param handsetPath the handset path
     * @return the string
     * @throws SQLException the SQL exception
     */
    public static String toJSONLiveLoadHandsetsMetadataUI(final ResultSet rs, final String servicesUrl,
                                                          final String handsetPath) throws SQLException {
        final StringBuilder json = new StringBuilder();
        json.append(OPEN_BRACE).append(JSONUtils.getJSONSuccessHeader()).append(COMMA).append(DATA_ARRAY_BEGIN);
        final ResultSetMetaData rsMetaData = rs.getMetaData();
        final int columnCount = rsMetaData.getColumnCount();
        while (rs.next()) {
            final String rowData = JSONLiveLoadUtils.concatRowValues(columnCount, rs);
            json.append(OPEN_BRACE);
            json.append(NVLIST_ID).append(rowData).append(QUOTE).append(COMMA);
            final String idParameter = ID_EQUALS + rowData;
            final String liveLoadURL = servicesUrl + handsetPath + PATH_END + idParameter;
            json.append(LIVELOAD_URL).append(liveLoadURL).append(QUOTE);
            json.append(CLOSE_BRACE).append(COMMA);
        }
        json.append(ARRAY_END).append(CLOSE_BRACE);
        return json.toString();
    }

    /**
     * Concat row values.
     *
     * @param columnCount  the column count
     * @param rs           the rs
     * @param liveLoadType BSC or CELL
     * @return the string
     * @throws SQLException the SQL exception
     */
    private static String concatRowValues(final int columnCount, final ResultSet rs, final String liveLoadType) throws SQLException {
        final StringBuilder rowData = new StringBuilder();
        if (LIVE_LOAD_TYPE_COUNTRY.equalsIgnoreCase(liveLoadType) || LIVE_LOAD_TYPE_OPERATOR.equals(liveLoadType)) {
            rowData.append(OPEN_BRACE+ "\"1\":" +"\""+JSONUtils.jsonEscapedString(rs.getString(1))+"\"" + COMMA + "\"2\":" + "\""+JSONUtils.jsonEscapedString(rs.getString(2))+"\"" +CLOSE_BRACE);
            return rowData.toString().trim();
        }

        for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
            String cellValue = rs.getString(colIndex);
            // If HIERARCHY_2 etc is null in database, substitute with EMPTY_STRING to generate proper hashIds
            if (cellValue == null) {
                cellValue = EMPTY_STRING;
            }
            if ((LIVELOAD_TYPE_BSC.equalsIgnoreCase(liveLoadType) || LIVELOAD_TYPE_CELL.equalsIgnoreCase(liveLoadType))
                    && colIndex == columnCount) {
                    rowData.append(cellValue);
            } else {
                rowData.append(cellValue);
            }

            if (colIndex < columnCount) {
                rowData.append(DELIMITER);
            }
        }


        return QUOTE+JSONUtils.jsonEscapedString(rowData.toString().trim())+QUOTE;
    }

    /**
     * Concat row values.
     *
     * @param columnCount the column count
     * @param rs          the rs
     * @return the string
     * @throws SQLException the SQL exception
     */
    static String concatRowValues(final int columnCount, final ResultSet rs) throws SQLException {
        final StringBuilder rowData = new StringBuilder();
        for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
            final String cellValue = rs.getString(colIndex);
            rowData.append(cellValue);
            if (colIndex < columnCount) {
                rowData.append(DELIMITER);
            }
        }
        return JSONUtils.jsonEscapedString(rowData.toString().trim());
    }


}
