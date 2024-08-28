/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.tablesandviews;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Holder of table/view names for a particular techpack - used by templates.
 * 
 * @author EEMECOY
 *
 * 
 */
public class TechPack {

    private final Map<String, List<String>> rawErrTablesMap;

    private final Map<String, List<String>> rawSucTablesMap;

    private final Map<String, String> errAggregationView;

    private final Map<String, String> sucAggregationView;

    /** The dt tables. */
    private List<String> dtTables;

    /** The dt pdp tables. */
    private List<String> dtPdpTables;

    /** The dt aggregation view. */
    private String dtAggregationView;

    /** The dt pdp aggregation view. */
    private String dtPdpAggregationView;

    /** The table type. */
    private final TableType tableType;

    /** The static lookup tech pack. */
    private final String staticLookupTechPack;

    /** The tech pack name. */
    private final String techPackName;

    /**
     * The Constructor.
     *
    * @param techPackName
    *          EVENT_E_SGEH or EVENT_E_LTE
    * @param tableType
    *          whether its raw or aggregation
    * @param staticLookupTechPack
    *          the matching static lookup techpack eg DIM_E_SGEH, DIM_E_LTE or
    *          DIM_E_MSS
     */
    public TechPack(final String techPackName, final TableType tableType, final String staticLookupTechPack) {
        this.techPackName = techPackName;
        this.tableType = tableType;
        this.staticLookupTechPack = staticLookupTechPack;
        this.rawErrTablesMap = new HashMap<String, List<String>>();
        this.errAggregationView = new HashMap<String, String>();
        this.rawSucTablesMap = new HashMap<String, List<String>>();
        this.sucAggregationView = new HashMap<String, String>();
    }

    /**
    * This method will return the list of tables for a particular key
    * in the raw error tables map if the table type is RAW else
    * the aggregation view by default 
     * 
    * @param key the map the list of error type tables
    * @return an object representing a list of error tables or aggregation view
    */
    public Object getErrTables(final String key) {
        if (tableType == TableType.AGGREGATION) {
            return errAggregationView.get(key.toUpperCase());
        }
        return rawErrTablesMap.get(key.toUpperCase());

    }

    /**
    * Get the error tables or view name to use in query If its an aggregation
    * query - then one string is returned, that of the aggregation error view If
    * its a raw query - then a list of strings is returned, each one a raw error
    * table to include in the query
    * 
    * Note - return method is ambiguous to allow the templates to use it in
    * different contexts
    */
    public Object getErrTables() {
        return this.getErrTables(KEY_TYPE_ERR);
    }

    /**
     * Set the raw err tables in this holder class Example raw err tables are
     * EVENT_E_SGEH_ERR_RAW_01 or EVENT_E_LTE_ERR_RAW_02
       * 
       * @param rawErrTables the err raw tables
       */
    public void setErrRawTables(final List<String> rawErrTables) {
        this.setErrRawTables(KEY_TYPE_ERR, rawErrTables);
    }

    /**
     * Set the raw err tables in this holder class Example raw err tables are
     * EVENT_E_SGEH_ERR_RAW_01 or EVENT_E_LTE_ERR_RAW_02
     * 
     * @param rawErrTables
     */
    public void setErrRawTables(final String key, final List<String> rawErrTables) {
        this.rawErrTablesMap.put(key.toUpperCase(), rawErrTables);
    }

    /**
     * Set the raw suc tables in this holder class Example raw suc tables are
     * EVENT_E_SGEH_SUC_RAW_01 or EVENT_E_SUC_ERR_RAW_02
       * 
       * @param sucRawTables the suc raw tables
       */
    public void setSucRawTables(final List<String> sucRawTables) {
        this.setSucRawTables(KEY_TYPE_SUC, sucRawTables);

    }

    /**
     * Set the raw suc tables in this holder class Example raw err tables are
     * EVENT_E_SGEH_ERR_RAW_01 or EVENT_E_LTE_ERR_RAW_02 against the corresponding
     * key
     * 
     * @param rawErrTables
     */
    public void setSucRawTables(final String key, final List<String> sucRawTables) {
        this.rawSucTablesMap.put(key.toUpperCase(), sucRawTables);
    }

    /**
     * Checks for any tables.
     *
    * @return true if tech pack's error tables and success tables have been set,
    *         false otherwise
     */
    public boolean hasAnyTables() {
        return this.getTables(rawErrTablesMap).size() > 0 && this.getTables(rawSucTablesMap).size() > 0;
    }

    private List<String> getTables(final Map<String, List<String>> map) {
        final Iterator<List<String>> it = map.values().iterator();
        final List<String> listOfTables = new ArrayList<String>();
        while (it.hasNext()) {
            final List<String> list = it.next();
            if (list != null) {
                listOfTables.addAll(list);
            }
        }
        return listOfTables;
    }

    /**
     * Get the success tables or view name to use in query If its an aggregation
     * query - then one string is returned, that of the aggregation suc view If
     * its a raw query - then a list of strings is returned, each one a raw suc
     * table to include in the query
     * 
     * Note - return method is ambiguous to allow the templates to use it in
     * different contexts
       * Checks for any dt put tables.
       *
       * @return true, if checks for any dt put tables
       */
    public boolean hasAnyDTPutTables() {
        return dtTables != null || dtPdpTables != null;
    }

    /**
     * Note - return method is ambiguous to allow the templates to use it in different contexts.
     *
     * @return the suc tables
     */
    public Object getSucTables() {
        return getSucTables(KEY_TYPE_SUC);
    }

    /**
     * getSucTables is used to get suc tables
     * @param key
     * @return
     */
    public Object getSucTables(final String key) {
        if (tableType == TableType.AGGREGATION) {
            return sucAggregationView.get(key.toUpperCase());
        }
        return rawSucTablesMap.get(key.toUpperCase());
    }

    /**
     * Sets the err aggregation view.
     *
     * @param errorAggregationView the err aggregation view
     */
    public void setErrAggregationView(final String errorAggregationView) {
        this.errAggregationView.put(KEY_TYPE_ERR, errorAggregationView);
    }

    /**
     * Sets the err aggregation view.
     *
     * @param key denotes it is an error or drop call. KEY_TYPE_DROP_CALL or KEY_TYPE_ERR
     * @param errorAggregationView the err aggregation view
     */
    public void setErrAggregationView(final String key, final String errorAggregationView) {
        this.errAggregationView.put(key.toUpperCase(), errorAggregationView);
    }

    /**
     * Sets the suc aggregation view.
     *
     * @param sucAggregationView the suc aggregation view
     */
    public void setSucAggregationView(final String aSucAggregationView) {
        this.sucAggregationView.put(KEY_TYPE_SUC, aSucAggregationView);
    }

    /**
     * Sets the suc aggregation view.
     *
     * @param aSucAggregationView the suc aggregation view
     */
    public void setSucAggregationView(final String key, final String aSucAggregationView) {
        this.sucAggregationView.put(key.toUpperCase(), aSucAggregationView);
    }

    /**
     * Get the dt tables or view name to use in query
     * If its an aggregation query - then one string is returned, that of the aggregation error view
     * If its a raw query - then a list of strings is returned, each one a raw error table to include in the query
     * 
     * Note - return method is ambiguous to allow the templates to use it in different contexts.
     *
     * @return the dt tables
     */
    public Object getDtTables() {
        if (tableType == TableType.AGGREGATION) {
            return dtAggregationView;
        }
        return dtTables;
    }

    /**
     * Get the dtPDP tables or view name to use in query
     * If its an aggregation query - then one string is returned, that of the aggregation error view
     * If its a raw query - then a list of strings is returned, each one a raw error table to include in the query
     * 
     * Note - return method is ambiguous to allow the templates to use it in different contexts.
     *
     * @return the dtPDP tables
     */
    public Object getDtPdpTables() {
        if (tableType == TableType.AGGREGATION) {
            return dtPdpAggregationView;
        }
        return dtPdpTables;
    }

    /**
     * Sets the dt raw tables.
     *
     * @param rawDtTables the dt raw tables
     */
    public void setDtRawTables(final List<String> rawDtTables) {
        this.dtTables = rawDtTables;

    }

    /**
     * Sets the dtpdp raw tables.
     *
     * @param rawDtPdpTables the dt pdp raw tables
     */
    public void setDtPdpRawTables(final List<String> rawDtPdpTables) {
        this.dtPdpTables = rawDtPdpTables;

    }

    /**
     * Sets the dt aggregation view.
     *
     * @param dtAggregationView the dt aggregation view
     */
    public void setDtAggregationView(final String dtAggregationView) {
        this.dtAggregationView = dtAggregationView;

    }

    /**
     * Sets the dt pdp aggregation view.
     *
     * @param dtPdpAggregationView the dt pdp aggregation view
     */
    public void setDtPdpAggregationView(final String dtPdpAggregationView) {
        this.dtPdpAggregationView = dtPdpAggregationView;

    }

    /**
     * Gets the static lookup techpack.
     *
     * @return the static lookup techpack
     */
    public String getStaticLookupTechpack() {
        return staticLookupTechPack;
    }

    /**
     * Gets the tech pack name.
     *
     * @return the tech pack name
     */
    public String getTechPackName() {
        return techPackName;
    }

    /*
     * (non-Javadoc)
     * 
       * @see java.lang.Object#toString()
       */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tech Pack Name: ");
        sb.append(techPackName);
        sb.append(COMMA);
        sb.append(SQUARE_OPENING_BRACKET);
        if (tableType == TableType.AGGREGATION) {
            if (!techPackName.equals(EVENT_E_GSN_DT_TPNAME) && !techPackName.equals(EVENT_E_GSN_DTPDP_TPNAME)) {
                getAggregationTableDetails(sb);
            } else {
                getDTPutAggregationTableDetails(sb);
            }
        } else {
            if (!techPackName.equals(EVENT_E_GSN_DT_TPNAME) && !techPackName.equals(EVENT_E_GSN_DTPDP_TPNAME)) {
                getRawTableDetails(sb);
            } else {
                getDTPutRawTableDetails(sb);
            }
        }
        sb.append(SQUARE_CLOSING_BRACKET);
        return sb.toString();
    }

    /**
     * Gets the aggregation table details.
     *
     * @param sb the sb
     */
    private void getAggregationTableDetails(final StringBuilder sb) {
        sb.append(sucAggregationView);
        sb.append(COMMA);
        sb.append(errAggregationView);
    }

    /**
     * Gets the dtput aggregation table details.
     *
     * @param sb the sb
     */
    private void getDTPutAggregationTableDetails(final StringBuilder sb) {
        sb.append(dtAggregationView);
        sb.append(COMMA);
        sb.append(dtPdpAggregationView);
    }

    /**
     * Gets the raw table details.
     *
     * @param sb the sb
     */
    private void getRawTableDetails(final StringBuilder sb) {
        final List<String> errTables = this.getTables(rawErrTablesMap);
        for (final String table : errTables) {
            sb.append(table);
            sb.append(COMMA);
        }
        final List<String> sucTables = this.getTables(rawSucTablesMap);
        for (final String table : sucTables) {
            sb.append(table);
            sb.append(COMMA);
        }
    }

    /**
     * Gets the dtput raw table details.
     *
     * @param sb the sb
     */
    private void getDTPutRawTableDetails(final StringBuilder sb) {
        if (dtTables != null) {
            for (final String table : dtTables) {
                sb.append(table);
                sb.append(COMMA);
            }
        } else {
            for (final String table : dtPdpTables) {
                sb.append(table);
                sb.append(COMMA);
            }
        }

    }

}
