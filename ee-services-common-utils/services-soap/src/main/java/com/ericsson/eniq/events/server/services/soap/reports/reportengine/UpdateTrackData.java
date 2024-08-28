package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateTrackData complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="UpdateTrackData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}TrackData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateTrackData", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", propOrder = {
        "trackData"
})
public class UpdateTrackData
        extends Action {

    @XmlElement(name = "TrackData", nillable = true)
    protected TrackData trackData;

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

}
