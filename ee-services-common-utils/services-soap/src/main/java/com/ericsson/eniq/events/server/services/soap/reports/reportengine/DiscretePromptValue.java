package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DiscretePromptValue complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DiscretePromptValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}PromptValue">
 *       &lt;sequence>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}NativeValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RowIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscretePromptValue", propOrder = {
        "value",
        "nativeValue"
})
public class DiscretePromptValue
        extends PromptValue {

    @XmlElementRef(name = "Value", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> value;
    @XmlElement(name = "NativeValue", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected Object nativeValue;
    @XmlAttribute(name = "RowIndex")
    protected String rowIndex;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setValue(JAXBElement<String> value) {
        this.value = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the nativeValue property.
     *
     * @return possible object is
     *         {@link Object }
     */
    public Object getNativeValue() {
        return nativeValue;
    }

    /**
     * Sets the value of the nativeValue property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setNativeValue(Object value) {
        this.nativeValue = value;
    }

    /**
     * Gets the value of the rowIndex property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets the value of the rowIndex property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRowIndex(String value) {
        this.rowIndex = value;
    }

}
