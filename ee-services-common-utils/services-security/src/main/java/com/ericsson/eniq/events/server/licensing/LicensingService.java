/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import com.ericsson.eniq.events.server.common.exception.CannotAccessLicensingServiceException;
import com.ericsson.eniq.licensing.cache.LicenseInformation;

import java.util.Vector;

/**
 * Interface for accessing the ENIQ RMI Licensing service
 * 
 * @author EEMECOY
 *
 */
public interface LicensingService {

    /**
     * Check if the specified license is a valid license
     * This class checks all license information with the ENIQ RMI Licensing service
     * 
     * @param licenseCXC                eg CXC123456
     * @return boolean                  true if specified license valid, false otherwise
     * @throws CannotAccessLicensingServiceException 
     */
    boolean hasLicense(String licenseCXC) throws CannotAccessLicensingServiceException;

    Vector<LicenseInformation> getLicenseInformation() throws CannotAccessLicensingServiceException;


}
