package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LOVSort complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="LOVSort">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="SortType" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}SortType" />
 *       &lt;attribute name="SortedColumnIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LOVSort")
public class LOVSort {

    @XmlAttribute(name = "SortType", required = true)
    protected SortType sortType;
    @XmlAttribute(name = "SortedColumnIndex", required = true)
    protected int sortedColumnIndex;

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

}
