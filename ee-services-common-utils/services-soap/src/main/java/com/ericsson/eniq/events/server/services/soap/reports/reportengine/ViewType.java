package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ViewType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="ViewType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CHARACTER"/>
 *     &lt;enumeration value="XML"/>
 *     &lt;enumeration value="BINARY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "ViewType")
@XmlEnum
public enum ViewType {

    CHARACTER,
    XML,
    BINARY;

    public String value() {
        return name();
    }

    public static ViewType fromValue(String v) {
        return valueOf(v);
    }

}
