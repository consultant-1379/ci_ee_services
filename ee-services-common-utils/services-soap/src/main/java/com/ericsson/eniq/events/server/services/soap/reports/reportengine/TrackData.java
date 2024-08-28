package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Java class for TrackData complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="TrackData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Active" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ShowChanges" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CurrentAsReference" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="TrackDataViewMode" use="required" type="{http://reportengine.dsws.businessobjects.com/2007/06/01}TrackDataViewMode" />
 *       &lt;attribute name="ReferenceDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackData", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
public class TrackData {

    @XmlAttribute(name = "Active", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean active;
    @XmlAttribute(name = "ShowChanges", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean showChanges;
    @XmlAttribute(name = "CurrentAsReference", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean currentAsReference;
    @XmlAttribute(name = "TrackDataViewMode", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected TrackDataViewMode trackDataViewMode;
    @XmlAttribute(name = "ReferenceDate", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar referenceDate;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the active property.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the showChanges property.
     */
    public boolean isShowChanges() {
        return showChanges;
    }

    /**
     * Sets the value of the showChanges property.
     */
    public void setShowChanges(boolean value) {
        this.showChanges = value;
    }

    /**
     * Gets the value of the currentAsReference property.
     */
    public boolean isCurrentAsReference() {
        return currentAsReference;
    }

    /**
     * Sets the value of the currentAsReference property.
     */
    public void setCurrentAsReference(boolean value) {
        this.currentAsReference = value;
    }

    /**
     * Gets the value of the trackDataViewMode property.
     *
     * @return possible object is
     *         {@link TrackDataViewMode }
     */
    public TrackDataViewMode getTrackDataViewMode() {
        return trackDataViewMode;
    }

    /**
     * Sets the value of the trackDataViewMode property.
     *
     * @param value allowed object is
     *              {@link TrackDataViewMode }
     */
    public void setTrackDataViewMode(TrackDataViewMode value) {
        this.trackDataViewMode = value;
    }

    /**
     * Gets the value of the referenceDate property.
     *
     * @return possible object is
     *         {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getReferenceDate() {
        return referenceDate;
    }

    /**
     * Sets the value of the referenceDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setReferenceDate(XMLGregorianCalendar value) {
        this.referenceDate = value;
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
