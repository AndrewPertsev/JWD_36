package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.GuestDao;
import by.epam.heritage.ap.service.GuestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class GuestServiceImpl implements GuestServiceable {
    private static final Logger logger = LogManager.getLogger(GuestServiceImpl.class);

    @Override
    public Guest findById(int idGuest) throws ServiceException {
        Guest guest = new Guest();
        GuestDao guestDao = DaoFactory.getInstance().getGuestDao();
        try {
            guest = guestDao.findByid(idGuest);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return guest;

    }

    @Override
    public boolean add(Guest entity) throws ServiceException {
        boolean done = true;
        GuestDao guestDao = DaoFactory.getInstance().getGuestDao();
        try {
            done = guestDao.add((Guest) entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public boolean update(Guest entity) throws ServiceException {
        boolean done = true;
        GuestDao guestDao = DaoFactory.getInstance().getGuestDao();
        try {
            done = guestDao.update(entity);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        if (done == false) {
            logger.error("Service  command can't update element ");
        }
        return done;
    }


    public final Guest checkGuestAuthorization(String login, String passwordToCheck) throws ServiceException {
        boolean isValidPassword;
        Guest validGuest = null;

        GuestDao guestDao = DaoFactory.getInstance().getGuestDao();

        try {
            validGuest = guestDao.findGuestByLogin(login);
        } catch (DAOException e) {
            logger.error("Service can't  find  guest", e);
            throw new ServiceException(e);
        }
        if (validGuest == null) {
            logger.error("Service can't validate null guest ");
            return null;
        }
        if (validGuest.getGuestId() == 0) {
            logger.error("Service can't validate guest id");
            return null;
        }
        if (validGuest.isNonGrata()) {
            logger.error("Service can't validate non grata status ");
            return null;
        }
        isValidPassword = BCrypt.checkpw(passwordToCheck, validGuest.getPassword());
        if (!isValidPassword) {
            logger.error("Service can't validate password or login");
            return null;
        }
        return validGuest;
    }


    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done = true;
        return false;
    }


    @Override
    public List<Guest> findAll() throws ServiceException {
        List<Guest> list = new ArrayList<>();
        GuestDao guestDao = DaoFactory.getInstance().getGuestDao();
        try {
            list = guestDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return list;

    }


}