package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetrieveTrackData complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveTrackData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TrackDataViewMode" use="required" type="{http://reportengine.dsws.businessobjects.com/2007/06/01}TrackDataViewMode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveTrackData", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
public class RetrieveTrackData {

    @XmlAttribute(name = "TrackDataViewMode", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected TrackDataViewMode trackDataViewMode;

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

}
