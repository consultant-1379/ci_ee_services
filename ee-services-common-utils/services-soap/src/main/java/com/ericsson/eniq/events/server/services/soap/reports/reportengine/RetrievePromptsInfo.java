package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for RetrievePromptsInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrievePromptsInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SelectPrompts" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LOVSearch" type="{http://reportengine.dsws.businessobjects.com/2005}LOVSearch" minOccurs="0"/>
 *         &lt;element name="LOVSort" type="{http://reportengine.dsws.businessobjects.com/2005}LOVSort" minOccurs="0"/>
 *         &lt;element name="LOVBatch" type="{http://reportengine.dsws.businessobjects.com/2005}LOVBatch" minOccurs="0"/>
 *         &lt;element ref="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameterValues" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="PromptLOVRetrievalMode" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}PromptLOVRetrievalMode" />
 *       &lt;attribute name="RefreshReturnedLOVs" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ReturnLOVOnMustFillPrompts" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DisableAutoRefresh" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrievePromptsInfo", propOrder = {
        "selectPrompts",
        "lovSearch",
        "lovSort",
        "lovBatch",
        "dataSourceParameterValues"
})
public class RetrievePromptsInfo {

    @XmlElement(name = "SelectPrompts", nillable = true)
    protected List<String> selectPrompts;
    @XmlElementRef(name = "LOVSearch", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOVSearch> lovSearch;
    @XmlElementRef(name = "LOVSort", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOVSort> lovSort;
    @XmlElementRef(name = "LOVBatch", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOVBatch> lovBatch;
    @XmlElement(name = "DataSourceParameterValues", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01")
    protected List<DataSourceParameterValue> dataSourceParameterValues;
    @XmlAttribute(name = "PromptLOVRetrievalMode", required = true)
    protected PromptLOVRetrievalMode promptLOVRetrievalMode;
    @XmlAttribute(name = "RefreshReturnedLOVs", required = true)
    protected boolean refreshReturnedLOVs;
    @XmlAttribute(name = "ReturnLOVOnMustFillPrompts", required = true)
    protected boolean returnLOVOnMustFillPrompts;
    @XmlAttribute(name = "DisableAutoRefresh", required = true)
    protected boolean disableAutoRefresh;

    /**
     * Gets the value of the selectPrompts property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectPrompts property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectPrompts().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getSelectPrompts() {
        if (selectPrompts == null) {
            selectPrompts = new ArrayList<String>();
        }
        return this.selectPrompts;
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
     * Gets the value of the dataSourceParameterValues property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceParameterValues property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceParameterValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSourceParameterValue }
     */
    public List<DataSourceParameterValue> getDataSourceParameterValues() {
        if (dataSourceParameterValues == null) {
            dataSourceParameterValues = new ArrayList<DataSourceParameterValue>();
        }
        return this.dataSourceParameterValues;
    }

    /**
     * Gets the value of the promptLOVRetrievalMode property.
     *
     * @return possible object is
     *         {@link PromptLOVRetrievalMode }
     */
    public PromptLOVRetrievalMode getPromptLOVRetrievalMode() {
        return promptLOVRetrievalMode;
    }

    /**
     * Sets the value of the promptLOVRetrievalMode property.
     *
     * @param value allowed object is
     *              {@link PromptLOVRetrievalMode }
     */
    public void setPromptLOVRetrievalMode(PromptLOVRetrievalMode value) {
        this.promptLOVRetrievalMode = value;
    }

    /**
     * Gets the value of the refreshReturnedLOVs property.
     */
    public boolean isRefreshReturnedLOVs() {
        return refreshReturnedLOVs;
    }

    /**
     * Sets the value of the refreshReturnedLOVs property.
     */
    public void setRefreshReturnedLOVs(boolean value) {
        this.refreshReturnedLOVs = value;
    }

    /**
     * Gets the value of the returnLOVOnMustFillPrompts property.
     */
    public boolean isReturnLOVOnMustFillPrompts() {
        return returnLOVOnMustFillPrompts;
    }

    /**
     * Sets the value of the returnLOVOnMustFillPrompts property.
     */
    public void setReturnLOVOnMustFillPrompts(boolean value) {
        this.returnLOVOnMustFillPrompts = value;
    }

    /**
     * Gets the value of the disableAutoRefresh property.
     */
    public boolean isDisableAutoRefresh() {
        return disableAutoRefresh;
    }

    /**
     * Sets the value of the disableAutoRefresh property.
     */
    public void setDisableAutoRefresh(boolean value) {
        this.disableAutoRefresh = value;
    }

}
