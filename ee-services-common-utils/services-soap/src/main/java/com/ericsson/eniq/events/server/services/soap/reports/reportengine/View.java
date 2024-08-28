package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for View complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="View">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ViewSupport" type="{http://reportengine.dsws.businessobjects.com/2005}ViewSupport" minOccurs="0"/>
 *         &lt;element name="ReportPart" type="{http://reportengine.dsws.businessobjects.com/2005}ReportPart" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ChunkSize" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="LastChunk" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ContentLength" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "View", propOrder = {
        "viewSupport",
        "reportPart"
})
@XmlSeeAlso({
        BinaryView.class,
        CharacterView.class,
        XMLView.class
})
public class View {

    @XmlElementRef(name = "ViewSupport", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<ViewSupport> viewSupport;
    @XmlElement(name = "ReportPart", nillable = true)
    protected List<ReportPart> reportPart;
    @XmlAttribute(name = "ChunkSize")
    protected Integer chunkSize;
    @XmlAttribute(name = "LastChunk")
    protected Boolean lastChunk;
    @XmlAttribute(name = "ContentLength")
    protected Integer contentLength;

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
     * Gets the value of the reportPart property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportPart property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportPart().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportPart }
     */
    public List<ReportPart> getReportPart() {
        if (reportPart == null) {
            reportPart = new ArrayList<ReportPart>();
        }
        return this.reportPart;
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

    /**
     * Gets the value of the lastChunk property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isLastChunk() {
        return lastChunk;
    }

    /**
     * Sets the value of the lastChunk property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setLastChunk(Boolean value) {
        this.lastChunk = value;
    }

    /**
     * Gets the value of the contentLength property.
     *
     * @return possible object is
     *         {@link Integer }
     */
    public Integer getContentLength() {
        return contentLength;
    }

    /**
     * Sets the value of the contentLength property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setContentLength(Integer value) {
        this.contentLength = value;
    }

}
