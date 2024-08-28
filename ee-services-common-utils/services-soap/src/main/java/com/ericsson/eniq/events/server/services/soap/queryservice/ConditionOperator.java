package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConditionOperator.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="ConditionOperator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Equal"/>
 *     &lt;enumeration value="NotEqual"/>
 *     &lt;enumeration value="Greater"/>
 *     &lt;enumeration value="GreaterOrEqual"/>
 *     &lt;enumeration value="Less"/>
 *     &lt;enumeration value="LessOrEqual"/>
 *     &lt;enumeration value="InList"/>
 *     &lt;enumeration value="NotInList"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "ConditionOperator")
@XmlEnum
public enum ConditionOperator {

    @XmlEnumValue("Equal")
    EQUAL("Equal"),
    @XmlEnumValue("NotEqual")
    NOT_EQUAL("NotEqual"),
    @XmlEnumValue("Greater")
    GREATER("Greater"),
    @XmlEnumValue("GreaterOrEqual")
    GREATER_OR_EQUAL("GreaterOrEqual"),
    @XmlEnumValue("Less")
    LESS("Less"),
    @XmlEnumValue("LessOrEqual")
    LESS_OR_EQUAL("LessOrEqual"),
    @XmlEnumValue("InList")
    IN_LIST("InList"),
    @XmlEnumValue("NotInList")
    NOT_IN_LIST("NotInList");
    private final String value;

    ConditionOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConditionOperator fromValue(String v) {
        for (ConditionOperator c : ConditionOperator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
