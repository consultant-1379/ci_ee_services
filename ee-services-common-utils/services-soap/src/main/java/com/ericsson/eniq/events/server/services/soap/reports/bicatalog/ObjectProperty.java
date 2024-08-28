package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ObjectProperty complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ObjectProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anyType" form="unqualified"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectProperty", propOrder = {
        "value"
})
public class ObjectProperty {

    @XmlElement(name = "Value", namespace = "", required = true)
    protected Object value;
    @XmlAttribute(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     *         {@link Object }
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

}
