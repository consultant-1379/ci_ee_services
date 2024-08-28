package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CombinedQueryOperator.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="CombinedQueryOperator">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Union"/>
 *     &lt;enumeration value="Intersect"/>
 *     &lt;enumeration value="Minus"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "CombinedQueryOperator")
@XmlEnum
public enum CombinedQueryOperator {

    @XmlEnumValue("Union")
    UNION("Union"),
    @XmlEnumValue("Intersect")
    INTERSECT("Intersect"),
    @XmlEnumValue("Minus")
    MINUS("Minus");
    private final String value;

    CombinedQueryOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CombinedQueryOperator fromValue(String v) {
        for (CombinedQueryOperator c : CombinedQueryOperator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
