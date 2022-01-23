package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.OfferDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfferDaoImpl implements OfferDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(OfferDaoImpl.class);

    private static final String FIND_OFFER_BY_USER_ID = "SELECT offer_id, offer.request_id, offer.transfer_id, offer.menu_id, offer.apartment_id, booked_from, booked_before, date_sent, date_paid, date_closed,  is_sent,is_paid,is_closed , price_offer, manager_id, offer.quantity_persons FROM offer INNER JOIN request ON offer.request_id=request.request_id WHERE  request.user_id= ?";
    private static final String FIND_OFFER_BY_ID = "SELECT offer_id, request_id, transfer_id, menu_id, apartment_id, booked_from, booked_before, date_sent, date_paid, date_closed,  is_sent,is_paid,is_closed , price_offer, manager_id, quantity_persons FROM offer WHERE offer_id = ?";
    private static final String FIND_ALL_OFFERS = "SELECT offer_id, request_id, transfer_id, menu_id, apartment_id, booked_from, booked_before, date_sent, date_paid, date_closed,  is_sent,is_paid,is_closed , price_offer,manager_id, quantity_persons FROM offer ";
    private static final String INSERT_NEW_OFFER = "INSERT INTO hotelappdb.offer (request_id, transfer_id, menu_id, apartment_id, booked_from, booked_before, date_sent, date_paid, date_closed,  is_sent,is_paid,is_closed , price_offer,manager_id, quantity_persons) VALUES (?,?,?, ?,?,? ,?,?,?, ?,?,? ,?,?,?)";
    private static final String INSERT_OFFER_SENT_STATUS = "UPDATE hotelappdb.offer SET is_sent= ? WHERE (offer_id = ?)";
    private static final String INSERT_OFFER_PAID_STATUS = "UPDATE hotelappdb.offer SET is_paid= ? WHERE (offer_id = ?)";
    private static final String INSERT_OFFER_CLOSED_STATUS = "UPDATE hotelappdb.offer SET is_closed= ? WHERE (offer_id = ?)";
    private static final String UPDATE_OFFER_STATUS_AND_PRICE = "UPDATE hotelappdb.offer SET is_sent=?, is_paid=?, is_closed=?, price_offer=? WHERE (offer_id = ?)";


    public OfferDaoImpl() {
    }


    @Override
    public boolean setOfferSentStatus(int offerId, boolean status) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_OFFER_SENT_STATUS);

            statement.setBoolean(1, status);
            statement.setInt(2, offerId);

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
    public boolean setOfferClosedStatus(int offerId, boolean status) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_OFFER_CLOSED_STATUS);

            statement.setBoolean(1, status);
            statement.setInt(2, offerId);

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
    public boolean setOfferPaidStatus(int offerId, boolean status) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_OFFER_PAID_STATUS);

            statement.setBoolean(1, status);
            statement.setInt(2, offerId);

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
    public boolean add(Offer offer) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_NEW_OFFER);

            statement.setInt(1, offer.getRequestId());
            statement.setInt(2, offer.getTransfer());
            statement.setInt(3, offer.getMenu());
            statement.setInt(4, offer.getApartmentId());
            statement.setDate(5, java.sql.Date.valueOf(offer.getStart()));
            statement.setDate(6, java.sql.Date.valueOf(offer.getEnd()));
            statement.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            statement.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
            statement.setDate(9, java.sql.Date.valueOf(LocalDate.now()));
            statement.setBoolean(10, ConstantsParametersAndAttributes.DEFAULT_IS_OFFER_SENT);
            statement.setBoolean(11, ConstantsParametersAndAttributes.DEFAULT_IS_OFFER_PAID);
            statement.setBoolean(12, ConstantsParametersAndAttributes.DEFAULT_IS_OFFER_CLOSED);
            statement.setInt(13, offer.getPriceOffer().intValue());
            statement.setInt(14, offer.getManagerId());
            statement.setInt(15, offer.getQuantity());

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
    public Offer findByid(int id) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        Offer offer = new Offer();
        String idSearch = String.valueOf(id);

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_OFFER_BY_ID);
            statement.setString(1, idSearch);
            rs = statement.executeQuery();

            while (rs.next()) {

                offer.setOfferId(rs.getInt(1));
                offer.setRequestId(rs.getInt(2));
                offer.setTransfer(rs.getInt(3));
                offer.setMenu(rs.getInt(4));
                offer.setApartmentId(rs.getInt(5));
                offer.setStart(LocalDate.parse(rs.getString(6)));
                offer.setEnd(LocalDate.parse(rs.getString(7)));
                offer.setDateSent(LocalDate.parse(rs.getString(8)));
                offer.setDatePaid(LocalDate.parse(rs.getString(9)));
                offer.setDateClosed(LocalDate.parse(rs.getString(10)));
                offer.setSent(rs.getBoolean(11));
                offer.setPaid(rs.getBoolean(12));
                offer.setClosed(rs.getBoolean(13));
                offer.setPriceOffer(new BigDecimal(rs.getInt(14)));
                offer.setManagerId(rs.getInt(15));
                offer.setQuantity(rs.getInt(16));
            }

            return offer;

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
    public List<Offer> getOffersByGuestId(int idUser) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        String id = String.valueOf(idUser);
        List<Offer> offers = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_OFFER_BY_USER_ID);
            statement.setString(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {

                Offer offer = new Offer();

                offer.setOfferId(rs.getInt(1));
                offer.setRequestId(rs.getInt(2));
                offer.setTransfer(rs.getInt(3));
                offer.setMenu(rs.getInt(4));
                offer.setApartmentId(rs.getInt(5));
                offer.setStart(LocalDate.parse(rs.getString(6)));
                offer.setEnd(LocalDate.parse(rs.getString(7)));
                offer.setDateSent(LocalDate.parse(rs.getString(8)));
                offer.setDatePaid(LocalDate.parse(rs.getString(9)));
                offer.setDateClosed(LocalDate.parse(rs.getString(10)));
                offer.setSent(rs.getBoolean(11));
                offer.setPaid(rs.getBoolean(12));
                offer.setClosed(rs.getBoolean(13));
                offer.setPriceOffer(new BigDecimal(rs.getInt(14)));
                offer.setManagerId(rs.getInt(15));
                offer.setQuantity(rs.getInt(16));

                offers.add(offer);
            }
            return offers;

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
    public List<Offer> findAll() throws DAOException {
        List<Offer> offers = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ALL_OFFERS);
            rs = statement.executeQuery();

            while (rs.next()) {

                Offer offer = new Offer();

                offer.setOfferId(rs.getInt(1));
                offer.setRequestId(rs.getInt(2));
                offer.setTransfer(rs.getInt(3));
                offer.setMenu(rs.getInt(4));
                offer.setApartmentId(rs.getInt(5));
                offer.setStart(LocalDate.parse(rs.getString(6)));
                offer.setEnd(LocalDate.parse(rs.getString(7)));
                offer.setDateSent(LocalDate.parse(rs.getString(8)));
                offer.setDatePaid(LocalDate.parse(rs.getString(9)));
                offer.setDateClosed(LocalDate.parse(rs.getString(10)));
                offer.setSent(rs.getBoolean(11));
                offer.setPaid(rs.getBoolean(12));
                offer.setClosed(rs.getBoolean(13));
                offer.setPriceOffer(new BigDecimal(rs.getInt(14)));
                offer.setManagerId(rs.getInt(15));
                offer.setQuantity(rs.getInt(16));

                offers.add(offer);
            }
            return offers;

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
    public boolean update(Offer offer) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_OFFER_STATUS_AND_PRICE);

            statement.setBoolean(1, offer.isSent());
            statement.setBoolean(2, offer.isPaid());
            statement.setBoolean(3, offer.isClosed());
            statement.setInt(4,Integer.parseInt(String.valueOf(offer.getPriceOffer())));
            statement.setInt(5, offer.getOfferId());

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
        return false;
    }

    @Override
    public List<Offer> getOfferByRequestId(int id) throws DAOException {
        boolean done = true;
        return null;
    }

}
