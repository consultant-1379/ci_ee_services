/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.tablesandviews;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Holder for tech pack table/view information for later use in
 * templates/queries.
 * 
 * @author EEMECOY
 */
public class TechPackTables {

    /** The table type. */
    private TableType tableType;

    /** The tech packs. */
    private final List<TechPack> techPacks = new ArrayList<TechPack>();

    private boolean shouldQueryUseAggregationView;

    /**
     * The Constructor.
     * 
     * @param type
     *          - raw or aggregation
     */
    public TechPackTables(final TableType type) {
        this.tableType = type;
    }

    /**
     * 
     */
    public TechPackTables() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Get list of tech packs.
     * 
     * @return the tech packs
     */
    public List<TechPack> getTechPacks() {
        return techPacks;
    }

    /**
     * Get all raw tables for this query Method returns both err and suc tables
     * 
     * Method returns both err and suc tables.
     * 
     * @return the raw tables
     */
    public List<String> getRawTables() {
        final List<String> rawTables = new ArrayList<String>();
        rawTables.addAll(getSucTables());
        rawTables.addAll(getErrTables());
        rawTables.addAll(getDtTables());
        rawTables.addAll(getDtPdpTables());
        return rawTables;
    }

    /**
     * Get all err tables for this query If this object is of type AGGREGATION,
     * then the result will be a list containing the error aggregation view for
     * each tech pack included If this object is of type RAW, then result will be
     * a list containing the error raw tables for each tech pack If this object is
     * of type RAW, then result will be a list containing the error raw tables for
     * each tech pack.
     * 
     * @return the err tables
     */
    public List<String> getErrTables() {
        return this.getErrTables(KEY_TYPE_ERR);
    }

    /**
     * 
     * 
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> getErrTables(final String key) {
        final List<String> errTables = new ArrayList<String>();
        for (final TechPack techPack : techPacks) {
            final Object errorTables = techPack.getErrTables(key);
            if (isAggregation()) {
                errTables.add((String) errorTables);
            } else {
                errTables.addAll((List<String>) errorTables);
            }
        }
        return errTables;
    }

    /**
     * Checks if is aggregation.
     * 
     * @return true, if checks if is aggregation
     */
    private boolean isAggregation() {
        return tableType.equals(TableType.AGGREGATION);
    }

    /**
     * Get all suc tables for this query If this object is of type AGGREGATION,
     * then the result will be a list containing the success aggregation view for
     * each tech pack included If this object is of type RAW, then result will be
     * a list containing the success raw tables for each tech pack If this object
     * is of type RAW, then result will be a list containing the success raw
     * tables for each tech pack.
     * 
     * @return the suc tables
     */
    @SuppressWarnings("unchecked")
    public List<String> getSucTables() {
        return getSucTables(KEY_TYPE_SUC);
    }

    /**
     * This method is used to fetch the list of success raw tables for the given
     * key
     * 
     * @param key
     *          identifies the type of success table
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> getSucTables(final String key) {
        final List<String> sucTables = new ArrayList<String>();
        for (final TechPack techPack : techPacks) {
            final Object successTables = techPack.getSucTables(key);
            if (isAggregation()) {
                sucTables.add((String) successTables);
            } else {
                sucTables.addAll((List<String>) successTables);
            }
        }
        return sucTables;
    }

    /**
     * Get all dt tables for this query If this object is of type AGGREGATION,
     * then the result will be a list containing the error aggregation view for
     * each tech pack included If this object is of type RAW, then result will be
     * a list containing the error raw tables for each tech pack.
     * 
     * @return the dt tables
     */
    @SuppressWarnings("unchecked")
    public List<String> getDtTables() {
        final List<String> DTTables = new ArrayList<String>();
        for (final TechPack techPack : techPacks) {
            final Object dtTables = techPack.getDtTables();
            if (isAggregation() && dtTables != null) {
                DTTables.add((String) dtTables);
            } else if (dtTables instanceof Collection) {
                DTTables.addAll((Collection<String>) dtTables);
            }
        }
        return DTTables;
    }

    /**
     * Get all dtpdp tables for this query If this object is of type AGGREGATION,
     * then the result will be a list containing the error aggregation view for
     * each tech pack included If this object is of type RAW, then result will be
     * a list containing the error raw tables for each tech pack.
     * 
     * @return the dtpdp tables
     */
    @SuppressWarnings("unchecked")
    public List<String> getDtPdpTables() {
        final List<String> DTPDPTables = new ArrayList<String>();
        for (final TechPack techPack : techPacks) {
            final Object dtPdpTables = techPack.getDtPdpTables();
            if (isAggregation() && dtPdpTables != null) {
                DTPDPTables.add((String) dtPdpTables);
            } else if (dtPdpTables instanceof Collection) {
                DTPDPTables.addAll((Collection<String>) dtPdpTables);
            }
        }
        return DTPDPTables;
    }

    /**
     * Add tech pack table details to overall list.
     * 
     * @param techPack
     *          the tech pack
     */
    public void addTechPack(final TechPack techPack) {
        techPacks.add(techPack);

    }

    /**
     * True if this is a raw query, and there are no tech packs to include in
     * query.
     * 
     * @return true, if should report error about empty raw tables
     */
    public boolean shouldReportErrorAboutEmptyRawTables() {
        if (tableType == TableType.RAW) {
            return techPacks.isEmpty();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Table type: ");
        sb.append(tableType);
        sb.append(COMMA);
        for (final TechPack techPack : techPacks) {
            sb.append(SQUARE_OPENING_BRACKET);
            sb.append(techPack.toString());
            sb.append(SQUARE_CLOSING_BRACKET);
        }
        return sb.toString();
    }

    public List<TechPack> getValidTechPacks() {

        // return all tech packs until new licensing feature is introduced
        return getTechPacks();
    }

    public boolean shouldQueryUseAggregationTables() {
        return shouldQueryUseAggregationView;
    }

    public void setShouldQueryUseAggregationView(final boolean shouldQueryUseAggregationView) {
        this.shouldQueryUseAggregationView = shouldQueryUseAggregationView;
    }

    public List<String> getAllRawErrTables() {
        // TODO Auto-generated method stub
        return null;
    }

}
