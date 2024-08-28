/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * Read the tech pack to technology mapping file, and store for future reference
 * This mapping is used in the latency offset handling - the technology defined for a given tech pack may determine
 * the latency offset applied to queries using that tech pack
 * @author eemecoy
 *
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class TechPackTechnologies {

    private static final String DEFAULT_TECHNOLOGY = SGEH;

    private static final String TECH_PACK_DEFINITION_FILE = "/techpack_definitions/techpack_technology_mapping.csv";

    private Map<String, String> techPackTechnologyMapping;

    private Map<String, Boolean> techPackTimeRangeMapping;

    @PostConstruct
    public void readTechPackDefinitionsFile() {
        techPackTechnologyMapping = new HashMap<String, String>();
        techPackTimeRangeMapping = new HashMap<String, Boolean>();
        final Map<String, List<String>> valuesInFile = readValuesInFile();
        for (final String techPack : valuesInFile.keySet()) {
            final String technology = valuesInFile.get(techPack).get(1);
            ServicesLogger.detailed(this.getClass().getName(), "readTechPackDefinitionsFile",
                    "Technology for tech pack " + techPack + " is " + technology);
            techPackTechnologyMapping.put(techPack, technology);
            final boolean timeRangeTechpack = valuesInFile.get(techPack).get(2).equalsIgnoreCase(TRUE_UPPER_STR);
            ServicesLogger.detailed(this.getClass().getName(), "readTechPackDefinitionsFile", "Is this Tech pack "
                    + techPack + " a TimeRange Tech pack " + timeRangeTechpack);
            techPackTimeRangeMapping.put(techPack, timeRangeTechpack);
        }
    }

    @PreDestroy
    public void beanDestroy() {
        techPackTechnologyMapping = null;
        techPackTimeRangeMapping = null;
    }

    @Lock(LockType.READ)
    Map<String, List<String>> readValuesInFile() {
        return new CSVFileReader().readCSVFile(TECH_PACK_DEFINITION_FILE);
    }

    public String getTechPackTechnology(final String techPack) {
        if (techPackTechnologyMapping.containsKey(techPack)) {
            final String technology = techPackTechnologyMapping.get(techPack);
            if (isTechPackTechnologyDefinedInEnum(technology)) {
                return technology;
            }
            throw new RuntimeException("No enum value defined in " + OffsetOptions.class + " for the technology "
                    + technology);

        }
        return DEFAULT_TECHNOLOGY;
    }

    /**
     * Check if the view in parameter uses volume based raw partitions or time based raw partitions.
     *
     * @param view View name to check the tech packs against
     * @return true if the view in parameter uses volume based raw partitions, false otherwise, the default value is true
     */
    public boolean usesVolumeBasedRawPartitions(final String view) {
        for (final String techPack : techPackTimeRangeMapping.keySet()) {
            if (view.startsWith(techPack)) {
                final boolean status = techPackTimeRangeMapping.get(techPack);
                ServicesLogger.detailed(this.getClass().getName(), "usesVolumeBasedRawPartitions", "Does view " + view
                        + " use volume based raw partitions: " + status);
                return status;
            }
        }

        return true;
    }

    private boolean isTechPackTechnologyDefinedInEnum(final String technology) {
        for (final OffsetOptions offsetOption : OffsetOptions.values()) {
            if (technology.equals(offsetOption.toString())) {
                return true;
            }
        }
        return false;
    }
}
