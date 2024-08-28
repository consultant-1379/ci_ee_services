package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ericsson.eniq.events.server.services.soap.reports.bicatalog package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DSWSException_QNAME = new QName("http://dsws.businessobjects.com/2007/06/01", "DSWSException");
    private final static QName _NameValuePair_QNAME = new QName("http://dsws.businessobjects.com/2007/06/01", "NameValuePair");
    private final static QName _DSWSExceptionCauseDetail_QNAME = new QName("", "CauseDetail");
    private final static QName _DSWSExceptionCauseMessage_QNAME = new QName("", "CauseMessage");
    private final static QName _DSWSExceptionCauseException_QNAME = new QName("", "CauseException");
    private final static QName _DSWSExceptionCallStackTrace_QNAME = new QName("", "CallStackTrace");
    private final static QName _DSWSExceptionCauseID_QNAME = new QName("", "CauseID");
    private final static QName _SimpleSearchEndDate_QNAME = new QName("http://bicatalog.dsws.businessobjects.com/2007/06/01", "EndDate");
    private final static QName _SimpleSearchBeginDate_QNAME = new QName("http://bicatalog.dsws.businessobjects.com/2007/06/01", "BeginDate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ericsson.eniq.events.server.services.soap.reports.bicatalog
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DSWSException }
     */
    public DSWSException createDSWSException() {
        return new DSWSException();
    }

    /**
     * Create an instance of {@link GetCategoryList }
     */
    public GetCategoryList createGetCategoryList() {
        return new GetCategoryList();
    }

    /**
     * Create an instance of {@link GetCatalogResponse }
     */
    public GetCatalogResponse createGetCatalogResponse() {
        return new GetCatalogResponse();
    }

    /**
     * Create an instance of {@link Folder }
     */
    public Folder createFolder() {
        return new Folder();
    }

    /**
     * Create an instance of {@link GetDocumentListResponse }
     */
    public GetDocumentListResponse createGetDocumentListResponse() {
        return new GetDocumentListResponse();
    }

    /**
     * Create an instance of {@link Document }
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link SimpleSearch }
     */
    public SimpleSearch createSimpleSearch() {
        return new SimpleSearch();
    }

    /**
     * Create an instance of {@link GetCategoryListResponse }
     */
    public GetCategoryListResponse createGetCategoryListResponse() {
        return new GetCategoryListResponse();
    }

    /**
     * Create an instance of {@link SearchResponse }
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link GetFolderList }
     */
    public GetFolderList createGetFolderList() {
        return new GetFolderList();
    }

    /**
     * Create an instance of {@link ObjectProperty }
     */
    public ObjectProperty createObjectProperty() {
        return new ObjectProperty();
    }

    /**
     * Create an instance of {@link NameValuePair }
     */
    public NameValuePair createNameValuePair() {
        return new NameValuePair();
    }

    /**
     * Create an instance of {@link GetCatalog }
     */
    public GetCatalog createGetCatalog() {
        return new GetCatalog();
    }

    /**
     * Create an instance of {@link GetObjectProperties }
     */
    public GetObjectProperties createGetObjectProperties() {
        return new GetObjectProperties();
    }

    /**
     * Create an instance of {@link ResourceRight }
     */
    public ResourceRight createResourceRight() {
        return new ResourceRight();
    }

    /**
     * Create an instance of {@link Search }
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link BICatalogObject }
     */
    public BICatalogObject createBICatalogObject() {
        return new BICatalogObject();
    }

    /**
     * Create an instance of {@link WSResource }
     */
    public WSResource createWSResource() {
        return new WSResource();
    }

    /**
     * Create an instance of {@link GetResourceRightsResponse }
     */
    public GetResourceRightsResponse createGetResourceRightsResponse() {
        return new GetResourceRightsResponse();
    }

    /**
     * Create an instance of {@link GetObjectPropertiesResponse }
     */
    public GetObjectPropertiesResponse createGetObjectPropertiesResponse() {
        return new GetObjectPropertiesResponse();
    }

    /**
     * Create an instance of {@link SearchPattern }
     */
    public SearchPattern createSearchPattern() {
        return new SearchPattern();
    }

    /**
     * Create an instance of {@link Category }
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link GetResourceRights }
     */
    public GetResourceRights createGetResourceRights() {
        return new GetResourceRights();
    }

    /**
     * Create an instance of {@link GetFolderListResponse }
     */
    public GetFolderListResponse createGetFolderListResponse() {
        return new GetFolderListResponse();
    }

    /**
     * Create an instance of {@link GetDocumentList }
     */
    public GetDocumentList createGetDocumentList() {
        return new GetDocumentList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DSWSException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://dsws.businessobjects.com/2007/06/01", name = "DSWSException")
    public JAXBElement<DSWSException> createDSWSException(DSWSException value) {
        return new JAXBElement<DSWSException>(_DSWSException_QNAME, DSWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameValuePair }{@code >}}
     */
    @XmlElementDecl(namespace = "http://dsws.businessobjects.com/2007/06/01", name = "NameValuePair")
    public JAXBElement<NameValuePair> createNameValuePair(NameValuePair value) {
        return new JAXBElement<NameValuePair>(_NameValuePair_QNAME, NameValuePair.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseDetail", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseDetail(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseDetail_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseMessage", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseMessage(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseMessage_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseException", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseException(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseException_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CallStackTrace", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCallStackTrace(String value) {
        return new JAXBElement<String>(_DSWSExceptionCallStackTrace_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseID", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseID(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseID_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     */
    @XmlElementDecl(namespace = "http://bicatalog.dsws.businessobjects.com/2007/06/01", name = "EndDate", scope = SimpleSearch.class)
    public JAXBElement<XMLGregorianCalendar> createSimpleSearchEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SimpleSearchEndDate_QNAME, XMLGregorianCalendar.class, SimpleSearch.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     */
    @XmlElementDecl(namespace = "http://bicatalog.dsws.businessobjects.com/2007/06/01", name = "BeginDate", scope = SimpleSearch.class)
    public JAXBElement<XMLGregorianCalendar> createSimpleSearchBeginDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SimpleSearchBeginDate_QNAME, XMLGregorianCalendar.class, SimpleSearch.class, value);
    }

}
