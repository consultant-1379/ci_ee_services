package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for CallbackOption complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="CallbackOption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="CallbackScript" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CallbackFrame" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DocumentReferenceHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallbackOption")
@XmlSeeAlso({
        EmbeddedCallbackOption.class,
        DrillOption.class,
        ImageManagement.class
})
public class CallbackOption {

    @XmlAttribute(name = "CallbackScript")
    protected String callbackScript;
    @XmlAttribute(name = "CallbackFrame")
    protected String callbackFrame;
    @XmlAttribute(name = "DocumentReferenceHolder")
    protected String documentReferenceHolder;

    /**
     * Gets the value of the callbackScript property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getCallbackScript() {
        return callbackScript;
    }

    /**
     * Sets the value of the callbackScript property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCallbackScript(String value) {
        this.callbackScript = value;
    }

    /**
     * Gets the value of the callbackFrame property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getCallbackFrame() {
        return callbackFrame;
    }

    /**
     * Sets the value of the callbackFrame property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCallbackFrame(String value) {
        this.callbackFrame = value;
    }

    /**
     * Gets the value of the documentReferenceHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDocumentReferenceHolder() {
        return documentReferenceHolder;
    }

    /**
     * Sets the value of the documentReferenceHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentReferenceHolder(String value) {
        this.documentReferenceHolder = value;
    }

}
