package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

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
 *         &lt;element name="sessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sessionID",
        "documentReference",
        "imageName"
})
@XmlRootElement(name = "getImage")
public class GetImage {

    protected String sessionID;
    protected String documentReference;
    @XmlElement(name = "ImageName")
    protected String imageName;

    /**
     * Gets the value of the sessionID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSessionID(String value) {
        this.sessionID = value;
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
     * Gets the value of the imageName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets the value of the imageName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setImageName(String value) {
        this.imageName = value;
    }

}
