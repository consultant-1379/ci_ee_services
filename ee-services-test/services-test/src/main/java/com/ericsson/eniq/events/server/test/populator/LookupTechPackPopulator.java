/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.populator;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.ericsson.eniq.events.server.test.file.FileHelperForTests;
import com.ericsson.eniq.events.server.test.schema.ColumnTypes;
import com.ericsson.eniq.events.server.test.sql.SQLCommand;

/**
 * Class responsible for reading up the flat files in com/ericsson/eniq/events/server/test/lookup_table_schemas/
 * and creating and populating the temporary tables based on the schemas and values defined in those files
 *
 * @author eemecoy
 */
public class LookupTechPackPopulator {

    private static final String COMMA = ",";

    /**
     * Read the schema and values for given table from flat file, and create and populate the temporary table based
     * on that schema and value set.
     *
     * @param connection    database connection to use
     * @param tempTableName name of the temporary table eg #DIM_E_SGEH_TAC
     */
    public void createAndPopulateLookupTable(final Connection connection, final String tempTableName) throws Exception {
        final LookupTableDetails tableDetails = readFileAndPopulateFields(determineFileNameForTable(tempTableName));
        createTemporaryTable(connection, tempTableName, tableDetails.getColumns());
        populateTable(connection, tempTableName, tableDetails);
    }

    private void populateTable(final Connection connection, final String tempTableName,
            final LookupTableDetails tableDetails) throws SQLException {
        final List<Map<String, Object>> values = tableDetails.getValues();
        for (final Map<String, Object> row : values) {
            insertRow(connection, tempTableName, row);
        }
    }

    /**
     * Read the values for given table from flat file, and populate the temporary table based
     * on that value set.
     *
     * @param connection    database connection to use
     * @param tempTableName name of the temporary table eg #DIM_E_SGEH_TAC
     */
    public void populateLookupTable(final Connection connection, final String tempTableName) throws Exception {
        final LookupTableDetails tableDetails = readFileAndPopulateFields(determineFileNameForTable(tempTableName));
        populateTable(connection, tempTableName, tableDetails);
    }

    private LookupTableDetails readFileAndPopulateFields(final String fullFileName) throws IOException {
        final List<String> fileContents = new FileHelperForTests().readFileFromClasspath(fullFileName);
        return populateFields(fileContents);
    }

    private LookupTableDetails populateFields(final List<String> fileContents) {
        final Iterator<String> iterator = fileContents.iterator();
        List<String> tableColumns = null;
        List<Map<String, Object>> tableValues = null;
        while (iterator.hasNext()) {
            iterator.next(); //tech pack name
            tableColumns = getTechPackColumns(iterator.next());
            tableValues = new ArrayList<Map<String, Object>>();
            while (iterator.hasNext()) {
                final String lineInFile = iterator.next();
                tableValues.add(parseForTableValues(tableColumns, lineInFile));
            }
        }
        return new LookupTableDetails(tableColumns, tableValues);

    }

    private Map<String, Object> parseForTableValues(final List<String> techPackColumns, final String lineInFile) {
        final StringTokenizer stringTokenizer = new StringTokenizer(lineInFile, COMMA);
        final Iterator<String> columnsIterator = techPackColumns.iterator();
        final Map<String, Object> valuesForRow = new HashMap<String, Object>();
        while (stringTokenizer.hasMoreTokens()) {
            final String columnValue = stringTokenizer.nextToken();
            final String column = columnsIterator.next();
            if (isAStringColumn(column)) {
                valuesForRow.put(column, columnValue);
            } else if (isAReal(column)) {
                valuesForRow.put(column, Float.parseFloat(columnValue));
            } else {
                valuesForRow.put(column, Long.parseLong(columnValue));
            }
        }
        return valuesForRow;
    }

    /**
     * @param column
     * @return
     */
    private boolean isAReal(final String column) {
        final String columnType = ColumnTypes.getColumnType(column);
        return columnType.toUpperCase().contains(REAL_TYPE.toUpperCase());
    }

    private boolean isAStringColumn(final String column) {
        final String columnType = ColumnTypes.getColumnType(column);
        if (columnType != null
                && (columnType.toUpperCase().contains(VARCHAR.toUpperCase()) || columnType.toUpperCase().contains(
                        TIMESTAMP.toUpperCase()))) {
            return true;

        }
        return false;

    }

    private List<String> getTechPackColumns(final String lineInFile) {
        final StringTokenizer stringTokenizer = new StringTokenizer(lineInFile, COMMA);
        final List<String> columns = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            columns.add(stringTokenizer.nextToken());
        }
        return columns;
    }

    private String determineFileNameForTable(final String tempTableName) {
        final String techPackParentFolder = determineTechPack(tempTableName);
        return "com/ericsson/eniq/events/server/test/lookup_table_schemas/" + techPackParentFolder + "/"
                + tempTableName.substring(1) + TXT_EXTENSION;
    }

    private String determineTechPack(final String tempTableName) {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(DIM_E_RAN_CFA);
        techPacks.add(DIM_E_SGEH);
        techPacks.add(DIM_E_IMSI);
        techPacks.add(DIM_E_LTE);
        techPacks.add(DIM_E_RAN);
        for (final String techPack : techPacks) {
            if (tempTableName.contains(techPack)) {
                return techPack;
            }
        }
        throw new RuntimeException("Could not determine parent tech pack for the table " + tempTableName
                + ", please update this class");
    }

    private void insertRow(final Connection connection, final String tableName, final Map<String, Object> row)
            throws SQLException {
        new SQLCommand(connection).insertRow(tableName, row);

    }

    private void createTemporaryTable(final Connection connection, final String table, final List<String> columns)
            throws Exception {
        new SQLCommand(connection).createTemporaryTable(table, columns);

    }

}
