package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FillPrompt complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="FillPrompt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Values" type="{http://reportengine.dsws.businessobjects.com/2005}PromptValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ParentPromptValues" type="{http://reportengine.dsws.businessobjects.com/2005}FillPrompt" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FillPrompt", propOrder = {
        "values",
        "parentPromptValues"
})
public class FillPrompt {

    @XmlElement(name = "Values", nillable = true)
    protected List<PromptValue> values;
    @XmlElement(name = "ParentPromptValues", nillable = true)
    protected List<FillPrompt> parentPromptValues;
    @XmlAttribute(name = "ID", required = true)
    protected String id;

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
     * {@link PromptValue }
     */
    public List<PromptValue> getValues() {
        if (values == null) {
            values = new ArrayList<PromptValue>();
        }
        return this.values;
    }

    /**
     * Gets the value of the parentPromptValues property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentPromptValues property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentPromptValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link FillPrompt }
     */
    public List<FillPrompt> getParentPromptValues() {
        if (parentPromptValues == null) {
            parentPromptValues = new ArrayList<FillPrompt>();
        }
        return this.parentPromptValues;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setID(String value) {
        this.id = value;
    }

}
