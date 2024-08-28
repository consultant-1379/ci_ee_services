/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.common;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * @author EEMECOY
 * base class for unit tests that use jmock
 * sets jmock up to run with classes as well as interfaces
 * 
 * using the @RunWith(JMock.class) annotation means that the test will fail if there are any 
 * unfullfilled expectations after the test run has completed
 *
 */
@Ignore
@RunWith(JMock.class)
public class BaseJMockUnitTest {

    protected Mockery mockery = new JUnit4Mockery();
    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    protected <T> T createAndIgnore(final Class<T> classToMock) {
        final T mock = mockery.mock(classToMock);
        mockery.checking(new Expectations() {
            {
                ignoring(mock);
            }
        });
        return mock;
    }
}