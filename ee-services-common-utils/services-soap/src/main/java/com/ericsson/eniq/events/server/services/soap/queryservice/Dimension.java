package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Dimension complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Dimension">
 *   &lt;complexContent>
 *     &lt;extension base="{http://datasource.businessobjects.com/2005}DataSourceObject">
 *       &lt;sequence>
 *         &lt;element name="Detail" type="{http://datasource.businessobjects.com/2005}Detail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" use="required" type="{http://datasource.businessobjects.com/2005}ObjectType" />
 *       &lt;attribute name="HasLov" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimension", namespace = "http://datasource.businessobjects.com/2005", propOrder = {
        "detail"
})
public class Dimension
        extends DataSourceObject {

    @XmlElement(name = "Detail")
    protected List<Detail> detail;
    @XmlAttribute(name = "Type", required = true)
    protected ObjectType type;
    @XmlAttribute(name = "HasLov", required = true)
    protected boolean hasLov;

    /**
     * Gets the value of the detail property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detail property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetail().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.Detail }
     */
    public List<Detail> getDetail() {
        if (detail == null) {
            detail = new ArrayList<Detail>();
        }
        return this.detail;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link ObjectType }
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link ObjectType }
     */
    public void setType(ObjectType value) {
        this.type = value;
    }

    /**
     * Gets the value of the hasLov property.
     */
    public boolean isHasLov() {
        return hasLov;
    }

    /**
     * Sets the value of the hasLov property.
     */
    public void setHasLov(boolean value) {
        this.hasLov = value;
    }

}
