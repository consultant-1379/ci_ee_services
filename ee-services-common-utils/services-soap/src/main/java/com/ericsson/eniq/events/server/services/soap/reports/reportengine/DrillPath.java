package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for DrillPath complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DrillPath">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FromObjectID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ToObjectID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HierarchyID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Filter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="BlockID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DrillOperationType" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}DrillOperationType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillPath", propOrder = {
        "fromObjectID",
        "toObjectID",
        "hierarchyID",
        "filter"
})
public class DrillPath {

    @XmlElement(name = "FromObjectID", nillable = true)
    protected List<String> fromObjectID;
    @XmlElement(name = "ToObjectID", nillable = true)
    protected List<String> toObjectID;
    @XmlElement(name = "HierarchyID", nillable = true)
    protected List<String> hierarchyID;
    @XmlElement(name = "Filter", nillable = true)
    protected List<String> filter;
    @XmlAttribute(name = "BlockID", required = true)
    protected String blockID;
    @XmlAttribute(name = "DrillOperationType", required = true)
    protected DrillOperationType drillOperationType;

    /**
     * Gets the value of the fromObjectID property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fromObjectID property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFromObjectID().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getFromObjectID() {
        if (fromObjectID == null) {
            fromObjectID = new ArrayList<String>();
        }
        return this.fromObjectID;
    }

    /**
     * Gets the value of the toObjectID property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the toObjectID property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getToObjectID().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getToObjectID() {
        if (toObjectID == null) {
            toObjectID = new ArrayList<String>();
        }
        return this.toObjectID;
    }

    /**
     * Gets the value of the hierarchyID property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hierarchyID property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHierarchyID().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getHierarchyID() {
        if (hierarchyID == null) {
            hierarchyID = new ArrayList<String>();
        }
        return this.hierarchyID;
    }

    /**
     * Gets the value of the filter property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filter property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilter().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getFilter() {
        if (filter == null) {
            filter = new ArrayList<String>();
        }
        return this.filter;
    }

    /**
     * Gets the value of the blockID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getBlockID() {
        return blockID;
    }

    /**
     * Sets the value of the blockID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBlockID(String value) {
        this.blockID = value;
    }

    /**
     * Gets the value of the drillOperationType property.
     *
     * @return possible object is
     *         {@link DrillOperationType }
     */
    public DrillOperationType getDrillOperationType() {
        return drillOperationType;
    }

    /**
     * Sets the value of the drillOperationType property.
     *
     * @param value allowed object is
     *              {@link DrillOperationType }
     */
    public void setDrillOperationType(DrillOperationType value) {
        this.drillOperationType = value;
    }

}
