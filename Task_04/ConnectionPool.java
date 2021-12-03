package by.epam.ap.hotelapp.repository.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private BlockingQueue<WrapConnection> pool;
    private BlockingQueue<WrapConnection> activeConnections;

    private static ConnectionPool instance = null;

    private final static int POOL_SIZE = 5;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotelappdb";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "111111";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private ConnectionPool() {
    }

    public static final ConnectionPool getInstance() throws PoolException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionPool();
            instance.initPool();
        }
        return instance;
    }

    public static BlockingQueue initPool() throws PoolException, ClassNotFoundException {
        BlockingQueue<WrapConnection> pool;
        try {
            Class.forName(DB_DRIVER);
            pool = new ArrayBlockingQueue<>(POOL_SIZE);
            //  activeConnections = new ArrayBlockingQueue<WrapConnection>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
                WrapConnection wrapConnection = new WrapConnection(connection);
                pool.add(wrapConnection);
            }
        } catch (SQLException e) {
            throw new PoolException("Can't connect to data base.", e);
        } catch (ClassNotFoundException e) {
            throw new PoolException("DB driver not found.", e);
        }
        return pool;
    }


    public Connection takeConnection() throws PoolException, InterruptedException {
        Connection connection = null;
        connection = pool.take();
        return connection;
    }

    public boolean releaseConnection(Connection connection) throws PoolException {
        if (connection == null) {
            return false;
        }
        if (!(connection instanceof WrapConnection)) {
            return false;
        }
        pool.remove(connection);

        return true;
    }


    public void closePool() throws PoolException {
        while (!pool.isEmpty()) {
            try {
                pool.take().close();
            } catch (SQLException e) {
                throw new PoolException("Can not close the connection", e);
            } catch (InterruptedException e) {
                throw new PoolException("Can not close the pool", e);
            }
        }
    }

}
