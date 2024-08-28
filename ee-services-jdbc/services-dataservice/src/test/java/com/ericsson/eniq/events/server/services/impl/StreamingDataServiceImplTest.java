/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.services.impl;

import com.ericsson.eniq.events.server.datasource.DBConnectionManager;
import com.ericsson.eniq.events.server.datasource.DataSourceConfigurationException;
import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.query.NamedParameterStatement;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.query.resultsettransformers.ResultSetTransformer;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ericker
 * @since 2010
 *
 */

public class StreamingDataServiceImplTest extends BaseJMockUnitTest {
    StreamingDataServiceImpl objToTest;

    NamedParameterStatement mockedPreparedStatement;

    ResultSetTransformer<String> mockedTransformer;

    LoadBalancingPolicy mockedLoadBalancingPolicy;

    OutputStream mockedOutputStream;

    DBConnectionManager mockedDbConnectionManager;

    Connection mockedConnection;

    PreparedStatement mockedStatement;

    ResultSet mockedResultSet;

    private static final String COLUMN_NAMES = "C1,C2,C3,C4";

    private static final String COLUMN_VALUES = "\"a\",\"b\",\"c\",\"d\"";

    private static final String QUERY = "query";

    private static final String TIME_COLUMN = "timeColumn";

    private static final String TZOFFSET = "tzOffset";
    Method getExportFileMethod = null;
    Map<String, QueryParameter> queryParameters;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        objToTest = new StubbedStreamingDataServiceImpl();
        mockedPreparedStatement = mockery.mock(NamedParameterStatement.class);
        mockedTransformer = mockery.mock(ResultSetTransformer.class);
        mockedLoadBalancingPolicy = mockery.mock(LoadBalancingPolicy.class);
        mockedOutputStream = mockery.mock(OutputStream.class);
        mockedDbConnectionManager = mockery.mock(DBConnectionManager.class);
        mockedConnection = mockery.mock(Connection.class);
        mockedStatement = mockery.mock(PreparedStatement.class);
        mockedResultSet = mockery.mock(ResultSet.class);

//        try {
//            getExportFileMethod = StreamingDataServiceImpl.class.getMethod("getExportFile", null);
//            getExportFileMethod.setAccessible(true);
//        } catch (NoSuchMethodException e) {
//           e.printStackTrace();
//        }
        queryParameters = new HashMap<String, QueryParameter>();
        objToTest.setDbConnectionManager(mockedDbConnectionManager);
    }

    private void setupQueryExpectations() throws IOException, SQLException, DataSourceConfigurationException {
        mockery.checking(new Expectations() {
            {
                one(mockedOutputStream).close();
                one(mockedDbConnectionManager).getCSVConnection(mockedLoadBalancingPolicy);
                will(returnValue(mockedConnection));
                one(mockedPreparedStatement).executeQuery();
                one(mockedPreparedStatement).getResultSet().getMetaData();
                
                //Closing up all the connections...
                one(mockedPreparedStatement).getMoreResults();
                will(returnValue(false));
                //will(returnValue(mockedResultSet));
                //one(mockedPreparedStatement).getMoreResults();
                //will(returnValue(false));
            }
        });
    }

    private void writeCSVHeaderExpectations() throws IOException, SQLException {
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedOutputStream).write(COLUMN_NAMES.getBytes());
                one(mockedOutputStream).flush();
                one(mockedTransformer).transform(mockedResultSet);
                will(returnValue(COLUMN_VALUES));
                one(mockedOutputStream).write(COLUMN_VALUES.getBytes());
            }
        });
    }

    private void connectionCleanupExpectations() throws IOException, SQLException {
        mockery.checking(new Expectations() {
            {
                one(mockedOutputStream).flush();
                one(mockedResultSet).close();
                one(mockedPreparedStatement).close();
                one(mockedConnection).close();
            }
        });
    }

    private void connectionCleanupExpectationsThrownExceptions() throws IOException, SQLException {
        mockery.checking(new Expectations() {
            {
                one(mockedOutputStream).flush();
                one(mockedResultSet).close();
                will(throwException(new SQLException()));
                one(mockedPreparedStatement).close();
                will(throwException(new SQLException()));
                one(mockedConnection).close();
                will(throwException(new SQLException()));
            }
        });
    }


    @Test
    public void testFlushAfterTwoHundredResults() throws IOException, SQLException, DataSourceConfigurationException {
//        setupQueryExpectations();
//        writeCSVHeaderExpectations();
//
//        mockery.checking(new Expectations() {
//            {
//                final States flushLoop = mockery.states("loop").startsAs("written");
//
//                exactly(200).of(mockedResultSet).next();
//                will(returnValue(true));
//                when(flushLoop.is("written"));
//                then(flushLoop.is("next"));
//                exactly(200).of(mockedTransformer).transform(mockedResultSet);
//                will(returnValue(COLUMN_VALUES));
//                when(flushLoop.is("next"));
//                then(flushLoop.is("transform"));
//                exactly(200).of(mockedOutputStream).write(COLUMN_VALUES.getBytes());
//                when(flushLoop.is("transform"));
//                then(flushLoop.is("written"));
//
//                one(mockedResultSet).next();
//                will(returnValue(false));
//                one(mockedOutputStream).flush();
//
//                one(mockedResultSet).setFetchSize(1);
//            }
//        });
//
//        connectionCleanupExpectations();

//        objToTest.streamDataAsCsv(QUERY, queryParameters, TIME_COLUMN, TZOFFSET, mockedLoadBalancingPolicy,
//                mockedOutputStream);
    }

    //@Test
    @Ignore
    public void testStreamingDataToOutput() throws SQLException, IOException, DataSourceConfigurationException {

        setupQueryExpectations();
        writeCSVHeaderExpectations();
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).next();
                will(returnValue(false));
                one(mockedResultSet).setFetchSize(1);

            }
        });
        connectionCleanupExpectations();

        objToTest.streamDataAsCsv(QUERY, queryParameters, TIME_COLUMN, TZOFFSET, mockedLoadBalancingPolicy,
                mockedOutputStream);
    }

    //@Test
    @Ignore
    public void testStreamingDataToOutputExceptionOnResultSetClose() throws SQLException, IOException, DataSourceConfigurationException {

        setupQueryExpectations();
        writeCSVHeaderExpectations();
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).next();
                will(returnValue(false));
                one(mockedResultSet).setFetchSize(1);

            }
        });
        connectionCleanupExpectationsThrownExceptions();

        objToTest.streamDataAsCsv(QUERY, queryParameters, TIME_COLUMN, TZOFFSET, mockedLoadBalancingPolicy,
                mockedOutputStream);
    }


    class StubbedStreamingDataServiceImpl extends StreamingDataServiceImpl {
        @Override
        NamedParameterStatement getPreparedStatement(final String query, final Map<String, QueryParameter> parameters,
                final Connection conn) throws SQLException {
            return mockedPreparedStatement;
        }

//        @Override
//        String getColumnNames(final ResultSet rs) {
//            return COLUMN_NAMES;
//        }
//
//        @Override
//        ResultSetTransformer<String> getCSVResultSetTransformer(final String timeColumn, final String tzOffset) {
//            return mockedTransformer;
//        }
    }
}
