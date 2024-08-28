package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ericsson.eniq.events.server.services.soap.queryservice package.
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

    private final static QName _DSWSExceptionCauseDetail_QNAME = new QName("", "CauseDetail");
    private final static QName _DSWSExceptionCauseMessage_QNAME = new QName("", "CauseMessage");
    private final static QName _DSWSExceptionCauseException_QNAME = new QName("", "CauseException");
    private final static QName _DSWSExceptionCallStackTrace_QNAME = new QName("", "CallStackTrace");
    private final static QName _DSWSExceptionCauseID_QNAME = new QName("", "CauseID");
    private final static QName _DataSourceParameters_QNAME = new QName("http://datasourceparameter.businessobjects.com/2007/06/01", "DataSourceParameters");
    private final static QName _DSWSException_QNAME = new QName("http://dsws.businessobjects.com/2007/06/01", "DSWSException");
    private final static QName _NativeFreeValue_QNAME = new QName("http://query.businessobjects.com/2007/06/01", "NativeFreeValue");
    private final static QName _InputProperties_QNAME = new QName("http://queryservice.dsws.businessobjects.com/2007/06/01", "inputProperties");
    private final static QName _NameValuePair_QNAME = new QName("http://dsws.businessobjects.com/2007/06/01", "NameValuePair");
    private final static QName _NativeValue_QNAME = new QName("http://query.businessobjects.com/2007/06/01", "NativeValue");
    private final static QName _Sampling_QNAME = new QName("http://datasource.businessobjects.com/2007/06/01", "Sampling");
    private final static QName _DataSourceParameterValues_QNAME = new QName("http://datasourceparameter.businessobjects.com/2007/06/01", "DataSourceParameterValues");
    private final static QName _PreConditionName_QNAME = new QName("http://query.businessobjects.com/2005", "Name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ericsson.eniq.events.server.services.soap.queryservice
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataSourceListResponse }
     */
    public GetDataSourceListResponse createGetDataSourceListResponse() {
        return new GetDataSourceListResponse();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceClass }
     */
    public DataSourceClass createDataSourceClass() {
        return new DataSourceClass();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.ObjectComparison }
     */
    public ObjectComparison createObjectComparison() {
        return new ObjectComparison();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.ModifyDataProviderResponse }
     */
    public ModifyDataProviderResponse createModifyDataProviderResponse() {
        return new ModifyDataProviderResponse();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.Dimension }
     */
    public Dimension createDimension() {
        return new Dimension();
    }

    /**
     * Create an instance of {@link ReturnProperties }
     */
    public ReturnProperties createReturnProperties() {
        return new ReturnProperties();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceSpecification }
     */
    public DataSourceSpecification createDataSourceSpecification() {
        return new DataSourceSpecification();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DeleteDataProviderResponse }
     */
    public DeleteDataProviderResponse createDeleteDataProviderResponse() {
        return new DeleteDataProviderResponse();
    }

    /**
     * Create an instance of {@link QueryObjectSort }
     */
    public QueryObjectSort createQueryObjectSort() {
        return new QueryObjectSort();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.Filter }
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link Operand }
     */
    public Operand createOperand() {
        return new Operand();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.Detail }
     */
    public Detail createDetail() {
        return new Detail();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceObject }
     */
    public DataSourceObject createDataSourceObject() {
        return new DataSourceObject();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.CombinedQuery }
     */
    public CombinedQuery createCombinedQuery() {
        return new CombinedQuery();
    }

    /**
     * Create an instance of {@link ValueFromLov }
     */
    public ValueFromLov createValueFromLov() {
        return new ValueFromLov();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.CreateDataProvider }
     */
    public CreateDataProvider createCreateDataProvider() {
        return new CreateDataProvider();
    }

    /**
     * Create an instance of {@link QueryObject }
     */
    public QueryObject createQueryObject() {
        return new QueryObject();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.ConditionBase }
     */
    public ConditionBase createConditionBase() {
        return new ConditionBase();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.Measure }
     */
    public Measure createMeasure() {
        return new Measure();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.Hierarchy }
     */
    public Hierarchy createHierarchy() {
        return new Hierarchy();
    }

    /**
     * Create an instance of {@link Query }
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link Values }
     */
    public Values createValues() {
        return new Values();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceParameter }
     */
    public DataSourceParameter createDataSourceParameter() {
        return new DataSourceParameter();
    }

    /**
     * Create an instance of {@link QueryProperty }
     */
    public QueryProperty createQueryProperty() {
        return new QueryProperty();
    }

    /**
     * Create an instance of {@link SubQuery }
     */
    public SubQuery createSubQuery() {
        return new SubQuery();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataSourceList }
     */
    public GetDataSourceList createGetDataSourceList() {
        return new GetDataSourceList();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSource }
     */
    public DataSource createDataSource() {
        return new DataSource();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.NameValuePair }
     */
    public NameValuePair createNameValuePair() {
        return new NameValuePair();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataProviderInformation }
     */
    public GetDataProviderInformation createGetDataProviderInformation() {
        return new GetDataProviderInformation();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataSourceResponse }
     */
    public GetDataSourceResponse createGetDataSourceResponse() {
        return new GetDataSourceResponse();
    }

    /**
     * Create an instance of {@link QuerySpecification }
     */
    public QuerySpecification createQuerySpecification() {
        return new QuerySpecification();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.CreateDataProviderResponse }
     */
    public CreateDataProviderResponse createCreateDataProviderResponse() {
        return new CreateDataProviderResponse();
    }

    /**
     * Create an instance of {@link PreCondition }
     */
    public PreCondition createPreCondition() {
        return new PreCondition();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataProviderInformationResponse }
     */
    public GetDataProviderInformationResponse createGetDataProviderInformationResponse() {
        return new GetDataProviderInformationResponse();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataProviderList }
     */
    public GetDataProviderList createGetDataProviderList() {
        return new GetDataProviderList();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.InputProperties }
     */
    public InputProperties createInputProperties() {
        return new InputProperties();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataSource }
     */
    public GetDataSource createGetDataSource() {
        return new GetDataSource();
    }

    /**
     * Create an instance of {@link Prompt }
     */
    public Prompt createPrompt() {
        return new Prompt();
    }

    /**
     * Create an instance of {@link QueryBase }
     */
    public QueryBase createQueryBase() {
        return new QueryBase();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataProviderInformation }
     */
    public DataProviderInformation createDataProviderInformation() {
        return new DataProviderInformation();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DSWSException }
     */
    public DSWSException createDSWSException() {
        return new DSWSException();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceParameterValue }
     */
    public DataSourceParameterValue createDataSourceParameterValue() {
        return new DataSourceParameterValue();
    }

    /**
     * Create an instance of {@link PreConditionObject }
     */
    public PreConditionObject createPreConditionObject() {
        return new PreConditionObject();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.DeleteDataProvider }
     */
    public DeleteDataProvider createDeleteDataProvider() {
        return new DeleteDataProvider();
    }

    /**
     * Create an instance of {@link Sampling }
     */
    public Sampling createSampling() {
        return new Sampling();
    }

    /**
     * Create an instance of {@link QueryCondition }
     */
    public QueryCondition createQueryCondition() {
        return new QueryCondition();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.GetDataProviderListResponse }
     */
    public GetDataProviderListResponse createGetDataProviderListResponse() {
        return new GetDataProviderListResponse();
    }

    /**
     * Create an instance of {@link com.ericsson.eniq.events.server.services.soap.queryservice.ModifyDataProvider }
     */
    public ModifyDataProvider createModifyDataProvider() {
        return new ModifyDataProvider();
    }

    /**
     * Create an instance of {@link Rank }
     */
    public Rank createRank() {
        return new Rank();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseDetail", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseDetail(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseDetail_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseMessage", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseMessage(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseMessage_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseException", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseException(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseException_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CallStackTrace", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCallStackTrace(String value) {
        return new JAXBElement<String>(_DSWSExceptionCallStackTrace_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseID", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseID(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseID_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceParameter }{@code >}}
     */
    @XmlElementDecl(namespace = "http://datasourceparameter.businessobjects.com/2007/06/01", name = "DataSourceParameters")
    public JAXBElement<DataSourceParameter> createDataSourceParameters(DataSourceParameter value) {
        return new JAXBElement<DataSourceParameter>(_DataSourceParameters_QNAME, DataSourceParameter.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.ericsson.eniq.events.server.services.soap.queryservice.DSWSException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://dsws.businessobjects.com/2007/06/01", name = "DSWSException")
    public JAXBElement<DSWSException> createDSWSException(DSWSException value) {
        return new JAXBElement<DSWSException>(_DSWSException_QNAME, DSWSException.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Object }{@code >}}
     */
    @XmlElementDecl(namespace = "http://query.businessobjects.com/2007/06/01", name = "NativeFreeValue")
    public JAXBElement<Object> createNativeFreeValue(Object value) {
        return new JAXBElement<Object>(_NativeFreeValue_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.ericsson.eniq.events.server.services.soap.queryservice.InputProperties }{@code >}}
     */
    @XmlElementDecl(namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", name = "inputProperties")
    public JAXBElement<InputProperties> createInputProperties(InputProperties value) {
        return new JAXBElement<InputProperties>(_InputProperties_QNAME, InputProperties.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.ericsson.eniq.events.server.services.soap.queryservice.NameValuePair }{@code >}}
     */
    @XmlElementDecl(namespace = "http://dsws.businessobjects.com/2007/06/01", name = "NameValuePair")
    public JAXBElement<NameValuePair> createNameValuePair(NameValuePair value) {
        return new JAXBElement<NameValuePair>(_NameValuePair_QNAME, NameValuePair.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Object }{@code >}}
     */
    @XmlElementDecl(namespace = "http://query.businessobjects.com/2007/06/01", name = "NativeValue")
    public JAXBElement<Object> createNativeValue(Object value) {
        return new JAXBElement<Object>(_NativeValue_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Sampling }{@code >}}
     */
    @XmlElementDecl(namespace = "http://datasource.businessobjects.com/2007/06/01", name = "Sampling")
    public JAXBElement<Sampling> createSampling(Sampling value) {
        return new JAXBElement<Sampling>(_Sampling_QNAME, Sampling.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceParameterValue }{@code >}}
     */
    @XmlElementDecl(namespace = "http://datasourceparameter.businessobjects.com/2007/06/01", name = "DataSourceParameterValues")
    public JAXBElement<DataSourceParameterValue> createDataSourceParameterValues(DataSourceParameterValue value) {
        return new JAXBElement<DataSourceParameterValue>(_DataSourceParameterValues_QNAME, DataSourceParameterValue.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://query.businessobjects.com/2005", name = "Name", scope = PreCondition.class)
    public JAXBElement<String> createPreConditionName(String value) {
        return new JAXBElement<String>(_PreConditionName_QNAME, String.class, PreCondition.class, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://query.businessobjects.com/2005", name = "Name", scope = QueryObject.class)
    public JAXBElement<String> createQueryObjectName(String value) {
        return new JAXBElement<String>(_PreConditionName_QNAME, String.class, QueryObject.class, value);
    }

}
