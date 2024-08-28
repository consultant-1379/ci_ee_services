package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Filter complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Filter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}ConditionBase">
 *       &lt;sequence>
 *         &lt;element name="FilteredObject" type="{http://query.businessobjects.com/2005}QueryObject"/>
 *         &lt;element name="Operand" type="{http://query.businessobjects.com/2005}Operand" maxOccurs="2" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FilterOperator" use="required" type="{http://query.businessobjects.com/2005}FilterOperator" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Filter", propOrder = {
        "filteredObject",
        "operand"
})
public class Filter
        extends ConditionBase {

    @XmlElement(name = "FilteredObject", required = true)
    protected QueryObject filteredObject;
    @XmlElement(name = "Operand")
    protected List<Operand> operand;
    @XmlAttribute(name = "FilterOperator", required = true)
    protected FilterOperator filterOperator;

    /**
     * Gets the value of the filteredObject property.
     *
     * @return possible object is
     *         {@link QueryObject }
     */
    public QueryObject getFilteredObject() {
        return filteredObject;
    }

    /**
     * Sets the value of the filteredObject property.
     *
     * @param value allowed object is
     *              {@link QueryObject }
     */
    public void setFilteredObject(QueryObject value) {
        this.filteredObject = value;
    }

    /**
     * Gets the value of the operand property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operand property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperand().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Operand }
     */
    public List<Operand> getOperand() {
        if (operand == null) {
            operand = new ArrayList<Operand>();
        }
        return this.operand;
    }

    /**
     * Gets the value of the filterOperator property.
     *
     * @return possible object is
     *         {@link FilterOperator }
     */
    public FilterOperator getFilterOperator() {
        return filterOperator;
    }

    /**
     * Sets the value of the filterOperator property.
     *
     * @param value allowed object is
     *              {@link FilterOperator }
     */
    public void setFilterOperator(FilterOperator value) {
        this.filterOperator = value;
    }

}
