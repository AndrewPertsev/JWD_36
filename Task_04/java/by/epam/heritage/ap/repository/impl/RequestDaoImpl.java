package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.RequestDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl implements RequestDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(RequestDaoImpl.class);

    private static final String DELETE_REQUEST_BY_ID = "DELETE FROM hotelappdb.request WHERE request.request_id = ?";
    private static final String INSERT_NEW_REQUEST = "INSERT INTO hotelappdb.request (request.user_id, request.menu_id , request.transfer_id, request.quantity_persons, request.category_id, distance, date_in, date_out, date_request,  responded) VALUES (?,?,?, ?,?,? ,?,?,?, ?)";
    private static final String FIND_MAXIMUM_REQUEST_ID = "SELECT MAX(request_id) FROM hotelappdb.request ";
    private final static String FIND_ALL_REQUESTS = "SELECT request_id,user_id ,menu_id ,transfer_id ,category_id,quantity_persons,date_in ,date_out ,distance ,date_request , responded FROM request ";
    private final static String FIND_REQUEST_BY_ID = "SELECT request_id,user_id ,menu_id ,transfer_id ,category_id,quantity_persons,date_in ,date_out ,distance ,date_request , responded FROM request WHERE request_id = ?";
    private final static String UPDATE_REQUEST_BY_ID = "UPDATE hotelappdb.request SET user_id =? , menu_id = ?, transfer_id = ?, quantity_persons = ?, category_id = ? ,distance= ?  ,date_in = ?, date_out = ?, date_request= ? , responded= ? WHERE (request_id = ?)";
    private static final String UPDATE_REQUEST_IS_RESPONDED_STATUS = "UPDATE hotelappdb.request SET responded = ? WHERE (request_id = ?)";

    public RequestDaoImpl() {
    }


    @Override
    public boolean update(Request request) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_REQUEST_BY_ID);

            statement.setInt(1, request.getGuestId());
            statement.setInt(2, request.getMenu());
            statement.setInt(3, request.getTransfer());
            statement.setInt(4, request.getQuantity());
            statement.setInt(5, request.getCategory());
            statement.setInt(6, ConstantsParametersAndAttributes.DEFAULT_DISTANCE);
            statement.setDate(7, Date.valueOf(request.getStart()));
            statement.setDate(8, Date.valueOf(request.getEnd()));
            statement.setDate(9, Date.valueOf(LocalDate.now()));
            statement.setBoolean(10, ConstantsParametersAndAttributes.DEFAULT_IS_RESPONDED);
            statement.setInt(11, request.getRequestId());

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
    public boolean add(Request request) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_NEW_REQUEST);

            statement.setInt(1, request.getGuestId());
            statement.setInt(2, request.getMenu());
            statement.setInt(3, request.getTransfer());
            statement.setInt(4, request.getQuantity());
            statement.setInt(5, request.getCategory());
            statement.setInt(6, ConstantsParametersAndAttributes.DEFAULT_DISTANCE);
            statement.setDate(7, Date.valueOf(request.getStart()));
            statement.setDate(8, Date.valueOf(request.getEnd()));
            statement.setDate(9, Date.valueOf(LocalDate.now()));
            statement.setBoolean(10, ConstantsParametersAndAttributes.DEFAULT_IS_RESPONDED);

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
    public Request findByid(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        String idSearch = String.valueOf(id);
        Request request = new Request();

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_REQUEST_BY_ID);
            statement.setString(1, idSearch);
            rs = statement.executeQuery();

            while (rs.next()) {
                request.setRequestId(rs.getInt(1));
                request.setGuestId(rs.getInt(2));
                request.setMenu(rs.getInt(3));
                request.setTransfer(rs.getInt(4));
                request.setCategory(rs.getInt(5));
                request.setQuantity(rs.getInt(6));
                request.setStart(LocalDate.parse(rs.getString(7)));
                request.setEnd(LocalDate.parse(rs.getString(8)));
                request.setDistance(rs.getInt(9));
                request.setDateRequest(LocalDate.parse(rs.getString(10)));
                request.setResponded(rs.getBoolean(11));

            }
            return request;

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
    public List<Request> findAll() throws DAOException {
        List<Request> requests = new ArrayList<>(0);
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ALL_REQUESTS);
            rs = statement.executeQuery();

            while (rs.next()) {
                Request request = new Request();

                request.setRequestId(rs.getInt(1));
                request.setGuestId(rs.getInt(2));
                request.setMenu(rs.getInt(3));
                request.setTransfer(rs.getInt(4));
                request.setCategory(rs.getInt(5));
                request.setQuantity(rs.getInt(6));
                request.setStart(LocalDate.parse(rs.getString(7)));
                request.setEnd(LocalDate.parse(rs.getString(8)));
                request.setDistance(rs.getInt(9));
                request.setDateRequest(LocalDate.parse(rs.getString(10)));
                request.setResponded(rs.getBoolean(11));

                requests.add(request);
            }
            return requests;

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
    public boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_REQUEST_IS_RESPONDED_STATUS);

            statement.setBoolean(1, isResponded);
            statement.setInt(2, idRequest);

            statement.executeUpdate();

        } catch (SQLException | PoolException e) {
            isResponded = false;
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }
            return isResponded;
        }
    }


    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_REQUEST_BY_ID);
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
    public int findMaximumRequestid() throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        int idMax = -1;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_MAXIMUM_REQUEST_ID);
            rs = statement.executeQuery();

            while (rs.next()) {
                idMax = rs.getInt(1);
            }
            return idMax;

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


}

