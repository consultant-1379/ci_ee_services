package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PromptType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="PromptType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NUMERIC"/>
 *     &lt;enumeration value="TEXT"/>
 *     &lt;enumeration value="BOOLEAN"/>
 *     &lt;enumeration value="CURRENCY"/>
 *     &lt;enumeration value="DATETIME"/>
 *     &lt;enumeration value="TIME"/>
 *     &lt;enumeration value="DATE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "PromptType")
@XmlEnum
public enum PromptType {

    NUMERIC,
    TEXT,
    BOOLEAN,
    CURRENCY,
    DATETIME,
    TIME,
    DATE;

    public String value() {
        return name();
    }

    public static PromptType fromValue(String v) {
        return valueOf(v);
    }

}
