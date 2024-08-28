package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryObjectSort complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="QueryObjectSort">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}QueryObject">
 *       &lt;attribute name="SortType" use="required" type="{http://datasource.businessobjects.com/2005}SortType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryObjectSort")
public class QueryObjectSort
        extends QueryObject {

    @XmlAttribute(name = "SortType", required = true)
    protected SortType sortType;

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

}
