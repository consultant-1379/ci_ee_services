package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for QueryCondition complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="QueryCondition">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}ConditionBase">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://query.businessobjects.com/2005}ConditionBase" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="QueryConditionOperator" type="{http://query.businessobjects.com/2005}QueryConditionOperator" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryCondition", propOrder = {
        "item"
})
public class QueryCondition
        extends ConditionBase {

    @XmlElement(name = "Item", required = true)
    protected List<ConditionBase> item;
    @XmlAttribute(name = "QueryConditionOperator")
    protected QueryConditionOperator queryConditionOperator;

    /**
     * Gets the value of the item property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.ConditionBase }
     */
    public List<ConditionBase> getItem() {
        if (item == null) {
            item = new ArrayList<ConditionBase>();
        }
        return this.item;
    }

    /**
     * Gets the value of the queryConditionOperator property.
     *
     * @return possible object is
     *         {@link QueryConditionOperator }
     */
    public QueryConditionOperator getQueryConditionOperator() {
        return queryConditionOperator;
    }

    /**
     * Sets the value of the queryConditionOperator property.
     *
     * @param value allowed object is
     *              {@link QueryConditionOperator }
     */
    public void setQueryConditionOperator(QueryConditionOperator value) {
        this.queryConditionOperator = value;
    }

}
