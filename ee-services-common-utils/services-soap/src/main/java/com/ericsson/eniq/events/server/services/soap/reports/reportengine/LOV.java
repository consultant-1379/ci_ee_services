package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for LOV complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="LOV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Values" type="{http://reportengine.dsws.businessobjects.com/2005}Value" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BatchName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RowIndexed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SortType" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}SortType" />
 *       &lt;attribute name="SortedColumnIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Sorted" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SearchActivated" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CurrentBatchIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}PartialResult use="required""/>
 *       &lt;attribute ref="{http://reportengine.dsws.businessobjects.com/2008/06/01}RefreshDate use="required""/>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LOV", propOrder = {
        "header",
        "values",
        "batchName",
        "any"
})
public class LOV {

    @XmlElement(name = "Header", nillable = true)
    protected List<String> header;
    @XmlElement(name = "Values", nillable = true)
    protected List<Value> values;
    @XmlElement(name = "BatchName", nillable = true)
    protected List<String> batchName;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "RowIndexed", required = true)
    protected boolean rowIndexed;
    @XmlAttribute(name = "SortType", required = true)
    protected SortType sortType;
    @XmlAttribute(name = "SortedColumnIndex", required = true)
    protected int sortedColumnIndex;
    @XmlAttribute(name = "Sorted", required = true)
    protected boolean sorted;
    @XmlAttribute(name = "SearchActivated", required = true)
    protected boolean searchActivated;
    @XmlAttribute(name = "CurrentBatchIndex", required = true)
    protected int currentBatchIndex;
    @XmlAttribute(name = "PartialResult", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean partialResult;
    @XmlAttribute(name = "RefreshDate", namespace = "http://reportengine.dsws.businessobjects.com/2008/06/01", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar refreshDate;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the header property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the header property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeader().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getHeader() {
        if (header == null) {
            header = new ArrayList<String>();
        }
        return this.header;
    }

    /**
     * Gets the value of the values property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the values property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Value }
     */
    public List<Value> getValues() {
        if (values == null) {
            values = new ArrayList<Value>();
        }
        return this.values;
    }

    /**
     * Gets the value of the batchName property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchName property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchName().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getBatchName() {
        if (batchName == null) {
            batchName = new ArrayList<String>();
        }
        return this.batchName;
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
     * {@link Object }
     * {@link Element }
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the rowIndexed property.
     */
    public boolean isRowIndexed() {
        return rowIndexed;
    }

    /**
     * Sets the value of the rowIndexed property.
     */
    public void setRowIndexed(boolean value) {
        this.rowIndexed = value;
    }

    /**
     * Gets the value of the sortType property.
     *
     * @return possible object is
     *         {@link SortType }
     */
    public SortType getSortType() {
        return sortType;
    }

    /**
     * Sets the value of the sortType property.
     *
     * @param value allowed object is
     *              {@link SortType }
     */
    public void setSortType(SortType value) {
        this.sortType = value;
    }

    /**
     * Gets the value of the sortedColumnIndex property.
     */
    public int getSortedColumnIndex() {
        return sortedColumnIndex;
    }

    /**
     * Sets the value of the sortedColumnIndex property.
     */
    public void setSortedColumnIndex(int value) {
        this.sortedColumnIndex = value;
    }

    /**
     * Gets the value of the sorted property.
     */
    public boolean isSorted() {
        return sorted;
    }

    /**
     * Sets the value of the sorted property.
     */
    public void setSorted(boolean value) {
        this.sorted = value;
    }

    /**
     * Gets the value of the searchActivated property.
     */
    public boolean isSearchActivated() {
        return searchActivated;
    }

    /**
     * Sets the value of the searchActivated property.
     */
    public void setSearchActivated(boolean value) {
        this.searchActivated = value;
    }

    /**
     * Gets the value of the currentBatchIndex property.
     */
    public int getCurrentBatchIndex() {
        return currentBatchIndex;
    }

    /**
     * Sets the value of the currentBatchIndex property.
     */
    public void setCurrentBatchIndex(int value) {
        this.currentBatchIndex = value;
    }

    /**
     * Gets the value of the partialResult property.
     */
    public boolean isPartialResult() {
        return partialResult;
    }

    /**
     * Sets the value of the partialResult property.
     */
    public void setPartialResult(boolean value) {
        this.partialResult = value;
    }

    /**
     * Gets the value of the refreshDate property.
     *
     * @return possible object is
     *         {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getRefreshDate() {
        return refreshDate;
    }

    /**
     * Sets the value of the refreshDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setRefreshDate(XMLGregorianCalendar value) {
        this.refreshDate = value;
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
