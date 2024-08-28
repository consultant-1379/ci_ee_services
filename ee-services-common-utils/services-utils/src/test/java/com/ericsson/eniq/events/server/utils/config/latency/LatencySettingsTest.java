/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;
import static com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.utils.config.Property;
import com.ericsson.eniq.events.server.utils.config.PropertyMatcher;
import com.ericsson.eniq.events.server.utils.config.PropertyStore;

/**
 * @author eemecoy
 */
public class LatencySettingsTest {
    protected Mockery mockery = new JUnit4Mockery();

    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    private static final String ENIQ_EVENTS_SGEH_LEAST_OFFSET = "ENIQ_EVENTS_SGEH_LEAST_OFFSET";

    private static final String ENIQ_EVENTS_SGEH_LARGE_OFFSET = "ENIQ_EVENTS_SGEH_LARGE_OFFSET";

    private static final int INVALID_TIME_DELAY = 4700;

    private static final int A_NEGATIVE_NUMBER = -2;

    private static final int MINIMUM_CONFIGURABLE_OPTION_FOR_TIME_DELAY_PARAMETER = 0;

    private static final String DUMMY_TECH_PACK = "DUMMY_TECH_PACK";

    private static final String ENIQ_EVENTS_DT_LARGE_OFFSET = "ENIQ_EVENTS_DT_LARGE_OFFSET";

    private LatencySettings latencySettings;

    PropertyStore propertyStore;

    LatencyPropertyDefaultValuesAndLimits latencyPropertyDefaultValuesAndLimits;

    TechPackTechnologies techPackTechnologies;

    @Before
    public void setup() {
        latencySettings = new LatencySettings();
        propertyStore = mockery.mock(PropertyStore.class);
        latencySettings.setPropertyStore(propertyStore);
        latencyPropertyDefaultValuesAndLimits = mockery.mock(LatencyPropertyDefaultValuesAndLimits.class);
        latencySettings.setLatencyPropertyDefaultValuesAndLimits(latencyPropertyDefaultValuesAndLimits);
        techPackTechnologies = mockery.mock(TechPackTechnologies.class);
        setupTechnologyMappings();
        latencySettings.setTechPackTechnologies(techPackTechnologies);
    }

    private void setupTechnologyMappings() {
        mockery.checking(new Expectations() {
            {
                allowing(techPackTechnologies).getTechPackTechnology(EVENT_E_SGEH);
                will(returnValue(SGEH));
                allowing(techPackTechnologies).getTechPackTechnology(EVENT_E_GSN_DT);
                will(returnValue(DT));
                allowing(techPackTechnologies).getTechPackTechnology(DUMMY_TECH_PACK);
                will(throwException(new RuntimeException()));
            }
        });

    }

    @Test(expected = RuntimeException.class)
    public void testExceptionThrownWhenDeveloperHasntUpdatedForTechPack() {
        setTimeDelay(OffsetOptions.BEST_EFFORT);
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(DUMMY_TECH_PACK);
        latencySettings.getTimeDelayMediumOffset(techPacks);
    }

    @Test
    public void testSettingNegativeValueForTimeDelayToBeUsed() {
        setTimeDelay(A_NEGATIVE_NUMBER);
        final int defaultValueForSgehLargeOffset = 3;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LARGE_OFFSET, defaultValueForSgehLargeOffset);
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_SGEH);
        assertThat(latencySettings.getTimeDelayLargeOffset(techPacks), is(defaultValueForSgehLargeOffset));
    }

    @Test
    public void testSettingInvalidValueForTimeDelayToBeUsed() {
        setTimeDelay(INVALID_TIME_DELAY);
        final int defaultValueForSgehLargeOffset = 4;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LARGE_OFFSET, defaultValueForSgehLargeOffset);
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_SGEH);
        assertThat(latencySettings.getTimeDelayLargeOffset(techPacks), is(defaultValueForSgehLargeOffset));
    }

    @Test
    public void testGetTimeDelayLargeOffset_BestEffort_WithTwoTechPacks() {
        setTimeDelay(OffsetOptions.BEST_EFFORT);
        //these will be used to determine which is the "slowest" technology - we're setting DT to be the slowest
        final int valueForSgehLeastOffset = 3;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LEAST_OFFSET, valueForSgehLeastOffset);
        final int valueForDTLeastOffset = 4;
        expectCallForPropertyDefaultsAndLimits("ENIQ_EVENTS_DT_LEAST_OFFSET", valueForDTLeastOffset);

        final int expectedLatencySetting = 24;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_DT_LARGE_OFFSET, expectedLatencySetting);

        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_SGEH);
        techPacks.add(EVENT_E_GSN_DT_TPNAME);
        final int result = latencySettings.getTimeDelayLargeOffset(techPacks);
        assertThat(result, is(expectedLatencySetting));
    }

    @Test
    public void testGetTimeDelayLargeOffset_BestEffort_WithOneTechPack() {
        setTimeDelay(OffsetOptions.BEST_EFFORT);
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LEAST_OFFSET, 1);
        final int expectedValue = 17;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LARGE_OFFSET, expectedValue);
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_SGEH);
        final int result = latencySettings.getTimeDelayLargeOffset(techPacks);
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testGetTimeDelayDayMinuteData_DT() {
        setTimeDelay(OffsetOptions.DT);
        final int expectedValue = 17;
        expectCallForPropertyDefaultsAndLimits("ENIQ_EVENTS_DT_LARGE_OFFSET", expectedValue);
        final int result = latencySettings.getTimeDelayLargeOffset(null);
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testGetTimeDelayDayMinuteData_SGEH() {
        setTimeDelay(OffsetOptions.SGEH);
        final int expectedValue = 17;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LARGE_OFFSET, expectedValue);
        final int result = latencySettings.getTimeDelayLargeOffset(null);
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testGetTimeDelayFifteenMinuteData_DT() {
        setTimeDelay(OffsetOptions.DT);
        final int expectedValue = 17;
        expectCallForPropertyDefaultsAndLimits("ENIQ_EVENTS_DT_MEDIUM_OFFSET", expectedValue);
        final int result = latencySettings.getTimeDelayMediumOffset(null);
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testGetTimeDelayFifteenMinuteData_SGEH() {
        setTimeDelay(OffsetOptions.SGEH);
        final int expectedValue = 17;
        expectCallForPropertyDefaultsAndLimits("ENIQ_EVENTS_SGEH_MEDIUM_OFFSET", expectedValue);

        final int result = latencySettings.getTimeDelayMediumOffset(null);
        assertThat(result, is(expectedValue));
    }

    private Property expectCallForPropertyDefaultsAndLimits(final String propertyName, final int propertyValue) {

        final Property mockedProperty = mockery.mock(Property.class, propertyName);
        mockery.checking(new Expectations() {
            {
                allowing(latencyPropertyDefaultValuesAndLimits).getDefaultAndLimitsForProperty(propertyName);
                will(returnValue(mockedProperty));
                allowing(propertyStore).getIntegerPropertyValue(mockedProperty);
                will(returnValue(propertyValue));
            }
        });
        return mockedProperty;

    }

    @Test
    public void testGetTimeDelayOneMinuteData_SGEH() {
        setTimeDelay(OffsetOptions.SGEH);
        final int expectedValue = 3;
        expectCallForPropertyDefaultsAndLimits(ENIQ_EVENTS_SGEH_LEAST_OFFSET, expectedValue);

        final int result = latencySettings.getTimeDelayLeastOffset(null);
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testGetTimeDelayOneMinuteData_DT() {
        setTimeDelay(OffsetOptions.DT);
        final int expectedValue = 4;

        expectCallForPropertyDefaultsAndLimits("ENIQ_EVENTS_DT_LEAST_OFFSET", expectedValue);
        final int result = latencySettings.getTimeDelayLeastOffset(null);
        assertThat(result, is(expectedValue));
    }

    private void setTimeDelay(final OffsetOptions offsetOption) {
        setTimeDelay(offsetOption.getIntegerValue());
    }

    private void setTimeDelay(final int timeDelayToSet) {
        final Property property = new Property(ENIQ_EVENTS_TIME_DELAY_TO_BE_USED);
        property.setDefaultValue(OffsetOptions.BEST_EFFORT.getIntegerValue());
        property.setMinimumConfigurableValue(MINIMUM_CONFIGURABLE_OPTION_FOR_TIME_DELAY_PARAMETER);
        final int maxConfigurableOptionForTimeDelayParameter = determineMaxConfigurableOptionForTimeDelayParameter();
        property.setMaxConfigurableValue(maxConfigurableOptionForTimeDelayParameter);
        mockery.checking(new Expectations() {
            {
                one(propertyStore).getIntegerPropertyValue(with(PropertyMatcher.propertyMatches(property)));
                will(returnValue(timeDelayToSet));
            }
        });

    }

    private int determineMaxConfigurableOptionForTimeDelayParameter() {
        int maxValue = 0;
        for (final OffsetOptions value : OffsetOptions.values()) {
            if (value.getIntegerValue() > maxValue) {
                maxValue = value.getIntegerValue();
            }
        }
        return maxValue;
    }
}
