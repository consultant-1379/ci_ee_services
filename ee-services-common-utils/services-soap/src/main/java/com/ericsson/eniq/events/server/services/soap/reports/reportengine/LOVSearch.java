package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LOVSearch complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="LOVSearch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Pattern" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CaseSensitive" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LOVSearch")
public class LOVSearch {

    @XmlAttribute(name = "Pattern", required = true)
    protected String pattern;
    @XmlAttribute(name = "CaseSensitive", required = true)
    protected boolean caseSensitive;

    /**
     * Gets the value of the pattern property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the value of the pattern property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPattern(String value) {
        this.pattern = value;
    }

    /**
     * Gets the value of the caseSensitive property.
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Sets the value of the caseSensitive property.
     */
    public void setCaseSensitive(boolean value) {
        this.caseSensitive = value;
    }

}
