package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Manager;
import by.epam.heritage.ap.service.ManagerServiceable;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ManagerServiceImpl implements ManagerServiceable {
    private static final Logger logger = LogManager.getLogger(ManagerServiceImpl.class);

    @Override
    public List findAll() throws ServiceException {
        return null;
    }

    @Override
    public Manager findByid(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean add(Manager entity) throws ServiceException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean update(Manager entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done = true;
        return false;
    }
}

