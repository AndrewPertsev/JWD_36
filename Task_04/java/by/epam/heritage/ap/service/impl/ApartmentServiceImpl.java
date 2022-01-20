package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.repository.ApartmentDao;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.service.ApartmentServiceable;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ApartmentServiceImpl implements ApartmentServiceable {
    private static final Logger logger = LogManager.getLogger(ApartmentServiceImpl.class);


    @Override
    public boolean add(Apartment entity) throws ServiceException {
        boolean done = true;
        ApartmentDao apartmentDao = DaoFactory.getInstance().getApartmentDao();

        try {
            done = apartmentDao.add(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done = true;
        ApartmentDao apartmentDao = DaoFactory.getInstance().getApartmentDao();

        try {
            done = apartmentDao.deleteByid(id);
        } catch (DAOException e) {
            logger.error("Service can't delete element ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public List<Apartment> findAll() throws ServiceException {
        List<Apartment> rooms = new ArrayList<>(0);
        ApartmentDao apartmentDao = DaoFactory.getInstance().getApartmentDao();
        try {
            rooms = apartmentDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return rooms;
    }


    @Override
    public List<Apartment> findApartmentsSuitableForRequest(Request request) throws ServiceException {
        List<Apartment> suitableApartments = new ArrayList<>(0);
        ApartmentDao apartmentDao = DaoFactory.getInstance().getApartmentDao();
        try {
            suitableApartments = apartmentDao.findApartmentsSuitableForRequest(request);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }

        return suitableApartments;
    }


    @Override
    public boolean update(Apartment apartment) throws ServiceException {
        boolean done = true;
        ApartmentDao apartmentDao = DaoFactory.getInstance().getApartmentDao();

        try {
            done = apartmentDao.updateApartment(apartment);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public Apartment findByid(int id) throws ServiceException {
        return null;
    }

}

