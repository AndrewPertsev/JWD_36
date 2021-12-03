package by.epam.ap.hotelapp.repository.dao;

import by.epam.ap.hotelapp.model.Apartment;
import by.epam.ap.hotelapp.model.Offer;
import by.epam.ap.hotelapp.model.Request;
import by.epam.ap.hotelapp.model.User;
import by.epam.ap.hotelapp.repository.connection.PoolException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface UserDao {

    void addUser() throws PoolException;
    User findUserByLogin (String login) throws PoolException, SQLException;
    User findUserById(Integer id) throws PoolException;
    void updateUser() throws PoolException;
    void deleteUser() throws PoolException;

    boolean addRequest(Request request) throws PoolException;
    boolean deleteRequest(Request id) throws PoolException;

    List<Apartment> getSuitableApartments(Request id) throws PoolException;
    List<Apartment> getAllApartments() throws PoolException;

    List<Offer> getOffersByUserId(User id) throws PoolException;
    void deleteOfferByUserId(Offer id) throws PoolException;

    int getOffersByRequestId(Request id) throws PoolException;
    void deleteOfferByRequestId(Request id, Date date_from) throws PoolException;
    void updateOfferList() throws PoolException;

    void sortOfferByPriceCountOfBooks()  throws PoolException;  //services?
}
