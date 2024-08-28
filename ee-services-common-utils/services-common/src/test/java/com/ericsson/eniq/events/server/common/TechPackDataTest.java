package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TechPackDataTest {

    private static final String EVENT_E_SGEH_XXX = "EVENT_E_SGEH_XXX";

    private static final String EVENT_E_LTE_XXX = "EVENT_E_LTE_XXX";

    @Test
    public void testgetRATForTechPackTableWithString() {
        assertThat(TechPackData.getRATForTechPackTable(EVENT_E_LTE_XXX), is(RAT_INTEGER_VALUE_FOR_4G));
        assertThat(TechPackData.getRATForTechPackTable(EVENT_E_SGEH_XXX), is(RAT_INTEGER_VALUE_FOR_2G));
    }

    @Test
    public void testgetRATForTechPackTableWithListsOfString() {
        final List<String> listOfLteTables = new ArrayList<String>();
        listOfLteTables.add(EVENT_E_LTE_XXX);
        assertThat(TechPackData.getRATForTechPackTable(listOfLteTables), is(RAT_INTEGER_VALUE_FOR_4G));
        final List<String> listOfSgehTables = new ArrayList<String>();
        listOfSgehTables.add(EVENT_E_SGEH_XXX);
        assertThat(TechPackData.getRATForTechPackTable(listOfSgehTables), is(RAT_INTEGER_VALUE_FOR_2G));
    }
}
