/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import javax.management.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 *
 */
public class AMXPropertyReaderTest {

        protected Mockery mockery = new JUnit4Mockery();
    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    private AMXPropertyReader objToTest;

    MBeanServer mockedMBeanServer;

    @Before
    public void setup() {
        mockedMBeanServer = mockery.mock(MBeanServer.class);
        objToTest = new StubbedAMXPropertyReader();
    }

    @Test(expected = CannotReadAMXPropertyException.class)
    public void testExceptionWrapping() throws Exception {
        final String propertyType = "somePropertyType";
        final String propertyName = "somePropertyName";
        final String attribute = "poolSize";
        final Object attributeValue = "300";
        throwExceptionOnGetAttributeOnMBeanServer("amx:pp=/domain/resources,type=" + propertyType + ",name="
                + propertyName, attribute);
        final Object result = objToTest.getAttribute(propertyType, propertyName, attribute);
        assertThat(result, is(attributeValue));
    }

    private void throwExceptionOnGetAttributeOnMBeanServer(final String string, final String attribute)
            throws Exception {
        final ObjectName objectName = new ObjectName(string);
        mockery.checking(new Expectations() {
            {

                one(mockedMBeanServer).getAttribute(objectName, attribute);
                will(throwException(new ReflectionException(null)));
            }
        });

    }

    @Test
    public void testGetProperty() throws Exception {
        final String propertyType = "somePropertyType";
        final String propertyName = "somePropertyName";
        final String attribute = "poolSize";
        final Object attributeValue = "300";
        expectGetAttributeOnMBeanServer("amx:pp=/domain/resources,type=" + propertyType + ",name=" + propertyName,
                attribute, attributeValue);
        final Object result = objToTest.getAttribute(propertyType, propertyName, attribute);
        assertThat(result, is(attributeValue));
    }

    private void expectGetAttributeOnMBeanServer(final String string, final String attribute,
            final Object attributeValue) throws MalformedObjectNameException, NullPointerException,
            AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
        final ObjectName objectName = new ObjectName(string);
        mockery.checking(new Expectations() {
            {
                one(mockedMBeanServer).getAttribute(objectName, attribute);
                will(returnValue(attributeValue));
            }
        });

    }

    class StubbedAMXPropertyReader extends AMXPropertyReader {

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.config.AMXPropertyReader#getPlatformMBeanServerFromManagementFactory()
         */
        @Override
        MBeanServer getPlatformMBeanServerFromManagementFactory() {
            return mockedMBeanServer;
        }

    }

}
