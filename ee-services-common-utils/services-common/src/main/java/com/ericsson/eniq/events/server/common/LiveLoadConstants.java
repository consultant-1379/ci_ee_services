/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.common;

/**
 * @author ericker
 */
public final class LiveLoadConstants {
    /**
     * Generic LiveLoad Path for BSC/CELL.
     */
    public static final String LIVELOAD_TYPE_NODE = "liveload_type";

    /**
     * Node LiveLoad type for BSC
     */
    public static final String LIVELOAD_TYPE_BSC = "BSC";

    /**
     * Node LiveLoad type for CELL
     */
    public static final String LIVELOAD_TYPE_CELL = "CELL";

    public static final String DRILLTYPE = null;

    public static final String LIVE_LOAD_TYPE_COUNTRY="COUNTRY_LIVELOAD";

    public static final String LIVE_LOAD_TYPE_OPERATOR="OPERATOR_LIVELOAD";

    private LiveLoadConstants() {
        // Non instantiable
    }
}
