package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReportPart complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ReportPart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="TemporaryReference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UniqueReference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportPart")
public class ReportPart {

    @XmlAttribute(name = "TemporaryReference", required = true)
    protected String temporaryReference;
    @XmlAttribute(name = "UniqueReference", required = true)
    protected String uniqueReference;

    /**
     * Gets the value of the temporaryReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getTemporaryReference() {
        return temporaryReference;
    }

    /**
     * Sets the value of the temporaryReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTemporaryReference(String value) {
        this.temporaryReference = value;
    }

    /**
     * Gets the value of the uniqueReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUniqueReference() {
        return uniqueReference;
    }

    /**
     * Sets the value of the uniqueReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUniqueReference(String value) {
        this.uniqueReference = value;
    }

}
