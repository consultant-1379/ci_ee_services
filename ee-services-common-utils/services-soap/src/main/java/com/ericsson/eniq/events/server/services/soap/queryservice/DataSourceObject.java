package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DataSourceObject complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataSourceObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AllowedInResult" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AllowedInFilter" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AllowedInSort" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceObject", namespace = "http://datasource.businessobjects.com/2005")
@XmlSeeAlso({
        Detail.class,
        PreConditionObject.class,
        Dimension.class,
        Measure.class
})
public class DataSourceObject {

    @XmlAttribute(name = "Key", required = true)
    protected String key;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Description", required = true)
    protected String description;
    @XmlAttribute(name = "AllowedInResult", required = true)
    protected boolean allowedInResult;
    @XmlAttribute(name = "AllowedInFilter", required = true)
    protected boolean allowedInFilter;
    @XmlAttribute(name = "AllowedInSort", required = true)
    protected boolean allowedInSort;

    /**
     * Gets the value of the key property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKey(String value) {
        this.key = value;
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
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the allowedInResult property.
     */
    public boolean isAllowedInResult() {
        return allowedInResult;
    }

    /**
     * Sets the value of the allowedInResult property.
     */
    public void setAllowedInResult(boolean value) {
        this.allowedInResult = value;
    }

    /**
     * Gets the value of the allowedInFilter property.
     */
    public boolean isAllowedInFilter() {
        return allowedInFilter;
    }

    /**
     * Sets the value of the allowedInFilter property.
     */
    public void setAllowedInFilter(boolean value) {
        this.allowedInFilter = value;
    }

    /**
     * Gets the value of the allowedInSort property.
     */
    public boolean isAllowedInSort() {
        return allowedInSort;
    }

    /**
     * Sets the value of the allowedInSort property.
     */
    public void setAllowedInSort(boolean value) {
        this.allowedInSort = value;
    }

}
