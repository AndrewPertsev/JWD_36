package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Request;

import java.util.List;

public interface RequestDao extends BaseDAO<Request> {

    boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws DAOException;

    int findMaximumRequestid() throws DAOException;

    List<Request> findUnresponded() throws DAOException;
}
