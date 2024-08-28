/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.SystemException;

import com.distocraft.dc5000.etl.engine.main.ITransferEngineRMI;
import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * The Class RMIEngineUtils.
 *
 * @author eavidat
 * @since 2010
 * @author ehaoswa
 * @since Apr 2011
 */

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class RMIEngineUtils {

    private static final String DEFAULT_TRANSFER_ENGINE_NAME = "TransferEngine";

    protected static final String DEFAULT_PORT = "1200";

    /** The server port. */
    private static String serverPort;

    /** The server ref name. */
    private static String serverRefName;

    /** The i transfer engine rmi. */
    private ITransferEngineRMI iTransferEngineRMI;

    protected final Map<String, String> keyToMSSViewMapping = new HashMap<String, String>();

    /** The Constant ENGINE_HOSTNAME. */
    private static final String ENGINE_HOSTNAME = "engine";

    /**
     * Populate key to view maps.
     */
    @PostConstruct
    public void populateKeyToViewMaps() {
        keyToMSSViewMapping.put(KEY_TYPE_ERR, EVENT_E_MSS_VOICE_CDR_ERR_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_DROP_CALL, EVENT_E_MSS_VOICE_CDR_DROP_CALL_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_SUC, EVENT_E_MSS_VOICE_CDR_SUC_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_SUM, EVENT_E_MSS_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_TOTAL, EVENT_E_MSS_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_LOC_SERVICE_ERR, EVENT_E_MSS_LOC_SERVICE_CDR_ERR_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_LOC_SERVICE_SUC, EVENT_E_MSS_LOC_SERVICE_CDR_SUC_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_SMS_ERR, EVENT_E_MSS_SMS_CDR_ERR_RAW);
        keyToMSSViewMapping.put(KEY_TYPE_SMS_SUC, EVENT_E_MSS_SMS_CDR_SUC_RAW);
    }

    @PreDestroy
    public void applicationDestroy() {
        keyToMSSViewMapping.clear();
    }

    /**
     * This method has the responsibility to read the ETLCServer.properties and
     * get the URL parameters for RMI call
     * 
     * return true if all is fine; false otherwise
     *
     * @return the properties
     */
    protected boolean getProperties() {

        String sysPropDC5000 = System.getProperty("CONF_DIR", "/eniq/sw/conf");
        ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", "conf dir is " + sysPropDC5000);
        if (sysPropDC5000 == null) {
            try {
                throw new SystemException("System property dc5000.config.directory not defined");
            } catch (final SystemException e) {
                ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", e.getMessage());
                return false;
            }
        }
        if (!sysPropDC5000.endsWith(File.separator)) {
            sysPropDC5000 += File.separator;
        }

        final Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(sysPropDC5000 + "ETLCServer.properties")));
        } catch (final FileNotFoundException e) {
            ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", "have an exception 1");
            ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", e.getMessage());
            return false;
        } catch (final IOException e) {
            ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", "have an exception 2");
            ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", e.getMessage());
            return false;
        }

        serverPort = props.getProperty("ENGINE_PORT", DEFAULT_PORT);
        final String engineRefNameKey = "ENGINE_REFNAME";
        serverRefName = props.getProperty(engineRefNameKey, DEFAULT_TRANSFER_ENGINE_NAME);
        ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", "picking up ENGINE_REFNAME as "
                + serverRefName);
        final boolean containsKey = props.containsKey(engineRefNameKey);
        ServicesLogger.warn(RMIEngineUtils.class.getName(), "getProperties", "does property actually have key "
                + engineRefNameKey + ", " + containsKey);

        return true;
    }

    /**
     * This method has the responsibility to connect to RMI interface using lookup
     * naming service The URL that will be used to get the RMI object is found by
     * calling the getProperties() method
     * 
     * It creates the RMI client-object only once and keep that in memory till any
     * exception occurs (e.g. engine down, connection broken) If an exception
     * occurs, it tries to create the RMI object again in next call
     * 
     */
    private synchronized void lookupITransferEngineRMI() {
        if (iTransferEngineRMI == null && getProperties()) {

            final String rmiURL = "//" + getEngineHostName() + ":" + serverPort + "/" + serverRefName;
            ServicesLogger.info(RMIEngineUtils.class.getName(), "lookupITransferEngineRMI", "Connecting engine @ "
                    + rmiURL);
            try {
                iTransferEngineRMI = (ITransferEngineRMI) Naming.lookup(rmiURL);
            } catch (final Exception e) {
                ServicesLogger.exception(this.getClass().getName(), "lookupITransferEngineRMI",
                        "exception trying to contact " + rmiURL, e);
                iTransferEngineRMI = null;
            }
        }
    }

    @Lock(LockType.READ)
    protected String getEngineHostName() {
        return ENGINE_HOSTNAME;
    }

    /**
     * This is the one of the public methods to outside world which is used to get
     * a list of RAW table names for a particular time range This is used for MSS
     * queries. DROP_CALL tables are included as error tables.
     * 
     * 
     * @param from
     *          : start time for the query
     * @param to
     *          : end time for the query
     * @param key
     *          : key which controls what raw tables are needed (e.g. Error,
     *          Success)
     * 
     * @return : list of RAW table names that will be used inplace of specified
     *         MSS RAW view
     *         
     * @deprecated   provided only for legacy methods, classes should use the getTableNames() method, and pass in the view names to query on
     */
    @Deprecated
    public List<String> getTableNamesForMSS(final Timestamp from, final Timestamp to, final String key) {
        final String viewNameMSS = keyToMSSViewMapping.get(key);
        return getTableNamesForRawEvents(from, to, viewNameMSS);
    }

    /**
     * This is the main method to get a list of RAW table names for a particular
     * time range This method has the responsibility to connect to RMI interface
     * to get the table names
     * 
     * It creates the RMI client-object only once and keep that in memory till any
     * exception occurs (e.g. engine down, connection broken) If an exception
     * occurs, it tries to create the RMI object again in next call
     * 
     * If all is fine, it calls the method getTableNamesForRawEvents on the RMI
     * object to get the list of tables. Otherwise, it returns an empty list
     * 
     * @param from
     *          : start time for the query
     * @param to
     *          : end time for the query
     * @param viewNames
     *          : view names of which raw tables are needed (e.g.
     *          EVENT_E_SGEH_SUC_RAW, EVENT_E_SGEH_ERR_RAW, EVENT_E_LTE_RAW)
     * @return          : list of RAW table names
     */
    private List<String> getTableNamesForRawEvents(final Timestamp from, final Timestamp to, final String... viewNames) {
        if (iTransferEngineRMI == null) {
            lookupITransferEngineRMI();
        }
        if (iTransferEngineRMI != null) {
            try {
                final List<String> tableNames = new ArrayList<String>();
                for (final String viewName : viewNames) {
                    final List<String> tableNamesForThisView = iTransferEngineRMI.getTableNamesForRawEvents(viewName,
                            from, to);
                    tableNames.addAll(tableNamesForThisView);
                }
                return tableNames;
            } catch (final Exception e) {
                ServicesLogger.warn(RMIEngineUtils.class.getName(), "getTableNamesForRawEvents", e.getMessage());
                iTransferEngineRMI = null;
            }
        }
        return new ArrayList<String>();
    }

    /**
     * method exposed to help with testing.
     *
     * @param iTransferEngineRMI the i transfer engine rmi
     */
    void setITransferEngineRMI(final ITransferEngineRMI iTransferEngineRMI) {
        this.iTransferEngineRMI = iTransferEngineRMI;
    }

    /**
     * This is the one of the public methods to outside world which is used to get
     * a list of RAW table names for the latest time range
     * 
     * @param key error, success, drop_call
     * @return : list of latest RAW table names that will be used inplace of RAW
     *         view
     *         
     * @deprecated classes should use the newer getLatestTableNames(viewNames) instead
     */
    @Deprecated
    public List<String> getLatestTableNamesForMSSRawEvents(final String key) {
        final String viewNameMSS = keyToMSSViewMapping.get(key);
        System.out.println("for key " + key + " view name MSS is " + viewNameMSS);
        final List<String> result = getLatestTableNames(viewNameMSS);
        return result;
    }

    /**
     * Gets the latest table names.
     *
     * @param viewNames
     *          : view names of which raw tables are needed (e.g.
     *          EVENT_E_SGEH_SUC_RAW, EVENT_E_SGEH_ERR_RAW, EVENT_E_LTE_RAW)
     * @return          : list of the latest RAW tables for given views
     */
    public List<String> getLatestTableNames(final String... viewNames) {
        if (iTransferEngineRMI == null) {
            lookupITransferEngineRMI();
        }
        if (iTransferEngineRMI != null) {
            try {
                final List<String> tableNames = new ArrayList<String>();
                for (final String viewName : viewNames) {
                    final List<String> tableNamesForThisView = iTransferEngineRMI
                            .getLatestTableNamesForRawEvents(viewName);
                    tableNames.addAll(tableNamesForThisView);
                }
                return tableNames;
            } catch (final RemoteException e) {
                ServicesLogger.warn(RMIEngineUtils.class.getName(), "getLatestTableNamesForRawEvents", e.getMessage());
                iTransferEngineRMI = null;
            }
        }
        return new ArrayList<String>();
    }

    /**
     * Fetch the raw table names for the given views and timeranges from the ENIQ engine     
     * 
     * 
     * @param from
     *          : start time for the query
     * @param to
     *          : end time for the query
     * @param viewNames
     *          : view names of which raw tables are needed (e.g.
     *          EVENT_E_SGEH_SUC_RAW, EVENT_E_SGEH_ERR_RAW, EVENT_E_LTE_RAW)
     * @return          : list of RAW table names
     */
    public List<String> getTableNames(final Timestamp from, final Timestamp to, final String... viewNames) {
        return getTableNamesForRawEvents(from, to, viewNames);
    }

}
