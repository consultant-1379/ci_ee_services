
package com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Row complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Row">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NE_Version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pmErabEstabAttAdded" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmErabEstabSuccAdded" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmRrcConnEstabSucc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmRrcConnEstabAtt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmS1SigConnEstabSucc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmS1SigConnEstabAtt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmErabEstabSuccInit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmErabEstabAttInit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmZtemporary9" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pmRrcConnEstabAttReatt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Row", propOrder = {
    "date",
    "neVersion",
    "pmErabEstabAttAdded",
    "pmErabEstabSuccAdded",
    "pmRrcConnEstabSucc",
    "pmRrcConnEstabAtt",
    "pmS1SigConnEstabSucc",
    "pmS1SigConnEstabAtt",
    "pmErabEstabSuccInit",
    "pmErabEstabAttInit",
    "pmZtemporary9",
    "pmRrcConnEstabAttReatt"
})
public class Row {

    @XmlElement(name = "Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "NE_Version", required = true, nillable = true)
    protected String neVersion;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmErabEstabAttAdded;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmErabEstabSuccAdded;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmRrcConnEstabSucc;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmRrcConnEstabAtt;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmS1SigConnEstabSucc;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmS1SigConnEstabAtt;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmErabEstabSuccInit;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmErabEstabAttInit;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmZtemporary9;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double pmRrcConnEstabAttReatt;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the neVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNEVersion() {
        return neVersion;
    }

    /**
     * Sets the value of the neVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNEVersion(String value) {
        this.neVersion = value;
    }

    /**
     * Gets the value of the pmErabEstabAttAdded property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmErabEstabAttAdded() {
        return pmErabEstabAttAdded;
    }

    /**
     * Sets the value of the pmErabEstabAttAdded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmErabEstabAttAdded(Double value) {
        this.pmErabEstabAttAdded = value;
    }

    /**
     * Gets the value of the pmErabEstabSuccAdded property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmErabEstabSuccAdded() {
        return pmErabEstabSuccAdded;
    }

    /**
     * Sets the value of the pmErabEstabSuccAdded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmErabEstabSuccAdded(Double value) {
        this.pmErabEstabSuccAdded = value;
    }

    /**
     * Gets the value of the pmRrcConnEstabSucc property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmRrcConnEstabSucc() {
        return pmRrcConnEstabSucc;
    }

    /**
     * Sets the value of the pmRrcConnEstabSucc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmRrcConnEstabSucc(Double value) {
        this.pmRrcConnEstabSucc = value;
    }

    /**
     * Gets the value of the pmRrcConnEstabAtt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmRrcConnEstabAtt() {
        return pmRrcConnEstabAtt;
    }

    /**
     * Sets the value of the pmRrcConnEstabAtt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmRrcConnEstabAtt(Double value) {
        this.pmRrcConnEstabAtt = value;
    }

    /**
     * Gets the value of the pmS1SigConnEstabSucc property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmS1SigConnEstabSucc() {
        return pmS1SigConnEstabSucc;
    }

    /**
     * Sets the value of the pmS1SigConnEstabSucc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmS1SigConnEstabSucc(Double value) {
        this.pmS1SigConnEstabSucc = value;
    }

    /**
     * Gets the value of the pmS1SigConnEstabAtt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmS1SigConnEstabAtt() {
        return pmS1SigConnEstabAtt;
    }

    /**
     * Sets the value of the pmS1SigConnEstabAtt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmS1SigConnEstabAtt(Double value) {
        this.pmS1SigConnEstabAtt = value;
    }

    /**
     * Gets the value of the pmErabEstabSuccInit property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmErabEstabSuccInit() {
        return pmErabEstabSuccInit;
    }

    /**
     * Sets the value of the pmErabEstabSuccInit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmErabEstabSuccInit(Double value) {
        this.pmErabEstabSuccInit = value;
    }

    /**
     * Gets the value of the pmErabEstabAttInit property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmErabEstabAttInit() {
        return pmErabEstabAttInit;
    }

    /**
     * Sets the value of the pmErabEstabAttInit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmErabEstabAttInit(Double value) {
        this.pmErabEstabAttInit = value;
    }

    /**
     * Gets the value of the pmZtemporary9 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmZtemporary9() {
        return pmZtemporary9;
    }

    /**
     * Sets the value of the pmZtemporary9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmZtemporary9(Double value) {
        this.pmZtemporary9 = value;
    }

    /**
     * Gets the value of the pmRrcConnEstabAttReatt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPmRrcConnEstabAttReatt() {
        return pmRrcConnEstabAttReatt;
    }

    /**
     * Sets the value of the pmRrcConnEstabAttReatt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPmRrcConnEstabAttReatt(Double value) {
        this.pmRrcConnEstabAttReatt = value;
    }

}
