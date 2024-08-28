/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.ericsson.eniq.events.server.common.ApplicationConfigConstants;
import com.ericsson.eniq.events.server.common.exception.CannotAccessLicensingServiceException;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.licensing.cache.DefaultLicenseDescriptor;
import com.ericsson.eniq.licensing.cache.LicenseDescriptor;
import com.ericsson.eniq.licensing.cache.LicenseInformation;
import com.ericsson.eniq.licensing.cache.LicensingCache;
import com.ericsson.eniq.licensing.cache.LicensingResponse;

/**
 * Implemtation of the LicensingService
 * This classes connects to the ENIQ RMI licensing service and checks for licenses
 * 
 * @author EEMECOY
 *
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class LicensingServiceBean implements LicensingService {

    @Resource(name = ApplicationConfigConstants.ENIQ_EVENT_PROPERTIES)
    private Properties eniqEventsProperties;

    @Resource
    private String rmiLicensingServiceURL = null;

    @PostConstruct
    public void setUpProperties() throws IOException {
        final LicensingProperties licensingProperties = new LicensingProperties(eniqEventsProperties);
        rmiLicensingServiceURL = licensingProperties.determineURLOfLicensingService();
    }

    @PreDestroy
    public void cleanProperties() {
        rmiLicensingServiceURL = null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.licensing.LicensingService#hasStartUpLicense()
     */
    @Override
    public boolean hasLicense(final String licenseName) throws CannotAccessLicensingServiceException {
        try {
            final LicensingCache licensingCache = getLicensingCacheFromRMIRegistry();
            final LicenseDescriptor licenseDescriptor = new DefaultLicenseDescriptor(licenseName);
            final LicensingResponse licensingResponse = licensingCache.checkLicense(licenseDescriptor);
            return licensingResponse.isValid();
        } catch (final RemoteException e) {
            processExceptionWhenCannotAccessRemoteLicensingService(e);
        } catch (final MalformedURLException e) {
            processExceptionWhenCannotAccessRemoteLicensingService(e);
        } catch (final NotBoundException e) {
            processExceptionWhenCannotAccessRemoteLicensingService(e);
        }
        //will never hit this line, here to keep the compiler happy
        return false;
    }

    @Override
    public Vector<LicenseInformation> getLicenseInformation() throws CannotAccessLicensingServiceException {
        try {
            final LicensingCache licensingCache = getLicensingCacheFromRMIRegistry();
            return licensingCache.getLicenseInformation();

        } catch (final RemoteException e) {
            ServicesLogger.error("LicensingServiceBean", "getLicenseInformation", e.getMessage());
            processExceptionWhenCannotAccessRemoteLicensingService(e);
        } catch (final MalformedURLException e) {
            ServicesLogger.error("LicensingServiceBean", "getLicenseInformation", e.getMessage());
            processExceptionWhenCannotAccessRemoteLicensingService(e);
        } catch (final NotBoundException e) {
            ServicesLogger.error("LicensingServiceBean", "getLicenseInformation", e.getMessage());
            processExceptionWhenCannotAccessRemoteLicensingService(e);
        }
        //will never hit this line, here to keep the compiler happy
        return new Vector<LicenseInformation>();
    }

    /**
    * handle exceptions coming from trying to access the remote RMI licensing service
    * 
    * @param exceptionFromRMI
    * @throws CannotAccessLicensingServiceException
    */
    private void processExceptionWhenCannotAccessRemoteLicensingService(final Exception exceptionFromRMI)
            throws CannotAccessLicensingServiceException {
        ServicesLogger.error(getClass().getName(), "processExceptionWhenCannotAccessRemoteLicensingService",
                "Could not access the licensing service", exceptionFromRMI);
        throw new CannotAccessLicensingServiceException(exceptionFromRMI);
    }

    /**
     * extracted out for unit testing
     * 
     * @return
     * @throws NotBoundException
     * @throws MalformedURLException
     * @throws RemoteException
     */
    LicensingCache getLicensingCacheFromRMIRegistry() throws NotBoundException, MalformedURLException, RemoteException {
        return (LicensingCache) Naming.lookup(rmiLicensingServiceURL);
    }

}
