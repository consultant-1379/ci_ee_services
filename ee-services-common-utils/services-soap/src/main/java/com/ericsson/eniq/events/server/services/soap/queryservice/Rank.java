package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Rank complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Rank">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}ConditionBase">
 *       &lt;sequence>
 *         &lt;element name="FilteredObject" type="{http://query.businessobjects.com/2005}QueryObject"/>
 *         &lt;element name="BasedOnObject" type="{http://query.businessobjects.com/2005}QueryObject"/>
 *         &lt;element name="ForEachObject" type="{http://query.businessobjects.com/2005}QueryObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="QueryCondition" type="{http://query.businessobjects.com/2005}QueryCondition" minOccurs="0"/>
 *         &lt;element name="PromptSize" type="{http://query.businessobjects.com/2005}Prompt" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PromptSizeActive" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Size" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Podium" use="required" type="{http://query.businessobjects.com/2005}Podium" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rank", propOrder = {
        "filteredObject",
        "basedOnObject",
        "forEachObject",
        "queryCondition",
        "promptSize"
})
public class Rank
        extends ConditionBase {

    @XmlElement(name = "FilteredObject", required = true)
    protected QueryObject filteredObject;
    @XmlElement(name = "BasedOnObject", required = true)
    protected QueryObject basedOnObject;
    @XmlElement(name = "ForEachObject")
    protected List<QueryObject> forEachObject;
    @XmlElement(name = "QueryCondition")
    protected QueryCondition queryCondition;
    @XmlElement(name = "PromptSize")
    protected Prompt promptSize;
    @XmlAttribute(name = "PromptSizeActive", required = true)
    protected boolean promptSizeActive;
    @XmlAttribute(name = "Size")
    protected Integer size;
    @XmlAttribute(name = "Podium", required = true)
    protected Podium podium;

    /**
     * Gets the value of the filteredObject property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public QueryObject getFilteredObject() {
        return filteredObject;
    }

    /**
     * Sets the value of the filteredObject property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public void setFilteredObject(QueryObject value) {
        this.filteredObject = value;
    }

    /**
     * Gets the value of the basedOnObject property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public QueryObject getBasedOnObject() {
        return basedOnObject;
    }

    /**
     * Sets the value of the basedOnObject property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public void setBasedOnObject(QueryObject value) {
        this.basedOnObject = value;
    }

    /**
     * Gets the value of the forEachObject property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forEachObject property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForEachObject().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public List<QueryObject> getForEachObject() {
        if (forEachObject == null) {
            forEachObject = new ArrayList<QueryObject>();
        }
        return this.forEachObject;
    }

    /**
     * Gets the value of the queryCondition property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryCondition }
     */
    public QueryCondition getQueryCondition() {
        return queryCondition;
    }

    /**
     * Sets the value of the queryCondition property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryCondition }
     */
    public void setQueryCondition(QueryCondition value) {
        this.queryCondition = value;
    }

    /**
     * Gets the value of the promptSize property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.Prompt }
     */
    public Prompt getPromptSize() {
        return promptSize;
    }

    /**
     * Sets the value of the promptSize property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.Prompt }
     */
    public void setPromptSize(Prompt value) {
        this.promptSize = value;
    }

    /**
     * Gets the value of the promptSizeActive property.
     */
    public boolean isPromptSizeActive() {
        return promptSizeActive;
    }

    /**
     * Sets the value of the promptSizeActive property.
     */
    public void setPromptSizeActive(boolean value) {
        this.promptSizeActive = value;
    }

    /**
     * Gets the value of the size property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setSize(Integer value) {
        this.size = value;
    }

    /**
     * Gets the value of the podium property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.Podium }
     */
    public Podium getPodium() {
        return podium;
    }

    /**
     * Sets the value of the podium property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.Podium }
     */
    public void setPodium(Podium value) {
        this.podium = value;
    }

}
