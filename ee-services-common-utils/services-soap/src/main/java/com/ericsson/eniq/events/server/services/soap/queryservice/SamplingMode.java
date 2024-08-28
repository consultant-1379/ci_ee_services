package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SamplingMode.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="SamplingMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Free_Random"/>
 *     &lt;enumeration value="Repeatable_Random"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "SamplingMode", namespace = "http://query.businessobjects.com/2007/06/01")
@XmlEnum
public enum SamplingMode {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Free_Random")
    FREE_RANDOM("Free_Random"),
    @XmlEnumValue("Repeatable_Random")
    REPEATABLE_RANDOM("Repeatable_Random");
    private final String value;

    SamplingMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SamplingMode fromValue(String v) {
        for (SamplingMode c : SamplingMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
