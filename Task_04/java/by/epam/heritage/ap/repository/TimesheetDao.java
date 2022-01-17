package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetDao extends BaseDAO<Timesheet> {

    List<Timesheet> findReservedApartmentsOnDate(LocalDate date) throws DAOException;

    boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws DAOException;
}
