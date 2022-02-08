package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetDao extends BaseDAO<Timesheet> {


    boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws DAOException;
}
