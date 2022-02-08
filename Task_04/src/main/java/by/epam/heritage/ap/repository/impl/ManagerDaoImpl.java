package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.model.Manager;
import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.ManagerDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(ManagerDaoImpl.class);

    private static final String FIND_MANAGER_BY_ID = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel , passport_number FROM users WHERE users.user_id = ? AND role_id=2";

    public ManagerDaoImpl()  {
    }

    @Override
    public Manager findByid(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        String idString = String.valueOf(id);
        Manager manager = new Manager();

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_MANAGER_BY_ID);
            statement.setString(1, idString);
            rs = statement.executeQuery();

            while (rs.next()) {

                manager.setGuestId(rs.getInt(1));
                manager.setRoleId(rs.getInt(2));
                manager.setLogin(rs.getString(3));
                manager.setName(rs.getString(4));
                manager.setSurname(rs.getString(5));
                manager.setEmail(rs.getString(6));
                manager.setPhone(rs.getString(7));
                manager.setPassport(rs.getInt(8));
            }
            return manager;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        } finally {
            try {
                connectionPool.closeConnection(connection, statement, rs);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }

        }
    }

    @Override
    public List<Manager> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(Manager entity) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean add(Manager entity) throws DAOException {
        return false;
    }

}
