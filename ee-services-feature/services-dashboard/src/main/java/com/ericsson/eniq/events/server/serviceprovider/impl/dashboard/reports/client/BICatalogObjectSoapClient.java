/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2011
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.xml.namespace.QName;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BIObjectTreeDataType.ObjectType;
import com.ericsson.eniq.events.server.services.soap.reports.bicatalog.*;
import com.ericsson.eniq.events.server.services.soap.reports.bicatalog.DSWSException_Exception;
import com.ericsson.eniq.events.server.services.soap.reports.session.*;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;


/**
 * This class encapsulates the logic of the soap client. The soap client
 * initializes a session to BIS and get the information of all the folders and
 * reports. No concurrent access in all methods so no need to synchronize attributes
 * BIReports tree is updated every 7 seconds.
 *
 * @author ezhelao
 */
@Singleton
public class BICatalogObjectSoapClient {

    @EJB
    private ApplicationConfigManager applicationConfigManager;

    private static final List<String> EMPTY_STRING_LIST = new ArrayList<String>();

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final String FOLDER_OBJECT_TYPE = "Folder";

    private static final String BICATALOG = "BICatalog";

    private static final QName BICATALOG_SERVICE_QNAME = new QName(
            "http://bicatalog.dsws.businessobjects.com/2007/06/01", "BICatalog");

    private static final QName SESSION_SERVICE_QNAME = new QName("http://session.dsws.businessobjects.com/2007/06/01",
            "Session");

    private static final String SESSION = "Session";

    private static final String FOLDER_EMPTY_INFO = "the folder is empty";

    private static final String SERVICE_PATH_NAME = "dswsbobje/services/";

    private static final String ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG = "Root folder does not exist.";

    private static final String SESSION_NOT_INIT_ERROR_MSG = "please initialise the connection before this operation";

    private static String username;

    private static String password;

    private String serviceURL = null;

    private String opendocURLWithFormatter = null;

    private String bisReportsRootObjectName = null;

    private SessionInfo sessionInfo = null;

    private BICatalogSoap biCatalogSoap = null;

    private long resultIsInvalidBeyondThisTime = 0;

    private final long RESULT_TTL_MILLIS = 7000;

    private BIObjectTreeDataType currentResult = null;

    private Exception currentException = null;



    /**
     * get the parameter from the applicationConfigManager
     */
    @PostConstruct
    public void postConstruct() {

        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_PATH_NAME;
        opendocURLWithFormatter = applicationConfigManager.getBISServiceOpenDocCompleteURLWithFormatter();
        bisReportsRootObjectName = applicationConfigManager.getBISReportsRootObjectName();
    }

    /**
     * avoid full package name
     *
     * @throws MalformedURLException if the given url is in a wrong format , this exception will be thrown
     * @throws com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception
     *                               all of web service exception
     */

    public void connect() throws MalformedURLException,
            com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception, UnknownHostException {
        sessionInfo = getSessionInfo(); // get session info

        final BICatalog biCatalog = new BICatalog(new URL(serviceURL + BICATALOG), BICATALOG_SERVICE_QNAME); // init the biCatalog connection object

        biCatalogSoap = biCatalog.getPort(BICatalogSoap.class); // init the connection object ,could be wrong

    }

    /**
     * @return a {@link BIObjectTreeDataType} object which represents the tree structure of the folder.
     * @throws MalformedURLException if the url is wrong . in the searchRootFolder(.. ) method
     * @throws com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception
     *                               when trying to find the the document folder list
    */
    public BIObjectTreeDataType getDocumentFolderList() throws Exception {

        long currentMillis = System.currentTimeMillis();
        if (resultIsInvalidBeyondThisTime > currentMillis) {
            if (currentException != null) {
                throw currentException;
            }
            return currentResult;
        } else {
            try {
                connect();
                if (null == sessionInfo) {
                    throw new com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception(
                            SESSION_NOT_INIT_ERROR_MSG, null);
                }
                final Folder rootFolder = searchRootFolder(sessionInfo);
                currentResult = getDocumentFolderListRecursive(sessionInfo, rootFolder);
                currentException = null;
                return currentResult;
            } catch (Exception ex) {
                currentResult = null;
                currentException = ex;
                resultIsInvalidBeyondThisTime = 0;
                throw ex;
            } finally {
                currentMillis = System.currentTimeMillis();
                resultIsInvalidBeyondThisTime = currentMillis + RESULT_TTL_MILLIS;
            }

        }

    }

    /**
     * @param sessionInfo1 a valid session info object
     * @return the root folder object
     * @throws MalformedURLException   if the URL to the BICatalog is malformed
     * @throws DSWSException_Exception if the web service call goes wrong
     */

    private Folder searchRootFolder(final SessionInfo sessionInfo1) throws MalformedURLException,
            DSWSException_Exception {

        final SimpleSearch search = new SimpleSearch();
        search.setInName(bisReportsRootObjectName);
        search.setObjectType(FOLDER_OBJECT_TYPE);
        final List<SortType> sortTypes = new ArrayList<SortType>();
        sortTypes.add(SortType.NONE);

        final List<BICatalogObject> objects = biCatalogSoap.search(sessionInfo1.getSessionID(), search, sortTypes,
                EMPTY_STRING_LIST, EMPTY_STRING_LIST, InstanceRetrievalType.ALL);

        if ((null == objects) || (objects.size() < ONE) || !(objects.get(ZERO) instanceof Folder)) { // check something is found and valid
            throw new DSWSException_Exception(ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG, null);
        }

        return (Folder) objects.get(ZERO);
    }

    /**
     * @param sessionInfo1 a good sessionInfo object
     * @param rootFolder   the root folder of all elements.
     * @return a BIObjectTreeDataType represent to whole tree.
     * @throws MalformedURLException   if the url format is wrong
     * @throws DSWSException_Exception if something goes wrong when trying to get the document and folder object.
     */
    private BIObjectTreeDataType getDocumentFolderListRecursive(final SessionInfo sessionInfo1, final Folder rootFolder)
            throws MalformedURLException, DSWSException_Exception {

        final List<BIObjectTreeDataType> bioTrees = new ArrayList<BIObjectTreeDataType>(); // a list of current item including folder and document

        final List<SortType> sortTypes = new ArrayList<SortType>();
        sortTypes.add(SortType.NONE);

        final List<Folder> folders = biCatalogSoap.getFolderList(sessionInfo1.getSessionID(), rootFolder.getUID(), ONE,
                sortTypes, EMPTY_STRING_LIST, EMPTY_STRING_LIST);
        List<Document> documents = new ArrayList<Document>();
        try {
            documents = biCatalogSoap.getDocumentList(sessionInfo1.getSessionID(), rootFolder.getUID(), ZERO,
                    sortTypes, EMPTY_STRING_LIST, EMPTY_STRING_LIST, null);
        } catch (final Exception ex) {
            ServicesLogger.warn(getClass().getName(), "getDocumentFolderListRecursive", FOLDER_EMPTY_INFO);
        }
        bioTrees.addAll(convertDocumentListToBIObjectTreeDataTypeList(documents));

        for (final Folder folder : folders) {
            final BIObjectTreeDataType biObject = getDocumentFolderListRecursive(sessionInfo1, folder);
            biObject.setType(ObjectType.FOLDER);
            biObject.setDisplayName(folder.getName());
            bioTrees.add(biObject);

        }

        final BIObjectTreeDataType bioTreeRoot = new BIObjectTreeDataType();// construct the tree from the list ;
        bioTreeRoot.setChildren(bioTrees); // the elements found into the root object
        bioTreeRoot.setType(ObjectType.FOLDER);

        return bioTreeRoot;

    }

    /**
     * converts a list of documents to a list of BIObjectTreeDataType objects
     *
     * @param docs a list of document objects
     * @return a list of BIObjectTreeDataType the converted document list
     */

    private List<BIObjectTreeDataType> convertDocumentListToBIObjectTreeDataTypeList(final List<Document> docs) {
        final List<BIObjectTreeDataType> biObjectTrees = new ArrayList<BIObjectTreeDataType>();
        if (null != docs) {
            for (final Document doc : docs) {
                final BIObjectTreeDataType bioTree = new BIObjectTreeDataType();
                bioTree.setType(ObjectType.FILE);
                final String url = String.format(opendocURLWithFormatter, doc.getUID(), sessionInfo.getDefaultToken());
                bioTree.setFileURL(url);
                bioTree.setDisplayName(doc.getName());
                biObjectTrees.add(bioTree);
            }
        }
        return biObjectTrees;
    }

    /**
     * @return the sessionInfo object is everything is fine.
     * @throws MalformedURLException if the url is malformed ...
     * @throws com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception
     *                               Credential error
     */
    private SessionInfo getSessionInfo() throws MalformedURLException,
            com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception, UnknownHostException {
        final Session session = new Session(new URL(serviceURL + SESSION), SESSION_SERVICE_QNAME);
        final SessionPort sessionPort = session.getSession();

        final EnterpriseCredential credential = new EnterpriseCredential();

        credential.setLogin(username);
        credential.setPassword(password);
        return sessionPort.login(credential, null);

    }
}
