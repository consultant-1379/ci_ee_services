package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ValueFromLov complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ValueFromLov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://query.businessobjects.com/2007/06/01}NativeValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RowIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueFromLov", propOrder = {
        "value",
        "nativeValue"
})
public class ValueFromLov {

    @XmlElement(name = "Value", required = true, nillable = true)
    protected String value;
    @XmlElement(name = "NativeValue", namespace = "http://query.businessobjects.com/2007/06/01", nillable = true)
    protected Object nativeValue;
    @XmlAttribute(name = "RowIndex", required = true)
    protected String rowIndex;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
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
