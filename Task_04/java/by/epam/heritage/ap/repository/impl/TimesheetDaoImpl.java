package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.Timesheet;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.TimesheetDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class TimesheetDaoImpl implements TimesheetDao {
    ConnectionPool connectionPool= ConnectionPool.getInstance();
    private static  final Logger logger = LogManager.getLogger(TimesheetDaoImpl.class);

    private final static String FIND_TIMESHEET_BY_ID = "SELECT id_timesheet, apartment_id, reserved_date, isreserved FROM timesheet WHERE id_timesheet = ?";
    private final static String INSERT_INTO_TIMESHEET_CONFIRMED_DAYS = "INSERT INTO hotelappdb.timesheet SET apartment_id=? , isreserved=?, reserved_date=?";

    public TimesheetDaoImpl()  {
    }

    @Override
    public boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws DAOException {
        boolean done =true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(INSERT_INTO_TIMESHEET_CONFIRMED_DAYS);

            Duration duration = Duration.between(bookedFrom.atStartOfDay(), bookedBefore.atStartOfDay());
            long durationDays = duration.toDays();
            for (int i = 0; i < durationDays; i++) {
                bookedFrom = bookedFrom.plusDays(1);

                statement.setInt(1, apartmentId);
                statement.setBoolean(2, ConstantsParametersAndAttributes.IS_RESERVED_APARTMENT_TRUE);
                statement.setDate(3, java.sql.Date.valueOf(bookedFrom));

//            statement.setDate(6, java.sql.Date.valueOf(offer.getBookedBefore()));
//            statement.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
//            statement.setDate(8, java.sql.Date.valueOf(bookedFrom));
//            statement.setDate(9, java.sql.Date.valueOf(LocalDate.now()));

                statement.executeUpdate();
            }
        } catch (SQLException | PoolException e) {
            done=false;
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement);
            } catch ( PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }
            return done;
        }
    }


    @Override
    public Timesheet findByid(int id) throws DAOException {
        String idSearch = String.valueOf(id);
        Timesheet timesheet = new Timesheet();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_TIMESHEET_BY_ID);
            statement.setString(1, idSearch);

            rs = statement.executeQuery();

            while (rs.next()) {
                timesheet.setTimesheetId(rs.getInt(1));
                timesheet.setApartmentId(rs.getInt(2));
                timesheet.setReservedDate(LocalDate.parse(rs.getString(3)));
                timesheet.setIsReserved(rs.getBoolean(4));
            }
            return timesheet;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement, rs);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }

        }
    }

    @Override
    public List findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(Timesheet entity) throws DAOException {
        boolean done =true;
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done =true;
        return false;
    }

    @Override
    public boolean add(Timesheet entity) throws DAOException {
        boolean done =true;
        return false;
    }

    @Override
    public List<Timesheet> findReservedApartmentsOnDate(LocalDate date) throws DAOException {
        return null;
    }


}
