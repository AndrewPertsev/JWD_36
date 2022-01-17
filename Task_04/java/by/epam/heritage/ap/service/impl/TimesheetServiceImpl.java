package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Timesheet;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.TimesheetDao;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.TimesheetServiceable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class TimesheetServiceImpl implements TimesheetServiceable {
    private static final Logger logger = LogManager.getLogger(TimesheetServiceImpl.class);


    @Override
    public boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws ServiceException {
        boolean done = true;
        TimesheetDao timesheetDao = DaoFactory.getInstance().getTimesheetDao();
        try {
            done = timesheetDao.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);
        } catch (DAOException e) {
            logger.error("Service can't reserve apartment in the timesheet ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public List findAll() throws ServiceException {
        return null;
    }

    @Override
    public Timesheet findByid(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean add(Timesheet entity) throws ServiceException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean update(Timesheet entity) throws ServiceException {
        return false;
    }

}
