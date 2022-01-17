package by.epam.heritage.ap.repository;


import by.epam.heritage.ap.model.Manager;
import by.epam.heritage.ap.model.User;

public interface ManagerDao extends BaseDAO<Manager> {

    User findManagerByLogin(String login) throws DAOException;

}
