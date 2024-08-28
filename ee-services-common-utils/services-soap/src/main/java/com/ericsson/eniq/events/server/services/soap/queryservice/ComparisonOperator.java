package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComparisonOperator.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="ComparisonOperator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="Any"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "ComparisonOperator")
@XmlEnum
public enum ComparisonOperator {

    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("Any")
    ANY("Any");
    private final String value;

    ComparisonOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComparisonOperator fromValue(String v) {
        for (ComparisonOperator c : ComparisonOperator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
