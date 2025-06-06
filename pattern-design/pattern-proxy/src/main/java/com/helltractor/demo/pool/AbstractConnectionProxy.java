package com.helltractor.demo.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class AbstractConnectionProxy implements Connection {

    protected abstract Connection getRealConnection();

    @Override
    public Statement createStatement() throws SQLException {
        return getRealConnection().createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return getRealConnection().prepareStatement(sql);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getRealConnection().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getRealConnection().isWrapperFor(iface);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return getRealConnection().prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return getRealConnection().nativeSQL(sql);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return getRealConnection().getAutoCommit();
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        getRealConnection().setAutoCommit(autoCommit);
    }

    @Override
    public void commit() throws SQLException {
        getRealConnection().commit();
    }

    @Override
    public void rollback() throws SQLException {
        getRealConnection().rollback();
    }

    @Override
    public void close() throws SQLException {
        getRealConnection().close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return getRealConnection().isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return getRealConnection().getMetaData();
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return getRealConnection().isReadOnly();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        getRealConnection().setReadOnly(readOnly);
    }

    @Override
    public String getCatalog() throws SQLException {
        return getRealConnection().getCatalog();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        getRealConnection().setCatalog(catalog);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return getRealConnection().getTransactionIsolation();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        getRealConnection().setTransactionIsolation(level);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return getRealConnection().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        getRealConnection().clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return getRealConnection().createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return getRealConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return getRealConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return getRealConnection().getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        getRealConnection().setTypeMap(map);
    }

    @Override
    public int getHoldability() throws SQLException {
        return getRealConnection().getHoldability();
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        getRealConnection().setHoldability(holdability);
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return getRealConnection().setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return getRealConnection().setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        getRealConnection().rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        getRealConnection().releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return getRealConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        return getRealConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        return getRealConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return getRealConnection().prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return getRealConnection().prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return getRealConnection().prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return getRealConnection().createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return getRealConnection().createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return getRealConnection().createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return getRealConnection().createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return getRealConnection().isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        getRealConnection().setClientInfo(name, value);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return getRealConnection().getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return getRealConnection().getClientInfo();
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        getRealConnection().setClientInfo(properties);
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return getRealConnection().createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return getRealConnection().createStruct(typeName, attributes);
    }

    @Override
    public String getSchema() throws SQLException {
        return getRealConnection().getSchema();
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        getRealConnection().setSchema(schema);
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        getRealConnection().abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        getRealConnection().setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return getRealConnection().getNetworkTimeout();
    }

}
