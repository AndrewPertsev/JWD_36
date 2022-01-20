package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.TimesheetServiceable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class ReserveConfirmedDaysCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(ReserveConfirmedDaysCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int apartmentId;
        LocalDate bookedBefore;
        LocalDate bookedFrom;

        TimesheetServiceable timesheetService = ServiceFactory.getInstance().getTimesheetService();

        try {
            apartmentId = Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID));
            bookedFrom = LocalDate.parse(request.getParameter(PARAMETER_CHECK_IN_DATE));
            bookedBefore = LocalDate.parse(request.getParameter(PARAMETER_CHECK_OUT_DATE));
            timesheetService.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);//////////////////////////TODO
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


        boolean flag = true;///////TODO
        if (flag) {
            response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_OFFER_MANAGEMENT_PAGE&messageOffer=" + MESSAGE_SUCCESS);
        } else {
        }

    }

}
