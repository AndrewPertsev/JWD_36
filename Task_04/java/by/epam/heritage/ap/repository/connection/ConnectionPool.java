package by.epam.heritage.ap.repository.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<Connection> connectionPool = null;
    private BlockingQueue<Connection> activeConnections = null;

    private static int POOL_SIZE;
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_DRIVER;
    private static String DB_PASSWORD;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.DB_URL = dbResourceManager.getValue(DBParameter.DB_URL);
        this.DB_USER = dbResourceManager.getValue(DBParameter.DB_USER);
        this.DB_DRIVER = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.DB_PASSWORD = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.POOL_SIZE = Integer.parseInt(dbResourceManager.getValue(DBParameter.POOL_SIZE));
        } catch (NumberFormatException e) {
            logger.error("Unable to get pool size value", e);
            POOL_SIZE = 5;
        }

    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init() throws PoolException {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            System.out.println("DRIVER INIT"); //////////////////////////////////////////////////////////////
            connectionPool = new ArrayBlockingQueue<>(POOL_SIZE);
            activeConnections = new ArrayBlockingQueue<>(POOL_SIZE);

            for (int i = 0; i < POOL_SIZE; i++) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                connectionPool.add(proxyConnection);
                System.out.println("conn init");/////////////////////////////////////////////////////////////////////////
            }
        } catch (SQLException e) {
            logger.error("Unable to connect driver.", e);
            throw new PoolException(e);
        } catch (ClassNotFoundException e) {
            logger.error("Unable to found class driver.", e);
            throw new PoolException(e);
        }
    }

    public Connection getConnection() throws PoolException {
        Connection connection = null;
        try {
            connection = connectionPool.take();
            activeConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Unable get connection ", e);
            throw new PoolException(e);
        }
        return connection;
    }


    public void clearConnectionQueue() {
        try {
            closeConnectionsQueue(activeConnections);
            closeConnectionsQueue(connectionPool);
        } catch (SQLException | PoolException e) {
            logger.error("Error closing the connection.", e);
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException, PoolException {
        Connection connection = queue.poll();
        System.out.println("CPool CLOSE QUEUES");///////////////////////////////////////////////////////////
        while (queue.poll() != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((ProxyConnection) connection).reallyClose();

        }
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) throws PoolException {
        try {
            rs.close();

        } catch (SQLException e) {
            logger.error("ResultSet isn't closed.", e);
            throw new PoolException(e);
        }
        try {
            st.close();

        } catch (SQLException e) {
            logger.error("Statement isn't closed.", e);
            throw new PoolException(e);
        }
        try {
            con.close();
            System.out.println(" connection back to pool");////////////////////////////////////////////
        } catch (SQLException e) {
            logger.error("Connection isn't return to the pool.", e);
            throw new PoolException(e);
        }
    }

    public void closeConnection(Connection con, Statement st) throws PoolException {
        try {
            st.close();
        } catch (SQLException e) {
            logger.error("Statement isn't closed.", e);
            throw new PoolException(e);
        }
        try {
            con.close();
        } catch (SQLException e) {
            logger.error("Connection isn't return to the pool.", e);
            throw new PoolException(e);
        }
    }


    private class ProxyConnection implements Connection {
        private final Logger logger = LogManager.getLogger(this.getClass().getName());

        private Connection connection;

        public ProxyConnection(Connection connection) throws SQLException {
            this.connection = connection;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            System.out.println("proxy REALLY CLOSE ");///////////////////////////////////////////////////////////
            connection.close();

        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                logger.error("Attempting to close closed connection.");
                throw new SQLException();
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!activeConnections.remove(this)) {
                logger.error("Error deleting connection from the given away connections pool");
                throw new SQLException();
            }
            if (!connectionPool.offer(this)) {
                logger.error("Error allocating connection in the pool.");
                throw new SQLException();
            }
        }


        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }
}
