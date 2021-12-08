package by.epam.ap.hotelapp.repository.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static String DB_URL;
    private static String DB_DRIVER;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String PATH_TO_PROPERTIES = "resources/db.properties";
    private static int POOL_SIZE;
    private static int MAX_TIMEOUT;
    private BlockingQueue<Connection> connectionPool;
    private Queue<Connection> activeConnections;

    static {
        FileInputStream fis = null;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream(PATH_TO_PROPERTIES);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fis);
            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.user");
            DB_DRIVER = properties.getProperty("db.driver");
            DB_PASSWORD = properties.getProperty("db.password");
            POOL_SIZE = Integer.parseInt(properties.getProperty("db.pool_size"));
            MAX_TIMEOUT = Integer.parseInt(properties.getProperty("db.timeout"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
      
        Connection connection = null;
        Queue<Connection> activeConnections = new ArrayDeque<>();
        BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                pool.offer(connectDriver(DB_URL, DB_USER, DB_PASSWORD));
                connection = pool.take();
            } catch (PoolException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        activeConnections.offer(connection);

        if (!connection.isValid(MAX_TIMEOUT)) {
            try {
                connection = connectDriver(DB_URL, DB_USER, DB_PASSWORD);
            } catch (PoolException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if (connection == null) {
            return false;
        }
        if (!(connection instanceof ProxyConnection)) {
            //    ("Connection type must be ProxyConnection.");
            return false;
        }
        activeConnections.remove(connection);
        connectionPool.add(connection);
        return true;

    }

    private static Connection connectDriver(String url, String user, String password) throws PoolException {
        try {
            registerDriver();
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PoolException("Unable to connect driver.", e);
        }
    }

    private static void registerDriver() throws PoolException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new PoolException("Database driver not found", e);
        }
    }

    public void shutdown() throws PoolException {
        if (!connectionPool.isEmpty()) {
            for (int i = 0; i < POOL_SIZE; i++) {
                try {
                    connectionPool.take().close();

                } catch (SQLException throwable) {
                    throw new PoolException("Exception by closing free connections", throwable);
                } catch (InterruptedException e) {
                    throw new PoolException("Interrupt at shutdown pool", e);
                    //  logger.error("Interrupt at shutdown pool", e);
                }
            }
            deregisterDrivers();
        }
    }

    public void deregisterDrivers() throws PoolException {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            // logger.error("Error deregister drivers", e);
            throw new PoolException(e);
        }
    }
}

