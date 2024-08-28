package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SimpleSearch complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="SimpleSearch">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bicatalog.dsws.businessobjects.com/2007/06/01}SearchPattern">
 *       &lt;sequence>
 *         &lt;element name="BeginDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="InName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="InAuthor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ObjectType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleSearch", propOrder = {
        "beginDate",
        "endDate"
})
public class SimpleSearch
        extends SearchPattern {

    @XmlElementRef(name = "BeginDate", namespace = "http://bicatalog.dsws.businessobjects.com/2007/06/01", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> beginDate;
    @XmlElementRef(name = "EndDate", namespace = "http://bicatalog.dsws.businessobjects.com/2007/06/01", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> endDate;
    @XmlAttribute(name = "InName")
    protected String inName;
    @XmlAttribute(name = "InAuthor")
    protected String inAuthor;
    @XmlAttribute(name = "ObjectType")
    protected String objectType;

    /**
     * Gets the value of the beginDate property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    public JAXBElement<XMLGregorianCalendar> getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the value of the beginDate property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    public void setBeginDate(JAXBElement<XMLGregorianCalendar> value) {
        this.beginDate = ((JAXBElement<XMLGregorianCalendar>) value);
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    public JAXBElement<XMLGregorianCalendar> getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    public void setEndDate(JAXBElement<XMLGregorianCalendar> value) {
        this.endDate = ((JAXBElement<XMLGregorianCalendar>) value);
    }

    /**
     * Gets the value of the inName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getInName() {
        return inName;
    }

    /**
     * Sets the value of the inName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInName(String value) {
        this.inName = value;
    }

    /**
     * Gets the value of the inAuthor property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getInAuthor() {
        return inAuthor;
    }

    /**
     * Sets the value of the inAuthor property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInAuthor(String value) {
        this.inAuthor = value;
    }

    /**
     * Gets the value of the objectType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Sets the value of the objectType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setObjectType(String value) {
        this.objectType = value;
    }

}
