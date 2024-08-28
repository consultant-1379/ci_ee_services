/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DIM_Z_ALARM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SGEH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 *
 */
public class TechPackTechnologiesTest {

    private static final String VALID_TECH_PACK = "EVENT_E_SGEH";

    private static final String DUMMY_TECH_PACK = "DUMMY_TECH_PACK";

    private static final String DEFAULT_TECHNOLOGY = SGEH;

    private TechPackTechnologies techPackDefinitions;

    private Map<String, List<String>> valuesInFile;

    @Before
    public void setup() {
        valuesInFile = new HashMap<String, List<String>>();
        techPackDefinitions = new StubbedTechPackDefinitions();
    }

    @Test(expected = RuntimeException.class)
    public void testGetTechPackTechnologyThrowsExceptionForTechPackInFileButNotDefinedInEnum() {
        final String techPack = "EVENT_E_IMAGINE";
        final String technologyNotDefinedInEnum = "undefined_technology";
        addValuesInFile(techPack, technologyNotDefinedInEnum, true);
        techPackDefinitions.readTechPackDefinitionsFile();
        techPackDefinitions.getTechPackTechnology(techPack);
    }

    @Test
    public void testGetTechPackTechnology() {
        addValuesInFile(VALID_TECH_PACK, SGEH, true);
        techPackDefinitions.readTechPackDefinitionsFile();
        assertThat(techPackDefinitions.getTechPackTechnology(VALID_TECH_PACK), is(SGEH));
    }

    @Test
    public void testIsTechPackTechnologyForVolumeBasedTechPack() {
        addValuesInFile(VALID_TECH_PACK, SGEH, true);
        techPackDefinitions.readTechPackDefinitionsFile();
        assertThat(techPackDefinitions.usesVolumeBasedRawPartitions(VALID_TECH_PACK), is(true));
    }

    @Test
    public void testIsTechPackTechnologyForTimeBasedTechPack() {
        addValuesInFile(VALID_TECH_PACK, DIM_Z_ALARM, false);
        techPackDefinitions.readTechPackDefinitionsFile();
        assertThat(techPackDefinitions.usesVolumeBasedRawPartitions(VALID_TECH_PACK), is(false));
    }

    @Test
    public void testGetTechPackTechnologyDefaultsToSGEHForTechPackNotInFile() {
        techPackDefinitions.readTechPackDefinitionsFile();
        assertThat(techPackDefinitions.getTechPackTechnology(DUMMY_TECH_PACK), is(DEFAULT_TECHNOLOGY));
    }

    private void addValuesInFile(final String techPack, final String technology, final boolean isTimerange) {
        final List<String> valuesInFileForSgehTechPack = new ArrayList<String>();
        valuesInFileForSgehTechPack.add(techPack);
        valuesInFileForSgehTechPack.add(technology);
        valuesInFileForSgehTechPack.add(new Boolean(isTimerange).toString());
        valuesInFile.put(techPack, valuesInFileForSgehTechPack);
    }

    class StubbedTechPackDefinitions extends TechPackTechnologies {
        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.config.latency.TechPackDefinitions#readValuesInFile()
         */
        @Override
        Map<String, List<String>> readValuesInFile() {
            return valuesInFile;
        }
    }

}
