/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import com.ericsson.eniq.events.server.utils.config.Property;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 *
 */
public class LatencyPropertyDefaultValuesAndLimitsTest {

    private static final String NON_EXISTENT_PROPERTY = "nonExistentProperty";

    private LatencyPropertyDefaultValuesAndLimits latencyDefaultProperties;

    Map<String, List<String>> valuesInCSVFile;

    @Before
    public void setup() {
        valuesInCSVFile = new HashMap<String, List<String>>();
        latencyDefaultProperties = new StubbedLatencyDefaultProperties();
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionThrownIfPropertyNotSpecifiedCorrectlyInFile() {
        final String propertyName = "Manolo";
        populateValuesFromFile(propertyName, 0);
        latencyDefaultProperties.readDefaultValuesFromFile();
        latencyDefaultProperties.getDefaultAndLimitsForProperty(propertyName);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionThrownIfPropertyNotFoundInFile() {
        latencyDefaultProperties.readDefaultValuesFromFile();
        latencyDefaultProperties.getDefaultAndLimitsForProperty(NON_EXISTENT_PROPERTY);
    }

    @Test
    public void testParsingValuesInFile() {
        final String propertyName = "Louboutin";
        final int defaultValue = 3;
        final int minimumConfigurableValue = 2;
        final int maximumConfigurableValue = 7;
        populateValuesFromFile(propertyName, defaultValue, minimumConfigurableValue, maximumConfigurableValue);
        latencyDefaultProperties.readDefaultValuesFromFile();
        final Property result = latencyDefaultProperties.getDefaultAndLimitsForProperty(propertyName);
        assertNotNull(result);
        assertThat(result.getKey(), is(propertyName));
        assertThat(result.getMinimumConfigurableValue(), is(minimumConfigurableValue));
        assertThat(result.getMaximumConfigurableValue(), is(maximumConfigurableValue));
    }

    private void populateValuesFromFile(final String propertyName, final int defaultValue) {
        final List<String> values = new ArrayList<String>();
        values.add(propertyName);
        values.add(Integer.toString(defaultValue));
        valuesInCSVFile.put(propertyName, values);
    }

    private void populateValuesFromFile(final String propertyName, final int defaultValue,
            final int minimumConfigurableValue, final int maximumConfigurableValue) {
        final List<String> values = new ArrayList<String>();
        values.add(propertyName);
        values.add(Integer.toString(defaultValue));
        values.add(Integer.toString(minimumConfigurableValue));
        values.add(Integer.toString(maximumConfigurableValue));
        valuesInCSVFile.put(propertyName, values);

    }

    class StubbedLatencyDefaultProperties extends LatencyPropertyDefaultValuesAndLimits {
        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.config.latency.LatencyDefaultProperties#readCSVFile()
         */
        @Override
        Map<String, List<String>> readCSVFile() {
            return valuesInCSVFile;
        }
    }

}
