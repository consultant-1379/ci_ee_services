package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataProviderUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="includeQuerySpecification" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "documentReference",
        "dataProviderUID",
        "includeQuerySpecification"
})
@XmlRootElement(name = "getDataProviderInformation", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class GetDataProviderInformation {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String session;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String documentReference;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String dataProviderUID;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005")
    protected boolean includeQuerySpecification;

    /**
     * Gets the value of the session property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSession(String value) {
        this.session = value;
    }

    /**
     * Gets the value of the documentReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDocumentReference() {
        return documentReference;
    }

    /**
     * Sets the value of the documentReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentReference(String value) {
        this.documentReference = value;
    }

    /**
     * Gets the value of the dataProviderUID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDataProviderUID() {
        return dataProviderUID;
    }

    /**
     * Sets the value of the dataProviderUID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDataProviderUID(String value) {
        this.dataProviderUID = value;
    }

    /**
     * Gets the value of the includeQuerySpecification property.
     */
    public boolean isIncludeQuerySpecification() {
        return includeQuerySpecification;
    }

    /**
     * Sets the value of the includeQuerySpecification property.
     */
    public void setIncludeQuerySpecification(boolean value) {
        this.includeQuerySpecification = value;
    }

}
