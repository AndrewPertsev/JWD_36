package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.GuestDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoImpl implements GuestDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(GuestDaoImpl.class);

    private static final String UPDATE_GUEST_PASSWORD_IN_USERS = "UPDATE hotelappdb.users SET password= ? WHERE (user_id = ?)";
    private static final String ADD_GUEST_INTO_USERS = "INSERT INTO hotelappdb.users (role_id,login, password, user_name, surname, email, tel, passport_number, country, comments, vip_status, nongrata_status) VALUES (?,?,?, ?,?,? ,?,?,?, ?,?,?)";
    private static final String UPDATE_GUEST = "UPDATE hotelappdb.users SET role_id =?, user_name = ?,surname = ?,email = ?, tel = ?, comments = ?, vip_status = ?, nongrata_status = ? WHERE (user_id = ?);";
    private static final String FIND_GUEST_BY_LOGIN = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel ,passport_number ,country ,vip_status ,nongrata_status ,comments ,password FROM users WHERE login = ? ";
    private static final String FIND_GUEST_BY_ID = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel ,passport_number ,country ,vip_status ,nongrata_status ,comments ,password FROM users WHERE users.user_id = ?";
    private static final String DELETE_GUEST_BY_ID = "DELETE FROM hotelappdb.users WHERE user_id= ?";
    private static final String FIND_ALL_GUEST = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel ,passport_number ,country ,vip_status ,nongrata_status ,comments FROM hotelappdb.users ";

    public GuestDaoImpl() {
    }

    @Override
    public List<Guest> findAll() throws DAOException {

        List<Guest> guests = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_ALL_GUEST);

            rs = statement.executeQuery();

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestId(rs.getInt(1));
                guest.setRoleId(rs.getInt(2));
                guest.setLogin(rs.getString(3));
                guest.setName(rs.getString(4));
                guest.setSurname(rs.getString(5));
                guest.setEmail(rs.getString(6));
                guest.setPhone(rs.getString(7));
                guest.setPassport(rs.getInt(8));
                guest.setCountry(rs.getString(9));
                guest.setVip(rs.getBoolean(10));
                guest.setNonGrata(rs.getBoolean(11));
                guest.setComment(rs.getString(12));

                guests.add(guest);
            }
            return guests;

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
    public boolean update(Guest guest) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_GUEST);

            statement.setInt(1, guest.getRoleId());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getSurname());
            statement.setString(4, guest.getEmail());
            statement.setString(5, guest.getPhone());
            statement.setString(6, guest.getComment());
            statement.setBoolean(7, guest.isVip());
            statement.setBoolean(8, guest.isNonGrata());
            statement.setInt(9, guest.getGuestId());


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
    public boolean add(Guest guest) throws DAOException {
        boolean done = true;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_GUEST_INTO_USERS);

            statement.setInt(1, ConstantsParametersAndAttributes.DEFAULT_ROLE_ID);
            statement.setString(2, guest.getLogin());
            statement.setString(3, guest.getPassword());
            statement.setString(4, guest.getName());
            statement.setString(5, guest.getSurname());
            statement.setString(6, guest.getEmail());
            statement.setString(7, guest.getPhone());
            statement.setInt(8, guest.getPassport());
            statement.setString(9, guest.getCountry());
            statement.setString(10, ConstantsParametersAndAttributes.DEFAULT_COMMENT);
            statement.setBoolean(11, ConstantsParametersAndAttributes.DEFAULT_VIP_STATUS);
            statement.setBoolean(12, ConstantsParametersAndAttributes.DEFAULT_NONGRATA_STATUS);
//            statement.setBoolean(13, user.isActive());

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

    public boolean updatePassword(int idGuest, String newPassword) throws DAOException {
        boolean done = true;
        String hashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_GUEST_PASSWORD_IN_USERS);

            statement.setString(1, hashPassword);
            statement.setInt(2, idGuest);

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
    public Guest findGuestByLogin(String login) throws DAOException {
        Guest guest = new Guest();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_GUEST_BY_LOGIN);
            statement.setString(1, login);


            rs = statement.executeQuery();

            while (rs.next()) {
                guest.setGuestId(rs.getInt(1));
                guest.setRoleId(rs.getInt(2));
                guest.setLogin(rs.getString(3));
                guest.setName(rs.getString(4));
                guest.setSurname(rs.getString(5));
                guest.setEmail(rs.getString(6));
                guest.setPhone(rs.getString(7));
                guest.setPassport(rs.getInt(8));
                guest.setCountry(rs.getString(9));
                guest.setVip(rs.getBoolean(10));
                guest.setNonGrata(rs.getBoolean(11));
                guest.setComment(rs.getString(12));
                guest.setPassword(rs.getString(13));
            }
            return guest;

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
    public Guest findByid(int id) throws DAOException {
        String idString = String.valueOf(id);
        Guest guest = new Guest();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_GUEST_BY_ID);
            statement.setString(1, idString);

            rs = statement.executeQuery();

            while (rs.next()) {
                guest.setGuestId(rs.getInt(1));
                guest.setRoleId(rs.getInt(2));
                guest.setLogin(rs.getString(3));
                guest.setName(rs.getString(4));
                guest.setSurname(rs.getString(5));
                guest.setEmail(rs.getString(6));
                guest.setPhone(rs.getString(7));
                guest.setPassport(rs.getInt(8));
                guest.setCountry(rs.getString(9));
                guest.setVip(rs.getBoolean(10));
                guest.setNonGrata(rs.getBoolean(11));
                guest.setComment(rs.getString(12));
                guest.setPassword(rs.getString(13));
            }
            return guest;

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
    public void updateVipStatus(int id) throws DAOException {
        boolean done = true;
    }

    @Override
    public void updateNonGrataStatus(int id) throws DAOException {
        boolean done = true;
    }


    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        return false;
    }


}


