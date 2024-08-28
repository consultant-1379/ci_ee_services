package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for BICatalogObject complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="BICatalogObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Keywords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResourceRights" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}ResourceRight" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ObjectProperties" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}ObjectProperty" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="WSResourceList" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}WSResource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Children" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}BICatalogObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ObjectType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CreationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BICatalogObject", propOrder = {
        "description",
        "keywords",
        "resourceRights",
        "objectProperties",
        "wsResourceList",
        "children",
        "any"
})
@XmlSeeAlso({
        Category.class,
        Document.class,
        Folder.class
})
public class BICatalogObject {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Keywords")
    protected String keywords;
    @XmlElement(name = "ResourceRights")
    protected List<ResourceRight> resourceRights;
    @XmlElement(name = "ObjectProperties")
    protected List<ObjectProperty> objectProperties;
    @XmlElement(name = "WSResourceList")
    protected List<WSResource> wsResourceList;
    @XmlElement(name = "Children")
    protected List<BICatalogObject> children;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "ObjectType", required = true)
    protected String objectType;
    @XmlAttribute(name = "UID", required = true)
    protected String uid;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Author", required = true)
    protected String author;
    @XmlAttribute(name = "CreationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * Gets the value of the keywords property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKeywords(String value) {
        this.keywords = value;
    }

    /**
     * Gets the value of the resourceRights property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceRights property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceRights().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceRight }
     */
    public List<ResourceRight> getResourceRights() {
        if (resourceRights == null) {
            resourceRights = new ArrayList<ResourceRight>();
        }
        return this.resourceRights;
    }

    /**
     * Gets the value of the objectProperties property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectProperties property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectProperties().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectProperty }
     */
    public List<ObjectProperty> getObjectProperties() {
        if (objectProperties == null) {
            objectProperties = new ArrayList<ObjectProperty>();
        }
        return this.objectProperties;
    }

    /**
     * Gets the value of the wsResourceList property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsResourceList property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSResourceList().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link WSResource }
     */
    public List<WSResource> getWSResourceList() {
        if (wsResourceList == null) {
            wsResourceList = new ArrayList<WSResource>();
        }
        return this.wsResourceList;
    }

    /**
     * Gets the value of the children property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the children property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildren().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link BICatalogObject }
     */
    public List<BICatalogObject> getChildren() {
        if (children == null) {
            children = new ArrayList<BICatalogObject>();
        }
        return this.children;
    }

    /**
     * Gets the value of the any property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
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
     * Gets the value of the author property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the creationDate property.
     *
     * @return possible object is
     *         {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * <p/>
     * <p/>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     * <p/>
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     * @return always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
