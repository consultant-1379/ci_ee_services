package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DSWSException complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DSWSException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Operation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CallStackTrace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CauseException" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CauseID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CauseMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CauseDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DSWSException", namespace = "http://dsws.businessobjects.com/2007/06/01", propOrder = {
        "id",
        "message",
        "operation",
        "callStackTrace",
        "causeException",
        "causeID",
        "causeMessage",
        "causeDetail"
})
public class DSWSException {

    @XmlElement(name = "ID", namespace = "", required = true)
    protected String id;
    @XmlElement(name = "Message", namespace = "", required = true)
    protected String message;
    @XmlElement(name = "Operation", namespace = "", required = true)
    protected String operation;
    @XmlElementRef(name = "CallStackTrace", type = JAXBElement.class)
    protected JAXBElement<String> callStackTrace;
    @XmlElementRef(name = "CauseException", type = JAXBElement.class)
    protected JAXBElement<String> causeException;
    @XmlElementRef(name = "CauseID", type = JAXBElement.class)
    protected JAXBElement<String> causeID;
    @XmlElementRef(name = "CauseMessage", type = JAXBElement.class)
    protected JAXBElement<String> causeMessage;
    @XmlElementRef(name = "CauseDetail", type = JAXBElement.class)
    protected JAXBElement<String> causeDetail;

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setID(String value) {
        this.id = value;
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
     * Gets the value of the operation property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the value of the callStackTrace property.
     *
     * @return possible object is
     *         {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCallStackTrace() {
        return callStackTrace;
    }

    /**
     * Sets the value of the callStackTrace property.
     *
     * @param value allowed object is
     *              {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCallStackTrace(JAXBElement<String> value) {
        this.callStackTrace = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the causeException property.
     *
     * @return possible object is
     *         {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCauseException() {
        return causeException;
    }

    /**
     * Sets the value of the causeException property.
     *
     * @param value allowed object is
     *              {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCauseException(JAXBElement<String> value) {
        this.causeException = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the causeID property.
     *
     * @return possible object is
     *         {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCauseID() {
        return causeID;
    }

    /**
     * Sets the value of the causeID property.
     *
     * @param value allowed object is
     *              {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCauseID(JAXBElement<String> value) {
        this.causeID = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the causeMessage property.
     *
     * @return possible object is
     *         {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCauseMessage() {
        return causeMessage;
    }

    /**
     * Sets the value of the causeMessage property.
     *
     * @param value allowed object is
     *              {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCauseMessage(JAXBElement<String> value) {
        this.causeMessage = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the causeDetail property.
     *
     * @return possible object is
     *         {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCauseDetail() {
        return causeDetail;
    }

    /**
     * Sets the value of the causeDetail property.
     *
     * @param value allowed object is
     *              {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCauseDetail(JAXBElement<String> value) {
        this.causeDetail = ((JAXBElement<String>) value);
    }

}
