package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DataSourceParameterValue complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataSourceParameterValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Parameter" type="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameter"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="IsDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DPName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DPId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceParameterValue", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01", propOrder = {
        "parameter",
        "value"
})
public class DataSourceParameterValue {

    @XmlElement(name = "Parameter", required = true)
    protected DataSourceParameter parameter;
    @XmlElement(name = "Value", required = true, nillable = true)
    protected Object value;
    @XmlAttribute(name = "IsDefault")
    protected Boolean isDefault;
    @XmlAttribute(name = "DPName")
    protected String dpName;
    @XmlAttribute(name = "DPId")
    protected String dpId;

    /**
     * Gets the value of the parameter property.
     *
     * @return possible object is
     *         {@link DataSourceParameter }
     */
    public DataSourceParameter getParameter() {
        return parameter;
    }

    /**
     * Sets the value of the parameter property.
     *
     * @param value allowed object is
     *              {@link DataSourceParameter }
     */
    public void setParameter(DataSourceParameter value) {
        this.parameter = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     *         {@link Object }
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Gets the value of the isDefault property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setIsDefault(Boolean value) {
        this.isDefault = value;
    }

    /**
     * Gets the value of the dpName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDPName() {
        return dpName;
    }

    /**
     * Sets the value of the dpName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDPName(String value) {
        this.dpName = value;
    }

    /**
     * Gets the value of the dpId property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDPId() {
        return dpId;
    }

    /**
     * Sets the value of the dpId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDPId(String value) {
        this.dpId = value;
    }

}
