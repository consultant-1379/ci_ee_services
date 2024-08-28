package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectQualification.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="ObjectQualification">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CLASS"/>
 *     &lt;enumeration value="DETAIL"/>
 *     &lt;enumeration value="DIMENSION"/>
 *     &lt;enumeration value="HIERARCHY"/>
 *     &lt;enumeration value="LINKED_DIMENSION"/>
 *     &lt;enumeration value="MEASURE"/>
 *     &lt;enumeration value="PRE_CONDITION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "ObjectQualification", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
@XmlEnum
public enum ObjectQualification {

    CLASS,
    DETAIL,
    DIMENSION,
    HIERARCHY,
    LINKED_DIMENSION,
    MEASURE,
    PRE_CONDITION;

    public String value() {
        return name();
    }

    public static ObjectQualification fromValue(String v) {
        return valueOf(v);
    }

}
