/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.ericsson.eniq.licensing.cache.LicenseDescriptor;

/**
 * Matcher for the ENIQ LicenseDescriptor objects
 * Used for expectations in unit tests
 * @author EEMECOY
 *
 */
public class LicenseDescriptorMatcher extends TypeSafeMatcher<LicenseDescriptor> {

    private final LicenseDescriptor licenseDescriptor;

    /**
     * @param licenseDesriptorToMatch
     */
    public LicenseDescriptorMatcher(final LicenseDescriptor licenseDesriptorToMatch) {
        licenseDescriptor = licenseDesriptorToMatch;
    }

    /* (non-Javadoc)
     * @see org.junit.internal.matchers.TypeSafeMatcher#matchesSafely(java.lang.Object)
     */
    @Override
    public boolean matchesSafely(final LicenseDescriptor licenseDescriptorToMatch) {
        return licenseDescriptor.getName().equals(licenseDescriptorToMatch.getName());
    }

    /* (non-Javadoc)
     * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
     */
    @Override
    public void describeTo(final Description description) {
        description.appendText("Name of license descriptors should match " + licenseDescriptor.getName());
    }

    /**
     * @param licensedescriptor
     * @return
     */
    public static LicenseDescriptorMatcher licenseDesriptorMatches(final LicenseDescriptor licenseDescriptor) {
        return new LicenseDescriptorMatcher(licenseDescriptor);
    }

}
