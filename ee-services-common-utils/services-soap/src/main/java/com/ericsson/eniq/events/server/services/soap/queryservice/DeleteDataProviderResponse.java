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
 *         &lt;element name="updatedDocumentReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "updatedDocumentReference"
})
@XmlRootElement(name = "deleteDataProviderResponse", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class DeleteDataProviderResponse {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String updatedDocumentReference;

    /**
     * Gets the value of the updatedDocumentReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUpdatedDocumentReference() {
        return updatedDocumentReference;
    }

    /**
     * Sets the value of the updatedDocumentReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUpdatedDocumentReference(String value) {
        this.updatedDocumentReference = value;
    }

}
