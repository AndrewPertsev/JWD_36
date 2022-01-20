package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.model.Role;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.RoleDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    private static final String FIND_ROLE_BY_ID = "SELECT role_id, role FROM roles WHERE role_id = ?";

    public RoleDaoImpl() {
    }


    @Override
    public Role findByid(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        Role role = new Role();
        String idString = String.valueOf(id);

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ROLE_BY_ID);
            statement.setString(1, idString);

            rs = statement.executeQuery();

            while (rs.next()) {
                role.setRoleId(rs.getInt(1));
                role.setRole(rs.getString(2));
            }
            return role;

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
    public List<Role> findAll() throws DAOException {
        return null;
    }


    @Override
    public boolean update(Role entity) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean add(Role entity) throws DAOException {
        boolean done = true;
        return false;
    }
}
