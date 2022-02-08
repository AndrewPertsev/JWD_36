package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.model.Menu;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.MenuDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(MenuDaoImpl.class);

    private static final String FIND_MENU_BY_ID = "SELECT menu_id, menu, price_menu  FROM menu WHERE menu_id = ?";

    public MenuDaoImpl()  {
    }

    @Override
    public Menu findByid(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        Menu menu = new Menu();
        String idString = String.valueOf(id);

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_MENU_BY_ID);
            statement.setString(1, idString);
            rs = statement.executeQuery();

            while (rs.next()) {
                menu.setMenu(rs.getInt(1));
                menu.setMenuMode(rs.getString(2));
                menu.setPrice(new BigDecimal(rs.getInt(3)));
            }
            return menu;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        } finally {
            try {
                connectionPool.closeConnection(connection, statement, rs);
            } catch ( PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }

        }
    }

    @Override
    public List findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(Menu entity) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean add(Menu entity) throws DAOException {
        boolean done = true;
        return false;
    }


}
