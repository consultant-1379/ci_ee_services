/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eemecoy
 *
 */
public class JSONUtilsConstants {

    public static final String DATA = "data";

    public static final String ARRAY_START = "[";

    public static final String DATA_OBJECT = "\"" + DATA + "\":";

    public static final String DATA_ARRAY_BEGIN = "\"" + DATA + "\":" + ARRAY_START;

    public static final String VALUES_BEGIN = "\"values\":" + ARRAY_START;

    public static final String NVLIST_NAME = "\"name\" : \"";

    public static final String NVLIST_ID = "\"id\" : \"";

    public static final String ARRAY_END = "]";

    public static final String CLOSE_BRACE = "}";

    public static final String OPEN_BRACE = "{";

    public static final String CLOSE_BRACKET = ")";

    public static final String SUCCESS = "\"success\"";

    public static final String ERROR_DESCRIPTION = "\"errorDescription\"";

    public static final String LIVELOAD_TOTAL_KWORD = "${TOTAL}";

    public static final String LIVELOAD_TOTAL_HEADER = "({\"totalCount\":\"" + LIVELOAD_TOTAL_KWORD + "\",";

    public static final String LIVELOAD_URL = "\"liveLoadURL\" : \"";

    public static final String PATH_END = "?";

    public static final String ID_EQUALS = "id=";

    public static final String QUOTE = "\"";

    public static final String HASH = "#";

    public static final String DATA_TIME_FROM = "dataTimeFrom";

    public static final String PIPE = "|";

    public static final String DATA_TIME_TO = "dataTimeTo";

    public static final String TIMEZONE = "timeZone";

    public static final String ONE_SPACE = " ";

    public static final Map<Character, String> ESCAPE_MAPPINGS = new HashMap<Character, String>();

    public static final String UTC = "UTC";

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String INVERTED_COMMAS = "\"";

    public static final String ZERO = "0";

    public static final String DECIMAL_POINT = ".";

    public static final String NULL = "null";

    static {
        ESCAPE_MAPPINGS.put('"', "\\\"");
        ESCAPE_MAPPINGS.put('\\', "\\\\");
    }

}
