/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * 
 * @author EEMECOY
 *
 */
public class PropertyMatcher extends TypeSafeMatcher<Property> {

    private final Property property;

    public PropertyMatcher(final Property propertyToMatch) {
        property = propertyToMatch;
    }

    /* (non-Javadoc)
     * @see org.junit.internal.matchers.TypeSafeMatcher#matchesSafely(java.lang.Object)
     */
    @Override
    public boolean matchesSafely(final Property propertyToMatch) {
        return property.getKey().equals(propertyToMatch.getKey())
                && property.getDefaultValue() == propertyToMatch.getDefaultValue()
                && property.getMaximumConfigurableValue() == propertyToMatch.getMaximumConfigurableValue()
                && property.getMinimumConfigurableValue() == propertyToMatch.getMinimumConfigurableValue();
    }

    /* (non-Javadoc)
     * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
     */
    @Override
    public void describeTo(final Description description) {
        description.appendText("Property should match: " + property);
    }

    public static PropertyMatcher propertyMatches(final Property property) {
        return new PropertyMatcher(property);
    }

}
