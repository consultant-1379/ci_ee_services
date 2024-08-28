/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Class responsible for fetching property values from the JNDI resource in Glassfish
 * Also performs some basic checking ie defaulting to provided values if the value specified in Glassfish is incorrect
 * 
 * @author eemecoy
 *
 */
@Stateless
public class PropertyStore {

    private Context initialContext;

    /**
     * Parses integer property, sets defaults if not there or parse fails
     * If a maximum limit for this property has been set, and the property value exceeds this, then the property is
     * set to this maximum limit
     *
     * @param property  property (contains property key, default value, and maximum limit if set)
     * 
     * @return configured value or default if not formatted correctly or maximum (see above)
     */
    public int getIntegerPropertyValue(final Property property) {
        final Properties p = getApplicationProperties();
        final String value = p.getProperty(property.getKey());
        int parameterValue = property.getDefaultValue();
        if (value != null) {
            try {
                parameterValue = Integer.valueOf(value);
            } catch (final NumberFormatException e) {
                final String msg = "Invalid value for property " + property.getKey() + " (" + value
                        + "), setting default value of " + property.getDefaultValue() + ", Exception: "
                        + e.getMessage();
                ServicesLogger.warn(ApplicationConfigManagerImpl.class.getName(), "getIntegerPropertyValue", msg);
            }
        }
        if (parameterHasMaximumConfigurableLimit(property.getMaximumConfigurableValue())) {
            parameterValue = checkParameterValueDoesntExceedMaxLimitIfSet(property, parameterValue);
        }
        if (parameterHasMinimumConfigurableLimit(property.getMinimumConfigurableValue())) {
            parameterValue = checkParameterValueDoesntExceedMinLimitIfSet(property, parameterValue);
        }
        return parameterValue;
    }

    private int checkParameterValueDoesntExceedMinLimitIfSet(final Property property, final int parameterValue) {
        final int minimumConfigurableValue = property.getMinimumConfigurableValue();
        if (parameterValue < minimumConfigurableValue) {
            ServicesLogger.warn(getClass().getName(), "getIntegerPropertyValue", property.getKey()
                    + " is configured to be " + parameterValue
                    + " but this is less than the minimum possible configurable limit of " + minimumConfigurableValue
                    + ", the property will default to " + minimumConfigurableValue);
            return minimumConfigurableValue;
        }
        return parameterValue;
    }

    private boolean parameterHasMinimumConfigurableLimit(final int minimumConfigurableValue) {
        return isLimitConfigured(minimumConfigurableValue);
    }

    private boolean isLimitConfigured(final int limit) {
        return !(limit == ApplicationConfigManager.NO_LIMIT);
    }

    private boolean parameterHasMaximumConfigurableLimit(final int maximumConfigurableValue) {
        return isLimitConfigured(maximumConfigurableValue);
    }

    private int checkParameterValueDoesntExceedMaxLimitIfSet(final Property property, final int parameterValue) {
        final int maximumConfigurableValue = property.getMaximumConfigurableValue();
        if (parameterValue > maximumConfigurableValue) {
            ServicesLogger.warn(getClass().getName(), "getIntegerPropertyValue", property.getKey()
                    + " is configured to be " + parameterValue
                    + " but this exceeds the maximum possible configurable limit of " + maximumConfigurableValue
                    + ", the property will default to " + maximumConfigurableValue);
            return maximumConfigurableValue;
        }
        return parameterValue;
    }

    /**
     * Get application properties object from JNDI tree.
     *
     * @return config properties object or an empty properties object
     */

    private Properties getApplicationProperties() {
        Properties p = new Properties();
        try {
            if (this.initialContext == null) {
                this.initialContext = new InitialContext();
            }
            p = (Properties) this.initialContext.lookup(ApplicationConfigManager.ENIQ_EVENTS_JNDI_NAME);
        } catch (final Throwable ex) { // NOPMD edeccox - handle gracefully
            ServicesLogger.warn(
                    ApplicationConfigManagerImpl.class.getName(),
                    "getPropertiesFromJndi",
                    "Error accessing JNDI object " + ApplicationConfigManager.ENIQ_EVENTS_JNDI_NAME + ": "
                            + ex.getMessage());
        }
        return p;
    }

    /**
     * Gets the boolean property value.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return the boolean property value
     */
    public boolean getBooleanPropertyValue(final String key, final boolean defaultValue) {
        final Properties p = getApplicationProperties();
        final String value = p.getProperty(key);
        boolean result = defaultValue;
        if (value != null) {
            try {
                result = Boolean.valueOf(value);
            } catch (final Exception e) {
                final String msg = "Invalid value for property " + key + " (" + value + "), setting default value of "
                        + defaultValue + ", Exception: " + e.getMessage();
                ServicesLogger.warn(ApplicationConfigManagerImpl.class.getName(), "getBooleanPropertyValue", msg);
            }
        }
        return result;
    }

    public String getStringPropertyValue(final String key, final String defaultValue) {
        final Properties p = getApplicationProperties();
        return p.getProperty(key, defaultValue);
    }

    void setInitialContext(final Context initialContext) {
        this.initialContext = initialContext;
    }

}
