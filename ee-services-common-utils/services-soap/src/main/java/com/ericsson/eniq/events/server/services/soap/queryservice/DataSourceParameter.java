package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DataSourceParameter complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataSourceParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DefaultValue" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ClassName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Required" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Type" use="required" type="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameterType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceParameter", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01", propOrder = {
        "defaultValue"
})
public class DataSourceParameter {

    @XmlElement(name = "DefaultValue", required = true, nillable = true)
    protected Object defaultValue;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "ClassName", required = true)
    protected String className;
    @XmlAttribute(name = "Required", required = true)
    protected boolean required;
    @XmlAttribute(name = "Type", required = true)
    protected DataSourceParameterType type;

    /**
     * Gets the value of the defaultValue property.
     *
     * @return possible object is
     *         {@link Object }
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setDefaultValue(Object value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the className property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the required property.
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets the value of the required property.
     */
    public void setRequired(boolean value) {
        this.required = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link DataSourceParameterType }
     */
    public DataSourceParameterType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link DataSourceParameterType }
     */
    public void setType(DataSourceParameterType value) {
        this.type = value;
    }

}
