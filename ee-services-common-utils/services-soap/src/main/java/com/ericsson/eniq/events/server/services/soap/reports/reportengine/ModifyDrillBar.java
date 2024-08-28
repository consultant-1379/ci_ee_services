package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModifyDrillBar complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ModifyDrillBar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="add" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="remove" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModifyDrillBar", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
public class ModifyDrillBar {

    @XmlAttribute(namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
    protected String add;
    @XmlAttribute(namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
    protected String remove;

    /**
     * Gets the value of the add property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAdd() {
        return add;
    }

    /**
     * Sets the value of the add property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAdd(String value) {
        this.add = value;
    }

    /**
     * Gets the value of the remove property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRemove() {
        return remove;
    }

    /**
     * Sets the value of the remove property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRemove(String value) {
        this.remove = value;
    }

}
