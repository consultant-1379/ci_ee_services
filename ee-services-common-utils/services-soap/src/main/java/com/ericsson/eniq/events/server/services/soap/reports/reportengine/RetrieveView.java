package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for RetrieveView complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveView">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ViewSupport" type="{http://reportengine.dsws.businessobjects.com/2005}ViewSupport" minOccurs="0"/>
 *         &lt;element name="CallbackOption" type="{http://reportengine.dsws.businessobjects.com/2005}CallbackOption" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Media" type="{http://dsws.businessobjects.com/2007/06/01}NameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReportPartReference" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="UserAgent" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ChunkSize" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveView", propOrder = {
        "viewSupport",
        "callbackOption",
        "media",
        "reportPartReference"
})
@XmlSeeAlso({
        RetrieveXMLView.class,
        RetrieveBinaryView.class,
        RetrieveCharacterView.class
})
public class RetrieveView {

    @XmlElementRef(name = "ViewSupport", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<ViewSupport> viewSupport;
    @XmlElement(name = "CallbackOption", nillable = true)
    protected List<CallbackOption> callbackOption;
    @XmlElement(name = "Media", nillable = true)
    protected List<NameValuePair> media;
    @XmlElement(name = "ReportPartReference", nillable = true)
    protected List<String> reportPartReference;
    @XmlAttribute(name = "UserAgent", required = true)
    protected String userAgent;
    @XmlAttribute(name = "ChunkSize")
    protected Integer chunkSize;

    /**
     * Gets the value of the viewSupport property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link ViewSupport }{@code >}
     */
    public JAXBElement<ViewSupport> getViewSupport() {
        return viewSupport;
    }

    /**
     * Sets the value of the viewSupport property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ViewSupport }{@code >}
     */
    public void setViewSupport(JAXBElement<ViewSupport> value) {
        this.viewSupport = ((JAXBElement<ViewSupport>) value);
    }

    /**
     * Gets the value of the callbackOption property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the callbackOption property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCallbackOption().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link CallbackOption }
     */
    public List<CallbackOption> getCallbackOption() {
        if (callbackOption == null) {
            callbackOption = new ArrayList<CallbackOption>();
        }
        return this.callbackOption;
    }

    /**
     * Gets the value of the media property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the media property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedia().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValuePair }
     */
    public List<NameValuePair> getMedia() {
        if (media == null) {
            media = new ArrayList<NameValuePair>();
        }
        return this.media;
    }

    /**
     * Gets the value of the reportPartReference property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportPartReference property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportPartReference().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getReportPartReference() {
        if (reportPartReference == null) {
            reportPartReference = new ArrayList<String>();
        }
        return this.reportPartReference;
    }

    /**
     * Gets the value of the userAgent property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the chunkSize property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getChunkSize() {
        return chunkSize;
    }

    /**
     * Sets the value of the chunkSize property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setChunkSize(Integer value) {
        this.chunkSize = value;
    }

}
