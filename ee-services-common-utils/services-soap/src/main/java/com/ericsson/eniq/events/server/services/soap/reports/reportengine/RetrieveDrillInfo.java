package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for RetrieveDrillInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveDrillInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SelectDrillLOVs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LOVSearch" type="{http://reportengine.dsws.businessobjects.com/2005}LOVSearch" minOccurs="0"/>
 *         &lt;element name="LOVSort" type="{http://reportengine.dsws.businessobjects.com/2005}LOVSort" minOccurs="0"/>
 *         &lt;element name="LOVBatch" type="{http://reportengine.dsws.businessobjects.com/2005}LOVBatch" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DrillLOVRetrievalMode" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}DrillLOVRetrievalMode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveDrillInfo", propOrder = {
        "selectDrillLOVs",
        "lovSearch",
        "lovSort",
        "lovBatch"
})
public class RetrieveDrillInfo {

    @XmlElement(name = "SelectDrillLOVs", nillable = true)
    protected List<String> selectDrillLOVs;
    @XmlElementRef(name = "LOVSearch", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOVSearch> lovSearch;
    @XmlElementRef(name = "LOVSort", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOVSort> lovSort;
    @XmlElementRef(name = "LOVBatch", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOVBatch> lovBatch;
    @XmlAttribute(name = "DrillLOVRetrievalMode", required = true)
    protected DrillLOVRetrievalMode drillLOVRetrievalMode;

    /**
     * Gets the value of the selectDrillLOVs property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectDrillLOVs property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectDrillLOVs().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getSelectDrillLOVs() {
        if (selectDrillLOVs == null) {
            selectDrillLOVs = new ArrayList<String>();
        }
        return this.selectDrillLOVs;
    }

    /**
     * Gets the value of the lovSearch property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link LOVSearch }{@code >}
     */
    public JAXBElement<LOVSearch> getLOVSearch() {
        return lovSearch;
    }

    /**
     * Sets the value of the lovSearch property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link LOVSearch }{@code >}
     */
    public void setLOVSearch(JAXBElement<LOVSearch> value) {
        this.lovSearch = ((JAXBElement<LOVSearch>) value);
    }

    /**
     * Gets the value of the lovSort property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link LOVSort }{@code >}
     */
    public JAXBElement<LOVSort> getLOVSort() {
        return lovSort;
    }

    /**
     * Sets the value of the lovSort property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link LOVSort }{@code >}
     */
    public void setLOVSort(JAXBElement<LOVSort> value) {
        this.lovSort = ((JAXBElement<LOVSort>) value);
    }

    /**
     * Gets the value of the lovBatch property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link LOVBatch }{@code >}
     */
    public JAXBElement<LOVBatch> getLOVBatch() {
        return lovBatch;
    }

    /**
     * Sets the value of the lovBatch property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link LOVBatch }{@code >}
     */
    public void setLOVBatch(JAXBElement<LOVBatch> value) {
        this.lovBatch = ((JAXBElement<LOVBatch>) value);
    }

    /**
     * Gets the value of the drillLOVRetrievalMode property.
     *
     * @return possible object is
     *         {@link DrillLOVRetrievalMode }
     */
    public DrillLOVRetrievalMode getDrillLOVRetrievalMode() {
        return drillLOVRetrievalMode;
    }

    /**
     * Sets the value of the drillLOVRetrievalMode property.
     *
     * @param value allowed object is
     *              {@link DrillLOVRetrievalMode }
     */
    public void setDrillLOVRetrievalMode(DrillLOVRetrievalMode value) {
        this.drillLOVRetrievalMode = value;
    }

}
