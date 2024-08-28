package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for WSResource complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="WSResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="URL" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="WSType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSResource", propOrder = {
        "description"
})
public class WSResource {

    @XmlElement(name = "Description")
    protected String description;
    @XmlAttribute(name = "URL", required = true)
    protected String url;
    @XmlAttribute(name = "WSType", required = true)
    protected String wsType;

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the url property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the wsType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getWSType() {
        return wsType;
    }

    /**
     * Sets the value of the wsType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWSType(String value) {
        this.wsType = value;
    }

}
