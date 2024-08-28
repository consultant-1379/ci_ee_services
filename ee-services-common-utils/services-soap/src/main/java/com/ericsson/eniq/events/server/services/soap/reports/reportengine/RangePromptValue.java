package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RangePromptValue complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RangePromptValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}PromptValue">
 *       &lt;sequence>
 *         &lt;element name="StartValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}NativeStartValue" minOccurs="0"/>
 *         &lt;element name="EndValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}NativeEndValue" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StartValueInclusive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="StartValueUnbound" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="EndValueInclusive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="EndValueUnbound" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangePromptValue", propOrder = { "startValue", "nativeStartValue", "endValue", "nativeEndValue" })
public class RangePromptValue extends PromptValue {

    @XmlElementRef(name = "StartValue", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> startValue;

    @XmlElement(name = "NativeStartValue", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected Object nativeStartValue;

    @XmlElementRef(name = "EndValue", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> endValue;

    @XmlElement(name = "NativeEndValue", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected Object nativeEndValue;

    @XmlAttribute(name = "StartValueInclusive")
    protected Boolean startValueInclusive;

    @XmlAttribute(name = "StartValueUnbound")
    protected Boolean startValueUnbound;

    @XmlAttribute(name = "EndValueInclusive")
    protected Boolean endValueInclusive;

    @XmlAttribute(name = "EndValueUnbound")
    protected Boolean endValueUnbound;

    /**
     * Gets the value of the startValue property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getStartValue() {
        return startValue;
    }

    /**
     * Sets the value of the startValue property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setStartValue(final JAXBElement<String> value) {
        this.startValue = value;
    }

    /**
     * Gets the value of the nativeStartValue property.
     *
     * @return possible object is
     *         {@link Object }
     */
    public Object getNativeStartValue() {
        return nativeStartValue;
    }

    /**
     * Sets the value of the nativeStartValue property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setNativeStartValue(final Object value) {
        this.nativeStartValue = value;
    }

    /**
     * Gets the value of the endValue property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getEndValue() {
        return endValue;
    }

    /**
     * Sets the value of the endValue property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setEndValue(final JAXBElement<String> value) {
        this.endValue = value;
    }

    /**
     * Gets the value of the nativeEndValue property.
     *
     * @return possible object is
     *         {@link Object }
     */
    public Object getNativeEndValue() {
        return nativeEndValue;
    }

    /**
     * Sets the value of the nativeEndValue property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setNativeEndValue(final Object value) {
        this.nativeEndValue = value;
    }

    /**
     * Gets the value of the startValueInclusive property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public boolean isStartValueInclusive() {
        if (startValueInclusive == null) {
            return true;
        }
        return startValueInclusive;
    }

    /**
     * Sets the value of the startValueInclusive property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setStartValueInclusive(final Boolean value) {
        this.startValueInclusive = value;
    }

    /**
     * Gets the value of the startValueUnbound property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public boolean isStartValueUnbound() {
        if (startValueUnbound == null) {
            return false;
        }
        return startValueUnbound;
    }

    /**
     * Sets the value of the startValueUnbound property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setStartValueUnbound(final Boolean value) {
        this.startValueUnbound = value;
    }

    /**
     * Gets the value of the endValueInclusive property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public boolean isEndValueInclusive() {
        if (endValueInclusive == null) {
            return true;
        }
        return endValueInclusive;
    }

    /**
     * Sets the value of the endValueInclusive property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setEndValueInclusive(final Boolean value) {
        this.endValueInclusive = value;
    }

    /**
     * Gets the value of the endValueUnbound property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public boolean isEndValueUnbound() {
        if (endValueUnbound == null) {
            return false;
        }
        return endValueUnbound;
    }

    /**
     * Sets the value of the endValueUnbound property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setEndValueUnbound(final Boolean value) {
        this.endValueUnbound = value;
    }

}
