/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 *
 */
public class PropertyStoreTest {

    private static final String SAMPLE_KEY_FOR_INTEGER_PROPERTY = "SAMPLE_KEY_FOR_INTEGER_PROPERTY";

    private static final int DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY = 30;

    private static final int MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY = 100;

    private static final String SAMPLE_KEY_FOR_BOOLEAN_PROPERTY = "IS_CREDIT_CARD_ALLOWED";

    private static final boolean DEFAULT_VALUE_FOR_SAMPLE_BOOLEAN_PROPERTY = false;

    private static final int A_VERY_LOW_VALUE = -3000;

    private static final int MIN_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY = 2;

    private PropertyStore propertyStore;

    Context mockedContext;

    protected Properties applicationProperties;

    private final int A_VERY_LARGE_VALUE = 1000000000;

        protected Mockery mockery = new JUnit4Mockery();
    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    @Before
    public void setup() {
        propertyStore = new PropertyStore();
        applicationProperties = new Properties();
        mockedContext = mockery.mock(Context.class);
        propertyStore.setInitialContext(mockedContext);
        applicationProperties.clear();
    }

    @Test
    public void testGetBooleanProperty() throws Exception {
        expectGetPropertiesFromJNDI();
        final boolean expectedValue = true;
        applicationProperties.setProperty(SAMPLE_KEY_FOR_BOOLEAN_PROPERTY, Boolean.toString(expectedValue));
        final boolean result = propertyStore.getBooleanPropertyValue(SAMPLE_KEY_FOR_BOOLEAN_PROPERTY,
                DEFAULT_VALUE_FOR_SAMPLE_BOOLEAN_PROPERTY);
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testGetBooleanPropertyIsDefaultWhenInvalidValueSet() throws Exception {
        expectGetPropertiesFromJNDI();
        final String anInvalidBooleanValue = "not a boolean";
        applicationProperties.setProperty(SAMPLE_KEY_FOR_BOOLEAN_PROPERTY, anInvalidBooleanValue);
        final boolean result = propertyStore.getBooleanPropertyValue(SAMPLE_KEY_FOR_BOOLEAN_PROPERTY,
                DEFAULT_VALUE_FOR_SAMPLE_BOOLEAN_PROPERTY);
        assertThat(result, is(DEFAULT_VALUE_FOR_SAMPLE_BOOLEAN_PROPERTY));
    }

    @Test
    public void testGetBooleanPropertyIsDefaultWhenNoValueSet() throws Exception {
        expectGetPropertiesFromJNDI();
        final boolean result = propertyStore.getBooleanPropertyValue(SAMPLE_KEY_FOR_BOOLEAN_PROPERTY,
                DEFAULT_VALUE_FOR_SAMPLE_BOOLEAN_PROPERTY);
        assertThat(result, is(DEFAULT_VALUE_FOR_SAMPLE_BOOLEAN_PROPERTY));
    }

    @Test
    public void testDefaultsUsedWhenCannotAccessJNDI() throws Exception {
        setContextToReturnException();
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY));
    }

    private void setContextToReturnException() throws Exception {
        mockery.checking(new Expectations() {
            {
                allowing(mockedContext).lookup("Eniq_Event_Properties");
                will(throwException(new RuntimeErrorException(null)));
            }
        });

    }

    @Test
    public void testIntegerValueNotRestrictedWhenNoMinConfigurableValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(A_VERY_LOW_VALUE));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(A_VERY_LOW_VALUE));
    }

    @Test
    public void testIntegerValueNotRestrictedWhenNoMaxConfigurableValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(A_VERY_LARGE_VALUE));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(A_VERY_LARGE_VALUE));
    }

    @Test
    public void testIntegerValueNotRestrictedWhenMaxConfigurableSetToMinusOne() throws NamingException {
        expectGetPropertiesFromJNDI();
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(A_VERY_LARGE_VALUE));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMaxConfigurableValue(ApplicationConfigManager.NO_LIMIT);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(A_VERY_LARGE_VALUE));
    }

    @Test
    public void testIntegerValueNotRestrictedWhenMinConfigurableSetToMinusOne() throws NamingException {
        expectGetPropertiesFromJNDI();
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(A_VERY_LOW_VALUE));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMinimumConfigurableValue(ApplicationConfigManager.NO_LIMIT);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(A_VERY_LOW_VALUE));
    }

    @Test
    public void testGetIntegerPropertyWhenValidValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        final int validValue = 13;
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(validValue));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMinimumConfigurableValue(MIN_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMaxConfigurableValue(MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(validValue));
    }

    @Test
    public void testGetIntegerPropertyIsDefaultValueWhenInvalidValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        final String someInvalidValue = "not a number";
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, someInvalidValue);
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMaxConfigurableValue(MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);

        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY));
    }

    @Test
    public void testGetIntegerPropertyIsDefaultValueWhenNoValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMaxConfigurableValue(MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY));
    }

    @Test
    public void testGetIntegerPropertyIsSetToHighestPossibleValueWhenVeryHighValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(A_VERY_LARGE_VALUE));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMaxConfigurableValue(MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(MAX_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY));
    }

    @Test
    public void testGetIntegerPropertyIsSetToLowestPossibleValueWhenVeryLowValueSet() throws NamingException {
        expectGetPropertiesFromJNDI();
        applicationProperties.setProperty(SAMPLE_KEY_FOR_INTEGER_PROPERTY, Integer.toString(A_VERY_LOW_VALUE));
        final Property property = new Property(SAMPLE_KEY_FOR_INTEGER_PROPERTY);
        property.setDefaultValue(DEFAULT_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        property.setMinimumConfigurableValue(MIN_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY);
        final int result = propertyStore.getIntegerPropertyValue(property);
        assertThat(result, is(MIN_CONFIGURABLE_VALUE_FOR_SAMPLE_INTEGER_PROPERTY));
    }

    private void expectGetPropertiesFromJNDI() throws NamingException {
        mockery.checking(new Expectations() {
            {
                one(mockedContext).lookup("Eniq_Event_Properties");
                will(returnValue(applicationProperties));
            }
        });

    }

}
