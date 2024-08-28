/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.templates.mappingengine;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.MultiKeyMap;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.common.exception.DuplicateTemplateKeyException;
import com.ericsson.eniq.events.server.common.exception.TemplateNotFoundException;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.utils.file.FileOperations;

/**
 * The Class TemplateMappingEngine.
 *
 * @author ehaoswa
 * @since 2011 Jan
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class TemplateMappingEngine {

    private static final String DUPLICATE_KEY_PRESENT_FOR = "Duplicate key present for ";

    private final MultiKeyMap templatesMap = new MultiKeyMap();

    static final String CSV_FILE_EXTENSION = ".csv";

    static final String CSV_FILE = "/templateMap.csv";

    private static final String PATH_NAME = "PATH_NAME";

    @EJB
    private FileOperations fileOperations;

    /**
     * Application startup. Load the csv into Multi key map.
     */
    @PostConstruct
    public void applicationStartup() {

        try {
            //using the existing CSV_FILE as a marker to find all application .csv files
            final String csvFolder = fileOperations.getPathToParentFolder(CSV_FILE);
            final List<String> linesInFile = fileOperations.readFiles(csvFolder, CSV_FILE_EXTENSION);
            populateTemplateMap(linesInFile);
        } catch (final FileNotFoundException e) {
            ServicesLogger.error(getClass().getName(), "applicationStartup", e);
        } catch (final IOException e) {
            ServicesLogger.error(getClass().getName(), "applicationStartup", e);
        }
        ServicesLogger.warn(this.getClass().toString(), "applicationStartup", templatesMap.size());
    }

    /**
     * Application shutdown. Clear the map.
     */
    @PreDestroy
    public void applicationShutdown() {
        templatesMap.clear();
    }

    private void populateTemplateMap(final List<String> linesInFile) {
        for (final String line : linesInFile) {
            if (isNotAComment(line)) {
                final List<String> csvValues = tokenizeLineInFile(line);
                final List<String> key = getKeyForEntry(csvValues);
                final String templateName = getTemplateName(csvValues);
                final MultiKey multiKey = new MultiKey(key.toArray());
                checkForDuplicateKey(multiKey);
                templatesMap.put(multiKey, templateName);
            }
        }
    }

    private void checkForDuplicateKey(final MultiKey multiKey) {
        if (templatesMap.containsKey(multiKey)) {
            if (!isHeaderKey(multiKey)) {
                throw new DuplicateTemplateKeyException(DUPLICATE_KEY_PRESENT_FOR + multiKey);
            }
        }

    }

    private boolean isHeaderKey(final MultiKey multiKey) {
        return multiKey.getKey(0).equals(PATH_NAME);
    }

    /**
     * The entry in the file is:
     * EVENT_ANALYSIS,PTMSI,false,null,false,TOTAL,_RAW,common/q_event_analysis_details.vm
     * This method returns the template name in this line ie 
     * common/q_event_analysis_details.vm
     * 
     * @param csvValues                 line in the templateMap.csv file, tokenized
     * @return template name            the template name for this entry
     * 
     */
    private String getTemplateName(final List<String> csvValues) {
        return csvValues.get(csvValues.size() - 1);
    }

    /**
     * The entry in the file is:
     * EVENT_ANALYSIS,PTMSI,false,null,false,TOTAL,_RAW,common/q_event_analysis_details.vm
     * This method returns the key used for the templateMap ie
     * EVENT_ANALYSIS,PTMSI,false,null,false,TOTAL,_RAW
     * 
     * @param csvValues                 line in the templateMap.csv file, tokenized
     * @return key                      the key for this entry
     * 
     */
    private List<String> getKeyForEntry(final List<String> csvValues) {
        final List<String> key = new ArrayList<String>();
        for (int i = 0; i < csvValues.size() - 1; i++) {
            key.add(csvValues.get(i));
        }
        return key;
    }

    private List<String> tokenizeLineInFile(final String line) {
        final StringTokenizer stringTokenizer = new StringTokenizer(line, ApplicationConstants.DELIMITER);
        final List<String> csvValues = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            csvValues.add(nextToken);
        }
        return csvValues;
    }

    private boolean isNotAComment(final String line) {
        return !line.startsWith(COMMENT_PREFIX);
    }

    /**
     * Gets the template.
     *
     * @param multiKey the multi key
     * @return the template
     * @deprecated use the other getTemplate() methods in this class that take in the pathName, requestParameters and drillType
     */
    @Deprecated
    public String getTemplate(final MultiKey multiKey) {
        ServicesLogger.detailed(Level.FINE, getClass().getName(), "getTemplate", multiKey);
        return (String) templatesMap.get(multiKey);
    }

    /**
     * Gets the template. Does not consider the timerange when choosing the
     * template
     *
     * @param pathName          the path name
     * @param requestParameters the request parameters
     * @param drillType         the drill type
     * @return the template
     */
    public String getTemplate(final String pathName, final MultivaluedMap<String, String> requestParameters,
            final String drillType) {
        final MultiKey multiKey = getMultiKey(pathName, requestParameters, drillType, null);
        final String template = getTemplate(multiKey);
        if (template == null) {
            ServicesLogger.warn(this.getClass().toString(), "getTemplate", templatesMap.size());
            throw new TemplateNotFoundException(multiKey);
        }
        return template;
    }

    /**
     * Gets the template, and considers the timerange parameter when selecting the
     * template The timerange (which should be one of EventDataSourceType's
     * values) will be translated into the aggregate view type, and this key
     * passed to the template mapping file As more and more templates are
     * refactored into templates/queries that use the raw tables and
     * templates/queries that use the aggregation tables, this method should be
     * used exclusively
     * <p/>
     * If no matching template is found, this method retries without the dataViewType argument
     * Finally, if a matching template cannot be found without the dataViewType argument, an exception is thrown
     *
     * @param pathName          the path name
     * @param requestParameters the request parameters
     * @param drillType         the drill type
     * @param dataViewType      the view type eg _15MIN
     * @return
     */
    public String getTemplate(final String pathName, final MultivaluedMap<String, String> requestParameters,
            final String drillType, final String dataViewType) {
        final MultiKey multiKey = getMultiKey(pathName, requestParameters, drillType, dataViewType);
        String template = getTemplate(multiKey);
        if (template == null) {
            final MultiKey multiKeyWithoutTimeRange = getMultiKey(pathName, requestParameters, drillType, null);
            template = getTemplate(multiKeyWithoutTimeRange);
            if (template == null) {
                ServicesLogger.warn(this.getClass().toString(), "getTemplate", templatesMap.size());
                throw new TemplateNotFoundException(multiKey, multiKeyWithoutTimeRange);
            }
        }
        return template;
    }

    /**
     * Put together the multikey based on the parameters
     *
     * @param pathName          pathName of template
     * @param requestParameters request parameters provided by user
     * @param drillType         drill type for query
     * @param dataViewType      data view/time range for query eg _15MIN.  If this argument is null, then
     *                          it is not added to the multikey returned
     * @return MultiKey
     */
    private MultiKey getMultiKey(final String pathName, final MultivaluedMap<String, String> requestParameters,
            final String drillType, final String dataViewType) {
        boolean isGroup = false;
        String type = null;
        boolean hasEventID = false;
        String key = null;
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            isGroup = true;
        }
        if (requestParameters.containsKey(TYPE_PARAM)) {
            type = requestParameters.getFirst(TYPE_PARAM);
        }
        if (requestParameters.containsKey(EVENT_ID_PARAM)) {
            hasEventID = true;
        }
        if (requestParameters.containsKey(KEY_PARAM)) {
            key = requestParameters.getFirst(KEY_PARAM);
        }
        final String[] keys;
        if (dataViewType == null) {
            keys = new String[] { pathName, String.valueOf(type), String.valueOf(isGroup), String.valueOf(drillType),
                    String.valueOf(hasEventID), String.valueOf(key) };
        } else {
            keys = new String[] { pathName, String.valueOf(type), String.valueOf(isGroup), String.valueOf(drillType),
                    String.valueOf(hasEventID), String.valueOf(key), dataViewType };
        }
        final MultiKey multiKey = new MultiKey(keys);
        return multiKey;
    }

    public void setFileOperations(final FileOperations fileOperations) {
        this.fileOperations = fileOperations;

    }
}
