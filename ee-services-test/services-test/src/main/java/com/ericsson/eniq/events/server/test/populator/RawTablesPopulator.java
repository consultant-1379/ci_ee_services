/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.populator;

import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.ericsson.eniq.events.server.test.sql.SQLCommand;

/**
 * Class responsible for populating the raw tables for services that run detailed queries on the raw tables, and
 * require upwards of 100 columns in the raw tables.
 * This class is to save each test having to populate each of these columns
 * This class will populate the columns with default values unless otherwise specified
 * 
 * @TODO update methods to take in maps of values rather than long parameter lists - each map will be the values
 * that that test requires, any values it doesn't specify should default to a default value
 * 
 * @author EEMECOY
 *
 */
public class RawTablesPopulator {

    private void createAndPopulateTable(final String table, final Connection connection,
            final Map<String, Object> values) throws Exception, SQLException {
        new SQLCommand(connection).createTemporaryTable(table, values.keySet());
        new SQLCommand(connection).insertRow(table, values);
    }

    public void createAndPopulateRawLteErrTable(final Map<String, Object> valuesToInsertInTable,
            final String timestamp, final Connection connection) throws SQLException, Exception {
        final Map<String, Object> valuesForLteTable = new HashMap<String, Object>();
        valuesForLteTable.putAll(RawTableColumns.getColumnsExclusiveToRawLteTables());
        valuesForLteTable.putAll(RawTableColumns.getColumnsForRawTables(timestamp));
        valuesForLteTable.putAll(valuesToInsertInTable);
        createAndPopulateTable(TEMP_EVENT_E_LTE_ERR_RAW, connection, valuesForLteTable);
    }

    public void createAndPopulateRawSgehErrTable(final Map<String, Object> valuesToInsertInTable,
            final String timestamp, final Connection connection) throws SQLException, Exception {
        final Map<String, Object> valuesForSgehTable = new HashMap<String, Object>();
        valuesForSgehTable.putAll(RawTableColumns.getColumnsExclusiveToRawSgehTables());
        valuesForSgehTable.putAll(RawTableColumns.getColumnsForRawTables(timestamp));
        valuesForSgehTable.putAll(valuesToInsertInTable);
        createAndPopulateTable(TEMP_EVENT_E_SGEH_ERR_RAW, connection, valuesForSgehTable);

    }

    public void createRawLteErrTable(final Connection connection) throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.addAll(RawTableColumns.getColumnsExclusiveToRawLteTables().keySet());
        columns.addAll(RawTableColumns.getColumnsForRawTables(null).keySet());
        new SQLCommand(connection).createTemporaryTable(TEMP_EVENT_E_LTE_ERR_RAW, columns);

    }

    public void createRawSgehErrTable(final Connection connection) throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.addAll(RawTableColumns.getColumnsExclusiveToRawSgehTables().keySet());
        columns.addAll(RawTableColumns.getColumnsForRawTables(null).keySet());
        new SQLCommand(connection).createTemporaryTable(TEMP_EVENT_E_SGEH_ERR_RAW, columns);

    }

}
