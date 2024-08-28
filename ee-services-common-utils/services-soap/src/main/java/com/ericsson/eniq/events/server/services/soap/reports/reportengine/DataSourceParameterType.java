package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataSourceParameterType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="DataSourceParameterType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Date"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DataSourceParameterType", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01")
@XmlEnum
public enum DataSourceParameterType {

    @XmlEnumValue("Date")
    DATE("Date");
    private final String value;

    DataSourceParameterType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataSourceParameterType fromValue(String v) {
        for (DataSourceParameterType c : DataSourceParameterType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
