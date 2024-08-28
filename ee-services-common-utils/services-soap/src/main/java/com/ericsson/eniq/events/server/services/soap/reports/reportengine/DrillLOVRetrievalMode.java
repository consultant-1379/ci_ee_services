package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrillLOVRetrievalMode.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="DrillLOVRetrievalMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="ALL"/>
 *     &lt;enumeration value="SELECTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DrillLOVRetrievalMode")
@XmlEnum
public enum DrillLOVRetrievalMode {

    NONE,
    ALL,
    SELECTED;

    public String value() {
        return name();
    }

    public static DrillLOVRetrievalMode fromValue(String v) {
        return valueOf(v);
    }

}
