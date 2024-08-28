package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for RetrieveXMLView complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveXMLView">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}RetrieveView">
 *       &lt;sequence>
 *         &lt;element name="XPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Transport" type="{http://reportengine.dsws.businessobjects.com/2005}TransportType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveXMLView", propOrder = {
        "xPath"
})
public class RetrieveXMLView
        extends RetrieveView {

    @XmlElementRef(name = "XPath", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> xPath;
    @XmlAttribute(name = "Transport")
    protected TransportType transport;

    /**
     * Gets the value of the xPath property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getXPath() {
        return xPath;
    }

    /**
     * Sets the value of the xPath property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setXPath(JAXBElement<String> value) {
        this.xPath = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the transport property.
     *
     * @return possible object is
     *         {@link TransportType }
     */
    public TransportType getTransport() {
        return transport;
    }

    /**
     * Sets the value of the transport property.
     *
     * @param value allowed object is
     *              {@link TransportType }
     */
    public void setTransport(TransportType value) {
        this.transport = value;
    }

}
