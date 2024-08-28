package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for Drill complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Drill">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *         &lt;element name="DrillPath" type="{http://reportengine.dsws.businessobjects.com/2005}DrillPath" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}ModifyDrillBar" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ActiveDrill" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Snapshot" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Drill", propOrder = {
        "drillPath",
        "modifyDrillBar"
})
public class Drill
        extends Action {

    @XmlElementRef(name = "DrillPath", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<DrillPath> drillPath;
    @XmlElement(name = "ModifyDrillBar", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected ModifyDrillBar modifyDrillBar;
    @XmlAttribute(name = "ActiveDrill", required = true)
    protected boolean activeDrill;
    @XmlAttribute(name = "Snapshot", required = true)
    protected boolean snapshot;

    /**
     * Gets the value of the drillPath property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link DrillPath }{@code >}
     */
    public JAXBElement<DrillPath> getDrillPath() {
        return drillPath;
    }

    /**
     * Sets the value of the drillPath property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link DrillPath }{@code >}
     */
    public void setDrillPath(JAXBElement<DrillPath> value) {
        this.drillPath = ((JAXBElement<DrillPath>) value);
    }

    /**
     * Gets the value of the modifyDrillBar property.
     *
     * @return possible object is
     *         {@link ModifyDrillBar }
     */
    public ModifyDrillBar getModifyDrillBar() {
        return modifyDrillBar;
    }

    /**
     * Sets the value of the modifyDrillBar property.
     *
     * @param value allowed object is
     *              {@link ModifyDrillBar }
     */
    public void setModifyDrillBar(ModifyDrillBar value) {
        this.modifyDrillBar = value;
    }

    /**
     * Gets the value of the activeDrill property.
     */
    public boolean isActiveDrill() {
        return activeDrill;
    }

    /**
     * Sets the value of the activeDrill property.
     */
    public void setActiveDrill(boolean value) {
        this.activeDrill = value;
    }

    /**
     * Gets the value of the snapshot property.
     */
    public boolean isSnapshot() {
        return snapshot;
    }

    /**
     * Sets the value of the snapshot property.
     */
    public void setSnapshot(boolean value) {
        this.snapshot = value;
    }

}
