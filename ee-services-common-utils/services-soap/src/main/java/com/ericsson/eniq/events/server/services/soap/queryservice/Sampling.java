package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sampling complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Sampling">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="FreeRandomSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RepeatableRandomSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sampling", namespace = "http://datasource.businessobjects.com/2007/06/01")
public class Sampling {

    @XmlAttribute(name = "FreeRandomSupported", namespace = "http://datasource.businessobjects.com/2007/06/01", required = true)
    protected boolean freeRandomSupported;
    @XmlAttribute(name = "RepeatableRandomSupported", namespace = "http://datasource.businessobjects.com/2007/06/01", required = true)
    protected boolean repeatableRandomSupported;

    /**
     * Gets the value of the freeRandomSupported property.
     */
    public boolean isFreeRandomSupported() {
        return freeRandomSupported;
    }

    /**
     * Sets the value of the freeRandomSupported property.
     */
    public void setFreeRandomSupported(boolean value) {
        this.freeRandomSupported = value;
    }

    /**
     * Gets the value of the repeatableRandomSupported property.
     */
    public boolean isRepeatableRandomSupported() {
        return repeatableRandomSupported;
    }

    /**
     * Sets the value of the repeatableRandomSupported property.
     */
    public void setRepeatableRandomSupported(boolean value) {
        this.repeatableRandomSupported = value;
    }

}
