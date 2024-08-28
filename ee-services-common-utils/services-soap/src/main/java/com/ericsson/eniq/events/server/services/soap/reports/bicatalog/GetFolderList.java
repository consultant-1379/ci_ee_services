package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="session" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="objectUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sort" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}SortType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceRightNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="objectPropertyNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "objectUID",
        "depth",
        "sort",
        "resourceRightNames",
        "objectPropertyNames"
})
@XmlRootElement(name = "getFolderList")
public class GetFolderList {

    protected String session;
    protected String objectUID;
    protected int depth;
    protected List<SortType> sort;
    @XmlElement(nillable = true)
    protected List<String> resourceRightNames;
    @XmlElement(nillable = true)
    protected List<String> objectPropertyNames;

    /**
     * Gets the value of the session property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSession(String value) {
        this.session = value;
    }

    /**
     * Gets the value of the objectUID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getObjectUID() {
        return objectUID;
    }

    /**
     * Sets the value of the objectUID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setObjectUID(String value) {
        this.objectUID = value;
    }

    /**
     * Gets the value of the depth property.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     */
    public void setDepth(int value) {
        this.depth = value;
    }

    /**
     * Gets the value of the sort property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sort property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSort().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link SortType }
     */
    public List<SortType> getSort() {
        if (sort == null) {
            sort = new ArrayList<SortType>();
        }
        return this.sort;
    }

    /**
     * Gets the value of the resourceRightNames property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceRightNames property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceRightNames().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getResourceRightNames() {
        if (resourceRightNames == null) {
            resourceRightNames = new ArrayList<String>();
        }
        return this.resourceRightNames;
    }

    /**
     * Gets the value of the objectPropertyNames property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectPropertyNames property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectPropertyNames().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getObjectPropertyNames() {
        if (objectPropertyNames == null) {
            objectPropertyNames = new ArrayList<String>();
        }
        return this.objectPropertyNames;
    }

}
