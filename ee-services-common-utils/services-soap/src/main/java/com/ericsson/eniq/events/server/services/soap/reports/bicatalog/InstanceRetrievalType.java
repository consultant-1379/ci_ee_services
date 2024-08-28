package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstanceRetrievalType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="InstanceRetrievalType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INSTANCEONLY"/>
 *     &lt;enumeration value="WITHOUTINSTANCE"/>
 *     &lt;enumeration value="ALL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "InstanceRetrievalType")
@XmlEnum
public enum InstanceRetrievalType {

    INSTANCEONLY,
    WITHOUTINSTANCE,
    ALL;

    public String value() {
        return name();
    }

    public static InstanceRetrievalType fromValue(String v) {
        return valueOf(v);
    }

}
