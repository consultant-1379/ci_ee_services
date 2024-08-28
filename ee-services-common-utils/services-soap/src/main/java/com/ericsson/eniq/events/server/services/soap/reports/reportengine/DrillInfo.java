package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for DrillInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DrillInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrillHierarchies" type="{http://reportengine.dsws.businessobjects.com/2005}DrillHierarchy" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FreeDrillDimensions" type="{http://reportengine.dsws.businessobjects.com/2005}DrillDimension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FreeDrillDetails" type="{http://reportengine.dsws.businessobjects.com/2005}DrillDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}DrillBar" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ActiveBlockID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="WillGoOutOfScope" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillInfo", propOrder = {
        "drillHierarchies",
        "freeDrillDimensions",
        "freeDrillDetails",
        "drillBar"
})
public class DrillInfo {

    @XmlElement(name = "DrillHierarchies", nillable = true)
    protected List<DrillHierarchy> drillHierarchies;
    @XmlElement(name = "FreeDrillDimensions", nillable = true)
    protected List<DrillDimension> freeDrillDimensions;
    @XmlElement(name = "FreeDrillDetails", nillable = true)
    protected List<DrillDetail> freeDrillDetails;
    @XmlElement(name = "DrillBar", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected DrillBar drillBar;
    @XmlAttribute(name = "ActiveBlockID", required = true)
    protected String activeBlockID;
    @XmlAttribute(name = "WillGoOutOfScope", required = true)
    protected boolean willGoOutOfScope;

    /**
     * Gets the value of the drillHierarchies property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drillHierarchies property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrillHierarchies().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DrillHierarchy }
     */
    public List<DrillHierarchy> getDrillHierarchies() {
        if (drillHierarchies == null) {
            drillHierarchies = new ArrayList<DrillHierarchy>();
        }
        return this.drillHierarchies;
    }

    /**
     * Gets the value of the freeDrillDimensions property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the freeDrillDimensions property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFreeDrillDimensions().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DrillDimension }
     */
    public List<DrillDimension> getFreeDrillDimensions() {
        if (freeDrillDimensions == null) {
            freeDrillDimensions = new ArrayList<DrillDimension>();
        }
        return this.freeDrillDimensions;
    }

    /**
     * Gets the value of the freeDrillDetails property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the freeDrillDetails property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFreeDrillDetails().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DrillDetail }
     */
    public List<DrillDetail> getFreeDrillDetails() {
        if (freeDrillDetails == null) {
            freeDrillDetails = new ArrayList<DrillDetail>();
        }
        return this.freeDrillDetails;
    }

    /**
     * Gets the value of the drillBar property.
     *
     * @return possible object is
     *         {@link DrillBar }
     */
    public DrillBar getDrillBar() {
        return drillBar;
    }

    /**
     * Sets the value of the drillBar property.
     *
     * @param value allowed object is
     *              {@link DrillBar }
     */
    public void setDrillBar(DrillBar value) {
        this.drillBar = value;
    }

    /**
     * Gets the value of the activeBlockID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getActiveBlockID() {
        return activeBlockID;
    }

    /**
     * Sets the value of the activeBlockID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActiveBlockID(String value) {
        this.activeBlockID = value;
    }

    /**
     * Gets the value of the willGoOutOfScope property.
     */
    public boolean isWillGoOutOfScope() {
        return willGoOutOfScope;
    }

    /**
     * Sets the value of the willGoOutOfScope property.
     */
    public void setWillGoOutOfScope(boolean value) {
        this.willGoOutOfScope = value;
    }

}
