package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bicatalog.dsws.businessobjects.com/2007/06/01}BICatalogObject">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="MimeType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FileType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Instance" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="InstanceStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HasDocumentInstance" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="LastSuccessfullInstance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document")
public class Document
        extends BICatalogObject {

    @XmlAttribute(name = "MimeType", required = true)
    protected String mimeType;
    @XmlAttribute(name = "FileType", required = true)
    protected String fileType;
    @XmlAttribute(name = "Instance", required = true)
    protected boolean instance;
    @XmlAttribute(name = "InstanceStatus")
    protected String instanceStatus;
    @XmlAttribute(name = "HasDocumentInstance", required = true)
    protected boolean hasDocumentInstance;
    @XmlAttribute(name = "LastSuccessfullInstance")
    protected String lastSuccessfullInstance;

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
     * Gets the value of the instance property.
     */
    public boolean isInstance() {
        return instance;
    }

    /**
     * Sets the value of the instance property.
     */
    public void setInstance(boolean value) {
        this.instance = value;
    }

    /**
     * Gets the value of the instanceStatus property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getInstanceStatus() {
        return instanceStatus;
    }

    /**
     * Sets the value of the instanceStatus property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInstanceStatus(String value) {
        this.instanceStatus = value;
    }

    /**
     * Gets the value of the hasDocumentInstance property.
     */
    public boolean isHasDocumentInstance() {
        return hasDocumentInstance;
    }

    /**
     * Sets the value of the hasDocumentInstance property.
     */
    public void setHasDocumentInstance(boolean value) {
        this.hasDocumentInstance = value;
    }

    /**
     * Gets the value of the lastSuccessfullInstance property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLastSuccessfullInstance() {
        return lastSuccessfullInstance;
    }

    /**
     * Sets the value of the lastSuccessfullInstance property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLastSuccessfullInstance(String value) {
        this.lastSuccessfullInstance = value;
    }

}
