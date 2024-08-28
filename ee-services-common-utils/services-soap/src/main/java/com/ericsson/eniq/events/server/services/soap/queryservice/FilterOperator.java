package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilterOperator.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="FilterOperator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Equal"/>
 *     &lt;enumeration value="NotEqual"/>
 *     &lt;enumeration value="Greater"/>
 *     &lt;enumeration value="GreaterOrEqual"/>
 *     &lt;enumeration value="Less"/>
 *     &lt;enumeration value="LessOrEqual"/>
 *     &lt;enumeration value="Between"/>
 *     &lt;enumeration value="NotBetween"/>
 *     &lt;enumeration value="InList"/>
 *     &lt;enumeration value="NotInList"/>
 *     &lt;enumeration value="IsNull"/>
 *     &lt;enumeration value="IsNotNull"/>
 *     &lt;enumeration value="Like"/>
 *     &lt;enumeration value="NotLike"/>
 *     &lt;enumeration value="Both"/>
 *     &lt;enumeration value="Except"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "FilterOperator")
@XmlEnum
public enum FilterOperator {

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
    @XmlEnumValue("Between")
    BETWEEN("Between"),
    @XmlEnumValue("NotBetween")
    NOT_BETWEEN("NotBetween"),
    @XmlEnumValue("InList")
    IN_LIST("InList"),
    @XmlEnumValue("NotInList")
    NOT_IN_LIST("NotInList"),
    @XmlEnumValue("IsNull")
    IS_NULL("IsNull"),
    @XmlEnumValue("IsNotNull")
    IS_NOT_NULL("IsNotNull"),
    @XmlEnumValue("Like")
    LIKE("Like"),
    @XmlEnumValue("NotLike")
    NOT_LIKE("NotLike"),
    @XmlEnumValue("Both")
    BOTH("Both"),
    @XmlEnumValue("Except")
    EXCEPT("Except");
    private final String value;

    FilterOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FilterOperator fromValue(String v) {
        for (FilterOperator c : FilterOperator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
