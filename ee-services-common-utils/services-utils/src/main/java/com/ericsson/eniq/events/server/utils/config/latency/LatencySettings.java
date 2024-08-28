/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.utils.config.Property;
import com.ericsson.eniq.events.server.utils.config.PropertyStore;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.ENIQ_EVENTS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.UNDERSCORE;
import static com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_TO_BE_USED;

/**
 * 
 * Class responsible for managing the latency settings, see http://atrclin2.athtem.eei.ericsson.se/wiki/index.php/ENIQ_Events_Services_-_Latency_Settings
 * for more information on how latency settings are managed
 * @author eemecoy
 *
 */
@Stateless
public class LatencySettings {

    @EJB
    private PropertyStore propertyStore;

    @EJB
    private LatencyPropertyDefaultValuesAndLimits latencyPropertyDefaultValuesAndLimits;

    @EJB
    private TechPackTechnologies techPackTechnologies;

    /**
     * Get the latency offset to be applied for time ranges that fall into the Least Offset bracket
     * 
     * @param techPacks         list of the tech packs used in this query
     */
    public int getTimeDelayLeastOffset(final List<String> techPacks) {
        return getTimeDelay(Offsets.LEAST_OFFSET, techPacks);
    }

    /**
     * Get the latency offset to be applied for time ranges that fall into the Medium Offset bracket
     * 
     * @param techPacks         list of the tech packs used in this query
     */
    public int getTimeDelayMediumOffset(final List<String> techPacks) {
        return getTimeDelay(Offsets.MEDIUM_OFFSET, techPacks);
    }

    /**
     * Get the latency offset to be applied for time ranges that fall into the Large Offset bracket
     * 
     * @param techPacks         list of the tech packs used in this query
     */
    public int getTimeDelayLargeOffset(final List<String> techPacks) {
        return getTimeDelay(Offsets.LARGE_OFFSET, techPacks);
    }

    private int getTimeDelay(final Offsets offsetType, final List<String> techPacks) {
        final OffsetOptions userConfiguredLatencySetting = getLatencySettingUserHasConfigured();
        final String technologyName = getTechnologyToUse(userConfiguredLatencySetting, techPacks);
        final Property latencyProperty = getPropertyNameAndDefaultSettingsForLatencyProperty(offsetType, technologyName);
        return propertyStore.getIntegerPropertyValue(latencyProperty);
    }

    private Property getPropertyNameAndDefaultSettingsForLatencyProperty(final Offsets offset,
            final String technologyName) {
        final String propertyName = putTogetherLatencyPropertyName(technologyName, offset);
        return latencyPropertyDefaultValuesAndLimits.getDefaultAndLimitsForProperty(propertyName);
    }

    /**
     * Put together the latency property name
     * For a technologyName of SGEH and offsetType of LEAST_OFFSET, this will be:
     * ENIQ_EVENTS_SGEH_LEAST_OFFSET
     * 
     * @param technologyName            name of technology
     * @param offsetType                type of offset
     * 
     * @return latency property name, see above
     */
    private String putTogetherLatencyPropertyName(final String technologyName, final Offsets offsetType) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ENIQ_EVENTS);
        stringBuilder.append(UNDERSCORE);
        stringBuilder.append(technologyName);
        stringBuilder.append(UNDERSCORE);
        stringBuilder.append(offsetType);
        return stringBuilder.toString();
    }

    /**
     * Get the technology to use when determining which latency settings to use
     * This depends on the user's configuration:
     * - If the user has selected BEST_EFFORT, then this method will return the technology with the slowest offset
     *   from the list of technologies used in query (the technologies used in the query is determined by the
     *   tech packs used in the query)
     * - Otherwise, the technology returned will be the user's selection
     *
     * @param userConfiguredLatencySetting               the latency setting the user has selected
     * @param techPacks                                  tech packs used in query
     */
    private String getTechnologyToUse(final OffsetOptions userConfiguredLatencySetting, final List<String> techPacks) {
        if (userConfiguredLatencySetting == OffsetOptions.BEST_EFFORT) {
            ServicesLogger
                    .detailed(
                            this.getClass().getName(),
                            "getTechnologyOffsetToUse",
                            "User has selected the Best Effort Option, so will determine the slowest technology latency offset for the tech packs used in this query");
            return getTechnologyWithSlowestOffset(techPacks);
        }
        ServicesLogger.detailed(this.getClass().getName(), "getTechnologyOffsetToUse",
                "User has selected to use the latency offsets for technology " + userConfiguredLatencySetting
                        + ", these latency offsets will be used for all queries");
        return userConfiguredLatencySetting.toString();
    }

    private String getTechnologyWithSlowestOffset(final List<String> techPacks) {
        final Collection<String> technologies = getTechnologiesForGivenTechPacks(techPacks);
        final String slowestTechnology = getSlowestTechnology(technologies);
        ServicesLogger.detailed(this.getClass().getName(), "getTechnologyWithSlowestOffset", "Selecting "
                + slowestTechnology + " as the slowest technology from technologies employed in query (" + technologies
                + "), the latency offsets for the " + slowestTechnology + " will be applied to this query");
        return slowestTechnology;
    }

    private String getSlowestTechnology(final Collection<String> technologies) {
        int slowestOffset = 0;
        String technologyWithSlowestOffset = null;
        for (final String technology : technologies) {
            final Property property = getPropertyNameAndDefaultSettingsForLatencyProperty(Offsets.LEAST_OFFSET,
                    technology);
            final int userConfiguredLatencyForLeastOffset = propertyStore.getIntegerPropertyValue(property);
            if (userConfiguredLatencyForLeastOffset > slowestOffset) {
                slowestOffset = userConfiguredLatencyForLeastOffset;
                technologyWithSlowestOffset = technology;
            }
        }

        return technologyWithSlowestOffset;
    }

    private Set<String> getTechnologiesForGivenTechPacks(final List<String> techPacks) {
        final Set<String> technologies = new HashSet<String>();
        for (final String techPack : techPacks) {
            technologies.add(techPackTechnologies.getTechPackTechnology(techPack));
        }
        return technologies;
    }

    private OffsetOptions getLatencySettingUserHasConfigured() {
        final Property property = new Property(ENIQ_EVENTS_TIME_DELAY_TO_BE_USED);
        property.setDefaultValue(OffsetOptions.BEST_EFFORT.getIntegerValue());
        property.setMinimumConfigurableValue(getMinimumValueInEnum());
        property.setMaxConfigurableValue(getMaximumValueInEnum());
        final int userConfiguredValue = propertyStore.getIntegerPropertyValue(property);
        return translateIntoOffsetOptionsEnum(userConfiguredValue);
    }

    private int getMaximumValueInEnum() {
        int maximumPossibleValue = OffsetOptions.SGEH.getIntegerValue();
        for (final OffsetOptions option : OffsetOptions.values()) {
            if (option.getIntegerValue() > maximumPossibleValue) {
                maximumPossibleValue = option.getIntegerValue();
            }
        }
        return maximumPossibleValue;
    }

    private OffsetOptions translateIntoOffsetOptionsEnum(final int offsetOptionAsInteger) {
        for (final OffsetOptions option : OffsetOptions.values()) {
            if (option.getIntegerValue() == offsetOptionAsInteger) {
                return option;
            }

        }
        ServicesLogger.warn(this.getClass().getName(), "translateIntoEnum", "Invalid value of " + offsetOptionAsInteger
                + " specified for the parameter " + ENIQ_EVENTS_TIME_DELAY_TO_BE_USED + ", will default to "
                + OffsetOptions.SGEH);
        return OffsetOptions.SGEH;
    }

    private int getMinimumValueInEnum() {
        int minimumPossibleValue = OffsetOptions.SGEH.getIntegerValue();
        for (final OffsetOptions option : OffsetOptions.values()) {
            if (option.getIntegerValue() < minimumPossibleValue) {
                minimumPossibleValue = option.getIntegerValue();
            }
        }
        return minimumPossibleValue;
    }

    public void setPropertyStore(final PropertyStore propertyStore) {
        this.propertyStore = propertyStore;
    }

    public void setLatencyPropertyDefaultValuesAndLimits(
            final LatencyPropertyDefaultValuesAndLimits latencyPropertyDefaultValuesAndLimits) {
        this.latencyPropertyDefaultValuesAndLimits = latencyPropertyDefaultValuesAndLimits;
    }

    /**
     * @param techPackTechnologies the techPackDefinitions to set
     */
    public void setTechPackTechnologies(final TechPackTechnologies techPackTechnologies) {
        this.techPackTechnologies = techPackTechnologies;
    }

}
