package com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery;

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
 *         &lt;element name="lov" type="{EniqEventsServicesNetworkRNCCellQuery}Lov"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="delegated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partialResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "lov",
        "message",
        "delegated",
        "partialResult"
})
@XmlRootElement(name = "valuesOf_RNC_IdResponse")
public class ValuesOfRNCIdResponse {

    @XmlElement(required = true)
    protected Lov lov;
    @XmlElement(required = true)
    protected String message;
    protected boolean delegated;
    protected boolean partialResult;

    /**
     * Gets the value of the lov property.
     *
     * @return possible object is
     *         {@link Lov }
     */
    public Lov getLov() {
        return lov;
    }

    /**
     * Sets the value of the lov property.
     *
     * @param value allowed object is
     *              {@link Lov }
     */
    public void setLov(Lov value) {
        this.lov = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the delegated property.
     */
    public boolean isDelegated() {
        return delegated;
    }

    /**
     * Sets the value of the delegated property.
     */
    public void setDelegated(boolean value) {
        this.delegated = value;
    }

    /**
     * Gets the value of the partialResult property.
     */
    public boolean isPartialResult() {
        return partialResult;
    }

    /**
     * Sets the value of the partialResult property.
     */
    public void setPartialResult(boolean value) {
        this.partialResult = value;
    }

}
