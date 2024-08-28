package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PromptLOVRetrievalMode.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="PromptLOVRetrievalMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="ALL"/>
 *     &lt;enumeration value="SELECTED"/>
 *     &lt;enumeration value="CONSTRAINED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "PromptLOVRetrievalMode")
@XmlEnum
public enum PromptLOVRetrievalMode {

    NONE,
    ALL,
    SELECTED,
    CONSTRAINED;

    public String value() {
        return name();
    }

    public static PromptLOVRetrievalMode fromValue(String v) {
        return valueOf(v);
    }

}
