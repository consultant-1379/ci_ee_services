package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BICatalogObjectSoapClient;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BIObjectTreeDataType;
import com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Desrible MockBiCatalogObjectSoapClient
 *
 * @author ezhelao
 * @since 11/2011
 */
public class MockBiCatalogObjectSoapClient extends BICatalogObjectSoapClient {
    @Override
    public void postConstruct() {
    }

    public void connect() throws MalformedURLException, DSWSException_Exception {
    }

    @Override
    public BIObjectTreeDataType getDocumentFolderList() throws DSWSException_Exception, MalformedURLException, com.ericsson.eniq.events.server.services.soap.reports.bicatalog.DSWSException_Exception {
        final BIObjectTreeDataType biObjectTreeDataType = new BIObjectTreeDataType();
        final BIObjectTreeDataType theOnlyFile = new BIObjectTreeDataType();
        theOnlyFile.setDisplayName("TheOnlyOneFileIntheFolder.txt");
        theOnlyFile.setType(BIObjectTreeDataType.ObjectType.FILE);
        theOnlyFile.setFileURL("http://fileobjectmockurl/TheOnlyOneFileIntheFolder.txt");
        final List<BIObjectTreeDataType> childrenList = new ArrayList<BIObjectTreeDataType>();
        childrenList.add(theOnlyFile);
        biObjectTreeDataType.setChildren(childrenList);
        return biObjectTreeDataType;
    }
}
