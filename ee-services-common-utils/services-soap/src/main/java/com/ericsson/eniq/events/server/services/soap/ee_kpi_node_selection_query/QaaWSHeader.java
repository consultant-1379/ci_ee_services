package com.ericsson.eniq.events.server.services.soap.ee_kpi_node_selection_query;

import javax.xml.bind.JAXBElement;
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
 *         &lt;element name="serializedSession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sessionID",
        "serializedSession"
})
@XmlRootElement(name = "QaaWSHeader")
public class QaaWSHeader {

    @XmlElementRef(name = "sessionID", namespace = "EE_KPI_Node_Selection_Query", type = JAXBElement.class)
    protected JAXBElement<String> sessionID;
    @XmlElementRef(name = "serializedSession", namespace = "EE_KPI_Node_Selection_Query", type = JAXBElement.class)
    protected JAXBElement<String> serializedSession;

    /**
     * Gets the value of the sessionID property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setSessionID(JAXBElement<String> value) {
        this.sessionID = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the serializedSession property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getSerializedSession() {
        return serializedSession;
    }

    /**
     * Sets the value of the serializedSession property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setSerializedSession(JAXBElement<String> value) {
        this.serializedSession = ((JAXBElement<String>) value);
    }

}
