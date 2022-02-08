package by.epam.heritage.ap.repository;


import by.epam.heritage.ap.model.Guest;

public interface GuestDao extends BaseDAO<Guest> {

    Guest findGuestByLogin(String login) throws DAOException;

    void updateNonGrataStatus(int id) throws DAOException;

    void updateVipStatus(int id) throws DAOException;


}
