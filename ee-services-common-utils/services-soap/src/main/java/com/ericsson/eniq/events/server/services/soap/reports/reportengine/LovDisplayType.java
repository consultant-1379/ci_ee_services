package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LovDisplayType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="LovDisplayType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HIERARCHICAL"/>
 *     &lt;enumeration value="HIERARCHICAL_ANY_LEVEL"/>
 *     &lt;enumeration value="NESTED"/>
 *     &lt;enumeration value="STANDARD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "LovDisplayType")
@XmlEnum
public enum LovDisplayType {

    HIERARCHICAL,
    HIERARCHICAL_ANY_LEVEL,
    NESTED,
    STANDARD;

    public String value() {
        return name();
    }

    public static LovDisplayType fromValue(String v) {
        return valueOf(v);
    }

}
