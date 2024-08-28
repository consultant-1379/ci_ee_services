package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for DocumentInformation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DocumentInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CurrentReportState" type="{http://reportengine.dsws.businessobjects.com/2005}ReportState" minOccurs="0"/>
 *         &lt;element name="Reports" type="{http://reportengine.dsws.businessobjects.com/2005}Report" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="View" type="{http://reportengine.dsws.businessobjects.com/2005}View" minOccurs="0"/>
 *         &lt;element name="DrillInfo" type="{http://reportengine.dsws.businessobjects.com/2005}DrillInfo" minOccurs="0"/>
 *         &lt;element name="DataProviderInfo" type="{http://reportengine.dsws.businessobjects.com/2005}DataProviderInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NavigationMap" type="{http://reportengine.dsws.businessobjects.com/2005}NavigationNode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ViewSupports" type="{http://reportengine.dsws.businessobjects.com/2005}ViewSupport" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PromptInfo" type="{http://reportengine.dsws.businessobjects.com/2005}PromptInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="QueryContexts" type="{http://reportengine.dsws.businessobjects.com/2005}QueryContext" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DBLogonInfos" type="{http://reportengine.dsws.businessobjects.com/2005}DBLogonInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}TrackData" minOccurs="0"/>
 *         &lt;element ref="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameterValues" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DocumentReference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FileType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MustFillPassword" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MustFillPrompts" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MustFillQueryContexts" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MustFillDBLogons" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RefreshOnOpen" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CreationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Refreshable" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ViewChunkable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Drillable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}DataSourceParameterValuesRequired use="required""/>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentInformation", propOrder = {
        "currentReportState",
        "reports",
        "view",
        "drillInfo",
        "dataProviderInfo",
        "navigationMap",
        "viewSupports",
        "promptInfo",
        "queryContexts",
        "dbLogonInfos",
        "trackData",
        "dataSourceParameterValues"
})
public class DocumentInformation {

    @XmlElementRef(name = "CurrentReportState", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<ReportState> currentReportState;
    @XmlElement(name = "Reports", nillable = true)
    protected List<Report> reports;
    @XmlElementRef(name = "View", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<View> view;
    @XmlElementRef(name = "DrillInfo", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<DrillInfo> drillInfo;
    @XmlElement(name = "DataProviderInfo", nillable = true)
    protected List<DataProviderInfo> dataProviderInfo;
    @XmlElement(name = "NavigationMap", nillable = true)
    protected List<NavigationNode> navigationMap;
    @XmlElement(name = "ViewSupports", nillable = true)
    protected List<ViewSupport> viewSupports;
    @XmlElement(name = "PromptInfo", nillable = true)
    protected List<PromptInfo> promptInfo;
    @XmlElement(name = "QueryContexts", nillable = true)
    protected List<QueryContext> queryContexts;
    @XmlElement(name = "DBLogonInfos", nillable = true)
    protected List<DBLogonInfo> dbLogonInfos;
    @XmlElement(name = "TrackData", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected TrackData trackData;
    @XmlElement(name = "DataSourceParameterValues", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01")
    protected List<DataSourceParameterValue> dataSourceParameterValues;
    @XmlAttribute(name = "DocumentReference")
    protected String documentReference;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "FileType")
    protected String fileType;
    @XmlAttribute(name = "MimeType")
    protected String mimeType;
    @XmlAttribute(name = "MustFillPassword", required = true)
    protected boolean mustFillPassword;
    @XmlAttribute(name = "MustFillPrompts", required = true)
    protected boolean mustFillPrompts;
    @XmlAttribute(name = "MustFillQueryContexts", required = true)
    protected boolean mustFillQueryContexts;
    @XmlAttribute(name = "MustFillDBLogons", required = true)
    protected boolean mustFillDBLogons;
    @XmlAttribute(name = "RefreshOnOpen")
    protected Boolean refreshOnOpen;
    @XmlAttribute(name = "Author", required = true)
    protected String author;
    @XmlAttribute(name = "CreationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlAttribute(name = "Refreshable", required = true)
    protected boolean refreshable;
    @XmlAttribute(name = "ViewChunkable")
    protected Boolean viewChunkable;
    @XmlAttribute(name = "Drillable")
    protected Boolean drillable;
    @XmlAttribute(name = "DataSourceParameterValuesRequired", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean dataSourceParameterValuesRequired;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the currentReportState property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link ReportState }{@code >}
     */
    public JAXBElement<ReportState> getCurrentReportState() {
        return currentReportState;
    }

    /**
     * Sets the value of the currentReportState property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ReportState }{@code >}
     */
    public void setCurrentReportState(JAXBElement<ReportState> value) {
        this.currentReportState = ((JAXBElement<ReportState>) value);
    }

    /**
     * Gets the value of the reports property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reports property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReports().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Report }
     */
    public List<Report> getReports() {
        if (reports == null) {
            reports = new ArrayList<Report>();
        }
        return this.reports;
    }

    /**
     * Gets the value of the view property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link View }{@code >}
     */
    public JAXBElement<View> getView() {
        return view;
    }

    /**
     * Sets the value of the view property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link View }{@code >}
     */
    public void setView(JAXBElement<View> value) {
        this.view = ((JAXBElement<View>) value);
    }

    /**
     * Gets the value of the drillInfo property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link DrillInfo }{@code >}
     */
    public JAXBElement<DrillInfo> getDrillInfo() {
        return drillInfo;
    }

    /**
     * Sets the value of the drillInfo property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link DrillInfo }{@code >}
     */
    public void setDrillInfo(JAXBElement<DrillInfo> value) {
        this.drillInfo = ((JAXBElement<DrillInfo>) value);
    }

    /**
     * Gets the value of the dataProviderInfo property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataProviderInfo property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataProviderInfo().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataProviderInfo }
     */
    public List<DataProviderInfo> getDataProviderInfo() {
        if (dataProviderInfo == null) {
            dataProviderInfo = new ArrayList<DataProviderInfo>();
        }
        return this.dataProviderInfo;
    }

    /**
     * Gets the value of the navigationMap property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the navigationMap property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNavigationMap().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link NavigationNode }
     */
    public List<NavigationNode> getNavigationMap() {
        if (navigationMap == null) {
            navigationMap = new ArrayList<NavigationNode>();
        }
        return this.navigationMap;
    }

    /**
     * Gets the value of the viewSupports property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewSupports property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewSupports().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ViewSupport }
     */
    public List<ViewSupport> getViewSupports() {
        if (viewSupports == null) {
            viewSupports = new ArrayList<ViewSupport>();
        }
        return this.viewSupports;
    }

    /**
     * Gets the value of the promptInfo property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promptInfo property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromptInfo().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PromptInfo }
     */
    public List<PromptInfo> getPromptInfo() {
        if (promptInfo == null) {
            promptInfo = new ArrayList<PromptInfo>();
        }
        return this.promptInfo;
    }

    /**
     * Gets the value of the queryContexts property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryContexts property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryContexts().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryContext }
     */
    public List<QueryContext> getQueryContexts() {
        if (queryContexts == null) {
            queryContexts = new ArrayList<QueryContext>();
        }
        return this.queryContexts;
    }

    /**
     * Gets the value of the dbLogonInfos property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dbLogonInfos property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDBLogonInfos().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DBLogonInfo }
     */
    public List<DBLogonInfo> getDBLogonInfos() {
        if (dbLogonInfos == null) {
            dbLogonInfos = new ArrayList<DBLogonInfo>();
        }
        return this.dbLogonInfos;
    }

    /**
     * Gets the value of the trackData property.
     *
     * @return possible object is
     *         {@link TrackData }
     */
    public TrackData getTrackData() {
        return trackData;
    }

    /**
     * Sets the value of the trackData property.
     *
     * @param value allowed object is
     *              {@link TrackData }
     */
    public void setTrackData(TrackData value) {
        this.trackData = value;
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
     * Gets the value of the documentReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDocumentReference() {
        return documentReference;
    }

    /**
     * Sets the value of the documentReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentReference(String value) {
        this.documentReference = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the fileType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the value of the fileType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFileType(String value) {
        this.fileType = value;
    }

    /**
     * Gets the value of the mimeType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the mustFillPassword property.
     */
    public boolean isMustFillPassword() {
        return mustFillPassword;
    }

    /**
     * Sets the value of the mustFillPassword property.
     */
    public void setMustFillPassword(boolean value) {
        this.mustFillPassword = value;
    }

    /**
     * Gets the value of the mustFillPrompts property.
     */
    public boolean isMustFillPrompts() {
        return mustFillPrompts;
    }

    /**
     * Sets the value of the mustFillPrompts property.
     */
    public void setMustFillPrompts(boolean value) {
        this.mustFillPrompts = value;
    }

    /**
     * Gets the value of the mustFillQueryContexts property.
     */
    public boolean isMustFillQueryContexts() {
        return mustFillQueryContexts;
    }

    /**
     * Sets the value of the mustFillQueryContexts property.
     */
    public void setMustFillQueryContexts(boolean value) {
        this.mustFillQueryContexts = value;
    }

    /**
     * Gets the value of the mustFillDBLogons property.
     */
    public boolean isMustFillDBLogons() {
        return mustFillDBLogons;
    }

    /**
     * Sets the value of the mustFillDBLogons property.
     */
    public void setMustFillDBLogons(boolean value) {
        this.mustFillDBLogons = value;
    }

    /**
     * Gets the value of the refreshOnOpen property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isRefreshOnOpen() {
        return refreshOnOpen;
    }

    /**
     * Sets the value of the refreshOnOpen property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setRefreshOnOpen(Boolean value) {
        this.refreshOnOpen = value;
    }

    /**
     * Gets the value of the author property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the creationDate property.
     *
     * @return possible object is
     *         {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the refreshable property.
     */
    public boolean isRefreshable() {
        return refreshable;
    }

    /**
     * Sets the value of the refreshable property.
     */
    public void setRefreshable(boolean value) {
        this.refreshable = value;
    }

    /**
     * Gets the value of the viewChunkable property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isViewChunkable() {
        return viewChunkable;
    }

    /**
     * Sets the value of the viewChunkable property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setViewChunkable(Boolean value) {
        this.viewChunkable = value;
    }

    /**
     * Gets the value of the drillable property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isDrillable() {
        return drillable;
    }

    /**
     * Sets the value of the drillable property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setDrillable(Boolean value) {
        this.drillable = value;
    }

    /**
     * Gets the value of the dataSourceParameterValuesRequired property.
     */
    public boolean isDataSourceParameterValuesRequired() {
        return dataSourceParameterValuesRequired;
    }

    /**
     * Sets the value of the dataSourceParameterValuesRequired property.
     */
    public void setDataSourceParameterValuesRequired(boolean value) {
        this.dataSourceParameterValuesRequired = value;
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
