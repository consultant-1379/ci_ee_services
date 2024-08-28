package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrackDataViewMode.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="TrackDataViewMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOCUMENT"/>
 *     &lt;enumeration value="CURRENT_REPORT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "TrackDataViewMode", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
@XmlEnum
public enum TrackDataViewMode {

    DOCUMENT,
    CURRENT_REPORT;

    public String value() {
        return name();
    }

    public static TrackDataViewMode fromValue(String v) {
        return valueOf(v);
    }

}
