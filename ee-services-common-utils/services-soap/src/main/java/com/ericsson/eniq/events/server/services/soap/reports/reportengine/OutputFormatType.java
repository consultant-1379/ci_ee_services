package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutputFormatType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="OutputFormatType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BINARY_CONTENT"/>
 *     &lt;enumeration value="HTML"/>
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="EXCEL"/>
 *     &lt;enumeration value="WORD"/>
 *     &lt;enumeration value="RTF"/>
 *     &lt;enumeration value="XML"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "OutputFormatType")
@XmlEnum
public enum OutputFormatType {

    BINARY_CONTENT,
    HTML,
    PDF,
    EXCEL,
    WORD,
    RTF,
    XML;

    public String value() {
        return name();
    }

    public static OutputFormatType fromValue(String v) {
        return valueOf(v);
    }

}
