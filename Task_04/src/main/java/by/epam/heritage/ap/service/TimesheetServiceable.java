package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Timesheet;

import java.time.LocalDate;

public interface TimesheetServiceable extends Serviceable<Timesheet> {

    boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws ServiceException;
}
