/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

/**
 * Pattern constants for validating the values of Parameters.
 * 
 * @author ebhasou
 * 
 */

public class ParameterPatternConstants {
    public static final String APN_PATTERN = "^[A-Za-z0-9\\:._-]+$";

    public static final String SGSN_PATTERN = "^[A-Za-z0-9\\:._-]+$";

    public static final String MSC_PATTERN = "^[A-Za-z0-9\\:._-]+$";

    public static final String BSC_PATTERN = "^[A-Za-z0-9\\:._-]+,+[A-Za-z0-9\\:._-]+,+[A-Za-z0-9]+$";

    public static final String CELL_PATTERN = "^[A-Za-z0-9\\:._-]+,+[A-Za-z0-9\\:._-]*,+[A-Za-z0-9\\:._-]+,+[A-Za-z0-9\\:._-]+,+[A-Za-z0-9]+$";

    public static final String TAC_PATTERN = "^.+,+[0-9]+$";

    public static final String IMSI_PATTERN = "^[0-9]{1,18}$";

    public static final String TAC_GROUP_ELEMENT_PATTERN="^[0-9]{1,9}$";

    public static final String GROUPNAME_PATTERN = "^[A-Za-z][0-9a-zA-Z-_]{0,34}$";

    public static final String IMPORTED_FILE_KEY_PATTERN= "^[^\\t\\n\\r\\f\\a\\e\\\\/\\\",]+$";

    public static final String EVENTS_PATTERN = "^[A-Za-z0-9\\:._-]+,+[0-9]+$";

    public static final String SUBBI_TAU_PATTERN = "^.+,+.+$";

    public static final String SUBBI_HANDOVER_PATTERN = "^.+,+.+$";
    
    public static final String MSISDN_PATTERN = "^[0-9]{1,15}$"; 

}
