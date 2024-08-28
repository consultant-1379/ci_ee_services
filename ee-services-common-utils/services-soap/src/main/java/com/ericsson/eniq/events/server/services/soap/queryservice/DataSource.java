package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for DataSource complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataSource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ParentFolderUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParentFolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="UID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute ref="{http://queryservice.dsws.businessobjects.com/2007/06/01}Editable use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSource", namespace = "http://queryservice.dsws.businessobjects.com/2005", propOrder = {
        "name",
        "description",
        "parentFolderUID",
        "parentFolderName"
})
public class DataSource {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "ParentFolderUID")
    protected String parentFolderUID;
    @XmlElement(name = "ParentFolderName")
    protected String parentFolderName;
    @XmlAttribute(name = "UID", required = true)
    protected String uid;
    @XmlAttribute(name = "Editable", namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean editable;

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
     * Gets the value of the parentFolderUID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getParentFolderUID() {
        return parentFolderUID;
    }

    /**
     * Sets the value of the parentFolderUID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setParentFolderUID(String value) {
        this.parentFolderUID = value;
    }

    /**
     * Gets the value of the parentFolderName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getParentFolderName() {
        return parentFolderName;
    }

    /**
     * Sets the value of the parentFolderName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setParentFolderName(String value) {
        this.parentFolderName = value;
    }

    /**
     * Gets the value of the uid property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUID() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUID(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the editable property.
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     */
    public void setEditable(boolean value) {
        this.editable = value;
    }

}
