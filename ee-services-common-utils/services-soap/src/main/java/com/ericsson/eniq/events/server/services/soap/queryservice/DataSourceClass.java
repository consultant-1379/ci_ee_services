package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for DataSourceClass complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataSourceClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataSourceClasses" type="{http://datasource.businessobjects.com/2005}DataSourceClass" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DataSourceObject" type="{http://datasource.businessobjects.com/2005}DataSourceObject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceClass", namespace = "http://datasource.businessobjects.com/2005", propOrder = {
        "dataSourceClasses",
        "dataSourceObject"
})
public class DataSourceClass {

    @XmlElement(name = "DataSourceClasses")
    protected List<DataSourceClass> dataSourceClasses;
    @XmlElement(name = "DataSourceObject")
    protected List<DataSourceObject> dataSourceObject;
    @XmlAttribute(name = "Key", required = true)
    protected String key;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Description", required = true)
    protected String description;

    /**
     * Gets the value of the dataSourceClasses property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceClasses property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceClasses().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceClass }
     */
    public List<DataSourceClass> getDataSourceClasses() {
        if (dataSourceClasses == null) {
            dataSourceClasses = new ArrayList<DataSourceClass>();
        }
        return this.dataSourceClasses;
    }

    /**
     * Gets the value of the dataSourceObject property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceObject property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceObject().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSourceObject }
     */
    public List<DataSourceObject> getDataSourceObject() {
        if (dataSourceObject == null) {
            dataSourceObject = new ArrayList<DataSourceObject>();
        }
        return this.dataSourceObject;
    }

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

}
