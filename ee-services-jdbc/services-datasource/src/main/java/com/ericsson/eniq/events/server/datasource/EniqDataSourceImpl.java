/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Class that wraps the SQL DataSource interface and implementation to provide
 * an extra method (getWeight) for use in load balancing policies
 * 
 * @author eemecoy
 *
 */
public class EniqDataSourceImpl implements EniqDataSource {

    private final DataSource dataSource;

    /**
     *  weight that should be applied to the data source - the higher the weight, the
     *  more often the data source will be used in queries when the WeightedRoundRobinLoadBalancingPolicy
     *  is in use 
     */
    private final int weightFactor;

    /**
     * represents the connection pool name
     */
    private final String poolName ;
    
    /**
     * 
     * @param dataSource
     * @param poolName                  name of connection pool used for the current database connection
     * @param weightFactor              weight that should be applied to the data source - the higher the weight, the
     *                                  more often the data source will be used in queries when the WeightedRoundRobinLoadBalancingPolicy
     *                                  is in use 
     */
    public EniqDataSourceImpl(final DataSource dataSource,final String poolName, final int weightFactor) {
        this.dataSource = dataSource;
        this.weightFactor = weightFactor;
        this.poolName = poolName;
    }

    /* (non-Javadoc)
     * @see javax.sql.DataSource#getConnection()
     */
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /* (non-Javadoc)
     * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
     */
    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        return dataSource.getConnection(username, password);
    }

    /* (non-Javadoc)
     * @see javax.sql.CommonDataSource#getLogWriter()
     */
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    /* (non-Javadoc)
     * @see javax.sql.CommonDataSource#getLoginTimeout()
     */
    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    /* (non-Javadoc)
     * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
     */
    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    /* (non-Javadoc)
     * @see javax.sql.CommonDataSource#setLoginTimeout(int)
     */
    @Override
    public void setLoginTimeout(final int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);

    }

    /* (non-Javadoc)
     * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
     */
    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }

    /* (non-Javadoc)
     * @see java.sql.Wrapper#unwrap(java.lang.Class)
     */
    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public int getWeight() {
        return weightFactor;
    }

    @Override
	public String getPoolName() {
		return poolName;
	}

}
