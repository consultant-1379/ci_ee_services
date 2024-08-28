package com.ericsson.eniq.events.server.common;

/**
 * Enumerates the types of the event data source
 * whethere aggregated or not and the aggregation
 * interval. 
 * The intent is to replace constants currently
 * in ApplicationConstants and make the code for
 * determination of data source more understandable.
 * 
 * The string values are currently cryptic and used
 * in the query velocity templates. Refactoring 
 * opportunity. 
 * 
 * @author edeccox
 *
 */
public enum EventDataSourceType {
    RAW("TR_1"), AGGREGATED_1MIN("TR_2"), AGGREGATED_15MIN("TR_3"), AGGREGATED_DAY("TR_4");

    EventDataSourceType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

    public static EventDataSourceType getEventDataSourceType(final String value) {
        for (final EventDataSourceType type : EventDataSourceType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return RAW;
    }

    private final String value;
}
