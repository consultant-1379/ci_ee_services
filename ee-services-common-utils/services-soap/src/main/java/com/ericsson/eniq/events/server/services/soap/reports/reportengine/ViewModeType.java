package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ViewModeType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="ViewModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOCUMENT"/>
 *     &lt;enumeration value="REPORT"/>
 *     &lt;enumeration value="REPORT_PAGE"/>
 *     &lt;enumeration value="ALL_DATA_PROVIDERS"/>
 *     &lt;enumeration value="DATA_PROVIDER"/>
 *     &lt;enumeration value="REPORT_PART"/>
 *     &lt;enumeration value="QUICK_REPORT_PAGE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "ViewModeType")
@XmlEnum
public enum ViewModeType {

    DOCUMENT,
    REPORT,
    REPORT_PAGE,
    ALL_DATA_PROVIDERS,
    DATA_PROVIDER,
    REPORT_PART,
    QUICK_REPORT_PAGE;

    public String value() {
        return name();
    }

    public static ViewModeType fromValue(String v) {
        return valueOf(v);
    }

}
