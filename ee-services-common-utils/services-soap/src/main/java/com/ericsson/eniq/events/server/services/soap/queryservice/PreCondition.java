package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for PreCondition complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="PreCondition">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}ConditionBase">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreCondition", propOrder = {
        "name"
})
public class PreCondition
        extends ConditionBase {

    @XmlElementRef(name = "Name", namespace = "http://query.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> name;
    @XmlAttribute(name = "Key", required = true)
    protected String key;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setName(JAXBElement<String> value) {
        this.name = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the key property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKey(String value) {
        this.key = value;
    }

}
