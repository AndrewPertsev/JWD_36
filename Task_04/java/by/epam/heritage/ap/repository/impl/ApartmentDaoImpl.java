package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.repository.ApartmentDao;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImpl implements ApartmentDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(ApartmentDaoImpl.class);

    private static final String FIND_APARTMENT_SUITABLE_REQUEST = "SELECT DISTINCT apartments.apartment_id , apartments.category_id, apartments.capacity, apartments.picture , apartments.description FROM apartments INNER JOIN request ON apartments.category_id=(?) LEFT JOIN timesheet ON timesheet.apartment_id=apartments.apartment_id WHERE ((NOT( (?)<=timesheet.reserved_date AND (?)>=timesheet.reserved_date)) OR (timesheet.reserved_date IS NULL )) AND apartments.capacity>=(?)";
    private final static String FIND_APARTMENT_BY_ID = "SELECT apartment_id , category_id, capacity, picture , description  FROM apartments WHERE apartment_id=?";
    private final static String FIND_ALL_APARTMENT = "SELECT apartment_id, category_id, capacity ,picture , description  FROM apartments";
    private static final String INSERT_NEW_APARTMENT = "INSERT INTO hotelappdb.apartments (category_id, capacity, picture, description, apartment_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_APARTMENT = "UPDATE hotelappdb.apartments SET  category_id = ?,capacity = ?, picture =?, description = ? WHERE (apartment_id = ?)";
    private static final String DELETE_APARTMENT_BY_ID = "DELETE FROM hotelappdb.apartments WHERE apartments.apartment_id = ?";

    public ApartmentDaoImpl() {
    }


    @Override
    public boolean updateApartment(Apartment apartment) throws DAOException {
        boolean done = true;
        int id = apartment.getApartmentId();
        int category = apartment.getCategory();
        int capacity = apartment.getCapacity();
        String description = apartment.getDescription();
        String picturePath = apartment.getPathToPicture();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_APARTMENT);

            statement.setInt(1, category);
            statement.setInt(2, capacity);
            statement.setString(3, picturePath);
            statement.setString(4, description);
            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (SQLException | PoolException e) {
            done = false;
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        } finally {
            try {
                connectionPool.closeConnection(connection, statement);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }
            return done;
        }
    }

    @Override
    public List<Apartment> findApartmentsSuitableForRequest(Request request) throws DAOException {
        List<Apartment> suitableApartments = new ArrayList<>(0);
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_APARTMENT_SUITABLE_REQUEST);

            statement.setString(1, String.valueOf(request.getCategory()));
            statement.setString(2, String.valueOf(request.getStart()));
            statement.setString(3, String.valueOf(request.getEnd()));
            statement.setString(4, String.valueOf(request.getQuantity()));

            rs = statement.executeQuery();

            while (rs.next()) {
                Apartment apartment = new Apartment();

                apartment.setApartmentId(rs.getInt(1));
                apartment.setCategory(rs.getInt(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPathToPicture(rs.getString(4));
                apartment.setDescription(rs.getString(5));

                suitableApartments.add(apartment);
            }
            return suitableApartments;

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
    public List<Apartment> findAll() throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        List<Apartment> rooms = new ArrayList<>(0);

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ALL_APARTMENT);
            rs = statement.executeQuery();

            while (rs.next()) {
                Apartment apartment = new Apartment();

                apartment.setApartmentId(rs.getInt(1));
                apartment.setCategory(rs.getInt(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPathToPicture(rs.getString(4));
                apartment.setDescription(rs.getString(5));

                rooms.add(apartment);
            }
            return rooms;

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
    public Apartment findByid(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        String idSearch = String.valueOf(id);
        Apartment apartment = new Apartment();

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_APARTMENT_BY_ID);
            statement.setString(1, idSearch);
            rs = statement.executeQuery();

            while (rs.next()) {
                apartment.setApartmentId(rs.getInt(1));
                apartment.setCategory(rs.getInt(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPathToPicture(rs.getString(4));
                apartment.setDescription(rs.getString(5));
            }
            return apartment;

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
    public boolean update(Apartment apartment) throws DAOException {
        boolean done = true;
        int id = apartment.getApartmentId();
        int category = apartment.getCategory();
        int capacity = apartment.getCapacity();
        String description = apartment.getDescription();
        String picturePath = apartment.getPathToPicture();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_APARTMENT);

            statement.setInt(1, category);
            statement.setInt(2, capacity);
            statement.setString(3, picturePath);
            statement.setString(4, description);
            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (SQLException | PoolException e) {
            done = false;
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }
            return done;
        }
    }

    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_APARTMENT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException | PoolException e) {
            done = false;
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }
            return done;
        }
    }


    @Override
    public boolean add(Apartment apartment) throws DAOException {
        boolean done = true;
        int id = apartment.getApartmentId();
        int category = apartment.getCategory();
        int capacity = apartment.getCapacity();
        String description = apartment.getDescription();
        String picturePath = apartment.getPathToPicture();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_NEW_APARTMENT);

            statement.setInt(1, category);
            statement.setInt(2, capacity);
            statement.setString(3, picturePath);
            statement.setString(4, description);
            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (SQLException | PoolException e) {
            done = false;
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }
            return done;
        }
    }

}

