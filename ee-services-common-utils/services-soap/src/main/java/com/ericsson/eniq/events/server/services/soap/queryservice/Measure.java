package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Measure complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Measure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://datasource.businessobjects.com/2005}DataSourceObject">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" use="required" type="{http://datasource.businessobjects.com/2005}ObjectType" />
 *       &lt;attribute name="HasLov" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Measure", namespace = "http://datasource.businessobjects.com/2005")
public class Measure
        extends DataSourceObject {

    @XmlAttribute(name = "Type", required = true)
    protected ObjectType type;
    @XmlAttribute(name = "HasLov", required = true)
    protected boolean hasLov;

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
