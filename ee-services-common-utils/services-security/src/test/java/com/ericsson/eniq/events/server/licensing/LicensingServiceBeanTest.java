/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import com.ericsson.eniq.events.server.common.exception.CannotAccessLicensingServiceException;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.licensing.cache.*;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author EEMECOY
 *
 */
public class LicensingServiceBeanTest extends BaseJMockUnitTest {

    private static final String SOME_LICENSE_NAME = "CXCDUMMY";

    private LicensingServiceBean objToTest;

    LicensingCache mockedLicensingCache;

    LicenseInformation mockLicenseInformation;

    @Before
    public void setUp() throws CannotAccessLicensingServiceException {

        mockedLicensingCache = mockery.mock(LicensingCache.class);
        mockLicenseInformation = mockery.mock(LicenseInformation.class);
        objToTest = new StubbedLicensingServiceBean();
    }

    @Test(expected = CannotAccessLicensingServiceException.class)
    public void testWhenRemoteExceptionThrownFromENIQLicensingService() throws RemoteException,
            CannotAccessLicensingServiceException {
        setLicensingServiceToThrowExceptionOnHasStartUpLicenseCall();
        objToTest.hasLicense(SOME_LICENSE_NAME);
    }

    @Test
    public void testHasLicenseWhenItDoes() throws Exception {
        expectCallOnLicensingService(true);
        assertThat(objToTest.hasLicense(SOME_LICENSE_NAME), is(true));
    }

    @Test
    public void testHasLicenseWhenItDoesnt() throws Exception {
        expectCallOnLicensingService(false);
        assertThat(objToTest.hasLicense(SOME_LICENSE_NAME), is(false));
    }

    @Test
    public void testGetLicensesInformation()throws Exception {
        expectCallLicenseInformation();
        assertThat(objToTest.getLicenseInformation().get(0).getDescription(), is("desc"));


    }

    private void expectCallLicenseInformation () throws RemoteException {
        mockery.checking(new Expectations()
        {
            {
                final Vector<LicenseInformation> licenses = new Vector<LicenseInformation>();
                licenses.add(mockLicenseInformation);
                oneOf(mockedLicensingCache).getLicenseInformation();
                will(returnValue(licenses));

                oneOf(mockLicenseInformation).getDescription();
                will(returnValue("desc"));


            }
        });
    }

    private void expectCallOnLicensingService(final boolean doesLicenseExist) throws RemoteException {
        final LicenseDescriptor licensedescriptor = new DefaultLicenseDescriptor(SOME_LICENSE_NAME);
        final LicensingResponse mockedLicensingResponse = mockery.mock(LicensingResponse.class);
        mockery.checking(new Expectations() {
            {
                one(mockedLicensingCache).checkLicense(
                        with(LicenseDescriptorMatcher.licenseDesriptorMatches(licensedescriptor)));
                will(returnValue(mockedLicensingResponse));
                one(mockedLicensingResponse).isValid();
                will(returnValue(doesLicenseExist));
            }
        });

    }

    private void setLicensingServiceToThrowExceptionOnHasStartUpLicenseCall() throws RemoteException {
        mockery.checking(new Expectations() {
            {
                one(mockedLicensingCache).checkLicense(with(any(LicenseDescriptor.class)));
                will(throwException(new RemoteException()));
            }
        });

    }

    private class StubbedLicensingServiceBean extends LicensingServiceBean {

        /**
         * @throws CannotAccessLicensingServiceException
         */
        public StubbedLicensingServiceBean() throws CannotAccessLicensingServiceException {
            super();
        }

        @Override
        final LicensingCache getLicensingCacheFromRMIRegistry() throws NotBoundException, MalformedURLException,
                RemoteException {
            return mockedLicensingCache;
        }

    }

}
