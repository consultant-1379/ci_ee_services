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
 *         &lt;element name="DocumentInformation" type="{http://reportengine.dsws.businessobjects.com/2005}DocumentInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "documentInformation"
})
@XmlRootElement(name = "getDataSourceObjectLOVResponse")
public class GetDataSourceObjectLOVResponse {

    @XmlElement(name = "DocumentInformation", required = true, nillable = true)
    protected DocumentInformation documentInformation;

    /**
     * Gets the value of the documentInformation property.
     *
     * @return possible object is
     *         {@link DocumentInformation }
     */
    public DocumentInformation getDocumentInformation() {
        return documentInformation;
    }

    /**
     * Sets the value of the documentInformation property.
     *
     * @param value allowed object is
     *              {@link DocumentInformation }
     */
    public void setDocumentInformation(DocumentInformation value) {
        this.documentInformation = value;
    }

}
