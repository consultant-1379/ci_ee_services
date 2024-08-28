package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PreConditionObject complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="PreConditionObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://datasource.businessobjects.com/2005}DataSourceObject">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" use="required" type="{http://datasource.businessobjects.com/2005}ObjectType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreConditionObject", namespace = "http://datasource.businessobjects.com/2005")
public class PreConditionObject
        extends DataSourceObject {

    @XmlAttribute(name = "Type", required = true)
    protected ObjectType type;

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.ObjectType }
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.ObjectType }
     */
    public void setType(ObjectType value) {
        this.type = value;
    }

}
