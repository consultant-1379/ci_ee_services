/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Object to ensapulate all data relating to the tables/views that should be used for a given tech pack in a query
 * This object contains information on both raw tables and aggregation views for a single tech pack
 *
 * @author eemecoy
 */
public class TechPackRepresentation {
 
    private String errAggregationView;

    private List<String> rawErrTables;

    private String sucAggregationView;

    private List<String> rawSucTables;

    private String staticLookupTable;

    private final String name;

    private final String licencedName;

    private List<String> rawTables;

    private String plainAggregationView;

    private Map<String, List<String>> aggregationViewsWithMeasurementTypesMap;

    private List<String> rawTablesWithMeasurementTypes;

    private static List<String> techPackPrefixWithSameLicense;

    private String  allCallsAggregationView;

	static {
        techPackPrefixWithSameLicense = new ArrayList<String>();
        techPackPrefixWithSameLicense.add(EVENT_E_GSN_LICENCSED_TP_NAME);
        techPackPrefixWithSameLicense.add(EVENT_E_LTE_CFA_LICENCSED_TP_NAME);
        techPackPrefixWithSameLicense.add(EVENT_E_LTE_HFA_LICENCSED_TP_NAME);
    }

    /**
     * @param techPackName
     */
    public TechPackRepresentation(final String techPackName) {
        this.name = techPackName;
        //Set the licenced name, if it is different than what is called the techPackName
        licencedName = getTechPackNameToUseForLicenseCheck(techPackName);
    }

    private String getTechPackNameToUseForLicenseCheck(final String techPackName) {
        for (final String techPack : techPackPrefixWithSameLicense) {
            if (techPackName.startsWith(techPack)) {
                return techPack;
            }
        }
        return techPackName;
    }

    public void setErrAggregationView(final String errorAggregationView) {
        this.errAggregationView = errorAggregationView;

    }

    public List<String> getRawErrTables() {
        return rawErrTables;
    }

    public void setErrRawTables(final List<String> rawErrTables) {
        this.rawErrTables = rawErrTables;

    }

    public void setSucAggregationView(final String successAggregationView) {
        this.sucAggregationView = successAggregationView;

    }

    public String getErrAggregationView() {
        return errAggregationView;
    }

    public void setSucRawTables(final List<String> rawSucTables) {
        this.rawSucTables = rawSucTables;

    }

    public List<String> getRawSucTables() {
        return rawSucTables;
    }

    public List<String> getAllRawTables() {
        final List<String> tempList = new ArrayList<String>();
        tempList.addAll(rawSucTables);
        tempList.addAll(rawErrTables);
        return tempList;
    }

    public String getSucAggregationView() {
        return sucAggregationView;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(errAggregationView);
        stringBuilder.append(COMMA);
        stringBuilder.append(sucAggregationView);
        stringBuilder.append(COMMA);
        appendListOfTables(stringBuilder, rawErrTables);
        stringBuilder.append(COMMA);
        appendListOfTables(stringBuilder, rawSucTables);
        stringBuilder.append(COMMA);
        appendListOfTables(stringBuilder, rawTables);
        stringBuilder.append(COMMA);
        appendListOfTables(stringBuilder, rawTablesWithMeasurementTypes);
        return stringBuilder.toString();
    }

    private void appendListOfTables(final StringBuilder stringBuilder, final List<String> listOfTables) {
        stringBuilder.append(OPENING_BRACKET);
        if (listOfTables != null) {
            for (final String table : listOfTables) {
                stringBuilder.append(table);
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(CLOSING_BRACKET);
    }

    public void setStaticLookUpTable(final String staticLookupTable) {
        this.staticLookupTable = staticLookupTable;

    }

    public String getStaticLookupTechpack() {
        return staticLookupTable;
    }

    public String getName() {
        return name;
    }

    public void setRawTables(final List<String> rawTables) {
        this.rawTables = rawTables;

    }

    public List<String> getRawTables() {
        return rawTables;
    }

    public String getPlainAggregationView() {
        return plainAggregationView;
    }

    /**
     * Get the plain (ie not ERR or SUC) aggregation view for this tech pack
     * eg for the EVENT_E_RAN_CFA tech pack, there are no ERR/SUC tables or aggregations - the only aggregations
     * are eg EVENT_E_RAN_CFA_VEND_HIER3_15MIN
     *
     * @return plain (ie not ERR or SUC) aggregation view for this tech pack
     */
    public void setPlainAggregationView(final String plainAggregationView) {
        this.plainAggregationView = plainAggregationView;
    }

    /**
     * The Licenced name for a TechPack can be different that the "Measurement" name.
     * EG EVENT_E_GSN_DT is the measurement, but EVENT_E_GSN is the TP name.
     *
     * @return the name corresponding to the licenced name
     */
    public String getLicencedName() {
        return licencedName;
    }

    /**
     * @return the aggregationViewsWithMeasurementTypes
     */
    public List<String> getAggregationViewsWithMeasurementTypes() {
        final List<String> aggregationViewsWithMeasurementTypesList = new ArrayList<String>();
        if (aggregationViewsWithMeasurementTypesMap != null && aggregationViewsWithMeasurementTypesMap.values() != null) {
            for (final List<String> aggregationViewsWithMeasurementTypes : aggregationViewsWithMeasurementTypesMap
                    .values()) {
                aggregationViewsWithMeasurementTypesList.addAll(aggregationViewsWithMeasurementTypes);
            }
        }
        return aggregationViewsWithMeasurementTypesList;
    }

    /**
     * Gets the list of specific aggregation views with measurement types (filters the full names on aggregation view type)
     *
     * @param aggregationViewType
     * @return the list of specific aggregation views with measurement types
     */
    public List<String> getAggregationViewsWithMeasurementTypesSpecific(final String aggregationViewType) {
        final List<String> aggregationViewsWithMeasurementTypesList = new ArrayList<String>();
        if (aggregationViewsWithMeasurementTypesMap != null && aggregationViewsWithMeasurementTypesMap.values() != null) {
            for (final List<String> aggregationViewsWithMeasurementTypes : aggregationViewsWithMeasurementTypesMap
                    .values()) {
                for (final String aggregationViewsWithMeasurementType : aggregationViewsWithMeasurementTypes) {
                    if (aggregationViewsWithMeasurementType.contains(UNDERSCORE + aggregationViewType + UNDERSCORE)) { // hack for HFA
                        aggregationViewsWithMeasurementTypesList.add(aggregationViewsWithMeasurementType);
                    }
                }
            }
        }

        return aggregationViewsWithMeasurementTypesList;
    }

    /**
     * @param aggregationViewsWithMeasurementTypes
     *         the aggregationViewsWithMeasurementTypes to set
     */
    public void setAggregationViewsWithMeasurementTypes(
            final Map<String, List<String>> aggregationViewsWithMeasurementTypesMap) {
        this.aggregationViewsWithMeasurementTypesMap = aggregationViewsWithMeasurementTypesMap;
    }

    /**
     * @return the rawTablesWithMeausreTypes
     */
    public List<String> getRawTablesWithMeasurementTypes() {
        return rawTablesWithMeasurementTypes;
    }

    /**
     * @param rawTablesWithMeausreTypes the rawTablesWithMeausreTypes to set
     */
    public void setRawTablesWithMeausreTypes(final List<String> rawTablesWithMeasurementTypes) {
        this.rawTablesWithMeasurementTypes = rawTablesWithMeasurementTypes;
    }
    
    public String getAllCallsAggregationView() {
		return allCallsAggregationView;
	}

	public void setAllCallsAggregationView(String allCallsAggregationView) {
		this.allCallsAggregationView = allCallsAggregationView;
	}
}
