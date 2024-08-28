package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Values complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Values">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}Operand">
 *       &lt;sequence>
 *         &lt;element name="FreeValue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://query.businessobjects.com/2007/06/01}NativeFreeValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ValueFromLov" type="{http://query.businessobjects.com/2005}ValueFromLov" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Values", propOrder = {
        "freeValue",
        "nativeFreeValue",
        "valueFromLov"
})
public class Values
        extends Operand {

    @XmlElement(name = "FreeValue")
    protected List<String> freeValue;
    @XmlElement(name = "NativeFreeValue", namespace = "http://query.businessobjects.com/2007/06/01", nillable = true)
    protected List<Object> nativeFreeValue;
    @XmlElement(name = "ValueFromLov")
    protected List<ValueFromLov> valueFromLov;

    /**
     * Gets the value of the freeValue property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the freeValue property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFreeValue().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getFreeValue() {
        if (freeValue == null) {
            freeValue = new ArrayList<String>();
        }
        return this.freeValue;
    }

    /**
     * Gets the value of the nativeFreeValue property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nativeFreeValue property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNativeFreeValue().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     */
    public List<Object> getNativeFreeValue() {
        if (nativeFreeValue == null) {
            nativeFreeValue = new ArrayList<Object>();
        }
        return this.nativeFreeValue;
    }

    /**
     * Gets the value of the valueFromLov property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueFromLov property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueFromLov().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.ValueFromLov }
     */
    public List<ValueFromLov> getValueFromLov() {
        if (valueFromLov == null) {
            valueFromLov = new ArrayList<ValueFromLov>();
        }
        return this.valueFromLov;
    }

}
