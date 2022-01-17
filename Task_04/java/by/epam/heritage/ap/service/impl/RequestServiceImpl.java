package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.RequestDao;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestServiceable {
    private static final Logger logger = LogManager.getLogger(RequestServiceImpl.class);


    @Override
    public boolean add(Request entity) throws ServiceException {
        boolean done = true;
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            done = requestDao.add(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public boolean update(Request entity) throws ServiceException {
        boolean done = true;
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            done = requestDao.update((Request) entity);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    public boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws ServiceException {
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            requestDao.setRequestIsRespondedStatus(isResponded, idRequest);
        } catch (DAOException e) {
            logger.error("Service can't set request status ", e);
            throw new ServiceException(e);
        }
        return true;
    }


    @Override
    public Request findByid(int id) throws ServiceException {
        Request request = null;
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            request = requestDao.findByid(id);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return request;
    }


    @Override
    public List<Request> findAll() throws ServiceException {
        List<Request> request;
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            request = requestDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return request;
    }


    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done = true;
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            done = requestDao.deleteByid(id);
        } catch (DAOException e) {
            logger.error("Service can't delete element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public int findMaximumRequestid() throws ServiceException {
        int idMax = -1;
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        try {
            idMax = requestDao.findMaximumRequestid();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return idMax;
    }
}
