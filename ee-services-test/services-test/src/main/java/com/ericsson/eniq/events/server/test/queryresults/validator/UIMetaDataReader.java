/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.validator;

import java.io.InputStream;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.util.FileUtilities;

/**
 * Read the UIMetaData.json file, and given a grid ID, extract the schema/definition for that grid from the meta data
 * @author eemecoy
 *
 */
public class UIMetaDataReader {

    private static final String PATH_TO_UIMETADATA_MSS_GRIDS = "uimetadata/mss/grids";

    private static final String PATH_TO_UIMETADATA_STANDARD_GRIDS = "uimetadata/standard/grids/";

    public GridDefinition getGrid(final String gridId) throws Exception {

        final String uiMetaData = FileUtilities.readInputStream(loadGrid(gridId));
        final JSONObject jsonObject = new JSONObject(uiMetaData);

        if (gridId.equals(jsonObject.get("id"))) {
            return new GridDefinition(jsonObject);
        }

        throw new Exception("Grid " + gridId + " could not be found.");
    }

    public InputStream loadGrid(final String gridId) {
        final String gridDefinitionFileName = gridId + ".json";
        InputStream fileInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                PATH_TO_UIMETADATA_STANDARD_GRIDS + gridDefinitionFileName);

        if (fileInputStream == null) {
            fileInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    PATH_TO_UIMETADATA_MSS_GRIDS + gridDefinitionFileName);
        }
        if (fileInputStream == null) {
            throw new RuntimeException("Could not find grid definition for " + gridId + ", could not find the file "
                    + gridDefinitionFileName + " in the folders " + PATH_TO_UIMETADATA_STANDARD_GRIDS + " or "
                    + PATH_TO_UIMETADATA_MSS_GRIDS);
        }
        return fileInputStream;
    }

}
