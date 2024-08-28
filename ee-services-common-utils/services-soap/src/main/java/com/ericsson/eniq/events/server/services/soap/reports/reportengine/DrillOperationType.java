package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrillOperationType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="DrillOperationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOWN"/>
 *     &lt;enumeration value="UP"/>
 *     &lt;enumeration value="BY"/>
 *     &lt;enumeration value="SLICE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DrillOperationType")
@XmlEnum
public enum DrillOperationType {

    DOWN,
    UP,
    BY,
    SLICE;

    public String value() {
        return name();
    }

    public static DrillOperationType fromValue(String v) {
        return valueOf(v);
    }

}
