package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryScopeLevel.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="QueryScopeLevel">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Level1"/>
 *     &lt;enumeration value="Level2"/>
 *     &lt;enumeration value="Level3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "QueryScopeLevel")
@XmlEnum
public enum QueryScopeLevel {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Level1")
    LEVEL_1("Level1"),
    @XmlEnumValue("Level2")
    LEVEL_2("Level2"),
    @XmlEnumValue("Level3")
    LEVEL_3("Level3");
    private final String value;

    QueryScopeLevel(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QueryScopeLevel fromValue(String v) {
        for (QueryScopeLevel c : QueryScopeLevel.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
