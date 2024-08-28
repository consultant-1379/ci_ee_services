package com.ericsson.eniq.events.server.utils.sbr;

import java.lang.annotation.Annotation;

import com.ericsson.eniq.events.ui.shared.annotations.FieldMappingInfo;

/**
 * Helps creating <i>FieldMappingInfo</i> "annotations" at runtime.
 * 
 * @author ejedmar
 */
public abstract class AbstractFieldMappingInfo implements Annotation, FieldMappingInfo {

    @Override
    public String fieldName() {
        return null;
    }

    @Override
    public String columnName() {
        return null;
    }

    @Override
    public int columnIndex() {
        return 0;
    }

    @Override
    public boolean isLookupEnum() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class enumType() {
        return null;
    }

    @Override
    public String lookupMethod() {
        return null;
    }

    @Override
    public boolean isTimeStamp() {
        return false;
    }

    @Override
    public boolean canHaveMultiples() {
        return false;
    }

    @Override
    public boolean outputAsString() {
        return false;
    }

    @Override
    public String groupId() {
        return null;
    }

    @Override
    public String connectionPropertiesKey() {
        return null;
    }

    @Override
    public String nullValueReplacement() {
        return null;
    }

    @Override
    public String defaultEnumValue() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}