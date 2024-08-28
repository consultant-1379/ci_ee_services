package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetrieveBinaryView complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveBinaryView">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}RetrieveView">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Transport" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}TransportType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveBinaryView")
public class RetrieveBinaryView
        extends RetrieveView {

    @XmlAttribute(name = "Transport", required = true)
    protected TransportType transport;

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
