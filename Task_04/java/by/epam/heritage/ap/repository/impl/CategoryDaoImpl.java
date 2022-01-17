package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.model.Category;
import by.epam.heritage.ap.repository.CategoryDao;
import by.epam.heritage.ap.repository.DAOException;
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

public class CategoryDaoImpl implements CategoryDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    private static final String GET_CATEGORY_PRICE_BY_CATEGORY_ID = "SELECT category_price FROM category_room WHERE category_id = ?";
    private static final String FIND_CATEGORY_BY_ID = "SELECT category_id, category_name, category_price FROM category_room WHERE category_id = ?";

    public CategoryDaoImpl() {
    }


    public Category findByid(int id) throws DAOException {
        String idString = String.valueOf(id);
        Category category = new Category();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_CATEGORY_BY_ID);
            statement.setString(1, idString);

            rs = statement.executeQuery();

            while (rs.next()) {
                category.setCategory(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                category.setCategoryPrice(new BigDecimal(rs.getInt(3)));
            }
            return category;

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
    public BigDecimal getCategoryPrice(int idCategory) throws DAOException {
        BigDecimal categoryPrice = BigDecimal.valueOf(-1);
        String idString = String.valueOf(idCategory);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(GET_CATEGORY_PRICE_BY_CATEGORY_ID);
            statement.setString(1, idString);

            rs = statement.executeQuery();

            while (rs.next()) {
                categoryPrice = new BigDecimal(rs.getInt(1));
            }
            return categoryPrice;

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

    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean update(Category entity) throws DAOException {
        return false;
    }

    @Override
    public boolean add(Category entity) throws DAOException {
        return false;
    }

    public List findAll() throws DAOException {
        return null;
    }
}

