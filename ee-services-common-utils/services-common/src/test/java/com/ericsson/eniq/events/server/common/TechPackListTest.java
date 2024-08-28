/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;
import static junit.framework.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author EEMECOY
 */
public class TechPackListTest {

    @Test
    public void testHasRawTablesReturnsFalseForNoTables() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_SGEH);
        final List<String> rawErrTables = new ArrayList<String>();
        techPack.setErrRawTables(rawErrTables);
        final List<String> rawSucTables = new ArrayList<String>();
        techPack.setSucRawTables(rawSucTables);
        techPackList.addTechPack(EVENT_E_SGEH, techPack);
        assertThat(techPackList.hasRawTables(), is(false));
    }

    @Test
    public void testHasRawTablesReturnsTrueWhenErrTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_SGEH);
        final List<String> rawErrTables = new ArrayList<String>();
        rawErrTables.add("EVENT_E_SGEH_01");
        techPack.setErrRawTables(rawErrTables);
        final List<String> rawSucTables = new ArrayList<String>();
        techPack.setSucRawTables(rawSucTables);
        techPackList.addTechPack(EVENT_E_SGEH, techPack);
        assertThat(techPackList.hasRawTables(), is(true));
    }

    @Test
    public void testHasRawTablesReturnsTrueWhenSucTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_SGEH);
        final List<String> rawErrTables = new ArrayList<String>();
        techPack.setErrRawTables(rawErrTables);
        final List<String> rawSucTables = new ArrayList<String>();
        rawSucTables.add("EVENT_E_SGEH_SUC_01");
        techPack.setSucRawTables(rawSucTables);
        techPackList.addTechPack(EVENT_E_SGEH, techPack);
        assertThat(techPackList.hasRawTables(), is(true));
    }

    @Test
    public void testHasRawTablesWithMeasurementTypeWhenNoTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);
        final List<String> rawErrTables = new ArrayList<String>();
        techPack.setRawTablesWithMeausreTypes(rawErrTables);
        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);
        assertThat(techPackList.hasRawTables(), is(false));
    }

    @Test
    public void testHasRawTablesForAllMeasurementTypesWhenNoTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);
        final List<String> rawErrTables = new ArrayList<String>();
        techPack.setRawTablesWithMeausreTypes(rawErrTables);
        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);
        assertThat(techPackList.hasRawTables(), is(false));
    }

    @Test
    public void testHasRawTablesForAllMeasurementTypeWhenErrTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);
        final List<String> rawErrTables = new ArrayList<String>();
        rawErrTables.add("EVENT_E_RAN_HFA_IRAT_ERR_RAW_01");
        techPack.setRawTablesWithMeausreTypes(rawErrTables);
        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);
        assertThat(techPackList.hasRawTables(), is(true));
    }

    @Test
    public void testHasRawTablesForSpecificMeasurementTypesWhenNoTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);
        final List<String> rawErrTables = new ArrayList<String>();
        techPack.setRawTablesWithMeausreTypes(rawErrTables);
        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);
        assertThat((techPackList.getSpecificRawTablesWithMeasurementType(null).isEmpty() ? true : false), is(true));
    }

    @Test
    public void testHasRawTablesForSpecificMeasurementTypeWhenErrTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);
        final List<String> rawErrTables = new ArrayList<String>();
        final List<String> measurementTypes = new ArrayList<String>();
        measurementTypes.add(HFA_IRAT);
        rawErrTables.add("EVENT_E_RAN_HFA_IRAT_ERR_RAW_01");
        techPack.setRawTablesWithMeausreTypes(rawErrTables);
        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);
        assertThat((techPackList.getSpecificRawTablesWithMeasurementType(measurementTypes).isEmpty() ? true : false),
                is(false));
    }

    @Test
    public void testHasAggregationTablesForSpecificMeasurementTypesWhenNoTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);
        final Map<String, List<String>> aggErrTables = new HashMap<String, List<String>>();
        techPack.setAggregationViewsWithMeasurementTypes(aggErrTables);
        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);
        assertThat(
                (techPackList.getSpecificAggregationViewsWithMeasurementType(EVENT_E_RAN_HFA, null, null).isEmpty() ? true
                        : false), is(true));
    }

    @Test
    public void testHasAggregationTablesForSpecificMeasurementTypeWhenErrTablesPresent() {
        final TechPackList techPackList = new TechPackList();
        final TechPackRepresentation techPack = new TechPackRepresentation(EVENT_E_RAN_HFA);

        final List<String> measurementTypes = new ArrayList<String>();
        measurementTypes.add("SOHO");
        measurementTypes.add("IRAT");

        final List<String> aggregationViewTypes = new ArrayList<String>();
        aggregationViewTypes.add("HIER3_CELL");
        aggregationViewTypes.add("THIER3_CELL");

        final Map<String, List<String>> aggErrTables = new HashMap<String, List<String>>();
        final List<String> aggErrTablesListSource = new ArrayList<String>();
        aggErrTablesListSource.add("EVENT_E_RAN_HFA_SOHO_HIER3_CELL_ERR_RAW_01");
        aggErrTablesListSource.add("ANOTHER_SOURCE");

        final List<String> aggErrTablesListTarget = new ArrayList<String>();
        aggErrTablesListTarget.add("EVENT_E_RAN_HFA_IRAT_THIER3_CELL_ERR_RAW_01");
        aggErrTablesListTarget.add("ANOTHER_TARGET");

        aggErrTables.put("HIER3_CELL", aggErrTablesListSource);
        aggErrTables.put("THIER3_CELL", aggErrTablesListTarget);

        techPack.setAggregationViewsWithMeasurementTypes(aggErrTables);

        techPackList.addTechPack(EVENT_E_RAN_HFA, techPack);

        final List<String> hier3CellAggregationViewsWithMeasurementTypeList = techPackList
                .getSpecificAggregationViewsWithMeasurementType(EVENT_E_RAN_HFA, measurementTypes, "HIER3_CELL");

        assertEquals(1, hier3CellAggregationViewsWithMeasurementTypeList.size());

        assertEquals("EVENT_E_RAN_HFA_SOHO_HIER3_CELL_ERR_RAW_01",
                hier3CellAggregationViewsWithMeasurementTypeList.get(0));

        final List<String> thier3CellAggregationViewsWithMeasurementTypeList = techPackList
                .getSpecificAggregationViewsWithMeasurementType(EVENT_E_RAN_HFA, measurementTypes, "THIER3_CELL");

        assertEquals(1, thier3CellAggregationViewsWithMeasurementTypeList.size());

        assertEquals("EVENT_E_RAN_HFA_IRAT_THIER3_CELL_ERR_RAW_01",
                thier3CellAggregationViewsWithMeasurementTypeList.get(0));
    }
}
