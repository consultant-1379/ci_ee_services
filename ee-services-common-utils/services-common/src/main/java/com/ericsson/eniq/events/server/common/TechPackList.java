/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

/**
 * List or map of all tech packs and associated tables/views
 * Also contains information on whether this particular object should query the aggregation or raw views for the
 * main query.
 *
 * @author eemecoy
 */
public class TechPackList {

    /**
     * The should query use aggregation view.
     */
    private boolean shouldQueryUseAggregationView;

    /**
     * The tech packs.
     */
    private final Map<String, TechPackRepresentation> techPacks = new HashMap<String, TechPackRepresentation>();

    /**
     * Sets the should query use aggregation view.
     *
     * @param shouldQueryUseAggregationView the should query use aggregation view
     */
    public void setShouldQueryUseAggregationView(final boolean shouldQueryUseAggregationView) {
        this.shouldQueryUseAggregationView = shouldQueryUseAggregationView;

    }

    /**
     * Adds the tech pack.
     *
     * @param techPackName the tech pack name
     * @param techPack     the tech pack
     */
    public void addTechPack(final String techPackName, final TechPackRepresentation techPack) {
        techPacks.put(techPackName, techPack);

    }

    /**
     * Should query use aggregation tables.
     *
     * @return true, if should query use aggregation tables
     */
    public boolean shouldQueryUseAggregationTables() {
        return shouldQueryUseAggregationView;
    }

    /**
     * Gets the all raw err tables.
     *
     * @return the all raw err tables
     */
    public List<String> getAllRawErrTables() {
        final List<String> allRawErrTables = new ArrayList<String>();
        for (final String techPackName : techPacks.keySet()) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            final List<String> rawErrTables = techPack.getRawErrTables();
            if (rawErrTables != null) {
                allRawErrTables.addAll(rawErrTables);
            }
        }

        return allRawErrTables;
    }

    /**
     * Gets the all raw err tables for tech packs.
     *
     * @param tpNames the tp names
     * @return the all raw err tables for tech packs
     */
    public List<String> getAllRawErrTablesForTechPacks(final List<String> tpNames) {
        final List<String> allRawErrTables = new ArrayList<String>();
        for (final String techPackName : tpNames) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            final List<String> rawErrTables = techPack.getRawErrTables();
            if (rawErrTables != null) {
                allRawErrTables.addAll(rawErrTables);
            }
        }

        return allRawErrTables;
    }

    /**
     * Gets the all raw suc tables for tech packs.
     *
     * @param tpNames the tp names
     * @return the all raw suc tables for tech packs
     */
    public List<String> getAllRawSucTablesForTechPacks(final List<String> tpNames) {
        final List<String> allRawSucTables = new ArrayList<String>();
        for (final String techPackName : tpNames) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            final List<String> rawSucTables = techPack.getRawSucTables();
            if (rawSucTables != null) {
                allRawSucTables.addAll(rawSucTables);
            }
        }

        return allRawSucTables;
    }

    /**
     * Gets the all err aggregation views.
     *
     * @return the all err aggregation views
     */
    public List<String> getAllErrAggregationViews() {
        final List<String> allErrAggregationViews = new ArrayList<String>();
        for (final String techPackName : techPacks.keySet()) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            allErrAggregationViews.add(techPack.getErrAggregationView());
        }

        return allErrAggregationViews;
    }

    /**
     * Gets the Error aggregation views for the spcified TechPacks only
     *
     * @return the all err aggregation views
     */
    public List<String> getErrAggregationViewsForTechPacks(final List<String> techPackList) {
        final List<String> errAggregationViews = new ArrayList<String>();
        for (final String techPackName : techPackList) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            final String errAggregationView = techPack.getErrAggregationView();
            if (errAggregationView != null) {
                errAggregationViews.add(errAggregationView);
            }
        }

        return errAggregationViews;
    }

    /**
     * Gets the Success aggregation views for the spcified TechPacks only
     *
     * @return the all err aggregation views
     */
    public List<String> getSucAggregationViewsForTechPacks(final List<String> techPackList) {
        final List<String> sucAggregationViews = new ArrayList<String>();
        for (final String techPackName : techPackList) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            final String sucAggregationView = techPack.getSucAggregationView();
            if (sucAggregationView != null) {
                sucAggregationViews.add(sucAggregationView);
            }
        }

        return sucAggregationViews;
    }

    /**
     * Gets the all raw suc tables.
     *
     * @return the all raw suc tables
     */
    public List<String> getAllRawSucTables() {
        final List<String> allRawSucTables = new ArrayList<String>();
        for (final String techPackName : techPacks.keySet()) {
            final TechPackRepresentation techPack = techPacks.get(techPackName);
            final List<String> rawSucTables = techPack.getRawSucTables();
            if (rawSucTables != null) {
                allRawSucTables.addAll(rawSucTables);
            }
        }

        return allRawSucTables;
    }

    /**
     * Gets the all suc aggregation views.
     *
     * @return the all suc aggregation views
     */
    public List<String> getAllSucAggregationViews() {
        final List<String> allSucAggregationViews = new ArrayList<String>();
        for (final TechPackRepresentation techPack : techPacks.values()) {
            allSucAggregationViews.add(techPack.getSucAggregationView());
        }

        return allSucAggregationViews;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String techPackName : techPacks.keySet()) {
            stringBuilder.append(techPackName);
            stringBuilder.append(OPENING_BRACKET);
            stringBuilder.append(techPacks.get(techPackName));
            stringBuilder.append(CLOSING_BRACKET);
            stringBuilder.append(COMMA);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * Gets the tech pack.
     *
     * @param techPackName the tech pack name
     * @return the tech pack
     */
    public TechPackRepresentation getTechPack(final String techPackName) {
        return techPacks.get(techPackName);
    }

    /**
     * Gets the tech packs.
     *
     * @return the tech packs
     */
    public Collection<TechPackRepresentation> getTechPacks() {
        return techPacks.values();
    }

    /**
     * Checks for raw tables.
     *
     * @return true, if checks for raw tables
     */
    public boolean hasRawTables() {
        return getAllRawErrTables().size() > 0 || getAllRawSucTables().size() > 0 || getAllRawTables().size() > 0
                || getAllRawTablesWithMeasurementType().size() > 0;
    }

    /**
     * Gets the all raw tables.
     *
     * @return the all raw tables
     */
    public List<String> getAllRawTables() {
        final List<String> allRawTables = new ArrayList<String>();
        for (final TechPackRepresentation techPack : techPacks.values()) {
            final List<String> rawTables = techPack.getRawTables();
            if (rawTables != null) {
                allRawTables.addAll(rawTables);
            }
        }
        return allRawTables;
    }

    /**
     * Gets the hfa all raw tables.
     *
     * @return the hfa all raw tables
     */
    public List<String> getAllRawTablesWithMeasurementType() {
        final List<String> rawTablesWithMeasurementTypes = new ArrayList<String>();
        for (final TechPackRepresentation techPack : techPacks.values()) {
            final List<String> rawTables = techPack.getRawTablesWithMeasurementTypes();
            if (rawTables != null) {
                rawTablesWithMeasurementTypes.addAll(rawTables);
            }
        }
        return rawTablesWithMeasurementTypes;
    }

    /**
     * Gets the specific raw tables.
     * <p/>
     * Note: The method is not integrated into the framework.
     *
     * @param measurementTypes the measurement type list for which the tables will be returned.
     * @return the specific raw tables
     */
    public List<String> getSpecificRawTablesWithMeasurementType(final List<String> measurementTypes) {
        final List<String> rawTablesWithMeasurementTypes = new ArrayList<String>();
        for (final TechPackRepresentation techPack : techPacks.values()) {
            final List<String> rawTables = techPack.getRawTablesWithMeasurementTypes();
            // Use contains() because getRawTablesWithMeausreTypes() has no parameter
            if (rawTables != null) {
                for (final String rawTable : rawTables) {
                    for (final String measurementType : measurementTypes) {
                        if (rawTable.contains(measurementType)) {
                            rawTablesWithMeasurementTypes.add(rawTable);
                        }
                    }
                }
            }
        }

        return rawTablesWithMeasurementTypes;
    }

    /**
     * Gets the specific aggregation tables.
     * <p/>
     * Note: The method is not integrated into the framework.
     *
     * @param techPackName        the technology pack name
     * @param measurementTypes    the measurement type for which the aggregation tables will be returned.
     * @param measurementTypes    the measurement type list for which the aggregation tables will be returned.
     * @param aggregationViewType the aggregation view type
     * @return the specific aggregation tables
     */
    public List<String> getSpecificAggregationViewsWithMeasurementType(final String techPackName,
                                                                       final List<String> measurementTypes, final String aggregationViewType) {
        final List<String> aggTablesWithMeasurementTypes = new ArrayList<String>();
        final List<String> aggTables = getTechPack(techPackName).getAggregationViewsWithMeasurementTypesSpecific(
                aggregationViewType);
        if (aggTables != null) {
            for (final String aggTable : aggTables) {
                for (final String measurementType : measurementTypes) {
                    if (aggTable.contains(measurementType)) {
                        aggTablesWithMeasurementTypes.add(aggTable);
                    }
                }
            }
        }

        return aggTablesWithMeasurementTypes;
    }

    /**
     * Get the plain (ie not ERR or SUC) aggregation views for tech packs contained in this object
     * eg for the EVENT_E_RAN_CFA tech pack, there are no ERR/SUC tables or aggregations - the only aggregations
     * are eg EVENT_E_RAN_CFA_VEND_HIER3_15MIN.
     *
     * @return list of all plain (ie not ERR or SUC) aggregation views for these tech packs
     */
    public List<String> getAllPlainAggregationViews() {
        final List<String> allPlainAggregationViews = new ArrayList<String>();
        for (final TechPackRepresentation techPack : techPacks.values()) {
            allPlainAggregationViews.add(techPack.getPlainAggregationView());
        }
        return allPlainAggregationViews;
    }

}