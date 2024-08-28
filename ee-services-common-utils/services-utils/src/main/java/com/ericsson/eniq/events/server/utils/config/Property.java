/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.COLON;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.COMMA;

/**
 * Holder for property key, together with the default value for that property, and maximum configurable value
 * 
 * @author eemecoy
 *
 */
public class Property {

    private static final String KEY = "Key";

    private static final String DEFAULT_VALUE = "Default value";

    private static final String MAXIMUM_CONFIGURABLE_VALUE = "Maximum configurable value";

    private static final String MINIMUM_CONFIGURABLE_VALUE = "Minimum configurable value";

    private final String propertyKey;

    private int defaultValue;

    private int maxConfigurableValue = ApplicationConfigManager.NO_LIMIT;

    private int minConfigurableValue = ApplicationConfigManager.NO_LIMIT;

    public Property(final String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getKey() {
        return propertyKey;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public int getMaximumConfigurableValue() {
        return maxConfigurableValue;
    }

    public void setDefaultValue(final int defaultValue) {
        this.defaultValue = defaultValue;

    }

    public void setMaxConfigurableValue(final int maxConfigurableValue) {
        this.maxConfigurableValue = maxConfigurableValue;
    }

    public int getMinimumConfigurableValue() {
        return minConfigurableValue;
    }

    public void setMinimumConfigurableValue(final int minimumConfigurableValue) {
        this.minConfigurableValue = minimumConfigurableValue;

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        appendField(stringBuilder, KEY, propertyKey);
        stringBuilder.append(COMMA);
        appendField(stringBuilder, DEFAULT_VALUE, defaultValue);
        stringBuilder.append(COMMA);
        appendField(stringBuilder, MINIMUM_CONFIGURABLE_VALUE, minConfigurableValue);
        stringBuilder.append(COMMA);
        appendField(stringBuilder, MAXIMUM_CONFIGURABLE_VALUE, maxConfigurableValue);
        return stringBuilder.toString();
    }

    private void appendField(final StringBuilder stringBuilder, final String fieldName, final Object fieldValue) {
        stringBuilder.append(fieldName);
        stringBuilder.append(COLON);
        stringBuilder.append(fieldValue);
    }

}
