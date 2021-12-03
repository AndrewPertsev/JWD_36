package by.epam.ap.hotelapp.repository.dao.impl;

import by.epam.ap.hotelapp.model.Apartment;
import by.epam.ap.hotelapp.model.Offer;
import by.epam.ap.hotelapp.model.Request;
import by.epam.ap.hotelapp.model.User;
import by.epam.ap.hotelapp.repository.connection.ConnectionPool;
import by.epam.ap.hotelapp.repository.connection.PoolException;
import by.epam.ap.hotelapp.repository.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends ConnectionPool implements UserDao {
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";


    @Override
    public User findUserByLogin(String login) throws PoolException {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setRole_id(resultSet.getInt("roles_id"));
                user.setAttribute_id(resultSet.getInt("attribute_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password")); ///Char[] array?????
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("tel"));
                user.setPassport(resultSet.getInt("passport_number"));
            }
            resultSet.close();
            preparedStatement.close();
            releaseConnection(con);

        } catch (SQLException e) {
            throw new PoolException("Error select user from db " + e);
        }
        return user;
        }


        @Override
        public User findUserById (Integer id) throws PoolException {
            User user = null;
            Connection connection = getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                //todo
            }
            return user;
        }

        @Override
        public void addUser () throws PoolException {

        }

        @Override
        public void updateUser () throws PoolException {

        }

        @Override
        public void deleteUser () throws PoolException {

        }

        @Override
        public boolean addRequest (Request request) throws PoolException {
            return false;
        }

        @Override
        public boolean deleteRequest (Request id) throws PoolException {
            return false;
        }

        @Override
        public List<Apartment> getSuitableApartments (Request id) throws PoolException {
            return null;
        }

        @Override
        public List<Apartment> getAllApartments () throws PoolException {
            return null;
        }

        @Override
        public List<Offer> getOffersByUserId (User id) throws PoolException {
            return null;
        }

        @Override
        public void deleteOfferByUserId (Offer id) throws PoolException {

        }

        @Override
        public int getOffersByRequestId (Request id) throws PoolException {
            return 0;
        }

        @Override
        public void deleteOfferByRequestId (Request id, Date date_from) throws PoolException {

        }

        @Override
        public void updateOfferList () throws PoolException {

        }

        @Override
        public void sortOfferByPriceCountOfBooks () throws PoolException {

        }
    }
