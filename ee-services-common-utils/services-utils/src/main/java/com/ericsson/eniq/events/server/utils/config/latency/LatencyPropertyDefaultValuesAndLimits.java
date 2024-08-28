/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

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

import com.ericsson.eniq.events.server.utils.config.Property;

/**
 * Class responsible for reading up the default, minimum and maximum configurable values for the latency properties
 * These values are stored in a flat file
 * 
 * @author eemecoy
 *
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class LatencyPropertyDefaultValuesAndLimits {

    private static final String DEFAULT_LATENCY_PROPERTIES_CSV_FILE = "/latency_properties/latency_default_properties.csv";

    private static final int NUMBER_OF_VALUES_FOR_EACH_KEY = 4;

    private Map<String, Property> properties;

    /**
     * Application startup, read up the csv file, and store these for future reference
     */
    @PostConstruct
    public void readDefaultValuesFromFile() {
        final Map<String, List<String>> fileValues = readCSVFile();
        properties = transformFileValuesToProperties(fileValues);
    }

    @PreDestroy
    public void beanDestroy() {
        properties = null;
    }

    /**
     * transform the values read from the file into Property objects, each containing the property key, its default value
     * and minimum and maximum values 
     * 
     * The format of the DEFAULT_LATENCY_PROPERTIES_CSV_FILE file is property_key,default value, minimum value,max value 
     */
    private Map<String, Property> transformFileValuesToProperties(final Map<String, List<String>> fileValues) {
        final Map<String, Property> propertiesStoredInFile = new HashMap<String, Property>();
        for (final String key : fileValues.keySet()) {
            final List<String> propertySettings = fileValues.get(key);
            if (propertySettings.size() != NUMBER_OF_VALUES_FOR_EACH_KEY) {
                throw new RuntimeException("Invalid specification for property " + key + ", there should be "
                        + NUMBER_OF_VALUES_FOR_EACH_KEY + " values specified, see "
                        + DEFAULT_LATENCY_PROPERTIES_CSV_FILE + " for the correct file syntax");
            }
            final Property property = new Property(key);
            final int defaultValue = Integer.parseInt(propertySettings.get(1).trim());
            property.setDefaultValue(defaultValue);
            final int minimumConfigurableValue = Integer.parseInt(propertySettings.get(2).trim());
            property.setMinimumConfigurableValue(minimumConfigurableValue);
            final int maximumConfigurableValue = Integer.parseInt(propertySettings.get(3).trim());
            property.setMaxConfigurableValue(maximumConfigurableValue);
            propertiesStoredInFile.put(key, property);
        }
        return propertiesStoredInFile;
    }

    @Lock(LockType.READ)
    Map<String, List<String>> readCSVFile() {
        return new CSVFileReader().readCSVFile(DEFAULT_LATENCY_PROPERTIES_CSV_FILE);
    }

    /**
     * Get the default value and minimum and maximum configurable values for a given (latency) property
     * 
     * @param propertyName            name of property 
     * @return a Property object containing the default value for this property, and the maximum configurable limit
     * for this property
     * 
     */
    public Property getDefaultAndLimitsForProperty(final String propertyName) {
        if (properties.containsKey(propertyName)) {
            return properties.get(propertyName);
        }
        throw new RuntimeException("No definition (default value, min and max configurable limits) found for property "
                + propertyName + " in file " + DEFAULT_LATENCY_PROPERTIES_CSV_FILE);
    }

}
