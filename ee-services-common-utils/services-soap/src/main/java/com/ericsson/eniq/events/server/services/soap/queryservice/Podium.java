package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Podium.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="Podium">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Top"/>
 *     &lt;enumeration value="Bottom"/>
 *     &lt;enumeration value="Top_Percentage"/>
 *     &lt;enumeration value="Bottom_Percentage"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "Podium")
@XmlEnum
public enum Podium {

    @XmlEnumValue("Top")
    TOP("Top"),
    @XmlEnumValue("Bottom")
    BOTTOM("Bottom"),
    @XmlEnumValue("Top_Percentage")
    TOP_PERCENTAGE("Top_Percentage"),
    @XmlEnumValue("Bottom_Percentage")
    BOTTOM_PERCENTAGE("Bottom_Percentage");
    private final String value;

    Podium(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Podium fromValue(String v) {
        for (Podium c : Podium.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
