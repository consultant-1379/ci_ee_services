package com.ericsson.eniq.events.server.services.soap.reports.session;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DictionaryType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="DictionaryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="USER"/>
 *     &lt;enumeration value="SERVER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DictionaryType")
@XmlEnum
public enum DictionaryType {

    USER,
    SERVER;

    public String value() {
        return name();
    }

    public static DictionaryType fromValue(String v) {
        return valueOf(v);
    }

}
